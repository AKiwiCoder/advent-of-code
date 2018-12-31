package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day02IWasToldThereWouldBeNoMath {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day01NotQuiteLisp("/twenty_fifteen/Day02-IWasToldThereWouldBeNoMath-input.txt");

        assertEquals(74, dp.getPart1Answer().intValue());
        assertEquals(1795, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day01NotQuiteLisp("/twenty_fifteen/Day02-IWasToldThereWouldBeNoMath-example#1.txt");

        assertEquals(0, dp.getPart1Answer().intValue());
        assertEquals(0, dp.getPart2Answer().intValue());
    }
}
