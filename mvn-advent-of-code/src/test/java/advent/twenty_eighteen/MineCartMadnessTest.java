package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MineCartMadnessTest {
    @Test
    public void checkReal() {
        MineCartMadness im = new MineCartMadness("/twenty_eighteen/MineCartMadness-input.txt");

        assertEquals("139,65", im.getPart1Answer());
        assertEquals("40,77", im.getPart2Answer());
    }

    @Test
    public void checkExample1() {
        MineCartMadness im = new MineCartMadness("/twenty_eighteen/MineCartMadness-example#1.txt");

        assertEquals("7,3", im.getPart1Answer());
        assertEquals("9,4", im.getPart2Answer());
    }

    @Test
    public void checkExample2() {
        MineCartMadness im = new MineCartMadness("/twenty_eighteen/MineCartMadness-example#2.txt");

        assertEquals("2,0", im.getPart1Answer());
        assertEquals("6,4", im.getPart2Answer());
    }
}
