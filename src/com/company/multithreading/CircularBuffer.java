package com.company.multithreading;

import java.lang.reflect.Array;

public class CircularBuffer<E> {
    private final int _capacity;
    private final E[] _items;

    private volatile int _head = 0;
    private volatile int _tail = 0;

    public CircularBuffer(Class<E> type, int capacity) {
        if(0 != (capacity & (capacity - 1)))
            throw new IllegalArgumentException("capacity should be power of 2");

        _capacity = capacity;
        _items = (E[])Array.newInstance(type, capacity);
    }

    public int count() {
        return (_tail - _head) & (_capacity - 1);
    }

    public boolean tryAdd(E item) {
        int curTail = _tail;
        int nextTail = getNextPos(curTail);

        // Collection full
        if(nextTail == _head)
            return false;

        _tail = nextTail;
        _items[curTail] = item;
        return true;
    }

    public E tryTake() {
        int curHead = _head;

        // Collection empty
        if(curHead == _tail)
            return null;

        int nextHead = getNextPos(curHead);
        _head = nextHead;
        E result = _items[curHead];
        return result;
    }

    private int getNextPos(int pos) {
        return (pos + 1) & (_capacity - 1);
    }
}
