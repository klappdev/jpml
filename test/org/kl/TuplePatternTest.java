package org.kl;

import org.junit.jupiter.api.Test;
import org.kl.error.PatternException;
import org.kl.state.Side;

import static java.lang.System.*;
import static org.kl.handle.TuplePattern.matches;

public class TuplePatternTest {

    @Test
    public void matchesStatementTest() throws PatternException {
        /* 1 */
        byte width1 = 10;
        String side1 = "top";

        matches(width1, side1,
                (byte) 10, "top", () -> out.println("side[" + side1 + "]=" + width1)
        );

        Side corner1 = Side.TOP;
        matches(width1, side1, corner1,
                (byte) 10, "top", Side.TOP, () -> out.println("side[" + side1 + "]=" + width1 + " # " + corner1)
        );

        boolean flag1 = true;
        matches(width1, side1, corner1, flag1,
                (byte) 10, "top", Side.TOP, true, () -> out.println("side[" + side1 + "]=" + width1 +
                                                                    " # " + corner1 + " # " + flag1)
        );

        /* 2 */
        short width2 = 15;
        String side2 = "bottom";

        matches(width2, side2,
                (short) 10, "top",    () -> out.println("side[" + side2 + "]=" + width2),
                (short) 15, "bottom", () -> out.println("side[" + side2 + "]=" + width2)
        );

        Side corner2 = Side.BOTTOM;

        matches(width2, side2, corner2,
                (short) 10, "top",    Side.TOP,    () -> out.println("side[" + side2 + "]=" + width2 + " # " + corner2),
                (short) 15, "bottom", Side.BOTTOM, () -> out.println("side[" + side2 + "]=" + width2 + " # " + corner2)
        );

        boolean flag2 = false;
        matches(width2, side2, corner2, flag2,
                (short) 10, "top",    Side.TOP,    true,  () -> out.println("side[" + side2 + "]=" + width2 +
                                                                            " # " + corner2 + " # " + flag2),
                (short) 15, "bottom", Side.BOTTOM, false, () -> out.println("side[" + side2 + "]=" + width2 +
                                                                            " # " + corner2 + " # " + flag2)
        );

        /* 3 */
        int width3 = 20;
        String side3 = "left";

        matches(width3, side3,
                10, "top",    () -> out.println("side[" + side3 + "]=" + width3),
                15, "bottom", () -> out.println("side[" + side3 + "]=" + width3),
                20, "left",   () -> out.println("side[" + side3 + "]=" + width3)
        );

        Side corner3 = Side.LEFT;

        matches(width3, side3, corner3,
                10, "top",    Side.TOP,    () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3),
                15, "bottom", Side.BOTTOM, () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3),
                20, "left",   Side.LEFT,   () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3)
        );

        boolean flag3 = true;
        matches(width3, side3, corner3, flag3,
                10, "top",    Side.TOP,    true,  () -> out.println("side[" + side3 + "]=" + width3 +
                                                                    " # " + corner3 + " # " + flag3),
                15, "bottom", Side.BOTTOM, false, () -> out.println("side[" + side3 + "]=" + width3 +
                                                                    " # " + corner3 + " # " + flag3),
                20, "left",   Side.LEFT,   true,  () -> out.println("side[" + side3 + "]=" + width3 +
                                                                    " # " + corner3 + " # " + flag3)
        );

        /* 4 */
        long width4 = 25;
        String side4 = "right";

        matches(width4, side4,
                10L, "top",    () -> out.println("side[" + side4 + "]=" + width4),
                15L, "bottom", () -> out.println("side[" + side4 + "]=" + width4),
                20L, "left",   () -> out.println("side[" + side4 + "]=" + width4),
                25L, "right",  () -> out.println("side[" + side4 + "]=" + width4)
        );

        Side corner4 = Side.RIGHT;

        matches(width4, side4, corner4,
                10L, "top",    Side.TOP,    () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                15L, "bottom", Side.BOTTOM, () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                20L, "left",   Side.LEFT,   () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                25L, "right",  Side.RIGHT,  () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4)
        );

        boolean flag4 = false;
        matches(width4, side4, corner4, flag4,
                10L, "top",    Side.TOP,    true,  () -> out.println("side[" + side4 + "]=" + width4 +
                                                                    " # " + corner4 + " # " + flag4),
                15L, "bottom", Side.BOTTOM, false, () -> out.println("side[" + side4 + "]=" + width4 +
                                                                    " # " + corner4 + " # " + flag4),
                20L, "left",   Side.LEFT,   true,  () -> out.println("side[" + side4 + "]=" + width4 +
                                                                    " # " + corner4 + " # " + flag4),
                25L, "right",  Side.RIGHT,  false, () -> out.println("side[" + side4 + "]=" + width4 +
                                                                     " # " + corner4 + " # " + flag4)
        );

        /* 5 */
        float width5 = 30.0F;
        String side5 = "top-left";

        matches(width5, side5,
                10.0F, "top",    () -> out.println("side[" + side5 + "]=" + width5),
                15.0F, "bottom", () -> out.println("side[" + side5 + "]=" + width5),
                20.0F, "left",   () -> out.println("side[" + side5 + "]=" + width5),
                25.0F, "right",  () -> out.println("side[" + side5 + "]=" + width5),
                30.0F, "top-left", () -> out.println("side[" + side5 + "]=" + width5)
        );

        Side corner5 = Side.TOP_LEFT;

        matches(width5, side5, corner5,
                10.0F, "top",    Side.TOP,    () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                15.0F, "bottom", Side.BOTTOM, () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                20.0F, "left",   Side.LEFT,   () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                25.0F, "right",  Side.RIGHT,  () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                30.0F, "top-left",  Side.TOP_LEFT,  () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5)
        );

        boolean flag5 = true;
        matches(width5, side5, corner5, flag5,
                10F, "top",    Side.TOP,    true,  () -> out.println("side[" + side5 + "]=" + width5 +
                                                                     " # " + corner5 + " # " + flag5),
                15F, "bottom", Side.BOTTOM, false, () -> out.println("side[" + side5 + "]=" + width5 +
                                                                     " # " + corner5 + " # " + flag5),
                20F, "left",   Side.LEFT,   true,  () -> out.println("side[" + side5 + "]=" + width5 +
                                                                     " # " + corner5 + " # " + flag5),
                25F, "right",  Side.RIGHT,  false, () -> out.println("side[" + side5 + "]=" + width5 +
                                                                     " # " + corner5 + " # " + flag5),
                30F, "top-left",  Side.TOP_LEFT,  true, () -> out.println("side[" + side5 + "]=" + width5 +
                                                                     " # " + corner5 + " # " + flag5)
        );

        /* 6 */
        double width6 = 35.0D;
        String side6 = "top-right";

        matches(width6, side6,
                10.0D, "top",    () -> out.println("side[" + side6 + "]=" + width6),
                15.0D, "bottom", () -> out.println("side[" + side6 + "]=" + width6),
                20.0D, "left",   () -> out.println("side[" + side6 + "]=" + width6),
                25.0D, "right",  () -> out.println("side[" + side6 + "]=" + width6),
                30.0D, "top-left",  () -> out.println("side[" + side6 + "]=" + width6),
                35.0D, "top-right", () -> out.println("side[" + side6 + "]=" + width6)
        );

        Side corner6 = Side.TOP_RIGHT;

        matches(width6, side6, corner6,
                10.0D, "top",    Side.TOP,    () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                15.0D, "bottom", Side.BOTTOM, () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                20.0D, "left",   Side.LEFT,   () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                25.0D, "right",  Side.RIGHT,  () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                30.0D, "top-left",  Side.TOP_LEFT, () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                35.0D, "top-right", Side.TOP_RIGHT,() -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6)
        );

        boolean flag6 = false;
        matches(width6, side6, corner6, flag6,
                10D, "top",    Side.TOP,    true,  () -> out.println("side[" + side6 + "]=" + width6 +
                                                                     " # " + corner6 + " # " + flag6),
                15D, "bottom", Side.BOTTOM, false, () -> out.println("side[" + side6 + "]=" + width6 +
                                                                     " # " + corner6 + " # " + flag6),
                20D, "left",   Side.LEFT,   true,  () -> out.println("side[" + side6 + "]=" + width6 +
                                                                     " # " + corner6 + " # " + flag6),
                25D, "right",  Side.RIGHT,  false, () -> out.println("side[" + side6 + "]=" + width6 +
                                                                     " # " + corner6 + " # " + flag6),
                30D, "top-left",  Side.TOP_LEFT,  true, () -> out.println("side[" + side6 + "]=" + width6 +
                                                                     " # " + corner6 + " # " + flag6),
                35D, "top-right", Side.TOP_RIGHT, true, () -> out.println("side[" + side6 + "]=" + width6 +
                                                                    " # " + corner6 + " # " + flag6)
        );

    }
}
