package org.kl;

import org.junit.jupiter.api.Test;
import org.kl.error.PatternException;

import static org.kl.handle.ConstantPattern.matches;

public class ConstantPatternTest {

    @Test
    public void matchesStatementTest() throws PatternException {
        /* 1 */
        byte value1 = 5;

        matches(value1,
                (byte) 5, ()  -> System.out.println("brunch 1 - " + value1)
        );

        /* 2 */
        short value2 = 10;

        matches(value2,
                (short) 5,  ()  -> System.out.println("brunch 1 - " + value2),
                (short) 10, ()  -> System.out.println("brunch 2 - " + value2)
        );

        /* 3 */
        int value3 = 15;

        matches(value3,
                5,  ()  -> System.out.println("brunch 1 - " + value3),
                10, ()  -> System.out.println("brunch 2 - " + value3),
                15, ()  -> System.out.println("brunch 3 - " + value3)
        );
    }
}
