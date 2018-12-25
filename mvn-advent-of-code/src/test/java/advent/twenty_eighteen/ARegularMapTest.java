package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ARegularMapTest {
    @Test
    public void checkReal() {
        ARegularMap cc = new ARegularMap("/twenty_eighteen/ARegularMap-input.txt");

        assertEquals(4239, cc.getPart1Answer().intValue());
        assertEquals(8205, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        ARegularMap cc = new ARegularMap("/twenty_eighteen/ARegularMap-example#1.txt");

        assertEquals(3, cc.getPart1Answer().intValue());
        assertEquals(0, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample2() {
        ARegularMap cc = new ARegularMap("/twenty_eighteen/ARegularMap-example#2.txt");

        assertEquals(10, cc.getPart1Answer().intValue());
        assertEquals(0, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample3() {
        ARegularMap cc = new ARegularMap("/twenty_eighteen/ARegularMap-example#3.txt");

        assertEquals(18, cc.getPart1Answer().intValue());
        assertEquals(0, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample4() {
        ARegularMap cc = new ARegularMap("/twenty_eighteen/ARegularMap-example#4.txt");

        assertEquals(23, cc.getPart1Answer().intValue());
        assertEquals(0, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample5() {
        ARegularMap cc = new ARegularMap("/twenty_eighteen/ARegularMap-example#5.txt");

        assertEquals(31, cc.getPart1Answer().intValue());
        assertEquals(0, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample6() {
        ARegularMap cc = new ARegularMap("/twenty_eighteen/ARegularMap-example#6.txt");

        assertEquals(4, cc.getPart1Answer().intValue());
        assertEquals(0, cc.getPart2Answer().intValue());
    }
}
