package com.uh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ItOfIterators<T> implements Iterator<T> {

    private Iterator<Iterator<T>> iterators;
    private Iterator<T> current;

    public ItOfIterators(Iterator<Iterator<T>> iterators) {
        this.iterators = iterators;
    }

    @Override
    public boolean hasNext() {
        if (current == null || !current.hasNext()) {
            current = findNext();
        }
        return current != null && current.hasNext();
    }

    private Iterator<T> findNext() {
        while (iterators.hasNext()) {
            current = iterators.next();
            if (current.hasNext())
                return current;
        }
        return null;
    }

    @Override
    public T next() {
        return current.next();
    }

    @Override
    public void remove() {
        if (current != null) {
            current.remove();
        }
    }
    
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(5, 6, 7, 8));
        List<Iterator<Integer>> combined = new ArrayList<>(Arrays.asList(list1.iterator(), list2.iterator()));

        ItOfIterators ioi = new ItOfIterators(combined.iterator());
        while (ioi.hasNext()) {
             System.out.print(ioi.next());
        }
       
    }

}
