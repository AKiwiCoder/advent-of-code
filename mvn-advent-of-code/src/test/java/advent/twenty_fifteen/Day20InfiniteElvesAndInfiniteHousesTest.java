package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day20InfiniteElvesAndInfiniteHousesTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day20InfiniteElvesAndInfiniteHouses(29000000);

        assertEquals(665280, dp.getPart1Answer().intValue());
        assertEquals(705600, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day20InfiniteElvesAndInfiniteHouses(100);

        assertEquals(6, dp.getPart1Answer().intValue());
        assertEquals(6, dp.getPart2Answer().intValue());
    }
}