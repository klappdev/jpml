package org.kl.test.shape;

import org.kl.meta.Extract;
import org.kl.test.shape.Figure;
import org.kl.type.ByteRef;

public class Quartangle extends Figure {
    private byte a;
    private byte b;
    private byte c;
    private byte d;

    public Quartangle() {
        this.a = 5;
        this.b = 5;
        this.c = 10;
        this.d = 15;
    }

    public Quartangle(byte a, byte b, byte c, byte d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public int square() {
        return a * b * c * d;
    }

    @Extract
    public void deconstruct(ByteRef a, ByteRef b, ByteRef c, ByteRef d) {
        a.set(this.a);
        b.set(this.b);
        c.set(this.c);
        d.set(this.d);
    }
}