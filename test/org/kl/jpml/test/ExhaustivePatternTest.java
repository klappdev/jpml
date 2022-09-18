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

import org.kl.jpml.test.color.*;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kl.jpml.pattern.ExhaustivePattern.*;

public class ExhaustivePatternTest {

    @Test
    public void matchSealedStatementTest() {
        /* 1 */
        BiColor biColor = new BiColor.Red();

        match(biColor,
                BiColor.Red.class, r -> System.out.println("red subclass:  " + r),
                BiColor.Blue.class, (Consumer<BiColor.Blue>) b -> System.out.println("blue subclass: " + b)
        );

        /* 2 */
        TriColor triColor = new TriColor.Blue();

        match(triColor,
                TriColor.Red.class, r -> System.out.println("red subclass:  " + r),
                TriColor.Blue.class, b -> System.out.println("blue subclass: " + b),
                TriColor.White.class, (Consumer<TriColor.White>) w -> System.out.println("white subclass: " + w)
        );

        /* 3 */
        QuarColor quarColor = new QuarColor.Black();

        match(quarColor,
                QuarColor.Red.class, r -> System.out.println("red subclass:  " + r),
                QuarColor.Blue.class, b -> System.out.println("blue subclass: " + b),
                QuarColor.White.class, w -> System.out.println("white subclass: " + w),
                QuarColor.Black.class, (Consumer<QuarColor.Black>) b -> System.out.println("black subclass: " + b)
        );

        /* 4 */
        QuinColor quinColor = new QuinColor.Green();

        match(quinColor,
                QuinColor.Red.class, r -> System.out.println("red subclass:  " + r),
                QuinColor.Blue.class, b -> System.out.println("blue subclass: " + b),
                QuinColor.White.class, w -> System.out.println("white subclass: " + w),
                QuinColor.Black.class, b -> System.out.println("black subclass: " + b),
                QuinColor.Green.class, (Consumer<QuinColor.Green>) g -> System.out.println("green subclass: " + g)
        );

        /* 5 */
        SexColor sexColor = new SexColor.Yellow();

        match(sexColor,
                SexColor.Red.class, r -> System.out.println("red subclass:  " + r),
                SexColor.Blue.class, b -> System.out.println("blue subclass: " + b),
                SexColor.White.class, w -> System.out.println("white subclass: " + w),
                SexColor.Black.class, b -> System.out.println("black subclass: " + b),
                SexColor.Green.class, g -> System.out.println("green subclass: " + g),
                SexColor.Yellow.class, (Consumer<SexColor.Yellow>) y -> System.out.println("yellow subclass: " + y)
        );
    }

    @Test
    public void matchAsSealedStatementTest() {
        /* 1 */
        BiColor biColor = new BiColor.Red();

        match(biColor).as(
                BiColor.Red.class, r -> System.out.println("red subclass:  " + r),
                BiColor.Blue.class, (Consumer<BiColor.Blue>) b -> System.out.println("blue subclass: " + b)
        );

        /* 2 */
        TriColor triColor = new TriColor.Blue();

        match(triColor).as(
                TriColor.Red.class, r -> System.out.println("red subclass:  " + r),
                TriColor.Blue.class, b -> System.out.println("blue subclass: " + b),
                TriColor.White.class, (Consumer<TriColor.White>) w -> System.out.println("white subclass: " + w)
        );

        /* 3 */
        QuarColor quarColor = new QuarColor.Black();

        match(quarColor).as(
                QuarColor.Red.class, r -> System.out.println("red subclass:  " + r),
                QuarColor.Blue.class, b -> System.out.println("blue subclass: " + b),
                QuarColor.White.class, w -> System.out.println("white subclass: " + w),
                QuarColor.Black.class, (Consumer<QuarColor.Black>) b -> System.out.println("black subclass: " + b)
        );

        /* 4 */
        QuinColor quinColor = new QuinColor.Green();

        match(quinColor).as(
                QuinColor.Red.class, r -> System.out.println("red subclass:  " + r),
                QuinColor.Blue.class, b -> System.out.println("blue subclass: " + b),
                QuinColor.White.class, w -> System.out.println("white subclass: " + w),
                QuinColor.Black.class, b -> System.out.println("black subclass: " + b),
                QuinColor.Green.class, (Consumer<QuinColor.Green>) g -> System.out.println("green subclass: " + g)
        );

        /* 5 */
        SexColor sexColor = new SexColor.Yellow();

        match(sexColor).as(
                SexColor.Red.class, r -> System.out.println("red subclass:  " + r),
                SexColor.Blue.class, b -> System.out.println("blue subclass: " + b),
                SexColor.White.class, w -> System.out.println("white subclass: " + w),
                SexColor.Black.class, b -> System.out.println("black subclass: " + b),
                SexColor.Green.class, (Consumer<SexColor.Green>) g -> System.out.println("green subclass: " + g),
                SexColor.Yellow.class, y -> System.out.println("yellow subclass: " + y)
        );
    }

