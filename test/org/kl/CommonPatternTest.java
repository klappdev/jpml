package org.kl;

import org.junit.jupiter.api.Test;
import org.kl.error.PatternException;
import org.kl.pattern.CommonPattern;
import org.kl.shape.Rectangle;
import org.kl.state.Else;
import org.kl.state.Side;
import org.kl.util.Lazy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kl.pattern.CommonPattern.*;

public class CommonPatternTest {

    @Test
    public void elvisOperationTest() {
        Rectangle rect = null;
        Rectangle[] rects = null;
        String buffer = "";

        /* 1 */
        Rectangle emptyRect = new Rectangle();
        Rectangle result = elvis(rect, emptyRect);
        assertEquals(result, emptyRect);

        Rectangle[] emptyRects = new Rectangle[]{};
        Rectangle[] results = elvis(rects, emptyRects);
        assertEquals(results, emptyRects);

        String bufferResult = elvis(buffer, "empty line");
        assertEquals(bufferResult, "empty line");

        /* 2 */
        result = elvis(rect, () -> emptyRect);
        assertEquals(result, emptyRect);

        results = elvis(rects, () -> emptyRects);
        assertEquals(results, emptyRects);

        bufferResult = elvis(buffer, () -> "empty line");
        assertEquals(bufferResult, "empty line");

        /* 3 */
        result = elvisThrow(emptyRect, PatternException::new);
        assertEquals(result, emptyRect);

        results = elvisThrow(emptyRects, PatternException::new);
        assertEquals(results, emptyRects);

        bufferResult = elvisThrow("magic", PatternException::new);
        assertEquals(bufferResult, "magic");
    }

    @Test
    public void lazyOperationTest() {
        /* 1 */
        Lazy<Rectangle> lazyRect = lazy(Rectangle::new);

        assertEquals(lazyRect.get().getWidth(), 5);
        assertEquals(lazyRect.get().getHeight(), 10);

        /* 2 */
        Lazy<Integer> lazyInt = lazy(5);

        assertEquals(lazyInt.get(), 5);

        /* 3 */
        Lazy<Float> lazyFloat = lazy(10f);

        assertEquals(lazyFloat.get(), 10);

        /* 4 */
        Lazy<Character> lazyChar = lazy('c');

        assertEquals(lazyChar.get(), 'c');

        /* 5 */
        Lazy<String> lazyString = lazy("magic");

        assertEquals(lazyString.get(), "magic");
    }

    @Test
    public void withOperationTest() {
        Rectangle rect = new Rectangle();

        with(rect, it -> {
            it.setWidth(5);
            it.setHeight(10);
        });

        assertEquals(rect.getWidth(),  5);
        assertEquals(rect.getHeight(), 10);

        Rectangle result = with(rect, it -> {
            it.setWidth(15);
            it.setHeight(15);

            return it;
        });

        assertEquals(result.getWidth(),  15);
        assertEquals(result.getHeight(), 15);
    }

