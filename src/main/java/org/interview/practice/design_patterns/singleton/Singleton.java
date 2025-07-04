package org.interview.practice.design_patterns.singleton;

public class Singleton implements Cloneable {
    private static volatile Singleton instance = null;

    private Singleton() {}

    public static Singleton getInstance() {
        // double-checked locking
        if (instance == null) {
            synchronized (Singleton.class) {
                if(instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new CloneNotSupportedException("Clone is not supported");
    }
}
