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
import org.kl.jpml.state.Else;
import org.kl.jpml.state.Null;
import org.kl.jpml.state.Var;
import org.kl.jpml.test.shape.Circle;
import org.kl.jpml.test.shape.Figure;
import org.kl.jpml.test.shape.Unpiped;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kl.jpml.pattern.ConstantPattern.match;

public class ConstantPatternTest {

    @Test
    public void matchStatementTest() {
        /* 1 */
        byte value1 = 5;
        match(value1,
                (byte) 5, () -> System.out.println("branch 1 - " + value1)
        );

        Figure figure1 = new Circle(5);
        match(figure1,
                new Circle(5), () -> System.out.println("branch 1 - " + figure1)
        );

        /* 2 */
        short value2 = 10;
        match(value2,
                (short) 5, () -> System.out.println("branch 1 - " + value2),
                (short) 10, () -> System.out.println("branch 2 - " + value2)
        );

        Figure figure2 = new Circle(10);
        match(figure2,
                new Circle(5), () -> System.out.println("branch 1 - " + figure2),
                new Circle(10), () -> System.out.println("branch 2 - " + figure2)
        );

        /* 3 */
        int value3 = 15;

        match(value3,
                5, () -> System.out.println("branch 1 - " + value3),
                10, () -> System.out.println("branch 2 - " + value3),
                15, () -> System.out.println("branch 3 - " + value3)
        );

        Figure figure3 = new Unpiped(15);
        match(figure3,
                new Unpiped(5), () -> System.out.println("branch 1 - " + figure3),
                new Unpiped(10), () -> System.out.println("branch 2 - " + figure3),
                new Unpiped(15), () -> System.out.println("branch 3 - " + figure3)
        );

        /* 4 */
        long value4 = 20;
        match(value4,
                5L, () -> System.out.println("branch 1 - " + value4),
                10L, () -> System.out.println("branch 2 - " + value4),
                15L, () -> System.out.println("branch 3 - " + value4),
                20L, () -> System.out.println("branch 4 - " + value4)
        );

        Figure figure4 = new Unpiped(20);
        match(figure4,
                new Unpiped(5), () -> System.out.println("branch 1 - " + figure4),
                new Unpiped(10), () -> System.out.println("branch 2 - " + figure4),
                new Unpiped(15), () -> System.out.println("branch 3 - " + figure4),
                new Unpiped(20), () -> System.out.println("branch 4 - " + figure4)
        );

        /* 5 */
        float value5 = 25.0F;
        match(value5,
                5.0F, () -> System.out.println("branch 1 - " + value5),
                10.0F, () -> System.out.println("branch 2 - " + value5),
                15.0F, () -> System.out.println("branch 3 - " + value5),
                20.0F, () -> System.out.println("branch 4 - " + value5),
                25.0F, () -> System.out.println("branch 5 - " + value5)
        );

        /* 6 */
        double value6 = 30.0D;
        match(value6,
                5.0D, () -> System.out.println("branch 1 - " + value6),
                10.0D, () -> System.out.println("branch 2 - " + value6),
                15.0D, () -> System.out.println("branch 3 - " + value6),
                20.0D, () -> System.out.println("branch 4 - " + value6),
                25.0D, () -> System.out.println("branch 5 - " + value6),
                30.0D, () -> System.out.println("branch 6 - " + value6)
        );
    }

    @Test
    public void matchAsStatementTest() {
        /* 1 */
        byte value1 = 5;
        match(value1).as(
                (byte) 5, () -> System.out.println("safe branch 1 - " + value1)
        );

        Figure figure1 = new Circle(5);
        match(figure1).as(
                new Circle(5), () -> System.out.println("safe branch 1 - " + figure1)
        );

        /* 2 */
        short value2 = 10;
        match(value2).as(
                (short) 5, () -> System.out.println("safe branch 1 - " + value2),
                (short) 10, () -> System.out.println("safe branch 2 - " + value2)
        );

        Figure figure2 = new Circle(10);
        match(figure2).as(
                new Circle(5), () -> System.out.println("safe branch 1 - " + figure2),
                new Circle(10), () -> System.out.println("safe branch 2 - " + figure2)
        );

        /* 3 */
        int value3 = 15;

        match(value3).as(
                5, () -> System.out.println("safe branch 1 - " + value3),
                10, () -> System.out.println("safe branch 2 - " + value3),
                15, () -> System.out.println("safe branch 3 - " + value3)
        );

        Figure figure3 = new Unpiped(15);
        match(figure3).as(
                new Unpiped(5), () -> System.out.println("safe branch 1 - " + figure3),
                new Unpiped(10), () -> System.out.println("safe branch 2 - " + figure3),
                new Unpiped(15), () -> System.out.println("safe branch 3 - " + figure3)
        );

        /* 4 */
        long value4 = 20;
        match(value4).as(
                5L, () -> System.out.println("safe branch 1 - " + value4),
                10L, () -> System.out.println("safe branch 2 - " + value4),
                15L, () -> System.out.println("safe branch 3 - " + value4),
                20L, () -> System.out.println("safe branch 4 - " + value4)
        );

        Figure figure4 = new Unpiped(20);
        match(figure4).as(
                new Unpiped(5), () -> System.out.println("safe branch 1 - " + figure4),
                new Unpiped(10), () -> System.out.println("safe branch 2 - " + figure4),
                new Unpiped(15), () -> System.out.println("safe branch 3 - " + figure4),
                new Unpiped(20), () -> System.out.println("safe branch 4 - " + figure4)
        );

        /* 5 */
        float value5 = 25.0F;
        match(value5).as(
                5.0F, () -> System.out.println("safe branch 1 - " + value5),
                10.0F, () -> System.out.println("safe branch 2 - " + value5),
                15.0F, () -> System.out.println("safe branch 3 - " + value5),
                20.0F, () -> System.out.println("safe branch 4 - " + value5),
                25.0F, () -> System.out.println("safe branch 5 - " + value5)
        );

        /* 6 */
        double value6 = 30.0D;
        match(value6).as(
                5.0D, () -> System.out.println("safe branch 1 - " + value6),
                10.0D, () -> System.out.println("safe branch 2 - " + value6),
                15.0D, () -> System.out.println("safe branch 3 - " + value6),
                20.0D, () -> System.out.println("safe branch 4 - " + value6),
                25.0D, () -> System.out.println("safe branch 5 - " + value6),
                30.0D, () -> System.out.println("safe branch 6 - " + value6)
        );
    }

