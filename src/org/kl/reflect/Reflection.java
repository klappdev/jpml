package org.kl.reflect;

public class Reflection {

    public static boolean isPrimitive(Class<?> clazz) {
        boolean flag = false;

        if ((clazz == byte.class   || clazz == Byte.class)      ||
            (clazz == short.class  || clazz == Short.class)     ||
            (clazz == int.class    || clazz == Integer.class)   ||
            (clazz == long.class   || clazz == Long.class)      ||
            (clazz == float.class  || clazz == Float.class)     ||
            (clazz == double.class || clazz == Double.class)    ||
            (clazz == char.class   || clazz == Character.class) ||
            (clazz == boolean.class || clazz == Boolean.class)) {
            flag = true;
        }

        return flag;
    }
    
    public static boolean isPrimitive(Class<?> inClass, Class<?> outClazz) {
        boolean flag = false;

        if (inClass.isPrimitive()) {
            if ((inClass == byte.class   && outClazz == Byte.class)      ||
                (inClass == short.class  && outClazz == Short.class)     ||
                (inClass == int.class    && outClazz == Integer.class)   ||
                (inClass == long.class   && outClazz == Long.class)      ||
                (inClass == float.class  && outClazz == Float.class)     ||
                (inClass == double.class && outClazz == Double.class)    ||
                (inClass == char.class   && outClazz == Character.class) ||
                (inClass == boolean.class && outClazz == Boolean.class)) {
                flag = true;
            }
        }

        return flag;
    }
}
