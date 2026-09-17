package com.awbp.domain.compare;

import com.awbp.domain.model.Film;
import com.awbp.domain.model.FilmField;

import java.util.Comparator;

/**
 * Компараторы фильмов по всем трём полям (сравнение вручную).
 */
public final class FilmComparators {

    private FilmComparators() {
    }

    public static Comparator<Film> byTitle() {
        return (left, right) -> left.getTitle().compareToIgnoreCase(right.getTitle());
    }

    public static Comparator<Film> byYear() {
        return (left, right) -> Integer.compare(left.getYear(), right.getYear());
    }

    public static Comparator<Film> byRating() {
        return (left, right) -> Double.compare(left.getRating(), right.getRating());
    }

    public static Comparator<Film> byField(FilmField field) {
        return switch (field) {
            case TITLE -> byTitle();
            case YEAR -> byYear();
            case RATING -> byRating();
        };
    }
}
