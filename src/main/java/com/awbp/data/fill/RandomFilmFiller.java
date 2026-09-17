package com.awbp.data.fill;

import com.awbp.data.collections.MyArrayList;
import com.awbp.domain.model.Film;

/**
 * Заглушка: полная реализация — Data 2.2 ({@code RandomFilmFiller}).
 */
public class RandomFilmFiller implements FilmFiller {

    @Override
    public MyArrayList<Film> fill(int size) {
        System.out.println("RandomFilmFiller ещё не реализован (задача Data 2.2).");
        return new MyArrayList<>();
    }
}
