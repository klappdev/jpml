package org.kl;

import org.junit.jupiter.api.Test;
import org.kl.error.PatternException;
import org.kl.shape.Circle;
import org.kl.shape.Figure;
import org.kl.shape.Parallelepiped;
import org.kl.shape.Rectangle;

import static java.lang.System.*;
import static org.kl.handle.PositionPattern.matches;
import static org.kl.handle.PositionPattern.of;


public class PositionPatternTest {

    @Test
    public void matchesStatementTest() throws PatternException {
        Figure figure;

        /* 1 */
        figure = new Circle(5);

        matches(figure,
                Circle.class, of(5), () -> out.println("figure circle")
        );

        /* 2 */
        figure = new Rectangle(5, 10);

        matches(figure,
                Rectangle.class, of(5, 10), () -> out.println("figure rectangle")
        );

        /* 3 */
        figure = new Parallelepiped((short) 5, (short) 10, (short) 15);

        matches(figure,
                Parallelepiped.class, of((short)5, (short)10, (short)15), () -> out.println("figure parallelepiped")
        );
    }
}
