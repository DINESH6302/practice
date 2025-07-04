package org.interview.practice.design_patterns.strategy;

public class Stripe implements Payment{
    @Override
    public void processPayment() {
        System.out.println("Processing Stripe Payment");
    }
}
