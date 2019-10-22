package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day16DragonChecksumTest {
    @Test
    public void checkReal() {
        DailyProblem<String, String> dp = new Day16DragonChecksum(272, 35651584, "11110010111001001");

        assertEquals("01110011101111011", dp.getPart1Answer());
        assertEquals("11001111011000111", dp.getPart2Answer());
    }

    @Test
    public void checkExample1() {
        DailyProblem<String, String> dp = new Day16DragonChecksum(20, 30, "10000");

        assertEquals("01100", dp.getPart1Answer());
        assertEquals("011111010111011", dp.getPart2Answer());
    }
}