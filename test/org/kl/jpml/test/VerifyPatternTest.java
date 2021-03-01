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
import org.kl.jpml.lambda.Purchaser;
import org.kl.jpml.lambda.Routine;
import org.kl.jpml.test.shape.Circle;
import org.kl.jpml.test.shape.Figure;
import org.kl.jpml.test.shape.Rectangle;
import org.kl.jpml.state.Else;
import org.kl.jpml.state.Null;
import org.kl.jpml.state.Var;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kl.jpml.pattern.VerifyPattern.matches;

public class VerifyPatternTest {

    @Test
    public void matchesStatementTest() {
        /* 1 */
        byte value1 = 5;
        matches(value1,
                Byte.class,  b  -> { System.out.println(b * b); }
        );

        matches(value1,
                byte.class,  b -> { System.out.println("pc1: " + b * b * b); }
        );

        /* 2 */
        short value2 = 15;
        matches(value2,
                Byte.class,  b -> { System.out.println(b * b); },
                Short.class, s -> { System.out.println(s * s); }
        );

        matches(value2,
                byte.class,  b -> { System.out.println("pc2: " + b * b * b); },
                short.class, s -> { System.out.println("pc2: " + s * s * s); }
        );

        /* 3 */
        int value3 = 25;
        matches(value3,
                Byte.class,    b -> { System.out.println(b * b); },
                Short.class,   s -> { System.out.println(s * s); },
                Integer.class, i -> { System.out.println(i * i); }
        );

        matches(value3,
                byte.class,    b -> { System.out.println("pc3: " + b * b * b); },
                short.class,   s -> { System.out.println("pc3: " + s * s * s); },
                int.class,     i -> { System.out.println("pc3: " + i * i * i); }
        );

        /* 4 */
        long value4 = 35;
        matches(value4,
                Byte.class,    b -> { System.out.println(b * b); },
                Short.class,   s -> { System.out.println(s * s); },
                Integer.class, i -> { System.out.println(i * i); },
                Long.class,    l -> { System.out.println(l * l); }
        );

        matches(value4,
                byte.class,    b -> { System.out.println("pc4: " + b * b * b); },
                short.class,   s -> { System.out.println("pc4: " + s * s * s); },
                int.class,     i -> { System.out.println("pc4: " + i * i * i); },
                long.class,    l -> { System.out.println("pc4: " + l * l * l); }
        );

        /* 5 */
        float value5 = 45.0F;
        matches(value5,
                Byte.class,    b -> { System.out.println(b * b); },
                Short.class,   s -> { System.out.println(s * s); },
                Integer.class, i -> { System.out.println(i * i); },
                Long.class,    l -> { System.out.println(l * l); },
                Float.class,   f -> { System.out.println(f * f); }
        );

        matches(value5,
                byte.class,    b -> { System.out.println("pc5: " + b * b * b); },
                short.class,   s -> { System.out.println("pc5: " + s * s * s); },
                int.class,     i -> { System.out.println("pc5: " + i * i * i); },
                long.class,    l -> { System.out.println("pc5: " + l * l * l); },
                float.class,   f -> { System.out.println("pc5: " + f * f * f); }
        );

        /* 6 */
        double value6 = 55;
        matches(value6,
                Byte.class,    b -> { System.out.println(b * b); },
                Short.class,   s -> { System.out.println(s * s); },
                Integer.class, i -> { System.out.println(i * i); },
                Long.class,    l -> { System.out.println(l * l); },
                Float.class,   f -> { System.out.println(f * f); },
                Double.class,  d -> { System.out.println(d * d); }
        );

        matches(value6,
                byte.class,    b -> { System.out.println("pc6: " + b * b * b); },
                short.class,   s -> { System.out.println("pc6: " + s * s * s); },
                int.class,     i -> { System.out.println("pc6: " + i * i * i); },
                long.class,    l -> { System.out.println("pc6: " + l * l * l); },
                float.class,   f -> { System.out.println("pc6: " + f * f * f); },
                double.class,  d -> { System.out.println("pc6: " + d * d * d); }
        );

        /* 7 */
        Figure figure = new Rectangle();

        matches(figure,
                Rectangle.class, r -> { System.out.println("rect:   " + r.square()); },
                Circle.class,    c -> { System.out.println("circle: " + c.square()); }
        );
    }

