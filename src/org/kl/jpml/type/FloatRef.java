package org.kl.jpml.type;

import java.util.Objects;

public final class FloatRef {
    private float value;

    public FloatRef() {}

    public void set(float value) {
        this.value = value;
    }

    public float get() {
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

        final FloatRef tmp = (FloatRef) other;

        return Float.compare(tmp.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "FloatRef [value=" + value + "]";
    }
}