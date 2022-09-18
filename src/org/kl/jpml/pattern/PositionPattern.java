/*
 * Licensed under the MIT License <http://opensource.org/licenses/MIT>.
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2019 - 2022 https://github.com/klappdev
 *
 * Permission is hereby  granted, free of charge, to any  person obtaining a copy
 * of this software and associated  documentation files (the "Software"), to deal
 * in the Software  without restriction, including without  limitation the rights
 * to  use, copy,  modify, merge,  publish, distribute,  sublicense, and/or  sell
 * copies  of  the Software,  and  to  permit persons  to  whom  the Software  is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE  IS PROVIDED "AS  IS", WITHOUT WARRANTY  OF ANY KIND,  EXPRESS OR
 * IMPLIED,  INCLUDING BUT  NOT  LIMITED TO  THE  WARRANTIES OF  MERCHANTABILITY,
 * FITNESS FOR  A PARTICULAR PURPOSE AND  NONINFRINGEMENT. IN NO EVENT  SHALL THE
 * AUTHORS  OR COPYRIGHT  HOLDERS  BE  LIABLE FOR  ANY  CLAIM,  DAMAGES OR  OTHER
 * LIABILITY, WHETHER IN AN ACTION OF  CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE  OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.kl.jpml.pattern;

import org.kl.jpml.error.PatternException;
import org.kl.jpml.reflect.Reflection;
import org.kl.jpml.util.Tuple;

import java.util.function.Supplier;

/**
 * Position pattern allow match type and check value fields
 * class in order of declaration. Maximum number of branches
 * for match three with three value fields.
 */
public final class PositionPattern {
    private final Object value;

    private <V> PositionPattern(V value) {
        this.value = value;
    }

    public static <V> PositionPattern match(V value) {
        return new PositionPattern(value);
    }

    public static <T> Tuple.Tuple2<String, T> of(T value) {
        return new Tuple.Tuple2<>("", value);
    }

