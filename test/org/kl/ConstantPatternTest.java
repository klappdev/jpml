package org.kl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.kl.shape.Circle;
import org.kl.shape.Figure;
import org.kl.shape.Unpiped;
import org.kl.state.Else;
import org.kl.state.Null;
import org.kl.state.Var;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kl.pattern.ConstantPattern.matches;

public class ConstantPatternTest {

    @Test
    public void matchesStatementTest()  {
        /* 1 */
        byte value1 = 5;
        matches(value1).as(
                (byte) 5, ()  -> System.out.println("safe brunch 1 - " + value1)
        );

        matches(value1,
                (byte) 5, ()  -> System.out.println("brunch 1 - " + value1)
        );

        Figure figure1 = new Circle(5);
        matches(figure1).as(
                new Circle(5), ()  -> System.out.println("safe brunch 1 - " + figure1)
        );

        matches(figure1,
                new Circle(5), ()  -> System.out.println("brunch 1 - " + figure1)
        );

        /* 2 */
        short value2 = 10;
        matches(value2,
                (short) 5,  ()  -> System.out.println("brunch 1 - " + value2),
                (short) 10, ()  -> System.out.println("brunch 2 - " + value2)
        );

        Figure figure2 = new Circle(10);
        matches(figure2,
                new Circle(5),  ()  -> System.out.println("brunch 1 - " + figure2),
                new Circle(10), ()  -> System.out.println("brunch 2 - " + figure2)
        );

        /* 3 */
        int value3 = 15;

        matches(value3,
                5,  ()  -> System.out.println("brunch 1 - " + value3),
                10, ()  -> System.out.println("brunch 2 - " + value3),
                15, ()  -> System.out.println("brunch 3 - " + value3)
        );

        Figure figure3 = new Unpiped(15);
        matches(figure3,
                new Unpiped(5),  ()  -> System.out.println("brunch 1 - " + figure3),
                new Unpiped(10), ()  -> System.out.println("brunch 2 - " + figure3),
                new Unpiped(15), ()  -> System.out.println("brunch 3 - " + figure3)
        );

        /* 4 */
        long value4 = 20;
        matches(value4,
                5L,  ()  -> System.out.println("brunch 1 - " + value4),
                10L, ()  -> System.out.println("brunch 2 - " + value4),
                15L, ()  -> System.out.println("brunch 3 - " + value4),
                20L, ()  -> System.out.println("brunch 4 - " + value4)
        );

        Figure figure4 = new Unpiped(20);
        matches(figure4,
                new Unpiped(5),  ()  -> System.out.println("brunch 1 - " + figure4),
                new Unpiped(10), ()  -> System.out.println("brunch 2 - " + figure4),
                new Unpiped(15), ()  -> System.out.println("brunch 3 - " + figure4),
                new Unpiped(20), ()  -> System.out.println("brunch 4 - " + figure4)
        );

        /* 5 */
        float value5 = 25.0F;
        matches(value5,
                5.0F,  ()  -> System.out.println("brunch 1 - " + value5),
                10.0F, ()  -> System.out.println("brunch 2 - " + value5),
                15.0F, ()  -> System.out.println("brunch 3 - " + value5),
                20.0F, ()  -> System.out.println("brunch 4 - " + value5),
                25.0F, ()  -> System.out.println("brunch 5 - " + value5)
        );

        /* 6 */
        double value6 = 30.0D;
        matches(value6,
                5.0D,  ()  -> System.out.println("brunch 1 - " + value6),
                10.0D, ()  -> System.out.println("brunch 2 - " + value6),
                15.0D, ()  -> System.out.println("brunch 3 - " + value6),
                20.0D, ()  -> System.out.println("brunch 4 - " + value6),
                25.0D, ()  -> System.out.println("brunch 5 - " + value6),
                30.0D, ()  -> System.out.println("brunch 6 - " + value6)
        );
    }

