package org.interview.practice.multithreading;

public class ThreadDemo extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " from Thread_Demo");
    }
}
