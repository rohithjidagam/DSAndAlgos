package com.uh;

import java.util.Iterator;

public class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> iterator;
    private int peekedElement;
    private boolean hasPeekElement;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        peekedElement = 0;
        hasPeekElement = false;

    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (!hasPeekElement) {
            peekedElement = iterator.next();
            hasPeekElement = true;
        }
        return peekedElement;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        int res = 0;
        if (!hasPeekElement) {
            res = iterator.next();
        } else {
            res = peekedElement;
            hasPeekElement = false;
        }

        return res;
    }

    @Override
    public boolean hasNext() {
        return hasPeekElement == true || iterator.hasNext();
    }
}
