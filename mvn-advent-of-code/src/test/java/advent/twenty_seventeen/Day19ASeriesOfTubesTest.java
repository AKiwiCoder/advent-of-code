package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day19ASeriesOfTubesTest {
    @Test
    public void checkReal() {
        DailyProblem<String, Integer> dp = new Day19ASeriesOfTubes("/twenty_seventeen/Day19-ASeriesOfTubes-input.txt");

        assertEquals("EPYDUXANIT", dp.getPart1Answer());
        assertEquals(17544, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<String, Integer> dp = new Day19ASeriesOfTubes("/twenty_seventeen/Day19-ASeriesOfTubes-example#1.txt");

        assertEquals("ABCDEF", dp.getPart1Answer());
        assertEquals(38, dp.getPart2Answer().intValue());
    }
}