    @Test
    public void matchStatementWithDefaultTest() {
        /* 1 */
        byte value1 = 10;
        match(value1,
                (byte) 5, () -> System.out.println("branch 1 - " + value1),
                Else.class, () -> System.out.println("Else value 1 type")
        );

        /* 2 */
        short value2 = 15;
        match(value2,
                (short) 5, () -> System.out.println("branch 1 - " + value2),
                (short) 10, () -> System.out.println("branch 2 - " + value2),
                Else.class, () -> System.out.println("Else value 2 type")
        );

        /* 3 */
        int value3 = 20;
        match(value3,
                5, () -> System.out.println("branch 1 - " + value3),
                10, () -> System.out.println("branch 2 - " + value3),
                15, () -> System.out.println("branch 3 - " + value3),
                Else.class, () -> System.out.println("Else value 3 type")
        );

        /* 4 */
        long value4 = 25;
        match(value4,
                5L, () -> System.out.println("branch 1 - " + value4),
                10L, () -> System.out.println("branch 2 - " + value4),
                15L, () -> System.out.println("branch 3 - " + value4),
                20L, () -> System.out.println("branch 4 - " + value4),
                Else.class, () -> System.out.println("Else value 4 type")
        );

        /* 5 */
        float value5 = 30.0F;
        match(value5,
                5.0F, () -> System.out.println("branch 1 - " + value5),
                10.0F, () -> System.out.println("branch 2 - " + value5),
                15.0F, () -> System.out.println("branch 3 - " + value5),
                20.0F, () -> System.out.println("branch 4 - " + value5),
                25.0F, () -> System.out.println("branch 5 - " + value5),
                Else.class, () -> System.out.println("Else value 5 type")
        );

        /* 6 */
        double value6 = 35.0D;
        match(value6,
                5.0D, () -> System.out.println("branch 1 - " + value6),
                10.0D, () -> System.out.println("branch 2 - " + value6),
                15.0D, () -> System.out.println("branch 3 - " + value6),
                20.0D, () -> System.out.println("branch 4 - " + value6),
                25.0D, () -> System.out.println("branch 5 - " + value6),
                30.0D, () -> System.out.println("branch 6 - " + value6),
                Else.class, () -> System.out.println("Else value 6 type")
        );
    }

    @Test
    public void matchAsStatementWithDefaultTest() {
        /* 1 */
        byte value1 = 10;
        match(value1).as(
                (byte) 5, () -> System.out.println("safe branch 1 - " + value1),
                Else.class, () -> System.out.println("Else value 1 type")
        );

        /* 2 */
        short value2 = 15;
        match(value2).as(
                (short) 5, () -> System.out.println("safe branch 1 - " + value2),
                (short) 10, () -> System.out.println("safe branch 2 - " + value2),
                Else.class, () -> System.out.println("Else value 2 type")
        );

        /* 3 */
        int value3 = 20;
        match(value3).as(
                5, () -> System.out.println("safe branch 1 - " + value3),
                10, () -> System.out.println("safe branch 2 - " + value3),
                15, () -> System.out.println("safe branch 3 - " + value3),
                Else.class, () -> System.out.println("Else value 3 type")
        );

        /* 4 */
        long value4 = 25;
        match(value4).as(
                5L, () -> System.out.println("safe branch 1 - " + value4),
                10L, () -> System.out.println("safe branch 2 - " + value4),
                15L, () -> System.out.println("safe branch 3 - " + value4),
                20L, () -> System.out.println("safe branch 4 - " + value4),
                Else.class, () -> System.out.println("Else value 4 type")
        );

        /* 5 */
        float value5 = 30.0F;
        match(value5).as(
                5.0F, () -> System.out.println("safe branch 1 - " + value5),
                10.0F, () -> System.out.println("safe branch 2 - " + value5),
                15.0F, () -> System.out.println("safe branch 3 - " + value5),
                20.0F, () -> System.out.println("safe branch 4 - " + value5),
                25.0F, () -> System.out.println("safe branch 5 - " + value5),
                Else.class, () -> System.out.println("Else value 5 type")
        );

        /* 6 */
        double value6 = 35.0D;
        match(value6).as(
                5.0D, () -> System.out.println("safe branch 1 - " + value6),
                10.0D, () -> System.out.println("safe branch 2 - " + value6),
                15.0D, () -> System.out.println("safe branch 3 - " + value6),
                20.0D, () -> System.out.println("safe branch 4 - " + value6),
                25.0D, () -> System.out.println("safe branch 5 - " + value6),
                30.0D, () -> System.out.println("safe branch 6 - " + value6),
                Else.class, () -> System.out.println("Else value 6 type")
        );
    }

