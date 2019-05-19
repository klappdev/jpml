package org.kl.shape;

import org.kl.meta.Extract;
import org.kl.type.ShortRef;

public class Bipiped extends Figure {
    private short width  = 15;
    private short height = 10;

    public Bipiped() {
        this.width = 15;
        this.height = 10;
    }

    public Bipiped(short width, short height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int square() {
        return (int) (width * height);
    }

    @Extract
    public void deconstruct(ShortRef width, ShortRef height) {
        width.set(this.width);
        height.set(this.height);
    }

    public short width() {
        return width;
    }

    public short height() {
        return height;
    }
}
