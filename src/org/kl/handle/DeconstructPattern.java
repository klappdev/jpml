/*
 * JPML - Java pattern matching library.
 *
 * Deconstruction pattern allow match type and deconstruct
 * object at the parts. Maximum number of branches for match
 * three with three deconstruction params.
 */
package org.kl.handle;

import org.kl.meta.Extract;
import org.kl.error.PatternException;
import org.kl.lambda.QuarConsumer;
import org.kl.lambda.TriConsumer;
import org.kl.type.*;
import org.kl.reflect.Reflection;
import org.kl.state.Default;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class DeconstructPattern {

    public static <V, T> void foreach(Collection<V> data, Consumer<T> consumer) throws PatternException {
        for (V value : data) {
            Object[] args = checkExtractMethods(value, 1);

            consumer.accept((T) args[0]);
        }
    }

    public static <V, C, T> void matches(V value,
                                         Class<C> clazz, Consumer<T> consumer) throws PatternException {
        if (clazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            consumer.accept((T) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C, T> void matches(V value,
                                         Class<C> clazz, Consumer<T> consumer,
                                         Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (clazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            consumer.accept((T) args[0]);
            return;
        }

        defaultConsumer.run();
    }

    @SuppressWarnings("unused")
    public static <V, C, T, R> R matches(V value,
                                         Class<C> clazz, Function<T, R> function,
                                         Class<Default> defaultClass, Supplier<R> defaultSupplier) throws PatternException {
        if (clazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            return function.apply((T) args[0]);
        }

        return defaultSupplier.get();
    }

    public static <V, T1, T2> void foreach(Collection<V> data, BiConsumer<T1, T2> consumer) throws PatternException {
        for (V value : data) {
            Object[] args = checkExtractMethods(value, 2);

            consumer.accept((T1) args[0], (T2) args[1]);
        }
    }

    public static <K, V, T1, T2> void foreach(Map<K, V> data, BiConsumer<T1, T2> consumer) {
        for (Map.Entry<K, V> entry : data.entrySet()) {
            consumer.accept((T1) entry.getKey(), (T2) entry.getValue());
        }
    }

    public static <V, C, T1, T2> void matches(V value,
                                          Class<C> clazz, BiConsumer<T1, T2> consumer) throws PatternException {
        if (clazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            consumer.accept((T1) args[0], (T2) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C, T1, T2> void matches(V value,
                                          Class<C> clazz, BiConsumer<T1, T2> consumer,
                                          Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (clazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            consumer.accept((T1) args[0], (T2) args[1]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, T1, T2, T3> void foreach(Collection<V> data, TriConsumer<T1, T2, T3> consumer) throws PatternException {
        for (V value : data) {
            Object[] args = checkExtractMethods(value, 3);

            consumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        }
    }

    public static <V, C, T1, T2, T3> void matches(V value,
                                          Class<C> clazz, TriConsumer<T1, T2, T3> consumer) throws PatternException {
        if (clazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            consumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C, T1, T2, T3> void matches(V value,
                                          Class<C> clazz, TriConsumer<T1, T2, T3> consumer,
                                          Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (clazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            consumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, T1, T2> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T2> secondConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T2) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, T1, T2> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T2> secondConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T2) args[0]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, T1, T2, T3> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T2, T3> secondConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T2) args[0], (T3) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, T1, T2, T3> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T2, T3> secondConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T2) args[0], (T3) args[1]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, T1, T2, T3> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T3> secondConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T3) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, T1, T2, T3> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T3> secondConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T3) args[0]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, T1, T2, T3, T4> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T3, T4> secondConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T3) args[0], (T4) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, T1, T2, T3, T4> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T3, T4> secondConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T3) args[0], (T4) args[1]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, T1, T2, T3, T4> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T2, T3, T4> secondConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T2) args[0], (T3) args[1], (T4) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, T1, T2, T3, T4> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T2, T3, T4> secondConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T2) args[0], (T3) args[1], (T4) args[2]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, T1, T2, T3, T4> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T4> secondConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T4) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, T1, T2, T3, T4> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T4> secondConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T4) args[0]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T4, T5, T6> secondConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T4) args[0], (T5) args[1], (T6) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, T1, T2, T3, T4, T5, T6> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T4, T5, T6> secondConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T4) args[0], (T5) args[1], (T6) args[2]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T3, T4, T5> secondConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T3) args[0], (T4) args[1], (T5) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, T1, T2, T3, T4, T5> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T3, T4, T5> secondConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T3) args[0], (T4) args[1], (T5) args[2]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T4, T5> secondConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T4) args[0], (T5) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, T1, T2, T3, T4, T5> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T4, T5> secondConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T4) args[0], (T5) args[1]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T2> secondConsumer,
                                           Class<C3> thirdClazz,  Consumer<T3> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T2) args[0]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            thirdConsumer.accept((T3) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T2> secondConsumer,
                                           Class<C3> thirdClazz,  Consumer<T3> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T2) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            thirdConsumer.accept((T3) args[0]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T2> secondConsumer,
                                           Class<C3> thirdClazz,  BiConsumer<T3, T4> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T2) args[0]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            thirdConsumer.accept((T3) args[0], (T4) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T2> secondConsumer,
                                           Class<C3> thirdClazz,  BiConsumer<T3, T4> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T2) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            thirdConsumer.accept((T3) args[0], (T4) args[1]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T2, T3> secondConsumer,
                                           Class<C3> thirdClazz,  BiConsumer<T4, T5> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T2) args[0], (T3) args[1]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            thirdConsumer.accept((T4) args[0], (T5) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T2, T3> secondConsumer,
                                           Class<C3> thirdClazz,  BiConsumer<T4, T5> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T2) args[0], (T3) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            thirdConsumer.accept((T4) args[0], (T5) args[1]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T3, T4> secondConsumer,
                                           Class<C3> thirdClazz,  BiConsumer<T5, T6> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T3) args[0], (T4) args[1]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            thirdConsumer.accept((T5) args[0], (T6) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T3, T4> secondConsumer,
                                           Class<C3> thirdClazz,  BiConsumer<T5, T6> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T3) args[0], (T4) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            thirdConsumer.accept((T5) args[0], (T6) args[1]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T3> secondConsumer,
                                           Class<C3> thirdClazz,  Consumer<T4> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T3) args[0]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            thirdConsumer.accept((T4) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T3> secondConsumer,
                                           Class<C3> thirdClazz,  Consumer<T4> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T3) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            thirdConsumer.accept((T4) args[0]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T3, T4> secondConsumer,
                                           Class<C3> thirdClazz,  Consumer<T5> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T3) args[0], (T4) args[1]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            thirdConsumer.accept((T5) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T3, T4> secondConsumer,
                                           Class<C3> thirdClazz,  Consumer<T5> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T3) args[0], (T4) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            thirdConsumer.accept((T5) args[0]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T3> secondConsumer,
                                           Class<C3> thirdClazz,  BiConsumer<T4, T5> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T3) args[0]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            thirdConsumer.accept((T4) args[0], (T5) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T3> secondConsumer,
                                           Class<C3> thirdClazz,  BiConsumer<T4, T5> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T3) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            thirdConsumer.accept((T4) args[0], (T5) args[1]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T2, T3> secondConsumer,
                                           Class<C3> thirdClazz,  Consumer<T4> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T2) args[0], (T3) args[1]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            thirdConsumer.accept((T4) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T2, T3> secondConsumer,
                                           Class<C3> thirdClazz,  Consumer<T4> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T2) args[0], (T3) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            thirdConsumer.accept((T4) args[0]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T2> secondConsumer,
                                           Class<C3> thirdClazz,  TriConsumer<T3, T4, T5> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T2) args[0]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            thirdConsumer.accept((T3) args[0], (T4) args[1], (T5) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T2> secondConsumer,
                                           Class<C3> thirdClazz,  TriConsumer<T3, T4, T5> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T2) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            thirdConsumer.accept((T3) args[0], (T4) args[1], (T5) args[2]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T2, T3, T4> secondConsumer,
                                           Class<C3> thirdClazz,  TriConsumer<T5, T6, T7> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T2) args[0], (T3) args[1], (T4) args[2]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            thirdConsumer.accept((T5) args[0], (T6) args[1], (T7) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T2, T3, T4> secondConsumer,
                                           Class<C3> thirdClazz,  TriConsumer<T5, T6, T7> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T2) args[0], (T3) args[1], (T4) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            thirdConsumer.accept((T5) args[0], (T6) args[1], (T7) args[2]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7, T8, T9> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T4, T5, T6> secondConsumer,
                                           Class<C3> thirdClazz,  TriConsumer<T7, T8, T9> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T4) args[0], (T5) args[1], (T6) args[2]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            thirdConsumer.accept((T7) args[0], (T8) args[1], (T9) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7, T8, T9> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T4, T5, T6> secondConsumer,
                                           Class<C3> thirdClazz,  TriConsumer<T7, T8, T9> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T4) args[0], (T5) args[1], (T6) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            thirdConsumer.accept((T7) args[0], (T8) args[1], (T9) args[2]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T4> secondConsumer,
                                           Class<C3> thirdClazz,  Consumer<T5> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T4) args[0]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            thirdConsumer.accept((T5) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T4> secondConsumer,
                                           Class<C3> thirdClazz,  Consumer<T5> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T4) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            thirdConsumer.accept((T5) args[0]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T4, T5, T6> secondConsumer,
                                           Class<C3> thirdClazz,  Consumer<T7> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T4) args[0], (T5) args[1], (T6) args[2]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            thirdConsumer.accept((T7) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T4, T5, T6> secondConsumer,
                                           Class<C3> thirdClazz,  Consumer<T7> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T4) args[0], (T5) args[1], (T6) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            thirdConsumer.accept((T7) args[0]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T4> secondConsumer,
                                           Class<C3> thirdClazz,  TriConsumer<T5, T6, T7> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T4) args[0]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            thirdConsumer.accept((T5) args[0], (T6) args[1], (T7) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T4> secondConsumer,
                                           Class<C3> thirdClazz,  TriConsumer<T5, T6, T7> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T4) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            thirdConsumer.accept((T5) args[0], (T6) args[1], (T7) args[2]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T2, T3 ,T4> secondConsumer,
                                           Class<C3> thirdClazz,  Consumer<T5> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T2) args[0], (T3) args[1], (T4) args[2]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            thirdConsumer.accept((T5) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T2, T3 ,T4> secondConsumer,
                                           Class<C3> thirdClazz,  Consumer<T5> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T2) args[0], (T3) args[1], (T4) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            thirdConsumer.accept((T5) args[0]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T3, T4> secondConsumer,
                                           Class<C3> thirdClazz,  TriConsumer<T5, T6, T7> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T3) args[0], (T4) args[1]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            thirdConsumer.accept((T5) args[0], (T6) args[1], (T7) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T3, T4> secondConsumer,
                                           Class<C3> thirdClazz,  TriConsumer<T5, T6, T7> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T3) args[0], (T4) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            thirdConsumer.accept((T5) args[0], (T6) args[1], (T7) args[2]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7, T8> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T3, T4, T5> secondConsumer,
                                           Class<C3> thirdClazz,  TriConsumer<T6, T7, T8> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T3) args[0], (T4) args[1], (T5) args[2]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            thirdConsumer.accept((T6) args[0], (T7) args[1], (T8) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7, T8> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T3, T4, T5> secondConsumer,
                                           Class<C3> thirdClazz,  TriConsumer<T6, T7, T8> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T3) args[0], (T4) args[1], (T5) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            thirdConsumer.accept((T6) args[0], (T7) args[1], (T8) args[2]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T4, T5> secondConsumer,
                                           Class<C3> thirdClazz,  BiConsumer<T6, T7> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T4) args[0], (T5) args[1]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            thirdConsumer.accept((T6) args[0], (T7) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T4, T5> secondConsumer,
                                           Class<C3> thirdClazz,  BiConsumer<T6, T7> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T4) args[0], (T5) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            thirdConsumer.accept((T6) args[0], (T7) args[1]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7, T8> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T4, T5, T6> secondConsumer,
                                           Class<C3> thirdClazz,  BiConsumer<T7, T8> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T4) args[0], (T5) args[1], (T6) args[2]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            thirdConsumer.accept((T7) args[0], (T8) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7, T8> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T4, T5, T6> secondConsumer,
                                           Class<C3> thirdClazz,  BiConsumer<T7, T8> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T4) args[0], (T5) args[1], (T6) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            thirdConsumer.accept((T7) args[0], (T8) args[1]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7, T8> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T4, T5> secondConsumer,
                                           Class<C3> thirdClazz,  TriConsumer<T6, T7, T8> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T4) args[0], (T5) args[1]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            thirdConsumer.accept((T6) args[0], (T7) args[1], (T8) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7, T8> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T4, T5> secondConsumer,
                                           Class<C3> thirdClazz,  TriConsumer<T6, T7, T8> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T4) args[0], (T5) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            thirdConsumer.accept((T6) args[0], (T7) args[1], (T8) args[2]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T3, T4, T5> secondConsumer,
                                           Class<C3> thirdClazz,  BiConsumer<T6, T7> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T3) args[0], (T4) args[1], (T5) args[2]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            thirdConsumer.accept((T6) args[0], (T7) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T3, T4, T5> secondConsumer,
                                           Class<C3> thirdClazz,  BiConsumer<T6, T7> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T3) args[0], (T4) args[1], (T5) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            thirdConsumer.accept((T6) args[0], (T7) args[1]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T2, T3> secondConsumer,
                                           Class<C3> thirdClazz,  TriConsumer<T4, T5, T6> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T2) args[0], (T3) args[1]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            thirdConsumer.accept((T4) args[0], (T5) args[1], (T6) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T2, T3> secondConsumer,
                                           Class<C3> thirdClazz,  TriConsumer<T4, T5, T6> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T2) args[0], (T3) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            thirdConsumer.accept((T4) args[0], (T5) args[1], (T6) args[2]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T4, T5> secondConsumer,
                                           Class<C3> thirdClazz,  Consumer<T6> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T4) args[0], (T5) args[1]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            thirdConsumer.accept((T6) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T4, T5> secondConsumer,
                                           Class<C3> thirdClazz,  Consumer<T6> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T4) args[0], (T5) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            thirdConsumer.accept((T6) args[0]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T3> secondConsumer,
                                           Class<C3> thirdClazz,  TriConsumer<T4, T5, T6> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T3) args[0]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            thirdConsumer.accept((T4) args[0], (T5) args[1], (T6) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T3> secondConsumer,
                                           Class<C3> thirdClazz,  TriConsumer<T4, T5, T6> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T3) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            thirdConsumer.accept((T4) args[0], (T5) args[1], (T6) args[2]);
            return;
        }

        defaultConsumer.run();
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T4> secondConsumer,
                                           Class<C3> thirdClazz,  BiConsumer<T5, T6> thirdConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T4) args[0]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            thirdConsumer.accept((T5) args[0], (T6) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T4> secondConsumer,
                                           Class<C3> thirdClazz,  BiConsumer<T5, T6> thirdConsumer,
                                           Class<Default> defaultClass, Runnable defaultConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T4) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            thirdConsumer.accept((T5) args[0], (T6) args[1]);
            return;
        }

        defaultConsumer.run();
    }

    private static <V> Object[] checkExtractMethods(V value, int countParameters) throws PatternException {
        Object[] result = null;
        boolean  flag = false;

        for (final Method method : takeExtractMethods(value)) {
            if (method.getParameterCount() == countParameters) {
                Object[] parameters = prepareParameters(method.getParameterTypes());

                try {
                    method.invoke(value, parameters);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new PatternException("Can not call extract method: " + e.getMessage());
                }

                result = resolveParameters(parameters);
                flag = true;

                break;
            }
        }

        if (!flag) {
            throw new PatternException("Checked class hasn't extract methods with parameters " + countParameters);
        }

        return result;
    }

    private static <V> List<Method> takeExtractMethods(V value) throws PatternException {
        List<Method> extractMethods = new ArrayList<>();

        for (final Method method : value.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Extract.class)) {
                if (method.getReturnType() != void.class) {
                    throw new PatternException("Extract method must not has return value");
                }

                if (method.getParameterCount() == 0) {
                    throw new PatternException("Extract method must to have one or more parameters");
                }

                if (!Modifier.isPublic(method.getModifiers())) {
                    throw new PatternException("Extract method must to be public");
                }

                extractMethods.add(method);
            }
        }

        if (extractMethods.size() == 0) {
            throw new PatternException("Checked class must to have extract method(s)");
        }

        return extractMethods;
    }

    private static Object[] prepareParameters(Class<?>[] parameterTypes) throws PatternException {
        Object[] parameters = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isArray()) {
                throw new PatternException("Parameter extract method must not be array");
            }

            if (Reflection.isPrimitive(parameterTypes[i])) {
                throw new PatternException("Can not pass primitives or wrappers by reference.\n" +
                                           "Use instead IntRef, FloatRef and etc.");
            }

            try {
                parameters[i] = parameterTypes[i].newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new PatternException("Can not resolveParameters parameters extract method: " + e.getMessage());
            }
        }

        return parameters;
    }

    private static Object[] resolveParameters(Object[] args) {
        Object[] result = new Object[args.length];

        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ByteRef) {
                result[i] = ((ByteRef) args[i]).get();

            } else if (args[i] instanceof ShortRef) {
                result[i] = ((ShortRef) args[i]).get();

            } else  if (args[i] instanceof IntRef) {
                result[i] = ((IntRef) args[i]).get();

            } else if (args[i] instanceof LongRef) {
                result[i] = ((LongRef) args[i]).get();

            } else if (args[i] instanceof FloatRef) {
                result[i] = ((FloatRef) args[i]).get();

            } else if (args[i] instanceof DoubleRef) {
                result[i] = ((DoubleRef) args[i]).get();

            } else if (args[i] instanceof CharRef) {
                result[i] = ((CharRef) args[i]).get();

            } else if (args[i] instanceof BooleanRef) {
                result[i] = ((BooleanRef) args[i]).get();

            } else {
                result[i] = args[i];
            }
        }

        return result;
    }
}
