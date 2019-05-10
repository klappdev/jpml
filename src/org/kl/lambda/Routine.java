package org.kl.lambda;

import java.util.Objects;

@FunctionalInterface
public interface Routine<T, R> {

    R hold(T t);

    default <V> Routine<V, R> compose(Routine<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> hold(before.hold(v));
    }

    default <V> Routine<T, V> andThen(Routine<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.hold(hold(t));
    }

    static <T> Routine<T, T> identity() {
        return t -> t;
    }
}
