package org.kl.handle;

import org.kl.reflect.Reflection;
import org.kl.state.Default;
import org.kl.state.Null;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class GuardPattern {

    public static Predicate<Byte>    lt(byte value)   { return t -> t < value; }
    public static Predicate<Short>   lt(short value)  { return t -> t < value; }
    public static Predicate<Integer> lt(int value)    { return t -> t < value; }
    public static Predicate<Long>    lt(long value)   { return t -> t < value; }
    public static Predicate<Float>   lt(float value)  { return t -> t < value; }
    public static Predicate<Double>  lt(double value) { return t -> t < value; }

    public static Predicate<Byte>    lessThan(byte value)   { return t -> t < value; }
    public static Predicate<Short>   lessThan(short value)  { return t -> t < value; }
    public static Predicate<Integer> lessThan(int value)    { return t -> t < value; }
    public static Predicate<Long>    lessThan(long value)   { return t -> t < value; }
    public static Predicate<Float>   lessThan(float value)  { return t -> t < value; }
    public static Predicate<Double>  lessThan(double value) { return t -> t < value; }

    public static Predicate<Byte>    le(byte value)   { return t -> t <= value; }
    public static Predicate<Short>   le(short value)  { return t -> t <= value; }
    public static Predicate<Integer> le(int value)    { return t -> t <= value; }
    public static Predicate<Long>    le(long value)   { return t -> t <= value; }
    public static Predicate<Float>   le(float value)  { return t -> t <= value; }
    public static Predicate<Double>  le(double value) { return t -> t <= value; }

    public static Predicate<Byte>    lessThanOrEqual(byte value)   { return t -> t <= value; }
    public static Predicate<Short>   lessThanOrEqual(short value)  { return t -> t <= value; }
    public static Predicate<Integer> lessThanOrEqual(int value)    { return t -> t <= value; }
    public static Predicate<Long>    lessThanOrEqual(long value)   { return t -> t <= value; }
    public static Predicate<Float>   lessThanOrEqual(float value)  { return t -> t <= value; }
    public static Predicate<Double>  lessThanOrEqual(double value) { return t -> t <= value; }

    public static Predicate<Byte>    gt(byte value)   { return t -> t > value; }
    public static Predicate<Short>   gt(short value)  { return t -> t > value; }
    public static Predicate<Integer> gt(int value)    { return t -> t > value; }
    public static Predicate<Long>    gt(long value)   { return t -> t > value; }
    public static Predicate<Float>   gt(float value)  { return t -> t > value; }
    public static Predicate<Double>  gt(double value) { return t -> t > value; }

    public static Predicate<Byte>    greaterThan(byte value)   { return t -> t > value; }
    public static Predicate<Short>   greaterThan(short value)  { return t -> t > value; }
    public static Predicate<Integer> greaterThan(int value)    { return t -> t > value; }
    public static Predicate<Long>    greaterThan(long value)   { return t -> t > value; }
    public static Predicate<Float>   greaterThan(float value)  { return t -> t > value; }
    public static Predicate<Double>  greaterThan(double value) { return t -> t > value; }

    public static Predicate<Byte>    ge(byte value)   { return t -> t >= value; }
    public static Predicate<Short>   ge(short value)  { return t -> t >= value; }
    public static Predicate<Integer> ge(int value)    { return t -> t >= value; }
    public static Predicate<Long>    ge(long value)   { return t -> t >= value; }
    public static Predicate<Float>   ge(float value)  { return t -> t >= value; }
    public static Predicate<Double>  ge(double value) { return t -> t >= value; }

    public static Predicate<Byte>    greaterThanOrEqual(byte value)   { return t -> t >= value; }
    public static Predicate<Short>   greaterThanOrEqual(short value)  { return t -> t >= value; }
    public static Predicate<Integer> greaterThanOrEqual(int value)    { return t -> t >= value; }
    public static Predicate<Long>    greaterThanOrEqual(long value)   { return t -> t >= value; }
    public static Predicate<Float>   greaterThanOrEqual(float value)  { return t -> t >= value; }
    public static Predicate<Double>  greaterThanOrEqual(double value) { return t -> t >= value; }

    public static Predicate<Byte>    eq(byte value)   { return t -> t == value; }
    public static Predicate<Short>   eq(short value)  { return t -> t == value; }
    public static Predicate<Integer> eq(int value)    { return t -> t == value; }
    public static Predicate<Long>    eq(long value)   { return t -> t == value; }
    public static Predicate<Float>   eq(float value)  { return t -> t == value; }
    public static Predicate<Double>  eq(double value) { return t -> t == value; }
    public static Predicate<Character> eq(char value)    { return t -> t == value; }
    public static Predicate<Boolean>   eq(boolean value) { return t -> t == value; }

    public static Predicate<Byte>    equal(byte value)   { return t -> t == value; }
    public static Predicate<Short>   equal(short value)  { return t -> t == value; }
    public static Predicate<Integer> equal(int value)    { return t -> t == value; }
    public static Predicate<Long>    equal(long value)   { return t -> t == value; }
    public static Predicate<Float>   equal(float value)  { return t -> t == value; }
    public static Predicate<Double>  equal(double value) { return t -> t == value; }
    public static Predicate<Character> equal(char value)    { return t -> t == value; }
    public static Predicate<Boolean>   equal(boolean value) { return t -> t == value; }

    public static Predicate<Byte>    ne(byte value)   { return t -> t != value; }
    public static Predicate<Short>   ne(short value)  { return t -> t != value; }
    public static Predicate<Integer> ne(int value)    { return t -> t != value; }
    public static Predicate<Long>    ne(long value)   { return t -> t != value; }
    public static Predicate<Float>   ne(float value)  { return t -> t != value; }
    public static Predicate<Double>  ne(double value) { return t -> t != value; }
    public static Predicate<Character> ne(char value)    { return t -> t != value; }
    public static Predicate<Boolean>   ne(boolean value) { return t -> t != value; }

    public static Predicate<Byte>    notEqual(byte value)   { return t -> t != value; }
    public static Predicate<Short>   notEqual(short value)  { return t -> t != value; }
    public static Predicate<Integer> notEqual(int value)    { return t -> t != value; }
    public static Predicate<Long>    notEqual(long value)   { return t -> t != value; }
    public static Predicate<Float>   notEqual(float value)  { return t -> t != value; }
    public static Predicate<Double>  notEqual(double value) { return t -> t != value; }
    public static Predicate<Character> notEqual(char value)    { return t -> t != value; }
    public static Predicate<Boolean>   notEqual(boolean value) { return t -> t != value; }

    public static <V, T> void matches(V value,
                                      Class<T> clazz, Predicate<T> predicate, Consumer<T> consumer) {
        Class<?> valueClass = value.getClass();

        if (clazz == valueClass || Reflection.isPrimitive(clazz, valueClass)) {
            if (predicate.test((T) value)) {
                consumer.accept((T) value);
            }
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
    public static <V, T> void matches(V value,
                                      Class<T> clazz, Predicate<T> predicate, Consumer<T> consumer,
                                      Class<Default> defaultClass, Runnable defaultConsumer) {
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
                                      Class<Null> nullClass, Runnable nullConsumer,
                                      Class<Default> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, clazz, predicate, consumer, defaultClass, defaultConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T, R> R matches(V value,
                                      Class<T> clazz, Predicate<T> predicate, Function<T, R> function,
                                      Class<Default> defaultClass, Supplier<R> defaultSupplier) {
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
                                      Class<Null> nullClass, Supplier<R> nullSupplier,
                                      Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, clazz, predicate, function, defaultClass, defaultSupplier);
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
    public static <V, T1, T2> void matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<Default> defaultClass, Runnable defaultConsumer) {
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
                                      Class<Null> nullClass, Runnable nullConsumer,
                                      Class<Default> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, firstClazz,  firstPredicate,  firstConsumer,
                           secondClazz, secondPredicate, secondConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, R> R matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate,  Function<T2, R> secondFunction,
                                       Class<Default> defaultClass, Supplier<R> defaultSupplier) {
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
                                      Class<T2> secondClazz, Predicate<T2> secondPredicate,  Function<T2, R> secondFunction,
                                      Class<Null> nullClass, Supplier<R> nullSupplier,
                                      Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
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
    public static <V, T1, T2, T3> void matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Consumer<T3> thirdConsumer,
                                       Class<Default> defaultClass, Runnable defaultConsumer) {
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
                                       Class<Null> nullClass, Runnable nullConsumer,
                                       Class<Default> defaultClass, Runnable defaultConsumer) {
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
    public static <V, T1, T2, T3, R> R matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Function<T3, R> thirdFunction,
                                       Class<Default> defaultClass, Supplier<R> defaultSupplier) {
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
                                       Class<Null> nullClass, Supplier<R> nullSupplier,
                                       Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, firstClazz,  firstPredicate,  firstFunction,
                                  secondClazz, secondPredicate, secondFunction,
                                  thirdClazz,  thirdPredicate,  thirdFunction,
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
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Consumer<T3> thirdConsumer,
                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                       Class<Default> defaultClass, Runnable defaultConsumer) {
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
                                       Class<Null> nullClass, Runnable nullConsumer,
                                       Class<Default> defaultClass, Runnable defaultConsumer) {
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
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Function<T3, R> thirdFunction,
                                       Class<T4> forthClazz,  Predicate<T4> forthPredicate,  Function<T4, R> forthFunction,
                                       Class<Default> defaultClass, Supplier<R> defaultSupplier) {
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
                                       Class<Null> nullClass, Supplier<R> nullSupplier,
                                       Class<Default> defaultClass, Supplier<R> defaultSupplier) {
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
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Consumer<T3> thirdConsumer,
                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                       Class<T5> fifthClazz,  Predicate<T5> fifthPredicate,  Consumer<T5> fifthConsumer,
                                       Class<Default> defaultClass, Runnable defaultConsumer) {
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
                                       Class<Null> nullClass, Runnable nullConsumer,
                                       Class<Default> defaultClass, Runnable defaultConsumer) {
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
    public static <V, T1, T2, T3, T4, T5, R> R matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Function<T3, R> thirdFunction,
                                       Class<T4> forthClazz,  Predicate<T4> forthPredicate,  Function<T4, R> forthFunction,
                                       Class<T5> fifthClazz,  Predicate<T5> fifthPredicate,  Function<T5, R> fifthFunction,
                                       Class<Default> defaultClass, Supplier<R> defaultSupplier) {
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
                                       Class<Null> nullClass, Supplier<R> nullSupplier,
                                       Class<Default> defaultClass, Supplier<R> defaultSupplier) {
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
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Consumer<T3> thirdConsumer,
                                       Class<T4> fourthClazz, Predicate<T4> fourthPredicate, Consumer<T4> fourthConsumer,
                                       Class<T5> fifthClazz,  Predicate<T5> fifthPredicate,  Consumer<T5> fifthConsumer,
                                       Class<T6> sixthClazz,  Predicate<T6> sixthPredicate,  Consumer<T6> sixthConsumer,
                                       Class<Default> defaultClass, Runnable defaultConsumer) {
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
                                       Class<Null> nullClass, Runnable nullConsumer,
                                       Class<Default> defaultClass, Runnable defaultConsumer) {
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
    public static <V, T1, T2, T3, T4, T5, T6, R> R matches(V value,
                                       Class<T1> firstClazz,  Predicate<T1> firstPredicate,  Function<T1, R> firstFunction,
                                       Class<T2> secondClazz, Predicate<T2> secondPredicate, Function<T2, R> secondFunction,
                                       Class<T3> thirdClazz,  Predicate<T3> thirdPredicate,  Function<T3, R> thirdFunction,
                                       Class<T4> forthClazz,  Predicate<T4> forthPredicate, Function<T4, R> forthFunction,
                                       Class<T5> fifthClazz,  Predicate<T5> fifthPredicate,  Function<T5, R> fifthFunction,
                                       Class<T6> sixthClazz,  Predicate<T6> sixthPredicate,  Function<T6, R> sixthFunction,
                                       Class<Default> defaultClass, Supplier<R> defaultSupplier) {
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
                                       Class<Null> nullClass, Supplier<R> nullSupplier,
                                       Class<Default> defaultClass, Supplier<R> defaultSupplier) {
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
}
