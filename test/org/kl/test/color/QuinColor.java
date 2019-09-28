package org.kl.test.color;

import org.kl.meta.Sealed;

@Sealed
public abstract class QuinColor {
    private QuinColor() {}

    public static class Red extends QuinColor {}
    public static class Blue extends QuinColor {}
    public static class White extends QuinColor {}
    public static class Black extends QuinColor {}
    public static class Green extends QuinColor {}
}
