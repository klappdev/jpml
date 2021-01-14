package org.kl.jpml.type;

import java.util.Objects;

public final class ByteRef {
    private byte value;

    public ByteRef() {}

    public void set(byte value) {
        this.value = value;
    }

    public byte get() {
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

        final ByteRef tmp = (ByteRef) other;

        return value == tmp.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ByteRef [value=" + value + "]";
    }
}
