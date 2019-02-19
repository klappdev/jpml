package org.kl.shape;

import org.kl.attr.Extract;
import org.kl.ref.DoubleRef;

public class Triangle extends Figure {
    private double width  = 5;
    private double height = 10;

    @Override
    public int square() {
        return (int) (width * height);
    }

    @Extract
    public void deconstruct(DoubleRef width, DoubleRef height) {
        width.set(this.width);
        height.set(this.height);
    }
}
