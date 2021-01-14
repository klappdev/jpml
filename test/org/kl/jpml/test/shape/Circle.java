package org.kl.jpml.test.shape;

import org.kl.jpml.meta.Exclude;
import org.kl.jpml.meta.Extract;
import org.kl.jpml.type.IntRef;

public class Circle extends Figure {
    private int radius;

    @Exclude private static int staticRadius = 5;
    @Exclude private int temp;

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

    @Extract
    public static void unapply(IntRef radius) {
        radius.set(staticRadius);
    }

    public int radius() {
        return radius;
    }
}