    @Test
    public void matchStatementWithVarTest() {
        /* 1 */
        byte value1 = 10;
        match(value1,
                (byte) 5, () -> System.out.println("branch 1 - " + value1),
                Var.class, any -> System.out.println("Var value 1 type")
        );

        /* 2 */
        short value2 = 15;
        match(value2,
                (short) 5, () -> System.out.println("branch 1 - " + value2),
                (short) 10, () -> System.out.println("branch 2 - " + value2),
                Var.class, any -> System.out.println("Var value 2 type")
        );

        /* 3 */
        int value3 = 20;
        match(value3,
                5, () -> System.out.println("branch 1 - " + value3),
                10, () -> System.out.println("branch 2 - " + value3),
                15, () -> System.out.println("branch 3 - " + value3),
                Var.class, any -> System.out.println("Var value 3 type")
        );

        /* 4 */
        long value4 = 25;
        match(value4,
                5L, () -> System.out.println("branch 1 - " + value4),
                10L, () -> System.out.println("branch 2 - " + value4),
                15L, () -> System.out.println("branch 3 - " + value4),
                20L, () -> System.out.println("branch 4 - " + value4),
                Var.class, any -> System.out.println("Var value 4 type")
        );

        /* 5 */
        float value5 = 30.0F;
        match(value5,
                5.0F, () -> System.out.println("branch 1 - " + value5),
                10.0F, () -> System.out.println("branch 2 - " + value5),
                15.0F, () -> System.out.println("branch 3 - " + value5),
                20.0F, () -> System.out.println("branch 4 - " + value5),
                25.0F, () -> System.out.println("branch 5 - " + value5),
                Var.class, any -> System.out.println("Var value 5 type")
        );

        /* 6 */
        double value6 = 35.0D;
        match(value6,
                5.0D, () -> System.out.println("branch 1 - " + value6),
                10.0D, () -> System.out.println("branch 2 - " + value6),
                15.0D, () -> System.out.println("branch 3 - " + value6),
                20.0D, () -> System.out.println("branch 4 - " + value6),
                25.0D, () -> System.out.println("branch 5 - " + value6),
                30.0D, () -> System.out.println("branch 6 - " + value6),
                Var.class, any -> System.out.println("Var value 6 type")
        );
    }

    @Test
    public void matchAsStatementWithVarTest() {
        /* 1 */
        byte value1 = 10;
        match(value1).as(
                (byte) 5, () -> System.out.println("safe branch 1 - " + value1),
                Var.class, any -> System.out.println("Var value 1 type")
        );

        /* 2 */
        short value2 = 15;
        match(value2).as(
                (short) 5, () -> System.out.println("safe branch 1 - " + value2),
                (short) 10, () -> System.out.println("safe branch 2 - " + value2),
                Var.class, any -> System.out.println("Var value 2 type")
        );

        /* 3 */
        int value3 = 20;
        match(value3).as(
                5, () -> System.out.println("safe branch 1 - " + value3),
                10, () -> System.out.println("safe branch 2 - " + value3),
                15, () -> System.out.println("safe branch 3 - " + value3),
                Var.class, any -> System.out.println("Var value 3 type")
        );

        /* 4 */
        long value4 = 25;
        match(value4).as(
                5L, () -> System.out.println("safe branch 1 - " + value4),
                10L, () -> System.out.println("safe branch 2 - " + value4),
                15L, () -> System.out.println("safe branch 3 - " + value4),
                20L, () -> System.out.println("safe  4 - " + value4),
                Var.class, any -> System.out.println("Var value 4 type")
        );

        /* 5 */
        float value5 = 30.0F;
        match(value5).as(
                5.0F, () -> System.out.println("safe branch 1 - " + value5),
                10.0F, () -> System.out.println("safe branch 2 - " + value5),
                15.0F, () -> System.out.println("safe branch 3 - " + value5),
                20.0F, () -> System.out.println("safe branch 4 - " + value5),
                25.0F, () -> System.out.println("safe branch 5 - " + value5),
                Var.class, any -> System.out.println("Var value 5 type")
        );

        /* 6 */
        double value6 = 35.0D;
        match(value6).as(
                5.0D, () -> System.out.println("safe branch 1 - " + value6),
                10.0D, () -> System.out.println("safe branch 2 - " + value6),
                15.0D, () -> System.out.println("safe branch 3 - " + value6),
                20.0D, () -> System.out.println("safe branch 4 - " + value6),
                25.0D, () -> System.out.println("safe branch 5 - " + value6),
                30.0D, () -> System.out.println("safe branch 6 - " + value6),
                Var.class, any -> System.out.println("Var value 6 type")
        );
    }

    @Test
    public void matchStatementWithNullDefaultTest() {
        /* 1 */
        match(null,
                (byte) 5, () -> System.out.println("branch 1"),
                Null.class, () -> System.out.println("Null value 1 type"),
                Else.class, () -> System.out.println("Else value 1 type")
        );

        /* 2 */
        match(null,
                (short) 5, () -> System.out.println("branch 1"),
                (short) 10, () -> System.out.println("branch 2"),
                Null.class, () -> System.out.println("Null value 2 type"),
                Else.class, () -> System.out.println("Else value 2 type")
        );

        /* 3 */
        match(null,
                5, () -> System.out.println("branch 1"),
                10, () -> System.out.println("branch 2"),
                15, () -> System.out.println("branch 3"),
                Null.class, () -> System.out.println("Null value 3 type"),
                Else.class, () -> System.out.println("Else value 3 type")
        );

        /* 4*/
        match(null,
                5L, () -> System.out.println("branch 1"),
                10L, () -> System.out.println("branch 2"),
                15L, () -> System.out.println("branch 3"),
                20L, () -> System.out.println("branch 4"),
                Null.class, () -> System.out.println("Null value 4 type"),
                Else.class, () -> System.out.println("Else value 4 type")
        );

        /* 5 */
        match(null,
                5.0F, () -> System.out.println("branch 1"),
                10.0F, () -> System.out.println("branch 2"),
                15.0F, () -> System.out.println("branch 3"),
                20.0F, () -> System.out.println("branch 4"),
                25.0F, () -> System.out.println("branch 5"),
                Null.class, () -> System.out.println("Null value 5 type"),
                Else.class, () -> System.out.println("Else value 5 type")
        );

        /* 6 */
        match(null,
                5.0D, () -> System.out.println("branch 1"),
                10.0D, () -> System.out.println("branch 2"),
                15.0D, () -> System.out.println("branch 3"),
                20.0D, () -> System.out.println("branch 4"),
                25.0D, () -> System.out.println("branch 5"),
                30.0D, () -> System.out.println("branch 6"),
                Null.class, () -> System.out.println("Null    value 6 type"),
                Else.class, () -> System.out.println("Else value 6 type")
        );
    }

