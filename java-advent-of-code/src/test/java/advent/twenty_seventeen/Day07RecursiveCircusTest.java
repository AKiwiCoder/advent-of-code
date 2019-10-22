package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day07RecursiveCircusTest {
    @Test
    public void checkReal() {
        DailyProblem<String, Integer> dp = new Day07RecursiveCircus("/twenty_seventeen/Day07-RecursiveCircus-input.txt");

        assertEquals("qibuqqg", dp.getPart1Answer());
        assertEquals(1079, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<String, Integer> dp = new Day07RecursiveCircus("/twenty_seventeen/Day07-RecursiveCircus-example#1.txt");

        assertEquals("tknk", dp.getPart1Answer());
        assertEquals(60, dp.getPart2Answer().intValue());
    }
}