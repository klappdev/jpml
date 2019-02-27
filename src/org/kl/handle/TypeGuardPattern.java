package org.kl.handle;

import org.kl.reflect.Reflection;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class TypeGuardPattern {

    public static <V, T> void matches(V value,
                                      Class<T> clazz, Predicate<T> predicate, Consumer<T> consumer) {
        Class<?> valueClass = value.getClass();

        if (clazz == valueClass || Reflection.isPrimitive(clazz, valueClass)) {
            if (predicate.test((T) value)) {
                consumer.accept((T) value);
            }
        }
    }
}
