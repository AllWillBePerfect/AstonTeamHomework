package com.awbp.domain.model;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FilmTest {

    @Test
    void builderCreatesFilmWithAllFields() {
        Film film = Film.builder()
                .title("The Matrix")
                .year(1999)
                .rating(8.7)
                .build();

        assertEquals("The Matrix", film.getTitle());
        assertEquals(1999, film.getYear());
        assertEquals(8.7, film.getRating());
    }

    @Test
    void filmFieldsArePrivateAndFinal() throws NoSuchFieldException {
        assertImmutableField(Film.class.getDeclaredField("title"));
        assertImmutableField(Film.class.getDeclaredField("year"));
        assertImmutableField(Film.class.getDeclaredField("rating"));
    }

    @Test
    void builderRejectsMissingRequiredFields() {
        assertThrows(IllegalArgumentException.class, () -> Film.builder().year(2000).rating(5).build());
        assertThrows(IllegalArgumentException.class, () -> Film.builder().title("Film").rating(5).build());
        assertThrows(IllegalArgumentException.class, () -> Film.builder().title("Film").year(2000).build());
    }

    @Test
    void builderRejectsInvalidRatingValues() {
        assertThrows(IllegalArgumentException.class, () -> validBuilder().rating(-0.1).build());
        assertThrows(IllegalArgumentException.class, () -> validBuilder().rating(10.1).build());
        assertThrows(IllegalArgumentException.class, () -> validBuilder().rating(Double.NaN).build());
        assertThrows(IllegalArgumentException.class, () -> validBuilder().rating(Double.POSITIVE_INFINITY).build());
    }

    @Test
    void constructorIsNotPublic() {
        assertFalse(java.util.Arrays.stream(Film.class.getDeclaredConstructors())
                .anyMatch(constructor -> Modifier.isPublic(constructor.getModifiers())));
    }

    @Test
    void equalFilmsHaveEqualHashCodes() {
        Film first = validBuilder().build();
        Film second = validBuilder().build();

        assertTrue(first.equals(second));
        assertEquals(first.hashCode(), second.hashCode());
    }

    private static Film.Builder validBuilder() {
        return Film.builder().title("Film").year(2000).rating(5.0);
    }

    private static void assertImmutableField(Field field) {
        assertTrue(Modifier.isPrivate(field.getModifiers()));
        assertTrue(Modifier.isFinal(field.getModifiers()));
    }
}
