package com.qiaweidata.mulit;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title: ItemThreadFactory
 * @Description: ItemThreadFactory
 * @Company: www.qiaweidata.com
 * @author: shenshilong
 * @date: 2025-09-16
 * @version: V1.0
 */
public class ItemThreadFactory implements ThreadFactory {

    private final String namePrefix;

    private final AtomicInteger nextId = new AtomicInteger(1);

    /**
     * 定义线程组名称，在 jstack 问题排查时，非常有帮助
     *
     * @param whatFeaturOfGroup
     */
    public ItemThreadFactory(String whatFeaturOfGroup) {
        namePrefix = "From ItemThreadFactory's " + whatFeaturOfGroup + "-Worker-";
    }

    /**
     * Constructs a new {@code Thread}.  Implementations may also initialize priority, name, daemon status, {@code
     * ThreadGroup}, etc.
     *
     * @param r a runnable to be executed by new thread instance
     * @return constructed thread, or {@code null} if the request to create a thread is rejected
     */
    @Override
    public Thread newThread(Runnable task) {

        String name = namePrefix + nextId.getAndIncrement();
        Thread thread = new Thread(null, task, name, 0);
        return thread;
    }
}
