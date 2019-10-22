package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day02InventoryManagementSystemTest {
    @Test
    public void checkReal() {
        Day02InventoryManagementSystem im = new Day02InventoryManagementSystem("/twenty_eighteen/Day02-InventoryManagementSystem-input.txt");

        assertEquals(6225, im.getPart1Answer().intValue());
        assertEquals("revtaubfniyhsgxdoajwkqilp", im.getPart2Answer());
    }

    @Test
    public void checkExample1() {
        Day02InventoryManagementSystem im = new Day02InventoryManagementSystem("/twenty_eighteen/Day02-InventoryManagementSystem-example#1.txt");

        assertEquals(12, im.getPart1Answer().intValue());
        assertEquals("abcde", im.getPart2Answer());
    }

    @Test
    public void checkExample2() {
        Day02InventoryManagementSystem im = new Day02InventoryManagementSystem("/twenty_eighteen/Day02-InventoryManagementSystem-example#2.txt");

        assertEquals(0, im.getPart1Answer().intValue());
        assertEquals("fgij", im.getPart2Answer());
    }
}
