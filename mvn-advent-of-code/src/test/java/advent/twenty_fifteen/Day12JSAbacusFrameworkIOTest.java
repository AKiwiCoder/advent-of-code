package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day12JSAbacusFrameworkIOTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day12JSAbacusFrameworkIO("/twenty_fifteen/Day12-JSAbacusFrameworkIO-input.txt");

        assertEquals(111754, dp.getPart1Answer().intValue());
        assertEquals(65402, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day12JSAbacusFrameworkIO("/twenty_fifteen/Day12-JSAbacusFrameworkIO-example#1.txt");

        assertEquals(18, dp.getPart1Answer().intValue());
        assertEquals(18, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample2() {
        DailyProblem<Integer, Integer> dp = new Day12JSAbacusFrameworkIO("/twenty_fifteen/Day12-JSAbacusFrameworkIO-example#2.txt");

        assertEquals(33, dp.getPart1Answer().intValue());
        assertEquals(16, dp.getPart2Answer().intValue());
    }
}