package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day09ExplosivesInCyberspaceTest {
    @Test
    public void checkReal() {
        DailyProblem<Long, Long> dp = new Day09ExplosivesInCyberspace("/twenty_sixteen/Day09-ExplosivesInCyberspace-input.txt");

        assertEquals(70186l, dp.getPart1Answer().longValue());
        assertEquals(10915059201l, dp.getPart2Answer().longValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Long, Long> dp = new Day09ExplosivesInCyberspace("/twenty_sixteen/Day09-ExplosivesInCyberspace-example#1.txt");

        assertEquals(57l, dp.getPart1Answer().longValue());
        assertEquals(56l, dp.getPart2Answer().longValue());
    }
}