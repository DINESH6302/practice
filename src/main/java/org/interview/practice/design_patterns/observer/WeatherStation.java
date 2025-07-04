package org.interview.practice.design_patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherStation implements Subject{
    double temperature;
    List<Observer> observers;

    public WeatherStation() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer obr : observers) {
            obr.update(temperature);
        }
    }

    public void setTemperature(double temp) {
        temperature = temp;
        System.out.println("WeatherStation: New Temperature = " + temperature);
        notifyObservers();
    }
}
