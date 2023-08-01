/*
 * Licensed under the MIT License <http://opensource.org/licenses/MIT>.
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2019 - 2023 https://github.com/klappdev
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

import org.kl.jpml.reflect.Reflection;
import org.kl.jpml.state.Else;
import org.kl.jpml.state.Null;
import org.kl.jpml.state.Var;

import java.util.function.*;

/**
 * Guard pattern allow match type and check condition for the
 * truth at one time. Maximum number of branches for match six.
 */
public final class GuardPattern {
    private final Object value;

    private <V> GuardPattern(V value) {
        this.value = value;
    }

    public static <V> GuardPattern match(V value) {
        return new GuardPattern(value);
    }

    public static <V, T> void match(V value,
                                    Class<T> clazz, Predicate<T> predicate, Consumer<T> consumer) {
        Class<?> valueClass = value.getClass();

        if (clazz == valueClass || Reflection.isPrimitive(clazz, valueClass)) {
            if (predicate.test((T) value)) {
                consumer.accept((T) value);
            }
        }
    }

    public <T> void as(Class<T> clazz, Predicate<T> predicate, Consumer<T> consumer) {
        match(value, clazz, predicate, consumer);
    }

    public static <V, T> void match(V value,
                                    Class<T> clazz, Predicate<T> predicate, Consumer<T> consumer,
                                    Class<Else> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (clazz == valueClass || Reflection.isPrimitive(clazz, valueClass)) {
            if (predicate.test((T) value)) {
                consumer.accept((T) value);
                return;
            }
        }

        defaultConsumer.run();
    }

    public <T> void as(Class<T> clazz, Predicate<T> predicate, Consumer<T> consumer,
                       Class<Else> defaultClass, Runnable defaultConsumer) {
        match(value, clazz, predicate, consumer, defaultClass, defaultConsumer);
    }

    public static <V, T> void match(V value,
                                    Class<T> clazz, Predicate<T> predicate, Consumer<T> consumer,
                                    Class<Var> varClass, Consumer<V> varConsumer) {
        Class<?> valueClass = value.getClass();

        if (clazz == valueClass || Reflection.isPrimitive(clazz, valueClass)) {
            if (predicate.test((T) value)) {
                consumer.accept((T) value);
                return;
            }
        }

        varConsumer.accept(value);
    }

    public <V, T> void as(Class<T> clazz, Predicate<T> predicate, Consumer<T> consumer,
                          Class<Var> varClass, Consumer<V> varConsumer) {
        match((V) value, clazz, predicate, consumer, varClass, varConsumer);
    }