    @Disabled
    @Test
    public void matchesStatementWithDefaultTest()  {
        /* 1 */
        byte value1 = 10;
        matches(value1,
                (byte) 5,   () -> System.out.println("brunch 1 - " + value1),
                Else.class, () -> System.out.println("Else value 1 type")
        );

        /* 2 */
        short value2 = 15;
        matches(value2,
                (short) 5,     () -> System.out.println("brunch 1 - " + value2),
                (short) 10,    () -> System.out.println("brunch 2 - " + value2),
                Else.class, () -> System.out.println("Else value 2 type")
        );

        /* 3 */
        int value3 = 20;
        matches(value3,
                5,  ()  -> System.out.println("brunch 1 - " + value3),
                10, ()  -> System.out.println("brunch 2 - " + value3),
                15, ()  -> System.out.println("brunch 3 - " + value3),
                Else.class, () -> System.out.println("Else value 3 type")
        );

        /* 4 */
        long value4 = 25;
        matches(value4,
                5L,  ()  -> System.out.println("brunch 1 - " + value4),
                10L, ()  -> System.out.println("brunch 2 - " + value4),
                15L, ()  -> System.out.println("brunch 3 - " + value4),
                20L, ()  -> System.out.println("brunch 4 - " + value4),
                Else.class, () -> System.out.println("Else value 4 type")
        );

        /* 5 */
        float value5 = 30.0F;
        matches(value5,
                5.0F,  ()  -> System.out.println("brunch 1 - " + value5),
                10.0F, ()  -> System.out.println("brunch 2 - " + value5),
                15.0F, ()  -> System.out.println("brunch 3 - " + value5),
                20.0F, ()  -> System.out.println("brunch 4 - " + value5),
                25.0F, ()  -> System.out.println("brunch 5 - " + value5),
                Else.class, () -> System.out.println("Else value 5 type")
        );

        /* 6 */
        double value6 = 35.0D;
        matches(value6,
                5.0D,  ()  -> System.out.println("brunch 1 - " + value6),
                10.0D, ()  -> System.out.println("brunch 2 - " + value6),
                15.0D, ()  -> System.out.println("brunch 3 - " + value6),
                20.0D, ()  -> System.out.println("brunch 4 - " + value6),
                25.0D, ()  -> System.out.println("brunch 5 - " + value6),
                30.0D, ()  -> System.out.println("brunch 6 - " + value6),
                Else.class, () -> System.out.println("Else value 6 type")
        );
    }

    @Disabled
    @Test
    public void matchesStatementWithVarTest()  {
        /* 1 */
        byte value1 = 10;
        matches(value1,
                (byte) 5,  ()  -> System.out.println("brunch 1 - " + value1),
                Var.class, any -> System.out.println("Else value 1 type")
        );

        /* 2 */
        short value2 = 15;
        matches(value2,
                (short) 5,  ()  -> System.out.println("brunch 1 - " + value2),
                (short) 10, ()  -> System.out.println("brunch 2 - " + value2),
                Var.class,  any -> System.out.println("Else value 2 type")
        );

        /* 3 */
        int value3 = 20;
        matches(value3,
                5,  ()  -> System.out.println("brunch 1 - " + value3),
                10, ()  -> System.out.println("brunch 2 - " + value3),
                15, ()  -> System.out.println("brunch 3 - " + value3),
                Var.class,  any -> System.out.println("Else value 3 type")
        );

        /* 4 */
        long value4 = 25;
        matches(value4,
                5L,  ()  -> System.out.println("brunch 1 - " + value4),
                10L, ()  -> System.out.println("brunch 2 - " + value4),
                15L, ()  -> System.out.println("brunch 3 - " + value4),
                20L, ()  -> System.out.println("brunch 4 - " + value4),
                Var.class,  any -> System.out.println("Else value 4 type")
        );

        /* 5 */
        float value5 = 30.0F;
        matches(value5,
                5.0F,  ()  -> System.out.println("brunch 1 - " + value5),
                10.0F, ()  -> System.out.println("brunch 2 - " + value5),
                15.0F, ()  -> System.out.println("brunch 3 - " + value5),
                20.0F, ()  -> System.out.println("brunch 4 - " + value5),
                25.0F, ()  -> System.out.println("brunch 5 - " + value5),
                Var.class,  any -> System.out.println("Else value 5 type")
        );

        /* 6 */
        double value6 = 35.0D;
        matches(value6,
                5.0D,  ()  -> System.out.println("brunch 1 - " + value6),
                10.0D, ()  -> System.out.println("brunch 2 - " + value6),
                15.0D, ()  -> System.out.println("brunch 3 - " + value6),
                20.0D, ()  -> System.out.println("brunch 4 - " + value6),
                25.0D, ()  -> System.out.println("brunch 5 - " + value6),
                30.0D, ()  -> System.out.println("brunch 6 - " + value6),
                Var.class,  any -> System.out.println("Else value 6 type")
        );
    }

