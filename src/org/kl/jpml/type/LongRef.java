package org.kl.jpml.type;

import java.util.Objects;

public final class LongRef {
    private long value;

    public LongRef() {}

    public void set(long value) {
        this.value = value;
    }

    public long get() {
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

        final LongRef tmp = (LongRef) other;

        return value == tmp.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "LongRef [value=" + value + "]";
    }
}