    @Test
    public void matchSealedExpressionTest() {
        /* 1 */
        BiColor biColor = new BiColor.Red();

        int result1 = match(biColor,
                BiColor.Red.class, r -> 0x1,
                BiColor.Blue.class, b -> 0x2
        );

        assertEquals(result1, 0x1);

        /* 2 */
        TriColor triColor = new TriColor.Blue();

        int result2 = match(triColor,
                TriColor.Red.class, r -> 0x1,
                TriColor.Blue.class, b -> 0x2,
                TriColor.White.class, w -> 0x3
        );

        assertEquals(result2, 0x2);

        /* 3 */
        QuarColor quarColor = new QuarColor.White();

        int result3 = match(quarColor,
                QuarColor.Red.class, r -> 0x1,
                QuarColor.Blue.class, b -> 0x2,
                QuarColor.White.class, w -> 0x3,
                QuarColor.Black.class, b -> 0x4
        );

        assertEquals(result3, 0x3);

        /* 4 */
        QuinColor quinColor = new QuinColor.Green();

        int result4 = match(quinColor,
                QuinColor.Red.class, r -> 0x1,
                QuinColor.Blue.class, b -> 0x2,
                QuinColor.White.class, w -> 0x3,
                QuinColor.Black.class, b -> 0x4,
                QuinColor.Green.class, g -> 0x5
        );

        assertEquals(result4, 0x5);

        /* 5 */
        SexColor sexColor = new SexColor.Yellow();

        int result5 = match(sexColor,
                SexColor.Red.class, r -> 0x1,
                SexColor.Blue.class, b -> 0x2,
                SexColor.White.class, w -> 0x3,
                SexColor.Black.class, b -> 0x4,
                SexColor.Green.class, g -> 0x5,
                SexColor.Yellow.class, y -> 0x6
        );

        assertEquals(result5, 0x6);
    }

    @Test
    public void matchAsSealedExpressionTest() {
        /* 1 */
        BiColor biColor = new BiColor.Red();

        int result1 = match(biColor).as(
                BiColor.Red.class, r -> 0x1,
                BiColor.Blue.class, b -> 0x2
        );

        assertEquals(result1, 0x1);

        /* 2 */
        TriColor triColor = new TriColor.Blue();

        int result2 = match(triColor).as(
                TriColor.Red.class, r -> 0x1,
                TriColor.Blue.class, b -> 0x2,
                TriColor.White.class, w -> 0x3
        );

        assertEquals(result2, 0x2);

        /* 3 */
        QuarColor quarColor = new QuarColor.White();

        int result3 = match(quarColor).as(
                QuarColor.Red.class, r -> 0x1,
                QuarColor.Blue.class, b -> 0x2,
                QuarColor.White.class, w -> 0x3,
                QuarColor.Black.class, b -> 0x4
        );

        assertEquals(result3, 0x3);

        /* 4 */
        QuinColor quinColor = new QuinColor.Green();

        int result4 = match(quinColor).as(
                QuinColor.Red.class, r -> 0x1,
                QuinColor.Blue.class, b -> 0x2,
                QuinColor.White.class, w -> 0x3,
                QuinColor.Black.class, b -> 0x4,
                QuinColor.Green.class, g -> 0x5
        );

        assertEquals(result4, 0x5);

        /* 5 */
        SexColor sexColor = new SexColor.Yellow();

        int result5 = match(sexColor).as(
                SexColor.Red.class, r -> 0x1,
                SexColor.Blue.class, b -> 0x2,
                SexColor.White.class, w -> 0x3,
                SexColor.Black.class, b -> 0x4,
                SexColor.Green.class, g -> 0x5,
                SexColor.Yellow.class, y -> 0x6
        );

        assertEquals(result5, 0x6);
    }
}
