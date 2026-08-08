package design_patterns.behavioural.observer;


class DisplayDevice {
    public void showTemp(float temp) {
        System.out.println("Current temp : " + temp);
    }
}

class WeatherStation {
    private float temperature;
    private DisplayDevice displayDevice;

    public WeatherStation(DisplayDevice displayDevice) {
        this.displayDevice = displayDevice;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyDevice();
    }

    public void notifyDevice() {
        this.displayDevice.showTemp(temperature);
    }

}

public class WithoutObserverPattern {

    public static void main(String[] args) {
        DisplayDevice device = new DisplayDevice();
        WeatherStation weatherStation = new WeatherStation(device);

        //Tight coupling between station and device
        weatherStation.setTemperature(25.0f);
        weatherStation.setTemperature(30.0f);
    }
}
