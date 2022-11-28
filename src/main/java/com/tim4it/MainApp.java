package com.tim4it;

public class MainApp {

    public static void main(String... args) {
        var consecutiveNumbers = new ConsecutiveNumbers("340876123976");

        var apply = consecutiveNumbers.apply();
        var applyStream = consecutiveNumbers.applyStream();
        var applyStreamSimple = consecutiveNumbers.applyStreamSimple();
        var applyFastLimited = consecutiveNumbers.applyFastLimited();

        System.out.println("Apply: " + apply + ", Apply stream: " + applyStream +
                ", Apply stream simple: " + applyStreamSimple + ", Apply limited: " + applyFastLimited);
    }
}
