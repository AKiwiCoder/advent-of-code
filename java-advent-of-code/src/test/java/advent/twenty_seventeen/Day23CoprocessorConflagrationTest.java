package advent.twenty_seventeen;

import advent.common.DailyProblem;
import advent.twenty_seventeen.support.DuetCodeGenerator;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day23CoprocessorConflagrationTest {
    @Test
    public void checkReal() {
        DailyProblem<Integer, Integer> dp = new Day23CoprocessorConflagration("/twenty_seventeen/Day23-CoprocessorConflagration-input.txt");

        assertEquals(3025, dp.getPart1Answer().intValue());
        assertEquals(915, dp.getPart2Answer().intValue());
    }

    @Test
    @Ignore
    public void generateProgram() {
        DuetCodeGenerator generator = new DuetCodeGenerator("/twenty_seventeen/Day23-CoprocessorConflagration-input.txt");
        generator.generate(System.out);
    }
}

