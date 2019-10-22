package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day08MemoryManeuverTest {
    @Test
    public void checkReal() {
        Day08MemoryManeuver im = new Day08MemoryManeuver("/twenty_eighteen/Day08-MemoryManeuver-input.txt");

        assertEquals(43825, im.getPart1Answer().intValue());
        assertEquals(19276, im.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        Day08MemoryManeuver im = new Day08MemoryManeuver("/twenty_eighteen/Day08-MemoryManeuver-example#1.txt");

        assertEquals(138, im.getPart1Answer().intValue());
        assertEquals(66, im.getPart2Answer().intValue());
    }
}
