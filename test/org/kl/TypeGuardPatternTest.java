package org.kl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.kl.shape.Circle;
import org.kl.shape.Figure;
import org.kl.shape.Rectangle;
import org.kl.state.Default;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kl.handle.TypeGuardPattern.matches;

public class TypeGuardPatternTest {

    @Disabled
    @Test
    public void matchesStatementTest() {
        /* 1 */
        byte value1 = 5;
        matches(value1,
                Byte.class, b -> b > - 1,
                            b -> { System.out.println(b * b); }
        );

        matches(value1,
                byte.class, b -> b > - 1,
                            b -> { System.out.println("pc1: " + b * b); }
        );

        /* 2 */
        short value2 = 10;
        matches(value2,
                Short.class, s1 -> s1 > 15,
                             s1 -> { System.out.println("br1: " + s1 * s1); },
                Short.class, s2 -> s2 >= 5,
                             s2 -> { System.out.println("br2: " + s2 * s2); }
        );

        matches(value2,
                short.class, s1 -> s1 > 15,
                             s1 -> { System.out.println("pc1: " + s1 * s1); },
                short.class, s2 -> s2 >= 5,
                             s2 -> { System.out.println("pc2: " + s2 * s2); }
        );

        matches(value2,
                Byte.class,  b1 -> b1 > 15,
                             b1 -> { System.out.println("br1: " + b1 * b1); },
                Short.class, s2 -> s2 >= 5,
                             s2 -> { System.out.println("br2: " + s2 * s2); }
        );

        /* 3 */
        int value3 = 15;
        matches(value3,
                Short.class,   s1 -> s1 > -1,
                               s1 -> { System.out.println("br1: " + s1 * s1); },
                Integer.class, i1 -> i1 > 20,
                               i1 -> { System.out.println("br2: " + i1 * i1); },
                Integer.class, i2 -> i2 >= 5,
                               i2 -> { System.out.println("br3: " + i2 * i2); }
        );

        matches(value3,
                short.class, s1 -> s1 > -1,
                             s1 -> { System.out.println("pc1: " + s1 * s1); },
                int.class,   i1 -> i1 > 10,
                             i1 -> { System.out.println("pc2: " + i1 * i1); },
                int.class,   i2 -> i2 >= 20,
                             i2 -> { System.out.println("pc3: " + i2 * i2); }
        );

        matches(value3,
                Byte.class,    b1 -> b1 > -1,
                               b1 -> { System.out.println("br1: " + b1 * b1); },
                Short.class,   s1 -> s1 > 20,
                               s1 -> { System.out.println("br2: " + s1 * s1); },
                Integer.class, i1 -> i1 >= 5,
                               i1 -> { System.out.println("br3: " + i1 * i1); }
        );

        /* 4 */
        long value4 = 25;
        matches(value4,
                Short.class,   s -> s > -1,
                               s -> { System.out.println("br1: " + s * s); },
                Integer.class, i -> i > 20,
                               i -> { System.out.println("br2: " + i * i); },
                Long.class,    l1 -> l1 != -1,
                               l1 -> { System.out.println("br3: " + l1 * l1); },
                Long.class,    l2 -> l2 >= 5,
                               l2 -> { System.out.println("br4: " + l2 * l2); }
        );

        matches(value4,
                short.class,   s -> s > -1,
                               s -> { System.out.println("pc1: " + s * s); },
                int.class,     i -> i > 20,
                               i -> { System.out.println("pc2: " + i * i); },
                long.class,    l1 -> l1 < 10,
                               l1 -> { System.out.println("pc3: " + l1 * l1); },
                long.class,    l2 -> l2 >= 5,
                               l2 -> { System.out.println("pc4: " + l2 * l2); }
        );

        matches(value4,
                Byte.class,    b -> b > -1,
                               b -> { System.out.println("br1: " + b * b); },
                Short.class,   s -> s > -1,
                               s -> { System.out.println("br2: " + s * s); },
                Integer.class, i -> i > -1,
                               i -> { System.out.println("br3: " + i * i); },
                Long.class,    l -> l != 0,
                               l -> { System.out.println("br4: " + l * l); }
        );

        /* 5 */
        float value5 = 35;
        matches(value5,
                Short.class,   s -> s > -1,
                               s -> { System.out.println("br1: " + s * s); },
                Integer.class, i -> i > 20,
                               i -> { System.out.println("br2: " + i * i); },
                Long.class,    l -> l != -1,
                               l -> { System.out.println("br3: " + l * l); },
                Float.class,   f1 -> f1 >= 5,
                               f1 -> { System.out.println("br4: " + f1 * f1); },
                Float.class,   f2 -> f2 < 50,
                               f2 -> { System.out.println("br5: " + f2 * f2); }
        );

        matches(value5,
                short.class,   s -> s > -1,
                               s -> { System.out.println("pc1: " + s * s); },
                int.class,     i -> i > 20,
                               i -> { System.out.println("pc2: " + i * i); },
                long.class,    l -> l > 10,
                               l -> { System.out.println("pc3: " + l * l); },
                float.class,   f1 -> f1 >= 40,
                               f1 -> { System.out.println("pc4: " + f1 * f1); },
                float.class,   f2 -> f2 >= 5,
                               f2 -> { System.out.println("pc5: " + f2 * f2); }
        );

        matches(value5,
                Byte.class,    b -> b > -1,
                               b -> { System.out.println("br1: " + b * b); },
                Short.class,   s -> s > -1,
                               s -> { System.out.println("br2: " + s * s); },
                Integer.class, i -> i > -1,
                               i -> { System.out.println("br3: " + i * i); },
                Long.class,    l -> l != -1,
                               l -> { System.out.println("br4: " + l * l); },
                Float.class,   f -> f != 0,
                               f -> { System.out.println("br5: " + f * f); }
        );

        /* 6 */
        double value6 = 40;
        matches(value6,
                Short.class,   s -> s > -1,
                               s -> { System.out.println("br1: " + s * s); },
                Integer.class, i -> i > 20,
                               i -> { System.out.println("br2: " + i * i); },
                Long.class,    l -> l != -1,
                               l -> { System.out.println("br3: " + l * l); },
                Float.class,   f -> f >= 5,
                               f -> { System.out.println("br4: " + f * f); },
                Double.class,  d1 -> d1 >= 5,
                               d1 -> { System.out.println("br5: " + d1 * d1); },
                Double.class,  d2 -> d2 < 50,
                               d2 -> { System.out.println("br6: " + d2 * d2); }
        );

        matches(value6,
                short.class,   s -> s > -1,
                               s -> { System.out.println("pc1: " + s * s); },
                int.class,     i -> i > 20,
                               i -> { System.out.println("pc2: " + i * i); },
                long.class,    l -> l > 10,
                               l -> { System.out.println("pc3: " + l * l); },
                float.class,   f -> f >= 5,
                               f -> { System.out.println("pc4: " + f * f); },
                double.class,  d1 -> d1 >= 40,
                               d1 -> { System.out.println("pc5: " + d1 * d1); },
                double.class,  d2 -> d2 >= 5,
                               d2 -> { System.out.println("pc6: " + d2 * d2); }
        );

        matches(value6,
                Byte.class,    b -> b > -1,
                               b -> { System.out.println("br1: " + b * b); },
                Short.class,   s -> s > -1,
                               s -> { System.out.println("br2: " + s * s); },
                Integer.class, i -> i > -1,
                               i -> { System.out.println("br3: " + i * i); },
                Long.class,    l -> l != -1,
                               l -> { System.out.println("br4: " + l * l); },
                Float.class,   f -> f != -1,
                               f -> { System.out.println("br5: " + f * f); },
                Double.class,  d -> d != 0,
                               d -> { System.out.println("br6: " + d * d); }
        );

        /* 7 */
        Figure figure = new Rectangle();

        matches(figure,
                Rectangle.class, r -> r != null,
                                 r -> { System.out.println("rect:   " + r.square()); },
                Circle.class,    c -> c != null,
                                 c -> { System.out.println("circle: " + c.square()); }
        );
    }

