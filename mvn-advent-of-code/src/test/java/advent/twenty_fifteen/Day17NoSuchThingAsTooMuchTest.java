package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day17NoSuchThingAsTooMuchTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day17NoSuchThingAsTooMuch("/twenty_fifteen/Day17-NoSuchThingAsTooMuch-input.txt", 150);

        assertEquals(1304, dp.getPart1Answer().intValue());
        assertEquals(18, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day17NoSuchThingAsTooMuch("/twenty_fifteen/Day17-NoSuchThingAsTooMuch-example#1.txt", 25);

        assertEquals(4, dp.getPart1Answer().intValue());
        assertEquals(3, dp.getPart2Answer().intValue());
    }
}