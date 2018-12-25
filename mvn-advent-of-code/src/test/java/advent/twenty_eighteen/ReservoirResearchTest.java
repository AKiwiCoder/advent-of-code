package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReservoirResearchTest {
    @Test
    public void checkReal() {
        ReservoirResearch im = new ReservoirResearch("/twenty_eighteen/ReservoirResearch-input.txt");

        assertEquals(31158, im.getPart1Answer().intValue());
        assertEquals(25419, im.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        ReservoirResearch im = new ReservoirResearch("/twenty_eighteen/ReservoirResearch-example#1.txt");

        assertEquals(57, im.getPart1Answer().intValue());
        assertEquals(29, im.getPart2Answer().intValue());
    }
}
