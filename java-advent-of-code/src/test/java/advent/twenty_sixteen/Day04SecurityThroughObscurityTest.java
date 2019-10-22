package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day04SecurityThroughObscurityTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day04SecurityThroughObscurity("/twenty_sixteen/Day04-SecurityThroughObscurity-input.txt");

        assertEquals(245102, dp.getPart1Answer().intValue());
        assertEquals(324, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day04SecurityThroughObscurity("/twenty_sixteen/Day04-SecurityThroughObscurity-example#1.txt");

        assertEquals(1514, dp.getPart1Answer().intValue());
        assertEquals(-1, dp.getPart2Answer().intValue());
    }
}