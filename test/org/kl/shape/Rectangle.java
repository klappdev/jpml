package org.kl.shape;

import org.kl.meta.Extract;
import org.kl.type.IntRef;

public class Rectangle extends Figure {
    private int width;
    private int height;

    private static int staticWidth  = 5;
    private static int staticHeight = 10;

    public Rectangle() {
        this.width  = 5;
        this.height = 10;
    }

    public Rectangle(int width, int height) {
        this.width  = width;
        this.height = height;
    }

    @Override
    public int square() {
        return width * height;
    }

    @Extract
    public void deconstruct(IntRef width, IntRef height) {
        width.set(this.width);
        height.set(this.height);
    }

    @Extract
    public static void unapply(IntRef width, IntRef height) {
        width.set(staticWidth);
        height.set(staticHeight);
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
}

