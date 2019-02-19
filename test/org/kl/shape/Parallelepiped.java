package org.kl.shape;

import org.kl.attr.Extract;
import org.kl.ref.ShortRef;

public class Parallelepiped extends Figure {
    private short width  = 5;
    private short longitude = 5;
    private short height = 10;

    @Override
    public int square() {
        return width * height * longitude;
    }

    @Extract
    public void deconstruct(ShortRef width, ShortRef longitude, ShortRef height) {
        width.set(this.width);
        longitude.set(this.longitude);
        height.set(this.height);
    }
}
