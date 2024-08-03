/*
 * Licensed under the MIT License <http://opensource.org/licenses/MIT>.
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2019 - 2024 https://github.com/klappdev
 *
 * Permission is hereby  granted, free of charge, to any  person obtaining a copy
 * of this software and associated  documentation files (the "Software"), to deal
 * in the Software  without restriction, including without  limitation the rights
 * to  use, copy,  modify, merge,  publish, distribute,  sublicense, and/or  sell
 * copies  of  the Software,  and  to  permit persons  to  whom  the Software  is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE  IS PROVIDED "AS  IS", WITHOUT WARRANTY  OF ANY KIND,  EXPRESS OR
 * IMPLIED,  INCLUDING BUT  NOT  LIMITED TO  THE  WARRANTIES OF  MERCHANTABILITY,
 * FITNESS FOR  A PARTICULAR PURPOSE AND  NONINFRINGEMENT. IN NO EVENT  SHALL THE
 * AUTHORS  OR COPYRIGHT  HOLDERS  BE  LIABLE FOR  ANY  CLAIM,  DAMAGES OR  OTHER
 * LIABILITY, WHETHER IN AN ACTION OF  CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE  OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.kl.jpml.benchmark;

import org.kl.jpml.lambda.Action;
import org.kl.jpml.test.color.*;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.kl.jpml.pattern.ExhaustivePattern.match;

/*
    UnionPatternBenchmark.matchSealedBiExpressionPlain           avgt    9   5,992 ±  0,332  ns/op
    UnionPatternBenchmark.matchSealedTriExpressionPlain          avgt    9   7,199 ±  0,356  ns/op
    UnionPatternBenchmark.matchSealedQuarExpressionPlain         avgt    9   8,349 ±  1,789  ns/op
    UnionPatternBenchmark.matchSealedQuinExpressionPlain         avgt    9   8,163 ±  0,541  ns/op
    UnionPatternBenchmark.matchSealedSexExpressionPlain          avgt    9   8,746 ±  0,600  ns/op

    UnionPatternBenchmark.matchSealedBiExpressionReflective      avgt    9  45,192 ± 11,951  ns/op
    UnionPatternBenchmark.matchSealedTriExpressionReflective     avgt    9  43,413 ±  0,702  ns/op
    UnionPatternBenchmark.matchSealedQuarExpressionReflective    avgt    9  59,127 ± 12,189  ns/op
    UnionPatternBenchmark.matchSealedQuinExpressionReflective    avgt    9  57,653 ±  3,363  ns/op
    UnionPatternBenchmark.matchSealedSexExpressionReflective     avgt    9  65,511 ±  4,214  ns/op

    UnionPatternBenchmark.matchAsSealedBiExpressionReflective    avgt    9  51,458 ± 20,076  ns/op
    UnionPatternBenchmark.matchAsSealedTriExpressionReflective   avgt    9  51,867 ± 10,918  ns/op
    UnionPatternBenchmark.matchAsSealedQuarExpressionReflective  avgt    9  60,489 ± 11,864  ns/op
    UnionPatternBenchmark.matchAsSealedQuinExpressionReflective  avgt    9  65,481 ±  6,173  ns/op
    UnionPatternBenchmark.matchAsSealedSexExpressionReflective   avgt    9  69,764 ± 11,748  ns/op
 */
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 3, time = 1)
@Fork(3)
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ExhaustivePatternBenchmark {
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
    public int matchSealedBiExpressionPlain() {
        if (biColor instanceof BiColor.Red) {
            return 0x1;
        } else if (biColor instanceof BiColor.Blue) {
            return 0x2;
        }

        return 0x0;
    }

    @Benchmark
    public int matchSealedTriExpressionPlain() {
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
    public int matchSealedQuarExpressionPlain() {
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
    public int matchSealedQuinExpressionPlain() {
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
    public int matchSealedSexExpressionPlain() {
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
    public int matchSealedBiExpressionReflective() {
        return match(biColor,
                BiColor.Red.class,  r -> 0x1,
                BiColor.Blue.class, b -> 0x2
        );
    }

    @Benchmark
    public int matchAsSealedBiExpressionReflective() {
        return match(biColor).as(
                BiColor.Red.class,  r -> 0x1,
                BiColor.Blue.class, (Action<BiColor.Blue, Integer>) b -> 0x2
        );
    }

    @Benchmark
    public int matchSealedTriExpressionReflective() {
        return match(triColor,
                TriColor.Red.class,  r -> 0x1,
                TriColor.Blue.class, b -> 0x2,
                TriColor.White.class, b -> 0x3
        );
    }

    @Benchmark
    public int matchAsSealedTriExpressionReflective() {
        return match(triColor).as(
                TriColor.Red.class,  r -> 0x1,
                TriColor.Blue.class, b -> 0x2,
                TriColor.White.class, (Action<TriColor.White, Integer>) b -> 0x3
        );
    }

    @Benchmark
    public int matchSealedQuarExpressionReflective() {
        return match(quarColor,
                QuarColor.Red.class,  r -> 0x1,
                QuarColor.Blue.class, b -> 0x2,
                QuarColor.White.class, w -> 0x3,
                QuarColor.Black.class, b -> 0x4
        );
    }

    @Benchmark
    public int matchAsSealedQuarExpressionReflective() {
        return match(quarColor).as(
                QuarColor.Red.class,  r -> 0x1,
                QuarColor.Blue.class, b -> 0x2,
                QuarColor.White.class, w -> 0x3,
                QuarColor.Black.class, (Action<QuarColor.Black, Integer>) b -> 0x4
        );
    }

    @Benchmark
    public int matchSealedQuinExpressionReflective() {
        return match(quinColor,
                QuinColor.Red.class,  r -> 0x1,
                QuinColor.Blue.class, b -> 0x2,
                QuinColor.White.class, w -> 0x3,
                QuinColor.Black.class, b -> 0x4,
                QuinColor.Green.class, g -> 0x5
        );
    }

    @Benchmark
    public int matchAsSealedQuinExpressionReflective() {
        return match(quinColor).as(
                QuinColor.Red.class,  r -> 0x1,
                QuinColor.Blue.class, b -> 0x2,
                QuinColor.White.class, w -> 0x3,
                QuinColor.Black.class, b -> 0x4,
                QuinColor.Green.class, (Action<QuinColor.Green, Integer>) g -> 0x5
        );
    }

    @Benchmark
    public int matchSealedSexExpressionReflective() {
        return match(sexColor,
                SexColor.Red.class,  r -> 0x1,
                SexColor.Blue.class, b -> 0x2,
                SexColor.White.class, w -> 0x3,
                SexColor.Black.class, b -> 0x4,
                SexColor.Green.class, g -> 0x5,
                SexColor.Yellow.class, y -> 0x6
        );
    }

    @Benchmark
    public int matchAsSealedSexExpressionReflective() {
        return match(sexColor).as(
                SexColor.Red.class,  r -> 0x1,
                SexColor.Blue.class, b -> 0x2,
                SexColor.White.class, w -> 0x3,
                SexColor.Black.class, b -> 0x4,
                SexColor.Green.class, g -> 0x5,
                SexColor.Yellow.class, (Action<SexColor.Yellow, Integer>) y -> 0x6
        );
    }

    public static void main(String[] args) throws Exception {
        Main.main(args);
    }
}
