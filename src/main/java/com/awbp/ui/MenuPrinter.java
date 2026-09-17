package com.awbp.ui;

/**
 * Печать меню консольного приложения.
 */
public final class MenuPrinter {

    private MenuPrinter() {
    }

    public static void printMainMenu() {
        System.out.println();
        System.out.println("--- Главное меню ---");
        System.out.println("1. Заполнить коллекцию");
        System.out.println("2. Отсортировать коллекцию");
        System.out.println("3. Вывести коллекцию");
        System.out.println("4. Записать результат в файл");
        System.out.println("5. Подсчитать вхождения");
        System.out.println("0. Выход");
    }

    public static void printFillMenu() {
        System.out.println();
        System.out.println("--- Способ заполнения ---");
        System.out.println("1. Случайно");
        System.out.println("2. Вручную");
        System.out.println("3. Из файла");
        System.out.println("0. Отмена");
    }

    public static void printSortFieldMenu() {
        System.out.println();
        System.out.println("--- Поле сортировки ---");
        System.out.println("1. Название");
        System.out.println("2. Год");
        System.out.println("3. Рейтинг");
        System.out.println("0. Отмена");
    }

    public static void printSortAlgorithmMenu() {
        System.out.println();
        System.out.println("--- Алгоритм сортировки ---");
        System.out.println("1. Bubble sort");
        System.out.println("2. Quick sort");
        System.out.println("0. Отмена");
    }

    public static void printSortModeMenu() {
        System.out.println();
        System.out.println("--- Режим сортировки ---");
        System.out.println("1. Обычный");
        System.out.println("2. Чёт/нечет (по году)");
        System.out.println("0. Отмена");
    }
}
