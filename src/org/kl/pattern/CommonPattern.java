package org.kl.pattern;

import org.kl.lambda.Provider;
import org.kl.state.Else;
import org.kl.util.Lazy;

import java.util.function.*;

public final class CommonPattern {
    private static CommonPattern instance;
    private static Object value;

    private CommonPattern() {}

    public static <T> CommonPattern self(T other) {
        value = other;

        if (instance == null) {
            instance = new CommonPattern();
        }

        return instance;
    }

    public static void run(Runnable block) {
        block.run();
    }

    public static <R> R run(Supplier<R> block) {
        return block.get();
    }

    public static void repeat(int times, Consumer<Integer> block) {
        for (int i = 0; i < times; i++) {
            block.accept(i);
        }
    }

    public static void repeat(int times, Runnable block) {
        for (int i = 0; i < times; i++) {
            block.run();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> void also(Consumer<T> block) {
        if (value != null) {
            block.accept((T) value);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T also(UnaryOperator<T> block) {
        if (value != null) {
            return block.apply((T) value);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> void let(Consumer<T> block) {
        if (value != null) {
            block.accept((T) value);
        }
    }

    @SuppressWarnings("unchecked")
    public <T, R> R let(Function<T, R> block) {
        if (value != null) {
            return block.apply((T) value);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T takeIf(Predicate<T> predicate) {
        if (predicate.test((T) value)) {
            return (T) value;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T takeUnless(Predicate<T> predicate) {
        if (!predicate.test((T) value)) {
            return (T) value;
        } else {
            return null;
        }
    }

    public static <T> T elvis(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static <T> T[] elvis(T[] value, T[] defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static String elvis(String value, String defaultValue) {
        return value == null || value.isEmpty() ? defaultValue : value;
    }

    public static <T> T elvis(T value, Supplier<? extends T> other) {
        return value == null ? other.get() : value;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] elvis(T[] value, Supplier<T[]> other) {
        return value == null ? other.get() : value;
    }

    public static String elvis(String value, Supplier<String> other) {
        return value == null || value.isEmpty() ? other.get() : value;
    }

    public static <T, X extends Throwable> T elvisThrow(T value, Provider<? extends X> other) throws X {
        if (value != null) {
            return value;
        } else {
            throw other.take();
        }
    }

    public static <T, X extends Throwable> T[] elvisThrow(T[] value, Provider<? extends X> other) throws X {
        if (value != null) {
            return value;
        } else {
            throw other.take();
        }
    }

    public static <X extends Throwable> String elvisThrow(String value, Provider<? extends X> other) throws X {
        if (value != null) {
            return value;
        } else {
            throw other.take();
        }
    }

    public static <T> Lazy<T> lazy(Supplier<T> supplier) {
		return new Lazy<>(supplier);
	}

	public static Lazy<Byte> lazy(byte value) {
        return new Lazy<>(() -> value);
    }

    public static Lazy<Short> lazy(short value) {
        return new Lazy<>(() -> value);
    }

    public static Lazy<Integer> lazy(int value) {
        return new Lazy<>(() -> value);
    }

    public static Lazy<Long> lazy(long value) {
        return new Lazy<>(() -> value);
    }

    public static Lazy<Float> lazy(float value) {
        return new Lazy<>(() -> value);
    }

    public static Lazy<Double> lazy(double value) {
        return new Lazy<>(() -> value);
    }

    public static Lazy<Character> lazy(char value) {
        return new Lazy<>(() -> value);
    }

    public static Lazy<Boolean> lazy(boolean value) {
        return new Lazy<>(() -> value);
    }

    public static Lazy<String> lazy(String value) {
        return new Lazy<>(() -> value);
    }

    @SuppressWarnings("unchecked")
    public <T> T apply(Consumer<T> block) {
        block.accept((T) value);

        return (T) value;
    }

    public static <T> T apply(T value, Consumer<T> block) {
        block.accept(value);

        return value;
    }

    public static <T> void with(T instance, Consumer<T> consumer) {
        consumer.accept(instance);
    }

    public static <T, R> R with(T instance, Function<T, R> function) {
        return function.apply(instance);
    }

    public static void when(boolean firstCondition,  Runnable firstBranch) {
        if (firstCondition) {
            firstBranch.run();
        }
    }

    @SuppressWarnings("unused")
    public static void when(boolean firstCondition, Runnable firstBranch,
                            Class<Else> defaultClass, Runnable defaultBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else {
            defaultBranch.run();
        }
    }

    public static <R> R when(boolean firstCondition, Supplier<R> firstBranch) {
        if (firstCondition) {
            return firstBranch.get();
        }
        
        return null;
    }

    @SuppressWarnings("unused")
    public static <R> R when(boolean firstCondition, Supplier<R> firstBranch,
                             Class<Else> defaultClass, Supplier<R> defaultBranch) {
        if (firstCondition) {
            return firstBranch.get();
        }

        return defaultBranch.get();
    }

    public static void when(boolean firstCondition,  Runnable firstBranch,
                            boolean secondCondition, Runnable secondBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else if (secondCondition) {
            secondBranch.run();
        }
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static void when(boolean firstCondition, Runnable firstBranch,
                            boolean secondCondition, Runnable secondBranch,
                            Class<Else> defaultClass, Runnable defaultBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else if (secondCondition) {
            secondBranch.run();
        } else {
            defaultBranch.run();
        }
    }

    public static <R> R when(boolean firstCondition,  Supplier<R> firstBranch,
                             boolean secondCondition, Supplier<R> secondBranch) {
        if (firstCondition) {
            return firstBranch.get();
        } else if (secondCondition) {
            return secondBranch.get();
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <R> R when(boolean firstCondition, Supplier<R> firstBranch,
                             boolean secondCondition, Supplier<R> secondBranch,
                             Class<Else> defaultClass, Supplier<R> defaultBranch) {
        if (firstCondition) {
            return firstBranch.get();
        } else if (secondCondition) {
            return secondBranch.get();
        }

        return defaultBranch.get();
    }

    @SuppressWarnings("Duplicates")
    public static void when(boolean firstCondition,  Runnable firstBranch,
                            boolean secondCondition, Runnable secondBranch,
                            boolean thirdCondition,  Runnable thirdBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else if (secondCondition) {
            secondBranch.run();
        } else if (thirdCondition) {
            thirdBranch.run();
        }
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static void when(boolean firstCondition, Runnable firstBranch,
                            boolean secondCondition, Runnable secondBranch,
                            boolean thirdCondition, Runnable thirdBranch,
                            Class<Else> defaultClass, Runnable defaultBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else if (secondCondition) {
            secondBranch.run();
        } else if (thirdCondition) {
            thirdBranch.run();
        } else {
            defaultBranch.run();
        }
    }

    @SuppressWarnings("Duplicates")
    public static <R> R when(boolean firstCondition,  Supplier<R> firstBranch,
                             boolean secondCondition, Supplier<R> secondBranch,
                             boolean thirdCondition,  Supplier<R> thirdBranch) {
        if (firstCondition) {
            return firstBranch.get();
        } else if (secondCondition) {
            return secondBranch.get();
        } else if (thirdCondition) {
            return thirdBranch.get();
        }

        return null;
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <R> R when(boolean firstCondition, Supplier<R> firstBranch,
                             boolean secondCondition, Supplier<R> secondBranch,
                             boolean thirdCondition, Supplier<R> thirdBranch,
                             Class<Else> defaultClass, Supplier<R> defaultBranch) {
        if (firstCondition) {
            return firstBranch.get();
        } else if (secondCondition) {
            return secondBranch.get();
        } else if (thirdCondition) {
            return thirdBranch.get();
        }

        return defaultBranch.get();
    }

    @SuppressWarnings("Duplicates")
    public static void when(boolean firstCondition,  Runnable firstBranch,
                            boolean secondCondition, Runnable secondBranch,
                            boolean thirdCondition,  Runnable thirdBranch,
                            boolean fourthCondition, Runnable fourthBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else if (secondCondition) {
            secondBranch.run();
        } else if (thirdCondition) {
            thirdBranch.run();
        } else if (fourthCondition) {
            fourthBranch.run();
        }
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static void when(boolean firstCondition, Runnable firstBranch,
                            boolean secondCondition, Runnable secondBranch,
                            boolean thirdCondition, Runnable thirdBranch,
                            boolean fourthCondition, Runnable fourthBranch,
                            Class<Else> defaultClass, Runnable defaultBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else if (secondCondition) {
            secondBranch.run();
        } else if (thirdCondition) {
            thirdBranch.run();
        } else if (fourthCondition) {
            fourthBranch.run();
        } else {
            defaultBranch.run();
        }
    }

    @SuppressWarnings("Duplicates")
    public static <R> R when(boolean firstCondition,  Supplier<R> firstBranch,
                             boolean secondCondition, Supplier<R> secondBranch,
                             boolean thirdCondition,  Supplier<R> thirdBranch,
                             boolean fourthCondition, Supplier<R> fourthBranch) {
        if (firstCondition) {
            return firstBranch.get();
        } else if (secondCondition) {
            return secondBranch.get();
        } else if (thirdCondition) {
            return thirdBranch.get();
        } else if (fourthCondition) {
            return fourthBranch.get();
        }

        return null;
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <R> R when(boolean firstCondition, Supplier<R> firstBranch,
                             boolean secondCondition, Supplier<R> secondBranch,
                             boolean thirdCondition, Supplier<R> thirdBranch,
                             boolean fourthCondition, Supplier<R> fourthBranch,
                             Class<Else> defaultClass, Supplier<R> defaultBranch) {
        if (firstCondition) {
            return firstBranch.get();
        } else if (secondCondition) {
            return secondBranch.get();
        } else if (thirdCondition) {
            return thirdBranch.get();
        } else if (fourthCondition) {
            return fourthBranch.get();
        }

        return defaultBranch.get();
    }

    @SuppressWarnings("Duplicates")
    public static void when(boolean firstCondition,  Runnable firstBranch,
                            boolean secondCondition, Runnable secondBranch,
                            boolean thirdCondition,  Runnable thirdBranch,
                            boolean fourthCondition, Runnable fourthBranch,
                            boolean fifthCondition,  Runnable fifthBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else if (secondCondition) {
            secondBranch.run();
        } else if (thirdCondition) {
            thirdBranch.run();
        } else if (fourthCondition) {
            fourthBranch.run();
        } else if (fifthCondition) {
            fifthBranch.run();
        }
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static void when(boolean firstCondition, Runnable firstBranch,
                            boolean secondCondition, Runnable secondBranch,
                            boolean thirdCondition, Runnable thirdBranch,
                            boolean fourthCondition, Runnable fourthBranch,
                            boolean fifthCondition, Runnable fifthBranch,
                            Class<Else> defaultClass, Runnable defaultBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else if (secondCondition) {
            secondBranch.run();
        } else if (thirdCondition) {
            thirdBranch.run();
        } else if (fourthCondition) {
            fourthBranch.run();
        } else if (fifthCondition) {
            fifthBranch.run();
        } else {
            defaultBranch.run();
        }
    }

    @SuppressWarnings("Duplicates")
    public static <R> R when(boolean firstCondition,  Supplier<R> firstBranch,
                             boolean secondCondition, Supplier<R> secondBranch,
                             boolean thirdCondition,  Supplier<R> thirdBranch,
                             boolean fourthCondition, Supplier<R> fourthBranch,
                             boolean fifthCondition,  Supplier<R> fifthBranch) {
        if (firstCondition) {
            return firstBranch.get();
        } else if (secondCondition) {
            return secondBranch.get();
        } else if (thirdCondition) {
            return thirdBranch.get();
        } else if (fourthCondition) {
            return fourthBranch.get();
        } else if (fifthCondition) {
            return fifthBranch.get();
        }

        return null;
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <R> R when(boolean firstCondition, Supplier<R> firstBranch,
                             boolean secondCondition, Supplier<R> secondBranch,
                             boolean thirdCondition, Supplier<R> thirdBranch,
                             boolean fourthCondition, Supplier<R> fourthBranch,
                             boolean fifthCondition, Supplier<R> fifthBranch,
                             Class<Else> defaultClass, Supplier<R> defaultBranch) {
        if (firstCondition) {
            return firstBranch.get();
        } else if (secondCondition) {
            return secondBranch.get();
        } else if (thirdCondition) {
            return thirdBranch.get();
        } else if (fourthCondition) {
            return fourthBranch.get();
        } else if (fifthCondition) {
            return fifthBranch.get();
        }

        return defaultBranch.get();
    }


    @SuppressWarnings("Duplicates")
    public static void when(boolean firstCondition,  Runnable firstBranch,
                            boolean secondCondition, Runnable secondBranch,
                            boolean thirdCondition,  Runnable thirdBranch,
                            boolean fourthCondition, Runnable fourthBranch,
                            boolean fifthCondition,  Runnable fifthBranch,
                            boolean sixthCondition,  Runnable sixthBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else if (secondCondition) {
            secondBranch.run();
        } else if (thirdCondition) {
            thirdBranch.run();
        } else if (fourthCondition) {
            fifthBranch.run();
        } else if (fifthCondition) {
            fourthBranch.run();
        } else if (sixthCondition) {
            sixthBranch.run();
        }
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static void when(boolean firstCondition, Runnable firstBranch,
                            boolean secondCondition, Runnable secondBranch,
                            boolean thirdCondition, Runnable thirdBranch,
                            boolean fourthCondition, Runnable fourthBranch,
                            boolean fifthCondition, Runnable fifthBranch,
                            boolean sixthCondition, Runnable sixthBranch,
                            Class<Else> defaultClass, Runnable defaultBranch) {
        if (firstCondition) {
            firstBranch.run();
        } else if (secondCondition) {
            secondBranch.run();
        } else if (thirdCondition) {
            thirdBranch.run();
        } else if (fourthCondition) {
            fifthBranch.run();
        } else if (fifthCondition) {
            fourthBranch.run();
        } else if (sixthCondition) {
            sixthBranch.run();
        } else {
            defaultBranch.run();
        }
    }

    @SuppressWarnings("Duplicates")
    public static <R> R when(boolean firstCondition,  Supplier<R> firstBranch,
                             boolean secondCondition, Supplier<R> secondBranch,
                             boolean thirdCondition,  Supplier<R> thirdBranch,
                             boolean fourthCondition, Supplier<R> fourthBranch,
                             boolean fifthCondition,  Supplier<R> fifthBranch,
                             boolean sixthCondition,  Supplier<R> sixthBranch) {
        if (firstCondition) {
            return firstBranch.get();
        } else if (secondCondition) {
            return secondBranch.get();
        } else if (thirdCondition) {
            return thirdBranch.get();
        } else if (fourthCondition) {
            return fourthBranch.get();
        } else if (fifthCondition) {
            return fifthBranch.get();
        } else if (sixthCondition) {
            return sixthBranch.get();
        }

        return null;
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <R> R when(boolean firstCondition, Supplier<R> firstBranch,
                             boolean secondCondition, Supplier<R> secondBranch,
                             boolean thirdCondition, Supplier<R> thirdBranch,
                             boolean fourthCondition, Supplier<R> fourthBranch,
                             boolean fifthCondition, Supplier<R> fifthBranch,
                             boolean sixthCondition, Supplier<R> sixthBranch,
                             Class<Else> defaultClass, Supplier<R> defaultBranch)  {
        if (firstCondition) {
            return firstBranch.get();
        } else if (secondCondition) {
            return secondBranch.get();
        } else if (thirdCondition) {
            return thirdBranch.get();
        } else if (fourthCondition) {
            return fourthBranch.get();
        } else if (fifthCondition) {
            return fifthBranch.get();
        } else if (sixthCondition) {
            return sixthBranch.get();
        }

        return defaultBranch.get();
    }

    /* noreturn */
    public static void TODO() {
        throw new UnsupportedOperationException();
    }

    /* noreturn */
    public static void TODO(String reason) {
        throw new UnsupportedOperationException("An operation is not implemented: " + reason);
    }
}
