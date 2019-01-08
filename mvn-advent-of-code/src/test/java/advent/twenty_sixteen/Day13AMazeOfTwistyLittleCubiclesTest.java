package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day13AMazeOfTwistyLittleCubiclesTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day13AMazeOfTwistyLittleCubicles(1358, 31,39);

        assertEquals(96, dp.getPart1Answer().intValue());
        assertEquals(141, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day13AMazeOfTwistyLittleCubicles(10,7,4);

        assertEquals(11, dp.getPart1Answer().intValue());
        assertEquals(116, dp.getPart2Answer().intValue());
    }
}