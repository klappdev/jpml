package org.kl;

import org.junit.jupiter.api.Test;

import static org.kl.handle.TypeGuardPattern.matches;

public class TypeGuardPatternTest {

    @Test
    public void matchesStatementTest() {
        /* 1 */
        byte value1 = 5;
        matches(value1,
                Byte.class, b -> b > - 1,
                            b -> { System.out.println(b * b); }
        );

        /* 1.1 */
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
    }
}
