package com.baek.switchex;

public class Switch {
    private Light light;

    public Switch(Light light) {
        this.light = light;
    }

    public void activate() {
        light.turnOn();
    }
}
