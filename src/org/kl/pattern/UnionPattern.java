package org.kl.pattern;

import org.kl.lambda.Purchaser;
import org.kl.lambda.Routine;
import org.kl.reflect.Reflection;
import org.kl.util.Union;

import java.util.function.Consumer;
import java.util.function.Function;

public final class UnionPattern {
    private static Union unionValue;
    private static Object sealedValue;
    private static UnionPattern instance;

    private UnionPattern() {}

    public static UnionPattern matches(Union value) {
        unionValue = value;

        if (instance == null) {
            instance = new UnionPattern();
        }

        return instance;
    }

    public static <V> UnionPattern matches(V value) {
        sealedValue = value;

        if (instance == null) {
            instance = new UnionPattern();
        }

        return instance;
    }

    public static <T1, T2>
    void matches(Union value,
                 Class<T1> firstClazz, Purchaser<T1> firstBranch,
                 Class<T2> secondClazz, Purchaser<T2> secondBranch) {
        Class<?> valueClass = value.getActive();

        if (firstClazz == valueClass) {
            firstBranch.obtain(value.get(firstClazz));
        } else if (secondClazz == valueClass) {
            secondBranch.obtain(value.get(secondClazz));
        }
    }

    public <T1, T2>
    void as(Class<T1> firstClazz,  Purchaser<T1> firstBranch,
            Class<T2> secondClazz, Purchaser<T2> secondBranch) {
        matches(unionValue,
                firstClazz, firstBranch,
                secondClazz, secondBranch);
    }

    public static <T1, T2, R>
    R matches(Union value,
              Class<T1> firstClazz,  Function<T1, R> firstBranch,
              Class<T2> secondClazz, Function<T2, R> secondBranch) {
        Class<?> valueClass = value.getActive();

        if (firstClazz == valueClass) {
            return firstBranch.apply(value.get(firstClazz));
        } else if (secondClazz == valueClass) {
            return secondBranch.apply(value.get(secondClazz));
        }

        return null;
    }

    public <T1, T2, R>
    R as(Class<T1> firstClazz,  Function<T1, R> firstBranch,
         Class<T2> secondClazz, Function<T2, R> secondBranch) {
        return matches(unionValue,
                       firstClazz, firstBranch,
                       secondClazz, secondBranch);
    }

