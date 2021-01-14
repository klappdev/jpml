package org.kl.jpml.type;

import java.util.Objects;

public final class BooleanRef {
    private boolean value;

    public BooleanRef() {}

    public void set(boolean value) {
        this.value = value;
    }

    public boolean get() {
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

        final BooleanRef tmp = (BooleanRef) other;

        return value == tmp.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "BooleanRef [value=" + value + "]";
    }
}
