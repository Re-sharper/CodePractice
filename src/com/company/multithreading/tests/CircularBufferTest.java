package com.company.multithreading.tests;

import com.company.multithreading.CircularBuffer;

public class CircularBufferTest {
    public void run() {
        CircularBuffer<Integer> buffer = new CircularBuffer<Integer>(Integer.class, 4);
        int item;
        boolean result;

        item = 100;
        result = buffer.tryAdd(item);
        System.out.println("tryAdd: item = " + item + " result = " + result + " , count = " + buffer.count());

        item = 200;
        result = buffer.tryAdd(item);
        System.out.println("tryAdd: item = " + item + " result = " + result + " , count = " + buffer.count());

        item = 300;
        result = buffer.tryAdd(item);
        System.out.println("tryAdd: item = " + item + " result = " + result + " , count = " + buffer.count());

        item = 400;
        result = buffer.tryAdd(item);
        System.out.println("tryAdd: item = " + item + " result = " + result + " , count = " + buffer.count());

        item = buffer.tryTake();
        System.out.println("tryTake: item = " + item + " , count = " + buffer.count());

        item = buffer.tryTake();
        System.out.println("tryTake: item = " + item + " , count = " + buffer.count());

        item = 500;
        result = buffer.tryAdd(item);
        System.out.println("tryAdd: item = " + item + " result = " + result + " , count = " + buffer.count());

        item = buffer.tryTake();
        System.out.println("tryTake: item = " + item + " , count = " + buffer.count());

        item = buffer.tryTake();
        System.out.println("tryTake: item = " + item + " , count = " + buffer.count());
    }
}
