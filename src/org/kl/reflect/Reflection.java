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
        return checkTypes(inClass, firstOutClass) &&
               (inClass == secondOutClass || isPrimitive(inClass, secondOutClass));
    }

    public static boolean checkBiTypes(Class<?> leftClass, Class<?> rightClass,
                                       Class<?> firstLeftClass,  Class<?> firstRightClass,
                                       Class<?> secondLeftClass, Class<?> secondRightClass) {
        return checkBiTypes(leftClass, rightClass, firstLeftClass,  firstRightClass) &&
               (leftClass  == secondLeftClass  || isPrimitive(leftClass,  secondLeftClass)) &&
               (rightClass == secondRightClass || isPrimitive(rightClass, secondRightClass));
    }

    public static boolean checkTrioTypes(Class<?> leftClass, Class<?> middleClass, Class<?> rightClass,
                                         Class<?> firstLeftClass,  Class<?> firstMiddleClass,  Class<?> firstRightClass,
                                         Class<?> secondLeftClass, Class<?> secondMiddleClass, Class<?> secondRightClass) {
        return checkTrioTypes(leftClass, middleClass, rightClass,
                              firstLeftClass,  firstMiddleClass,  firstRightClass) &&
               (leftClass   == secondLeftClass   || isPrimitive(leftClass,  secondLeftClass))    &&
               (middleClass == secondMiddleClass || isPrimitive(middleClass, secondMiddleClass)) &&
               (rightClass  == secondRightClass  || isPrimitive(rightClass, secondRightClass)) ;
    }

    public static 
    boolean checkQuarTypes(Class<?> leftClass, Class<?> bottomClass, Class<?> rightClass, Class<?> topClass,
            Class<?> firstLeftClass,  Class<?> firstBottomClass,  Class<?> firstRightClass,  Class<?> firstTopClass,
            Class<?> secondLeftClass, Class<?> secondBottomClass, Class<?> secondRightClass, Class<?> secondTopClass) {
        return checkQuarTypes(leftClass, bottomClass, rightClass, topClass,
                              firstLeftClass,  firstBottomClass,  firstRightClass,  firstTopClass) &&
               (leftClass   == secondLeftClass   || isPrimitive(leftClass,  secondLeftClass))    &&
               (bottomClass == secondBottomClass || isPrimitive(bottomClass, secondBottomClass)) &&
               (rightClass  == secondRightClass  || isPrimitive(rightClass, secondRightClass))   &&
               (topClass    == secondTopClass    || isPrimitive(topClass, secondTopClass));
    }

    public static boolean checkTypes(Class<?> inClass, Class<?> firstOutClass,
                                     Class<?> secondOutClass, Class<?> thirdOutClass) {
        return checkTypes(inClass, firstOutClass, secondOutClass) &&
               (inClass == thirdOutClass  || isPrimitive(inClass, thirdOutClass));
    }

    public static boolean checkBiTypes(Class<?> leftClass, Class<?> rightClass,
                                       Class<?> firstLeftClass,  Class<?> firstRightClass,
                                       Class<?> secondLeftClass, Class<?> secondRightClass,
                                       Class<?> thirdLeftClass,  Class<?> thirdRightClass) {
        return checkBiTypes(leftClass, rightClass,
                            firstLeftClass,  firstRightClass,
                            secondLeftClass, secondRightClass) &&
               (leftClass  == thirdLeftClass   || isPrimitive(leftClass,  thirdLeftClass))  &&
               (rightClass == thirdRightClass  || isPrimitive(rightClass, thirdRightClass));
    }

    public static boolean checkTrioTypes(Class<?> leftClass, Class<?> middleClass, Class<?> rightClass,
                                         Class<?> firstLeftClass,  Class<?> firstMiddleClass,  Class<?> firstRightClass,
                                         Class<?> secondLeftClass, Class<?> secondMiddleClass, Class<?> secondRightClass,
                                         Class<?> thirdLeftClass,  Class<?> thirdMiddleClass,  Class<?> thirdRightClass) {
        return checkTrioTypes(leftClass, middleClass, rightClass,
                              firstLeftClass,  firstMiddleClass,  firstRightClass,
                              secondLeftClass, secondMiddleClass, secondRightClass) &&
               (leftClass   == thirdLeftClass    || isPrimitive(leftClass,  thirdLeftClass))     &&
               (middleClass == thirdMiddleClass  || isPrimitive(middleClass, thirdMiddleClass))  &&
               (rightClass  == thirdRightClass   || isPrimitive(rightClass, thirdRightClass));
    }

    public static
    boolean checkQuarTypes(Class<?> leftClass, Class<?> bottomClass, Class<?> rightClass, Class<?> topClass,
            Class<?> firstLeftClass,  Class<?> firstBottomClass,  Class<?> firstRightClass,  Class<?> firstTopClass,
            Class<?> secondLeftClass, Class<?> secondBottomClass, Class<?> secondRightClass, Class<?> secondTopClass,
            Class<?> thirdLeftClass,  Class<?> thirdBottomClass,  Class<?> thirdRightClass,  Class<?> thirdTopClass) {
        return checkQuarTypes(leftClass, bottomClass, rightClass, topClass,
                              firstLeftClass,  firstBottomClass,  firstRightClass,  firstTopClass,
                              secondLeftClass, secondBottomClass, secondRightClass, secondTopClass) &&
               (leftClass   == thirdLeftClass    || isPrimitive(leftClass,  thirdLeftClass))     &&
               (bottomClass == thirdBottomClass  || isPrimitive(bottomClass, thirdBottomClass))  &&
               (rightClass  == thirdRightClass   || isPrimitive(rightClass, thirdRightClass))    &&
               (topClass    == thirdTopClass     || isPrimitive(topClass, thirdTopClass));
    }

    public static boolean checkTypes(Class<?> inClass, Class<?> firstOutClass, Class<?> secondOutClass,
                                     Class<?> thirdOutClass, Class<?> fourthOutClass) {
        return checkTypes(inClass, firstOutClass, secondOutClass, thirdOutClass) &&
               (inClass == fourthOutClass || isPrimitive(inClass, fourthOutClass));
    }

    public static boolean checkBiTypes(Class<?> leftClass, Class<?> rightClass,
                                       Class<?> firstLeftClass,  Class<?> firstRightClass,
                                       Class<?> secondLeftClass, Class<?> secondRightClass,
                                       Class<?> thirdLeftClass,  Class<?> thirdRightClass,
                                       Class<?> fourthLeftClass, Class<?> fourthRightClass) {
        return checkBiTypes(leftClass, rightClass,
                            firstLeftClass,  firstRightClass,
                            secondLeftClass, secondRightClass,
                            thirdLeftClass,  thirdRightClass) &&
               (leftClass  == fourthLeftClass  || isPrimitive(leftClass,  fourthLeftClass)) &&
               (rightClass == fourthRightClass || isPrimitive(rightClass, fourthRightClass));
    }

    public static boolean checkTrioTypes(Class<?> leftClass, Class<?> middleClass, Class<?> rightClass,
                                         Class<?> firstLeftClass,  Class<?> firstMiddleClass,  Class<?> firstRightClass,
                                         Class<?> secondLeftClass, Class<?> secondMiddleClass, Class<?> secondRightClass,
                                         Class<?> thirdLeftClass,  Class<?> thirdMiddleClass,  Class<?> thirdRightClass,
                                         Class<?> fourthLeftClass, Class<?> fourthMiddleClass, Class<?> fourthRightClass) {
        return checkTrioTypes(leftClass, middleClass, rightClass,
                              firstLeftClass,  firstMiddleClass,  firstRightClass,
                              secondLeftClass, secondMiddleClass, secondRightClass,
                              thirdLeftClass,  thirdMiddleClass,  thirdRightClass) &&
               (leftClass   == fourthLeftClass   || isPrimitive(leftClass,  fourthLeftClass))    &&
               (middleClass == fourthMiddleClass || isPrimitive(middleClass, fourthMiddleClass)) &&
               (rightClass  == fourthRightClass  || isPrimitive(rightClass, fourthRightClass));
    }

    public static
    boolean checkQuarTypes(Class<?> leftClass, Class<?> bottomClass, Class<?> rightClass, Class<?> topClass,
            Class<?> firstLeftClass,  Class<?> firstBottomClass,  Class<?> firstRightClass,  Class<?> firstTopClass,
            Class<?> secondLeftClass, Class<?> secondBottomClass, Class<?> secondRightClass, Class<?> secondTopClass,
            Class<?> thirdLeftClass,  Class<?> thirdBottomClass,  Class<?> thirdRightClass,  Class<?> thirdTopClass,
            Class<?> fourthLeftClass, Class<?> fourthBottomClass, Class<?> fourthRightClass, Class<?> fourthTopClass) {
        return checkQuarTypes(leftClass, bottomClass, rightClass, topClass,
                              firstLeftClass,  firstBottomClass,  firstRightClass,  firstTopClass,
                              secondLeftClass, secondBottomClass, secondRightClass, secondTopClass,
                              thirdLeftClass,  thirdBottomClass,  thirdRightClass,  thirdTopClass) &&
               (leftClass   == fourthLeftClass   || isPrimitive(leftClass,  fourthLeftClass))    &&
               (bottomClass == fourthBottomClass || isPrimitive(bottomClass, fourthBottomClass)) &&
               (rightClass  == fourthRightClass  || isPrimitive(rightClass, fourthRightClass))   &&
               (topClass    == fourthTopClass    || isPrimitive(topClass, fourthTopClass));
    }

    public static boolean checkTypes(Class<?> inClass, Class<?> firstOutClass, Class<?> secondOutClass,
                                     Class<?> thirdOutClass, Class<?> fourthOutClass, Class<?> fifthOutClass) {
        return checkTypes(inClass,
                          firstOutClass, secondOutClass,
                          thirdOutClass, fourthOutClass) &&
               (inClass == fifthOutClass  || isPrimitive(inClass, fifthOutClass));
    }

    public static boolean checkBiTypes(Class<?> leftClass, Class<?> rightClass,
                                       Class<?> firstLeftClass,  Class<?> firstRightClass,
                                       Class<?> secondLeftClass, Class<?> secondRightClass,
                                       Class<?> thirdLeftClass,  Class<?> thirdRightClass,
                                       Class<?> fourthLeftClass, Class<?> fourthRightClass,
                                       Class<?> fifthLeftClass,  Class<?> fifthRightClass) {
        return checkBiTypes(leftClass, rightClass,
                            firstLeftClass,  firstRightClass,
                            secondLeftClass, secondRightClass,
                            thirdLeftClass,  thirdRightClass,
                            fourthLeftClass, fourthRightClass) &&
               (leftClass  == fifthLeftClass   || isPrimitive(leftClass,  fifthLeftClass))   &&
               (rightClass == fifthRightClass  || isPrimitive(rightClass, fifthRightClass));
    }

    public static boolean checkTrioTypes(Class<?> leftClass, Class<?> middleClass, Class<?> rightClass,
                                         Class<?> firstLeftClass,  Class<?> firstMiddleClass,  Class<?> firstRightClass,
                                         Class<?> secondLeftClass, Class<?> secondMiddleClass, Class<?> secondRightClass,
                                         Class<?> thirdLeftClass,  Class<?> thirdMiddleClass,  Class<?> thirdRightClass,
                                         Class<?> fourthLeftClass, Class<?> fourthMiddleClass, Class<?> fourthRightClass,
                                         Class<?> fifthLeftClass,  Class<?> fifthMiddleClass,  Class<?> fifthRightClass) {
        return checkTrioTypes(leftClass, middleClass, rightClass,
                              firstLeftClass,  firstMiddleClass,  firstRightClass,
                              secondLeftClass, secondMiddleClass, secondRightClass,
                              thirdLeftClass,  thirdMiddleClass,  thirdRightClass,
                              fourthLeftClass, fourthMiddleClass, fourthRightClass) &&
               (leftClass   == fifthLeftClass    || isPrimitive(leftClass,  fifthMiddleClass))   &&
               (middleClass == fifthMiddleClass  || isPrimitive(middleClass, fifthMiddleClass))  &&
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
        return checkTypes(inClass,
                firstOutClass,  secondOutClass,
                thirdOutClass, fourthOutClass, fifthOutClass) &&
               (inClass == sixthOutClass  || isPrimitive(inClass, sixthOutClass));
    }

    public static boolean checkBiTypes(Class<?> leftClass, Class<?> rightClass,
                                       Class<?> firstLeftClass,  Class<?> firstRightClass,
                                       Class<?> secondLeftClass, Class<?> secondRightClass,
                                       Class<?> thirdLeftClass,  Class<?> thirdRightClass,
                                       Class<?> fourthLeftClass, Class<?> fourthRightClass,
                                       Class<?> fifthLeftClass,  Class<?> fifthRightClass,
                                       Class<?> sixthLeftClass,  Class<?> sixthRightClass) {
        return checkBiTypes(leftClass, rightClass,
                            firstLeftClass,  firstRightClass,
                            secondLeftClass, secondRightClass,
                            thirdLeftClass,  thirdRightClass,
                            fourthLeftClass, fourthRightClass,
                            fifthLeftClass,  fifthRightClass) &&
               (leftClass  == sixthLeftClass   || isPrimitive(leftClass,  sixthLeftClass))   &&
               (rightClass == sixthRightClass  || isPrimitive(rightClass, sixthRightClass));
    }

    public static boolean checkTrioTypes(Class<?> leftClass, Class<?> middleClass, Class<?> rightClass,
                                         Class<?> firstLeftClass,  Class<?> firstMiddleClass,  Class<?> firstRightClass,
                                         Class<?> secondLeftClass, Class<?> secondMiddleClass, Class<?> secondRightClass,
                                         Class<?> thirdLeftClass,  Class<?> thirdMiddleClass,  Class<?> thirdRightClass,
                                         Class<?> fourthLeftClass, Class<?> fourthMiddleClass, Class<?> fourthRightClass,
                                         Class<?> fifthLeftClass,  Class<?> fifthMiddleClass,  Class<?> fifthRightClass,
                                         Class<?> sixthLeftClass,  Class<?> sixthMiddleClass,  Class<?> sixthRightClass) {
        return checkTrioTypes(leftClass, middleClass, rightClass,
                              firstLeftClass,  firstMiddleClass,  firstRightClass,
                              secondLeftClass, secondMiddleClass, secondRightClass,
                              thirdLeftClass,  thirdMiddleClass,  thirdRightClass,
                              fourthLeftClass, fourthMiddleClass, fourthRightClass,
                              fifthLeftClass,  fifthMiddleClass,  fifthRightClass) &&
               (leftClass   == sixthLeftClass    || isPrimitive(leftClass,  sixthLeftClass))     &&
               (middleClass == sixthMiddleClass  || isPrimitive(middleClass, sixthMiddleClass))  &&
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
