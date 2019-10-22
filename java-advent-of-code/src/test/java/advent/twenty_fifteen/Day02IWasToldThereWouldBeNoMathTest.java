package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day02IWasToldThereWouldBeNoMathTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day02IWasToldThereWouldBeNoMath("/twenty_fifteen/Day02-IWasToldThereWouldBeNoMath-input.txt");

        assertEquals(1606483, dp.getPart1Answer().intValue());
        assertEquals(3842356, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day02IWasToldThereWouldBeNoMath("/twenty_fifteen/Day02-IWasToldThereWouldBeNoMath-example#1.txt");

        assertEquals(101, dp.getPart1Answer().intValue());
        assertEquals(48, dp.getPart2Answer().intValue());
    }
}
