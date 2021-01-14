package org.kl.jpml.reflect;

import org.kl.jpml.error.PatternException;
import org.kl.jpml.lambda.QuarConsumer;
import org.kl.jpml.lambda.TriConsumer;
import org.kl.jpml.meta.Extract;
import org.kl.jpml.util.Tuple;

import java.lang.invoke.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import static org.kl.jpml.reflect.Reflection.*;

final class ExtractorAttribute {
    private List<Tuple.Tuple3<String, BiConsumer<?, ?>, Object>> extractors;
    private List<Tuple.Tuple3<String, TriConsumer<?, ?, ?>, Object[]>> biExtractors;
    private List<Tuple.Tuple3<String, QuarConsumer<?, ?, ?, ?>, Object[]>> triExtractors;

    /*package-private*/ ExtractorAttribute(MethodHandles.Lookup lookup, Class<?> clazz)  {
        verifyExtractors(lookup, clazz);
    }

    @SuppressWarnings("unchecked")
    private void verifyExtractors(MethodHandles.Lookup lookup, Class<?> clazz)  {
        for (final Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Extract.class) && !Modifier.isStatic(method.getModifiers())) {
                verifySignatureExtractor(method);
                verifyParametersExtractor(method);

                Class<?>[] parameterTypes = method.getParameterTypes();

                try {
                    if (parameterTypes.length == 1) {
                        if (extractors == null) {
                            this.extractors = new ArrayList<>(3);
                        }

                        extractors.add(Tuple.of(
                            method.getName(),
                            prepareExtractor(lookup, method),
                            prepareParameters(parameterTypes)[0]
                        ));
                    } else if (parameterTypes.length == 2) {
                        if (biExtractors == null) {
                            this.biExtractors = new ArrayList<>(3);
                        }

                        biExtractors.add(Tuple.of(
                            method.getName(),
                            prepareBiExtractor(lookup, method),
                            prepareParameters(parameterTypes)
                        ));
                    } else if (parameterTypes.length == 3) {
                        if (triExtractors == null) {
                            this.triExtractors = new ArrayList<>(3);
                        }

                        triExtractors.add(Tuple.of(
                            method.getName(),
                            prepareTriExtractor(lookup, method),
                            prepareParameters(parameterTypes)
                        ));
                    }
                } catch (Throwable e) {
                    throw new PatternException("Can't prepare extract method: " + e.getMessage());
                }
            }
        }

        if ((extractors != null && extractors.size() == 0)     ||
            (biExtractors != null && biExtractors.size() == 0) ||
            (triExtractors != null && triExtractors.size() == 0)) {
            throw new PatternException("Checked class must to have extract method(s)");
        }
    }

    private BiConsumer prepareExtractor(MethodHandles.Lookup lookup, Method method) throws Throwable {
        MethodHandle handle = lookup.unreflect(method);

        CallSite site = LambdaMetafactory.metafactory(
                lookup, "accept",
                MethodType.methodType(BiConsumer.class),
                MethodType.methodType(void.class, Object.class, Object.class),
                handle, handle.type()
        );

        return (BiConsumer) site.getTarget().invokeExact();
    }

    private TriConsumer prepareBiExtractor(MethodHandles.Lookup lookup, Method method) throws Throwable {
        MethodHandle handle = lookup.unreflect(method);

        CallSite site = LambdaMetafactory.metafactory(
                lookup, "accept",
                MethodType.methodType(TriConsumer.class),
                MethodType.methodType(void.class, Object.class, Object.class, Object.class),
                handle, handle.type()
        );

        return (TriConsumer) site.getTarget().invokeExact();
    }

    private QuarConsumer prepareTriExtractor(MethodHandles.Lookup lookup, Method method) throws Throwable {
        MethodHandle handle = lookup.unreflect(method);

        CallSite site = LambdaMetafactory.metafactory(
                lookup, "accept",
                MethodType.methodType(QuarConsumer.class),
                MethodType.methodType(void.class, Object.class, Object.class, Object.class, Object.class),
                handle, handle.type()
        );

        return (QuarConsumer) site.getTarget().invokeExact();
    }

    /*package-private*/ List<Tuple.Tuple3<String, BiConsumer<?, ?>, Object>> getExtractors() {
        return extractors;
    }

    /*package-private*/ List<Tuple.Tuple3<String, TriConsumer<?, ?, ?>, Object[]>> getBiExtractors() {
        return biExtractors;
    }

    /*package-private*/ List<Tuple.Tuple3<String, QuarConsumer<?, ?, ?, ?>, Object[]>> getTriExtractors() {
        return triExtractors;
    }
}
