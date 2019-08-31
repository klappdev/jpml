package org.kl.reflect;

import org.kl.error.PatternException;
import org.kl.lambda.TriConsumer;
import org.kl.meta.Extract;
import org.kl.type.*;
import org.kl.util.Extractor;
import sun.misc.SharedSecrets;
import sun.reflect.ConstantPool;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Reflection {
    private static MethodHandles.Lookup lookup = MethodHandles.lookup();
    private static MethodHandle extractorInvoker;
    private static MethodHandle memberInvoker;
    private static HashMap<Class<?>, Extracture> cacheExtractures = new HashMap<>();
    private static HashMap<Class<?>, Structure>  cacheStructures  = new HashMap<>();

    public static Object[] invokeUnreferenceExtractor(Consumer<?> consumer, int countParameters) {
        return invokeUnreferenceExtractor(consumer.getClass(), countParameters);
    }

    public static Object[] invokeUnreferenceExtractor(BiConsumer<?, ?> consumer, int countParameters) {
        return invokeUnreferenceExtractor(consumer.getClass(), countParameters);
    }

    public static Object[] invokeUnreferenceExtractor(TriConsumer<?, ?, ?> consumer, int countParameters) {
        return invokeUnreferenceExtractor(consumer.getClass(), countParameters);
    }

    private static Object[] invokeUnreferenceExtractor(Class<?> clazz, int countParameters) {
        Object[] result;
        Object[] parameters;

        try {
            Method method = unreferenceExtractor(clazz, countParameters);
            parameters = prepareParameters(method.getParameterTypes());

            extractorInvoker = MethodHandles.lookup().unreflect(method);
            extractorInvoker.invokeWithArguments(parameters);

            result = resolveParameters(parameters);
        } catch (Throwable e) {
            throw new PatternException("Can't invoke extract method: " + e.getMessage());
        }

        return result;
    }

    /* Method target-independent:
     * v8  - sun.reflect.ConstantPool
     * v9  - jdk.unsupported.ConstantPool
     * v12 - java.lang.constant.Constable
     */
    @SuppressWarnings("sunapi")
    private static Method unreferenceExtractor(Class<?> clazz, int countParameters) {
        ConstantPool pool = SharedSecrets.getJavaLangAccess().getConstantPool(clazz);
        int size = pool.getSize();

        for (int i = 1; i < size; i++) {
            try {
                Member member = pool.getMethodAt(i);

                if (member instanceof Method && ((Method) member).isAnnotationPresent(Extract.class)) {
                    Method extractMethod = ((Method) member);

                    System.err.println("\nunreference: " + extractMethod);

                    verifySignatureExtractor(extractMethod);
                    verifyParametersExtractor(extractMethod, countParameters);

                    return extractMethod;
                }
            } catch (IllegalArgumentException e) {
                /* skip non method entry */
            }
        }

        throw new PatternException("Method is not marked as extract");
    }

    public static <V> Object[] invokeExtractor(V value, int countParameters) {
        Extracture data = cacheExtractures.computeIfAbsent(value.getClass(),
                                                          routine -> new Extracture(lookup, routine));
        switch (countParameters) {
            case 1: return invokeUnExtractor(value, data);
            case 2: return invokeBiExtractor(value, data);
            case 3: return invokeTriExtractor(value, data);
            default: throw new PatternException("Extract method must not has more than 3 parameters");
        }
    }

    @SuppressWarnings("unchecked")
    public static <V> Object[] invokeExtractor(V value, String name, int countParameters) {
        Extracture data = cacheExtractures.computeIfAbsent(value.getClass(),
                                                          routine -> new Extracture(lookup, routine));
        switch (countParameters) {
            case 1: return invokeUnExtractor(value, name, data);
            case 2: return invokeBiExtractor(value, name, data);
            case 3: return invokeTriExtractor(value, name, data);
            default: throw new PatternException("Extract method must not has more than 3 parameters");
        }
    }

    @SuppressWarnings("unchecked")
    private static <V> Object[] invokeUnExtractor(V value, String name, Extracture data) {
        Object[] result = new Object[1];
        Extractor.Extractor2 extractor = data.getExtractors().get(0);

        if (extractor.getName().equals(name)) {
            Object parameter = extractor.getParameter();

            extractor.getConsumer().accept(value, parameter);
            result[0] = resolveParameter(parameter);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private static <V> Object[] invokeUnExtractor(V value, Extracture data) {
        Object[] result = new Object[1];
        Extractor.Extractor2 extractor = data.getExtractors().get(0);
        Object parameter = extractor.getParameter();

        extractor.getConsumer().accept(value, parameter);
        result[0] = resolveParameter(parameter);

        return result;
    }

    @SuppressWarnings("unchecked")
    private static <V> Object[] invokeBiExtractor(V value, String name, Extracture data) {
        Object[] result = new Object[2];

        for (Extractor.Extractor3 biExtractor : data.getBiExtractors()) {
            if (biExtractor.getName().equals(name)) {
                Object[] parameters = biExtractor.getParameters();

                biExtractor.getConsumer().accept(value, parameters[0], parameters[1]);
                result = resolveParameters(parameters);
                break;
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private static <V> Object[] invokeBiExtractor(V value, Extracture data) {
        Object[] result = new Object[2];

        for (Extractor.Extractor3 biExtractor : data.getBiExtractors()) {
            Object[] parameters = biExtractor.getParameters();

            biExtractor.getConsumer().accept(value, parameters[0], parameters[1]);
            result = resolveParameters(parameters);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private static <V> Object[] invokeTriExtractor(V value, String name, Extracture data) {
        Object[] result = new Object[3];

        for (Extractor.Extractor4 triExtractor : data.getTriExtractors()) {
            if (triExtractor.getName().equals(name)) {
                Object[] parameters = triExtractor.getParameters();

                triExtractor.getConsumer().accept(value, parameters[0], parameters[1], parameters[2]);
                result = resolveParameters(parameters);
                break;
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private static <V> Object[] invokeTriExtractor(V value, Extracture data) {
        Object[] result = new Object[3];

        for (Extractor.Extractor4 triExtractor : data.getTriExtractors()) {
            Object[] parameters = triExtractor.getParameters();

            triExtractor.getConsumer().accept(value, parameters[0], parameters[1], parameters[2]);
            result = resolveParameters(parameters);
        }

        return result;
    }

    public static <V> Object[] fetchMembers(V value, int countMembers) {
        Structure data = cacheStructures.computeIfAbsent(value.getClass(), Structure::new);
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        switch (countMembers) {
            case 1: return fetchMember(lookup, value, data, 1);
            case 2: return fetchMember(lookup, value, data, 2);
            case 3: return fetchMember(lookup, value, data, 3);
            default: throw new PatternException("Structure must not has more than 3 fields");
        }
    }

    /* Method target-independent:
     * v8 - MethodHandle,
     * v9 - VarHandle
     */
    private static <V> Object[] fetchMember(MethodHandles.Lookup lookup, V value, Structure data, int countMember) {
        List<Field> members = data.getMembers();
        Object[] result = new Object[countMember];

        if (members.size() != countMember) {
            throw new PatternException("Count fields more then in target. Exclude unnecessary fields");
        }

        for (int i = 0; i < members.size(); i++) {
            Field member = members.get(i);

            try {
                member.setAccessible(true);
                memberInvoker = lookup.unreflectGetter(member);
                result[i] = memberInvoker.invoke(value);
            } catch (IllegalAccessException e) {
                throw new PatternException("Can not access to field " + member.getName() + " " + e.getMessage());
            } catch (Throwable throwable) {
                throw new PatternException("Can not get value field " + member.getName());
            }
        }

        return result;
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
            try {
                parameters[i] = parameterTypes[i].newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new PatternException("Can not resolve parameters extract method: " + e.getMessage());
            }
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
