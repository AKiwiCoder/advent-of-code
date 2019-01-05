package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day21RpgSimulator20xxTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day21RpgSimulator20xx("/twenty_fifteen/Day21-RpgSimulator20xx-input.txt");

        assertEquals(91, dp.getPart1Answer().intValue());
        assertEquals(158, dp.getPart2Answer().intValue());
    }
}