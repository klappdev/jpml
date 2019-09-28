package org.kl.test;

import org.junit.jupiter.api.Test;
import org.kl.test.color.*;
import org.kl.lambda.Purchaser;
import org.kl.lambda.Routine;
import org.kl.util.Union;

import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.kl.pattern.UnionPattern.matches;

public class UnionPatternTest {

    @Test
    public void matchesUnionStatementTest() {
        /* 1 */
        Union biUnion = Union.of(Integer.class, String.class);
        biUnion.set(5);

        assertEquals(biUnion.getActive(), Integer.class);
        assertTrue(biUnion.isActive(Integer.class));

        matches(biUnion,
                Integer.class, i -> System.out.println("bi-union number: " + i),
                String.class, (Purchaser<String>) s -> System.out.println("bi-union string: " + s)
        );

        /* 2 */
        Union triUnion = Union.of(Integer.class, String.class, Float.class);
        triUnion.set(10);

        assertEquals(triUnion.getActive(), Integer.class);
        assertTrue(triUnion.isActive(Integer.class));

        matches(triUnion,
                Integer.class, i -> System.out.println("tri-union int:    " + i),
                String.class,  s -> System.out.println("tri-union string: " + s),
                Float.class, (Purchaser<Float>) f -> System.out.println("tri-union float:  " + f)
        );

        /* 3 */
        Union quarUnion = Union.of(Integer.class, String.class, Float.class, Double.class);
        quarUnion.set(10D);

        assertEquals(quarUnion.getActive(), Double.class);
        assertTrue(quarUnion.isActive(Double.class));

        matches(quarUnion,
                Integer.class, i -> System.out.println("quar-union int:    " + i),
                String.class,  s -> System.out.println("quar-union string: " + s),
                Float.class,   f -> System.out.println("quar-union float:  " + f),
                Double.class, (Purchaser<Double>) d -> System.out.println("quar-union double: " + d)
        );

        /* 4 */
        Union quinUnion = Union.of(Integer.class, String.class, Float.class, Double.class, Long.class);
        quinUnion.set(10L);

        assertEquals(quinUnion.getActive(), Long.class);
        assertTrue(quinUnion.isActive(Long.class));

        matches(quinUnion,
                Integer.class, i -> System.out.println("quin-union int:    " + i),
                String.class,  s -> System.out.println("quin-union string: " + s),
                Float.class,   f -> System.out.println("quin-union float:  " + f),
                Double.class,  d -> System.out.println("quin-union double: " + d),
                Long.class, (Purchaser<Long>) l -> System.out.println("quin-union long:   " + l)
        );

        /* 5 */
        Union sexUnion = Union.of(Integer.class, String.class, Float.class, Double.class, Long.class, Short.class);
        sexUnion.set((short) 10);

        assertEquals(sexUnion.getActive(), Short.class);
        assertTrue(sexUnion.isActive(Short.class));

        matches(sexUnion,
                Integer.class, i -> System.out.println("sex-union int:    " + i),
                String.class,  s -> System.out.println("sex-union string: " + s),
                Float.class,   f -> System.out.println("sex-union float:  " + f),
                Double.class,  d -> System.out.println("sex-union double: " + d),
                Long.class,    l -> System.out.println("sex-union long:   " + l),
                Short.class, (Purchaser<Short>) s -> System.out.println("sex-union short:  " + s)
        );
    }

