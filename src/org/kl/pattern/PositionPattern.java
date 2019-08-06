/*
 * JPML - Java pattern matching library.
 *
 * Position pattern allow match type and check value fields
 * class in order of declaration. Maximum number of branches
 * for match three with three value fields.
 */
package org.kl.pattern;

import org.kl.bean.BiItem;
import org.kl.bean.Item;
import org.kl.bean.TriItem;
import org.kl.error.PatternException;
import org.kl.meta.Exclude;

import java.lang.reflect.Field;
import java.util.Arrays;

public final class PositionPattern {

    private PositionPattern() {}

    public static <T> Item<T> of(T value) {
        return new Item<>(value);
    }

    public static <V, C, T> void matches(V value, Class<C> clazz,
                                         Item<T> item, Runnable branch)  {
        if (clazz == value.getClass()) {
            Object[] args = prepareFields(value, clazz);

            if (countIncludeFields(clazz) != 1) {
                throw new PatternException("Count fields more then in target. Exclude unnecessary fields");
            }

            if (compareValues(item.getValue(), args[0])) {
                return;
            }

            branch.run();
        }
    }

    public static <T1, T2> BiItem<T1, T2> of(T1 firstValue, T2 secondValue) {
        return new BiItem<>(firstValue, secondValue);
    }

    public static <V, C, T1, T2> void matches(V value, Class<C> clazz,
                                              BiItem<T1, T2> item, Runnable branch)  {
        if (clazz == value.getClass()) {
            Object[] args = prepareFields(value, clazz);

            if (countIncludeFields(clazz) != 2) {
                throw new PatternException("Count fields more then in target. Exclude unnecessary fields");
            }

            if (compareValues(item.getFirstValue(), args[0]) || compareValues(item.getSecondValue(), args[1])) {
                return;
            }

            branch.run();
        }
    }

    public static <T1, T2, T3> TriItem<T1, T2, T3> of(T1 firstValue, T2 secondValue, T3 thirdValue) {
        return new TriItem<>(firstValue, secondValue, thirdValue);
    }

    public static <V, C, T1, T2, T3> void matches(V value, Class<C> clazz,
                                                  TriItem<T1, T2, T3> item, Runnable branch)  {
        if (clazz == value.getClass()) {
            Object[] args = prepareFields(value, clazz);

            if (countIncludeFields(clazz) != 3) {
                throw new PatternException("Count fields more then in target. Exclude unnecessary fields");
            }

            if (compareValues(item.getFirstValue(), args[0]) || compareValues(item.getSecondValue(), args[1]) ||
                compareValues(item.getThirdValue(), args[2])) {
                return;
            }

            branch.run();
        }
    }

    private static <V, C> Object[] prepareFields(V value, Class<C> clazz) {
        Field[]  fields = clazz.getDeclaredFields();
        Object[] list = new Object[fields.length];

        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                list[i] = fields[i].get(value);
            } catch (IllegalAccessException e) {
                throw new PatternException("Can not access to field " + fields[i].getName());
            }
        }

        return list;
    }

    private static <C> int countIncludeFields(Class<C> clazz) {
        return (int) Arrays.stream(clazz.getDeclaredFields())
                           .filter(field -> !field.isAnnotationPresent(Exclude.class))
                           .count();
    }

    private static <T1, T2> boolean compareValues(T1 first, T2 second) {
        return first != null && !first.equals(second);
    }
}
