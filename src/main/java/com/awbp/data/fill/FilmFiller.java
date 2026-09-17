package com.awbp.data.fill;

import com.awbp.data.collections.MyArrayList;
import com.awbp.domain.model.Film;

/**
 * Стратегия заполнения коллекции фильмов.
 */
public interface FilmFiller {

    /**
     * Заполняет коллекцию указанной длины.
     *
     * @param size желаемое количество элементов (для файла — верхняя граница)
     * @return заполненная коллекция
     */
    MyArrayList<Film> fill(int size);
}
