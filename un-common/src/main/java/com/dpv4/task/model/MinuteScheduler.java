package com.dpv4.task.model;

import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * 按分钟调度任务的执行器。
 * 支持动态间隔（分钟数）、优雅启停、异常处理。
 */
public class MinuteScheduler {
    private final long intervalMinutes;
    private final Runnable task;
    private ScheduledExecutorService executor;
    private ScheduledFuture<?> future;

    /**
     * @param intervalMinutes 执行间隔（分钟），必须 > 0
     * @param task            要执行的任务
     */
    public MinuteScheduler(long intervalMinutes, Runnable task) {
        if (intervalMinutes <= 0) {
            throw new IllegalArgumentException("间隔必须大于0分钟");
        }
        this.intervalMinutes = intervalMinutes;
        this.task = task;
    }

    /**
     * 启动调度（立即执行第一次，然后按固定间隔执行）
     */
    public synchronized void start() {
        if (isRunning()) {
            return;
        }
        executor = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "MinuteScheduler-Thread");
            t.setDaemon(true);
            return t;
        });
        future = executor.scheduleAtFixedRate(
                () -> {
                    try {
                        task.run();
                    } catch (Exception e) {
                        // 记录异常，防止调度器停止
                        System.err.println("任务执行失败: " + e.getMessage());
                    }
                },
                0, intervalMinutes, TimeUnit.MINUTES
        );
    }

    /**
     * 停止调度，等待当前任务完成（最多5秒）
     */
    public synchronized void stop() {
        if (!isRunning()) {
            return;
        }
        future.cancel(false);      // 不中断正在运行的任务
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            executor.shutdownNow();
        }
    }

    /**
     * 检查调度器是否正在运行
     */
    public synchronized boolean isRunning() {
        return future != null && !future.isDone() && !future.isCancelled();
    }

    // 使用示例
    public static void main(String[] args) throws InterruptedException {
        MinuteScheduler scheduler = new MinuteScheduler(1, () -> 
            System.out.println("每分钟执行一次：" + System.currentTimeMillis())
        );
        scheduler.start();
        TimeUnit.MINUTES.sleep(5);
        scheduler.stop();
    }
}