package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.twenty_sixteen.Day01NoTimeForATaxicab;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day01InverseCaptchaTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day01InverseCaptcha("/twenty_seventeen/Day01-InverseCaptcha-input.txt");

        assertEquals(1253, dp.getPart1Answer().intValue());
        assertEquals(1278, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day01InverseCaptcha("/twenty_seventeen/Day01-InverseCaptcha-example#1.txt");

        assertEquals(16, dp.getPart1Answer().intValue());
        assertEquals(10, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample2() {
        DailyProblem<Integer, Integer> dp = new Day01InverseCaptcha("/twenty_seventeen/Day01-InverseCaptcha-example#2.txt");

        assertEquals(3, dp.getPart1Answer().intValue());
        assertEquals(26, dp.getPart2Answer().intValue());
    }
}