package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChocolateChartsTest {
    @Test
    public void checkReal() {
        ChocolateCharts cc = new ChocolateCharts("074501", "074501");

        assertEquals("1464411010", cc.getPart1Answer());
        assertEquals("20288091", cc.getPart2Answer());
    }

    @Test
    public void checkExample1() {
        ChocolateCharts cc = new ChocolateCharts("9", "51589");

        assertEquals("5158916779", cc.getPart1Answer());
        assertEquals("9", cc.getPart2Answer());
    }

    @Test
    public void checkExample2() {
        ChocolateCharts cc = new ChocolateCharts("5", "01245");

        assertEquals("0124515891", cc.getPart1Answer());
        assertEquals("5", cc.getPart2Answer());
    }

    @Test
    public void checkExample3() {
        ChocolateCharts cc = new ChocolateCharts("18", "92510");

        assertEquals("9251071085", cc.getPart1Answer());
        assertEquals("18", cc.getPart2Answer());
    }

    @Test
    public void checkExample4() {
        ChocolateCharts cc = new ChocolateCharts("2018", "59414");

        assertEquals("5941429882", cc.getPart1Answer());
        assertEquals("2018", cc.getPart2Answer());
    }
}
