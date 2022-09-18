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

import java.util.Optional;
import java.util.function.Consumer;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kl.jpml.pattern.StaticPattern.*;

public class StaticPatternTest {

    @Test
    public void matchStatementTest() {
        /* 1 */
        Figure figure = new Circle(5);

        match(figure,
                Circle.class, of("deconstruct"), (Consumer<Object>) v -> out.println("circle: " + v)
        );

        /* 2 */
        figure = new Rectangle();

        match(figure,
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> out.println("Rectangle square: " + (w * h))
        );

        /* 3 */
        figure = new Parallelepiped();

        match(figure,
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 1 - 1 */
        figure = new Quadrate(10);

        match(figure,
                Circle.class, of("deconstruct"), c -> out.println("circle: " + c),
                Quadrate.class, of("deconstruct"), (Consumer<Object>) a -> out.println("quadrate: " + a)
        );

        Optional<Integer> data1 = Optional.of(5);

        match(data1,
                Optional::empty, () -> out.println("empty value"),
                Optional::get, v -> out.println("value: " + v)
        );

        /* 1 - 2 */
        figure = new Rectangle();

        match(figure,
                Circle.class, of("deconstruct"), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> out.println("Rectangle square: " + (w * h))
        );

        /* 2 - 1 */
        figure = new Triangle();

        match(figure,
                Triangle.class, of("deconstruct"), (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Quadrate.class, of("deconstruct"), (Integer a) -> out.println("Quadrate  square: " + (a * a))
        );

        /* 2 - 2 */
        figure = new Rectangle();

        match(figure,
                Triangle.class, of("deconstruct"), (Double w, Double h) -> out.println("Triangle  square: " + (w * h)),
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> out.println("Rectangle square: " + (w * h))
        );

        /* 1 - 3 */
        figure = new Parallelepiped();

        match(figure,
                Circle.class, of("deconstruct"), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> out.println("piped square: " + 2 * (w * l + l * h + w * h))
        );

        /* 3 - 1 */
        figure = new Circle();

        match(figure,
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> out.println("piped square: " + 2 * (w * l + l * h + w * h)),
                Circle.class, of("deconstruct"), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r)))
        );

        /* 3 - 3 */
        figure = new Tripiped();

