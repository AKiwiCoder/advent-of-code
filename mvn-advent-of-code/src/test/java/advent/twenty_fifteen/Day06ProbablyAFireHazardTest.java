package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day06ProbablyAFireHazardTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day06ProbablyAFireHazard("/twenty_fifteen/Day06-ProbablyAFireHazard-input.txt");

        assertEquals(569999, dp.getPart1Answer().intValue());
        assertEquals(17836115, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day06ProbablyAFireHazard("/twenty_fifteen/Day06-ProbablyAFireHazard-example#1.txt");

        assertEquals(998996, dp.getPart1Answer().intValue());
        assertEquals(1001996, dp.getPart2Answer().intValue());
    }
}