    @Test
    public void matchesStatementWithDefaultTest() {
        String data = "unknown";

        /* 1 */
        matches(data,
                Byte.class,    b  -> System.out.println(b * b),
                Else.class, () -> System.out.println("Else value 1 type")
        );

        byte value1 = 1;
        matches(value1,
                byte.class,    b  -> System.out.println("pc1: " + b * b),
                Else.class, () -> System.out.println("Else value 1 type")
        );

        /* 2 */
        matches(data,
                Byte.class,  b -> System.out.println(b * b),
                Short.class, s -> System.out.println(s * s),
                Else.class, () -> System.out.println("Else value 2 types")
        );

        short value2 = 2;
        matches(value2,
                byte.class,  b -> System.out.println("pc2: " + b * b),
                short.class, s -> System.out.println("pc2: " + s * s),
                Else.class, () -> System.out.println("Else value 2 types")
        );

        /* 3 */
        matches(data,
                Byte.class,    b  -> System.out.println(b * b),
                Short.class,   s  -> System.out.println(s * s),
                Integer.class, i  -> System.out.println(i * i),
                Else.class, () -> System.out.println("Else value 3 types")
        );

        int value3 = 3;
        matches(value3,
                byte.class,    b  -> System.out.println("pc3: " + b * b),
                short.class,   s  -> System.out.println("pc3: " + s * s),
                int.class,     i  -> System.out.println("pc3: " + i * i),
                Else.class, () -> System.out.println("Else value 3 types")
        );

        /* 4 */
        matches(data,
                Byte.class,    b  -> System.out.println(b * b),
                Short.class,   s  -> System.out.println(s * s),
                Integer.class, i  -> System.out.println(i * i),
                Long.class,    l  -> System.out.println(l * l),
                Else.class, () -> System.out.println("Else value 4 types")
        );

        long value4 = 4;
        matches(value4,
                byte.class,    b  -> System.out.println("pc4: " + b * b),
                short.class,   s  -> System.out.println("pc4: " + s * s),
                int.class,     i  -> System.out.println("pc4: " + i * i),
                long.class,    l  -> System.out.println("pc4: " + l * l),
                Else.class, () -> System.out.println("Else value 4 types")
        );

        /* 5 */
        matches(data,
                Byte.class,    b -> System.out.println(b * b),
                Short.class,   s -> System.out.println(s * s),
                Integer.class, i -> System.out.println(i * i),
                Long.class,    l -> System.out.println(l * l),
                Float.class,   f -> System.out.println(f * f),
                Else.class, () -> System.out.println("Else value 5 types")
        );

        float value5 = 5;
        matches(value5,
                byte.class,    b -> System.out.println("pc5: " + b * b),
                short.class,   s -> System.out.println("pc5: " + s * s),
                int.class,     i -> System.out.println("pc5: " + i * i),
                long.class,    l -> System.out.println("pc5: " + l * l),
                float.class,   f -> System.out.println("pc5: " + f * f),
                Else.class, () -> System.out.println("Else value 5 types")
        );

        /* 6 */
        matches(data,
                Byte.class,    b -> System.out.println(b * b),
                Short.class,   s -> System.out.println(s * s),
                Integer.class, i -> System.out.println(i * i),
                Long.class,    l -> System.out.println(l * l),
                Float.class,   f -> System.out.println(f * f),
                Double.class,  d -> System.out.println(d * d),
                Else.class, () -> System.out.println("Else value 6 types")
        );

        double value6 = 6;
        matches(value6,
                byte.class,    b -> System.out.println("pc6: " + b * b * b),
                short.class,   s -> System.out.println("pc6: " + s * s * s),
                int.class,     i -> System.out.println("pc6: " + i * i * i),
                long.class,    l -> System.out.println("pc6: " + l * l * l),
                float.class,   f -> System.out.println("pc6: " + f * f * f),
                double.class,  d -> System.out.println("pc6: " + d * d * d),
                Else.class, () -> System.out.println("Else value 6 types")
        );

        /* 7 */
        matches(data,
                Rectangle.class, r  -> System.out.println("rect:   " + r.square()),
                Circle.class,    c  -> System.out.println("circle: " + c.square()),
                Else.class,   () -> System.out.println("Else shape")
        );
    }

    @Test
    public void matchesStatementWithVarTest() {
        String data = "unknown";

        /* 1 */
        matches(data,
                Byte.class, b -> System.out.println(b * b),
                Var.class, (Purchaser<String>) any -> System.out.println("Else value 1 type")
        );

        byte value1 = 1;
        matches(value1,
                byte.class, b -> System.out.println("pc1: " + b * b),
                Var.class, (Purchaser<Byte>) any -> System.out.println("Else value 1 type")
        );

        /* 2 */
        matches(data,
                Byte.class,  b -> System.out.println(b * b),
                Short.class, s -> System.out.println(s * s),
                Var.class, (Purchaser<String>) any -> System.out.println("Else value 2 types")
        );

        short value2 = 2;
        matches(value2,
                byte.class,  b -> System.out.println("pc2: " + b * b),
                short.class, s -> System.out.println("pc2: " + s * s),
                Var.class, (Purchaser<Short>) any -> System.out.println("Else value 2 types")
        );

        /* 3 */
        matches(data,
                Byte.class,    b  -> System.out.println(b * b),
                Short.class,   s  -> System.out.println(s * s),
                Integer.class, i  -> System.out.println(i * i),
                Var.class, (Purchaser<String>) any -> System.out.println("Else value 3 types")
        );

        int value3 = 3;
        matches(value3,
                byte.class,    b  -> System.out.println("pc3: " + b * b),
                short.class,   s  -> System.out.println("pc3: " + s * s),
                int.class,     i  -> System.out.println("pc3: " + i * i),
                Var.class, (Purchaser<Integer>) any -> System.out.println("Else value 3 types")
        );

        /* 4 */
        matches(data,
                Byte.class,    b  -> System.out.println(b * b),
                Short.class,   s  -> System.out.println(s * s),
                Integer.class, i  -> System.out.println(i * i),
                Long.class,    l  -> System.out.println(l * l),
                Var.class, (Purchaser<String>) any -> System.out.println("Else value 4 types")
        );

        long value4 = 4;
        matches(value4,
                byte.class,    b  -> System.out.println("pc4: " + b * b),
                short.class,   s  -> System.out.println("pc4: " + s * s),
                int.class,     i  -> System.out.println("pc4: " + i * i),
                long.class,    l  -> System.out.println("pc4: " + l * l),
                Var.class, (Purchaser<Long>) any -> System.out.println("Else value 4 types")
        );

        /* 5 */
        matches(data,
                Byte.class,    b -> System.out.println(b * b),
                Short.class,   s -> System.out.println(s * s),
                Integer.class, i -> System.out.println(i * i),
                Long.class,    l -> System.out.println(l * l),
                Float.class,   f -> System.out.println(f * f),
                Var.class, (Purchaser<String>) any -> System.out.println("Else value 5 types")
        );

        float value5 = 5;
        matches(value5,
                byte.class,    b -> System.out.println("pc5: " + b * b),
                short.class,   s -> System.out.println("pc5: " + s * s),
                int.class,     i -> System.out.println("pc5: " + i * i),
                long.class,    l -> System.out.println("pc5: " + l * l),
                float.class,   f -> System.out.println("pc5: " + f * f),
                Var.class, (Purchaser<Float>) any -> System.out.println("Else value 5 types")
        );

        /* 6 */
        matches(data,
                Byte.class,    b -> System.out.println(b * b),
                Short.class,   s -> System.out.println(s * s),
                Integer.class, i -> System.out.println(i * i),
                Long.class,    l -> System.out.println(l * l),
                Float.class,   f -> System.out.println(f * f),
                Double.class,  d -> System.out.println(d * d),
                Var.class, (Purchaser<String>) any -> System.out.println("Else value 6 types")
        );

        double value6 = 6;
        matches(value6,
                byte.class,    b -> System.out.println("pc6: " + b * b * b),
                short.class,   s -> System.out.println("pc6: " + s * s * s),
                int.class,     i -> System.out.println("pc6: " + i * i * i),
                long.class,    l -> System.out.println("pc6: " + l * l * l),
                float.class,   f -> System.out.println("pc6: " + f * f * f),
                double.class,  d -> System.out.println("pc6: " + d * d * d),
                Var.class, (Purchaser<Double>) any -> System.out.println("Else value 6 types")
        );

        /* 7 */
        matches(data,
                Rectangle.class, r  -> System.out.println("rect:   " + r.square()),
                Circle.class,    c  -> System.out.println("circle: " + c.square()),
                Var.class, (Purchaser<String>) any -> System.out.println("Else shape")
        );
    }

