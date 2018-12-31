package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day03NoMatterHowYouSliceItTest {
    @Test
    public void checkReal() {
        Day03NoMatterHowYouSliceIt im = new Day03NoMatterHowYouSliceIt("/twenty_eighteen/Day03-NoMatterHowYouSliceIt-input.txt");

        assertEquals(110389, im.getPart1Answer().intValue());
        assertEquals(552, im.getPart2Answer().intValue());
    }

    @Test
    public void checkExample1() {
        Day03NoMatterHowYouSliceIt im = new Day03NoMatterHowYouSliceIt("/twenty_eighteen/Day03-NoMatterHowYouSliceIt-example#1.txt");

        assertEquals(4, im.getPart1Answer().intValue());
        assertEquals(3, im.getPart2Answer().intValue());
    }
}
