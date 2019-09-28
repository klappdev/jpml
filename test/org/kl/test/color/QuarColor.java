package org.kl.test.color;

import org.kl.meta.Sealed;

@Sealed
public abstract class QuarColor {
    private QuarColor() {}

    public static class Red extends QuarColor {}
    public static class Blue extends QuarColor {}
    public static class White extends QuarColor {}
    public static class Black extends QuarColor {}
}