    @Test
    public void matchesAsUnionStatementTest() {
        /* 1 */
        Union biUnion = Union.of(Integer.class, String.class);
        biUnion.set(5);

        assertEquals(biUnion.getActive(), Integer.class);
        assertTrue(biUnion.isActive(Integer.class));

        matches(biUnion).as(
                Integer.class, i -> System.out.println("bi-union number: " + i),
                String.class, (Purchaser<String>) s -> System.out.println("bi-union string: " + s)
        );

        /* 2 */
        Union triUnion = Union.of(Integer.class, String.class, Float.class);
        triUnion.set("Some");

        assertEquals(triUnion.getActive(), String.class);
        assertTrue(triUnion.isActive(String.class));

        matches(triUnion).as(
                Integer.class, i -> System.out.println("tri-union int:    " + i),
                String.class,  s -> System.out.println("tri-union string: " + s),
                Float.class, (Purchaser<Float>) f -> System.out.println("tri-union float:  " + f)
        );

        /* 3 */
        Union quarUnion = Union.of(Integer.class, String.class, Float.class, Double.class);
        quarUnion.set(10D);

        assertEquals(quarUnion.getActive(), Double.class);
        assertTrue(quarUnion.isActive(Double.class));

        matches(quarUnion).as(
                Integer.class, i -> System.out.println("quar-union int:    " + i),
                String.class,  s -> System.out.println("quar-union string: " + s),
                Float.class,   f -> System.out.println("quar-union float:  " + f),
                Double.class, (Purchaser<Double>) d -> System.out.println("quar-union double: " + d)
        );

        /* 4 */
        Union quinUnion = Union.of(Integer.class, String.class, Float.class, Double.class, Long.class);
        quinUnion.set(10L);

        assertEquals(quinUnion.getActive(), Long.class);
        assertTrue(quinUnion.isActive(Long.class));

        matches(quinUnion).as(
                Integer.class, i -> System.out.println("quin-union int:    " + i),
                String.class,  s -> System.out.println("quin-union string: " + s),
                Float.class,   f -> System.out.println("quin-union float:  " + f),
                Double.class,  d -> System.out.println("quin-union double: " + d),
                Long.class, (Purchaser<Long>) l -> System.out.println("quin-union long:   " + l)
        );

        /* 5 */
        Union sexUnion = Union.of(Integer.class, String.class, Float.class, Double.class, Long.class, Short.class);
        sexUnion.set((short) 10);

        assertEquals(sexUnion.getActive(), Short.class);
        assertTrue(sexUnion.isActive(Short.class));

        matches(sexUnion).as(
                Integer.class, i -> System.out.println("sex-union int:    " + i),
                String.class,  s -> System.out.println("sex-union string: " + s),
                Float.class,   f -> System.out.println("sex-union float:  " + f),
                Double.class,  d -> System.out.println("sex-union double: " + d),
                Long.class,    l -> System.out.println("sex-union long:   " + l),
                Short.class, (Purchaser<Short>) s -> System.out.println("sex-union short:  " + s)
        );
    }

    @Test
    public void matchesUnionExpressionTest() {
        /* 1 */
        Union biUnion = Union.of(Integer.class, String.class);
        biUnion.set(5);

        int result1 = matches(biUnion,
                Integer.class, i -> 0x3,
                String.class, (Function<String, Integer>) s -> 0x9
        );

        assertEquals(result1, 0x3);

        /* 2 */
        Union triUnion = Union.of(Integer.class, String.class, Float.class);
        triUnion.set(10f);

        int result2 = matches(triUnion,
                Integer.class, i -> 0x3,
                String.class,  s -> 0x9,
                Float.class, (Function<Float, Integer>) f -> 0x5
        );

        assertEquals(result2, 0x5);

        /* 3 */
        Union quarUnion = Union.of(Integer.class, String.class, Float.class, Double.class);
        quarUnion.set(10D);

        int result3 = matches(quarUnion,
                Integer.class, i -> 0x3,
                String.class,  s -> 0x9,
                Float.class,   f -> 0x5,
                Double.class, (Function<Double, Integer>) d -> 0x6
        );

        assertEquals(result3, 0x6);

        /* 4 */
        Union quinUnion = Union.of(Integer.class, String.class, Float.class, Double.class, Long.class);
        quinUnion.set(10L);

        int result4 = matches(quinUnion,
                Integer.class, i -> 0x3,
                String.class,  s -> 0x9,
                Float.class,   f -> 0x5,
                Double.class,  d -> 0x6,
                Long.class, (Function<Long, Integer>) l -> 0x4
        );

        assertEquals(result4, 0x4);

        /* 5 */
        Union sexUnion = Union.of(Integer.class, String.class, Float.class, Double.class, Long.class, Short.class);
        sexUnion.set((short) 10);

        int result5 = matches(sexUnion,
                Integer.class, i -> 0x3,
                String.class,  s -> 0x9,
                Float.class,   f -> 0x5,
                Double.class,  d -> 0x6,
                Long.class,    l -> 0x4,
                Short.class, (Function<Short, Integer>) s -> 0x2
        );

        assertEquals(result5, 0x2);
    }

