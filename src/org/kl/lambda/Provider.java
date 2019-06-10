package org.kl.lambda;

@FunctionalInterface
public interface Provider<T> {
    T take();
}
