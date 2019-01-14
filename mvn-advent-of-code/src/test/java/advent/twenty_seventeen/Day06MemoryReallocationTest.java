package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day06MemoryReallocationTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day06MemoryReallocation("/twenty_seventeen/Day06-MemoryReallocation-input.txt");

        assertEquals(3156, dp.getPart1Answer().intValue());
        assertEquals(1610, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day06MemoryReallocation("/twenty_seventeen/Day06-MemoryReallocation-example#1.txt");

        assertEquals(5, dp.getPart1Answer().intValue());
        assertEquals(4, dp.getPart2Answer().intValue());
    }
}