    @Test
    public void matchesAsUnionExpressionTest() {
        /* 1 */
        Union biUnion = Union.of(Integer.class, String.class);
        biUnion.set(5);

        int result1 = matches(biUnion).as(
                Integer.class, i -> 0x3,
                String.class, (Function<String, Integer>) s -> 0x9
        );

        assertEquals(result1, 0x3);

        /* 2 */
        Union triUnion = Union.of(Integer.class, String.class, Float.class);
        triUnion.set(10f);

        int result2 = matches(triUnion).as(
                Integer.class, i -> 0x3,
                String.class,  s -> 0x9,
                Float.class, (Function<Float, Integer>) f -> 0x5
        );

        assertEquals(result2, 0x5);

        /* 3 */
        Union quarUnion = Union.of(Integer.class, String.class, Float.class, Double.class);
        quarUnion.set(10D);

        int result3 = matches(quarUnion).as(
                Integer.class, i -> 0x3,
                String.class,  s -> 0x9,
                Float.class,   f -> 0x5,
                Double.class, (Function<Double, Integer>) d -> 0x6
        );

        assertEquals(result3, 0x6);

        /* 4 */
        Union quinUnion = Union.of(Integer.class, String.class, Float.class, Double.class, Long.class);
        quinUnion.set(10L);

        int result4 = matches(quinUnion).as(
                Integer.class, i -> 0x3,
                String.class,  s -> 0x9,
                Float.class,   f -> 0x5,
                Double.class,  d -> 0x6,
                Long.class, (Function<Long, Integer>) l -> 0x4
        );

        assertEquals(result4, 0x4);

        /* 5 */
        Union sexUnion = Union.of(Integer.class, String.class, Float.class, Double.class, Long.class, Short.class);
        sexUnion.set((short) 10);

        int result5 = matches(sexUnion).as(
                Integer.class, i -> 0x3,
                String.class,  s -> 0x9,
                Float.class,   f -> 0x5,
                Double.class,  d -> 0x6,
                Long.class,    l -> 0x4,
                Short.class, (Function<Short, Integer>) s -> 0x2
        );

        assertEquals(result5, 0x2);
    }

