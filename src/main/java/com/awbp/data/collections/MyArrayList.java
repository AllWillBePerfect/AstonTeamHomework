package com.awbp.data.collections;

import java.util.Iterator;

public class MyArrayList<T> implements Iterable<T> {

    private static final int DEFAULT_SIZE = 10;

    private Object[] elements;
    private int size;

    public MyArrayList() {
        elements = new Object[DEFAULT_SIZE];
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(size, elements);
    }

    public void add(T item) {
        increaseSize();
        elements[size++] = item;
    }

    public void set(int index, T item) {
        checkIndex(index);
        elements[index] = item;
    }

    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }

    private void increaseSize() {
        if (size == elements.length) {
            int newSize = elements.length + elements.length / 2 + 1;
            Object[] newElements = new Object[newSize];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }



}
