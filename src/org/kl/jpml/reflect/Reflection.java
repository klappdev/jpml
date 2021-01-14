package org.kl.jpml.reflect;

import org.kl.jpml.error.PatternException;
import org.kl.jpml.lambda.QuarConsumer;
import org.kl.jpml.lambda.TriConsumer;
import org.kl.jpml.type.*;
import org.kl.jpml.util.Tuple;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

public final class Reflection {
    private static MethodHandles.Lookup lookup = MethodHandles.lookup();

    private static HashMap<Class<?>, ExtractorAttribute> cacheExtractors = new HashMap<>();
    private static HashMap<Class<?>, FieldAttribute> cacheFields = new HashMap<>();
    private static HashMap<Class<?>, SealedAttribute> cacheSubclasses = new HashMap<>();

    @SuppressWarnings("unchecked")
    private static <T> T createInstance(Class<T> clazz) {
        if (byte.class == clazz || Byte.class == clazz) {
            return (T)(Byte)(byte) 0;
        } else if (short.class == clazz || Short.class == clazz) {
            return (T)(Short)(short) 0;
        } else if (int.class == clazz || Integer.class == clazz) {
            return (T)(Integer) 0;
        } else if (long.class == clazz || Long.class == clazz) {
            return (T)(Long) 0L;
        } else if (float.class == clazz || Float.class == clazz) {
            return (T)(Float) 0F;
        } else if (double.class == clazz || Double.class == clazz) {
            return (T)(Double) 0D;
        } else if (char.class == clazz || Character.class == clazz) {
            return (T)(Character) '0';
        } else if (boolean.class == clazz || Boolean.class == clazz) {
            return (T)(Boolean) false;
        } else if (void.class == clazz || Void.class == clazz) {
            return null;
        }

        if (clazz.isArray()) {
            return (T) Array.newInstance(clazz.getComponentType(), 0);
        }

        try {
            return clazz.getConstructor().newInstance();
        } catch (Exception e) {
            throw new PatternException("Can't create instance: " + e.getMessage());
        }
    }

    /**
     * Method using target-dependent components
     * @since 8 - java.lang.reflect.Field
     * @since 9 - java.lang.invoke.VarHandle
     */
    private static void setField(Object instance, Field field, Object value) {
        if (value == null && field.getType().isPrimitive()) {
            return;
        }

        field.setAccessible(true);

        try {
            if (byte.class == field.getType()) {
                field.setByte(instance, (byte) value);
            } else if (short.class == field.getType()) {
                field.setShort(instance, (short) value);
            } else if (int.class == field.getType()) {
                field.setInt(instance, (int) value);
            } else if (long.class == field.getType()) {
                field.setLong(instance, (long) value);
            } else if (float.class == field.getType()) {
                field.setFloat(instance, (float) value);
            } else if (double.class == field.getType()) {
                field.setDouble(instance, (double) value);
            } else if (char.class == field.getType()) {
                field.setChar(instance, (char) value);
            } else if (boolean.class == field.getType()) {
                field.setBoolean(instance, (boolean) value);
            } else {
                field.set(instance, value);
            }
        } catch (IllegalAccessException e) {
            throw new PatternException("Illegal access to field: " + e.getMessage());
        }
    }

    /**
     * Method using target-dependent components
     * @since 8 - java.lang.reflect.Field
     * @since 9 - java.lang.invoke.VarHandle
     */
    private static Object getField(Object instance, Field field) {
        field.setAccessible(true);

        try {
            if (byte.class == field.getType()) {
                return field.getByte(instance);
            } else if (short.class == field.getType()) {
                return field.getShort(instance);
            } if (int.class == field.getType()) {
                return field.getInt(instance);
            } else if (long.class == field.getType()) {
                return field.getLong(instance);
            } else if (float.class == field.getType()) {
                return field.getFloat(instance);
            } else if (double.class == field.getType()) {
                return field.getDouble(instance);
            } else if (char.class == field.getType()) {
                return field.getChar(instance);
            } else if (boolean.class == field.getType()) {
                return field.getBoolean(instance);
            } else if (void.class == field.getType()) {
                return null;
            } else {
                return field.get(instance);
            }
        } catch (IllegalAccessException e) {
            throw new PatternException("Illegal access to field: " + e.getMessage());
        }
    }

    public static <V> Object[] invokeExtractor(V value, int countParameters) {
        ExtractorAttribute data = cacheExtractors.computeIfAbsent(value.getClass(),
                                                          routine -> new ExtractorAttribute(lookup, routine));
        switch (countParameters) {
            case 1: return invokeUnExtractor(value, data);
            case 2: return invokeBiExtractor(value, data);
            case 3: return invokeTriExtractor(value, data);
            default: throw new PatternException("Extract method must not has more than 3 parameters");
        }
    }

