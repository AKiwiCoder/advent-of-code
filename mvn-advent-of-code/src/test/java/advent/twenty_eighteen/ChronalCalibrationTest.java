package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChronalCalibrationTest {
    @Test
    public void checkReal() {
        ChronalCalibration cc = new ChronalCalibration("/twenty_eighteen/ChronalCalibration-input.txt");

        assertEquals(430, cc.getPart1Answer().intValue());
        assertEquals(462, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        ChronalCalibration cc = new ChronalCalibration("/twenty_eighteen/ChronalCalibration-example#1.txt");

        assertEquals(3, cc.getPart1Answer().intValue());
        assertEquals(2, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample2() {
        ChronalCalibration cc = new ChronalCalibration("/twenty_eighteen/ChronalCalibration-example#2.txt");

        assertEquals(0, cc.getPart1Answer().intValue());
        assertEquals(0, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample3() {
        ChronalCalibration cc = new ChronalCalibration("/twenty_eighteen/ChronalCalibration-example#3.txt");

        assertEquals(4, cc.getPart1Answer().intValue());
        assertEquals(10, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample4() {
        ChronalCalibration cc = new ChronalCalibration("/twenty_eighteen/ChronalCalibration-example#4.txt");

        assertEquals(4, cc.getPart1Answer().intValue());
        assertEquals(5, cc.getPart2Answer().intValue());
    }

    @Test
    public void checkExample5() {
        ChronalCalibration cc = new ChronalCalibration("/twenty_eighteen/ChronalCalibration-example#5.txt");

        assertEquals(1, cc.getPart1Answer().intValue());
        assertEquals(14, cc.getPart2Answer().intValue());
    }
}