    @Test
    public void whenStatementTest() {
        /* 1 */
        Side side = Side.LEFT;

        when(
             side == Side.LEFT,   () -> System.out.println("LEFT value")
        );

        /* 2 */
        side = Side.RIGHT;

        when(
            side == Side.LEFT,   () -> System.out.println("LEFT   value"),
            side == Side.RIGHT,  () -> System.out.println("RIGHT  value")
        );

        /* 3 */
        side = Side.TOP;

        when(
            side == Side.LEFT,   () -> System.out.println("LEFT   value"),
            side == Side.RIGHT,  () -> System.out.println("RIGHT  value"),
            side == Side.TOP,    () -> System.out.println("TOP    value")
        );

        /* 4 */
        side = Side.BOTTOM;

        when(
             side == Side.LEFT,   () -> System.out.println("LEFT   value"),
             side == Side.RIGHT,  () -> System.out.println("RIGHT  value"),
             side == Side.TOP,    () -> System.out.println("TOP    value"),
             side == Side.BOTTOM, () -> System.out.println("BOTTOM value")
        );

        /* 5 */
        side = Side.TOP_LEFT;

        when(
            side == Side.LEFT,   () -> System.out.println("LEFT   value"),
            side == Side.RIGHT,  () -> System.out.println("RIGHT  value"),
            side == Side.TOP,    () -> System.out.println("TOP    value"),
            side == Side.BOTTOM, () -> System.out.println("BOTTOM value"),
            side == Side.TOP_LEFT, () -> System.out.println("TOP LEFT value")
        );

        /* 6 */
        side = Side.TOP_RIGHT;

        when(
            side == Side.LEFT,   () -> System.out.println("LEFT   value"),
            side == Side.RIGHT,  () -> System.out.println("RIGHT  value"),
            side == Side.TOP,    () -> System.out.println("TOP    value"),
            side == Side.BOTTOM, () -> System.out.println("BOTTOM value"),
            side == Side.TOP_LEFT,  () -> System.out.println("TOP LEFT value"),
            side == Side.TOP_RIGHT, () -> System.out.println("TOP RIGHT value")
        );
    }

    @Test
    public void whenStatementWithDefaultTest() {
        /* 1 */
        Side side = Side.NONE;

        when(
            side == Side.LEFT, () -> System.out.println("LEFT value"),
            Else.class,        () -> System.out.println("Else value")
        );

        /* 2 */
        side = Side.RIGHT;

        when(
            side == Side.LEFT,  () -> System.out.println("LEFT   value"),
            side == Side.RIGHT, () -> System.out.println("RIGHT  value"),
            Else.class,         () -> System.out.println("Else value")
        );

        /* 3 */
        side = Side.TOP;

        when(
            side == Side.LEFT,  () -> System.out.println("LEFT   value"),
            side == Side.RIGHT, () -> System.out.println("RIGHT  value"),
            side == Side.TOP,   () -> System.out.println("TOP    value"),
            Else.class,         () -> System.out.println("Else value")
        );

        /* 4 */
        side = Side.BOTTOM;

        when(
            side == Side.LEFT,   () -> System.out.println("LEFT   value"),
            side == Side.RIGHT,  () -> System.out.println("RIGHT  value"),
            side == Side.TOP,    () -> System.out.println("TOP    value"),
            side == Side.BOTTOM, () -> System.out.println("BOTTOM value"),
            Else.class,          () -> System.out.println("Else value")
        );

        /* 5 */
        side = Side.TOP_LEFT;

        when(
            side == Side.LEFT,   () -> System.out.println("LEFT   value"),
            side == Side.RIGHT,  () -> System.out.println("RIGHT  value"),
            side == Side.TOP,    () -> System.out.println("TOP    value"),
            side == Side.BOTTOM, () -> System.out.println("BOTTOM value"),
            side == Side.TOP_LEFT, () -> System.out.println("TOP LEFT value"),
            Else.class,          () -> System.out.println("Else value")
        );

        /* 6 */
        side = Side.TOP_RIGHT;

        when(
            side == Side.LEFT,   () -> System.out.println("LEFT   value"),
            side == Side.RIGHT,  () -> System.out.println("RIGHT  value"),
            side == Side.TOP,    () -> System.out.println("TOP    value"),
            side == Side.BOTTOM, () -> System.out.println("BOTTOM value"),
            side == Side.TOP_LEFT,  () -> System.out.println("TOP LEFT value"),
            side == Side.TOP_RIGHT, () -> System.out.println("TOP RIGHT value"),
            Else.class,          () -> System.out.println("Else value")
        );
    }

