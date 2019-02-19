package org.kl.shape;

import org.kl.attr.Extract;
import org.kl.ref.ByteRef;

public class Quartangle extends Figure {
    private byte a = 5;
    private byte b = 5;
    private byte c = 10;
    private byte d = 15;

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