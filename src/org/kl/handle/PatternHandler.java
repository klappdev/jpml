package org.kl.handle;

import java.util.function.Consumer;

public class PatternHandler {
    public enum Default {}

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
        if (clazz == value.getClass()) {
            consumer.accept((T) value);
        }
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

    public static <V, T1, T2> void matches(V value,
                                       Class<T1> firstClazz,  Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Consumer<T2> secondConsumer) {
        if (firstClazz == value.getClass()) {
            firstConsumer.accept((T1) value);
        } else if (secondClazz == value.getClass()) {
            secondConsumer.accept((T2) value);
        }
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

    public static <V, T1, T2, T3> void matches(V value,
                                       Class<T1> firstClazz,  Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz,  Consumer<T3> thirdConsumer) {
        if (firstClazz == value.getClass()) {
            firstConsumer.accept((T1) value);
        } else if (secondClazz == value.getClass()) {
            secondConsumer.accept((T2) value);
        } else if (thirdClazz == value.getClass()) {
            thirdConsumer.accept((T3) value);
        }
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

    public static <V, T1, T2, T3, T4> void matches(V value,
                                       Class<T1> firstClazz,  Consumer<T1> firstConsumer,
                                       Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                       Class<T3> thirdClazz,  Consumer<T3> thirdConsumer,
                                       Class<T4> forthClazz,  Consumer<T4> forthConsumer) {
        if (firstClazz == value.getClass()) {
            firstConsumer.accept((T1) value);
        } else if (secondClazz == value.getClass()) {
            secondConsumer.accept((T2) value);
        } else if (thirdClazz == value.getClass()) {
            thirdConsumer.accept((T3) value);
        } else if (forthClazz == value.getClass()) {
            forthConsumer.accept((T4) value);
        }
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

    public static <V, T1, T2, T3, T4, T5> void matches(V value,
                                                   Class<T1> firstClazz,  Consumer<T1> firstConsumer,
                                                   Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                   Class<T3> thirdClazz,  Consumer<T3> thirdConsumer,
                                                   Class<T4> forthClazz,  Consumer<T4> forthConsumer,
                                                   Class<T5> fifthClazz,  Consumer<T5> fifthConsumer) {
        if (firstClazz == value.getClass()) {
            firstConsumer.accept((T1) value);
        } else if (secondClazz == value.getClass()) {
            secondConsumer.accept((T2) value);
        } else if (thirdClazz == value.getClass()) {
            thirdConsumer.accept((T3) value);
        } else if (forthClazz == value.getClass()) {
            forthConsumer.accept((T4) value);
        } else if (fifthClazz == value.getClass()) {
            fifthConsumer.accept((T5) value);
        }
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

    public static <V, T1, T2, T3, T4, T5, T6> void matches(V value,
                                                       Class<T1> firstClazz,  Consumer<T1> firstConsumer,
                                                       Class<T2> secondClazz, Consumer<T2> secondConsumer,
                                                       Class<T3> thirdClazz,  Consumer<T3> thirdConsumer,
                                                       Class<T4> forthClazz,  Consumer<T4> forthConsumer,
                                                       Class<T5> fifthClazz,  Consumer<T5> fifthConsumer,
                                                       Class<T6> sixthClazz,  Consumer<T6> sixthConsumer) {
        if (firstClazz == value.getClass()) {
            firstConsumer.accept((T1) value);
        } else if (secondClazz == value.getClass()) {
            secondConsumer.accept((T2) value);
        } else if (thirdClazz == value.getClass()) {
            thirdConsumer.accept((T3) value);
        } else if (forthClazz == value.getClass()) {
            forthConsumer.accept((T4) value);
        } else if (fifthClazz == value.getClass()) {
            fifthConsumer.accept((T5) value);
        } else if (sixthClazz == value.getClass()) {
            sixthConsumer.accept((T6) value);
        }
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
        } else if (sixthClazz == value.getClass()) {
            sixthConsumer.accept((T6) value);
            return;
        }

        defaultConsumer.run();
    }
}
