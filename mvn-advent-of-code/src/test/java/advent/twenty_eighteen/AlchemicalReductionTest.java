package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlchemicalReductionTest {
    @Test
    public void checkReal() {
        AlchemicalReduction im = new AlchemicalReduction("/twenty_eighteen/AlchemicalReduction-input.txt");

        assertEquals(11242, im.getPart1Answer().intValue());
        assertEquals(5492, im.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        AlchemicalReduction im = new AlchemicalReduction("/twenty_eighteen/AlchemicalReduction-example#1.txt");

        assertEquals(10, im.getPart1Answer().intValue());
        assertEquals(4, im.getPart2Answer().intValue());
    }
}
