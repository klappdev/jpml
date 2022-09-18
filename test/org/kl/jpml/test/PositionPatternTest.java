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
import org.kl.jpml.test.shape.*;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kl.jpml.pattern.PositionPattern.*;

public class PositionPatternTest {

    @Test
    public void matchStatementTest() {
        Figure figure;

        /* 1 */
        figure = new Circle(5);

        match(figure,
                Circle.class, of(5), () -> out.println("figure circle")
        );

        /* 2 */
        figure = new Rectangle(5, 10);

        match(figure,
                Rectangle.class, of(5, 10), () -> out.println("figure rectangle")
        );

        /* 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        match(figure,
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> out.println("figure parallelepiped")
        );

        /* 1 - 1 */
        figure = new Quadrate(10);

        match(figure,
                Circle.class, of(5), () -> out.println("br 1 - figure circle "),
                Quadrate.class, of(10), () -> out.println("br 2 - figure quadrate ")
        );

        /* 1 - 2 */
        figure = new Rectangle(5, 10);

        match(figure,
                Circle.class, of(5), () -> out.println("br 1 - figure circle"),
                Rectangle.class, of(5, 10), () -> out.println("br 2 - figure rectangle")
        );

        /* 2 - 1 */
        figure = new Triangle(10D, 20D);

        match(figure,
                Triangle.class, of(10D, 20D), () -> out.println("br 1 - figure triangle"),
                Quadrate.class, of(10), () -> out.println("br 2 - figure quadrate ")
        );

        /* 2 - 2 */
        figure = new Rectangle(5, 10);

        match(figure,
                Triangle.class, of(10D, 20D), () -> out.println("br 1 - figure triangle"),
                Rectangle.class, of(5, 10), () -> out.println("br 2 - figure rectangle")
        );

        /* 1 - 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        match(figure,
                Circle.class, of(5), () -> out.println("br 1 - figure circle"),
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> out.println("br 2 - figure parallelepiped")
        );

        /* 3 - 1 */
        figure = new Circle(5);

        match(figure,
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> out.println("br 1 - figure parallelepiped"),
                Circle.class, of(5), () -> out.println("br 2 - figure circle")
        );

        /* 3 - 3 */
        figure = new Tripiped(10f, 5f, 10f);

        match(figure,
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> out.println("br 1 - figure parallelepiped"),
                Tripiped.class, of(10f, 5f, 10f), () -> out.println("br 2 - figure tripiped")
        );

        /* 2 - 3 */
        figure = new Triangle(10D, 20D);

        match(figure,
                Triangle.class, of(10D, 20D), () -> out.println("br 1 - figure triangle"),
                Tripiped.class, of(10f, 5f, 10f), () -> out.println("br 2 - figure tripiped")
        );

        /* 3 - 2 */
        figure = new Tripiped(10f, 5f, 10f);

        match(figure,
                Tripiped.class, of(10f, 5f, 10f), () -> out.println("br 1 - figure tripiped"),
                Triangle.class, of(10D, 20D), () -> out.println("br 2 - figure triangle")
        );
    }

    @Test
    public void matchAsStatementTest() {
        Figure figure;

        /* 1 */
        figure = new Circle(5);

        match(figure).as(
                Circle.class, of(5), () -> out.println("figure circle")
        );

        /* 2 */
        figure = new Rectangle(5, 10);

        match(figure).as(
                Rectangle.class, of(5, 10), () -> out.println("figure rectangle")
        );

        /* 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        match(figure).as(
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> out.println("figure parallelepiped")
        );

        /* 1 - 1 */
        figure = new Quadrate(10);

        match(figure).as(
                Circle.class, of(5), () -> out.println("br 1 - figure circle "),
                Quadrate.class, of(10), () -> out.println("br 2 - figure quadrate ")
        );

        /* 1 - 2 */
        figure = new Rectangle(5, 10);

        match(figure).as(
                Circle.class, of(5), () -> out.println("br 1 - figure circle"),
                Rectangle.class, of(5, 10), () -> out.println("br 2 - figure rectangle")
        );

        /* 2 - 1 */
        figure = new Triangle(10D, 20D);

        match(figure).as(
                Triangle.class, of(10D, 20D), () -> out.println("br 1 - figure triangle"),
                Quadrate.class, of(10), () -> out.println("br 2 - figure quadrate ")
        );

        /* 2 - 2 */
        figure = new Rectangle(5, 10);

        match(figure).as(
                Triangle.class, of(10D, 20D), () -> out.println("br 1 - figure triangle"),
                Rectangle.class, of(5, 10), () -> out.println("br 2 - figure rectangle")
        );

        /* 1 - 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        match(figure).as(
                Circle.class, of(5), () -> out.println("br 1 - figure circle"),
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> out.println("br 2 - figure parallelepiped")
        );

        /* 3 - 1 */
        figure = new Circle(5);

        match(figure).as(
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> out.println("br 1 - figure parallelepiped"),
                Circle.class, of(5), () -> out.println("br 2 - figure circle")
        );

        /* 3 - 3 */
        figure = new Tripiped(10f, 5f, 10f);

        match(figure).as(
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> out.println("br 1 - figure parallelepiped"),
                Tripiped.class, of(10f, 5f, 10f), () -> out.println("br 2 - figure tripiped")
        );

        /* 2 - 3 */
        figure = new Triangle(10D, 20D);

        match(figure).as(
                Triangle.class, of(10D, 20D), () -> out.println("br 1 - figure triangle"),
                Tripiped.class, of(10f, 5f, 10f), () -> out.println("br 2 - figure tripiped")
        );

        /* 3 - 2 */
        figure = new Tripiped(10f, 5f, 10f);

        match(figure).as(
                Tripiped.class, of(10f, 5f, 10f), () -> out.println("br 1 - figure tripiped"),
                Triangle.class, of(10D, 20D), () -> out.println("br 2 - figure triangle")
        );
    }

