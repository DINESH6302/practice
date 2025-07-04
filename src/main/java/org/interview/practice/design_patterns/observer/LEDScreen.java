package org.interview.practice.design_patterns.observer;

public class LEDScreen implements Observer{
    @Override
    public void update(double temperature) {
        System.out.println("LED Screen : Temperature updated to " + temperature + "°C");
    }
}
