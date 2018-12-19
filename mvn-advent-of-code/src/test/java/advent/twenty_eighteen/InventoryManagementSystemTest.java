package advent.twenty_eighteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InventoryManagementSystemTest {
    @Test
    public void checkReal() {
        InventoryManagementSystem im = new InventoryManagementSystem("/twenty_eighteen/InventoryManagementSystem-input.txt");

        assertEquals(6225, im.getPart1Answer().intValue());
        assertEquals("revtaubfniyhsgxdoajwkqilp", im.getPart2Answer());
    }

    @Test
    public void checkExample1() {
        InventoryManagementSystem im = new InventoryManagementSystem("/twenty_eighteen/InventoryManagementSystem-example#1.txt");

        assertEquals(12, im.getPart1Answer().intValue());
        assertEquals("abcde", im.getPart2Answer());
    }

    @Test
    public void checkExample2() {
        InventoryManagementSystem im = new InventoryManagementSystem("/twenty_eighteen/InventoryManagementSystem-example#2.txt");

        assertEquals(0, im.getPart1Answer().intValue());
        assertEquals("fgij", im.getPart2Answer());
    }
}