    @Test
    public void matchAsStatementWithNullDefaultTest() {
        /* 1 */
        match(null).as(
                (byte) 5, () -> System.out.println("safe branch 1"),
                Null.class, () -> System.out.println("Null value 1 type"),
                Else.class, () -> System.out.println("Else value 1 type")
        );

        /* 2 */
        match(null).as(
                (short) 5, () -> System.out.println("safe branch 1"),
                (short) 10, () -> System.out.println("safe branch 2"),
                Null.class, () -> System.out.println("Null value 2 type"),
                Else.class, () -> System.out.println("Else value 2 type")
        );

        /* 3 */
        match(null).as(
                5, () -> System.out.println("safe branch 1"),
                10, () -> System.out.println("safe branch 2"),
                15, () -> System.out.println("safe branch 3"),
                Null.class, () -> System.out.println("Null value 3 type"),
                Else.class, () -> System.out.println("Else value 3 type")
        );

        /* 4*/
        match(null).as(
                5L, () -> System.out.println("safe branch 1"),
                10L, () -> System.out.println("safe branch 2"),
                15L, () -> System.out.println("safe branch 3"),
                20L, () -> System.out.println("safe branch 4"),
                Null.class, () -> System.out.println("Null value 4 type"),
                Else.class, () -> System.out.println("Else value 4 type")
        );

        /* 5 */
        match(null).as(
                5.0F, () -> System.out.println("safe branch 1"),
                10.0F, () -> System.out.println("safe branch 2"),
                15.0F, () -> System.out.println("safe branch 3"),
                20.0F, () -> System.out.println("safe branch 4"),
                25.0F, () -> System.out.println("safe branch 5"),
                Null.class, () -> System.out.println("Null value 5 type"),
                Else.class, () -> System.out.println("Else value 5 type")
        );

        /* 6 */
        match(null).as(
                5.0D, () -> System.out.println("safe branch 1"),
                10.0D, () -> System.out.println("safe branch 2"),
                15.0D, () -> System.out.println("safe branch 3"),
                20.0D, () -> System.out.println("safe branch 4"),
                25.0D, () -> System.out.println("safe branch 5"),
                30.0D, () -> System.out.println("safe branch 6"),
                Null.class, () -> System.out.println("Null value 6 type"),
                Else.class, () -> System.out.println("Else value 6 type")
        );
    }

    @Test
    public void matchStatementWithNullVarTest() {
        /* 1 */
        match(null,
                (byte) 5, () -> System.out.println("branch 1"),
                Null.class, () -> System.out.println("Null value 1 type"),
                Var.class, (Consumer<Byte>) any -> System.out.println("Var value 1 type")
        );

        /* 2 */
        match(null,
                (short) 5, () -> System.out.println("branch 1"),
                (short) 10, () -> System.out.println("branch 2"),
                Null.class, () -> System.out.println("Null value 2 type"),
                Var.class, (Consumer<Short>) any -> System.out.println("Var value 2 type")
        );

        /* 3 */
        match(null,
                5, () -> System.out.println("branch 1"),
                10, () -> System.out.println("branch 2"),
                15, () -> System.out.println("branch 3"),
                Null.class, () -> System.out.println("Null value 3 type"),
                Var.class, (Consumer<Integer>) any -> System.out.println("Var value 3 type")
        );

        /* 4*/
        match(null,
                5L, () -> System.out.println("branch 1"),
                10L, () -> System.out.println("branch 2"),
                15L, () -> System.out.println("branch 3"),
                20L, () -> System.out.println("branch 4"),
                Null.class, () -> System.out.println("Null value 4 type"),
                Var.class, (Consumer<Long>) any -> System.out.println("Var value 4 type")
        );

        /* 5 */
        match(null,
                5.0F, () -> System.out.println("branch 1"),
                10.0F, () -> System.out.println("branch 2"),
                15.0F, () -> System.out.println("branch 3"),
                20.0F, () -> System.out.println("branch 4"),
                25.0F, () -> System.out.println("branch 5"),
                Null.class, () -> System.out.println("Null value 5 type"),
                Var.class, (Consumer<Float>) any -> System.out.println("Var value 5 type")
        );

        /* 6 */
        match(null,
                5.0D, () -> System.out.println("branch 1"),
                10.0D, () -> System.out.println("branch 2"),
                15.0D, () -> System.out.println("branch 3"),
                20.0D, () -> System.out.println("branch 4"),
                25.0D, () -> System.out.println("branch 5"),
                30.0D, () -> System.out.println("branch 6"),
                Null.class, () -> System.out.println("Null value 6 type"),
                Var.class, (Consumer<Double>) any -> System.out.println("Var value 6 type")
        );
    }

