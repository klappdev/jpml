package org.kl.handle;

import org.kl.reflect.Reflection;
import org.kl.state.Default;

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
        if (clazz == value.getClass()) {
            return function.apply((T) value);
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V, T> void matches(V value,
                                      Class<T> clazz, Consumer<T> consumer,
                                      Class<Default> defaultClass, Runnable defaultConsumer) {
        if (clazz == value.getClass()) {
            consumer.accept((T) value);
            return;
        }

        defaultConsumer.run();
    }

    @SuppressWarnings("unused")
    public static <V, T, R> R matches(V value,
                                      Class<T> clazz, Function<T, R> function,
                                      Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        if (clazz == value.getClass()) {
            return function.apply((T) value);
        }

        return defaultSupplier.get();
    }

    public static <V, T1, T2> void matches(V value,
                                           Class<T1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<T2> secondClazz, Consumer<T2> secondConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstConsumer.accept((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondConsumer.accept((T2) value);
        }
    }

    public static <V, T1, T2, R> R matches(V value,
                                           Class<T1> firstClazz,  Function<T1, R> firstFunction,
                                           Class<T2> secondClazz, Function<T2, R> secondFunction) {
        if (firstClazz == value.getClass()) {
            return firstFunction.apply((T1) value);
        } else if (secondClazz == value.getClass()) {
            return secondFunction.apply((T2) value);
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2> void matches(V value,
                                           Class<T1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) {
        if (firstClazz == value.getClass()) {
            firstConsumer.accept((T1) value);
            return;
        } else if (secondClazz == value.getClass()) {
            secondConsumer.accept((T2) value);
            return;
        }

        defaultConsumer.run();
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, R> R matches(V value,
                                           Class<T1> firstClazz,  Function<T1, R> firstFunction,
                                           Class<T2> secondClazz, Function<T2, R> secondFunction,
                                           Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        if (firstClazz == value.getClass()) {
            return firstFunction.apply((T1) value);
        } else if (secondClazz == value.getClass()) {
            return secondFunction.apply((T2) value);
        }

        return defaultSupplier.get();
    }

    public static <V, T1, T2, T3> void matches(V value,
                                               Class<T1> firstClazz,  Consumer<T1> firstConsumer,
                                               Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                               Class<T3> thirdClazz,  Consumer<T3> thirdConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstConsumer.accept((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondConsumer.accept((T2) value);
        } else if (thirdClazz == valueClass || Reflection.isPrimitive(thirdClazz, valueClass)) {
            thirdConsumer.accept((T3) value);
        }
    }

    @SuppressWarnings("Duplicates")
    public static <V, T1, T2, T3, R> R matches(V value,
                                               Class<T1> firstClazz,  Function<T1, R> firstFunction,
                                               Class<T2> secondClazz, Function<T2, R> secondFunction,
                                               Class<T3> thirdClazz,  Function<T3, R> thirdFunction) {
        if (firstClazz == value.getClass()) {
            return firstFunction.apply((T1) value);
        } else if (secondClazz == value.getClass()) {
            return secondFunction.apply((T2) value);
        } else if (thirdClazz == value.getClass()) {
            return thirdFunction.apply((T3) value);
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3> void matches(V value,
                                               Class<T1> firstClazz,  Consumer<T1> firstConsumer,
                                               Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                               Class<T3> thirdClazz,  Consumer<T3> thirdConsumer,
                                               Class<Default> defaultClass, Runnable defaultConsumer) {
        if (firstClazz == value.getClass()) {
            firstConsumer.accept((T1) value);
            return;
        } else if (secondClazz == value.getClass()) {
            secondConsumer.accept((T2) value);
            return;
        } else if (thirdClazz == value.getClass()) {
            thirdConsumer.accept((T3) value);
            return;
        }

        defaultConsumer.run();
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V, T1, T2, T3, R> R matches(V value,
                                               Class<T1> firstClazz,  Function<T1, R> firstFunction,
                                               Class<T2> secondClazz, Function<T2, R> secondFunction,
                                               Class<T3> thirdClazz,  Function<T3, R> thirdFunction,
                                               Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        if (firstClazz == value.getClass()) {
            return firstFunction.apply((T1) value);
        } else if (secondClazz == value.getClass()) {
            return secondFunction.apply((T2) value);
        } else if (thirdClazz == value.getClass()) {
            return thirdFunction.apply((T3) value);
        }

        return defaultSupplier.get();
    }

    public static <V, T1, T2, T3, T4> void matches(V value,
                                                   Class<T1> firstClazz,  Consumer<T1> firstConsumer,
                                                   Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                   Class<T3> thirdClazz,  Consumer<T3> thirdConsumer,
                                                   Class<T4> forthClazz,  Consumer<T4> forthConsumer) {
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass || Reflection.isPrimitive(firstClazz, valueClass)) {
            firstConsumer.accept((T1) value);
        } else if (secondClazz == valueClass || Reflection.isPrimitive(secondClazz, valueClass)) {
            secondConsumer.accept((T2) value);
        } else if (thirdClazz == valueClass  || Reflection.isPrimitive(thirdClazz, valueClass)) {
            thirdConsumer.accept((T3) value);
        } else if (forthClazz == valueClass  || Reflection.isPrimitive(forthClazz, valueClass)) {
            forthConsumer.accept((T4) value);
        }
    }

    @SuppressWarnings("Duplicates")
    public static <V, T1, T2, T3, T4, R> R matches(V value,
                                                   Class<T1> firstClazz,  Function<T1, R> firstFunction,
                                                   Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                   Class<T3> thirdClazz,  Function<T3, R> thirdFunction,
                                                   Class<T4> forthClazz,  Function<T4, R> forthFunction) {
        if (firstClazz == value.getClass()) {
            return firstFunction.apply((T1) value);
        } else if (secondClazz == value.getClass()) {
            return secondFunction.apply((T2) value);
        } else if (thirdClazz == value.getClass()) {
            return thirdFunction.apply((T3) value);
        } else if (forthClazz == value.getClass()) {
            return forthFunction.apply((T4) value);
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4> void matches(V value,
                                                   Class<T1> firstClazz,  Consumer<T1> firstConsumer,
                                                   Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                   Class<T3> thirdClazz,  Consumer<T3> thirdConsumer,
                                                   Class<T4> forthClazz,  Consumer<T4> forthConsumer,
                                                   Class<Default> defaultClass, Runnable defaultConsumer) {
        if (firstClazz == value.getClass()) {
            firstConsumer.accept((T1) value);
            return;
        } else if (secondClazz == value.getClass()) {
            secondConsumer.accept((T2) value);
            return;
        } else if (thirdClazz == value.getClass()) {
            thirdConsumer.accept((T3) value);
            return;
        } else if (forthClazz == value.getClass()) {
            forthConsumer.accept((T4) value);
            return;
        }

        defaultConsumer.run();
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V, T1, T2, T3, T4, R> R matches(V value,
                                                   Class<T1> firstClazz,  Function<T1, R> firstFunction,
                                                   Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                   Class<T3> thirdClazz,  Function<T3, R> thirdFunction,
                                                   Class<T4> forthClazz,  Function<T4, R> forthFunction,
                                                   Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        if (firstClazz == value.getClass()) {
            return firstFunction.apply((T1) value);
        } else if (secondClazz == value.getClass()) {
            return secondFunction.apply((T2) value);
        } else if (thirdClazz == value.getClass()) {
            return thirdFunction.apply((T3) value);
        } else if (forthClazz == value.getClass()) {
            return forthFunction.apply((T4) value);
        }

        return defaultSupplier.get();
    }

    public static <V, T1, T2, T3, T4, T5> void matches(V value,
                                                       Class<T1> firstClazz,  Consumer<T1> firstConsumer,
                                                       Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                       Class<T3> thirdClazz,  Consumer<T3> thirdConsumer,
                                                       Class<T4> forthClazz,  Consumer<T4> forthConsumer,
                                                       Class<T5> fifthClazz,  Consumer<T5> fifthConsumer) {
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

    @SuppressWarnings("Duplicates")
    public static <V, T1, T2, T3, T4, T5, R> R matches(V value,
                                                       Class<T1> firstClazz,  Function<T1, R> firstFunction,
                                                       Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                       Class<T3> thirdClazz,  Function<T3, R> thirdFunction,
                                                       Class<T4> forthClazz,  Function<T4, R> forthFunction,
                                                       Class<T5> fifthClazz,  Function<T5, R> fifthFunction) {
        if (firstClazz == value.getClass()) {
            return firstFunction.apply((T1) value);
        } else if (secondClazz == value.getClass()) {
            return secondFunction.apply((T2) value);
        } else if (thirdClazz == value.getClass()) {
            return thirdFunction.apply((T3) value);
        } else if (forthClazz == value.getClass()) {
            return forthFunction.apply((T4) value);
        } else if (fifthClazz == value.getClass()) {
            return fifthFunction.apply((T5) value);
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5> void matches(V value,
                                                       Class<T1> firstClazz,  Consumer<T1> firstConsumer,
                                                       Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                       Class<T3> thirdClazz,  Consumer<T3> thirdConsumer,
                                                       Class<T4> forthClazz,  Consumer<T4> forthConsumer,
                                                       Class<T5> fifthClazz,  Consumer<T5> fifthConsumer,
                                                       Class<Default> defaultClass, Runnable defaultConsumer) {
        if (firstClazz == value.getClass()) {
            firstConsumer.accept((T1) value);
            return;
        } else if (secondClazz == value.getClass()) {
            secondConsumer.accept((T2) value);
            return;
        } else if (thirdClazz == value.getClass()) {
            thirdConsumer.accept((T3) value);
            return;
        } else if (forthClazz == value.getClass()) {
            forthConsumer.accept((T4) value);
            return;
        } else if (fifthClazz == value.getClass()) {
            fifthConsumer.accept((T5) value);
            return;
        }

        defaultConsumer.run();
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V, T1, T2, T3, T4, T5, R> R matches(V value,
                                                       Class<T1> firstClazz,  Function<T1, R> firstFunction,
                                                       Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                       Class<T3> thirdClazz,  Function<T3, R> thirdFunction,
                                                       Class<T4> forthClazz,  Function<T4, R> forthFunction,
                                                       Class<T5> fifthClazz,  Function<T5, R> fifthFunction,
                                                       Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        if (firstClazz == value.getClass()) {
            return firstFunction.apply((T1) value);
        } else if (secondClazz == value.getClass()) {
            return secondFunction.apply((T2) value);
        } else if (thirdClazz == value.getClass()) {
            return thirdFunction.apply((T3) value);
        } else if (forthClazz == value.getClass()) {
            return forthFunction.apply((T4) value);
        } else if (fifthClazz == value.getClass()) {
            return fifthFunction.apply((T5) value);
        }

        return defaultSupplier.get();
    }

    public static <V, T1, T2, T3, T4, T5, T6> void matches(V value,
                                                           Class<T1> firstClazz,  Consumer<T1> firstConsumer,
                                                           Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                           Class<T3> thirdClazz,  Consumer<T3> thirdConsumer,
                                                           Class<T4> forthClazz,  Consumer<T4> forthConsumer,
                                                           Class<T5> fifthClazz,  Consumer<T5> fifthConsumer,
                                                           Class<T6> sixthClazz,  Consumer<T6> sixthConsumer) {
        if (firstClazz == value.getClass() || Reflection.isPrimitive(firstClazz, value.getClass())) {
            firstConsumer.accept((T1) value);
        } else if (secondClazz == value.getClass() || Reflection.isPrimitive(secondClazz, value.getClass())) {
            secondConsumer.accept((T2) value);
        } else if (thirdClazz == value.getClass() || Reflection.isPrimitive(thirdClazz, value.getClass())) {
            thirdConsumer.accept((T3) value);
        } else if (forthClazz == value.getClass() || Reflection.isPrimitive(forthClazz, value.getClass())) {
            forthConsumer.accept((T4) value);
        } else if (fifthClazz == value.getClass() || Reflection.isPrimitive(fifthClazz, value.getClass())) {
            fifthConsumer.accept((T5) value);
        } else if (sixthClazz == value.getClass() || Reflection.isPrimitive(sixthClazz, value.getClass())) {
            sixthConsumer.accept((T6) value);
        }
    }

    @SuppressWarnings("Duplicates")
    public static <V, T1, T2, T3, T4, T5, T6, R> R matches(V value,
                                                           Class<T1> firstClazz,  Function<T1, R> firstFunction,
                                                           Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                           Class<T3> thirdClazz,  Function<T3, R> thirdFunction,
                                                           Class<T4> forthClazz,  Function<T4, R> forthFunction,
                                                           Class<T5> fifthClazz,  Function<T5, R> fifthFunction,
                                                           Class<T6> sixthClazz,  Function<T6, R> sixthFunction) {
        if (firstClazz == value.getClass()) {
            return firstFunction.apply((T1) value);
        } else if (secondClazz == value.getClass()) {
            return secondFunction.apply((T2) value);
        } else if (thirdClazz == value.getClass()) {
            return thirdFunction.apply((T3) value);
        } else if (forthClazz == value.getClass()) {
            return forthFunction.apply((T4) value);
        } else if (fifthClazz == value.getClass()) {
            return fifthFunction.apply((T5) value);
        } else if (sixthClazz == value.getClass()) {
            return sixthFunction.apply((T6) value);
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V, T1, T2, T3, T4, T5, T6> void matches(V value,
                                                           Class<T1> firstClazz,  Consumer<T1> firstConsumer,
                                                           Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                           Class<T3> thirdClazz,  Consumer<T3> thirdConsumer,
                                                           Class<T4> forthClazz,  Consumer<T4> forthConsumer,
                                                           Class<T5> fifthClazz,  Consumer<T5> fifthConsumer,
                                                           Class<T6> sixthClazz,  Consumer<T6> sixthConsumer,
                                                           Class<Default> defaultClass, Runnable defaultConsumer) {
        if (firstClazz == value.getClass() || Reflection.isPrimitive(firstClazz, value.getClass())) {
            firstConsumer.accept((T1) value);
            return;
        } else if (secondClazz == value.getClass() || Reflection.isPrimitive(secondClazz, value.getClass())) {
            secondConsumer.accept((T2) value);
            return;
        } else if (thirdClazz == value.getClass() || Reflection.isPrimitive(thirdClazz, value.getClass())) {
            thirdConsumer.accept((T3) value);
            return;
        } else if (forthClazz == value.getClass() || Reflection.isPrimitive(forthClazz, value.getClass())) {
            forthConsumer.accept((T4) value);
            return;
        } else if (fifthClazz == value.getClass() || Reflection.isPrimitive(fifthClazz, value.getClass())) {
            fifthConsumer.accept((T5) value);
            return;
        } else if (sixthClazz == value.getClass() || Reflection.isPrimitive(sixthClazz, value.getClass())) {
            sixthConsumer.accept((T6) value);
            return;
        }

        defaultConsumer.run();
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V, T1, T2, T3, T4, T5, T6, R> R matches(V value,
                                                           Class<T1> firstClazz,  Function<T1, R> firstFunction,
                                                           Class<T2> secondClazz, Function<T2, R> secondFunction,
                                                           Class<T3> thirdClazz,  Function<T3, R> thirdFunction,
                                                           Class<T4> forthClazz,  Function<T4, R> forthFunction,
                                                           Class<T5> fifthClazz,  Function<T5, R> fifthFunction,
                                                           Class<T6> sixthClazz,  Function<T6, R> sixthFunction,
                                                           Class<Default> defaultClass, Supplier<R> defaultSupplier) {
        if (firstClazz == value.getClass()) {
            return firstFunction.apply((T1) value);
        } else if (secondClazz == value.getClass()) {
            return secondFunction.apply((T2) value);
        } else if (thirdClazz == value.getClass()) {
            return thirdFunction.apply((T3) value);
        } else if (forthClazz == value.getClass()) {
            return forthFunction.apply((T4) value);
        } else if (fifthClazz == value.getClass()) {
            return fifthFunction.apply((T5) value);
        } else if (sixthClazz == value.getClass()) {
            return sixthFunction.apply((T6) value);
        }

        return defaultSupplier.get();
    }
}
