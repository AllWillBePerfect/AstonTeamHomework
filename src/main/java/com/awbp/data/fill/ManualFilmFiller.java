package com.awbp.data.fill;

import com.awbp.data.collections.MyArrayList;
import com.awbp.domain.model.Film;

import java.util.Scanner;

/**
 * Заглушка: полная реализация — Data 2.3 ({@code ManualFilmFiller}).
 */
public class ManualFilmFiller implements FilmFiller {

    private final Scanner scanner;

    public ManualFilmFiller(Scanner scanner) {
        if (scanner == null) {
            throw new IllegalArgumentException("scanner must not be null");
        }
        this.scanner = scanner;
    }

    @Override
    public MyArrayList<Film> fill(int size) {
        System.out.println("ManualFilmFiller ещё не реализован (задача Data 2.3).");
        return new MyArrayList<>();
    }
}
