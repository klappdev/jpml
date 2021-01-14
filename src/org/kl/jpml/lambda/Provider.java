package org.kl.jpml.lambda;

@FunctionalInterface
public interface Provider<T> {
    T take();
}
