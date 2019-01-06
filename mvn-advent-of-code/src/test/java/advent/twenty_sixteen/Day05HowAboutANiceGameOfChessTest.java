package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class Day05HowAboutANiceGameOfChessTest {
    @Test
    public void checkReal() throws NoSuchAlgorithmException {
        DailyProblem<String, String> dp = new Day05HowAboutANiceGameOfChess("abbhdwsy");

        assertEquals("801b56a7", dp.getPart1Answer());
        assertEquals("424a0197", dp.getPart2Answer());
    }

    @Test
    public void checkExample1() throws NoSuchAlgorithmException {
        DailyProblem<String, String> dp = new Day05HowAboutANiceGameOfChess("abc");

        assertEquals("18f47a30", dp.getPart1Answer());
        assertEquals("05ace8e3", dp.getPart2Answer());
    }
}