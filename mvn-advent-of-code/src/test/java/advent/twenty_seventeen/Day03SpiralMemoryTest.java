package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day03SpiralMemoryTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day03SpiralMemory(312051);

        assertEquals(430, dp.getPart1Answer().intValue());
        assertEquals(312453, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day03SpiralMemory(1);

        assertEquals(0, dp.getPart1Answer().intValue());
        assertEquals(2, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample2() {
        DailyProblem<Integer, Integer> dp = new Day03SpiralMemory(12);

        assertEquals(3, dp.getPart1Answer().intValue());
        assertEquals(23, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample3() {
        DailyProblem<Integer, Integer> dp = new Day03SpiralMemory(23);

        assertEquals(2, dp.getPart1Answer().intValue());
        assertEquals(25, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample4() {
        DailyProblem<Integer, Integer> dp = new Day03SpiralMemory(1024);

        assertEquals(31, dp.getPart1Answer().intValue());
        assertEquals(1968, dp.getPart2Answer().intValue());
    }
}