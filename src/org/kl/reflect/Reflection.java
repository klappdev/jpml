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

    public static boolean checkTypes(Class<?> inClass, Class<?> outClazz) {
        return inClass == outClazz || Reflection.isPrimitive(inClass, outClazz);
    }

    public static boolean checkTypes(Class<?> inClass, Class<?> firstOutClazz, Class<?> secondOutClazz) {
        return (inClass == firstOutClazz  || Reflection.isPrimitive(inClass, firstOutClazz)) ||
               (inClass == secondOutClazz || Reflection.isPrimitive(inClass, secondOutClazz));
    }

    public static boolean checkTypes(Class<?> inClass, Class<?> firstOutClazz,
                                     Class<?> secondOutClazz, Class<?> thirdOutClazz) {
        return (inClass == firstOutClazz  || Reflection.isPrimitive(inClass, firstOutClazz))  ||
               (inClass == secondOutClazz || Reflection.isPrimitive(inClass, secondOutClazz)) ||
               (inClass == thirdOutClazz  || Reflection.isPrimitive(inClass, thirdOutClazz));
    }

    public static boolean checkTypes(Class<?> inClass, Class<?> firstOutClazz, Class<?> secondOutClazz,
                                     Class<?> thirdOutClazz, Class<?> fourthOutClazz) {
        return (inClass == firstOutClazz  || Reflection.isPrimitive(inClass, firstOutClazz))  ||
               (inClass == secondOutClazz || Reflection.isPrimitive(inClass, secondOutClazz)) ||
               (inClass == thirdOutClazz  || Reflection.isPrimitive(inClass, thirdOutClazz))  ||
               (inClass == fourthOutClazz || Reflection.isPrimitive(inClass, fourthOutClazz));
    }

    public static boolean checkTypes(Class<?> inClass, Class<?> firstOutClazz, Class<?> secondOutClazz,
                                     Class<?> thirdOutClazz, Class<?> fourthOutClazz, Class<?> fifthOutClazz) {
        return (inClass == firstOutClazz  || Reflection.isPrimitive(inClass, firstOutClazz))  ||
               (inClass == secondOutClazz || Reflection.isPrimitive(inClass, secondOutClazz)) ||
               (inClass == thirdOutClazz  || Reflection.isPrimitive(inClass, thirdOutClazz))  ||
               (inClass == fourthOutClazz || Reflection.isPrimitive(inClass, fourthOutClazz)) ||
               (inClass == fifthOutClazz  || Reflection.isPrimitive(inClass, fifthOutClazz));
    }

    public static boolean checkTypes(Class<?> inClass, Class<?> firstOutClazz, Class<?> secondOutClazz,
                                     Class<?> thirdOutClazz, Class<?> fourthOutClazz,
                                     Class<?> fifthOutClazz, Class<?> sixthOutClazz) {
        return (inClass == firstOutClazz  || Reflection.isPrimitive(inClass, firstOutClazz))  ||
               (inClass == secondOutClazz || Reflection.isPrimitive(inClass, secondOutClazz)) ||
               (inClass == thirdOutClazz  || Reflection.isPrimitive(inClass, thirdOutClazz))  ||
               (inClass == fourthOutClazz || Reflection.isPrimitive(inClass, fourthOutClazz)) ||
               (inClass == fifthOutClazz  || Reflection.isPrimitive(inClass, fifthOutClazz))  ||
               (inClass == sixthOutClazz  || Reflection.isPrimitive(inClass, sixthOutClazz));
    }
}
