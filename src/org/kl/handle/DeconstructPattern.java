package org.kl.handle;

import org.kl.attr.Extract;
import org.kl.error.PatternException;
import org.kl.lambda.QuarConsumer;
import org.kl.lambda.TriConsumer;
import org.kl.ref.*;
import org.kl.reflect.Reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class DeconstructPattern {

    public static <V, C, T> void matches(V value,
                                         Class<C> clazz, Consumer<T> consumer) throws PatternException {
        if (clazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            consumer.accept((T) args[0]);
        }
    }

    public static <V, C, T1, T2> void matches(V value,
                                              Class<C> clazz, BiConsumer<T1, T2> consumer) throws PatternException {
        if (clazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            consumer.accept((T1) args[0], (T2) args[1]);
        }
    }

    public static <V, C, T1, T2, T3> void matches(V value,
                                                  Class<C> clazz, TriConsumer<T1, T2, T3> consumer) throws PatternException {
        if (clazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            consumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        }
    }

    public static <V, C, T1, T2, T3, T4> void matches(V value,
                                                     Class<C> clazz, QuarConsumer<T1, T2, T3, T4> consumer) throws PatternException {
        if (clazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 4);

            consumer.accept((T1) args[0], (T2) args[1], (T3) args[2], (T4) args[3]);
        }
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

    public static <V, C1, C2, T1, T2, T3, T4, T5> void matches(V value,
                                           Class<C1> firstClazz,  Consumer<T1> firstConsumer,
                                           Class<C2> secondClazz, QuarConsumer<T2, T3, T4, T5> secondConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            firstConsumer.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 4);

            secondConsumer.accept((T2) args[0], (T3) args[1], (T4) args[2], (T5) args[3]);
        }
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5> void matches(V value,
                                           Class<C1> firstClazz,  QuarConsumer<T1, T2, T3, T4> firstConsumer,
                                           Class<C2> secondClazz, Consumer<T5> secondConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 4);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2], (T4) args[3]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 1);

            secondConsumer.accept((T5) args[0]);
        }
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6, T7, T8> void matches(V value,
                                           Class<C1> firstClazz,  QuarConsumer<T1, T2, T3, T4> firstConsumer,
                                           Class<C2> secondClazz, QuarConsumer<T5, T6, T7, T8> secondConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 4);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2], (T4) args[3]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 4);

            secondConsumer.accept((T5) args[0], (T6) args[1], (T7) args[2], (T8) args[3]);
        }
    }


    public static <V, C1, C2, T1, T2, T3, T4, T5, T6> void matches(V value,
                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstConsumer,
                                           Class<C2> secondClazz, QuarConsumer<T3, T4, T5, T6> secondConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            firstConsumer.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 4);

            secondConsumer.accept((T3) args[0], (T4) args[1], (T5) args[2], (T6) args[3]);
        }
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6> void matches(V value,
                                           Class<C1> firstClazz,  QuarConsumer<T1, T2, T3, T4> firstConsumer,
                                           Class<C2> secondClazz, BiConsumer<T5, T6> secondConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 4);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2], (T4) args[3]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 2);

            secondConsumer.accept((T5) args[0], (T6) args[1]);
        }
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6, T7> void matches(V value,
                                           Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstConsumer,
                                           Class<C2> secondClazz, QuarConsumer<T4, T5, T6, T7> secondConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 4);

            secondConsumer.accept((T4) args[0], (T5) args[1], (T6) args[2], (T7) args[3]);
        }
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6, T7> void matches(V value,
                                           Class<C1> firstClazz,  QuarConsumer<T1, T2, T3, T4> firstConsumer,
                                           Class<C2> secondClazz, TriConsumer<T5, T6, T7> secondConsumer) throws PatternException {
        if (firstClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 4);

            firstConsumer.accept((T1) args[0], (T2) args[1], (T3) args[2], (T4) args[3]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = checkExtractMethods(value, 3);

            secondConsumer.accept((T5) args[0], (T6) args[1], (T7) args[2]);
        }
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

                /* TODO: Permit work with few extract methods */
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
