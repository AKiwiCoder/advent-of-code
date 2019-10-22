package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day22WizardSimulator20xxTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day22WizardSimulator20xx("/twenty_fifteen/Day22-WizardSimulator20xx-input.txt");

        assertEquals(900, dp.getPart1Answer().intValue());
        assertEquals(1216, dp.getPart2Answer().intValue());
    }
}