    @Test
    public void matchesStatementWithNullDefaultTest() {
        String data = "unknown";

        /* 1 */
        matches(null,
                Byte.class,  b -> { System.out.println(b * b); },
                Null.class, () -> { System.out.println("Null value 1 type"); },
                Else.class, () -> { System.out.println("Else value 1 type"); }
        );

        byte value1 = 1;
        matches(value1,
                byte.class,  b -> { System.out.println("pc1: " + b * b); },
                Null.class, () -> { System.out.println("Null value 1 type"); },
                Else.class, () -> { System.out.println("Else value 1 type"); }
        );

        /* 2 */
        matches(null,
                Byte.class,  b -> { System.out.println(b * b); },
                Short.class, s -> { System.out.println(s * s); },
                Null.class,    () -> { System.out.println("Null value 2 type"); },
                Else.class, () -> { System.out.println("Else value 2 types"); }
        );

        short value2 = 2;
        matches(value2,
                byte.class,  b -> System.out.println("pc2: " + b * b),
                short.class, s -> System.out.println("pc2: " + s * s),
                Null.class,    () -> { System.out.println("Null value 2 type"); },
                Else.class, () -> System.out.println("Else value 2 types")
        );

        /* 3 */
        matches(null,
                Byte.class,    b  -> { System.out.println(b * b); },
                Short.class,   s  -> { System.out.println(s * s); },
                Integer.class, i  -> { System.out.println(i * i); },
                Null.class,    () -> { System.out.println("Null value 3 type"); },
                Else.class, () -> { System.out.println("Else value 3 types"); }
        );

        int value3 = 3;
        matches(value3,
                byte.class,    b  -> { System.out.println("pc3: " + b * b); },
                short.class,   s  -> { System.out.println("pc3: " + s * s); },
                int.class,     i  -> { System.out.println("pc3: " + i * i); },
                Null.class,    () -> { System.out.println("Null value 3 type"); },
                Else.class, () -> { System.out.println("Else value 3 types"); }
        );

        /* 4 */
        matches(null,
                Byte.class,    b  -> { System.out.println(b * b); },
                Short.class,   s  -> { System.out.println(s * s); },
                Integer.class, i  -> { System.out.println(i * i); },
                Long.class,    l  -> { System.out.println(l * l); },
                Null.class,    () -> { System.out.println("Null value 4 type"); },
                Else.class, () -> { System.out.println("Else value 4 types"); }
        );

        long value4 = 4;
        matches(value4,
                byte.class,    b  -> { System.out.println("pc4: " + b * b); },
                short.class,   s  -> { System.out.println("pc4: " + s * s); },
                int.class,     i  -> { System.out.println("pc4: " + i * i); },
                long.class,    l  -> { System.out.println("pc4: " + l * l); },
                Null.class,    () -> { System.out.println("Null value 4 type"); },
                Else.class, () -> { System.out.println("Else value 4 types"); }
        );

        /* 5 */
        matches(null,
                Byte.class,    b -> { System.out.println(b * b); },
                Short.class,   s -> { System.out.println(s * s); },
                Integer.class, i -> { System.out.println(i * i); },
                Long.class,    l -> { System.out.println(l * l); },
                Float.class,   f -> { System.out.println(f * f); },
                Null.class,    () -> { System.out.println("Null value 5 type"); },
                Else.class, () -> { System.out.println("Else value 5 types"); }
        );

        float value5 = 5;
        matches(value5,
                byte.class,    b -> { System.out.println("pc5: " + b * b); },
                short.class,   s -> { System.out.println("pc5: " + s * s); },
                int.class,     i -> { System.out.println("pc5: " + i * i); },
                long.class,    l -> { System.out.println("pc5: " + l * l); },
                float.class,   f -> { System.out.println("pc5: " + f * f); },
                Null.class,    () -> { System.out.println("Null value 5 type"); },
                Else.class, () -> { System.out.println("Else value 5 types"); }
        );

        /* 6 */
        matches(null,
                Byte.class,    b -> { System.out.println(b * b); },
                Short.class,   s -> { System.out.println(s * s); },
                Integer.class, i -> { System.out.println(i * i); },
                Long.class,    l -> { System.out.println(l * l); },
                Float.class,   f -> { System.out.println(f * f); },
                Double.class,  d -> { System.out.println(d * d); },
                Null.class,    () -> { System.out.println("Null value 6 type"); },
                Else.class, () -> { System.out.println("Else value 6 types"); }
        );

        double value6 = 6;
        matches(value6,
                byte.class,    b -> { System.out.println("pc6: " + b * b * b); },
                short.class,   s -> { System.out.println("pc6: " + s * s * s); },
                int.class,     i -> { System.out.println("pc6: " + i * i * i); },
                long.class,    l -> { System.out.println("pc6: " + l * l * l); },
                float.class,   f -> { System.out.println("pc6: " + f * f * f); },
                double.class,  d -> { System.out.println("pc6: " + d * d * d); },
                Null.class,    () -> { System.out.println("Null value 6 type"); },
                Else.class, () -> { System.out.println("Else value 6 types"); }
        );

        /* 7 */
        matches(data,
                Rectangle.class, r  -> { System.out.println("rect:   " + r.square()); },
                Circle.class,    c  -> { System.out.println("circle: " + c.square()); },
                Null.class,      () -> { System.out.println("Null value shape"); },
                Else.class,   () -> { System.out.println("Else shape"); }
        );
    }

