package advent.twenty_sixteen.support;

import java.util.Objects;

public class RadioisotopeComponentMicrochip extends RadioisotopeComponent {
    private final String name;

    public RadioisotopeComponentMicrochip(String material) {
        super(material);

        this.name = material.substring(0,1).toUpperCase() + material.substring(1,2) + "M";
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RadioisotopeComponentMicrochip that = (RadioisotopeComponentMicrochip) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
