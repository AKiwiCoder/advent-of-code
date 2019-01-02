package advent.twenty_fifteen.support;

import java.util.Objects;

public final class Ingredient {
    private final String name;
    private final int capacity;
    private final int durability;
    private final int flavor;
    private final int texture;
    private final int calories;

    public Ingredient(String name, int capacity, int durability, int flavor, int texture, int calories) {
        this.name = name;
        this.capacity = capacity;
        this.durability = durability;
        this.flavor = flavor;
        this.texture = texture;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getDurability() {
        return durability;
    }

    public int getFlavor() {
        return flavor;
    }

    public int getTexture() {
        return texture;
    }


    public int getCalories() {
        return calories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return capacity == that.capacity &&
                durability == that.durability &&
                flavor == that.flavor &&
                texture == that.texture &&
                calories == that.calories &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capacity, durability, flavor, texture, calories);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", durability=" + durability +
                ", flavor=" + flavor +
                ", texture=" + texture +
                ", calories=" + calories +
                '}';
    }

    /*
     Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
     */
    public static Ingredient PARSE(String line) {
        int index = line.indexOf(":");
        String name = line.substring(0, index);
        String[] bits = line.substring(index+1).split(",");
        return new Ingredient(name, PARSE_VALUE(bits[0]),PARSE_VALUE(bits[1]),PARSE_VALUE(bits[2]),PARSE_VALUE(bits[3]),PARSE_VALUE(bits[4]));
    }

    private static int PARSE_VALUE(String line) {
        String[] bits = line.trim().split(" ");
        return Integer.parseInt(bits[1]);
    }
}