    @Test
    public void matchExpressionTest() {
        /* 1 */
        Figure figure = new Circle(5);

        int result = match(figure,
                Circle.class, of(5), () -> 5
        );

        assertEquals(result, 5);

        /* 2 */
        figure = new Rectangle(5, 10);

        result = match(figure,
                Rectangle.class, of(5, 10), () -> 10
        );

        assertEquals(result, 10);

        /* 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        result = match(figure,
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> 15
        );

        assertEquals(result, 15);

        /* 1 - 1 */
        figure = new Quadrate(10);

        result = match(figure,
                Circle.class, of(5), () -> 5,
                Quadrate.class, of(10), () -> 10
        );

        assertEquals(result, 10);

        /* 1 - 2 */
        figure = new Rectangle(5, 10);

        result = match(figure,
                Circle.class, of(5), () -> 5,
                Rectangle.class, of(5, 10), () -> 10
        );

        assertEquals(result, 10);

        /* 2 - 1 */
        figure = new Triangle(10D, 20D);

        result = match(figure,
                Triangle.class, of(10D, 20D), () -> 20,
                Quadrate.class, of(10), () -> 10
        );

        assertEquals(result, 20D);

        /* 2 - 2 */
        figure = new Rectangle(5, 10);

        result = match(figure,
                Triangle.class, of(10D, 20D), () -> 20,
                Rectangle.class, of(5, 10), () -> 10
        );

        assertEquals(result, 10);

        /* 1 - 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        result = match(figure,
                Circle.class, of(5), () -> 5,
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> 15
        );

        assertEquals(result, 15);

        /* 3 - 1 */
        figure = new Circle(5);

        result = match(figure,
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> 15,
                Circle.class, of(5), () -> 10
        );

        assertEquals(result, 10);

        /* 3 - 3 */
        figure = new Tripiped(10f, 5f, 10f);

        result = match(figure,
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> 15,
                Tripiped.class, of(10f, 5f, 10f), () -> 10
        );

        assertEquals(result, 10);

        /* 2 - 3 */
        figure = new Triangle(10D, 20D);

        result = match(figure,
                Triangle.class, of(10D, 20D), () -> 15,
                Tripiped.class, of(10f, 5f, 10f), () -> 10
        );

        assertEquals(result, 15);

        /* 3 - 2 */
        figure = new Tripiped(10f, 5f, 10f);

        result = match(figure,
                Tripiped.class, of(10f, 5f, 10f), () -> 10,
                Triangle.class, of(10D, 20D), () -> 15
        );

        assertEquals(result, 10);
    }

    @Test
    public void matchAsExpressionTest() {
        /* 1 */
        Figure figure = new Circle(5);

        int result = match(figure).as(
                Circle.class, of(5), () -> 5
        );

        assertEquals(result, 5);

        /* 2 */
        figure = new Rectangle(5, 10);

        result = match(figure).as(
                Rectangle.class, of(5, 10), () -> 10
        );

        assertEquals(result, 10);

        /* 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        result = match(figure).as(
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> 15
        );

        assertEquals(result, 15);

        /* 1 - 1 */
        figure = new Quadrate(10);

        result = match(figure).as(
                Circle.class, of(5), () -> 5,
                Quadrate.class, of(10), () -> 10
        );

        assertEquals(result, 10);

        /* 1 - 2 */
        figure = new Rectangle(5, 10);

        result = match(figure).as(
                Circle.class, of(5), () -> 5,
                Rectangle.class, of(5, 10), () -> 10
        );

        assertEquals(result, 10);

        /* 2 - 1 */
        figure = new Triangle(10D, 20D);

        result = match(figure).as(
                Triangle.class, of(10D, 20D), () -> 20,
                Quadrate.class, of(10), () -> 10
        );

        assertEquals(result, 20D);

        /* 2 - 2 */
        figure = new Rectangle(5, 10);

        result = match(figure).as(
                Triangle.class, of(10D, 20D), () -> 20,
                Rectangle.class, of(5, 10), () -> 10
        );

        assertEquals(result, 10);

        /* 1 - 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        result = match(figure).as(
                Circle.class, of(5), () -> 5,
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> 15
        );

        assertEquals(result, 15);

        /* 3 - 1 */
        figure = new Circle(5);

        result = match(figure).as(
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> 15,
                Circle.class, of(5), () -> 10
        );

        assertEquals(result, 10);

        /* 3 - 3 */
        figure = new Tripiped(10f, 5f, 10f);

        result = match(figure).as(
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> 15,
                Tripiped.class, of(10f, 5f, 10f), () -> 10
        );

        assertEquals(result, 10);

        /* 2 - 3 */
        figure = new Triangle(10D, 20D);

        result = match(figure).as(
                Triangle.class, of(10D, 20D), () -> 15,
                Tripiped.class, of(10f, 5f, 10f), () -> 10
        );

        assertEquals(result, 15);

        /* 3 - 2 */
        figure = new Tripiped(10f, 5f, 10f);

        result = match(figure).as(
                Tripiped.class, of(10f, 5f, 10f), () -> 10,
                Triangle.class, of(10D, 20D), () -> 15
        );

        assertEquals(result, 10);
    }
}
