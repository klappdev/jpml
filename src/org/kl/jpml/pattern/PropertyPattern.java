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
import org.kl.jpml.lambda.TriConsumer;
import org.kl.jpml.lambda.TriFunction;
import org.kl.jpml.reflect.Reflection;
import org.kl.jpml.util.Tuple;

import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Property pattern allow match type and access to fields class.
 * Maximum number of branches for match three with three
 * value params.
 */
public final class PropertyPattern {
    private final Object value;

    private <V> PropertyPattern(V value) {
        this.value = value;
    }

    public static <V> PropertyPattern match(V value) {
        return new PropertyPattern(value);
    }

    public static <V, T>
    void foreach(Collection<V> data, Tuple.Tuple2<String, T> item, Consumer<T> consumer) {
        for (V value : data) {
            Object[] args = Reflection.fetchFields(value, 1, (String) item.first());

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
    void let(V data, Tuple.Tuple2<String, T> item, Consumer<T> consumer) {
        Object[] args = Reflection.fetchFields(data, 1, item.first());

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
    void match(V value, Class<C> clazz, Tuple.Tuple2<String, T> item, Consumer<T> branch) {
        if (clazz == value.getClass()) {
            if (executeBranch(value, item, branch)) return;
        }

        throw new PatternException("Statement must to have only one branch");
    }

    private static <V, T>
    boolean executeBranch(V value, Tuple.Tuple2<String, T> item, Consumer<T> branch) {
        Object[] args = Reflection.fetchFields(value, 1, item.first());
        Object firstArg = item.second();

        if (firstArg == null || Reflection.compareValues(firstArg, args[0])) {
            branch.accept((T) args[0]);
            return true;
        }

        return false;
    }

    public <C, T>
    void as(Class<C> clazz, Tuple.Tuple2<String, T> item, Consumer<T> branch) {
        match(value, clazz, item, branch);
    }

    public static <V, C, T, R>
    R match(V value, Class<C> clazz, Tuple.Tuple2<String, T> item, Function<T, R> branch) {
        if (clazz == value.getClass()) {
            R result = executeBranch(value, item, branch);

            if (result != null) return result;
        }

        throw new PatternException("Expression must to have only one branch");
    }

    public <C, T, R>
    R as(Class<C> clazz, Tuple.Tuple2<String, T> item, Function<T, R> branch) {
        return match(value, clazz, item, branch);
    }

    private static <V, T, R>
    R executeBranch(V value, Tuple.Tuple2<String, T> item, Function<T, R> branch) {
        Object[] args = Reflection.fetchFields(value, 1, item.first());
        Object firstArg = item.second();

        if (firstArg == null || Reflection.compareValues(firstArg, args[0])) {
            return branch.apply((T) args[0]);
        }

        return null;
    }

    public static <V, U, C, T>
    void match(V value, Class<C> clazz, Function<U, T> function, Consumer<T> branch) {
        if (clazz == value.getClass()) {
            executeBranch(value, function, branch);
            return;
        }

        throw new PatternException("Statement must to have only one branch");
    }

    private static <V, U, T>
    void executeBranch(V value, Function<U, T> function, Consumer<T> branch) {
        T arg = function.apply((U) value);
        branch.accept(arg);
    }

    public <U, C, T>
    void as(Class<C> clazz, Function<U, T> function, Consumer<T> branch) {
        match(value, clazz, function, branch);
    }

    public static <V, U, C, T, R>
    R match(V value, Class<C> clazz, Function<U, T> function, Function<T, R> branch) {
        if (clazz == value.getClass()) {
            return executeBranch(value, function, branch);
        }

        throw new PatternException("Statement must to have only one branch");
    }

    public <U, C, T, R>
    R as(Class<C> clazz, Function<U, T> function, Function<T, R> branch) {
        return match(value, clazz, function, branch);
    }

    private static <V, U, T, R>
    R executeBranch(V value, Function<U, T> function, Function<T, R> branch) {
        T arg = function.apply((U) value);
        return branch.apply(arg);
    }

    public static <V, T1, T2>
    void foreach(Collection<V> data, Tuple.Tuple4<String, T1, String, T2> item, BiConsumer<T1, T2> consumer) {
        for (V value : data) {
            Object[] args = Reflection.fetchFields(value, 2, item.first(), item.third());

            consumer.accept((T1) args[0], (T2) args[1]);
        }
    }

    public static <V, T1, T2>
    void foreach(Collection<V> data,
                 Function<V, T1> firstFunction, Function<V, T2> secondFunction, BiConsumer<T1, T2> consumer) {
        for (V value : data) {
            T1 firstArg = firstFunction.apply(value);
            T2 secondArg = secondFunction.apply(value);

            consumer.accept(firstArg, secondArg);
        }
    }

    public static <K, V, T1, T2>
    void foreach(Map<K, V> data, Tuple.Tuple4<String, T1, String, T2> item, BiConsumer<T1, T2> consumer) {
        for (Map.Entry<K, V> entry : data.entrySet()) {
            consumer.accept((T1) entry.getKey(), (T2) entry.getValue());
        }
    }

    public static <V, T1, T2>
    void let(V data, Tuple.Tuple4<String, T1, String, T2> item, BiConsumer<T1, T2> consumer) {
        Object[] args = Reflection.fetchFields(data, 2, item.first(), item.third());

        consumer.accept((T1) args[0], (T2) args[1]);
    }

    public static <V, T1, T2>
    void let(V data,
             Function<V, T1> firstFunction, Function<V, T2> secondFunction, BiConsumer<T1, T2> consumer) {
        T1 firstArg = firstFunction.apply(data);
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
    void match(V value, Class<C> clazz, Tuple.Tuple4<String, T1, String, T2> item, BiConsumer<T1, T2> branch) {
        if (clazz == value.getClass()) {
            if (executeBranch(value, item, branch)) return;
        }

        throw new PatternException("Statement must to have only one branch");
    }

    private static <V, T1, T2>
    boolean executeBranch(V value, Tuple.Tuple4<String, T1, String, T2> item, BiConsumer<T1, T2> branch) {
        Object[] args = Reflection.fetchFields(value, 2, item.first(), item.third());
        Object firstArg = item.second();
        Object secondArg = item.fourth();

        if ((firstArg == null && secondArg == null) ||
                (Reflection.compareValues(firstArg, args[0]) && Reflection.compareValues(secondArg, args[1]))) {
            branch.accept((T1) args[0], (T2) args[1]);
            return true;
        }

        return false;
    }

    public <C, T1, T2>
    void as(Class<C> clazz, Tuple.Tuple4<String, T1, String, T2> item, BiConsumer<T1, T2> branch) {
        match(value, clazz, item, branch);
    }

    public static <V, C, T1, T2, R>
    R match(V value, Class<C> clazz, Tuple.Tuple4<String, T1, String, T2> item, BiFunction<T1, T2, R> branch) {
        if (clazz == value.getClass()) {
            R result = executeBranch(value, item, branch);

            if (result != null) return result;
        }

        throw new PatternException("Expression must to have only one branch");
    }

    private static <V, T1, T2, R>
    R executeBranch(V value, Tuple.Tuple4<String, T1, String, T2> item, BiFunction<T1, T2, R> branch) {
        Object[] args = Reflection.fetchFields(value, 2, item.first(), item.third());
        Object firstArg = item.second();
        Object secondArg = item.fourth();

        if ((firstArg == null && secondArg == null) ||
                (Reflection.compareValues(firstArg, args[0]) && Reflection.compareValues(secondArg, args[1]))) {
            return branch.apply((T1) args[0], (T2) args[1]);
        }

        return null;
    }

    public <C, T1, T2, R>
    R as(Class<C> clazz, Tuple.Tuple4<String, T1, String, T2> item, BiFunction<T1, T2, R> branch) {
        return match(value, clazz, item, branch);
    }

    public static <V, U, C, T1, T2>
    void match(V value, Class<C> clazz, Function<U, T1> firstFunction,
               Function<U, T2> secondFunction, BiConsumer<T1, T2> branch) {
        if (clazz == value.getClass()) {
            executeBranch(value, firstFunction, secondFunction, branch);
            return;
        }

        throw new PatternException("Statement must to have only one branch");
    }

    private static <V, U, T1, T2>
    void executeBranch(V value, Function<U, T1> firstFunction,
                       Function<U, T2> secondFunction, BiConsumer<T1, T2> branch) {
        T1 firstArg = firstFunction.apply((U) value);
        T2 secondArg = secondFunction.apply((U) value);

        branch.accept(firstArg, secondArg);
    }

    public <U, C, T1, T2>
    void as(Class<C> clazz, Function<U, T1> firstFunction, Function<U, T2> secondFunction, BiConsumer<T1, T2> branch) {
        match(value, clazz, firstFunction, secondFunction, branch);
    }

    public static <V, U, C, T1, T2, R>
    R match(V value, Class<C> clazz, Function<U, T1> firstFunction, Function<U, T2> secondFunction,
            BiFunction<T1, T2, R> branch) {
        if (clazz == value.getClass()) {
            return executeBranch(value, firstFunction, secondFunction, branch);
        }

        throw new PatternException("Statement must to have only one branch");
    }

    private static <V, U, T1, T2, R>
    R executeBranch(V value, Function<U, T1> firstFunction, Function<U, T2> secondFunction,
                    BiFunction<T1, T2, R> branch) {
        T1 firstArg = firstFunction.apply((U) value);
        T2 secondArg = secondFunction.apply((U) value);

        return branch.apply(firstArg, secondArg);
    }

    public <U, C, T1, T2, R>
    R as(Class<C> clazz, Function<U, T1> firstFunction, Function<U, T2> secondFunction,
         BiFunction<T1, T2, R> branch) {
        return match(value, clazz, firstFunction, secondFunction, branch);
    }

    public static <V, T1, T2, T3>
    void foreach(Collection<V> data,
                 Tuple.Tuple6<String, T1, String, T2, String, T3> item, TriConsumer<T1, T2, T3> consumer) {
        for (V value : data) {
            Object[] args = Reflection.fetchFields(value, 3, item.first(), item.third(), item.fifth());

            consumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        }
    }

    public static <V, T1, T2, T3>
    void foreach(Collection<V> data,
                 Function<V, T1> firstFunction, Function<V, T2> secondFunction,
                 Function<V, T3> thirdFunction, TriConsumer<T1, T2, T3> consumer) {
        for (V value : data) {
            T1 firstArg = firstFunction.apply(value);
            T2 secondArg = secondFunction.apply(value);
            T3 thirdArg = thirdFunction.apply(value);

            consumer.accept(firstArg, secondArg, thirdArg);
        }
    }

    public static <V, T1, T2, T3>
    void let(V data,
             Tuple.Tuple6<String, T1, String, T2, String, T3> item, TriConsumer<T1, T2, T3> consumer) {
        Object[] args = Reflection.fetchFields(data, 3, item.first(), item.third(), item.fifth());

        consumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
    }

    public static <V, T1, T2, T3>
    void let(V data,
             Function<V, T1> firstFunction, Function<V, T2> secondFunction,
             Function<V, T3> thirdFunction, TriConsumer<T1, T2, T3> consumer) {
        T1 firstArg = firstFunction.apply(data);
        T2 secondArg = secondFunction.apply(data);
        T3 thirdArg = thirdFunction.apply(data);

        consumer.accept(firstArg, secondArg, thirdArg);
    }

    public static <T1, T2, T3>
    Tuple.Tuple6<String, T1, String, T2, String, T3> of(String firstField, String secondField, String thirdField) {
        return new Tuple.Tuple6<>(firstField, null, secondField, null, thirdField, null);
    }

    public static <T1, T2, T3>
    Tuple.Tuple6<String, T1, String, T2, String, T3> of(String firstField, T1 firstValue,
                                                        String secondField, T2 secondValue,
                                                        String thirdField, T3 thirdValue) {
        return new Tuple.Tuple6<>(firstField, firstValue, secondField, secondValue, thirdField, thirdValue);
    }

    public static <V, C, T1, T2, T3>
    void match(V value, Class<C> clazz,
               Tuple.Tuple6<String, T1, String, T2, String, T3> item, TriConsumer<T1, T2, T3> branch) {
        if (clazz == value.getClass()) {
            if (executeBranch(value, item, branch)) return;
        }

        throw new PatternException("Statement must to have only one branch");
    }

    private static <V, T1, T2, T3>
    boolean executeBranch(V value, Tuple.Tuple6<String, T1, String, T2, String, T3> item, TriConsumer<T1, T2, T3> branch) {
        Object[] args = Reflection.fetchFields(value, 3, item.first(), item.third(), item.fifth());
        Object firstArg = item.second();
        Object secondArg = item.fourth();
        Object thirdArg = item.sixth();

        if ((firstArg == null && secondArg == null && thirdArg == null) ||
                (Reflection.compareValues(firstArg, args[0]) && Reflection.compareValues(secondArg, args[1]) &&
                        Reflection.compareValues(thirdArg, args[2]))) {
            branch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return true;
        }

        return false;
    }

    public <C, T1, T2, T3>
    void as(Class<C> clazz, Tuple.Tuple6<String, T1, String, T2, String, T3> item, TriConsumer<T1, T2, T3> branch) {
        match(value, clazz, item, branch);
    }


    public static <V, C, T1, T2, T3, R>
    R match(V value, Class<C> clazz, Tuple.Tuple6<String, T1, String, T2, String, T3> item,
            TriFunction<T1, T2, T3, R> branch) {
        if (clazz == value.getClass()) {
            R result = executeBranch(value, item, branch);

            if (result != null) return result;
        }

        throw new PatternException("Expression must to have only one branch");
    }

    private static <V, T1, T2, T3, R>
    R executeBranch(V value,
                    Tuple.Tuple6<String, T1, String, T2, String, T3> item, TriFunction<T1, T2, T3, R> branch) {
        Object[] args = Reflection.fetchFields(value, 3, item.first(), item.third(), item.fifth());
        Object firstArg = item.second();
        Object secondArg = item.fourth();
        Object thirdArg = item.sixth();

        if ((firstArg == null && secondArg == null && thirdArg == null) ||
                (Reflection.compareValues(firstArg, args[0]) && Reflection.compareValues(secondArg, args[1]) &&
                        Reflection.compareValues(thirdArg, args[2]))) {
            return branch.apply((T1) args[0], (T2) args[1], (T3) args[2]);
        }

        return null;
    }

    public <C, T1, T2, T3, R>
    R as(Class<C> clazz, Tuple.Tuple6<String, T1, String, T2, String, T3> item,
         TriFunction<T1, T2, T3, R> branch) {
        return match(value, clazz, item, branch);
    }

    public static <V, U, C, T1, T2, T3>
    void match(V value, Class<C> clazz, Function<U, T1> firstFunction, Function<U, T2> secondFunction,
               Function<U, T3> thirdFunction, TriConsumer<T1, T2, T3> branch) {
        if (clazz == value.getClass()) {
            executeBranch(value, firstFunction, secondFunction, thirdFunction, branch);
            return;
        }

        throw new PatternException("Statement must to have only one branch");
    }

    private static <V, U, T1, T2, T3>
    void executeBranch(V value, Function<U, T1> firstFunction, Function<U, T2> secondFunction,
                       Function<U, T3> thirdFunction, TriConsumer<T1, T2, T3> consumer) {
        T1 firstArg = firstFunction.apply((U) value);
        T2 secondArg = secondFunction.apply((U) value);
        T3 thirdArg = thirdFunction.apply((U) value);

        consumer.accept(firstArg, secondArg, thirdArg);
    }

    public <V1, C, T1, T2, T3>
    void as(Class<C> clazz, Function<V1, T1> firstFunction, Function<V1, T2> secondFunction,
            Function<V1, T3> thirdFunction, TriConsumer<T1, T2, T3> branch) {
        match(value, clazz, firstFunction, secondFunction, thirdFunction, branch);
    }

    public static <V, U, C, T1, T2, T3, R>
    R match(V value, Class<C> clazz, Function<U, T1> firstFunction, Function<U, T2> secondFunction,
            Function<U, T3> thirdFunction, TriFunction<T1, T2, T3, R> branch) {
        if (clazz == value.getClass()) {
            return executeBranch(value, firstFunction, secondFunction, thirdFunction, branch);
        }

        throw new PatternException("Statement must to have only one branch");
    }

    private static <V, U, T1, T2, T3, R>
    R executeBranch(V value, Function<U, T1> firstFunction, Function<U, T2> secondFunction,
                    Function<U, T3> thirdFunction, TriFunction<T1, T2, T3, R> branch) {
        T1 firstArg = firstFunction.apply((U) value);
        T2 secondArg = secondFunction.apply((U) value);
        T3 thirdArg = thirdFunction.apply((U) value);

        return branch.apply(firstArg, secondArg, thirdArg);
    }

    public <U, C, T1, T2, T3, R>
    R as(Class<C> clazz, Function<U, T1> firstFunction, Function<U, T2> secondFunction,
         Function<U, T3> thirdFunction, TriFunction<T1, T2, T3, R> branch) {
        return match(value, clazz, firstFunction, secondFunction,
                thirdFunction, branch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, C1, C2, T1, T2>
    void match(V value,
               Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Consumer<T1> firstBranch,
               Class<C2> secondClazz, Tuple.Tuple2<String, T2> secondItem, Consumer<T2> secondBranch) {
        if (firstClazz == value.getClass()) {
            if (executeBranch(value, firstItem, firstBranch)) return;
        } else if (secondClazz == value.getClass()) {
            if (executeBranch(value, secondItem, secondBranch)) return;
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <C1, C2, T1, T2>
    void as(Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Consumer<T1> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple2<String, T2> secondItem, Consumer<T2> secondBranch) {
        match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, C1, C2, T1, T2, R>
    R match(V value,
            Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Function<T1, R> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple2<String, T2> secondItem, Function<T2, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            R result = executeBranch(value, firstItem, firstBranch);

            if (result != null) return result;
        } else if (secondClazz == value.getClass()) {
            R result = executeBranch(value, secondItem, secondBranch);

            if (result != null) return result;
        }

        throw new PatternException("Expression must to have only one branch");
    }

    public <C1, C2, T1, T2, R>
    R as(Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Function<T1, R> firstBranch,
         Class<C2> secondClazz, Tuple.Tuple2<String, T2> secondItem, Function<T2, R> secondBranch) {
        return match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, U1, U2, C1, C2, T1, T2>
    void match(V value,
               Class<C1> firstClazz, Function<U1, T1> firstFunction, Consumer<T1> firstBranch,
               Class<C2> secondClazz, Function<U2, T2> secondFunction, Consumer<T2> secondBranch) {
        if (firstClazz == value.getClass()) {
            executeBranch(value, firstFunction, firstBranch);
            return;
        } else if (secondClazz == value.getClass()) {
            executeBranch(value, secondFunction, secondBranch);
            return;
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <U1, U2, C1, C2, T1, T2>
    void as(Class<C1> firstClazz, Function<U1, T1> firstFunction, Consumer<T1> firstBranch,
            Class<C2> secondClazz, Function<U2, T2> secondFunction, Consumer<T2> secondBranch) {
        match(value,
                firstClazz, firstFunction, firstBranch,
                secondClazz, secondFunction, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, U1, U2, C1, C2, T1, T2, R>
    R match(V value,
            Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<T1, R> firstBranch,
            Class<C2> secondClazz, Function<U2, T2> secondFunction, Function<T2, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            return executeBranch(value, firstFunction, firstBranch);
        } else if (secondClazz == value.getClass()) {
            return executeBranch(value, secondFunction, secondBranch);
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <U1, U2, C1, C2, T1, T2, R>
    R as(Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<T1, R> firstBranch,
         Class<C2> secondClazz, Function<U2, T2> secondFunction, Function<T2, R> secondBranch) {
        return match(value,
                firstClazz, firstFunction, firstBranch,
                secondClazz, secondFunction, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, C1, C2, T1, T2, T3>
    void match(V value,
               Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Consumer<T1> firstBranch,
               Class<C2> secondClazz, Tuple.Tuple4<String, T2, String, T3> secondItem, BiConsumer<T2, T3> secondBranch) {
        if (firstClazz == value.getClass()) {
            if (executeBranch(value, firstItem, firstBranch)) return;
        } else if (secondClazz == value.getClass()) {
            if (executeBranch(value, secondItem, secondBranch)) return;
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <C1, C2, T1, T2, T3>
    void as(Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Consumer<T1> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple4<String, T2, String, T3> secondItem, BiConsumer<T2, T3> secondBranch) {
        match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, C1, C2, T1, T2, T3, R>
    R match(V value,
            Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Function<T1, R> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple4<String, T2, String, T3> secondItem, BiFunction<T2, T3, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            R result = executeBranch(value, firstItem, firstBranch);

            if (result != null) return result;
        } else if (secondClazz == value.getClass()) {
            R result = executeBranch(value, secondItem, secondBranch);

            if (result != null) return result;
        }

        throw new PatternException("Expression must to have only one branch");
    }

    public <C1, C2, T1, T2, T3, R>
    R as(Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Function<T1, R> firstBranch,
         Class<C2> secondClazz, Tuple.Tuple4<String, T2, String, T3> secondItem, BiFunction<T2, T3, R> secondBranch) {
        return match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, U1, U2, C1, C2, T1, T2, T3>
    void match(V value,
               Class<C1> firstClazz, Function<U1, T1> firstFunction, Consumer<T1> firstBranch,
               Class<C2> secondClazz, Function<U2, T2> secondFunction,
               Function<U2, T3> thirdFunction, BiConsumer<T2, T3> secondBranch) {
        if (firstClazz == value.getClass()) {
            executeBranch(value, firstFunction, firstBranch);
            return;
        } else if (secondClazz == value.getClass()) {
            executeBranch(value, secondFunction, thirdFunction, secondBranch);
            return;
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <U1, U2, C1, C2, T1, T2, T3>
    void as(Class<C1> firstClazz, Function<U1, T1> firstFunction, Consumer<T1> firstBranch,
            Class<C2> secondClazz, Function<U2, T2> secondFunction,
            Function<U2, T3> thirdFunction, BiConsumer<T2, T3> secondBranch) {
        match(value,
                firstClazz, firstFunction, firstBranch,
                secondClazz, secondFunction, thirdFunction, secondBranch);
    }

    public static <V, U1, U2, C1, C2, T1, T2, T3, R>
    R match(V value,
            Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<T1, R> firstBranch,
            Class<C2> secondClazz, Function<U2, T2> secondFunction, Function<U2, T3> thirdFunction,
            BiFunction<T2, T3, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            return executeBranch(value, firstFunction, firstBranch);
        } else if (secondClazz == value.getClass()) {
            return executeBranch(value, secondFunction, thirdFunction, secondBranch);
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <U1, U2, C1, C2, T1, T2, T3, R>
    R as(Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<T1, R> firstBranch,
         Class<C2> secondClazz, Function<U2, T2> secondFunction, Function<U2, T3> thirdFunction,
         BiFunction<T2, T3, R> secondBranch) {
        return match(value,
                firstClazz, firstFunction, firstBranch,
                secondClazz, secondFunction, thirdFunction, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, C1, C2, T1, T2, T3>
    void match(V value,
               Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, BiConsumer<T1, T2> firstBranch,
               Class<C2> secondClazz, Tuple.Tuple2<String, T3> secondItem, Consumer<T3> secondBranch) {
        if (firstClazz == value.getClass()) {
            if (executeBranch(value, firstItem, firstBranch)) return;
        } else if (secondClazz == value.getClass()) {
            if (executeBranch(value, secondItem, secondBranch)) return;
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <C1, C2, T1, T2, T3>
    void as(Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, BiConsumer<T1, T2> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple2<String, T3> secondItem, Consumer<T3> secondBranch) {
        match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, C1, C2, T1, T2, T3, R>
    R match(V value,
            Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, BiFunction<T1, T2, R> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple2<String, T3> secondItem, Function<T3, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            R result = executeBranch(value, firstItem, firstBranch);

            if (result != null) return result;
        } else if (secondClazz == value.getClass()) {
            R result = executeBranch(value, secondItem, secondBranch);

            if (result != null) return result;
        }

        throw new PatternException("Expression must to have only one branch");
    }

    public <C1, C2, T1, T2, T3, R>
    R as(Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, BiFunction<T1, T2, R> firstBranch,
         Class<C2> secondClazz, Tuple.Tuple2<String, T3> secondItem, Function<T3, R> secondBranch) {
        return match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, U1, U2, C1, C2, T1, T2, T3>
    void match(V value,
               Class<C1> firstClazz, Function<U1, T1> firstFunction,
               Function<U1, T2> secondFunction, BiConsumer<T1, T2> firstBranch,
               Class<C2> secondClazz, Function<U2, T3> thirdFunction, Consumer<T3> secondBranch) {
        if (firstClazz == value.getClass()) {
            executeBranch(value, firstFunction, secondFunction, firstBranch);
            return;
        } else if (secondClazz == value.getClass()) {
            executeBranch(value, thirdFunction, secondBranch);
            return;
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <U1, U2, C1, C2, T1, T2, T3>
    void as(Class<C1> firstClazz, Function<U1, T1> firstFunction,
            Function<U1, T2> secondFunction, BiConsumer<T1, T2> firstBranch,
            Class<C2> secondClazz, Function<U2, T3> thirdFunction, Consumer<T3> secondBranch) {
        match(value,
                firstClazz, firstFunction, secondFunction, firstBranch,
                secondClazz, thirdFunction, secondBranch);
    }

    public static <V, U1, U2, C1, C2, T1, T2, T3, R>
    R match(V value,
            Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<U1, T2> secondFunction,
            BiFunction<T1, T2, R> firstBranch,
            Class<C2> secondClazz, Function<U2, T3> thirdFunction, Function<T3, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            return executeBranch(value, firstFunction, secondFunction, firstBranch);
        } else if (secondClazz == value.getClass()) {
            return executeBranch(value, thirdFunction, secondBranch);
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <U1, U2, C1, C2, T1, T2, T3, R>
    R as(Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<U1, T2> secondFunction,
         BiFunction<T1, T2, R> firstBranch,
         Class<C2> secondClazz, Function<U2, T3> thirdFunction, Function<T3, R> secondBranch) {
        return match(value,
                firstClazz, firstFunction, secondFunction, firstBranch,
                secondClazz, thirdFunction, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, C1, C2, T1, T2, T3, T4>
    void match(V value,
               Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, BiConsumer<T1, T2> firstBranch,
               Class<C2> secondClazz, Tuple.Tuple4<String, T3, String, T4> secondItem, BiConsumer<T3, T4> secondBranch) {
        if (firstClazz == value.getClass()) {
            if (executeBranch(value, firstItem, firstBranch)) return;
        } else if (secondClazz == value.getClass()) {
            if (executeBranch(value, secondItem, secondBranch)) return;
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4>
    void as(Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, BiConsumer<T1, T2> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple4<String, T3, String, T4> secondItem, BiConsumer<T3, T4> secondBranch) {
        match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, C1, C2, T1, T2, T3, T4, R>
    R match(V value,
            Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, BiFunction<T1, T2, R> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple4<String, T3, String, T4> secondItem, BiFunction<T3, T4, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            R result = executeBranch(value, firstItem, firstBranch);

            if (result != null) return result;
        } else if (secondClazz == value.getClass()) {
            R result = executeBranch(value, secondItem, secondBranch);

            if (result != null) return result;
        }

        throw new PatternException("Expression must to have only one branch");
    }

    public <C1, C2, T1, T2, T3, T4, R>
    R as(Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, BiFunction<T1, T2, R> firstBranch,
         Class<C2> secondClazz, Tuple.Tuple4<String, T3, String, T4> secondItem, BiFunction<T3, T4, R> secondBranch) {
        return match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, U1, U2, C1, C2, T1, T2, T3, T4>
    void match(V value,
               Class<C1> firstClazz, Function<U1, T1> firstFunction,
               Function<U1, T2> secondFunction, BiConsumer<T1, T2> firstBranch,
               Class<C2> secondClazz, Function<U2, T3> thirdFunction,
               Function<U2, T4> fourthFunction, BiConsumer<T3, T4> secondBranch) {
        if (firstClazz == value.getClass()) {
            executeBranch(value, firstFunction, secondFunction, firstBranch);
            return;
        } else if (secondClazz == value.getClass()) {
            executeBranch(value, thirdFunction, fourthFunction, secondBranch);
            return;
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <U1, U2, C1, C2, T1, T2, T3, T4>
    void as(Class<C1> firstClazz, Function<U1, T1> firstFunction,
            Function<U1, T2> secondFunction, BiConsumer<T1, T2> firstBranch,
            Class<C2> secondClazz, Function<U2, T3> thirdFunction,
            Function<U2, T4> fourthFunction, BiConsumer<T3, T4> secondBranch) {
        match(value,
                firstClazz, firstFunction, secondFunction, firstBranch,
                secondClazz, thirdFunction, fourthFunction, secondBranch);
    }

    public static <V, U1, U2, C1, C2, T1, T2, T3, T4, R>
    R match(V value,
            Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<U1, T2> secondFunction,
            BiFunction<T1, T2, R> firstBranch,
            Class<C2> secondClazz, Function<U2, T3> thirdFunction, Function<U2, T4> fourthFunction,
            BiFunction<T3, T4, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            return executeBranch(value, firstFunction, secondFunction, firstBranch);
        } else if (secondClazz == value.getClass()) {
            return executeBranch(value, thirdFunction, fourthFunction, secondBranch);
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <U1, U2, C1, C2, T1, T2, T3, T4, R>
    R as(Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<U1, T2> secondFunction,
         BiFunction<T1, T2, R> firstBranch,
         Class<C2> secondClazz, Function<U2, T3> thirdFunction, Function<U2, T4> fourthFunction,
         BiFunction<T3, T4, R> secondBranch) {
        return match(value,
                firstClazz, firstFunction, secondFunction, firstBranch,
                secondClazz, thirdFunction, fourthFunction, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, C1, C2, T1, T2, T3, T4>
    void match(V value,
               Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Consumer<T1> firstBranch,
               Class<C2> secondClazz, Tuple.Tuple6<String, T2, String, T3, String, T4> secondItem,
               TriConsumer<T2, T3, T4> secondBranch) {
        if (firstClazz == value.getClass()) {
            if (executeBranch(value, firstItem, firstBranch)) return;
        } else if (secondClazz == value.getClass()) {
            if (executeBranch(value, secondItem, secondBranch)) return;
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4>
    void as(Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Consumer<T1> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple6<String, T2, String, T3, String, T4> secondItem,
            TriConsumer<T2, T3, T4> secondBranch) {
        match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, C1, C2, T1, T2, T3, T4, R>
    R match(V value,
            Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Function<T1, R> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple6<String, T2, String, T3, String, T4> secondItem,
            TriFunction<T2, T3, T4, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            R result = executeBranch(value, firstItem, firstBranch);

            if (result != null) return result;
        } else if (secondClazz == value.getClass()) {
            R result = executeBranch(value, secondItem, secondBranch);

            if (result != null) return result;
        }

        throw new PatternException("Expression must to have only one branch");
    }

    public <C1, C2, T1, T2, T3, T4, R>
    R as(Class<C1> firstClazz, Tuple.Tuple2<String, T1> firstItem, Function<T1, R> firstBranch,
         Class<C2> secondClazz, Tuple.Tuple6<String, T2, String, T3, String, T4> secondItem,
         TriFunction<T2, T3, T4, R> secondBranch) {
        return match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, U1, U2, C1, C2, T1, T2, T3, T4>
    void match(V value,
               Class<C1> firstClazz, Function<U1, T1> firstFunction, Consumer<T1> firstBranch,
               Class<C2> secondClazz, Function<U2, T2> secondFunction, Function<U2, T3> thirdFunction,
               Function<U2, T4> fourthFunction, TriConsumer<T2, T3, T4> secondBranch) {
        if (firstClazz == value.getClass()) {
            executeBranch(value, firstFunction, firstBranch);
            return;
        } else if (secondClazz == value.getClass()) {
            executeBranch(value, secondFunction, thirdFunction, fourthFunction, secondBranch);
            return;
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <U1, U2, C1, C2, T1, T2, T3, T4>
    void as(Class<C1> firstClazz, Function<U1, T1> firstFunction, Consumer<T1> firstBranch,
            Class<C2> secondClazz, Function<U2, T2> secondFunction, Function<U2, T3> thirdFunction,
            Function<U2, T4> fourthFunction, TriConsumer<T2, T3, T4> secondBranch) {
        match(value,
                firstClazz, firstFunction, firstBranch,
                secondClazz, secondFunction, thirdFunction, fourthFunction, secondBranch);
    }

    public static <V, U1, U2, C1, C2, T1, T2, T3, T4, R>
    R match(V value,
            Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<T1, R> firstBranch,
            Class<C2> secondClazz, Function<U2, T2> secondFunction, Function<U2, T3> thirdFunction,
            Function<U2, T4> fourthFunction, TriFunction<T2, T3, T4, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            return executeBranch(value, firstFunction, firstBranch);
        } else if (secondClazz == value.getClass()) {
            return executeBranch(value, secondFunction, thirdFunction, fourthFunction, secondBranch);
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <U1, U2, C1, C2, T1, T2, T3, T4, R>
    R as(Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<T1, R> firstBranch,
         Class<C2> secondClazz, Function<U2, T2> secondFunction, Function<U2, T3> thirdFunction,
         Function<U2, T4> fourthFunction, TriFunction<T2, T3, T4, R> secondBranch) {
        return match(value,
                firstClazz, firstFunction, firstBranch,
                secondClazz, secondFunction, thirdFunction, fourthFunction, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, C1, C2, T1, T2, T3, T4>
    void match(V value,
               Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem,
               TriConsumer<T1, T2, T3> firstBranch,
               Class<C2> secondClazz, Tuple.Tuple2<String, T4> secondItem, Consumer<T4> secondBranch) {
        if (firstClazz == value.getClass()) {
            if (executeBranch(value, firstItem, firstBranch)) return;
        } else if (secondClazz == value.getClass()) {
            if (executeBranch(value, secondItem, secondBranch)) return;
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4>
    void as(Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem,
            TriConsumer<T1, T2, T3> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple2<String, T4> secondItem, Consumer<T4> secondBranch) {
        match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, C1, C2, T1, T2, T3, T4, R>
    R match(V value,
            Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem,
            TriFunction<T1, T2, T3, R> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple2<String, T4> secondItem, Function<T4, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            R result = executeBranch(value, firstItem, firstBranch);

            if (result != null) return result;
        } else if (secondClazz == value.getClass()) {
            R result = executeBranch(value, secondItem, secondBranch);

            if (result != null) return result;
        }

        throw new PatternException("Expression must to have only one branch");
    }

    public <C1, C2, T1, T2, T3, T4, R>
    R as(Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem,
         TriFunction<T1, T2, T3, R> firstBranch,
         Class<C2> secondClazz, Tuple.Tuple2<String, T4> secondItem, Function<T4, R> secondBranch) {
        return match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, U1, U2, C1, C2, T1, T2, T3, T4>
    void match(V value,
               Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<U1, T2> secondFunction,
               Function<U1, T3> thirdFunction, TriConsumer<T1, T2, T3> firstBranch,
               Class<C2> secondClazz, Function<U2, T4> fourthFunction, Consumer<T4> secondBranch) {
        if (firstClazz == value.getClass()) {
            executeBranch(value, firstFunction, secondFunction, thirdFunction, firstBranch);
            return;
        } else if (secondClazz == value.getClass()) {
            executeBranch(value, fourthFunction, secondBranch);
            return;
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <U1, U2, C1, C2, T1, T2, T3, T4>
    void as(Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<U1, T2> secondFunction,
            Function<U1, T3> thirdFunction, TriConsumer<T1, T2, T3> firstBranch,
            Class<C2> secondClazz, Function<U2, T4> fourthFunction, Consumer<T4> secondBranch) {
        match(value,
                firstClazz, firstFunction, secondFunction, thirdFunction, firstBranch,
                secondClazz, fourthFunction, secondBranch);
    }

    public static <V, U1, U2, C1, C2, T1, T2, T3, T4, R>
    R match(V value,
            Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<U1, T2> secondFunction,
            Function<U1, T3> thirdFunction, TriFunction<T1, T2, T3, R> firstBranch,
            Class<C2> secondClazz, Function<U2, T4> fourthFunction,
            Function<T4, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            return executeBranch(value, firstFunction, secondFunction, thirdFunction, firstBranch);
        } else if (secondClazz == value.getClass()) {
            return executeBranch(value, fourthFunction, secondBranch);
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <U1, U2, C1, C2, T1, T2, T3, T4, R>
    R as(Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<U1, T2> secondFunction,
         Function<U1, T3> thirdFunction, TriFunction<T1, T2, T3, R> firstBranch,
         Class<C2> secondClazz, Function<U2, T4> fourthFunction,
         Function<T4, R> secondBranch) {
        return match(value,
                firstClazz, firstFunction, secondFunction, thirdFunction, firstBranch,
                secondClazz, fourthFunction, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, C1, C2, T1, T2, T3, T4, T5, T6>
    void match(V value,
               Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem,
               TriConsumer<T1, T2, T3> firstBranch,
               Class<C2> secondClazz, Tuple.Tuple6<String, T4, String, T5, String, T6> secondItem,
               TriConsumer<T4, T5, T6> secondBranch) {
        if (firstClazz == value.getClass()) {
            if (executeBranch(value, firstItem, firstBranch)) return;
        } else if (secondClazz == value.getClass()) {
            if (executeBranch(value, secondItem, secondBranch)) return;
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, T5, T6>
    void as(Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem,
            TriConsumer<T1, T2, T3> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple6<String, T4, String, T5, String, T6> secondItem,
            TriConsumer<T4, T5, T6> secondBranch) {
        match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, C1, C2, T1, T2, T3, T4, T5, T6, R>
    R match(V value,
            Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem,
            TriFunction<T1, T2, T3, R> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple6<String, T4, String, T5, String, T6> secondItem,
            TriFunction<T4, T5, T6, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            R result = executeBranch(value, firstItem, firstBranch);

            if (result != null) return result;
        } else if (secondClazz == value.getClass()) {
            R result = executeBranch(value, secondItem, secondBranch);

            if (result != null) return result;
        }

        throw new PatternException("Expression must to have only one branch");
    }

    public <C1, C2, T1, T2, T3, T4, T5, T6, R>
    R as(Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem,
         TriFunction<T1, T2, T3, R> firstBranch,
         Class<C2> secondClazz, Tuple.Tuple6<String, T4, String, T5, String, T6> secondItem,
         TriFunction<T4, T5, T6, R> secondBranch) {
        return match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, U1, U2, C1, C2, T1, T2, T3, T4, T5, T6>
    void match(V value,
               Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<U1, T2> secondFunction,
               Function<U1, T3> thirdFunction, TriConsumer<T1, T2, T3> firstBranch,
               Class<C2> secondClazz, Function<U2, T4> fourthFunction, Function<U2, T5> fifthFunction,
               Function<U2, T6> sixthFunction, TriConsumer<T4, T5, T6> secondBranch) {
        if (firstClazz == value.getClass()) {
            executeBranch(value, firstFunction, secondFunction, thirdFunction, firstBranch);
            return;
        } else if (secondClazz == value.getClass()) {
            executeBranch(value, fourthFunction, fifthFunction, sixthFunction, secondBranch);
            return;
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <U1, U2, C1, C2, T1, T2, T3, T4, T5, T6>
    void as(Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<U1, T2> secondFunction,
            Function<U1, T3> thirdFunction, TriConsumer<T1, T2, T3> firstBranch,
            Class<C2> secondClazz, Function<U2, T4> fourthFunction, Function<U2, T5> fifthFunction,
            Function<U2, T6> sixthFunction, TriConsumer<T4, T5, T6> secondBranch) {
        match(value,
                firstClazz, firstFunction, secondFunction, thirdFunction, firstBranch,
                secondClazz, fourthFunction, fifthFunction, sixthFunction, secondBranch);
    }

    public static <V, U1, U2, C1, C2, T1, T2, T3, T4, T5, T6, R>
    R match(V value,
            Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<U1, T2> secondFunction,
            Function<U1, T3> thirdFunction, TriFunction<T1, T2, T3, R> firstBranch,
            Class<C2> secondClazz, Function<U2, T4> fourthFunction, Function<U2, T5> fifthFunction,
            Function<U2, T6> sixthFunction, TriFunction<T4, T5, T6, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            return executeBranch(value, firstFunction, secondFunction, thirdFunction, firstBranch);
        } else if (secondClazz == value.getClass()) {
            return executeBranch(value, fourthFunction, fifthFunction, sixthFunction, secondBranch);
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <U1, U2, C1, C2, T1, T2, T3, T4, T5, T6, R>
    R as(Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<U1, T2> secondFunction,
         Function<U1, T3> thirdFunction, TriFunction<T1, T2, T3, R> firstBranch,
         Class<C2> secondClazz, Function<U2, T4> fourthFunction, Function<U2, T5> fifthFunction,
         Function<U2, T6> sixthFunction, TriFunction<T4, T5, T6, R> secondBranch) {
        return match(value,
                firstClazz, firstFunction, secondFunction, thirdFunction, firstBranch,
                secondClazz, fourthFunction, fifthFunction, sixthFunction, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, C1, C2, T1, T2, T3, T4, T5>
    void match(V value,
               Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, BiConsumer<T1, T2> firstBranch,
               Class<C2> secondClazz, Tuple.Tuple6<String, T3, String, T4, String, T5> secondItem,
               TriConsumer<T3, T4, T5> secondBranch) {
        if (firstClazz == value.getClass()) {
            if (executeBranch(value, firstItem, firstBranch)) return;
        } else if (secondClazz == value.getClass()) {
            if (executeBranch(value, secondItem, secondBranch)) return;
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, T5>
    void as(Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem, BiConsumer<T1, T2> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple6<String, T3, String, T4, String, T5> secondItem,
            TriConsumer<T3, T4, T5> secondBranch) {
        match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, C1, C2, T1, T2, T3, T4, T5, R>
    R match(V value,
            Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem,
            BiFunction<T1, T2, R> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple6<String, T3, String, T4, String, T5> secondItem,
            TriFunction<T3, T4, T5, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            R result = executeBranch(value, firstItem, firstBranch);

            if (result != null) return result;
        } else if (secondClazz == value.getClass()) {
            R result = executeBranch(value, secondItem, secondBranch);

            if (result != null) return result;
        }

        throw new PatternException("Expression must to have only one branch");
    }

    public <C1, C2, T1, T2, T3, T4, T5, T6, R>
    R as(Class<C1> firstClazz, Tuple.Tuple4<String, T1, String, T2> firstItem,
         BiFunction<T1, T2, R> firstBranch,
         Class<C2> secondClazz, Tuple.Tuple6<String, T3, String, T4, String, T5> secondItem,
         TriFunction<T3, T4, T5, R> secondBranch) {
        return match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, U1, U2, C1, C2, T1, T2, T3, T4, T5>
    void match(V value,
               Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<U1, T2> secondFunction,
               BiConsumer<T1, T2> firstBranch,
               Class<C2> secondClazz, Function<U2, T3> thirdFunction, Function<U2, T4> fourthFunction,
               Function<U2, T5> fifthFunction, TriConsumer<T3, T4, T5> secondBranch) {
        if (firstClazz == value.getClass()) {
            executeBranch(value, firstFunction, secondFunction, firstBranch);
            return;
        } else if (secondClazz == value.getClass()) {
            executeBranch(value, thirdFunction, fourthFunction, fifthFunction, secondBranch);
            return;
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <U1, U2, C1, C2, T1, T2, T3, T4, T5>
    void as(Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<U1, T2> secondFunction,
            BiConsumer<T1, T2> firstBranch,
            Class<C2> secondClazz, Function<U2, T3> thirdFunction, Function<U2, T4> fourthFunction,
            Function<U2, T5> fifthFunction, TriConsumer<T3, T4, T5> secondBranch) {
        match(value,
                firstClazz, firstFunction, secondFunction, firstBranch,
                secondClazz, thirdFunction, fourthFunction, fifthFunction, secondBranch);
    }

    public static <V, U1, U2, C1, C2, T1, T2, T3, T4, T5, T6, R>
    R match(V value,
            Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<U1, T2> secondFunction,
            BiFunction<T1, T2, R> firstBranch,
            Class<C2> secondClazz, Function<U2, T3> thirdFunction, Function<U2, T4> fourthFunction,
            Function<U2, T5> fifthFunction, TriFunction<T3, T4, T5, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            return executeBranch(value, firstFunction, secondFunction, firstBranch);
        } else if (secondClazz == value.getClass()) {
            return executeBranch(value, thirdFunction, fourthFunction, fifthFunction, secondBranch);
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <U1, U2, C1, C2, T1, T2, T3, T4, T5, R>
    R as(Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<U1, T2> secondFunction,
         BiFunction<T1, T2, R> firstBranch,
         Class<C2> secondClazz, Function<U2, T3> thirdFunction, Function<U2, T4> fourthFunction,
         Function<U2, T5> fifthFunction, TriFunction<T3, T4, T5, R> secondBranch) {
        return match(value,
                firstClazz, firstFunction, secondFunction, firstBranch,
                secondClazz, thirdFunction, fourthFunction, fifthFunction, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, C1, C2, T1, T2, T3, T4, T5>
    void match(V value,
               Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem,
               TriConsumer<T1, T2, T3> firstBranch,
               Class<C2> secondClazz, Tuple.Tuple4<String, T4, String, T5> secondItem,
               BiConsumer<T4, T5> secondBranch) {
        if (firstClazz == value.getClass()) {
            if (executeBranch(value, firstItem, firstBranch)) return;
        } else if (secondClazz == value.getClass()) {
            if (executeBranch(value, secondItem, secondBranch)) return;
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, T5>
    void as(Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem,
            TriConsumer<T1, T2, T3> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple4<String, T4, String, T5> secondItem,
            BiConsumer<T4, T5> secondBranch) {
        match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V, C1, C2, T1, T2, T3, T4, T5, R>
    R match(V value,
            Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem,
            TriFunction<T1, T2, T3, R> firstBranch,
            Class<C2> secondClazz, Tuple.Tuple4<String, T4, String, T5> secondItem,
            BiFunction<T4, T5, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            R result = executeBranch(value, firstItem, firstBranch);

            if (result != null) return result;
        } else if (secondClazz == value.getClass()) {
            R result = executeBranch(value, secondItem, secondBranch);

            if (result != null) return result;
        }

        throw new PatternException("Expression must to have only one branch");
    }

    public <C1, C2, T1, T2, T3, T4, T5, T6, R>
    R as(Class<C1> firstClazz, Tuple.Tuple6<String, T1, String, T2, String, T3> firstItem,
         TriFunction<T1, T2, T3, R> firstBranch,
         Class<C2> secondClazz, Tuple.Tuple4<String, T4, String, T5> secondItem,
         BiFunction<T4, T5, R> secondBranch) {
        return match(value,
                firstClazz, firstItem, firstBranch,
                secondClazz, secondItem, secondBranch);
    }

    public static <V, U1, U2, C1, C2, T1, T2, T3, T4, T5>
    void match(V value,
               Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<U1, T2> secondFunction,
               Function<U1, T3> thirdFunction, TriConsumer<T1, T2, T3> firstBranch,
               Class<C2> secondClazz, Function<U2, T4> fourthFunction, Function<U2, T5> fifthFunction,
               BiConsumer<T4, T5> secondBranch) {
        if (firstClazz == value.getClass()) {
            executeBranch(value, firstFunction, secondFunction, thirdFunction, firstBranch);
            return;
        } else if (secondClazz == value.getClass()) {
            executeBranch(value, fourthFunction, fifthFunction, secondBranch);
            return;
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <U1, U2, C1, C2, T1, T2, T3, T4, T5, T6>
    void as(Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<U1, T2> secondFunction,
            Function<U1, T3> thirdFunction, TriConsumer<T1, T2, T3> firstBranch,
            Class<C2> secondClazz, Function<U2, T4> fourthFunction, Function<U2, T5> fifthFunction,
            BiConsumer<T4, T5> secondBranch) {
        match(value,
                firstClazz, firstFunction, secondFunction, thirdFunction, firstBranch,
                secondClazz, fourthFunction, fifthFunction, secondBranch);
    }

    public static <V, U1, U2, C1, C2, T1, T2, T3, T4, T5, T6, R>
    R match(V value,
            Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<U1, T2> secondFunction,
            Function<U1, T3> thirdFunction, TriFunction<T1, T2, T3, R> firstBranch,
            Class<C2> secondClazz, Function<U2, T4> fourthFunction, Function<U2, T5> fifthFunction,
            BiFunction<T4, T5, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            return executeBranch(value, firstFunction, secondFunction, thirdFunction, firstBranch);
        } else if (secondClazz == value.getClass()) {
            return executeBranch(value, fourthFunction, fifthFunction, secondBranch);
        }

        throw new PatternException("Statement must to have only two branches");
    }

    public <U1, U2, C1, C2, T1, T2, T3, T4, T5, R>
    R as(Class<C1> firstClazz, Function<U1, T1> firstFunction, Function<U1, T2> secondFunction,
         Function<U1, T3> thirdFunction, TriFunction<T1, T2, T3, R> firstBranch,
         Class<C2> secondClazz, Function<U2, T4> fourthFunction, Function<U2, T5> fifthFunction,
         BiFunction<T4, T5, R> secondBranch) {
        return match(value,
                firstClazz, firstFunction, secondFunction, thirdFunction, firstBranch,
                secondClazz, fourthFunction, fifthFunction, secondBranch);
    }
}
