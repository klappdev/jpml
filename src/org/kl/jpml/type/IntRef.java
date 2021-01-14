package org.kl.jpml.type;

import java.util.Objects;

public final class IntRef {
    private int value;

    public IntRef() {}

    public void set(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        final IntRef tmp = (IntRef) other;

        return value == tmp.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "IntRef [value=" + value + "]";
    }
}