    @Disabled
    @Test
    public void matchesStatementWithDefaultTest() {
        String data = "unknown";

        /* 1 */
        matches(data,
                Byte.class, b -> b > - 1,
                            b -> { System.out.println("br1: " + b * b); },
                Default.class, () -> { System.out.println("Default value 1 type"); }
        );

        matches(data,
                byte.class, b -> b > - 1,
                            b -> { System.out.println("pc1: " + b * b); },
                Default.class, () -> { System.out.println("Default value 1 type"); }
        );

        /* 2 */
        matches(data,
                Short.class, s1 -> s1 > 15,
                             s1 -> { System.out.println("br1: " + s1 * s1); },
                Short.class, s2 -> s2 >= 5,
                             s2 -> { System.out.println("br2: " + s2 * s2); },
                Default.class, () -> { System.out.println("Default value 2 type"); }
        );

        matches(data,
                short.class, s1 -> s1 > 15,
                             s1 -> { System.out.println("pc1: " + s1 * s1); },
                short.class, s2 -> s2 >= 5,
                             s2 -> { System.out.println("pc2: " + s2 * s2); },
                Default.class, () -> { System.out.println("Default value 2 type"); }
        );

        /* 3 */
        matches(data,
                Short.class,   s1 -> s1 > -1,
                               s1 -> { System.out.println("br1: " + s1 * s1); },
                Integer.class, i1 -> i1 > 20,
                               i1 -> { System.out.println("br2: " + i1 * i1); },
                Integer.class, i2 -> i2 >= 5,
                               i2 -> { System.out.println("br3: " + i2 * i2); },
                Default.class, () -> { System.out.println("Default value 3 type"); }
        );

        matches(data,
                short.class, s1 -> s1 > -1,
                             s1 -> { System.out.println("pc1: " + s1 * s1); },
                int.class,   i1 -> i1 > 10,
                             i1 -> { System.out.println("pc2: " + i1 * i1); },
                int.class,   i2 -> i2 >= 20,
                             i2 -> { System.out.println("pc3: " + i2 * i2); },
                Default.class, () -> { System.out.println("Default value 3 type"); }
        );

        /* 4 */
        matches(data,
                Short.class,   s -> s > -1,
                               s -> { System.out.println("br1: " + s * s); },
                Integer.class, i -> i > 20,
                               i -> { System.out.println("br2: " + i * i); },
                Long.class,    l1 -> l1 != -1,
                               l1 -> { System.out.println("br3: " + l1 * l1); },
                Long.class,    l2 -> l2 >= 5,
                               l2 -> { System.out.println("br4: " + l2 * l2); },
                Default.class, () -> { System.out.println("Default value 4 type"); }
        );

        matches(data,
                short.class,   s -> s > -1,
                               s -> { System.out.println("pc1: " + s * s); },
                int.class,     i -> i > 20,
                               i -> { System.out.println("pc2: " + i * i); },
                long.class,    l1 -> l1 < 10,
                               l1 -> { System.out.println("pc3: " + l1 * l1); },
                long.class,    l2 -> l2 >= 5,
                               l2 -> { System.out.println("pc4: " + l2 * l2); },
                Default.class, () -> { System.out.println("Default value 4 type"); }
        );

        /* 5 */
        matches(data,
                Short.class,   s -> s > -1,
                               s -> { System.out.println("br1: " + s * s); },
                Integer.class, i -> i > 20,
                               i -> { System.out.println("br2: " + i * i); },
                Long.class,    l -> l != -1,
                               l -> { System.out.println("br3: " + l * l); },
                Float.class,   f1 -> f1 >= 5,
                               f1 -> { System.out.println("br4: " + f1 * f1); },
                Float.class,   f2 -> f2 < 50,
                               f2 -> { System.out.println("br5: " + f2 * f2); },
                Default.class, () -> { System.out.println("Default value 5 type"); }
        );

        matches(data,
                short.class,   s -> s > -1,
                               s -> { System.out.println("pc1: " + s * s); },
                int.class,     i -> i > 20,
                               i -> { System.out.println("pc2: " + i * i); },
                long.class,    l -> l > 10,
                               l -> { System.out.println("pc3: " + l * l); },
                float.class,   f1 -> f1 >= 40,
                               f1 -> { System.out.println("pc4: " + f1 * f1); },
                float.class,   f2 -> f2 >= 5,
                               f2 -> { System.out.println("pc5: " + f2 * f2); },
                Default.class, () -> { System.out.println("Default value 5 type"); }
        );

        /* 6 */
        matches(data,
                Short.class,   s -> s > -1,
                               s -> { System.out.println("br1: " + s * s); },
                Integer.class, i -> i > 20,
                               i -> { System.out.println("br2: " + i * i); },
                Long.class,    l -> l != -1,
                               l -> { System.out.println("br3: " + l * l); },
                Float.class,   f -> f >= 5,
                               f -> { System.out.println("br4: " + f * f); },
                Double.class,  d1 -> d1 >= 5,
                               d1 -> { System.out.println("br5: " + d1 * d1); },
                Double.class,  d2 -> d2 < 50,
                               d2 -> { System.out.println("br6: " + d2 * d2); },
                Default.class, () -> { System.out.println("Default value 6 type"); }
        );

        matches(data,
                short.class,   s -> s > -1,
                               s -> { System.out.println("pc1: " + s * s); },
                int.class,     i -> i > 20,
                               i -> { System.out.println("pc2: " + i * i); },
                long.class,    l -> l > 10,
                               l -> { System.out.println("pc3: " + l * l); },
                float.class,   f -> f >= 5,
                               f -> { System.out.println("pc4: " + f * f); },
                double.class,  d1 -> d1 >= 40,
                               d1 -> { System.out.println("pc5: " + d1 * d1); },
                double.class,  d2 -> d2 >= 5,
                               d2 -> { System.out.println("pc6: " + d2 * d2); },
                Default.class, () -> { System.out.println("Default value 6 type"); }
        );

        /* 7 */
        matches(data,
                Rectangle.class, Objects::nonNull,
                                 r -> { System.out.println("rect:   " + r.square()); },
                Circle.class,    Objects::nonNull,
                                 c -> { System.out.println("circle: " + c.square()); },
                Default.class,   () -> { System.out.println("Default figure"); }
        );
    }

