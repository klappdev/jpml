package org.kl.util;

import org.kl.meta.Extract;
import org.kl.type.IntRef;

import java.util.NoSuchElementException;
import java.util.Objects;

public final class Optional<T> {
    private static final Optional<?> EMPTY = new Optional<>();
    private final T value;

    private Optional() {
        this.value = null;
    }

    private Optional(T value) {
        this.value = Objects.requireNonNull(value);
    }

    @SuppressWarnings("unchecked")
    public static<T> Optional<T> empty() {
        Optional<T> other = (Optional<T>) EMPTY;
        return other;
    }

    public static <T> Optional<T> of(T value) {
        return new Optional<>(value);
    }

    @Extract
    public void of(IntRef data) {
        data.set((Integer) value);
    }

    public static <T> Optional<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    public T get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }
}
