package org.kl.jpml.test.color;

import org.kl.jpml.meta.Sealed;

@Sealed
public abstract class TriColor {
    private TriColor() {}

    public static class Red extends TriColor {}
    public static class Blue extends TriColor {}
    public static class White extends TriColor {}
}
