package com.awbp.ui;

import java.util.Scanner;

/**
 * Обёртка над {@link Scanner} с повторным запросом при некорректном вводе
 * (не число / вне диапазона / пустая строка).
 */
public class InputReader {

    private final Scanner scanner;

    public InputReader(Scanner scanner) {
        if (scanner == null) {
            throw new IllegalArgumentException("scanner must not be null");
        }
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Читает целое число в диапазоне [{@code min}; {@code max}].
     * При ошибке запрашивает ввод повторно.
     */
    public int readInt(String prompt, int min, int max) {
        validateRange(min, max);

        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();

            if (line.isEmpty()) {
                System.out.printf("Ошибка: введите целое число от %d до %d.%n", min, max);
                continue;
            }

            try {
                int value = Integer.parseInt(line);
                if (value < min || value > max) {
                    System.out.printf("Ошибка: значение должно быть в диапазоне [%d; %d].%n", min, max);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.printf("Ошибка: введите целое число от %d до %d.%n", min, max);
            }
        }
    }

    /**
     * Читает вещественное число в диапазоне [{@code min}; {@code max}].
     * При ошибке запрашивает ввод повторно.
     */
    public double readDouble(String prompt, double min, double max) {
        validateRange(min, max);

        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim().replace(',', '.');

            if (line.isEmpty()) {
                System.out.printf("Ошибка: введите число от %s до %s.%n", min, max);
                continue;
            }

            try {
                double value = Double.parseDouble(line);
                if (value < min || value > max) {
                    System.out.printf("Ошибка: значение должно быть в диапазоне [%s; %s].%n", min, max);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.printf("Ошибка: введите число от %s до %s.%n", min, max);
            }
        }
    }

    /**
     * Читает непустую строку. При пустом вводе запрашивает повторно.
     */
    public String readLine(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("Ошибка: введите непустую строку.");
        }
    }

    private static void validateRange(double min, double max) {
        if (min > max) {
            throw new IllegalArgumentException("min must be <= max, got min=" + min + ", max=" + max);
        }
    }
}
