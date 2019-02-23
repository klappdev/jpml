package org.kl;

import org.junit.jupiter.api.Test;
import org.kl.error.PatternException;
import org.kl.shape.*;

import static java.lang.System.*;
import static org.kl.handle.DeconstructPattern.matches;

public class DeconstructPatternTest {

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
}
