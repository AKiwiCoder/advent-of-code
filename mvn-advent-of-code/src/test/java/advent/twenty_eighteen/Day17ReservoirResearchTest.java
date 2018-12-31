package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day17ReservoirResearchTest {
    @Test
    public void checkReal() {
        Day17ReservoirResearch im = new Day17ReservoirResearch("/twenty_eighteen/Day17-ReservoirResearch-input.txt");

        assertEquals(31158, im.getPart1Answer().intValue());
        assertEquals(25419, im.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        Day17ReservoirResearch im = new Day17ReservoirResearch("/twenty_eighteen/Day17-ReservoirResearch-example#1.txt");

        assertEquals(57, im.getPart1Answer().intValue());
        assertEquals(29, im.getPart2Answer().intValue());
    }
}
