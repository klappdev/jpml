package org.kl.jpml.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.kl.jpml.state.Else;
import org.kl.jpml.test.state.Side;
import org.kl.jpml.state.Var;
import org.kl.jpml.util.Tuple;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;
import static org.kl.jpml.pattern.TuplePattern.let;
import static org.kl.jpml.pattern.TuplePattern.matches;
import static org.kl.jpml.pattern.TuplePattern.foreach;

public class TuplePatternTest {
    private static List<Tuple.Tuple2<Integer, String>> listBiValues;
    private static List<Tuple.Tuple3<Integer, String,Integer>> listTriValues;
    private static List<Tuple.Tuple4<Integer, String, Integer, Boolean>> listQuarValues;

    @BeforeAll
    public static void init() {
        listBiValues = new ArrayList<Tuple.Tuple2<Integer, String>>() {{
            add(Tuple.of(1, "Piter"));
            add(Tuple.of(2, "John"));
            add(Tuple.of(3, "Jesica"));
        }};

        listTriValues = new ArrayList<Tuple.Tuple3<Integer, String,Integer>>() {{
            add(Tuple.of(1, "Piter", 27));
            add(Tuple.of(2, "John", 13));
            add(Tuple.of(3, "Jesica", 42));
        }};

        listQuarValues = new ArrayList<Tuple.Tuple4<Integer, String, Integer, Boolean>>() {{
            add(Tuple.of(1, "Piter", 27, false));
            add(Tuple.of(2, "John", 13, true));
            add(Tuple.of(3, "Jesica", 42, true));
        }};
    }

    @AfterAll
    public static void destroy() {
        listBiValues.clear();
        listTriValues.clear();
        listQuarValues.clear();
    }

