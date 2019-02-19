package org.kl.shape;

import org.kl.attr.Extract;
import org.kl.ref.IntRef;

public class Quadrate extends Figure {
    private int width = 10;

    @Override
    public int square() {
        return width * width;
    }

    @Extract
    public void deconstruct(IntRef width) {
        width.set(this.width);
    }
}
