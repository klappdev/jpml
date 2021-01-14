package org.kl.jpml.type;

import java.util.Objects;

public final class DoubleRef {
    private double value;

    public DoubleRef() {}

    public void set(double value) {
        this.value = value;
    }

    public double get() {
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

        final DoubleRef tmp = (DoubleRef) other;

        return Double.compare(tmp.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "DoubleRef [value=" + value + "]";
    }
}