    @Disabled
    @Test
    public void matchesExpressionTest() {
        /* 1 */
        byte value1 = 5;
        int result1 = matches(value1,
                Byte.class,  b -> b >= 1,
                             b -> { int result = 2 * (b + b); return result; }
        );

        assertEquals(result1, 2 * (value1 + value1));


        result1 = matches(value1,
               byte.class, b -> b != 0,
                           b -> { int result = 2 * (b + b); return result; }
        );

        assertEquals(result1, 2 * (value1 + value1));

        /* 2 */
        short value2 = 10;
        int result2 = matches(value2,
                Short.class,  s1 -> s1 >= 20,
                              s1 -> { int result = 2 * (s1 + s1);  return result; },
                Short.class,  s2 -> s2 >=  10,
                              s2 -> { int result = 3 * (s2 + s2);  return result; }
        );

        assertEquals(result2, 3 * (value2 + value2));

        result2 = matches(value2,
                short.class, s1 -> s1 != -1,
                             s1 -> { int result = 2 * (s1 + s1);  return result; },
                short.class, s2 -> s2 != 0,
                             s2 -> { int result = 2 * (s2 + s2);  return result; }
        );

        assertEquals(result2, 2 * (value2 + value2));

        result2 = matches(value2,
                Byte.class,  b -> b != -1,
                             b -> { int result = 2 * (b + b);  return result; },
                Short.class, s -> s != 0,
                             s -> { int result = 3 * (s + s);  return result; }
        );

        assertEquals(result2, 3 * (value2 + value2));

        /* 3 */
        int value3 = 15;
        int result3 = matches(value3,
                Byte.class,    b -> b != -1,
                               b -> { int result = 2 * (b + b);  return result; },
                Integer.class, i1 -> i1 > 20,
                               i1 -> { int result = 2 * (i1 + i1);  return result; },
                Integer.class, i2 -> i2 > 10,
                               i2 -> { int result = 3 * (i2 + i2);  return result; }
        );

        assertEquals(result3, 3 * (value3 + value3));

        result3 = matches(value3,
                byte.class,    b -> b != -1,
                               b -> { int result = 2 * (b + b);  return result; },
                int.class,     i1 -> i1 > 20,
                               i1 -> { int result = 2 * (i1 + i1);  return result; },
                int.class,     i2 -> i2 > 10,
                               i2 -> { int result = 2 * (i2 + i2);  return result; }
        );

        assertEquals(result3, 2 * (value3 + value3));

        result3 = matches(value3,
                Byte.class,    b -> b != -1,
                               b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s != 0,
                               s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> i > 10,
                               i -> { int result = 3 * (i + i);  return result; }
        );

        assertEquals(result3, 3 * (value3 + value3));

        /* 4 */
        long value4 = 20;
        int result4 = matches(value4,
                Byte.class,    b -> b != -1,
                               b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s > 5,
                               s -> { int result = 2 * (s + s);  return result; },
                Long.class,    l1 -> l1 > 25,
                               l1 -> { int result = (int) (2 * (l1 + l1));  return result; },
                Long.class,    l2 -> l2 > 15,
                               l2 -> { int result = (int) (2 * (l2 + l2));  return result; }
        );

        assertEquals(result4, 2 * (value4 + value4));

        result4 = matches(value4,
                byte.class,    b -> b != -1,
                               b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> s > 5,
                               s -> { int result = 2 * (s + s);  return result; },
                long.class,    l1 -> l1 > 25,
                               l1 -> { int result = (int) (2 * (l1 + l1));  return result; },
                long.class,    l2 -> l2 > 15,
                               l2 -> { int result = (int) (2 * (l2 + l2));  return result; }
        );

        assertEquals(result4, 2 * (value4 + value4));

        result4 = matches(value4,
                Byte.class,    b -> b < 255,
                               b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s != 0,
                               s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> i > 1,
                               i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> l > 15,
                               l -> { int result = (int) (2 * (l + l));  return result; }
        );

        assertEquals(result4, 2 * (value4 + value4));

        /* 5 */
        float value5 = 25;
        int result5 = matches(value5,
                Byte.class,    b -> b != -1,
                               b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s > 5,
                               s -> { int result = 2 * (s + s);  return result; },
                Long.class,    l -> l != 10,
                               l -> { int result = (int) (2 * (l + l));  return result; },
                Float.class,   f1 -> f1 > 25,
                               f1 -> { int result = (int) (3 * (f1 + f1));  return result; },
                Float.class,   f2 -> f2 > 15,
                               f2 -> { int result = (int) (4 * (f2 + f2));  return result; }
        );

        assertEquals(result5, 4 * (value5 + value5));

        result5 = matches(value5,
                byte.class,    b -> b != -1,
                               b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> s > 5,
                               s -> { int result = 2 * (s + s);  return result; },
                long.class,    l -> l != 10,
                               l -> { int result = (int) (2 * (l + l));  return result; },
                float.class,   f1 -> f1 > 25,
                               f1 -> { int result = (int) (3 * (f1 + f1));  return result; },
                float.class,   f2 -> f2 > 15,
                               f2 -> { int result = (int) (4 * (f2 + f2));  return result; }
        );

        assertEquals(result5, 4 * (value5 + value5));

        result5 = matches(value5,
                Byte.class,    b -> b < 255,
                               b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s != 0,
                               s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> i > 1,
                               i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> l > 15,
                               l -> { int result = (int) (3 * (l + l));  return result; },
                Float.class,   f -> f >= 25,
                               f -> { int result = (int) (4 * (f + f));  return result; }
        );

        assertEquals(result5, 4 * (value5 + value5));

        /* 6 */
        String value6 = "test";
        String result6 = matches(value6,
                Byte.class,    b -> b != -1,
                               b -> { int result = 2 * (b + b);  return String.valueOf(result); },
                Short.class,   s -> s > 5,
                               s -> { int result = 2 * (s + s);  return String.valueOf(result); },
                Long.class,    l -> l != 10,
                               l -> { int result = (int) (2 * (l + l));  return String.valueOf(result); },
                Float.class,   f -> f >= 25,
                               f -> { int result = (int) (4 * (f + f));  return String.valueOf(result); },
                String.class,  s1 -> !s1.isEmpty(),
                               s1 -> s1,
                String.class,  s2 -> s2.equals("test"),
                               s2 -> s2 + s2
        );

        assertEquals(result6, value6);

        result6 = matches(value6,
                byte.class,    b -> b != -1,
                               b -> { int result = 2 * (b + b);  return String.valueOf(result); },
                short.class,   s -> s > 5,
                               s -> { int result = 2 * (s + s);  return String.valueOf(result); },
                long.class,    l -> l != 10,
                               l -> { int result = (int) (2 * (l + l));  return String.valueOf(result); },
                float.class,   f -> f >= 25,
                               f -> { int result = (int) (4 * (f + f));  return String.valueOf(result); },
                String.class,  s1 -> s1 == null,
                               s1 -> s1,
                String.class,  s2 -> s2.equals("test"),
                               s2 -> s2 + s2
        );

        assertEquals(result6, value6 + value6);

        /* 7 */
        Figure figure = new Rectangle();

        int square = matches(figure,
                Rectangle.class, r -> r instanceof Figure,
                                 r -> { int result = r.square(); return result; },
                Circle.class,    c -> c instanceof Figure,
                                 c -> { int result = c.square(); return result; }
        );

        assertEquals(square, 50);
    }