    @Test
    public void matchAsStatementWithNullVarTest() {
        /* 1 */
        match(null).as(
                (byte) 5, () -> System.out.println("Safe branch 1"),
                Null.class, () -> System.out.println("Null value 1 type"),
                Var.class, (Consumer<Byte>) any -> System.out.println("Var value 1 type")
        );

        /* 2 */
        match(null).as(
                (short) 5, () -> System.out.println("Safe branch 1"),
                (short) 10, () -> System.out.println("Safe branch 2"),
                Null.class, () -> System.out.println("Null value 2 type"),
                Var.class, (Consumer<Short>) any -> System.out.println("Var value 2 type")
        );

        /* 3 */
        match(null).as(
                5, () -> System.out.println("Safe branch 1"),
                10, () -> System.out.println("Safe branch 2"),
                15, () -> System.out.println("Safe branch 3"),
                Null.class, () -> System.out.println("Null value 3 type"),
                Var.class, (Consumer<Integer>) any -> System.out.println("Var value 3 type")
        );

        /* 4*/
        match(null).as(
                5L, () -> System.out.println("Safe branch 1"),
                10L, () -> System.out.println("Safe branch 2"),
                15L, () -> System.out.println("Safe branch 3"),
                20L, () -> System.out.println("Safe branch 4"),
                Null.class, () -> System.out.println("Null value 4 type"),
                Var.class, (Consumer<Long>) any -> System.out.println("Var value 4 type")
        );

        /* 5 */
        match(null).as(
                5.0F, () -> System.out.println("Safe branch 1"),
                10.0F, () -> System.out.println("Safe branch 2"),
                15.0F, () -> System.out.println("Safe branch 3"),
                20.0F, () -> System.out.println("Safe branch 4"),
                25.0F, () -> System.out.println("Safe branch 5"),
                Null.class, () -> System.out.println("Null value 5 type"),
                Var.class, (Consumer<Float>) any -> System.out.println("Var value 5 type")
        );

        /* 6 */
        match(null).as(
                5.0D, () -> System.out.println("Safe branch 1"),
                10.0D, () -> System.out.println("Safe branch 2"),
                15.0D, () -> System.out.println("Safe branch 3"),
                20.0D, () -> System.out.println("Safe branch 4"),
                25.0D, () -> System.out.println("Safe branch 5"),
                30.0D, () -> System.out.println("Safe branch 6"),
                Null.class, () -> System.out.println("Safe Null value 6 type"),
                Var.class, (Consumer<Double>) any -> System.out.println("Var value 6 type")
        );
    }

    @Test
    public void matchExpressionTest() {
        /* 1 */
        byte value1 = 5;
        byte result1 = match(value1,
                (byte) 5, () -> {
                    byte result = (byte) (2 * (value1));
                    return result;
                }
        );

        assertEquals(result1, 2 * (value1));

        /* 2 */
        short value2 = 10;
        short result2 = match(value2,
                (short) 5, () -> {
                    short result = (short) (2 * (value2));
                    return result;
                },
                (short) 10, () -> {
                    short result = (short) (2 * (value2));
                    return result;
                }
        );

        assertEquals(result2, 2 * (value2));

        /* 3 */
        int value3 = 15;
        int result3 = match(value3,
                5, () -> {
                    int result = 2 * (value3);
                    return result;
                },
                10, () -> {
                    int result = 2 * (value3);
                    return result;
                },
                15, () -> {
                    int result = 2 * (value3);
                    return result;
                }
        );

        assertEquals(result3, 2 * (value3));

        /* 4 */
        long value4 = 20;
        long result4 = match(value4,
                5L, () -> {
                    long result = 2 * (value4);
                    return result;
                },
                10L, () -> {
                    long result = 2 * (value4);
                    return result;
                },
                15L, () -> {
                    long result = 2 * (value4);
                    return result;
                },
                20L, () -> {
                    long result = 2 * (value4);
                    return result;
                }
        );

        assertEquals(result4, 2 * (value4));

        /* 5 */
        float value5 = 25.0F;
        float result5 = match(value5,
                5.0F, () -> {
                    float result = 2 * (value5);
                    return result;
                },
                10.0F, () -> {
                    float result = 2 * (value5);
                    return result;
                },
                15.0F, () -> {
                    float result = 2 * (value5);
                    return result;
                },
                20.0F, () -> {
                    float result = 2 * (value5);
                    return result;
                },
                25.0F, () -> {
                    float result = 2 * (value5);
                    return result;
                }
        );

        assertEquals(result5, 2 * (value5));

        /* 6 */
        double value6 = 30.0;
        double result6 = match(value6,
                5.0D, () -> {
                    double result = 2 * (value6);
                    return result;
                },
                10.0D, () -> {
                    double result = 2 * (value6);
                    return result;
                },
                15.0D, () -> {
                    double result = 2 * (value6);
                    return result;
                },
                20.0D, () -> {
                    double result = 2 * (value6);
                    return result;
                },
                25.0D, () -> {
                    double result = 2 * (value6);
                    return result;
                },
                30.0D, () -> {
                    double result = 2 * (value6);
                    return result;
                }
        );

        assertEquals(result6, 2 * (value6));
    }

