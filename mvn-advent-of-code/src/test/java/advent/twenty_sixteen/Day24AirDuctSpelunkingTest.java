package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day24AirDuctSpelunkingTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day24AirDuctSpelunking("/twenty_sixteen/Day24-AirDuctSpelunking-input.txt");

        assertEquals(470, dp.getPart1Answer().intValue());
        assertEquals(720, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day24AirDuctSpelunking("/twenty_sixteen/Day24-AirDuctSpelunking-example#1.txt");

        assertEquals(14, dp.getPart1Answer().intValue());
        assertEquals(20, dp.getPart2Answer().intValue());
    }
}