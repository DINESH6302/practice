package org.interview.practice.multithreading;

public class RunnableDemo implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " from Runnable_Demo");
    }
}
