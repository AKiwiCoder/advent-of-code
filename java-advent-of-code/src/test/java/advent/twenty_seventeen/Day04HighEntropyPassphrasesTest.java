package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day04HighEntropyPassphrasesTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day04HighEntropyPassphrases("/twenty_seventeen/Day04-HighEntropyPassphrases-input.txt");

        assertEquals(386, dp.getPart1Answer().intValue());
        assertEquals(208, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day04HighEntropyPassphrases("/twenty_seventeen/Day04-HighEntropyPassphrases-example#1.txt");

        assertEquals(2, dp.getPart1Answer().intValue());
        assertEquals(2, dp.getPart2Answer().intValue());
    }
}