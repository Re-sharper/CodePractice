package com.company.multithreading;

import java.io.IOException;

public class ThreadedWorker {
    public void run() {

        long mainThreadId = Thread.currentThread().getId();
        System.out.println("Main Thread: " + mainThreadId);

        Thread thread = new Thread(new MyRunnable());
        thread.start();

        try {
            byte[] a = new byte[128];
            System.in.read(a);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        long threadId = Thread.currentThread().getId();
        System.out.println("New Thread: " + threadId);
    }
}
