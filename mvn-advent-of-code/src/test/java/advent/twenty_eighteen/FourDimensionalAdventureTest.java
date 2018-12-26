package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FourDimensionalAdventureTest {
    @Test
    public void checkReal() {
        FourDimensionalAdventure cc = new FourDimensionalAdventure("/twenty_eighteen/FourDimensionalAdventure-input.txt");
        assertEquals(314, cc.getPart1Answer().intValue());
    }

    @Test
    public void checkExample1() {
        FourDimensionalAdventure cc = new FourDimensionalAdventure("/twenty_eighteen/FourDimensionalAdventure-example#1.txt");
        assertEquals(2, cc.getPart1Answer().intValue());
    }

    @Test
    public void checkExample2() {
        FourDimensionalAdventure cc = new FourDimensionalAdventure("/twenty_eighteen/FourDimensionalAdventure-example#2.txt");
        assertEquals(4, cc.getPart1Answer().intValue());
    }

    @Test
    public void checkExample3() {
        FourDimensionalAdventure cc = new FourDimensionalAdventure("/twenty_eighteen/FourDimensionalAdventure-example#3.txt");
        assertEquals(3, cc.getPart1Answer().intValue());
    }

    @Test
    public void checkExample4() {
        FourDimensionalAdventure cc = new FourDimensionalAdventure("/twenty_eighteen/FourDimensionalAdventure-example#4.txt");
        assertEquals(8, cc.getPart1Answer().intValue());
    }
}