    @Test
    public void matchAsExpressionTest() {
        /* 1 */
        byte value1 = 5;
        byte result1 = match(value1).as(
                (byte) 5, () -> {
                    byte result = (byte) (2 * (value1));
                    return result;
                }
        );

        assertEquals(result1, 2 * (value1));

        /* 2 */
        short value2 = 10;
        short result2 = match(value2).as(
                (short) 5, () -> {
                    short result = (short) (2 * (value2));
                    return result;
                },
                (short) 10, () -> {
                    short result = (short) (2 * (value2));
                    return result;
                }
        );

        assertEquals(result2, 2 * (value2));

        /* 3 */
        int value3 = 15;
        int result3 = match(value3).as(
                5, () -> {
                    int result = 2 * (value3);
                    return result;
                },
                10, () -> {
                    int result = 2 * (value3);
                    return result;
                },
                15, () -> {
                    int result = 2 * (value3);
                    return result;
                }
        );

        assertEquals(result3, 2 * (value3));

        /* 4 */
        long value4 = 20;
        long result4 = match(value4).as(
                5L, () -> {
                    long result = 2 * (value4);
                    return result;
                },
                10L, () -> {
                    long result = 2 * (value4);
                    return result;
                },
                15L, () -> {
                    long result = 2 * (value4);
                    return result;
                },
                20L, () -> {
                    long result = 2 * (value4);
                    return result;
                }
        );

        assertEquals(result4, 2 * (value4));

        /* 5 */
        float value5 = 25.0F;
        float result5 = match(value5).as(
                5.0F, () -> {
                    float result = 2 * (value5);
                    return result;
                },
                10.0F, () -> {
                    float result = 2 * (value5);
                    return result;
                },
                15.0F, () -> {
                    float result = 2 * (value5);
                    return result;
                },
                20.0F, () -> {
                    float result = 2 * (value5);
                    return result;
                },
                25.0F, () -> {
                    float result = 2 * (value5);
                    return result;
                }
        );

        assertEquals(result5, 2 * (value5));

        /* 6 */
        double value6 = 30.0;
        double result6 = match(value6).as(
                5.0D, () -> {
                    double result = 2 * (value6);
                    return result;
                },
                10.0D, () -> {
                    double result = 2 * (value6);
                    return result;
                },
                15.0D, () -> {
                    double result = 2 * (value6);
                    return result;
                },
                20.0D, () -> {
                    double result = 2 * (value6);
                    return result;
                },
                25.0D, () -> {
                    double result = 2 * (value6);
                    return result;
                },
                30.0D, () -> {
                    double result = 2 * (value6);
                    return result;
                }
        );

        assertEquals(result6, 2 * (value6));
    }

    @Test
    public void matchExpressionWithDefaultTest() {
        /* 1 */
        byte value1 = 10;
        byte result1 = match(value1,
                (byte) 5, () -> (byte) (2 * (value1)),
                Else.class, (Supplier<Byte>) () -> (byte) 0
        );

        assertEquals(result1, 0);

        /* 2 */
        short value2 = 15;
        short result2 = match(value2,
                (short) 5, () -> (short) (2 * (value2)),
                (short) 10, () -> (short) (2 * (value2)),
                Else.class, (Supplier<Short>) () -> (short) 0
        );

        assertEquals(result2, 0);

        /* 3 */
        int value3 = 20;
        int result3 = match(value3,
                5, () -> 2 * (value3),
                10, () -> 2 * (value3),
                15, () -> 2 * (value3),
                Else.class, (Supplier<Integer>) () -> 0
        );

        assertEquals(result3, 0);

        /* 4 */
        long value4 = 25;
        long result4 = match(value4,
                5L, () -> 2 * (value4),
                10L, () -> 2 * (value4),
                15L, () -> 2 * (value4),
                20L, () -> 2 * (value4),
                Else.class, (Supplier<Long>) () -> (long) 0
        );

        assertEquals(result4, 0);

        /* 5 */
        float value5 = 30.0F;
        float result5 = match(value5,
                5.0F, () -> 2 * (value5),
                10.0F, () -> 2 * (value5),
                15.0F, () -> 2 * (value5),
                20.0F, () -> 2 * (value5),
                25.0F, () -> 2 * (value5),
                Else.class, (Supplier<Float>) () -> (float) 0
        );

        assertEquals(result5, 0);

        /* 6 */
        double value6 = 35.0D;
        double result6 = match(value6,
                5.0D, () -> 2 * (value6),
                10.0D, () -> 2 * (value6),
                15.0D, () -> 2 * (value6),
                20.0D, () -> 2 * (value6),
                25.0D, () -> 2 * (value6),
                30.0D, () -> 2 * (value6),
                Else.class, () -> (double) 0
        );

        assertEquals(result6, 0);
    }

    @Test
    public void matchAsExpressionWithDefaultTest() {
        /* 1 */
        byte value1 = 10;
        byte result1 = match(value1).as(
                (byte) 5, () -> (byte) (2 * (value1)),
                Else.class, (Supplier<Byte>) () -> (byte) 0
        );

        assertEquals(result1, 0);

        /* 2 */
        short value2 = 15;
        short result2 = match(value2).as(
                (short) 5, () -> (short) (2 * (value2)),
                (short) 10, () -> (short) (2 * (value2)),
                Else.class, (Supplier<Short>) () -> (short) 0
        );

        assertEquals(result2, 0);

        /* 3 */
        int value3 = 20;
        int result3 = match(value3).as(
                5, () -> 2 * (value3),
                10, () -> 2 * (value3),
                15, () -> 2 * (value3),
                Else.class, (Supplier<Integer>) () -> 0
        );

        assertEquals(result3, 0);

        /* 4 */
        long value4 = 25;
        long result4 = match(value4).as(
                5L, () -> 2 * (value4),
                10L, () -> 2 * (value4),
                15L, () -> 2 * (value4),
                20L, () -> 2 * (value4),
                Else.class, (Supplier<Long>) () -> (long) 0
        );

        assertEquals(result4, 0);

        /* 5 */
        float value5 = 30.0F;
        float result5 = match(value5).as(
                5.0F, () -> 2 * (value5),
                10.0F, () -> 2 * (value5),
                15.0F, () -> 2 * (value5),
                20.0F, () -> 2 * (value5),
                25.0F, () -> 2 * (value5),
                Else.class, (Supplier<Float>) () -> (float) 0
        );

        assertEquals(result5, 0);

        /* 6 */
        double value6 = 35.0D;
        double result6 = match(value6).as(
                5.0D, () -> 2 * (value6),
                10.0D, () -> 2 * (value6),
                15.0D, () -> 2 * (value6),
                20.0D, () -> 2 * (value6),
                25.0D, () -> 2 * (value6),
                30.0D, () -> 2 * (value6),
                Else.class, () -> (double) 0
        );

        assertEquals(result6, 0);
    }

