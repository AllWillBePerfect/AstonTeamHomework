package com.awbp.data.collections;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    @Test
    void givenNewList_whenElementsAdded_thenGetReturnsThemInOrder() {
        MyArrayList<String> list = new MyArrayList<>();

        list.add("a");
        list.add("b");
        list.add("c");

        assertEquals(3, list.size());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
    }

    @Test
    void givenEmptyList_whenElementAdded_thenIsEmptyBecomesFalse() {
        MyArrayList<Integer> list = new MyArrayList<>();

        assertTrue(list.isEmpty());

        list.add(1);
        assertFalse(list.isEmpty());
    }

    @Test
    void givenListWithElements_whenSetCalled_thenElementIsReplacedAndSizeUnchanged() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("a");
        list.add("b");

        list.set(1, "z");

        assertEquals("z", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    void givenListWithOneElement_whenGetCalledWithInvalidIndex_thenThrowsIndexOutOfBounds() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("a");

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    void givenEmptyList_whenSetCalledWithInvalidIndex_thenThrowsIndexOutOfBounds() {
        MyArrayList<String> list = new MyArrayList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, "x"));
    }

    @Test
    void givenListAtDefaultCapacity_whenMoreElementsAdded_thenCapacityGrowsAndDataIsPreserved() {
        MyArrayList<Integer> list = new MyArrayList<>();


        for (int i = 0; i < 50; i++) {
            list.add(i);
        }

        assertEquals(50, list.size());
        for (int i = 0; i < 50; i++) {
            assertEquals(i, list.get(i));
        }
    }

    @Test
    void givenListWithElements_whenIteratedFully_thenElementsReturnedInInsertionOrder() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("x");
        list.add("y");
        list.add("z");

        Iterator<String> it = list.iterator();
        StringBuilder result = new StringBuilder();
        while (it.hasNext()) {
            result.append(it.next());
        }

        assertEquals("xyz", result.toString());
    }

    @Test
    void givenIteratorExhausted_whenNextCalledAgain_thenThrowsNoSuchElementException() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("only");

        Iterator<String> it = list.iterator();
        it.next();

        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    void givenEmptyList_whenIteratorRequested_thenHasNextIsFalseAndNextThrows() {
        MyArrayList<String> list = new MyArrayList<>();

        Iterator<String> it = list.iterator();

        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, it::next);
    }
}