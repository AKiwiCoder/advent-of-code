package advent.twenty_fifteen;

import advent.common.DailyProblem;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Day07SomeAssemblyRequiredTest {
    @Test
    public void checkReal() {
        DailyProblem<Map<String, Integer>, Map<String, Integer>> dp = new Day07SomeAssemblyRequired("/twenty_fifteen/Day07-SomeAssemblyRequired-input.txt");

        assertEquals(46065, dp.getPart1Answer().get("a").intValue());
        assertEquals(14134, dp.getPart2Answer().get("a").intValue());
    }

    @Test
    public void checkExample1() {
        DailyProblem<Map<String, Integer>, Map<String, Integer>> dp = new Day07SomeAssemblyRequired("/twenty_fifteen/Day07-SomeAssemblyRequired-example#1.txt");

        Map<String, Integer> part1Answer = dp.getPart1Answer();
        assertEquals(72, part1Answer.get("d").intValue());
        assertEquals(507, part1Answer.get("e").intValue());
        assertEquals(492, part1Answer.get("f").intValue());
        assertEquals(114, part1Answer.get("g").intValue());
        assertEquals(65412, part1Answer.get("h").intValue());
        assertEquals(65079, part1Answer.get("i").intValue());
        assertEquals(123, part1Answer.get("x").intValue());
        assertEquals(456, part1Answer.get("y").intValue());
    }
}