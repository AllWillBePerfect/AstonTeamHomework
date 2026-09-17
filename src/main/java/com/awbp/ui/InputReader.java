package com.awbp.ui;

import java.util.Scanner;

/**
 * Обёртка над {@link Scanner} с повторным запросом при некорректном вводе.
 */
public class InputReader {

    private final Scanner scanner;

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
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
}
