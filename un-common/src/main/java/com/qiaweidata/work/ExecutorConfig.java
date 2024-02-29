package com.qiaweidata.work;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorConfig {

    public ExecutorService executorService() {
        return new ThreadPoolExecutor(
            2,
            1,
            0,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(6),
            null,
            new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }
}