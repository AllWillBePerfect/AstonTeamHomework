package com.awbp.domain.sort;

import com.awbp.data.collections.MyArrayList;

import java.util.Comparator;

/**
 * Контекст паттерна Strategy: переключение и запуск сортировки.
 */
public class SortChooser<T> {

    private SortStrategy<T> strategy;

    public SortChooser(SortStrategy<T> strategy) {
        setStrategy(strategy);
    }

    public void setStrategy(SortStrategy<T> strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("strategy must not be null");
        }
        this.strategy = strategy;
    }

    public SortStrategy<T> getStrategy() {
        return strategy;
    }

    public void sort(MyArrayList<T> list, Comparator<? super T> comparator) {
        if (list == null) {
            throw new IllegalArgumentException("list must not be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("comparator must not be null");
        }
        strategy.sort(list, comparator);
    }
}
