package org.kl.test.color;

import org.kl.meta.Sealed;

@Sealed
public abstract class TriColor {
    private TriColor() {}

    public static class Red extends TriColor {}
    public static class Blue extends TriColor {}
    public static class White extends TriColor {}
}
