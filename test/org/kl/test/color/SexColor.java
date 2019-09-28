package org.kl.test.color;

import org.kl.meta.Sealed;

@Sealed
public abstract class SexColor {
    private SexColor() {}

    public static class Red extends SexColor {}
    public static class Blue extends SexColor {}
    public static class White extends SexColor {}
    public static class Black extends SexColor {}
    public static class Green extends SexColor {}
    public static class Yellow extends SexColor {}
}
