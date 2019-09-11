package org.kl.shape;

import org.kl.meta.Extract;
import org.kl.type.DoubleRef;

public class Triangle extends Figure {
    private double width;
    private double height;

    private static int staticWidth  = 5;
    private static int staticHeight = 10;

    public Triangle() {
        this.width  = 5;
        this.height = 10;
    }

    public Triangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int square() {
        return (int) (width * height);
    }

    @Extract
    public void deconstruct(DoubleRef width, DoubleRef height) {
        width.set(this.width);
        height.set(this.height);
    }

    @Extract
    public static void unapply(DoubleRef width, DoubleRef height) {
        width.set(staticWidth);
        height.set(staticHeight);
    }

    public double width() {
        return width;
    }

    public double height() {
        return height;
    }
}
