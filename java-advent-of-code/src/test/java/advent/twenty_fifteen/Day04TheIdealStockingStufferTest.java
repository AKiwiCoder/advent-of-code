package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class Day04TheIdealStockingStufferTest {
    @Test
    public void checkReal() throws NoSuchAlgorithmException {
        DailyProblem<Integer, Integer> dp = new Day04TheIdealStockingStuffer("iwrupvqb");

        assertEquals(346386, dp.getPart1Answer().intValue());
        assertEquals(9958218, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() throws NoSuchAlgorithmException {
        DailyProblem<Integer, Integer> dp = new Day04TheIdealStockingStuffer("abcdef");

        assertEquals(609043, dp.getPart1Answer().intValue());
        assertEquals(6742839, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample2() throws NoSuchAlgorithmException {
        DailyProblem<Integer, Integer> dp = new Day04TheIdealStockingStuffer("pqrstuv");

        assertEquals(1048970, dp.getPart1Answer().intValue());
        assertEquals(5714438, dp.getPart2Answer().intValue());
    }
}