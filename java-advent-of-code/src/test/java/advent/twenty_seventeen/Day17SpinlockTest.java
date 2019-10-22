package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day17SpinlockTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day17Spinlock(304);

        assertEquals(1173, dp.getPart1Answer().intValue());
        assertEquals(1930815, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day17Spinlock(3);

        assertEquals(638, dp.getPart1Answer().intValue());
        assertEquals(1222153, dp.getPart2Answer().intValue());
    }
}

