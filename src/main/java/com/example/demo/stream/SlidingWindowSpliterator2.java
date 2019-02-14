package com.example.demo.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SlidingWindowSpliterator2 {

    public static <T> Stream<Window<T>> of(List<T> source, int windowSize) {

        return IntStream.range(0, source.size())
                .mapToObj(i -> {
                    int fromIndex = Math.max(i - windowSize + 1, 0);
                    int toIndex = Math.max(i, 0);
                    List<T> previous = (fromIndex <= toIndex) ? source.subList(fromIndex, toIndex) : new ArrayList<>();

                    return new Window<>(previous, source.get(i));
                });
    }

    public static class Window<T> {

        final List<T> previous;

        final T target;

        Window(List<T> previous, T target) {
            this.previous = previous;
            this.target = target;
        }
    }
}
