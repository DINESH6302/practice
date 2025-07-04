package org.interview.practice.design_patterns.observer;

public class MobileScreen implements Observer{
    String name;

    public MobileScreen(String name) {
        this.name = name;
    }

    @Override
    public void update(double temperature) {
        System.out.println(name + " - Mobile Display: Temperature updated to " + temperature + "°C");
    }
}
