package com.awbp.data.io;

import com.awbp.data.collections.MyArrayList;
import com.awbp.domain.model.Film;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Запись коллекции в файл в режиме добавления (append).
 */
public class ResultFileWriter {

    public void append(String path, MyArrayList<Film> films) throws IOException {
        if (path == null || path.isBlank()) {
            throw new IllegalArgumentException("path must not be blank");
        }
        if (films == null) {
            throw new IllegalArgumentException("films must not be null");
        }

        Path filePath = Path.of(path);
        if (filePath.getParent() != null) {
            Files.createDirectories(filePath.getParent());
        }

        try (BufferedWriter writer = Files.newBufferedWriter(
                filePath,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.APPEND
        )) {
            for (Film film : films) {
                writer.write(format(film));
                writer.newLine();
            }
        }
    }

    private String format(Film film) {
        return film.getTitle() + ";" + film.getYear() + ";" + film.getRating();
    }
}
