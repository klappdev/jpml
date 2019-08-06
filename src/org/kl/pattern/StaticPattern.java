/*
 * JPML - Java pattern matching library.
 *
 * Static pattern allow match type and deconstruct object
 * using factory methods. Maximum number of branches for
 * match three with three value params.
 */
package org.kl.pattern;

import org.kl.lambda.TriConsumer;
import org.kl.lambda.TriFunction;
import org.kl.reflect.Reflection;
import org.kl.util.Expected;

import java.util.Optional;
import java.util.function.*;

public final class StaticPattern {
    private static Object data;
    private static Optional optionalData;
    private static Expected expectedData;

    private static StaticPattern instance;

    private StaticPattern() {}

    public static <V> StaticPattern matches(V value) {
        data = value;

        if (instance == null) {
            instance = new StaticPattern();
        }

        return instance;
    }

    public static <V> StaticPattern matches(Optional<V> value) {
        optionalData = value;

        if (instance == null) {
            instance = new StaticPattern();
        }

        return instance;
    }

    public static <V, E extends Throwable> StaticPattern matches(Expected<V, E> value) {
        expectedData = value;

        if (instance == null) {
            instance = new StaticPattern();
        }

        return instance;
    }

    public static String of(String name) {
        return name;
    }

    public static <V, C, T>
    void matches(V data, Class<C> clazz, String name, Consumer<T> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] args = Reflection.invokeExtractor(data, name, 1);

