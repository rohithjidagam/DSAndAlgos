package com.google;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PeekingIterator implements Iterator<Integer> {

    Iterator<Integer> it;
    Integer next;
    Integer prev;

    public PeekingIterator(Iterator<Integer> it) {
        this.it = it;
        prev = null;
        if (it.hasNext())
            next = it.next();
    }

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        PeekingIterator p = new PeekingIterator(list.iterator());

        while (p.hasNext()) {
            System.out.println(p.prev() + "--" + p.next() + "--" + p.peek());
        }
    }

    public Integer peek() {
        return next;
    }

    public boolean hasPrev() {
        return prev != null;
    }

    public Integer prev() {
        Integer val = prev;
        prev = next != null ? next : null;
        return val;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public Integer next() {
        Integer val = next;
        next = it.hasNext() ? it.next() : null;
        return val;
    }

}
