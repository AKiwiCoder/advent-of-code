package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MarbleManiaTest {
    @Test
    public void checkReal() {
        MarbleMania im = new  MarbleMania("/twenty_eighteen/MarbleMania-input.txt");

        assertEquals(425688, im.getPart1Answer().longValue());
        assertEquals(3526561003l, im.getPart2Answer().longValue());
    }

    @Test
    public void checkExample1() {
        MarbleMania im = new  MarbleMania("/twenty_eighteen/MarbleMania-example#1.txt");

        assertEquals(32, im.getPart1Answer().longValue());
        assertEquals(22563, im.getPart2Answer().longValue());
    }

    @Test
    public void checkExample2() {
        MarbleMania im = new  MarbleMania("/twenty_eighteen/MarbleMania-example#2.txt");

        assertEquals(8317, im.getPart1Answer().longValue());
        assertEquals(74765078, im.getPart2Answer().longValue());
    }

    @Test
    public void checkExample3() {
        MarbleMania im = new  MarbleMania("/twenty_eighteen/MarbleMania-example#3.txt");

        assertEquals(146373, im.getPart1Answer().longValue());
        assertEquals(1406506154l, im.getPart2Answer().longValue());
    }

    @Test
    public void checkExample4() {
        MarbleMania im = new  MarbleMania("/twenty_eighteen/MarbleMania-example#4.txt");

        assertEquals(2764, im.getPart1Answer().longValue());
        assertEquals(20548882, im.getPart2Answer().longValue());
    }

    @Test
    public void checkExample5() {
        MarbleMania im = new  MarbleMania("/twenty_eighteen/MarbleMania-example#5.txt");

        assertEquals(54718, im.getPart1Answer().longValue());
        assertEquals(507583214l, im.getPart2Answer().longValue());
    }

    @Test
    public void checkExample6() {
        MarbleMania im = new  MarbleMania("/twenty_eighteen/MarbleMania-example#6.txt");

        assertEquals(37305, im.getPart1Answer().longValue());
        assertEquals(320997431, im.getPart2Answer().longValue());
    }
}
