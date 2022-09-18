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

import org.kl.jpml.lambda.Provider;
import org.kl.jpml.state.Else;
import org.kl.jpml.util.Lazy;

import java.util.function.*;

/**
 * Common pattern contains general constructions
 * which could be useful.
 */
public final class CommonPattern {
    private final Object value;

    private <V> CommonPattern(V value) {
        this.value = value;
    }

    public static Predicate<Byte> lt(byte value) {
        return t -> t < value;
    }

    public static Predicate<Short> lt(short value) {
        return t -> t < value;
    }

    public static Predicate<Integer> lt(int value) {
        return t -> t < value;
    }

    public static Predicate<Long> lt(long value) {
        return t -> t < value;
    }

    public static Predicate<Float> lt(float value) {
        return t -> t < value;
    }

    public static Predicate<Double> lt(double value) {
        return t -> t < value;
    }

    public static Predicate<Byte> lessThan(byte value) {
        return t -> t < value;
    }

    public static Predicate<Short> lessThan(short value) {
        return t -> t < value;
    }

    public static Predicate<Integer> lessThan(int value) {
        return t -> t < value;
    }

    public static Predicate<Long> lessThan(long value) {
        return t -> t < value;
    }

    public static Predicate<Float> lessThan(float value) {
        return t -> t < value;
    }

    public static Predicate<Double> lessThan(double value) {
        return t -> t < value;
    }

    public static Predicate<Byte> le(byte value) {
        return t -> t <= value;
    }

    public static Predicate<Short> le(short value) {
        return t -> t <= value;
    }

    public static Predicate<Integer> le(int value) {
        return t -> t <= value;
    }

    public static Predicate<Long> le(long value) {
        return t -> t <= value;
    }

    public static Predicate<Float> le(float value) {
        return t -> t <= value;
    }

    public static Predicate<Double> le(double value) {
        return t -> t <= value;
    }

    public static Predicate<Byte> lessThanOrEqual(byte value) {
        return t -> t <= value;
    }

    public static Predicate<Short> lessThanOrEqual(short value) {
        return t -> t <= value;
    }

    public static Predicate<Integer> lessThanOrEqual(int value) {
        return t -> t <= value;
    }

    public static Predicate<Long> lessThanOrEqual(long value) {
        return t -> t <= value;
    }

    public static Predicate<Float> lessThanOrEqual(float value) {
        return t -> t <= value;
    }

    public static Predicate<Double> lessThanOrEqual(double value) {
        return t -> t <= value;
    }

    public static Predicate<Byte> gt(byte value) {
        return t -> t > value;
    }

    public static Predicate<Short> gt(short value) {
        return t -> t > value;
    }

    public static Predicate<Integer> gt(int value) {
        return t -> t > value;
    }

    public static Predicate<Long> gt(long value) {
        return t -> t > value;
    }

    public static Predicate<Float> gt(float value) {
        return t -> t > value;
    }

    public static Predicate<Double> gt(double value) {
        return t -> t > value;
    }

    public static Predicate<Byte> greaterThan(byte value) {
        return t -> t > value;
    }

    public static Predicate<Short> greaterThan(short value) {
        return t -> t > value;
    }

    public static Predicate<Integer> greaterThan(int value) {
        return t -> t > value;
    }

    public static Predicate<Long> greaterThan(long value) {
        return t -> t > value;
    }

    public static Predicate<Float> greaterThan(float value) {
        return t -> t > value;
    }

    public static Predicate<Double> greaterThan(double value) {
        return t -> t > value;
    }

    public static Predicate<Byte> ge(byte value) {
        return t -> t >= value;
    }

    public static Predicate<Short> ge(short value) {
        return t -> t >= value;
    }

    public static Predicate<Integer> ge(int value) {
        return t -> t >= value;
    }

    public static Predicate<Long> ge(long value) {
        return t -> t >= value;
    }

