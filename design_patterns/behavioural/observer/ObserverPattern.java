package design_patterns.behavioural.observer;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(float temp);
}

class DisplayDeviceIn implements Observer {

    private String name;

    public DisplayDeviceIn(String name) {
        this.name = name;
    }

    @Override
    public void update(float temp) {
        System.out.println("Display Device " + name + " shows temperature: " + temp);
    }
}

class MobileDevice implements Observer {
    @Override
    public void update(float temp) {
        System.out.println("Mobile Device shows temperature: " + temp);
    }
}

interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}


class WeatherStationIn implements Subject {

    public float temperature;
    public List<Observer> observers;

    public WeatherStationIn(float temperature) {
        this.temperature = temperature;
        this.observers = new ArrayList<>();
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (var obs : observers) {
            obs.update(temperature);
        }
    }
}

public class ObserverPattern {

    public static void main(String[] args) {
        WeatherStationIn weatherStation = new WeatherStationIn(25.0f);

        // devices
        DisplayDeviceIn displayDevice = new DisplayDeviceIn("Display Device");
        MobileDevice mobileDevice = new MobileDevice();

        //register
        weatherStation.attach(displayDevice);
        weatherStation.attach(mobileDevice);

        //update
        weatherStation.setTemperature(25);

        //remove
        weatherStation.detach(displayDevice);

        weatherStation.setTemperature(36);
    }
}


