package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day01NoTimeForATaxicabTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day01NoTimeForATaxicab("/twenty_sixteen/Day01-NoTimeForATaxicab-input.txt");

        assertEquals(301, dp.getPart1Answer().intValue());
        assertEquals(130, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day01NoTimeForATaxicab("/twenty_sixteen/Day01-NoTimeForATaxicab-example#1.txt");

        assertEquals(8, dp.getPart1Answer().intValue());
        assertEquals(4, dp.getPart2Answer().intValue());
    }
}