        match(figure,
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h)),
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> out.println("Tripiped square: " + 2 * (w * l + l * h + w * h))
        );

        /* 2 - 3 */
        figure = new Triangle();

        match(figure,
                Triangle.class, of("deconstruct"), (Double w, Double h) -> out.println("Triangle  square: " + (w * h)),
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> out.println("Tripiped square: " + 2 * (w * l + l * h + w * h))
        );

        /* 3 - 2 */
        figure = new Tripiped();

        match(figure,
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> out.println("Tripiped square: " + 2 * (w * l + l * h + w * h)),
                Triangle.class, of("deconstruct"), (Double w, Double h) -> out.println("Triangle  square: " + (w * h))
        );
    }

    @Test
    public void matchAsStatementTest() {
        /* 1 */
        Figure figure = new Circle(5);

        match(figure).as(
                Circle.class, of("deconstruct"), (Consumer<Object>) v -> out.println("circle: " + v)
        );

        /* 2 */
        figure = new Rectangle();

        match(figure).as(
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> out.println("Rectangle square: " + (w * h))
        );

        /* 3 */
        figure = new Parallelepiped();

        match(figure).as(
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 1 - 1 */
        figure = new Quadrate(10);

        match(figure).as(
                Circle.class, of("deconstruct"), c -> out.println("circle: " + c),
                Quadrate.class, of("deconstruct"), (Consumer<Object>) a -> out.println("quadrate: " + a)
        );

        Optional<Integer> data1 = Optional.of(5);

        match(data1).as(
                Optional::empty, () -> out.println("empty value"),
                Optional::get, v -> out.println("value: " + v)
        );

        /* 1 - 2 */
        figure = new Rectangle();

        match(figure).as(
                Circle.class, of("deconstruct"), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> out.println("Rectangle square: " + (w * h))
        );

        /* 2 - 1 */
        figure = new Triangle();

        match(figure).as(
                Triangle.class, of("deconstruct"), (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Quadrate.class, of("deconstruct"), (Integer a) -> out.println("Quadrate  square: " + (a * a))
        );

        /* 2 - 2 */
        figure = new Rectangle();

        match(figure).as(
                Triangle.class, of("deconstruct"), (Double w, Double h) -> out.println("Triangle  square: " + (w * h)),
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> out.println("Rectangle square: " + (w * h))
        );

        /* 1 - 3 */
        figure = new Parallelepiped();

        match(figure).as(
                Circle.class, of("deconstruct"), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> out.println("piped square: " + 2 * (w * l + l * h + w * h))
        );

        /* 3 - 1 */
        figure = new Circle();

        match(figure).as(
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> out.println("piped square: " + 2 * (w * l + l * h + w * h)),
                Circle.class, of("deconstruct"), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r)))
        );

        /* 3 - 3 */
        figure = new Tripiped();

        match(figure).as(
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h)),
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> out.println("Tripiped square: " + 2 * (w * l + l * h + w * h))
        );

        /* 2 - 3 */
        figure = new Triangle();

        match(figure).as(
                Triangle.class, of("deconstruct"), (Double w, Double h) -> out.println("Triangle  square: " + (w * h)),
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> out.println("Tripiped square: " + 2 * (w * l + l * h + w * h))
        );

        /* 3 - 2 */
        figure = new Tripiped();

        match(figure).as(
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> out.println("Tripiped square: " + 2 * (w * l + l * h + w * h)),
                Triangle.class, of("deconstruct"), (Double w, Double h) -> out.println("Triangle  square: " + (w * h))
        );
    }

    @Test
    public void matchExpressionTest() {
        /* 1 */
        Figure figure = new Circle(5);

        int result = match(figure,
                Circle.class, of("deconstruct"), (Integer r) -> 2 * (r + r)
        );

        assertEquals(result, 20);

        /* 2 */
        figure = new Rectangle(5, 10);

        result = match(figure,
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> w * h
        );

        assertEquals(result, 50);

        /* 3 */
        figure = new Parallelepiped((short) 5, (short) 5, (short) 5);

        result = match(figure,
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> w * l * h
        );

        assertEquals(result, 125);

        /* 1 - 1 */
        figure = new Quadrate(10);

        result = match(figure,
                Circle.class, of("deconstruct"), (Integer r) -> 2 * (r + r),
                Quadrate.class, of("deconstruct"), (Integer a) -> a * a
        );

        assertEquals(result, 100);

        Optional<Integer> data1 = Optional.of(5);

        result = match(data1,
                Optional::empty, () -> 0,
                Optional::get, v -> v
        );

        /* 1 - 2 */
        figure = new Rectangle();

        result = match(figure,
                Circle.class, of("deconstruct"), (Integer r) -> ((int) (2 * Math.PI * r)),
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        /* 2 - 1 */
        figure = new Triangle();

        result = match(figure,
                Triangle.class, of("deconstruct"), (Double w, Double h) -> ((int) ((w * h))),
                Quadrate.class, of("deconstruct"), (Integer a) -> (a * a)
        );

        assertEquals(result, 50);

        /* 2 - 2 */
        figure = new Rectangle();

        result = match(figure,
                Triangle.class, of("deconstruct"), (Double w, Double h) -> ((int) ((w * h))),
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        /* 1 - 3 */
        figure = new Parallelepiped();

        result = match(figure,
                Circle.class, of("deconstruct"), (Integer r) -> ((int) (2 * Math.PI * r)),
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> 2 * (w * l + l * h + w * h)
        );

        assertEquals(result, 250);

        /* 3 - 1 */
        figure = new Circle();

        result = match(figure,
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> 2 * (w * l + l * h + w * h),
                Circle.class, of("deconstruct"), (Integer r) -> ((int) (2 * Math.PI * r))
        );

        assertEquals(result, 31);

        /* 3 - 3 */
        figure = new Tripiped();

        result = match(figure,
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> 2 * (w * l + l * h + w * h),
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> ((int) (2 * (w * l + l * h + w * h)))
        );

        assertEquals(result, 550);

        /* 2 - 3 */
        figure = new Triangle();

        result = match(figure,
                Triangle.class, of("deconstruct"), (Double w, Double h) -> ((int) (w * h)),
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> ((int) (2 * (w * l + l * h + w * h)))
        );

        assertEquals(result, 50);

        /* 3 - 2 */
        figure = new Tripiped();

        result = match(figure,
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> ((int) (2 * (w * l + l * h + w * h))),
                Triangle.class, of("deconstruct"), (Double w, Double h) -> ((int) (w * h))
        );

        assertEquals(result, 550);
    }

    @Test
    public void matchAsExpressionTest() {
        /* 1 */
        Figure figure = new Circle(5);

        int result = match(figure).as(
                Circle.class, of("deconstruct"), (Integer r) -> 2 * (r + r)
        );

        assertEquals(result, 20);

        /* 2 */
        figure = new Rectangle(5, 10);

        result = match(figure).as(
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> w * h
        );

        assertEquals(result, 50);

        /* 3 */
        figure = new Parallelepiped((short) 5, (short) 5, (short) 5);

        result = match(figure).as(
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> w * l * h
        );

        assertEquals(result, 125);

        /* 1 - 1 */
        figure = new Quadrate(10);

        result = match(figure).as(
                Circle.class, of("deconstruct"), (Integer r) -> 2 * (r + r),
                Quadrate.class, of("deconstruct"), (Integer a) -> a * a
        );

        assertEquals(result, 100);

        Optional<Integer> data1 = Optional.of(5);

        result = match(data1).as(
                Optional::empty, () -> 0,
                Optional::get, v -> 1
        );

        assertEquals(result, 1);

        /* 1 - 2 */
        figure = new Rectangle();

        result = match(figure).as(
                Circle.class, of("deconstruct"), (Integer r) -> ((int) (2 * Math.PI * r)),
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        /* 2 - 1 */
        figure = new Triangle();

        result = match(figure).as(
                Triangle.class, of("deconstruct"), (Double w, Double h) -> ((int) ((w * h))),
                Quadrate.class, of("deconstruct"), (Integer a) -> (a * a)
        );

        assertEquals(result, 50);

        /* 2 - 2 */
        figure = new Rectangle();

        result = match(figure).as(
                Triangle.class, of("deconstruct"), (Double w, Double h) -> ((int) ((w * h))),
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        /* 1 - 3 */
        figure = new Parallelepiped();

        result = match(figure).as(
                Circle.class, of("deconstruct"), (Integer r) -> ((int) (2 * Math.PI * r)),
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> 2 * (w * l + l * h + w * h)
        );

        assertEquals(result, 250);

        /* 3 - 1 */
        figure = new Circle();

        result = match(figure).as(
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> 2 * (w * l + l * h + w * h),
                Circle.class, of("deconstruct"), (Integer r) -> ((int) (2 * Math.PI * r))
        );

        assertEquals(result, 31);

        /* 3 - 3 */
        figure = new Tripiped();

        result = match(figure).as(
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> 2 * (w * l + l * h + w * h),
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> ((int) (2 * (w * l + l * h + w * h)))
        );

        assertEquals(result, 550);

        /* 2 - 3 */
        figure = new Triangle();

        result = match(figure).as(
                Triangle.class, of("deconstruct"), (Double w, Double h) -> ((int) (w * h)),
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> ((int) (2 * (w * l + l * h + w * h)))
        );

        assertEquals(result, 50);

        /* 3 - 2 */
        figure = new Tripiped();

        result = match(figure).as(
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> ((int) (2 * (w * l + l * h + w * h))),
                Triangle.class, of("deconstruct"), (Double w, Double h) -> ((int) (w * h))
        );

        assertEquals(result, 550);
    }
}
