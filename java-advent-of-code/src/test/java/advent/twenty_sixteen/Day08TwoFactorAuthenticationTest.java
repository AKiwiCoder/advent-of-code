package advent.twenty_sixteen;

import advent.common.DailyProblem;
import org.junit.Test;

import static org.junit.Assert.*;

public class Day08TwoFactorAuthenticationTest {
    private static final String REAL = //
            ".##..####.###..#..#.###..####.###....##.###...###.\n" + //
            "#..#.#....#..#.#..#.#..#....#.#..#....#.#..#.#....\n" + //
            "#..#.###..###..#..#.#..#...#..###.....#.#..#.#....\n" + //
            "####.#....#..#.#..#.###...#...#..#....#.###...##..\n" + //
            "#..#.#....#..#.#..#.#....#....#..#.#..#.#.......#.\n" + //
            "#..#.#....###...##..#....####.###...##..#....###..\n";

    @Test
    public void checkReal() {
        DailyProblem<Integer, String> dp = new Day08TwoFactorAuthentication("/twenty_sixteen/Day08-TwoFactorAuthentication-input.txt", 6, 50);

        assertEquals(123, dp.getPart1Answer().intValue());
        assertEquals(REAL, dp.getPart2Answer());
    }

    private static final String EXAMPLE_1 = //
            ".#..#.#\n" + //
            "#.#....\n" + //
            ".#.....\n";

    @Test
    public void checkExample1() {
        DailyProblem<Integer, String> dp = new Day08TwoFactorAuthentication("/twenty_sixteen/Day08-TwoFactorAuthentication-example#1.txt", 3 , 7);

        assertEquals(6, dp.getPart1Answer().intValue());
        assertEquals(EXAMPLE_1, dp.getPart2Answer());
    }
}