    @Disabled
    @Test
    public void matchesStatementWithNullDefaultTest()  {
        /* 1 */
        matches(null,
                (byte) 5,      () -> System.out.println("brunch 1"),
                Null.class,    () -> System.out.println("Null    value 1 type"),
                Else.class, () -> System.out.println("Else value 1 type")
        );

        /* 2 */
        matches(null,
                (short) 5,     () -> System.out.println("brunch 1"),
                (short) 10,    () -> System.out.println("brunch 2"),
                Null.class,    () -> System.out.println("Null    value 2 type"),
                Else.class, () -> System.out.println("Else value 2 type")
        );

        /* 3 */
        matches(null,
                5,  ()  -> System.out.println("brunch 1"),
                10, ()  -> System.out.println("brunch 2"),
                15, ()  -> System.out.println("brunch 3"),
                Null.class,    () -> System.out.println("Null    value 3 type"),
                Else.class, () -> System.out.println("Else value 3 type")
        );

        /* 4*/
        matches(null,
                5L,  ()  -> System.out.println("brunch 1"),
                10L, ()  -> System.out.println("brunch 2"),
                15L, ()  -> System.out.println("brunch 3"),
                20L, ()  -> System.out.println("brunch 4"),
                Null.class,    () -> System.out.println("Null    value 4 type"),
                Else.class, () -> System.out.println("Else value 4 type")
        );

        /* 5 */
        matches(null,
                5.0F,  ()  -> System.out.println("brunch 1"),
                10.0F, ()  -> System.out.println("brunch 2"),
                15.0F, ()  -> System.out.println("brunch 3"),
                20.0F, ()  -> System.out.println("brunch 4"),
                25.0F, ()  -> System.out.println("brunch 5"),
                Null.class,    () -> System.out.println("Null    value 5 type"),
                Else.class, () -> System.out.println("Else value 5 type")
        );

        /* 6 */
        matches(null,
                5.0D,  ()  -> System.out.println("brunch 1"),
                10.0D, ()  -> System.out.println("brunch 2"),
                15.0D, ()  -> System.out.println("brunch 3"),
                20.0D, ()  -> System.out.println("brunch 4"),
                25.0D, ()  -> System.out.println("brunch 5"),
                30.0D, ()  -> System.out.println("brunch 6"),
                Null.class,    () -> System.out.println("Null    value 6 type"),
                Else.class, () -> System.out.println("Else value 6 type")
        );
    }

