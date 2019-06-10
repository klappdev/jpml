/*
 * JPML - Java pattern matching library.
 *
 * Property pattern allow match type and access to fields class.
 * Maximum number of branches for match three with three
 * value params.
 */
package org.kl.pattern;

import org.kl.bean.Item;
import org.kl.bean.BiItem;
import org.kl.bean.TriItem;
import org.kl.error.PatternException;

import java.lang.reflect.Field;
import java.util.Collection;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.kl.lambda.TriConsumer;

public final class PropertyPattern {

    private PropertyPattern() {}

    public static <V, T> void foreach(Collection<V> data, Item<T> item,
                                      Consumer<T> consumer) throws PatternException {
        for (V value : data) {
            Object[] args = prepareFields(value, item.getName());

            consumer.accept((T) args[0]);
        }
    }

    public static <V, T> void foreach(Collection<V> data, Function<V, T> function,
                                      Consumer<T> consumer) {
        for (V value : data) {
            T arg = function.apply(value);

            consumer.accept(arg);
        }
    }

    public static <V, T> void let(V data, Item<T> item,
                                  Consumer<T> consumer) throws PatternException {
        Object[] args = prepareFields(data, item.getName());

        consumer.accept((T) args[0]);
    }

    public static <V, T> void let(V data, Function<V, T> function,
                                  Consumer<T> consumer) {
        T arg = function.apply(data);

        consumer.accept(arg);
    }

    public static <T> Item<T> of(String field) {
        return new Item<>(field);
    }

    public static <T> Item<T> of(String field, T value) {
        return new Item<>(field, value);
    }

    public static <V, C, T> void matches(V value, Class<C> clazz,
                                         Item<T> item, Consumer<T> consumer) throws PatternException {
        if (clazz == value.getClass()) {
            Object[] args = prepareFields(value, clazz, item.getName());

            if (compareValues(item.getValue(), args[0])) {
                return;
            }

            consumer.accept((T) args[0]);
        }
    }

    public static <V, T1, T2> void foreach(Collection<V> data, BiItem<T1, T2> item,
                                           BiConsumer<T1, T2> consumer) throws PatternException {
        for (V value : data) {
            Object[] args = prepareFields(value, item.getFirstName(), item.getSecondName());

            consumer.accept((T1) args[0], (T2) args[1]);
        }
    }

    public static <V, T1, T2> void foreach(Collection<V> data, Function<V, T1> firstFunction,
                                           Function<V, T2> secondFunction, BiConsumer<T1, T2> consumer) {
        for (V value : data) {
            T1 firstArg  = firstFunction.apply(value);
            T2 secondArg = secondFunction.apply(value);

            consumer.accept(firstArg, secondArg);
        }
    }

    @SuppressWarnings("unused")
    public static <K, V, T1, T2> void foreach(Map<K, V> data, BiItem<T1, T2> item, BiConsumer<T1, T2> consumer) {
        for (Map.Entry<K, V> entry : data.entrySet()) {
            consumer.accept((T1) entry.getKey(), (T2) entry.getValue());
        }
    }

    public static <V, T1, T2> void let(V data, BiItem<T1, T2> item,
                                       BiConsumer<T1, T2> consumer) throws PatternException {
        Object[] args = prepareFields(data, item.getFirstName(), item.getSecondName());

        consumer.accept((T1) args[0], (T2) args[1]);
    }

    public static <V, T1, T2> void let(V data, Function<V, T1> firstFunction,
                                       Function<V, T2> secondFunction, BiConsumer<T1, T2> consumer) {
        T1 firstArg  = firstFunction.apply(data);
        T2 secondArg = secondFunction.apply(data);

        consumer.accept(firstArg, secondArg);
    }

    public static <T1, T2> BiItem<T1, T2> of(String firstField, String secondField) {
        return new BiItem<>(firstField, secondField);
    }

    public static <T1, T2> BiItem<T1, T2> of(String firstField, T1 firstValue, String secondField, T2 secondValue) {
        return new BiItem<>(firstField, firstValue, secondField, secondValue);
    }

    public static <V, C, T1, T2> void matches(V value, Class<C> clazz, BiItem<T1, T2> item,
                                              BiConsumer<T1, T2> consumer) throws PatternException {
        if (clazz == value.getClass()) {
            Object[] args = prepareFields(value, clazz, item.getFirstName(), item.getSecondName());

            if (compareValues(item.getFirstValue(), args[0]) || compareValues(item.getSecondValue(), args[1])) {
                return;
            }

            consumer.accept((T1) args[0], (T2) args[1]);
        }
    }

    public static <V, T1, T2, T3> void foreach(Collection<V> data, TriItem<T1, T2, T3> item,
                                               TriConsumer<T1, T2, T3> consumer) throws PatternException {
        for (V value : data) {
            Object[] args = prepareFields(value, item.getFirstName(), item.getSecondName(), item.getThirdName());

            consumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        }
    }

    public static <V, T1, T2, T3> void foreach(Collection<V> data,
                                               Function<V, T1> firstFunction, Function<V, T2> secondFunction,
                                               Function<V, T3> thirdFunction, TriConsumer<T1, T2, T3> consumer) {
        for (V value : data) {
            T1 firstArg  = firstFunction.apply(value);
            T2 secondArg = secondFunction.apply(value);
            T3 thirdArg  = thirdFunction.apply(value);

            consumer.accept(firstArg, secondArg, thirdArg);
        }
    }

    public static <V, T1, T2, T3> void let(V data, TriItem<T1, T2, T3> item,
                                           TriConsumer<T1, T2, T3> consumer) throws PatternException {
        Object[] args = prepareFields(data, item.getFirstName(), item.getSecondName(), item.getThirdName());

        consumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
    }

    public static <V, T1, T2, T3> void let(V data,
                                           Function<V, T1> firstFunction, Function<V, T2> secondFunction,
                                           Function<V, T3> thirdFunction, TriConsumer<T1, T2, T3> consumer) {
        T1 firstArg  = firstFunction.apply(data);
        T2 secondArg = secondFunction.apply(data);
        T3 thirdArg  = thirdFunction.apply(data);

        consumer.accept(firstArg, secondArg, thirdArg);
    }

    public static <T1, T2, T3> TriItem<T1, T2, T3> of(String firstField, String secondField, String thirdField) {
        return new TriItem<>(firstField, secondField, thirdField);
    }

    public static <T1, T2, T3> TriItem<T1, T2, T3> of(String firstField, T1 firstValue,
                                                      String secondField, T2 secondValue,
                                                      String thirdField, T3 thirdValue) {
        return new TriItem<>(firstField, firstValue, secondField, secondValue, thirdField, thirdValue);
    }

    public static <V, C, T1, T2, T3> void matches(V value, Class<C> clazz, TriItem<T1, T2, T3> item,
                                                  TriConsumer<T1, T2, T3> consumer) throws PatternException {
        if (clazz == value.getClass()) {
            Object[] args = prepareFields(value, clazz, item.getFirstName(), item.getSecondName(), item.getThirdName());

            if (compareValues(item.getFirstValue(), args[0]) || compareValues(item.getSecondValue(), args[1]) ||
                compareValues(item.getThirdValue(), args[2])) {
                return;
            }

            consumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        }
    }

    private static <V> Object[] prepareFields(V value, String... names) throws PatternException {
        return  prepareFields(value, value.getClass(), names);
    }

    private static <V, C> Object[] prepareFields(V value, Class<C> clazz, String... names) throws PatternException {
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
