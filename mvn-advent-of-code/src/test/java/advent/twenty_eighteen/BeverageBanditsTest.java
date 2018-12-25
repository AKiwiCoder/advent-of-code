package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BeverageBanditsTest {
    @Test
    public void checkReal() {
        BeverageBandits cc = new BeverageBandits("/twenty_eighteen/BeverageBandits-input.txt");

        assertEquals(346574, cc.getPart1Answer().intValue());
        assertEquals(60864, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        BeverageBandits cc = new BeverageBandits("/twenty_eighteen/BeverageBandits-example#1.txt");

        assertEquals(27828, cc.getPart1Answer().intValue());
        assertEquals(1328, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample2() {
        BeverageBandits cc = new BeverageBandits("/twenty_eighteen/BeverageBandits-example#2.txt");

        assertEquals(27730, cc.getPart1Answer().intValue());
        assertEquals(4988, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample3() {
        BeverageBandits cc = new BeverageBandits("/twenty_eighteen/BeverageBandits-example#3.txt");

        assertEquals(36334, cc.getPart1Answer().intValue());
        assertEquals(29064, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample4() {
        BeverageBandits cc = new BeverageBandits("/twenty_eighteen/BeverageBandits-example#4.txt");

        assertEquals(39514, cc.getPart1Answer().intValue());
        assertEquals(31284, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample5() {
        BeverageBandits cc = new BeverageBandits("/twenty_eighteen/BeverageBandits-example#5.txt");

        assertEquals(27755, cc.getPart1Answer().intValue());
        assertEquals(3478, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample6() {
        BeverageBandits cc = new BeverageBandits("/twenty_eighteen/BeverageBandits-example#6.txt");

        assertEquals(28944, cc.getPart1Answer().intValue());
        assertEquals(6474, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample7() {
        BeverageBandits cc = new BeverageBandits("/twenty_eighteen/BeverageBandits-example#7.txt");

        assertEquals(18740, cc.getPart1Answer().intValue());
        assertEquals(1140, cc.getPart2Answer().intValue());
    }
}

