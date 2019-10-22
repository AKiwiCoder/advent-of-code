package advent.utilities;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ArrayUtilitiesTest {

    @Test
    public void testIntArrayCreationAndPopulation() {
        int[][] result = ArrayUtilities.createIntArray(2, 3, (x, y) -> x * 1000 + y);

        assertArrayEquals(new int[]{0, 1000}, result[0]);
        assertArrayEquals(new int[]{1, 1001}, result[1]);
        assertArrayEquals(new int[]{2, 1002}, result[2]);
    }

}