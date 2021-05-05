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

import org.kl.jpml.lambda.Purchaser;
import org.kl.jpml.lambda.Routine;
import org.kl.jpml.reflect.Reflection;
import org.kl.jpml.state.Else;
import org.kl.jpml.state.Null;
import org.kl.jpml.state.Var;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/*
 * Type test pattern allow match type and then extract
 * value. Maximum number of branches for match six.
 */
public final class TypeTestPattern {
    private static TypeTestPattern instance;
    private static Object value;

    private TypeTestPattern() {}

    public static <V> TypeTestPattern match(V other) {
        value = other;

        if (instance == null) {
            instance = new TypeTestPattern();
        }

        return instance;
    }

    @SuppressWarnings("unused")
    private static <T> Consumer<Object> acceptType(Class<T> clazz, Consumer<? super T> consumer) {
        return x -> {
            if (clazz.isInstance(x)) {
                consumer.accept(clazz.cast(x));
            }
        };
    }

    public static <V, T> void match(V value,
                                    Class<T> clazz, Consumer<T> consumer) {
        Class<?> valueClass = value.getClass();

        if (clazz == valueClass || Reflection.isPrimitive(clazz, valueClass)) {
            consumer.accept((T) value);
        }
    }

    public <T> void as(Class<T> clazz, Consumer<T> consumer)  {
        match(value, clazz, consumer);
    }

    @SuppressWarnings("unused")
    public static <V, T> void match(V value,
                                    Class<T> clazz, Consumer<T> consumer,
                                    Class<Else> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (clazz == valueClass || Reflection.isPrimitive(clazz, valueClass)) {
            consumer.accept((T) value);
        } else {
            defaultConsumer.run();
        }
    }

    public <T> void as(Class<T> clazz, Consumer<T> consumer,
                       Class<Else> defaultClass, Runnable defaultConsumer)  {
        match(value, clazz, consumer, defaultClass, defaultConsumer);
    }

    @SuppressWarnings("unused")
    public static <V, T> void match(V value,
                                    Class<T> clazz, Purchaser<T> purchaser,
                                    Class<Var> varClass, Purchaser<V> varPurchaser) {
        Class<?> valueClass = value.getClass();

        if (clazz == valueClass || Reflection.isPrimitive(clazz, valueClass)) {
            purchaser.obtain((T) value);
        } else {
            varPurchaser.obtain(value);
        }
    }

    public <T, V> void as(Class<T> clazz, Purchaser<T> purchaser,
                          Class<Var> varClass, Purchaser<V> varPurchaser)  {
        match((V) value, clazz, purchaser, varClass, varPurchaser);
    }

