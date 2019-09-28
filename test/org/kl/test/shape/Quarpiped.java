package org.kl.test.shape;

import org.kl.meta.Extract;
import org.kl.test.shape.Figure;
import org.kl.type.CharRef;

public class Quarpiped extends Figure {
    private char a;
    private char b;
    private char c;
    private char d;

    public Quarpiped() {
        this.a = 5;
        this.b = 5;
        this.c = 10;
        this.d = 15;
    }

    public Quarpiped(char a, char b, char c, char d) {
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
    public void deconstruct(CharRef a, CharRef b, CharRef c, CharRef d) {
        a.set(this.a);
        b.set(this.b);
        c.set(this.c);
        d.set(this.d);
    }
}