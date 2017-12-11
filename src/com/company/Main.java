package com.company;

import com.company.multithreading.ConcurrentCircularBuffer;
import com.company.multithreading.LockConsumer;
import com.company.multithreading.ThreadPoolWorker;
import com.company.multithreading.ThreadedWorker;
import com.company.multithreading.tests.CircularBufferTest;
import com.company.multithreading.tests.ConcurrentCircularBufferTest;
import com.company.practice.KMax;
import com.company.practice.NextSparseNumber;
import com.company.practice.ZigzagSort;
import com.company.practice.VLQ;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	    // write your code here

        //AlternateLinkedList practice = new AlternateLinkedList();
        //ZigzagSort practice = new ZigzagSort();
        //NextSparseNumber practice = new NextSparseNumber();
        //VLQ practice = new VLQ();
        //KMax practice = new KMax();
        //practice.run();

        try {
            //ThreadedWorker worker = new ThreadedWorker();
            //ThreadPoolWorker worker = new ThreadPoolWorker();
            //LockConsumer worker = new LockConsumer();
            //CircularBufferTest worker = new CircularBufferTest();
            ConcurrentCircularBufferTest worker = new ConcurrentCircularBufferTest();
            worker.run();
        }
        catch (Exception ex) {
            System.out.println("Caught exception in main()");
            ex.printStackTrace();
        }
    }
}


