package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SubterraneanSustainabilityTest {
    @Test
    public void checkReal() {
        SubterraneanSustainability im = new SubterraneanSustainability("/twenty_eighteen/SubterraneanSustainability-input.txt");

        assertEquals(1816, im.getPart1Answer().longValue());
        assertEquals(399999999957l, im.getPart2Answer().longValue());
    }

    @Test
    public void checkExample1() {
        SubterraneanSustainability im = new SubterraneanSustainability("/twenty_eighteen/SubterraneanSustainability-example#1.txt");

        assertEquals(325, im.getPart1Answer().longValue());
        assertEquals(50000000501l, im.getPart2Answer().longValue());
    }
}