    @Test
    public void matchesStatementWithNullVarTest() {
        /* 1 */
        matches(null,
                Byte.class,  b -> System.out.println(b * b),
                Null.class, () -> System.out.println("Null value 1 type"),
                Var.class, any -> System.out.println("Else value 1 type")
        );

        byte value1 = 1;
        matches(value1,
                byte.class,  b -> System.out.println("pc1: " + b * b),
                Null.class, () -> System.out.println("Null value 1 type"),
                Var.class, any -> System.out.println("Else value 1 type")
        );

        /* 2 */
        matches(null,
                Byte.class,  b -> System.out.println(b * b),
                Short.class, s -> System.out.println(s * s),
                Null.class, () -> System.out.println("Null value 2 type"),
                Var.class, any -> System.out.println("Else value 2 types")
        );

        short value2 = 2;
        matches(value2,
                byte.class,  b -> System.out.println("pc2: " + b * b),
                short.class, s -> System.out.println("pc2: " + s * s),
                Null.class, () -> System.out.println("Null value 2 type"),
                Var.class, any -> System.out.println("Else value 2 types")
        );

        /* 3 */
        matches(null,
                Byte.class,    b  -> { System.out.println(b * b); },
                Short.class,   s  -> { System.out.println(s * s); },
                Integer.class, i  -> { System.out.println(i * i); },
                Null.class,    () -> { System.out.println("Null value 3 type"); },
                Var.class, any -> { System.out.println("Else value 3 types"); }
        );

        int value3 = 3;
        matches(value3,
                byte.class,   b  -> { System.out.println("pc3: " + b * b); },
                short.class,  s  -> { System.out.println("pc3: " + s * s); },
                int.class,    i  -> { System.out.println("pc3: " + i * i); },
                Null.class,   () -> { System.out.println("Null value 3 type"); },
                Var.class, any -> { System.out.println("Else value 3 types"); }
        );

        /* 4 */
        matches(null,
                Byte.class,    b  -> { System.out.println(b * b); },
                Short.class,   s  -> { System.out.println(s * s); },
                Integer.class, i  -> { System.out.println(i * i); },
                Long.class,    l  -> { System.out.println(l * l); },
                Null.class,    () -> { System.out.println("Null value 4 type"); },
                Var.class, any -> { System.out.println("Else value 4 types"); }
        );

        long value4 = 4;
        matches(value4,
                byte.class,    b  -> { System.out.println("pc4: " + b * b); },
                short.class,   s  -> { System.out.println("pc4: " + s * s); },
                int.class,     i  -> { System.out.println("pc4: " + i * i); },
                long.class,    l  -> { System.out.println("pc4: " + l * l); },
                Null.class,    () -> { System.out.println("Null value 4 type"); },
                Var.class, any -> { System.out.println("Else value 4 types"); }
        );

        /* 5 */
        matches(null,
                Byte.class,    b -> { System.out.println(b * b); },
                Short.class,   s -> { System.out.println(s * s); },
                Integer.class, i -> { System.out.println(i * i); },
                Long.class,    l -> { System.out.println(l * l); },
                Float.class,   f -> { System.out.println(f * f); },
                Null.class,    () -> { System.out.println("Null value 5 type"); },
                Var.class, any -> { System.out.println("Else value 5 types"); }
        );

        float value5 = 5;
        matches(value5,
                byte.class,    b -> { System.out.println("pc5: " + b * b); },
                short.class,   s -> { System.out.println("pc5: " + s * s); },
                int.class,     i -> { System.out.println("pc5: " + i * i); },
                long.class,    l -> { System.out.println("pc5: " + l * l); },
                float.class,   f -> { System.out.println("pc5: " + f * f); },
                Null.class,    () -> { System.out.println("Null value 5 type"); },
                Var.class, any -> { System.out.println("Else value 5 types"); }
        );

        /* 6 */
        matches(null,
                Byte.class,    b -> { System.out.println(b * b); },
                Short.class,   s -> { System.out.println(s * s); },
                Integer.class, i -> { System.out.println(i * i); },
                Long.class,    l -> { System.out.println(l * l); },
                Float.class,   f -> { System.out.println(f * f); },
                Double.class,  d -> { System.out.println(d * d); },
                Null.class,    () -> { System.out.println("Null value 6 type"); },
                Var.class, any -> { System.out.println("Else value 6 types"); }
        );

        double value6 = 6;
        matches(value6,
                byte.class,    b -> { System.out.println("pc6: " + b * b * b); },
                short.class,   s -> { System.out.println("pc6: " + s * s * s); },
                int.class,     i -> { System.out.println("pc6: " + i * i * i); },
                long.class,    l -> { System.out.println("pc6: " + l * l * l); },
                float.class,   f -> { System.out.println("pc6: " + f * f * f); },
                double.class,  d -> { System.out.println("pc6: " + d * d * d); },
                Null.class,    () -> { System.out.println("Null value 6 type"); },
                Var.class, any -> { System.out.println("Else value 6 types"); }
        );

        /* 7 */
        matches(null,
                Rectangle.class, r  -> { System.out.println("rect:   " + r.square()); },
                Circle.class,    c  -> { System.out.println("circle: " + c.square()); },
                Null.class,      () -> { System.out.println("Null value shape"); },
                Var.class, any -> { System.out.println("Else shape"); }
        );
    }

