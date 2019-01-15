package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day15DuelingGeneratorsTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day15DuelingGenerators("/twenty_seventeen/Day15-DuelingGenerators-input.txt");

        assertEquals(612, dp.getPart1Answer().intValue());
        assertEquals(285, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day15DuelingGenerators("/twenty_seventeen/Day15-DuelingGenerators-example#1.txt");

        assertEquals(588, dp.getPart1Answer().intValue());
        assertEquals(309, dp.getPart2Answer().intValue());
    }
}

