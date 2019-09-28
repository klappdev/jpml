package org.kl.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.kl.test.shape.Circle;
import org.kl.test.shape.Figure;
import org.kl.test.shape.Rectangle;
import org.kl.state.Else;
import org.kl.state.Null;
import org.kl.state.Var;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kl.pattern.GuardPattern.*;

public class GuardPatternTest {

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
                Byte.class, gt((byte) -1), b -> { System.out.println(b * b); }
        );

        matches(value1,
                Byte.class, greaterThan((byte) -1), b -> { System.out.println(b * b); }
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
                Short.class, gt((short) 15), s1 -> { System.out.println("br1: " + s1 * s1); },
                Short.class, ge((short) 5),  s2 -> { System.out.println("br2: " + s2 * s2); }
        );

        matches(value2,
                Short.class, greaterThan((short) 15), s1 -> { System.out.println("br1: " + s1 * s1); },
                Short.class, greaterThanOrEqual((short) 5),  s2 -> { System.out.println("br2: " + s2 * s2); }
        );

        /* 3 */
        int value3 = 15;
        matches(value3,
                Short.class,   s1 -> s1 == -1,
                               s1 -> { System.out.println("br1: " + s1 * s1); },
                Integer.class, i1 -> i1 > 20,
                               i1 -> { System.out.println("br2: " + i1 * i1); },
                Integer.class, i2 -> i2 >= 5,
                               i2 -> { System.out.println("br3: " + i2 * i2); }
        );

        matches(value3,
                short.class, eq((short)-1), s1 -> { System.out.println("pc1: " + s1 * s1); },
                int.class,   gt(10),        i1 -> { System.out.println("pc2: " + i1 * i1); },
                int.class,   ge(20),        i2 -> { System.out.println("pc3: " + i2 * i2); }
        );

        matches(value3,
                short.class, equal((short)-1), s1 -> { System.out.println("pc1: " + s1 * s1); },
                int.class,   greaterThan(10),  i1 -> { System.out.println("pc2: " + i1 * i1); },
                int.class,   greaterThan(20),  i2 -> { System.out.println("pc3: " + i2 * i2); }
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
                short.class,   gt((short)-1), s ->  { System.out.println("pc1: " + s * s); },
                int.class,     gt(20),        i ->  { System.out.println("pc2: " + i * i); },
                long.class,    lt(10L),       l1 -> { System.out.println("pc3: " + l1 * l1); },
                long.class,    ge(5L),        l2 -> { System.out.println("pc4: " + l2 * l2); }
        );

        matches(value4,
                short.class,   greaterThan((short)-1), s ->  { System.out.println("pc1: " + s * s); },
                int.class,     greaterThan(20),        i ->  { System.out.println("pc2: " + i * i); },
                long.class,    lessThan(10L),          l1 -> { System.out.println("pc3: " + l1 * l1); },
                long.class,    greaterThanOrEqual(5L), l2 -> { System.out.println("pc4: " + l2 * l2); }
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
                short.class,   gt((short)-1), s ->  { System.out.println("pc1: " + s * s); },
                int.class,     gt(20),        i ->  { System.out.println("pc2: " + i * i); },
                long.class,    gt(10L),       l ->  { System.out.println("pc3: " + l * l); },
                float.class,   ge(40F),       f1 -> { System.out.println("pc4: " + f1 * f1); },
                float.class,   ge(5F),        f2 -> { System.out.println("pc5: " + f2 * f2); }
        );

        matches(value5,
                Short.class,   greaterThan((short)-1),  s ->  { System.out.println("pc1: " + s * s); },
                Integer.class, greaterThan(20),         i ->  { System.out.println("pc2: " + i * i); },
                Long.class,    greaterThan(10L),        l ->  { System.out.println("pc3: " + l * l); },
                Float.class,   greaterThanOrEqual(40F), f1 -> { System.out.println("pc4: " + f1 * f1); },
                Float.class,   greaterThanOrEqual(5F),  f2 -> { System.out.println("pc5: " + f2 * f2); }
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

        /* 7 */
        Figure figure = new Rectangle();

        matches(figure,
                Rectangle.class, Objects::nonNull, r -> { System.out.println("rect:   " + r.square()); },
                Circle.class,    Objects::nonNull, c -> { System.out.println("circle: " + c.square()); }
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
                Else.class, () -> { System.out.println("Else value 1 type"); }
        );

        matches(data,
                byte.class, b -> b > - 1,
                            b -> { System.out.println("pc1: " + b * b); },
                Else.class, () -> { System.out.println("Else value 1 type"); }
        );

        /* 2 */
        matches(data,
                Short.class, s1 -> s1 > 15,
                             s1 -> { System.out.println("br1: " + s1 * s1); },
                Short.class, s2 -> s2 >= 5,
                             s2 -> { System.out.println("br2: " + s2 * s2); },
                Else.class, () -> { System.out.println("Else value 2 type"); }
        );

        matches(data,
                short.class, s1 -> s1 > 15,
                             s1 -> { System.out.println("pc1: " + s1 * s1); },
                short.class, s2 -> s2 >= 5,
                             s2 -> { System.out.println("pc2: " + s2 * s2); },
                Else.class, () -> { System.out.println("Else value 2 type"); }
        );

        /* 3 */
        matches(data,
                Short.class,   s1 -> s1 > -1,
                               s1 -> { System.out.println("br1: " + s1 * s1); },
                Integer.class, i1 -> i1 > 20,
                               i1 -> { System.out.println("br2: " + i1 * i1); },
                Integer.class, i2 -> i2 >= 5,
                               i2 -> { System.out.println("br3: " + i2 * i2); },
                Else.class, () -> { System.out.println("Else value 3 type"); }
        );

        matches(data,
                short.class, s1 -> s1 > -1,
                             s1 -> { System.out.println("pc1: " + s1 * s1); },
                int.class,   i1 -> i1 > 10,
                             i1 -> { System.out.println("pc2: " + i1 * i1); },
                int.class,   i2 -> i2 >= 20,
                             i2 -> { System.out.println("pc3: " + i2 * i2); },
                Else.class, () -> { System.out.println("Else value 3 type"); }
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
                Else.class, () -> { System.out.println("Else value 4 type"); }
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
                Else.class, () -> { System.out.println("Else value 4 type"); }
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
                Else.class, () -> { System.out.println("Else value 5 type"); }
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
                Else.class, () -> { System.out.println("Else value 5 type"); }
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
                Else.class, () -> { System.out.println("Else value 6 type"); }
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
                Else.class, () -> { System.out.println("Else value 6 type"); }
        );

        /* 7 */
        matches(data,
                Rectangle.class, Objects::nonNull,
                                 r -> { System.out.println("rect:   " + r.square()); },
                Circle.class,    Objects::nonNull,
                                 c -> { System.out.println("circle: " + c.square()); },
                Else.class,   () -> { System.out.println("Else figure"); }
        );
    }

    @Disabled
    @Test
    public void matchesStatementWithVarTest() {
        String data = "unknown";

        /* 1 */
        matches(data,
                Byte.class, b -> b > -1, b -> { System.out.println("br1: " + b * b); },
                Var.class, any -> System.out.println("Var value 1 type")
        );

        /* 2 */
        matches(data,
                Short.class, s1 -> s1 > 15, s1 -> { System.out.println("br1: " + s1 * s1); },
                Short.class, s2 -> s2 >= 5, s2 -> { System.out.println("br2: " + s2 * s2); },
                Var.class, any -> System.out.println("Var value 2 type")
        );

        /* 3 */
        matches(data,
                Short.class,   s1 -> s1 > -1, s1 -> { System.out.println("br1: " + s1 * s1); },
                Integer.class, i1 -> i1 > 20, i1 -> { System.out.println("br2: " + i1 * i1); },
                Integer.class, i2 -> i2 >= 5, i2 -> { System.out.println("br3: " + i2 * i2); },
                Var.class, any -> System.out.println("Var value 3 type")
        );

        /* 4 */
        matches(data,
                Short.class,   s -> s > -1,    s -> { System.out.println("br1: " + s * s); },
                Integer.class, i -> i > 20,    i -> { System.out.println("br2: " + i * i); },
                Long.class,    l1 -> l1 != -1, l1 -> { System.out.println("br3: " + l1 * l1); },
                Long.class,    l2 -> l2 >= 5,  l2 -> { System.out.println("br4: " + l2 * l2); },
                Var.class, any -> System.out.println("Var value 4 type")
        );

        /* 5 */
        matches(data,
                Short.class,   s -> s > -1,   s -> { System.out.println("br1: " + s * s); },
                Integer.class, i -> i > 20,   i -> { System.out.println("br2: " + i * i); },
                Long.class,    l -> l != -1,  l -> { System.out.println("br3: " + l * l); },
                Float.class,   f1 -> f1 >= 5, f1 -> { System.out.println("br4: " + f1 * f1); },
                Float.class,   f2 -> f2 < 50, f2 -> { System.out.println("br5: " + f2 * f2); },
                Var.class, any -> System.out.println("Var value 5 type")
        );

        /* 6 */
        matches(data,
                Short.class,   s -> s > -1,   s -> { System.out.println("br1: " + s * s); },
                Integer.class, i -> i > 20,   i -> { System.out.println("br2: " + i * i); },
                Long.class,    l -> l != -1,  l -> { System.out.println("br3: " + l * l); },
                Float.class,   f -> f >= 5,   f -> { System.out.println("br4: " + f * f); },
                Double.class,  d1 -> d1 >= 5, d1 -> { System.out.println("br5: " + d1 * d1); },
                Double.class,  d2 -> d2 < 50, d2 -> { System.out.println("br6: " + d2 * d2); },
                Var.class, any -> System.out.println("Var value 6 type")
        );

        /* 7 */
        matches(data,
                Rectangle.class, Objects::nonNull, r -> { System.out.println("rect:   " + r.square()); },
                Circle.class,    Objects::nonNull, c -> { System.out.println("circle: " + c.square()); },
                Var.class, any -> System.out.println("Var figure")
        );
    }

    @Disabled
    @Test
    public void matchesStatementWithVarDefaultTest() {
        String data = "unknown";

        ArrayList<String> list = new ArrayList<String>() {{
            add("new"); add("delete"); add("unknown");
        }};

        /* 1 */
        matches(data,
                Byte.class,  b  -> b > -1,
                             b -> System.out.println("br1: " + b * b),
                Var.class,   v -> list.contains(data),
                               () -> System.out.println("Var value 1 type "),
                Else.class, () -> System.out.println("Else value  1 type")
        );

        /* 2 */
        matches(data,
                Short.class, s1 -> s1 > 15, s1 -> { System.out.println("br1: " + s1 * s1); },
                Short.class, s2 -> s2 >= 5, s2 -> { System.out.println("br2: " + s2 * s2); },
                Var.class,     v -> list.contains(data),
                               () -> System.out.println("Var value with 2 type"),
                Else.class, () -> System.out.println("Else value  2 type")
        );

        /* 3 */
        matches(data,
                Short.class,   s1 -> s1 > -1, s1 -> { System.out.println("br1: " + s1 * s1); },
                Integer.class, i1 -> i1 > 20, i1 -> { System.out.println("br2: " + i1 * i1); },
                Integer.class, i2 -> i2 >= 5, i2 -> { System.out.println("br3: " + i2 * i2); },
                Var.class,     v -> list.contains(data),
                               () -> System.out.println("Var value with 4 type"),
                Else.class, () -> System.out.println("Else value  4 type")
        );

        /* 4 */
        matches(data,
                Short.class,   s -> s > -1,    s -> { System.out.println("br1: " + s * s); },
                Integer.class, i -> i > 20,    i -> { System.out.println("br2: " + i * i); },
                Long.class,    l1 -> l1 != -1, l1 -> { System.out.println("br3: " + l1 * l1); },
                Long.class,    l2 -> l2 >= 5,  l2 -> { System.out.println("br4: " + l2 * l2); },
                Var.class,     v -> list.contains(data),
                               () -> System.out.println("Var value with 4 type"),
                Else.class, () -> System.out.println("Else value  4 type")
        );

        /* 5 */
        matches(data,
                Short.class,   s -> s > -1,   s -> { System.out.println("br1: " + s * s); },
                Integer.class, i -> i > 20,   i -> { System.out.println("br2: " + i * i); },
                Long.class,    l -> l != -1,  l -> { System.out.println("br3: " + l * l); },
                Float.class,   f1 -> f1 >= 5, f1 -> { System.out.println("br4: " + f1 * f1); },
                Float.class,   f2 -> f2 < 50, f2 -> { System.out.println("br5: " + f2 * f2); },
                Var.class,     v -> list.contains(data),
                               () -> System.out.println("Var value with 5 type"),
                Else.class, () -> System.out.println("Else value  5 type")
        );

        /* 6 */
        matches(data,
                Short.class,   s -> s > -1,   s -> { System.out.println("br1: " + s * s); },
                Integer.class, i -> i > 20,   i -> { System.out.println("br2: " + i * i); },
                Long.class,    l -> l != -1,  l -> { System.out.println("br3: " + l * l); },
                Float.class,   f -> f >= 5,   f -> { System.out.println("br4: " + f * f); },
                Double.class,  d1 -> d1 >= 5, d1 -> { System.out.println("br5: " + d1 * d1); },
                Double.class,  d2 -> d2 < 50, d2 -> { System.out.println("br6: " + d2 * d2); },
                Var.class,     v -> list.contains(data),
                               () -> System.out.println("Var value with 6 type"),
                Else.class, () -> System.out.println("Else value  6 type")
        );
 	}

    @Disabled
    @Test
    public void matchesStatementWithNullDefaultTest() {
        String data = "unknown";

        /* 1 */
        matches(null,
                Byte.class,  b -> b !=  0,
                             b -> { System.out.println(b * b); },
                Null.class,    () -> { System.out.println("Null value 1 type"); },
                Else.class, () -> { System.out.println("Else value 1 type"); }
        );

        byte value1 = 1;
        matches(value1,
                byte.class,  b -> b !=  0,
                             b -> { System.out.println(b * b); },
                Null.class,    () -> { System.out.println("Null value 1 type"); },
                Else.class, () -> { System.out.println("Else value 1 type"); }
        );

        /* 2 */
        matches(null,
                Byte.class, b -> b != 0,
                            b -> System.out.println(b * b),
                Short.class, s -> s > 5,
                             s -> System.out.println(s * s),
                Null.class,    () -> System.out.println("Null value 2 type"),
                Else.class, () -> System.out.println("Else value 2 types")
        );

        short value2 = 2;
        matches(value2,
                Byte.class,  b -> b != 0,
                             b -> System.out.println(b * b),
                Short.class, s -> s > 1,
                             s -> System.out.println(s * s),
                Null.class,    () -> System.out.println("Null value 2 type"),
                Else.class, () -> System.out.println("Else value 2 types")
        );

        /* 3 */
        matches(null,
                Byte.class,    b -> b != 0,
                               b  -> { System.out.println(b * b); },
                Short.class,   s -> s > 5,
                               s  -> { System.out.println(s * s); },
                Integer.class, i -> i < 25,
                               i  -> { System.out.println(i * i); },
                Null.class,    () -> { System.out.println("Null value 3 type"); },
                Else.class, () -> { System.out.println("Else value 3 types"); }
        );

        int value3 = 3;
        matches(value3,
                byte.class,    b -> b != 0,
                               b  -> { System.out.println(b * b); },
                short.class,   s -> s > 5,
                               s  -> { System.out.println(s * s); },
                int.class,     i -> i < 25,
                               i  -> { System.out.println(i * i); },
                Null.class,    () -> { System.out.println("Null value 3 type"); },
                Else.class, () -> { System.out.println("Else value 3 types"); }
        );

        /* 4 */
        matches(null,
                Byte.class,    b -> b != 0,
                               b  -> { System.out.println(b * b); },
                Short.class,   s -> s > 1,
                               s -> { System.out.println(s * s); },
                Integer.class, i -> i > 2,
                               i  -> { System.out.println(i * i); },
                Long.class,    l -> l < 10,
                               l  -> { System.out.println(l * l); },
                Null.class,    () -> { System.out.println("Null value 4 type"); },
                Else.class, () -> { System.out.println("Else value 4 types"); }
        );

        long value4 = 4;
        matches(value4,
                byte.class,    b -> b != 0,
                               b  -> { System.out.println(b * b); },
                short.class,   s -> s > 1,
                               s -> { System.out.println(s * s); },
                int.class,     i -> i > 2,
                               i  -> { System.out.println(i * i); },
                long.class,    l -> l < 10,
                               l  -> { System.out.println(l * l); },
                Null.class,    () -> { System.out.println("Null value 4 type"); },
                Else.class, () -> { System.out.println("Else value 4 types"); }
        );

        /* 5 */
        matches(null,
                Byte.class,    b -> b != 0,
                               b  -> { System.out.println(b * b); },
                Short.class,   s -> s > 1,
                               s -> { System.out.println(s * s); },
                Integer.class, i -> i > 2,
                               i  -> { System.out.println(i * i); },
                Long.class,    l -> l < 10,
                               l  -> { System.out.println(l * l); },
                Float.class,   f -> f == 5,
                               f -> { System.out.println(f * f); },
                Null.class,    () -> { System.out.println("Null value 5 type"); },
                Else.class, () -> { System.out.println("Else value 5 types"); }
        );

        float value5 = 5;
        matches(value5,
                byte.class,    b -> b != 0,
                               b  -> { System.out.println(b * b); },
                short.class,   s -> s > 1,
                               s -> { System.out.println(s * s); },
                int.class,     i -> i > 2,
                               i  -> { System.out.println(i * i); },
                long.class,    l -> l < 10,
                               l  -> { System.out.println(l * l); },
                float.class,   f -> f == 5,
                               f -> { System.out.println(f * f); },
                Null.class,    () -> { System.out.println("Null value 5 type"); },
                Else.class, () -> { System.out.println("Else value 5 types"); }
        );

        /* 6 */
        matches(null,
                Byte.class,    b -> b != 0,
                               b  -> { System.out.println(b * b); },
                Short.class,   s -> s > 1,
                               s -> { System.out.println(s * s); },
                Integer.class, i -> i > 2,
                               i  -> { System.out.println(i * i); },
                Long.class,    l -> l < 10,
                               l  -> { System.out.println(l * l); },
                Float.class,   f -> f == 5,
                               f -> { System.out.println(f * f); },
                Double.class,  d -> d <= 15,
                               d -> { System.out.println(d * d); },
                Null.class,    () -> { System.out.println("Null value 6 type"); },
                Else.class, () -> { System.out.println("Else value 6 types"); }
        );

        double value6 = 6;
        matches(value6,
                byte.class,    b -> b != 0,
                               b  -> { System.out.println(b * b); },
                short.class,   s -> s > 1,
                               s -> { System.out.println(s * s); },
                int.class,     i -> i > 2,
                               i  -> { System.out.println(i * i); },
                long.class,    l -> l < 10,
                               l  -> { System.out.println(l * l); },
                float.class,   f -> f == 5,
                               f -> { System.out.println(f * f); },
                Double.class,  d -> d <= 15,
                               d -> { System.out.println(d * d); },
                Null.class,    () -> { System.out.println("Null value 6 type"); },
                Else.class, () -> { System.out.println("Else value 6 types"); }
        );

        /* 7 */
        matches(data,
                Rectangle.class, Objects::nonNull,
                                 r  -> { System.out.println("rect:   " + r.square()); },
                Circle.class,    c -> c instanceof Figure,
                                 c  -> { System.out.println("circle: " + c.square()); },
                Null.class,      () -> { System.out.println("Null value shape"); },
                Else.class,   () -> { System.out.println("Else shape"); }
        );
    }

    @Disabled
    @Test
    public void matchesStatementWithNullVarTest() {
        /* 1 */
        matches(null,
                Byte.class, b -> b != 0, b -> System.out.println(b * b),
                Null.class, () -> System.out.println("Null value 1 type"),
                Var.class, any -> System.out.println("Var  value 1 type")
        );

        /* 2 */
        matches(null,
                Byte.class,  b -> b != 0, b -> System.out.println(b * b),
                Short.class, s -> s > 5,  s -> System.out.println(s * s),
                Null.class, () -> System.out.println("Null value 2 type"),
                Var.class, any -> System.out.println("Var  value 2 types")
        );

        /* 3 */
        matches(null,
                Byte.class,    b -> b != 0, b  -> System.out.println(b * b),
                Short.class,   s -> s > 5,  s  -> System.out.println(s * s),
                Integer.class, i -> i < 25, i  -> System.out.println(i * i),
                Null.class,    () -> System.out.println("Null value 3 type"),
                Var.class,    any -> System.out.println("Var value 3 types")
        );

        /* 4 */
        matches(null,
                Byte.class,    b -> b != 0, b -> System.out.println(b * b),
                Short.class,   s -> s > 1,  s -> System.out.println(s * s),
                Integer.class, i -> i > 2,  i -> System.out.println(i * i),
                Long.class,    l -> l < 10, l -> System.out.println(l * l),
                Null.class,    () -> System.out.println("Null value 4 type"),
                Var.class,    any -> System.out.println("Var value 4 types")
        );

        /* 5 */
        matches(null,
                Byte.class,    b -> b != 0, b  -> System.out.println(b * b),
                Short.class,   s -> s > 1,  s -> System.out.println(s * s),
                Integer.class, i -> i > 2,  i  -> System.out.println(i * i),
                Long.class,    l -> l < 10, l  -> System.out.println(l * l),
                Float.class,   f -> f == 5, f -> System.out.println(f * f),
                Null.class,    () -> System.out.println("Null value 5 type"),
                Var.class,    any -> System.out.println("Var  value 5 types")
        );

        /* 6 */
        matches(null,
                Byte.class,    b -> b != 0,  b -> System.out.println(b * b),
                Short.class,   s -> s > 1,   s -> System.out.println(s * s),
                Integer.class, i -> i > 2,   i -> System.out.println(i * i),
                Long.class,    l -> l < 10,  l -> System.out.println(l * l),
                Float.class,   f -> f == 5,  f -> System.out.println(f * f),
                Double.class,  d -> d <= 15, d -> System.out.println(d * d),
                Null.class,    () -> System.out.println("Null value 6 type"),
                Var.class,    any -> System.out.println("Var  value 6 types")
        );

        /* 7 */
        matches(null,
                Rectangle.class, Objects::nonNull,         r -> System.out.println("rect:   " + r.square()),
                Circle.class,    Figure.class::isInstance, c -> System.out.println("circle: " + c.square()),
                Null.class,      () -> System.out.println("Null value shape"),
                Else.class,   () -> System.out.println("Var shape")
        );
    }

    @Disabled
    @Test
    public void matchesStatementWithNullVarDefaultTest() {
        String data = "unknown";

        ArrayList<String> list = new ArrayList<String>() {{
            add("new"); add("delete"); add("unknown");
        }};

        /* 1 */
        matches(null,
                Byte.class,  b -> b != 0, b -> System.out.println(b * b),
                Null.class, () -> System.out.println("Null value 1 type"),
                Var.class,   v -> list.contains(data),
                            () -> System.out.println("Var value 1 type "),
                Else.class, () -> System.out.println("Else value  1 type")
        );

        /* 2 */
        matches(null,
                Byte.class,  b -> b != 0, b -> System.out.println(b * b),
                Short.class, s -> s > 5,  s -> System.out.println(s * s),
                Null.class, () -> System.out.println("Null value 2 type"),
                Var.class,   v -> list.contains(data),
                            () -> System.out.println("Var value 2 type "),
                Else.class, () -> System.out.println("Else value  2 type")
        );

        /* 3 */
        matches(null,
                Byte.class,    b -> b != 0, b  -> System.out.println(b * b),
                Short.class,   s -> s > 5,  s  -> System.out.println(s * s),
                Integer.class, i -> i < 25, i  -> System.out.println(i * i),
                Null.class,    () -> System.out.println("Null value 3 type"),
                Var.class,     v  -> list.contains(data),
                               () -> System.out.println("Var value 3 type "),
                Else.class, () -> System.out.println("Else value 3 type")
        );

        /* 4 */
        matches(null,
                Byte.class,    b -> b != 0, b -> System.out.println(b * b),
                Short.class,   s -> s > 1,  s -> System.out.println(s * s),
                Integer.class, i -> i > 2,  i -> System.out.println(i * i),
                Long.class,    l -> l < 10, l -> System.out.println(l * l),
                Null.class,    () -> System.out.println("Null value 4 type"),
                Var.class,     v  -> list.contains(data),
                               () -> System.out.println("Var value 4 type "),
                Else.class, () -> System.out.println("Else value 4 type")
        );

        /* 5 */
        matches(null,
                Byte.class,    b -> b != 0, b  -> System.out.println(b * b),
                Short.class,   s -> s > 1,  s -> System.out.println(s * s),
                Integer.class, i -> i > 2,  i  -> System.out.println(i * i),
                Long.class,    l -> l < 10, l  -> System.out.println(l * l),
                Float.class,   f -> f == 5, f -> System.out.println(f * f),
                Null.class,    () -> System.out.println("Null value 5 type"),
                Var.class,     v  -> list.contains(data),
                               () -> System.out.println("Var value 5 type "),
                Else.class, () -> System.out.println("Else value 5 type")
        );

        /* 6 */
        matches(null,
                Byte.class,    b -> b != 0,  b -> System.out.println(b * b),
                Short.class,   s -> s > 1,   s -> System.out.println(s * s),
                Integer.class, i -> i > 2,   i -> System.out.println(i * i),
                Long.class,    l -> l < 10,  l -> System.out.println(l * l),
                Float.class,   f -> f == 5,  f -> System.out.println(f * f),
                Double.class,  d -> d <= 15, d -> System.out.println(d * d),
                Null.class,    () -> System.out.println("Null value 6 type"),
                Var.class,     v  -> list.contains(data),
                               () -> System.out.println("Var value 6 type "),
                Else.class, () -> System.out.println("Else value 6 type")
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

    @Disabled
    @Test
    public void matchesExpressionWithDefaultTest() {
        int data = 0;

        /* 1 */
        int result1 = matches(data,
                Byte.class,  b -> b >= 1,
                             b -> { int result = 2 * (b + b); return result; },
                Else.class, () -> { System.out.println("Else value 1 types"); return 0; }
        );

        assertEquals(result1, 0);


        byte value1 = 5;
        result1 = matches(value1,
                byte.class, b -> b >= 3,
                            b -> { int result = 2 * (b + b); return result; },
                Else.class, () -> { System.out.println("Else value 1 types"); return 0; }
        );

        assertEquals(result1, 20);

        /* 2 */
        int result2 = matches(data,
                Short.class,  s1 -> s1 < 20,
                              s1 -> { int result = 2 * (s1 + s1);  return result; },
                Short.class,  s2 -> s2 >= 10,
                              s2 -> { int result = 3 * (s2 + s2);  return result; },
                Else.class, () -> { System.out.println("Else value 2 types"); return 1; }
        );

        assertEquals(result2, 1);

        short value2 = 10;
        result2 = matches(value2,
                short.class, s1 -> s1 > 30,
                             s1 -> { int result = 3 * (s1 + s1);  return result; },
                short.class, s2 -> s2 != 5,
                             s2 -> { int result = 2 * (s2 + s2);  return result; },
                Else.class, () -> { System.out.println("Else value 2 types"); return 0; }
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
                Else.class, () -> { System.out.println("Else value 3 types"); return 2; }
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
                Else.class, () -> { System.out.println("Else value 3 types"); return 0; }
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
                Else.class, () -> { System.out.println("Else value 4 types"); return 4; }
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
                Else.class, () -> { System.out.println("Else value 4 types"); return 0; }
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
                Else.class, () -> { System.out.println("Else value 5 types"); return 5; }
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
                Else.class, () -> { System.out.println("Else value 5 types"); return 0; }
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
                Else.class, () -> { System.out.println("Else value 6 types"); return "6"; }
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
                Else.class, () -> { System.out.println("Else value 6 types"); return "0"; }
        );

        assertEquals(result6, value6 + value6);

        /* 7 */
        Figure figure = new Rectangle();

        int square = matches(figure,
                Rectangle.class, r -> r instanceof Figure,
                                 r -> { int result = r.square(); return result; },
                Circle.class,    c -> c instanceof Figure,
                                 c -> { int result = c.square(); return result; },
                Else.class,   () -> { System.out.println("Else shape"); return 5; }
        );

        assertEquals(square, 50);
    }

    @Disabled
    @Test
    public void matchesExpressionWithVarTest() {
        int data = 0;

        /* 1 */
        int result1 = matches(data,
                Byte.class,  b -> b >= 1, b -> { int result = 2 * (b + b); return result; },
                Var.class, any -> { System.out.println("Var value 1 types"); return 0; }
        );

        assertEquals(result1, 0);


        /* 2 */
        int result2 = matches(data,
                Short.class,  s1 -> s1 < 20,  s1 -> { int result = 2 * (s1 + s1);  return result; },
                Short.class,  s2 -> s2 >= 10, s2 -> { int result = 3 * (s2 + s2);  return result; },
                Var.class, any -> { System.out.println("Var value 2 types"); return 1; }
        );

        assertEquals(result2, 1);

        /* 3 */
        int result3 = matches(data,
                Byte.class,    b -> b != -1,  b  -> { int result = 2 * (b + b);  return result; },
                Integer.class, i1 -> i1 > 20, i1 -> { int result = 2 * (i1 + i1);  return result; },
                Integer.class, i2 -> i2 > 10, i2 -> { int result = 3 * (i2 + i2);  return result; },
                Var.class, any -> { System.out.println("Var value 3 types"); return 2; }
        );

        assertEquals(result3, 2);

        /* 4 */
        int result4 = matches(data,
                Byte.class,    b -> b != -1,   b  -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s > 5,     s  -> { int result = 2 * (s + s);  return result; },
                Long.class,    l1 -> l1 > 25,  l1 -> { int result = (int) (3 * (l1 + l1));  return result; },
                Long.class,    l2 -> l2 > 15,  l2 -> { int result = (int) (4 * (l2 + l2));  return result; },
                Var.class, any -> { System.out.println("Var value 4 types"); return 4; }
        );

        assertEquals(result4, 4);

        /* 5 */
        int result5 = matches(data,
                Byte.class,    b -> b != -1,   b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s > 5,     s -> { int result = 2 * (s + s);  return result; },
                Long.class,    l -> l != 10,   l -> { int result = (int) (2 * (l + l));  return result; },
                Float.class,   f1 -> f1 > 25,  f1 -> { int result = (int) (3 * (f1 + f1));  return result; },
                Float.class,   f2 -> f2 > 15,  f2 -> { int result = (int) (4 * (f2 + f2));  return result; },
                Var.class, any -> { System.out.println("Var value 5 types"); return 5; }
        );

        assertEquals(result5, 5);

        /* 6 */
        String result6 = matches(data,
                Byte.class,    b -> b != -1,  b -> { int result = 2 * (b + b);  return String.valueOf(result); },
                Short.class,   s -> s > 5,    s -> { int result = 2 * (s + s);  return String.valueOf(result); },
                Long.class,    l -> l != 10,  l -> { int result = (int) (2 * (l + l));  return String.valueOf(result); },
                Float.class,   f -> f >= 25,  f -> { int result = (int) (4 * (f + f));  return String.valueOf(result); },
                String.class,  s1 -> !s1.isEmpty(),      s1 -> s1,
                String.class,  s2 -> s2.equals("test"),  s2 -> s2 + s2,
                Var.class,    any -> { System.out.println("Var value 6 types"); return "6"; }
        );

        assertEquals(result6, "6");
    }

    @Disabled
    @Test
    public void matchesExpressionWithVarDefaultTest() {
        String data = "unknown";

        ArrayList<String> list = new ArrayList<String>() {{
            add("new"); add("delete"); add("unknown");
        }};

        /* 1 */
        int result1 = matches(data,
                Byte.class,  b -> b >= 1, b -> { int result = 2 * (b + b); return result; },
                Var.class,   v -> list.contains(data),
                            () -> { System.out.println("Var value with 1 type"); return -1; },
                Else.class, () -> { System.out.println("Else value 1 type"); return 0; }
        );

        assertEquals(result1, -1);


        /* 2 */
        int result2 = matches(data,
                Short.class,  s1 -> s1 < 20,  s1 -> { int result = 2 * (s1 + s1);  return result; },
                Short.class,  never(),        s2 -> { int result = 3 * (s2 + s2);  return result; },
                Var.class,      v -> list.contains(data),
                               () -> { System.out.println("Var value with 2 type"); return -1; },
                Else.class, () -> { System.out.println("Else value 2 type"); return 0; }
        );

        assertEquals(result2, -1);

        /* 3 */
        int result3 = matches(data,
                Byte.class,    b -> b != -1,  b  -> { int result = 2 * (b + b);  return result; },
                Integer.class, never(),       i1 -> { int result = 2 * (i1 + i1);  return result; },
                Integer.class, always(),      i2 -> { int result = 3 * (i2 + i2);  return result; },
                Var.class,     v  -> list.contains(data),
                               () -> { System.out.println("Var value with 3 type"); return -1; },
                Else.class, () -> { System.out.println("Else value  3 type"); return 0; }
        );

        assertEquals(result3, -1);

        /* 4 */
        int result4 = matches(data,
                Byte.class,    b -> b != -1,   b  -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s > 5,     s  -> { int result = 2 * (s + s);  return result; },
                Long.class,    no(),           l1 -> { int result = (int) (3 * (l1 + l1));  return result; },
                Long.class,    yes(),          l2 -> { int result = (int) (4 * (l2 + l2));  return result; },
                Var.class,     v  -> list.contains(data),
                               () -> { System.out.println("Var value with 4 type"); return -1; },
                Else.class, () -> { System.out.println("Else value  4 type"); return 0; }
        );

        assertEquals(result4, -1);

        /* 5 */
        int result5 = matches(data,
                Byte.class,    b -> b != -1,   b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s > 5,     s -> { int result = 2 * (s + s);  return result; },
                Long.class,    l -> l != 10,   l -> { int result = (int) (2 * (l + l));  return result; },
                Float.class,   f1 -> f1 > 25,  f1 -> { int result = (int) (3 * (f1 + f1));  return result; },
                Float.class,   f2 -> f2 > 15,  f2 -> { int result = (int) (4 * (f2 + f2));  return result; },
                Var.class,     v  -> list.contains(data),
                               () -> { System.out.println("Var value with 5 type"); return -1; },
                Else.class, () -> { System.out.println("Else value  5 type"); return 0; }
        );

        assertEquals(result5, -1);

        /* 6 */
        String result6 = matches(data,
                Byte.class,    b -> b != -1,  b -> { int result = 2 * (b + b);  return String.valueOf(result); },
                Short.class,   s -> s > 5,    s -> { int result = 2 * (s + s);  return String.valueOf(result); },
                Long.class,    l -> l != 10,  l -> { int result = (int) (2 * (l + l));  return String.valueOf(result); },
                Float.class,   f -> f >= 25,  f -> { int result = (int) (4 * (f + f));  return String.valueOf(result); },
                String.class,  s1 -> !s1.isEmpty(),      s1 -> s1,
                String.class,  s2 -> s2.equals("test"),  s2 -> s2 + s2,
                Var.class,     v  -> list.contains(data),
                               () -> { System.out.println("Var value with 6 type"); return "unknown"; },
                Else.class, () -> { System.out.println("Else value  6 type"); return "0"; }
        );

        assertEquals(result6, "unknown");
    }

    @Disabled
    @Test
    public void matchesExpressionWithNullDefaultTest() {
        /* 1 */
        int result1 = matches(null,
                Byte.class,    b -> b != 0,
                               b -> { int result = 2 * (b + b);  return result; },
                Null.class,    () -> { System.out.println("Null value 1 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 1 types"); return  0; }
        );

        assertEquals(result1, -1);

        byte value1 = 1;
        result1 = matches(value1,
                byte.class,    b -> b != 0,
                               b -> { int result = 2 * (b + b);  return result; },
                Null.class,    () -> { System.out.println("Null value 1 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 1 types"); return  0; }
        );

        assertEquals(result1, 4);

        /* 2 */
        int result2 = matches(null,
                Byte.class,    b -> b != 0,
                               b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s > 5,
                               s  -> { int result = 2 * (s + s);  return result; },
                Null.class,    () -> { System.out.println("Null value 2 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 2 types"); return  1; }
        );

        assertEquals(result2, -1);

        short value2 = 2;
        result2 = matches(value2,
                byte.class,    b -> b != 0,
                               b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> s == 2,
                               s  -> { int result = 2 * (s + s);  return result; },
                Null.class,    () -> { System.out.println("Null value 2 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 2 types"); return  0; }
        );

        assertEquals(result2, 8);

        /* 3 */
        int result3 = matches(null,
                Byte.class,    b -> b != 0,
                               b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s == 2,
                               s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> i < 5,
                               i  -> { int result = 2 * (i + i);  return result; },
                Null.class,    () -> { System.out.println("Null value 3 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 3 types"); return  2; }
        );

        assertEquals(result3, -1);

        int value3 = 3;
        result3 = matches(value3,
                byte.class,    b -> b != 0,
                               b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> s == 2,
                               s -> { int result = 2 * (s + s);  return result; },
                int.class,     i -> i < 5,
                               i  -> { int result = 2 * (i + i);  return result; },
                Null.class,    () -> { System.out.println("Null value 3 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 3 types"); return  0; }
        );

        assertEquals(result3, 12);

        /* 4 */
        int result4 = matches(null,
                Byte.class,    b -> b != 0,
                               b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s == 2,
                               s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> i < 5,
                               i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> l >= 4,
                               l -> { int result = (int) (2 * (l + l));  return result; },
                Null.class,    () -> { System.out.println("Null value 4 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 4 types"); return  4; }
        );

        assertEquals(result4, -1);

        long value4 = 4;
        result4 = matches(value4,
                byte.class,    b -> b != 0,
                               b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> s == 2,
                               s -> { int result = 2 * (s + s);  return result; },
                int.class,     i -> i < 5,
                               i -> { int result = 2 * (i + i);  return result; },
                long.class,    l -> l >= 4,
                               l -> { int result = (int) (2 * (l + l));  return result; },
                Null.class,    () -> { System.out.println("Null value 4 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 4 types"); return  0; }
        );

        assertEquals(result4, 16);

        /* 5 */
        int result5 = matches(null,
                Byte.class,    b -> b != 0,
                               b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s == 2,
                               s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> i < 5,
                               i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> l >= 4,
                               l -> { int result = (int) (2 * (l + l));  return result; },
                Float.class,   f -> f <= 5,
                               f -> { int result = (int) (2 * (f + f));  return result;},
                Null.class,    () -> { System.out.println("Null value 5 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 5 types"); return  5; }
        );
        assertEquals(result5, -1);

        float value5 = 5;
        result5 = matches(value5,
                byte.class,    b -> b != 0,
                               b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> s == 2,
                               s -> { int result = 2 * (s + s);  return result; },
                int.class,     i -> i < 5,
                               i -> { int result = 2 * (i + i);  return result; },
                long.class,    l -> l >= 4,
                               l -> { int result = (int) (2 * (l + l));  return result; },
                float.class,   f -> f <= 5,
                               f -> { int result = (int) (2 * (f + f));  return result;},
                Null.class,    () -> { System.out.println("Null value 5 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 5 types"); return  0; }
        );

        assertEquals(result5, 20);

        /* 6 */
        int result6 = matches(null,
                Byte.class,    b -> b != 0,
                               b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s == 2,
                               s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> i < 5,
                               i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> l >= 4,
                               l -> { int result = (int) (2 * (l + l));  return result; },
                Float.class,   f -> f <= 5,
                               f -> { int result = (int) (2 * (f + f));  return result; },
                Double.class,  d -> d >= 6,
                               d -> { int result = (int) (2 * (d + d));  return result; },
                Null.class,    () -> { System.out.println("Null value 6 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 6 types"); return  6; }
        );

        assertEquals(result6, -1);

        double value6 = 6;
        result6 = matches(value6,
                byte.class,    b -> b != 0,
                               b -> { int result = 2 * (b + b);  return result; },
                short.class,   s -> s == 2,
                               s -> { int result = 2 * (s + s);  return result; },
                int.class,     i -> i < 5,
                               i -> { int result = 2 * (i + i);  return result; },
                long.class,    l -> l >= 4,
                               l -> { int result = (int) (2 * (l + l));  return result; },
                float.class,   f -> f <= 5,
                               f -> { int result = (int) (2 * (f + f));  return result; },
                double.class,  d -> d >= 6,
                               d -> { int result = (int) (2 * (d + d));  return result; },
                Null.class,    () -> { System.out.println("Null value 6 type");     return -1; },
                Else.class, () -> { System.out.println("Else value 6 types"); return  0; }
        );

        assertEquals(result6, 24);

        /* 7 */
        int square = matches(null,
                Rectangle.class, r -> r != null,
                                 r -> { int result = r.square(); return result; },
                Circle.class,    c -> c != null,
                                 c -> { int result = c.square(); return result; },
                Null.class,      () -> { System.out.println("Null value 7 type"); return -1; },
                Else.class,   () -> { System.out.println("Else shape"); return 5; }
        );

        assertEquals(square, -1);
    }

    @Disabled
    @Test
    public void matchesExpressionWithNullVarTest() {
        /* 1 */
        int result1 = matches(null,
                Byte.class,  b -> b != 0, b -> { int result = 2 * (b + b);   return result; },
                Null.class, () -> { System.out.println("Null value 1 type"); return -1; },
                Var.class, any -> { System.out.println("Var value 1 types"); return  0; }
        );

        assertEquals(result1, -1);

        /* 2 */
        int result2 = matches(null,
                Byte.class,   b -> b != 0, b -> { int result = 2 * (b + b);  return result;  },
                Short.class,  s -> s > 5,  s  -> { int result = 2 * (s + s);  return result; },
                Null.class,  () -> { System.out.println("Null value 2 type");     return -1; },
                Var.class,  any -> { System.out.println("Var value 2 types"); return  1;     }
        );

        assertEquals(result2, -1);

        /* 3 */
        int result3 = matches(null,
                Byte.class,    b -> b != 0,  b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s == 2,  s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> i < 5,   i  -> { int result = 2 * (i + i);  return result; },
                Null.class,    () -> { System.out.println("Null value 3 type");     return -1; },
                Var.class,    any -> { System.out.println("Else value 3 types"); return  2; }
        );

        assertEquals(result3, -1);

        /* 4 */
        int result4 = matches(null,
                Byte.class,    b -> b != 0,  b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s == 2,  s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> i < 5,   i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> l >= 4,  l -> { int result = (int) (2 * (l + l));  return result; },
                Null.class,    () -> { System.out.println("Null value 4 type");     return -1; },
                Var.class,  any -> { System.out.println("Else value 4 types"); return  4; }
        );

        assertEquals(result4, -1);

        /* 5 */
        int result5 = matches(null,
                Byte.class,    b -> b != 0, b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s == 2, s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> i < 5,  i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> l >= 4, l -> { int result = (int) (2 * (l + l));  return result; },
                Float.class,   f -> f <= 5, f -> { int result = (int) (2 * (f + f));  return result;},
                Null.class,    () -> { System.out.println("Null value 5 type");     return -1; },
                Var.class,  any -> { System.out.println("Else value 5 types"); return  5; }
        );
        assertEquals(result5, -1);

        /* 6 */
        int result6 = matches(null,
                Byte.class,    b -> b != 0,  b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s == 2,  s -> { int result = 2 * (s + s);  return result; },
                Integer.class, i -> i < 5,   i -> { int result = 2 * (i + i);  return result; },
                Long.class,    l -> l >= 4,  l -> { int result = (int) (2 * (l + l));  return result; },
                Float.class,   f -> f <= 5,  f -> { int result = (int) (2 * (f + f));  return result; },
                Double.class,  d -> d >= 6,  d -> { int result = (int) (2 * (d + d));  return result; },
                Null.class,    () -> { System.out.println("Null value 6 type");     return -1; },
                Var.class,  any -> { System.out.println("Else value 6 types"); return  6; }
        );

        assertEquals(result6, -1);
    }

    @Test
    public void matchesExpressionWithNullVarDefaultTest() {
        String data = "unknown";

        ArrayList<String> list = new ArrayList<String>() {{
            add("new"); add("delete"); add("unknown");
        }};

        /* 1 */
        int result1 = matches(null,
                Byte.class,    b  -> b >= 1, b -> { int result = 2 * (b + b); return result; },
                Null.class,    () -> { System.out.println("Null value 1 type");     return -1; },
                Var.class,     v  -> list.contains(data),
                               () -> { System.out.println("Var value with 1 type"); return +1; },
                Else.class, () -> { System.out.println("Else value 1 type"); return 0; }
        );

        assertEquals(result1, -1);


        /* 2 */
        int result2 = matches(null,
                Short.class,  s1 -> s1 < 20,  s1 -> { int result = 2 * (s1 + s1);  return result; },
                Short.class,  never(),        s2 -> { int result = 3 * (s2 + s2);  return result; },
                Null.class,    () -> { System.out.println("Null value 2 type");     return -1; },
                Var.class,      v -> list.contains(data),
                               () -> { System.out.println("Var value with 2 type"); return +1; },
                Else.class, () -> { System.out.println("Else value 2 type"); return 0; }
        );

        assertEquals(result2, -1);

        /* 3 */
        int result3 = matches(null,
                Byte.class,    b -> b != -1,   b -> { int result = 2 * (b + b);  return result; },
                Short.class,   always(),       s -> { int result = 2 * (s + s);  return result; },
                Integer.class, never(),        i -> { int result = 3 * (i + i);  return result; },
                Null.class,    () -> { System.out.println("Null value 3 type");     return -1; },
                Var.class,     v  -> list.contains(data),
                               () -> { System.out.println("Var value with 3 type"); return +1; },
                Else.class, () -> { System.out.println("Else value  3 type"); return 0; }
        );

        assertEquals(result3, -1);

        /* 4 */
        int result4 = matches(null,
                Byte.class,    b -> b != -1,   b  -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s > 5,     s  -> { int result = 2 * (s + s);  return result; },
                Long.class,    no(),           l1 -> { int result = (int) (3 * (l1 + l1));  return result; },
                Long.class,    yes(),          l2 -> { int result = (int) (4 * (l2 + l2));  return result; },
                Null.class,    () -> { System.out.println("Null value 4 type");     return -1; },
                Var.class,     v  -> list.contains(data),
                               () -> { System.out.println("Var value with 4 type"); return +1; },
                Else.class, () -> { System.out.println("Else value  4 type"); return 0; }
        );

        assertEquals(result4, -1);

        /* 5 */
        int result5 = matches(null,
                Byte.class,    b -> b != -1,   b -> { int result = 2 * (b + b);  return result; },
                Short.class,   s -> s > 5,     s -> { int result = 2 * (s + s);  return result; },
                Long.class,    l -> l != 10,   l -> { int result = (int) (2 * (l + l));  return result; },
                Float.class,   f1 -> f1 > 25,  f1 -> { int result = (int) (3 * (f1 + f1));  return result; },
                Float.class,   f2 -> f2 > 15,  f2 -> { int result = (int) (4 * (f2 + f2));  return result; },
                Null.class,    () -> { System.out.println("Null value 5 type");     return -1; },
                Var.class,     v  -> list.contains(data),
                               () -> { System.out.println("Var value with 5 type"); return +1; },
                Else.class, () -> { System.out.println("Else value  5 type"); return 0; }
        );

        assertEquals(result5, -1);

        /* 6 */
        String result6 = matches(null,
                Byte.class,    b -> b != -1,  b -> { int result = 2 * (b + b);  return String.valueOf(result); },
                Short.class,   s -> s > 5,    s -> { int result = 2 * (s + s);  return String.valueOf(result); },
                Long.class,    l -> l != 10,  l -> { int result = (int) (2 * (l + l));  return String.valueOf(result); },
                Float.class,   f -> f >= 25,  f -> { int result = (int) (4 * (f + f));  return String.valueOf(result); },
                String.class,  s1 -> !s1.isEmpty(),      s1 -> s1,
                String.class,  s2 -> s2.equals("test"),  s2 -> s2 + s2,
                Null.class,    () -> { System.out.println("Null value 6 type");     return "nil"; },
                Var.class,     v  -> list.contains(data),
                               () -> { System.out.println("Var value with 6 type"); return "unknown"; },
                Else.class, () -> { System.out.println("Else value  6 type"); return "0"; }
        );

        assertEquals(result6, "nil");
    }
}
