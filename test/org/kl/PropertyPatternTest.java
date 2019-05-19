package org.kl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.kl.error.PatternException;
import org.kl.shape.*;

import java.util.*;

import static java.lang.System.out;
import static org.kl.handle.PropertyPattern.matches;
import static org.kl.handle.PropertyPattern.foreach;
import static org.kl.handle.PropertyPattern.of;

public class PropertyPatternTest {
    private static Set<Unpiped> listUpipeds;
    private static List<Bipiped> listBipipeds;
    private static Queue<Parallelepiped> listParallelepipeds;
    private static Map<Integer, String> map;

    @BeforeAll
    public static void init() {
        listUpipeds = new HashSet<Unpiped>() {{
            add(new Unpiped(5));
            add(new Unpiped(10));
            add(new Unpiped(15));
        }};

        listBipipeds = new ArrayList<Bipiped>() {{
            add(new Bipiped((short)5, (short)10));
            add(new Bipiped((short)10, (short)15));
            add(new Bipiped((short)15, (short)20));
        }};

        listParallelepipeds = new ArrayDeque<Parallelepiped>() {{
            add(new Parallelepiped((short)5,  (short)10, (short)15));
            add(new Parallelepiped((short)10, (short)15, (short)20));
            add(new Parallelepiped((short)15, (short)20, (short)25));
        }};

        map = new HashMap<Integer, String>() {{
            put(1, "one");
            put(2, "two");
            put(3, "three");
        }};
    }

    @AfterAll
    @SuppressWarnings("Duplicates")
    public static void destroy() {
        listUpipeds.clear();
        listBipipeds.clear();
        listParallelepipeds.clear();
        map.clear();
    }


    @Test
    public void matchesStatementTest() throws PatternException {
        Figure figure;

        /* 1 */
        figure = new Circle(10);

        matches(figure,
                Circle.class, of("radius"), (Integer r) -> {
                    out.println("Circle square: " + ((int) (2 * Math.PI * r)));
                }
        );

        matches(figure,
                Circle.class, of("radius", 15), (Integer r) -> {
                    out.println("Circle square: " + ((int) (2 * Math.PI * r)));
                }
        );

        /* 2 */
        figure = new Rectangle(15, 20);

        matches(figure,
                Rectangle.class, of("width", "height"), (Integer w, Integer h) -> {
                    out.println("Rect square: " + (w * h));
                }
        );

        matches(figure,
                Rectangle.class, of("width", 15, "height", 25), (Integer w, Integer h) -> {
                    out.println("Rect square: " + (w * h));
                }
        );

        /* 3 */
        figure = new Parallelepiped((short)10, (short)15, (short)20);

        matches(figure,
                Parallelepiped.class, of("width", "longitude", "height"), (Short w, Short s, Short h) -> {
                    out.println("Parallelepiped square: " + (w * s * h));
                }
        );

        matches(figure,
                Parallelepiped.class, of("width", (short)15, "longitude", (short)15, "height", (short)20),
                (Short w, Short s, Short h) -> out.println("Parallelepiped square: " + (w * s * h))
        );
    }

    @Test
    public void foreachLoopTest() throws PatternException {
        /* 1 */
        foreach(listUpipeds, of("radius"), (Double r) -> {
            System.out.println("Upiped square: " + (2 * Math.PI * r));
        });

        /* 2 */
        foreach(listBipipeds, of("width", "height"), (Short w, Short h) -> {
            System.out.println("Bipiped square: " + (w * h));
        });

        /* 3 */
        foreach(listParallelepipeds, of("width", "longitude", "height"), (Short w, Short l, Short h) -> {
            System.out.println("Parallelepiped square: " + (w * l * h));
        });

        /* 4 */
        foreach(map, of("key", "value"), (Integer k, String v) -> {
            System.out.println("map entry: " + k  + " - " + v);
        });

    }
}
