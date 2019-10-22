package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day06SignalsAndNoiseTest {
    @Test
    public void checkReal() {
        DailyProblem<String, String> dp = new Day06SignalsAndNoise("/twenty_sixteen/Day06-SignalsAndNoise-input.txt");

        assertEquals("gebzfnbt", dp.getPart1Answer());
        assertEquals("fykjtwyn", dp.getPart2Answer());
    }

    @Test
    public void checkExample1() {
        DailyProblem<String, String> dp = new Day06SignalsAndNoise("/twenty_sixteen/Day06-SignalsAndNoise-example#1.txt");

        assertEquals("easter", dp.getPart1Answer());
        assertEquals("advent", dp.getPart2Answer());
    }
}