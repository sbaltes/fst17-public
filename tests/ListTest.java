import de.unitrier.st.fst17.list.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListTest {
    private static final double DELTA = 0.00001;
    private List list;

    @BeforeEach
    void setUp() {
        list = new List();
    }

    @Test
    void testAppendAndLength() {
        assertEquals(0, list.getLength());

        list.append(0.9);
        assertEquals(1, list.getLength());
        assertEquals(0.9, list.getBegin().getValue(), DELTA);
        assertNull(list.getBegin().getPrev());
        assertNull(list.getBegin().getNext());
        assertEquals(0.9, list.getEnd().getValue(), DELTA);
        assertNull(list.getEnd().getPrev());
        assertNull(list.getEnd().getNext());

        list.append(0.5);
        assertEquals(2, list.getLength());
        assertEquals(0.9, list.getBegin().getValue(), DELTA);
        assertNull(list.getBegin().getPrev());
        assertEquals(0.5, list.getBegin().getNext().getValue(), DELTA);
        assertEquals(0.5, list.getEnd().getValue(), DELTA);
        assertEquals(0.9, list.getEnd().getPrev().getValue(), DELTA);
        assertNull(list.getEnd().getNext());
        testBeginEndPointers(list);

        list.append(0.4);
        assertEquals(3, list.getLength());
        assertEquals(0.9, list.getBegin().getValue(), DELTA);
        assertNull(list.getBegin().getPrev());
        assertEquals(0.5, list.getBegin().getNext().getValue(), DELTA);
        assertEquals(0.4, list.getEnd().getValue(), DELTA);
        assertEquals(0.5, list.getEnd().getPrev().getValue(), DELTA);
        assertNull(list.getEnd().getNext());
        testBeginEndPointers(list);
    }

    @Test
    void testAppendElementFromExistingList() {
        assertEquals(0, list.getLength());

        list.append(new double[]{0.9, 0.5, 0.4});

        List list2 = new List();
        list2.append(list.getBegin().getNext());
        assertNull(list2.getBegin().getPrev());
        assertNull(list2.getBegin().getNext());
    }

    @Test
    void testToArray() {
        assertArrayEquals(new double[]{}, list.asArray(), DELTA);
        list.append(0.9);
        list.append(0.5);
        list.append(0.4);
        assertArrayEquals(new double[]{0.9, 0.5, 0.4}, list.asArray(), DELTA);
    }

    @Test
    void testEmpty() {
        assertTrue(list.isEmpty());
        list.append(0.9);
        list.append(0.5);
        list.append(0.4);
        assertFalse(list.isEmpty());
        list.empty();
        assertEquals(0, list.getLength());
        assertTrue(list.isEmpty());
        assertArrayEquals(new double[]{}, list.asArray(), DELTA);
    }

    @Test
    void testAppendArray() {
        list.append(new double[]{0.9, 0.5, 0.4});
        assertArrayEquals(new double[]{0.9, 0.5, 0.4}, list.asArray(), DELTA);
    }

    @Test
    void testAppendList() {
        List otherList = new List();
        otherList.append(new double[]{0.9, 0.5, 0.4});

        // check if append of one element does not append whole list
        list.append(otherList.getBegin());
        assertEquals(list.getLength(), 1);
        assertEquals(list.getBegin().getValue(), 0.9, DELTA);
        assertNull(list.getBegin().getPrev());
        assertNull(list.getBegin().getNext());

        list.empty();
        otherList.empty();
        otherList.append(new double[]{0.9, 0.5, 0.4});
        list.append(otherList);
        assertArrayEquals(new double[]{0.9, 0.5, 0.4}, list.asArray(), DELTA);
    }

    @Test
    void testInsert() {
        list.append(new double[]{0.2, 0.4, 0.5, 0.8});
        list.insert(new List.Element(0.1));
        assertArrayEquals(new double[]{0.1, 0.2, 0.4, 0.5, 0.8}, list.asArray(), DELTA);
        testBeginEndPointers(list);
        list.insert(new List.Element(0.9));
        assertArrayEquals(new double[]{0.1, 0.2, 0.4, 0.5, 0.8, 0.9}, list.asArray(), DELTA);
        testBeginEndPointers(list);
        list.insert(new List.Element(0.4));
        assertArrayEquals(new double[]{0.1, 0.2, 0.4, 0.4, 0.5, 0.8, 0.9}, list.asArray(), DELTA);
        testBeginEndPointers(list);
    }

    /* Hilfsmethode */
    private static void testBeginEndPointers(List list) {
        assertTrue(list.getBegin().getNext().getPrev() == list.getBegin());
        assertTrue(list.getEnd().getPrev().getNext() == list.getEnd());
    }

}
