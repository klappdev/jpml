package org.kl.shape;

import org.kl.meta.Extract;
import org.kl.type.DoubleRef;

import java.util.Objects;

public class Unpiped extends Figure {
    private double radius;

    public Unpiped() {
        this.radius = 20;
    }

    public Unpiped(double radius) {
        this.radius = radius;
    }

    @Override
    public int square() {
        return (int) (2 * Math.PI * radius);
    }

    @Extract
    public void deconstruct(DoubleRef radius) {
        radius.set(this.radius);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Unpiped unpiped = (Unpiped) other;

        return Double.compare(unpiped.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius);
    }

    @Override
    public String toString() {
        return "Unpiped{radius=" + radius + "}";
    }
}