    @SuppressWarnings("unused")
    public static <V, T> void match(V value,
                                    Class<T> clazz, Consumer<T> consumer,
                                    Class<Null> nullClass, Runnable nullConsumer,
                                    Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, clazz, consumer, defaultClass, defaultConsumer);
        }
    }

    public <T, V> void as(Class<T> clazz, Consumer<T> consumer,
                          Class<Null> nullClass, Runnable nullConsumer,
                          Class<Else> defaultClass, Runnable defaultConsumer)  {
        match((V) value, clazz, consumer, nullClass, nullConsumer, defaultClass, defaultConsumer);
    }

    @SuppressWarnings("unused")
    public static <V, T> void match(V value,
                                    Class<T> clazz, Purchaser<T> purchaser,
                                    Class<Null> nullClass, Runnable nullPurchaser,
                                    Class<Var> varClass, Purchaser<V> varPurchaser) {
        if (value == null) {
            nullPurchaser.run();
        } else {
            match(value, clazz, purchaser, varClass, varPurchaser);
        }
    }

    public static <V, T, R> R match(V value,
                                    Class<T> clazz, Function<T, R> function) {
        Class<?> valueClass = value.getClass();

        if (clazz == valueClass || Reflection.isPrimitive(clazz, valueClass)) {
            return function.apply((T) value);
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V, T, R> R match(V value,
                                    Class<T> clazz, Function<T, R> function,
                                    Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = match(value, clazz, function);

        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V, T, R> R match(V value,
                                    Class<T> clazz, Function<T, R> function,
                                    Class<Var> varClass, Routine<V, R> defaultRoutine) {
        R result = match(value, clazz, function);

        if (result != null) {
            return result;
        } else {
            return defaultRoutine.hold(value);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T, R> R match(V value,
                                    Class<T> clazz, Function<T, R> function,
                                    Class<Null> nullClass, Supplier<R> nullSupplier,
                                    Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, clazz, function, defaultClass, defaultSupplier);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T, R> R match(V value,
                                    Class<T> clazz, Function<T, R> function,
                                    Class<Null> nullClass, Supplier<R> nullSupplier,
                                    Class<Var> varClass, Routine<V, R> defaultRoutine) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, clazz, function, varClass, defaultRoutine);
        }
    }

    public static <V, T1, T2> void match(V value,
                                         Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                         Class<T2> secondClazz, Consumer<T2> secondConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstConsumer.accept((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondConsumer.accept((T2) value);
        }
    }

    public <T1, T2> void as(Class<T1> firstClazz, Consumer<T1> firstConsumer,
                            Class<T2> secondClazz, Consumer<T2> secondConsumer)  {
        match(value,
              firstClazz, firstConsumer,
              secondClazz, secondConsumer);
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2> void match(V value,
                                         Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                         Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                         Class<Else> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstConsumer.accept((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondConsumer.accept((T2) value);
        } else {
            defaultConsumer.run();
        }
    }

    public <T1, T2> void as(Class<T1> firstClazz, Consumer<T1> firstConsumer,
                       Class<T2> secondClazz, Consumer<T2> secondConsumer,
                       Class<Else> defaultClass, Runnable defaultConsumer)  {
        match(value,
              firstClazz, firstConsumer,
              secondClazz, secondConsumer,
              defaultClass, defaultConsumer);
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2> void match(V value,
                                         Class<T1> firstClazz, Purchaser<T1> firstPurchaser,
                                         Class<T2> secondClazz, Purchaser<T2> secondPurchaser,
                                         Class<Var> varClass, Purchaser<V> varPurchaser) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstPurchaser.obtain((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondPurchaser.obtain((T2) value);
        } else {
            varPurchaser.obtain(value);
        }
    }

    public <T1, T2, V> void as(Class<T1> firstClazz, Purchaser<T1> firstPurchaser,
                          Class<T2> secondClazz, Purchaser<T2> secondPurchaser,
                          Class<Var> varClass, Purchaser<V> varPurchaser)  {
        match((V) value,
              firstClazz, firstPurchaser,
              secondClazz, secondPurchaser,
              varClass, varPurchaser);
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2> void match(V value,
                                         Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                         Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                         Class<Null> nullClass, Runnable nullConsumer,
                                         Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, firstClazz, firstConsumer, secondClazz, secondConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    public <T1, T2, V> void as(Class<T1> firstClazz, Consumer<T1> firstConsumer,
                          Class<T2> secondClazz, Consumer<T2> secondConsumer,
                          Class<Null> nullClass, Runnable nullConsumer,
                          Class<Else> defaultClass, Runnable defaultConsumer)  {
        match((V) value,
              firstClazz, firstConsumer,
              secondClazz, secondConsumer,
              nullClass, nullConsumer,
              defaultClass, defaultConsumer);
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2> void match(V value,
                                         Class<T1> firstClazz, Purchaser<T1> firstPurchaser,
                                         Class<T2> secondClazz, Purchaser<T2> secondPurchaser,
                                         Class<Null> nullClass, Runnable nullPurchaser,
                                         Class<Var> varClass, Purchaser<V> varPurchaser) {
        if (value == null) {
            nullPurchaser.run();
        } else {
            match(value, firstClazz,  firstPurchaser,
                           secondClazz, secondPurchaser,
                    varClass, varPurchaser);
        }
    }

    public static <V, T1, T2, R> R match(V value,
                                         Class<T1> firstClazz, Function<T1, R> firstFunction,
                                         Class<T2> secondClazz, Function<T2, R> secondFunction) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            return firstFunction.apply((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            return secondFunction.apply((T2) value);
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, R> R match(V value,
                                         Class<T1> firstClazz, Function<T1, R> firstFunction,
                                         Class<T2> secondClazz, Function<T2, R> secondFunction,
                                         Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = match(value, firstClazz, firstFunction, secondClazz, secondFunction);

        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, R> R match(V value,
                                         Class<T1> firstClazz, Function<T1, R> firstFunction,
                                         Class<T2> secondClazz, Function<T2, R> secondFunction,
                                         Class<Var> varClass, Routine<V, R> defaultRoutine) {
        R result = match(value, firstClazz, firstFunction, secondClazz, secondFunction);

        if (result != null) {
            return result;
        } else {
            return defaultRoutine.hold(value);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, R> R match(V value,
                                         Class<T1> firstClazz, Function<T1, R> firstFunction,
                                         Class<T2> secondClazz, Function<T2, R> secondFunction,
                                         Class<Null> nullClass, Supplier<R> nullSupplier,
                                         Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  defaultClass, defaultSupplier);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, R> R match(V value,
                                         Class<T1> firstClazz, Function<T1, R> firstFunction,
                                         Class<T2> secondClazz, Function<T2, R> secondFunction,
                                         Class<Null> nullClass, Supplier<R> nullSupplier,
                                         Class<Var> varClass, Routine<V, R> defaultRoutine) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz,  firstFunction,
                                  secondClazz, secondFunction,
                           varClass, defaultRoutine);
        }
    }

    public static <V, T1, T2, T3> void match(V value,
                                             Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                             Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                             Class<T3> thirdClazz, Consumer<T3> thirdConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstConsumer.accept((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondConsumer.accept((T2) value);
        } else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            thirdConsumer.accept((T3) value);
        }
    }

    public <T1, T2, T3> void as(Class<T1> firstClazz, Consumer<T1> firstConsumer,
                            Class<T2> secondClazz, Consumer<T2> secondConsumer,
                            Class<T3> thirdClazz, Consumer<T3> thirdConsumer)  {
        match(value,
              firstClazz, firstConsumer,
              secondClazz, secondConsumer,
              thirdClazz, thirdConsumer);
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3> void match(V value,
                                             Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                             Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                             Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                             Class<Else> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstConsumer.accept((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondConsumer.accept((T2) value);
        } else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            thirdConsumer.accept((T3) value);
        } else {
            defaultConsumer.run();
        }
    }

    public <T1, T2, T3> void as(Class<T1> firstClazz, Consumer<T1> firstConsumer,
                            Class<T2> secondClazz, Consumer<T2> secondConsumer,
                            Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                            Class<Else> defaultClass, Runnable defaultConsumer)  {
        match(value,
                firstClazz, firstConsumer,
                secondClazz, secondConsumer,
                thirdClazz, thirdConsumer,
                defaultClass, defaultConsumer);
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3> void match(V value,
                                             Class<T1> firstClazz, Purchaser<T1> firstPurchaser,
                                             Class<T2> secondClazz, Purchaser<T2> secondPurchaser,
                                             Class<T3> thirdClazz, Purchaser<T3> thirdPurchaser,
                                             Class<Var> varClass, Purchaser<V> varPurchaser) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstPurchaser.obtain((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondPurchaser.obtain((T2) value);
        }else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            thirdPurchaser.obtain((T3) value);
        } else {
            varPurchaser.obtain(value);
        }
    }

    public <T1, T2, T3, V> void as(Class<T1> firstClazz, Purchaser<T1> firstPurchaser,
                               Class<T2> secondClazz, Purchaser<T2> secondPurchaser,
                               Class<T3> thirdClazz, Purchaser<T3> thirdPurchaser,
                               Class<Var> varClass, Purchaser<V> varPurchaser)  {
        match((V) value,
              firstClazz, firstPurchaser,
              secondClazz, secondPurchaser,
              thirdClazz, thirdPurchaser,
              varClass, varPurchaser);
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3> void match(V value,
                                             Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                             Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                             Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                             Class<Null> nullClass, Runnable nullConsumer,
                                             Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, firstClazz, firstConsumer, secondClazz,  secondConsumer,
                           thirdClazz, thirdConsumer, defaultClass, defaultConsumer);
        }
    }

    public <T1, T2, T3, V> void as(Class<T1> firstClazz, Consumer<T1> firstConsumer,
                               Class<T2> secondClazz, Consumer<T2> secondConsumer,
                               Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                               Class<Null> nullClass, Runnable nullConsumer,
                               Class<Else> defaultClass, Runnable defaultConsumer)  {
        match((V) value,
              firstClazz, firstConsumer,
              secondClazz, secondConsumer,
              thirdClazz, thirdConsumer,
              nullClass, nullConsumer,
              defaultClass, defaultConsumer);
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3> void match(V value,
                                             Class<T1> firstClazz, Purchaser<T1> firstPurchaser,
                                             Class<T2> secondClazz, Purchaser<T2> secondPurchaser,
                                             Class<T3> thirdClazz, Purchaser<T3> thirdPurchaser,
                                             Class<Null> nullClass, Runnable nullPurchaser,
                                             Class<Var> varClass, Purchaser<V> varPurchaser) {
        if (value == null) {
            nullPurchaser.run();
        } else {
            match(value, firstClazz,  firstPurchaser,
                           secondClazz, secondPurchaser,
                           thirdClazz,  thirdPurchaser,
                    varClass, varPurchaser);
        }
    }

    public static <V, T1, T2, T3, R> R match(V value,
                                             Class<T1> firstClazz, Function<T1, R> firstFunction,
                                             Class<T2> secondClazz, Function<T2, R> secondFunction,
                                             Class<T3> thirdClazz, Function<T3, R> thirdFunction) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            return firstFunction.apply((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            return secondFunction.apply((T2) value);
        } else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            return thirdFunction.apply((T3) value);
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, R> R match(V value,
                                             Class<T1> firstClazz, Function<T1, R> firstFunction,
                                             Class<T2> secondClazz, Function<T2, R> secondFunction,
                                             Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                             Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = match(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  thirdClazz, thirdFunction);
        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, R> R match(V value,
                                             Class<T1> firstClazz, Function<T1, R> firstFunction,
                                             Class<T2> secondClazz, Function<T2, R> secondFunction,
                                             Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                             Class<Var> varClass, Routine<V, R> defaultRoutine) {
        R result = match(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  thirdClazz, thirdFunction);

        if (result != null) {
            return result;
        } else {
            return defaultRoutine.hold(value);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, R> R match(V value,
                                             Class<T1> firstClazz, Function<T1, R> firstFunction,
                                             Class<T2> secondClazz, Function<T2, R> secondFunction,
                                             Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                             Class<Null> nullClass, Supplier<R> nullSupplier,
                                             Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  thirdClazz, thirdFunction, defaultClass, defaultSupplier);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, R> R match(V value,
                                             Class<T1> firstClazz, Function<T1, R> firstFunction,
                                             Class<T2> secondClazz, Function<T2, R> secondFunction,
                                             Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                             Class<Null> nullClass, Supplier<R> nullSupplier,
                                             Class<Var> varClass, Routine<V, R> defaultRoutine) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz,  firstFunction,
                                  secondClazz, secondFunction,
                                  thirdClazz,  thirdFunction,
                           varClass, defaultRoutine);
        }
    }

    public static <V, T1, T2, T3, T4> void match(V value,
                                                 Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                                 Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                 Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                                 Class<T4> forthClazz, Consumer<T4> forthConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstConsumer.accept((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondConsumer.accept((T2) value);
        } else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            thirdConsumer.accept((T3) value);
        } else if (forthClazz == valueClass || Reflection.isPrimitive(forthClazz, valueClass)) {
            forthConsumer.accept((T4) value);
        }
    }

    public <T1, T2, T3, T4> void as(Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                Class<T4> forthClazz, Consumer<T4> forthConsumer)  {
        match(value,
              firstClazz, firstConsumer,
              secondClazz, secondConsumer,
              thirdClazz, thirdConsumer,
              forthClazz, forthConsumer);
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4> void match(V value,
                                                 Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                                 Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                 Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                                 Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                                 Class<Else> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstConsumer.accept((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondConsumer.accept((T2) value);
        } else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            thirdConsumer.accept((T3) value);
        } else if (forthClazz == valueClass || Reflection.isPrimitive(forthClazz, valueClass)) {
            forthConsumer.accept((T4) value);
        } else {
            defaultConsumer.run();
        }
    }

    public <T1, T2, T3, T4> void as(Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                Class<Else> defaultClass, Runnable defaultConsumer)  {
        match(value,
              firstClazz, firstConsumer,
              secondClazz, secondConsumer,
              thirdClazz, thirdConsumer,
              forthClazz, forthConsumer,
              defaultClass, defaultConsumer);
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4> void match(V value,
                                                 Class<T1> firstClazz, Purchaser<T1> firstPurchaser,
                                                 Class<T2> secondClazz, Purchaser<T2> secondPurchaser,
                                                 Class<T3> thirdClazz, Purchaser<T3> thirdPurchaser,
                                                 Class<T4> fourthClazz, Purchaser<T4> fourthPurchaser,
                                                 Class<Var> varClass, Purchaser<V> varPurchaser) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstPurchaser.obtain((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondPurchaser.obtain((T2) value);
        } else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            thirdPurchaser.obtain((T3) value);
        } else if (fourthClazz == valueClass || Reflection.isPrimitive(fourthClazz, valueClass)) {
            fourthPurchaser.obtain((T4) value);
        } else {
            varPurchaser.obtain(value);
        }
    }

    public <T1, T2, T3, T4, V> void as(Class<T1> firstClazz, Purchaser<T1> firstPurchaser,
                                   Class<T2> secondClazz, Purchaser<T2> secondPurchaser,
                                   Class<T3> thirdClazz, Purchaser<T3> thirdPurchaser,
                                   Class<T4> fourthClazz, Purchaser<T4> fourthPurchaser,
                                   Class<Var> varClass, Purchaser<V> varPurchaser)  {
        match((V) value,
              firstClazz, firstPurchaser,
              secondClazz, secondPurchaser,
              thirdClazz, thirdPurchaser,
              fourthClazz, fourthPurchaser,
              varClass, varPurchaser);
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4> void match(V value,
                                                 Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                                 Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                 Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                                 Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                                 Class<Null> nullClass, Runnable nullConsumer,
                                                 Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, firstClazz, firstConsumer, secondClazz, secondConsumer,
                           thirdClazz, thirdConsumer, forthClazz,  forthConsumer,
                           defaultClass, defaultConsumer);
        }
    }

    public <T1, T2, T3, T4, V> void as(Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                   Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                   Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                   Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                   Class<Null> nullClass, Runnable nullConsumer,
                                   Class<Else> defaultClass, Runnable defaultConsumer)  {
        match((V) value,
              firstClazz, firstConsumer,
              secondClazz, secondConsumer,
              thirdClazz, thirdConsumer,
              forthClazz, forthConsumer,
              nullClass, nullConsumer,
              defaultClass, defaultConsumer);
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4> void match(V value,
                                                 Class<T1> firstClazz, Purchaser<T1> firstPurchaser,
                                                 Class<T2> secondClazz, Purchaser<T2> secondPurchaser,
                                                 Class<T3> thirdClazz, Purchaser<T3> thirdPurchaser,
                                                 Class<T4> forthClazz, Purchaser<T4> forthPurchaser,
                                                 Class<Null> nullClass, Runnable nullPurchaser,
                                                 Class<Var> varClass, Purchaser<V> varPurchaser) {
        if (value == null) {
            nullPurchaser.run();
        } else {
            match(value, firstClazz,  firstPurchaser,
                           secondClazz, secondPurchaser,
                           thirdClazz,  thirdPurchaser,
                           forthClazz,  forthPurchaser,
                    varClass, varPurchaser);
        }
    }

    public static <V, T1, T2, T3, T4, R> R match(V value,
                                                 Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                 Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                 Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                 Class<T4> forthClazz, Function<T4, R> forthFunction) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            return firstFunction.apply((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            return secondFunction.apply((T2) value);
        } else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            return thirdFunction.apply((T3) value);
        } else if (forthClazz == valueClass || Reflection.isPrimitive(forthClazz, valueClass)) {
            return forthFunction.apply((T4) value);
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, R> R match(V value,
                                                 Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                 Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                 Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                 Class<T4> forthClazz, Function<T4, R> forthFunction,
                                                 Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = match(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  thirdClazz, thirdFunction, forthClazz,  forthFunction);
        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, R> R match(V value,
                                                 Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                 Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                 Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                 Class<T4> forthClazz, Function<T4, R> forthFunction,
                                                 Class<Var> varClass, Routine<V, R> defaultRoutine) {
        R result = match(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  thirdClazz, thirdFunction, forthClazz,  forthFunction);

        if (result != null) {
            return result;
        } else {
            return defaultRoutine.hold(value);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, R> R match(V value,
                                                 Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                 Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                 Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                 Class<T4> forthClazz, Function<T4, R> forthFunction,
                                                 Class<Null> nullClass, Supplier<R> nullSupplier,
                                                 Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  thirdClazz, thirdFunction, forthClazz,  forthFunction,
                           defaultClass, defaultSupplier);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, R> R match(V value,
                                                 Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                 Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                 Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                 Class<T4> forthClazz, Function<T4, R> forthFunction,
                                                 Class<Null> nullClass, Supplier<R> nullSupplier,
                                                 Class<Var> varClass, Routine<V, R> defaultRoutine) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz,  firstFunction,
                                  secondClazz, secondFunction,
                                  thirdClazz,  thirdFunction,
                                  forthClazz,  forthFunction,
                           varClass, defaultRoutine);
        }
    }

    public static <V, T1, T2, T3, T4, T5> void match(V value,
                                                     Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                                     Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                     Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                                     Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                                     Class<T5> fifthClazz, Consumer<T5> fifthConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstConsumer.accept((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondConsumer.accept((T2) value);
        } else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            thirdConsumer.accept((T3) value);
        } else if (forthClazz == valueClass || Reflection.isPrimitive(forthClazz, valueClass)) {
            forthConsumer.accept((T4) value);
        } else if (fifthClazz == valueClass || Reflection.isPrimitive(fifthClazz, valueClass)) {
            fifthConsumer.accept((T5) value);
        }
    }

    public <T1, T2, T3, T4, T5> void as(Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                    Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                    Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                    Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                    Class<T5> fifthClazz, Consumer<T5> fifthConsumer)  {
        match(value,
              firstClazz, firstConsumer,
              secondClazz, secondConsumer,
              thirdClazz, thirdConsumer,
              forthClazz, forthConsumer,
              fifthClazz, fifthConsumer);
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5> void match(V value,
                                                     Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                                     Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                     Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                                     Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                                     Class<T5> fifthClazz, Consumer<T5> fifthConsumer,
                                                     Class<Else> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstConsumer.accept((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondConsumer.accept((T2) value);
        } else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            thirdConsumer.accept((T3) value);
        } else if (forthClazz == valueClass || Reflection.isPrimitive(forthClazz, valueClass)) {
            forthConsumer.accept((T4) value);
        } else if (fifthClazz == valueClass || Reflection.isPrimitive(fifthClazz, valueClass)) {
            fifthConsumer.accept((T5) value);
        } else {
            defaultConsumer.run();
        }
    }

    public <T1, T2, T3, T4, T5> void as(Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                    Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                    Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                    Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                    Class<T5> fifthClazz, Consumer<T5> fifthConsumer,
                                    Class<Else> defaultClass, Runnable defaultConsumer)  {
        match(value,
              firstClazz, firstConsumer,
              secondClazz, secondConsumer,
              thirdClazz, thirdConsumer,
              forthClazz, forthConsumer,
              fifthClazz, fifthConsumer,
              defaultClass, defaultConsumer);
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5> void match(V value,
                                                     Class<T1> firstClazz, Purchaser<T1> firstPurchaser,
                                                     Class<T2> secondClazz, Purchaser<T2> secondPurchaser,
                                                     Class<T3> thirdClazz, Purchaser<T3> thirdPurchaser,
                                                     Class<T4> fourthClazz, Purchaser<T4> fourthPurchaser,
                                                     Class<T5> fifthClazz, Purchaser<T5> fifthPurchaser,
                                                     Class<Var> varClass, Purchaser<V> varPurchaser) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstPurchaser.obtain((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondPurchaser.obtain((T2) value);
        } else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            thirdPurchaser.obtain((T3) value);
        } else if (fourthClazz == valueClass || Reflection.isPrimitive(fourthClazz, valueClass)) {
            fourthPurchaser.obtain((T4) value);
        } else if (fifthClazz == valueClass || Reflection.isPrimitive(fifthClazz, valueClass)) {
            fifthPurchaser.obtain((T5) value);
        } else {
            varPurchaser.obtain(value);
        }
    }

    public <T1, T2, T3, T4, T5, V> void as(Class<T1> firstClazz, Purchaser<T1> firstPurchaser,
                                       Class<T2> secondClazz, Purchaser<T2> secondPurchaser,
                                       Class<T3> thirdClazz, Purchaser<T3> thirdPurchaser,
                                       Class<T4> fourthClazz, Purchaser<T4> fourthPurchaser,
                                       Class<T5> fifthClazz, Purchaser<T5> fifthPurchaser,
                                       Class<Var> varClass, Purchaser<V> varPurchaser)  {
        match((V) value,
              firstClazz, firstPurchaser,
              secondClazz, secondPurchaser,
              thirdClazz, thirdPurchaser,
              fourthClazz, fourthPurchaser,
              fifthClazz, fifthPurchaser,
              varClass, varPurchaser);
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5> void match(V value,
                                                     Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                                     Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                     Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                                     Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                                     Class<T5> fifthClazz, Consumer<T5> fifthConsumer,
                                                     Class<Null> nullClass, Runnable nullConsumer,
                                                     Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, firstClazz, firstConsumer, secondClazz, secondConsumer,
                           thirdClazz, thirdConsumer, forthClazz,  forthConsumer,
                           fifthClazz, fifthConsumer, defaultClass, defaultConsumer);
        }
    }

    public <T1, T2, T3, T4, T5, V> void as(Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                       Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                       Class<T5> fifthClazz, Consumer<T5> fifthConsumer,
                                       Class<Null> nullClass, Runnable nullConsumer,
                                       Class<Else> defaultClass, Runnable defaultConsumer)  {
        match((V) value,
              firstClazz, firstConsumer,
              secondClazz, secondConsumer,
              thirdClazz, thirdConsumer,
              forthClazz, forthConsumer,
              fifthClazz, fifthConsumer,
              nullClass, nullConsumer,
              defaultClass, defaultConsumer);
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5> void match(V value,
                                                     Class<T1> firstClazz, Purchaser<T1> firstPurchaser,
                                                     Class<T2> secondClazz, Purchaser<T2> secondPurchaser,
                                                     Class<T3> thirdClazz, Purchaser<T3> thirdPurchaser,
                                                     Class<T4> forthClazz, Purchaser<T4> forthPurchaser,
                                                     Class<T5> fifthClazz, Purchaser<T5> fifthPurchaser,
                                                     Class<Null> nullClass, Runnable nullPurchaser,
                                                     Class<Var> varClass, Purchaser<V> varPurchaser) {
        if (value == null) {
            nullPurchaser.run();
        } else {
            match(value, firstClazz,  firstPurchaser,
                           secondClazz, secondPurchaser,
                           thirdClazz,  thirdPurchaser,
                           forthClazz,  forthPurchaser,
                           fifthClazz,  fifthPurchaser,
                    varClass, varPurchaser);
        }
    }

    public static <V, T1, T2, T3, T4, T5, R> R match(V value,
                                                     Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                     Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                     Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                     Class<T4> forthClazz, Function<T4, R> forthFunction,
                                                     Class<T5> fifthClazz, Function<T5, R> fifthFunction) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            return firstFunction.apply((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            return secondFunction.apply((T2) value);
        } else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            return thirdFunction.apply((T3) value);
        } else if (forthClazz == valueClass || Reflection.isPrimitive(forthClazz, valueClass)) {
            return forthFunction.apply((T4) value);
        } else if (fifthClazz == valueClass || Reflection.isPrimitive(fifthClazz, valueClass)) {
            return fifthFunction.apply((T5) value);
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, R> R match(V value,
                                                     Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                     Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                     Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                     Class<T4> forthClazz, Function<T4, R> forthFunction,
                                                     Class<T5> fifthClazz, Function<T5, R> fifthFunction,
                                                     Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = match(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  thirdClazz, thirdFunction, forthClazz,  forthFunction,
                                  fifthClazz, fifthFunction);
        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, R> R match(V value,
                                                     Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                     Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                     Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                     Class<T4> forthClazz, Function<T4, R> forthFunction,
                                                     Class<T5> fifthClazz, Function<T5, R> fifthFunction,
                                                     Class<Var> varClass, Routine<V, R> defaultRoutine) {
        R result = match(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  thirdClazz, thirdFunction, forthClazz,  forthFunction,
                                  fifthClazz, fifthFunction);

        if (result != null) {
            return result;
        } else {
            return defaultRoutine.hold(value);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, R> R match(V value,
                                                     Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                     Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                     Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                     Class<T4> forthClazz, Function<T4, R> forthFunction,
                                                     Class<T5> fifthClazz, Function<T5, R> fifthFunction,
                                                     Class<Null> nullClass, Supplier<R> nullSupplier,
                                                     Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  thirdClazz, thirdFunction, forthClazz,  forthFunction,
                                  fifthClazz, fifthFunction, defaultClass, defaultSupplier);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, R> R match(V value,
                                                     Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                     Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                     Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                     Class<T4> forthClazz, Function<T4, R> forthFunction,
                                                     Class<T5> fifthClazz, Function<T5, R> fifthFunction,
                                                     Class<Null> nullClass, Supplier<R> nullSupplier,
                                                     Class<Var> varClass, Routine<V, R> defaultRoutine) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz,  firstFunction,
                                  secondClazz, secondFunction,
                                  thirdClazz,  thirdFunction,
                                  forthClazz,  forthFunction,
                                  fifthClazz,  fifthFunction,
                           varClass, defaultRoutine);
        }
    }

    public static <V, T1, T2, T3, T4, T5, T6> void match(V value,
                                                         Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                                         Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                         Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                                         Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                                         Class<T5> fifthClazz, Consumer<T5> fifthConsumer,
                                                         Class<T6> sixthClazz, Consumer<T6> sixthConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstConsumer.accept((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondConsumer.accept((T2) value);
        } else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            thirdConsumer.accept((T3) value);
        } else if (forthClazz == valueClass || Reflection.isPrimitive(forthClazz, valueClass)) {
            forthConsumer.accept((T4) value);
        } else if (fifthClazz == valueClass || Reflection.isPrimitive(fifthClazz, valueClass)) {
            fifthConsumer.accept((T5) value);
        } else if (sixthClazz == valueClass || Reflection.isPrimitive(sixthClazz, valueClass)) {
            sixthConsumer.accept((T6) value);
        }
    }

    public <T1, T2, T3, T4, T5, T6> void as(Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                        Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                        Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                        Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                        Class<T5> fifthClazz, Consumer<T5> fifthConsumer,
                                        Class<T6> sixthClazz, Consumer<T6> sixthConsumer)  {
        match(value,
              firstClazz, firstConsumer,
              secondClazz, secondConsumer,
              thirdClazz, thirdConsumer,
              forthClazz, forthConsumer,
              fifthClazz, fifthConsumer,
              sixthClazz, sixthConsumer);
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6> void match(V value,
                                                         Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                                         Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                         Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                                         Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                                         Class<T5> fifthClazz, Consumer<T5> fifthConsumer,
                                                         Class<T6> sixthClazz, Consumer<T6> sixthConsumer,
                                                         Class<Else> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstConsumer.accept((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondConsumer.accept((T2) value);
        } else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            thirdConsumer.accept((T3) value);
        } else if (forthClazz == valueClass || Reflection.isPrimitive(forthClazz, valueClass)) {
            forthConsumer.accept((T4) value);
        } else if (fifthClazz == valueClass || Reflection.isPrimitive(fifthClazz, valueClass)) {
            fifthConsumer.accept((T5) value);
        } else if (sixthClazz == valueClass || Reflection.isPrimitive(sixthClazz, valueClass)) {
            sixthConsumer.accept((T6) value);
        } else {
            defaultConsumer.run();
        }
    }

    public <T1, T2, T3, T4, T5, T6> void as(Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                        Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                        Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                        Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                        Class<T5> fifthClazz, Consumer<T5> fifthConsumer,
                                        Class<T6> sixthClazz, Consumer<T6> sixthConsumer,
                                        Class<Else> defaultClass, Runnable defaultConsumer)  {
        match(value,
              firstClazz, firstConsumer,
              secondClazz, secondConsumer,
              thirdClazz, thirdConsumer,
              forthClazz, forthConsumer,
              fifthClazz, fifthConsumer,
              sixthClazz, sixthConsumer,
              defaultClass, defaultConsumer);
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6> void match(V value,
                                                         Class<T1> firstClazz, Purchaser<T1> firstPurchaser,
                                                         Class<T2> secondClazz, Purchaser<T2> secondPurchaser,
                                                         Class<T3> thirdClazz, Purchaser<T3> thirdPurchaser,
                                                         Class<T4> fourthClazz, Purchaser<T4> fourthPurchaser,
                                                         Class<T5> fifthClazz, Purchaser<T5> fifthPurchaser,
                                                         Class<T6> sixthClazz, Purchaser<T6> sixthPurchaser,
                                                         Class<Var> varClass, Purchaser<V> varPurchaser) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstPurchaser.obtain((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondPurchaser.obtain((T2) value);
        } else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            thirdPurchaser.obtain((T3) value);
        } else if (fourthClazz == valueClass || Reflection.isPrimitive(fourthClazz, valueClass)) {
            fourthPurchaser.obtain((T4) value);
        } else if (fifthClazz == valueClass || Reflection.isPrimitive(fifthClazz, valueClass)) {
            fifthPurchaser.obtain((T5) value);
        } else if (sixthClazz == valueClass || Reflection.isPrimitive(sixthClazz, valueClass)) {
            sixthPurchaser.obtain((T6) value);
        } else {
            varPurchaser.obtain(value);
        }
    }

    public <T1, T2, T3, T4, T5, T6, V> void as(Class<T1> firstClazz, Purchaser<T1> firstPurchaser,
                                           Class<T2> secondClazz, Purchaser<T2> secondPurchaser,
                                           Class<T3> thirdClazz, Purchaser<T3> thirdPurchaser,
                                           Class<T4> fourthClazz, Purchaser<T4> fourthPurchaser,
                                           Class<T5> fifthClazz, Purchaser<T5> fifthPurchaser,
                                           Class<T6> sixthClazz, Purchaser<T6> sixthPurchaser,
                                           Class<Var> varClass, Purchaser<V> varPurchaser)  {
        match((V) value,
              firstClazz, firstPurchaser,
              secondClazz, secondPurchaser,
              thirdClazz, thirdPurchaser,
              fourthClazz, fourthPurchaser,
              fifthClazz, fifthPurchaser,
              sixthClazz, sixthPurchaser,
              varClass, varPurchaser);
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6> void match(V value,
                                                         Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                                         Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                         Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                                         Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                                         Class<T5> fifthClazz, Consumer<T5> fifthConsumer,
                                                         Class<T6> sixthClazz, Consumer<T6> sixthConsumer,
                                                         Class<Null> nullClass, Runnable nullConsumer,
                                                         Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            match(value, firstClazz, firstConsumer, secondClazz, secondConsumer,
                           thirdClazz, thirdConsumer, forthClazz,  forthConsumer,
                           fifthClazz, fifthConsumer, sixthClazz,  sixthConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    public <T1, T2, T3, T4, T5, T6, V> void as(Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                           Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                           Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                           Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                           Class<T5> fifthClazz, Consumer<T5> fifthConsumer,
                                           Class<T6> sixthClazz, Consumer<T6> sixthConsumer,
                                           Class<Null> nullClass, Runnable nullConsumer,
                                           Class<Else> defaultClass, Runnable defaultConsumer)  {
        match((V) value,
              firstClazz, firstConsumer,
              secondClazz, secondConsumer,
              thirdClazz, thirdConsumer,
              forthClazz, forthConsumer,
              fifthClazz, fifthConsumer,
              sixthClazz, sixthConsumer,
              nullClass, nullConsumer,
              defaultClass, defaultConsumer);
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6> void match(V value,
                                                         Class<T1> firstClazz, Purchaser<T1> firstPurchaser,
                                                         Class<T2> secondClazz, Purchaser<T2> secondPurchaser,
                                                         Class<T3> thirdClazz, Purchaser<T3> thirdPurchaser,
                                                         Class<T4> forthClazz, Purchaser<T4> forthPurchaser,
                                                         Class<T5> fifthClazz, Purchaser<T5> fifthPurchaser,
                                                         Class<T6> sixthClazz, Purchaser<T6> sixthPurchaser,
                                                         Class<Null> nullClass, Runnable nullPurchaser,
                                                         Class<Var> varClass, Purchaser<V> varPurchaser) {
        if (value == null) {
            nullPurchaser.run();
        } else {
            match(value, firstClazz,  firstPurchaser,
                           secondClazz, secondPurchaser,
                           thirdClazz,  thirdPurchaser,
                           forthClazz,  forthPurchaser,
                           fifthClazz,  fifthPurchaser,
                           sixthClazz,  sixthPurchaser,
                    varClass, varPurchaser);
        }
    }

    public static <V, T1, T2, T3, T4, T5, T6, R> R match(V value,
                                                         Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                         Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                         Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                         Class<T4> forthClazz, Function<T4, R> forthFunction,
                                                         Class<T5> fifthClazz, Function<T5, R> fifthFunction,
                                                         Class<T6> sixthClazz, Function<T6, R> sixthFunction) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            return firstFunction.apply((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            return secondFunction.apply((T2) value);
        } else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            return thirdFunction.apply((T3) value);
        } else if (forthClazz == valueClass || Reflection.isPrimitive(forthClazz, valueClass)) {
            return forthFunction.apply((T4) value);
        } else if (fifthClazz == valueClass || Reflection.isPrimitive(fifthClazz, valueClass)) {
            return fifthFunction.apply((T5) value);
        } else if (sixthClazz == valueClass || Reflection.isPrimitive(sixthClazz, valueClass)) {
            return sixthFunction.apply((T6) value);
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6, R> R match(V value,
                                                         Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                         Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                         Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                         Class<T4> forthClazz, Function<T4, R> forthFunction,
                                                         Class<T5> fifthClazz, Function<T5, R> fifthFunction,
                                                         Class<T6> sixthClazz, Function<T6, R> sixthFunction,
                                                         Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = match(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  thirdClazz, thirdFunction, forthClazz,  forthFunction,
                                  fifthClazz, fifthFunction, sixthClazz,  sixthFunction);
        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6, R> R match(V value,
                                                         Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                         Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                         Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                         Class<T4> forthClazz, Function<T4, R> forthFunction,
                                                         Class<T5> fifthClazz, Function<T5, R> fifthFunction,
                                                         Class<T6> sixthClazz, Function<T6, R> sixthFunction,
                                                         Class<Var> varClass, Routine<V, R> defaultRoutine) {
        R result = match(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  thirdClazz, thirdFunction, forthClazz,  forthFunction,
                                  fifthClazz, fifthFunction, sixthClazz,  sixthFunction);

        if (result != null) {
            return result;
        } else {
            return defaultRoutine.hold(value);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6, R> R match(V value,
                                                         Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                         Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                         Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                         Class<T4> forthClazz, Function<T4, R> forthFunction,
                                                         Class<T5> fifthClazz, Function<T5, R> fifthFunction,
                                                         Class<T6> sixthClazz, Function<T6, R> sixthFunction,
                                                         Class<Null> nullClass, Supplier<R> nullSupplier,
                                                         Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  thirdClazz, thirdFunction, forthClazz,  forthFunction,
                                  fifthClazz, fifthFunction, sixthClazz,  sixthFunction,
                           defaultClass, defaultSupplier);
        }

    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6, R> R match(V value,
                                                         Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                         Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                         Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                         Class<T4> forthClazz, Function<T4, R> forthFunction,
                                                         Class<T5> fifthClazz, Function<T5, R> fifthFunction,
                                                         Class<T6> sixthClazz, Function<T6, R> sixthFunction,
                                                         Class<Null> nullClass, Supplier<R> nullSupplier,
                                                         Class<Var> varClass, Routine<V, R> defaultRoutine) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return match(value, firstClazz,  firstFunction,
                                  secondClazz, secondFunction,
                                  thirdClazz,  thirdFunction,
                                  forthClazz,  forthFunction,
                                  fifthClazz,  fifthFunction,
                                  sixthClazz,  sixthFunction,
                           varClass, defaultRoutine);
        }
    }
}
