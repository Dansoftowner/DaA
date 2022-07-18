package datastructures;

import datastructures.linkedlist.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {

    private LinkedList<Integer> underTest;

    @BeforeEach
    void init() {
        underTest = new LinkedList<>();
    }

    // TODO: addFirst tests

    @Test
    void itShouldContainItems() {
        underTest.addLast(1);
        underTest.addLast(2);
        underTest.addLast(3);

        assertTrue(underTest.contains(1));
        assertTrue(underTest.contains(2));
        assertTrue(underTest.contains(3));
    }

    @Disabled
    @Test
    void itShouldInsertAtIndex() {
        IntStream.rangeClosed(0, 100).forEach(underTest::addLast);
        // underTest.insertAt(50, 777);

        assertEquals(50, underTest.indexOf(777));
        assertEquals(101, underTest.indexOf(100));
    }

    @Test
    void itShouldNotContainItems() {
        underTest.addLast(2);
        underTest.addLast(32);
        underTest.addLast(4);

        assertFalse(underTest.contains(1));
    }

    @Test
    void itShouldGetItems() {
        underTest.addLast(11);
        underTest.addLast(134);
        underTest.addLast(-11);

        assertEquals(11, underTest.get(0));
        assertEquals(134, underTest.get(1));
        assertEquals(-11, underTest.get(2));
    }

    @Test
    void itShouldFindIndex() {
        underTest.addLast(123);
        underTest.addLast(202);
        underTest.addLast(1);

        assertEquals(0, underTest.indexOf(123));
        assertEquals(1, underTest.indexOf(202));
        assertEquals(2, underTest.indexOf(1));
        assertEquals(-1, underTest.indexOf(Integer.MAX_VALUE));
    }

    @Test
    void itShouldRemoveItems() {
        underTest.addLast(1);
        underTest.addLast(3);
        underTest.addLast(12);

        underTest.deleteFirst();
        underTest.deleteLast();

        assertEquals(-1, underTest.indexOf(1));
        assertEquals(0, underTest.indexOf(3));
        assertEquals(-1, underTest.indexOf(12));
    }

    @Test
    void itShouldIterateItems() {
        IntStream.range(0, 100).forEach(underTest::addLast);

        List<Integer> copy = new ArrayList<>();
        for (Integer i : underTest)
            copy.add(i);

        assertIterableEquals(copy, underTest);
    }
}
