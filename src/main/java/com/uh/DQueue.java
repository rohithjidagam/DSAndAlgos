package com.uh;

public class DQueue {

    private static final int MAX = 99;

    int[] arr;
    int front;
    int rear;
    int size;
    int count;

    public DQueue(int s) {
        arr = new int[MAX];
        front = -1;
        rear = -1;
        size = s;
        count = 0;
    }

    public static void main(String[] args) {

        DQueue dq = new DQueue(8);
        dq.insertLast(7);
        dq.insertLast(6);
        dq.insertLast(5);
        dq.insertLast(1);
        dq.insertFirst(2);
        dq.insertFirst(3);
        dq.insertFirst(4);
        dq.insertFirst(8);
        dq.insertFirst(8);
        
    }

    private void insertFirst(int num) {

        if (isFull()) {
            System.out.println("Queue Full..");
        }

        if (front == -1) {
            front = 0;
            rear = 0;
        } else if (front == 0) {
            front = size-1;
        } else {
            front--;
        }

        arr[front] = num;
    }

    private void insertLast(int num) {

        if (isFull()) {
            System.out.println("Queue full..");
        }

        if (front == -1) {
            front = 0;
            rear = 0;
        } else if (rear == size - 1) {
            rear = 0;
        } else {
            rear++;
        }

        arr[rear] = num;

    }

    private boolean isFull() {
        return front == 0 && rear == size - 1 || front == rear + 1;
    }

}
