package org.interview.practice.producer_consumer;

import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue {
    Queue<Integer> sharedQueue = new LinkedList<>();
    int capacity = 5;

    public synchronized void produce(int item) throws InterruptedException {
        while(sharedQueue.size() == capacity) {
            System.out.println("Queue is full. Producer waiting...");
            wait();
        }

        sharedQueue.add(item);
        System.out.println("Produced : " + item);
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        while(sharedQueue.isEmpty()) {
            System.out.println("Queue is empty. Consumer waiting...");
            wait();
        }

        System.out.println("Consumed : " + sharedQueue.poll());
        notify();
    }
}
