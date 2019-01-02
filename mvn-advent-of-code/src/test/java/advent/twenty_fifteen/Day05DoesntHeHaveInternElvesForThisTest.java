package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day05DoesntHeHaveInternElvesForThisTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day05DoesntHeHaveInternElvesForThis("/twenty_fifteen/Day05-DoesntHeHaveInternElvesForThis-input.txt");

        assertEquals(258, dp.getPart1Answer().intValue());
        assertEquals(53, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day05DoesntHeHaveInternElvesForThis("/twenty_fifteen/Day05-DoesntHeHaveInternElvesForThis-example#1.txt");

        assertEquals(2, dp.getPart1Answer().intValue());
        assertEquals(2, dp.getPart2Answer().intValue());
    }
}