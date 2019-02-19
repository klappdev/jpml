package org.kl.shape;

import org.kl.attr.Extract;
import org.kl.ref.IntRef;

public class Rectangle extends Figure {
    private int width  = 5;
    private int height = 10;

    @Override
    public int square() {
        return width * height;
    }

    @Extract
    public void deconstruct(IntRef width, IntRef height) {
        width.set(this.width);
        height.set(this.height);
    }
}

