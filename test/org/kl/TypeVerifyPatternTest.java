package org.kl;

import org.junit.jupiter.api.Test;
import org.kl.state.Default;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kl.handle.TypeVerifyPattern.matches;

public class TypeVerifyPatternTest {

    private abstract class Figure {
        public abstract int square();
    }

    private class Rectangle extends Figure {
        private int width  = 5;
        private int height = 10;

        @Override
        public int square() {
            return width * height;
        }
    }

    private class Circle extends Figure {
        private int radius = 5;

        @Override
        public int square() {
            return (int) (2 * Math.PI * radius);
        }
    }

    @Test
    public void matchesStatementTest() {
        /* 1 */
        byte value1 = 5;
        matches(value1,
                Byte.class,  b  -> { System.out.println(b * b); }
        );

        /* 1.1 */
        matches(value1,
                byte.class,  b -> { System.out.println("pc1: " + b * b * b); }
        );

        /* 2 */
        short value2 = 15;
        matches(value2,
                Byte.class,  b -> { System.out.println(b * b); },
                Short.class, s -> { System.out.println(s * s); }
        );

        /* 2.1 */
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

        /* 3.1 */
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

        /* 4.1 */
        matches(value4,
                byte.class,    b -> { System.out.println("pc4: " + b * b * b); },
                short.class,   s -> { System.out.println("pc4: " + s * s * s); },
                int.class,     i -> { System.out.println("pc4: " + i * i * i); },
                long.class,    l -> { System.out.println("pc4: " + l * l * l); }
        );

        /* 5 */
        float value5 = 45;
        matches(value5,
                Byte.class,    b -> { System.out.println(b * b); },
                Short.class,   s -> { System.out.println(s * s); },
                Integer.class, i -> { System.out.println(i * i); },
                Long.class,    l -> { System.out.println(l * l); },
                Float.class,   f -> { System.out.println(f * f); }
        );

        /* 5.1 */
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

        /* 6.1 */
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
                Rectangle.class,   r -> { System.out.println("rect:   " + r.square()); },
                Circle.class,      c -> { System.out.println("circle: " + c.square()); }
        );
    }

    @Test
    public void matchesStatementWithDefaultTest() {
        String data = "unknown";

        /* 1 */
        matches(data,
                Byte.class,    b  -> { System.out.println(b * b); },
                Default.class, () -> { System.out.println("Default value 1 type"); }
        );

        /* 2 */
        matches(data,
                Byte.class,  b -> { System.out.println(b * b); },
                Short.class, s -> { System.out.println(s * s); },
                Default.class, () -> { System.out.println("Default value 2 types"); }
        );

        /* 3 */
        matches(data,
                Byte.class,    b  -> { System.out.println(b * b); },
                Short.class,   s  -> { System.out.println(s * s); },
                Integer.class, i  -> { System.out.println(i * i); },
                Default.class, () -> { System.out.println("Default value 3 types"); }
        );

        /* 4 */
        matches(data,
                Byte.class,    b  -> { System.out.println(b * b); },
                Short.class,   s  -> { System.out.println(s * s); },
                Integer.class, i  -> { System.out.println(i * i); },
                Long.class,    l  -> { System.out.println(l * l); },
                Default.class, () -> { System.out.println("Default value 4 types"); }
        );

        /* 5 */
        matches(data,
                Byte.class,    b -> { System.out.println(b * b); },
                Short.class,   s -> { System.out.println(s * s); },
                Integer.class, i -> { System.out.println(i * i); },
                Long.class,    l -> { System.out.println(l * l); },
                Float.class,   f -> { System.out.println(f * f); },
                Default.class, () -> { System.out.println("Default value 5 types"); }
        );

        /* 6 */
        matches(data,
                Byte.class,    b -> { System.out.println(b * b); },
                Short.class,   s -> { System.out.println(s * s); },
                Integer.class, i -> { System.out.println(i * i); },
                Long.class,    l -> { System.out.println(l * l); },
                Float.class,   f -> { System.out.println(f * f); },
                Double.class,  d -> { System.out.println(d * d); },
                Default.class, () -> { System.out.println("Default value 6 types"); }
        );

        /* 7 */
        matches(data,
                byte.class,    b -> { System.out.println(b * b * b); },
                short.class,   s -> { System.out.println(s * s * s); },
                int.class,     i -> { System.out.println(i * i * i); },
                long.class,    l -> { System.out.println(l * l * l); },
                float.class,   f -> { System.out.println(f * f * f); },
                double.class,  d -> { System.out.println(d * d * d); },
                Default.class, () -> { System.out.println("Default value 7 types"); }
        );

        /* 8 */
        matches(data,
                Rectangle.class, r  -> { System.out.println("rect:   " + r.square()); },
                Circle.class,    c  -> { System.out.println("circle: " + c.square()); },
                Default.class,   () -> { System.out.println("Default shape"); }
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

        /* 2 */
        short value2 = 15;
        int result2 = matches(value2,
                              Byte.class,  b -> { int result = 2 * (b + b);  return result; },
                              Short.class, s -> { int result = 2 * (s + s);  return result; }
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

        /* 4 */
        long value4 = 35;
        int result4 = matches(value4,
                              Byte.class,    b -> { int result = 2 * (b + b);  return result; },
                              Short.class,   s -> { int result = 2 * (s + s);  return result; },
                              Integer.class, i -> { int result = 2 * (i + i);  return result; },
                              Long.class,    l -> { int result = (int) (2 * (l + l));  return result; }
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


        /* 7 */
        /* TODO: test this case */

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
                              Default.class, () -> { System.out.println("Default value 1 types"); return 0; }
                      );

        assertEquals(result1, 0);

        /* 2 */
        int result2 = matches(data,
                              Byte.class,    b  -> { int result = 2 * (b + b);  return result; },
                              Short.class,   s  -> { int result = 2 * (s + s);  return result; },
                              Default.class, () -> { System.out.println("Default value 2 types"); return 1; }
        );

        assertEquals(result2, 1);

        /* 3 */
        int result3 = matches(data,
                              Byte.class,    b  -> { int result = 2 * (b + b);  return result; },
                              Short.class,   s  -> { int result = 2 * (s + s);  return result; },
                              Integer.class, i  -> { int result = 2 * (i + i);  return result; },
                              Default.class, () -> { System.out.println("Default value 3 types"); return 0; }
        );

        assertEquals(result3, 0);

        /* 4 */
        int result4 = matches(data,
                              Byte.class,    b -> { int result = 2 * (b + b);  return result; },
                              Short.class,   s -> { int result = 2 * (s + s);  return result; },
                              Integer.class, i -> { int result = 2 * (i + i);  return result; },
                              Long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                              Default.class, () -> { System.out.println("Default value 4 types"); return 0; }
        );

        assertEquals(result4, 0);

        /* 5 */
        int result5 = matches(data,
                             Byte.class,    b -> { int result = 2 * (b + b);  return result; },
                             Short.class,   s -> { int result = 2 * (s + s);  return result; },
                             Integer.class, i -> { int result = 2 * (i + i);  return result; },
                             Long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                             Float.class,   f -> { int result = (int) (2 * (f + f));  return result;},
                             Default.class, () -> { System.out.println("Default value 5 types"); return 0; }
        );

        assertEquals(result5, 0);

        /* 6 */
        int result6 = matches(data,
                              Byte.class,    b -> { int result = 2 * (b + b);  return result; },
                              Short.class,   s -> { int result = 2 * (s + s);  return result; },
                              Integer.class, i -> { int result = 2 * (i + i);  return result; },
                              Long.class,    l -> { int result = (int) (2 * (l + l));  return result; },
                              Float.class,   f -> { int result = (int) (2 * (f + f));  return result; },
                              Double.class,  d -> { int result = (int) (2 * (d + d));  return result; },
                              Default.class, () -> { System.out.println("Default value 6 types"); return 0; }
        );

        assertEquals(result6, 0);

        /* 7 */
        /* TODO: test this case */

        /* 8 */
        int square = matches(data,
                             Rectangle.class, r -> { int result = r.square(); return result; },
                             Circle.class,    c -> { int result = c.square(); return result; },
                             Default.class,   () -> { System.out.println("Default shape"); return 5; }
        );

        assertEquals(square, 5);
    }
}
