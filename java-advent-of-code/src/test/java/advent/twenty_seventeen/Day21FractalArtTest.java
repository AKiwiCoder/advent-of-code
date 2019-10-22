package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day21FractalArtTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day21FractalArt("/twenty_seventeen/Day21-FractalArt-input.txt", 5, 18);

        assertEquals(142, dp.getPart1Answer().intValue());
        assertEquals(1879071, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day21FractalArt("/twenty_seventeen/Day21-FractalArt-example#1.txt", 2, 5);

        assertEquals(12, dp.getPart1Answer().intValue());
        assertEquals(0, dp.getPart2Answer().intValue());
    }
}

