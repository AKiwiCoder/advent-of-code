package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day23SafeCrackingTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day23SafeCracking("/twenty_sixteen/Day23-SafeCracking-input.txt");

        assertEquals(12315, dp.getPart1Answer().intValue());
        assertEquals(479008875, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day23SafeCracking("/twenty_sixteen/Day23-SafeCracking-example#1.txt");

        assertEquals(3, dp.getPart1Answer().intValue());
        assertEquals(3, dp.getPart2Answer().intValue());
    }
}