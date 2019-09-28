package org.kl.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.kl.test.shape.*;

import java.util.*;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kl.pattern.PropertyPattern.*;

public class PropertyPatternTest {
    private static Set<Unpiped> listUpipeds;
    private static List<Bipiped> listBipipeds;
    private static Queue<Parallelepiped> listParallelepipeds;
    private static Map<Integer, String> map;

    @BeforeAll
    public static void init() {
        listUpipeds = new HashSet<Unpiped>() {{
            add(new Unpiped(5));
            add(new Unpiped(10));
            add(new Unpiped(15));
        }};

        listBipipeds = new ArrayList<Bipiped>() {{
            add(new Bipiped((short) 5, (short) 10));
            add(new Bipiped((short) 10, (short) 15));
            add(new Bipiped((short) 15, (short) 20));
        }};

        listParallelepipeds = new ArrayDeque<Parallelepiped>() {{
            add(new Parallelepiped((short) 5, (short) 10, (short) 15));
            add(new Parallelepiped((short) 10, (short) 15, (short) 20));
            add(new Parallelepiped((short) 15, (short) 20, (short) 25));
        }};

        map = new HashMap<Integer, String>() {{
            put(1, "one");
            put(2, "two");
            put(3, "three");
        }};
    }

    @AfterAll
    @SuppressWarnings("Duplicates")
    public static void destroy() {
        listUpipeds.clear();
        listBipipeds.clear();
        listParallelepipeds.clear();
        map.clear();
    }

