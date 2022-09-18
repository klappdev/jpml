/*
 * Licensed under the MIT License <http://opensource.org/licenses/MIT>.
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2019 - 2022 https://github.com/klappdev
 *
 * Permission is hereby  granted, free of charge, to any  person obtaining a copy
 * of this software and associated  documentation files (the "Software"), to deal
 * in the Software  without restriction, including without  limitation the rights
 * to  use, copy,  modify, merge,  publish, distribute,  sublicense, and/or  sell
 * copies  of  the Software,  and  to  permit persons  to  whom  the Software  is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE  IS PROVIDED "AS  IS", WITHOUT WARRANTY  OF ANY KIND,  EXPRESS OR
 * IMPLIED,  INCLUDING BUT  NOT  LIMITED TO  THE  WARRANTIES OF  MERCHANTABILITY,
 * FITNESS FOR  A PARTICULAR PURPOSE AND  NONINFRINGEMENT. IN NO EVENT  SHALL THE
 * AUTHORS  OR COPYRIGHT  HOLDERS  BE  LIABLE FOR  ANY  CLAIM,  DAMAGES OR  OTHER
 * LIABILITY, WHETHER IN AN ACTION OF  CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE  OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.kl.jpml.test;

import org.junit.jupiter.api.Test;
import org.kl.jpml.error.PatternException;
import org.kl.jpml.test.shape.Circle;
import org.kl.jpml.test.shape.Rectangle;
import org.kl.jpml.state.Else;
import org.kl.jpml.test.state.Side;
import org.kl.jpml.util.Lazy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kl.jpml.pattern.CommonPattern.*;

public class CommonPatternTest {

    @Test
    public void scopeOperationTest() {
        /* 1 */
        run(() -> {
            System.out.println("Run some text");
        });

        /* 2 */
        repeat(3, () -> {
            System.out.println("Repeat three times");
        });

        /* 3 */
        Rectangle rect = new Rectangle(5, 5);
        self(rect).also(it -> {
            System.out.println("rect: " + it);
        });

        /* 4 */
        Circle circle = null;
        self(circle).let(it -> {
            System.out.println("circle:" + it);
        });

        /* 5 */
        int number = 2;
        int event = self(number).takeIf((Integer it) -> it % 2 == 0);

        System.out.println("event number: " + event);

        /* 6 */
        number = 3;
        int odd = self(number).takeUnless((Integer it) -> it % 2 == 0);
        System.out.println("Odd number: " + odd);

    }

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

        assertEquals(lazyRect.get().width(), 5);
        assertEquals(lazyRect.get().height(), 10);

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
    public void withStatementTest() {
        Rectangle rect = new Rectangle();

        with(rect, it -> {
            it.setWidth(5);
            it.setHeight(10);
        });

        assertEquals(rect.width(), 5);
        assertEquals(rect.height(), 10);

        Rectangle result = with(rect, it -> {
            it.setWidth(15);
            it.setHeight(15);

            return it;
        });

        assertEquals(result.width(), 15);
        assertEquals(result.height(), 15);
    }

    @Test
    public void applyStatementTest() {
        Rectangle rect = new Rectangle();

        rect = self(rect).apply(it -> {
            it.setWidth(5);
            it.setHeight(10);
        });

        assertEquals(rect.width(), 5);
        assertEquals(rect.height(), 10);

        self(rect).<Rectangle>apply(it -> {
            it.setWidth(15);
            it.setHeight(15);
        });

        assertEquals(rect.width(), 15);
        assertEquals(rect.height(), 15);
    }

    @Test
    public void matchStatementTest() {
        /* 1 */
        Side side = Side.LEFT;

        match(
                side == Side.LEFT, () -> System.out.println("LEFT value")
        );

        /* 2 */
        side = Side.RIGHT;

        match(
                side == Side.LEFT, () -> System.out.println("LEFT   value"),
                side == Side.RIGHT, () -> System.out.println("RIGHT  value")
        );

        /* 3 */
        side = Side.TOP;

        match(
                side == Side.LEFT, () -> System.out.println("LEFT   value"),
                side == Side.RIGHT, () -> System.out.println("RIGHT  value"),
                side == Side.TOP, () -> System.out.println("TOP    value")
        );

        /* 4 */
        side = Side.BOTTOM;

        match(
                side == Side.LEFT, () -> System.out.println("LEFT   value"),
                side == Side.RIGHT, () -> System.out.println("RIGHT  value"),
                side == Side.TOP, () -> System.out.println("TOP    value"),
                side == Side.BOTTOM, () -> System.out.println("BOTTOM value")
        );

        /* 5 */
        side = Side.TOP_LEFT;

        match(
                side == Side.LEFT, () -> System.out.println("LEFT   value"),
                side == Side.RIGHT, () -> System.out.println("RIGHT  value"),
                side == Side.TOP, () -> System.out.println("TOP    value"),
                side == Side.BOTTOM, () -> System.out.println("BOTTOM value"),
                side == Side.TOP_LEFT, () -> System.out.println("TOP LEFT value")
        );

        /* 6 */
        side = Side.TOP_RIGHT;

        match(
                side == Side.LEFT, () -> System.out.println("LEFT   value"),
                side == Side.RIGHT, () -> System.out.println("RIGHT  value"),
                side == Side.TOP, () -> System.out.println("TOP    value"),
                side == Side.BOTTOM, () -> System.out.println("BOTTOM value"),
                side == Side.TOP_LEFT, () -> System.out.println("TOP LEFT value"),
                side == Side.TOP_RIGHT, () -> System.out.println("TOP RIGHT value")
        );
    }

    @Test
    public void matchStatementWithDefaultTest() {
        /* 1 */
        Side side = Side.NONE;

        match(
                side == Side.LEFT, () -> System.out.println("LEFT value"),
                Else.class, () -> System.out.println("Else value")
        );

        /* 2 */
        side = Side.RIGHT;

        match(
                side == Side.LEFT, () -> System.out.println("LEFT   value"),
                side == Side.RIGHT, () -> System.out.println("RIGHT  value"),
                Else.class, () -> System.out.println("Else value")
        );

        /* 3 */
        side = Side.TOP;

        match(
                side == Side.LEFT, () -> System.out.println("LEFT   value"),
                side == Side.RIGHT, () -> System.out.println("RIGHT  value"),
                side == Side.TOP, () -> System.out.println("TOP    value"),
                Else.class, () -> System.out.println("Else value")
        );

        /* 4 */
        side = Side.BOTTOM;

        match(
                side == Side.LEFT, () -> System.out.println("LEFT   value"),
                side == Side.RIGHT, () -> System.out.println("RIGHT  value"),
                side == Side.TOP, () -> System.out.println("TOP    value"),
                side == Side.BOTTOM, () -> System.out.println("BOTTOM value"),
                Else.class, () -> System.out.println("Else value")
        );

        /* 5 */
        side = Side.TOP_LEFT;

        match(
                side == Side.LEFT, () -> System.out.println("LEFT   value"),
                side == Side.RIGHT, () -> System.out.println("RIGHT  value"),
                side == Side.TOP, () -> System.out.println("TOP    value"),
                side == Side.BOTTOM, () -> System.out.println("BOTTOM value"),
                side == Side.TOP_LEFT, () -> System.out.println("TOP LEFT value"),
                Else.class, () -> System.out.println("Else value")
        );

        /* 6 */
        side = Side.TOP_RIGHT;

        match(
                side == Side.LEFT, () -> System.out.println("LEFT   value"),
                side == Side.RIGHT, () -> System.out.println("RIGHT  value"),
                side == Side.TOP, () -> System.out.println("TOP    value"),
                side == Side.BOTTOM, () -> System.out.println("BOTTOM value"),
                side == Side.TOP_LEFT, () -> System.out.println("TOP LEFT value"),
                side == Side.TOP_RIGHT, () -> System.out.println("TOP RIGHT value"),
                Else.class, () -> System.out.println("Else value")
        );
    }

    @Test
    public void matchExpressionTest() {
        /* 1 */
        Side side = Side.LEFT;
        Side result;

        result = match(
                side == Side.LEFT, () -> Side.RIGHT
        );

        assertEquals(result, Side.RIGHT);

        /* 2 */
        side = Side.RIGHT;

        result = match(
                side == Side.LEFT, () -> Side.RIGHT,
                side == Side.RIGHT, () -> Side.LEFT
        );

        assertEquals(result, Side.LEFT);

        /* 3 */
        side = Side.TOP;

        result = match(
                side == Side.LEFT, () -> Side.RIGHT,
                side == Side.RIGHT, () -> Side.LEFT,
                side == Side.TOP, () -> Side.BOTTOM
        );

        assertEquals(result, Side.BOTTOM);

        /* 4 */
        side = Side.BOTTOM;

        result = match(
                side == Side.LEFT, () -> Side.RIGHT,
                side == Side.RIGHT, () -> Side.LEFT,
                side == Side.TOP, () -> Side.BOTTOM,
                side == Side.BOTTOM, () -> Side.TOP
        );

        assertEquals(result, Side.TOP);

        /* 5 */
        side = Side.TOP_LEFT;

        result = match(
                side == Side.LEFT, () -> Side.RIGHT,
                side == Side.RIGHT, () -> Side.LEFT,
                side == Side.TOP, () -> Side.BOTTOM,
                side == Side.BOTTOM, () -> Side.TOP,
                side == Side.TOP_LEFT, () -> Side.TOP_RIGHT
        );

        assertEquals(result, Side.TOP_RIGHT);

        /* 6 */
        side = Side.TOP_RIGHT;

        result = match(
                side == Side.LEFT, () -> Side.RIGHT,
                side == Side.RIGHT, () -> Side.LEFT,
                side == Side.TOP, () -> Side.BOTTOM,
                side == Side.BOTTOM, () -> Side.TOP,
                side == Side.TOP_LEFT, () -> Side.TOP_RIGHT,
                side == Side.TOP_RIGHT, () -> Side.TOP_LEFT
        );

        assertEquals(result, Side.TOP_LEFT);
    }

    @Test
    public void matchExpressionWithDefaultTest() {
        /* 1 */
        Side side = Side.NONE;
        Side result;

        result = match(
                side == Side.LEFT, () -> Side.RIGHT,
                Else.class, () -> Side.NONE
        );

        assertEquals(result, Side.NONE);

        /* 2 */
        result = match(
                side == Side.LEFT, () -> Side.RIGHT,
                side == Side.RIGHT, () -> Side.LEFT,
                Else.class, () -> Side.NONE
        );

        assertEquals(result, Side.NONE);

        /* 3 */
        result = match(
                side == Side.LEFT, () -> Side.RIGHT,
                side == Side.RIGHT, () -> Side.LEFT,
                side == Side.TOP, () -> Side.BOTTOM,
                Else.class, () -> Side.NONE
        );

        assertEquals(result, Side.NONE);

        /* 4 */
        result = match(
                side == Side.LEFT, () -> Side.RIGHT,
                side == Side.RIGHT, () -> Side.LEFT,
                side == Side.TOP, () -> Side.BOTTOM,
                side == Side.BOTTOM, () -> Side.TOP,
                Else.class, () -> Side.NONE
        );

        assertEquals(result, Side.NONE);

        /* 5 */
        result = match(
                side == Side.LEFT, () -> Side.RIGHT,
                side == Side.RIGHT, () -> Side.LEFT,
                side == Side.TOP, () -> Side.BOTTOM,
                side == Side.BOTTOM, () -> Side.TOP,
                side == Side.TOP_LEFT, () -> Side.TOP_RIGHT,
                Else.class, () -> Side.NONE
        );

        assertEquals(result, Side.NONE);

        /* 6 */
        result = match(
                side == Side.LEFT, () -> Side.RIGHT,
                side == Side.RIGHT, () -> Side.LEFT,
                side == Side.TOP, () -> Side.BOTTOM,
                side == Side.BOTTOM, () -> Side.TOP,
                side == Side.TOP_LEFT, () -> Side.TOP_RIGHT,
                side == Side.TOP_RIGHT, () -> Side.TOP_LEFT,
                Else.class, () -> Side.NONE
        );

        assertEquals(result, Side.NONE);
    }
}