    @Test
    public void matchesSealedStatementTest() {
        /* 1 */
        BiColor biColor = new BiColor.Red();

        matches(biColor,
                BiColor.Red.class,  r -> System.out.println("red subclass:  " + r),
                BiColor.Blue.class, (Consumer<BiColor.Blue>) b -> System.out.println("blue subclass: " + b)
        );

        /* 2 */
        TriColor triColor = new TriColor.Blue();

        matches(triColor,
                TriColor.Red.class,  r -> System.out.println("red subclass:  " + r),
                TriColor.Blue.class, b -> System.out.println("blue subclass: " + b),
                TriColor.White.class, (Consumer<TriColor.White>) w -> System.out.println("white subclass: " + w)
        );

        /* 3 */
        QuarColor quarColor = new QuarColor.Black();

        matches(quarColor,
                QuarColor.Red.class,  r -> System.out.println("red subclass:  " + r),
                QuarColor.Blue.class, b -> System.out.println("blue subclass: " + b),
                QuarColor.White.class, w -> System.out.println("white subclass: " + w),
                QuarColor.Black.class, (Consumer<QuarColor.Black>) b -> System.out.println("black subclass: " + b)
        );

        /* 4 */
        QuinColor quinColor = new QuinColor.Green();

        matches(quinColor,
                QuinColor.Red.class,  r -> System.out.println("red subclass:  " + r),
                QuinColor.Blue.class, b -> System.out.println("blue subclass: " + b),
                QuinColor.White.class, w -> System.out.println("white subclass: " + w),
                QuinColor.Black.class, b -> System.out.println("black subclass: " + b),
                QuinColor.Green.class, (Consumer<QuinColor.Green>) g -> System.out.println("green subclass: " + g)
        );

        /* 5 */
        SexColor sexColor = new SexColor.Yellow();

        matches(sexColor,
                SexColor.Red.class,  r -> System.out.println("red subclass:  " + r),
                SexColor.Blue.class, b -> System.out.println("blue subclass: " + b),
                SexColor.White.class, w -> System.out.println("white subclass: " + w),
                SexColor.Black.class, b -> System.out.println("black subclass: " + b),
                SexColor.Green.class, g -> System.out.println("green subclass: " + g),
                SexColor.Yellow.class, (Consumer<SexColor.Yellow>) y -> System.out.println("yellow subclass: " + y)
        );
    }

    @Test
    public void matchesAsSealedStatementTest() {
        /* 1 */
        BiColor biColor = new BiColor.Red();

        matches(biColor).as(
                BiColor.Red.class,  r -> System.out.println("red subclass:  " + r),
                BiColor.Blue.class, (Purchaser<BiColor.Blue>) b -> System.out.println("blue subclass: " + b)
        );

        /* 2 */
        TriColor triColor = new TriColor.Blue();

        matches(triColor).as(
                TriColor.Red.class,  r -> System.out.println("red subclass:  " + r),
                TriColor.Blue.class, b -> System.out.println("blue subclass: " + b),
                TriColor.White.class, (Purchaser<TriColor.White>) w -> System.out.println("white subclass: " + w)
        );

        /* 3 */
        QuarColor quarColor = new QuarColor.Black();

        matches(quarColor).as(
                QuarColor.Red.class,  r -> System.out.println("red subclass:  " + r),
                QuarColor.Blue.class, b -> System.out.println("blue subclass: " + b),
                QuarColor.White.class, w -> System.out.println("white subclass: " + w),
                QuarColor.Black.class, (Purchaser<QuarColor.Black>) b -> System.out.println("black subclass: " + b)
        );

        /* 4 */
        QuinColor quinColor = new QuinColor.Green();

        matches(quinColor).as(
                QuinColor.Red.class,  r -> System.out.println("red subclass:  " + r),
                QuinColor.Blue.class, b -> System.out.println("blue subclass: " + b),
                QuinColor.White.class, w -> System.out.println("white subclass: " + w),
                QuinColor.Black.class, b -> System.out.println("black subclass: " + b),
                QuinColor.Green.class, (Purchaser<QuinColor.Green>) g -> System.out.println("green subclass: " + g)
        );

        /* 5 */
        SexColor sexColor = new SexColor.Yellow();

        matches(sexColor).as(
                SexColor.Red.class,  r -> System.out.println("red subclass:  " + r),
                SexColor.Blue.class, b -> System.out.println("blue subclass: " + b),
                SexColor.White.class, w -> System.out.println("white subclass: " + w),
                SexColor.Black.class, b -> System.out.println("black subclass: " + b),
                SexColor.Green.class, g -> System.out.println("green subclass: " + g),
                SexColor.Yellow.class, (Purchaser<SexColor.Yellow>) y -> System.out.println("yellow subclass: " + y)
        );
    }

