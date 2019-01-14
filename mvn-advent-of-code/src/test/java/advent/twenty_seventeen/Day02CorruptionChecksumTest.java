package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day02CorruptionChecksumTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day02CorruptionChecksum("/twenty_seventeen/Day02-CorruptionChecksum-input.txt");

        assertEquals(41919, dp.getPart1Answer().intValue());
        assertEquals(303, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day02CorruptionChecksum("/twenty_seventeen/Day02-CorruptionChecksum-example#1.txt");

        assertEquals(18, dp.getPart1Answer().intValue());
        assertEquals(7, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample2() {
        DailyProblem<Integer, Integer> dp = new Day02CorruptionChecksum("/twenty_seventeen/Day02-CorruptionChecksum-example#2.txt");

        assertEquals(18, dp.getPart1Answer().intValue());
        assertEquals(9, dp.getPart2Answer().intValue());
    }
}