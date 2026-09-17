package com.awbp.domain.sort;

import com.awbp.data.collections.MyArrayList;

import java.util.Comparator;

/**
 * Стратегия сортировки коллекции.
 */
public interface SortStrategy<T> {

    void sort(MyArrayList<T> list, Comparator<? super T> comparator);
}