    @Test
    public void whenExpressionTest() {
        /* 1 */
        Side side = Side.LEFT;
        Side result;

        result = when(
                side == Side.LEFT, () -> Side.RIGHT
        );

        assertEquals(result, Side.RIGHT);

        /* 2 */
        side = Side.RIGHT;

        result = when(
                side == Side.LEFT,  () -> Side.RIGHT,
                side == Side.RIGHT, () -> Side.LEFT
        );

        assertEquals(result, Side.LEFT);

        /* 3 */
        side = Side.TOP;

        result = when(
                side == Side.LEFT,   () -> Side.RIGHT,
                side == Side.RIGHT,  () -> Side.LEFT,
                side == Side.TOP,    () -> Side.BOTTOM
        );

        assertEquals(result, Side.BOTTOM);

        /* 4 */
        side = Side.BOTTOM;

        result = when(
                side == Side.LEFT,   () -> Side.RIGHT,
                side == Side.RIGHT,  () -> Side.LEFT,
                side == Side.TOP,    () -> Side.BOTTOM,
                side == Side.BOTTOM, () -> Side.TOP
        );

        assertEquals(result, Side.TOP);

        /* 5 */
        side = Side.TOP_LEFT;

        result = when(
                side == Side.LEFT,   () -> Side.RIGHT,
                side == Side.RIGHT,  () -> Side.LEFT,
                side == Side.TOP,    () -> Side.BOTTOM,
                side == Side.BOTTOM, () -> Side.TOP,
                side == Side.TOP_LEFT, () -> Side.TOP_RIGHT
        );

        assertEquals(result, Side.TOP_RIGHT);

        /* 6 */
        side = Side.TOP_RIGHT;

        result = when(
                side == Side.LEFT,   () -> Side.RIGHT,
                side == Side.RIGHT,  () -> Side.LEFT,
                side == Side.TOP,    () -> Side.BOTTOM,
                side == Side.BOTTOM, () -> Side.TOP,
                side == Side.TOP_LEFT,  () -> Side.TOP_RIGHT,
                side == Side.TOP_RIGHT, () -> Side.TOP_LEFT
        );

        assertEquals(result, Side.TOP_LEFT);
    }

    @Test
    public void whenExpressionWithDefaultTest() {
        /* 1 */
        Side side = Side.NONE;
        Side result;

        result = when(
                side == Side.LEFT, () -> Side.RIGHT,
                Else.class,        () -> Side.NONE
        );

        assertEquals(result, Side.NONE);

        /* 2 */
        result = when(
                side == Side.LEFT,  () -> Side.RIGHT,
                side == Side.RIGHT, () -> Side.LEFT,
                Else.class,         () -> Side.NONE
        );

        assertEquals(result, Side.NONE);

        /* 3 */
        result = when(
                side == Side.LEFT,   () -> Side.RIGHT,
                side == Side.RIGHT,  () -> Side.LEFT,
                side == Side.TOP,    () -> Side.BOTTOM,
                Else.class,          () -> Side.NONE
        );

        assertEquals(result, Side.NONE);

        /* 4 */
        result = when(
                side == Side.LEFT,   () -> Side.RIGHT,
                side == Side.RIGHT,  () -> Side.LEFT,
                side == Side.TOP,    () -> Side.BOTTOM,
                side == Side.BOTTOM, () -> Side.TOP,
                Else.class,          () -> Side.NONE
        );

        assertEquals(result, Side.NONE);

        /* 5 */
        result = when(
                side == Side.LEFT,   () -> Side.RIGHT,
                side == Side.RIGHT,  () -> Side.LEFT,
                side == Side.TOP,    () -> Side.BOTTOM,
                side == Side.BOTTOM, () -> Side.TOP,
                side == Side.TOP_LEFT, () -> Side.TOP_RIGHT,
                Else.class,          () -> Side.NONE
        );

        assertEquals(result, Side.NONE);

        /* 6 */
        result = when(
                side == Side.LEFT,   () -> Side.RIGHT,
                side == Side.RIGHT,  () -> Side.LEFT,
                side == Side.TOP,    () -> Side.BOTTOM,
                side == Side.BOTTOM, () -> Side.TOP,
                side == Side.TOP_LEFT,  () -> Side.TOP_RIGHT,
                side == Side.TOP_RIGHT, () -> Side.TOP_LEFT,
                Else.class,          () -> Side.NONE
        );

        assertEquals(result, Side.NONE);
    }
}
