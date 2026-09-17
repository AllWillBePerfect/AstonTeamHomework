package com.awbp.domain.sort;

import com.awbp.data.collections.MyArrayList;

import java.util.Comparator;

/**
 * Быстрая сортировка.
 */
public class QuickSortStrategy<T> implements SortStrategy<T> {

    @Override
    public void sort(MyArrayList<T> list, Comparator<? super T> comparator) {
        if (list.size() < 2) {
            return;
        }
        quickSort(list, 0, list.size() - 1, comparator);
    }

    private void quickSort(MyArrayList<T> list, int left, int right, Comparator<? super T> comparator) {
        if (left >= right) {
            return;
        }
        int pivotIndex = partition(list, left, right, comparator);
        quickSort(list, left, pivotIndex - 1, comparator);
        quickSort(list, pivotIndex + 1, right, comparator);
    }

    private int partition(MyArrayList<T> list, int left, int right, Comparator<? super T> comparator) {
        T pivot = list.get(right);
        int i = left;
        for (int j = left; j < right; j++) {
            if (comparator.compare(list.get(j), pivot) <= 0) {
                swap(list, i, j);
                i++;
            }
        }
        swap(list, i, right);
        return i;
    }

    private void swap(MyArrayList<T> list, int i, int j) {
        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}
