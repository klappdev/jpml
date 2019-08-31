package org.kl;

import org.junit.jupiter.api.Test;
import org.kl.shape.*;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kl.pattern.PositionPattern.matches;
import static org.kl.pattern.PositionPattern.of;


public class PositionPatternTest {

    @Test
    public void matchesStatementTest() {
        Figure figure;

        /* 1 */
        figure = new Circle(5);

        matches(figure,
                Circle.class, of(5), () -> out.println("figure circle")
        );

        /* 2 */
        figure = new Rectangle(5, 10);

        matches(figure,
                Rectangle.class, of(5, 10), () -> out.println("figure rectangle")
        );

        /* 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        matches(figure,
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> out.println("figure parallelepiped")
        );

        /* 1 - 1 */
        figure = new Quadrate(10);

        matches(figure,
                Circle.class, of(5), () -> out.println("br 1 - figure circle "),
                Quadrate.class, of(10), () -> out.println("br 2 - figure quadrate ")
        );

        /* 1 - 2 */
        figure = new Rectangle(5, 10);

        matches(figure,
                Circle.class, of(5), () -> out.println("br 1 - figure circle"),
                Rectangle.class, of(5, 10), () -> out.println("br 2 - figure rectangle")
        );

        /* 2 - 1 */
        figure = new Triangle(10D, 20D);

        matches(figure,
                Triangle.class, of(10D, 20D), () -> out.println("br 1 - figure triangle"),
                Quadrate.class, of(10), () -> out.println("br 2 - figure quadrate ")
        );

        /* 2 - 2 */
        figure = new Rectangle(5, 10);

        matches(figure,
                Triangle.class, of(10D, 20D), () -> out.println("br 1 - figure triangle"),
                Rectangle.class, of(5, 10), () -> out.println("br 2 - figure rectangle")
        );

        /* 1 - 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        matches(figure,
                Circle.class, of(5), () -> out.println("br 1 - figure circle"),
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> out.println("br 2 - figure parallelepiped")
        );

        /* 3 - 1 */
        figure = new Circle(5);

        matches(figure,
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> out.println("br 1 - figure parallelepiped"),
                Circle.class, of(5), () -> out.println("br 2 - figure circle")
        );

        /* 3 - 3 */
        figure = new Tripiped(10f, 5f, 10f);

        matches(figure,
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> out.println("br 1 - figure parallelepiped"),
                Tripiped.class, of(10f, 5f, 10f), () -> out.println("br 2 - figure tripiped")
        );

        /* 2 - 3 */
        figure = new Triangle(10D, 20D);

        matches(figure,
                Triangle.class, of(10D, 20D), () -> out.println("br 1 - figure triangle"),
                Tripiped.class, of(10f, 5f, 10f), () -> out.println("br 2 - figure tripiped")
        );

        /* 3 - 2 */
        figure = new Tripiped(10f, 5f, 10f);

        matches(figure,
                Tripiped.class, of(10f, 5f, 10f), () -> out.println("br 1 - figure tripiped"),
                Triangle.class, of(10D, 20D), () -> out.println("br 2 - figure triangle")
        );
    }

    @Test
    public void matchesAsStatementTest() {
        Figure figure;

        /* 1 */
        figure = new Circle(5);

        matches(figure).as(
                Circle.class, of(5), () -> out.println("figure circle")
        );

        /* 2 */
        figure = new Rectangle(5, 10);

        matches(figure).as(
                Rectangle.class, of(5, 10), () -> out.println("figure rectangle")
        );

        /* 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        matches(figure).as(
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> out.println("figure parallelepiped")
        );

        /* 1 - 1 */
        figure = new Quadrate(10);

        matches(figure).as(
                Circle.class, of(5), () -> out.println("br 1 - figure circle "),
                Quadrate.class, of(10), () -> out.println("br 2 - figure quadrate ")
        );

        /* 1 - 2 */
        figure = new Rectangle(5, 10);

        matches(figure).as(
                Circle.class, of(5), () -> out.println("br 1 - figure circle"),
                Rectangle.class, of(5, 10), () -> out.println("br 2 - figure rectangle")
        );

        /* 2 - 1 */
        figure = new Triangle(10D, 20D);

        matches(figure).as(
                Triangle.class, of(10D, 20D), () -> out.println("br 1 - figure triangle"),
                Quadrate.class, of(10), () -> out.println("br 2 - figure quadrate ")
        );

        /* 2 - 2 */
        figure = new Rectangle(5, 10);

        matches(figure).as(
                Triangle.class, of(10D, 20D), () -> out.println("br 1 - figure triangle"),
                Rectangle.class, of(5, 10), () -> out.println("br 2 - figure rectangle")
        );

        /* 1 - 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        matches(figure).as(
                Circle.class, of(5), () -> out.println("br 1 - figure circle"),
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> out.println("br 2 - figure parallelepiped")
        );

        /* 3 - 1 */
        figure = new Circle(5);

        matches(figure).as(
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> out.println("br 1 - figure parallelepiped"),
                Circle.class, of(5), () -> out.println("br 2 - figure circle")
        );

        /* 3 - 3 */
        figure = new Tripiped(10f, 5f, 10f);

        matches(figure).as(
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> out.println("br 1 - figure parallelepiped"),
                Tripiped.class, of(10f, 5f, 10f), () -> out.println("br 2 - figure tripiped")
        );

        /* 2 - 3 */
        figure = new Triangle(10D, 20D);

        matches(figure).as(
                Triangle.class, of(10D, 20D), () -> out.println("br 1 - figure triangle"),
                Tripiped.class, of(10f, 5f, 10f), () -> out.println("br 2 - figure tripiped")
        );

        /* 3 - 2 */
        figure = new Tripiped(10f, 5f, 10f);

        matches(figure).as(
                Tripiped.class, of(10f, 5f, 10f), () -> out.println("br 1 - figure tripiped"),
                Triangle.class, of(10D, 20D), () -> out.println("br 2 - figure triangle")
        );
    }

