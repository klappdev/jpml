package org.kl.state;

public enum State {
    DEFAULT(0);

    private int value;

    private State(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
