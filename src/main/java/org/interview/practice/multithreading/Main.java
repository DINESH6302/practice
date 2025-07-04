package org.interview.practice.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("ProduceConsumerDemo Thread : " + Thread.currentThread().getName() + "\n");

        // THREAD
        Thread t1 = new ThreadDemo();
        t1.start();

        new Thread(new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

        new Thread(() -> {System.out.println(Thread.currentThread().getName());}).start();


        // RUNNABLE
        RunnableDemo rd = new RunnableDemo();
        Thread runnable1 = new Thread(rd);
        runnable1.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();


        //CALLABLE
        ExecutorService es = Executors.newSingleThreadExecutor();

        Callable<Integer> callable = new CallableDemo();
        Future<Integer> future = es.submit(callable);

        System.out.println(future.get());
        es.shutdown();
    }
}
