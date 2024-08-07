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

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import org.kl.jpml.lambda.Acceptor;
import org.kl.jpml.test.shape.*;
import org.kl.jpml.state.Else;
import org.kl.jpml.state.Null;
import org.kl.jpml.state.Var;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kl.jpml.pattern.DeconstructPattern.foreach;
import static org.kl.jpml.pattern.DeconstructPattern.let;
import static org.kl.jpml.pattern.DeconstructPattern.match;

public class DeconstructPatternTest {
    private static Set<Circle> listCircles;
    private static List<Rectangle> listRectangles;
    private static Queue<Tripiped> listTripipeds;
    private static List<Quarpiped> listQuarpiped;
    private static Map<Integer, String> map;

    @BeforeAll
    public static void setUp() {
        listCircles = new HashSet<Circle>() {{
            add(new Circle(5));
            add(new Circle(10));
            add(new Circle(15));
        }};

        listRectangles = new ArrayList<Rectangle>() {{
            add(new Rectangle(5, 10));
            add(new Rectangle(10, 15));
            add(new Rectangle(15, 20));
        }};

        listTripipeds = new ArrayDeque<Tripiped>() {{
            add(new Tripiped(5, 10, 15));
            add(new Tripiped(10, 15, 20));
            add(new Tripiped(15, 20, 25));
        }};

        listQuarpiped = new LinkedList<Quarpiped>() {{
            add(new Quarpiped('1', '2', '3', '4'));
            add(new Quarpiped('3', '4', '5', '6'));
            add(new Quarpiped('5', '6', '7', '8'));
        }};

        map = new HashMap<Integer, String>() {{
            put(1, "one");
            put(2, "two");
            put(3, "three");
        }};
    }

    @AfterAll
    public static void tearDown() {
        listCircles.clear();
        listRectangles.clear();
        listTripipeds.clear();
        listQuarpiped.clear();
        map.clear();
    }

    @Test
    public void matchStatementTest() {
        Figure figure;

        /* 1 */
        figure = new Circle();

        match(figure,
                Circle.class, (Integer r) -> {
                    out.println("Circle square: " + ((int) (2 * Math.PI * r)));
                }
        );

        /* 2 */
        figure = new Rectangle();

        match(figure,
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                }
        );

        /* 3 */
        figure = new Parallelepiped();

        match(figure,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 1 - 1 */
        figure = new Quadrate();

        match(figure,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                }
        );

        /* 1 - 2 */
        figure = new Rectangle();

        match(figure,
                Circle.class, (Integer r) -> {
                    out.println("Circle    square: " + ((int) (2 * Math.PI * r)));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                }
        );

        /* 2 - 1 */
        figure = new Triangle();

