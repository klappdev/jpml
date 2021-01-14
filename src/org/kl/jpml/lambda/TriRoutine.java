package org.kl.jpml.lambda;

import java.util.Objects;

public interface TriRoutine<T1, T2, T3, R> {

    R hold(T1 t1, T2 t2, T3 t3);

    default <V> TriRoutine<T1, T2, T3, V> andThen(Routine<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T1 t1, T2 t2, T3 t3) -> after.hold(hold(t1, t2, t3));
    }
}