    @Disabled
    @Test
    public void matchesStatementWithNullVarTest()  {
        /* 1 */
        matches(null,
                (byte) 5,   () -> System.out.println("brunch 1"),
                Null.class, () -> System.out.println("Null value 1 type"),
                Var.class, (Consumer<Byte>) any -> System.out.println("Var value 1 type")
        );

        /* 2 */
        matches(null,
                (short) 5, () -> System.out.println("brunch 1"),
                (short) 10, () -> System.out.println("brunch 2"),
                Null.class, () -> System.out.println("Null value 2 type"),
                Var.class, (Consumer<Short>) any -> System.out.println("Var value 2 type")
        );

        /* 3 */
        matches(null,
                5,  ()  -> System.out.println("brunch 1"),
                10, ()  -> System.out.println("brunch 2"),
                15, ()  -> System.out.println("brunch 3"),
                Null.class, () -> System.out.println("Null value 3 type"),
                Var.class,  (Consumer<Integer>) any -> System.out.println("Var value 3 type")
        );

        /* 4*/
        matches(null,
                5L,  ()  -> System.out.println("brunch 1"),
                10L, ()  -> System.out.println("brunch 2"),
                15L, ()  -> System.out.println("brunch 3"),
                20L, ()  -> System.out.println("brunch 4"),
                Null.class, () -> System.out.println("Null value 4 type"),
                Var.class,  (Consumer<Long>) any -> System.out.println("Var value 4 type")
        );

        /* 5 */
        matches(null,
                5.0F,  ()  -> System.out.println("brunch 1"),
                10.0F, ()  -> System.out.println("brunch 2"),
                15.0F, ()  -> System.out.println("brunch 3"),
                20.0F, ()  -> System.out.println("brunch 4"),
                25.0F, ()  -> System.out.println("brunch 5"),
                Null.class, () -> System.out.println("Null value 5 type"),
                Var.class,  (Consumer<Float>) any -> System.out.println("Var value 5 type")
        );

        /* 6 */
        matches(null,
                5.0D,  ()  -> System.out.println("brunch 1"),
                10.0D, ()  -> System.out.println("brunch 2"),
                15.0D, ()  -> System.out.println("brunch 3"),
                20.0D, ()  -> System.out.println("brunch 4"),
                25.0D, ()  -> System.out.println("brunch 5"),
                30.0D, ()  -> System.out.println("brunch 6"),
                Null.class, () -> System.out.println("Null value 6 type"),
                Var.class,  (Consumer<Double>) any -> System.out.println("Var value 6 type")
        );
    }

    @Disabled
    @Test
    public void matchesExpressionTest()  {
        /* 1 */
        byte value1  = 5;
        byte result1 = matches(value1,
                (byte) 5, () -> { byte result = (byte) (2 * (value1)); return result; }
        );

        assertEquals(result1, 2 * (value1));

        /* 2 */
        short value2  = 10;
        short result2 = matches(value2,
                (short) 5,  () -> { short result = (short) (2 * (value2)); return result; },
                (short) 10, () -> { short result = (short) (2 * (value2)); return result; }
        );

        assertEquals(result2, 2 * (value2));

        /* 3 */
        int value3  = 15;
        int result3 = matches(value3,
                5,  () -> { int result = 2 * (value3); return result; },
                10, () -> { int result = 2 * (value3); return result; },
                15, () -> { int result = 2 * (value3); return result; }
        );

        assertEquals(result3, 2 * (value3));

        /* 4 */
        long value4  = 20;
        long result4 = matches(value4,
                5L,  () -> { long result = 2 * (value4); return result; },
                10L, () -> { long result = 2 * (value4); return result; },
                15L, () -> { long result = 2 * (value4); return result; },
                20L, () -> { long result = 2 * (value4); return result; }
        );

        assertEquals(result4, 2 * (value4));

        /* 5 */
        float value5  = 25.0F;
        float result5 = matches(value5,
                5.0F,  () -> { float result = 2 * (value5); return result; },
                10.0F, () -> { float result = 2 * (value5); return result; },
                15.0F, () -> { float result = 2 * (value5); return result; },
                20.0F, () -> { float result = 2 * (value5); return result; },
                25.0F, () -> { float result = 2 * (value5); return result; }
        );

        assertEquals(result5, 2 * (value5));

        /* 6 */
        double value6  = 30.0;
        double result6 = matches(value6,
                5.0D,  () -> { double result = 2 * (value6); return result; },
                10.0D, () -> { double result = 2 * (value6); return result; },
                15.0D, () -> { double result = 2 * (value6); return result; },
                20.0D, () -> { double result = 2 * (value6); return result; },
                25.0D, () -> { double result = 2 * (value6); return result; },
                30.0D, () -> { double result = 2 * (value6); return result; }
        );

        assertEquals(result6, 2 * (value6));
    }

