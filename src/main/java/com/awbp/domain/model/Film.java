package com.awbp.domain.model;

import java.time.Year;
import java.util.Objects;

/**
 * Модель фильма. Создание только через {@link Builder}.
 * Полная валидация — зона ответственности domain (задачи 1.1–1.2).
 */
public final class Film {

    private static final int MIN_YEAR = 1895;
    private static final int MAX_TITLE_LENGTH = 200;

    private final String title;
    private final int year;
    private final double rating;

    private Film(Builder builder) {
        this.title = builder.title;
        this.year = builder.year;
        this.rating = builder.rating;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "Film{title='" + title + "', year=" + year + ", rating=" + rating + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Film film = (Film) o;
        return year == film.year
                && Double.compare(film.rating, rating) == 0
                && Objects.equals(title, film.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year, rating);
    }

    public static final class Builder {

        private String title;
        private Integer year;
        private Double rating;

        private Builder() {
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder rating(double rating) {
            this.rating = rating;
            return this;
        }

        public Film build() {
            validate();
            return new Film(this);
        }

        private void validate() {
            if (title == null || title.isBlank()) {
                throw new IllegalArgumentException("Название не должно быть пустым");
            }
            if (title.length() > MAX_TITLE_LENGTH) {
                throw new IllegalArgumentException("Название слишком длинное (макс. " + MAX_TITLE_LENGTH + ")");
            }
            if (year == null) {
                throw new IllegalArgumentException("Год обязателен");
            }
            int maxYear = Year.now().getValue() + 1;
            if (year < MIN_YEAR || year > maxYear) {
                throw new IllegalArgumentException("Год должен быть в диапазоне [" + MIN_YEAR + "; " + maxYear + "]");
            }
            if (rating == null) {
                throw new IllegalArgumentException("Рейтинг обязателен");
            }
            if (rating < 0.0 || rating > 10.0) {
                throw new IllegalArgumentException("Рейтинг должен быть в диапазоне [0.0; 10.0]");
            }
        }
    }
}
