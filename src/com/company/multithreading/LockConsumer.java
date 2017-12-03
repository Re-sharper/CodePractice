package com.company.multithreading;

import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class LockConsumer {
    private int value;

    public void run() {
        final MyConcurrentLinkedQueue<Integer> queue = new MyConcurrentLinkedQueue<Integer>();

        Thread producerThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                long id = Thread.currentThread().getId();
                for(int i=1; i<=100; i++) {
                    int value = i + 1000;
                    queue.offer(value);
                    System.out.println("Thread " + id + " : enqueue " + value);
                }
            }
        });
        Thread producerThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                long id = Thread.currentThread().getId();
                for(int i=1; i<=100; i++) {
                    int value = i + 2000;
                    queue.offer(value);
                    System.out.println("Thread " + id + " : enqueue " + value);
                }
            }
        });
        producerThread1.start();
        producerThread2.start();

        long id = Thread.currentThread().getId();
        for(int i=1; i<=200; i++) {
            System.out.println("Head = " + queue.poll() + ", size = " + queue.getSize());


            Integer value = queue.dequeue();
            System.out.println("Dequeue " + value);
        }
    }

    private class MyConcurrentLinkedQueue<E> {
        private final Node<E> dummy = new Node<E>(null);
        private Node<E> tail = dummy;
        private int size = 0;
        private ReentrantLock lock = new ReentrantLock();

        public boolean offer(E e) {
            final Node<E> node = new Node<E>(e);

            try {
                lock.lock();
                tail.next = node;
                tail = node;
                size++;
            }
            finally {
                lock.unlock();
            }

            return true;
        }

        public E dequeue() {
            E result = null;
            try {
                lock.lock();

                Node<E> head = dummy.next;
                if (head != null) {
                    dummy.next = head.next;
                    result = head.value;
                    size--;
                }
            }
            catch (Exception ex) {
                System.out.println("Caught exception: ");
                ex.printStackTrace();
                throw ex;
            }
            finally {
                lock.unlock();
            }
            return result;
        }

        public E poll() {
            try {
                lock.lock();
                Node<E> head = dummy.next;
                return head != null ? head.value : null;
            }
            finally {
                lock.unlock();
            }
        }

        public int getSize() {
            return size;
        }


        private class Node<E> {
            Node<E> next;
            E value;

            public Node(E value) {
                this.value = value;
            }

        }
    }
}
