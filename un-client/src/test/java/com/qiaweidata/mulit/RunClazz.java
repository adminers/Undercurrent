package com.qiaweidata.mulit;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Title: RunClazz
 * @Description: RunClazz
 * @Company: www.qiaweidata.com
 * @author: shenshilong
 * @date: 2025-09-18
 * @version: V1.0
 */
public class RunClazz {

    public static void main(String[] args) {

        int size = 100;
        String nowSystemTime = "2025-9-18 10:07:09";
        int corePoolSize = Runtime.getRuntime().availableProcessors(); // 获取 CPU 核心数
        int maximumPoolSize = corePoolSize * 2; // 最大线程数设置为核心数的两倍
        ExecutorService service = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(size),
                new ItemThreadFactory(nowSystemTime + "-自动-"),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        for (int i = 0; i < size; i++) {
            service.execute(new UpdateDb());
        }
    }
}
