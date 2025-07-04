package org.interview.practice.producer_consumer;

public class Producer implements Runnable{

    private final SharedQueue sharedQueue;

    Producer(SharedQueue sq) {
        sharedQueue = sq;
    }

    @Override
    public void run() {
        int value = 1;
        while (true) {
            try {
                sharedQueue.produce(value++);
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