    @Test
    public void matchesExpressionTest() {
        /* 1 */
        byte value1 = 5;
        int result1 = matches(value1,
                Byte.class, b  -> { int result = 2 * (b + b);  return result; }
        );

        assertEquals(result1, 2 * (value1 + value1));

        /* 1.1 */
        result1 = matches(value1,
                byte.class, b  -> { int result = 2 * (b + b);  return result; }
        );

        assertEquals(result1, 2 * (value1 + value1));

        /* 2 */
        short value2 = 15;
        int result2 = matches(value2,
                Byte.class,  b -> { int result = 2 * (b + b);  return result; },
                Short.class, s -> { int result = 2 * (s + s);  return result; }
        );

        assertEquals(result2, 2 * (value2 + value2));

        /* 2.1 */
        result2 = matches(value2,
                byte.class,  b -> { int result = 2 * (b + b);  return result; },
                short.class, s -> { int result = 2 * (s + s);  return result; }
        );

        assertEquals(result2, 2 * (value2 + value2));

        /* 3 */
        int value3 = 25;
        int result3 = matches(value3,
                Byte.class,    b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> { int result = 2 * (i + i);  return result; }
        );

        assertEquals(result3, 2 * (value3 + value3));

        /* 3.1 */
        result3 = matches(value3,
                byte.class,    b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> { int result = 2 * (s + s);  return result; },
                int.class,     i -> { int result = 2 * (i + i);  return result; }
        );

        assertEquals(result3, 2 * (value3 + value3));

        /* 4 */
        long value4 = 35;
        int result4 = matches(value4,
                Byte.class,    b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> { int result = (int) (2 * (l + l));  return result; }
        );

        assertEquals(result4, 2 * (value4 + value4));

        /* 4.1 */
        result4 = matches(value4,
                byte.class,    b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> { int result = 2 * (s + s);  return result; },
                int.class,     i -> { int result = 2 * (i + i);  return result; },
                long.class,    l -> { int result = (int) (2 * (l + l));  return result; }
        );

        assertEquals(result4, 2 * (value4 + value4));

        /* 5 */
        long value5 = 45;
        int result5 = matches(value5,
                Byte.class,    b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                Float.class,   f -> { int result = (int) (2 * (f + f));  return result;}
        );

        assertEquals(result5, 2 * (value5 + value5));

        /* 5.1 */
        result5 = matches(value5,
                byte.class,    b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> { int result = 2 * (s + s);  return result; },
                int.class,     i -> { int result = 2 * (i + i);  return result; },
                long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                float.class,   f -> { int result = (int) (2 * (f + f));  return result;}
        );

        assertEquals(result5, 2 * (value5 + value5));

        /* 6 */
        long value6 = 45;
        int result6 = matches(value6,
                Byte.class,    b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                Float.class,   f -> { int result = (int) (2 * (f + f));  return result;},
                Double.class,  d -> { int result = (int) (2 * (d + d));  return result;}
        );

        assertEquals(result6, 2 * (value6 + value6));

        /* 6.1 */
        result6 = matches(value6,
                byte.class,    b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> { int result = 2 * (s + s);  return result; },
                int.class,     i -> { int result = 2 * (i + i);  return result; },
                long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                float.class,   f -> { int result = (int) (2 * (f + f));  return result;},
                double.class,  d -> { int result = (int) (2 * (d + d));  return result;}
        );

        assertEquals(result6, 2 * (value6 + value6));


        /* 7 */
        Figure figure = new Rectangle();

        int square = matches(figure,
                Rectangle.class, r -> { int result = r.square(); return result; },
                Circle.class,    c -> { int result = c.square(); return result; }
        );

        assertEquals(square, 50);
    }

