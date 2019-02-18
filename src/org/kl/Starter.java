package org.kl;

import static org.kl.handle.PatternHandler.matches;
import static org.kl.handle.PatternHandler.Default;

public class Starter {

    public static void main(String... args) {
        String data = "unknown";

        /* 1 */
        byte value1 = 5;
        matches(value1,
                Byte.class,  b  -> { System.out.println(b * b); }
        );

        /* 2 */
        matches(data,
                Byte.class,    b  -> { System.out.println(b * b); },
                Default.class, () -> { System.out.println("Default value"); }
        );

        /* 3 */
        short value2 = 15;
        matches(value2,
                Byte.class,  b -> { System.out.println(b * b); },
                Short.class, s -> { System.out.println(s * s); }
        );

        /* 4 */
        matches(data,
                Byte.class,  b -> { System.out.println(b * b); },
                Short.class, s -> { System.out.println(s * s); },
                Default.class, () -> { System.out.println("Default value"); }
        );

        /* 5 */
        int value3 = 25;
        matches(value3,
                Byte.class,    b -> { System.out.println(b * b); },
                Short.class,   s -> { System.out.println(s * s); },
                Integer.class, i -> { System.out.println(i * i); }
        );

        /* 6 */
        matches(data,
                Byte.class,    b  -> { System.out.println(b * b); },
                Short.class,   s  -> { System.out.println(s * s); },
                Integer.class, i  -> { System.out.println(i * i); },
                Default.class, () -> { System.out.println("Default value"); }
        );

        /* 7 */
        long value4 = 35;
        matches(value4,
                Byte.class,    b -> { System.out.println(b * b); },
                Short.class,   s -> { System.out.println(s * s); },
                Integer.class, i -> { System.out.println(i * i); },
                Long.class,    l -> { System.out.println(l * l); }
        );

        /* 8 */
        matches(data,
                Byte.class,    b  -> { System.out.println(b * b); },
                Short.class,   s  -> { System.out.println(s * s); },
                Integer.class, i  -> { System.out.println(i * i); },
                Long.class,    l  -> { System.out.println(l * l); },
                Default.class, () -> { System.out.println("Default value"); }
        );

        /* 9 */
        float value5 = 45;
        matches(value5,
                Byte.class,    b -> { System.out.println(b * b); },
                Short.class,   s -> { System.out.println(s * s); },
                Integer.class, i -> { System.out.println(i * i); },
                Long.class,    l -> { System.out.println(l * l); },
                Float.class,   f -> { System.out.println(f * f); }
        );

        /* 10 */
        matches(data,
                Byte.class,    b -> { System.out.println(b * b); },
                Short.class,   s -> { System.out.println(s * s); },
                Integer.class, i -> { System.out.println(i * i); },
                Long.class,    l -> { System.out.println(l * l); },
                Float.class,   f -> { System.out.println(f * f); },
                Default.class, () -> { System.out.println("Default value"); }
        );

        /* 11 */
        double value6 = 55;
        matches(value6,
                Byte.class,    b -> { System.out.println(b * b); },
                Short.class,   s -> { System.out.println(s * s); },
                Integer.class, i -> { System.out.println(i * i); },
                Long.class,    l -> { System.out.println(l * l); },
                Float.class,   f -> { System.out.println(f * f); },
                Double.class,  d -> { System.out.println(d * d); }
        );

        /* 12 */
        matches(data,
                Byte.class,    b -> { System.out.println(b * b); },
                Short.class,   s -> { System.out.println(s * s); },
                Integer.class, i -> { System.out.println(i * i); },
                Long.class,    l -> { System.out.println(l * l); },
                Float.class,   f -> { System.out.println(f * f); },
                Double.class,  d -> { System.out.println(d * d); },
                Default.class, () -> { System.out.println("Default value"); }
        );
    }
}
