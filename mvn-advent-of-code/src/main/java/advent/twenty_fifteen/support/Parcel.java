package advent.twenty_fifteen.support;

import java.util.Objects;

public final class Parcel {
    private final int widht;
    private final int height;
    private final int length;

    public Parcel(int widht, int height, int length) {
        this.widht = widht;
        this.height = height;
        this.length = length;
    }

    public int getWidth() {
        return widht;
    }


    public int getHeight() {
        return height;
    }


    public int getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcel parcel = (Parcel) o;
        return widht == parcel.widht &&
                height == parcel.height &&
                length == parcel.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(widht, height, length);
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "widht=" + widht +
                ", height=" + height +
                ", length=" + length +
                '}';
    }

    public static Parcel PARSE(String line) {
        String[] bits= line.split("x");
        return new Parcel(Integer.parseInt(bits[0]), Integer.parseInt(bits[1]), Integer.parseInt(bits[2]));
    }
}