    @Test
    public void matchesExpressionWithDefaultTest() {
        int data = 0;

        /* 1 */
        int result1 = matches(data,
                Byte.class,    b  -> { int result = 2 * (b + b);  return result; },
                Else.class, () -> { System.out.println("Else value 1 types"); return 0; }
        );

        assertEquals(result1, 0);

        byte value1 = 1;
        result1 = matches(value1,
                byte.class,    b  -> { int result = 2 * (b + b);  return result; },
                Else.class, () -> { System.out.println("Else value 1 types"); return 0; }
        );

        assertEquals(result1, 4);

        /* 2 */
        int result2 = matches(data,
                Byte.class,    b  -> { int result = 2 * (b + b);  return result; },
                Short.class,   s  -> { int result = 2 * (s + s);  return result; },
                Else.class, () -> { System.out.println("Else value 2 types"); return 1; }
        );

        assertEquals(result2, 1);

        short value2 = 2;
        result2 = matches(value2,
                byte.class,    b  -> { int result = 2 * (b + b);  return result; },
                short.class,   s  -> { int result = 2 * (s + s);  return result; },
                Else.class, () -> { System.out.println("Else value 2 types"); return 0; }
        );

        assertEquals(result2, 8);

        /* 3 */
        int result3 = matches((long) data,
                Byte.class,    b  -> { int result = 2 * (b + b);  return result; },
                Short.class,   s  -> { int result = 2 * (s + s);  return result; },
                Integer.class, i  -> { int result = 2 * (i + i);  return result; },
                Else.class, () -> { System.out.println("Else value 3 types"); return 2; }
        );

        assertEquals(result3, 2);

        int value3 = 3;
        result3 = matches(value3,
                byte.class,    b  -> { int result = 2 * (b + b);  return result; },
                short.class,   s  -> { int result = 2 * (s + s);  return result; },
                int.class,     i  -> { int result = 2 * (i + i);  return result; },
                Else.class, () -> { System.out.println("Else value 3 types"); return 0; }
        );

        assertEquals(result3, 12);

        /* 4 */
        int result4 = matches((float) data,
                Byte.class,    b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                Else.class, () -> { System.out.println("Else value 4 types"); return 4; }
        );

        assertEquals(result4, 4);

        long value4 = 4;
        result4 = matches(value4,
                byte.class,    b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> { int result = 2 * (s + s);  return result; },
                int.class,     i -> { int result = 2 * (i + i);  return result; },
                long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                Else.class, () -> { System.out.println("Else value 4 types"); return 0; }
        );

        assertEquals(result4, 16);

        /* 5 */
        int result5 = matches((double) data,
                Byte.class,    b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                Float.class,   f -> { int result = (int) (2 * (f + f));  return result;},
                Else.class, () -> { System.out.println("Else value 5 types"); return 5; }
        );
        assertEquals(result5, 5);

        float value5 = 5;
        result5 = matches(value5,
                byte.class,    b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> { int result = 2 * (s + s);  return result; },
                int.class,     i -> { int result = 2 * (i + i);  return result; },
                long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                float.class,   f -> { int result = (int) (2 * (f + f));  return result;},
                Else.class, () -> { System.out.println("Else value 5 types"); return 0; }
        );

        assertEquals(result5, 20);

        /* 6 */
        String buffer = "best";
        int result6 = matches(buffer,
                Byte.class,    b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                Float.class,   f -> { int result = (int) (2 * (f + f));  return result; },
                Double.class,  d -> { int result = (int) (2 * (d + d));  return result; },
                Else.class, () -> { System.out.println("Else value 6 types"); return 6; }
        );

        assertEquals(result6, 6);

        double value6 = 6;
        result6 = matches(value6,
                byte.class,    b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> { int result = 2 * (s + s);  return result; },
                int.class,     i -> { int result = 2 * (i + i);  return result; },
                long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                float.class,   f -> { int result = (int) (2 * (f + f));  return result; },
                double.class,  d -> { int result = (int) (2 * (d + d));  return result; },
                Else.class, () -> { System.out.println("Else value 6 types"); return 0; }
        );

        assertEquals(result6, 24);

        /* 7 */
        int square = matches(data,
                Rectangle.class, r -> { int result = r.square(); return result; },
                Circle.class,    c -> { int result = c.square(); return result; },
                Else.class,   () -> { System.out.println("Else shape"); return 5; }
        );

        assertEquals(square, 5);
    }

    @Test
    public void matchesExpressionWithVarTest() {
        int data = 0;

        /* 1 */
        int result1 = matches(data,
                Byte.class, b  -> { int result = 2 * (b + b);  return result; },
                Var.class, (Routine<Integer, Integer>) any -> { System.out.println("Else value 1 types"); return 0; }
        );

        assertEquals(result1, 0);

        byte value1 = 1;
        result1 = matches(value1,
                byte.class, b  -> { int result = 2 * (b + b);  return result; },
                Var.class, (Routine<Byte, Integer>) any -> { System.out.println("Else value 1 types"); return 0; }
        );

        assertEquals(result1, 4);

        /* 2 */
        int result2 = matches(data,
                Byte.class,    b  -> { int result = 2 * (b + b);  return result; },
                Short.class,   s  -> { int result = 2 * (s + s);  return result; },
                Var.class, (Routine<Integer, Integer>) any -> { System.out.println("Else value 2 types"); return 1; }
        );

        assertEquals(result2, 1);

        short value2 = 2;
        result2 = matches(value2,
                byte.class,    b  -> { int result = 2 * (b + b);  return result; },
                short.class,   s  -> { int result = 2 * (s + s);  return result; },
                Var.class, (Routine<Short, Integer>) any -> { System.out.println("Else value 2 types"); return 0; }
        );

        assertEquals(result2, 8);

        /* 3 */
        int result3 = matches((long) data,
                Byte.class,    b  -> { int result = 2 * (b + b);  return result; },
                Short.class,   s  -> { int result = 2 * (s + s);  return result; },
                Integer.class, i  -> { int result = 2 * (i + i);  return result; },
                Var.class, (Routine<Long, Integer>) any -> { System.out.println("Else value 3 types"); return 2; }
        );

        assertEquals(result3, 2);

        int value3 = 3;
        result3 = matches(value3,
                byte.class,    b  -> { int result = 2 * (b + b);  return result; },
                short.class,   s  -> { int result = 2 * (s + s);  return result; },
                int.class,     i  -> { int result = 2 * (i + i);  return result; },
                Var.class, (Routine<Integer, Integer>) any -> { System.out.println("Else value 3 types"); return 0; }
        );

        assertEquals(result3, 12);

        /* 4 */
        int result4 = matches((float) data,
                Byte.class,    b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                Var.class, (Routine<Float, Integer>) any -> { System.out.println("Else value 4 types"); return 4; }
        );

        assertEquals(result4, 4);

        long value4 = 4;
        result4 = matches(value4,
                byte.class,    b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> { int result = 2 * (s + s);  return result; },
                int.class,     i -> { int result = 2 * (i + i);  return result; },
                long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                Var.class, (Routine<Long, Integer>) any -> { System.out.println("Else value 4 types"); return 0; }
        );

        assertEquals(result4, 16);

        /* 5 */
        int result5 = matches((double) data,
                Byte.class,    b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                Float.class,   f -> { int result = (int) (2 * (f + f));  return result;},
                Var.class, (Routine<Double, Integer>) any -> { System.out.println("Else value 5 types"); return 5; }
        );
        assertEquals(result5, 5);

        float value5 = 5;
        result5 = matches(value5,
                byte.class,    b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> { int result = 2 * (s + s);  return result; },
                int.class,     i -> { int result = 2 * (i + i);  return result; },
                long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                float.class,   f -> { int result = (int) (2 * (f + f));  return result;},
                Var.class, (Routine<Float, Integer>) any -> { System.out.println("Else value 5 types"); return 0; }
        );

        assertEquals(result5, 20);

        /* 6 */
        String buffer = "best";
        int result6 = matches(buffer,
                Byte.class,    b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                Float.class,   f -> { int result = (int) (2 * (f + f));  return result; },
                Double.class,  d -> { int result = (int) (2 * (d + d));  return result; },
                Var.class, any -> { System.out.println("Else value 6 types"); return 6; }
        );

        assertEquals(result6, 6);

        double value6 = 6;
        result6 = matches(value6,
                byte.class,    b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> { int result = 2 * (s + s);  return result; },
                int.class,     i -> { int result = 2 * (i + i);  return result; },
                long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                float.class,   f -> { int result = (int) (2 * (f + f));  return result; },
                double.class,  d -> { int result = (int) (2 * (d + d));  return result; },
                Var.class, any -> { System.out.println("Else value 6 types"); return 0; }
        );

        assertEquals(result6, 24);

        /* 7 */
        int square = matches(data,
                Rectangle.class, r -> { int result = r.square(); return result; },
                Circle.class,    c -> { int result = c.square(); return result; },
                Var.class, (Routine<Integer, Integer>) any -> { System.out.println("Else shape"); return 5; }
        );

        assertEquals(square, 5);
	}

