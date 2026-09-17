package com.awbp.domain.sort;

import com.awbp.data.collections.MyArrayList;
import com.awbp.domain.model.Film;

import java.util.Comparator;

/**
 * Декоратор: фильмы с чётным годом сортируются между собой,
 * элементы с нечётным годом остаются на исходных позициях.
 */
public class EvenOddSortStrategy implements SortStrategy<Film> {

    private final SortStrategy<Film> delegate;

    public EvenOddSortStrategy(SortStrategy<Film> delegate) {
        if (delegate == null) {
            throw new IllegalArgumentException("delegate must not be null");
        }
        this.delegate = delegate;
    }

    @Override
    public void sort(MyArrayList<Film> list, Comparator<? super Film> comparator) {
        MyArrayList<Film> evenFilms = new MyArrayList<>();
        MyArrayList<Integer> evenIndexes = new MyArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            Film film = list.get(i);
            if (film.getYear() % 2 == 0) {
                evenFilms.add(film);
                evenIndexes.add(i);
            }
        }

        if (evenFilms.size() < 2) {
            return;
        }

        delegate.sort(evenFilms, comparator);

        for (int i = 0; i < evenIndexes.size(); i++) {
            list.set(evenIndexes.get(i), evenFilms.get(i));
        }
    }
}
