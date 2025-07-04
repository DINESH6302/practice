package org.interview.practice.producer_consumer;

public class Consumer implements Runnable{
    private final SharedQueue sharedQueue;

    Consumer(SharedQueue sq) {
        sharedQueue = sq;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sharedQueue.consume();
                Thread.sleep(800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
