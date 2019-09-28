package org.kl.test.color;

import org.kl.meta.Sealed;

@Sealed
public abstract class BiColor {
    private BiColor() {}

    public static class Red extends BiColor {}
    public static class Blue extends BiColor {}
}
