package org.kl.pattern;

import org.kl.error.PatternException;
import org.kl.meta.Extract;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class StaticPattern {

    public static String of(String name) {
        return name;
    }

    public static <V, C>
    void matches(V value, Class<C> clazz, String name, Runnable branch) throws PatternException {
        if (clazz == value.getClass()) {
            V result = prepareResult(clazz, name);

            if (compareValues(value, result)) {
                return;
            }

            branch.run();
        }
    }

    public static <V, C1, C2, T1>
    void matches(V value,
                 Class<C1> firstClazz,  String firstName,  Runnable firstBranch,
                 Class<C2> secondClazz, String secondName, Consumer<T1> secondBranch) throws PatternException {
        if (firstClazz == value.getClass()) {
            V result = prepareResult(firstClazz, firstName);

            if (!compareValues(value, result)) {
                firstBranch.run();
            }
        }

        if (secondClazz == value.getClass()) {
            Object[] args = verifyExtractMethod(value, secondName, 1);

            secondBranch.accept((T1) args[0]);
        }
    }

    private static <V, C> V prepareResult(Class<C> clazz, String name) throws PatternException {
        V result;

        try {
            Method method = clazz.getDeclaredMethod(name);

            if (Modifier.isStatic(method.getModifiers())) {
                result = (V) method.invoke(null);
            } else {
                throw new PatternException("Can not use not static method " + name);
            }
        } catch (NoSuchMethodException e) {
            throw new PatternException("Can not find method " + name);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new PatternException("Can not access to method " + name);
        }

        return result;
    }

    private static <V> Object[] verifyExtractMethod(V value, String name, int countParameters) throws PatternException {
        Object[] result = null;
        boolean  flag = false;

        final Method method = takeExtractMethod(value, name);

        if (method != null && method.getParameterCount() == countParameters) {
            Object[] parameters = CommonPattern.prepareParameters(method.getParameterTypes());

            try {
                method.invoke(value, parameters);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new PatternException("Can not call extract method: " + e.getMessage());
            }

            result = CommonPattern.resolveParameters(parameters);
            flag = true;
        }

        if (!flag) {
            throw new PatternException("Checked class hasn't extract methods with parameters " + countParameters);
        }

        return result;
    }

    private static <V> Method takeExtractMethod(V value, String name) throws PatternException {
        Method target = null;

        for (final Method method : value.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Extract.class) && method.getName().equals(name)) {
                if (method.getReturnType() != void.class) {
                    throw new PatternException("Extract method must not has return value");
                }

                if (method.getParameterCount() == 0) {
                    throw new PatternException("Extract method must to have one or more parameters");
                }

                if (!Modifier.isPublic(method.getModifiers())) {
                    throw new PatternException("Extract method must to be public");
                }

                target = method;
                break;
            }
        }

        return target;
    }

    private static <T1, T2> boolean compareValues(T1 first, T2 second) {
        return first != null && !first.equals(second);
    }
}
