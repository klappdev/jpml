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
import org.kl.reflect.Reflection;

import java.util.function.Supplier;

public final class PositionPattern {
    private static Object data;
    private static PositionPattern instance;

    private PositionPattern() {}

    public static <V> PositionPattern matches(V value) {
        data = value;

        if (instance == null) {
            instance = new PositionPattern();
        }

        return instance;
    }

    public static <T> Item<T> of(T value) {
        return new Item<>(value);
    }

    public static <V, C, T>
    void matches(V value, Class<C> clazz, Item<T> item, Runnable branch) {
        if (clazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 1);

            if (Reflection.compareValues(item.getValue(), members[0])) {
                branch.run();
            }
        }
    }

    public <C, T>
    void as(Class<C> clazz, Item<T> item, Runnable branch) {
        matches(data, clazz, item, branch);
    }

    public static <V, C, T, R>
    R matches(V data, Class<C> clazz, Item<T> item, Supplier<R> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] members = Reflection.fetchMembers(data, 1);

            if (Reflection.compareValues(item.getValue(), members[0])) {
                return firstBranch.get();
            }
        }

        throw new PatternException("Expression must to have only one branch");
    }

    public <C, T, R>
    R as(Class<C> clazz, Item<T> item, Supplier<R> firstBranch) {
        return matches(data, clazz, item, firstBranch);
    }

    public static <T1, T2> BiItem<T1, T2> of(T1 firstValue, T2 secondValue) {
        return new BiItem<>(firstValue, secondValue);
    }

    public static <V, C, T1, T2>
    void matches(V value, Class<C> clazz, BiItem<T1, T2> item, Runnable branch) {
        if (clazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 2);

            if (Reflection.compareValues(item.getFirstValue(),  members[0]) &&
                Reflection.compareValues(item.getSecondValue(), members[1])) {
                branch.run();
            }
        }
    }

    public <C, T1, T2>
    void as(Class<C> clazz, BiItem<T1, T2> item, Runnable branch) {
        matches(data, clazz, item, branch);
    }

    public static <V, C, T1, T2, R>
    R matches(V data, Class<C> clazz, BiItem<T1, T2> item, Supplier<R> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] members = Reflection.fetchMembers(data, 2);

            if (Reflection.compareValues(item.getFirstValue(),  members[0]) &&
                Reflection.compareValues(item.getSecondValue(), members[1])) {
                return firstBranch.get();
            }
        }

        throw new PatternException("Expression must to have only one branch");
    }

    public <C, T1, T2, R>
    R as(Class<C> clazz, BiItem<T1, T2> item, Supplier<R> firstBranch) {
        return matches(data, clazz, item, firstBranch);
    }

    public static <T1, T2, T3> TriItem<T1, T2, T3> of(T1 firstValue, T2 secondValue, T3 thirdValue) {
        return new TriItem<>(firstValue, secondValue, thirdValue);
    }

    public static <V, C, T1, T2, T3>
    void matches(V value, Class<C> clazz, TriItem<T1, T2, T3> item, Runnable branch)  {
        if (clazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 3);

            if (Reflection.compareValues(item.getFirstValue(),  members[0]) &&
                Reflection.compareValues(item.getSecondValue(), members[1]) &&
                Reflection.compareValues(item.getThirdValue(),  members[2])) {
                branch.run();
            }
        }
    }

    public <C, T1, T2, T3>
    void as(Class<C> clazz, TriItem<T1, T2, T3> item, Runnable branch) {
        matches(data, clazz, item, branch);
    }

    public static <V, C, T1, T2, T3, R>
    R matches(V data, Class<C> clazz, TriItem<T1, T2, T3> item, Supplier<R> firstBranch)  {
        if (clazz == data.getClass()) {
            Object[] members = Reflection.fetchMembers(data, 3);

            if (Reflection.compareValues(item.getFirstValue(),  members[0]) &&
                Reflection.compareValues(item.getSecondValue(), members[1]) &&
                Reflection.compareValues(item.getThirdValue(),  members[2])) {
                return firstBranch.get();
            }
        }

        throw new PatternException("Expression must to have only one branch");
    }

    public <C, T1, T2, T3, R>
    R as(Class<C> clazz, TriItem<T1, T2, T3> item, Supplier<R> firstBranch) {
        return matches(data, clazz, item, firstBranch);
    }

    public static <V, C1, C2, T1, T2>
    void matches(V value,
                 Class<C1> firstClazz,  Item<T1> firstItem,  Runnable firstBranch,
                 Class<C2> secondClazz, Item<T2> secondItem, Runnable secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 1);

            if (Reflection.compareValues(firstItem.getValue(), members[0])) {
                firstBranch.run();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 1);

            if (Reflection.compareValues(secondItem.getValue(), members[0])) {
                secondBranch.run();
            }
        }
    }

    public <C1, C2, T1, T2>
    void as(Class<C1> firstClazz,  Item<T1> firstItem,  Runnable firstBranch,
            Class<C2> secondClazz, Item<T2> secondItem, Runnable secondBranch) {
        matches(data,
                firstClazz, firstItem, firstBranch,
                secondClazz,secondItem,secondBranch);
    }

    public static <V, C1, C2, T1, T2, R>
    R matches(V value,
              Class<C1> firstClazz,  Item<T1> firstItem,  Supplier<R> firstBranch,
              Class<C2> secondClazz, Item<T2> secondItem, Supplier<R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 1);

            if (Reflection.compareValues(firstItem.getValue(), members[0])) {
                return firstBranch.get();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 1);

            if (Reflection.compareValues(secondItem.getValue(), members[0])) {
                return secondBranch.get();
            }
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, R>
    R as(Class<C1> firstClazz,  Item<T1> firstItem,  Supplier<R> firstBranch,
         Class<C2> secondClazz, Item<T2> secondItem, Supplier<R> secondBranch) {
        return matches(data,
                       firstClazz, firstItem, firstBranch,
                       secondClazz,secondItem,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3>
    void matches(V value,
                 Class<C1> firstClazz,  Item<T1> firstItem,  Runnable firstBranch,
                 Class<C2> secondClazz, BiItem<T2, T3> secondItem, Runnable secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 1);

            if (Reflection.compareValues(firstItem.getValue(), members[0])) {
                firstBranch.run();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 2);

            if (Reflection.compareValues(secondItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(secondItem.getSecondValue(), members[1])) {
                secondBranch.run();
            }
        }
    }

    public <C1, C2, T1, T2, T3>
    void as(Class<C1> firstClazz,  Item<T1> firstItem,  Runnable firstBranch,
            Class<C2> secondClazz, BiItem<T2, T3> secondItem, Runnable secondBranch) {
        matches(data,
                firstClazz, firstItem, firstBranch,
                secondClazz,secondItem,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, R>
    R matches(V value,
              Class<C1> firstClazz,  Item<T1> firstItem,  Supplier<R> firstBranch,
              Class<C2> secondClazz, BiItem<T2, T3> secondItem, Supplier<R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 1);

            if (Reflection.compareValues(firstItem.getValue(), members[0])) {
                return firstBranch.get();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 2);

            if (Reflection.compareValues(secondItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(secondItem.getSecondValue(), members[1])) {
                return secondBranch.get();
            }
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, R>
    R as(Class<C1> firstClazz,  Item<T1> firstItem,  Supplier<R> firstBranch,
         Class<C2> secondClazz, BiItem<T2, T3> secondItem, Supplier<R> secondBranch) {
        return matches(data,
                       firstClazz, firstItem, firstBranch,
                       secondClazz,secondItem,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3>
    void matches(V value,
                 Class<C1> firstClazz,  BiItem<T1, T2> firstItem,  Runnable firstBranch,
                 Class<C2> secondClazz, Item<T3> secondItem, Runnable secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 2);

            if (Reflection.compareValues(firstItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(firstItem.getSecondValue(), members[1])) {
                firstBranch.run();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 1);

            if (Reflection.compareValues(secondItem.getValue(), members[0])) {
                secondBranch.run();
            }
        }
    }

    public <C1, C2, T1, T2, T3>
    void as(Class<C1> firstClazz,  BiItem<T1, T2> firstItem,  Runnable firstBranch,
            Class<C2> secondClazz, Item<T3> secondItem, Runnable secondBranch) {
        matches(data,
                firstClazz, firstItem, firstBranch,
                secondClazz,secondItem,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, R>
    R matches(V value,
              Class<C1> firstClazz,  BiItem<T1, T2> firstItem,  Supplier<R> firstBranch,
              Class<C2> secondClazz, Item<T3> secondItem, Supplier<R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 2);

            if (Reflection.compareValues(firstItem.getFirstValue(), members[0]) &&
                Reflection.compareValues(firstItem.getSecondValue(), members[1])) {
                return firstBranch.get();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 1);

            if (Reflection.compareValues(secondItem.getValue(), members[0])) {
                return secondBranch.get();
            }
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, R>
    R as(Class<C1> firstClazz,  BiItem<T1, T2> firstItem,  Supplier<R> firstBranch,
         Class<C2> secondClazz, Item<T3> secondItem, Supplier<R> secondBranch) {
        return matches(data,
                       firstClazz, firstItem, firstBranch,
                       secondClazz,secondItem,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz,  BiItem<T1, T2> firstItem,  Runnable firstBranch,
                 Class<C2> secondClazz, BiItem<T3, T4> secondItem, Runnable secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 2);

            if (Reflection.compareValues(firstItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(firstItem.getSecondValue(), members[1])) {
                firstBranch.run();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 2);

            if (Reflection.compareValues(secondItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(secondItem.getSecondValue(), members[1])) {
                secondBranch.run();
            }
        }
    }

    public <C1, C2, T1, T2, T3, T4>
    void as(Class<C1> firstClazz,  BiItem<T1, T2> firstItem,  Runnable firstBranch,
            Class<C2> secondClazz, BiItem<T3, T4> secondItem, Runnable secondBranch) {
        matches(data,
                firstClazz, firstItem, firstBranch,
                secondClazz,secondItem,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, R>
    R matches(V value,
              Class<C1> firstClazz,  BiItem<T1, T2> firstItem,  Supplier<R> firstBranch,
              Class<C2> secondClazz, BiItem<T3, T4> secondItem, Supplier<R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 2);

            if (Reflection.compareValues(firstItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(firstItem.getSecondValue(), members[1])) {
                return firstBranch.get();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 2);

            if (Reflection.compareValues(secondItem.getFirstValue(), members[0]) &&
                Reflection.compareValues(secondItem.getSecondValue(), members[1])) {
                return secondBranch.get();
            }
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, R>
    R as(Class<C1> firstClazz,  BiItem<T1, T2> firstItem,  Supplier<R> firstBranch,
         Class<C2> secondClazz, BiItem<T3, T4> secondItem, Supplier<R> secondBranch) {
        return matches(data,
                       firstClazz, firstItem, firstBranch,
                       secondClazz,secondItem,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz,  Item<T1> firstItem,  Runnable firstBranch,
                 Class<C2> secondClazz, TriItem<T2, T3, T4> secondItem, Runnable secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 1);

            if (Reflection.compareValues(firstItem.getValue(), members[0])) {
                firstBranch.run();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 3);

            if (Reflection.compareValues(secondItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(secondItem.getSecondValue(), members[1]) &&
                Reflection.compareValues(secondItem.getThirdValue(),  members[2])) {
                secondBranch.run();
            }
        }
    }

    public <C1, C2, T1, T2, T3, T4>
    void as(Class<C1> firstClazz,  Item<T1> firstItem,  Runnable firstBranch,
            Class<C2> secondClazz, TriItem<T2, T3, T4> secondItem, Runnable secondBranch) {
        matches(data,
                firstClazz, firstItem, firstBranch,
                secondClazz,secondItem,secondBranch);
    }


    public static <V, C1, C2, T1, T2, T3, T4, R>
    R matches(V value,
              Class<C1> firstClazz,  Item<T1> firstItem,  Supplier<R> firstBranch,
              Class<C2> secondClazz, TriItem<T2, T3, T4> secondItem, Supplier<R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 1);

            if (Reflection.compareValues(firstItem.getValue(),  members[0])) {
                return firstBranch.get();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 3);

            if (Reflection.compareValues(secondItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(secondItem.getSecondValue(), members[1]) &&
                Reflection.compareValues(secondItem.getThirdValue(),  members[2])) {
                return secondBranch.get();
            }
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, R>
    R as(Class<C1> firstClazz,  Item<T1> firstItem,  Supplier<R> firstBranch,
         Class<C2> secondClazz, TriItem<T2, T3, T4> secondItem, Supplier<R> secondBranch) {
        return matches(data,
                       firstClazz, firstItem, firstBranch,
                       secondClazz,secondItem,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz,  TriItem<T1, T2, T3> firstItem,  Runnable firstBranch,
                 Class<C2> secondClazz, Item<T4> secondItem, Runnable secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 3);

            if (Reflection.compareValues(firstItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(firstItem.getSecondValue(), members[1]) &&
                Reflection.compareValues(firstItem.getThirdValue(),  members[2])) {
                firstBranch.run();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 1);

            if (Reflection.compareValues(secondItem.getValue(), members[0])) {
                secondBranch.run();
            }
        }
    }

    public <C1, C2, T1, T2, T3, T4>
    void as(Class<C1> firstClazz,  TriItem<T1, T2, T3> firstItem,  Runnable firstBranch,
            Class<C2> secondClazz, Item<T4> secondItem, Runnable secondBranch) {
        matches(data,
                firstClazz, firstItem, firstBranch,
                secondClazz,secondItem,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, R>
    R matches(V value,
              Class<C1> firstClazz,  TriItem<T1, T2, T3> firstItem,  Supplier<R> firstBranch,
              Class<C2> secondClazz, Item<T4> secondItem, Supplier<R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 3);

            if (Reflection.compareValues(firstItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(firstItem.getSecondValue(), members[1]) &&
                Reflection.compareValues(firstItem.getThirdValue(),  members[2])) {
                return firstBranch.get();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 1);

            if (Reflection.compareValues(secondItem.getValue(),  members[0])) {
                return secondBranch.get();
            }
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, R>
    R as(Class<C1> firstClazz,  TriItem<T1, T2, T3> firstItem,  Supplier<R> firstBranch,
         Class<C2> secondClazz, Item<T4> secondItem, Supplier<R> secondBranch) {
        return matches(data,
                       firstClazz, firstItem, firstBranch,
                       secondClazz,secondItem,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  TriItem<T1, T2, T3> firstItem,  Runnable firstBranch,
                 Class<C2> secondClazz, TriItem<T4, T5, T6> secondItem, Runnable secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 3);

            if (Reflection.compareValues(firstItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(firstItem.getSecondValue(), members[1]) &&
                Reflection.compareValues(firstItem.getThirdValue(),  members[2])) {
                firstBranch.run();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 3);

            if (Reflection.compareValues(secondItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(secondItem.getSecondValue(), members[1]) &&
                Reflection.compareValues(secondItem.getThirdValue(),  members[2])) {
                secondBranch.run();
            }
        }
    }

    public <C1, C2, T1, T2, T3, T4, T5, T6>
    void as(Class<C1> firstClazz,  TriItem<T1, T2, T3> firstItem,  Runnable firstBranch,
            Class<C2> secondClazz, TriItem<T4, T5, T6> secondItem, Runnable secondBranch) {
        matches(data,
                firstClazz, firstItem, firstBranch,
                secondClazz,secondItem,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6, R>
    R matches(V value,
              Class<C1> firstClazz,  TriItem<T1, T2, T3> firstItem,  Supplier<R> firstBranch,
              Class<C2> secondClazz, TriItem<T4, T5, T6> secondItem, Supplier<R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 3);

            if (Reflection.compareValues(firstItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(firstItem.getSecondValue(), members[1]) &&
                Reflection.compareValues(firstItem.getThirdValue(),  members[2])) {
                return firstBranch.get();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 3);

            if (Reflection.compareValues(secondItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(secondItem.getSecondValue(), members[1]) &&
                Reflection.compareValues(secondItem.getThirdValue(),  members[2])) {
                return secondBranch.get();
            }
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, T5, T6, R>
    R as(Class<C1> firstClazz,  TriItem<T1, T2, T3> firstItem,  Supplier<R> firstBranch,
         Class<C2> secondClazz, TriItem<T4, T5, T6> secondItem, Supplier<R> secondBranch) {
        return matches(data,
                       firstClazz, firstItem, firstBranch,
                       secondClazz,secondItem,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz,  BiItem<T1, T2> firstItem,  Runnable firstBranch,
                 Class<C2> secondClazz, TriItem<T3, T4, T5> secondItem, Runnable secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 2);

            if (Reflection.compareValues(firstItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(firstItem.getSecondValue(), members[1])) {
                firstBranch.run();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 3);

            if (Reflection.compareValues(secondItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(secondItem.getSecondValue(), members[1]) &&
                Reflection.compareValues(secondItem.getThirdValue(),  members[2])) {
                secondBranch.run();
            }
        }
    }

    public <C1, C2, T1, T2, T3, T4, T5>
    void as(Class<C1> firstClazz,  BiItem<T1, T2> firstItem,  Runnable firstBranch,
            Class<C2> secondClazz, TriItem<T3, T4, T5> secondItem, Runnable secondBranch) {
        matches(data,
                firstClazz, firstItem, firstBranch,
                secondClazz,secondItem,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, R>
    R matches(V value,
              Class<C1> firstClazz,  BiItem<T1, T2> firstItem,  Supplier<R> firstBranch,
              Class<C2> secondClazz, TriItem<T3, T4, T5> secondItem, Supplier<R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 2);

            if (Reflection.compareValues(firstItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(firstItem.getSecondValue(), members[1])) {
                return firstBranch.get();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 3);

            if (Reflection.compareValues(secondItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(secondItem.getSecondValue(), members[1]) &&
                Reflection.compareValues(secondItem.getThirdValue(),  members[2])) {
                return secondBranch.get();
            }
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, T5, R>
    R as(Class<C1> firstClazz,  BiItem<T1, T2> firstItem,  Supplier<R> firstBranch,
         Class<C2> secondClazz, TriItem<T3, T4, T5> secondItem, Supplier<R> secondBranch) {
        return matches(data,
                       firstClazz, firstItem, firstBranch,
                       secondClazz,secondItem,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz,  TriItem<T1, T2, T3> firstItem,  Runnable firstBranch,
                 Class<C2> secondClazz, BiItem<T4, T5> secondItem, Runnable secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 3);

            if (Reflection.compareValues(firstItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(firstItem.getSecondValue(), members[1]) &&
                Reflection.compareValues(firstItem.getThirdValue(),  members[2])) {
                firstBranch.run();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 2);

            if (Reflection.compareValues(secondItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(secondItem.getSecondValue(), members[1])) {
                secondBranch.run();
            }
        }
    }

    public <C1, C2, T1, T2, T3, T4, T5>
    void as(Class<C1> firstClazz,  TriItem<T1, T2, T3> firstItem,  Runnable firstBranch,
            Class<C2> secondClazz, BiItem<T4, T5> secondItem, Runnable secondBranch) {
        matches(data,
                firstClazz, firstItem, firstBranch,
                secondClazz,secondItem,secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, R>
    R matches(V value,
              Class<C1> firstClazz,  TriItem<T1, T2, T3> firstItem,  Supplier<R> firstBranch,
              Class<C2> secondClazz, BiItem<T4, T5> secondItem, Supplier<R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 3);

            if (Reflection.compareValues(firstItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(firstItem.getSecondValue(), members[1]) &&
                Reflection.compareValues(firstItem.getThirdValue(),  members[2])) {
                return firstBranch.get();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchMembers(value, 2);

            if (Reflection.compareValues(secondItem.getFirstValue(),  members[0]) &&
                Reflection.compareValues(secondItem.getSecondValue(), members[1])) {
                return secondBranch.get();
            }
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, T5, R>
    R as(Class<C1> firstClazz,  TriItem<T1, T2, T3> firstItem,  Supplier<R> firstBranch,
         Class<C2> secondClazz, BiItem<T4, T5> secondItem, Supplier<R> secondBranch) {
        return matches(data,
                       firstClazz, firstItem, firstBranch,
                       secondClazz,secondItem,secondBranch);
    }
}
