package com.uh;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AZ extends BC implements AQ {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        AZ a = new AZ();
        a.play();

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            int n = iterator.next();
            if (n == 1) {
                iterator.remove();
                //list.remove(new Integer(n));
            }
        }

        System.out.println(list.size());

    }
    
    
}

interface AQ {
    default void play() {
        System.out.println("play interface");
    };
}

class BC implements AQ {
    @Override
    public void play() {
        System.out.println("play bc");
    }
}

class DC implements AQ {
    @Override
    public void play() {
        System.out.println("play dc");
    }
}

class DD implements AQ {

    void show() {
        System.out.println("show");
    }
}
