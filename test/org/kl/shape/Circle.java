package org.kl.shape;

import org.kl.meta.Exclude;
import org.kl.meta.Extract;
import org.kl.type.IntRef;

import java.util.Objects;

public class Circle extends Figure {
    private int radius;

    @Exclude
    private int temp;

    public Circle() {
        this.radius = 5;
    }

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public int square() {
        return (int) (2 * Math.PI * radius);
    }

    @Extract
    public void deconstruct(IntRef radius) {
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

        Circle circle = (Circle) other;

        return radius == circle.radius &&
               temp == circle.temp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius, temp);
    }

    @Override
    public String toString() {
        return "Circle{radius=" + radius + "}";

    }
}
