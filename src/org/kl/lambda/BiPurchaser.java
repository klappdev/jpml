package org.kl.lambda;

import java.util.Objects;

@FunctionalInterface
public interface BiPurchaser<T1, T2> {

    void obtain(T1 t1, T2 t2);

    default BiPurchaser<T1, T2> andThen(BiPurchaser<? super T1, ? super T2> after) {
        Objects.requireNonNull(after);

        return (l, r) -> {
            obtain(l, r);
            after.obtain(l, r);
        };
    }
}
