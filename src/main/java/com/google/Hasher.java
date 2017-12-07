package com.google;

import java.util.ArrayList;
import java.util.List;

public class Hasher<K, V> {

    private static class LinkedListNode<K, V> {

        public LinkedListNode next;
        public LinkedListNode prev;
        public K key;
        public V value;

        public LinkedListNode(K k, V v) {
            key = k;
            value = v;
        }
    }

    private List<LinkedListNode<K, V>> arr;

    public Hasher(int cap) {

        arr = new ArrayList(cap);
        for (int i = 0; i < cap; i++) {
            arr.add(null);
        }
    }

    private void put(K key, V value) {

        LinkedListNode<K, V> node = getNodeForKey(key);

        if (node != null) {
            node.value = value;
            return;
        }

        node = new LinkedListNode<K, V>(key, value);
        int index = getIndex(key);
        if (arr.get(index) != null) {
            node.next = arr.get(index);
            node.next.prev = node;
        }

        arr.set(index, node);
    }

    private LinkedListNode<K, V> getNodeForKey(K key) {

        int index = getIndex(key);

        LinkedListNode<K, V> node = arr.get(index);
        while (node != null) {

            if (node.key == key)
                return node;
            node = node.next;
        }

        return null;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode() % arr.size());
    }

    private V get(K key) {

        LinkedListNode<K, V> node = getNodeForKey(key);
        if (node == null)
            return null;
        return node.value;

    }

    private void remove(K key) {

        LinkedListNode<K, V> node = getNodeForKey(key);

        if (node == null)
            return;

        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            /* Removing head - update. */
            int hashKey = getIndex(key);
            arr.set(hashKey, node.next);
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }

    }

    public static void main(String[] args) {

        Hasher<Integer, String> map = new Hasher<>(5);
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        System.out.println(map.get(5));
        System.out.println(map.get(6));
        map.remove(5);
        System.out.println(map.get(5));
    }

}
