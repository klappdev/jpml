package org.kl.jpml.pattern;

import org.kl.jpml.lambda.Routine;
import org.kl.jpml.reflect.Reflection;

import java.util.function.Consumer;

public final class ExhaustivePattern {
    private static Object sealedValue;
    private static ExhaustivePattern instance;

    private ExhaustivePattern() {}

    public static <V> ExhaustivePattern matches(V value) {
        sealedValue = value;

        if (instance == null) {
            instance = new ExhaustivePattern();
        }

        return instance;
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
