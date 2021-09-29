package datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicArrayTest {

    private DynamicArray<Integer> underTest;

    @BeforeEach
    void init() {
        underTest = new DynamicArray<>();
    }

    @Test
    void itShouldContainItems() {
        underTest.insert(1);
        underTest.insert(2);
        underTest.insert(3);

        assertTrue(underTest.contains(1));
        assertTrue(underTest.contains(2));
        assertTrue(underTest.contains(3));
    }

    @Test
    void itShouldNotContainItems() {
        underTest.insert(2);
        underTest.insert(32);
        underTest.insert(4);

        assertFalse(underTest.contains(1));
    }

    @Test
    void itShouldGetItems() {
        underTest.insert(11);
        underTest.insert(134);
        underTest.insert(-11);

        assertEquals(11, underTest.get(0));
        assertEquals(134, underTest.get(1));
        assertEquals(-11, underTest.get(2));
    }

    @Test
    void itShouldFindIndex() {
        underTest.insert(123);
        underTest.insert(202);
        underTest.insert(1);

        assertEquals(0, underTest.indexOf(123));
        assertEquals(1, underTest.indexOf(202));
        assertEquals(2, underTest.indexOf(1));
    }

    @Test
    void itShouldRemoveItems() {
        underTest.insert(1);
        underTest.insert(3);
        underTest.insert(12);

        underTest.removeAt(0);

        assertEquals(-1, underTest.indexOf(1));
        assertEquals(0, underTest.indexOf(3));
        assertEquals(1, underTest.indexOf(12));
    }

    @Test
    void itShouldIterateItems() {
        List<Integer> copy = new ArrayList<>();
        for (Integer i : underTest)
            copy.add(i);

        assertIterableEquals(copy, underTest);
    }
}
