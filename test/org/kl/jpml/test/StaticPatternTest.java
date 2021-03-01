/*
 * Licensed under the MIT License <http://opensource.org/licenses/MIT>.
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2019 - 2021 https://github.com/klappdev
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
import org.kl.jpml.util.Result;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kl.jpml.pattern.StaticPattern.matches;
import static org.kl.jpml.pattern.StaticPattern.of;

public class StaticPatternTest {

    @Test
    public void matchesStatementTest() {
        /* 1 */
        Figure figure = new Circle(5);

        matches(figure,
                Circle.class, of("deconstruct"), (Consumer<Object>) v -> out.println("circle: " + v)
        );

        /* 2 */
        figure = new Rectangle();

        matches(figure,
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> out.println("Rectangle square: " + (w * h))
        );

        /* 3 */
        figure = new Parallelepiped();

        matches(figure,
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 1 - 1 */
        figure = new Quadrate(10);

        matches(figure,
                Circle.class, of("deconstruct"), c -> out.println("circle: " + c),
                Quadrate.class, of("deconstruct"), (Consumer<Object>) a -> out.println("quadrate: " + a)
        );

        Optional<Integer> data1 = Optional.of(5);

        matches(data1,
                Optional::empty, () -> out.println("empty value"),
                Optional::get, v -> out.println("value: " + v)
        );

        Result<Integer, SQLException> data2 = Result.of(new SQLException());

        matches(data2,
                Result::error, e -> out.println("get error: " + e),
                Result::value, (Consumer<Integer>) v -> out.println("get value: " + v)
        );

        Result<Integer, ArithmeticException> data3 = Result.of((Supplier<Integer>) () -> 4 / 0);

        matches(data3,
                Result::error, e -> out.println("get error: " + e),
                Result::value, (Consumer<Integer>) v -> out.println("get value: " + v)
        );

        /* 1 - 2 */
        figure = new Rectangle();

        matches(figure,
                Circle.class, of("deconstruct"), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> out.println("Rectangle square: " + (w * h))
        );

        /* 2 - 1 */
        figure = new Triangle();

        matches(figure,
                Triangle.class, of("deconstruct"), (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Quadrate.class, of("deconstruct"), (Integer a) -> out.println("Quadrate  square: " + (a * a))
        );

        /* 2 - 2 */
        figure = new Rectangle();

        matches(figure,
                Triangle.class, of("deconstruct"), (Double w, Double h) -> out.println("Triangle  square: " + (w * h)),
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> out.println("Rectangle square: " + (w * h))
        );

        /* 1 - 3 */
        figure = new Parallelepiped();

        matches(figure,
                Circle.class, of("deconstruct"), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> out.println("piped square: " + 2 * (w * l + l * h + w * h))
        );

        /* 3 - 1 */
        figure = new Circle();

        matches(figure,
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> out.println("piped square: " + 2 * (w * l + l * h + w * h)),
                Circle.class, of("deconstruct"), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r)))
        );

        /* 3 - 3 */
        figure = new Tripiped();

        matches(figure,
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h)),
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> out.println("Tripiped square: " + 2 * (w * l + l * h + w * h))
        );

        /* 2 - 3 */
        figure = new Triangle();

        matches(figure,
                Triangle.class, of("deconstruct"), (Double w, Double h) -> out.println("Triangle  square: " + (w * h)),
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> out.println("Tripiped square: " + 2 * (w * l + l * h + w * h))
        );

        /* 3 - 2 */
        figure = new Tripiped();

        matches(figure,
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> out.println("Tripiped square: " + 2 * (w * l + l * h + w * h)),
                Triangle.class, of("deconstruct"), (Double w, Double h) -> out.println("Triangle  square: " + (w * h))
        );
    }

    @Test
    public void matchesAsStatementTest() {
        /* 1 */
        Figure figure = new Circle(5);

        matches(figure).as(
                Circle.class, of("deconstruct"), (Consumer<Object>) v -> out.println("circle: " + v)
        );

        /* 2 */
        figure = new Rectangle();

        matches(figure).as(
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> out.println("Rectangle square: " + (w * h))
        );

        /* 3 */
        figure = new Parallelepiped();

        matches(figure).as(
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 1 - 1 */
        figure = new Quadrate(10);

        matches(figure).as(
                Circle.class, of("deconstruct"), c -> out.println("circle: " + c),
                Quadrate.class, of("deconstruct"), (Consumer<Object>) a -> out.println("quadrate: " + a)
        );

        Optional<Integer> data1 = Optional.of(5);

        matches(data1).as(
                Optional::empty, () -> out.println("empty value"),
                Optional::get, v -> out.println("value: " + v)
        );

        Result<Integer, SQLException> data2 = Result.of(new SQLException());

        matches(data2).as(
                Result::error, e -> out.println("get error: " + e),
                Result::value, (Consumer<Integer>) v -> out.println("get value: " + v)
        );

        /* 1 - 2 */
        figure = new Rectangle();

        matches(figure).as(
                Circle.class, of("deconstruct"), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> out.println("Rectangle square: " + (w * h))
        );

        /* 2 - 1 */
        figure = new Triangle();

        matches(figure).as(
                Triangle.class, of("deconstruct"), (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Quadrate.class, of("deconstruct"), (Integer a) -> out.println("Quadrate  square: " + (a * a))
        );

        /* 2 - 2 */
        figure = new Rectangle();

        matches(figure).as(
                Triangle.class, of("deconstruct"), (Double w, Double h) -> out.println("Triangle  square: " + (w * h)),
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> out.println("Rectangle square: " + (w * h))
        );

        /* 1 - 3 */
        figure = new Parallelepiped();

        matches(figure).as(
                Circle.class, of("deconstruct"), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> out.println("piped square: " + 2 * (w * l + l * h + w * h))
        );

        /* 3 - 1 */
        figure = new Circle();

        matches(figure).as(
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> out.println("piped square: " + 2 * (w * l + l * h + w * h)),
                Circle.class, of("deconstruct"), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r)))
        );

        /* 3 - 3 */
        figure = new Tripiped();

        matches(figure).as(
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h)),
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> out.println("Tripiped square: " + 2 * (w * l + l * h + w * h))
        );

        /* 2 - 3 */
        figure = new Triangle();

        matches(figure).as(
                Triangle.class, of("deconstruct"), (Double w, Double h) -> out.println("Triangle  square: " + (w * h)),
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> out.println("Tripiped square: " + 2 * (w * l + l * h + w * h))
        );

        /* 3 - 2 */
        figure = new Tripiped();

        matches(figure).as(
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> out.println("Tripiped square: " + 2 * (w * l + l * h + w * h)),
                Triangle.class, of("deconstruct"), (Double w, Double h) -> out.println("Triangle  square: " + (w * h))
        );
    }

    @Test
    public void matchesExpressionTest() {
        /* 1 */
        Figure figure = new Circle(5);

        int result = matches(figure,
                Circle.class, of("deconstruct"), (Integer r) -> 2 * (r + r)
        );

        assertEquals(result, 20);

        /* 2 */
        figure = new Rectangle(5, 10);

        result = matches(figure,
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> w * h
        );

        assertEquals(result, 50);

        /* 3 */
        figure = new Parallelepiped((short) 5, (short) 5, (short) 5);

        result = matches(figure,
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> w * l * h
        );

        assertEquals(result, 125);

        /* 1 - 1 */
        figure = new Quadrate(10);

        result = matches(figure,
                Circle.class, of("deconstruct"), (Integer r) -> 2 * (r + r),
                Quadrate.class, of("deconstruct"), (Integer a) -> a * a
        );

        assertEquals(result, 100);

        Optional<Integer> data1 = Optional.of(5);

        result = matches(data1,
                Optional::empty, () -> 0,
                Optional::get, v -> v
        );

        assertEquals(result, 5);

        Result<Integer, SQLException> data2 = Result.of(new SQLException());

        result = matches(data2,
                Result::error, e -> -1,
                Result::value, v -> v
        );

        assertEquals(result, -1);

        /* 1 - 2 */
        figure = new Rectangle();

        result = matches(figure,
                Circle.class, of("deconstruct"), (Integer r) -> ((int) (2 * Math.PI * r)),
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        /* 2 - 1 */
        figure = new Triangle();

        result = matches(figure,
                Triangle.class, of("deconstruct"), (Double w, Double h) -> ((int) ((w * h))),
                Quadrate.class, of("deconstruct"), (Integer a) -> (a * a)
        );

        assertEquals(result, 50);

        /* 2 - 2 */
        figure = new Rectangle();

        result = matches(figure,
                Triangle.class, of("deconstruct"), (Double w, Double h) -> ((int) ((w * h))),
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        /* 1 - 3 */
        figure = new Parallelepiped();

        result = matches(figure,
                Circle.class, of("deconstruct"), (Integer r) -> ((int) (2 * Math.PI * r)),
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> 2 * (w * l + l * h + w * h)
        );

        assertEquals(result, 250);

        /* 3 - 1 */
        figure = new Circle();

        result = matches(figure,
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> 2 * (w * l + l * h + w * h),
                Circle.class, of("deconstruct"), (Integer r) -> ((int) (2 * Math.PI * r))
        );

        assertEquals(result, 31);

        /* 3 - 3 */
        figure = new Tripiped();

        result = matches(figure,
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> 2 * (w * l + l * h + w * h),
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> ((int) (2 * (w * l + l * h + w * h)))
        );

        assertEquals(result, 550);

        /* 2 - 3 */
        figure = new Triangle();

        result = matches(figure,
                Triangle.class, of("deconstruct"), (Double w, Double h) -> ((int) (w * h)),
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> ((int) (2 * (w * l + l * h + w * h)))
        );

        assertEquals(result, 50);

        /* 3 - 2 */
        figure = new Tripiped();

        result = matches(figure,
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> ((int) (2 * (w * l + l * h + w * h))),
                Triangle.class, of("deconstruct"), (Double w, Double h) -> ((int) (w * h))
        );

        assertEquals(result, 550);
    }

    @Test
    public void matchesAsExpressionTest() {
        /* 1 */
        Figure figure = new Circle(5);

        int result = matches(figure).as(
                Circle.class, of("deconstruct"), (Integer r) -> 2 * (r + r)
        );

        assertEquals(result, 20);

        /* 2 */
        figure = new Rectangle(5, 10);

        result = matches(figure).as(
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> w * h
        );

        assertEquals(result, 50);

        /* 3 */
        figure = new Parallelepiped((short) 5, (short) 5, (short) 5);

        result = matches(figure).as(
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> w * l * h
        );

        assertEquals(result, 125);

        /* 1 - 1 */
        figure = new Quadrate(10);

        result = matches(figure).as(
                Circle.class, of("deconstruct"), (Integer r) -> 2 * (r + r),
                Quadrate.class, of("deconstruct"), (Integer a) -> a * a
        );

        assertEquals(result, 100);

        Optional<Integer> data1 = Optional.of(5);

        result = matches(data1).as(
                Optional::empty, () -> 0,
                Optional::get, v -> 1
        );

        assertEquals(result, 1);

        Result<Integer, SQLException> data2 = Result.of(new SQLException());

        result = matches(data2).as(
                Result::error, e -> -1,
                Result::value, v -> 1
        );

        assertEquals(result, -1);

        /* 1 - 2 */
        figure = new Rectangle();

        result = matches(figure).as(
                Circle.class, of("deconstruct"), (Integer r) -> ((int) (2 * Math.PI * r)),
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        /* 2 - 1 */
        figure = new Triangle();

        result = matches(figure).as(
                Triangle.class, of("deconstruct"), (Double w, Double h) -> ((int) ((w * h))),
                Quadrate.class, of("deconstruct"), (Integer a) -> (a * a)
        );

        assertEquals(result, 50);

        /* 2 - 2 */
        figure = new Rectangle();

        result = matches(figure).as(
                Triangle.class, of("deconstruct"), (Double w, Double h) -> ((int) ((w * h))),
                Rectangle.class, of("deconstruct"), (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        /* 1 - 3 */
        figure = new Parallelepiped();

        result = matches(figure).as(
                Circle.class, of("deconstruct"), (Integer r) -> ((int) (2 * Math.PI * r)),
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> 2 * (w * l + l * h + w * h)
        );

        assertEquals(result, 250);

        /* 3 - 1 */
        figure = new Circle();

        result = matches(figure).as(
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> 2 * (w * l + l * h + w * h),
                Circle.class, of("deconstruct"), (Integer r) -> ((int) (2 * Math.PI * r))
        );

        assertEquals(result, 31);

        /* 3 - 3 */
        figure = new Tripiped();

        result = matches(figure).as(
                Parallelepiped.class, of("deconstruct"), (Short w, Short l, Short h) -> 2 * (w * l + l * h + w * h),
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> ((int) (2 * (w * l + l * h + w * h)))
        );

        assertEquals(result, 550);

        /* 2 - 3 */
        figure = new Triangle();

        result = matches(figure).as(
                Triangle.class, of("deconstruct"), (Double w, Double h) -> ((int) (w * h)),
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> ((int) (2 * (w * l + l * h + w * h)))
        );

        assertEquals(result, 50);

        /* 3 - 2 */
        figure = new Tripiped();

        result = matches(figure).as(
                Tripiped.class, of("deconstruct"), (Float w, Float l, Float h) -> ((int) (2 * (w * l + l * h + w * h))),
                Triangle.class, of("deconstruct"), (Double w, Double h) -> ((int) (w * h))
        );

        assertEquals(result, 550);
    }
}
