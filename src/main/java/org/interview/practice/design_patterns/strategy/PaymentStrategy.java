package org.interview.practice.design_patterns.strategy;

public class PaymentStrategy {
    Payment payment_strategy;

    public PaymentStrategy() {
    }

    public PaymentStrategy(Payment payment) {
        payment_strategy = payment;
    }

    public void processPayment() {
        if(payment_strategy != null) {
            payment_strategy.processPayment();
        }
        else {
            throw new RuntimeException("Initialize Payment Strategy");
        }
    }

    public void setPaymentStrategy(Payment paymentStrategy) {
        payment_strategy = paymentStrategy;
    }
}
