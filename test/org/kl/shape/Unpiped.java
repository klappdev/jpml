package org.kl.shape;

import org.kl.attr.Extract;
import org.kl.ref.DoubleRef;

public class Unpiped extends Figure {
    private double radius = 20;

    @Override
    public int square() {
        return (int) (2 * Math.PI * radius);
    }

    @Extract
    public void deconstruct(DoubleRef radius) {
        radius.set(this.radius);
    }
}
