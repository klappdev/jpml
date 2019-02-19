package org.kl.ref;

public class LongRef {
    private long[] array;

    public LongRef() {
        this.array = new long[1];
    }

    public void set(long value) {
        this.array[0] = value;
    }

    public long get() {
        return array[0];
    }
}