    @Test
    public void matchesSealedExpressionTest() {
        /* 1 */
        BiColor biColor = new BiColor.Red();

        int result1 = matches(biColor,
                BiColor.Red.class,  r -> 0x1,
                BiColor.Blue.class, b -> 0x2
        );

        assertEquals(result1, 0x1);

        /* 2 */
        TriColor triColor = new TriColor.Blue();

        int result2 = matches(triColor,
                TriColor.Red.class,  r -> 0x1,
                TriColor.Blue.class, b -> 0x2,
                TriColor.White.class, w -> 0x3
        );

        assertEquals(result2, 0x2);

        /* 3 */
        QuarColor quarColor = new QuarColor.White();

        int result3 = matches(quarColor,
                QuarColor.Red.class,  r -> 0x1,
                QuarColor.Blue.class, b -> 0x2,
                QuarColor.White.class, w -> 0x3,
                QuarColor.Black.class, b -> 0x4
        );

        assertEquals(result3, 0x3);

        /* 4 */
        QuinColor quinColor = new QuinColor.Green();

        int result4 = matches(quinColor,
                QuinColor.Red.class,  r -> 0x1,
                QuinColor.Blue.class, b -> 0x2,
                QuinColor.White.class, w -> 0x3,
                QuinColor.Black.class, b -> 0x4,
                QuinColor.Green.class, g -> 0x5
        );

        assertEquals(result4, 0x5);

        /* 5 */
        SexColor sexColor = new SexColor.Yellow();

        int result5 = matches(sexColor,
                SexColor.Red.class,  r -> 0x1,
                SexColor.Blue.class, b -> 0x2,
                SexColor.White.class, w -> 0x3,
                SexColor.Black.class, b -> 0x4,
                SexColor.Green.class, g -> 0x5,
                SexColor.Yellow.class, y -> 0x6
        );

        assertEquals(result5, 0x6);
    }

    @Test
    public void matchesAsSealedExpressionTest() {
        /* 1 */
        BiColor biColor = new BiColor.Red();

        int result1 = matches(biColor).as(
                BiColor.Red.class,  r -> 0x1,
                BiColor.Blue.class, (Routine<BiColor.Blue, Integer>) b -> 0x2
        );

        assertEquals(result1, 0x1);

        /* 2 */
        TriColor triColor = new TriColor.Blue();

        int result2 = matches(triColor).as(
                TriColor.Red.class,  r -> 0x1,
                TriColor.Blue.class, b -> 0x2,
                TriColor.White.class, (Routine<TriColor.White, Integer>) w -> 0x3
        );

        assertEquals(result2, 0x2);

        /* 3 */
        QuarColor quarColor = new QuarColor.White();

        int result3 = matches(quarColor).as(
                QuarColor.Red.class,  r -> 0x1,
                QuarColor.Blue.class, b -> 0x2,
                QuarColor.White.class, w -> 0x3,
                QuarColor.Black.class, (Routine<QuarColor.Black, Integer>) b -> 0x4
        );

        assertEquals(result3, 0x3);

        /* 4 */
        QuinColor quinColor = new QuinColor.Green();

        int result4 = matches(quinColor).as(
                QuinColor.Red.class,  r -> 0x1,
                QuinColor.Blue.class, b -> 0x2,
                QuinColor.White.class, w -> 0x3,
                QuinColor.Black.class, b -> 0x4,
                QuinColor.Green.class, (Routine<QuinColor.Green, Integer>) g -> 0x5
        );

        assertEquals(result4, 0x5);

        /* 5 */
        SexColor sexColor = new SexColor.Yellow();

        int result5 = matches(sexColor).as(
                SexColor.Red.class,  r -> 0x1,
                SexColor.Blue.class, b -> 0x2,
                SexColor.White.class, w -> 0x3,
                SexColor.Black.class, b -> 0x4,
                SexColor.Green.class, g -> 0x5,
                SexColor.Yellow.class, (Routine<SexColor.Yellow, Integer>) y -> 0x6
        );

        assertEquals(result5, 0x6);
    }
}
