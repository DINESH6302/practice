package org.interview.practice.design_patterns.observer;

public class Main {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        MobileScreen user1 = new MobileScreen("User1");
        MobileScreen user2 = new MobileScreen("User2");
        LEDScreen ledScreen = new LEDScreen();

        weatherStation.registerObserver(user1);
        weatherStation.registerObserver(user2);
        weatherStation.registerObserver(ledScreen);

        weatherStation.setTemperature(24.5);
        System.out.println("----------------------------");
        weatherStation.setTemperature(20.5);

        System.out.println("\n...Removing User2...");
        weatherStation.removeObserver(user2);
        System.out.println("----------------------------");
        weatherStation.setTemperature(22.8);

    }
}
