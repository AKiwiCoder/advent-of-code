package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day02BathroomSecurityTest {
    @Test
    public void checkReal() {
        DailyProblem<String, String> dp = new Day02BathroomSecurity("/twenty_sixteen/Day02-BathroomSecurity-input.txt");

        assertEquals("14894", dp.getPart1Answer());
        assertEquals("26B96", dp.getPart2Answer());
    }

    @Test
    public void checkExample1() {
        DailyProblem<String, String> dp = new Day02BathroomSecurity("/twenty_sixteen/Day02-BathroomSecurity-example#1.txt");

        assertEquals("1985", dp.getPart1Answer());
        assertEquals("5DB3", dp.getPart2Answer());
    }
}