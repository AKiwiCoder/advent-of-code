package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day09MarbleManiaTest {
    @Test
    public void checkReal() {
        Day09MarbleMania im = new Day09MarbleMania("/twenty_eighteen/Day09-MarbleMania-input.txt");

        assertEquals(425688, im.getPart1Answer().longValue());
        assertEquals(3526561003l, im.getPart2Answer().longValue());
    }

    @Test
    public void checkExample1() {
        Day09MarbleMania im = new Day09MarbleMania("/twenty_eighteen/Day09-MarbleMania-example#1.txt");

        assertEquals(32, im.getPart1Answer().longValue());
        assertEquals(22563, im.getPart2Answer().longValue());
    }

    @Test
    public void checkExample2() {
        Day09MarbleMania im = new Day09MarbleMania("/twenty_eighteen/Day09-MarbleMania-example#2.txt");

        assertEquals(8317, im.getPart1Answer().longValue());
        assertEquals(74765078, im.getPart2Answer().longValue());
    }

    @Test
    public void checkExample3() {
        Day09MarbleMania im = new Day09MarbleMania("/twenty_eighteen/Day09-MarbleMania-example#3.txt");

        assertEquals(146373, im.getPart1Answer().longValue());
        assertEquals(1406506154l, im.getPart2Answer().longValue());
    }

    @Test
    public void checkExample4() {
        Day09MarbleMania im = new Day09MarbleMania("/twenty_eighteen/Day09-MarbleMania-example#4.txt");

        assertEquals(2764, im.getPart1Answer().longValue());
        assertEquals(20548882, im.getPart2Answer().longValue());
    }

    @Test
    public void checkExample5() {
        Day09MarbleMania im = new Day09MarbleMania("/twenty_eighteen/Day09-MarbleMania-example#5.txt");

        assertEquals(54718, im.getPart1Answer().longValue());
        assertEquals(507583214l, im.getPart2Answer().longValue());
    }

    @Test
    public void checkExample6() {
        Day09MarbleMania im = new Day09MarbleMania("/twenty_eighteen/Day09-MarbleMania-example#6.txt");

        assertEquals(37305, im.getPart1Answer().longValue());
        assertEquals(320997431, im.getPart2Answer().longValue());
    }
}
