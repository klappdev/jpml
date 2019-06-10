package org.kl;

import org.junit.jupiter.api.Test;
import org.kl.util.Optional;
import static org.kl.pattern.StaticPattern.matches;
import static org.kl.pattern.StaticPattern.of;

import org.kl.error.PatternException;

import static java.lang.System.out;

public class StaticPatternTest {

    @Test
    public void matchesStatementTest() throws PatternException {
        Optional<Integer> data;

        /* 1 */
        data = Optional.empty();

        matches(data,
                Optional.class, of("empty"), () -> out.println("empty value")
        );

        /* 1 */
        data = Optional.of(5);

        matches(data,
                Optional.class, of("empty"), () -> out.println("empty value"),
                Optional.class, of("of"),    v  -> System.out.println("value: " + v)
        );
    }
}
