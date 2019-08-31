package org.kl.util;

import org.kl.lambda.QuarConsumer;
import org.kl.lambda.TriConsumer;

import java.util.function.BiConsumer;

public abstract class Extractor {

    public static final class Extractor2<T1, T2> {
        private String name;
        private BiConsumer<T1, T2> consumer;
        private Object parameter;

        public Extractor2(String name, BiConsumer<T1, T2> consumer, Object parameter) {
            this.name = name;
            this.consumer = consumer;
            this.parameter = parameter;
        }

        public String getName() {
            return name;
        }

        public BiConsumer<T1, T2> getConsumer() {
            return consumer;
        }

        public Object getParameter() {
            return parameter;
        }
    }

    public static final class Extractor3<T1, T2, T3> {
        private String name;
        private TriConsumer<T1, T2, T3> consumer;
        private Object[] parameters;

        public Extractor3(String name, TriConsumer<T1, T2, T3> consumer, Object[] parameters) {
            this.name = name;
            this.consumer = consumer;
            this.parameters = parameters;
        }

        public String getName() {
            return name;
        }

        public TriConsumer<T1, T2, T3> getConsumer() {
            return consumer;
        }

        public Object[] getParameters() {
            return parameters;
        }
    }

    public static final class Extractor4<T1, T2, T3, T4> {
        private String name;
        private QuarConsumer<T1, T2, T3, T4> consumer;
        private Object[] parameters;

        public Extractor4(String name, QuarConsumer<T1, T2, T3, T4> consumer, Object[] parameters) {
            this.name = name;
            this.consumer = consumer;
            this.parameters = parameters;
        }

        public String getName() {
            return name;
        }

        public QuarConsumer<T1, T2, T3, T4> getConsumer() {
            return consumer;
        }

        public Object[] getParameters() {
            return parameters;
        }
    }
}