    public static <V, T1, T2>
    void matches(V value,
                Class<T1> firstClazz,  Consumer<T1> firstBranch,
                Class<T2> secondClazz, Consumer<T2> secondBranch) {
        Reflection.verifyExhaustiveness(value, new Class<?>[]{ firstClazz, secondClazz });
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass) {
            firstBranch.accept((T1) value);
        } else if (secondClazz == valueClass) {
            secondBranch.accept((T2) value);
        }
    }

    public <T1, T2>
    void as(Class<T1> firstClazz,  Consumer<T1> firstBranch,
            Class<T2> secondClazz, Consumer<T2> secondBranch) {
        matches(sealedValue,
                firstClazz, firstBranch,
                secondClazz, secondBranch);
    }

    public static <V, T1, T2, R>
    R matches(V value,
              Class<T1> firstClazz,  Routine<T1, R> firstBranch,
              Class<T2> secondClazz, Routine<T2, R> secondBranch) {
        Reflection.verifyExhaustiveness(value, new Class<?>[]{ firstClazz, secondClazz });
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass) {
            return firstBranch.hold((T1) value);
        } else if (secondClazz == valueClass) {
            return secondBranch.hold((T2) value);
        }

        return null;
    }

    public <T1, T2, R>
    R as(Class<T1> firstClazz,  Routine<T1, R> firstBranch,
         Class<T2> secondClazz, Routine<T2, R> secondBranch) {
        return matches(sealedValue,
                       firstClazz, firstBranch,
                       secondClazz, secondBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <T1, T2, T3>
    void matches(Union value,
                 Class<T1> firstClazz,  Purchaser<T1> firstBranch,
                 Class<T2> secondClazz, Purchaser<T2> secondBranch,
                 Class<T3> thirdClazz,  Purchaser<T3> thirdBranch) {
        Class<?> valueClass = value.getActive();

        if (firstClazz == valueClass) {
            firstBranch.obtain(value.get(firstClazz));
        } else if (secondClazz == valueClass) {
            secondBranch.obtain(value.get(secondClazz));
        } else if (thirdClazz == valueClass) {
            thirdBranch.obtain(value.get(thirdClazz));
        }
    }

    public <T1, T2, T3>
    void as(Class<T1> firstClazz,  Purchaser<T1> firstBranch,
            Class<T2> secondClazz, Purchaser<T2> secondBranch,
            Class<T3> thirdClazz,  Purchaser<T3> thirdBranch) {
        matches(unionValue,
                firstClazz, firstBranch,
                secondClazz, secondBranch,
                thirdClazz, thirdBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <T1, T2, T3, R>
    R matches(Union value,
              Class<T1> firstClazz,  Function<T1, R> firstBranch,
              Class<T2> secondClazz, Function<T2, R> secondBranch,
              Class<T3> thirdClazz,  Function<T3, R> thirdBranch) {
        Class<?> valueClass = value.getActive();

        if (firstClazz == valueClass) {
            return firstBranch.apply(value.get(firstClazz));
        } else if (secondClazz == valueClass) {
            return secondBranch.apply(value.get(secondClazz));
        } else if (thirdClazz == valueClass) {
            return thirdBranch.apply(value.get(thirdClazz));
        }

        return null;
    }

    public <T1, T2, T3, R>
    R as(Class<T1> firstClazz,  Function<T1, R> firstBranch,
         Class<T2> secondClazz, Function<T2, R> secondBranch,
         Class<T3> thirdClazz,  Function<T3, R> thirdBranch) {
        return matches(unionValue,
                       firstClazz, firstBranch,
                       secondClazz, secondBranch,
                       thirdClazz, thirdBranch);
    }

    public static <V, T1, T2, T3>
    void matches(V value,
                Class<T1> firstClazz, Consumer<T1> firstBranch,
                Class<T2> secondClazz, Consumer<T2> secondBranch,
                Class<T3> thirdClazz, Consumer<T3> thirdBranch) {
        Reflection.verifyExhaustiveness(value, new Class<?>[]{ firstClazz, secondClazz, thirdClazz });
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass) {
            firstBranch.accept((T1) value);
        } else if (secondClazz == valueClass) {
            secondBranch.accept((T2) value);
        } else if (thirdClazz == valueClass) {
            thirdBranch.accept((T3) value);
        }
    }

    public <T1, T2, T3>
    void as(Class<T1> firstClazz,  Consumer<T1> firstBranch,
            Class<T2> secondClazz, Consumer<T2> secondBranch,
            Class<T3> thirdClazz, Consumer<T3> thirdBranch) {
        matches(sealedValue,
                firstClazz, firstBranch,
                secondClazz, secondBranch,
                thirdClazz, thirdBranch);
    }

    public static <V, T1, T2, T3, R>
    R matches(V value,
              Class<T1> firstClazz,  Routine<T1, R> firstBranch,
              Class<T2> secondClazz, Routine<T2, R> secondBranch,
              Class<T3> thirdClazz,  Routine<T3, R> thirdBranch) {
        Reflection.verifyExhaustiveness(value, new Class<?>[]{ firstClazz, secondClazz, thirdClazz });
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass) {
            return firstBranch.hold((T1) value);
        } else if (secondClazz == valueClass) {
            return secondBranch.hold((T2) value);
        } else if (thirdClazz == valueClass) {
            return thirdBranch.hold((T3) value);
        }

        return null;
    }

    public <T1, T2, T3, R>
    R as(Class<T1> firstClazz,  Routine<T1, R> firstBranch,
         Class<T2> secondClazz, Routine<T2, R> secondBranch,
         Class<T3> thirdClazz,  Routine<T3, R> thirdBranch) {
        return matches(sealedValue,
                       firstClazz, firstBranch,
                       secondClazz, secondBranch,
                       thirdClazz, thirdBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <T1, T2, T3, T4>
    void matches(Union value,
                 Class<T1> firstClazz,  Purchaser<T1> firstBranch,
                 Class<T2> secondClazz, Purchaser<T2> secondBranch,
                 Class<T3> thirdClazz,  Purchaser<T3> thirdBranch,
                 Class<T4> fourthClazz, Purchaser<T4> fourthBranch) {
        Class<?> valueClass = value.getActive();

        if (firstClazz == valueClass) {
            firstBranch.obtain(value.get(firstClazz));
        } else if (secondClazz == valueClass) {
            secondBranch.obtain(value.get(secondClazz));
        } else if (thirdClazz == valueClass) {
            thirdBranch.obtain(value.get(thirdClazz));
        } else if (fourthClazz == valueClass) {
            fourthBranch.obtain(value.get(fourthClazz));
        }
    }

    public <T1, T2, T3, T4>
    void as(Class<T1> firstClazz,  Purchaser<T1> firstBranch,
            Class<T2> secondClazz, Purchaser<T2> secondBranch,
            Class<T3> thirdClazz,  Purchaser<T3> thirdBranch,
            Class<T4> fourthClazz, Purchaser<T4> fourthBranch) {
        matches(unionValue,
                firstClazz, firstBranch,
                secondClazz, secondBranch,
                thirdClazz, thirdBranch,
                fourthClazz, fourthBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <T1, T2, T3, T4, R>
    R matches(Union value,
              Class<T1> firstClazz,  Function<T1, R> firstBranch,
              Class<T2> secondClazz, Function<T2, R> secondBranch,
              Class<T3> thirdClazz,  Function<T3, R> thirdBranch,
              Class<T4> fourthClazz, Function<T4, R> fourthBranch) {
        Class<?> valueClass = value.getActive();

        if (firstClazz == valueClass) {
            return firstBranch.apply(value.get(firstClazz));
        } else if (secondClazz == valueClass) {
            return secondBranch.apply(value.get(secondClazz));
        } else if (thirdClazz == valueClass) {
            return thirdBranch.apply(value.get(thirdClazz));
        } else if (fourthClazz == valueClass) {
            return fourthBranch.apply(value.get(fourthClazz));
        }

        return null;
    }

    public <T1, T2, T3, T4, R>
    R as(Class<T1> firstClazz,  Function<T1, R> firstBranch,
         Class<T2> secondClazz, Function<T2, R> secondBranch,
         Class<T3> thirdClazz,  Function<T3, R> thirdBranch,
         Class<T4> fourthClazz, Function<T4, R> fourthBranch) {
        return matches(unionValue,
                       firstClazz, firstBranch,
                       secondClazz, secondBranch,
                       thirdClazz, thirdBranch,
                       fourthClazz, fourthBranch);
    }

    public static <V, T1, T2, T3, T4>
    void matches(V value,
                 Class<T1> firstClazz, Consumer<T1> firstBranch,
                 Class<T2> secondClazz, Consumer<T2> secondBranch,
                 Class<T3> thirdClazz, Consumer<T3> thirdBranch,
                 Class<T4> fourthClazz, Consumer<T4> fourthBranch) {
        Reflection.verifyExhaustiveness(value, new Class<?>[]{
                firstClazz, secondClazz, thirdClazz, fourthClazz
        });
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass) {
            firstBranch.accept((T1) value);
        } else if (secondClazz == valueClass) {
            secondBranch.accept((T2) value);
        } else if (thirdClazz == valueClass) {
            thirdBranch.accept((T3) value);
        } else if (fourthClazz == valueClass) {
            fourthBranch.accept((T4) value);
        }
    }

    public <T1, T2, T3, T4>
    void as(Class<T1> firstClazz,  Consumer<T1> firstBranch,
            Class<T2> secondClazz, Consumer<T2> secondBranch,
            Class<T3> thirdClazz, Consumer<T3> thirdBranch,
            Class<T4> fourthClazz, Consumer<T4> fourthBranch) {
        matches(sealedValue,
                firstClazz, firstBranch,
                secondClazz, secondBranch,
                thirdClazz, thirdBranch,
                fourthClazz, fourthBranch);
    }

    public static <V, T1, T2, T3, T4, R>
    R matches(V value,
              Class<T1> firstClazz,  Routine<T1, R> firstBranch,
              Class<T2> secondClazz, Routine<T2, R> secondBranch,
              Class<T3> thirdClazz,  Routine<T3, R> thirdBranch,
              Class<T4> fourthClazz, Routine<T4, R> fourthBranch) {
        Reflection.verifyExhaustiveness(value, new Class<?>[]{
                firstClazz, secondClazz, thirdClazz, fourthClazz
        });
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass) {
            return firstBranch.hold((T1) value);
        } else if (secondClazz == valueClass) {
            return secondBranch.hold((T2) value);
        } else if (thirdClazz == valueClass) {
            return thirdBranch.hold((T3) value);
        } else if (fourthClazz == valueClass) {
            return fourthBranch.hold((T4) value);
        }

        return null;
    }

    public <T1, T2, T3, T4, R>
    R as(Class<T1> firstClazz,  Routine<T1, R> firstBranch,
         Class<T2> secondClazz, Routine<T2, R> secondBranch,
         Class<T3> thirdClazz,  Routine<T3, R> thirdBranch,
         Class<T4> fourthClazz, Routine<T4, R> fourthBranch) {
        return matches(sealedValue,
                       firstClazz, firstBranch,
                       secondClazz, secondBranch,
                       thirdClazz, thirdBranch,
                       fourthClazz, fourthBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <T1, T2, T3, T4, T5>
    void matches(Union value,
                 Class<T1> firstClazz,  Purchaser<T1> firstBranch,
                 Class<T2> secondClazz, Purchaser<T2> secondBranch,
                 Class<T3> thirdClazz,  Purchaser<T3> thirdBranch,
                 Class<T4> fourthClazz, Purchaser<T4> fourthBranch,
                 Class<T5> fifthClazz,  Purchaser<T5> fifthBranch) {
        Class<?> valueClass = value.getActive();

        if (firstClazz == valueClass) {
            firstBranch.obtain(value.get(firstClazz));
        } else if (secondClazz == valueClass) {
            secondBranch.obtain(value.get(secondClazz));
        } else if (thirdClazz == valueClass) {
            thirdBranch.obtain(value.get(thirdClazz));
        } else if (fourthClazz == valueClass) {
            fourthBranch.obtain(value.get(fourthClazz));
        } else if (fifthClazz == valueClass) {
            fifthBranch.obtain(value.get(fifthClazz));
        }
    }

    public <T1, T2, T3, T4, T5>
    void as(Class<T1> firstClazz,  Purchaser<T1> firstBranch,
            Class<T2> secondClazz, Purchaser<T2> secondBranch,
            Class<T3> thirdClazz,  Purchaser<T3> thirdBranch,
            Class<T4> fourthClazz, Purchaser<T4> fourthBranch,
            Class<T5> fifthClazz,  Purchaser<T5> fifthBranch) {
        matches(unionValue,
                firstClazz, firstBranch,
                secondClazz, secondBranch,
                thirdClazz, thirdBranch,
                fourthClazz, fourthBranch,
                fifthClazz, fifthBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <T1, T2, T3, T4, T5, R>
    R matches(Union value,
              Class<T1> firstClazz,  Function<T1, R> firstBranch,
              Class<T2> secondClazz, Function<T2, R> secondBranch,
              Class<T3> thirdClazz,  Function<T3, R> thirdBranch,
              Class<T4> fourthClazz, Function<T4, R> fourthBranch,
              Class<T5> fifthClazz,  Function<T5, R> fifthBranch) {
        Class<?> valueClass = value.getActive();

        if (firstClazz == valueClass) {
            return firstBranch.apply(value.get(firstClazz));
        } else if (secondClazz == valueClass) {
            return secondBranch.apply(value.get(secondClazz));
        } else if (thirdClazz == valueClass) {
            return thirdBranch.apply(value.get(thirdClazz));
        } else if (fourthClazz == valueClass) {
            return fourthBranch.apply(value.get(fourthClazz));
        } else if (fifthClazz == valueClass) {
            return fifthBranch.apply(value.get(fifthClazz));
        }

        return null;
    }

    public <T1, T2, T3, T4, T5, R>
    R as(Class<T1> firstClazz,  Function<T1, R> firstBranch,
         Class<T2> secondClazz, Function<T2, R> secondBranch,
         Class<T3> thirdClazz,  Function<T3, R> thirdBranch,
         Class<T4> fourthClazz, Function<T4, R> fourthBranch,
         Class<T5> fifthClazz,  Function<T5, R> fifthBranch) {
        return matches(unionValue,
                       firstClazz, firstBranch,
                       secondClazz, secondBranch,
                       thirdClazz, thirdBranch,
                       fourthClazz, fourthBranch,
                       fifthClazz, fifthBranch);
    }

    public static <V, T1, T2, T3, T4, T5>
    void matches(V value,
                 Class<T1> firstClazz, Consumer<T1> firstBranch,
                 Class<T2> secondClazz, Consumer<T2> secondBranch,
                 Class<T3> thirdClazz, Consumer<T3> thirdBranch,
                 Class<T4> fourthClazz, Consumer<T4> fourthBranch,
                 Class<T5> fifthClazz,  Consumer<T5> fifthBranch) {
        Reflection.verifyExhaustiveness(value, new Class<?>[]{
                firstClazz, secondClazz, thirdClazz,
                fourthClazz, fifthClazz
        });
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass) {
            firstBranch.accept((T1) value);
        } else if (secondClazz == valueClass) {
            secondBranch.accept((T2) value);
        } else if (thirdClazz == valueClass) {
            thirdBranch.accept((T3) value);
        } else if (fourthClazz == valueClass) {
            fourthBranch.accept((T4) value);
        } else if (fifthClazz == valueClass) {
            fifthBranch.accept((T5) value);
        }
    }

    public <T1, T2, T3, T4, T5>
    void as(Class<T1> firstClazz,  Consumer<T1> firstBranch,
            Class<T2> secondClazz, Consumer<T2> secondBranch,
            Class<T3> thirdClazz, Consumer<T3> thirdBranch,
            Class<T4> fourthClazz, Consumer<T4> fourthBranch,
            Class<T5> fifthClazz,  Consumer<T5> fifthBranch) {
        matches(sealedValue,
                firstClazz, firstBranch,
                secondClazz, secondBranch,
                thirdClazz, thirdBranch,
                fourthClazz, fourthBranch,
                fifthClazz, fifthBranch);
    }

    public static <V, T1, T2, T3, T4, T5, R>
    R matches(V value,
              Class<T1> firstClazz,  Routine<T1, R> firstBranch,
              Class<T2> secondClazz, Routine<T2, R> secondBranch,
              Class<T3> thirdClazz,  Routine<T3, R> thirdBranch,
              Class<T4> fourthClazz, Routine<T4, R> fourthBranch,
              Class<T5> fifthClazz,  Routine<T5, R> fifthBranch) {
        Reflection.verifyExhaustiveness(value, new Class<?>[]{
                firstClazz, secondClazz, thirdClazz,
                fourthClazz, fifthClazz
        });
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass) {
            return firstBranch.hold((T1) value);
        } else if (secondClazz == valueClass) {
            return secondBranch.hold((T2) value);
        } else if (thirdClazz == valueClass) {
            return thirdBranch.hold((T3) value);
        } else if (fourthClazz == valueClass) {
            return fourthBranch.hold((T4) value);
        } else if (fifthClazz == valueClass) {
            return fifthBranch.hold((T5) value);
        }

        return null;
    }

    public <T1, T2, T3, T4, T5, R>
    R as(Class<T1> firstClazz,  Routine<T1, R> firstBranch,
         Class<T2> secondClazz, Routine<T2, R> secondBranch,
         Class<T3> thirdClazz,  Routine<T3, R> thirdBranch,
         Class<T4> fourthClazz, Routine<T4, R> fourthBranch,
         Class<T5> fifthClazz,  Routine<T5, R> fifthBranch) {
        return matches(sealedValue,
                       firstClazz, firstBranch,
                       secondClazz, secondBranch,
                       thirdClazz, thirdBranch,
                       fourthClazz, fourthBranch,
                       fifthClazz, fifthBranch);
    }

        @SuppressWarnings("Duplicates")
    public static <T1, T2, T3, T4, T5, T6>
    void matches(Union value,
                 Class<T1> firstClazz,  Purchaser<T1> firstBranch,
                 Class<T2> secondClazz, Purchaser<T2> secondBranch,
                 Class<T3> thirdClazz,  Purchaser<T3> thirdBranch,
                 Class<T4> fourthClazz, Purchaser<T4> fourthBranch,
                 Class<T5> fifthClazz,  Purchaser<T5> fifthBranch,
                 Class<T6> sixthClazz,  Purchaser<T6> sixthBranch) {
        Class<?> valueClass = value.getActive();

        if (firstClazz == valueClass) {
            firstBranch.obtain(value.get(firstClazz));
        } else if (secondClazz == valueClass) {
            secondBranch.obtain(value.get(secondClazz));
        } else if (thirdClazz == valueClass) {
            thirdBranch.obtain(value.get(thirdClazz));
        } else if (fourthClazz == valueClass) {
            fourthBranch.obtain(value.get(fourthClazz));
        } else if (fifthClazz == valueClass) {
            fifthBranch.obtain(value.get(fifthClazz));
        } else if (sixthClazz == valueClass) {
            sixthBranch.obtain(value.get(sixthClazz));
        }
    }

    public <T1, T2, T3, T4, T5, T6>
    void as(Class<T1> firstClazz,  Purchaser<T1> firstBranch,
            Class<T2> secondClazz, Purchaser<T2> secondBranch,
            Class<T3> thirdClazz,  Purchaser<T3> thirdBranch,
            Class<T4> fourthClazz, Purchaser<T4> fourthBranch,
            Class<T5> fifthClazz,  Purchaser<T5> fifthBranch,
            Class<T6> sixthClazz,  Purchaser<T6> sixthBranch) {
        matches(unionValue,
                firstClazz, firstBranch,
                secondClazz, secondBranch,
                thirdClazz, thirdBranch,
                fourthClazz, fourthBranch,
                fifthClazz, fifthBranch,
                sixthClazz, sixthBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <T1, T2, T3, T4, T5, T6, R>
    R matches(Union value,
              Class<T1> firstClazz,  Function<T1, R> firstBranch,
              Class<T2> secondClazz, Function<T2, R> secondBranch,
              Class<T3> thirdClazz,  Function<T3, R> thirdBranch,
              Class<T4> fourthClazz, Function<T4, R> fourthBranch,
              Class<T5> fifthClazz,  Function<T5, R> fifthBranch,
              Class<T6> sixthClazz,  Function<T6, R> sixthBranch) {
        Class<?> valueClass = value.getActive();

        if (firstClazz == valueClass) {
            return firstBranch.apply(value.get(firstClazz));
        } else if (secondClazz == valueClass) {
            return secondBranch.apply(value.get(secondClazz));
        } else if (thirdClazz == valueClass) {
            return thirdBranch.apply(value.get(thirdClazz));
        } else if (fourthClazz == valueClass) {
            return fourthBranch.apply(value.get(fourthClazz));
        } else if (fifthClazz == valueClass) {
            return fifthBranch.apply(value.get(fifthClazz));
        } else if (sixthClazz == valueClass) {
            return sixthBranch.apply(value.get(sixthClazz));
        }

        return null;
    }

    public <T1, T2, T3, T4, T5, T6, R>
    R as(Class<T1> firstClazz,  Function<T1, R> firstBranch,
         Class<T2> secondClazz, Function<T2, R> secondBranch,
         Class<T3> thirdClazz,  Function<T3, R> thirdBranch,
         Class<T4> fourthClazz, Function<T4, R> fourthBranch,
         Class<T5> fifthClazz,  Function<T5, R> fifthBranch,
         Class<T6> sixthClazz,  Function<T6, R> sixthBranch) {
        return matches(unionValue,
                       firstClazz, firstBranch,
                       secondClazz, secondBranch,
                       thirdClazz, thirdBranch,
                       fourthClazz, fourthBranch,
                       fifthClazz, fifthBranch,
                       sixthClazz, sixthBranch);
    }

    public static <V, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<T1> firstClazz, Consumer<T1> firstBranch,
                 Class<T2> secondClazz, Consumer<T2> secondBranch,
                 Class<T3> thirdClazz, Consumer<T3> thirdBranch,
                 Class<T4> fourthClazz, Consumer<T4> fourthBranch,
                 Class<T5> fifthClazz,  Consumer<T5> fifthBranch,
                 Class<T6> sixthClazz,  Consumer<T6> sixthBranch) {
        Reflection.verifyExhaustiveness(value, new Class<?>[]{
                firstClazz, secondClazz, thirdClazz,
                fourthClazz, fifthClazz, sixthClazz
        });
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass) {
            firstBranch.accept((T1) value);
        } else if (secondClazz == valueClass) {
            secondBranch.accept((T2) value);
        } else if (thirdClazz == valueClass) {
            thirdBranch.accept((T3) value);
        } else if (fourthClazz == valueClass) {
            fourthBranch.accept((T4) value);
        } else if (fifthClazz == valueClass) {
            fifthBranch.accept((T5) value);
        } else if (sixthClazz == valueClass) {
            sixthBranch.accept((T6) value);
        }
    }

    public <T1, T2, T3, T4, T5, T6>
    void as(Class<T1> firstClazz,  Consumer<T1> firstBranch,
            Class<T2> secondClazz, Consumer<T2> secondBranch,
            Class<T3> thirdClazz, Consumer<T3> thirdBranch,
            Class<T4> fourthClazz, Consumer<T4> fourthBranch,
            Class<T5> fifthClazz,  Consumer<T5> fifthBranch,
            Class<T6> sixthClazz,  Consumer<T6> sixthBranch) {
        matches(sealedValue,
                firstClazz, firstBranch,
                secondClazz, secondBranch,
                thirdClazz, thirdBranch,
                fourthClazz, fourthBranch,
                fifthClazz, fifthBranch,
                sixthClazz, sixthBranch);
    }

    public static <V, T1, T2, T3, T4, T5, T6, R>
    R matches(V value,
              Class<T1> firstClazz,  Routine<T1, R> firstBranch,
              Class<T2> secondClazz, Routine<T2, R> secondBranch,
              Class<T3> thirdClazz,  Routine<T3, R> thirdBranch,
              Class<T4> fourthClazz, Routine<T4, R> fourthBranch,
              Class<T5> fifthClazz,  Routine<T5, R> fifthBranch,
              Class<T6> sixthClazz,  Routine<T6, R> sixthBranch) {
        Reflection.verifyExhaustiveness(value, new Class<?>[]{
                firstClazz, secondClazz, thirdClazz,
                fourthClazz, fifthClazz, sixthClazz
        });
        Class<?> valueClass = value.getClass();

        if (firstClazz == valueClass) {
            return firstBranch.hold((T1) value);
        } else if (secondClazz == valueClass) {
            return secondBranch.hold((T2) value);
        } else if (thirdClazz == valueClass) {
            return thirdBranch.hold((T3) value);
        } else if (fourthClazz == valueClass) {
            return fourthBranch.hold((T4) value);
        } else if (fifthClazz == valueClass) {
            return fifthBranch.hold((T5) value);
        } else if (sixthClazz == valueClass) {
            return sixthBranch.hold((T6) value);
        }

        return null;
    }

    public <T1, T2, T3, T4, T5, T6, R>
    R as(Class<T1> firstClazz,  Routine<T1, R> firstBranch,
         Class<T2> secondClazz, Routine<T2, R> secondBranch,
         Class<T3> thirdClazz,  Routine<T3, R> thirdBranch,
         Class<T4> fourthClazz, Routine<T4, R> fourthBranch,
         Class<T5> fifthClazz,  Routine<T5, R> fifthBranch,
         Class<T6> sixthClazz,  Routine<T6, R> sixthBranch) {
        return matches(sealedValue,
                       firstClazz, firstBranch,
                       secondClazz, secondBranch,
                       thirdClazz, thirdBranch,
                       fourthClazz, fourthBranch,
                       fifthClazz, fifthBranch,
                       sixthClazz, sixthBranch);
    }
}
