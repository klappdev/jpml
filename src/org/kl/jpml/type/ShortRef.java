package org.kl.jpml.type;

import java.util.Objects;

public final class ShortRef {
    private short value;

    public ShortRef() {}

    public void set(short value) {
        this.value = value;
    }

    public short get() {
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

        final ShortRef tmp = (ShortRef) other;

        return value == tmp.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ShortRef [value=" + value + "]";
    }
}