    @Test
    public void matchesStatementTest() {
        Figure figure;

        /* 1 */
        figure = new Circle(5);

        matches(figure,
                Circle.class, of("radius"), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r)))
        );

        matches(figure,
                Circle.class, Circle::radius, (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r)))
        );

        matches(figure,
                Circle.class, of("radius", 5), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r)))
        );

        /* 2 */
        figure = new Rectangle(5, 10);

        matches(figure,
                Rectangle.class, of("width", "height"), (Integer w, Integer h) -> {
                    out.println("Rect square: " + (w * h));
                }
        );

        matches(figure,
                Rectangle.class, Rectangle::width, Rectangle::height, (Integer w, Integer h) -> {
                    out.println("Rect square: " + (w * h));
                }
        );

        matches(figure,
                Rectangle.class, of("width", 5, "height", 10), (Integer w, Integer h) -> {
                    out.println("Rect square: " + (w * h));
                }
        );

        /* 3 */
        figure = new Parallelepiped((short) 10, (short) 15, (short) 20);

        matches(figure,
                Parallelepiped.class, of("width", "longitude", "height"), (Short w, Short s, Short h) -> {
                    out.println("Parallelepiped square: " + (w * s * h));
                }
        );

        matches(figure,
                Parallelepiped.class, Parallelepiped::width, Parallelepiped::longitude, Parallelepiped::height,
                (Short w, Short s, Short h) -> out.println("Parallelepiped square: " + (w * s * h))
        );

        matches(figure,
                Parallelepiped.class, of("width", (short) 10, "longitude", (short) 15, "height", (short) 20),
                (Short w, Short s, Short h) -> out.println("Parallelepiped square: " + (w * s * h))
        );

        /* 1 - 1 */
        figure = new Quadrate(10);

        matches(figure,
                Circle.class, of("radius"),  (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Quadrate.class, of("width"), (Integer w) -> out.println("Quadrate square: " + (w * w))
        );

        matches(figure,
                Quadrate.class, Quadrate::width, (Integer w) -> out.println("Quadrate square: " + (w * w)),
                Circle.class, Circle::radius,  (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r)))
        );

        matches(figure,
                Circle.class, of("radius", 5),  (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Quadrate.class, of("width", 10), (Integer w) -> out.println("Quadrate square: " + (w * w))
        );

        /* 1 - 2 */
        figure = new Rectangle(5, 10);

        matches(figure,
                Circle.class, of("radius"), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Rectangle.class, of("width", "height"), (Integer w, Integer h) -> out.println("Rect square: " + (w * h))
        );

        matches(figure,
                Circle.class, Circle::radius, (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Rectangle.class, Rectangle::width, Rectangle::height, (Integer w, Integer h) ->  out.println("Rect square: " + (w * h))
        );

        matches(figure,
                Circle.class, of("radius", 5), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Rectangle.class, of("width", 5, "height", 10), (Integer w, Integer h) -> out.println("Rect square: " + (w * h))
        );

        /* 2 - 1 */
        figure = new Triangle(10D, 20D);

        matches(figure,
                Triangle.class, of("width", "height"), (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Quadrate.class, of("width"), (Integer w) -> out.println("Quadrate square: " + (w * w))
        );

        matches(figure,
                Triangle.class, Triangle::width, Triangle::height, (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Quadrate.class, Quadrate::width, (Integer w) -> out.println("Quadrate square: " + (w * w))
        );

        matches(figure,
                Triangle.class, of("width", 10D, "height", 20D), (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Quadrate.class, of("width", 10), (Integer w) -> out.println("Quadrate square: " + (w * w))
        );

        /* 2 - 2 */
        figure = new Rectangle(5, 10);

        matches(figure,
                Triangle.class, of("width", "height"), (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Rectangle.class, of("width", "height"), (Integer w, Integer h) -> out.println("Rect square: " + (w * h))
        );

        matches(figure,
                Triangle.class, Triangle::width, Triangle::height, (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Rectangle.class, Rectangle::width, Rectangle::height, (Integer w, Integer h) ->  out.println("Rect square: " + (w * h))
        );

        matches(figure,
                Triangle.class, of("width", 10D, "height", 20D), (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Rectangle.class, of("width", 5, "height", 10), (Integer w, Integer h) -> out.println("Rect square: " + (w * h))
        );

        /* 1 - 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        matches(figure,
                Circle.class, of("radius"), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Parallelepiped.class, of("width", "longitude", "height"), (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + (w * l * h));
                }
        );

        matches(figure,
                Circle.class, Circle::radius, (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Parallelepiped.class, Parallelepiped::width, Parallelepiped::longitude, Parallelepiped::height,
                (Short w, Short l, Short h) -> out.println("Parallelepiped square: " + (w * l * h))
        );

        matches(figure,
                Circle.class, of("radius", 5), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Parallelepiped.class, of("width", (short) 5, "longitude", (short) 10, "height", (short) 15),
                (Short w, Short l, Short h) -> out.println("Parallelepiped square: " + (w * l * h))
        );

        /* 3 - 1 */
        figure = new Circle(5);

        matches(figure,
                Parallelepiped.class, of("width", "longitude", "height"), (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + (w * l * h));
                },
                Circle.class, of("radius"), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r)))
        );

        matches(figure,
                Parallelepiped.class, Parallelepiped::width, Parallelepiped::longitude, Parallelepiped::height,
                (Short w, Short l, Short h) -> out.println("Parallelepiped square: " + (w * l * h)),
                Circle.class, Circle::radius, (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r)))
        );

        matches(figure,
                Parallelepiped.class, of("width", (short) 5, "longitude", (short) 10, "height", (short) 15),
                (Short w, Short l, Short h) -> out.println("Parallelepiped square: " + (w * l * h)),
                Circle.class, of("radius", 5), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r)))
        );

        /* 3 - 3 */
        figure = new Tripiped(10f, 5f, 10f);

        matches(figure,
                Parallelepiped.class, of("width", "longitude", "height"), (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + (w * l * h));
                },
                Tripiped.class, of("width", "longitude", "height"), (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + (w * l * h));
                }
        );

        matches(figure,
                Parallelepiped.class, Parallelepiped::width, Parallelepiped::longitude, Parallelepiped::height,
                (Short w, Short l, Short h) -> out.println("Parallelepiped square: " + (w * l * h)),
                Tripiped.class, Tripiped::width, Tripiped::longitude, Tripiped::height, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + (w * l * h));
                }
        );

        matches(figure,
                Parallelepiped.class, of("width", (short) 5, "longitude", (short) 10, "height", (short) 15),
                (Short w, Short l, Short h) -> out.println("Parallelepiped square: " + (w * l * h)),
                Tripiped.class, of("width", 10f, "longitude", 5f, "height", 10f), (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + (w * l * h));
                }
        );

        /* 2 - 3 */
        figure = new Triangle(10D, 20D);

        matches(figure,
                Triangle.class, of("width", "height"), (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Tripiped.class, of("width", "longitude", "height"), (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + (w * l * h));
                }
        );

        matches(figure,
                Triangle.class, Triangle::width, Triangle::height, (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Tripiped.class, Tripiped::width, Tripiped::longitude, Tripiped::height, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + (w * l * h));
                }
        );

        matches(figure,
                Triangle.class, of("width", 10D, "height", 20D), (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Tripiped.class, of("width", 10f, "longitude", 5f, "height", 10f), (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + (w * l * h));
                }
        );

        /* 3 - 2 */
        figure = new Tripiped(10f, 5f, 10f);

        matches(figure,
                Tripiped.class, of("width", "longitude", "height"), (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + (w * l * h));
                },
                Triangle.class, of("width", "height"), (Double w, Double h) -> out.println("Triangle square: " + (w * h))
        );

        matches(figure,
                Tripiped.class, Tripiped::width, Tripiped::longitude, Tripiped::height, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + (w * l * h));
                },
                Triangle.class, Triangle::width, Triangle::height, (Double w, Double h) -> out.println("Triangle square: " + (w * h))
        );

        matches(figure,
                Tripiped.class, of("width", 10f, "longitude", 5f, "height", 10f), (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + (w * l * h));
                },
                Triangle.class, of("width", 10D, "height", 20D), (Double w, Double h) -> out.println("Triangle square: " + (w * h))
        );
    }

    @Test
    public void matchesAsStatementTest() {
        Figure figure;

        /* 1 */
        figure = new Circle(5);

        matches(figure).as(
                Circle.class, of("radius"), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r)))
        );

        matches(figure).as(
                Circle.class, Circle::radius, (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r)))
        );

        matches(figure).as(
                Circle.class, of("radius", 5), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r)))
        );

        /* 2 */
        figure = new Rectangle(5, 10);

        matches(figure).as(
                Rectangle.class, of("width", "height"), (Integer w, Integer h) -> {
                    out.println("Rect square: " + (w * h));
                }
        );

        matches(figure).as(
                Rectangle.class, Rectangle::width, Rectangle::height, (Integer w, Integer h) -> {
                    out.println("Rect square: " + (w * h));
                }
        );

        matches(figure).as(
                Rectangle.class, of("width", 5, "height", 10), (Integer w, Integer h) -> {
                    out.println("Rect square: " + (w * h));
                }
        );

        /* 3 */
        figure = new Parallelepiped((short) 10, (short) 15, (short) 20);

        matches(figure).as(
                Parallelepiped.class, of("width", "longitude", "height"), (Short w, Short s, Short h) -> {
                    out.println("Parallelepiped square: " + (w * s * h));
                }
        );

        matches(figure).as(
                Parallelepiped.class, Parallelepiped::width, Parallelepiped::longitude, Parallelepiped::height,
                (Short w, Short s, Short h) -> out.println("Parallelepiped square: " + (w * s * h))
        );

        matches(figure).as(
                Parallelepiped.class, of("width", (short) 10, "longitude", (short) 15, "height", (short) 20),
                (Short w, Short s, Short h) -> out.println("Parallelepiped square: " + (w * s * h))
        );

        /* 1 - 1 */
        figure = new Quadrate(10);

        matches(figure).as(
                Circle.class, of("radius"),  (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Quadrate.class, of("width"), (Integer w) -> out.println("Quadrate square: " + (w * w))
        );

        matches(figure).as(
                Quadrate.class, Quadrate::width, (Integer w) -> out.println("Quadrate square: " + (w * w)),
                Circle.class, Circle::radius,  (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r)))
        );

        matches(figure).as(
                Circle.class, of("radius", 5),  (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Quadrate.class, of("width", 10), (Integer w) -> out.println("Quadrate square: " + (w * w))
        );

        /* 1 - 2 */
        figure = new Rectangle(5, 10);

        matches(figure).as(
                Circle.class, of("radius"), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Rectangle.class, of("width", "height"), (Integer w, Integer h) -> out.println("Rect square: " + (w * h))
        );

        matches(figure).as(
                Circle.class, Circle::radius, (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Rectangle.class, Rectangle::width, Rectangle::height, (Integer w, Integer h) ->  out.println("Rect square: " + (w * h))
        );

        matches(figure).as(
                Circle.class, of("radius", 5), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Rectangle.class, of("width", 5, "height", 10), (Integer w, Integer h) -> out.println("Rect square: " + (w * h))
        );

        /* 2 - 1 */
        figure = new Triangle(10D, 20D);

        matches(figure).as(
                Triangle.class, of("width", "height"), (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Quadrate.class, of("width"), (Integer w) -> out.println("Quadrate square: " + (w * w))
        );

        matches(figure).as(
                Triangle.class, Triangle::width, Triangle::height, (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Quadrate.class, Quadrate::width, (Integer w) -> out.println("Quadrate square: " + (w * w))
        );

        matches(figure).as(
                Triangle.class, of("width", 10D, "height", 20D), (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Quadrate.class, of("width", 10), (Integer w) -> out.println("Quadrate square: " + (w * w))
        );

        /* 2 - 2 */
        figure = new Rectangle(5, 10);

        matches(figure).as(
                Triangle.class, of("width", "height"), (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Rectangle.class, of("width", "height"), (Integer w, Integer h) -> out.println("Rect square: " + (w * h))
        );

        matches(figure).as(
                Triangle.class, Triangle::width, Triangle::height, (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Rectangle.class, Rectangle::width, Rectangle::height, (Integer w, Integer h) ->  out.println("Rect square: " + (w * h))
        );

        matches(figure).as(
                Triangle.class, of("width", 10D, "height", 20D), (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Rectangle.class, of("width", 5, "height", 10), (Integer w, Integer h) -> out.println("Rect square: " + (w * h))
        );

        /* 1 - 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        matches(figure).as(
                Circle.class, of("radius"), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Parallelepiped.class, of("width", "longitude", "height"), (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + (w * l * h));
                }
        );

        matches(figure).as(
                Circle.class, Circle::radius, (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Parallelepiped.class, Parallelepiped::width, Parallelepiped::longitude, Parallelepiped::height,
                (Short w, Short l, Short h) -> out.println("Parallelepiped square: " + (w * l * h))
        );

        matches(figure).as(
                Circle.class, of("radius", 5), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r))),
                Parallelepiped.class, of("width", (short) 5, "longitude", (short) 10, "height", (short) 15),
                (Short w, Short l, Short h) -> out.println("Parallelepiped square: " + (w * l * h))
        );

        /* 3 - 1 */
        figure = new Circle(5);

        matches(figure).as(
                Parallelepiped.class, of("width", "longitude", "height"), (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + (w * l * h));
                },
                Circle.class, of("radius"), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r)))
        );

        matches(figure).as(
                Parallelepiped.class, Parallelepiped::width, Parallelepiped::longitude, Parallelepiped::height,
                (Short w, Short l, Short h) -> out.println("Parallelepiped square: " + (w * l * h)),
                Circle.class, Circle::radius, (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r)))
        );

        matches(figure).as(
                Parallelepiped.class, of("width", (short) 5, "longitude", (short) 10, "height", (short) 15),
                (Short w, Short l, Short h) -> out.println("Parallelepiped square: " + (w * l * h)),
                Circle.class, of("radius", 5), (Integer r) -> out.println("Circle square: " + ((int) (2 * Math.PI * r)))
        );

        /* 3 - 3 */
        figure = new Tripiped(10f, 5f, 10f);

        matches(figure).as(
                Parallelepiped.class, of("width", "longitude", "height"), (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + (w * l * h));
                },
                Tripiped.class, of("width", "longitude", "height"), (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + (w * l * h));
                }
        );

        matches(figure).as(
                Parallelepiped.class, Parallelepiped::width, Parallelepiped::longitude, Parallelepiped::height,
                (Short w, Short l, Short h) -> out.println("Parallelepiped square: " + (w * l * h)),
                Tripiped.class, Tripiped::width, Tripiped::longitude, Tripiped::height, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + (w * l * h));
                }
        );

        matches(figure).as(
                Parallelepiped.class, of("width", (short) 5, "longitude", (short) 10, "height", (short) 15),
                (Short w, Short l, Short h) -> out.println("Parallelepiped square: " + (w * l * h)),
                Tripiped.class, of("width", 10f, "longitude", 5f, "height", 10f), (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + (w * l * h));
                }
        );

        /* 2 - 3 */
        figure = new Triangle(10D, 20D);

        matches(figure).as(
                Triangle.class, of("width", "height"), (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Tripiped.class, of("width", "longitude", "height"), (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + (w * l * h));
                }
        );

        matches(figure).as(
                Triangle.class, Triangle::width, Triangle::height, (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Tripiped.class, Tripiped::width, Tripiped::longitude, Tripiped::height, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + (w * l * h));
                }
        );

        matches(figure).as(
                Triangle.class, of("width", 10D, "height", 20D), (Double w, Double h) -> out.println("Triangle square: " + (w * h)),
                Tripiped.class, of("width", 10f, "longitude", 5f, "height", 10f), (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + (w * l * h));
                }
        );

        /* 3 - 2 */
        figure = new Tripiped(10f, 5f, 10f);

        matches(figure).as(
                Tripiped.class, of("width", "longitude", "height"), (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + (w * l * h));
                },
                Triangle.class, of("width", "height"), (Double w, Double h) -> out.println("Triangle square: " + (w * h))
        );

        matches(figure).as(
                Tripiped.class, Tripiped::width, Tripiped::longitude, Tripiped::height, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + (w * l * h));
                },
                Triangle.class, Triangle::width, Triangle::height, (Double w, Double h) -> out.println("Triangle square: " + (w * h))
        );

        matches(figure).as(
                Tripiped.class, of("width", 10f, "longitude", 5f, "height", 10f), (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + (w * l * h));
                },
                Triangle.class, of("width", 10D, "height", 20D), (Double w, Double h) -> out.println("Triangle square: " + (w * h))
        );
    }

    @Test
    public void matchesExpressionTest() {
        /* 1 */
        Figure figure = new Circle(5);

        int result = matches(figure,
                Circle.class, of("radius"), (Integer r) -> ((int) (2 * Math.PI * r))
        );

        assertEquals(result, 31);

        result = matches(figure,
                Circle.class, Circle::radius, (Integer r) -> ((int) (2 * Math.PI * r))
        );

        assertEquals(result, 31);

        result = matches(figure,
                Circle.class, of("radius", 5), (Integer r) -> ((int) (2 * Math.PI * r))
        );

        assertEquals(result, 31);

        /* 2 */
        figure = new Rectangle(5, 10);

        result = matches(figure,
                Rectangle.class, of("width", "height"), (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        result = matches(figure,
                Rectangle.class, Rectangle::width, Rectangle::height, (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        result = matches(figure,
                Rectangle.class, of("width", 5, "height", 10), (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        /* 3 */
        figure = new Parallelepiped((short) 10, (short) 15, (short) 20);

        result = matches(figure,
                Parallelepiped.class, of("width", "longitude", "height"), (Short w, Short s, Short h) -> (w * s * h)
        );

        assertEquals(result, 3_000);

        result = matches(figure,
                Parallelepiped.class, Parallelepiped::width, Parallelepiped::longitude, Parallelepiped::height,
                (Short w, Short s, Short h) -> (w * s * h)
        );

        assertEquals(result, 3_000);

        result = matches(figure,
                Parallelepiped.class, of("width", (short) 10, "longitude", (short) 15, "height", (short) 20),
                (Short w, Short s, Short h) -> (w * s * h)
        );

        assertEquals(result, 3_000);

        /* 1 - 1 */
        figure = new Quadrate(10);

        result = matches(figure,
                Circle.class, of("radius"),  (Integer r) -> ((int) (2 * Math.PI * r)),
                Quadrate.class, of("width"), (Integer w) -> (w * w)
        );

        assertEquals(result, 100);

        result = matches(figure,
                Circle.class, Circle::radius,  (Integer r) -> ((int) (2 * Math.PI * r)),
                Quadrate.class, Quadrate::width, (Integer w) -> (w * w)
        );

        assertEquals(result, 100);

        result = matches(figure,
                Circle.class, of("radius", 5),  (Integer r) -> ((int) (2 * Math.PI * r)),
                Quadrate.class, of("width", 10), (Integer w) -> (w * w)
        );

        assertEquals(result, 100);

        /* 1 - 2 */
        figure = new Rectangle(5, 10);

        result = matches(figure,
                Circle.class, of("radius"), (Integer r) -> ((int) (2 * Math.PI * r)),
                Rectangle.class, of("width", "height"), (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        result = matches(figure,
                Circle.class, Circle::radius, (Integer r) -> ((int) (2 * Math.PI * r)),
                Rectangle.class, Rectangle::width, Rectangle::height, (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        result = matches(figure,
                Circle.class, of("radius", 5), (Integer r) -> ((int) (2 * Math.PI * r)),
                Rectangle.class, of("width", 5, "height", 10), (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        /* 2 - 1 */
        figure = new Triangle(10D, 20D);

        result = matches(figure,
                Triangle.class, of("width", "height"), (Double w, Double h) -> (int)(w * h),
                Quadrate.class, of("width"), (Integer w) -> (w * w)
        );

        assertEquals(result, 200D);

        result = matches(figure,
                Triangle.class, Triangle::width, Triangle::height, (Double w, Double h) -> (int)(w * h),
                Quadrate.class, Quadrate::width, (Integer w) -> (w * w)
        );

        assertEquals(result, 200D);

        result = matches(figure,
                Triangle.class, of("width", 10D, "height", 20D), (Double w, Double h) -> (int)(w * h),
                Quadrate.class, of("width", 10), (Integer w) -> (w * w)
        );

        assertEquals(result, 200D);

        /* 2 - 2 */
        figure = new Rectangle(5, 10);

        result = matches(figure,
                Triangle.class, of("width", "height"), (Double w, Double h) -> (int)(w * h),
                Rectangle.class, of("width", "height"), (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        result = matches(figure,
                Triangle.class, Triangle::width, Triangle::height, (Double w, Double h) -> (int)(w * h),
                Rectangle.class, Rectangle::width, Rectangle::height, (Integer w, Integer h) ->  (w * h)
        );

        assertEquals(result, 50);

        result = matches(figure,
                Triangle.class, of("width", 10D, "height", 20D), (Double w, Double h) -> (int)(w * h),
                Rectangle.class, of("width", 5, "height", 10), (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        /* 1 - 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        result = matches(figure,
                Circle.class, of("radius"), (Integer r) -> ((int) (2 * Math.PI * r)),
                Parallelepiped.class, of("width", "longitude", "height"), (Short w, Short l, Short h) -> (w * l * h)
        );

        assertEquals(result, 750);

        result = matches(figure,
                Circle.class, Circle::radius, (Integer r) -> (int) (2 * Math.PI * r),
                Parallelepiped.class, Parallelepiped::width, Parallelepiped::longitude, Parallelepiped::height,
                (Short w, Short l, Short h) -> w * l * h
        );

        assertEquals(result, 750);

        result = matches(figure,
                Circle.class, of("radius", 5), (Integer r) -> ((int) (2 * Math.PI * r)),
                Parallelepiped.class, of("width", (short) 5, "longitude", (short) 10, "height", (short) 15),
                (Short w, Short l, Short h) -> w * l * h
        );

        assertEquals(result, 750);

        /* 3 - 1 */
        figure = new Circle(5);

        result = matches(figure,
                Parallelepiped.class, of("width", "longitude", "height"), (Short w, Short l, Short h) -> w * l * h,
                Circle.class, of("radius"), (Integer r) -> (int)(2 * Math.PI * r)
        );

        assertEquals(result, 31);

        result = matches(figure,
                Parallelepiped.class, Parallelepiped::width, Parallelepiped::longitude, Parallelepiped::height,
                (Short w, Short l, Short h) -> w * l * h,
                Circle.class, Circle::radius, (Integer r) -> (int)(2 * Math.PI * r)
        );

        assertEquals(result, 31);

        result = matches(figure,
                Parallelepiped.class, of("width", (short) 5, "longitude", (short) 10, "height", (short) 15),
                (Short w, Short l, Short h) -> w * l * h,
                Circle.class, of("radius", 5), (Integer r) -> (int)(2 * Math.PI * r)
        );

        assertEquals(result, 31);

        /* 3 - 3 */
        figure = new Tripiped(10f, 5f, 10f);

        result = matches(figure,
                Parallelepiped.class, of("width", "longitude", "height"), (Short w, Short l, Short h) -> w * l * h,
                Tripiped.class, of("width", "longitude", "height"), (Float w, Float l, Float h) -> (int)(w * l * h)
        );

        assertEquals(result, 500f);

        result = matches(figure,
                Parallelepiped.class, Parallelepiped::width, Parallelepiped::longitude, Parallelepiped::height,
                (Short w, Short l, Short h) -> w * l * h,
                Tripiped.class, Tripiped::width, Tripiped::longitude, Tripiped::height,
                (Float w, Float l, Float h) -> (int)(w * l * h)
        );

        assertEquals(result, 500f);

        result = matches(figure,
                Parallelepiped.class, of("width", (short) 5, "longitude", (short) 10, "height", (short) 15),
                (Short w, Short l, Short h) -> w * l * h,
                Tripiped.class, of("width", 10f, "longitude", 5f, "height", 10f),
                (Float w, Float l, Float h) -> (int)(w * l * h)
        );

        assertEquals(result, 500f);

        /* 2 - 3 */
        figure = new Triangle(10D, 20D);

        result = matches(figure,
                Triangle.class, of("width", "height"),
                (Double w, Double h) -> (int)(w * h),
                Tripiped.class, of("width", "longitude", "height"),
                (Float w, Float l, Float h) -> (int)(w * l * h)
        );

        assertEquals(result, 200D);

        result = matches(figure,
                Triangle.class, Triangle::width, Triangle::height,
                (Double w, Double h) -> (int)(w * h),
                Tripiped.class, Tripiped::width, Tripiped::longitude, Tripiped::height,
                (Float w, Float l, Float h) -> (int)(w * l * h)
        );

        assertEquals(result, 200D);

        result = matches(figure,
                Triangle.class, of("width", 10D, "height", 20D),
                (Double w, Double h) -> (int)(w * h),
                Tripiped.class, of("width", 10f, "longitude", 5f, "height", 10f),
                (Float w, Float l, Float h) -> (int)(w * l * h)
        );

        assertEquals(result, 200D);

        /* 3 - 2 */
        figure = new Tripiped(10f, 5f, 10f);

        result = matches(figure,
                Tripiped.class, of("width", "longitude", "height"), (Float w, Float l, Float h) -> (int)(w * l * h),
                Triangle.class, of("width", "height"), (Double w, Double h) -> (int)(w * h)
        );

        assertEquals(result, 500f);

        result = matches(figure,
                Tripiped.class, Tripiped::width, Tripiped::longitude, Tripiped::height,
                (Float w, Float l, Float h) -> (int)(w * l * h),
                Triangle.class, Triangle::width, Triangle::height,
                (Double w, Double h) -> (int)(w * h)
        );

        assertEquals(result, 500f);

        result = matches(figure,
                Tripiped.class, of("width", 10f, "longitude", 5f, "height", 10f),
                (Float w, Float l, Float h) -> (int)(w * l * h),
                Triangle.class, of("width", 10D, "height", 20D),
                (Double w, Double h) -> (int)(w * h)
        );

        assertEquals(result, 500f);
    }

    @Test
    public void matchesAsExpressionTest() {
        /* 1 */
        Figure figure = new Circle(5);

        int result = matches(figure).as(
                Circle.class, of("radius"), (Integer r) -> ((int) (2 * Math.PI * r))
        );

        assertEquals(result, 31);

        result = matches(figure).as(
                Circle.class, Circle::radius, (Integer r) -> ((int) (2 * Math.PI * r))
        );

        assertEquals(result, 31);

        result = matches(figure).as(
                Circle.class, of("radius", 5), (Integer r) -> ((int) (2 * Math.PI * r))
        );

        assertEquals(result, 31);

        /* 2 */
        figure = new Rectangle(5, 10);

        result = matches(figure).as(
                Rectangle.class, of("width", "height"), (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        result = matches(figure).as(
                Rectangle.class, Rectangle::width, Rectangle::height, (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        result = matches(figure).as(
                Rectangle.class, of("width", 5, "height", 10), (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        /* 3 */
        figure = new Parallelepiped((short) 10, (short) 15, (short) 20);

        result = matches(figure).as(
                Parallelepiped.class, of("width", "longitude", "height"), (Short w, Short s, Short h) -> (w * s * h)
        );

        assertEquals(result, 3_000);

        result = matches(figure).as(
                Parallelepiped.class, Parallelepiped::width, Parallelepiped::longitude, Parallelepiped::height,
                (Short w, Short s, Short h) -> (w * s * h)
        );

        assertEquals(result, 3_000);

        result = matches(figure).as(
                Parallelepiped.class, of("width", (short) 10, "longitude", (short) 15, "height", (short) 20),
                (Short w, Short s, Short h) -> (w * s * h)
        );

        assertEquals(result, 3_000);

        /* 1 - 1 */
        figure = new Quadrate(10);

        result = matches(figure).as(
                Circle.class, of("radius"),  (Integer r) -> ((int) (2 * Math.PI * r)),
                Quadrate.class, of("width"), (Integer w) -> (w * w)
        );

        assertEquals(result, 100);

        result = matches(figure).as(
                Circle.class, Circle::radius,  (Integer r) -> ((int) (2 * Math.PI * r)),
                Quadrate.class, Quadrate::width, (Integer w) -> (w * w)
        );

        assertEquals(result, 100);

        result = matches(figure).as(
                Circle.class, of("radius", 5),  (Integer r) -> ((int) (2 * Math.PI * r)),
                Quadrate.class, of("width", 10), (Integer w) -> (w * w)
        );

        assertEquals(result, 100);

        /* 1 - 2 */
        figure = new Rectangle(5, 10);

        result = matches(figure).as(
                Circle.class, of("radius"), (Integer r) -> ((int) (2 * Math.PI * r)),
                Rectangle.class, of("width", "height"), (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        result = matches(figure).as(
                Circle.class, Circle::radius, (Integer r) -> ((int) (2 * Math.PI * r)),
                Rectangle.class, Rectangle::width, Rectangle::height, (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        result = matches(figure).as(
                Circle.class, of("radius", 5), (Integer r) -> ((int) (2 * Math.PI * r)),
                Rectangle.class, of("width", 5, "height", 10), (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        /* 2 - 1 */
        figure = new Triangle(10D, 20D);

        result = matches(figure).as(
                Triangle.class, of("width", "height"), (Double w, Double h) -> (int)(w * h),
                Quadrate.class, of("width"), (Integer w) -> (w * w)
        );

        assertEquals(result, 200D);

        result = matches(figure).as(
                Triangle.class, Triangle::width, Triangle::height, (Double w, Double h) -> (int)(w * h),
                Quadrate.class, Quadrate::width, (Integer w) -> (w * w)
        );

        assertEquals(result, 200D);

        result = matches(figure).as(
                Triangle.class, of("width", 10D, "height", 20D), (Double w, Double h) -> (int)(w * h),
                Quadrate.class, of("width", 10), (Integer w) -> (w * w)
        );

        assertEquals(result, 200D);

        /* 2 - 2 */
        figure = new Rectangle(5, 10);

        result = matches(figure).as(
                Triangle.class, of("width", "height"), (Double w, Double h) -> (int)(w * h),
                Rectangle.class, of("width", "height"), (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        result = matches(figure).as(
                Triangle.class, Triangle::width, Triangle::height, (Double w, Double h) -> (int)(w * h),
                Rectangle.class, Rectangle::width, Rectangle::height, (Integer w, Integer h) ->  (w * h)
        );

        assertEquals(result, 50);

        result = matches(figure).as(
                Triangle.class, of("width", 10D, "height", 20D), (Double w, Double h) -> (int)(w * h),
                Rectangle.class, of("width", 5, "height", 10), (Integer w, Integer h) -> (w * h)
        );

        assertEquals(result, 50);

        /* 1 - 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        result = matches(figure).as(
                Circle.class, of("radius"), (Integer r) -> ((int) (2 * Math.PI * r)),
                Parallelepiped.class, of("width", "longitude", "height"), (Short w, Short l, Short h) -> (w * l * h)
        );

        assertEquals(result, 750);

        result = matches(figure).as(
                Circle.class, Circle::radius, (Integer r) -> (int) (2 * Math.PI * r),
                Parallelepiped.class, Parallelepiped::width, Parallelepiped::longitude, Parallelepiped::height,
                (Short w, Short l, Short h) -> w * l * h
        );

        assertEquals(result, 750);

        result = matches(figure).as(
                Circle.class, of("radius", 5), (Integer r) -> ((int) (2 * Math.PI * r)),
                Parallelepiped.class, of("width", (short) 5, "longitude", (short) 10, "height", (short) 15),
                (Short w, Short l, Short h) -> w * l * h
        );

        assertEquals(result, 750);

        /* 3 - 1 */
        figure = new Circle(5);

        result = matches(figure).as(
                Parallelepiped.class, of("width", "longitude", "height"), (Short w, Short l, Short h) -> w * l * h,
                Circle.class, of("radius"), (Integer r) -> (int)(2 * Math.PI * r)
        );

        assertEquals(result, 31);

        result = matches(figure).as(
                Parallelepiped.class, Parallelepiped::width, Parallelepiped::longitude, Parallelepiped::height,
                (Short w, Short l, Short h) -> w * l * h,
                Circle.class, Circle::radius, (Integer r) -> (int)(2 * Math.PI * r)
        );

        assertEquals(result, 31);

        result = matches(figure).as(
                Parallelepiped.class, of("width", (short) 5, "longitude", (short) 10, "height", (short) 15),
                (Short w, Short l, Short h) -> w * l * h,
                Circle.class, of("radius", 5), (Integer r) -> (int)(2 * Math.PI * r)
        );

        assertEquals(result, 31);

        /* 3 - 3 */
        figure = new Tripiped(10f, 5f, 10f);

        result = matches(figure).as(
                Parallelepiped.class, of("width", "longitude", "height"), (Short w, Short l, Short h) -> w * l * h,
                Tripiped.class, of("width", "longitude", "height"), (Float w, Float l, Float h) -> (int)(w * l * h)
        );

        assertEquals(result, 500f);

        result = matches(figure).as(
                Parallelepiped.class, Parallelepiped::width, Parallelepiped::longitude, Parallelepiped::height,
                (Short w, Short l, Short h) -> w * l * h,
                Tripiped.class, Tripiped::width, Tripiped::longitude, Tripiped::height,
                (Float w, Float l, Float h) -> (int)(w * l * h)
        );

        assertEquals(result, 500f);

        result = matches(figure).as(
                Parallelepiped.class, of("width", (short) 5, "longitude", (short) 10, "height", (short) 15),
                (Short w, Short l, Short h) -> w * l * h,
                Tripiped.class, of("width", 10f, "longitude", 5f, "height", 10f),
                (Float w, Float l, Float h) -> (int)(w * l * h)
        );

        assertEquals(result, 500f);

        /* 2 - 3 */
        figure = new Triangle(10D, 20D);

        result = matches(figure).as(
                Triangle.class, of("width", "height"),
                (Double w, Double h) -> (int)(w * h),
                Tripiped.class, of("width", "longitude", "height"),
                (Float w, Float l, Float h) -> (int)(w * l * h)
        );

        assertEquals(result, 200D);

        result = matches(figure).as(
                Triangle.class, Triangle::width, Triangle::height,
                (Double w, Double h) -> (int)(w * h),
                Tripiped.class, Tripiped::width, Tripiped::longitude, Tripiped::height,
                (Float w, Float l, Float h) -> (int)(w * l * h)
        );

        assertEquals(result, 200D);

        result = matches(figure).as(
                Triangle.class, of("width", 10D, "height", 20D),
                (Double w, Double h) -> (int)(w * h),
                Tripiped.class, of("width", 10f, "longitude", 5f, "height", 10f),
                (Float w, Float l, Float h) -> (int)(w * l * h)
        );

        assertEquals(result, 200D);

        /* 3 - 2 */
        figure = new Tripiped(10f, 5f, 10f);

        result = matches(figure).as(
                Tripiped.class, of("width", "longitude", "height"), (Float w, Float l, Float h) -> (int)(w * l * h),
                Triangle.class, of("width", "height"), (Double w, Double h) -> (int)(w * h)
        );

        assertEquals(result, 500f);

        result = matches(figure).as(
                Tripiped.class, Tripiped::width, Tripiped::longitude, Tripiped::height,
                (Float w, Float l, Float h) -> (int)(w * l * h),
                Triangle.class, Triangle::width, Triangle::height,
                (Double w, Double h) -> (int)(w * h)
        );

        assertEquals(result, 500f);

        result = matches(figure).as(
                Tripiped.class, of("width", 10f, "longitude", 5f, "height", 10f),
                (Float w, Float l, Float h) -> (int)(w * l * h),
                Triangle.class, of("width", 10D, "height", 20D),
                (Double w, Double h) -> (int)(w * h)
        );

        assertEquals(result, 500f);
    }

    @Test
    public void foreachLoopTest() {
        /* 1 */
        foreach(listUpipeds, of("radius"), (Double r) -> {
            System.out.println("Upiped square I: " + (2 * Math.PI * r));
        });

        foreach(listUpipeds, Unpiped::radius, (Double r) -> {
            System.out.println("Unpiped square II: " + (2 * Math.PI * r));
        });

        /* 2 */
        foreach(listBipipeds, of("width", "height"), (Short w, Short h) -> {
            System.out.println("Bipiped square I: " + (w * h));
        });

        foreach(listBipipeds, Bipiped::width, Bipiped::height, (Short w, Short h) -> {
            System.out.println("Bipiped square II: " + (w * h));
        });

        /* 3 */
        foreach(listParallelepipeds, of("width", "longitude", "height"), (Short w, Short l, Short h) -> {
            System.out.println("Parallelepiped square I: " + (w * l * h));
        });

        foreach(listParallelepipeds, Parallelepiped::width, Parallelepiped::longitude, Parallelepiped::height, (Short w, Short l, Short h) -> {
            System.out.println("Parallelepiped square II: " + (w * l * h));
        });

        /* 4 */
        foreach(map, of("key", "value"), (Integer k, String v) -> {
            System.out.println("map entry I: " + k + " - " + v);
        });
    }

    @Test
    public void letOperationTest() {
        /* 1 */
        Unpiped unpiped = new Unpiped(5);

        let(unpiped, of("radius"), (Double r) -> {
            out.println("radius: " + r);
        });

        let(unpiped, Unpiped::radius, (Double r) -> {
            out.println("radius: " + r);
        });

        /* 2 */
        Bipiped bipiped = new Bipiped((short) 15, (short) 25);

        let(bipiped, of("width", "height"), (Short w, Short h) -> {
            out.println("border: " + w + " " + h);
        });

        let(bipiped, Bipiped::width, Bipiped::height, (Short w, Short h) -> {
            out.println("border: " + w + " " + h);
        });

        /* 3 */
        Tripiped tripiped = new Tripiped(5, 10, 15);

        let(tripiped, of("width", "longitude", "height"), (Float w, Float l, Float h) -> {
            out.println("border: " + w + " " + l + " " + h);
        });

        let(tripiped, Tripiped::width, Tripiped::longitude, Tripiped::height, (Float w, Float l, Float h) -> {
            out.println("border: " + w + " " + l + " " + h);
        });
    }
}
