package org.kl.benchmark;

import org.kl.reflect.Reflection;
import org.kl.test.color.BiColor;
import org.kl.test.color.TriColor;
import org.kl.test.shape.Rectangle;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import sun.misc.Unsafe;

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
    private static final Unsafe UNSAFE = Reflection.getUnsafe();

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

        if (UNSAFE != null) {
            offset = UNSAFE.objectFieldOffset(rectangleField);
        }

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
    public Object invokeUnsafeConstructor() throws Throwable {
        return UNSAFE != null ? UNSAFE.allocateInstance(Rectangle.class) : null;
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
    public Object getUnsafeValueField() {
        return UNSAFE.getObject(this, offset);
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
