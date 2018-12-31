package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day24ImmuneSystemSimulator20XXTest {
    @Test
    public void checkReal() {
        Day24ImmuneSystemSimulator20XX im = new Day24ImmuneSystemSimulator20XX("/twenty_eighteen/Day24-ImmuneSystemSimulator20XX-input.txt");

        assertEquals(22859, im.getPart1Answer().intValue());
        assertEquals(2834, im.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        Day24ImmuneSystemSimulator20XX im = new Day24ImmuneSystemSimulator20XX("/twenty_eighteen/Day24-ImmuneSystemSimulator20XX-example#1.txt");

        assertEquals(5216, im.getPart1Answer().intValue());
        assertEquals(51, im.getPart2Answer().intValue());
    }
}