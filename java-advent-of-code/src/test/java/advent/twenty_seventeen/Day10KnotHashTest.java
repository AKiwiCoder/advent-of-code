package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day10KnotHashTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, String> dp = new Day10KnotHash("/twenty_seventeen/Day10-KnotHash-input.txt", 256);

        assertEquals(11375, dp.getPart1Answer().intValue());
        assertEquals("e0387e2ad112b7c2ef344e44885fe4d8", dp.getPart2Answer());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, String> dp = new Day10KnotHash("/twenty_seventeen/Day10-KnotHash-example#1.txt", 5);

        assertEquals(12, dp.getPart1Answer().intValue());

        assertEquals("a2582a3a0e66e6e86e3812dcb672a272", Day10KnotHash.hash(""));
        assertEquals("33efeb34ea91902bb2f59c9920caa6cd", Day10KnotHash.hash("AoC 2017"));
        assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", Day10KnotHash.hash("1,2,3"));
        assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", Day10KnotHash.hash("1,2,4"));
    }
}

