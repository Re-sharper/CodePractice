package com.company.multithreading.tests;

import com.company.multithreading.ConcurrentCircularBuffer;

import java.util.Random;

public class ConcurrentCircularBufferTest {
    public void run() {
        ConcurrentCircularBuffer<Integer> buffer = new ConcurrentCircularBuffer<>(Integer.class, 32);

        int producerCount = 2;
        Thread[] producers = new Thread[producerCount];
        for(int i =0; i<producerCount; i++)
            producers[i] = new Thread(new Producer(buffer, Integer.toString(i)));

        int consumerCount = 2;
        Thread[] consumers = new Thread[consumerCount];
        for(int i =0; i<consumerCount; i++)
            consumers[i] = new Thread(new Consumer(buffer, Integer.toString(i)));

        for(int i=0; i<producerCount; i++)
            producers[i].start();

        for(int i=0; i<consumerCount; i++)
            consumers[i].start();

        try {
            System.in.read();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

class Producer implements Runnable {
    private final ConcurrentCircularBuffer<Integer> _buffer;
    private final String _id;
    private final Random _random = new Random();

    public Producer(ConcurrentCircularBuffer<Integer> buffer, String id) {
        _buffer = buffer;
        _id = id;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++) {
            Integer num = _random.nextInt(100);
            boolean result = _buffer.tryAdd(num);
            System.out.println("Producer " + _id + " : try add " + num + " , result = " + result);
        }
    }
}

class Consumer implements Runnable {
    private final ConcurrentCircularBuffer<Integer> _buffer;
    private final String _id;

    public Consumer(ConcurrentCircularBuffer<Integer> buffer, String id) {
        _buffer = buffer;
        _id = id;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++) {
            Integer num = _buffer.tryTake();
            System.out.println("Consumer " + _id + " : try take, result = " + num);
        }
    }
}