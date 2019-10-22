package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day12SubterraneanSustainabilityTest {
    @Test
    public void checkReal() {
        Day12SubterraneanSustainability im = new Day12SubterraneanSustainability("/twenty_eighteen/Day12-SubterraneanSustainability-input.txt");

        assertEquals(1816, im.getPart1Answer().longValue());
        assertEquals(399999999957l, im.getPart2Answer().longValue());
    }

    @Test
    public void checkExample1() {
        Day12SubterraneanSustainability im = new Day12SubterraneanSustainability("/twenty_eighteen/Day12-SubterraneanSustainability-example#1.txt");

        assertEquals(325, im.getPart1Answer().longValue());
        assertEquals(50000000501l, im.getPart2Answer().longValue());
    }
}
