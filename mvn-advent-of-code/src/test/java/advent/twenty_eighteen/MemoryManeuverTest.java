package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MemoryManeuverTest {
    @Test
    public void checkReal() {
        MemoryManeuver im = new MemoryManeuver("/twenty_eighteen/MemoryManeuver-input.txt");

        assertEquals(43825, im.getPart1Answer().intValue());
        assertEquals(19276, im.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        MemoryManeuver im = new MemoryManeuver("/twenty_eighteen/MemoryManeuver-example#1.txt");

        assertEquals(138, im.getPart1Answer().intValue());
        assertEquals(66, im.getPart2Answer().intValue());
    }
}