    @Test
    public void matchesExpressionWithNullDefaultTest() {
        /* 1 */
        int result1 = matches(null,
                Byte.class,    b  -> { int result = 2 * (b + b);  return result; },
                Null.class,    () -> { System.out.println("Null value 1 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 1 types"); return 0;   }
        );

        assertEquals(result1, -1);

        byte value1 = 1;
        result1 = matches(value1,
                byte.class,    b  -> { int result = 2 * (b + b);  return result; },
                Null.class,    () -> { System.out.println("Null value 1 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 1 types"); return 0;   }
        );

        assertEquals(result1, 4);

        /* 2 */
        int result2 = matches(null,
                Byte.class,    b  -> { int result = 2 * (b + b);  return result; },
                Short.class,   s  -> { int result = 2 * (s + s);  return result; },
                Null.class,    () -> { System.out.println("Null value 2 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 2 types"); return  1; }
        );

        assertEquals(result2, -1);

        short value2 = 2;
        result2 = matches(value2,
                byte.class,    b  -> { int result = 2 * (b + b);  return result; },
                short.class,   s  -> { int result = 2 * (s + s);  return result; },
                Null.class,    () -> { System.out.println("Null value 2 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 2 types"); return  0; }
        );

        assertEquals(result2, 8);

        /* 3 */
        int result3 = matches(null,
                Byte.class,    b  -> { int result = 2 * (b + b);  return result; },
                Short.class,   s  -> { int result = 2 * (s + s);  return result; },
                Integer.class, i  -> { int result = 2 * (i + i);  return result; },
                Null.class,    () -> { System.out.println("Null value 3 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 3 types"); return  2; }
        );

        assertEquals(result3, -1);

        int value3 = 3;
        result3 = matches(value3,
                byte.class,    b  -> { int result = 2 * (b + b);  return result; },
                short.class,   s  -> { int result = 2 * (s + s);  return result; },
                int.class,     i  -> { int result = 2 * (i + i);  return result; },
                Null.class,    () -> { System.out.println("Null value 3 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 3 types"); return  0; }
        );

        assertEquals(result3, 12);

        /* 4 */
        int result4 = matches(null,
                Byte.class,    b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                Null.class,    () -> { System.out.println("Null value 4 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 4 types"); return  4; }
        );

        assertEquals(result4, -1);

        long value4 = 4;
        result4 = matches(value4,
                byte.class,    b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> { int result = 2 * (s + s);  return result; },
                int.class,     i -> { int result = 2 * (i + i);  return result; },
                long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                Null.class,    () -> { System.out.println("Null value 4 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 4 types"); return  0; }
        );

        assertEquals(result4, 16);

        /* 5 */
        int result5 = matches(null,
                Byte.class,    b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                Float.class,   f -> { int result = (int) (2 * (f + f));  return result;},
                Null.class,    () -> { System.out.println("Null value 5 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 5 types"); return  5; }
        );
        assertEquals(result5, -1);

        float value5 = 5;
        result5 = matches(value5,
                byte.class,    b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> { int result = 2 * (s + s);  return result; },
                int.class,     i -> { int result = 2 * (i + i);  return result; },
                long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                float.class,   f -> { int result = (int) (2 * (f + f));  return result;},
                Null.class,    () -> { System.out.println("Null value 5 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 5 types"); return  0; }
        );

        assertEquals(result5, 20);

        /* 6 */
        int result6 = matches(null,
                Byte.class,    b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                Float.class,   f -> { int result = (int) (2 * (f + f));  return result; },
                Double.class,  d -> { int result = (int) (2 * (d + d));  return result; },
                Null.class,    () -> { System.out.println("Null value 6 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 6 types"); return  6; }
        );

        assertEquals(result6, -1);

        double value6 = 6;
        result6 = matches(value6,
                byte.class,    b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> { int result = 2 * (s + s);  return result; },
                int.class,     i -> { int result = 2 * (i + i);  return result; },
                long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                float.class,   f -> { int result = (int) (2 * (f + f));  return result; },
                double.class,  d -> { int result = (int) (2 * (d + d));  return result; },
                Null.class,    () -> { System.out.println("Null value 6 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 6 types"); return  0; }
        );

        assertEquals(result6, 24);

        /* 7 */
        int square = matches(null,
                Rectangle.class, r -> { int result = r.square(); return result; },
                Circle.class,    c -> { int result = c.square(); return result; },
                Null.class,      () -> { System.out.println("Null value 7 type"); return -1; },
                Else.class,   () -> { System.out.println("Else shape"); return 5; }
        );

        assertEquals(square, -1);
    }

