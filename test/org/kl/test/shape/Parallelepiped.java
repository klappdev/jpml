package org.kl.test.shape;

import org.kl.meta.Exclude;
import org.kl.meta.Extract;
import org.kl.type.ShortRef;

public class Parallelepiped extends Figure {
    private short width;
    private short longitude;
    private short height;

    @Exclude private int temp;
    @Exclude private static short staticWidth = 5;
    @Exclude private static short staticLongitude = 5;
    @Exclude private static short staticHeight = 10;

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

    @Extract
    public static void unapply(ShortRef width, ShortRef longitude, ShortRef height) {
        width.set(staticWidth);
        longitude.set(staticLongitude);
        height.set(staticHeight);
    }

    public short width() {
        return width;
    }

    public short longitude() {
        return longitude;
    }

    public short height() {
        return height;
    }
}