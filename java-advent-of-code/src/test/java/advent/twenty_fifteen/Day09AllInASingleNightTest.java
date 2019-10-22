package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day09AllInASingleNightTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day09AllInASingleNight("/twenty_fifteen/Day09-AllInASingleNight-input.txt");

        assertEquals(207, dp.getPart1Answer().intValue());
        assertEquals(804, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day09AllInASingleNight("/twenty_fifteen/Day09-AllInASingleNight-example#1.txt");

        assertEquals(605, dp.getPart1Answer().intValue());
        assertEquals(982, dp.getPart2Answer().intValue());
    }
}