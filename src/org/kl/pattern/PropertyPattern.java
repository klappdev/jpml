/*
 * JPML - Java pattern matching library.
 *
 * Property pattern allow match type and access to fields class.
 * Maximum number of branches for match three with three
 * value params.
 */
package org.kl.pattern;

import org.kl.lambda.TriConsumer;
import org.kl.reflect.Reflection;
import org.kl.util.Tuple;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public final class PropertyPattern {

    private PropertyPattern() {}

    public static <V, T>
    void foreach(Collection<V> data, Tuple.Tuple2<String, T> item, Consumer<T> consumer)  {
        for (V value : data) {
            Object[] args = Reflection.fetchMembers(value, 1, (String) item.get(0));

            consumer.accept((T) args[0]);
        }
    }

    public static <V, T>
    void foreach(Collection<V> data, Function<V, T> function, Consumer<T> consumer) {
        for (V value : data) {
            T arg = function.apply(value);

            consumer.accept(arg);
        }
    }

    public static <V, T>
    void let(V data, Tuple.Tuple2<String, T> item, Consumer<T> consumer)  {
        Object[] args = Reflection.fetchMembers(data, 1, (String) item.get(0));

        consumer.accept((T) args[0]);
    }

    public static <V, T>
    void let(V data, Function<V, T> function, Consumer<T> consumer) {
        T arg = function.apply(data);

        consumer.accept(arg);
    }

    public static <T>
    Tuple.Tuple2<String, T> of(String field) {
        return new Tuple.Tuple2<>(field, null);
    }

    public static <T>
    Tuple.Tuple2<String, T> of(String field, T value) {
        return new Tuple.Tuple2<>(field, value);
    }

    public static <V, C, T>
    void matches(V value, Class<C> clazz, Tuple.Tuple2<String, T> item, Consumer<T> consumer)  {
        if (clazz == value.getClass()) {
            Object[] args = Reflection.fetchMembers(value, 1, (String) item.get(0));
            Object firstArg = item.get(1);

            if (firstArg == null) {
                consumer.accept((T) args[0]);
            } else if (Reflection.compareValues(firstArg, args[0])) {
                consumer.accept((T) args[0]);
            }
        }
    }

    public static <V, C, T>
    void matches(V value, Class<C> clazz, Function<V, T> function, Consumer<T> consumer)  {
        if (clazz == value.getClass()) {
            T arg = function.apply(value);
            consumer.accept(arg);
        }
    }

    public static <V, T1, T2>
    void foreach(Collection<V> data, Tuple.Tuple4<String, T1, String, T2> item, BiConsumer<T1, T2> consumer)  {
        for (V value : data) {
            Object[] args = Reflection.fetchMembers(value, 2, (String) item.get(0), item.get(2));

            consumer.accept((T1) args[0], (T2) args[1]);
        }
    }

    public static <V, T1, T2>
    void foreach(Collection<V> data,
                 Function<V, T1> firstFunction, Function<V, T2> secondFunction, BiConsumer<T1, T2> consumer) {
        for (V value : data) {
            T1 firstArg  = firstFunction.apply(value);
            T2 secondArg = secondFunction.apply(value);

            consumer.accept(firstArg, secondArg);
        }
    }

    @SuppressWarnings("unused")
    public static <K, V, T1, T2>
    void foreach(Map<K, V> data, Tuple.Tuple4<String, T1, String, T2> item, BiConsumer<T1, T2> consumer) {
        for (Map.Entry<K, V> entry : data.entrySet()) {
            consumer.accept((T1) entry.getKey(), (T2) entry.getValue());
        }
    }

    public static <V, T1, T2>
    void let(V data, Tuple.Tuple4<String, T1, String, T2> item, BiConsumer<T1, T2> consumer)  {
        Object[] args = Reflection.fetchMembers(data, 2, (String) item.get(0), item.get(2));

        consumer.accept((T1) args[0], (T2) args[1]);
    }

    public static <V, T1, T2>
    void let(V data,
             Function<V, T1> firstFunction, Function<V, T2> secondFunction, BiConsumer<T1, T2> consumer) {
        T1 firstArg  = firstFunction.apply(data);
        T2 secondArg = secondFunction.apply(data);

        consumer.accept(firstArg, secondArg);
    }

    public static <T1, T2>
    Tuple.Tuple4<String, T1, String, T2> of(String firstField, String secondField) {
        return new Tuple.Tuple4<>(firstField, null, secondField, null);
    }

    public static <T1, T2>
    Tuple.Tuple4<String, T1, String, T2> of(String firstField, T1 firstValue, String secondField, T2 secondValue) {
        return new Tuple.Tuple4<>(firstField, firstValue, secondField, secondValue);
    }

    public static <V, C, T1, T2>
    void matches(V value, Class<C> clazz, Tuple.Tuple4<String, T1, String, T2> item, BiConsumer<T1, T2> consumer)  {
        if (clazz == value.getClass()) {
            Object[] args = Reflection.fetchMembers(value, 2, item.get(0), item.get(2));
            Object firstArg  = item.get(1);
            Object secondArg = item.get(3);

            if (firstArg == null && secondArg == null) {
                consumer.accept((T1) args[0], (T2) args[1]);
            } else if (Reflection.compareValues(firstArg, args[0]) || Reflection.compareValues(secondArg, args[1])) {
                consumer.accept((T1) args[0], (T2) args[1]);
            }
        }
    }

    public static <V, C, T1, T2>
    void matches(V value, Class<C> clazz, Function<V, T1> firstFunction,
                 Function<V, T2> secondFunction, BiConsumer<T1, T2> consumer)  {
        if (clazz == value.getClass()) {
            T1 firstArg  = firstFunction.apply(value);
            T2 secondArg = secondFunction.apply(value);

            consumer.accept(firstArg, secondArg);
        }
    }

    public static <V, T1, T2, T3>
    void foreach(Collection<V> data,
                 Tuple.Tuple6<String, T1, String, T2, String, T3> item, TriConsumer<T1, T2, T3> consumer)  {
        for (V value : data) {
            Object[] args = Reflection.fetchMembers(value, 3, (String) item.get(0), item.get(2), item.get(4));

            consumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        }
    }

    public static <V, T1, T2, T3>
    void foreach(Collection<V> data,
                 Function<V, T1> firstFunction, Function<V, T2> secondFunction,
                 Function<V, T3> thirdFunction, TriConsumer<T1, T2, T3> consumer) {
        for (V value : data) {
            T1 firstArg  = firstFunction.apply(value);
            T2 secondArg = secondFunction.apply(value);
            T3 thirdArg  = thirdFunction.apply(value);

            consumer.accept(firstArg, secondArg, thirdArg);
        }
    }

    public static <V, T1, T2, T3>
    void let(V data,
             Tuple.Tuple6<String, T1, String, T2, String, T3> item, TriConsumer<T1, T2, T3> consumer)  {
        Object[] args = Reflection.fetchMembers(data, 3, (String) item.get(0), item.get(2), item.get(4));

        consumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
    }

    public static <V, T1, T2, T3>
    void let(V data,
             Function<V, T1> firstFunction, Function<V, T2> secondFunction,
             Function<V, T3> thirdFunction, TriConsumer<T1, T2, T3> consumer) {
        T1 firstArg  = firstFunction.apply(data);
        T2 secondArg = secondFunction.apply(data);
        T3 thirdArg  = thirdFunction.apply(data);

        consumer.accept(firstArg, secondArg, thirdArg);
    }

    public static <T1, T2, T3>
    Tuple.Tuple6<String, T1, String, T2, String, T3> of(String firstField, String secondField, String thirdField) {
        return new Tuple.Tuple6<>(firstField, null, secondField, null, thirdField, null);
    }

    public static <T1, T2, T3>
    Tuple.Tuple6<String, T1, String, T2, String, T3> of(String firstField,  T1 firstValue,
                                                        String secondField, T2 secondValue,
                                                        String thirdField,  T3 thirdValue) {
        return new Tuple.Tuple6<>(firstField, firstValue, secondField, secondValue, thirdField, thirdValue);
    }

    public static <V, C, T1, T2, T3>
    void matches(V value, Class<C> clazz,
                 Tuple.Tuple6<String, T1, String, T2, String, T3> item, TriConsumer<T1, T2, T3> consumer)  {
        if (clazz == value.getClass()) {
            Object[] args = Reflection.fetchMembers(value, 3, item.get(0), item.get(2), item.get(4));
            Object firstArg  = item.get(1);
            Object secondArg = item.get(3);
            Object thirdArg  = item.get(5);

            if (firstArg == null && secondArg == null && thirdArg == null) {
                consumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            } else if (Reflection.compareValues(firstArg, args[0]) || Reflection.compareValues(secondArg, args[1]) ||
                Reflection.compareValues(thirdArg, args[2])) {
                consumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            }
        }
    }

    public static <V, C, T1, T2, T3>
    void matches(V value, Class<C> clazz, Function<V, T1> firstFunction, Function<V, T2> secondFunction,
                 Function<V, T3> thirdFunction, TriConsumer<T1, T2, T3> consumer)  {
        if (clazz == value.getClass()) {
            T1 firstArg  = firstFunction.apply(value);
            T2 secondArg = secondFunction.apply(value);
            T3 thirdArg  = thirdFunction.apply(value);

            consumer.accept(firstArg, secondArg, thirdArg);
        }
    }
}
