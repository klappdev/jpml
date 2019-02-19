package org.kl.shape;

import org.kl.attr.Extract;
import org.kl.ref.CharRef;

public class Quarpiped extends Figure {
    private char a = 5;
    private char b = 5;
    private char c = 10;
    private char d = 15;

    @Override
    public int square() {
        return a * b * c * d;
    }

    @Extract
    public void deconstruct(CharRef a, CharRef b, CharRef c, CharRef d) {
        a.set(this.a);
        b.set(this.b);
        c.set(this.c);
        d.set(this.d);
    }
}