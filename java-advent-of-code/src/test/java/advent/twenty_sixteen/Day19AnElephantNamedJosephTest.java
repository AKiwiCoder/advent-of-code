package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day19AnElephantNamedJosephTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day19AnElephantNamedJoseph(3012210);

        assertEquals(1830117, dp.getPart1Answer().intValue());
        assertEquals(1417887, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day19AnElephantNamedJoseph(5);

        assertEquals(3, dp.getPart1Answer().intValue());
        assertEquals(2, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample2() {
        DailyProblem<Integer, Integer> dp = new Day19AnElephantNamedJoseph(100);

        assertEquals(73, dp.getPart1Answer().intValue());
        assertEquals(19, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample3() {
        DailyProblem<Integer, Integer> dp = new Day19AnElephantNamedJoseph(50);

        assertEquals(37, dp.getPart1Answer().intValue());
        assertEquals(23, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample4() {
        DailyProblem<Integer, Integer> dp = new Day19AnElephantNamedJoseph(10);

        assertEquals(5, dp.getPart1Answer().intValue());
        assertEquals(1, dp.getPart2Answer().intValue());
    }
}