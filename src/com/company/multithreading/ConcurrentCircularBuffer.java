package com.company.multithreading;

import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentCircularBuffer<E> {
    private final int _capacity;
    private final E[] _items;
    private AtomicInteger _head, _tail;

    public ConcurrentCircularBuffer(Class<E> type, int capacity) {
        if (0 != (capacity & (capacity - 1))) {
            throw new IllegalArgumentException("capacity should be power of 2");
        }

        _capacity = capacity;
        _items = (E[]) Array.newInstance(type, capacity);

        _head = new AtomicInteger(0);
        _tail = new AtomicInteger(0);
    }

    public boolean tryAdd(E item) {
        int curTail = _tail.get();
        while(true) {
            int nextTail = getNextPos(curTail);
            if(nextTail == _head.get())
                return false;

            if(_tail.compareAndSet(curTail, nextTail))
                break;

            curTail = _tail.get();
        }

        _items[curTail] = item;
        return true;
    }

    public E tryTake() {
        int curHead = _head.get();
        while(true) {
            if(curHead == _tail.get())
                return null;

            int nextHead = getNextPos(curHead);
            if(_head.compareAndSet(curHead, nextHead))
                break;

            curHead = _head.get();
        }
        return _items[curHead];
    }

    private int getNextPos(int pos) {
        return (pos + 1) & (_capacity - 1);
    }
}
