package org.kl.shape;

import org.kl.meta.Exclude;
import org.kl.meta.Extract;
import org.kl.type.IntRef;

public class Circle extends Figure {
    private int radius;

    @Exclude
    private int temp;

    public Circle() {
        this.radius = 5;
    }

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public int square() {
        return (int) (2 * Math.PI * radius);
    }

    @Extract
    public void deconstruct(IntRef radius) {
        radius.set(this.radius);
    }
}