    public static <V, C, T>
    void match(V value, Class<C> clazz, Tuple.Tuple2<String, T> item, Runnable branch) {
        if (clazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 1);

            if (Reflection.compareValues(item.second(), members[0])) {
                branch.run();
            }
        }
    }

    public <C, T>
    void as(Class<C> clazz, Tuple.Tuple2<String, T> item, Runnable branch) {
        match(value, clazz, item, branch);
    }

    public static <V, C, T, R>
    R match(V data, Class<C> clazz, Tuple.Tuple2<String, T> item, Supplier<R> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] members = Reflection.fetchFields(data, 1);

            if (Reflection.compareValues(item.second(), members[0])) {
                return firstBranch.get();
            }
        }

        throw new PatternException("Expression must to have only one branch");
    }

    public <C, T, R>
    R as(Class<C> clazz, Tuple.Tuple2<String, T> item, Supplier<R> firstBranch) {
        return match(value, clazz, item, firstBranch);
    }

    public static <T1, T2> Tuple.Tuple4<String, T1, String, T2> of(T1 firstValue, T2 secondValue) {
        return new Tuple.Tuple4<>("", firstValue, "", secondValue);
    }

    public static <V, C, T1, T2>
    void match(V value, Class<C> clazz, Tuple.Tuple4<String, T1, String, T2> item, Runnable branch) {
        if (clazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 2);

            if (Reflection.compareValues(item.second(), members[0]) &&
                    Reflection.compareValues(item.fourth(), members[1])) {
                branch.run();
            }
        }
    }

    public <C, T1, T2>
    void as(Class<C> clazz, Tuple.Tuple4<String, T1, String, T2> item, Runnable branch) {
        match(value, clazz, item, branch);
    }

    public static <V, C, T1, T2, R>
    R match(V data, Class<C> clazz, Tuple.Tuple4<String, T1, String, T2> item, Supplier<R> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] members = Reflection.fetchFields(data, 2);

            if (Reflection.compareValues(item.second(), members[0]) &&
                    Reflection.compareValues(item.fourth(), members[1])) {
                return firstBranch.get();
            }
        }

        throw new PatternException("Expression must to have only one branch");
    }

    public <C, T1, T2, R>
    R as(Class<C> clazz, Tuple.Tuple4<String, T1, String, T2> item, Supplier<R> firstBranch) {
        return match(value, clazz, item, firstBranch);
    }

    public static <T1, T2, T3>
    Tuple.Tuple6<String, T1, String, T2, String, T3> of(T1 firstValue, T2 secondValue, T3 thirdValue) {
        return new Tuple.Tuple6<>("", firstValue, "", secondValue, "", thirdValue);
    }

    public static <V, C, T1, T2, T3>
    void match(V value, Class<C> clazz, Tuple.Tuple6<String, T1, String, T2, String, T3> item, Runnable branch) {
        if (clazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 3);

            if (Reflection.compareValues(item.second(), members[0]) &&
                    Reflection.compareValues(item.fourth(), members[1]) &&
                    Reflection.compareValues(item.sixth(), members[2])) {
                branch.run();
            }
        }
    }

    public <C, T1, T2, T3>
    void as(Class<C> clazz, Tuple.Tuple6<String, T1, String, T2, String, T3> item, Runnable branch) {
        match(value, clazz, item, branch);
    }

    public static <V, C, T1, T2, T3, R>
    R match(V data, Class<C> clazz, Tuple.Tuple6<String, T1, String, T2, String, T3> item, Supplier<R> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] members = Reflection.fetchFields(data, 3);

            if (Reflection.compareValues(item.second(), members[0]) &&
                    Reflection.compareValues(item.fourth(), members[1]) &&
                    Reflection.compareValues(item.sixth(), members[2])) {
                return firstBranch.get();
            }
        }

        throw new PatternException("Expression must to have only one branch");
    }

    public <C, T1, T2, T3, R>
    R as(Class<C> clazz, Tuple.Tuple6<String, T1, String, T2, String, T3> item, Supplier<R> firstBranch) {
        return match(value, clazz, item, firstBranch);
    }

    public static <V, C1, C2, T1, T2>
    void match(V value,
               Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Runnable firstBranch,
               Class<C2> secondClazz, Tuple.Tuple2<String, T2> secondItem, Runnable secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 1);

            if (Reflection.compareValues(firstItem.second(), members[0])) {
                firstBranch.run();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 1);

            if (Reflection.compareValues(secondItem.second(), members[0])) {
                secondBranch.run();
            }
        }
    }

    public <C1, C2, T1, T2>
    void as(Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Runnable firstBranch,
            Class<C2> secondClazz, Tuple.Tuple2<String, T2> secondItem, Runnable secondBranch) {
        match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, C1, C2, T1, T2, R>
    R match(V value,
            Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Supplier<R> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple2<String, T2> secondItem, Supplier<R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 1);

            if (Reflection.compareValues(firstItem.second(), members[0])) {
                return firstBranch.get();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 1);

            if (Reflection.compareValues(secondItem.second(), members[0])) {
                return secondBranch.get();
            }
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, R>
    R as(Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Supplier<R> firstBranch,
         Class<C2> secondClazz, Tuple.Tuple2<String, T2> secondItem, Supplier<R> secondBranch) {
        return match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3>
    void match(V value,
               Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Runnable firstBranch,
               Class<C2> secondClazz, Tuple.Tuple4<String, T2, String, T3> secondItem, Runnable secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 1);

            if (Reflection.compareValues(firstItem.second(), members[0])) {
                firstBranch.run();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 2);

            if (Reflection.compareValues(secondItem.second(), members[0]) &&
                    Reflection.compareValues(secondItem.fourth(), members[1])) {
                secondBranch.run();
            }
        }
    }

    public <C1, C2, T1, T2, T3>
    void as(Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Runnable firstBranch,
            Class<C2> secondClazz, Tuple.Tuple4<String, T2, String, T3> secondItem, Runnable secondBranch) {
        match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, R>
    R match(V value,
            Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Supplier<R> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple4<String, T2, String, T3> secondItem, Supplier<R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 1);

            if (Reflection.compareValues(firstItem.second(), members[0])) {
                return firstBranch.get();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 2);

            if (Reflection.compareValues(secondItem.second(), members[0]) &&
                    Reflection.compareValues(secondItem.fourth(), members[1])) {
                return secondBranch.get();
            }
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, R>
    R as(Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Supplier<R> firstBranch,
         Class<C2> secondClazz, Tuple.Tuple4<String, T2, String, T3> secondItem, Supplier<R> secondBranch) {
        return match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3>
    void match(V value,
               Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, Runnable firstBranch,
               Class<C2> secondClazz, Tuple.Tuple2<String, T3> secondItem, Runnable secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 2);

            if (Reflection.compareValues(firstItem.second(), members[0]) &&
                    Reflection.compareValues(firstItem.fourth(), members[1])) {
                firstBranch.run();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 1);

            if (Reflection.compareValues(secondItem.second(), members[0])) {
                secondBranch.run();
            }
        }
    }

    public <C1, C2, T1, T2, T3>
    void as(Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, Runnable firstBranch,
            Class<C2> secondClazz, Tuple.Tuple2<String, T3> secondItem, Runnable secondBranch) {
        match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, R>
    R match(V value,
            Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, Supplier<R> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple2<String, T3> secondItem, Supplier<R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 2);

            if (Reflection.compareValues(firstItem.second(), members[0]) &&
                    Reflection.compareValues(firstItem.fourth(), members[1])) {
                return firstBranch.get();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 1);

            if (Reflection.compareValues(secondItem.second(), members[0])) {
                return secondBranch.get();
            }
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, R>
    R as(Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, Supplier<R> firstBranch,
         Class<C2> secondClazz, Tuple.Tuple2<String, T3> secondItem, Supplier<R> secondBranch) {
        return match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4>
    void match(V value,
               Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, Runnable firstBranch,
               Class<C2> secondClazz, Tuple.Tuple4<String, T3, String, T4> secondItem, Runnable secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 2);

            if (Reflection.compareValues(firstItem.second(), members[0]) &&
                    Reflection.compareValues(firstItem.fourth(), members[1])) {
                firstBranch.run();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 2);

            if (Reflection.compareValues(secondItem.second(), members[0]) &&
                    Reflection.compareValues(secondItem.fourth(), members[1])) {
                secondBranch.run();
            }
        }
    }

    public <C1, C2, T1, T2, T3, T4>
    void as(Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, Runnable firstBranch,
            Class<C2> secondClazz, Tuple.Tuple4<String, T3, String, T4> secondItem, Runnable secondBranch) {
        match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, R>
    R match(V value,
            Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, Supplier<R> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple4<String, T3, String, T4> secondItem, Supplier<R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 2);

            if (Reflection.compareValues(firstItem.second(), members[0]) &&
                    Reflection.compareValues(firstItem.fourth(), members[1])) {
                return firstBranch.get();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 2);

            if (Reflection.compareValues(secondItem.second(), members[0]) &&
                    Reflection.compareValues(secondItem.fourth(), members[1])) {
                return secondBranch.get();
            }
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, R>
    R as(Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, Supplier<R> firstBranch,
         Class<C2> secondClazz, Tuple.Tuple4<String, T3, String, T4> secondItem, Supplier<R> secondBranch) {
        return match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4>
    void match(V value,
               Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Runnable firstBranch,
               Class<C2> secondClazz, Tuple.Tuple6<String, T2, String, T3, String, T4> secondItem, Runnable secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 1);

            if (Reflection.compareValues(firstItem.second(), members[0])) {
                firstBranch.run();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 3);

            if (Reflection.compareValues(secondItem.second(), members[0]) &&
                    Reflection.compareValues(secondItem.fourth(), members[1]) &&
                    Reflection.compareValues(secondItem.sixth(), members[2])) {
                secondBranch.run();
            }
        }
    }

    public <C1, C2, T1, T2, T3, T4>
    void as(Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Runnable firstBranch,
            Class<C2> secondClazz, Tuple.Tuple6<String, T2, String, T3, String, T4> secondItem, Runnable secondBranch) {
        match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }


    public static <V, C1, C2, T1, T2, T3, T4, R>
    R match(V value,
            Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Supplier<R> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple6<String, T2, String, T3, String, T4> secondItem, Supplier<R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 1);

            if (Reflection.compareValues(firstItem.second(), members[0])) {
                return firstBranch.get();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 3);

            if (Reflection.compareValues(secondItem.second(), members[0]) &&
                    Reflection.compareValues(secondItem.fourth(), members[1]) &&
                    Reflection.compareValues(secondItem.sixth(), members[2])) {
                return secondBranch.get();
            }
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, R>
    R as(Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Supplier<R> firstBranch,
         Class<C2> secondClazz, Tuple.Tuple6<String, T2, String, T3, String, T4> secondItem, Supplier<R> secondBranch) {
        return match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4>
    void match(V value,
               Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem, Runnable firstBranch,
               Class<C2> secondClazz, Tuple.Tuple2<String, T4> secondItem, Runnable secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 3);

            if (Reflection.compareValues(firstItem.second(), members[0]) &&
                    Reflection.compareValues(firstItem.fourth(), members[1]) &&
                    Reflection.compareValues(firstItem.sixth(), members[2])) {
                firstBranch.run();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 1);

            if (Reflection.compareValues(secondItem.second(), members[0])) {
                secondBranch.run();
            }
        }
    }

    public <C1, C2, T1, T2, T3, T4>
    void as(Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem, Runnable firstBranch,
            Class<C2> secondClazz, Tuple.Tuple2<String, T4> secondItem, Runnable secondBranch) {
        match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, R>
    R match(V value,
            Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem, Supplier<R> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple2<String, T4> secondItem, Supplier<R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 3);

            if (Reflection.compareValues(firstItem.second(), members[0]) &&
                    Reflection.compareValues(firstItem.fourth(), members[1]) &&
                    Reflection.compareValues(firstItem.sixth(), members[2])) {
                return firstBranch.get();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 1);

            if (Reflection.compareValues(secondItem.second(), members[0])) {
                return secondBranch.get();
            }
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, R>
    R as(Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem, Supplier<R> firstBranch,
         Class<C2> secondClazz, Tuple.Tuple2<String, T4> secondItem, Supplier<R> secondBranch) {
        return match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6>
    void match(V value,
               Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem, Runnable firstBranch,
               Class<C2> secondClazz, Tuple.Tuple6<String, T4, String, T5, String, T6> secondItem, Runnable secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 3);

            if (Reflection.compareValues(firstItem.second(), members[0]) &&
                    Reflection.compareValues(firstItem.fourth(), members[1]) &&
                    Reflection.compareValues(firstItem.sixth(), members[2])) {
                firstBranch.run();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 3);

            if (Reflection.compareValues(secondItem.second(), members[0]) &&
                    Reflection.compareValues(secondItem.fourth(), members[1]) &&
                    Reflection.compareValues(secondItem.sixth(), members[2])) {
                secondBranch.run();
            }
        }
    }

    public <C1, C2, T1, T2, T3, T4, T5, T6>
    void as(Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem, Runnable firstBranch,
            Class<C2> secondClazz, Tuple.Tuple6<String, T4, String, T5, String, T6> secondItem, Runnable secondBranch) {
        match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6, R>
    R match(V value,
            Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem, Supplier<R> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple6<String, T4, String, T5, String, T6> secondItem, Supplier<R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 3);

            if (Reflection.compareValues(firstItem.second(), members[0]) &&
                    Reflection.compareValues(firstItem.fourth(), members[1]) &&
                    Reflection.compareValues(firstItem.sixth(), members[2])) {
                return firstBranch.get();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 3);

            if (Reflection.compareValues(secondItem.second(), members[0]) &&
                    Reflection.compareValues(secondItem.fourth(), members[1]) &&
                    Reflection.compareValues(secondItem.sixth(), members[2])) {
                return secondBranch.get();
            }
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, T5, T6, R>
    R as(Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem, Supplier<R> firstBranch,
         Class<C2> secondClazz, Tuple.Tuple6<String, T4, String, T5, String, T6> secondItem, Supplier<R> secondBranch) {
        return match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5>
    void match(V value,
               Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, Runnable firstBranch,
               Class<C2> secondClazz, Tuple.Tuple6<String, T3, String, T4, String, T5> secondItem, Runnable secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 2);

            if (Reflection.compareValues(firstItem.second(), members[0]) &&
                    Reflection.compareValues(firstItem.fourth(), members[1])) {
                firstBranch.run();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 3);

            if (Reflection.compareValues(secondItem.second(), members[0]) &&
                    Reflection.compareValues(secondItem.fourth(), members[1]) &&
                    Reflection.compareValues(secondItem.sixth(), members[2])) {
                secondBranch.run();
            }
        }
    }

    public <C1, C2, T1, T2, T3, T4, T5>
    void as(Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, Runnable firstBranch,
            Class<C2> secondClazz, Tuple.Tuple6<String, T3, String, T4, String, T5> secondItem, Runnable secondBranch) {
        match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, R>
    R match(V value,
            Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, Supplier<R> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple6<String, T3, String, T4, String, T5> secondItem, Supplier<R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 2);

            if (Reflection.compareValues(firstItem.second(), members[0]) &&
                    Reflection.compareValues(firstItem.fourth(), members[1])) {
                return firstBranch.get();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 3);

            if (Reflection.compareValues(secondItem.second(), members[0]) &&
                    Reflection.compareValues(secondItem.fourth(), members[1]) &&
                    Reflection.compareValues(secondItem.sixth(), members[2])) {
                return secondBranch.get();
            }
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, T5, R>
    R as(Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, Supplier<R> firstBranch,
         Class<C2> secondClazz, Tuple.Tuple6<String, T3, String, T4, String, T5> secondItem, Supplier<R> secondBranch) {
        return match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5>
    void match(V value,
               Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem, Runnable firstBranch,
               Class<C2> secondClazz, Tuple.Tuple4<String, T4, String, T5> secondItem, Runnable secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 3);

            if (Reflection.compareValues(firstItem.second(), members[0]) &&
                    Reflection.compareValues(firstItem.fourth(), members[1]) &&
                    Reflection.compareValues(firstItem.sixth(), members[2])) {
                firstBranch.run();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 2);

            if (Reflection.compareValues(secondItem.second(), members[0]) &&
                    Reflection.compareValues(secondItem.fourth(), members[1])) {
                secondBranch.run();
            }
        }
    }

    public <C1, C2, T1, T2, T3, T4, T5>
    void as(Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem, Runnable firstBranch,
            Class<C2> secondClazz, Tuple.Tuple4<String, T4, String, T5> secondItem, Runnable secondBranch) {
        match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, R>
    R match(V value,
            Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem, Supplier<R> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple4<String, T4, String, T5> secondItem, Supplier<R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 3);

            if (Reflection.compareValues(firstItem.second(), members[0]) &&
                    Reflection.compareValues(firstItem.fourth(), members[1]) &&
                    Reflection.compareValues(firstItem.sixth(), members[2])) {
                return firstBranch.get();
            }
        } else if (secondClazz == value.getClass()) {
            Object[] members = Reflection.fetchFields(value, 2);

            if (Reflection.compareValues(secondItem.second(), members[0]) &&
                    Reflection.compareValues(secondItem.fourth(), members[1])) {
                return secondBranch.get();
            }
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, T5, R>
    R as(Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem, Supplier<R> firstBranch,
         Class<C2> secondClazz, Tuple.Tuple4<String, T4, String, T5> secondItem, Supplier<R> secondBranch) {
        return match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }
}
