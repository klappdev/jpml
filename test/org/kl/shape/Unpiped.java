package org.kl.shape;

import org.kl.meta.Extract;
import org.kl.type.DoubleRef;

public class Unpiped extends Figure {
    private double radius;

    public Unpiped() {
        this.radius = 20;
    }

    public Unpiped(double radius) {
        this.radius = radius;
    }

    @Override
    public int square() {
        return (int) (2 * Math.PI * radius);
    }

    @Extract
    public void deconstruct(DoubleRef radius) {
        radius.set(this.radius);
    }
}
