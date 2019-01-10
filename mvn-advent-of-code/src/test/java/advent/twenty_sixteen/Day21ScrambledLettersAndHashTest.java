package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day21ScrambledLettersAndHashTest {
    @Test
    public void checkReal() {
        DailyProblem<String, String> dp = new Day21ScrambledLettersAndHash("/twenty_sixteen/Day21-ScrambledLettersAndHash-input.txt", "abcdefgh", "fbgdceah");

        assertEquals("bgfacdeh", dp.getPart1Answer());
        assertEquals("bdgheacf", dp.getPart2Answer());
    }

    @Test
    public void checkExample1() {
        DailyProblem<String, String> dp = new Day21ScrambledLettersAndHash("/twenty_sixteen/Day21-ScrambledLettersAndHash-example#1.txt", "abcde", null);

        assertEquals("decab", dp.getPart1Answer());
        // assertEquals("", dp.getPart2Answer());  -- Cannot uniquely decode a 5 character password
    }
}