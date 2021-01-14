package org.kl.jpml.util;

import java.util.Objects;
import java.util.function.Supplier;

public final class Result<T, E extends Throwable> {
    private final T value;
    private final E error;

    private Result() {
        this.value = null;
        this.error = null;
    }

    private Result(T value) {
        this.value = Objects.requireNonNull(value);
        this.error = null;
    }

    private Result(E error) {
        this.error = Objects.requireNonNull(error);
        this.value = null;
    }

    public static <T, E extends Throwable> Result<T, E> of(T value) {
        return new Result<>(value);
    }

    public static <T, E extends Throwable> Result<T, E> of(E error) {
        return new Result<>(error);
    }

    public static <T, E extends Throwable> Result<T, E> of(Supplier<T> supplier) {
        try {
            return new Result<>(supplier.get());
        } catch (Throwable e) {
            return new Result<>((E) e);
        }
    }

    public boolean isValue() {
        return value != null;
    }

    public boolean isError() {
        return error != null;
    }

    public T value() {
        return value;
    }

    public E error() {
        return error;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Result<?, ?> result = (Result<?, ?>) other;

        return Objects.equals(value, result.value) &&
               Objects.equals(error, result.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, error);
    }

    @Override
    public String toString() {
        return value != null
                ? "Value: " + value
                : "Error: " + error;
    }
}
