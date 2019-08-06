package org.kl.bean;

import java.util.function.BiConsumer;

public final class Extractor<T1, T2> {
    private String name;
    private BiConsumer<T1, T2> consumer;
    private Object parameter;

    public Extractor(String name, BiConsumer<T1, T2> consumer, Object parameter) {
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
