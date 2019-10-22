package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day22ModeMazeTest {
    @Test
    public void checkReal() {
        Day22ModeMaze im = new Day22ModeMaze("/twenty_eighteen/Day22-ModeMaze-input.txt");

        assertEquals(9659, im.getPart1Answer().intValue());
        assertEquals(1043, im.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        Day22ModeMaze im = new Day22ModeMaze("/twenty_eighteen/Day22-ModeMaze-example#1.txt");

        assertEquals(114, im.getPart1Answer().intValue());
        assertEquals(45, im.getPart2Answer().intValue());
    }
}
