package com.uh;

import java.util.LinkedList;

public class ProducerConsumer {

    public static void main(String[] args) throws InterruptedException {

        final Process p = new Process();

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    p.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    p.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}

class Process {

    LinkedList<Integer> queue = new LinkedList<>();
    int capacity = 2;

    public void produce() throws InterruptedException {
        int val = 0;
        ;
        while (true) {
            synchronized (this) {

                while (queue.size() == capacity)
                    wait();

                System.out.println("Producer produced:" + val);

                queue.add(val++);

                notify();

                Thread.sleep(1000);
            }
        }
    }

    public void consumer() throws InterruptedException {

        while (true) {
            synchronized (this) {
                while (queue.size() == 0)
                    wait();

                int val = queue.removeFirst();

                System.out.println("Consumer consumed:" + val);

                notify();

                Thread.sleep(1000);
            }
        }
    }

}