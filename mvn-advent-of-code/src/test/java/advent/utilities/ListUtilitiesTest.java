package advent.utilities;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class ListUtilitiesTest {
    private static final Set<String> EMPTY = new HashSet<>();
    private static final Set<String> ONE = new HashSet<>(Arrays.asList("A"));
    private static final Set<String> TWO = new HashSet<>(Arrays.asList("A", "B"));
    private static final Set<String> THREE = new HashSet<>(Arrays.asList("A", "B", "C"));


@Test
    public void testGenerateCombinations() {
    List<List<String>> test0 = ListUtilities.generateCombinations(null, EMPTY, null);
    System.out.println(test0);
    assertEquals(0, test0.size());

    List<List<String>> test1 = ListUtilities.generateCombinations(null, ONE, null);
    System.out.println(test1);
    assertEquals(1, test1.size());

    List<List<String>> test2 = ListUtilities.generateCombinations(null, TWO, null);
    System.out.println(test2);
    assertEquals(2, test2.size());

    List<List<String>> test3 = ListUtilities.generateCombinations(null, THREE, null);
    System.out.println(test3);
    assertEquals(6, test3.size());
}
}