    public static Predicate<Float> ge(float value) {
        return t -> t >= value;
    }

    public static Predicate<Double> ge(double value) {
        return t -> t >= value;
    }

    public static Predicate<Byte> greaterThanOrEqual(byte value) {
        return t -> t >= value;
    }

    public static Predicate<Short> greaterThanOrEqual(short value) {
        return t -> t >= value;
    }

    public static Predicate<Integer> greaterThanOrEqual(int value) {
        return t -> t >= value;
    }

    public static Predicate<Long> greaterThanOrEqual(long value) {
        return t -> t >= value;
    }

    public static Predicate<Float> greaterThanOrEqual(float value) {
        return t -> t >= value;
    }

    public static Predicate<Double> greaterThanOrEqual(double value) {
        return t -> t >= value;
    }

    public static Predicate<Byte> eq(byte value) {
        return t -> t == value;
    }

    public static Predicate<Short> eq(short value) {
        return t -> t == value;
    }

    public static Predicate<Integer> eq(int value) {
        return t -> t == value;
    }

    public static Predicate<Long> eq(long value) {
        return t -> t == value;
    }

    public static Predicate<Float> eq(float value) {
        return t -> t == value;
    }

    public static Predicate<Double> eq(double value) {
        return t -> t == value;
    }

    public static Predicate<Character> eq(char value) {
        return t -> t == value;
    }

    public static Predicate<Boolean> eq(boolean value) {
        return t -> t == value;
    }

    public static Predicate<Byte> equal(byte value) {
        return t -> t == value;
    }

    public static Predicate<Short> equal(short value) {
        return t -> t == value;
    }

    public static Predicate<Integer> equal(int value) {
        return t -> t == value;
    }

    public static Predicate<Long> equal(long value) {
        return t -> t == value;
    }

    public static Predicate<Float> equal(float value) {
        return t -> t == value;
    }

    public static Predicate<Double> equal(double value) {
        return t -> t == value;
    }

    public static Predicate<Character> equal(char value) {
        return t -> t == value;
    }

    public static Predicate<Boolean> equal(boolean value) {
        return t -> t == value;
    }

    public static Predicate<Byte> ne(byte value) {
        return t -> t != value;
    }

    public static Predicate<Short> ne(short value) {
        return t -> t != value;
    }

    public static Predicate<Integer> ne(int value) {
        return t -> t != value;
    }

    public static Predicate<Long> ne(long value) {
        return t -> t != value;
    }

    public static Predicate<Float> ne(float value) {
        return t -> t != value;
    }

    public static Predicate<Double> ne(double value) {
        return t -> t != value;
    }

    public static Predicate<Character> ne(char value) {
        return t -> t != value;
    }

    public static Predicate<Boolean> ne(boolean value) {
        return t -> t != value;
    }

    public static Predicate<Byte> notEqual(byte value) {
        return t -> t != value;
    }

    public static Predicate<Short> notEqual(short value) {
        return t -> t != value;
    }

    public static Predicate<Integer> notEqual(int value) {
        return t -> t != value;
    }

    public static Predicate<Long> notEqual(long value) {
        return t -> t != value;
    }

    public static Predicate<Float> notEqual(float value) {
        return t -> t != value;
    }

    public static Predicate<Double> notEqual(double value) {
        return t -> t != value;
    }

    public static Predicate<Character> notEqual(char value) {
        return t -> t != value;
    }

    public static Predicate<Boolean> notEqual(boolean value) {
        return t -> t != value;
    }

    public static <T> Predicate<T> no() {
        return t -> false;
    }

    public static <T> Predicate<T> yes() {
        return t -> true;
    }

    public static <T> Predicate<T> never() {
        return t -> false;
    }

    public static <T> Predicate<T> always() {
        return t -> true;
    }

    public static <T> CommonPattern self(T value) {
        return new CommonPattern(value);
    }

    public static void run(Runnable block) {
        block.run();
    }

