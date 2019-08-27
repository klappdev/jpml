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
import org.kl.reflect.Reflection;

public final class PositionPattern {

    private PositionPattern() {}

    public static <T> Item<T> of(T value) {
        return new Item<>(value);
    }

    public static <V, C, T> void matches(V value, Class<C> clazz,
                                         Item<T> item, Runnable branch)  {
        if (clazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 1);

            if (Reflection.compareValues(item.getValue(), members[0])) {
                branch.run();
            }
        }
    }

    public static <T1, T2> BiItem<T1, T2> of(T1 firstValue, T2 secondValue) {
        return new BiItem<>(firstValue, secondValue);
    }

    public static <V, C, T1, T2> void matches(V value, Class<C> clazz,
                                              BiItem<T1, T2> item, Runnable branch)  {
        if (clazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 2);


            if (Reflection.compareValues(item.getFirstValue(),  members[0]) &&
                Reflection.compareValues(item.getSecondValue(), members[1])) {
                branch.run();
            }
        }
    }

    public static <T1, T2, T3> TriItem<T1, T2, T3> of(T1 firstValue, T2 secondValue, T3 thirdValue) {
        return new TriItem<>(firstValue, secondValue, thirdValue);
    }

    public static <V, C, T1, T2, T3> void matches(V value, Class<C> clazz,
                                                  TriItem<T1, T2, T3> item, Runnable branch)  {
        if (clazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 3);

            if (Reflection.compareValues(item.getFirstValue(),  members[0]) &&
                Reflection.compareValues(item.getSecondValue(), members[1]) &&
                Reflection.compareValues(item.getThirdValue(),  members[2])) {
                branch.run();
            }
        }
    }
}
