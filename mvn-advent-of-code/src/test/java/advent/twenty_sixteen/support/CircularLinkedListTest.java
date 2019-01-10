package advent.twenty_sixteen.support;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CircularLinkedListTest {
    @Test
    public void testDeleteMiddle() {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        list.insertBeforeCurrent(0);
        assertEquals(0, list.middle().intValue());
        list.insertBeforeCurrent(1);
        assertEquals(1, list.middle().intValue());
        list.insertBeforeCurrent(2);
        assertEquals(1, list.middle().intValue());
        list.insertBeforeCurrent(3);
        assertEquals(2, list.middle().intValue());
        list.insertBeforeCurrent(4);
        assertEquals(2, list.middle().intValue());
        list.insertBeforeCurrent(5);
        assertEquals(3, list.middle().intValue());
        list.insertBeforeCurrent(6);
        assertEquals(3, list.middle().intValue());
        list.insertBeforeCurrent(7);
        assertEquals(4, list.middle().intValue());
        list.insertBeforeCurrent(8);
        assertEquals(4, list.middle().intValue());
        list.insertBeforeCurrent(9);
        assertEquals(5, list.middle().intValue());

        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", list.toString());
        assertEquals(5, list.middle().intValue());
        list.deleteMiddle();
        assertEquals("[0, 1, 2, 3, 4, 6, 7, 8, 9]", list.toString());
        assertEquals(4, list.middle().intValue());
        list.deleteMiddle();
        assertEquals("[0, 1, 2, 3, 6, 7, 8, 9]", list.toString());
        assertEquals(6, list.middle().intValue());
        list.deleteMiddle();
        assertEquals("[0, 1, 2, 3, 7, 8, 9]", list.toString());
        assertEquals(3, list.middle().intValue());
        list.deleteMiddle();
        assertEquals("[0, 1, 2, 7, 8, 9]", list.toString());
        assertEquals(7, list.middle().intValue());
        list.deleteMiddle();
        assertEquals("[0, 1, 2, 8, 9]", list.toString());
        assertEquals(2, list.middle().intValue());
        list.deleteMiddle();
        assertEquals("[0, 1, 8, 9]", list.toString());
        assertEquals(8, list.middle().intValue());
        list.deleteMiddle();
        assertEquals("[0, 1, 9]", list.toString());
        assertEquals(1, list.middle().intValue());
        list.deleteMiddle();
        assertEquals("[0, 9]", list.toString());
        assertEquals(9, list.middle().intValue());
        list.deleteMiddle();
        assertEquals("[0]", list.toString());
        assertEquals(0, list.middle().intValue());
        list.deleteMiddle();
        assertEquals("[]", list.toString());
    }
}