    @Disabled
    @Test
    public void matchesExpressionWithDefaultTest()  {
        /* 1 */
        byte value1  = 10;
        byte result1 = matches(value1,
                (byte) 5,      () -> (byte) (2 * (value1)),
                Else.class, (Supplier<Byte>) () -> (byte) 0
        );

        assertEquals(result1, 0);

        /* 2 */
        short value2  = 15;
        short result2 = matches(value2,
                (short) 5,  () -> (short) (2 * (value2)),
                (short) 10, () -> (short) (2 * (value2)),
                Else.class, (Supplier<Short>) () -> (short) 0
        );

        assertEquals(result2, 0);

        /* 3 */
        int value3  = 20;
        int result3 = matches(value3,
                5,  () -> 2 * (value3),
                10, () -> 2 * (value3),
                15, () -> 2 * (value3),
                Else.class, (Supplier<Integer>) () -> 0
        );

        assertEquals(result3, 0);

        /* 4 */
        long value4  = 25;
        long result4 = matches(value4,
                5L,  () -> 2 * (value4),
                10L, () -> 2 * (value4),
                15L, () -> 2 * (value4),
                20L, () -> 2 * (value4),
                Else.class, (Supplier<Long>) () -> (long) 0
        );

        assertEquals(result4, 0);

        /* 5 */
        float value5  = 30.0F;
        float result5 = matches(value5,
                5.0F,  () -> 2 * (value5),
                10.0F, () -> 2 * (value5),
                15.0F, () -> 2 * (value5),
                20.0F, () -> 2 * (value5),
                25.0F, () -> 2 * (value5),
                Else.class, (Supplier<Float>) () -> (float) 0
        );

        assertEquals(result5, 0);

        /* 6 */
        double value6  = 35.0D;
        double result6 = matches(value6,
                5.0D,  () -> 2 * (value6),
                10.0D, () -> 2 * (value6),
                15.0D, () -> 2 * (value6),
                20.0D, () -> 2 * (value6),
                25.0D, () -> 2 * (value6),
                30.0D, () -> 2 * (value6),
                Else.class, () -> (double) 0
        );

        assertEquals(result6, 0);
    }

    @Disabled
    @Test
    public void matchesExpressionWithVarTest()  {
        /* 1 */
        byte value1 = 10;
        byte result1 = matches(value1,
                (byte) 5,  ()  -> (byte) (2 * (value1)),
                Var.class, any -> (byte) -1
        );

        assertEquals(result1, -1);

        /* 2 */
        short value2  = 15;
        short result2 = matches(value2,
                (short) 5,  () -> (short) (2 * (value2)),
                (short) 10, () -> (short) (2 * (value2)),
                Var.class, any -> (short) -1
        );

        assertEquals(result2, -1);

        /* 3 */
        int value3 = 20;
        int result3 = matches(value3,
                5,  () -> 2 * (value3),
                10, () -> 2 * (value3),
                15, () -> 2 * (value3),
                Var.class, any -> -1
        );

        assertEquals(result3, -1);

        /* 4 */
        long value4  = 25;
        long result4 = matches(value4,
                5L,  () -> 2 * (value4),
                10L, () -> 2 * (value4),
                15L, () -> 2 * (value4),
                20L, () -> 2 * (value4),
                Var.class, any -> (long) -1
        );

        assertEquals(result4, -1);

        /* 5 */
        float value5  = 30.0F;
        float result5 = matches(value5,
                5.0F,  () -> 2 * (value5),
                10.0F, () -> 2 * (value5),
                15.0F, () -> 2 * (value5),
                20.0F, () -> 2 * (value5),
                25.0F, () -> 2 * (value5),
                Var.class, any -> (float) -1
        );

        assertEquals(result5, -1);

        /* 6 */
        double value6  = 35.0D;
        double result6 = matches(value6,
                5.0D,  () -> 2 * (value6),
                10.0D, () -> 2 * (value6),
                15.0D, () -> 2 * (value6),
                20.0D, () -> 2 * (value6),
                25.0D, () -> 2 * (value6),
                30.0D, () -> 2 * (value6),
                Var.class, any -> (double) -1
        );

        assertEquals(result6, -1);
    }

