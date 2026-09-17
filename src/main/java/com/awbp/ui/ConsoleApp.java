package com.awbp.ui;

import com.awbp.data.collections.MyArrayList;
import com.awbp.data.fill.FileFilmFiller;
import com.awbp.data.fill.FilmFiller;
import com.awbp.data.fill.ManualFilmFiller;
import com.awbp.data.fill.RandomFilmFiller;
import com.awbp.domain.compare.FilmComparators;
import com.awbp.domain.model.Film;
import com.awbp.domain.model.FilmField;
import com.awbp.domain.sort.BubbleSortStrategy;
import com.awbp.domain.sort.EvenOddSortStrategy;
import com.awbp.domain.sort.QuickSortStrategy;
import com.awbp.domain.sort.SortChooser;
import com.awbp.domain.sort.SortStrategy;

import java.util.Comparator;
import java.util.Scanner;

/**
 * Консольное приложение: главный цикл меню.
 */
public class ConsoleApp {

    private static final int MIN_COLLECTION_SIZE = 1;
    private static final int MAX_COLLECTION_SIZE = 10_000;

    private final InputReader inputReader;
    private MyArrayList<Film> films = new MyArrayList<>();

    public ConsoleApp(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    public ConsoleApp() {
        this(new InputReader(new Scanner(System.in)));
    }

    /**
     * Бесконечный цикл с меню. Выход только по выбору пункта 0.
     */
    public void run() {
        System.out.println("=== Сортировка коллекции Film ===");

        while (true) {
            MenuPrinter.printMainMenu();
            int choice = inputReader.readInt("Выберите пункт меню: ", 0, 5);

            switch (choice) {
                case 1 -> fillCollection();
                case 2 -> sortCollection();
                case 3 -> printCollection();
                case 4 -> writeToFile();
                case 5 -> countOccurrences();
                case 0 -> {
                    System.out.println("Выход из программы.");
                    return;
                }
                default -> System.out.println("Неизвестный пункт меню.");
            }
        }
    }

    private void fillCollection() {
        MenuPrinter.printFillMenu();
        int mode = inputReader.readInt("Выберите способ заполнения: ", 0, 3);
        if (mode == 0) {
            System.out.println("Заполнение отменено.");
            return;
        }

        int size = inputReader.readInt(
                "Введите длину коллекции: ",
                MIN_COLLECTION_SIZE,
                MAX_COLLECTION_SIZE
        );

        FilmFiller filler = switch (mode) {
            case 1 -> new RandomFilmFiller();
            case 2 -> new ManualFilmFiller(inputReader.getScanner());
            case 3 -> {
                String path = inputReader.readLine("Введите путь к файлу: ");
                yield new FileFilmFiller(path);
            }
            default -> throw new IllegalStateException("Неизвестный способ заполнения: " + mode);
        };

        try {
            films = filler.fill(size);
            System.out.printf("Коллекция заполнена. Элементов: %d%n", films.size());
        } catch (RuntimeException e) {
            System.out.println("Ошибка заполнения: " + e.getMessage());
        }
    }

    private void sortCollection() {
        if (films.isEmpty()) {
            System.out.println("Коллекция пуста. Сначала заполните её.");
            return;
        }

        MenuPrinter.printSortFieldMenu();
        int fieldChoice = inputReader.readInt("Выберите поле: ", 0, 3);
        if (fieldChoice == 0) {
            System.out.println("Сортировка отменена.");
            return;
        }

        MenuPrinter.printSortAlgorithmMenu();
        int algorithmChoice = inputReader.readInt("Выберите алгоритм: ", 0, 2);
        if (algorithmChoice == 0) {
            System.out.println("Сортировка отменена.");
            return;
        }

        MenuPrinter.printSortModeMenu();
        int modeChoice = inputReader.readInt("Выберите режим: ", 0, 2);
        if (modeChoice == 0) {
            System.out.println("Сортировка отменена.");
            return;
        }

        FilmField field = switch (fieldChoice) {
            case 1 -> FilmField.TITLE;
            case 2 -> FilmField.YEAR;
            case 3 -> FilmField.RATING;
            default -> throw new IllegalStateException("Неизвестное поле: " + fieldChoice);
        };

        SortStrategy<Film> strategy = switch (algorithmChoice) {
            case 1 -> new BubbleSortStrategy<>();
            case 2 -> new QuickSortStrategy<>();
            default -> throw new IllegalStateException("Неизвестный алгоритм: " + algorithmChoice);
        };

        if (modeChoice == 2) {
            strategy = new EvenOddSortStrategy(strategy);
        }

        Comparator<Film> comparator = FilmComparators.byField(field);
        SortChooser<Film> chooser = new SortChooser<>(strategy);
        chooser.sort(films, comparator);

        System.out.printf(
                "Коллекция отсортирована (поле=%s, алгоритм=%s, режим=%s).%n",
                field,
                algorithmChoice == 1 ? "bubble" : "quick",
                modeChoice == 1 ? "обычный" : "чёт/нечет"
        );
    }

    private void printCollection() {
        // TODO: пункт 3.4
    }

    private void writeToFile() {
        // TODO: пункт 3.4
    }

    private void countOccurrences() {
        // TODO: пункт 3.4
    }
}
