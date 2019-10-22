package advent.twenty_fifteen.support;

import java.util.Objects;

public final class Reindeer {
    private final String name;
    private final int speed;
    private final int restPeriod;
    private int flyPeriod;

    public Reindeer(String name, int speed, int flyPeriod, int restPeriod) {
        this.name = name;
        this.speed = speed;
        this.flyPeriod = flyPeriod;
        this.restPeriod = restPeriod;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public int getFlyPeriod() {
        return flyPeriod;
    }

    public int getRestPeriod() {
        return restPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reindeer reindeer = (Reindeer) o;
        return speed == reindeer.speed &&
                restPeriod == reindeer.restPeriod &&
                flyPeriod == reindeer.flyPeriod &&
                Objects.equals(name, reindeer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, speed, restPeriod, flyPeriod);
    }

    @Override
    public String toString() {
        return "Reindeer{" +
                "name='" + name + '\'' +
                ", speed=" + speed +
                ", restPeriod=" + restPeriod +
                ", flyPeriod=" + flyPeriod +
                '}';
    }

    //  Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
    public static Reindeer PARSE(String line) {
        String[] bits = line.split(" ");
        return new Reindeer(bits[0], Integer.parseInt(bits[3]), Integer.parseInt(bits[6]), Integer.parseInt(bits[13]));
    }
}
