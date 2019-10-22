package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day20FirewallRulesTest {
    @Test
    public void checkReal() {
        DailyProblem<Long, Long> dp = new Day20FirewallRules("/twenty_sixteen/Day20-FirewallRules-input.txt", 0, 4294967295l);

        assertEquals(19449262, dp.getPart1Answer().longValue());
        assertEquals(119, dp.getPart2Answer().longValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Long, Long> dp = new Day20FirewallRules("/twenty_sixteen/Day20-FirewallRules-example#1.txt", 0, 9);

        assertEquals(3, dp.getPart1Answer().longValue());
        assertEquals(2, dp.getPart2Answer().longValue());
    }

    @Test
    public void checkExample2() {
        DailyProblem<Long, Long> dp = new Day20FirewallRules("/twenty_sixteen/Day20-FirewallRules-example#2.txt", 0, 19);

        assertEquals(-1, dp.getPart1Answer().longValue());
        assertEquals(0, dp.getPart2Answer().longValue());
    }
}