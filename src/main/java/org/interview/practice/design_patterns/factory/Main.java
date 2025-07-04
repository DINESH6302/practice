package org.interview.practice.design_patterns.factory;

public class Main {
    public static void main(String[] args) {

        Car car = (Car) VehicleFactory.createVehicle(Type.Car);
        Vehicle bus = VehicleFactory.createVehicle(Type.Bus);

        car.start();
        car.run();

        bus.start();
//        bus.run();
    }
}
