package com.uh;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    // DLL and HashMap
    int capacity;
    DNode head;
    DNode end;
    Map<Integer, DNode> data;

    public LRUCache(int c) {
        capacity = c;
        data = new HashMap<>();
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(5);
        cache.set(1, 1);
        cache.set(2, 2);
        cache.set(3, 3);
        System.out.println(cache.head.key);
        System.out.println(cache.get(2));
        System.out.println(cache.head.key);
        cache.set(4, 4);
        cache.set(5, 5);
        cache.set(5, 6);
        cache.set(6, 1);
        System.out.println(cache.get(1)); // return -1 as it is deleted by this point. 
        cache.set(3, 4);
        System.out.println(cache.get(2));
        
        
        
        DNode head = cache.head;
        while(head != null){
            System.out.println(head.key + "--" + head.value);
            head = head.next;
        }
        
        System.out.println(cache.end.key);

    }

    private int get(int key) {

        //present in cache
        if(data.containsKey(key)){
            
            DNode node = data.get(key);
            moveFirst(node);
            
            return node.value;
        }
        
        //Not Found
        return -1;
        
    }

    private void set(int key, int value) {

        //already present
        if (data.containsKey(key)) {
            DNode node = data.get(key);
            node.value = value;
            moveFirst(node);
            return;
        }
        
        //out of capacity
        if(data.size() >= capacity){
            int id = end.key;
            removeLast();
            data.remove(id);
        }
        
        //new entry
        DNode node = new DNode();
        node.key = key;
        node.value = value;
        addFirst(node);
        data.put(key, node);
        
    }

    private void removeLast() {
        remove(end);
    }

    private void moveFirst(DNode node) {
        remove(node);
        addFirst(node);
    }

    private void addFirst(DNode node) {

        //reset values asd it is first node to be inserted
        node.next = null;
        node.prev = null;
        
        //first element
        if(head == null){
            head = node;
            end = node;
            return;
        }
        
        //insert first
        head.prev = node;
        node.next = head;
        head = node;
    }

    private void remove(DNode node) {

        if(head == null || node == null)
            return;
        
        //only item
        if(head == end && head == node){
            head = null;
            end = null;
            return;
        }
        
        //remove from head
        if(head == node){
            head.next.prev = null;
            head = head.next;
            return;
        }
        
        //remove from end
        if(node == end){
            end.prev.next = null;
            end = end.prev;
            return;
        }
        
        //remove from middle
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

}

class DNode {
    int key;
    int value;
    DNode next;
    DNode prev;

}