    @Test
    public void matchExpressionWithVarTest() {
        /* 1 */
        byte value1 = 10;
        byte result1 = match(value1,
                (byte) 5, () -> (byte) (2 * (value1)),
                Var.class, any -> (byte) -1
        );

        assertEquals(result1, -1);

        /* 2 */
        short value2 = 15;
        short result2 = match(value2,
                (short) 5, () -> (short) (2 * (value2)),
                (short) 10, () -> (short) (2 * (value2)),
                Var.class, any -> (short) -1
        );

        assertEquals(result2, -1);

        /* 3 */
        int value3 = 20;
        int result3 = match(value3,
                5, () -> 2 * (value3),
                10, () -> 2 * (value3),
                15, () -> 2 * (value3),
                Var.class, any -> -1
        );

        assertEquals(result3, -1);

        /* 4 */
        long value4 = 25;
        long result4 = match(value4,
                5L, () -> 2 * (value4),
                10L, () -> 2 * (value4),
                15L, () -> 2 * (value4),
                20L, () -> 2 * (value4),
                Var.class, any -> (long) -1
        );

        assertEquals(result4, -1);

        /* 5 */
        float value5 = 30.0F;
        float result5 = match(value5,
                5.0F, () -> 2 * (value5),
                10.0F, () -> 2 * (value5),
                15.0F, () -> 2 * (value5),
                20.0F, () -> 2 * (value5),
                25.0F, () -> 2 * (value5),
                Var.class, any -> (float) -1
        );

        assertEquals(result5, -1);

        /* 6 */
        double value6 = 35.0D;
        double result6 = match(value6,
                5.0D, () -> 2 * (value6),
                10.0D, () -> 2 * (value6),
                15.0D, () -> 2 * (value6),
                20.0D, () -> 2 * (value6),
                25.0D, () -> 2 * (value6),
                30.0D, () -> 2 * (value6),
                Var.class, any -> (double) -1
        );

        assertEquals(result6, -1);
    }

    @Test
    public void matchAsExpressionWithVarTest() {
        /* 1 */
        byte value1 = 10;
        byte result1 = match(value1).as(
                (byte) 5, () -> (byte) (2 * (value1)),
                Var.class, any -> (byte) -1
        );

        assertEquals(result1, -1);

        /* 2 */
        short value2 = 15;
        short result2 = match(value2).as(
                (short) 5, () -> (short) (2 * (value2)),
                (short) 10, () -> (short) (2 * (value2)),
                Var.class, any -> (short) -1
        );

        assertEquals(result2, -1);

        /* 3 */
        int value3 = 20;
        int result3 = match(value3).as(
                5, () -> 2 * (value3),
                10, () -> 2 * (value3),
                15, () -> 2 * (value3),
                Var.class, any -> -1
        );

        assertEquals(result3, -1);

        /* 4 */
        long value4 = 25;
        long result4 = match(value4).as(
                5L, () -> 2 * (value4),
                10L, () -> 2 * (value4),
                15L, () -> 2 * (value4),
                20L, () -> 2 * (value4),
                Var.class, any -> (long) -1
        );

        assertEquals(result4, -1);

        /* 5 */
        float value5 = 30.0F;
        float result5 = match(value5).as(
                5.0F, () -> 2 * (value5),
                10.0F, () -> 2 * (value5),
                15.0F, () -> 2 * (value5),
                20.0F, () -> 2 * (value5),
                25.0F, () -> 2 * (value5),
                Var.class, any -> (float) -1
        );

        assertEquals(result5, -1);

        /* 6 */
        double value6 = 35.0D;
        double result6 = match(value6).as(
                5.0D, () -> 2 * (value6),
                10.0D, () -> 2 * (value6),
                15.0D, () -> 2 * (value6),
                20.0D, () -> 2 * (value6),
                25.0D, () -> 2 * (value6),
                30.0D, () -> 2 * (value6),
                Var.class, any -> (double) -1
        );

        assertEquals(result6, -1);
    }

    @Test
    public void matchExpressionWithNullDefaultTest() {
        /* 1 */
        byte result1 = match(null,
                (byte) 5, () -> (byte) 1,
                Null.class, () -> (byte) -1,
                Else.class, (Supplier<Byte>) () -> (byte) 0
        );

        assertEquals(result1, -1);

        /* 2 */
        short result2 = match(null,
                (short) 5, () -> (short) 1,
                (short) 10, () -> (short) 2,
                Null.class, () -> (short) -1,
                Else.class, (Supplier<Short>) () -> (short) 0
        );

        assertEquals(result2, -1);

        /* 3 */
        int result3 = match(null,
                5, () -> 1,
                10, () -> 2,
                15, () -> 3,
                Null.class, () -> -1,
                Else.class, (Supplier<Integer>) () -> 0
        );

        assertEquals(result3, -1);

        /* 4 */
        long result4 = match(null,
                5L, () -> (long) 1,
                10L, () -> (long) 2,
                15L, () -> (long) 3,
                20L, () -> (long) 4,
                Null.class, () -> (long) -1,
                Else.class, (Supplier<Long>) () -> (long) 0
        );

        assertEquals(result4, -1);

        /* 5 */
        float result5 = match(null,
                5.0F, () -> (float) 1,
                10.0F, () -> (float) 2,
                15.0F, () -> (float) 3,
                20.0F, () -> (float) 4,
                25.0F, () -> (float) 5,
                Null.class, () -> (float) -1,
                Else.class, (Supplier<Float>) () -> (float) 0
        );

        assertEquals(result5, -1);

        /* 6 */
        double result6 = match(null,
                5.0D, () -> (double) 1,
                10.0D, () -> (double) 2,
                15.0D, () -> (double) 3,
                20.0D, () -> (double) 4,
                25.0D, () -> (double) 5,
                30.0D, () -> (double) 6,
                Null.class, () -> (double) -1,
                Else.class, () -> (double) 0
        );

        assertEquals(result6, -1);
    }

