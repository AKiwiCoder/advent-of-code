package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day09StreamProcessingTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day09StreamProcessing("/twenty_seventeen/Day09-StreamProcessing-input.txt");

        assertEquals(10616, dp.getPart1Answer().intValue());
        assertEquals(5101, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day09StreamProcessing("/twenty_seventeen/Day09-StreamProcessing-example#1.txt");

        assertEquals(50, dp.getPart1Answer().intValue());
        assertEquals(29, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample2() {
        DailyProblem<Integer, Integer> dp = new Day09StreamProcessing("/twenty_seventeen/Day09-StreamProcessing-example#2.txt");

        assertEquals(0, dp.getPart1Answer().intValue());
        assertEquals(32, dp.getPart2Answer().intValue());
    }
}

