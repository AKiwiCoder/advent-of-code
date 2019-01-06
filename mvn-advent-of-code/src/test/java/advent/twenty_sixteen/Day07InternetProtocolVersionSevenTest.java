package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day07InternetProtocolVersionSevenTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day07InternetProtocolVersionSeven("/twenty_sixteen/Day07-InternetProtocolVersionSeven-input.txt");

        assertEquals(110, dp.getPart1Answer().intValue());
        assertEquals(242, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Integer, Integer> dp = new Day07InternetProtocolVersionSeven("/twenty_sixteen/Day07-InternetProtocolVersionSeven-example#1.txt");

        assertEquals(2, dp.getPart1Answer().intValue());
        assertEquals(0, dp.getPart2Answer().intValue());
    }

    @Test
    public void checkExample2() {
        DailyProblem<Integer, Integer> dp = new Day07InternetProtocolVersionSeven("/twenty_sixteen/Day07-InternetProtocolVersionSeven-example#2.txt");

        assertEquals(0, dp.getPart1Answer().intValue());
        assertEquals(3, dp.getPart2Answer().intValue());
    }
}