    public static <R> R run(Supplier<R> block) {
        return block.get();
    }

    public static void repeat(int times, Consumer<Integer> block) {
        for (int i = 0; i < times; i++) {
            block.accept(i);
        }
    }

    public static void repeat(int times, Runnable block) {
        for (int i = 0; i < times; i++) {
            block.run();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> void also(Consumer<T> block) {
        if (value != null) {
            block.accept((T) value);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T also(UnaryOperator<T> block) {
        if (value != null) {
            return block.apply((T) value);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> void let(Consumer<T> block) {
        if (value != null) {
            block.accept((T) value);
        }
    }

    @SuppressWarnings("unchecked")
    public <T, R> R let(Function<T, R> block) {
        if (value != null) {
            return block.apply((T) value);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T takeIf(Predicate<T> predicate) {
        if (predicate.test((T) value)) {
            return (T) value;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T takeUnless(Predicate<T> predicate) {
        if (!predicate.test((T) value)) {
            return (T) value;
        } else {
            return null;
        }
    }

    public static <T> T elvis(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static <T> T[] elvis(T[] value, T[] defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static String elvis(String value, String defaultValue) {
        return value == null || value.isEmpty() ? defaultValue : value;
    }

    public static <T> T elvis(T value, Supplier<? extends T> other) {
        return value == null ? other.get() : value;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] elvis(T[] value, Supplier<T[]> other) {
        return value == null ? other.get() : value;
    }

    public static String elvis(String value, Supplier<String> other) {
        return value == null || value.isEmpty() ? other.get() : value;
    }

    public static <T, X extends Throwable> T elvisThrow(T value, Provider<? extends X> other) throws X {
        if (value != null) {
            return value;
        } else {
            throw other.take();
        }
    }

    public static <T, X extends Throwable> T[] elvisThrow(T[] value, Provider<? extends X> other) throws X {
        if (value != null) {
            return value;
        } else {
            throw other.take();
        }
    }

    public static <X extends Throwable> String elvisThrow(String value, Provider<? extends X> other) throws X {
        if (value != null) {
            return value;
        } else {
            throw other.take();
        }
    }

    public static <T> Lazy<T> lazy(Supplier<T> supplier) {
        return new Lazy<>(supplier);
    }

    public static Lazy<Byte> lazy(byte value) {
        return new Lazy<>(() -> value);
    }

    public static Lazy<Short> lazy(short value) {
        return new Lazy<>(() -> value);
    }

    public static Lazy<Integer> lazy(int value) {
        return new Lazy<>(() -> value);
    }

    public static Lazy<Long> lazy(long value) {
        return new Lazy<>(() -> value);
    }

    public static Lazy<Float> lazy(float value) {
        return new Lazy<>(() -> value);
    }

    public static Lazy<Double> lazy(double value) {
        return new Lazy<>(() -> value);
    }

    public static Lazy<Character> lazy(char value) {
        return new Lazy<>(() -> value);
    }

    public static Lazy<Boolean> lazy(boolean value) {
        return new Lazy<>(() -> value);
    }

    public static Lazy<String> lazy(String value) {
        return new Lazy<>(() -> value);
    }

    @SuppressWarnings("unchecked")
    public <T> T apply(Consumer<T> block) {
        block.accept((T) value);

        return (T) value;
    }

    public static <T> T apply(T value, Consumer<T> block) {
        block.accept(value);

        return value;
    }

    public static <T> void with(T instance, Consumer<T> consumer) {
        consumer.accept(instance);
    }

    public static <T, R> R with(T instance, Function<T, R> function) {
        return function.apply(instance);
    }

    public static void match(boolean firstCondition, Runnable firstBranch) {
        if (firstCondition) {
            firstBranch.run();
        }
    }

    @SuppressWarnings("unused")
    public static void match(boolean firstCondition, Runnable firstBranch,
                            Class<Else> defaultClass, Runnable defaultBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else {
            defaultBranch.run();
        }
    }

    public static <R> R match(boolean firstCondition, Supplier<R> firstBranch) {
        if (firstCondition) {
            return firstBranch.get();
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <R> R match(boolean firstCondition, Supplier<R> firstBranch,
                             Class<Else> defaultClass, Supplier<R> defaultBranch) {
        if (firstCondition) {
            return firstBranch.get();
        }

        return defaultBranch.get();
    }

    public static void match(boolean firstCondition, Runnable firstBranch,
                            boolean secondCondition, Runnable secondBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else if (secondCondition) {
            secondBranch.run();
        }
    }

    public static void match(boolean firstCondition, Runnable firstBranch,
                            boolean secondCondition, Runnable secondBranch,
                            Class<Else> defaultClass, Runnable defaultBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else if (secondCondition) {
            secondBranch.run();
        } else {
            defaultBranch.run();
        }
    }

    public static <R> R match(boolean firstCondition, Supplier<R> firstBranch,
                             boolean secondCondition, Supplier<R> secondBranch) {
        if (firstCondition) {
            return firstBranch.get();
        } else if (secondCondition) {
            return secondBranch.get();
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <R> R match(boolean firstCondition, Supplier<R> firstBranch,
                             boolean secondCondition, Supplier<R> secondBranch,
                             Class<Else> defaultClass, Supplier<R> defaultBranch) {
        if (firstCondition) {
            return firstBranch.get();
        } else if (secondCondition) {
            return secondBranch.get();
        }

        return defaultBranch.get();
    }

    @SuppressWarnings("Duplicates")
    public static void match(boolean firstCondition, Runnable firstBranch,
                            boolean secondCondition, Runnable secondBranch,
                            boolean thirdCondition, Runnable thirdBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else if (secondCondition) {
            secondBranch.run();
        } else if (thirdCondition) {
            thirdBranch.run();
        }
    }

    public static void match(boolean firstCondition, Runnable firstBranch,
                            boolean secondCondition, Runnable secondBranch,
                            boolean thirdCondition, Runnable thirdBranch,
                            Class<Else> defaultClass, Runnable defaultBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else if (secondCondition) {
            secondBranch.run();
        } else if (thirdCondition) {
            thirdBranch.run();
        } else {
            defaultBranch.run();
        }
    }

    @SuppressWarnings("Duplicates")
    public static <R> R match(boolean firstCondition, Supplier<R> firstBranch,
                             boolean secondCondition, Supplier<R> secondBranch,
                             boolean thirdCondition, Supplier<R> thirdBranch) {
        if (firstCondition) {
            return firstBranch.get();
        } else if (secondCondition) {
            return secondBranch.get();
        } else if (thirdCondition) {
            return thirdBranch.get();
        }

        return null;
    }

    public static <R> R match(boolean firstCondition, Supplier<R> firstBranch,
                             boolean secondCondition, Supplier<R> secondBranch,
                             boolean thirdCondition, Supplier<R> thirdBranch,
                             Class<Else> defaultClass, Supplier<R> defaultBranch) {
        if (firstCondition) {
            return firstBranch.get();
        } else if (secondCondition) {
            return secondBranch.get();
        } else if (thirdCondition) {
            return thirdBranch.get();
        }

        return defaultBranch.get();
    }

    public static void match(boolean firstCondition, Runnable firstBranch,
                            boolean secondCondition, Runnable secondBranch,
                            boolean thirdCondition, Runnable thirdBranch,
                            boolean fourthCondition, Runnable fourthBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else if (secondCondition) {
            secondBranch.run();
        } else if (thirdCondition) {
            thirdBranch.run();
        } else if (fourthCondition) {
            fourthBranch.run();
        }
    }

    public static void match(boolean firstCondition, Runnable firstBranch,
                            boolean secondCondition, Runnable secondBranch,
                            boolean thirdCondition, Runnable thirdBranch,
                            boolean fourthCondition, Runnable fourthBranch,
                            Class<Else> defaultClass, Runnable defaultBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else if (secondCondition) {
            secondBranch.run();
        } else if (thirdCondition) {
            thirdBranch.run();
        } else if (fourthCondition) {
            fourthBranch.run();
        } else {
            defaultBranch.run();
        }
    }

    public static <R> R match(boolean firstCondition, Supplier<R> firstBranch,
                             boolean secondCondition, Supplier<R> secondBranch,
                             boolean thirdCondition, Supplier<R> thirdBranch,
                             boolean fourthCondition, Supplier<R> fourthBranch) {
        if (firstCondition) {
            return firstBranch.get();
        } else if (secondCondition) {
            return secondBranch.get();
        } else if (thirdCondition) {
            return thirdBranch.get();
        } else if (fourthCondition) {
            return fourthBranch.get();
        }

        return null;
    }

    public static <R> R match(boolean firstCondition, Supplier<R> firstBranch,
                             boolean secondCondition, Supplier<R> secondBranch,
                             boolean thirdCondition, Supplier<R> thirdBranch,
                             boolean fourthCondition, Supplier<R> fourthBranch,
                             Class<Else> defaultClass, Supplier<R> defaultBranch) {
        if (firstCondition) {
            return firstBranch.get();
        } else if (secondCondition) {
            return secondBranch.get();
        } else if (thirdCondition) {
            return thirdBranch.get();
        } else if (fourthCondition) {
            return fourthBranch.get();
        }

        return defaultBranch.get();
    }

    @SuppressWarnings("Duplicates")
    public static void match(boolean firstCondition, Runnable firstBranch,
                            boolean secondCondition, Runnable secondBranch,
                            boolean thirdCondition, Runnable thirdBranch,
                            boolean fourthCondition, Runnable fourthBranch,
                            boolean fifthCondition, Runnable fifthBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else if (secondCondition) {
            secondBranch.run();
        } else if (thirdCondition) {
            thirdBranch.run();
        } else if (fourthCondition) {
            fourthBranch.run();
        } else if (fifthCondition) {
            fifthBranch.run();
        }
    }

    public static void match(boolean firstCondition, Runnable firstBranch,
                            boolean secondCondition, Runnable secondBranch,
                            boolean thirdCondition, Runnable thirdBranch,
                            boolean fourthCondition, Runnable fourthBranch,
                            boolean fifthCondition, Runnable fifthBranch,
                            Class<Else> defaultClass, Runnable defaultBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else if (secondCondition) {
            secondBranch.run();
        } else if (thirdCondition) {
            thirdBranch.run();
        } else if (fourthCondition) {
            fourthBranch.run();
        } else if (fifthCondition) {
            fifthBranch.run();
        } else {
            defaultBranch.run();
        }
    }

    @SuppressWarnings("Duplicates")
    public static <R> R match(boolean firstCondition, Supplier<R> firstBranch,
                             boolean secondCondition, Supplier<R> secondBranch,
                             boolean thirdCondition, Supplier<R> thirdBranch,
                             boolean fourthCondition, Supplier<R> fourthBranch,
                             boolean fifthCondition, Supplier<R> fifthBranch) {
        if (firstCondition) {
            return firstBranch.get();
        } else if (secondCondition) {
            return secondBranch.get();
        } else if (thirdCondition) {
            return thirdBranch.get();
        } else if (fourthCondition) {
            return fourthBranch.get();
        } else if (fifthCondition) {
            return fifthBranch.get();
        }

        return null;
    }

    public static <R> R match(boolean firstCondition, Supplier<R> firstBranch,
                             boolean secondCondition, Supplier<R> secondBranch,
                             boolean thirdCondition, Supplier<R> thirdBranch,
                             boolean fourthCondition, Supplier<R> fourthBranch,
                             boolean fifthCondition, Supplier<R> fifthBranch,
                             Class<Else> defaultClass, Supplier<R> defaultBranch) {
        if (firstCondition) {
            return firstBranch.get();
        } else if (secondCondition) {
            return secondBranch.get();
        } else if (thirdCondition) {
            return thirdBranch.get();
        } else if (fourthCondition) {
            return fourthBranch.get();
        } else if (fifthCondition) {
            return fifthBranch.get();
        }

        return defaultBranch.get();
    }


    @SuppressWarnings("Duplicates")
    public static void match(boolean firstCondition, Runnable firstBranch,
                            boolean secondCondition, Runnable secondBranch,
                            boolean thirdCondition, Runnable thirdBranch,
                            boolean fourthCondition, Runnable fourthBranch,
                            boolean fifthCondition, Runnable fifthBranch,
                            boolean sixthCondition, Runnable sixthBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else if (secondCondition) {
            secondBranch.run();
        } else if (thirdCondition) {
            thirdBranch.run();
        } else if (fourthCondition) {
            fifthBranch.run();
        } else if (fifthCondition) {
            fourthBranch.run();
        } else if (sixthCondition) {
            sixthBranch.run();
        }
    }

    public static void match(boolean firstCondition, Runnable firstBranch,
                            boolean secondCondition, Runnable secondBranch,
                            boolean thirdCondition, Runnable thirdBranch,
                            boolean fourthCondition, Runnable fourthBranch,
                            boolean fifthCondition, Runnable fifthBranch,
                            boolean sixthCondition, Runnable sixthBranch,
                            Class<Else> defaultClass, Runnable defaultBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else if (secondCondition) {
            secondBranch.run();
        } else if (thirdCondition) {
            thirdBranch.run();
        } else if (fourthCondition) {
            fifthBranch.run();
        } else if (fifthCondition) {
            fourthBranch.run();
        } else if (sixthCondition) {
            sixthBranch.run();
        } else {
            defaultBranch.run();
        }
    }

    @SuppressWarnings("Duplicates")
    public static <R> R match(boolean firstCondition, Supplier<R> firstBranch,
                             boolean secondCondition, Supplier<R> secondBranch,
                             boolean thirdCondition, Supplier<R> thirdBranch,
                             boolean fourthCondition, Supplier<R> fourthBranch,
                             boolean fifthCondition, Supplier<R> fifthBranch,
                             boolean sixthCondition, Supplier<R> sixthBranch) {
        if (firstCondition) {
            return firstBranch.get();
        } else if (secondCondition) {
            return secondBranch.get();
        } else if (thirdCondition) {
            return thirdBranch.get();
        } else if (fourthCondition) {
            return fourthBranch.get();
        } else if (fifthCondition) {
            return fifthBranch.get();
        } else if (sixthCondition) {
            return sixthBranch.get();
        }

        return null;
    }

    public static <R> R match(boolean firstCondition, Supplier<R> firstBranch,
                             boolean secondCondition, Supplier<R> secondBranch,
                             boolean thirdCondition, Supplier<R> thirdBranch,
                             boolean fourthCondition, Supplier<R> fourthBranch,
                             boolean fifthCondition, Supplier<R> fifthBranch,
                             boolean sixthCondition, Supplier<R> sixthBranch,
                             Class<Else> defaultClass, Supplier<R> defaultBranch) {
        if (firstCondition) {
            return firstBranch.get();
        } else if (secondCondition) {
            return secondBranch.get();
        } else if (thirdCondition) {
            return thirdBranch.get();
        } else if (fourthCondition) {
            return fourthBranch.get();
        } else if (fifthCondition) {
            return fifthBranch.get();
        } else if (sixthCondition) {
            return sixthBranch.get();
        }

        return defaultBranch.get();
    }

    /* noreturn */
    public static void TODO() {
        throw new UnsupportedOperationException();
    }

    /* noreturn */
    public static void TODO(String reason) {
        throw new UnsupportedOperationException("An operation is not implemented: " + reason);
    }
}
