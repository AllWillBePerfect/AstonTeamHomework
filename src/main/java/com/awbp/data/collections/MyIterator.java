package com.awbp.data.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyIterator<T> implements Iterator<T> {

    private int size = 0;
    private Object[] elements;

    MyIterator(int size, Object[] elements) {
        this.size = size;
        this.elements = elements;
    }

    private int pointer = 0;

    @Override
    public boolean hasNext() {
        return pointer < size;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return (T) elements[pointer++];
    }
}
