package org.interview.practice.rate_limiter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

class TokenBucket {

    int capacity;
    AtomicInteger token;
    double refill_per_mills;
    long last_refill_timestamp;
    ReentrantLock rl = new ReentrantLock();

    TokenBucket(int capacity, double refill_per_sec) {
        this.capacity = capacity;
        this.token = new AtomicInteger(capacity);
        this.refill_per_mills = refill_per_sec / 1000.0;
        this.last_refill_timestamp = System.currentTimeMillis();
    }

    public boolean allowRequest() {
        rl.lock();
        try {
            reFill();

            if (token.get() >= 1) {
                token.getAndDecrement();
                return true;
            }
            return false;
        } finally {
            rl.unlock();
        }
    }

    private void reFill() {
        long now = System.currentTimeMillis();
        long elapsed = now - last_refill_timestamp;
        double tokens_to_add = elapsed * refill_per_mills;

        if (tokens_to_add > 0) {
            double newTokenCount = Math.min(capacity, token.get() + tokens_to_add);
            token.set((int) newTokenCount);
            last_refill_timestamp = now;
        }
    }
}

class Main {
    public static void main(String[] args) throws InterruptedException {
        TokenBucket tokenBucket = new TokenBucket(5, 2);

        for (int i = 1; i <= 10; i++) {
            boolean isAllowed = tokenBucket.allowRequest();
            System.out.println("Request-" + i + " : " + (isAllowed ? "Allowed" : "Not Allowed"));
            Thread.sleep(100);
        }
    }
}
