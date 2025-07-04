package org.interview.practice.race_condition;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SharedData data = new SharedData();

        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 10000000; i++) {
                data.incrementCounter();
            }
            System.out.println("t1 completed");
        });

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 10000000; i++) {
                data.decrementCounter();
            }
            System.out.println("t2 completed");
        });

        t1.start();
        t2.start();

        t2.join();
        t1.join();

        System.out.println("ProduceConsumerDemo Completed");
        System.out.println(data.getCounter());
    }
}
