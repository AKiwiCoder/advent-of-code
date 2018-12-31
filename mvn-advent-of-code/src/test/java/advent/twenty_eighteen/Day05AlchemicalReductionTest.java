package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day05AlchemicalReductionTest {
    @Test
    public void checkReal() {
        Day05AlchemicalReduction im = new Day05AlchemicalReduction("/twenty_eighteen/Day05-AlchemicalReduction-input.txt");

        assertEquals(11242, im.getPart1Answer().intValue());
        assertEquals(5492, im.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        Day05AlchemicalReduction im = new Day05AlchemicalReduction("/twenty_eighteen/Day05-AlchemicalReduction-example#1.txt");

        assertEquals(10, im.getPart1Answer().intValue());
        assertEquals(4, im.getPart2Answer().intValue());
    }
}
