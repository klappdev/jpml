package org.kl.shape;

import org.kl.attr.Extract;
import org.kl.ref.FloatRef;

public class Tripiped extends Figure {
    private float width  = 5;
    private float longitude = 10;
    private float height = 15;

    @Override
    public int square() {
        return (int) (width * height * longitude);
    }

    @Extract
    public void deconstruct(FloatRef width, FloatRef longitude, FloatRef height) {
        width.set(this.width);
        longitude.set(this.longitude);
        height.set(this.height);
    }
}
