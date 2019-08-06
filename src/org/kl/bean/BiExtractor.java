package org.kl.bean;

import org.kl.lambda.TriConsumer;

public class BiExtractor<T1, T2, T3> {
    private String name;
    private TriConsumer<T1, T2, T3> consumer;
    private Object[] parameters;

    public BiExtractor(String name, TriConsumer<T1, T2, T3> consumer, Object[] parameters) {
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
