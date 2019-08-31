/*
 * JPML - Java pattern matching library.
 *
 * Property pattern allow match type and access to fields class.
 * Maximum number of branches for match three with three
 * value params.
 */
package org.kl.pattern;

import org.kl.error.PatternException;
import org.kl.lambda.TriConsumer;
import org.kl.util.Tuple;

import java.lang.reflect.Field;
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
            Object[] args = prepareFields(value, (String) item.get(0));

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
        Object[] args = prepareFields(data, (String) item.get(0));

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
            Object[] args = prepareFields(value, clazz, (String) item.get(0));

            if (compareValues(item.get(1), args[0])) {
                return;
            }

            consumer.accept((T) args[0]);
        }
    }

    public static <V, T1, T2>
    void foreach(Collection<V> data, Tuple.Tuple4<String, T1, String, T2> item, BiConsumer<T1, T2> consumer)  {
        for (V value : data) {
            Object[] args = prepareFields(value, (String) item.get(0), item.get(2));

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
        Object[] args = prepareFields(data, (String) item.get(0), item.get(2));

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
            Object[] args = prepareFields(value, clazz, item.get(0), item.get(2));

            if (compareValues(item.get(1), args[0]) || compareValues(item.get(3), args[1])) {
                return;
            }

            consumer.accept((T1) args[0], (T2) args[1]);
        }
    }

    public static <V, T1, T2, T3>
    void foreach(Collection<V> data,
                 Tuple.Tuple6<String, T1, String, T2, String, T3> item, TriConsumer<T1, T2, T3> consumer)  {
        for (V value : data) {
            Object[] args = prepareFields(value, (String) item.get(0), item.get(2), item.get(4));

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
        Object[] args = prepareFields(data, (String) item.get(0), item.get(2), item.get(4));

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
            Object[] args = prepareFields(value, clazz, item.get(0), item.get(2), item.get(4));

            if (compareValues(item.get(1), args[0]) || compareValues(item.get(3), args[1]) ||
                compareValues(item.get(5), args[2])) {
                return;
            }

            consumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        }
    }

    private static <V> Object[] prepareFields(V value, String... names)  {
        return  prepareFields(value, value.getClass(), names);
    }

    private static <V, C> Object[] prepareFields(V value, Class<C> clazz, String... names)  {
        Object[] list = new Object[names.length];

        for (int i = 0; i < names.length; i++) {
            try {
                Field field = clazz.getDeclaredField(names[i]);
                field.setAccessible(true);

                list[i] = field.get(value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new PatternException("Can not access to field " + names[i]);
            }
        }

        return list;
    }

    private static <T1, T2> boolean compareValues(T1 first, T2 second) {
        return first != null && !first.equals(second);
    }
}
