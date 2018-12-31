package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ModeMazeTest {
    @Test
    public void checkReal() {
        ModeMaze im = new ModeMaze("/twenty_eighteen/ModeMaze-input.txt");

        assertEquals(9659, im.getPart1Answer().intValue());
//        assertEquals(1043, im.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        ModeMaze im = new ModeMaze("/twenty_eighteen/ModeMaze-example#1.txt");

        assertEquals(114, im.getPart1Answer().intValue());
        assertEquals(45, im.getPart2Answer().intValue());
    }
}
