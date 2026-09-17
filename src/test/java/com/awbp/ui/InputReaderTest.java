package com.awbp.ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputReaderTest {

    @Test
    void readInt_skipsInvalidAndOutOfRange_thenReturnsValid() {
        InputReader reader = readerOf("abc\n-1\n5\n");

        int value = reader.readInt("n: ", 0, 10);

        assertEquals(5, value);
    }

    @Test
    void readInt_skipsEmptyLine_thenReturnsValid() {
        InputReader reader = readerOf("\n  \n3\n");

        int value = reader.readInt("n: ", 1, 5);

        assertEquals(3, value);
    }

    @Test
    void readDouble_acceptsCommaAsDecimalSeparator() {
        InputReader reader = readerOf("не число\n11\n7,5\n");

        double value = reader.readDouble("r: ", 0.0, 10.0);

        assertEquals(7.5, value, 1e-9);
    }

    @Test
    void readLine_skipsEmpty_thenReturnsText() {
        InputReader reader = readerOf("\n\nfilms.txt\n");

        String value = reader.readLine("path: ");

        assertEquals("films.txt", value);
    }

    @Test
    void constructor_rejectsNullScanner() {
        assertThrows(IllegalArgumentException.class, () -> new InputReader(null));
    }

    @Test
    void readInt_rejectsInvalidRange() {
        InputReader reader = readerOf("1\n");

        assertThrows(IllegalArgumentException.class, () -> reader.readInt("n: ", 10, 1));
    }

    private static InputReader readerOf(String input) {
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        return new InputReader(scanner);
    }
}