    @Test
    public void matchesExpressionTest() {
        /* 1 */
        Figure figure = new Circle(5);

        int result = matches(figure,
                Circle.class, of(5), () -> 5
        );

        assertEquals(result, 5);

        /* 2 */
        figure = new Rectangle(5, 10);

        result = matches(figure,
                Rectangle.class, of(5, 10), () -> 10
        );

        assertEquals(result, 10);

        /* 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        result = matches(figure,
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> 15
        );

        assertEquals(result, 15);

        /* 1 - 1 */
        figure = new Quadrate(10);

        result = matches(figure,
                Circle.class, of(5), () -> 5,
                Quadrate.class, of(10), () -> 10
        );

        assertEquals(result, 10);

        /* 1 - 2 */
        figure = new Rectangle(5, 10);

        result = matches(figure,
                Circle.class, of(5), () -> 5,
                Rectangle.class, of(5, 10), () -> 10
        );

        assertEquals(result, 10);

        /* 2 - 1 */
        figure = new Triangle(10D, 20D);

        result = matches(figure,
                Triangle.class, of(10D, 20D), () -> 20,
                Quadrate.class, of(10), () -> 10
        );

        assertEquals(result, 20D);

        /* 2 - 2 */
        figure = new Rectangle(5, 10);

        result = matches(figure,
                Triangle.class, of(10D, 20D), () -> 20,
                Rectangle.class, of(5, 10), () -> 10
        );

        assertEquals(result, 10);

        /* 1 - 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        result = matches(figure,
                Circle.class, of(5), () -> 5,
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> 15
        );

        assertEquals(result, 15);

        /* 3 - 1 */
        figure = new Circle(5);

        result = matches(figure,
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> 15,
                Circle.class, of(5), () -> 10
        );

        assertEquals(result, 10);

        /* 3 - 3 */
        figure = new Tripiped(10f, 5f, 10f);

        result = matches(figure,
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> 15,
                Tripiped.class, of(10f, 5f, 10f), () -> 10
        );

        assertEquals(result, 10);

        /* 2 - 3 */
        figure = new Triangle(10D, 20D);

        result = matches(figure,
                Triangle.class, of(10D, 20D), () -> 15,
                Tripiped.class, of(10f, 5f, 10f), () -> 10
        );

        assertEquals(result, 15);

        /* 3 - 2 */
        figure = new Tripiped(10f, 5f, 10f);

        result = matches(figure,
                Tripiped.class, of(10f, 5f, 10f), () -> 10,
                Triangle.class, of(10D, 20D), () -> 15
        );

        assertEquals(result, 10);
    }

    @Test
    public void matchesAsExpressionTest() {
        /* 1 */
        Figure figure = new Circle(5);

        int result = matches(figure).as(
                Circle.class, of(5), () -> 5
        );

        assertEquals(result, 5);

        /* 2 */
        figure = new Rectangle(5, 10);

        result = matches(figure).as(
                Rectangle.class, of(5, 10), () -> 10
        );

        assertEquals(result, 10);

        /* 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        result = matches(figure).as(
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> 15
        );

        assertEquals(result, 15);

        /* 1 - 1 */
        figure = new Quadrate(10);

        result = matches(figure).as(
                Circle.class, of(5), () -> 5,
                Quadrate.class, of(10), () -> 10
        );

        assertEquals(result, 10);

        /* 1 - 2 */
        figure = new Rectangle(5, 10);

        result = matches(figure).as(
                Circle.class, of(5), () -> 5,
                Rectangle.class, of(5, 10), () -> 10
        );

        assertEquals(result, 10);

        /* 2 - 1 */
        figure = new Triangle(10D, 20D);

        result = matches(figure).as(
                Triangle.class, of(10D, 20D), () -> 20,
                Quadrate.class, of(10), () -> 10
        );

        assertEquals(result, 20D);

        /* 2 - 2 */
        figure = new Rectangle(5, 10);

        result = matches(figure).as(
                Triangle.class, of(10D, 20D), () -> 20,
                Rectangle.class, of(5, 10), () -> 10
        );

        assertEquals(result, 10);

        /* 1 - 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        result = matches(figure).as(
                Circle.class, of(5), () -> 5,
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> 15
        );

        assertEquals(result, 15);

        /* 3 - 1 */
        figure = new Circle(5);

        result = matches(figure).as(
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> 15,
                Circle.class, of(5), () -> 10
        );

        assertEquals(result, 10);

        /* 3 - 3 */
        figure = new Tripiped(10f, 5f, 10f);

        result = matches(figure).as(
                Parallelepiped.class, of((short) 5, (short) 10, (short) 15), () -> 15,
                Tripiped.class, of(10f, 5f, 10f), () -> 10
        );

        assertEquals(result, 10);

        /* 2 - 3 */
        figure = new Triangle(10D, 20D);

        result = matches(figure).as(
                Triangle.class, of(10D, 20D), () -> 15,
                Tripiped.class, of(10f, 5f, 10f), () -> 10
        );

        assertEquals(result, 15);

        /* 3 - 2 */
        figure = new Tripiped(10f, 5f, 10f);

        result = matches(figure).as(
                Tripiped.class, of(10f, 5f, 10f), () -> 10,
                Triangle.class, of(10D, 20D), () -> 15
        );

        assertEquals(result, 10);
    }
}
