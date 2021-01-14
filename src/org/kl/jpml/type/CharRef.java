package org.kl.jpml.type;

import java.util.Objects;

public final class CharRef {
    private char value;

    public CharRef() {}

    public void set(char value) {
        this.value = value;
    }

    public char get() {
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

        final CharRef tmp = (CharRef) other;

        return value == tmp.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "CharRef [value=" + value + "]";
    }
}
