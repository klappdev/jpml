package org.kl.lambda;

import java.util.Objects;

@FunctionalInterface
public interface BiRoutine<T1, T2, R> {

    R hold(T1 t1, T2 t2);

    default <V> BiRoutine<T1, T2, V> andThen(Routine<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T1 t1, T2 t2) -> after.hold(hold(t1, t2));
    }
}
