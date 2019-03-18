package org.kl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.kl.error.PatternException;
import org.kl.shape.*;
import org.kl.state.Default;

import java.util.*;

import static java.lang.System.out;
import static org.kl.handle.DeconstructPattern.matches;
import static org.kl.handle.DeconstructPattern.foreach;

public class DeconstructPatternTest {
    private static Set<Circle>     listCircles;
    private static List<Rectangle> listRectangles;
    private static Queue<Tripiped> listTripipeds;
    private static List<Quarpiped> listQuarpiped;
    private static Map<Integer, String> map;

    @BeforeAll
    public static void init() {
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
    public static void destroy() {
        listCircles.clear();
        listRectangles.clear();
        listTripipeds.clear();
        listQuarpiped.clear();
        map.clear();
    }

    @Disabled
    @Test
    public void matchesStatementTest() throws PatternException {
        Figure figure;

        /* 1 */
        figure = new Circle();

        matches(figure,
                Circle.class, (Integer r) -> { out.println("Circle square: " + ((int)(2 * Math.PI * r))); }
        );

        /* 2 */
        figure = new Rectangle();

        matches(figure,
                Rectangle.class, (Integer w, Integer h) -> { out.println("Rectangle square: " + (w * h)); }
        );

        /* 3 */
        figure = new Parallelepiped();

        matches(figure,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                }
        );

        /* 4 */
        figure = new Quartangle();

        matches(figure,
                Quartangle.class, (Byte a, Byte b, Byte c, Byte d) -> {
                    out.println("Quartangle square: " + (a * b * c * d));
                }
        );

        /* 1 - 1 */
        figure = new Quadrate();

        matches(figure,
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); }
        );

        /* 1 - 2 */
        figure = new Rectangle();

        matches(figure,
                Circle.class,    (Integer r)            -> { out.println("Circle    square: " + ((int)(2 * Math.PI * r))); },
                Rectangle.class, (Integer w, Integer h) -> { out.println("Rectangle square: " + (w * h)); }
        );

        /* 2 - 1 */
        figure = new Triangle();

        matches(figure,
                Triangle.class, (Double w, Double h) -> { out.println("Triangle square: " + (w * h)); },
                Quadrate.class, (Integer a)          -> { out.println("Quadrate  square: " + (a * a)); }
        );

        /* 2 - 2 */
        figure = new Rectangle();

        matches(figure,
                Triangle.class,  (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Rectangle.class, (Integer w, Integer h) -> { out.println("Rectangle square: " + (w * h)); }
        );

        /* 1 - 3 */
        figure = new Parallelepiped();

        matches(figure,
                Circle.class,         (Integer r) -> { out.println("Circle    square: " + ((int)(2 * Math.PI * r))); },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                }
        );

        /* 3 - 1 */
        figure = new Circle();

        matches(figure,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Circle.class,         (Integer r) -> { out.println("Circle    square: " + ((int)(2 * Math.PI * r))); }
        );

        /* 3 - 3 */
        figure = new Tripiped();

        matches(figure,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Tripiped.class,       (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                }
        );

        /* 2 - 3 */
        figure = new Triangle();

        matches(figure,
                Triangle.class,  (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Tripiped.class,  (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                }
        );

        /* 3 - 2 */
        figure = new Tripiped();

        matches(figure,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Triangle.class, (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); }
        );

        /* 1 - 4 */
        figure = new Quartangle();

        matches(figure,
                Circle.class,     (Integer r) -> { out.println("Circle  square: " + ((int)(2 * Math.PI * r))); },
                Quartangle.class, (Byte a, Byte b, Byte c, Byte d) -> {
                    out.println("Quartangle square: " + (a * b * c * d));
                }
        );

        /* 4 - 1 */
        figure = new Circle();

        matches(figure,
                Quartangle.class, (Byte a, Byte b, Byte c, Byte d) -> {
                    out.println("Quartangle square: " + (a * b * c * d));
                },
                Circle.class,     (Integer r) -> { out.println("Circle  square: " + ((int)(2 * Math.PI * r))); }
        );

        /* 4 - 4 */
        figure = new Quarpiped();

        matches(figure,
                Quartangle.class, (Byte a, Byte b, Byte c, Byte d) -> {
                    out.println("Quartangle square: " + (a * b * c * d));
                },
                Quarpiped.class,  (Character a, Character b, Character c, Character d) -> {
                    out.println("Quarpiped  square: " + (a * b * c * d));
                }
        );

        /* 2 - 4 */
        figure = new Triangle();

        matches(figure,
                Triangle.class,   (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Quarpiped.class,  (Character a, Character b, Character c, Character d) -> {
                    out.println("Quarpiped  square: " + (a * b * c * d));
                }
        );

        /* 4 - 2 */
        figure = new Quarpiped();

        matches(figure,
                Quarpiped.class,  (Character a, Character b, Character c, Character d) -> {
                    out.println("Quarpiped  square: " + (a * b * c * d));
                },
                Triangle.class,   (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); }
        );

        /* 3 - 4 */
        figure = new Quarpiped();

        matches(figure,
                Tripiped.class,  (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Quarpiped.class, (Character a, Character b, Character c, Character d) -> {
                    out.println("Quarpiped  square: " + (a * b * c * d));
                }
        );

        /* 4 - 3 */
        figure = new Tripiped();

        matches(figure,
                Quarpiped.class, (Character a, Character b, Character c, Character d) -> {
                    out.println("Quarpiped  square: " + (a * b * c * d));
                },
                Tripiped.class,  (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                }
        );

        /* 1 - 1 - 1 */
        figure = new Unpiped();

        matches(figure,
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Unpiped.class,  (Double u)  -> { out.println("Unpiped  square: " + (u * u)); }
        );

        /* 1 - 1 - 2 */
        figure = new Triangle();

        matches(figure,
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Triangle.class, (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); }
        );

        /* 1 - 2 - 2 */
        figure = new Rectangle();

        matches(figure,
                Circle.class,    (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Rectangle.class, (Integer w, Integer h) -> { out.println("Rectangle square: " + (w * h)); },
                Triangle.class,  (Double  w, Double  h) -> { out.println("Triangle  square: " + (w * h)); }
        );

        /* 2 - 2 - 2 */
        figure = new Bipiped();

        matches(figure,
                Bipiped.class,   (Short   w, Short   h) -> { out.println("Bipiped  square:  " + (w * h)); },
                Rectangle.class, (Integer w, Integer h) -> { out.println("Rectangle square: " + (w * h)); },
                Triangle.class,  (Double  w, Double  h) -> { out.println("Triangle  square: " + (w * h)); }
        );

        /* 2 - 1 - 1 */
        figure = new Triangle();

        matches(figure,
                Triangle.class, (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); }
        );

        /* 2 - 2 - 1 */
        figure = new Bipiped();

        matches(figure,
                Triangle.class, (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Bipiped.class,  (Short   w, Short   h) -> { out.println("Bipiped  square:  " + (w * h)); },
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); }
        );

        /* 2 - 1 - 2 */
        figure = new Bipiped();

        matches(figure,
                Triangle.class, (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Bipiped.class,  (Short   w, Short   h) -> { out.println("Bipiped  square:  " + (w * h)); }
        );

        /* 1 - 2 - 1 */
        figure = new Triangle();

        matches(figure,
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Triangle.class, (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); }
        );

        /* 1 - 1 - 3 */
        figure = new Tripiped();

        matches(figure,
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                }
        );

        /* 1 - 3 - 3 */
        figure = new Parallelepiped();

        matches(figure,
                Quadrate.class,  (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Tripiped.class,  (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                }
        );

        /* 3 - 3 - 3 */
        figure = new Parallelepiped();

        matches(figure,
                Tripiped.class,  (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Tripiped.class,  (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                }
        );

        /* 3 - 1 - 1 */
        figure = new Circle();

        matches(figure,
                Tripiped.class,  (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); }
        );

        /* 3 - 3 - 1 */
        figure = new Tripiped();

        matches(figure,
                Tripiped.class,  (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); }
        );

        /* 3 - 1 - 3 */
        figure = new Parallelepiped();

        matches(figure,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                }
        );

        /* 1 - 3 - 1 */
        figure = new Quadrate();

        matches(figure,
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); }
        );

        /* 2 - 2 - 3 */
        figure = new Bipiped();

        matches(figure,
                Triangle.class, (Double w,  Double h) -> { out.println("Triangle  square: " + (w * h)); },
                Bipiped.class,  (Short  w,  Short  h) -> { out.println("Bipiped   square: " + (w * h)); },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                }
        );

        /* 2 - 2 - 3 */
        figure = new Parallelepiped();

        matches(figure,
                Triangle.class, (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                }
        );

        /* 3 - 2 - 2 */
        figure = new Triangle();

        matches(figure,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Triangle.class, (Double w,  Double h) -> { out.println("Triangle  square: " + (w * h)); },
                Bipiped.class,  (Short  w,  Short  h) -> { out.println("Bipiped   square: " + (w * h)); }
        );

        /* 3 - 2 - 2 */
        figure = new Tripiped();

        matches(figure,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Triangle.class, (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); }
        );

        /* 3 - 2 - 3 */
        figure = new Triangle();

        matches(figure,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Triangle.class, (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                }
        );

        /* 2 - 3 - 2 */
        figure = new Bipiped();

        matches(figure,
                Triangle.class, (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Bipiped.class,  (Short  w,  Short  h) -> { out.println("Bipiped   square: " + (w * h)); }
        );

        /* 1 - 2 - 3 */
        figure = new Parallelepiped();

        matches(figure,
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Bipiped.class,  (Short  w,  Short  h) -> { out.println("Bipiped   square: " + (w * h)); },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                }
        );

        /* 3 - 2 - 1 */
        figure = new Bipiped();

        matches(figure,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Bipiped.class,  (Short  w,  Short  h) -> { out.println("Bipiped   square: " + (w * h)); },
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); }
        );

        /* 2 - 1 - 3 */
        figure = new Circle();

        matches(figure,
                Bipiped.class,  (Short  w,  Short  h) -> { out.println("Bipiped   square: " + (w * h)); },
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                }
        );

        /* 3 - 1 - 2 */
        figure = new Bipiped();

        matches(figure,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Bipiped.class,  (Short  w,  Short  h) -> { out.println("Bipiped   square: " + (w * h)); }
        );
    }

    @Disabled
    @Test
    public void matchesStatementWithDefaultTest() throws PatternException {
        String data = "unknown";

        /* 1 */
        matches(data,
                Circle.class,  (Integer r) -> { out.println("Circle square: " + ((int)(2 * Math.PI * r))); },
                Default.class, () -> { System.out.println("Default value 1 type"); }
        );

        /* 2 */
        matches(data,
                Rectangle.class, (Integer w, Integer h) -> { out.println("Rectangle square: " + (w * h)); },
                Default.class,   () -> { System.out.println("Default value 2 type"); }
        );

        /* 3 */
        matches(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Default.class,   () -> { System.out.println("Default value 3 type"); }
        );

        /* 4 */
        matches(data,
                Quartangle.class, (Byte a, Byte b, Byte c, Byte d) -> {
                    out.println("Quartangle square: " + (a * b * c * d));
                },
                Default.class,   () -> { System.out.println("Default value 4 type"); }
        );

        /* 1 - 1 */
        matches(data,
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Default.class,  () -> { System.out.println("Default value 1-1 type"); }
        );

        /* 1 - 2 */
        matches(data,
                Circle.class,    (Integer r)            -> { out.println("Circle    square: " + ((int)(2 * Math.PI * r))); },
                Rectangle.class, (Integer w, Integer h) -> { out.println("Rectangle square: " + (w * h)); },
                Default.class,  () -> { System.out.println("Default value 1-2 type"); }
        );

        /* 2 - 1 */
        matches(data,
                Triangle.class, (Double w, Double h) -> { out.println("Triangle square: " + (w * h)); },
                Quadrate.class, (Integer a)          -> { out.println("Quadrate square: " + (a * a)); },
                Default.class,  () -> { System.out.println("Default value 2-1 type"); }
        );

        /* 2 - 2 */
        matches(data,
                Triangle.class,  (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Rectangle.class, (Integer w, Integer h) -> { out.println("Rectangle square: " + (w * h)); },
                Default.class,  () -> { System.out.println("Default value 2-2 type"); }
        );

        /* 1 - 3 */
        matches(data,
                Circle.class,         (Integer r) -> { out.println("Circle    square: " + ((int)(2 * Math.PI * r))); },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Default.class,  () -> { System.out.println("Default value 1-3 type"); }
        );

        /* 3 - 1 */
        matches(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Circle.class,         (Integer r) -> { out.println("Circle    square: " + ((int)(2 * Math.PI * r))); },
                Default.class,  () -> { System.out.println("Default value 3-1 type"); }
        );

        /* 3 - 3 */
        matches(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Tripiped.class,       (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Default.class,  () -> { System.out.println("Default value 3-3 type"); }
        );

        /* 2 - 3 */
        matches(data,
                Triangle.class,  (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Tripiped.class,  (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Default.class,  () -> { System.out.println("Default value 2-3 type"); }
        );

        /* 3 - 2 */
        matches(data,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Triangle.class, (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Default.class,  () -> { System.out.println("Default value 3-2 type"); }
        );

        /* 1 - 4 */
        matches(data,
                Circle.class,     (Integer r) -> { out.println("Circle  square: " + ((int)(2 * Math.PI * r))); },
                Quartangle.class, (Byte a, Byte b, Byte c, Byte d) -> {
                    out.println("Quartangle square: " + (a * b * c * d));
                },
                Default.class,  () -> { System.out.println("Default value 1-4 type"); }

        );

        /* 4 - 1 */
        matches(data,
                Quartangle.class, (Byte a, Byte b, Byte c, Byte d) -> {
                    out.println("Quartangle square: " + (a * b * c * d));
                },
                Circle.class,     (Integer r) -> { out.println("Circle  square: " + ((int)(2 * Math.PI * r))); },
                Default.class,  () -> { System.out.println("Default value 4-1 type"); }
        );

        /* 4 - 4 */
        matches(data,
                Quartangle.class, (Byte a, Byte b, Byte c, Byte d) -> {
                    out.println("Quartangle square: " + (a * b * c * d));
                },
                Quarpiped.class,  (Character a, Character b, Character c, Character d) -> {
                    out.println("Quarpiped  square: " + (a * b * c * d));
                },
                Default.class,  () -> { System.out.println("Default value 4-4 type"); }
        );

        /* 2 - 4 */
        matches(data,
                Triangle.class,   (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Quarpiped.class,  (Character a, Character b, Character c, Character d) -> {
                    out.println("Quarpiped  square: " + (a * b * c * d));
                },
                Default.class,  () -> { System.out.println("Default value 2-4 type"); }
        );

        /* 4 - 2 */
        matches(data,
                Quarpiped.class,  (Character a, Character b, Character c, Character d) -> {
                    out.println("Quarpiped  square: " + (a * b * c * d));
                },
                Triangle.class,   (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Default.class,  () -> { System.out.println("Default value 4-2 type"); }
        );

        /* 3 - 4 */
        matches(data,
                Tripiped.class,  (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Quarpiped.class, (Character a, Character b, Character c, Character d) -> {
                    out.println("Quarpiped  square: " + (a * b * c * d));
                },
                Default.class,  () -> { System.out.println("Default value 3-4 type"); }
        );

        /* 4 - 3 */
        matches(data,
                Quarpiped.class, (Character a, Character b, Character c, Character d) -> {
                    out.println("Quarpiped  square: " + (a * b * c * d));
                },
                Tripiped.class,  (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Default.class,  () -> { System.out.println("Default value 4-3 type"); }
        );

        /* 1 - 1 - 1 */
        matches(data,
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Unpiped.class,  (Double u)  -> { out.println("Unpiped  square: " + (u * u)); },
                Default.class,  () -> { System.out.println("Default value 1-1-1 type"); }
        );

        /* 1 - 1 - 2 */
        matches(data,
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Triangle.class, (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Default.class,  () -> { System.out.println("Default value 1-1-2 type"); }
        );

        /* 1 - 2 - 2 */
        matches(data,
                Circle.class,    (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Rectangle.class, (Integer w, Integer h) -> { out.println("Rectangle square: " + (w * h)); },
                Triangle.class,  (Double  w, Double  h) -> { out.println("Triangle  square: " + (w * h)); },
                Default.class,   () -> { System.out.println("Default value 1-2-2 type"); }
        );

        /* 2 - 2 - 2 */
        matches(data,
                Bipiped.class,   (Short   w, Short   h) -> { out.println("Bipiped  square:  " + (w * h)); },
                Rectangle.class, (Integer w, Integer h) -> { out.println("Rectangle square: " + (w * h)); },
                Triangle.class,  (Double  w, Double  h) -> { out.println("Triangle  square: " + (w * h)); },
                Default.class,   () -> { System.out.println("Default value 2-2-2 type"); }
        );

        /* 2 - 1 - 1 */
        matches(data,
                Triangle.class, (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Default.class,   () -> { System.out.println("Default value 2-1-1 type"); }
        );

        /* 2 - 2 - 1 */
        matches(data,
                Triangle.class, (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Bipiped.class,  (Short   w, Short   h) -> { out.println("Bipiped  square:  " + (w * h)); },
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Default.class,   () -> { System.out.println("Default value 2-2-1 type"); }
        );

        /* 2 - 1 - 2 */
        matches(data,
                Triangle.class, (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Bipiped.class,  (Short   w, Short   h) -> { out.println("Bipiped  square:  " + (w * h)); },
                Default.class,   () -> { System.out.println("Default value 2-1-2 type"); }
        );

        /* 1 - 2 - 1 */
        matches(data,
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Triangle.class, (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Default.class,   () -> { System.out.println("Default value 1-2-1 type"); }
        );

        /* 1 - 1 - 3 */
        matches(data,
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Default.class,   () -> { System.out.println("Default value 1-1-3 type"); }
        );

        /* 1 - 3 - 3 */
        matches(data,
                Quadrate.class,  (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Tripiped.class,  (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Default.class,   () -> { System.out.println("Default value 1-3-3 type"); }
        );

        /* 3 - 3 - 3 */
        matches(data,
                Tripiped.class,  (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Tripiped.class,  (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Default.class,   () -> { System.out.println("Default value 3-3-3 type"); }
        );

        /* 3 - 1 - 1 */
        matches(data,
                Tripiped.class,  (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Default.class,   () -> { System.out.println("Default value 3-1-1 type"); }
        );

        /* 3 - 3 - 1 */
        matches(data,
                Tripiped.class,  (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Default.class,   () -> { System.out.println("Default value 3-3-1 type"); }
        );

        /* 3 - 1 - 3 */
        matches(data,
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Default.class,   () -> { System.out.println("Default value 3-1-3 type"); }
        );

        /* 1 - 3 - 1 */
        matches(data,
                Quadrate.class, (Integer a) -> { out.println("Quadrate square: " + (a * a)); },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Default.class,   () -> { System.out.println("Default value 1-3-1 type"); }
        );

        /* 2 - 2 - 3 */
        matches(data,
                Triangle.class, (Double w,  Double h) -> { out.println("Triangle  square: " + (w * h)); },
                Bipiped.class,  (Short  w,  Short  h) -> { out.println("Bipiped   square: " + (w * h)); },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Default.class,   () -> { System.out.println("Default value 2-2-3 type"); }
        );

        /* 2 - 3 - 3 */
        matches(data,
                Triangle.class, (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Default.class,   () -> { System.out.println("Default value 2-3-3 type"); }
        );

        /* 3 - 2 - 2 */
        matches(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Triangle.class, (Double w,  Double h) -> { out.println("Triangle  square: " + (w * h)); },
                Bipiped.class,  (Short  w,  Short  h) -> { out.println("Bipiped   square: " + (w * h)); },
                Default.class,   () -> { System.out.println("Default value 3-2-2 type"); }
        );

        /* 3 - 3 - 2 */
        matches(new Bipiped(),
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Triangle.class, (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Default.class,   () -> { System.out.println("Default value 3-3-2 type"); }
        );

        /* 3 - 2 - 3 */
        matches(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Triangle.class, (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Tripiped.class, (Float w, Float l, Float h) -> {
                    out.println("Tripiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Default.class,   () -> { System.out.println("Default value 3-2-3 type"); }
        );

        /* 2 - 3 - 2 */
        matches(data,
                Triangle.class, (Double w,  Double h)  -> { out.println("Triangle  square: " + (w * h)); },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Bipiped.class,  (Short  w,  Short  h) -> { out.println("Bipiped   square: " + (w * h)); },
                Default.class,   () -> { System.out.println("Default value 2-3-2 type"); }
        );

        /* 1 - 2 - 3 */
        matches(data,
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Bipiped.class,  (Short  w,  Short  h) -> { out.println("Bipiped   square: " + (w * h)); },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Default.class,   () -> { System.out.println("Default value 1-2-3 type"); }
        );

        /* 3 - 2 - 1 */
        matches(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Bipiped.class,  (Short  w,  Short  h) -> { out.println("Bipiped   square: " + (w * h)); },
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Default.class,   () -> { System.out.println("Default value 3-2-1 type"); }
        );

        /* 2 - 1 - 3 */
        matches(data,
                Bipiped.class,  (Short  w,  Short  h) -> { out.println("Bipiped   square: " + (w * h)); },
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Default.class,   () -> { System.out.println("Default value 2-1-3 type"); }
        );

        /* 3 - 1 - 2 */
        matches(data,
                Parallelepiped.class, (Short w, Short l, Short h) -> {
                    out.println("Parallelepiped square: " + 2 * (w * l + l * h + w * h ));
                },
                Circle.class,   (Integer r) -> { out.println("Circle   square: " + ((int)(2 * Math.PI * r))); },
                Bipiped.class,  (Short  w,  Short  h) -> { out.println("Bipiped   square: " + (w * h)); },
                Default.class,  () -> { System.out.println("Default value 3-1-2 type"); }
        );
    }

    @Test
    public void foreachLoopTest() throws PatternException {
        /* 1 */
        foreach(listCircles, (Integer r) -> {
            System.out.println("Circle square: " + (2 * Math.PI * r));
        });

        /* 2 */
        foreach(listRectangles, (Integer w, Integer h) -> {
            System.out.println("Rect square: " + (w * h));
        });

        /* 3 */
        foreach(listTripipeds, (Float f, Float s, Float t) -> {
            System.out.println("Tripiped square: " + (f * s * t));
        });

        /* 4 */
        foreach(listQuarpiped, (Character a, Character b, Character c, Character d) -> {
            System.out.println("Quarpiped square: " + (a * b * c * d));
        });

        /* 5 */
        foreach(map, (Integer k, String v) -> {
            System.out.println("map entry: " + k  + " - " + v);
        });
    }
}
