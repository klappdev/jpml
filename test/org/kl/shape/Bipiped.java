package org.kl.shape;

import org.kl.attr.Extract;
import org.kl.ref.ShortRef;

public class Bipiped extends Figure {
    private short width  = 15;
    private short height = 10;

    @Override
    public int square() {
        return (int) (width * height);
    }

    @Extract
    public void deconstruct(ShortRef width, ShortRef height) {
        width.set(this.width);
        height.set(this.height);
    }
}
