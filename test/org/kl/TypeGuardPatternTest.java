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
    }
}
