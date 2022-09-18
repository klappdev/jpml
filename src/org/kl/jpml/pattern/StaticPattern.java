/*
 * Licensed under the MIT License <http://opensource.org/licenses/MIT>.
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2019 - 2021 https://github.com/klappdev
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

import java.util.Optional;
import java.util.function.*;

/**
 * Static pattern allow match type and deconstruct object
 * using factory methods. Maximum number of branches for
 * match three with three value params.
 */
public final class StaticPattern {
    private final Object data;
    private final Optional optionalData;

    private <V, E extends Throwable> StaticPattern(V data, Optional<V> optionalData) {
        this.data = data;
        this.optionalData = optionalData;
    }

    public static <V> StaticPattern match(V value) {
        return new StaticPattern(value, null);
    }

    public static <V> StaticPattern match(Optional<V> optionalValue) {
        return new StaticPattern(null, optionalValue);
    }

    public static String of(String name) {
        return name;
    }

    public static <V, C, T>
    void match(V data, Class<C> clazz, String name, Consumer<T> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] args = Reflection.invokeExtractor(data, name, 1);

            firstBranch.accept((T) args[0]);
        }
    }

    public <C, T>
    void as(Class<C> clazz, String name, Consumer<T> firstBranch) {
        match(data, clazz, name, firstBranch);
    }

    public static <V, C, T, R>
    R match(V data, Class<C> clazz, String name, Function<T, R> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] args = Reflection.invokeExtractor(data, name, 1);

            return firstBranch.apply((T) args[0]);
        }

        throw new PatternException("Expression must to have only one branch");
    }

    public <C, T, R>
    R as(Class<C> clazz, String name, Function<T, R> firstBranch) {
        return match(data, clazz, name, firstBranch);
    }

    public static <V, C, T1, T2>
    void match(V data, Class<C> clazz, String name, BiConsumer<T1, T2> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] args = Reflection.invokeExtractor(data, name, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
        }
    }

    public <C, T1, T2>
    void as(Class<C> clazz, String name, BiConsumer<T1, T2> firstBranch) {
        match(data, clazz, name, firstBranch);
    }

    public static <V, C, T1, T2, R>
    R match(V data, Class<C> clazz, String name, BiFunction<T1, T2, R> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] args = Reflection.invokeExtractor(data, name, 2);

            return firstBranch.apply((T1) args[0], (T2) args[1]);
        }

        throw new PatternException("Expression must to have only one branch");
    }

    public <C, T1, T2, R>
    R as(Class<C> clazz, String name, BiFunction<T1, T2, R> firstBranch) {
        return match(data, clazz, name, firstBranch);
    }

    public static <V, C, T1, T2, T3>
    void match(V data, Class<C> clazz, String name, TriConsumer<T1, T2, T3> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] args = Reflection.invokeExtractor(data, name, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        }
    }

    public <C, T1, T2, T3>
    void as(Class<C> clazz, String name, TriConsumer<T1, T2, T3> firstBranch) {
        match(data, clazz, name, firstBranch);
    }

    public static <V, C, T1, T2, T3, R>
    R match(V data, Class<C> clazz, String name, TriFunction<T1, T2, T3, R> firstBranch) {
        if (clazz == data.getClass()) {
            Object[] args = Reflection.invokeExtractor(data, name, 3);

            return firstBranch.apply((T1) args[0], (T2) args[1], (T3) args[2]);
        }

        throw new PatternException("Expression must to have only one branch");
    }

    public <C, T1, T2, T3, R>
    R as(Class<C> clazz, String name, TriFunction<T1, T2, T3, R> firstBranch) {
        return match(data, clazz, name, firstBranch);
    }

    public static <V, C1, C2, T1, T2>
    void match(V value,
               Class<C1> firstClazz, String firstName, Consumer<T1> firstBranch,
               Class<C2> secondClazz, String secondName, Consumer<T2> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 1);

            firstBranch.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 1);

            secondBranch.accept((T2) args[0]);
        }
    }

    public static <V, T>
    void match(Optional<V> value,
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

    public <C1, C2, T1, T2>
    void as(Class<C1> firstClazz, String firstName, Consumer<T1> firstBranch,
            Class<C2> secondClazz, String secondName, Consumer<T2> secondBranch) {
        match(data,
                firstClazz, firstName, firstBranch,
                secondClazz, secondName, secondBranch);
    }

    @SuppressWarnings("unchecked")
    public <V, T> void as(Supplier<Optional<V>> supplier, Runnable firstBranch,
                          Function<Optional<V>, T> function, Consumer<T> secondBranch) {
        match(optionalData,
                supplier, firstBranch,
                function, secondBranch);
    }

    public static <V, C1, C2, T1, T2, R>
    R match(V value,
            Class<C1> firstClazz, String firstName, Function<T1, R> firstBranch,
            Class<C2> secondClazz, String secondName, Function<T2, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 1);

            return firstBranch.apply((T1) args[0]);
        }

        if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 1);

            return secondBranch.apply((T2) args[0]);
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public static <V, T, R>
    R match(Optional<V> value,
            Supplier<Optional<V>> supplier, Supplier<R> firstBranch,
            Function<Optional<V>, T> function, Function<T, R> secondBranch) {
        Optional<V> tmp = supplier.get();

        if (tmp.equals(value)) {
            return firstBranch.get();
        } else {
            T arg = function.apply(value);
            return secondBranch.apply(arg);
        }
    }

    public <C1, C2, T1, T2, T3, R>
    R as(Class<C1> firstClazz, String firstName, Function<T1, R> firstBranch,
         Class<C2> secondClazz, String secondName, Function<T2, R> secondBranch) {
        return match(data,
                firstClazz, firstName, firstBranch,
                secondClazz, secondName, secondBranch);
    }

    @SuppressWarnings("unchecked")
    public <V, T, R>
    R as(Supplier<Optional<V>> supplier, Supplier<R> firstBranch,
         Function<Optional<V>, T> function, Function<T, R> secondBranch) {
        return match((Optional<V>) optionalData,
                supplier, firstBranch,
                function, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3>
    void match(V value,
               Class<C1> firstClazz, String firstName, Consumer<T1> firstBranch,
               Class<C2> secondClazz, String secondName, BiConsumer<T2, T3> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 1);

            firstBranch.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 2);

            secondBranch.accept((T2) args[0], (T3) args[1]);
        }
    }

    public <C1, C2, T1, T2, T3>
    void as(Class<C1> firstClazz, String firstName, Consumer<T1> firstBranch,
            Class<C2> secondClazz, String secondName, BiConsumer<T2, T3> secondBranch) {
        match(data,
                firstClazz, firstName, firstBranch,
                secondClazz, secondName, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, R>
    R match(V value,
            Class<C1> firstClazz, String firstName, Function<T1, R> firstBranch,
            Class<C2> secondClazz, String secondName, BiFunction<T2, T3, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 1);

            return firstBranch.apply((T1) args[0]);
        }

        if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 2);

            return secondBranch.apply((T2) args[0], (T3) args[1]);
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, R>
    R as(Class<C1> firstClazz, String firstName, Function<T1, R> firstBranch,
         Class<C2> secondClazz, String secondName, BiFunction<T2, T3, R> secondBranch) {
        return match(data,
                firstClazz, firstName, firstBranch,
                secondClazz, secondName, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3>
    void match(V value,
               Class<C1> firstClazz, String firstName, BiConsumer<T1, T2> firstBranch,
               Class<C2> secondClazz, String secondName, Consumer<T3> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 1);

            secondBranch.accept((T3) args[0]);
        }
    }

    public <C1, C2, T1, T2, T3>
    void as(Class<C1> firstClazz, String firstName, BiConsumer<T1, T2> firstBranch,
            Class<C2> secondClazz, String secondName, Consumer<T3> secondBranch) {
        match(data,
                firstClazz, firstName, firstBranch,
                secondClazz, secondName, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, R>
    R match(V value,
            Class<C1> firstClazz, String firstName, BiFunction<T1, T2, R> firstBranch,
            Class<C2> secondClazz, String secondName, Function<T3, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 2);

            return firstBranch.apply((T1) args[0], (T2) args[1]);
        }

        if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 1);

            return secondBranch.apply((T3) args[0]);
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, R>
    R as(Class<C1> firstClazz, String firstName, BiFunction<T1, T2, R> firstBranch,
         Class<C2> secondClazz, String secondName, Function<T3, R> secondBranch) {
        return match(data,
                firstClazz, firstName, firstBranch,
                secondClazz, secondName, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4>
    void match(V value,
               Class<C1> firstClazz, String firstName, BiConsumer<T1, T2> firstBranch,
               Class<C2> secondClazz, String secondName, BiConsumer<T3, T4> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 2);

            secondBranch.accept((T3) args[0], (T4) args[1]);
        }
    }

    public <C1, C2, T1, T2, T3, T4>
    void as(Class<C1> firstClazz, String firstName, BiConsumer<T1, T2> firstBranch,
            Class<C2> secondClazz, String secondName, BiConsumer<T3, T4> secondBranch) {
        match(data,
                firstClazz, firstName, firstBranch,
                secondClazz, secondName, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, R>
    R match(V value,
            Class<C1> firstClazz, String firstName, BiFunction<T1, T2, R> firstBranch,
            Class<C2> secondClazz, String secondName, BiFunction<T3, T4, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 2);

            return firstBranch.apply((T1) args[0], (T2) args[1]);
        }

        if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 2);

            return secondBranch.apply((T3) args[0], (T4) args[1]);
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, R>
    R as(Class<C1> firstClazz, String firstName, BiFunction<T1, T2, R> firstBranch,
         Class<C2> secondClazz, String secondName, BiFunction<T3, T4, R> secondBranch) {
        return match(data,
                firstClazz, firstName, firstBranch,
                secondClazz, secondName, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4>
    void match(V value,
               Class<C1> firstClazz, String firstName, Consumer<T1> firstBranch,
               Class<C2> secondClazz, String secondName, TriConsumer<T2, T3, T4> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 1);

            firstBranch.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 3);

            secondBranch.accept((T2) args[0], (T3) args[1], (T4) args[2]);
        }
    }

    public <C1, C2, T1, T2, T3, T4>
    void as(Class<C1> firstClazz, String firstName, Consumer<T1> firstBranch,
            Class<C2> secondClazz, String secondName, TriConsumer<T2, T3, T4> secondBranch) {
        match(data,
                firstClazz, firstName, firstBranch,
                secondClazz, secondName, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, R>
    R match(V value,
            Class<C1> firstClazz, String firstName, Function<T1, R> firstBranch,
            Class<C2> secondClazz, String secondName, TriFunction<T2, T3, T4, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 1);

            return firstBranch.apply((T1) args[0]);
        }

        if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 3);

            return secondBranch.apply((T2) args[0], (T3) args[1], (T4) args[2]);
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, R>
    R as(Class<C1> firstClazz, String firstName, Function<T1, R> firstBranch,
         Class<C2> secondClazz, String secondName, TriFunction<T2, T3, T4, R> secondBranch) {
        return match(data,
                firstClazz, firstName, firstBranch,
                secondClazz, secondName, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4>
    void match(V value,
               Class<C1> firstClazz, String firstName, TriConsumer<T1, T2, T3> firstBranch,
               Class<C2> secondClazz, String secondName, Consumer<T4> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 1);

            secondBranch.accept((T4) args[0]);
        }
    }

    public <C1, C2, T1, T2, T3, T4>
    void as(Class<C1> firstClazz, String firstName, TriConsumer<T1, T2, T3> firstBranch,
            Class<C2> secondClazz, String secondName, Consumer<T4> secondBranch) {
        match(data,
                firstClazz, firstName, firstBranch,
                secondClazz, secondName, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, R>
    R match(V value,
            Class<C1> firstClazz, String firstName, TriFunction<T1, T2, T3, R> firstBranch,
            Class<C2> secondClazz, String secondName, Function<T4, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 3);

            return firstBranch.apply((T1) args[0], (T2) args[1], (T3) args[2]);
        }

        if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 1);

            return secondBranch.apply((T4) args[0]);
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, R>
    R as(Class<C1> firstClazz, String firstName, TriFunction<T1, T2, T3, R> firstBranch,
         Class<C2> secondClazz, String secondName, Function<T4, R> secondBranch) {
        return match(data,
                firstClazz, firstName, firstBranch,
                secondClazz, secondName, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6>
    void match(V value,
               Class<C1> firstClazz, String firstName, TriConsumer<T1, T2, T3> firstBranch,
               Class<C2> secondClazz, String secondName, TriConsumer<T4, T5, T6> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 3);

            secondBranch.accept((T4) args[0], (T5) args[1], (T6) args[2]);
        }
    }

    public <C1, C2, T1, T2, T3, T4, T5, T6>
    void as(Class<C1> firstClazz, String firstName, TriConsumer<T1, T2, T3> firstBranch,
            Class<C2> secondClazz, String secondName, TriConsumer<T4, T5, T6> secondBranch) {
        match(data,
                firstClazz, firstName, firstBranch,
                secondClazz, secondName, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6, R>
    R match(V value,
            Class<C1> firstClazz, String firstName, TriFunction<T1, T2, T3, R> firstBranch,
            Class<C2> secondClazz, String secondName, TriFunction<T4, T5, T6, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 3);

            return firstBranch.apply((T1) args[0], (T2) args[1], (T3) args[2]);
        }

        if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 3);

            return secondBranch.apply((T4) args[0], (T5) args[1], (T6) args[2]);
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, T5, T6, R>
    R as(Class<C1> firstClazz, String firstName, TriFunction<T1, T2, T3, R> firstBranch,
         Class<C2> secondClazz, String secondName, TriFunction<T4, T5, T6, R> secondBranch) {
        return match(data,
                firstClazz, firstName, firstBranch,
                secondClazz, secondName, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5>
    void match(V value,
               Class<C1> firstClazz, String firstName, BiConsumer<T1, T2> firstBranch,
               Class<C2> secondClazz, String secondName, TriConsumer<T3, T4, T5> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 3);

            secondBranch.accept((T3) args[0], (T4) args[1], (T5) args[2]);
        }
    }

    public <C1, C2, T1, T2, T3, T4, T5>
    void as(Class<C1> firstClazz, String firstName, BiConsumer<T1, T2> firstBranch,
            Class<C2> secondClazz, String secondName, TriConsumer<T3, T4, T5> secondBranch) {
        match(data,
                firstClazz, firstName, firstBranch,
                secondClazz, secondName, secondBranch);

    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, R>
    R match(V value,
            Class<C1> firstClazz, String firstName, BiFunction<T1, T2, R> firstBranch,
            Class<C2> secondClazz, String secondName, TriFunction<T3, T4, T5, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 2);

            return firstBranch.apply((T1) args[0], (T2) args[1]);
        }

        if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 3);

            return secondBranch.apply((T3) args[0], (T4) args[1], (T5) args[2]);
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, T5, R>
    R as(Class<C1> firstClazz, String firstName, BiFunction<T1, T2, R> firstBranch,
         Class<C2> secondClazz, String secondName, TriFunction<T3, T4, T5, R> secondBranch) {
        return match(data,
                firstClazz, firstName, firstBranch,
                secondClazz, secondName, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5>
    void match(V value,
               Class<C1> firstClazz, String firstName, TriConsumer<T1, T2, T3> firstBranch,
               Class<C2> secondClazz, String secondName, BiConsumer<T4, T5> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 2);

            secondBranch.accept((T4) args[0], (T5) args[1]);
        }
    }

    public <C1, C2, T1, T2, T3, T4, T5>
    void as(Class<C1> firstClazz, String firstName, TriConsumer<T1, T2, T3> firstBranch,
            Class<C2> secondClazz, String secondName, BiConsumer<T4, T5> secondBranch) {
        match(data,
                firstClazz, firstName, firstBranch,
                secondClazz, secondName, secondBranch);
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, R>
    R match(V value,
            Class<C1> firstClazz, String firstName, TriFunction<T1, T2, T3, R> firstBranch,
            Class<C2> secondClazz, String secondName, BiFunction<T4, T5, R> secondBranch) {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, firstName, 3);

            return firstBranch.apply((T1) args[0], (T2) args[1], (T3) args[2]);
        }

        if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, secondName, 2);

            return secondBranch.apply((T4) args[0], (T5) args[1]);
        }

        throw new PatternException("Expression must to have only two branches");
    }

    public <C1, C2, T1, T2, T3, T4, T5, R>
    R as(Class<C1> firstClazz, String firstName, TriFunction<T1, T2, T3, R> firstBranch,
         Class<C2> secondClazz, String secondName, BiFunction<T4, T5, R> secondBranch) {
        return match(data,
                firstClazz, firstName, firstBranch,
                secondClazz, secondName, secondBranch);
    }
}
