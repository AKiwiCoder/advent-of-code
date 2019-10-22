package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day25FourDimensionalAdventureTest {
    @Test
    public void checkReal() {
        Day25FourDimensionalAdventure cc = new Day25FourDimensionalAdventure("/twenty_eighteen/Day25-FourDimensionalAdventure-input.txt");
        assertEquals(314, cc.getPart1Answer().intValue());
    }

    @Test
    public void checkExample1() {
        Day25FourDimensionalAdventure cc = new Day25FourDimensionalAdventure("/twenty_eighteen/Day25-FourDimensionalAdventure-example#1.txt");
        assertEquals(2, cc.getPart1Answer().intValue());
    }

    @Test
    public void checkExample2() {
        Day25FourDimensionalAdventure cc = new Day25FourDimensionalAdventure("/twenty_eighteen/Day25-FourDimensionalAdventure-example#2.txt");
        assertEquals(4, cc.getPart1Answer().intValue());
    }

    @Test
    public void checkExample3() {
        Day25FourDimensionalAdventure cc = new Day25FourDimensionalAdventure("/twenty_eighteen/Day25-FourDimensionalAdventure-example#3.txt");
        assertEquals(3, cc.getPart1Answer().intValue());
    }

    @Test
    public void checkExample4() {
        Day25FourDimensionalAdventure cc = new Day25FourDimensionalAdventure("/twenty_eighteen/Day25-FourDimensionalAdventure-example#4.txt");
        assertEquals(8, cc.getPart1Answer().intValue());
    }
}