        match(figure,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle square: " + (w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate  square: " + (a * a));
                }
        );

        /* 2 - 2 */
        figure = new Rectangle();

        match(figure,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                }
        );

        /* 1 - 3 */
        figure = new Parallelepiped();

        match(figure,
                Circle.class, (Integer r) -> {
                    out.println("Circle    square: " + ((int) (2 * Math.PI * r)));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 3 - 1 */
        figure = new Circle();

        match(figure,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle    square: " + ((int) (2 * Math.PI * r)));
                }
        );

        /* 3 - 3 */
        figure = new Tripiped();

        match(figure,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 2 - 3 */
        figure = new Triangle();

        match(figure,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 3 - 2 */
        figure = new Tripiped();

        match(figure,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                }
        );

        /* 1 - 1 - 1 */
        figure = new Unpiped();

        match(figure,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Unpiped.class, (Double u) -> {
                    out.println("Unpiped  square: " + (u * u));
                }
        );

        /* 1 - 1 - 2 */
        figure = new Triangle();

        match(figure,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                }
        );

        /* 1 - 2 - 2 */
        figure = new Rectangle();

        match(figure,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                }
        );

        /* 2 - 2 - 2 */
        figure = new Bipiped();

        match(figure,
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                }
        );

        /* 2 - 1 - 1 */
        figure = new Triangle();

        match(figure,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                }
        );

        /* 2 - 2 - 1 */
        figure = new Bipiped();

        match(figure,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                }
        );

        /* 2 - 1 - 2 */
        figure = new Bipiped();

        match(figure,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                }
        );

        /* 1 - 2 - 1 */
        figure = new Triangle();

        match(figure,
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                }
        );

        /* 1 - 1 - 3 */
        figure = new Tripiped();

        match(figure,
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 1 - 3 - 3 */
        figure = new Parallelepiped();

        match(figure,
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 3 - 3 - 3 */
        figure = new Parallelepiped();

        match(figure,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 3 - 1 - 1 */
        figure = new Circle();

        match(figure,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                }
        );

        /* 3 - 3 - 1 */
        figure = new Tripiped();

        match(figure,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                }
        );

        /* 3 - 1 - 3 */
        figure = new Parallelepiped();

        match(figure,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 1 - 3 - 1 */
        figure = new Quadrate();

        match(figure,
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                }
        );

        /* 2 - 2 - 3 */
        figure = new Bipiped();

        match(figure,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 2 - 2 - 3 */
        figure = new Parallelepiped();

        match(figure,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 3 - 2 - 2 */
        figure = new Triangle();

        match(figure,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                }
        );

        /* 3 - 2 - 2 */
        figure = new Tripiped();

        match(figure,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                }
        );

        /* 3 - 2 - 3 */
        figure = new Triangle();

        match(figure,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 2 - 3 - 2 */
        figure = new Bipiped();

        match(figure,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                }
        );

        /* 1 - 2 - 3 */
        figure = new Parallelepiped();

        match(figure,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 3 - 2 - 1 */
        figure = new Bipiped();

        match(figure,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                }
        );

        /* 2 - 1 - 3 */
        figure = new Circle();

        match(figure,
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 3 - 1 - 2 */
        figure = new Bipiped();

        match(figure,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                }
        );

        /* 1 - 3 - 2 */
        figure = new Bipiped();

        match(figure,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                }
        );

        /* 2 - 3 - 1 */
        figure = new Bipiped();

        match(figure,
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                }
        );
    }

    @Test
    public void matchAsStatementTest() {
        Figure figure;

        /* 1 */
        figure = new Circle();

        match(figure).as(
                Circle.class, (Integer r) -> {
                    out.println("Circle square: " + ((int) (2 * Math.PI * r)));
                }
        );

        /* 2 */
        figure = new Rectangle();

        match(figure).as(
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                }
        );

        /* 3 */
        figure = new Parallelepiped();

        match(figure).as(
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 1 - 1 */
        figure = new Quadrate();

        match(figure).as(
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                }
        );

        /* 1 - 2 */
        figure = new Rectangle();

        match(figure).as(
                Circle.class, (Integer r) -> {
                    out.println("Circle    square: " + ((int) (2 * Math.PI * r)));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                }
        );

        /* 2 - 1 */
        figure = new Triangle();

        match(figure).as(
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle square: " + (w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate  square: " + (a * a));
                }
        );

        /* 2 - 2 */
        figure = new Rectangle();

        match(figure).as(
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                }
        );

        /* 1 - 3 */
        figure = new Parallelepiped();

        match(figure).as(
                Circle.class, (Integer r) -> {
                    out.println("Circle    square: " + ((int) (2 * Math.PI * r)));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 3 - 1 */
        figure = new Circle();

        match(figure).as(
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle    square: " + ((int) (2 * Math.PI * r)));
                }
        );

        /* 3 - 3 */
        figure = new Tripiped();

        match(figure).as(
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 2 - 3 */
        figure = new Triangle();

        match(figure).as(
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 3 - 2 */
        figure = new Tripiped();

        match(figure).as(
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                }
        );

        /* 1 - 1 - 1 */
        figure = new Unpiped();

        match(figure).as(
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Unpiped.class, (Double u) -> {
                    out.println("Unpiped  square: " + (u * u));
                }
        );

        /* 1 - 1 - 2 */
        figure = new Triangle();

        match(figure).as(
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                }
        );

        /* 1 - 2 - 2 */
        figure = new Rectangle();

        match(figure).as(
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                }
        );

        /* 2 - 2 - 2 */
        figure = new Bipiped();

        match(figure).as(
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                }
        );

        /* 2 - 1 - 1 */
        figure = new Triangle();

        match(figure).as(
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                }
        );

        /* 2 - 2 - 1 */
        figure = new Bipiped();

        match(figure).as(
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                }
        );

        /* 2 - 1 - 2 */
        figure = new Bipiped();

        match(figure).as(
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                }
        );

        /* 1 - 2 - 1 */
        figure = new Triangle();

        match(figure).as(
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                }
        );

        /* 1 - 1 - 3 */
        figure = new Tripiped();

        match(figure).as(
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 1 - 3 - 3 */
        figure = new Parallelepiped();

        match(figure).as(
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 3 - 3 - 3 */
        figure = new Parallelepiped();

        match(figure).as(
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 3 - 1 - 1 */
        figure = new Circle();

        match(figure).as(
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                }
        );

        /* 3 - 3 - 1 */
        figure = new Tripiped();

        match(figure).as(
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                }
        );

        /* 3 - 1 - 3 */
        figure = new Parallelepiped();

        match(figure).as(
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 1 - 3 - 1 */
        figure = new Quadrate();

        match(figure).as(
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                }
        );

        /* 2 - 2 - 3 */
        figure = new Bipiped();

        match(figure).as(
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 2 - 2 - 3 */
        figure = new Parallelepiped();

        match(figure).as(
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 3 - 2 - 2 */
        figure = new Triangle();

        match(figure).as(
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                }
        );

        /* 3 - 2 - 2 */
        figure = new Tripiped();

        match(figure).as(
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                }
        );

        /* 3 - 2 - 3 */
        figure = new Triangle();

        match(figure).as(
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 2 - 3 - 2 */
        figure = new Bipiped();

        match(figure).as(
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                }
        );

        /* 1 - 2 - 3 */
        figure = new Parallelepiped();

        match(figure).as(
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 3 - 2 - 1 */
        figure = new Bipiped();

        match(figure).as(
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                }
        );

        /* 2 - 1 - 3 */
        figure = new Circle();

        match(figure).as(
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                }
        );

        /* 3 - 1 - 2 */
        figure = new Bipiped();

        match(figure).as(
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                }
        );

        /* 1 - 3 - 2 */
        figure = new Bipiped();

        match(figure).as(
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                }
        );

        /* 2 - 3 - 1 */
        figure = new Bipiped();

        match(figure).as(
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                }
        );
    }

    @Test
    public void matchStatementWithDefaultTest() {
        String data = "unknown";

        /* 1 */
        match(data,
                Circle.class, (Integer r) -> {
                    out.println("Circle square: " + ((int) (2 * Math.PI * r)));
                },
                Else.class, () -> {
                    out.println("Else value 1 type");
                }
        );

        /* 2 */
        match(data,
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 2 type");
                }
        );

        /* 3 */
        match(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 3 type");
                }
        );

        /* 1 - 1 */
        match(data,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Else.class, () -> {
                    out.println("Else value 1-1 type");
                }
        );

        /* 1 - 2 */
        match(data,
                Circle.class, (Integer r) -> {
                    out.println("Circle    square: " + ((int) (2 * Math.PI * r)));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 1-2 type");
                }
        );

        /* 2 - 1 */
        match(data,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle square: " + (w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Else.class, () -> {
                    out.println("Else value 2-1 type");
                }
        );

        /* 2 - 2 */
        match(data,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 2-2 type");
                }
        );

        /* 1 - 3 */
        match(data,
                Circle.class, (Integer r) -> {
                    out.println("Circle    square: " + ((int) (2 * Math.PI * r)));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 1-3 type");
                }
        );

        /* 3 - 1 */
        match(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle    square: " + ((int) (2 * Math.PI * r)));
                },
                Else.class, () -> {
                    out.println("Else value 3-1 type");
                }
        );

        /* 3 - 3 */
        match(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 3-3 type");
                }
        );

        /* 2 - 3 */
        match(data,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 2-3 type");
                }
        );

        /* 3 - 2 */
        match(data,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 3-2 type");
                }
        );

        /* 1 - 1 - 1 */
        match(data,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Unpiped.class, (Double u) -> {
                    out.println("Unpiped  square: " + (u * u));
                },
                Else.class, () -> {
                    out.println("Else value 1-1-1 type");
                }
        );

        /* 1 - 1 - 2 */
        match(data,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 1-1-2 type");
                }
        );

        /* 1 - 2 - 2 */
        match(data,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 1-2-2 type");
                }
        );

        /* 2 - 2 - 2 */
        match(data,
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 2-2-2 type");
                }
        );

        /* 2 - 1 - 1 */
        match(data,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Else.class, () -> {
                    out.println("Else value 2-1-1 type");
                }
        );

        /* 2 - 2 - 1 */
        match(data,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Else.class, () -> {
                    out.println("Else value 2-2-1 type");
                }
        );

        /* 2 - 1 - 2 */
        match(data,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 2-1-2 type");
                }
        );

        /* 1 - 2 - 1 */
        match(data,
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Else.class, () -> {
                    out.println("Else value 1-2-1 type");
                }
        );

        /* 1 - 1 - 3 */
        match(data,
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 1-1-3 type");
                }
        );

        /* 1 - 3 - 3 */
        match(data,
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 1-3-3 type");
                }
        );

        /* 3 - 3 - 3 */
        match(data,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 3-3-3 type");
                }
        );

        /* 3 - 1 - 1 */
        match(data,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Else.class, () -> {
                    out.println("Else value 3-1-1 type");
                }
        );

        /* 3 - 3 - 1 */
        match(data,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Else.class, () -> {
                    out.println("Else value 3-3-1 type");
                }
        );

        /* 3 - 1 - 3 */
        match(data,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 3-1-3 type");
                }
        );

        /* 1 - 3 - 1 */
        match(data,
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Else.class, () -> {
                    out.println("Else value 1-3-1 type");
                }
        );

        /* 2 - 2 - 3 */
        match(data,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 2-2-3 type");
                }
        );

        /* 2 - 3 - 3 */
        match(data,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 2-3-3 type");
                }
        );

        /* 3 - 2 - 2 */
        match(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 3-2-2 type");
                }
        );

        /* 3 - 3 - 2 */
        match(new Bipiped(),
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 3-3-2 type");
                }
        );

        /* 3 - 2 - 3 */
        match(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 3-2-3 type");
                }
        );

        /* 2 - 3 - 2 */
        match(data,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 2-3-2 type");
                }
        );

        /* 1 - 2 - 3 */
        match(data,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 1-2-3 type");
                }
        );

        /* 3 - 2 - 1 */
        match(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Else.class, () -> {
                    out.println("Else value 3-2-1 type");
                }
        );

        /* 2 - 1 - 3 */
        match(data,
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 2-1-3 type");
                }
        );

        /* 3 - 1 - 2 */
        match(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 3-1-2 type");
                }
        );

        /* 1 - 3 - 2 */
        match(data,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 1-3-2 type");
                }
        );

        /* 2 - 3 - 1 */
        match(data,
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Else.class, () -> {
                    out.println("Else value 2-3-1 type");
                }
        );
    }

    @Test
    public void matchAsStatementWithDefaultTest() {
        String data = "unknown";

        /* 1 */
        match(data).as(
                Circle.class, (Integer r) -> {
                    out.println("Circle square: " + ((int) (2 * Math.PI * r)));
                },
                Else.class, () -> {
                    out.println("Else value 1 type");
                }
        );

        /* 2 */
        match(data).as(
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 2 type");
                }
        );

        /* 3 */
        match(data).as(
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 3 type");
                }
        );

        /* 1 - 1 */
        match(data).as(
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Else.class, () -> {
                    out.println("Else value 1-1 type");
                }
        );

        /* 1 - 2 */
        match(data).as(
                Circle.class, (Integer r) -> {
                    out.println("Circle    square: " + ((int) (2 * Math.PI * r)));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 1-2 type");
                }
        );

        /* 2 - 1 */
        match(data).as(
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle square: " + (w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Else.class, () -> {
                    out.println("Else value 2-1 type");
                }
        );

        /* 2 - 2 */
        match(data).as(
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 2-2 type");
                }
        );

        /* 1 - 3 */
        match(data).as(
                Circle.class, (Integer r) -> {
                    out.println("Circle    square: " + ((int) (2 * Math.PI * r)));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 1-3 type");
                }
        );

        /* 3 - 1 */
        match(data).as(
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle    square: " + ((int) (2 * Math.PI * r)));
                },
                Else.class, () -> {
                    out.println("Else value 3-1 type");
                }
        );

        /* 3 - 3 */
        match(data).as(
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 3-3 type");
                }
        );

        /* 2 - 3 */
        match(data).as(
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 2-3 type");
                }
        );

        /* 3 - 2 */
        match(data).as(
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 3-2 type");
                }
        );

        /* 1 - 1 - 1 */
        match(data).as(
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Unpiped.class, (Double u) -> {
                    out.println("Unpiped  square: " + (u * u));
                },
                Else.class, () -> {
                    out.println("Else value 1-1-1 type");
                }
        );

        /* 1 - 1 - 2 */
        match(data).as(
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 1-1-2 type");
                }
        );

        /* 1 - 2 - 2 */
        match(data).as(
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 1-2-2 type");
                }
        );

        /* 2 - 2 - 2 */
        match(data).as(
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 2-2-2 type");
                }
        );

        /* 2 - 1 - 1 */
        match(data).as(
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Else.class, () -> {
                    out.println("Else value 2-1-1 type");
                }
        );

        /* 2 - 2 - 1 */
        match(data).as(
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Else.class, () -> {
                    out.println("Else value 2-2-1 type");
                }
        );

        /* 2 - 1 - 2 */
        match(data).as(
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 2-1-2 type");
                }
        );

        /* 1 - 2 - 1 */
        match(data).as(
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Else.class, () -> {
                    out.println("Else value 1-2-1 type");
                }
        );

        /* 1 - 1 - 3 */
        match(data).as(
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 1-1-3 type");
                }
        );

        /* 1 - 3 - 3 */
        match(data).as(
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 1-3-3 type");
                }
        );

        /* 3 - 3 - 3 */
        match(data).as(
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 3-3-3 type");
                }
        );

        /* 3 - 1 - 1 */
        match(data).as(
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Else.class, () -> {
                    out.println("Else value 3-1-1 type");
                }
        );

        /* 3 - 3 - 1 */
        match(data).as(
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Else.class, () -> {
                    out.println("Else value 3-3-1 type");
                }
        );

        /* 3 - 1 - 3 */
        match(data).as(
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 3-1-3 type");
                }
        );

        /* 1 - 3 - 1 */
        match(data).as(
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Else.class, () -> {
                    out.println("Else value 1-3-1 type");
                }
        );

        /* 2 - 2 - 3 */
        match(data).as(
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 2-2-3 type");
                }
        );

        /* 2 - 3 - 3 */
        match(data).as(
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 2-3-3 type");
                }
        );

        /* 3 - 2 - 2 */
        match(data).as(
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 3-2-2 type");
                }
        );

        /* 3 - 3 - 2 */
        match(new Bipiped()).as(
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 3-3-2 type");
                }
        );

        /* 3 - 2 - 3 */
        match(data).as(
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 3-2-3 type");
                }
        );

        /* 2 - 3 - 2 */
        match(data).as(
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 2-3-2 type");
                }
        );

        /* 1 - 2 - 3 */
        match(data).as(
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 1-2-3 type");
                }
        );

        /* 3 - 2 - 1 */
        match(data).as(
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Else.class, () -> {
                    out.println("Else value 3-2-1 type");
                }
        );

        /* 2 - 1 - 3 */
        match(data).as(
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Else.class, () -> {
                    out.println("Else value 2-1-3 type");
                }
        );

        /* 3 - 1 - 2 */
        match(data).as(
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 3-1-2 type");
                }
        );

        /* 1 - 3 - 2 */
        match(data).as(
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Else.class, () -> {
                    out.println("Else value 1-3-2 type");
                }
        );

        /* 2 - 3 - 1 */
        match(data).as(
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Else.class, () -> {
                    out.println("Else value 2-3-1 type");
                }
        );
    }

    @Test
    public void matchStatementWithNullDefaultTest() {
        /* 1 */
        match(null,
                Circle.class, (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Null.class, () -> out.println("Null    value 1 type"),
                Else.class, () -> out.println("Else value 1 type")
        );

        /* 2 */
        match(null,
                Rectangle.class, (Integer w, Integer h) -> out.println("Rectangle square: " + (w * h)),
                Null.class, () -> out.println("Null    value 2 type"),
                Else.class, () -> out.println("Else value 2 type")
        );

        /* 3 */
        match(null,
                Parallelepiped.class, (Short w, Short l, Short h) -> out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h)),
                Null.class, () -> out.println("Null    value 3 type"),
                Else.class, () -> out.println("Else value 3 type")
        );

        /* 1 - 1 */
        match(null,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Null.class, () -> out.println("Null    value 1-1 type"),
                Else.class, () -> out.println("Else value 1-1 type")
        );

        /* 1 - 2 */
        match(null,
                Circle.class, (Integer r) -> out.println("Circle    square: " + ((int) (2 * Math.PI * r))),
                Rectangle.class, (Integer w, Integer h) -> out.println("Rectangle square: " + (w * h)),
                Null.class, () -> out.println("Null    value 1-2 type"),
                Else.class, () -> out.println("Else value 1-2 type")
        );

        /* 2 - 1 */
        match(null,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle square: " + (w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Null.class, () -> out.println("Null    value 2-1 type"),
                Else.class, () -> out.println("Else value 2-1 type")
        );

        /* 2 - 2 */
        match(null,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Null.class, () -> out.println("Null    value 2-2 type"),
                Else.class, () -> out.println("Else value 2-2 type")
        );

        /* 1 - 3 */
        match(null,
                Circle.class, (Integer r) -> {
                    out.println("Circle    square: " + ((int) (2 * Math.PI * r)));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null    value 1-3 type"),
                Else.class, () -> out.println("Else value 1-3 type")
        );

        /* 3 - 1 */
        match(null,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle    square: " + ((int) (2 * Math.PI * r)));
                },
                Null.class, () -> out.println("Null    value 3-1 type"),
                Else.class, () -> out.println("Else value 3-1 type")
        );

        /* 3 - 3 */
        match(null,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null    value 3-3 type"),
                Else.class, () -> out.println("Else value 3-3 type")
        );

        /* 2 - 3 */
        match(null,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null    value 2-3 type"),
                Else.class, () -> out.println("Else value 2-3 type")
        );

        /* 3 - 2 */
        match(null,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Null.class, () -> out.println("Null    value 3-2 type"),
                Else.class, () -> out.println("Else value 3-2 type")
        );

        /* 1 - 1 - 1 */
        match(null,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Unpiped.class, (Double u) -> {
                    out.println("Unpiped  square: " + (u * u));
                },
                Null.class, () -> out.println("Null    value 1-1-1 type"),
                Else.class, () -> out.println("Else value 1-1-1 type")
        );

        /* 1 - 1 - 2 */
        match(null,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Null.class, () -> out.println("Null    value 1-1-2 type"),
                Else.class, () -> out.println("Else value 1-1-2 type")
        );

        /* 1 - 2 - 2 */
        match(null,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Null.class, () -> out.println("Null    value 1-2-2 type"),
                Else.class, () -> out.println("Else value 1-2-2 type")
        );

        /* 2 - 2 - 2 */
        match(null,
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Null.class, () -> out.println("Null    value 2-2-2 type"),
                Else.class, () -> out.println("Else value 2-2-2 type")
        );

        /* 2 - 1 - 1 */
        match(null,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Null.class, () -> out.println("Null    value 2-1-1 type"),
                Else.class, () -> out.println("Else value 2-1-1 type")
        );

        /* 2 - 2 - 1 */
        match(null,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Null.class, () -> out.println("Null    value 2-2-1 type"),
                Else.class, () -> out.println("Else value 2-2-1 type")
        );

        /* 2 - 1 - 2 */
        match(null,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                },
                Null.class, () -> out.println("Null    value 2-1-2 type"),
                Else.class, () -> out.println("Else value 2-1-2 type")
        );

        /* 1 - 2 - 1 */
        match(null,
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Null.class, () -> out.println("Null    value 1-2-1 type"),
                Else.class, () -> out.println("Else value 1-2-1 type")
        );

        /* 1 - 1 - 3 */
        match(null,
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null    value 1-1-3 type"),
                Else.class, () -> out.println("Else value 1-1-3 type")
        );

        /* 1 - 3 - 3 */
        match(null,
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null    value 1-3-3 type"),
                Else.class, () -> out.println("Else value 1-3-3 type")
        );

        /* 3 - 3 - 3 */
        match(null,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null    value 3-3-3 type"),
                Else.class, () -> out.println("Else value 3-3-3 type")
        );

        /* 3 - 1 - 1 */
        match(null,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Null.class, () -> out.println("Null    value 3-1-1 type"),
                Else.class, () -> out.println("Else value 3-1-1 type")
        );

        /* 3 - 3 - 1 */
        match(null,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Null.class, () -> out.println("Null    value 3-3-1 type"),
                Else.class, () -> out.println("Else value 3-3-1 type")
        );

        /* 3 - 1 - 3 */
        match(null,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null    value 3-1-3 type"),
                Else.class, () -> out.println("Else value 3-1-3 type")
        );

        /* 1 - 3 - 1 */
        match(null,
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Null.class, () -> out.println("Null    value 1-3-1 type"),
                Else.class, () -> out.println("Else value 1-3-1 type")
        );

        /* 2 - 2 - 3 */
        match(null,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null    value 2-2-3 type"),
                Else.class, () -> out.println("Else value 2-2-3 type")
        );

        /* 2 - 3 - 3 */
        match(null,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null    value 2-3-3 type"),
                Else.class, () -> out.println("Else value 2-3-3 type")
        );

        /* 3 - 2 - 2 */
        match(null,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Null.class, () -> out.println("Null    value 3-2-2 type"),
                Else.class, () -> out.println("Else value 3-2-2 type")
        );

        /* 3 - 3 - 2 */
        match(null,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Null.class, () -> out.println("Null    value 3-3-2 type"),
                Else.class, () -> out.println("Else value 3-3-2 type")
        );

        /* 3 - 2 - 3 */
        match(null,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null    value 3-2-3 type"),
                Else.class, () -> out.println("Else value 3-2-3 type")
        );

        /* 2 - 3 - 2 */
        match(null,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Null.class, () -> out.println("Null    value 2-3-2 type"),
                Else.class, () -> out.println("Else value 2-3-2 type")
        );

        /* 1 - 2 - 3 */
        match(null,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null    value 1-2-3 type"),
                Else.class, () -> out.println("Else value 1-2-3 type")
        );

        /* 3 - 2 - 1 */
        match(null,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Null.class, () -> out.println("Null    value 3-2-1 type"),
                Else.class, () -> out.println("Else value 3-2-1 type")
        );

        /* 2 - 1 - 3 */
        match(null,
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null    value 2-1-3 type"),
                Else.class, () -> out.println("Else value 2-1-3 type")
        );

        /* 3 - 1 - 2 */
        match(null,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Null.class, () -> out.println("Null    value 3-1-2 type"),
                Else.class, () -> out.println("Else value 3-1-2 type")
        );

        /* 1 - 3 - 2 */
        match(null,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Null.class, () -> out.println("Null    value 1-3-2 type"),
                Else.class, () -> out.println("Else value 1-3-2 type")
        );

        /* 2 - 3 - 1 */
        match(null,
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Null.class, () -> out.println("Null    value 2-3-1 type"),
                Else.class, () -> out.println("Else value 2-3-1 type")
        );
    }

    @Test
    public void matchStatementWithVarTest() {
        String data = "unknown";

        /* 1 */
        match(data,
                Circle.class, (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Var.class, (Acceptor<String>) any -> out.println("Var value 1 type")
        );

        /* 2 */
        match(data,
                Rectangle.class, (Integer w, Integer h) -> out.println("Rectangle square: " + (w * h)),
                Var.class, (Acceptor<String>) any -> out.println("Var value 2 type")
        );

        /* 3 */
        match(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Var.class, (Acceptor<String>) any -> {
                    out.println("Var value 3 type");
                }
        );

        /* 1 - 1 */
        match(data,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Var.class, (Acceptor<String>) any -> {
                    out.println("Var value 1-1 type");
                }
        );

        /* 1 - 2 */
        match(data,
                Circle.class, (Integer r) -> {
                    out.println("Circle    square: " + ((int) (2 * Math.PI * r)));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Var.class, (Acceptor<String>) any -> {
                    out.println("Var value 1-2 type");
                }
        );

        /* 2 - 1 */
        match(data,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle square: " + (w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Var.class, (Acceptor<String>) any -> {
                    out.println("Var value 2-1 type");
                }
        );

        /* 2 - 2 */
        match(data,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Var.class, (Acceptor<String>) any -> {
                    out.println("Var value 2-2 type");
                }
        );

        /* 1 - 3 */
        match(data,
                Circle.class, (Integer r) -> {
                    out.println("Circle    square: " + ((int) (2 * Math.PI * r)));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Var.class, (Acceptor<String>) any -> {
                    out.println("Var value 1-3 type");
                }
        );

        /* 3 - 1 */
        match(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle    square: " + ((int) (2 * Math.PI * r)));
                },
                Var.class, (Acceptor<String>) any -> {
                    out.println("Var value 3-1 type");
                }
        );

        /* 3 - 3 */
        match(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Var.class, (Acceptor<String>) any -> {
                    out.println("Var value 3-3 type");
                }
        );

        /* 2 - 3 */
        match(data,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Var.class, (Acceptor<String>) any -> {
                    out.println("Var value 2-3 type");
                }
        );

        /* 3 - 2 */
        match(data,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Var.class, (Acceptor<String>) any -> {
                    out.println("Var value 3-2 type");
                }
        );

        /* 1 - 1 - 1 */
        match(data,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Unpiped.class, (Double u) -> {
                    out.println("Unpiped  square: " + (u * u));
                },
                Var.class, any -> {
                    out.println("Var value 1-1-1 type");
                }
        );

        /* 1 - 1 - 2 */
        match(data,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Var.class, any -> {
                    out.println("Var value 1-1-2 type");
                }
        );

        /* 1 - 2 - 2 */
        match(data,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Var.class, any -> {
                    out.println("Var value 1-2-2 type");
                }
        );

        /* 2 - 2 - 2 */
        match(data,
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Var.class, any -> {
                    out.println("Var value 2-2-2 type");
                }
        );

        /* 2 - 1 - 1 */
        match(data,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Var.class, any -> {
                    out.println("Var value 2-1-1 type");
                }
        );

        /* 2 - 2 - 1 */
        match(data,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Var.class, any -> {
                    out.println("Var value 2-2-1 type");
                }
        );

        /* 2 - 1 - 2 */
        match(data,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                },
                Var.class, any -> {
                    out.println("Var value 2-1-2 type");
                }
        );

        /* 1 - 2 - 1 */
        match(data,
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Var.class, any -> {
                    out.println("Var value 1-2-1 type");
                }
        );

        /* 1 - 1 - 3 */
        match(data,
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Var.class, any -> {
                    out.println("Var value 1-1-3 type");
                }
        );

        /* 1 - 3 - 3 */
        match(data,
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Var.class, any -> {
                    out.println("Var value 1-3-3 type");
                }
        );

        /* 3 - 3 - 3 */
        match(data,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Var.class, any -> {
                    out.println("Var value 3-3-3 type");
                }
        );

        /* 3 - 1 - 1 */
        match(data,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Var.class, any -> {
                    out.println("Var value 3-1-1 type");
                }
        );

        /* 3 - 3 - 1 */
        match(data,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Var.class, any -> {
                    out.println("Var value 3-3-1 type");
                }
        );

        /* 3 - 1 - 3 */
        match(data,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Var.class, any -> {
                    out.println("Var value 3-1-3 type");
                }
        );

        /* 1 - 3 - 1 */
        match(data,
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Var.class, any -> {
                    out.println("Var value 1-3-1 type");
                }
        );

        /* 2 - 2 - 3 */
        match(data,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Var.class, any -> {
                    out.println("Var value 2-2-3 type");
                }
        );

        /* 2 - 3 - 3 */
        match(data,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Var.class, any -> {
                    out.println("Var value 2-3-3 type");
                }
        );

        /* 3 - 2 - 2 */
        match(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Var.class, any -> {
                    out.println("Var value 3-2-2 type");
                }
        );

        /* 3 - 3 - 2 */
        match(new Bipiped(),
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Var.class, any -> {
                    out.println("Var value 3-3-2 type");
                }
        );

        /* 3 - 2 - 3 */
        match(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Var.class, any -> {
                    out.println("Var value 3-2-3 type");
                }
        );

        /* 2 - 3 - 2 */
        match(data,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Var.class, any -> {
                    out.println("Var value 2-3-2 type");
                }
        );

        /* 1 - 2 - 3 */
        match(data,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Var.class, any -> {
                    out.println("Var value 1-2-3 type");
                }
        );

        /* 3 - 2 - 1 */
        match(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Var.class, any -> {
                    out.println("Var value 3-2-1 type");
                }
        );

        /* 2 - 1 - 3 */
        match(data,
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Var.class, any -> {
                    out.println("Var value 2-1-3 type");
                }
        );

        /* 3 - 1 - 2 */
        match(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Var.class, any -> {
                    out.println("Var value 3-1-2 type");
                }
        );

        /* 1 - 3 - 2 */
        match(data,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Var.class, any -> {
                    out.println("Var value 1-3-2 type");
                }
        );

        /* 2 - 3 - 1 */
        match(data,
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Var.class, any -> {
                    out.println("Var value 2-3-1 type");
                }
        );
    }

    @Test
    public void matchStatementWithNullVarTest() {
        String data = "unknown";

        /* 1 */
        match(null,
                Circle.class, (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Null.class, () -> out.println("Null value 1 type"),
                Var.class, any -> out.println("Var  value 1 type")
        );

        /* 2 */
        match(null,
                Rectangle.class, (Integer w, Integer h) -> out.println("Rectangle square: " + (w * h)),
                Null.class, () -> out.println("Null value 2 type"),
                Var.class, any -> out.println("Var  value 2 type")
        );

        /* 3 */
        match(null,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null value 3 type"),
                Var.class, any -> out.println("Var  value 3 type")
        );

        /* 1 - 1 */
        match(null,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Null.class, () -> out.println("Null value 1-1 type"),
                Var.class, any -> out.println("Var  value 1-1 type")
        );

        /* 1 - 2 */
        match(null,
                Circle.class, (Integer r) -> out.println("Circle    square: " + ((int) (2 * Math.PI * r))),
                Rectangle.class, (Integer w, Integer h) -> out.println("Rectangle square: " + (w * h)),
                Null.class, () -> out.println("Null value 1-2 type"),
                Var.class, any -> out.println("Var  value 1-2 type")
        );

        /* 2 - 1 */
        match(null,
                Triangle.class, (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Quadrate.class, (Integer a) -> out.println("Quadrate square: " + (a * a)),
                Null.class, () -> out.println("Null value 2-1 type"),
                Var.class, any -> out.println("Var  value 2-1 type")
        );

        /* 2 - 2 */
        match(null,
                Triangle.class, (Double w, Double h) -> out.println("Triangle  square: " + (w * h)),
                Rectangle.class, (Integer w, Integer h) -> out.println("Rectangle square: " + (w * h)),
                Null.class, () -> out.println("Null value 2-2 type"),
                Var.class, any -> out.println("Var  value 2-2 type")
        );

        /* 1 - 3 */
        match(null,
                Circle.class, (Integer r) -> out.println("Circle    square: " + ((int) (2 * Math.PI * r))),
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null value 1-3 type"),
                Var.class, any -> out.println("Var  value 1-3 type")
        );

        /* 3 - 1 */
        match(null,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> out.println("Circle    square: " + ((int) (2 * Math.PI * r))),
                Null.class, () -> out.println("Null value 3-1 type"),
                Var.class, any -> out.println("Var  value 3-1 type")
        );

        /* 3 - 3 */
        match(null,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null value 3-3 type"),
                Var.class, any -> out.println("Var  value 3-3 type")
        );

        /* 2 - 3 */
        match(null,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null value 2-3 type"),
                Var.class, any -> out.println("Var  value 2-3 type")
        );

        /* 3 - 2 */
        match(null,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Null.class, () -> out.println("Null value 3-2 type"),
                Var.class, any -> out.println("Var  value 3-2 type")
        );

        /* 1 - 1 - 1 */
        match(null,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Unpiped.class, (Double u) -> {
                    out.println("Unpiped  square: " + (u * u));
                },
                Null.class, () -> out.println("Null value 1-1-1 type"),
                Var.class, any -> out.println("Var  value 1-1-1 type")
        );

        /* 1 - 1 - 2 */
        match(null,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Null.class, () -> out.println("Null value 1-1-2 type"),
                Var.class, any -> out.println("Var  value 1-1-2 type")
        );

        /* 1 - 2 - 2 */
        match(null,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Null.class, () -> out.println("Null value 1-2-2 type"),
                Var.class, any -> out.println("Var  value 1-2-2 type")
        );

        /* 2 - 2 - 2 */
        match(null,
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                },
                Rectangle.class, (Integer w, Integer h) -> {
                    out.println("Rectangle square: " + (w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Null.class, () -> out.println("Null value 2-2-2 type"),
                Var.class, any -> out.println("Var  value 2-2-2 type")
        );

        /* 2 - 1 - 1 */
        match(null,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Null.class, () -> out.println("Null value 2-1-1 type"),
                Var.class, any -> out.println("Var  value 2-1-1 type")
        );

        /* 2 - 2 - 1 */
        match(null,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Null.class, () -> out.println("Null value 2-2-1 type"),
                Var.class, any -> out.println("Var  value 2-2-1 type")
        );

        /* 2 - 1 - 2 */
        match(null,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped  square:  " + (w * h));
                },
                Null.class, () -> out.println("Null value 2-1-2 type"),
                Var.class, any -> out.println("Var  value 2-1-2 type")
        );

        /* 1 - 2 - 1 */
        match(null,
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Null.class, () -> out.println("Null value 1-2-1 type"),
                Var.class, any -> out.println("Var  value 1-2-1 type")
        );

        /* 1 - 1 - 3 */
        match(null,
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null value 1-1-3 type"),
                Var.class, any -> out.println("Var  value 1-1-3 type")
        );

        /* 1 - 3 - 3 */
        match(null,
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null value 1-3-3 type"),
                Var.class, any -> out.println("Var  value 1-3-3 type")
        );

        /* 3 - 3 - 3 */
        match(null,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null value 3-3-3 type"),
                Var.class, any -> out.println("Var  value 3-3-3 type")
        );

        /* 3 - 1 - 1 */
        match(null,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Null.class, () -> out.println("Null value 3-1-1 type"),
                Var.class, any -> out.println("Var  value 3-1-1 type")
        );

        /* 3 - 3 - 1 */
        match(null,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Null.class, () -> out.println("Null value 3-3-1 type"),
                Var.class, any -> out.println("Var  value 3-3-1 type")
        );

        /* 3 - 1 - 3 */
        match(null,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null value 3-1-3 type"),
                Var.class, any -> out.println("Var  value 3-1-3 type")
        );

        /* 1 - 3 - 1 */
        match((null),
                Quadrate.class, (Integer a) -> {
                    out.println("Quadrate square: " + (a * a));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Null.class, () -> out.println("Null value 1-3-1 type"),
                Var.class, any -> out.println("Var  value 1-3-1 type")
        );

        /* 2 - 2 - 3 */
        match(null,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null value 2-2-3 type"),
                Var.class, any -> out.println("Var  value 2-2-3 type")
        );

        /* 2 - 3 - 3 */
        match(null,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null value 2-3-3 type"),
                Var.class, any -> out.println("Var  value 2-3-3 type")
        );

        /* 3 - 2 - 2 */
        match(null,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Null.class, () -> out.println("Null value 3-2-2 type"),
                Var.class, any -> out.println("Var  value 3-2-2 type")
        );

        /* 3 - 3 - 2 */
        match(null,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Null.class, () -> out.println("Null value 3-3-2 type"),
                Var.class, any -> out.println("Var  value 3-3-2 type")
        );

        /* 3 - 2 - 3 */
        match(null,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null value 3-2-3 type"),
                Var.class, any -> out.println("Var  value 3-2-3 type")
        );

        /* 2 - 3 - 2 */
        match(null,
                Triangle.class, (Double w, Double h) -> {
                    out.println("Triangle  square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Null.class, () -> out.println("Null value 2-3-2 type"),
                Var.class, any -> out.println("Var  value 2-3-2 type")
        );

        /* 1 - 2 - 3 */
        match(null,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null value 2-3-2 type"),
                Var.class, any -> out.println("Var  value 2-3-2 type")
        );

        /* 3 - 2 - 1 */
        match(null,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Null.class, () -> out.println("Null value 3-2-1 type"),
                Var.class, any -> out.println("Var  value 3-2-1 type")
        );

        /* 2 - 1 - 3 */
        match(null,
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Null.class, () -> out.println("Null value 2-1-3 type"),
                Var.class, any -> out.println("Var  value 2-1-3 type")
        );

        /* 3 - 1 - 2 */
        match(null,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Null.class, () -> out.println("Null value 3-1-2 type"),
                Var.class, any -> out.println("Var  value 3-1-2 type")
        );

        /* 1 - 3 - 2 */
        match(null,
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Null.class, () -> out.println("Null value 1-3-2 type"),
                Var.class, any -> out.println("Var  value 1-3-2 type")
        );

        /* 2 - 3 - 1 */
        match(null,
                Bipiped.class, (Short w, Short h) -> {
                    out.println("Bipiped   square: " + (w * h));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h));
                },
                Circle.class, (Integer r) -> {
                    out.println("Circle   square: " + ((int) (2 * Math.PI * r)));
                },
                Null.class, () -> out.println("Null value 2-3-1 type"),
                Var.class, any -> out.println("Var  value 2-3-1 type")
        );
    }

    @Test
    public void matchExpressionTest() {
        Figure figure;

        /* 1 */
        figure = new Circle(10);

        int result = match(figure,
                Circle.class, (Integer r) -> 2 * (r + r)
        );

        assertEquals(result, 40);

        /* 2 */
        figure = new Rectangle(5, 10);

        result = match(figure,
                Rectangle.class, (Integer w, Integer h) -> w * h
        );

        assertEquals(result, 25);

        /* 3 */
        figure = new Parallelepiped((short) 5, (short) 5, (short) 5);

        result = match(figure,
                Parallelepiped.class, (Short w, Short l, Short h) -> w * l * h
        );

        assertEquals(result, 125);

        /* 1 - 1 */
        figure = new Quadrate(15);

        result = match(figure,
                Circle.class, (Integer r) -> 2 * (r + r),
                Quadrate.class, (Integer a) -> a * a
        );

        assertEquals(result, 225);

        /* 1 - 2 */
        figure = new Rectangle(10, 12);

        result = match(figure,
                Circle.class, (Integer r) -> 2 * (r + r),
                Rectangle.class, (Integer w, Integer h) -> w * h
        );

        assertEquals(result, 120);

        /* 2 - 1 */
        figure = new Triangle(5D, 15D);

        result = match(figure,
                Triangle.class, (Double w, Double h) -> (int) (w * h),
                Quadrate.class, (Integer a) -> a * a
        );

        assertEquals(result, 75);

        /* 2 - 2 */
        figure = new Rectangle(10, 12);

        result = match(figure,
                Triangle.class, (Double w, Double h) -> (int) (w * h),
                Rectangle.class, (Integer w, Integer h) -> w * h
        );

        assertEquals(result, 120);

        /* 1 - 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 5);

        result = match(figure,
                Circle.class, (Integer r) -> 2 * (r + r),
                Parallelepiped.class, (Short w, Short l, Short h) -> (int) (w * l * h)
        );

        assertEquals(result, 250);

        /* 3 - 1 */
        figure = new Circle(5);

        result = match(figure,
                Parallelepiped.class, (Short w, Short l, Short h) -> (int) (w * l * h),
                Circle.class, (Integer r) -> 2 * (r + r)
        );

        assertEquals(result, 20);

        /* 3 - 3 */
        figure = new Tripiped(5, 5, 5);

        result = match(figure,
                Parallelepiped.class, (Short w, Short l, Short h) -> (int) (w * l * h),
                Tripiped.class, (Float w, Float l, Float h) -> (int) (w * l * h)
        );

        assertEquals(result, 125);

        /* 2 - 3 */
        figure = new Triangle(5, 5);

        result = match(figure,
                Triangle.class, (Double w, Double h) -> (int) (w * h),
                Tripiped.class, (Float w, Float l, Float h) -> (int) (w * l * h)
        );

        assertEquals(result, 25);

        /* 3 - 2 */
        figure = new Tripiped(5, 5, 5);

        result = match(figure,
                Tripiped.class, (Float w, Float l, Float h) -> (int) (w * l * h),
                Triangle.class, (Double w, Double h) -> (int) (w * h)
        );

        assertEquals(result, 125);
    }

    @Test
    public void foreachLoopTest() {
        /* 1 */
        foreach(listCircles, (Integer r) -> {
            out.println("Circle square: " + (2 * Math.PI * r));
        });

        /* 2 */
        foreach(listRectangles, (Integer w, Integer h) -> {
            out.println("Rect square: " + (w * h));
        });

        /* 3 */
        foreach(listTripipeds, (Float f, Float s, Float t) -> {
            out.println("Tripiped square: " + (f * s * t));
        });

        /* 4 */
        foreach(map, (Integer k, String v) -> {
            out.println("map entry: " + k + " - " + v);
        });
    }

    @Test
    public void letStatementTest() {
        /* 1 */
        Circle circle = new Circle(5);

        let(circle, (Integer r) -> {
            out.println("radius: " + r);
        });

        /* 2 */
        Rectangle rect = new Rectangle(15, 25);

        let(rect, (Integer w, Integer h) -> {
            out.println("border: " + w + " " + h);
        });

        /* 3 */
        Tripiped tripiped = new Tripiped(5, 10, 15);

        let(tripiped, (Float f, Float s, Float t) -> {
            out.println("border: " + f + " " + s + " " + t);
        });
    }
}
