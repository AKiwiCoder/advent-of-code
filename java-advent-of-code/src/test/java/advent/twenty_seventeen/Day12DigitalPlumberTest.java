package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day12DigitalPlumberTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day12DigitalPlumber("/twenty_seventeen/Day12-DigitalPlumber-input.txt");

        assertEquals(169, dp.getPart1Answer().intValue());
        assertEquals(179, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day12DigitalPlumber("/twenty_seventeen/Day12-DigitalPlumber-example#1.txt");

        assertEquals(6, dp.getPart1Answer().intValue());
        assertEquals(2, dp.getPart2Answer().intValue());
    }
}

