package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day10ElvesLookElvesSayTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day10ElvesLookElvesSay("1113222113");

        assertEquals(252594, dp.getPart1Answer().intValue());
        assertEquals(3579328, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day10ElvesLookElvesSay("1");

        assertEquals(82350, dp.getPart1Answer().intValue());
        assertEquals(1166642, dp.getPart2Answer().intValue());
    }
}