package com.awbp.data.fill;

import com.awbp.data.collections.MyArrayList;
import com.awbp.domain.model.Film;

import java.time.Year;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Заглушка: полная реализация — Data 2.2 ({@code RandomFilmFiller}).
 */
public class RandomFilmFiller implements FilmFiller {

    private final Random random = new Random();

    @Override
    public MyArrayList<Film> fill(int size) {
        MyArrayList<Film> films = new MyArrayList<>();
        IntStream.range(0, size)
                .mapToObj(i -> randomFilm())
                .forEach(films::add);
        return films;
    }

    private Film randomFilm() {
        String title = WORDS_FIRST[random.nextInt(WORDS_FIRST.length)]
                + " " + WORDS_SECOND[random.nextInt(WORDS_SECOND.length)];
        int maxYear = Year.now().getValue();
        int year = MIN_YEAR + random.nextInt(maxYear - MIN_YEAR + 1);
        double rating = Math.round(random.nextDouble() * 100) / 10.0;
        return Film.builder()
                .title(title)
                .year(year)
                .rating(rating)
                .build();
    }

    private static final String[] WORDS_FIRST = {
            "Тень", "Полёт", "Легенда", "Возвращение", "Граница",
            "Голос", "Огонь", "Территория", "Свет", "Иллюзия"
    };
    private static final String[] WORDS_SECOND = {
            "Севера", "Времени", "Надежды", "Забвения", "Прошлого",
            "Рассвета", "Бури", "Тишины", "Судьбы", "Осени"
    };
    private static final int MIN_YEAR = 1950;
}