    @Disabled
    @Test
    public void matchesExpressionWithNullDefaultTest()  {
        /* 1 */
        byte result1 = matches(null,
                (byte) 5,      () -> (byte) 1,
                Null.class,    () -> (byte) -1,
                Else.class, (Supplier<Byte>) () -> (byte) 0
        );

        assertEquals(result1, -1);

        /* 2 */
        short result2 = matches(null,
                (short) 5,  () -> (short) 1,
                (short) 10, () -> (short) 2,
                Null.class, () -> (short) -1,
                Else.class, (Supplier<Short>) () -> (short) 0
        );

        assertEquals(result2, -1);

        /* 3 */
        int result3 = matches(null,
                5,  () -> 1,
                10, () -> 2,
                15, () -> 3,
                Null.class, () -> -1,
                Else.class, (Supplier<Integer>) () -> 0
        );

        assertEquals(result3, -1);

        /* 4 */
        long result4 = matches(null,
                5L,  () -> (long) 1,
                10L, () -> (long) 2,
                15L, () -> (long) 3,
                20L, () -> (long) 4,
                Null.class, () -> (long) -1,
                Else.class, (Supplier<Long>) () -> (long) 0
        );

        assertEquals(result4, -1);

        /* 5 */
        float result5 = matches(null,
                5.0F,  () -> (float) 1,
                10.0F, () -> (float) 2,
                15.0F, () -> (float) 3,
                20.0F, () -> (float) 4,
                25.0F, () -> (float) 5,
                Null.class, () -> (float) -1,
                Else.class, (Supplier<Float>) () -> (float) 0
        );

        assertEquals(result5, -1);

        /* 6 */
        double result6 = matches(null,
                5.0D,  () -> (double) 1,
                10.0D, () -> (double) 2,
                15.0D, () -> (double) 3,
                20.0D, () -> (double) 4,
                25.0D, () -> (double) 5,
                30.0D, () -> (double) 6,
                Null.class,    () -> (double) -1,
                Else.class, () -> (double)  0
        );

        assertEquals(result6, -1);
    }

    @Disabled
    @Test
    public void matchesExpressionWithNullVarTest()  {
        /* 1 */
        byte result1 = matches(null,
                (byte) 5,   () -> (byte) 1,
                Null.class, () -> (byte) -1,
                Var.class, (UnaryOperator<Byte>) any -> (byte) 0
        );

        assertEquals(result1, -1);

        /* 2 */
        short result2 = matches(null,
                (short) 5,  () -> (short) 1,
                (short) 10, () -> (short) 2,
                Null.class, () -> (short) -1,
                Var.class, (UnaryOperator<Short>) any -> (short) 0
        );

        assertEquals(result2, -1);

        /* 3 */
        int result3 = matches(null,
                5,  () -> 1,
                10, () -> 2,
                15, () -> 3,
                Null.class, () -> -1,
                Var.class, (UnaryOperator<Integer>) any -> 0
        );

        assertEquals(result3, -1);

        /* 4 */
        long result4 = matches(null,
                5L,  () -> (long) 1,
                10L, () -> (long) 2,
                15L, () -> (long) 3,
                20L, () -> (long) 4,
                Null.class, () -> (long) -1,
                Var.class, (UnaryOperator<Long>) any -> (long) 0
        );

        assertEquals(result4, -1);

        /* 5 */
        float result5 = matches(null,
                5.0F,  () -> (float) 1,
                10.0F, () -> (float) 2,
                15.0F, () -> (float) 3,
                20.0F, () -> (float) 4,
                25.0F, () -> (float) 5,
                Null.class, () -> (float) -1,
                Var.class, (UnaryOperator<Float>) any -> (float) 0
        );

        assertEquals(result5, -1);

        /* 6 */
        double result6 = matches(null,
                5.0D,  () -> (double) 1,
                10.0D, () -> (double) 2,
                15.0D, () -> (double) 3,
                20.0D, () -> (double) 4,
                25.0D, () -> (double) 5,
                30.0D, () -> (double) 6,
                Null.class, () -> (double) -1,
                Var.class, any -> (double) 0
        );

        assertEquals(result6, -1);
    }
}
