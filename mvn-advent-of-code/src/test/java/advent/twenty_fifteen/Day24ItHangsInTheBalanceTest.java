package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day24ItHangsInTheBalanceTest {
    @Test
    public void checkReal() {
        DailyProblem<Long, Long> dp = new Day24ItHangsInTheBalance("/twenty_fifteen/Day24-ItHangsInTheBalance-input.txt");

        assertEquals(10439961859l, dp.getPart1Answer().longValue());
        assertEquals(72050269l, dp.getPart2Answer().longValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Long, Long> dp = new Day24ItHangsInTheBalance("/twenty_fifteen/Day24-ItHangsInTheBalance-example#1.txt");

        assertEquals(88, dp.getPart1Answer().longValue());
        assertEquals(33, dp.getPart2Answer().longValue());
    }
}