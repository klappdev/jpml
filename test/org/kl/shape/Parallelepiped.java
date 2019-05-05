package org.kl.shape;

import org.kl.meta.Exclude;
import org.kl.meta.Extract;
import org.kl.type.ShortRef;

public class Parallelepiped extends Figure {
    private short width;
    private short longitude;
    private short height;

    @Exclude
    private int temp;

    public Parallelepiped() {
        this.width = 5;
        this.longitude = 5;
        this.height = 10;
    }

    public Parallelepiped(short width, short longitude, short height) {
        this.width = width;
        this.longitude = longitude;
        this.height = height;
    }

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
