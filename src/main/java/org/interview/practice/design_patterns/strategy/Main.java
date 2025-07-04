package org.interview.practice.design_patterns.strategy;

public class Main {
    public static void main(String[] args) {
        Paypal paypal = new Paypal();
        Stripe stripe = new Stripe();
        Card card = new Card();

        PaymentStrategy paymentStrategy = new PaymentStrategy();

        paymentStrategy.setPaymentStrategy(paypal);
        paymentStrategy.processPayment();

        paymentStrategy.setPaymentStrategy(stripe);
        paymentStrategy.processPayment();

        paymentStrategy.setPaymentStrategy(card);
        paymentStrategy.processPayment();
    }
}