            firstBranch.accept((T) args[0]);
        }
    }

    public static <V, C, T1, T2>
    void matches(V data, Class<C> clazz, Consumer<T1> consumer, Consumer<T2> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(consumer, 1);

            firstBranch.accept((T2) args[0]);
        }
    }

    public <C, T>
    void as(Class<C> clazz, String name, Consumer<T> firstBranch) {
        matches(data, clazz, name, firstBranch);
    }

    public <C, T1, T2>
    void as(Class<C> clazz, Consumer<T1> consumer, Consumer<T2> firstBranch) {
        matches(data, clazz, consumer, firstBranch);
    }

    public static <V, C, T, R>
    R matches(V data, Class<C> clazz, String name, Function<T, R> firstBranch)  {
        if (clazz == data.getClass()) {
            Object[] args = Reflection.invokeExtractor(data, name, 1);

            return firstBranch.apply((T) args[0]);
        }

        return null;
    }

    public static <V, C, T1, T2, R>
    R matches(V data, Class<C> clazz, Consumer<T1> consumer, Function<T2, R> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(consumer, 1);

            return firstBranch.apply((T2) args[0]);
        }

        return null;
    }

    public static <V, C, T1, T2>
    void matches(V data, Class<C> clazz, String name, BiConsumer<T1, T2> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] args = Reflection.invokeExtractor(data, name, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
        }
    }

    public static <V, C, T1, T2, T3, T4>
    void matches(V data, Class<C> clazz, BiConsumer<T1, T2> consumer, BiConsumer<T3, T4> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(consumer, 2);

            firstBranch.accept((T3) args[0], (T4) args[1]);
        }
    }

    public <C, T1, T2>
    void as(Class<C> clazz, String name, BiConsumer<T1, T2> firstBranch) {
        matches(data, clazz, name, firstBranch);
    }

    public <C, T1, T2, T3, T4>
    void as(Class<C> clazz, BiConsumer<T1, T2> consumer, BiConsumer<T3, T4> firstBranch) {
        matches(data, clazz, consumer, firstBranch);
    }

    public static <V, C, T1, T2, R>
    R matches(V data, Class<C> clazz, String name, BiFunction<T1, T2, R> firstBranch)  {
        if (clazz == data.getClass()) {
            Object[] args = Reflection.invokeExtractor(data, name, 2);

            return firstBranch.apply((T1) args[0], (T2) args[1]);
        }

        return null;
    }

    public static <V, C, T1, T2, T3, T4, R>
    R matches(V data, Class<C> clazz, BiConsumer<T1, T2> consumer, BiFunction<T3, T4, R> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(consumer, 2);

            return firstBranch.apply((T3) args[0], (T4) args[1]);
        }

        return null;
    }

    public static <V, C, T1, T2, T3>
    void matches(V data, Class<C> clazz, String name, TriConsumer<T1, T2, T3> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] args = Reflection.invokeExtractor(data, name, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        }
    }

    public static <V, C, T1, T2, T3, T4, T5, T6>
    void matches(V data, Class<C> clazz, TriConsumer<T1, T2, T3> consumer, TriConsumer<T4, T5, T6> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(consumer, 3);

            firstBranch.accept((T4) args[0], (T5) args[1], (T6) args[2]);
        }
    }

    public <C, T1, T2, T3>
    void as(Class<C> clazz, String name, TriConsumer<T1, T2, T3> firstBranch) {
        matches(data, clazz, name, firstBranch);
    }

    public <C, T1, T2, T3, T4, T5, T6>
    void as(Class<C> clazz, TriConsumer<T1, T2, T3> consumer, TriConsumer<T4, T5, T6> firstBranch) {
        matches(data, clazz, consumer, firstBranch);
    }

    public static <V, C, T1, T2, T3, R>
    R matches(V data, Class<C> clazz, String name, TriFunction<T1, T2, T3, R> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] args = Reflection.invokeExtractor(data, name, 3);

            return firstBranch.apply((T1) args[0], (T2) args[1], (T3) args[2]);
        }

        return null;
    }

    public static <V, C, T1, T2, T3, T4, T5, T6, R>
    R matches(V data, Class<C> clazz, TriConsumer<T1, T2, T3> consumer, TriFunction<T4, T5, T6, R> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(consumer, 3);

            return firstBranch.apply((T4) args[0], (T5) args[1], (T6) args[2]);
        }

        return null;
    }

    public static <V, C1, C2, T1, T2>
    void matches(V value,
                 Class<C1> firstClazz,  String firstName,  Consumer<T1> firstBranch,
                 Class<C2> secondClazz, String secondName, Consumer<T2> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 1);

            firstBranch.accept((T1) args[0]);
        }

        if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 1);

            secondBranch.accept((T2) args[0]);
        }
    }

    public static <V, T>
    void matches(Optional<V> value,
                 Supplier<Optional<V>> supplier, Runnable firstBranch,
                 Function<Optional<V>, T> function, Consumer<T> secondBranch) {
        Optional<V> tmp = supplier.get();

        if (tmp.equals(value)) {
            firstBranch.run();
        } else {
            T arg = function.apply(value);
            secondBranch.accept(arg);
        }
    }

    public static <T, E extends Throwable>
    void matches(Expected<T, E> value,
                 Function<Expected<T, E>, E> firstFunction,  Consumer<E> firstBranch,
                 Function<Expected<T, E>, T> secondFunction, Consumer<T> secondBranch) {
        if (value.isError()) {
            E arg = firstFunction.apply(value);
            firstBranch.accept(arg);
        } else {
            T arg = secondFunction.apply(value);
            secondBranch.accept(arg);
        }
    }

    public <C1, C2, T1, T2>
    void as(Class<C1> firstClazz,  String firstName,  Consumer<T1> firstBranch,
            Class<C2> secondClazz, String secondName, Consumer<T2> secondBranch)  {
        matches(data,
                firstClazz, firstName, firstBranch,
                secondClazz,secondName,secondBranch);
    }

    @SuppressWarnings("unchecked")
    public <V, T> void as(Supplier<Optional<V>> supplier, Runnable firstBranch,
                         Function<Optional<V>, T> function, Consumer<T> secondBranch) {
        matches(optionalData,
                supplier, firstBranch,
                function, secondBranch);
    }

    public <T, E extends Throwable>
    void as(Function<Expected<T, E>, E> firstFunction,  Consumer<E> firstBranch,
            Function<Expected<T, E>, T> secondFunction, Consumer<T> secondBranch) {
        matches(expectedData,
                firstFunction, firstBranch,
                secondFunction,secondBranch);
    }

    public static <V, C1, C2, T1, T2, R>
    R matches(V value,
              Class<C1> firstClazz,  String firstName,  Function<T1, R> firstBranch,
              Class<C2> secondClazz, String secondName, Function<T2, R> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 1);

            return firstBranch.apply((T1) args[0]);
        }

        if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 1);

            return secondBranch.apply((T2) args[0]);
        }

        return null;
    }

    public static <V, T, R>
    R matches(Optional<V> value,
              Supplier<Optional<V>> supplier,    Supplier<R> firstBranch,
              Function<Optional<V>, T> function, Function<T, R> secondBranch) {
        Optional<V> tmp = supplier.get();

        if (tmp.equals(value)) {
            return firstBranch.get();
        } else {
            T arg = function.apply(value);
            return secondBranch.apply(arg);
        }
    }

    public static <T, E extends Throwable, R>
    R matches(Expected<T, E> value,
              Function<Expected<T, E>, E> firstFunction,  Function<E, R> firstBranch,
              Function<Expected<T, E>, T> secondFunction, Function<T, R> secondBranch) {
        if (value.isError()) {
            E arg = firstFunction.apply(value);
            return firstBranch.apply(arg);
        } else {
            T arg = secondFunction.apply(value);
            return secondBranch.apply(arg);
        }
    }

    public static <V, C1, C2, T1, T2, T3>
    void matches(V value,
                 Class<C1> firstClazz,  String firstName,  Consumer<T1> firstBranch,
                 Class<C2> secondClazz, String secondName, BiConsumer<T2, T3> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 1);

            firstBranch.accept((T1) args[0]);
        }

        if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 2);

            secondBranch.accept((T2) args[0], (T3) args[1]);
        }
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6>
    void matches(V data,
                 Class<C1> firstClazz,  Consumer<T1> firstConsumer, Consumer<T2> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T3, T4> secondConsumer, BiConsumer<T5, T6> secondBranch) {
        if (firstClazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(firstConsumer, 1);

            firstBranch.accept((T2) args[0]);
        }

        if (secondClazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(secondConsumer, 2);

            secondBranch.accept((T5) args[0], (T6) args[1]);
        }
    }

    public <C1, C2, T1, T2, T3>
    void as(Class<C1> firstClazz,  String firstName,  Consumer<T1> firstBranch,
            Class<C2> secondClazz, String secondName, BiConsumer<T2, T3> secondBranch)  {
        matches(data,
                firstClazz, firstName, firstBranch,
                secondClazz,secondName,secondBranch);
    }

    public <C1, C2, T1, T2, T3, T4, T5, T6>
    void as(Class<C1> firstClazz,  Consumer<T1> firstConsumer, Consumer<T2> firstBranch,
            Class<C2> secondClazz, BiConsumer<T3, T4> secondConsumer, BiConsumer<T5, T6> secondBranch) {
        matches(data,
                firstClazz, firstConsumer, firstBranch,
                secondClazz,secondConsumer,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3>
    void matches(V value,
                 Class<C1> firstClazz,  String firstName,  BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, String secondName, Consumer<T3> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
        }

        if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 1);

            secondBranch.accept((T3) args[0]);
        }
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6>
    void matches(V data,
                 Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer, BiConsumer<T3, T4> firstBranch,
                 Class<C2> secondClazz, Consumer<T5> secondConsumer, Consumer<T6> secondBranch) {
        if (firstClazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(firstConsumer, 2);

            firstBranch.accept((T3) args[0], (T4) args[1]);
        }

        if (secondClazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(secondConsumer, 1);

            secondBranch.accept((T6) args[0]);
        }
    }

    public <C1, C2, T1, T2, T3>
    void as(Class<C1> firstClazz,  String firstName,  BiConsumer<T1, T2> firstBranch,
            Class<C2> secondClazz, String secondName, Consumer<T3> secondBranch)  {
        matches(data,
                firstClazz, firstName, firstBranch,
                secondClazz,secondName,secondBranch);
    }

    public <C1, C2, T1, T2, T3, T4, T5, T6>
    void as(Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer, BiConsumer<T3, T4> firstBranch,
            Class<C2> secondClazz, Consumer<T5> secondConsumer, Consumer<T6> secondBranch) {
        matches(data,
                firstClazz, firstConsumer, firstBranch,
                secondClazz,secondConsumer,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz,  String firstName,  BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, String secondName, BiConsumer<T3, T4> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
        }

        if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 2);

            secondBranch.accept((T3) args[0], (T4) args[1]);
        }
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6, T7, T8>
    void matches(V data,
                 Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,  BiConsumer<T3, T4> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T5, T6> secondConsumer, BiConsumer<T7, T8> secondBranch) {
        if (firstClazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(firstConsumer, 2);

            firstBranch.accept((T3) args[0], (T4) args[1]);
        }

        if (secondClazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(secondConsumer, 2);

            secondBranch.accept((T7) args[0], (T8) args[1]);
        }
    }

    public <C1, C2, T1, T2, T3, T4>
    void as(Class<C1> firstClazz,  String firstName,  BiConsumer<T1, T2> firstBranch,
            Class<C2> secondClazz, String secondName, BiConsumer<T3, T4> secondBranch)  {
        matches(data,
                firstClazz, firstName, firstBranch,
                secondClazz,secondName,secondBranch);
    }

    public <C1, C2, T1, T2, T3, T4, T5, T6, T7, T8>
    void as(Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,  BiConsumer<T3, T4> firstBranch,
            Class<C2> secondClazz, BiConsumer<T5, T6> secondConsumer, BiConsumer<T7, T8> secondBranch) {
        matches(data,
                firstClazz, firstConsumer, firstBranch,
                secondClazz,secondConsumer,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz,  String firstName,  Consumer<T1> firstBranch,
                 Class<C2> secondClazz, String secondName, TriConsumer<T2, T3, T4> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 1);

            firstBranch.accept((T1) args[0]);
        }

        if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 3);

            secondBranch.accept((T2) args[0], (T3) args[1], (T4) args[2]);
        }
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6, T7, T8>
    void matches(V data,
                 Class<C1> firstClazz,  Consumer<T1> firstConsumer, Consumer<T2> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T3, T4, T5> secondConsumer, TriConsumer<T6, T7, T8> secondBranch) {
        if (firstClazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(firstConsumer, 1);

            firstBranch.accept((T2) args[0]);
        }

        if (secondClazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(secondConsumer, 3);

            secondBranch.accept((T6) args[0], (T7) args[1], (T8) args[2]);
        }
    }

    public <C1, C2, T1, T2, T3, T4>
    void as(Class<C1> firstClazz,  String firstName,  Consumer<T1> firstBranch,
            Class<C2> secondClazz, String secondName, TriConsumer<T2, T3, T4> secondBranch)  {
        matches(data,
                firstClazz, firstName, firstBranch,
                secondClazz,secondName,secondBranch);
    }

    public <C1, C2, T1, T2, T3, T4, T5, T6, T7, T8>
    void as(Class<C1> firstClazz,  Consumer<T1> firstConsumer, Consumer<T2> firstBranch,
            Class<C2> secondClazz, TriConsumer<T3, T4, T5> secondConsumer, TriConsumer<T6, T7, T8> secondBranch) {
        matches(data,
                firstClazz, firstConsumer, firstBranch,
                secondClazz,secondConsumer,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz,  String firstName,  TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, String secondName, Consumer<T4> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        }

        if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 1);

            secondBranch.accept((T4) args[0]);
        }
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6, T7, T8>
    void matches(V data,
                 Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer, TriConsumer<T4, T5, T6> firstBranch,
                 Class<C2> secondClazz, Consumer<T7> secondConsumer, Consumer<T8> secondBranch) {
        if (firstClazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(firstConsumer, 3);

            firstBranch.accept((T4) args[0], (T5) args[1], (T6) args[2]);
        }

        if (secondClazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(secondConsumer, 1);

            secondBranch.accept((T8) args[0]);
        }
    }

    public <C1, C2, T1, T2, T3, T4>
    void as(Class<C1> firstClazz,  String firstName,  TriConsumer<T1, T2, T3> firstBranch,
            Class<C2> secondClazz, String secondName, Consumer<T4> secondBranch) {
        matches(data,
                firstClazz, firstName, firstBranch,
                secondClazz,secondName,secondBranch);
    }

    public <C1, C2, T1, T2, T3, T4, T5, T6, T7, T8>
    void as(Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer, TriConsumer<T4, T5, T6> firstBranch,
            Class<C2> secondClazz, Consumer<T7> secondConsumer, Consumer<T8> secondBranch) {
        matches(data,
                firstClazz, firstConsumer, firstBranch,
                secondClazz,secondConsumer,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  String firstName,  TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, String secondName, TriConsumer<T4, T5, T6> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        }

        if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 3);

            secondBranch.accept((T4) args[0], (T5) args[1], (T6) args[2]);
        }
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>
    void matches(V data,
                 Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,  TriConsumer<T4, T5, T6> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T7, T8, T9> secondConsumer, TriConsumer<T10, T11, T12> secondBranch) {
        if (firstClazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(firstConsumer, 3);

            firstBranch.accept((T4) args[0], (T5) args[1], (T6) args[2]);
        }

        if (secondClazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(secondConsumer, 3);

            secondBranch.accept((T10) args[0], (T11) args[1], (T12) args[2]);
        }
    }

    public <C1, C2, T1, T2, T3, T4, T5, T6>
    void as(Class<C1> firstClazz,  String firstName,  TriConsumer<T1, T2, T3> firstBranch,
            Class<C2> secondClazz, String secondName, TriConsumer<T4, T5, T6> secondBranch) {
        matches(data,
                firstClazz, firstName, firstBranch,
                secondClazz,secondName,secondBranch);
    }

    public <C1, C2, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12>
    void as(Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,  TriConsumer<T4, T5, T6> firstBranch,
            Class<C2> secondClazz, TriConsumer<T7, T8, T9> secondConsumer, TriConsumer<T10, T11, T12> secondBranch) {
        matches(data,
                firstClazz, firstConsumer, firstBranch,
                secondClazz,secondConsumer,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz,  String firstName,  BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, String secondName, TriConsumer<T3, T4, T5> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
        }

        if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 3);

            secondBranch.accept((T3) args[0], (T4) args[1], (T5) args[2]);
        }
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>
    void matches(V data,
                 Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer, BiConsumer<T3, T4> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T5, T6, T7> secondConsumer, TriConsumer<T8, T9, T10> secondBranch) {
        if (firstClazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(firstConsumer, 2);

            firstBranch.accept((T3) args[0], (T4) args[1]);
        }

        if (secondClazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(secondConsumer, 3);

            secondBranch.accept((T8) args[0], (T9) args[1], (T10) args[2]);
        }
    }

    public <C1, C2, T1, T2, T3, T4, T5>
    void as(Class<C1> firstClazz,  String firstName,  BiConsumer<T1, T2> firstBranch,
            Class<C2> secondClazz, String secondName, TriConsumer<T3, T4, T5> secondBranch)  {
        matches(data,
                firstClazz, firstName, firstBranch,
                secondClazz,secondName,secondBranch);

    }

    public <C1, C2, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>
    void as(Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer, BiConsumer<T3, T4> firstBranch,
            Class<C2> secondClazz, TriConsumer<T5, T6, T7> secondConsumer, TriConsumer<T8, T9, T10> secondBranch) {
        matches(data,
                firstClazz, firstConsumer, firstBranch,
                secondClazz,secondConsumer,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz,  String firstName,  TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, String secondName, BiConsumer<T4, T5> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        }

        if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 2);

            secondBranch.accept((T4) args[0], (T5) args[1]);
        }
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>
    void matches(V data,
                 Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,  TriConsumer<T4, T5, T6> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T7, T8> secondConsumer, BiConsumer<T9, T10> secondBranch) {
        if (firstClazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(firstConsumer, 3);

            firstBranch.accept((T4) args[0], (T5) args[1], (T6) args[2]);
        }

        if (secondClazz == data.getClass()) {
            Object[] args = Reflection.invokeUnreferenceExtractor(secondConsumer, 2);

            secondBranch.accept((T9) args[0], (T10) args[1]);
        }
    }

    public <C1, C2, T1, T2, T3, T4, T5>
    void as(Class<C1> firstClazz,  String firstName,  TriConsumer<T1, T2, T3> firstBranch,
            Class<C2> secondClazz, String secondName, BiConsumer<T4, T5> secondBranch)  {
        matches(data,
                firstClazz, firstName, firstBranch,
                secondClazz,secondName,secondBranch);
    }

    public <C1, C2, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>
    void as(Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,  TriConsumer<T4, T5, T6> firstBranch,
            Class<C2> secondClazz, BiConsumer<T7, T8> secondConsumer, BiConsumer<T9, T10> secondBranch) {
        matches(data,
                firstClazz, firstConsumer, firstBranch,
                secondClazz,secondConsumer,secondBranch);
    }

    private static <T1, T2> boolean compareValues(T1 first, T2 second) {
        return first != null && !first.equals(second);
    }
}
