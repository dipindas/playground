package com.demo.playground.util.lowleveldesign.designpattern.behavioral.observer;

public class WithoutObserver {
    public static void main(String[] args) {
        Device device = new Device();
        WeatherStation weatherStation = new WeatherStation(device);
        weatherStation.setTemperature(20);
        weatherStation.setTemperature(30);
    }
}

class WeatherStation {

    private Device device;

    public WeatherStation(Device device) {
        this.device = device;
    }

    public void setTemperature(int temp) {
        device.setTemp(temp);
    }

}

class Device {
    public void setTemp(int temp) {
        System.out.println("Temperature set as " + temp);
    }
}