    @Test
    public void matchesStatementTest() {
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

        boolean flag6 = true;
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

    @Test
    public void matchesAsStatementTest()  {
        /* 1 */
        byte width1 = 10;
        String side1 = "top";

        matches(width1, side1).as(
                (byte) 10, "top", () -> out.println("side[" + side1 + "]=" + width1)
        );

        Side corner1 = Side.TOP;
        matches(width1, side1, corner1).as(
                (byte) 10, "top", Side.TOP, () -> out.println("side[" + side1 + "]=" + width1 + " # " + corner1)
        );

        boolean flag1 = true;
        matches(width1, side1, corner1, flag1).as(
                (byte) 10, "top", Side.TOP, true, () -> out.println("side[" + side1 + "]=" + width1 +
                                                        " # " + corner1 + " # " + flag1)
        );

        /* 2 */
        short width2 = 15;
        String side2 = "bottom";

        matches(width2, side2).as(
                (short) 10, "top",    () -> out.println("side[" + side2 + "]=" + width2),
                (short) 15, "bottom", () -> out.println("side[" + side2 + "]=" + width2)
        );

        Side corner2 = Side.BOTTOM;

        matches(width2, side2, corner2).as(
                (short) 10, "top",    Side.TOP,    () -> out.println("side[" + side2 + "]=" + width2 + " # " + corner2),
                (short) 15, "bottom", Side.BOTTOM, () -> out.println("side[" + side2 + "]=" + width2 + " # " + corner2)
        );

        boolean flag2 = false;
        matches(width2, side2, corner2, flag2).as(
                (short) 10, "top",    Side.TOP,    true,  () -> out.println("side[" + side2 + "]=" + width2 +
                                                                " # " + corner2 + " # " + flag2),
                (short) 15, "bottom", Side.BOTTOM, false, () -> out.println("side[" + side2 + "]=" + width2 +
                                                                " # " + corner2 + " # " + flag2)
        );

        /* 3 */
        int width3 = 20;
        String side3 = "left";

        matches(width3, side3).as(
                10, "top",    () -> out.println("side[" + side3 + "]=" + width3),
                15, "bottom", () -> out.println("side[" + side3 + "]=" + width3),
                20, "left",   () -> out.println("side[" + side3 + "]=" + width3)
        );

        Side corner3 = Side.LEFT;

        matches(width3, side3, corner3).as(
                10, "top",    Side.TOP,    () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3),
                15, "bottom", Side.BOTTOM, () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3),
                20, "left",   Side.LEFT,   () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3)
        );

        boolean flag3 = true;
        matches(width3, side3, corner3, flag3).as(
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

        matches(width4, side4).as(
                10L, "top",    () -> out.println("side[" + side4 + "]=" + width4),
                15L, "bottom", () -> out.println("side[" + side4 + "]=" + width4),
                20L, "left",   () -> out.println("side[" + side4 + "]=" + width4),
                25L, "right",  () -> out.println("side[" + side4 + "]=" + width4)
        );

        Side corner4 = Side.RIGHT;

        matches(width4, side4, corner4).as(
                10L, "top",    Side.TOP,    () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                15L, "bottom", Side.BOTTOM, () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                20L, "left",   Side.LEFT,   () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                25L, "right",  Side.RIGHT,  () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4)
        );

        boolean flag4 = false;
        matches(width4, side4, corner4, flag4).as(
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

        matches(width5, side5).as(
                10.0F, "top",    () -> out.println("side[" + side5 + "]=" + width5),
                15.0F, "bottom", () -> out.println("side[" + side5 + "]=" + width5),
                20.0F, "left",   () -> out.println("side[" + side5 + "]=" + width5),
                25.0F, "right",  () -> out.println("side[" + side5 + "]=" + width5),
                30.0F, "top-left", () -> out.println("side[" + side5 + "]=" + width5)
        );

        Side corner5 = Side.TOP_LEFT;

        matches(width5, side5, corner5).as(
                10.0F, "top",    Side.TOP,    () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                15.0F, "bottom", Side.BOTTOM, () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                20.0F, "left",   Side.LEFT,   () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                25.0F, "right",  Side.RIGHT,  () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                30.0F, "top-left",  Side.TOP_LEFT,  () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5)
        );

        boolean flag5 = true;
        matches(width5, side5, corner5, flag5).as(
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

        matches(width6, side6).as(
                10.0D, "top",    () -> out.println("side[" + side6 + "]=" + width6),
                15.0D, "bottom", () -> out.println("side[" + side6 + "]=" + width6),
                20.0D, "left",   () -> out.println("side[" + side6 + "]=" + width6),
                25.0D, "right",  () -> out.println("side[" + side6 + "]=" + width6),
                30.0D, "top-left",  () -> out.println("side[" + side6 + "]=" + width6),
                35.0D, "top-right", () -> out.println("side[" + side6 + "]=" + width6)
        );

        Side corner6 = Side.TOP_RIGHT;

        matches(width6, side6, corner6).as(
                10.0D, "top",    Side.TOP,    () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                15.0D, "bottom", Side.BOTTOM, () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                20.0D, "left",   Side.LEFT,   () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                25.0D, "right",  Side.RIGHT,  () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                30.0D, "top-left",  Side.TOP_LEFT, () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                35.0D, "top-right", Side.TOP_RIGHT,() -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6)
        );

        boolean flag6 = true;
        matches(width6, side6, corner6, flag6).as(
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

    @Test
    public void matchesSmartStatementTest() {
        /* 1 */
        byte width1 = 10;
        String side1 = "top";

        matches(width1, side1,
                Tuple.of((byte) 10, "top"), () -> out.println("side[" + side1 + "]=" + width1)
        );

        Side corner1 = Side.TOP;
        matches(width1, side1, corner1,
                Tuple.of((byte) 10, "top", Side.TOP), () -> out.println("side[" + side1 + "]=" + width1 + " # " + corner1)
        );

        boolean flag1 = true;
        matches(width1, side1, corner1, flag1,
                Tuple.of((byte) 10, "top", Side.TOP, true), () -> out.println("side[" + side1 + "]=" + width1 +
                                                                  " # " + corner1 + " # " + flag1)
        );

        /* 2 */
        short width2 = 15;
        String side2 = "bottom";

        matches(width2, side2,
                Tuple.of((short) 10, "top"),    () -> out.println("side[" + side2 + "]=" + width2),
                Tuple.of((short) 15, "bottom"), () -> out.println("side[" + side2 + "]=" + width2)
        );

        Side corner2 = Side.BOTTOM;

        matches(width2, side2, corner2,
                Tuple.of((short) 10, "top", Side.TOP),    () -> out.println("side[" + side2 + "]=" + width2 + " # " + corner2),
                Tuple.of((short) 15, "bottom", Side.BOTTOM), () -> out.println("side[" + side2 + "]=" + width2 + " # " + corner2)
        );

        boolean flag2 = true;
        matches(width2, side2, corner2, flag2,
                Tuple.of((short) 10, "top", Side.TOP, true),  () -> out.println("side[" + side2 + "]=" + width2 +
                                                              " # " + corner2 + " # " + flag2),
                Tuple.of((short) 15, "bottom", Side.BOTTOM, false), () -> out.println("side[" + side2 + "]=" + width2 +
                                                              " # " + corner2 + " # " + flag2)
        );

        /* 3 */
        int width3 = 20;
        String side3 = "left";

        matches(width3, side3,
                Tuple.of(10, "top"),    () -> out.println("side[" + side3 + "]=" + width3),
                Tuple.of(15, "bottom"), () -> out.println("side[" + side3 + "]=" + width3),
                Tuple.of(20, "left"),   () -> out.println("side[" + side3 + "]=" + width3)
        );

        Side corner3 = Side.LEFT;

        matches(width3, side3, corner3,
                Tuple.of(10, "top",    Side.TOP),    () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3),
                Tuple.of(15, "bottom", Side.BOTTOM), () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3),
                Tuple.of(20, "left",   Side.LEFT),   () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3)
        );

        boolean flag3 = true;
        matches(width3, side3, corner3, flag3,
                Tuple.of(10, "top",    Side.TOP,    true),  () -> out.println("side[" + side3 + "]=" + width3 +
                                                                  " # " + corner3 + " # " + flag3),
                Tuple.of(15, "bottom", Side.BOTTOM, false), () -> out.println("side[" + side3 + "]=" + width3 +
                                                                  " # " + corner3 + " # " + flag3),
                Tuple.of(20, "left",   Side.LEFT,   true),  () -> out.println("side[" + side3 + "]=" + width3 +
                                                                  " # " + corner3 + " # " + flag3)
        );

        /* 4 */
        long width4 = 25L;
        String side4 = "right";

        matches(width4, side4,
                Tuple.of(10L, "top"),    () -> out.println("side[" + side4 + "]=" + width4),
                Tuple.of(15L, "bottom"), () -> out.println("side[" + side4 + "]=" + width4),
                Tuple.of(20L, "left"),   () -> out.println("side[" + side4 + "]=" + width4),
                Tuple.of(25L, "right"),  () -> out.println("side[" + side4 + "]=" + width4)
        );

        Side corner4 = Side.RIGHT;

        matches(width4, side4, corner4,
                Tuple.of(10L, "top",    Side.TOP),    () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                Tuple.of(15L, "bottom", Side.BOTTOM), () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                Tuple.of(20L, "left",   Side.LEFT),   () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                Tuple.of(25L, "right",  Side.RIGHT),  () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4)
        );

        boolean flag4 = false;
        matches(width4, side4, corner4, flag4,
                Tuple.of(10L, "top",    Side.TOP,    true),  () -> out.println("side[" + side4 + "]=" + width4 +
                                                                   " # " + corner4 + " # " + flag4),
                Tuple.of(15L, "bottom", Side.BOTTOM, false), () -> out.println("side[" + side4 + "]=" + width4 +
                                                                   " # " + corner4 + " # " + flag4),
                Tuple.of(20L, "left",   Side.LEFT,   true),  () -> out.println("side[" + side4 + "]=" + width4 +
                                                                   " # " + corner4 + " # " + flag4),
                Tuple.of(25L, "right",  Side.RIGHT,  false), () -> out.println("side[" + side4 + "]=" + width4 +
                                                                   " # " + corner4 + " # " + flag4)
        );

        /* 5 */
        float width5 = 30.0F;
        String side5 = "top-left";

        matches(width5, side5,
                Tuple.of(10.0F, "top"),    () -> out.println("side[" + side5 + "]=" + width5),
                Tuple.of(15.0F, "bottom"), () -> out.println("side[" + side5 + "]=" + width5),
                Tuple.of(20.0F, "left"),   () -> out.println("side[" + side5 + "]=" + width5),
                Tuple.of(25.0F, "right"),  () -> out.println("side[" + side5 + "]=" + width5),
                Tuple.of(30.0F, "top-left"), () -> out.println("side[" + side5 + "]=" + width5)
        );

        Side corner5 = Side.TOP_LEFT;

        matches(width5, side5, corner5,
                Tuple.of(10.0F, "top",    Side.TOP),    () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                Tuple.of(15.0F, "bottom", Side.BOTTOM), () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                Tuple.of(20.0F, "left",   Side.LEFT),   () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                Tuple.of(25.0F, "right",  Side.RIGHT),  () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                Tuple.of(30.0F, "top-left",  Side.TOP_LEFT),  () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5)
        );

        boolean flag5 = true;
        matches(width5, side5, corner5, flag5,
                Tuple.of(10F, "top",    Side.TOP,    true),  () -> out.println("side[" + side5 + "]=" + width5 +
                                                                   " # " + corner5 + " # " + flag5),
                Tuple.of(15F, "bottom", Side.BOTTOM, false), () -> out.println("side[" + side5 + "]=" + width5 +
                                                                   " # " + corner5 + " # " + flag5),
                Tuple.of(20F, "left",   Side.LEFT,   true),  () -> out.println("side[" + side5 + "]=" + width5 +
                                                                   " # " + corner5 + " # " + flag5),
                Tuple.of(25F, "right",  Side.RIGHT,  false), () -> out.println("side[" + side5 + "]=" + width5 +
                                                                   " # " + corner5 + " # " + flag5),
                Tuple.of(30F, "top-left",  Side.TOP_LEFT,  true), () -> out.println("side[" + side5 + "]=" + width5 +
                                                                   " # " + corner5 + " # " + flag5)
        );

        /* 6 */
        double width6 = 35.0D;
        String side6 = "top-right";

        matches(width6, side6,
                Tuple.of(10.0D, "top"),    () -> out.println("side[" + side6 + "]=" + width6),
                Tuple.of(15.0D, "bottom"), () -> out.println("side[" + side6 + "]=" + width6),
                Tuple.of(20.0D, "left"),   () -> out.println("side[" + side6 + "]=" + width6),
                Tuple.of(25.0D, "right"),  () -> out.println("side[" + side6 + "]=" + width6),
                Tuple.of(30.0D, "top-left"),  () -> out.println("side[" + side6 + "]=" + width6),
                Tuple.of(35.0D, "top-right"), () -> out.println("side[" + side6 + "]=" + width6)
        );

        Side corner6 = Side.TOP_RIGHT;

        matches(width6, side6, corner6,
                Tuple.of(10.0D, "top",    Side.TOP),    () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                Tuple.of(15.0D, "bottom", Side.BOTTOM), () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                Tuple.of(20.0D, "left",   Side.LEFT),   () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                Tuple.of(25.0D, "right",  Side.RIGHT),  () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                Tuple.of(30.0D, "top-left",  Side.TOP_LEFT), () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                Tuple.of(35.0D, "top-right", Side.TOP_RIGHT),() -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6)
        );

        boolean flag6 = true;
        matches(width6, side6, corner6, flag6,
                Tuple.of(10D, "top",    Side.TOP,    true),  () -> out.println("side[" + side6 + "]=" + width6 +
                                                                   " # " + corner6 + " # " + flag6),
                Tuple.of(15D, "bottom", Side.BOTTOM, false), () -> out.println("side[" + side6 + "]=" + width6 +
                                                                   " # " + corner6 + " # " + flag6),
                Tuple.of(20D, "left",   Side.LEFT,   true),  () -> out.println("side[" + side6 + "]=" + width6 +
                                                                   " # " + corner6 + " # " + flag6),
                Tuple.of(25D, "right",  Side.RIGHT,  false), () -> out.println("side[" + side6 + "]=" + width6 +
                                                                   " # " + corner6 + " # " + flag6),
                Tuple.of(30D, "top-left",  Side.TOP_LEFT,  true), () -> out.println("side[" + side6 + "]=" + width6 +
                                                                   " # " + corner6 + " # " + flag6),
                Tuple.of(35D, "top-right", Side.TOP_RIGHT, true), () -> out.println("side[" + side6 + "]=" + width6 +
                                                                   " # " + corner6 + " # " + flag6)
        );
    }

    @Test
    public void matchesStatementWithDefaultTest() {
        /* 1 */
        byte width1 = 15;
        String side1 = "bottom";

        matches(width1, side1,
                (byte) 10, "top", () -> out.println("side[" + side1 + "]=" + width1),
                Else.class,       () -> out.println("side[none]=" + 0)
        );

        Side corner1 = Side.BOTTOM;
        matches(width1, side1, corner1,
                (byte) 10, "top", Side.TOP, () -> out.println("side[" + side1 + "]=" + width1 + " # " + corner1),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE)
        );

        boolean flag1 = false;
        matches(width1, side1, corner1, flag1,
                (byte) 10, "top", Side.TOP, true, () -> out.println("side[" + side1 + "]=" + width1 +
                                                                    " # " + corner1 + " # " + flag1),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE + " # " + 0)
        );

        /* 2 */
        short width2 = 20;
        String side2 = "top";

        matches(width2, side2,
                (short) 10, "top",    () -> out.println("side[" + side2 + "]=" + width2),
                (short) 15, "bottom", () -> out.println("side[" + side2 + "]=" + width2),
                Else.class,           () -> out.println("side[none]=" + 0)
        );

        Side corner2 = Side.TOP;

        matches(width2, side2, corner2,
                (short) 10, "top",    Side.TOP,    () -> out.println("side[" + side2 + "]=" + width2 + " # " + corner2),
                (short) 15, "bottom", Side.BOTTOM, () -> out.println("side[" + side2 + "]=" + width2 + " # " + corner2),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE)
        );

        boolean flag2 = false;
        matches(width2, side2, corner2, flag2,
                (short) 10, "top",    Side.TOP,    true,  () -> out.println("side[" + side2 + "]=" + width2 +
                                                                            " # " + corner2 + " # " + flag2),
                (short) 15, "bottom", Side.BOTTOM, false, () -> out.println("side[" + side2 + "]=" + width2 +
                                                                            " # " + corner2 + " # " + flag2),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE + " # " + 0)
        );

        /* 3 */
        int width3 = 25;
        String side3 = "right";

        matches(width3, side3,
                10, "top",    () -> out.println("side[" + side3 + "]=" + width3),
                15, "bottom", () -> out.println("side[" + side3 + "]=" + width3),
                20, "left",   () -> out.println("side[" + side3 + "]=" + width3),
                Else.class,   () -> out.println("side[none]=" + 0)
        );

        Side corner3 = Side.RIGHT;

        matches(width3, side3, corner3,
                10, "top",    Side.TOP,    () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3),
                15, "bottom", Side.BOTTOM, () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3),
                20, "left",   Side.LEFT,   () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE)
        );

        boolean flag3 = true;
        matches(width3, side3, corner3, flag3,
                10, "top",    Side.TOP,    true,  () -> out.println("side[" + side3 + "]=" + width3 +
                                                                    " # " + corner3 + " # " + flag3),
                15, "bottom", Side.BOTTOM, false, () -> out.println("side[" + side3 + "]=" + width3 +
                                                                    " # " + corner3 + " # " + flag3),
                20, "left",   Side.LEFT,   true,  () -> out.println("side[" + side3 + "]=" + width3 +
                                                                    " # " + corner3 + " # " + flag3),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE + " # " + 0)
        );

        /* 4 */
        long width4 = 30;
        String side4 = "left";

        matches(width4, side4,
                10L, "top",    () -> out.println("side[" + side4 + "]=" + width4),
                15L, "bottom", () -> out.println("side[" + side4 + "]=" + width4),
                20L, "left",   () -> out.println("side[" + side4 + "]=" + width4),
                25L, "right",  () -> out.println("side[" + side4 + "]=" + width4),
                Else.class,    () -> out.println("side[none]=" + 0)
        );

        Side corner4 = Side.LEFT;

        matches(width4, side4, corner4,
                10L, "top",    Side.TOP,    () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                15L, "bottom", Side.BOTTOM, () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                20L, "left",   Side.LEFT,   () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                25L, "right",  Side.RIGHT,  () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE)
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
                                                                     " # " + corner4 + " # " + flag4),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE + " # " + 0)
        );

        /* 5 */
        float width5 = 35.0F;
        String side5 = "top-left";

        matches(width5, side5,
                10.0F, "top",    () -> out.println("side[" + side5 + "]=" + width5),
                15.0F, "bottom", () -> out.println("side[" + side5 + "]=" + width5),
                20.0F, "left",   () -> out.println("side[" + side5 + "]=" + width5),
                25.0F, "right",  () -> out.println("side[" + side5 + "]=" + width5),
                30.0F, "top-left", () -> out.println("side[" + side5 + "]=" + width5),
                Else.class,      () -> out.println("side[none]=" + 0)
        );

        Side corner5 = Side.TOP_LEFT;

        matches(width5, side5, corner5,
                10.0F, "top",    Side.TOP,    () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                15.0F, "bottom", Side.BOTTOM, () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                20.0F, "left",   Side.LEFT,   () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                25.0F, "right",  Side.RIGHT,  () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                30.0F, "top-left",  Side.TOP_LEFT,  () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE)
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
                                                                     " # " + corner5 + " # " + flag5),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE + " # " + 0)
        );

        /* 6 */
        double width6 = 40.0D;
        String side6 = "top-right";

        matches(width6, side6,
                10.0D, "top",    () -> out.println("side[" + side6 + "]=" + width6),
                15.0D, "bottom", () -> out.println("side[" + side6 + "]=" + width6),
                20.0D, "left",   () -> out.println("side[" + side6 + "]=" + width6),
                25.0D, "right",  () -> out.println("side[" + side6 + "]=" + width6),
                30.0D, "top-left",  () -> out.println("side[" + side6 + "]=" + width6),
                35.0D, "top-right", () -> out.println("side[" + side6 + "]=" + width6),
                Else.class,      () -> out.println("side[none]=" + 0)
        );

        Side corner6 = Side.TOP_RIGHT;

        matches(width6, side6, corner6,
                10.0D, "top",    Side.TOP,    () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                15.0D, "bottom", Side.BOTTOM, () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                20.0D, "left",   Side.LEFT,   () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                25.0D, "right",  Side.RIGHT,  () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                30.0D, "top-left",  Side.TOP_LEFT, () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                35.0D, "top-right", Side.TOP_RIGHT,() -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE)
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
                                                                          " # " + corner6 + " # " + flag6),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE + " # " + 0)
        );
    }

    @Test
    public void matchesAsStatementWithDefaultTest() {
        /* 1 */
        byte width1 = 15;
        String side1 = "bottom";

        matches(width1, side1).as(
                (byte) 10, "top", () -> out.println("side[" + side1 + "]=" + width1),
                Else.class,       () -> out.println("side[none]=" + 0)
        );

        Side corner1 = Side.BOTTOM;
        matches(width1, side1, corner1).as(
                (byte) 10, "top", Side.TOP, () -> out.println("side[" + side1 + "]=" + width1 + " # " + corner1),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE)
        );

        boolean flag1 = false;
        matches(width1, side1, corner1, flag1).as(
                (byte) 10, "top", Side.TOP, true, () -> out.println("side[" + side1 + "]=" + width1 +
                                                        " # " + corner1 + " # " + flag1),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE + " # " + 0)
        );

        /* 2 */
        short width2 = 20;
        String side2 = "top";

        matches(width2, side2).as(
                (short) 10, "top",    () -> out.println("side[" + side2 + "]=" + width2),
                (short) 15, "bottom", () -> out.println("side[" + side2 + "]=" + width2),
                Else.class,           () -> out.println("side[none]=" + 0)
        );

        Side corner2 = Side.TOP;

        matches(width2, side2, corner2).as(
                (short) 10, "top",    Side.TOP,    () -> out.println("side[" + side2 + "]=" + width2 + " # " + corner2),
                (short) 15, "bottom", Side.BOTTOM, () -> out.println("side[" + side2 + "]=" + width2 + " # " + corner2),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE)
        );

        boolean flag2 = false;
        matches(width2, side2, corner2, flag2).as(
                (short) 10, "top",    Side.TOP,    true,  () -> out.println("side[" + side2 + "]=" + width2 +
                                                                " # " + corner2 + " # " + flag2),
                (short) 15, "bottom", Side.BOTTOM, false, () -> out.println("side[" + side2 + "]=" + width2 +
                                                                " # " + corner2 + " # " + flag2),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE + " # " + 0)
        );

        /* 3 */
        int width3 = 25;
        String side3 = "right";

        matches(width3, side3).as(
                10, "top",    () -> out.println("side[" + side3 + "]=" + width3),
                15, "bottom", () -> out.println("side[" + side3 + "]=" + width3),
                20, "left",   () -> out.println("side[" + side3 + "]=" + width3),
                Else.class,   () -> out.println("side[none]=" + 0)
        );

        Side corner3 = Side.RIGHT;

        matches(width3, side3, corner3).as(
                10, "top",    Side.TOP,    () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3),
                15, "bottom", Side.BOTTOM, () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3),
                20, "left",   Side.LEFT,   () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE)
        );

        boolean flag3 = true;
        matches(width3, side3, corner3, flag3).as(
                10, "top",    Side.TOP,    true,  () -> out.println("side[" + side3 + "]=" + width3 +
                                                        " # " + corner3 + " # " + flag3),
                15, "bottom", Side.BOTTOM, false, () -> out.println("side[" + side3 + "]=" + width3 +
                                                        " # " + corner3 + " # " + flag3),
                20, "left",   Side.LEFT,   true,  () -> out.println("side[" + side3 + "]=" + width3 +
                                                        " # " + corner3 + " # " + flag3),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE + " # " + 0)
        );

        /* 4 */
        long width4 = 30;
        String side4 = "left";

        matches(width4, side4).as(
                10L, "top",    () -> out.println("side[" + side4 + "]=" + width4),
                15L, "bottom", () -> out.println("side[" + side4 + "]=" + width4),
                20L, "left",   () -> out.println("side[" + side4 + "]=" + width4),
                25L, "right",  () -> out.println("side[" + side4 + "]=" + width4),
                Else.class,    () -> out.println("side[none]=" + 0)
        );

        Side corner4 = Side.LEFT;

        matches(width4, side4, corner4).as(
                10L, "top",    Side.TOP,    () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                15L, "bottom", Side.BOTTOM, () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                20L, "left",   Side.LEFT,   () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                25L, "right",  Side.RIGHT,  () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE)
        );

        boolean flag4 = false;
        matches(width4, side4, corner4, flag4).as(
                10L, "top",    Side.TOP,    true,  () -> out.println("side[" + side4 + "]=" + width4 +
                                                         " # " + corner4 + " # " + flag4),
                15L, "bottom", Side.BOTTOM, false, () -> out.println("side[" + side4 + "]=" + width4 +
                                                         " # " + corner4 + " # " + flag4),
                20L, "left",   Side.LEFT,   true,  () -> out.println("side[" + side4 + "]=" + width4 +
                                                         " # " + corner4 + " # " + flag4),
                25L, "right",  Side.RIGHT,  false, () -> out.println("side[" + side4 + "]=" + width4 +
                                                         " # " + corner4 + " # " + flag4),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE + " # " + 0)
        );

        /* 5 */
        float width5 = 35.0F;
        String side5 = "top-left";

        matches(width5, side5).as(
                10.0F, "top",    () -> out.println("side[" + side5 + "]=" + width5),
                15.0F, "bottom", () -> out.println("side[" + side5 + "]=" + width5),
                20.0F, "left",   () -> out.println("side[" + side5 + "]=" + width5),
                25.0F, "right",  () -> out.println("side[" + side5 + "]=" + width5),
                30.0F, "top-left", () -> out.println("side[" + side5 + "]=" + width5),
                Else.class,      () -> out.println("side[none]=" + 0)
        );

        Side corner5 = Side.TOP_LEFT;

        matches(width5, side5, corner5).as(
                10.0F, "top",    Side.TOP,    () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                15.0F, "bottom", Side.BOTTOM, () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                20.0F, "left",   Side.LEFT,   () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                25.0F, "right",  Side.RIGHT,  () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                30.0F, "top-left",  Side.TOP_LEFT,  () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE)
        );

        boolean flag5 = true;
        matches(width5, side5, corner5, flag5).as(
                10F, "top",    Side.TOP,    true,  () -> out.println("side[" + side5 + "]=" + width5 +
                                                         " # " + corner5 + " # " + flag5),
                15F, "bottom", Side.BOTTOM, false, () -> out.println("side[" + side5 + "]=" + width5 +
                                                         " # " + corner5 + " # " + flag5),
                20F, "left",   Side.LEFT,   true,  () -> out.println("side[" + side5 + "]=" + width5 +
                                                         " # " + corner5 + " # " + flag5),
                25F, "right",  Side.RIGHT,  false, () -> out.println("side[" + side5 + "]=" + width5 +
                                                         " # " + corner5 + " # " + flag5),
                30F, "top-left",  Side.TOP_LEFT,  true, () -> out.println("side[" + side5 + "]=" + width5 +
                                                         " # " + corner5 + " # " + flag5),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE + " # " + 0)
        );

        /* 6 */
        double width6 = 40.0D;
        String side6 = "top-right";

        matches(width6, side6).as(
                10.0D, "top",    () -> out.println("side[" + side6 + "]=" + width6),
                15.0D, "bottom", () -> out.println("side[" + side6 + "]=" + width6),
                20.0D, "left",   () -> out.println("side[" + side6 + "]=" + width6),
                25.0D, "right",  () -> out.println("side[" + side6 + "]=" + width6),
                30.0D, "top-left",  () -> out.println("side[" + side6 + "]=" + width6),
                35.0D, "top-right", () -> out.println("side[" + side6 + "]=" + width6),
                Else.class,      () -> out.println("side[none]=" + 0)
        );

        Side corner6 = Side.TOP_RIGHT;

        matches(width6, side6, corner6).as(
                10.0D, "top",    Side.TOP,    () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                15.0D, "bottom", Side.BOTTOM, () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                20.0D, "left",   Side.LEFT,   () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                25.0D, "right",  Side.RIGHT,  () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                30.0D, "top-left",  Side.TOP_LEFT, () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                35.0D, "top-right", Side.TOP_RIGHT,() -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE)
        );

        boolean flag6 = false;
        matches(width6, side6, corner6, flag6).as(
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
                                                         " # " + corner6 + " # " + flag6),
                Else.class, () -> out.println("side[none]=" + 0 + " # " + Side.NONE + " # " + 0)
        );
    }

    @Test
    public void matchesStatementWithVarTest() {
        /* 1 */
        byte width1 = 15;
        String side1 = "bottom";

        matches(width1, side1,
                (byte) 10, "top", () -> out.println("side[" + side1 + "]=" + width1),
                Var.class, (w, s) -> out.println("side[none]=" + w)
        );

        Side corner1 = Side.BOTTOM;
        matches(width1, side1, corner1,
                (byte) 10, "top", Side.TOP, () -> out.println("side[" + side1 + "]=" + width1 + " # " + corner1),
                Var.class, (w, s, c) -> out.println("side[none]=" + w + " # " + s)
        );

        boolean flag1 = false;
        matches(width1, side1, corner1, flag1,
                (byte) 10, "top", Side.TOP, true, () -> out.println("side[" + side1 + "]=" + width1 +
                                                        " # " + corner1 + " # " + flag1),
                Var.class, (w, s, c, f) -> out.println("side[none]=" + w + " # " + s + " # " + c)
        );

        /* 2 */
        short width2 = 20;
        String side2 = "top";

        matches(width2, side2,
                (short) 10, "top",    () -> out.println("side[" + side2 + "]=" + width2),
                (short) 15, "bottom", () -> out.println("side[" + side2 + "]=" + width2),
                Var.class, (w, s) -> out.println("side[none]=" + w)
        );

        Side corner2 = Side.TOP;

        matches(width2, side2, corner2,
                (short) 10, "top",    Side.TOP,    () -> out.println("side[" + side2 + "]=" + width2 + " # " + corner2),
                (short) 15, "bottom", Side.BOTTOM, () -> out.println("side[" + side2 + "]=" + width2 + " # " + corner2),
                Var.class, (w, s, c) -> out.println("side[none]=" + w + " # " + s)
        );

        boolean flag2 = false;
        matches(width2, side2, corner2, flag2,
                (short) 10, "top",    Side.TOP,    true,  () -> out.println("side[" + side2 + "]=" + width2 +
                                                                " # " + corner2 + " # " + flag2),
                (short) 15, "bottom", Side.BOTTOM, false, () -> out.println("side[" + side2 + "]=" + width2 +
                                                                " # " + corner2 + " # " + flag2),
                Var.class, (w, s, c, f) -> out.println("side[none]=" + w + " # " + s + " # " + c)
        );

        /* 3 */
        int width3 = 25;
        String side3 = "right";

        matches(width3, side3,
                10, "top",    () -> out.println("side[" + side3 + "]=" + width3),
                15, "bottom", () -> out.println("side[" + side3 + "]=" + width3),
                20, "left",   () -> out.println("side[" + side3 + "]=" + width3),
                Var.class, (w, s) -> out.println("side[none]=" + w)
        );

        Side corner3 = Side.RIGHT;

        matches(width3, side3, corner3,
                10, "top",    Side.TOP,    () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3),
                15, "bottom", Side.BOTTOM, () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3),
                20, "left",   Side.LEFT,   () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3),
                Var.class, (w, s, c) -> out.println("side[none]=" + w + " # " + s)
        );

        boolean flag3 = true;
        matches(width3, side3, corner3, flag3,
                10, "top",    Side.TOP,    true,  () -> out.println("side[" + side3 + "]=" + width3 +
                                                        " # " + corner3 + " # " + flag3),
                15, "bottom", Side.BOTTOM, false, () -> out.println("side[" + side3 + "]=" + width3 +
                                                        " # " + corner3 + " # " + flag3),
                20, "left",   Side.LEFT,   true,  () -> out.println("side[" + side3 + "]=" + width3 +
                                                        " # " + corner3 + " # " + flag3),
                Var.class, (w, s, c, f) -> out.println("side[none]=" + w + " # " + s + " # " + c)
        );

        /* 4 */
        long width4 = 30;
        String side4 = "left";

        matches(width4, side4,
                10L, "top",    () -> out.println("side[" + side4 + "]=" + width4),
                15L, "bottom", () -> out.println("side[" + side4 + "]=" + width4),
                20L, "left",   () -> out.println("side[" + side4 + "]=" + width4),
                25L, "right",  () -> out.println("side[" + side4 + "]=" + width4),
                Var.class, (w, s) -> out.println("side[none]=" + w)
        );

        Side corner4 = Side.LEFT;

        matches(width4, side4, corner4,
                10L, "top",    Side.TOP,    () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                15L, "bottom", Side.BOTTOM, () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                20L, "left",   Side.LEFT,   () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                25L, "right",  Side.RIGHT,  () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                Var.class, (w, s, c) -> out.println("side[none]=" + w + " # " + s)
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
                                                         " # " + corner4 + " # " + flag4),
                Var.class, (w, s, c, f) -> out.println("side[none]=" + w + " # " + s + " # " + c)
        );

        /* 5 */
        float width5 = 35.0F;
        String side5 = "top-left";

        matches(width5, side5,
                10.0F, "top",    () -> out.println("side[" + side5 + "]=" + width5),
                15.0F, "bottom", () -> out.println("side[" + side5 + "]=" + width5),
                20.0F, "left",   () -> out.println("side[" + side5 + "]=" + width5),
                25.0F, "right",  () -> out.println("side[" + side5 + "]=" + width5),
                30.0F, "top-left", () -> out.println("side[" + side5 + "]=" + width5),
                Var.class, (w, s) -> out.println("side[none]=" + w)
        );

        Side corner5 = Side.TOP_LEFT;

        matches(width5, side5, corner5,
                10.0F, "top",    Side.TOP,    () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                15.0F, "bottom", Side.BOTTOM, () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                20.0F, "left",   Side.LEFT,   () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                25.0F, "right",  Side.RIGHT,  () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                30.0F, "top-left",  Side.TOP_LEFT,  () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                Var.class, (w, s, c) -> out.println("side[none]=" + w + " # " + s)
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
                                                        " # " + corner5 + " # " + flag5),
                Var.class, (w, s, c, f) -> out.println("side[none]=" + w + " # " + s + " # " + c)
        );

        /* 6 */
        double width6 = 40.0D;
        String side6 = "top-right";

        matches(width6, side6,
                10.0D, "top",    () -> out.println("side[" + side6 + "]=" + width6),
                15.0D, "bottom", () -> out.println("side[" + side6 + "]=" + width6),
                20.0D, "left",   () -> out.println("side[" + side6 + "]=" + width6),
                25.0D, "right",  () -> out.println("side[" + side6 + "]=" + width6),
                30.0D, "top-left",  () -> out.println("side[" + side6 + "]=" + width6),
                35.0D, "top-right", () -> out.println("side[" + side6 + "]=" + width6),
                Var.class, (w, s) -> out.println("side[none]=" + w)
        );

        Side corner6 = Side.TOP_RIGHT;

        matches(width6, side6, corner6,
                10.0D, "top",    Side.TOP,    () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                15.0D, "bottom", Side.BOTTOM, () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                20.0D, "left",   Side.LEFT,   () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                25.0D, "right",  Side.RIGHT,  () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                30.0D, "top-left",  Side.TOP_LEFT, () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                35.0D, "top-right", Side.TOP_RIGHT,() -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                Var.class, (w, s, c) -> out.println("side[none]=" + w + " # " + s)
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
                                                              " # " + corner6 + " # " + flag6),
                Var.class, (w, s, c, f) -> out.println("side[none]=" + w + " # " + s + " # " + c)
        );
    }

    @Test
    public void matchesAsStatementWithVarTest() {
        /* 1 */
        byte width1 = 15;
        String side1 = "bottom";

        matches(width1, side1).as(
                (byte) 10, "top", () -> out.println("side[" + side1 + "]=" + width1),
                Var.class, (w, s) -> out.println("side[none]=" + w)
        );

        Side corner1 = Side.BOTTOM;
        matches(width1, side1, corner1).as(
                (byte) 10, "top", Side.TOP, () -> out.println("side[" + side1 + "]=" + width1 + " # " + corner1),
                Var.class, (w, s, c) -> out.println("side[none]=" + w + " # " + s)
        );

        boolean flag1 = false;
        matches(width1, side1, corner1, flag1).as(
                (byte) 10, "top", Side.TOP, true, () -> out.println("side[" + side1 + "]=" + width1 +
                                                        " # " + corner1 + " # " + flag1),
                Var.class, (w, s, c, f) -> out.println("side[none]=" + w + " # " + s + " # " + c)
        );

        /* 2 */
        short width2 = 20;
        String side2 = "top";

        matches(width2, side2).as(
                (short) 10, "top",    () -> out.println("side[" + side2 + "]=" + width2),
                (short) 15, "bottom", () -> out.println("side[" + side2 + "]=" + width2),
                Var.class, (w, s) -> out.println("side[none]=" + w)
        );

        Side corner2 = Side.TOP;

        matches(width2, side2, corner2).as(
                (short) 10, "top",    Side.TOP,    () -> out.println("side[" + side2 + "]=" + width2 + " # " + corner2),
                (short) 15, "bottom", Side.BOTTOM, () -> out.println("side[" + side2 + "]=" + width2 + " # " + corner2),
                Var.class, (w, s, c) -> out.println("side[none]=" + w + " # " + s)
        );

        boolean flag2 = false;
        matches(width2, side2, corner2, flag2).as(
                (short) 10, "top",    Side.TOP,    true,  () -> out.println("side[" + side2 + "]=" + width2 +
                                                                " # " + corner2 + " # " + flag2),
                (short) 15, "bottom", Side.BOTTOM, false, () -> out.println("side[" + side2 + "]=" + width2 +
                                                                " # " + corner2 + " # " + flag2),
                Var.class, (w, s, c, f) -> out.println("side[none]=" + w + " # " + s + " # " + c)
        );

        /* 3 */
        int width3 = 25;
        String side3 = "right";

        matches(width3, side3).as(
                10, "top",    () -> out.println("side[" + side3 + "]=" + width3),
                15, "bottom", () -> out.println("side[" + side3 + "]=" + width3),
                20, "left",   () -> out.println("side[" + side3 + "]=" + width3),
                Var.class, (w, s) -> out.println("side[none]=" + w)
        );

        Side corner3 = Side.RIGHT;

        matches(width3, side3, corner3).as(
                10, "top",    Side.TOP,    () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3),
                15, "bottom", Side.BOTTOM, () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3),
                20, "left",   Side.LEFT,   () -> out.println("side[" + side3 + "]=" + width3 + " # " + corner3),
                Var.class, (w, s, c) -> out.println("side[none]=" + w + " # " + s)
        );

        boolean flag3 = true;
        matches(width3, side3, corner3, flag3).as(
                10, "top",    Side.TOP,    true,  () -> out.println("side[" + side3 + "]=" + width3 +
                                                        " # " + corner3 + " # " + flag3),
                15, "bottom", Side.BOTTOM, false, () -> out.println("side[" + side3 + "]=" + width3 +
                                                        " # " + corner3 + " # " + flag3),
                20, "left",   Side.LEFT,   true,  () -> out.println("side[" + side3 + "]=" + width3 +
                                                        " # " + corner3 + " # " + flag3),
                Var.class, (w, s, c, f) -> out.println("side[none]=" + w + " # " + s + " # " + c)
        );

        /* 4 */
        long width4 = 30;
        String side4 = "left";

        matches(width4, side4).as(
                10L, "top",    () -> out.println("side[" + side4 + "]=" + width4),
                15L, "bottom", () -> out.println("side[" + side4 + "]=" + width4),
                20L, "left",   () -> out.println("side[" + side4 + "]=" + width4),
                25L, "right",  () -> out.println("side[" + side4 + "]=" + width4),
                Var.class, (w, s) -> out.println("side[none]=" + w)
        );

        Side corner4 = Side.LEFT;

        matches(width4, side4, corner4).as(
                10L, "top",    Side.TOP,    () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                15L, "bottom", Side.BOTTOM, () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                20L, "left",   Side.LEFT,   () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                25L, "right",  Side.RIGHT,  () -> out.println("side[" + side4 + "]=" + width4 + " # " + corner4),
                Var.class, (w, s, c) -> out.println("side[none]=" + w + " # " + s)
        );

        boolean flag4 = false;
        matches(width4, side4, corner4, flag4).as(
                10L, "top",    Side.TOP,    true,  () -> out.println("side[" + side4 + "]=" + width4 +
                                                         " # " + corner4 + " # " + flag4),
                15L, "bottom", Side.BOTTOM, false, () -> out.println("side[" + side4 + "]=" + width4 +
                                                         " # " + corner4 + " # " + flag4),
                20L, "left",   Side.LEFT,   true,  () -> out.println("side[" + side4 + "]=" + width4 +
                                                         " # " + corner4 + " # " + flag4),
                25L, "right",  Side.RIGHT,  false, () -> out.println("side[" + side4 + "]=" + width4 +
                                                         " # " + corner4 + " # " + flag4),
                Var.class, (w, s, c, f) -> out.println("side[none]=" + w + " # " + s + " # " + c)
        );

        /* 5 */
        float width5 = 35.0F;
        String side5 = "top-left";

        matches(width5, side5).as(
                10.0F, "top",    () -> out.println("side[" + side5 + "]=" + width5),
                15.0F, "bottom", () -> out.println("side[" + side5 + "]=" + width5),
                20.0F, "left",   () -> out.println("side[" + side5 + "]=" + width5),
                25.0F, "right",  () -> out.println("side[" + side5 + "]=" + width5),
                30.0F, "top-left", () -> out.println("side[" + side5 + "]=" + width5),
                Var.class, (w, s) -> out.println("side[none]=" + w)
        );

        Side corner5 = Side.TOP_LEFT;

        matches(width5, side5, corner5).as(
                10.0F, "top",    Side.TOP,    () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                15.0F, "bottom", Side.BOTTOM, () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                20.0F, "left",   Side.LEFT,   () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                25.0F, "right",  Side.RIGHT,  () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                30.0F, "top-left",  Side.TOP_LEFT,  () -> out.println("side[" + side5 + "]=" + width5 + " # " + corner5),
                Var.class, (w, s, c) -> out.println("side[none]=" + w + " # " + s)
        );

        boolean flag5 = true;
        matches(width5, side5, corner5, flag5).as(
                10F, "top",    Side.TOP,    true,  () -> out.println("side[" + side5 + "]=" + width5 +
                                                        " # " + corner5 + " # " + flag5),
                15F, "bottom", Side.BOTTOM, false, () -> out.println("side[" + side5 + "]=" + width5 +
                                                        " # " + corner5 + " # " + flag5),
                20F, "left",   Side.LEFT,   true,  () -> out.println("side[" + side5 + "]=" + width5 +
                                                        " # " + corner5 + " # " + flag5),
                25F, "right",  Side.RIGHT,  false, () -> out.println("side[" + side5 + "]=" + width5 +
                                                        " # " + corner5 + " # " + flag5),
                30F, "top-left",  Side.TOP_LEFT,  true, () -> out.println("side[" + side5 + "]=" + width5 +
                                                        " # " + corner5 + " # " + flag5),
                Var.class, (w, s, c, f) -> out.println("side[none]=" + w + " # " + s + " # " + c)
        );

        /* 6 */
        double width6 = 40.0D;
        String side6 = "top-right";

        matches(width6, side6).as(
                10.0D, "top",    () -> out.println("side[" + side6 + "]=" + width6),
                15.0D, "bottom", () -> out.println("side[" + side6 + "]=" + width6),
                20.0D, "left",   () -> out.println("side[" + side6 + "]=" + width6),
                25.0D, "right",  () -> out.println("side[" + side6 + "]=" + width6),
                30.0D, "top-left",  () -> out.println("side[" + side6 + "]=" + width6),
                35.0D, "top-right", () -> out.println("side[" + side6 + "]=" + width6),
                Var.class, (w, s) -> out.println("side[none]=" + w)
        );

        Side corner6 = Side.TOP_RIGHT;

        matches(width6, side6, corner6).as(
                10.0D, "top",    Side.TOP,    () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                15.0D, "bottom", Side.BOTTOM, () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                20.0D, "left",   Side.LEFT,   () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                25.0D, "right",  Side.RIGHT,  () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                30.0D, "top-left",  Side.TOP_LEFT, () -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                35.0D, "top-right", Side.TOP_RIGHT,() -> out.println("side[" + side6 + "]=" + width6 + " # " + corner6),
                Var.class, (w, s, c) -> out.println("side[none]=" + w + " # " + s)
        );

        boolean flag6 = false;
        matches(width6, side6, corner6, flag6).as(
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
                                                              " # " + corner6 + " # " + flag6),
                Var.class, (w, s, c, f) -> out.println("side[none]=" + w + " # " + s + " # " + c)
        );
    }

    @Test
    public void letStatementTest() {
        /* 1 */
        Tuple.Tuple2<Integer, String> biValue = Tuple.of(1, "James");

        let(biValue, (Integer number, String name) -> {
            out.println("№: " + number + " name: " + name);
        });

        /* 2 */
        Tuple.Tuple3<Integer, String, Integer> triValue = Tuple.of(1, "James", 27);

        let(triValue, (Integer number, String name, Integer age) -> {
            out.println("№: " + number + " name: " + name + " age: " + age);
        });

        /* 3 */
        Tuple.Tuple4<Integer, String, Integer, Boolean> quarValue = Tuple.of(1, "James", 27, false);

        let(quarValue, (Integer number, String name, Integer age, Boolean sex) -> {
            out.println("№: " + number + " name: " + name + " age: " + age + " sex: " + (sex ? "male" : "female"));
        });
    }

    @Test
    public void foreachLoopTest() {
        /* 1 */
        foreach(listBiValues, (Integer number, String name) -> {
            out.println("№: " + number + " name: " + name);
        });

        /* 2 */
        foreach(listTriValues, (Integer number, String name, Integer age) -> {
            out.println("№: " + number + " name: " + name + " age: " + age);
        });

        /* 3 */
        foreach(listQuarValues, (Integer number, String name, Integer age, Boolean sex) -> {
            out.println("№: " + number + " name: " + name + " age: " + age + " sex: " + (sex ? "male" : "female"));
        });
    }
}
