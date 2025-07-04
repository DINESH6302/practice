package org.interview.practice.race_condition;

import java.util.concurrent.atomic.AtomicInteger;

public class SharedData {
//    private volatile int counter = 0;
    AtomicInteger counter = new AtomicInteger();

    public int getCounter() {
        return counter.get();
    }

    public void incrementCounter() {
        counter.getAndIncrement();
//        counter++;
    }

    public void decrementCounter() {
        counter.getAndDecrement();
//        counter--;
    }
}
