package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class Day14OneTimePadTest {
    @Test
    public void checkReal() throws NoSuchAlgorithmException {
        DailyProblem<Integer, Integer> dp = new Day14OneTimePad("ngcjuoqr");

        assertEquals(18626, dp.getPart1Answer().intValue());
        assertEquals(20092, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() throws NoSuchAlgorithmException {
        DailyProblem<Integer, Integer> dp = new Day14OneTimePad("abc");

        assertEquals(22728, dp.getPart1Answer().intValue());
        assertEquals(22551, dp.getPart2Answer().intValue());
    }
}