package com.awbp.data.fill;

import com.awbp.data.collections.MyArrayList;
import com.awbp.domain.model.Film;

import java.util.Scanner;
import java.util.stream.IntStream;

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
        MyArrayList<Film> films = new MyArrayList<>();
        IntStream.range(0, size).forEach(i -> {
            System.out.println("Фильм " + (i + 1) + " из " + size + ":");
            films.add(readFilm());
        });
        return films;
    }

    private Film readFilm() {
        while (true) {
            try {
                System.out.print("  Название: ");
                String title = scanner.nextLine();
                System.out.print("  Год выпуска: ");
                int year = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("  Рейтинг (0-10): ");
                double rating = Double.parseDouble(scanner.nextLine().trim());
                return Film.builder().title(title).year(year).rating(rating).build();
            } catch (NumberFormatException e) {
                System.out.println("  Ошибка: год и рейтинг должны быть числами. Повторите ввод.");
            } catch (IllegalArgumentException e) {
                System.out.println("  Ошибка валидации: " + e.getMessage() + ". Повторите ввод.");
            }
        }
    }
}
