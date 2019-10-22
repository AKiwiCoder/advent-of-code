package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day08MatchsticksTest {
    @Test
    public void testEncode() {
        assertEquals("\"\\\"\\\"\"", Day08Matchsticks.encode("\"\""));
        assertEquals("\"\\\"abc\\\"\"", Day08Matchsticks.encode("\"abc\""));
        assertEquals("\"\\\"aaa\\\\\\\"aaa\\\"\"", Day08Matchsticks.encode("\"aaa\\\"aaa\""));
        assertEquals("\"\\\"\\\\x27\\\"\"", Day08Matchsticks.encode("\"\\x27\""));
    }

    @Test
    public void testDecode() {
        assertEquals("", Day08Matchsticks.decode("\"\""));
        assertEquals("abc", Day08Matchsticks.decode("\"abc\""));
        assertEquals("aaa\"aaa", Day08Matchsticks.decode("\"aaa\\\"aaa\""));
        assertEquals("'", Day08Matchsticks.decode("\"\\x27\""));
    }

    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day08Matchsticks("/twenty_fifteen/Day08-Matchsticks-input.txt");

        assertEquals(1350, dp.getPart1Answer().intValue());
        assertEquals(2085, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day08Matchsticks("/twenty_fifteen/Day08-Matchsticks-example#1.txt");

        assertEquals(12, dp.getPart1Answer().intValue());
        assertEquals(19, dp.getPart2Answer().intValue());
    }
}