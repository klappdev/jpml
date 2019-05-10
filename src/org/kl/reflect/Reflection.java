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
    
    public static boolean isPrimitive(Class<?> inClass, Class<?> outClass) {
        boolean flag = false;

        if (inClass.isPrimitive()) {
            if ((inClass == byte.class   && outClass == Byte.class)      ||
                (inClass == short.class  && outClass == Short.class)     ||
                (inClass == int.class    && outClass == Integer.class)   ||
                (inClass == long.class   && outClass == Long.class)      ||
                (inClass == float.class  && outClass == Float.class)     ||
                (inClass == double.class && outClass == Double.class)    ||
                (inClass == char.class   && outClass == Character.class) ||
                (inClass == boolean.class && outClass == Boolean.class)) {
                flag = true;
            }
        }

        return flag;
    }

    public static boolean checkTypes(Class<?> inClass, Class<?> outClass) {
        return inClass == outClass || isPrimitive(inClass, outClass);
    }

    public static boolean checkBiTypes(Class<?> leftClass, Class<?> rightClass,
                                       Class<?> firstLeftClass, Class<?> firstRightClass) {
        return (leftClass  == firstLeftClass  || isPrimitive(leftClass,  firstLeftClass)) &&
               (rightClass == firstRightClass || isPrimitive(rightClass, firstRightClass));
    }

    public static boolean checkTrioTypes(Class<?> leftClass, Class<?> middleClass, Class<?> rightClass,
                                       Class<?> firstLeftClass, Class<?> firstMiddleClass, Class<?> firstRightClass) {
        return (leftClass   == firstLeftClass   || isPrimitive(leftClass,  firstLeftClass))    &&
               (middleClass == firstMiddleClass || isPrimitive(middleClass, firstMiddleClass)) &&
               (rightClass  == firstRightClass  || isPrimitive(rightClass, firstRightClass));
    }

    public static
    boolean checkQuarTypes(Class<?> leftClass, Class<?> bottomClass, Class<?> rightClass, Class<?> topClass,
            Class<?> firstLeftClass, Class<?> firstBottomClass, Class<?> firstRightClass, Class<?> firstTopClass) {
        return (leftClass   == firstLeftClass   || isPrimitive(leftClass,  firstLeftClass))    &&
               (bottomClass == firstBottomClass || isPrimitive(bottomClass, firstBottomClass)) &&
               (rightClass  == firstRightClass  || isPrimitive(rightClass, firstRightClass))   &&
               (topClass    == firstTopClass    || isPrimitive(topClass, firstTopClass));
    }

    public static boolean checkTypes(Class<?> inClass, Class<?> firstOutClass, Class<?> secondOutClass) {
        return (inClass == firstOutClass  || isPrimitive(inClass, firstOutClass)) &&
               (inClass == secondOutClass || isPrimitive(inClass, secondOutClass));
    }

    public static boolean checkBiTypes(Class<?> leftClass, Class<?> rightClass,
                                       Class<?> firstLeftClass,  Class<?> firstRightClass,
                                       Class<?> secondLeftClass, Class<?> secondRightClass) {
        return (leftClass  == firstLeftClass   || isPrimitive(leftClass,  firstLeftClass))  &&
               (leftClass  == secondLeftClass  || isPrimitive(leftClass,  secondLeftClass)) &&
               (rightClass == firstRightClass  || isPrimitive(rightClass, firstRightClass)) &&
               (rightClass == secondRightClass || isPrimitive(rightClass, secondRightClass));
    }

    public static boolean checkTrioTypes(Class<?> leftClass, Class<?> middleClass, Class<?> rightClass,
                                         Class<?> firstLeftClass,  Class<?> firstMiddleClass,  Class<?> firstRightClass,
                                         Class<?> secondLeftClass, Class<?> secondMiddleClass, Class<?> secondRightClass) {
        return (leftClass   == firstLeftClass    || isPrimitive(leftClass,  firstLeftClass))     &&
               (leftClass   == secondLeftClass   || isPrimitive(leftClass,  secondLeftClass))    &&
               (middleClass == firstMiddleClass  || isPrimitive(middleClass, firstMiddleClass))  &&
               (middleClass == secondMiddleClass || isPrimitive(middleClass, secondMiddleClass)) &&
               (rightClass  == firstRightClass   || isPrimitive(rightClass, firstRightClass))    &&
               (rightClass  == secondRightClass  || isPrimitive(rightClass, secondRightClass)) ;
    }

    public static 
    boolean checkQuarTypes(Class<?> leftClass, Class<?> bottomClass, Class<?> rightClass, Class<?> topClass,
            Class<?> firstLeftClass,  Class<?> firstBottomClass,  Class<?> firstRightClass,  Class<?> firstTopClass,
            Class<?> secondLeftClass, Class<?> secondBottomClass, Class<?> secondRightClass, Class<?> secondTopClass) {
        return (leftClass   == firstLeftClass    || isPrimitive(leftClass,  firstLeftClass))     &&
               (leftClass   == secondLeftClass   || isPrimitive(leftClass,  secondLeftClass))    &&
               (bottomClass == firstBottomClass  || isPrimitive(bottomClass, firstBottomClass))  &&
               (bottomClass == secondBottomClass || isPrimitive(bottomClass, secondBottomClass)) &&
               (rightClass  == firstRightClass   || isPrimitive(rightClass, firstRightClass))    &&
               (rightClass  == secondRightClass  || isPrimitive(rightClass, secondRightClass))   &&
               (topClass    == firstTopClass     || isPrimitive(topClass, firstTopClass))        &&
               (topClass    == secondTopClass    || isPrimitive(topClass, secondTopClass));
    }

    public static boolean checkTypes(Class<?> inClass, Class<?> firstOutClass,
                                     Class<?> secondOutClass, Class<?> thirdOutClass) {
        return (inClass == firstOutClass  || isPrimitive(inClass, firstOutClass))  &&
               (inClass == secondOutClass || isPrimitive(inClass, secondOutClass)) &&
               (inClass == thirdOutClass  || isPrimitive(inClass, thirdOutClass));
    }

    public static boolean checkBiTypes(Class<?> leftClass, Class<?> rightClass,
                                       Class<?> firstLeftClass,  Class<?> firstRightClass,
                                       Class<?> secondLeftClass, Class<?> secondRightClass,
                                       Class<?> thirdLeftClass,  Class<?> thirdRightClass) {
        return (leftClass  == firstLeftClass   || isPrimitive(leftClass,  firstLeftClass))  &&
               (leftClass  == secondLeftClass  || isPrimitive(leftClass,  secondLeftClass)) &&
               (leftClass  == thirdLeftClass   || isPrimitive(leftClass,  thirdLeftClass))  &&
               (rightClass == firstRightClass  || isPrimitive(rightClass, firstRightClass)) &&
               (rightClass == secondRightClass || isPrimitive(rightClass, secondRightClass))&&
               (rightClass == thirdRightClass  || isPrimitive(rightClass, thirdRightClass));
    }

    public static boolean checkTrioTypes(Class<?> leftClass, Class<?> middleClass, Class<?> rightClass,
                                         Class<?> firstLeftClass,  Class<?> firstMiddleClass,  Class<?> firstRightClass,
                                         Class<?> secondLeftClass, Class<?> secondMiddleClass, Class<?> secondRightClass,
                                         Class<?> thirdLeftClass,  Class<?> thirdMiddleClass,  Class<?> thirdRightClass) {
        return (leftClass   == firstLeftClass    || isPrimitive(leftClass,  firstLeftClass))     &&
               (leftClass   == secondLeftClass   || isPrimitive(leftClass,  secondLeftClass))    &&
               (leftClass   == thirdLeftClass    || isPrimitive(leftClass,  thirdLeftClass))     &&
               (middleClass == firstMiddleClass  || isPrimitive(middleClass, firstMiddleClass))  &&
               (middleClass == secondMiddleClass || isPrimitive(middleClass, secondMiddleClass)) &&
               (middleClass == thirdMiddleClass  || isPrimitive(middleClass, thirdMiddleClass))  &&
               (rightClass  == firstRightClass   || isPrimitive(rightClass, firstRightClass))    &&
               (rightClass  == secondRightClass  || isPrimitive(rightClass, secondRightClass))   &&
               (rightClass  == thirdRightClass   || isPrimitive(rightClass, thirdRightClass));
    }

    public static
    boolean checkQuarTypes(Class<?> leftClass, Class<?> bottomClass, Class<?> rightClass, Class<?> topClass,
            Class<?> firstLeftClass,  Class<?> firstBottomClass,  Class<?> firstRightClass,  Class<?> firstTopClass,
            Class<?> secondLeftClass, Class<?> secondBottomClass, Class<?> secondRightClass, Class<?> secondTopClass,
            Class<?> thirdLeftClass,  Class<?> thirdBottomClass,  Class<?> thirdRightClass,  Class<?> thirdTopClass) {
        return (leftClass   == firstLeftClass    || isPrimitive(leftClass,  firstLeftClass))     &&
               (leftClass   == secondLeftClass   || isPrimitive(leftClass,  secondLeftClass))    &&
               (leftClass   == thirdLeftClass    || isPrimitive(leftClass,  thirdLeftClass))     &&
               (bottomClass == firstBottomClass  || isPrimitive(bottomClass, firstBottomClass))  &&
               (bottomClass == secondBottomClass || isPrimitive(bottomClass, secondBottomClass)) &&
               (bottomClass == thirdBottomClass  || isPrimitive(bottomClass, thirdBottomClass))  &&
               (rightClass  == firstRightClass   || isPrimitive(rightClass, firstRightClass))    &&
               (rightClass  == secondRightClass  || isPrimitive(rightClass, secondRightClass))   &&
               (rightClass  == thirdRightClass   || isPrimitive(rightClass, thirdRightClass))    &&
               (topClass    == firstTopClass     || isPrimitive(topClass, firstTopClass))        &&
               (topClass    == secondTopClass    || isPrimitive(topClass, secondTopClass))       &&
               (topClass    == thirdTopClass     || isPrimitive(topClass, thirdTopClass));
    }

    public static boolean checkTypes(Class<?> inClass, Class<?> firstOutClass, Class<?> secondOutClass,
                                     Class<?> thirdOutClass, Class<?> fourthOutClass) {
        return (inClass == firstOutClass  || isPrimitive(inClass, firstOutClass))  &&
               (inClass == secondOutClass || isPrimitive(inClass, secondOutClass)) &&
               (inClass == thirdOutClass  || isPrimitive(inClass, thirdOutClass))  &&
               (inClass == fourthOutClass || isPrimitive(inClass, fourthOutClass));
    }

    public static boolean checkBiTypes(Class<?> leftClass, Class<?> rightClass,
                                       Class<?> firstLeftClass,  Class<?> firstRightClass,
                                       Class<?> secondLeftClass, Class<?> secondRightClass,
                                       Class<?> thirdLeftClass,  Class<?> thirdRightClass,
                                       Class<?> fourthLeftClass, Class<?> fourthRightClass) {
        return (leftClass  == firstLeftClass   || isPrimitive(leftClass,  firstLeftClass))  &&
               (leftClass  == secondLeftClass  || isPrimitive(leftClass,  secondLeftClass)) &&
               (leftClass  == thirdLeftClass   || isPrimitive(leftClass,  thirdLeftClass))  &&
               (leftClass  == fourthLeftClass  || isPrimitive(leftClass,  fourthLeftClass)) &&
               (rightClass == firstRightClass  || isPrimitive(rightClass, firstRightClass)) &&
               (rightClass == secondRightClass || isPrimitive(rightClass, secondRightClass))&&
               (rightClass == thirdRightClass  || isPrimitive(rightClass, thirdRightClass)) &&
               (rightClass == fourthRightClass || isPrimitive(rightClass, fourthRightClass));
    }

    public static boolean checkTrioTypes(Class<?> leftClass, Class<?> middleClass, Class<?> rightClass,
                                         Class<?> firstLeftClass,  Class<?> firstMiddleClass,  Class<?> firstRightClass,
                                         Class<?> secondLeftClass, Class<?> secondMiddleClass, Class<?> secondRightClass,
                                         Class<?> thirdLeftClass,  Class<?> thirdMiddleClass,  Class<?> thirdRightClass,
                                         Class<?> fourthLeftClass, Class<?> fourthMiddleClass, Class<?> fourthRightClass) {
        return (leftClass   == firstLeftClass    || isPrimitive(leftClass,  firstLeftClass))     &&
               (leftClass   == secondLeftClass   || isPrimitive(leftClass,  secondLeftClass))    &&
               (leftClass   == thirdLeftClass    || isPrimitive(leftClass,  thirdLeftClass))     &&
               (leftClass   == fourthLeftClass   || isPrimitive(leftClass,  fourthLeftClass))    &&
               (middleClass == firstMiddleClass  || isPrimitive(middleClass, firstMiddleClass))  &&
               (middleClass == secondMiddleClass || isPrimitive(middleClass, secondMiddleClass)) &&
               (middleClass == thirdMiddleClass  || isPrimitive(middleClass, thirdMiddleClass))  &&
               (middleClass == fourthMiddleClass || isPrimitive(middleClass, fourthMiddleClass)) &&
               (rightClass  == firstRightClass   || isPrimitive(rightClass, firstRightClass))    &&
               (rightClass  == secondRightClass  || isPrimitive(rightClass, secondRightClass))   &&
               (rightClass  == thirdRightClass   || isPrimitive(rightClass, thirdRightClass))    &&
               (rightClass  == fourthRightClass  || isPrimitive(rightClass, fourthRightClass));
    }

    public static
    boolean checkQuarTypes(Class<?> leftClass, Class<?> bottomClass, Class<?> rightClass, Class<?> topClass,
            Class<?> firstLeftClass,  Class<?> firstBottomClass,  Class<?> firstRightClass,  Class<?> firstTopClass,
            Class<?> secondLeftClass, Class<?> secondBottomClass, Class<?> secondRightClass, Class<?> secondTopClass,
            Class<?> thirdLeftClass,  Class<?> thirdBottomClass,  Class<?> thirdRightClass,  Class<?> thirdTopClass,
            Class<?> fourthLeftClass, Class<?> fourthBottomClass, Class<?> fourthRightClass, Class<?> fourthTopClass) {
        return (leftClass   == firstLeftClass    || isPrimitive(leftClass,  firstLeftClass))     &&
               (leftClass   == secondLeftClass   || isPrimitive(leftClass,  secondLeftClass))    &&
               (leftClass   == thirdLeftClass    || isPrimitive(leftClass,  thirdLeftClass))     &&
               (leftClass   == fourthLeftClass   || isPrimitive(leftClass,  fourthLeftClass))    &&
               (bottomClass == firstBottomClass  || isPrimitive(bottomClass, firstBottomClass))  &&
               (bottomClass == secondBottomClass || isPrimitive(bottomClass, secondBottomClass)) &&
               (bottomClass == thirdBottomClass  || isPrimitive(bottomClass, thirdBottomClass))  &&
               (bottomClass == fourthBottomClass || isPrimitive(bottomClass, fourthBottomClass)) &&
               (rightClass  == firstRightClass   || isPrimitive(rightClass, firstRightClass))    &&
               (rightClass  == secondRightClass  || isPrimitive(rightClass, secondRightClass))   &&
               (rightClass  == thirdRightClass   || isPrimitive(rightClass, thirdRightClass))    &&
               (rightClass  == fourthRightClass  || isPrimitive(rightClass, fourthRightClass))   &&
               (topClass    == firstTopClass     || isPrimitive(topClass, firstTopClass))        &&
               (topClass    == secondTopClass    || isPrimitive(topClass, secondTopClass))       &&
               (topClass    == thirdTopClass     || isPrimitive(topClass, thirdTopClass))        &&
               (topClass    == fourthTopClass    || isPrimitive(topClass, fourthTopClass));
    }

    public static boolean checkTypes(Class<?> inClass, Class<?> firstOutClass, Class<?> secondOutClass,
                                     Class<?> thirdOutClass, Class<?> fourthOutClass, Class<?> fifthOutClass) {
        return (inClass == firstOutClass  || isPrimitive(inClass, firstOutClass))  &&
               (inClass == secondOutClass || isPrimitive(inClass, secondOutClass)) &&
               (inClass == thirdOutClass  || isPrimitive(inClass, thirdOutClass))  &&
               (inClass == fourthOutClass || isPrimitive(inClass, fourthOutClass)) &&
               (inClass == fifthOutClass  || isPrimitive(inClass, fifthOutClass));
    }

    public static boolean checkBiTypes(Class<?> leftClass, Class<?> rightClass,
                                       Class<?> firstLeftClass,  Class<?> firstRightClass,
                                       Class<?> secondLeftClass, Class<?> secondRightClass,
                                       Class<?> thirdLeftClass,  Class<?> thirdRightClass,
                                       Class<?> fourthLeftClass, Class<?> fourthRightClass,
                                       Class<?> fifthLeftClass,  Class<?> fifthRightClass) {
        return (leftClass  == firstLeftClass   || isPrimitive(leftClass,  firstLeftClass))   &&
               (leftClass  == secondLeftClass  || isPrimitive(leftClass,  secondLeftClass))  &&
               (leftClass  == thirdLeftClass   || isPrimitive(leftClass,  thirdLeftClass))   &&
               (leftClass  == fourthLeftClass  || isPrimitive(leftClass,  fourthLeftClass))  &&
               (leftClass  == fifthLeftClass   || isPrimitive(leftClass,  fifthLeftClass))   &&
               (rightClass == firstRightClass  || isPrimitive(rightClass, firstRightClass))  &&
               (rightClass == secondRightClass || isPrimitive(rightClass, secondRightClass)) &&
               (rightClass == thirdRightClass  || isPrimitive(rightClass, thirdRightClass))  &&
               (rightClass == fourthRightClass || isPrimitive(rightClass, fourthRightClass)) &&
               (rightClass == fifthRightClass  || isPrimitive(rightClass, fifthRightClass));
    }

    public static boolean checkTrioTypes(Class<?> leftClass, Class<?> middleClass, Class<?> rightClass,
                                         Class<?> firstLeftClass,  Class<?> firstMiddleClass,  Class<?> firstRightClass,
                                         Class<?> secondLeftClass, Class<?> secondMiddleClass, Class<?> secondRightClass,
                                         Class<?> thirdLeftClass,  Class<?> thirdMiddleClass,  Class<?> thirdRightClass,
                                         Class<?> fourthLeftClass, Class<?> fourthMiddleClass, Class<?> fourthRightClass,
                                         Class<?> fifthLeftClass,  Class<?> fifthMiddleClass,  Class<?> fifthRightClass) {
        return (leftClass   == firstLeftClass    || isPrimitive(leftClass,  firstLeftClass))     &&
               (leftClass   == secondLeftClass   || isPrimitive(leftClass,  secondLeftClass))    &&
               (leftClass   == thirdLeftClass    || isPrimitive(leftClass,  thirdLeftClass))     &&
               (leftClass   == fourthLeftClass   || isPrimitive(leftClass,  fourthLeftClass))    &&
               (leftClass   == fifthLeftClass    || isPrimitive(leftClass,  fifthMiddleClass))   &&
               (middleClass == firstMiddleClass  || isPrimitive(middleClass, firstMiddleClass))  &&
               (middleClass == secondMiddleClass || isPrimitive(middleClass, secondMiddleClass)) &&
               (middleClass == thirdMiddleClass  || isPrimitive(middleClass, thirdMiddleClass))  &&
               (middleClass == fourthMiddleClass || isPrimitive(middleClass, fourthMiddleClass)) &&
               (middleClass == fifthMiddleClass  || isPrimitive(middleClass, fifthMiddleClass))  &&
               (rightClass  == firstRightClass   || isPrimitive(rightClass, firstRightClass))    &&
               (rightClass  == secondRightClass  || isPrimitive(rightClass, secondRightClass))   &&
               (rightClass  == thirdRightClass   || isPrimitive(rightClass, thirdRightClass))    &&
               (rightClass  == fourthRightClass  || isPrimitive(rightClass, fourthRightClass))   &&
               (rightClass  == fifthRightClass   || isPrimitive(rightClass, fifthRightClass));
    }

    public static
    boolean checkQuarTypes(Class<?> leftClass, Class<?> bottomClass, Class<?> rightClass, Class<?> topClass,
            Class<?> firstLeftClass,  Class<?> firstBottomClass,  Class<?> firstRightClass,  Class<?> firstTopClass,
            Class<?> secondLeftClass, Class<?> secondBottomClass, Class<?> secondRightClass, Class<?> secondTopClass,
            Class<?> thirdLeftClass,  Class<?> thirdBottomClass,  Class<?> thirdRightClass,  Class<?> thirdTopClass,
            Class<?> fourthLeftClass, Class<?> fourthBottomClass, Class<?> fourthRightClass, Class<?> fourthTopClass,
            Class<?> fifthLeftClass,  Class<?> fifthBottomClass,  Class<?> fifthRightClass,  Class<?> fifthTopClass) {
        return checkQuarTypes(leftClass, bottomClass, rightClass, topClass,
                              firstLeftClass,  firstBottomClass,  firstRightClass,  firstTopClass,
                              secondLeftClass, secondBottomClass, secondRightClass, secondTopClass,
                              thirdLeftClass,  thirdBottomClass,  thirdRightClass,  thirdTopClass,
                              fourthLeftClass, fourthBottomClass, fourthRightClass, fourthTopClass) &&
               (leftClass   == fifthLeftClass    || isPrimitive(leftClass,  fifthLeftClass))     &&
               (bottomClass == fifthBottomClass  || isPrimitive(bottomClass, fifthBottomClass))  &&
               (rightClass  == fifthRightClass   || isPrimitive(rightClass, fifthRightClass))    &&
               (topClass    == fifthTopClass     || isPrimitive(topClass, fifthTopClass));
    }

    public static boolean checkTypes(Class<?> inClass, Class<?> firstOutClass, Class<?> secondOutClass,
                                     Class<?> thirdOutClass, Class<?> fourthOutClass,
                                     Class<?> fifthOutClass, Class<?> sixthOutClass) {
        return (inClass == firstOutClass  || isPrimitive(inClass, firstOutClass))  &&
               (inClass == secondOutClass || isPrimitive(inClass, secondOutClass)) &&
               (inClass == thirdOutClass  || isPrimitive(inClass, thirdOutClass))  &&
               (inClass == fourthOutClass || isPrimitive(inClass, fourthOutClass)) &&
               (inClass == fifthOutClass  || isPrimitive(inClass, fifthOutClass))  &&
               (inClass == sixthOutClass  || isPrimitive(inClass, sixthOutClass));
    }

    public static boolean checkBiTypes(Class<?> leftClass, Class<?> rightClass,
                                       Class<?> firstLeftClass,  Class<?> firstRightClass,
                                       Class<?> secondLeftClass, Class<?> secondRightClass,
                                       Class<?> thirdLeftClass,  Class<?> thirdRightClass,
                                       Class<?> fourthLeftClass, Class<?> fourthRightClass,
                                       Class<?> fifthLeftClass,  Class<?> fifthRightClass,
                                       Class<?> sixthLeftClass,  Class<?> sixthRightClass) {
        return (leftClass  == firstLeftClass   || isPrimitive(leftClass,  firstLeftClass))   &&
               (leftClass  == secondLeftClass  || isPrimitive(leftClass,  secondLeftClass))  &&
               (leftClass  == thirdLeftClass   || isPrimitive(leftClass,  thirdLeftClass))   &&
               (leftClass  == fourthLeftClass  || isPrimitive(leftClass,  fourthLeftClass))  &&
               (leftClass  == fifthLeftClass   || isPrimitive(leftClass,  fifthLeftClass))   &&
               (leftClass  == sixthLeftClass   || isPrimitive(leftClass,  sixthLeftClass))   &&
               (rightClass == firstRightClass  || isPrimitive(rightClass, firstRightClass))  &&
               (rightClass == secondRightClass || isPrimitive(rightClass, secondRightClass)) &&
               (rightClass == thirdRightClass  || isPrimitive(rightClass, thirdRightClass))  &&
               (rightClass == fourthRightClass || isPrimitive(rightClass, fourthRightClass)) &&
               (rightClass == fifthRightClass  || isPrimitive(rightClass, fifthRightClass))  &&
               (rightClass == sixthRightClass  || isPrimitive(rightClass, sixthRightClass));
    }

    public static boolean checkTrioTypes(Class<?> leftClass, Class<?> middleClass, Class<?> rightClass,
                                         Class<?> firstLeftClass,  Class<?> firstMiddleClass,  Class<?> firstRightClass,
                                         Class<?> secondLeftClass, Class<?> secondMiddleClass, Class<?> secondRightClass,
                                         Class<?> thirdLeftClass,  Class<?> thirdMiddleClass,  Class<?> thirdRightClass,
                                         Class<?> fourthLeftClass, Class<?> fourthMiddleClass, Class<?> fourthRightClass,
                                         Class<?> fifthLeftClass,  Class<?> fifthMiddleClass,  Class<?> fifthRightClass,
                                         Class<?> sixthLeftClass,  Class<?> sixthMiddleClass,  Class<?> sixthRightClass) {
        return (leftClass   == firstLeftClass    || isPrimitive(leftClass,  firstLeftClass))     &&
               (leftClass   == secondLeftClass   || isPrimitive(leftClass,  secondLeftClass))    &&
               (leftClass   == thirdLeftClass    || isPrimitive(leftClass,  thirdLeftClass))     &&
               (leftClass   == fourthLeftClass   || isPrimitive(leftClass,  fourthLeftClass))    &&
               (leftClass   == fifthLeftClass    || isPrimitive(leftClass,  fifthMiddleClass))   &&
               (leftClass   == sixthLeftClass    || isPrimitive(leftClass,  sixthLeftClass))     &&
               (middleClass == firstMiddleClass  || isPrimitive(middleClass, firstMiddleClass))  &&
               (middleClass == secondMiddleClass || isPrimitive(middleClass, secondMiddleClass)) &&
               (middleClass == thirdMiddleClass  || isPrimitive(middleClass, thirdMiddleClass))  &&
               (middleClass == fourthMiddleClass || isPrimitive(middleClass, fourthMiddleClass)) &&
               (middleClass == fifthMiddleClass  || isPrimitive(middleClass, fifthMiddleClass))  &&
               (middleClass == sixthMiddleClass  || isPrimitive(middleClass, sixthMiddleClass))  &&
               (rightClass  == firstRightClass   || isPrimitive(rightClass, firstRightClass))    &&
               (rightClass  == secondRightClass  || isPrimitive(rightClass, secondRightClass))   &&
               (rightClass  == thirdRightClass   || isPrimitive(rightClass, thirdRightClass))    &&
               (rightClass  == fourthRightClass  || isPrimitive(rightClass, fourthRightClass))   &&
               (rightClass  == fifthRightClass   || isPrimitive(rightClass, fifthRightClass))    &&
               (rightClass  == sixthRightClass   || isPrimitive(rightClass, sixthRightClass));
    }

    public static
    boolean checkQuarTypes(Class<?> leftClass, Class<?> bottomClass, Class<?> rightClass, Class<?> topClass,
            Class<?> firstLeftClass,  Class<?> firstBottomClass,  Class<?> firstRightClass,  Class<?> firstTopClass,
            Class<?> secondLeftClass, Class<?> secondBottomClass, Class<?> secondRightClass, Class<?> secondTopClass,
            Class<?> thirdLeftClass,  Class<?> thirdBottomClass,  Class<?> thirdRightClass,  Class<?> thirdTopClass,
            Class<?> fourthLeftClass, Class<?> fourthBottomClass, Class<?> fourthRightClass, Class<?> fourthTopClass,
            Class<?> fifthLeftClass,  Class<?> fifthBottomClass,  Class<?> fifthRightClass,  Class<?> fifthTopClass,
            Class<?> sixthLeftClass,  Class<?> sixthBottomClass,  Class<?> sixthRightClass,  Class<?> sixthTopClass) {
        return checkQuarTypes(leftClass, bottomClass, rightClass, topClass,
                              firstLeftClass,  firstBottomClass,  firstRightClass,  firstTopClass,
                              secondLeftClass, secondBottomClass, secondRightClass, secondTopClass,
                              thirdLeftClass,  thirdBottomClass,  thirdRightClass,  thirdTopClass,
                              fourthLeftClass, fourthBottomClass, fourthRightClass, fourthTopClass,
                              fifthLeftClass,  fifthBottomClass,  fifthRightClass,  fifthTopClass) &&
               (leftClass   == sixthLeftClass    || isPrimitive(leftClass,  sixthLeftClass))     &&
               (bottomClass == sixthBottomClass  || isPrimitive(bottomClass, sixthBottomClass))  &&
               (rightClass  == sixthRightClass   || isPrimitive(rightClass, sixthRightClass))    &&
               (topClass    == sixthTopClass     || isPrimitive(topClass, sixthTopClass));
    }
}
