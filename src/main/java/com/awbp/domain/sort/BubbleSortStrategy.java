package com.awbp.domain.sort;

import com.awbp.data.collections.MyArrayList;

import java.util.Comparator;

/**
 * Пузырьковая сортировка.
 */
public class BubbleSortStrategy<T> implements SortStrategy<T> {

    @Override
    public void sort(MyArrayList<T> list, Comparator<? super T> comparator) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    swap(list, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    private void swap(MyArrayList<T> list, int i, int j) {
        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}
