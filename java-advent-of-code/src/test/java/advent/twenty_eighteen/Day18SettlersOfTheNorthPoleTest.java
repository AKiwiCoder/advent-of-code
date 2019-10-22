package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day18SettlersOfTheNorthPoleTest {
    @Test
    public void checkReal() {
        Day18SettlersOfTheNorthPole im = new Day18SettlersOfTheNorthPole("/twenty_eighteen/Day18-SettlersOfTheNorthPole-input.txt");

        assertEquals(588436, im.getPart1Answer().longValue());
        assertEquals(195290, im.getPart2Answer().longValue());
    }

    @Test
    public void checkExample1() {
        Day18SettlersOfTheNorthPole im = new Day18SettlersOfTheNorthPole("/twenty_eighteen/Day18-SettlersOfTheNorthPole-example#1.txt");

        assertEquals(1147, im.getPart1Answer().longValue());
        assertEquals(0, im.getPart2Answer().longValue());
    }
}
