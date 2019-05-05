package org.kl;

import org.junit.jupiter.api.Test;
import org.kl.error.PatternException;

import static org.kl.handle.TuplePattern.matches;

public class TuplePatternTest {

    @Test
    public void matchesStatementTest() throws PatternException {
        /* 1 */
        byte width1 = 10;
        String side1 = "top";

        matches(width1, side1,
                (byte) 10, "top", () -> System.out.println("side[" + side1 + "]=" + width1)
        );

        /* 2 */
        short width2 = 15;
        String side2 = "bottom";

        matches(width2, side2,
                (short) 10, "top",    () -> System.out.println("side[" + side2 + "]=" + width2),
                (short) 15, "bottom", () -> System.out.println("side[" + side2 + "]=" + width2)
        );

        /* 2 */
        int width3 = 20;
        String side3 = "left";

        matches(width3, side3,
                10, "top",    () -> System.out.println("side[" + side3 + "]=" + width3),
                15, "bottom", () -> System.out.println("side[" + side3 + "]=" + width3),
                20, "left",   () -> System.out.println("side[" + side3 + "]=" + width3)
        );
    }
}
