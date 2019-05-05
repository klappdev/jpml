package org.kl.bean;

public final class Item<T> {
    private final String name;
    private final T value;

    public Item(String name) {
        this.name  = name;
        this.value = null;
    }

    public Item(T value) {
        this.name  = null;
        this.value = value;
    }

    public Item(String name, T value) {
        this.name  = name;
        this.value = value;
    }

    public String getName() { return name; }
    public T getValue() { return value; }
}
