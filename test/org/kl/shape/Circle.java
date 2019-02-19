package org.kl.shape;

import org.kl.attr.Extract;
import org.kl.ref.IntRef;

public class Circle extends Figure {
    private int radius = 5;

    @Override
    public int square() {
        return (int) (2 * Math.PI * radius);
    }

    @Extract
    public void deconstruct(IntRef radius) {
        radius.set(this.radius);
    }
}
