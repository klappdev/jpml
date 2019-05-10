package org.kl.lambda;

import java.util.Objects;

@FunctionalInterface
public interface Purchaser<T> {
    void obtain(T t);

    default Purchaser<T> andThen(Purchaser<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> { obtain(t); after.obtain(t); };
    }
}
