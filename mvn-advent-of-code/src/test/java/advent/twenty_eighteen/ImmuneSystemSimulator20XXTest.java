package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ImmuneSystemSimulator20XXTest {
    @Test
    public void checkReal() {
        ImmuneSystemSimulator20XX im = new ImmuneSystemSimulator20XX("/twenty_eighteen/ImmuneSystemSimulator20XX-input.txt");

        assertEquals(22859, im.getPart1Answer().intValue());
        assertEquals(2834, im.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        ImmuneSystemSimulator20XX im = new ImmuneSystemSimulator20XX("/twenty_eighteen/ImmuneSystemSimulator20XX-example#1.txt");

        assertEquals(5216, im.getPart1Answer().intValue());
        assertEquals(51, im.getPart2Answer().intValue());
    }
}