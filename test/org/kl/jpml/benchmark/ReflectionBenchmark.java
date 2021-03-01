/*
 * Licensed under the MIT License <http://opensource.org/licenses/MIT>.
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2019 - 2021 https://github.com/klappdev
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

import org.kl.jpml.reflect.Reflection;
import org.kl.jpml.test.color.BiColor;
import org.kl.jpml.test.color.TriColor;
import org.kl.jpml.test.shape.Rectangle;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

/*
    ReflectionBenchmark.getMethodHandleValueField            avgt    9  15,224 ±    0,715  ns/op
    ReflectionBenchmark.getPlainValueField                   avgt    9  7,739 ±     0,301  ns/op
    ReflectionBenchmark.getReflectiveValueField              avgt    9  16,910 ±    2,456  ns/op
    ReflectionBenchmark.getUnsafeValueField                  avgt    9  8,074 ±     0,176  ns/op

    ReflectionBenchmark.invokePlainConstructor               avgt    9  11,688 ±    0,136  ns/op
    ReflectionBenchmark.invokeReflectiveConstructor          avgt    9  77,381 ±    3,098  ns/op
    ReflectionBenchmark.invokeUnsafeConstructor              avgt    9  11,975 ±    0,445  ns/op

    ReflectionBenchmark.verifyCachedExhaustiveness           avgt    9  34,172 ±    0,774  ns/op
    ReflectionBenchmark.verifyReflectiveExhaustiveness       avgt    9  35,144 ±    0,899  ns/op
 */
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 3, time = 1)
@Fork(3)
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ReflectionBenchmark {
    private static MethodHandle rectangleMethodHandle;
    private Field rectangleField;
    private long offset;

    private Rectangle rectangle;
    private BiColor biColor;
    private TriColor triColor;

    @Setup
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        this.rectangle = new Rectangle();

        rectangleField = ReflectionBenchmark.class.getDeclaredField("rectangleField");
        rectangleMethodHandle = MethodHandles.lookup().unreflectGetter(rectangleField);

        biColor = new BiColor.Red();
        triColor = new TriColor.Blue();
    }

    @Benchmark
    public Object invokePlainConstructor() {
        return new Rectangle();
    }

    @Benchmark
    public Object invokeReflectiveConstructor() throws Throwable {
        return rectangle.getClass().getConstructor().newInstance();
    }

    @Benchmark
    public Object getPlainValueField() {
        return rectangle;
    }

    @Benchmark
    public Object getReflectiveValueField() throws IllegalAccessException {
        return rectangleField.get(this);
    }

    @Benchmark
    public Object getMethodHandleValueField() throws Throwable {
        return rectangleMethodHandle.invoke(this);
    }

    @Benchmark
    public int verifyReflectiveExhaustiveness() {
        Reflection.verifyExhaustiveness(biColor, new Class<?>[]{ BiColor.Red.class, BiColor.Blue.class });

        return 0x0;
    }

    @Benchmark
    public int verifyCachedExhaustiveness() {
        Reflection.verifyExhaustiveness(biColor, new Class<?>[]{ BiColor.Red.class, BiColor.Blue.class });

        return 0x1;
    }

    public static void main(String[] args) throws Exception {
        Main.main(args);
    }
}
