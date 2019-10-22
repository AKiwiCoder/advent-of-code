package advent.twenty_fifteen.support;

import java.util.Objects;

public final class Light {
    private boolean on;
    private int brightness;

    public Light() {
        this.on = false;
        this.brightness = 0;
    }

    public boolean isOn() {
        return on;
    }

    public void turnOn() {
        this.on = true;
    }

    public void turnOff() {
        this.on = false;
    }

    public void toggle() {
        this.on = !this.on;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Light light = (Light) o;
        return on == light.on &&
                brightness == light.brightness;
    }

    @Override
    public int hashCode() {
        return Objects.hash(on, brightness);
    }
}