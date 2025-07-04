package org.interview.practice.design_patterns.strategy;

public class Paypal implements Payment{
    @Override
    public void processPayment() {
        System.out.println("Processing Paypal Payment");
    }
}
