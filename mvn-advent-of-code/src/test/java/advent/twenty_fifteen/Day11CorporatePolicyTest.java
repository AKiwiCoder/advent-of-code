package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day11CorporatePolicyTest {
    @Test
    public void checkAcceptablePasswords() {
        assertFalse(Day11CorporatePolicy.validPassword("hijklmmn"));
        assertFalse(Day11CorporatePolicy.validPassword("abbceffg"));
        assertFalse(Day11CorporatePolicy.validPassword("abbcegjk"));

        assertTrue(Day11CorporatePolicy.validPassword(("abcaaaa")));
        assertTrue(Day11CorporatePolicy.validPassword(("abcaabb")));
    }

    @Test
    public void checkPasswordIncrement() {
        assertEquals("aab", Day11CorporatePolicy.incrementPassword("aaa"));
        assertEquals("bba", Day11CorporatePolicy.incrementPassword("baz"));
    }

    @Test
    public void checkReal() {
        DailyProblem<String, String> dp = new Day11CorporatePolicy("cqjxjnds");

        assertEquals("cqjxxyzz", dp.getPart1Answer());
        assertEquals("cqkaabcc", dp.getPart2Answer());
    }

    @Test
    public void checkExample1() {
        DailyProblem<String, String> dp = new Day11CorporatePolicy("abcdefgh");

        assertEquals("abcdffaa", dp.getPart1Answer());
        assertEquals("abcdffbb", dp.getPart2Answer());
    }

    @Test
    public void checkExample2() {
        DailyProblem<String, String> dp = new Day11CorporatePolicy("ghijklmn");

        assertEquals("ghjaabcc", dp.getPart1Answer());
        assertEquals("ghjbbcdd", dp.getPart2Answer());
    }
}