    @Test
    public void matchesExpressionWithNullVarTest() {
        /* 1 */
        int result1 = matches(null,
                Byte.class, b  -> { int result = 2 * (b + b);  return result; },
                Null.class, () -> { System.out.println("Null value 1 type");     return -1; },
                Var.class, any -> { System.out.println("Else value 1 types"); return 0;  }
        );

        assertEquals(result1, -1);

        byte value1 = 1;
        result1 = matches(value1,
                byte.class, b  -> { int result = 2 * (b + b);  return result; },
                Null.class, () -> { System.out.println("Null value 1 type");     return -1; },
                Var.class, any -> { System.out.println("Else value 1 types"); return 0;  }
        );

        assertEquals(result1, 4);

        /* 2 */
        int result2 = matches(null,
                Byte.class, b  -> { int result = 2 * (b + b);  return result; },
                Short.class,s  -> { int result = 2 * (s + s);  return result; },
                Null.class, () -> { System.out.println("Null value 2 type");     return -1; },
                Var.class, any -> { System.out.println("Else value 2 types"); return  1; }
        );

        assertEquals(result2, -1);

        short value2 = 2;
        result2 = matches(value2,
                byte.class,  b  -> { int result = 2 * (b + b);  return result; },
                short.class, s  -> { int result = 2 * (s + s);  return result; },
                Null.class,  () -> { System.out.println("Null value 2 type");     return -1; },
                Var.class,  any -> { System.out.println("Else value 2 types"); return  0; }
        );

        assertEquals(result2, 8);

        /* 3 */
        int result3 = matches(null,
                Byte.class,    b  -> { int result = 2 * (b + b);  return result; },
                Short.class,   s  -> { int result = 2 * (s + s);  return result; },
                Integer.class, i  -> { int result = 2 * (i + i);  return result; },
                Null.class,    () -> { System.out.println("Null value 3 type");     return -1; },
                Var.class, any -> { System.out.println("Else value 3 types"); return  2; }
        );

        assertEquals(result3, -1);

        int value3 = 3;
        result3 = matches(value3,
                byte.class,  b  -> { int result = 2 * (b + b);  return result; },
                short.class, s  -> { int result = 2 * (s + s);  return result; },
                int.class,   i  -> { int result = 2 * (i + i);  return result; },
                Null.class,  () -> { System.out.println("Null value 3 type");     return -1; },
                Var.class, any -> { System.out.println("Else value 3 types"); return  0; }
        );

        assertEquals(result3, 12);

        /* 4 */
        int result4 = matches(null,
                Byte.class,    b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                Null.class,    () -> { System.out.println("Null value 4 type");     return -1; },
                Var.class, any -> { System.out.println("Else value 4 types"); return  4; }
        );

        assertEquals(result4, -1);

        long value4 = 4;
        result4 = matches(value4,
                byte.class,    b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> { int result = 2 * (s + s);  return result; },
                int.class,     i -> { int result = 2 * (i + i);  return result; },
                long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                Null.class,    () -> { System.out.println("Null value 4 type");     return -1; },
                Var.class, any -> { System.out.println("Else value 4 types"); return  0; }
        );

        assertEquals(result4, 16);

        /* 5 */
        int result5 = matches(null,
                Byte.class,    b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                Float.class,   f -> { int result = (int) (2 * (f + f));  return result;},
                Null.class,    () -> { System.out.println("Null value 5 type");     return -1; },
                Var.class, any -> { System.out.println("Else value 5 types"); return  5; }
        );
        assertEquals(result5, -1);

        float value5 = 5;
        result5 = matches(value5,
                byte.class,    b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> { int result = 2 * (s + s);  return result; },
                int.class,     i -> { int result = 2 * (i + i);  return result; },
                long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                float.class,   f -> { int result = (int) (2 * (f + f));  return result;},
                Null.class,    () -> { System.out.println("Null value 5 type");     return -1; },
                Var.class, any -> { System.out.println("Else value 5 types"); return  0; }
        );

        assertEquals(result5, 20);

        /* 6 */
        int result6 = matches(null,
                Byte.class,    b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                Float.class,   f -> { int result = (int) (2 * (f + f));  return result; },
                Double.class,  d -> { int result = (int) (2 * (d + d));  return result; },
                Null.class,    () -> { System.out.println("Null value 6 type");     return -1; },
                Var.class, any -> { System.out.println("Else value 6 types"); return  6; }
        );

        assertEquals(result6, -1);

        double value6 = 6;
        result6 = matches(value6,
                byte.class,    b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> { int result = 2 * (s + s);  return result; },
                int.class,     i -> { int result = 2 * (i + i);  return result; },
                long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                float.class,   f -> { int result = (int) (2 * (f + f));  return result; },
                double.class,  d -> { int result = (int) (2 * (d + d));  return result; },
                Null.class,    () -> { System.out.println("Null value 6 type");     return -1; },
                Var.class, any -> { System.out.println("Else value 6 types"); return  0; }
        );

        assertEquals(result6, 24);

        /* 7 */
        int square = matches(null,
                Rectangle.class, r -> { int result = r.square(); return result; },
                Circle.class,    c -> { int result = c.square(); return result; },
                Null.class,      () -> { System.out.println("Null value 7 type"); return -1; },
                Var.class, any -> { System.out.println("Else shape"); return 5; }
        );

        assertEquals(square, -1);
	}
}
