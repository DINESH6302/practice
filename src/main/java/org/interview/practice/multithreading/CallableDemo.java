package org.interview.practice.multithreading;

import java.util.Random;
import java.util.concurrent.Callable;

public class CallableDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Thread.sleep(3000);
        return 1;
    }
}
