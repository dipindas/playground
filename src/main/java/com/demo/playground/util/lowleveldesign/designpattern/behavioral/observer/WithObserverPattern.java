package com.demo.playground.util.lowleveldesign.designpattern.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class WithObserverPattern {
    public static void main(String[] args) {
        MobileDevice mobileDevice = new MobileDevice();
        DisplayDevice displayDevice = new DisplayDevice();

        WeatherStationIn weatherStationIn = new WeatherStationIn();
        weatherStationIn.addObserver(mobileDevice);
        weatherStationIn.addObserver(displayDevice);
        weatherStationIn.setTemperature(20);

        weatherStationIn.notifyDevice();

        weatherStationIn.removeObserver(mobileDevice);
        weatherStationIn.setTemperature(30);
        weatherStationIn.notifyDevice();
    }
}

interface Observer {
    void display(int temp);
}

interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyDevice();
}

class WeatherStationIn implements Subject {

    private List<Observer> observerList;
    private int temperature;

    public WeatherStationIn() {
        observerList = new ArrayList<>();
    }

    public void setTemperature(int temp) {
        this.temperature = temp;
    }

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyDevice() {
        for(Observer obs : observerList) {
            obs.display(temperature);
        }
    }
}

class DisplayDevice implements Observer {

    @Override
    public void display(int temp) {
        System.out.println("Display device temp " + temp);
    }
}

class MobileDevice implements Observer {

    @Override
    public void display(int temp) {
        System.out.println("Mobile device temp " + temp);
    }
}
