package com.awbp.model;

import java.time.Year;
import java.util.Objects;

public final class Book {

    private final String title;
    private final String author;
    private final int year;

    private Book(Builder builder) {
        this.title = builder.title;
        this.author = builder.author;
        this.year = builder.year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Book{title='" + title + "', author='" + author + "', year=" + year + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String title;
        private String author;
        private int year;

        private Builder() {
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Book build() {
            if (title == null || title.isBlank()) {
                throw new BookValidationException("Title must not be empty");
            }

            if (author == null || author.isBlank()) {
                throw new BookValidationException("Author must not be empty");
            }

            int currentYear = Year.now().getValue();

            if (year < 1700 || year > currentYear) {
                throw new BookValidationException(
                        "Year must be between 1700 and " + currentYear
                );
            }

            return new Book(this);
        }
    }
}
