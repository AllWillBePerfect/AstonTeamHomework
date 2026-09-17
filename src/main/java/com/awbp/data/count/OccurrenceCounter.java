package com.awbp.data.count;

import com.awbp.data.collections.MyArrayList;
import com.awbp.domain.model.Film;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Многопоточный подсчёт фильмов заданного года.
 */
public class OccurrenceCounter {

    /**
     * Считает количество фильмов с указанным годом, разбивая коллекцию на диапазоны
     * и обрабатывая их в нескольких потоках.
     *
     * @param films       коллекция фильмов
     * @param year        искомый год
     * @param threadCount число потоков
     * @return суммарное количество вхождений
     */
    public int countByYear(MyArrayList<Film> films, int year, int threadCount) {
        if (films == null) {
            throw new IllegalArgumentException("films must not be null");
        }
        if (threadCount < 1) {
            throw new IllegalArgumentException("threadCount must be >= 1");
        }
        if (films.isEmpty()) {
            System.out.printf("Вхождений фильмов года %d: 0%n", year);
            return 0;
        }

        int size = films.size();
        int workers = Math.min(threadCount, size);
        int chunkSize = (size + workers - 1) / workers;

        AtomicInteger total = new AtomicInteger();
        List<Thread> threads = new ArrayList<>(workers);

        for (int w = 0; w < workers; w++) {
            int from = w * chunkSize;
            int to = Math.min(from + chunkSize, size);
            if (from >= to) {
                break;
            }

            Thread thread = new Thread(() -> {
                int local = 0;
                for (int i = from; i < to; i++) {
                    if (films.get(i).getYear() == year) {
                        local++;
                    }
                }
                total.addAndGet(local);
            }, "occurrence-counter-" + w);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Подсчёт прерван", e);
            }
        }

        int result = total.get();
        System.out.printf(
                "Вхождений фильмов года %d: %d (потоков: %d)%n",
                year,
                result,
                threads.size()
        );
        return result;
    }
}