    public static <V, T> void match(V value,
                                    Class<T> clazz, Predicate<T> predicate, Consumer<T> consumer,
                                    Class<Null> nullClass, Runnable nullConsumer,
                                    Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, clazz, predicate, consumer, defaultClass, defaultConsumer);
        }
    }

    public <V, T> void as(Class<T> clazz, Predicate<T> predicate, Consumer<T> consumer,
                          Class<Null> nullClass, Runnable nullConsumer,
                          Class<Else> defaultClass, Runnable defaultConsumer) {
        match((V) value, clazz, predicate, consumer,
                nullClass, nullConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T> void match(V value,
                                    Class<T> clazz, Predicate<T> predicate, Consumer<T> consumer,
                                    Class<Null> nullClass, Runnable nullConsumer,
                                    Class<Var> varClass, Consumer<V> varConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, clazz, predicate, consumer, varClass, varConsumer);
        }
    }

    public <V, T> void as(Class<T> clazz, Predicate<T> predicate, Consumer<T> consumer,
                          Class<Null> nullClass, Runnable nullConsumer,
                          Class<Var> varClass, Consumer<V> varConsumer) {
        match((V) value, clazz, predicate, consumer,
                nullClass, nullConsumer,
                varClass, varConsumer);
    }

    public static <V, T1, T2> void match(V value,
                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                         Class<Var> varClazz, Predicate<V> varPredicate, Runnable varConsumer,
                                         Class<Else> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (varPredicate.test(value)) {
            varConsumer.run();
        } else {
            defaultConsumer.run();
        }
    }

    public <V, T1, T2> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                            Class<Var> varClazz, Predicate<V> varPredicate, Runnable varConsumer,
                            Class<Else> defaultClass, Runnable defaultConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                varClazz, varPredicate, varConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T> void match(V value,
                                    Class<T> clazz, Predicate<T> predicate, Consumer<T> consumer,
                                    Class<Null> nullClass, Runnable nullConsumer,
                                    Class<Var> varClass, Predicate<V> varPredicate, Runnable varConsumer,
                                    Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, clazz, predicate, consumer,
                    varClass, varPredicate, varConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    public <V, T> void as(Class<T> clazz, Predicate<T> predicate, Consumer<T> consumer,
                          Class<Null> nullClass, Runnable nullConsumer,
                          Class<Var> varClass, Predicate<V> varPredicate, Runnable varConsumer,
                          Class<Else> defaultClass, Runnable defaultConsumer) {
        match((V) value, clazz, predicate, consumer,
                nullClass, nullConsumer,
                varClass, varPredicate, varConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T, R> R match(V value,
                                    Class<T> clazz, Predicate<T> predicate, Function<T, R> function) {
        Class<?> valueClass = value.getClass();

        if (clazz == valueClass || Reflection.isPrimitive(clazz, valueClass)) {
            if (predicate.test((T) value)) {
                return function.apply((T) value);
            }
        }

        return null;
    }

    public <V, T, R> R as(Class<T> clazz, Predicate<T> predicate, Function<T, R> function) {
        return match((V) value, clazz, predicate, function);
    }

    public static <V, T, R> R match(V value,
                                    Class<T> clazz, Predicate<T> predicate, Function<T, R> function,
                                    Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = match(value, clazz, predicate, function);

        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    public <V, T, R> R as(Class<T> clazz, Predicate<T> predicate, Function<T, R> function,
                          Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        return match((V) value,
                     clazz, predicate, function,
                     defaultClass, defaultSupplier);
    }

    public static <V, T, R> R match(V value,
                                    Class<T> clazz, Predicate<T> predicate, Function<T, R> function,
                                    Class<Var> varClass, Function<V, R> varFunction) {
        R result = match(value, clazz, predicate, function);

        if (result != null) {
            return result;
        } else {
            return varFunction.apply(value);
        }
    }

    public <V, T, R> R as(Class<T> clazz, Predicate<T> predicate, Function<T, R> function,
                          Class<Var> varClass, Function<V, R> varFunction) {
        return match((V) value,
                     clazz, predicate, function,
                     varClass, varFunction);
    }

    public static <V, T, R> R match(V value,
                                    Class<T> clazz, Predicate<T> predicate, Function<T, R> function,
                                    Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                    Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = match(value, clazz, predicate, function);

        if (result != null) {
            return result;
        } else {
            if (varPredicate.test(value)) {
                return varSupplier.get();
            } else {
                return defaultSupplier.get();
            }
        }
    }

    public <V, T, R> R as(Class<T> clazz, Predicate<T> predicate, Function<T, R> function,
                          Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                          Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        return match((V) value,
                     clazz, predicate, function,
                     varClass, varPredicate, varSupplier,
                     defaultClass, defaultSupplier);
    }

    public static <V, T, R> R match(V value,
                                    Class<T> clazz, Predicate<T> predicate, Function<T, R> function,
                                    Class<Null> nullClass, Supplier<R> nullSupplier,
                                    Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, clazz, predicate, function, defaultClass, defaultSupplier);
        }
    }

    public <V, T, R> R as(Class<T> clazz, Predicate<T> predicate, Function<T, R> function,
                          Class<Null> nullClass, Supplier<R> nullSupplier,
                          Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        return match((V) value,
                     clazz, predicate, function,
                     nullClass, nullSupplier,
                     defaultClass, defaultSupplier);
    }

    public static <V, T, R> R match(V value,
                                    Class<T> clazz, Predicate<T> predicate, Function<T, R> function,
                                    Class<Null> nullClass, Supplier<R> nullSupplier,
                                    Class<Var> varClass, Function<V, R> varFunction) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, clazz, predicate, function, varClass, varFunction);
        }
    }


    public static <V, T, R> R match(V value,
                                    Class<T> clazz, Predicate<T> predicate, Function<T, R> function,
                                    Class<Null> nullClass, Supplier<R> nullSupplier,
                                    Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                    Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, clazz, predicate, function,
                    varClass, varPredicate, varSupplier,
                    defaultClass, defaultSupplier);
        }
    }

    public static <V, T1, T2> void match(V value,
                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                secondConsumer.accept((T2) value);
            }
        }
    }

    public <T1, T2> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                            Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer) {
        match(value,
                firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer);
    }

    public static <V, T1, T2> void match(V value,
                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                         Class<Else> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                secondConsumer.accept((T2) value);
                return;
            }
        }

        defaultConsumer.run();
    }

    public <T1, T2> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                            Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                            Class<Else> defaultClass, Runnable defaultConsumer) {
        match(value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T1, T2> void match(V value,
                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                         Class<Var> varClass, Consumer<V> varConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                secondConsumer.accept((T2) value);
                return;
            }
        }

        varConsumer.accept(value);
    }

    public <V, T1, T2> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                               Class<Var> varClass, Consumer<V> varConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                varClass, varConsumer);
    }

    public static <V, T1, T2> void match(V value,
                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                         Class<Null> nullClass, Runnable nullConsumer,
                                         Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, firstClazz, firstPredicate, firstConsumer,
                    secondClazz, secondPredicate, secondConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    public <V, T1, T2> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                               Class<Null> nullClass, Runnable nullConsumer,
                               Class<Else> defaultClass, Runnable defaultConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                nullClass, nullConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T1, T2> void match(V value,
                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                         Class<Null> nullClass, Runnable nullConsumer,
                                         Class<Var> varClass, Consumer<V> varConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, firstClazz, firstPredicate, firstConsumer,
                    secondClazz, secondPredicate, secondConsumer,
                    varClass, varConsumer);
        }
    }

    public <V, T1, T2> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                               Class<Null> nullClass, Runnable nullConsumer,
                               Class<Var> varClass, Consumer<V> varConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                nullClass, nullConsumer,
                varClass, varConsumer);
    }

    public static <V, T1, T2> void match(V value,
                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                         Class<Null> nullClass, Runnable nullConsumer,
                                         Class<Var> varClass, Predicate<V> varPredicate, Runnable varConsumer,
                                         Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, firstClazz, firstPredicate, firstConsumer,
                    secondClazz, secondPredicate, secondConsumer,
                    varClass, varPredicate, varConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    public <V, T1, T2> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                               Class<Null> nullClass, Runnable nullConsumer,
                               Class<Var> varClass, Predicate<V> varPredicate, Runnable varConsumer,
                               Class<Else> defaultClass, Runnable defaultConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                nullClass, nullConsumer,
                varClass, varPredicate, varConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T1, T2> void match(V value,
                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                         Class<Var> varClazz, Predicate<V> varPredicate, Runnable varConsumer,
                                         Class<Else> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                secondConsumer.accept((T2) value);
                return;
            }
        }

        if (varPredicate.test(value)) {
            varConsumer.run();
        } else {
            defaultConsumer.run();
        }
    }

    public <V, T1, T2> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                               Class<Var> varClazz, Predicate<V> varPredicate, Runnable varConsumer,
                               Class<Else> defaultClass, Runnable defaultConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                varClazz, varPredicate, varConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T1, T2, R> R match(V value,
                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                return firstFunction.apply((T1) value);
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                return secondFunction.apply((T2) value);
            }
        }

        return null;
    }

    public <V, T1, T2, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction) {
        return match((V) value,
                    firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction);
    }

    public static <V, T1, T2, R> R match(V value,
                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                         Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = match(value, firstClazz, firstPredicate, firstFunction,
                secondClazz, secondPredicate, secondFunction);
        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    public <V, T1, T2, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                               Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        return match((V) value,
                     firstClazz, firstPredicate, firstFunction,
                     secondClazz, secondPredicate, secondFunction,
                     defaultClass, defaultSupplier);
    }

    public static <V, T1, T2, R> R match(V value,
                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                         Class<Var> varClass, Function<V, R> varFunction) {
        R result = match(value, firstClazz, firstPredicate, firstFunction,
                secondClazz, secondPredicate, secondFunction);
        if (result != null) {
            return result;
        } else {
            return varFunction.apply(value);
        }
    }

    public <V, T1, T2, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                               Class<Var> varClass, Function<V, R> varFunction) {
        return match((V) value,
                     firstClazz, firstPredicate, firstFunction,
                     secondClazz, secondPredicate, secondFunction,
                     varClass, varFunction);
    }

    public static <V, T1, T2, R> R match(V value,
                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                         Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                         Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = match(value, firstClazz, firstPredicate, firstFunction,
                secondClazz, secondPredicate, secondFunction);

        if (result != null) {
            return result;
        } else {
            if (varPredicate.test(value)) {
                return varSupplier.get();
            } else {
                return defaultSupplier.get();
            }
        }
    }

    public <V, T1, T2, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                               Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                               Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        return match((V) value,
                    firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    varClass, varPredicate, varSupplier,
                    defaultClass, defaultSupplier);
    }

    public static <V, T1, T2, R> R match(V value,
                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                         Class<Null> nullClass, Supplier<R> nullSupplier,
                                         Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    defaultClass, defaultSupplier);
        }
    }

    public <V, T1, T2, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                               Class<Null> nullClass, Supplier<R> nullSupplier,
                               Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        return match((V) value,
                    firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    nullClass, nullSupplier,
                    defaultClass, defaultSupplier);
    }

    public static <V, T1, T2, R> R match(V value,
                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                         Class<Null> nullClass, Supplier<R> nullSupplier,
                                         Class<Var> varClass, Function<V, R> varFunction) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    varClass, varFunction);
        }
    }


    public static <V, T1, T2, R> R match(V value,
                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                         Class<Null> nullClass, Supplier<R> nullSupplier,
                                         Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                         Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    varClass, varPredicate, varSupplier,
                    defaultClass, defaultSupplier);
        }
    }

    public static <V, T1, T2, T3> void match(V value,
                                             Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                             Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                             Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                secondConsumer.accept((T2) value);
                return;
            }
        }

        if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            if (thirdPredicate.test((T3) value)) {
                thirdConsumer.accept((T3) value);
            }
        }
    }

    public <T1, T2, T3> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer) {
        match(value,
                firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer);
    }

    public static <V, T1, T2, T3> void match(V value,
                                             Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                             Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                             Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                             Class<Else> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                secondConsumer.accept((T2) value);
                return;
            }
        }

        if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            if (thirdPredicate.test((T3) value)) {
                thirdConsumer.accept((T3) value);
                return;
            }
        }

        defaultConsumer.run();
    }

    public <T1, T2, T3> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                Class<Else> defaultClass, Runnable defaultConsumer) {
        match(value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T1, T2, T3> void match(V value,
                                             Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                             Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                             Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                             Class<Var> varClass, Consumer<V> varConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                secondConsumer.accept((T2) value);
                return;
            }
        }

        if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            if (thirdPredicate.test((T3) value)) {
                thirdConsumer.accept((T3) value);
                return;
            }
        }

        varConsumer.accept(value);
    }

    public <V, T1, T2, T3> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                   Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                   Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                   Class<Var> varClass, Consumer<V> varConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                varClass, varConsumer);
    }

    public static <V, T1, T2, T3> void match(V value,
                                             Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                             Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                             Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                             Class<Null> nullClass, Runnable nullConsumer,
                                             Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, firstClazz, firstPredicate, firstConsumer,
                    secondClazz, secondPredicate, secondConsumer,
                    thirdClazz, thirdPredicate, thirdConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    public <V, T1, T2, T3> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                   Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                   Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                   Class<Null> nullClass, Runnable nullConsumer,
                                   Class<Else> defaultClass, Runnable defaultConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                nullClass, nullConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T1, T2, T3> void match(V value,
                                             Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                             Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                             Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                             Class<Null> nullClass, Runnable nullConsumer,
                                             Class<Var> varClass, Consumer<V> varConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, firstClazz, firstPredicate, firstConsumer,
                    secondClazz, secondPredicate, secondConsumer,
                    thirdClazz, thirdPredicate, thirdConsumer,
                    varClass, varConsumer);
        }
    }

    public <V, T1, T2, T3> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                   Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                   Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                   Class<Null> nullClass, Runnable nullConsumer,
                                   Class<Var> varClass, Consumer<V> varConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                nullClass, nullConsumer,
                varClass, varConsumer);
    }

    public static <V, T1, T2, T3> void match(V value,
                                             Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                             Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                             Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                             Class<Null> nullClass, Runnable nullConsumer,
                                             Class<Var> varClass, Predicate<V> varPredicate, Runnable varConsumer,
                                             Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, firstClazz, firstPredicate, firstConsumer,
                    secondClazz, secondPredicate, secondConsumer,
                    thirdClazz, thirdPredicate, thirdConsumer,
                    varClass, varPredicate, varConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    public <V, T1, T2, T3> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                   Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                   Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                   Class<Null> nullClass, Runnable nullConsumer,
                                   Class<Var> varClass, Predicate<V> varPredicate, Runnable varConsumer,
                                   Class<Else> defaultClass, Runnable defaultConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                nullClass, nullConsumer,
                varClass, varPredicate, varConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T1, T2, T3> void match(V value,
                                             Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                             Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                             Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                             Class<Var> varClazz, Predicate<V> varPredicate, Runnable varConsumer,
                                             Class<Else> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                secondConsumer.accept((T2) value);
                return;
            }
        }

        if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            if (thirdPredicate.test((T3) value)) {
                thirdConsumer.accept((T3) value);
                return;
            }
        }

        if (varPredicate.test(value)) {
            varConsumer.run();
        } else {
            defaultConsumer.run();
        }
    }

    public <V, T1, T2, T3> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                   Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                   Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                   Class<Var> varClazz, Predicate<V> varPredicate, Runnable varConsumer,
                                   Class<Else> defaultClass, Runnable defaultConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                varClazz, varPredicate, varConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T1, T2, T3, R> R match(V value,
                                             Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                             Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                             Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                return firstFunction.apply((T1) value);
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                return secondFunction.apply((T2) value);
            }
        }

        if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            if (thirdPredicate.test((T3) value)) {
                return thirdFunction.apply((T3) value);
            }
        }

        return null;
    }

    public <V, T1, T2, T3, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                   Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                   Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction) {
        return match((V) value,
                firstClazz, firstPredicate, firstFunction,
                secondClazz, secondPredicate, secondFunction,
                thirdClazz, thirdPredicate, thirdFunction);
    }

    public static <V, T1, T2, T3, R> R match(V value,
                                             Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                             Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                             Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                             Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = match(value, firstClazz, firstPredicate, firstFunction, secondClazz, secondPredicate, secondFunction,
                thirdClazz, thirdPredicate, thirdFunction);
        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    public <V, T1, T2, T3, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                   Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                   Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                   Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        return match((V) value,
                firstClazz, firstPredicate, firstFunction,
                secondClazz, secondPredicate, secondFunction,
                thirdClazz, thirdPredicate, thirdFunction,
                defaultClass, defaultSupplier);
    }

    public static <V, T1, T2, T3, R> R match(V value,
                                             Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                             Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                             Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                             Class<Var> varClass, Function<V, R> varFunction) {
        R result = match(value, firstClazz, firstPredicate, firstFunction,
                secondClazz, secondPredicate, secondFunction,
                thirdClazz, thirdPredicate, thirdFunction);
        if (result != null) {
            return result;
        } else {
            return varFunction.apply(value);
        }
    }

    public <V, T1, T2, T3, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                   Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                   Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                   Class<Var> varClass, Function<V, R> varFunction) {
        return match((V) value,
                    firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    varClass, varFunction);
    }

    public static <V, T1, T2, T3, R> R match(V value,
                                             Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                             Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                             Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                             Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                             Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = match(value, firstClazz, firstPredicate, firstFunction,
                secondClazz, secondPredicate, secondFunction,
                thirdClazz, thirdPredicate, thirdFunction);

        if (result != null) {
            return result;
        } else {
            if (varPredicate.test(value)) {
                return varSupplier.get();
            } else {
                return defaultSupplier.get();
            }
        }
    }

    public <V, T1, T2, T3, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                   Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                   Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                   Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                   Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        return match((V) value,
                    firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    varClass, varPredicate, varSupplier,
                    defaultClass, defaultSupplier);
    }

    public static <V, T1, T2, T3, R> R match(V value,
                                             Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                             Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                             Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                             Class<Null> nullClass, Supplier<R> nullSupplier,
                                             Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    defaultClass, defaultSupplier);
        }
    }

    public <V, T1, T2, T3, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                   Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                   Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                   Class<Null> nullClass, Supplier<R> nullSupplier,
                                   Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        return match((V) value,
                    firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    nullClass, nullSupplier,
                    defaultClass, defaultSupplier);
    }

    public static <V, T1, T2, T3, R> R match(V value,
                                             Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                             Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                             Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                             Class<Null> nullClass, Supplier<R> nullSupplier,
                                             Class<Var> varClass, Function<V, R> varFunction) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    varClass, varFunction);
        }
    }


    public static <V, T1, T2, T3, R> R match(V value,
                                             Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                             Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                             Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                             Class<Null> nullClass, Supplier<R> nullSupplier,
                                             Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                             Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    varClass, varPredicate, varSupplier,
                    defaultClass, defaultSupplier);
        }
    }

    public static <V, T1, T2, T3, T4> void match(V value,
                                                 Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                 Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                 Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                 Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                secondConsumer.accept((T2) value);
                return;
            }
        }

        if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            if (thirdPredicate.test((T3) value)) {
                thirdConsumer.accept((T3) value);
                return;
            }
        }

        if (fourthClazz == valueClass || Reflection.isPrimitive(fourthClazz, valueClass)) {
            if (fourthPredicate.test((T4) value)) {
                fourthConsumer.accept((T4) value);
            }
        }
    }

    public <T1, T2, T3, T4> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                    Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                    Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                    Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer) {
        match(value,
                firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer);
    }

    public static <V, T1, T2, T3, T4> void match(V value,
                                                 Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                 Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                 Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                 Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                 Class<Else> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                secondConsumer.accept((T2) value);
                return;
            }
        }

        if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            if (thirdPredicate.test((T3) value)) {
                thirdConsumer.accept((T3) value);
                return;
            }
        }

        if (fourthClazz == valueClass || Reflection.isPrimitive(fourthClazz, valueClass)) {
            if (fourthPredicate.test((T4) value)) {
                fourthConsumer.accept((T4) value);
                return;
            }
        }

        defaultConsumer.run();
    }

    public <T1, T2, T3, T4> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                    Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                    Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                    Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                    Class<Else> defaultClass, Runnable defaultConsumer) {
        match(value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T1, T2, T3, T4> void match(V value,
                                                 Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                 Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                 Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                 Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                 Class<Var> varClass, Consumer<V> varConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                secondConsumer.accept((T2) value);
                return;
            }
        }

        if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            if (thirdPredicate.test((T3) value)) {
                thirdConsumer.accept((T3) value);
                return;
            }
        }

        if (fourthClazz == valueClass || Reflection.isPrimitive(fourthClazz, valueClass)) {
            if (fourthPredicate.test((T4) value)) {
                fourthConsumer.accept((T4) value);
                return;
            }
        }

        varConsumer.accept(value);
    }

    public <V, T1, T2, T3, T4> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                       Class<Var> varClass, Consumer<V> varConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer,
                varClass, varConsumer);
    }

    public static <V, T1, T2, T3, T4> void match(V value,
                                                 Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                 Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                 Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                 Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                 Class<Null> nullClass, Runnable nullConsumer,
                                                 Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, firstClazz, firstPredicate, firstConsumer,
                    secondClazz, secondPredicate, secondConsumer,
                    thirdClazz, thirdPredicate, thirdConsumer,
                    fourthClazz, fourthPredicate, fourthConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    public <V, T1, T2, T3, T4> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                       Class<Null> nullClass, Runnable nullConsumer,
                                       Class<Else> defaultClass, Runnable defaultConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer,
                nullClass, nullConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T1, T2, T3, T4> void match(V value,
                                                 Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                 Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                 Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                 Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                 Class<Null> nullClass, Runnable nullConsumer,
                                                 Class<Var> varClass, Consumer<V> varConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, firstClazz, firstPredicate, firstConsumer,
                    secondClazz, secondPredicate, secondConsumer,
                    thirdClazz, thirdPredicate, thirdConsumer,
                    fourthClazz, fourthPredicate, fourthConsumer,
                    varClass, varConsumer);
        }
    }

    public <V, T1, T2, T3, T4> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                       Class<Null> nullClass, Runnable nullConsumer,
                                       Class<Var> varClass, Consumer<V> varConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer,
                nullClass, nullConsumer,
                varClass, varConsumer);
    }

    public static <V, T1, T2, T3, T4> void match(V value,
                                                 Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                 Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                 Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                 Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                 Class<Null> nullClass, Runnable nullConsumer,
                                                 Class<Var> varClass, Predicate<V> varPredicate, Runnable varConsumer,
                                                 Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, firstClazz, firstPredicate, firstConsumer,
                    secondClazz, secondPredicate, secondConsumer,
                    thirdClazz, thirdPredicate, thirdConsumer,
                    fourthClazz, fourthPredicate, fourthConsumer,
                    varClass, varPredicate, varConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    public <V, T1, T2, T3, T4> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                       Class<Null> nullClass, Runnable nullConsumer,
                                       Class<Var> varClass, Predicate<V> varPredicate, Runnable varConsumer,
                                       Class<Else> defaultClass, Runnable defaultConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer,
                nullClass, nullConsumer,
                varClass, varPredicate, varConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T1, T2, T3, T4> void match(V value,
                                                 Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                 Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                 Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                 Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                 Class<Var> varClazz, Predicate<V> varPredicate, Runnable varConsumer,
                                                 Class<Else> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                secondConsumer.accept((T2) value);
                return;
            }
        }

        if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            if (thirdPredicate.test((T3) value)) {
                thirdConsumer.accept((T3) value);
                return;
            }
        }

        if (fourthClazz == valueClass || Reflection.isPrimitive(fourthClazz, valueClass)) {
            if (fourthPredicate.test((T4) value)) {
                fourthConsumer.accept((T4) value);
                return;
            }
        }

        if (varPredicate.test(value)) {
            varConsumer.run();
        } else {
            defaultConsumer.run();
        }
    }

    public <V, T1, T2, T3, T4> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                       Class<Var> varClazz, Predicate<V> varPredicate, Runnable varConsumer,
                                       Class<Else> defaultClass, Runnable defaultConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer,
                varClazz, varPredicate, varConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T1, T2, T3, T4, R> R match(V value,
                                                 Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                 Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                 Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                 Class<T4> forthClazz, Predicate<T4> fourthPredicate, Function<T4, R> forthFunction) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                return firstFunction.apply((T1) value);
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                return secondFunction.apply((T2) value);
            }
        }

        if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            if (thirdPredicate.test((T3) value)) {
                return thirdFunction.apply((T3) value);
            }
        }

        if (forthClazz == valueClass || Reflection.isPrimitive(forthClazz, valueClass)) {
            if (fourthPredicate.test((T4) value)) {
                return forthFunction.apply((T4) value);
            }
        }

        return null;
    }

    public <V, T1, T2, T3, T4, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Function<T4, R> fourthFunction) {
        return match((V) value,
                firstClazz, firstPredicate, firstFunction,
                secondClazz, secondPredicate, secondFunction,
                thirdClazz, thirdPredicate, thirdFunction,
                fourthClazz, fourthPredicate, fourthFunction);
    }

    public static <V, T1, T2, T3, T4, R> R match(V value,
                                                 Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                 Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                 Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                 Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                 Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = match(value, firstClazz, firstPredicate, firstFunction, secondClazz, secondPredicate, secondFunction,
                thirdClazz, thirdPredicate, thirdFunction, forthClazz, forthPredicate, forthFunction);
        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    public <V, T1, T2, T3, T4, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Function<T4, R> fourthFunction,
                                       Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        return match((V) value,
                firstClazz, firstPredicate, firstFunction,
                secondClazz, secondPredicate, secondFunction,
                fourthClazz, fourthPredicate, fourthFunction,
                thirdClazz, thirdPredicate, thirdFunction,
                defaultClass, defaultSupplier);
    }

    public static <V, T1, T2, T3, T4, R> R match(V value,
                                                 Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                 Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                 Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                 Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                 Class<Var> varClass, Function<V, R> varFunction) {
        R result = match(value, firstClazz, firstPredicate, firstFunction,
                secondClazz, secondPredicate, secondFunction,
                thirdClazz, thirdPredicate, thirdFunction,
                forthClazz, forthPredicate, forthFunction);
        if (result != null) {
            return result;
        } else {
            return varFunction.apply(value);
        }
    }

    public <V, T1, T2, T3, T4, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Function<T4, R> fourthFunction,
                                       Class<Var> varClass, Function<V, R> varFunction) {
        return match((V) value,
                    firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    fourthClazz, fourthPredicate, fourthFunction,
                    varClass, varFunction);
    }

    public static <V, T1, T2, T3, T4, R> R match(V value,
                                                 Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                 Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                 Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                 Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                 Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                                 Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = match(value, firstClazz, firstPredicate, firstFunction,
                secondClazz, secondPredicate, secondFunction,
                thirdClazz, thirdPredicate, thirdFunction,
                forthClazz, forthPredicate, forthFunction);
        if (result != null) {
            return result;
        } else {
            if (varPredicate.test(value)) {
                return varSupplier.get();
            } else {
                return defaultSupplier.get();
            }
        }
    }

    public <V, T1, T2, T3, T4, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Function<T4, R> fourthFunction,
                                       Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                       Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        return match((V) value,
                    firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    fourthClazz, fourthPredicate, fourthFunction,
                    varClass, varPredicate, varSupplier,
                    defaultClass, defaultSupplier);
    }

    public static <V, T1, T2, T3, T4, R> R match(V value,
                                                 Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                 Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                 Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                 Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                 Class<Null> nullClass, Supplier<R> nullSupplier,
                                                 Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    forthClazz, forthPredicate, forthFunction,
                    defaultClass, defaultSupplier);
        }
    }

    public <V, T1, T2, T3, T4, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Function<T4, R> fourthFunction,
                                       Class<Null> nullClass, Supplier<R> nullSupplier,
                                       Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        return match((V) value,
                    firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    fourthClazz, fourthPredicate, fourthFunction,
                    nullClass, nullSupplier,
                    defaultClass, defaultSupplier);
    }

    public static <V, T1, T2, T3, T4, R> R match(V value,
                                                 Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                 Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                 Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                 Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                 Class<Null> nullClass, Supplier<R> nullSupplier,
                                                 Class<Var> varClass, Function<V, R> varFunction) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    forthClazz, forthPredicate, forthFunction,
                    varClass, varFunction);
        }
    }


    public static <V, T1, T2, T3, T4, R> R match(V value,
                                                 Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                 Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                 Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                 Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                 Class<Null> nullClass, Supplier<R> nullSupplier,
                                                 Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                                 Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    forthClazz, forthPredicate, forthFunction,
                    varClass, varPredicate, varSupplier,
                    defaultClass, defaultSupplier);
        }
    }

    public static <V, T1, T2, T3, T4, T5> void match(V value,
                                                     Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                     Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                     Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                     Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                     Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                secondConsumer.accept((T2) value);
                return;
            }
        }

        if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            if (thirdPredicate.test((T3) value)) {
                thirdConsumer.accept((T3) value);
                return;
            }
        }

        if (fourthClazz == valueClass || Reflection.isPrimitive(fourthClazz, valueClass)) {
            if (fourthPredicate.test((T4) value)) {
                fourthConsumer.accept((T4) value);
                return;
            }
        }

        if (fifthClazz == valueClass || Reflection.isPrimitive(fifthClazz, valueClass)) {
            if (fifthPredicate.test((T5) value)) {
                fifthConsumer.accept((T5) value);
            }
        }
    }

    public <T1, T2, T3, T4, T5> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                        Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                        Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                        Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                        Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer) {
        match(value,
                firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer,
                fifthClazz, fifthPredicate, fifthConsumer);
    }

    public static <V, T1, T2, T3, T4, T5, R> R match(V value,
                                                     Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                     Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                     Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                     Class<T4> forthClazz, Predicate<T4> fourthPredicate, Function<T4, R> forthFunction,
                                                     Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                return firstFunction.apply((T1) value);
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                return secondFunction.apply((T2) value);
            }
        }

        if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            if (thirdPredicate.test((T3) value)) {
                return thirdFunction.apply((T3) value);
            }
        }

        if (forthClazz == valueClass || Reflection.isPrimitive(forthClazz, valueClass)) {
            if (fourthPredicate.test((T4) value)) {
                return forthFunction.apply((T4) value);
            }
        }

        if (fifthClazz == valueClass || Reflection.isPrimitive(fifthClazz, valueClass)) {
            if (fifthPredicate.test((T5) value)) {
                return fifthFunction.apply((T5) value);
            }
        }

        return null;
    }

    public <V, T1, T2, T3, T4, T5, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                           Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                           Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Function<T4, R> fourthFunction,
                                           Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction) {
        return match((V) value,
                firstClazz, firstPredicate, firstFunction,
                secondClazz, secondPredicate, secondFunction,
                thirdClazz, thirdPredicate, thirdFunction,
                fourthClazz, fourthPredicate, fourthFunction,
                fifthClazz, fifthPredicate, fifthFunction);
    }

    public static <V, T1, T2, T3, T4, T5> void match(V value,
                                                     Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                     Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                     Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                     Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                     Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                                     Class<Else> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                secondConsumer.accept((T2) value);
                return;
            }
        }

        if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            if (thirdPredicate.test((T3) value)) {
                thirdConsumer.accept((T3) value);
                return;
            }
        }

        if (fourthClazz == valueClass || Reflection.isPrimitive(fourthClazz, valueClass)) {
            if (fourthPredicate.test((T4) value)) {
                fourthConsumer.accept((T4) value);
                return;
            }
        }

        if (fifthClazz == valueClass || Reflection.isPrimitive(fifthClazz, valueClass)) {
            if (fifthPredicate.test((T5) value)) {
                fifthConsumer.accept((T5) value);
                return;
            }
        }

        defaultConsumer.run();
    }

    public <T1, T2, T3, T4, T5> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                        Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                        Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                        Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                        Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                        Class<Else> defaultClass, Runnable defaultConsumer) {
        match(value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer,
                fifthClazz, fifthPredicate, fifthConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T1, T2, T3, T4, T5> void match(V value,
                                                     Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                     Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                     Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                     Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                     Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                                     Class<Var> varClass, Consumer<V> varConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                secondConsumer.accept((T2) value);
                return;
            }
        }

        if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            if (thirdPredicate.test((T3) value)) {
                thirdConsumer.accept((T3) value);
                return;
            }
        }

        if (fourthClazz == valueClass || Reflection.isPrimitive(fourthClazz, valueClass)) {
            if (fourthPredicate.test((T4) value)) {
                fourthConsumer.accept((T4) value);
                return;
            }
        }

        if (fifthClazz == valueClass || Reflection.isPrimitive(fifthClazz, valueClass)) {
            if (fifthPredicate.test((T5) value)) {
                fifthConsumer.accept((T5) value);
                return;
            }
        }

        varConsumer.accept(value);
    }

    public <V, T1, T2, T3, T4, T5> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                           Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                           Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                           Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                           Class<Var> varClass, Consumer<V> varConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer,
                fifthClazz, fifthPredicate, fifthConsumer,
                varClass, varConsumer);
    }

    public static <V, T1, T2, T3, T4, T5> void match(V value,
                                                     Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                     Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                     Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                     Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                     Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                                     Class<Null> nullClass, Runnable nullConsumer,
                                                     Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, firstClazz, firstPredicate, firstConsumer,
                    secondClazz, secondPredicate, secondConsumer,
                    thirdClazz, thirdPredicate, thirdConsumer,
                    fourthClazz, fourthPredicate, fourthConsumer,
                    fifthClazz, fifthPredicate, fifthConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    public <V, T1, T2, T3, T4, T5> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                           Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                           Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                           Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                           Class<Null> nullClass, Runnable nullConsumer,
                                           Class<Else> defaultClass, Runnable defaultConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer,
                fifthClazz, fifthPredicate, fifthConsumer,
                nullClass, nullConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T1, T2, T3, T4, T5> void match(V value,
                                                     Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                     Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                     Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                     Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                     Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                                     Class<Null> nullClass, Runnable nullConsumer,
                                                     Class<Var> varClass, Consumer<V> varConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, firstClazz, firstPredicate, firstConsumer,
                    secondClazz, secondPredicate, secondConsumer,
                    thirdClazz, thirdPredicate, thirdConsumer,
                    fourthClazz, fourthPredicate, fourthConsumer,
                    fifthClazz, fifthPredicate, fifthConsumer,
                    varClass, varConsumer);
        }
    }

    public <V, T1, T2, T3, T4, T5> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                           Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                           Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                           Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                           Class<Null> nullClass, Runnable nullConsumer,
                                           Class<Var> varClass, Consumer<V> varConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer,
                fifthClazz, fifthPredicate, fifthConsumer,
                nullClass, nullConsumer,
                varClass, varConsumer);
    }

    public static <V, T1, T2, T3, T4, T5> void match(V value,
                                                     Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                     Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                     Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                     Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                     Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                                     Class<Null> nullClass, Runnable nullConsumer,
                                                     Class<Var> varClass, Predicate<V> varPredicate, Runnable varConsumer,
                                                     Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, firstClazz, firstPredicate, firstConsumer,
                    secondClazz, secondPredicate, secondConsumer,
                    thirdClazz, thirdPredicate, thirdConsumer,
                    fourthClazz, fourthPredicate, fourthConsumer,
                    fifthClazz, fifthPredicate, fifthConsumer,
                    varClass, varPredicate, varConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    public <V, T1, T2, T3, T4, T5> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                           Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                           Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                           Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                           Class<Null> nullClass, Runnable nullConsumer,
                                           Class<Var> varClass, Predicate<V> varPredicate, Runnable varConsumer,
                                           Class<Else> defaultClass, Runnable defaultConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer,
                fifthClazz, fifthPredicate, fifthConsumer,
                nullClass, nullConsumer,
                varClass, varPredicate, varConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T1, T2, T3, T4, T5> void match(V value,
                                                     Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                     Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                     Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                     Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                     Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                                     Class<Var> varClazz, Predicate<V> varPredicate, Runnable varConsumer,
                                                     Class<Else> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                secondConsumer.accept((T2) value);
                return;
            }
        }

        if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            if (thirdPredicate.test((T3) value)) {
                thirdConsumer.accept((T3) value);
                return;
            }
        }

        if (fourthClazz == valueClass || Reflection.isPrimitive(fourthClazz, valueClass)) {
            if (fourthPredicate.test((T4) value)) {
                fourthConsumer.accept((T4) value);
                return;
            }
        }

        if (fifthClazz == valueClass || Reflection.isPrimitive(fifthClazz, valueClass)) {
            if (fifthPredicate.test((T5) value)) {
                fifthConsumer.accept((T5) value);
                return;
            }
        }

        if (varPredicate.test(value)) {
            varConsumer.run();
        } else {
            defaultConsumer.run();
        }
    }

    public <V, T1, T2, T3, T4, T5> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                           Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                           Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                           Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                           Class<Var> varClazz, Predicate<V> varPredicate, Runnable varConsumer,
                                           Class<Else> defaultClass, Runnable defaultConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer,
                fifthClazz, fifthPredicate, fifthConsumer,
                varClazz, varPredicate, varConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T1, T2, T3, T4, T5, R> R match(V value,
                                                     Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                     Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                     Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                     Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                     Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                                     Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = match(value, firstClazz, firstPredicate, firstFunction, secondClazz, secondPredicate, secondFunction,
                thirdClazz, thirdPredicate, thirdFunction, forthClazz, forthPredicate, forthFunction,
                fifthClazz, fifthPredicate, fifthFunction);
        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    public <V, T1, T2, T3, T4, T5, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                           Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                           Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Function<T4, R> fourthFunction,
                                           Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                           Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        return match((V) value,
                firstClazz, firstPredicate, firstFunction,
                secondClazz, secondPredicate, secondFunction,
                fourthClazz, fourthPredicate, fourthFunction,
                thirdClazz, thirdPredicate, thirdFunction,
                fifthClazz, fifthPredicate, fifthFunction,
                defaultClass, defaultSupplier);
    }

    public static <V, T1, T2, T3, T4, T5, R> R match(V value,
                                                     Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                     Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                     Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                     Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                     Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                                     Class<Var> varClass, Function<V, R> varFunction) {
        R result = match(value, firstClazz, firstPredicate, firstFunction,
                secondClazz, secondPredicate, secondFunction,
                thirdClazz, thirdPredicate, thirdFunction,
                forthClazz, forthPredicate, forthFunction,
                fifthClazz, fifthPredicate, fifthFunction);
        if (result != null) {
            return result;
        } else {
            return varFunction.apply(value);
        }
    }

    public <V, T1, T2, T3, T4, T5, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                           Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                           Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Function<T4, R> fourthFunction,
                                           Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                           Class<Var> varClass, Function<V, R> varFunction) {
        return match((V) value,
                    firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    fourthClazz, fourthPredicate, fourthFunction,
                    fifthClazz, fifthPredicate, fifthFunction,
                    varClass, varFunction);
    }

    public static <V, T1, T2, T3, T4, T5, R> R match(V value,
                                                     Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                     Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                     Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                     Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                     Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                                     Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                                     Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = match(value, firstClazz, firstPredicate, firstFunction,
                secondClazz, secondPredicate, secondFunction,
                thirdClazz, thirdPredicate, thirdFunction,
                forthClazz, forthPredicate, forthFunction,
                fifthClazz, fifthPredicate, fifthFunction);
        if (result != null) {
            return result;
        } else {
            if (varPredicate.test(value)) {
                return varSupplier.get();
            } else {
                return defaultSupplier.get();
            }
        }
    }

    public <V, T1, T2, T3, T4, T5, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                           Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                           Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Function<T4, R> fourthFunction,
                                           Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                           Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                           Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        return match((V) value,
                    firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    fourthClazz, fourthPredicate, fourthFunction,
                    fifthClazz, fifthPredicate, fifthFunction,
                    varClass, varPredicate, varSupplier,
                    defaultClass, defaultSupplier);
    }

    public static <V, T1, T2, T3, T4, T5, R> R match(V value,
                                                     Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                     Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                     Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                     Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                     Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                                     Class<Null> nullClass, Supplier<R> nullSupplier,
                                                     Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    forthClazz, forthPredicate, forthFunction,
                    fifthClazz, fifthPredicate, fifthFunction,
                    defaultClass, defaultSupplier);
        }
    }

    public <V, T1, T2, T3, T4, T5, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                           Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                           Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Function<T4, R> fourthFunction,
                                           Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                           Class<Null> nullClass, Supplier<R> nullSupplier,
                                           Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        return match((V) value,
                    firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    fourthClazz, fourthPredicate, fourthFunction,
                    fifthClazz, fifthPredicate, fifthFunction,
                    nullClass, nullSupplier,
                    defaultClass, defaultSupplier);
    }

    public static <V, T1, T2, T3, T4, T5, R> R match(V value,
                                                     Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                     Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                     Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                     Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                     Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                                     Class<Null> nullClass, Supplier<R> nullSupplier,
                                                     Class<Var> varClass, Function<V, R> varFunction) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    forthClazz, forthPredicate, forthFunction,
                    fifthClazz, fifthPredicate, fifthFunction,
                    varClass, varFunction);
        }
    }


    public static <V, T1, T2, T3, T4, T5, R> R match(V value,
                                                     Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                     Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                     Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                     Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                     Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                                     Class<Null> nullClass, Supplier<R> nullSupplier,
                                                     Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                                     Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    forthClazz, forthPredicate, forthFunction,
                    fifthClazz, fifthPredicate, fifthFunction,
                    varClass, varPredicate, varSupplier,
                    defaultClass, defaultSupplier);
        }
    }

    public static <V, T1, T2, T3, T4, T5, T6> void match(V value,
                                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                         Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                         Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                         Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                                         Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Consumer<T6> sixthConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                secondConsumer.accept((T2) value);
                return;
            }
        }

        if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            if (thirdPredicate.test((T3) value)) {
                thirdConsumer.accept((T3) value);
                return;
            }
        }

        if (fourthClazz == valueClass || Reflection.isPrimitive(fourthClazz, valueClass)) {
            if (fourthPredicate.test((T4) value)) {
                fourthConsumer.accept((T4) value);
                return;
            }
        }

        if (fifthClazz == valueClass || Reflection.isPrimitive(fifthClazz, valueClass)) {
            if (fifthPredicate.test((T5) value)) {
                fifthConsumer.accept((T5) value);
                return;
            }
        }

        if (sixthClazz == valueClass || Reflection.isPrimitive(sixthClazz, valueClass)) {
            if (sixthPredicate.test((T6) value)) {
                sixthConsumer.accept((T6) value);
            }
        }
    }

    public <T1, T2, T3, T4, T5, T6> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                            Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                            Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                            Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                            Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                            Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Consumer<T6> sixthConsumer) {
        match(value,
                firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer,
                fifthClazz, fifthPredicate, fifthConsumer,
                sixthClazz, sixthPredicate, sixthConsumer);
    }

    public static <V, T1, T2, T3, T4, T5, T6, R> R match(V value,
                                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                         Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                         Class<T4> forthClazz, Predicate<T4> fourthPredicate, Function<T4, R> forthFunction,
                                                         Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                                         Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Function<T6, R> sixthFunction) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                return firstFunction.apply((T1) value);
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                return secondFunction.apply((T2) value);
            }
        }

        if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            if (thirdPredicate.test((T3) value)) {
                return thirdFunction.apply((T3) value);
            }
        }

        if (forthClazz == valueClass || Reflection.isPrimitive(forthClazz, valueClass)) {
            if (fourthPredicate.test((T4) value)) {
                return forthFunction.apply((T4) value);
            }
        }

        if (fifthClazz == valueClass || Reflection.isPrimitive(fifthClazz, valueClass)) {
            if (fifthPredicate.test((T5) value)) {
                return fifthFunction.apply((T5) value);
            }
        }

        if (sixthClazz == valueClass || Reflection.isPrimitive(sixthClazz, valueClass)) {
            if (sixthPredicate.test((T6) value)) {
                return sixthFunction.apply((T6) value);
            }
        }

        return null;
    }

    public <V, T1, T2, T3, T4, T5, T6, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                               Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                               Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Function<T4, R> fourthFunction,
                                               Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                               Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Function<T6, R> sixthFunction) {
        return match((V) value,
                firstClazz, firstPredicate, firstFunction,
                secondClazz, secondPredicate, secondFunction,
                thirdClazz, thirdPredicate, thirdFunction,
                fourthClazz, fourthPredicate, fourthFunction,
                fifthClazz, fifthPredicate, fifthFunction,
                sixthClazz, sixthPredicate, sixthFunction);
    }

    public static <V, T1, T2, T3, T4, T5, T6> void match(V value,
                                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                         Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                         Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                         Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                                         Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Consumer<T6> sixthConsumer,
                                                         Class<Else> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                secondConsumer.accept((T2) value);
                return;
            }
        }

        if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            if (thirdPredicate.test((T3) value)) {
                thirdConsumer.accept((T3) value);
                return;
            }
        }

        if (fourthClazz == valueClass || Reflection.isPrimitive(fourthClazz, valueClass)) {
            if (fourthPredicate.test((T4) value)) {
                fourthConsumer.accept((T4) value);
                return;
            }
        }

        if (fifthClazz == valueClass || Reflection.isPrimitive(fifthClazz, valueClass)) {
            if (fifthPredicate.test((T5) value)) {
                fifthConsumer.accept((T5) value);
                return;
            }
        }

        if (sixthClazz == valueClass || Reflection.isPrimitive(sixthClazz, valueClass)) {
            if (sixthPredicate.test((T6) value)) {
                sixthConsumer.accept((T6) value);
                return;
            }
        }

        defaultConsumer.run();
    }

    public <T1, T2, T3, T4, T5, T6> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                            Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                            Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                            Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                            Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                            Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Consumer<T6> sixthConsumer,
                                            Class<Else> defaultClass, Runnable defaultConsumer) {
        match(value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer,
                fifthClazz, fifthPredicate, fifthConsumer,
                sixthClazz, sixthPredicate, sixthConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T1, T2, T3, T4, T5, T6> void match(V value,
                                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                         Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                         Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                         Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                                         Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Consumer<T6> sixthConsumer,
                                                         Class<Var> varClass, Consumer<V> varConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                secondConsumer.accept((T2) value);
                return;
            }
        }

        if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            if (thirdPredicate.test((T3) value)) {
                thirdConsumer.accept((T3) value);
                return;
            }
        }

        if (fourthClazz == valueClass || Reflection.isPrimitive(fourthClazz, valueClass)) {
            if (fourthPredicate.test((T4) value)) {
                fourthConsumer.accept((T4) value);
                return;
            }
        }

        if (fifthClazz == valueClass || Reflection.isPrimitive(fifthClazz, valueClass)) {
            if (fifthPredicate.test((T5) value)) {
                fifthConsumer.accept((T5) value);
                return;
            }
        }

        if (sixthClazz == valueClass || Reflection.isPrimitive(sixthClazz, valueClass)) {
            if (sixthPredicate.test((T6) value)) {
                sixthConsumer.accept((T6) value);
                return;
            }
        }

        varConsumer.accept(value);
    }

    public <V, T1, T2, T3, T4, T5, T6> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                               Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                               Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                               Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                               Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Consumer<T6> sixthConsumer,
                                               Class<Var> varClass, Consumer<V> varConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer,
                fifthClazz, fifthPredicate, fifthConsumer,
                sixthClazz, sixthPredicate, sixthConsumer,
                varClass, varConsumer);
    }

    public static <V, T1, T2, T3, T4, T5, T6> void match(V value,
                                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                         Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                         Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                         Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                                         Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Consumer<T6> sixthConsumer,
                                                         Class<Null> nullClass, Runnable nullConsumer,
                                                         Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, firstClazz, firstPredicate, firstConsumer,
                    secondClazz, secondPredicate, secondConsumer,
                    thirdClazz, thirdPredicate, thirdConsumer,
                    fourthClazz, fourthPredicate, fourthConsumer,
                    fifthClazz, fifthPredicate, fifthConsumer,
                    sixthClazz, sixthPredicate, sixthConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    public <V, T1, T2, T3, T4, T5, T6> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                               Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                               Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                               Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                               Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Consumer<T6> sixthConsumer,
                                               Class<Null> nullClass, Runnable nullConsumer,
                                               Class<Else> defaultClass, Runnable defaultConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer,
                fifthClazz, fifthPredicate, fifthConsumer,
                sixthClazz, sixthPredicate, sixthConsumer,
                nullClass, nullConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T1, T2, T3, T4, T5, T6> void match(V value,
                                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                         Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                         Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                         Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                                         Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Consumer<T6> sixthConsumer,
                                                         Class<Null> nullClass, Runnable nullConsumer,
                                                         Class<Var> varClass, Consumer<V> varConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, firstClazz, firstPredicate, firstConsumer,
                    secondClazz, secondPredicate, secondConsumer,
                    thirdClazz, thirdPredicate, thirdConsumer,
                    fourthClazz, fourthPredicate, fourthConsumer,
                    fifthClazz, fifthPredicate, fifthConsumer,
                    sixthClazz, sixthPredicate, sixthConsumer,
                    varClass, varConsumer);
        }
    }

    public <V, T1, T2, T3, T4, T5, T6> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                               Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                               Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                               Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                               Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Consumer<T6> sixthConsumer,
                                               Class<Null> nullClass, Runnable nullConsumer,
                                               Class<Var> varClass, Consumer<V> varConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer,
                fifthClazz, fifthPredicate, fifthConsumer,
                sixthClazz, sixthPredicate, sixthConsumer,
                nullClass, nullConsumer,
                varClass, varConsumer);
    }

    public static <V, T1, T2, T3, T4, T5, T6> void match(V value,
                                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                         Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                         Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                         Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                                         Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Consumer<T6> sixthConsumer,
                                                         Class<Null> nullClass, Runnable nullConsumer,
                                                         Class<Var> varClass, Predicate<V> varPredicate, Runnable varConsumer,
                                                         Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, firstClazz, firstPredicate, firstConsumer,
                    secondClazz, secondPredicate, secondConsumer,
                    thirdClazz, thirdPredicate, thirdConsumer,
                    fourthClazz, fourthPredicate, fourthConsumer,
                    fifthClazz, fifthPredicate, fifthConsumer,
                    sixthClazz, sixthPredicate, sixthConsumer,
                    varClass, varPredicate, varConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    public <V, T1, T2, T3, T4, T5, T6> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                               Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                               Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                               Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                               Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Consumer<T6> sixthConsumer,
                                               Class<Null> nullClass, Runnable nullConsumer,
                                               Class<Var> varClass, Predicate<V> varPredicate, Runnable varConsumer,
                                               Class<Else> defaultClass, Runnable defaultConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer,
                fifthClazz, fifthPredicate, fifthConsumer,
                sixthClazz, sixthPredicate, sixthConsumer,
                nullClass, nullConsumer,
                varClass, varPredicate, varConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T1, T2, T3, T4, T5, T6> void match(V value,
                                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                         Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                         Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                         Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                                         Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Consumer<T6> sixthConsumer,
                                                         Class<Var> varClazz, Predicate<V> varPredicate, Runnable varConsumer,
                                                         Class<Else> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            if (firstPredicate.test((T1) value)) {
                firstConsumer.accept((T1) value);
                return;
            }
        }

        if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            if (secondPredicate.test((T2) value)) {
                secondConsumer.accept((T2) value);
                return;
            }
        }

        if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            if (thirdPredicate.test((T3) value)) {
                thirdConsumer.accept((T3) value);
                return;
            }
        }

        if (fourthClazz == valueClass || Reflection.isPrimitive(fourthClazz, valueClass)) {
            if (fourthPredicate.test((T4) value)) {
                fourthConsumer.accept((T4) value);
                return;
            }
        }

        if (fifthClazz == valueClass || Reflection.isPrimitive(fifthClazz, valueClass)) {
            if (fifthPredicate.test((T5) value)) {
                fifthConsumer.accept((T5) value);
                return;
            }
        }

        if (sixthClazz == valueClass || Reflection.isPrimitive(sixthClazz, valueClass)) {
            if (sixthPredicate.test((T6) value)) {
                sixthConsumer.accept((T6) value);
                return;
            }
        }

        if (varPredicate.test(value)) {
            varConsumer.run();
        } else {
            defaultConsumer.run();
        }
    }

    public <V, T1, T2, T3, T4, T5, T6> void as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                               Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                               Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                               Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                               Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Consumer<T6> sixthConsumer,
                                               Class<Var> varClazz, Predicate<V> varPredicate, Runnable varConsumer,
                                               Class<Else> defaultClass, Runnable defaultConsumer) {
        match((V) value, firstClazz, firstPredicate, firstConsumer,
                secondClazz, secondPredicate, secondConsumer,
                thirdClazz, thirdPredicate, thirdConsumer,
                fourthClazz, fourthPredicate, fourthConsumer,
                fifthClazz, fifthPredicate, fifthConsumer,
                sixthClazz, sixthPredicate, sixthConsumer,
                varClazz, varPredicate, varConsumer,
                defaultClass, defaultConsumer);
    }

    public static <V, T1, T2, T3, T4, T5, T6, R> R match(V value,
                                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                         Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                         Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                         Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                                         Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Function<T6, R> sixthFunction,
                                                         Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = match(value, firstClazz, firstPredicate, firstFunction, secondClazz, secondPredicate, secondFunction,
                thirdClazz, thirdPredicate, thirdFunction, forthClazz, forthPredicate, forthFunction,
                fifthClazz, fifthPredicate, fifthFunction, sixthClazz, sixthPredicate, sixthFunction);
        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    public <V, T1, T2, T3, T4, T5, T6, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                               Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                               Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Function<T4, R> fourthFunction,
                                               Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                               Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Function<T6, R> sixthFunction,
                                               Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        return match((V) value,
                firstClazz, firstPredicate, firstFunction,
                secondClazz, secondPredicate, secondFunction,
                fourthClazz, fourthPredicate, fourthFunction,
                thirdClazz, thirdPredicate, thirdFunction,
                fifthClazz, fifthPredicate, fifthFunction,
                sixthClazz, sixthPredicate, sixthFunction,
                defaultClass, defaultSupplier);
    }

    public static <V, T1, T2, T3, T4, T5, T6, R> R match(V value,
                                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                         Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                         Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                         Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                                         Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Function<T6, R> sixthFunction,
                                                         Class<Var> varClass, Function<V, R> varFunction) {
        R result = match(value, firstClazz, firstPredicate, firstFunction,
                secondClazz, secondPredicate, secondFunction,
                thirdClazz, thirdPredicate, thirdFunction,
                forthClazz, forthPredicate, forthFunction,
                fifthClazz, fifthPredicate, fifthFunction,
                sixthClazz, sixthPredicate, sixthFunction);
        if (result != null) {
            return result;
        } else {
            return varFunction.apply(value);
        }
    }

    public <V, T1, T2, T3, T4, T5, T6, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                               Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                               Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Function<T4, R> fourthFunction,
                                               Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                               Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Function<T6, R> sixthFunction,
                                               Class<Var> varClass, Function<V, R> varFunction) {
        return match((V) value,
                    firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    fourthClazz, fourthPredicate, fourthFunction,
                    fifthClazz, fifthPredicate, fifthFunction,
                    sixthClazz, sixthPredicate, sixthFunction,
                    varClass, varFunction);
    }

    public static <V, T1, T2, T3, T4, T5, T6, R> R match(V value,
                                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                         Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                         Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                         Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                                         Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Function<T6, R> sixthFunction,
                                                         Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                                         Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = match(value, firstClazz, firstPredicate, firstFunction,
                secondClazz, secondPredicate, secondFunction,
                thirdClazz, thirdPredicate, thirdFunction,
                forthClazz, forthPredicate, forthFunction,
                fifthClazz, fifthPredicate, fifthFunction,
                sixthClazz, sixthPredicate, sixthFunction);
        if (result != null) {
            return result;
        } else {
            if (varPredicate.test(value)) {
                return varSupplier.get();
            } else {
                return defaultSupplier.get();
            }
        }
    }

    public <V, T1, T2, T3, T4, T5, T6, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                               Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                               Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Function<T4, R> fourthFunction,
                                               Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                               Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Function<T6, R> sixthFunction,
                                               Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                               Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        return match((V) value,
                    firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    fourthClazz, fourthPredicate, fourthFunction,
                    fifthClazz, fifthPredicate, fifthFunction,
                    sixthClazz, sixthPredicate, sixthFunction,
                    varClass, varPredicate, varSupplier,
                    defaultClass, defaultSupplier);
    }

    public static <V, T1, T2, T3, T4, T5, T6, R> R match(V value,
                                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                         Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                         Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                         Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                                         Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Function<T6, R> sixthFunction,
                                                         Class<Null> nullClass, Supplier<R> nullSupplier,
                                                         Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    forthClazz, forthPredicate, forthFunction,
                    fifthClazz, fifthPredicate, fifthFunction,
                    sixthClazz, sixthPredicate, sixthFunction,
                    defaultClass, defaultSupplier);
        }
    }

    public <V, T1, T2, T3, T4, T5, T6, R> R as(Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                               Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                               Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Function<T4, R> fourthFunction,
                                               Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                               Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Function<T6, R> sixthFunction,
                                               Class<Null> nullClass, Supplier<R> nullSupplier,
                                               Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        return match((V) value,
                    firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    fourthClazz, fourthPredicate, fourthFunction,
                    fifthClazz, fifthPredicate, fifthFunction,
                    sixthClazz, sixthPredicate, sixthFunction,
                    nullClass, nullSupplier,
                    defaultClass, defaultSupplier);
    }

    public static <V, T1, T2, T3, T4, T5, T6, R> R match(V value,
                                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                         Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                         Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                         Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                                         Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Function<T6, R> sixthFunction,
                                                         Class<Null> nullClass, Supplier<R> nullSupplier,
                                                         Class<Var> varClass, Function<V, R> varFunction) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    forthClazz, forthPredicate, forthFunction,
                    fifthClazz, fifthPredicate, fifthFunction,
                    sixthClazz, sixthPredicate, sixthFunction,
                    varClass, varFunction);
        }
    }


    public static <V, T1, T2, T3, T4, T5, T6, R> R match(V value,
                                                         Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                         Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                         Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                         Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                         Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                                         Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Function<T6, R> sixthFunction,
                                                         Class<Null> nullClass, Supplier<R> nullSupplier,
                                                         Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                                         Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz, firstPredicate, firstFunction,
                    secondClazz, secondPredicate, secondFunction,
                    thirdClazz, thirdPredicate, thirdFunction,
                    forthClazz, forthPredicate, forthFunction,
                    fifthClazz, fifthPredicate, fifthFunction,
                    sixthClazz, sixthPredicate, sixthFunction,
                    varClass, varPredicate, varSupplier,
                    defaultClass, defaultSupplier);
        }
    }
}
