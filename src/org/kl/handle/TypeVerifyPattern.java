package org.kl.handle;

import org.kl.reflect.Reflection;
import org.kl.state.Default;
import org.kl.state.Null;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class TypeVerifyPattern {

    @SuppressWarnings("unused")
    private static <T> Consumer<Object> acceptType(Class<T> clazz, Consumer<? super T> consumer) {
        return x -> {
            if (clazz.isInstance(x)) {
                consumer.accept(clazz.cast(x));
            }
        };
    }

    public static <V, T> void matches(V value,
                                      Class<T> clazz, Consumer<T> consumer) {
        Class<?> valueClass = value.getClass();

        if (clazz == valueClass || Reflection.isPrimitive(clazz, valueClass)) {
            consumer.accept((T) value);
        }
    }

    public static <V, T, R> R matches(V value,
                                      Class<T> clazz, Function<T, R> function) {
        Class<?> valueClass = value.getClass();

        if (clazz == valueClass || Reflection.isPrimitive(clazz, valueClass)) {
            return function.apply((T) value);
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V, T> void matches(V value,
                                      Class<T> clazz, Consumer<T> consumer,
                                      Class<Default> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (clazz == valueClass || Reflection.isPrimitive(clazz, valueClass)) {
            consumer.accept((T) value);
            return;
        }

        defaultConsumer.run();
    }

    @SuppressWarnings("unused")
    public static <V, T> void matches(V value,
                                      Class<T> clazz, Consumer<T> consumer,
                                      Class<Null> nullClass, Runnable nullConsumer,
                                      Class<Default> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, clazz, consumer, defaultClass, defaultConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T, R> R matches(V value,
                                      Class<T> clazz, Function<T, R> function,
                                      Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        R result = matches(value, clazz, function);

        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V, T, R> R matches(V value,
                                      Class<T> clazz, Function<T, R> function,
                                      Class<Null> nullClass, Supplier<R> nullSupplier,
                                      Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, clazz, function, defaultClass, defaultSupplier);
        }
    }

    public static <V, T1, T2> void matches(V value,
                                           Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                           Class<T2> secondClazz, Consumer<T2> secondConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstConsumer.accept((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondConsumer.accept((T2) value);
        }
    }

    public static <V, T1, T2, R> R matches(V value,
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
    public static <V, T1, T2> void matches(V value,
                                           Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                           Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstConsumer.accept((T1) value);
            return;
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondConsumer.accept((T2) value);
            return;
        }

        defaultConsumer.run();
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2> void matches(V value,
                                           Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                           Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                           Class<Null> nullClass, Runnable nullConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, firstClazz, firstConsumer, secondClazz, secondConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, R> R matches(V value,
                                           Class<T1> firstClazz, Function<T1, R> firstFunction,
                                           Class<T2> secondClazz, Function<T2, R> secondFunction,
                                           Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        R result = matches(value, firstClazz, firstFunction, secondClazz, secondFunction);

        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, R> R matches(V value,
                                           Class<T1> firstClazz, Function<T1, R> firstFunction,
                                           Class<T2> secondClazz, Function<T2, R> secondFunction,
                                           Class<Null> nullClass, Supplier<R> nullSupplier,
                                           Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  defaultClass, defaultSupplier);
        }
    }

    public static <V, T1, T2, T3> void matches(V value,
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

    public static <V, T1, T2, T3, R> R matches(V value,
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
    public static <V, T1, T2, T3> void matches(V value,
                                               Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                               Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                               Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                               Class<Default> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstConsumer.accept((T1) value);
            return;
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondConsumer.accept((T2) value);
            return;
        } else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            thirdConsumer.accept((T3) value);
            return;
        }

        defaultConsumer.run();
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3> void matches(V value,
                                               Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                               Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                               Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                               Class<Null> nullClass, Runnable nullConsumer,
                                               Class<Default> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, firstClazz, firstConsumer, secondClazz,  secondConsumer,
                           thirdClazz, thirdConsumer, defaultClass, defaultConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, R> R matches(V value,
                                               Class<T1> firstClazz, Function<T1, R> firstFunction,
                                               Class<T2> secondClazz, Function<T2, R> secondFunction,
                                               Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                               Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        R result = matches(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  thirdClazz, thirdFunction);
        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, R> R matches(V value,
                                               Class<T1> firstClazz,  Function<T1, R> firstFunction,
                                               Class<T2> secondClazz, Function<T2, R> secondFunction,
                                               Class<T3> thirdClazz,  Function<T3, R> thirdFunction,
                                               Class<Null> nullClass, Supplier<R> nullSupplier,
                                               Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  thirdClazz, thirdFunction, defaultClass, defaultSupplier);
        }
    }

    public static <V, T1, T2, T3, T4> void matches(V value,
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

    public static <V, T1, T2, T3, T4, R> R matches(V value,
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
    public static <V, T1, T2, T3, T4> void matches(V value,
                                                   Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                                   Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                   Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                                   Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                                   Class<Default> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstConsumer.accept((T1) value);
            return;
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondConsumer.accept((T2) value);
            return;
        } else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            thirdConsumer.accept((T3) value);
            return;
        } else if (forthClazz == valueClass || Reflection.isPrimitive(forthClazz, valueClass)) {
            forthConsumer.accept((T4) value);
            return;
        }

        defaultConsumer.run();
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4> void matches(V value,
                                                   Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                                   Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                   Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                                   Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                                   Class<Null> nullClass, Runnable nullConsumer,
                                                   Class<Default> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, firstClazz, firstConsumer, secondClazz, secondConsumer,
                           thirdClazz, thirdConsumer, forthClazz,  forthConsumer,
                           defaultClass, defaultConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, R> R matches(V value,
                                                   Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                   Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                   Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                   Class<T4> forthClazz, Function<T4, R> forthFunction,
                                                   Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        R result = matches(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  thirdClazz, thirdFunction, forthClazz,  forthFunction);
        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, R> R matches(V value,
                                                   Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                   Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                   Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                   Class<T4> forthClazz, Function<T4, R> forthFunction,
                                                   Class<Null> nullClass, Supplier<R> nullSupplier,
                                                   Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  thirdClazz, thirdFunction, forthClazz,  forthFunction,
                           defaultClass, defaultSupplier);
        }
    }

    public static <V, T1, T2, T3, T4, T5> void matches(V value,
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

    public static <V, T1, T2, T3, T4, T5, R> R matches(V value,
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
    public static <V, T1, T2, T3, T4, T5> void matches(V value,
                                                       Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                                       Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                       Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                                       Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                                       Class<T5> fifthClazz, Consumer<T5> fifthConsumer,
                                                       Class<Default> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstConsumer.accept((T1) value);
            return;
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondConsumer.accept((T2) value);
            return;
        } else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            thirdConsumer.accept((T3) value);
            return;
        } else if (forthClazz == valueClass || Reflection.isPrimitive(forthClazz, valueClass)) {
            forthConsumer.accept((T4) value);
            return;
        } else if (fifthClazz == valueClass || Reflection.isPrimitive(fifthClazz, valueClass)) {
            fifthConsumer.accept((T5) value);
            return;
        }

        defaultConsumer.run();
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5> void matches(V value,
                                                       Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                                       Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                       Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                                       Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                                       Class<T5> fifthClazz, Consumer<T5> fifthConsumer,
                                                       Class<Null> nullClass, Runnable nullConsumer,
                                                       Class<Default> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, firstClazz, firstConsumer, secondClazz, secondConsumer,
                           thirdClazz, thirdConsumer, forthClazz,  forthConsumer,
                           fifthClazz, fifthConsumer, defaultClass, defaultConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, R> R matches(V value,
                                                       Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                       Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                       Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                       Class<T4> forthClazz, Function<T4, R> forthFunction,
                                                       Class<T5> fifthClazz, Function<T5, R> fifthFunction,
                                                       Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        R result = matches(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  thirdClazz, thirdFunction, forthClazz,  forthFunction,
                                  fifthClazz, fifthFunction);
        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, R> R matches(V value,
                                                       Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                       Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                       Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                       Class<T4> forthClazz, Function<T4, R> forthFunction,
                                                       Class<T5> fifthClazz, Function<T5, R> fifthFunction,
                                                       Class<Null> nullClass, Supplier<R> nullSupplier,
                                                       Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  thirdClazz, thirdFunction, forthClazz,  forthFunction,
                                  fifthClazz, fifthFunction, defaultClass, defaultSupplier);
        }
    }

    public static <V, T1, T2, T3, T4, T5, T6> void matches(V value,
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

    public static <V, T1, T2, T3, T4, T5, T6, R> R matches(V value,
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
    public static <V, T1, T2, T3, T4, T5, T6> void matches(V value,
                                                           Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                                           Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                           Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                                           Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                                           Class<T5> fifthClazz, Consumer<T5> fifthConsumer,
                                                           Class<T6> sixthClazz, Consumer<T6> sixthConsumer,
                                                           Class<Default> defaultClass, Runnable defaultConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstConsumer.accept((T1) value);
            return;
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondConsumer.accept((T2) value);
            return;
        } else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            thirdConsumer.accept((T3) value);
            return;
        } else if (forthClazz == valueClass || Reflection.isPrimitive(forthClazz, valueClass)) {
            forthConsumer.accept((T4) value);
            return;
        } else if (fifthClazz == valueClass || Reflection.isPrimitive(fifthClazz, valueClass)) {
            fifthConsumer.accept((T5) value);
            return;
        } else if (sixthClazz == valueClass || Reflection.isPrimitive(sixthClazz, valueClass)) {
            sixthConsumer.accept((T6) value);
            return;
        }

        defaultConsumer.run();
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6> void matches(V value,
                                                           Class<T1> firstClazz, Consumer<T1> firstConsumer,
                                                           Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                           Class<T3> thirdClazz, Consumer<T3> thirdConsumer,
                                                           Class<T4> forthClazz, Consumer<T4> forthConsumer,
                                                           Class<T5> fifthClazz, Consumer<T5> fifthConsumer,
                                                           Class<T6> sixthClazz, Consumer<T6> sixthConsumer,
                                                           Class<Null> nullClass, Runnable nullConsumer,
                                                           Class<Default> defaultClass, Runnable defaultConsumer) {
        if (value == null) {
            nullConsumer.run();
        } else {
            matches(value, firstClazz, firstConsumer, secondClazz, secondConsumer,
                           thirdClazz, thirdConsumer, forthClazz,  forthConsumer,
                           fifthClazz, fifthConsumer, sixthClazz,  sixthConsumer,
                    defaultClass, defaultConsumer);
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6, R> R matches(V value,
                                                           Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                           Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                           Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                           Class<T4> forthClazz, Function<T4, R> forthFunction,
                                                           Class<T5> fifthClazz, Function<T5, R> fifthFunction,
                                                           Class<T6> sixthClazz, Function<T6, R> sixthFunction,
                                                           Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        R result = matches(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  thirdClazz, thirdFunction, forthClazz,  forthFunction,
                                  fifthClazz, fifthFunction, sixthClazz,  sixthFunction);
        if (result != null) {
            return result;
        } else {
            return defaultSupplier.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6, R> R matches(V value,
                                                           Class<T1> firstClazz, Function<T1, R> firstFunction,
                                                           Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                           Class<T3> thirdClazz, Function<T3, R> thirdFunction,
                                                           Class<T4> forthClazz, Function<T4, R> forthFunction,
                                                           Class<T5> fifthClazz, Function<T5, R> fifthFunction,
                                                           Class<T6> sixthClazz, Function<T6, R> sixthFunction,
                                                           Class<Null> nullClass, Supplier<R> nullSupplier,
                                                           Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        if (value == null) {
            return nullSupplier.get();
        } else {
            return matches(value, firstClazz, firstFunction, secondClazz, secondFunction,
                                  thirdClazz, thirdFunction, forthClazz,  forthFunction,
                                  fifthClazz, fifthFunction, sixthClazz,  sixthFunction,
                           defaultClass, defaultSupplier);
        }

    }
}
