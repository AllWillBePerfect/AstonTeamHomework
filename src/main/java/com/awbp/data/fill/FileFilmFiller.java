package com.awbp.data.fill;

import com.awbp.data.collections.MyArrayList;
import com.awbp.domain.model.Film;

/**
 * Заглушка: полная реализация — Data 2.4 ({@code FileFilmFiller}).
 */
public class FileFilmFiller implements FilmFiller {

    private final String path;

    public FileFilmFiller(String path) {
        if (path == null || path.isBlank()) {
            throw new IllegalArgumentException("path must not be blank");
        }
        this.path = path;
    }

    @Override
    public MyArrayList<Film> fill(int size) {
        System.out.println("FileFilmFiller ещё не реализован (задача Data 2.4). Путь: " + path);
        return new MyArrayList<>();
    }
}
