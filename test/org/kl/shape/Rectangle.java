package org.kl.shape;

import org.kl.meta.Extract;
import org.kl.type.IntRef;

public class Rectangle extends Figure {
    private int width;
    private int height;

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

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

