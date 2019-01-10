package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day22GridComputingTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day22GridComputing("/twenty_sixteen/Day22-GridComputing-input.txt");

        assertEquals(1003, dp.getPart1Answer().intValue());
        assertEquals(0, dp.getPart2Answer().intValue());
    }
}