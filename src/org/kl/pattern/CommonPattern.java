package org.kl.pattern;

import org.kl.state.Else;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class CommonPattern {

    private CommonPattern() {}

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
        } else if (sixthCondition) {
            return sixthBranch.get();
        }

        return defaultBranch.get();
    }
}
