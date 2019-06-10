package org.kl.pattern;

import org.kl.error.PatternException;
import org.kl.reflect.Reflection;
import org.kl.state.Else;
import org.kl.type.*;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class CommonPattern {

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

    /*package-private*/ static Object[] prepareParameters(Class<?>[] parameterTypes) throws PatternException {
        Object[] parameters = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isArray()) {
                throw new PatternException("Parameter extract method must not be array");
            }

            if (Reflection.isPrimitive(parameterTypes[i])) {
                throw new PatternException("Can not pass primitives or wrappers by reference.\n" +
                        "Use instead IntRef, FloatRef and etc.");
            }

            try {
                parameters[i] = parameterTypes[i].newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new PatternException("Can not resolveParameters parameters extract method: " + e.getMessage());
            }
        }

        return parameters;
    }

    /*package-private*/ static Object[] resolveParameters(Object[] args) {
        Object[] result = new Object[args.length];

        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ByteRef) {
                result[i] = ((ByteRef) args[i]).get();

            } else if (args[i] instanceof ShortRef) {
                result[i] = ((ShortRef) args[i]).get();

            } else  if (args[i] instanceof IntRef) {
                result[i] = ((IntRef) args[i]).get();

            } else if (args[i] instanceof LongRef) {
                result[i] = ((LongRef) args[i]).get();

            } else if (args[i] instanceof FloatRef) {
                result[i] = ((FloatRef) args[i]).get();

            } else if (args[i] instanceof DoubleRef) {
                result[i] = ((DoubleRef) args[i]).get();

            } else if (args[i] instanceof CharRef) {
                result[i] = ((CharRef) args[i]).get();

            } else if (args[i] instanceof BooleanRef) {
                result[i] = ((BooleanRef) args[i]).get();

            } else {
                result[i] = args[i];
            }
        }

        return result;
    }
}
