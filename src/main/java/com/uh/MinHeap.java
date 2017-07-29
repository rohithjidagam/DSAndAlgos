package com.uh;

public class MinHeap {

    int[] arr;
    int capacity;
    int size;

    public MinHeap(int cap) {
        this.capacity = cap;
        arr = new int[cap];
        size = 0;
    }

    public static void main(String[] args) {

        MinHeap mH = new MinHeap(11);
        mH.insertKey(3);
        mH.insertKey(2);
        mH.deleteKey(1);
        mH.insertKey(15);
        mH.insertKey(5);
        mH.insertKey(4);
        mH.insertKey(45);
        
        
        System.out.println(mH.extractMin());
        System.out.println(mH.getMin());
        mH.decreaseKey(2, 1);
        System.out.println(mH.getMin());
        

    }
    int getMin() { return arr[0]; }
    
    int parent(int i) {
        return (i - 1) / 2;
    }

    int left(int i) {
        return (2 * i + 1);
    }

    int right(int i) {
        return (2 * i + 2);
    }

    void swap(int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    private void deleteKey(int key) {
        decreaseKey(key, Integer.MIN_VALUE);
        extractMin();
    }

    private int extractMin() {

        if (size <= 0)
            return Integer.MAX_VALUE;
        if (size == 1) {
            size--;
            return arr[0];
        }

        int root = arr[0];
        arr[0] = arr[size - 1];
        size--;
        MinHeapify(0);

        return root;
    }

    private void MinHeapify(int i) {

        int l = left(i);
        int r = right(i);
        int smallest = i;
        if (l < size && arr[l] < arr[i])
            smallest = l;
        if (r < size && arr[r] < arr[smallest])
            smallest = r;
        if (smallest != i) {
            swap(i, smallest);
            MinHeapify(smallest);
        }
    }

    private void decreaseKey(int i, int minValue) {

        arr[i] = minValue;
        while (i != 0 && arr[parent(i)] > arr[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public void insertKey(int key) {

        if (size == capacity) {
            System.out.println("Heap Full");
            return;
        }

        arr[size++] = key;
        int i = size - 1;

        while (i != 0 && arr[parent(i)] > arr[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

}
