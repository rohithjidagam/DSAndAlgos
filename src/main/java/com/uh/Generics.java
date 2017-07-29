package com.uh;

import java.util.ArrayList;
import java.util.List;

public class Generics {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        HMap<String, Integer> si = new HMap("1", 1);
        HMap<Integer, Integer> ii = new HMap(1, 1);

        String[] strings = new String[10];

        Object[] objects = strings;

       // objects[0] = new Object();
        
        List<String> list = new ArrayList<>();
        list.add("hi");
        list.add("hi");
        
       // accept(list);
        
        
        int n = 23;
        int a = 0xAAAAAAAA;
        int b = 0x55555555;
        
        System.out.println(((int)a));
        System.out.println(((int)b));
        
        
        int even = n & a;
        int odd = n & b;
        
        even >>= 1;
        odd <<= 1;
        
        System.out.println(even | odd);
        
    }

    public static void accept(ArrayList<?> al) {
        for (Object o : al)
            System.out.println(o);
    }

}

class HMap<K, V> {
    K key;
    V value;

    public HMap(K k, V v) {
        key = k;
        value = v;
    }

    public <T> void swap(T key) {

    }
}
