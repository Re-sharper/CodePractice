package com.company.multithreading;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolWorker {
    public void run() throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final ThreadPoolExecutor executor = (ThreadPoolExecutor)executorService;
        System.out.println("Pool size: " + executor.getPoolSize() + ", max size: " + executor.getMaximumPoolSize());

        for(int i=0; i<100; i++) {
            final int taskIndex = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    long threadId = Thread.currentThread().getId();
                    int poolSize = executor.getPoolSize();
                    System.out.println("Thread " + threadId + " is running task " + taskIndex + " , pool size: " + poolSize);
                }
            });
        }

        System.in.read();
    }
}
