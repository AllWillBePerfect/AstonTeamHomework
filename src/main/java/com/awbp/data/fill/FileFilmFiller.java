package com.awbp.data.fill;

import com.awbp.data.collections.MyArrayList;
import com.awbp.domain.model.Film;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

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
        MyArrayList<Film> films = new MyArrayList<>();
        try (var lines = Files.lines(Path.of(path))) {
            lines.map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .forEach(line -> parseLine(line).ifPresent(films::add));
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать файл: " + path, e);
        }
        return films;
    }

    private Optional<Film> parseLine(String line) {
        String[] parts = line.split(";");
        if (parts.length != 3) {
            System.out.println("Пропущена строка (ожидается 3 поля через ';'): " + line);
            return Optional.empty();
        }
        try {
            String title = parts[0].trim();
            int year = Integer.parseInt(parts[1].trim());
            double rating = Double.parseDouble(parts[2].trim());
            return Optional.of(Film.builder().title(title).year(year).rating(rating).build());
        } catch (NumberFormatException e) {
            System.out.println("Пропущена строка (некорректное число): " + line);
            return Optional.empty();
        } catch (IllegalArgumentException e) {
            System.out.println("Пропущена строка (" + e.getMessage() + "): " + line);
            return Optional.empty();
        }
    }
}
