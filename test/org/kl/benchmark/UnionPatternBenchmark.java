package org.kl.benchmark;

import org.kl.lambda.Routine;
import org.kl.test.color.*;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.kl.pattern.UnionPattern.matches;

/*
    UnionPatternBenchmark.matchesSealedBiExpressionPlain           avgt    9   5,992 ±  0,332  ns/op
    UnionPatternBenchmark.matchesSealedTriExpressionPlain          avgt    9   7,199 ±  0,356  ns/op
    UnionPatternBenchmark.matchesSealedQuarExpressionPlain         avgt    9   8,349 ±  1,789  ns/op
    UnionPatternBenchmark.matchesSealedQuinExpressionPlain         avgt    9   8,163 ±  0,541  ns/op
    UnionPatternBenchmark.matchesSealedSexExpressionPlain          avgt    9   8,746 ±  0,600  ns/op

    UnionPatternBenchmark.matchesSealedBiExpressionReflective      avgt    9  45,192 ± 11,951  ns/op
    UnionPatternBenchmark.matchesSealedTriExpressionReflective     avgt    9  43,413 ±  0,702  ns/op
    UnionPatternBenchmark.matchesSealedQuarExpressionReflective    avgt    9  59,127 ± 12,189  ns/op
    UnionPatternBenchmark.matchesSealedQuinExpressionReflective    avgt    9  57,653 ±  3,363  ns/op
    UnionPatternBenchmark.matchesSealedSexExpressionReflective     avgt    9  65,511 ±  4,214  ns/op

    UnionPatternBenchmark.matchesAsSealedBiExpressionReflective    avgt    9  51,458 ± 20,076  ns/op
    UnionPatternBenchmark.matchesAsSealedTriExpressionReflective   avgt    9  51,867 ± 10,918  ns/op
    UnionPatternBenchmark.matchesAsSealedQuarExpressionReflective  avgt    9  60,489 ± 11,864  ns/op
    UnionPatternBenchmark.matchesAsSealedQuinExpressionReflective  avgt    9  65,481 ±  6,173  ns/op
    UnionPatternBenchmark.matchesAsSealedSexExpressionReflective   avgt    9  69,764 ± 11,748  ns/op
 */
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 3, time = 1)
@Fork(3)
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class UnionPatternBenchmark {
    private BiColor biColor;
    private TriColor triColor;
    private QuarColor quarColor;
    private QuinColor quinColor;
    private SexColor sexColor;

    @Setup
    public void setup() {
        biColor = new BiColor.Red();
        triColor = new TriColor.White();
        quarColor = new QuarColor.White();
        quinColor = new QuinColor.Green();
        sexColor = new SexColor.Yellow();
    }

    @Benchmark
    public int matchesSealedBiExpressionPlain() {
        if (biColor instanceof BiColor.Red) {
            return 0x1;
        } else if (biColor instanceof BiColor.Blue) {
            return 0x2;
        }

        return 0x0;
    }

    @Benchmark
    public int matchesSealedTriExpressionPlain() {
        if (triColor instanceof TriColor.Red) {
            return 0x1;
        } else if (triColor instanceof TriColor.Blue) {
            return 0x2;
        } else if (triColor instanceof TriColor.White) {
            return 0x3;
        }

        return 0x0;
    }

    @Benchmark
    public int matchesSealedQuarExpressionPlain() {
        if (quarColor instanceof QuarColor.Red) {
            return 0x1;
        } else if (quarColor instanceof QuarColor.Blue) {
            return 0x2;
        } else if (quarColor instanceof QuarColor.White) {
            return 0x3;
        } else if (quarColor instanceof QuarColor.Black) {
            return 0x4;
        }

        return 0x0;
    }

    @Benchmark
    public int matchesSealedQuinExpressionPlain() {
        if (quinColor instanceof QuinColor.Red) {
            return 0x1;
        } else if (quinColor instanceof QuinColor.Blue) {
            return 0x2;
        } else if (quinColor instanceof QuinColor.White) {
            return 0x3;
        } else if (quinColor instanceof QuinColor.Black) {
            return 0x4;
        } else if (quinColor instanceof QuinColor.Green) {
            return 0x5;
        }

        return 0x0;
    }

    @Benchmark
    public int matchesSealedSexExpressionPlain() {
        if (sexColor instanceof SexColor.Red) {
            return 0x1;
        } else if (sexColor instanceof SexColor.Blue) {
            return 0x2;
        } else if (sexColor instanceof SexColor.White) {
            return 0x3;
        } else if (sexColor instanceof SexColor.Black) {
            return 0x4;
        } else if (sexColor instanceof SexColor.Green) {
            return 0x5;
        } else if (sexColor instanceof SexColor.Yellow) {
            return 0x6;
        }

        return 0x0;
    }

    @Benchmark
    public int matchesSealedBiExpressionReflective() {
        return matches(biColor,
                BiColor.Red.class,  r -> 0x1,
                BiColor.Blue.class, b -> 0x2
        );
    }

    @Benchmark
    public int matchesAsSealedBiExpressionReflective() {
        return matches(biColor).as(
                BiColor.Red.class,  r -> 0x1,
                BiColor.Blue.class, (Routine<BiColor.Blue, Integer>) b -> 0x2
        );
    }

    @Benchmark
    public int matchesSealedTriExpressionReflective() {
        return matches(triColor,
                TriColor.Red.class,  r -> 0x1,
                TriColor.Blue.class, b -> 0x2,
                TriColor.White.class, b -> 0x3
        );
    }

    @Benchmark
    public int matchesAsSealedTriExpressionReflective() {
        return matches(triColor).as(
                TriColor.Red.class,  r -> 0x1,
                TriColor.Blue.class, b -> 0x2,
                TriColor.White.class, (Routine<TriColor.White, Integer>) b -> 0x3
        );
    }

    @Benchmark
    public int matchesSealedQuarExpressionReflective() {
        return matches(quarColor,
                QuarColor.Red.class,  r -> 0x1,
                QuarColor.Blue.class, b -> 0x2,
                QuarColor.White.class, w -> 0x3,
                QuarColor.Black.class, b -> 0x4
        );
    }

    @Benchmark
    public int matchesAsSealedQuarExpressionReflective() {
        return matches(quarColor).as(
                QuarColor.Red.class,  r -> 0x1,
                QuarColor.Blue.class, b -> 0x2,
                QuarColor.White.class, w -> 0x3,
                QuarColor.Black.class, (Routine<QuarColor.Black, Integer>) b -> 0x4
        );
    }

    @Benchmark
    public int matchesSealedQuinExpressionReflective() {
        return matches(quinColor,
                QuinColor.Red.class,  r -> 0x1,
                QuinColor.Blue.class, b -> 0x2,
                QuinColor.White.class, w -> 0x3,
                QuinColor.Black.class, b -> 0x4,
                QuinColor.Green.class, g -> 0x5
        );
    }

    @Benchmark
    public int matchesAsSealedQuinExpressionReflective() {
        return matches(quinColor).as(
                QuinColor.Red.class,  r -> 0x1,
                QuinColor.Blue.class, b -> 0x2,
                QuinColor.White.class, w -> 0x3,
                QuinColor.Black.class, b -> 0x4,
                QuinColor.Green.class, (Routine<QuinColor.Green, Integer>) g -> 0x5
        );
    }

    @Benchmark
    public int matchesSealedSexExpressionReflective() {
        return matches(sexColor,
                SexColor.Red.class,  r -> 0x1,
                SexColor.Blue.class, b -> 0x2,
                SexColor.White.class, w -> 0x3,
                SexColor.Black.class, b -> 0x4,
                SexColor.Green.class, g -> 0x5,
                SexColor.Yellow.class, y -> 0x6
        );
    }

    @Benchmark
    public int matchesAsSealedSexExpressionReflective() {
        return matches(sexColor).as(
                SexColor.Red.class,  r -> 0x1,
                SexColor.Blue.class, b -> 0x2,
                SexColor.White.class, w -> 0x3,
                SexColor.Black.class, b -> 0x4,
                SexColor.Green.class, g -> 0x5,
                SexColor.Yellow.class, (Routine<SexColor.Yellow, Integer>) y -> 0x6
        );
    }
}
