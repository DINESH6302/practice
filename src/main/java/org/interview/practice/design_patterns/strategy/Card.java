package org.interview.practice.design_patterns.strategy;

public class Card implements Payment{
    @Override
    public void processPayment() {
        System.out.println("Processing Card Payment");
    }
}