    @Test
    public void matchesExpressionWithDefaultTest() {
        int data = 0;

        /* 1 */
        int result1 = matches(data,
                Byte.class,  b -> b >= 1,
                             b -> { int result = 2 * (b + b); return result; },
                Default.class, () -> { System.out.println("Default value 1 types"); return 0; }
        );

        assertEquals(result1, 0);


        byte value1 = 5;
        result1 = matches(value1,
                byte.class, b -> b >= 3,
                            b -> { int result = 2 * (b + b); return result; },
                Default.class, () -> { System.out.println("Default value 1 types"); return 0; }
        );

        assertEquals(result1, 20);

        /* 2 */
        int result2 = matches(data,
                Short.class,  s1 -> s1 < 20,
                              s1 -> { int result = 2 * (s1 + s1);  return result; },
                Short.class,  s2 -> s2 >= 10,
                              s2 -> { int result = 3 * (s2 + s2);  return result; },
                Default.class, () -> { System.out.println("Default value 2 types"); return 1; }
        );

        assertEquals(result2, 1);

        short value2 = 10;
        result2 = matches(value2,
                short.class, s1 -> s1 > 30,
                             s1 -> { int result = 3 * (s1 + s1);  return result; },
                short.class, s2 -> s2 != 5,
                             s2 -> { int result = 2 * (s2 + s2);  return result; },
                Default.class, () -> { System.out.println("Default value 2 types"); return 0; }
        );

        assertEquals(result2, 40);

        /* 3 */
        int result3 = matches(data,
                Byte.class,    b -> b != -1,
                               b -> { int result = 2 * (b + b);  return result; },
                Integer.class, i1 -> i1 > 20,
                               i1 -> { int result = 2 * (i1 + i1);  return result; },
                Integer.class, i2 -> i2 > 10,
                               i2 -> { int result = 3 * (i2 + i2);  return result; },
                Default.class, () -> { System.out.println("Default value 3 types"); return 2; }
        );

        assertEquals(result3, 2);

        int value3 = 15;
        result3 = matches(value3,
                byte.class,    b -> b != -1,
                               b -> { int result = 2 * (b + b);  return result; },
                int.class,     i1 -> i1 > 10,
                               i1 -> { int result = 2 * (i1 + i1);  return result; },
                int.class,     i2 -> i2 > 20,
                               i2 -> { int result = 3 * (i2 + i2);  return result; },
                Default.class, () -> { System.out.println("Default value 3 types"); return 0; }
        );

        assertEquals(result3, 60);

        /* 4 */
        int result4 = matches(data,
                Byte.class,    b -> b != -1,
                               b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s > 5,
                               s -> { int result = 2 * (s + s);  return result; },
                Long.class,    l1 -> l1 > 25,
                               l1 -> { int result = (int) (3 * (l1 + l1));  return result; },
                Long.class,    l2 -> l2 > 15,
                               l2 -> { int result = (int) (4 * (l2 + l2));  return result; },
                Default.class, () -> { System.out.println("Default value 4 types"); return 4; }
        );

        assertEquals(result4, 4);

        long value4 = 20;
        result4 = matches(value4,
                byte.class,    b -> b != -1,
                               b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> s > 5,
                               s -> { int result = 2 * (s + s);  return result; },
                long.class,    l1 -> l1 > 25,
                               l1 -> { int result = (int) (3 * (l1 + l1));  return result; },
                long.class,    l2 -> l2 > 15,
                               l2 -> { int result = (int) (4 * (l2 + l2));  return result; },
                Default.class, () -> { System.out.println("Default value 4 types"); return 0; }
        );

        assertEquals(result4, 160);

        /* 5 */
        int result5 = matches(data,
                Byte.class,    b -> b != -1,
                               b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s > 5,
                               s -> { int result = 2 * (s + s);  return result; },
                Long.class,    l -> l != 10,
                               l -> { int result = (int) (2 * (l + l));  return result; },
                Float.class,   f1 -> f1 > 25,
                               f1 -> { int result = (int) (3 * (f1 + f1));  return result; },
                Float.class,   f2 -> f2 > 15,
                               f2 -> { int result = (int) (4 * (f2 + f2));  return result; },
                Default.class, () -> { System.out.println("Default value 5 types"); return 5; }
        );

        assertEquals(result5, 5);

        float value5 = 25;
        result5 = matches(value5,
                byte.class,    b -> b != -1,
                               b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> s > 5,
                               s -> { int result = 2 * (s + s);  return result; },
                long.class,    l -> l != 10,
                               l -> { int result = (int) (2 * (l + l));  return result; },
                float.class,   f1 -> f1 > 25,
                               f1 -> { int result = (int) (3 * (f1 + f1));  return result; },
                float.class,   f2 -> f2 > 15,
                               f2 -> { int result = (int) (4 * (f2 + f2));  return result; },
                Default.class, () -> { System.out.println("Default value 5 types"); return 0; }
        );

        assertEquals(result5, 4 * (value5 + value5));

        /* 6 */
        String result6 = matches(data,
                Byte.class,    b -> b != -1,
                               b -> { int result = 2 * (b + b);  return String.valueOf(result); },
                Short.class,   s -> s > 5,
                               s -> { int result = 2 * (s + s);  return String.valueOf(result); },
                Long.class,    l -> l != 10,
                               l -> { int result = (int) (2 * (l + l));  return String.valueOf(result); },
                Float.class,   f -> f >= 25,
                               f -> { int result = (int) (4 * (f + f));  return String.valueOf(result); },
                String.class,  s1 -> !s1.isEmpty(),
                               s1 -> s1,
                String.class,  s2 -> s2.equals("test"),
                               s2 -> s2 + s2,
                Default.class, () -> { System.out.println("Default value 6 types"); return "6"; }
        );

        assertEquals(result6, "6");

        String value6 = "test";
        result6 = matches(value6,
                byte.class,    b -> b != -1,
                               b -> { int result = 2 * (b + b);  return String.valueOf(result); },
                short.class,   s -> s > 5,
                               s -> { int result = 2 * (s + s);  return String.valueOf(result); },
                long.class,    l -> l != 10,
                               l -> { int result = (int) (2 * (l + l));  return String.valueOf(result); },
                float.class,   f -> f >= 25,
                               f -> { int result = (int) (4 * (f + f));  return String.valueOf(result); },
                String.class,  s1 -> s1 == null,
                               s1 -> s1,
                String.class,  s2 -> s2.equals("test"),
                               s2 -> s2 + s2,
                Default.class, () -> { System.out.println("Default value 6 types"); return "0"; }
        );

        assertEquals(result6, value6 + value6);

        /* 7 */
        Figure figure = new Rectangle();

        int square = matches(figure,
                Rectangle.class, r -> r instanceof Figure,
                                 r -> { int result = r.square(); return result; },
                Circle.class,    c -> c instanceof Figure,
                                 c -> { int result = c.square(); return result; },
                Default.class,   () -> { System.out.println("Default shape"); return 5; }
        );

        assertEquals(square, 50);
    }
}
