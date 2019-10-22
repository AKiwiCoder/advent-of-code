package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day15BeverageBanditsTest {
    @Test
    public void checkReal() {
        Day15BeverageBandits cc = new Day15BeverageBandits("/twenty_eighteen/Day15-BeverageBandits-input.txt");

        assertEquals(346574, cc.getPart1Answer().intValue());
        assertEquals(60864, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        Day15BeverageBandits cc = new Day15BeverageBandits("/twenty_eighteen/Day15-BeverageBandits-example#1.txt");

        assertEquals(27828, cc.getPart1Answer().intValue());
        assertEquals(1328, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample2() {
        Day15BeverageBandits cc = new Day15BeverageBandits("/twenty_eighteen/Day15-BeverageBandits-example#2.txt");

        assertEquals(27730, cc.getPart1Answer().intValue());
        assertEquals(4988, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample3() {
        Day15BeverageBandits cc = new Day15BeverageBandits("/twenty_eighteen/Day15-BeverageBandits-example#3.txt");

        assertEquals(36334, cc.getPart1Answer().intValue());
        assertEquals(29064, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample4() {
        Day15BeverageBandits cc = new Day15BeverageBandits("/twenty_eighteen/Day15-BeverageBandits-example#4.txt");

        assertEquals(39514, cc.getPart1Answer().intValue());
        assertEquals(31284, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample5() {
        Day15BeverageBandits cc = new Day15BeverageBandits("/twenty_eighteen/Day15-BeverageBandits-example#5.txt");

        assertEquals(27755, cc.getPart1Answer().intValue());
        assertEquals(3478, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample6() {
        Day15BeverageBandits cc = new Day15BeverageBandits("/twenty_eighteen/Day15-BeverageBandits-example#6.txt");

        assertEquals(28944, cc.getPart1Answer().intValue());
        assertEquals(6474, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample7() {
        Day15BeverageBandits cc = new Day15BeverageBandits("/twenty_eighteen/Day15-BeverageBandits-example#7.txt");

        assertEquals(18740, cc.getPart1Answer().intValue());
        assertEquals(1140, cc.getPart2Answer().intValue());
    }
}

