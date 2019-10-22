package advent.twenty_seventeen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day16PermutationPromenadeTest {
    @Test
    public void checkReal() {
        DailyProblem<String, String> dp = new Day16PermutationPromenade("/twenty_seventeen/Day16-PermutationPromenade-input.txt", 16);

        assertEquals("bijankplfgmeodhc", dp.getPart1Answer());
        assertEquals("bpjahknliomefdgc", dp.getPart2Answer());
    }

    @Test
    public void checkExample1() {
        DailyProblem<String, String> dp = new Day16PermutationPromenade("/twenty_seventeen/Day16-PermutationPromenade-example#1.txt", 5);

        assertEquals("baedc", dp.getPart1Answer());
        assertEquals("abcde", dp.getPart2Answer());
    }
}

