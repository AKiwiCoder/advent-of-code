package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day08IHeardYouLikeRegistersTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day08IHeardYouLikeRegisters("/twenty_seventeen/Day08-IHeardYouLikeRegisters-input.txt");

        assertEquals(5221, dp.getPart1Answer().intValue());
        assertEquals(7491, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day08IHeardYouLikeRegisters("/twenty_seventeen/Day08-IHeardYouLikeRegisters-example#1.txt");

        assertEquals(1, dp.getPart1Answer().intValue());
        assertEquals(10, dp.getPart2Answer().intValue());
    }

}