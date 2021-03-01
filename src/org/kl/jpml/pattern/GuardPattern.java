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

import org.kl.jpml.reflect.Reflection;
import org.kl.jpml.state.Else;
import org.kl.jpml.state.Null;
import org.kl.jpml.state.Var;

import java.util.function.*;

/*
 * Guard pattern allow match type and check condition for the
 * truth at one time. Maximum number of branches for match six.
 */
public final class GuardPattern {
    private GuardPattern() {}

    public static <V, T> void matches(V value,
                                      Class<T> clazz, Predicate<T> predicate, Consumer<T> consumer) {
        Class<?> valueClass = value.getClass();

        if (clazz == valueClass || Reflection.isPrimitive(clazz, valueClass)) {
            if (predicate.test((T) value)) {
                consumer.accept((T) value);
            }
        }
    }

    @SuppressWarnings("unused")
    public static <V, T> void matches(V value,
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

    @SuppressWarnings("unused")
    public static <V, T> void matches(V value,
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

    @SuppressWarnings("unused")
    public static <V, T> void matches(V value,
                                      Class<T> clazz, Predicate<T> predicate, Consumer<T> consumer,
                                      Class<Null> nullClass, Runnable nullConsumer,
                                      Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, clazz, predicate, consumer, defaultClass, defaultConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T> void matches(V value,
                                      Class<T> clazz, Predicate<T> predicate, Consumer<T> consumer,
                                      Class<Null> nullClass, Runnable nullConsumer,
                                      Class<Var> varClass,   Consumer<V> varConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, clazz, predicate, consumer, varClass, varConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2> void matches(V value,
                                           Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                           Class<Var> varClazz, Predicate<V>  varPredicate, Runnable varConsumer,
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

    @SuppressWarnings("unused")
    public static <V, T> void matches(V value,
                                      Class<T> clazz, Predicate<T> predicate, Consumer<T> consumer,
                                      Class<Null> nullClass, Runnable nullConsumer,
                                      Class<Var> varClass, Predicate<V>  varPredicate, Runnable varConsumer,
                                      Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, clazz, predicate, consumer,
                    varClass, varPredicate, varConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    public static <V, T, R> R matches(V value,
                                      Class<T> clazz, Predicate<T> predicate, Function<T, R> function) {
        Class<?> valueClass = value.getClass();

        if (clazz == valueClass || Reflection.isPrimitive(clazz, valueClass)) {
            if (predicate.test((T) value)) {
                return function.apply((T) value);
            }
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V, T, R> R matches(V value,
                                      Class<T> clazz, Predicate<T> predicate, Function<T, R> function,
                                      Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = matches(value, clazz, predicate, function);

        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V, T, R> R matches(V value,
                                      Class<T> clazz, Predicate<T> predicate, Function<T, R> function,
                                      Class<Var> varClass, Function<V, R> varFunction) {
        R result = matches(value, clazz, predicate, function);

        if (result != null) {
            return result;
        } else {
            return varFunction.apply(value);
        }
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V, T, R> R matches(V value,
                                      Class<T> clazz, Predicate<T> predicate, Function<T, R> function,
                                      Class<Var> varClass, Predicate<V>  varPredicate, Supplier<R> varSupplier,
                                      Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = matches(value, clazz, predicate, function);

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

    @SuppressWarnings("unused")
    public static <V, T, R> R matches(V value,
                                      Class<T> clazz, Predicate<T> predicate, Function<T, R> function,
                                      Class<Null> nullClass, Supplier<R> nullSupplier,
                                      Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, clazz, predicate, function, defaultClass, defaultSupplier);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T, R> R matches(V value,
                                      Class<T> clazz, Predicate<T> predicate, Function<T, R> function,
                                      Class<Null> nullClass, Supplier<R> nullSupplier,
                                      Class<Var>  varClass,  Function<V, R> varFunction) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, clazz, predicate, function, varClass, varFunction);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T, R> R matches(V value,
                                      Class<T> clazz, Predicate<T> predicate, Function<T, R> function,
                                      Class<Null> nullClass, Supplier<R>  nullSupplier,
                                      Class<Var>  varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                      Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, clazz, predicate, function,
                           varClass, varPredicate, varSupplier,
                           defaultClass, defaultSupplier);
        }
    }

    public static <V, T1, T2> void matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Consumer<T1> firstConsumer,
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

    @SuppressWarnings("unused")
    public static <V, T1, T2> void matches(V value,
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

    @SuppressWarnings("unused")
    public static <V, T1, T2> void matches(V value,
                                   Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Consumer<T1> firstConsumer,
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

    @SuppressWarnings("unused")
    public static <V, T1, T2> void matches(V value,
                                           Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                           Class<Null> nullClass, Runnable nullConsumer,
                                           Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, firstClazz,  firstPredicate,  firstConsumer,
                           secondClazz, secondPredicate, secondConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2> void matches(V value,
                                           Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Consumer<T1> firstConsumer,
                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                           Class<Null> nullClass, Runnable nullConsumer,
                                           Class<Var> varClass,   Consumer<V> varConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, firstClazz,  firstPredicate,  firstConsumer,
                           secondClazz, secondPredicate, secondConsumer,
                    varClass, varConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2> void matches(V value,
                                           Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                           Class<Null> nullClass, Runnable nullConsumer,
                                           Class<Var> varClass, Predicate<V>  varPredicate, Runnable varConsumer,
                                           Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, firstClazz,  firstPredicate,  firstConsumer,
                           secondClazz, secondPredicate, secondConsumer,
                    varClass, varPredicate, varConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2> void matches(V value,
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

    public static <V, T1, T2, R> R matches(V value,
                                   Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Function<T1, R> firstFunction,
                                   Class<T2> secondClazz, Predicate<T2> secondPredicate,  Function<T2, R> secondFunction) {
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


    @SuppressWarnings("unused")
    public static <V, T1, T2, R> R matches(V value,
                                           Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                           Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = matches(value, firstClazz, firstPredicate, firstFunction,
                                  secondClazz, secondPredicate, secondFunction);
        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, R> R matches(V value,
                                   Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Function<T1, R> firstFunction,
                                   Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                   Class<Var> varClass,   Function<V, R> varFunction) {
        R result = matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction);
        if (result != null) {
            return result;
        } else {
            return varFunction.apply(value);
        }
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V, T1, T2, R> R matches(V value,
                                           Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                           Class<Var> varClass, Predicate<V>  varPredicate, Supplier<R> varSupplier,
                                           Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = matches(value, firstClazz,  firstPredicate,  firstFunction,
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

    @SuppressWarnings("unused")
    public static <V, T1, T2, R> R matches(V value,
                                           Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                           Class<Null> nullClass, Supplier<R> nullSupplier,
                                           Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                           defaultClass, defaultSupplier);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, R> R matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate,  Function<T2, R> secondFunction,
                                       Class<Null> nullClass, Supplier<R> nullSupplier,
                                       Class<Var> varClass,   Function<V, R> varFunction) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                           varClass, varFunction);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, R> R matches(V value,
                                           Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                           Class<Null> nullClass, Supplier<R>  nullSupplier,
                                           Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                           Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                           varClass, varPredicate, varSupplier,
                           defaultClass, defaultSupplier);
        }
    }

    public static <V, T1, T2, T3> void matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Consumer<T3> thirdConsumer) {
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

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3> void matches(V value,
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

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3> void matches(V value,
                                               Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Consumer<T1> firstConsumer,
                                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                               Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Consumer<T3> thirdConsumer,
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

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3> void matches(V value,
                                               Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                               Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                               Class<Null> nullClass, Runnable nullConsumer,
                                               Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, firstClazz,  firstPredicate,  firstConsumer,
                           secondClazz, secondPredicate, secondConsumer,
                           thirdClazz,  thirdPredicate,  thirdConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3> void matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Consumer<T3> thirdConsumer,
                                       Class<Null> nullClass, Runnable nullConsumer,
                                       Class<Var> varClass,   Consumer<V> varConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, firstClazz,  firstPredicate,  firstConsumer,
                           secondClazz, secondPredicate, secondConsumer,
                           thirdClazz,  thirdPredicate,  thirdConsumer,
                    varClass, varConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3> void matches(V value,
                                               Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                               Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                               Class<Null> nullClass, Runnable nullConsumer,
                                               Class<Var> varClass, Predicate<V>  varPredicate, Runnable varConsumer,
                                               Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, firstClazz,  firstPredicate,  firstConsumer,
                           secondClazz, secondPredicate, secondConsumer,
                           thirdClazz,  thirdPredicate,  thirdConsumer,
                    varClass, varPredicate, varConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3> void matches(V value,
                                               Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                               Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                               Class<Var> varClazz, Predicate<V>  varPredicate, Runnable varConsumer,
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

    public static <V, T1, T2, T3, R> R matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Function<T3, R> thirdFunction) {
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

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, R> R matches(V value,
                                               Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                               Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                               Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = matches(value, firstClazz, firstPredicate, firstFunction, secondClazz, secondPredicate, secondFunction,
                                  thirdClazz, thirdPredicate, thirdFunction);
        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, R> R matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Function<T3, R> thirdFunction,
                                       Class<Var> varClass,   Function<V, R> varFunction) {
        R result = matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction);
        if (result != null) {
            return result;
        } else {
            return varFunction.apply(value);
        }
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V, T1, T2, T3, R> R matches(V value,
                                               Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                               Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                               Class<Var> varClass, Predicate<V>  varPredicate, Supplier<R> varSupplier,
                                               Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction);

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

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, R> R matches(V value,
                                               Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                               Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                               Class<Null> nullClass, Supplier<R> nullSupplier,
                                               Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction,
                          defaultClass, defaultSupplier);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, R> R matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Function<T3, R> thirdFunction,
                                       Class<Null> nullClass, Supplier<R> nullSupplier,
                                       Class<Var> varClass, Function<V, R> varFunction) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction,
                           varClass, varFunction);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, R> R matches(V value,
                                               Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                               Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                               Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                               Class<Null> nullClass, Supplier<R>  nullSupplier,
                                               Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                               Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction,
                           varClass, varPredicate, varSupplier,
                           defaultClass, defaultSupplier);
        }
    }

    public static <V, T1, T2, T3, T4> void matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Consumer<T3> thirdConsumer,
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

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4> void matches(V value,
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

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4> void matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Consumer<T3> thirdConsumer,
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

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4> void matches(V value,
                                                   Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                   Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                   Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                   Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                   Class<Null> nullClass, Runnable nullConsumer,
                                                   Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, firstClazz,  firstPredicate,  firstConsumer,
                           secondClazz, secondPredicate, secondConsumer,
                           thirdClazz,  thirdPredicate,  thirdConsumer,
                           fourthClazz, fourthPredicate, fourthConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4> void matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Consumer<T3> thirdConsumer,
                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                       Class<Null> nullClass, Runnable nullConsumer,
                                       Class<Var> varClass,   Consumer<V> varConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, firstClazz,  firstPredicate,  firstConsumer,
                           secondClazz, secondPredicate, secondConsumer,
                           thirdClazz,  thirdPredicate,  thirdConsumer,
                           fourthClazz, fourthPredicate, fourthConsumer,
                    varClass, varConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4> void matches(V value,
                                                   Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                   Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                   Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                   Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                   Class<Null> nullClass, Runnable nullConsumer,
                                                   Class<Var> varClass, Predicate<V>  varPredicate, Runnable varConsumer,
                                                   Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, firstClazz,  firstPredicate,  firstConsumer,
                           secondClazz, secondPredicate, secondConsumer,
                           thirdClazz,  thirdPredicate,  thirdConsumer,
                           fourthClazz, fourthPredicate, fourthConsumer,
                    varClass, varPredicate, varConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4> void matches(V value,
                                                   Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                   Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                   Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                   Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                   Class<Var> varClazz, Predicate<V>  varPredicate, Runnable varConsumer,
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

    public static <V, T1, T2, T3, T4, R> R matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Function<T3, R> thirdFunction,
                                       Class<T4> forthClazz,  Predicate<T4> fourthPredicate, Function<T4, R> forthFunction) {
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

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, R> R matches(V value,
                                                   Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                   Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                   Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                   Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                   Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = matches(value, firstClazz, firstPredicate, firstFunction, secondClazz, secondPredicate, secondFunction,
                                  thirdClazz, thirdPredicate, thirdFunction, forthClazz,  forthPredicate,  forthFunction);
        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, R> R matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Function<T3, R> thirdFunction,
                                       Class<T4> forthClazz,  Predicate<T4> forthPredicate,  Function<T4, R> forthFunction,
                                       Class<Var> varClass,   Function<V, R> varFunction) {
        R result = matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction,
                                  forthClazz,  forthPredicate,  forthFunction);
        if (result != null) {
            return result;
        } else {
            return varFunction.apply(value);
        }
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V, T1, T2, T3, T4, R> R matches(V value,
                                                   Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                   Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                   Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                   Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                   Class<Var> varClass, Predicate<V>  varPredicate, Supplier<R> varSupplier,
                                                   Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction,
                                  forthClazz,  forthPredicate,  forthFunction);
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

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, R> R matches(V value,
                                                   Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                   Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                   Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                   Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                   Class<Null> nullClass, Supplier<R> nullSupplier,
                                                   Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction,
                                  forthClazz,  forthPredicate,  forthFunction,
                           defaultClass, defaultSupplier);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, R> R matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Function<T3, R> thirdFunction,
                                       Class<T4> forthClazz,  Predicate<T4> forthPredicate,  Function<T4, R> forthFunction,
                                       Class<Null> nullClass, Supplier<R> nullSupplier,
                                       Class<Var> varClass,   Function<V, R> varFunction) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction,
                                  forthClazz,  forthPredicate,  forthFunction,
                           varClass, varFunction);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, R> R matches(V value,
                                                   Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                   Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                   Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                   Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                   Class<Null> nullClass, Supplier<R>  nullSupplier,
                                                   Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                                   Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction,
                                  forthClazz,  forthPredicate,  forthFunction,
                           varClass, varPredicate, varSupplier,
                           defaultClass, defaultSupplier);
        }
    }

    public static <V, T1, T2, T3, T4, T5> void matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Consumer<T3> thirdConsumer,
                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                       Class<T5> fifthClazz,  Predicate<T5> fifthPredicate,  Consumer<T5> fifthConsumer) {
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

    public static <V, T1, T2, T3, T4, T5, R> R matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Function<T3, R> thirdFunction,
                                       Class<T4> forthClazz,  Predicate<T4> fourthPredicate, Function<T4, R> forthFunction,
                                       Class<T5> fifthClazz,  Predicate<T5> fifthPredicate,  Function<T5, R> fifthFunction) {
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

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5> void matches(V value,
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

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5> void matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Consumer<T3> thirdConsumer,
                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                       Class<T5> fifthClazz,  Predicate<T5> fifthPredicate,  Consumer<T5> fifthConsumer,
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

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5> void matches(V value,
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
            matches(value, firstClazz,  firstPredicate,  firstConsumer,
                           secondClazz, secondPredicate, secondConsumer,
                           thirdClazz,  thirdPredicate,  thirdConsumer,
                           fourthClazz, fourthPredicate, fourthConsumer,
                           fifthClazz,  fifthPredicate,  fifthConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5> void matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Consumer<T3> thirdConsumer,
                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                       Class<T5> fifthClazz,  Predicate<T5> fifthPredicate,  Consumer<T5> fifthConsumer,
                                       Class<Null> nullClass, Runnable nullConsumer,
                                       Class<Var> varClass,   Consumer<V> varConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, firstClazz,  firstPredicate,  firstConsumer,
                           secondClazz, secondPredicate, secondConsumer,
                           thirdClazz,  thirdPredicate,  thirdConsumer,
                           fourthClazz, fourthPredicate, fourthConsumer,
                           fifthClazz,  fifthPredicate,  fifthConsumer,
                    varClass, varConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5> void matches(V value,
                                                       Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                       Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                       Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                                       Class<Null> nullClass, Runnable nullConsumer,
                                                       Class<Var> varClass, Predicate<V>  varPredicate, Runnable varConsumer,
                                                       Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, firstClazz,  firstPredicate,  firstConsumer,
                           secondClazz, secondPredicate, secondConsumer,
                           thirdClazz,  thirdPredicate,  thirdConsumer,
                           fourthClazz, fourthPredicate, fourthConsumer,
                           fifthClazz,  fifthPredicate,  fifthConsumer,
                    varClass, varPredicate, varConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5> void matches(V value,
                                                       Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                       Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                       Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                                       Class<Var> varClazz, Predicate<V>  varPredicate, Runnable varConsumer,
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

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, R> R matches(V value,
                                                       Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                       Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                       Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                       Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                                       Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = matches(value, firstClazz, firstPredicate, firstFunction, secondClazz, secondPredicate, secondFunction,
                                  thirdClazz, thirdPredicate, thirdFunction, forthClazz,  forthPredicate,  forthFunction,
                                  fifthClazz, fifthPredicate, fifthFunction);
        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, R> R matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Function<T3, R> thirdFunction,
                                       Class<T4> forthClazz,  Predicate<T4> forthPredicate,  Function<T4, R> forthFunction,
                                       Class<T5> fifthClazz,  Predicate<T5> fifthPredicate,  Function<T5, R> fifthFunction,
                                       Class<Var> varClass,   Function<V, R> varFunction) {
        R result = matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction,
                                  forthClazz,  forthPredicate,  forthFunction,
                                  fifthClazz,  fifthPredicate,  fifthFunction);
        if (result != null) {
            return result;
        } else {
            return varFunction.apply(value);
        }
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V, T1, T2, T3, T4, T5, R> R matches(V value,
                                                       Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                       Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                       Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                       Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                                       Class<Var> varClass, Predicate<V>  varPredicate, Supplier<R> varSupplier,
                                                       Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction,
                                  forthClazz,  forthPredicate,  forthFunction,
                                  fifthClazz,  fifthPredicate,  fifthFunction);
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

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, R> R matches(V value,
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
            return matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction,
                                  forthClazz,  forthPredicate,  forthFunction,
                                  fifthClazz,  fifthPredicate,  fifthFunction,
                           defaultClass, defaultSupplier);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, R> R matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Function<T3, R> thirdFunction,
                                       Class<T4> forthClazz,  Predicate<T4> forthPredicate,  Function<T4, R> forthFunction,
                                       Class<T5> fifthClazz,  Predicate<T5> fifthPredicate,  Function<T5, R> fifthFunction,
                                       Class<Null> nullClass, Supplier<R> nullSupplier,
                                       Class<Var> varClass,   Function<V, R> varFunction) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction,
                                  forthClazz,  forthPredicate,  forthFunction,
                                  fifthClazz,  fifthPredicate,  fifthFunction,
                           varClass, varFunction);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, R> R matches(V value,
                                                       Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                       Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                       Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                       Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                                       Class<Null> nullClass, Supplier<R>  nullSupplier,
                                                       Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                                       Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction,
                                  forthClazz,  forthPredicate,  forthFunction,
                                  fifthClazz,  fifthPredicate,  fifthFunction,
                           varClass, varPredicate, varSupplier,
                           defaultClass, defaultSupplier);
        }
    }

    public static <V, T1, T2, T3, T4, T5, T6> void matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Consumer<T3> thirdConsumer,
                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                       Class<T5> fifthClazz,  Predicate<T5> fifthPredicate,  Consumer<T5> fifthConsumer,
                                       Class<T6> sixthClazz,  Predicate<T6> sixthPredicate,  Consumer<T6> sixthConsumer) {
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

    public static <V, T1, T2, T3, T4, T5, T6, R> R matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Function<T3, R> thirdFunction,
                                       Class<T4> forthClazz,  Predicate<T4> fourthPredicate, Function<T4, R> forthFunction,
                                       Class<T5> fifthClazz,  Predicate<T5> fifthPredicate,  Function<T5, R> fifthFunction,
                                       Class<T6> sixthClazz,  Predicate<T6> sixthPredicate,  Function<T6, R> sixthFunction) {
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

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6> void matches(V value,
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

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6> void matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Consumer<T3> thirdConsumer,
                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                       Class<T5> fifthClazz,  Predicate<T5> fifthPredicate,  Consumer<T5> fifthConsumer,
                                       Class<T6> sixthClazz,  Predicate<T6> sixthPredicate,  Consumer<T6> sixthConsumer,
                                       Class<Var> varClass,   Consumer<V> varConsumer) {
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

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6> void matches(V value,
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
            matches(value, firstClazz,  firstPredicate,  firstConsumer,
                           secondClazz, secondPredicate, secondConsumer,
                           thirdClazz,  thirdPredicate,  thirdConsumer,
                           fourthClazz, fourthPredicate, fourthConsumer,
                           fifthClazz,  fifthPredicate,  fifthConsumer,
                           sixthClazz,  sixthPredicate,  sixthConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6> void matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Consumer<T3> thirdConsumer,
                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                       Class<T5> fifthClazz,  Predicate<T5> fifthPredicate,  Consumer<T5> fifthConsumer,
                                       Class<T6> sixthClazz,  Predicate<T6> sixthPredicate,  Consumer<T6> sixthConsumer,
                                       Class<Null> nullClass, Runnable nullConsumer,
                                       Class<Var> varClass,   Consumer<V> varConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, firstClazz,  firstPredicate,  firstConsumer,
                           secondClazz, secondPredicate, secondConsumer,
                           thirdClazz,  thirdPredicate,  thirdConsumer,
                           fourthClazz, fourthPredicate, fourthConsumer,
                           fifthClazz,  fifthPredicate,  fifthConsumer,
                           sixthClazz,  sixthPredicate,  sixthConsumer,
                    varClass, varConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6> void matches(V value,
                                                           Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                           Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                           Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                           Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                                           Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Consumer<T6> sixthConsumer,
                                                           Class<Null> nullClass, Runnable nullConsumer,
                                                           Class<Var> varClass, Predicate<V>  varPredicate, Runnable varConsumer,
                                                           Class<Else> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, firstClazz,  firstPredicate,  firstConsumer,
                           secondClazz, secondPredicate, secondConsumer,
                           thirdClazz,  thirdPredicate,  thirdConsumer,
                           fourthClazz, fourthPredicate, fourthConsumer,
                           fifthClazz,  fifthPredicate,  fifthConsumer,
                           sixthClazz,  sixthPredicate,  sixthConsumer,
                    varClass, varPredicate, varConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6> void matches(V value,
                                                           Class<T1> firstClazz, Predicate<T1> firstPredicate, Consumer<T1> firstConsumer,
                                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                                           Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Consumer<T3> thirdConsumer,
                                                           Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                                           Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Consumer<T5> fifthConsumer,
                                                           Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Consumer<T6> sixthConsumer,
                                                           Class<Var> varClazz, Predicate<V>  varPredicate, Runnable varConsumer,
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

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6, R> R matches(V value,
                                                           Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                           Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                           Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                           Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                                           Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Function<T6, R> sixthFunction,
                                                           Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = matches(value, firstClazz, firstPredicate, firstFunction, secondClazz, secondPredicate, secondFunction,
                                  thirdClazz, thirdPredicate, thirdFunction, forthClazz,  forthPredicate,  forthFunction,
                                  fifthClazz, fifthPredicate, fifthFunction, sixthClazz,  sixthPredicate,  sixthFunction);
        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6, R> R matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Function<T3, R> thirdFunction,
                                       Class<T4> forthClazz,  Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                       Class<T5> fifthClazz,  Predicate<T5> fifthPredicate,  Function<T5, R> fifthFunction,
                                       Class<T6> sixthClazz,  Predicate<T6> sixthPredicate,  Function<T6, R> sixthFunction,
                                       Class<Var> varClass,   Function<V, R> varFunction) {
        R result = matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction,
                                  forthClazz,  forthPredicate,  forthFunction,
                                  fifthClazz,  fifthPredicate,  fifthFunction,
                                  sixthClazz,  sixthPredicate,  sixthFunction);
        if (result != null) {
            return result;
        } else {
            return varFunction.apply(value);
        }
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V, T1, T2, T3, T4, T5, T6, R> R matches(V value,
                                                           Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                           Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                           Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                           Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                                           Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Function<T6, R> sixthFunction,
                                                           Class<Var> varClass, Predicate<V>  varPredicate, Supplier<R> varSupplier,
                                                           Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        R result = matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction,
                                  forthClazz,  forthPredicate,  forthFunction,
                                  fifthClazz,  fifthPredicate,  fifthFunction,
                                  sixthClazz,  sixthPredicate,  sixthFunction);
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

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6, R> R matches(V value,
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
            return matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction,
                                  forthClazz,  forthPredicate,  forthFunction,
                                  fifthClazz,  fifthPredicate,  fifthFunction,
                                  sixthClazz,  sixthPredicate,  sixthFunction,
                           defaultClass, defaultSupplier);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6, R> R matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Function<T3, R> thirdFunction,
                                       Class<T4> forthClazz,  Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                       Class<T5> fifthClazz,  Predicate<T5> fifthPredicate,  Function<T5, R> fifthFunction,
                                       Class<T6> sixthClazz,  Predicate<T6> sixthPredicate,  Function<T6, R> sixthFunction,
                                       Class<Null> nullClass, Supplier<R> nullSupplier,
                                       Class<Var> varClass,   Function<V, R> varFunction) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction,
                                  forthClazz,  forthPredicate,  forthFunction,
                                  fifthClazz,  fifthPredicate,  fifthFunction,
                                  sixthClazz,  sixthPredicate,  sixthFunction,
                           varClass, varFunction);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6, R> R matches(V value,
                                                           Class<T1> firstClazz, Predicate<T1> firstPredicate, Function<T1, R> firstFunction,
                                                           Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                                           Class<T3> thirdClazz, Predicate<T3> thirdPredicate, Function<T3, R> thirdFunction,
                                                           Class<T4> forthClazz, Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                                           Class<T5> fifthClazz, Predicate<T5> fifthPredicate, Function<T5, R> fifthFunction,
                                                           Class<T6> sixthClazz, Predicate<T6> sixthPredicate, Function<T6, R> sixthFunction,
                                                           Class<Null> nullClass, Supplier<R>  nullSupplier,
                                                           Class<Var> varClass, Predicate<V> varPredicate, Supplier<R> varSupplier,
                                                           Class<Else> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction,
                                  forthClazz,  forthPredicate,  forthFunction,
                                  fifthClazz,  fifthPredicate,  fifthFunction,
                                  sixthClazz,  sixthPredicate,  sixthFunction,
                           varClass, varPredicate, varSupplier,
                           defaultClass, defaultSupplier);
        }
    }
}
