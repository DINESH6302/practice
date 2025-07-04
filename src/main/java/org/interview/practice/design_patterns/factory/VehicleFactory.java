package org.interview.practice.design_patterns.factory;

public class VehicleFactory{
    public static Vehicle createVehicle(Type type) {
        if(type == Type.Car){
            return new Car();
        } else if (type == Type.Bike) {
            return new Bike();
        } else if (type == Type.Bus) {
            return new Bus();
        }
        else if (type == Type.Truck) {
            return new Truck();
        }
        else {
            throw new IllegalArgumentException("Unknown Type.");
        }
    }
}
