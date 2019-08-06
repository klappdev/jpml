package org.kl.bean;

import org.kl.lambda.QuarConsumer;

public class TriExtractor<T1, T2, T3, T4> {
    private String name;
    private QuarConsumer<T1, T2, T3, T4> consumer;
    private Object[] parameters;

    public TriExtractor(String name, QuarConsumer<T1, T2, T3, T4> consumer, Object[] parameters) {
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
