package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day14DiskDefragmentationTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day14DiskDefragmentation("stpzcrnm");

        assertEquals(8250, dp.getPart1Answer().intValue());
        assertEquals(1113, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day14DiskDefragmentation("flqrgnkx");

        assertEquals(8108, dp.getPart1Answer().intValue());
        assertEquals(1242, dp.getPart2Answer().intValue());
    }
}

