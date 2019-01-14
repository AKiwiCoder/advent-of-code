package advent.twenty_sixteen.support;

public abstract class RadioisotopeComponent {
    private final String material;

    public RadioisotopeComponent(String material) {
        this.material = material.toUpperCase();
    }

    public String getMaterial() {
        return material;
    }
}