    public void matchAsExpressionWithNullDefaultTest() {
        /* 1 */
        byte result1 = match(null).as(
                (byte) 5, () -> (byte) 1,
                Null.class, () -> (byte) -1,
                Else.class, (Supplier<Byte>) () -> (byte) 0
        );

        assertEquals(result1, -1);

        /* 2 */
        short result2 = match(null).as(
                (short) 5, () -> (short) 1,
                (short) 10, () -> (short) 2,
                Null.class, () -> (short) -1,
                Else.class, (Supplier<Short>) () -> (short) 0
        );

        assertEquals(result2, -1);

        /* 3 */
        int result3 = match(null).as(
                5, () -> 1,
                10, () -> 2,
                15, () -> 3,
                Null.class, () -> -1,
                Else.class, (Supplier<Integer>) () -> 0
        );

        assertEquals(result3, -1);

        /* 4 */
        long result4 = match(null).as(
                5L, () -> (long) 1,
                10L, () -> (long) 2,
                15L, () -> (long) 3,
                20L, () -> (long) 4,
                Null.class, () -> (long) -1,
                Else.class, (Supplier<Long>) () -> (long) 0
        );

        assertEquals(result4, -1);

        /* 5 */
        float result5 = match(null).as(
                5.0F, () -> (float) 1,
                10.0F, () -> (float) 2,
                15.0F, () -> (float) 3,
                20.0F, () -> (float) 4,
                25.0F, () -> (float) 5,
                Null.class, () -> (float) -1,
                Else.class, (Supplier<Float>) () -> (float) 0
        );

        assertEquals(result5, -1);

        /* 6 */
        double result6 = match(null).as(
                5.0D, () -> (double) 1,
                10.0D, () -> (double) 2,
                15.0D, () -> (double) 3,
                20.0D, () -> (double) 4,
                25.0D, () -> (double) 5,
                30.0D, () -> (double) 6,
                Null.class, () -> (double) -1,
                Else.class, () -> (double) 0
        );

        assertEquals(result6, -1);
    }

    @Test
    public void matchExpressionWithNullVarTest() {
        /* 1 */
        byte result1 = match(null,
                (byte) 5, () -> (byte) 1,
                Null.class, () -> (byte) -1,
                Var.class, (UnaryOperator<Byte>) any -> (byte) 0
        );

        assertEquals(result1, -1);

        /* 2 */
        short result2 = match(null,
                (short) 5, () -> (short) 1,
                (short) 10, () -> (short) 2,
                Null.class, () -> (short) -1,
                Var.class, (UnaryOperator<Short>) any -> (short) 0
        );

        assertEquals(result2, -1);

        /* 3 */
        int result3 = match(null,
                5, () -> 1,
                10, () -> 2,
                15, () -> 3,
                Null.class, () -> -1,
                Var.class, (UnaryOperator<Integer>) any -> 0
        );

        assertEquals(result3, -1);

        /* 4 */
        long result4 = match(null,
                5L, () -> (long) 1,
                10L, () -> (long) 2,
                15L, () -> (long) 3,
                20L, () -> (long) 4,
                Null.class, () -> (long) -1,
                Var.class, (UnaryOperator<Long>) any -> (long) 0
        );

        assertEquals(result4, -1);

        /* 5 */
        float result5 = match(null,
                5.0F, () -> (float) 1,
                10.0F, () -> (float) 2,
                15.0F, () -> (float) 3,
                20.0F, () -> (float) 4,
                25.0F, () -> (float) 5,
                Null.class, () -> (float) -1,
                Var.class, (UnaryOperator<Float>) any -> (float) 0
        );

        assertEquals(result5, -1);

        /* 6 */
        double result6 = match(null,
                5.0D, () -> (double) 1,
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

    @Test
    public void matchAsExpressionWithNullVarTest() {
        /* 1 */
        byte result1 = match(null).as(
                (byte) 5, () -> (byte) 1,
                Null.class, () -> (byte) -1,
                Var.class, (UnaryOperator<Byte>) any -> (byte) 0
        );

        assertEquals(result1, -1);

        /* 2 */
        short result2 = match(null).as(
                (short) 5, () -> (short) 1,
                (short) 10, () -> (short) 2,
                Null.class, () -> (short) -1,
                Var.class, (UnaryOperator<Short>) any -> (short) 0
        );

        assertEquals(result2, -1);

        /* 3 */
        int result3 = match(null).as(
                5, () -> 1,
                10, () -> 2,
                15, () -> 3,
                Null.class, () -> -1,
                Var.class, (UnaryOperator<Integer>) any -> 0
        );

        assertEquals(result3, -1);

        /* 4 */
        long result4 = match(null).as(
                5L, () -> (long) 1,
                10L, () -> (long) 2,
                15L, () -> (long) 3,
                20L, () -> (long) 4,
                Null.class, () -> (long) -1,
                Var.class, (UnaryOperator<Long>) any -> (long) 0
        );

        assertEquals(result4, -1);

        /* 5 */
        float result5 = match(null).as(
                5.0F, () -> (float) 1,
                10.0F, () -> (float) 2,
                15.0F, () -> (float) 3,
                20.0F, () -> (float) 4,
                25.0F, () -> (float) 5,
                Null.class, () -> (float) -1,
                Var.class, (UnaryOperator<Float>) any -> (float) 0
        );

        assertEquals(result5, -1);

        /* 6 */
        double result6 = match(null).as(
                5.0D, () -> (double) 1,
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