    @SuppressWarnings("unchecked")
    public static <V> Object[] invokeExtractor(V value, String name, int countParameters) {
        ExtractorAttribute data = cacheExtractors.computeIfAbsent(value.getClass(),
                                                          routine -> new ExtractorAttribute(lookup, routine));
        switch (countParameters) {
            case 1: return invokeUnExtractor(value, name, data);
            case 2: return invokeBiExtractor(value, name, data);
            case 3: return invokeTriExtractor(value, name, data);
            default: throw new PatternException("Extract method must not has more than 3 parameters");
        }
    }

    @SuppressWarnings("unchecked")
    private static <V> Object[] invokeUnExtractor(V value, String name, ExtractorAttribute data) {
        Object[] result = new Object[1];
        Tuple.Tuple3<String, BiConsumer<?, ?>, Object> extractor = data.getExtractors().get(0);
        String label = extractor.first();

        if (label.equals(name)) {
            Object parameter = extractor.third();
            BiConsumer<V, Object> consumer = (BiConsumer<V, Object>) extractor.second();

            consumer.accept(value, parameter);
            result[0] = resolveParameter(parameter);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private static <V> Object[] invokeUnExtractor(V value, ExtractorAttribute data) {
        Object[] result = new Object[1];
        Tuple.Tuple3<String, BiConsumer<?, ?>, Object> extractor = data.getExtractors().get(0);

        BiConsumer<V, Object> consumer = (BiConsumer<V, Object>) extractor.second();
        Object parameter = extractor.third();

        consumer.accept(value, parameter);
        result[0] = resolveParameter(parameter);

        return result;
    }

    @SuppressWarnings("unchecked")
    private static <V> Object[] invokeBiExtractor(V value, String name, ExtractorAttribute data) {
        Object[] result = new Object[2];

        for (Tuple.Tuple3<String, TriConsumer<?, ?, ?>, Object[]> biExtractor : data.getBiExtractors()) {
            String label = biExtractor.first();

            if (label.equals(name)) {
                TriConsumer<V, Object, Object> consumer = (TriConsumer<V, Object, Object>) biExtractor.second();
                Object[] parameters = biExtractor.third();

                consumer.accept(value, parameters[0], parameters[1]);
                result = resolveParameters(parameters);
                break;
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private static <V> Object[] invokeBiExtractor(V value, ExtractorAttribute data) {
        Object[] result = new Object[2];

        for (Tuple.Tuple3<String, TriConsumer<?, ?, ?>, Object[]> biExtractor : data.getBiExtractors()) {
            TriConsumer<V, Object, Object> consumer = (TriConsumer<V, Object, Object>) biExtractor.second();
            Object[] parameters = biExtractor.third();

            consumer.accept(value, parameters[0], parameters[1]);
            result = resolveParameters(parameters);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private static <V> Object[] invokeTriExtractor(V value, String name, ExtractorAttribute data) {
        Object[] result = new Object[3];

        for (Tuple.Tuple3<String, QuarConsumer<?, ?, ?, ?>, Object[]> triExtractor : data.getTriExtractors()) {
            String label = triExtractor.first();

            if (label.equals(name)) {
                QuarConsumer<V, Object, Object, Object> consumer = (QuarConsumer<V, Object, Object, Object>) triExtractor.second();
                Object[] parameters = triExtractor.third();

                consumer.accept(value, parameters[0], parameters[1], parameters[2]);
                result = resolveParameters(parameters);
                break;
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private static <V> Object[] invokeTriExtractor(V value, ExtractorAttribute data) {
        Object[] result = new Object[3];

        for (Tuple.Tuple3<String, QuarConsumer<?, ?, ?, ?>, Object[]> triExtractor : data.getTriExtractors()) {
            QuarConsumer<V, Object, Object, Object> consumer = (QuarConsumer<V, Object, Object, Object>) triExtractor.second();
            Object[] parameters = triExtractor.third();

            consumer.accept(value, parameters[0], parameters[1], parameters[2]);
            result = resolveParameters(parameters);
        }

        return result;
    }

    public static <V> Object[] fetchFields(V value, int countFields) {
        FieldAttribute data = cacheFields.computeIfAbsent(value.getClass(), FieldAttribute::new);

        if (countFields > 3) {
            throw new PatternException("Class must not has more than 3 fields");
        }

        List<Field> fields = data.getFields();
        Object[] result = new Object[countFields];

        if (fields.size() != countFields) {
            throw new PatternException("Count fields more then in target. Exclude unnecessary fields");
        }

        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);

            result[i] = getField(value, field);
        }

        return result;
    }

    public static <V> Object[] fetchFields(V value, int countFields, String... names) {
        FieldAttribute data = cacheFields.computeIfAbsent(value.getClass(), FieldAttribute::new);

        List<Field> fields = data.getFields();
        Object[] result = new Object[countFields];
        int i = 0;

        if (countFields > 3) {
            throw new PatternException("Class must not has more than 3 fields");
        }

        if (fields.size() != countFields) {
            throw new PatternException("Count fields more then in target. Exclude unnecessary fields");
        }

        for (Field field : fields) {
            if (field.getName().equals(names[i])) {
                result[i] = getField(value, field);
                i++;
            }
        }

        return result;
    }

    public static <V> void verifyExhaustiveness(V value, Class<?>[] inputClasses) {
        SealedAttribute data = cacheSubclasses.computeIfAbsent(value.getClass(), SealedAttribute::new);
        Class<?>[] subClasses = data.getSubClasses();
        StringBuilder builder = new StringBuilder();
        boolean flag = false;

        if (inputClasses.length > 6) {
            throw new PatternException("Class must not has more than 6 fields");
        }

        if (subClasses.length != inputClasses.length) {
            throw new PatternException("Require " + inputClasses.length + " subclasses. " +
                                       "But checked class has " + subClasses.length + " subclasses.");
        }

        for (Class<?> subClass : subClasses) {
            for (Class<?> inputClass : inputClasses) {
                if (subClass == inputClass) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                builder.append(subClass).append(",");
            }

            flag = false;
        }

        if (builder.length() >= 1) {
            throw new PatternException("Must to be exhaustive, add necessary " + builder.toString() +
                                       " branches or else branch instead");
        }
    }

    /*package-private*/ static void verifySignatureExtractor(Method method) {
        if (method.getReturnType() != void.class) {
            throw new PatternException("Extract method must not has return value");
        }

        if (!Modifier.isPublic(method.getModifiers())) {
            throw new PatternException("Extract method must to be public");
        }
    }

    /*package-private*/ static void verifyParametersExtractor(Method method) {
        Arrays.stream(method.getParameterTypes())
              .forEach(Reflection::verifyParameterExtractor);
    }

    private static void verifyParametersExtractor(Method method, int countParameters) {
        if (method.getParameterCount() != countParameters) {
            throw new PatternException("Extract method must to have one or more parameters");
        }

        Arrays.stream(method.getParameterTypes())
              .forEach(Reflection::verifyParameterExtractor);
    }

    private static void verifyParameterExtractor(Class<?> clazz) {
        if (clazz.isArray()) {
            throw new PatternException("Parameter extract method must not be array");
        }

        if (isPrimitive(clazz)) {
            throw new PatternException("Can not pass primitives or wrappers by reference.\n" +
                                       "Use instead IntRef, FloatRef and etc.");
        }
    }

    /*package-private*/ static Object[] prepareParameters(Class<?>[] parameterTypes) {
        Object[] parameters = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            parameters[i] = createInstance(parameterTypes[i]);
        }

        return parameters;
    }

    private static Object[] resolveParameters(Object[] args) {
        Object[] result = new Object[args.length];

        for (int i = 0; i < args.length; i++) {
            result[i] = resolveParameter(args[i]);
        }

        return result;
    }

    private static Object resolveParameter(Object arg) {
        Object result;

        if (arg instanceof ByteRef) {
            result = ((ByteRef) arg).get();

        } else if (arg instanceof ShortRef) {
            result = ((ShortRef) arg).get();

        } else  if (arg instanceof IntRef) {
            result = ((IntRef) arg).get();

        } else if (arg instanceof LongRef) {
            result = ((LongRef) arg).get();

        } else if (arg instanceof FloatRef) {
            result = ((FloatRef) arg).get();

        } else if (arg instanceof DoubleRef) {
            result = ((DoubleRef) arg).get();

        } else if (arg instanceof CharRef) {
            result = ((CharRef) arg).get();

        } else if (arg instanceof BooleanRef) {
            result = ((BooleanRef) arg).get();

        } else {
            result = arg;
        }

        return result;
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

    private static boolean isPrimitive(Class<?> clazz) {
        boolean flag = false;

        if ((clazz == byte.class   || clazz == Byte.class)          ||
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

    public static <T1, T2> boolean compareValues(T1 first, T2 second) {
        return first != null && first.equals(second);
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
