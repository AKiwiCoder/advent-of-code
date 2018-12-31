package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day20ARegularMapTest {
    @Test
    public void checkReal() {
        Day20ARegularMap cc = new Day20ARegularMap("/twenty_eighteen/Day20-ARegularMap-input.txt");

        assertEquals(4239, cc.getPart1Answer().intValue());
        assertEquals(8205, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        Day20ARegularMap cc = new Day20ARegularMap("/twenty_eighteen/Day20-ARegularMap-example#1.txt");

        assertEquals(3, cc.getPart1Answer().intValue());
        assertEquals(0, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample2() {
        Day20ARegularMap cc = new Day20ARegularMap("/twenty_eighteen/Day20-ARegularMap-example#2.txt");

        assertEquals(10, cc.getPart1Answer().intValue());
        assertEquals(0, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample3() {
        Day20ARegularMap cc = new Day20ARegularMap("/twenty_eighteen/Day20-ARegularMap-example#3.txt");

        assertEquals(18, cc.getPart1Answer().intValue());
        assertEquals(0, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample4() {
        Day20ARegularMap cc = new Day20ARegularMap("/twenty_eighteen/Day20-ARegularMap-example#4.txt");

        assertEquals(23, cc.getPart1Answer().intValue());
        assertEquals(0, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample5() {
        Day20ARegularMap cc = new Day20ARegularMap("/twenty_eighteen/Day20-ARegularMap-example#5.txt");

        assertEquals(31, cc.getPart1Answer().intValue());
        assertEquals(0, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample6() {
        Day20ARegularMap cc = new Day20ARegularMap("/twenty_eighteen/Day20-ARegularMap-example#6.txt");

        assertEquals(4, cc.getPart1Answer().intValue());
        assertEquals(0, cc.getPart2Answer().intValue());
    }
}
