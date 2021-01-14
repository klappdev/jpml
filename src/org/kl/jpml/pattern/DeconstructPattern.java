/*
 * JPML - Java pattern matching library.
 *
 * Deconstruction pattern allow match type and deconstruct
 * object at the parts. Maximum number of branches for match
 * three with three deconstruction params.
 */
package org.kl.jpml.pattern;

import org.kl.jpml.lambda.*;
import org.kl.jpml.reflect.Reflection;
import org.kl.jpml.state.Else;
import org.kl.jpml.state.Null;
import org.kl.jpml.state.Var;

import java.util.Collection;
import java.util.Map;
import java.util.function.*;

public final class DeconstructPattern {

    private DeconstructPattern() {}

    public static <V, T>
    void foreach(Collection<V> data, Consumer<T> branch)  {
        for (V value : data) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            branch.accept((T) args[0]);
        }
    }

    public static <V, T>
    void let(V data, Consumer<T> branch)  {
        Object[] args = Reflection.invokeExtractor(data, 1);

        branch.accept((T) args[0]);
    }

    public static <V, C, T>
    void matches(V value,
                 Class<C> clazz, Consumer<T> branch)  {
        if (clazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            branch.accept((T) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C, T>
    void matches(V value,
                 Class<C> clazz, Purchaser<T> branch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (clazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            branch.obtain((T) args[0]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C, T>
    void matches(V value,
                 Class<C> clazz, Purchaser<T> branch,
                 Class<Var> varClass, Purchaser<V> varBranch)  {
        if (clazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            branch.obtain((T) args[0]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C, T>
    void matches(V value,
                 Class<C> clazz, Purchaser<T> branch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, clazz, branch, elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C, T>
    void matches(V value,
                 Class<C> clazz, Purchaser<T> branch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, clazz, branch, varClass, varBranch);
        }
    }

    public static <V, C, T, R>
    R matches(V value,
              Class<C> clazz, Function<T, R> branch)  {
        if (clazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            return branch.apply((T) args[0]);
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V, C, T, R>
    R matches(V value,
              Class<C> clazz, Function<T, R> branch,
              Class<Else> elseClass, Supplier<R> elseBranch)  {
        R result = matches(value, clazz, branch);

        if (result != null) {
            return result;
        } else {
            return elseBranch.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V, C, T, R>
    R matches(V value,
              Class<C> clazz, Function<T, R> branch,
              Class<Var> varClass, Routine<V, R> varBranch)  {
        R result = matches(value, clazz, branch);

        if (result != null) {
            return result;
        } else {
            return varBranch.hold(value);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C, T, R>
    R matches(V value,
              Class<C> clazz, Function<T, R> branch,
              Class<Null> nullClass, Supplier<R> nullBranch,
              Class<Else> elseClass, Supplier<R> elseBranch)  {
        if (value == null) {
            return nullBranch.get();
        } else {
            return matches(value, clazz, branch, elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C, T, R>
    R matches(V value,
              Class<C> clazz, Function<T, R> branch,
              Class<Null> nullClass, Supplier<R> nullBranch,
              Class<Var> varClass,   Routine<V, R> varBranch)  {
        if (value == null) {
            return nullBranch.get();
        } else {
            return matches(value, clazz, branch, varClass, varBranch);
        }
    }

    public static <V, T1, T2>
    void foreach(Collection<V> data, BiConsumer<T1, T2> branch)  {
        for (V value : data) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            branch.accept((T1) args[0], (T2) args[1]);
        }
    }

    public static <K, V, T1, T2>
    void foreach(Map<K, V> data, BiConsumer<T1, T2> branch) {
        for (Map.Entry<K, V> entry : data.entrySet()) {
            branch.accept((T1) entry.getKey(), (T2) entry.getValue());
        }
    }

    public static <V, T1, T2>
    void let(V data, BiConsumer<T1, T2> branch)  {
        Object[] args = Reflection.invokeExtractor(data, 2);

        branch.accept((T1) args[0], (T2) args[1]);
    }

    public static <V, C, T1, T2>
    void matches(V value,
                 Class<C> clazz, BiConsumer<T1, T2> branch)  {
        if (clazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            branch.accept((T1) args[0], (T2) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C, T1, T2>
    void matches(V value,
                 Class<C> clazz, BiPurchaser<T1, T2> branch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (clazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            branch.obtain((T1) args[0], (T2) args[1]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C, T1, T2>
    void matches(V value,
                 Class<C> clazz, BiPurchaser<T1, T2> branch,
                 Class<Var> varClass, Purchaser<V> varBranch)  {
        if (clazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            branch.obtain((T1) args[0], (T2) args[0]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C, T1, T2>
    void matches(V value,
                 Class<C> clazz, BiPurchaser<T1, T2> branch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, clazz, branch, elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C, T1, T2>
    void matches(V value,
                 Class<C> clazz, BiPurchaser<T1, T2> branch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, clazz, branch, varClass, varBranch);
        }
    }

    public static <V, C, T1, T2, R>
    R matches(V value,
              Class<C> clazz, BiFunction<T1, T2, R> branch)  {
        if (clazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            return branch.apply((T1) args[0], (T2) args[0]);
        }

        return null;
    }

    public static <V, T1, T2, T3>
    void foreach(Collection<V> data, TriConsumer<T1, T2, T3> branch)  {
        for (V value : data) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            branch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        }
    }

    public static <V, T1, T2, T3>
    void let(V data, TriConsumer<T1, T2, T3> branch)  {
        Object[] args = Reflection.invokeExtractor(data, 3);

        branch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
    }

    public static <V, C, T1, T2, T3>
    void matches(V value,
                 Class<C> clazz, TriConsumer<T1, T2, T3> branch)  {
        if (clazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            branch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C, T1, T2, T3>
    void matches(V value,
                 Class<C> clazz, TriPurchaser<T1, T2, T3> branch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (clazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            branch.obtain((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C, T1, T2, T3>
    void matches(V value,
                 Class<C> clazz, TriPurchaser<T1, T2, T3> branch,
                 Class<Var> varClass, Purchaser<V> varBranch)  {
        if (clazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            branch.obtain((T1) args[0], (T2) args[0], (T3) args[2]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C, T1, T2, T3>
    void matches(V value,
                 Class<C> clazz, TriPurchaser<T1, T2, T3> branch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, clazz, branch, elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C, T1, T2, T3>
    void matches(V value,
                 Class<C> clazz, TriPurchaser<T1, T2, T3> branch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, clazz, branch, varClass, varBranch);
        }
    }

    public static <V, C, T1, T2, T3, R>
    R matches(V value,
              Class<C> clazz, TriFunction<T1, T2, T3, R> branch)  {
        if (clazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            return branch.apply((T1) args[0], (T2) args[0], (T3) args[2]);
        }

        return null;
    }

    public static <V, C1, C2, T1, T2>
    void matches(V value,
                 Class<C1> firstClazz,  Consumer<T1> firstBranch,
                 Class<C2> secondClazz, Consumer<T2> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T2) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, T1, T2>
    void matches(V value,
                 Class<C1> firstClazz, Consumer<T1> firstBranch,
                 Class<C2> secondClazz, Consumer<T2> secondBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T2) args[0]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2>
    void matches(V value,
                 Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                 Class<C2> secondClazz, Purchaser<T2> secondBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.obtain((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.obtain((T2) args[0]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2>
    void matches(V value,
                 Class<C1> firstClazz,  Consumer<T1> firstBranch,
                 Class<C2> secondClazz, Consumer<T2> secondBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2>
    void matches(V value,
                 Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                 Class<C2> secondClazz, Purchaser<T2> secondBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, T1, C2, T2, R>
    R matches(V value,
              Class<C1> firstClazz,  Function<T1, R> firstBranch,
              Class<C2> secondClazz, Function<T2, R> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            return firstBranch.apply((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            return secondBranch.apply((T2) args[0]);
        }

        return null;
    }

    public static <V, C1, C2, T1, T2, T3>
    void matches(V value,
                 Class<C1> firstClazz,  Consumer<T1> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T2, T3> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T2) args[0], (T3) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, T1, T2, T3>
    void matches(V value,
                 Class<C1> firstClazz, Consumer<T1> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T2, T3> secondBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T2) args[0], (T3) args[1]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3>
    void matches(V value,
                 Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                 Class<C2> secondClazz, BiPurchaser<T2, T3> secondBranch,
                 Class<Var> varClass, Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.obtain((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.obtain((T2) args[0], (T3) args[1]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3>
    void matches(V value,
                 Class<C1> firstClazz, Consumer<T1> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T2, T3> secondBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3>
    void matches(V value,
                 Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                 Class<C2> secondClazz, BiPurchaser<T2, T3> secondBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, T1, C2, T2, T3, R>
    R matches(V value,
              Class<C1> firstClazz,  Function<T1, R> firstBranch,
              Class<C2> secondClazz, BiFunction<T2, T3, R> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            return firstBranch.apply((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            return secondBranch.apply((T2) args[0], (T3) args[1]);
        }

        return null;
    }

    public static <V, C1, C2, T1, T2, T3>
    void matches(V value,
                 Class<C1> firstClazz,  BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, Consumer<T3> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T3) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, T1, T2, T3>
    void matches(V value,
                 Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, Consumer<T3> secondBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T3) args[0]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3>
    void matches(V value,
                 Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                 Class<C2> secondClazz, Purchaser<T3> secondBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.obtain((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.obtain((T3) args[0]);
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3>
    void matches(V value,
                 Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, Consumer<T3> secondBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3>
    void matches(V value,
                 Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                 Class<C2> secondClazz, Purchaser<T3> secondBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, T1, C2, T2, T3, R>
    R matches(V value,
              Class<C1> firstClazz,  BiFunction<T1, T2, R> firstBranch,
              Class<C2> secondClazz, Function<T3, R> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            return firstBranch.apply((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            return secondBranch.apply((T3) args[0]);
        }

        return null;
    }

    public static <V, C1, C2, T1, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz,  BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T3, T4> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T3) args[0], (T4) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, T1, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T3, T4> secondBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T3) args[0], (T4) args[1]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                 Class<C2> secondClazz, BiPurchaser<T3, T4> secondBranch,
                 Class<Var> varClass, Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.obtain((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.obtain((T3) args[0], (T4) args[1]);
            return;
        }

        varBranch.obtain(value);
    }


    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T3, T4> secondBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                 Class<C2> secondClazz, BiPurchaser<T3, T4> secondBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, T1, C2, T2, T3, T4, R>
    R matches(V value,
              Class<C1> firstClazz,  BiFunction<T1, T2, R> firstBranch,
              Class<C2> secondClazz, BiFunction<T3, T4, R> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            return firstBranch.apply((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            return secondBranch.apply((T3) args[0], (T4) args[1]);
        }

        return null;
    }

    public static <V, C1, C2, T1, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz,  Consumer<T1> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T2, T3, T4> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T2) args[0], (T3) args[1], (T4) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, T1, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz,  Consumer<T1> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T2, T3, T4> secondBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T2) args[0], (T3) args[1], (T4) args[2]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T2, T3, T4> secondBranch,
                 Class<Var> varClass, Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.obtain((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.obtain((T2) args[0], (T3) args[1], (T4) args[2]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz, Consumer<T1> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T2, T3, T4> secondBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T2, T3, T4> secondBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, T1, C2, T2, T3, T4, R>
    R matches(V value,
              Class<C1> firstClazz,  Function<T1, R> firstBranch,
              Class<C2> secondClazz, TriFunction<T2, T3, T4, R> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            return firstBranch.apply((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            return secondBranch.apply((T2) args[0], (T3) args[1], (T4) args[2]);
        }

        return null;
    }

    public static <V, C1, C2, T1, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, Consumer<T4> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T4) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, T1, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz, TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, Consumer<T4> secondBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T4) args[0]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, Purchaser<T4> secondBranch,
                 Class<Var> varClass, Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.obtain((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.obtain((T4) args[0]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz, TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, Consumer<T4> secondBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3, T4>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, Purchaser<T4> secondBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, T1, C2, T2, T3, T4, R>
    R matches(V value,
              Class<C1> firstClazz,  TriFunction<T1, T2, T3, R> firstBranch,
              Class<C2> secondClazz, Function<T4, R> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            return firstBranch.apply((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            return secondBranch.apply((T4) args[0]);
        }

        return null;
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T4, T5, T6> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T4) args[0], (T5) args[1], (T6) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz, TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T4, T5, T6> secondBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T4) args[0], (T5) args[1], (T6) args[2]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T4, T5, T6> secondBranch,
                 Class<Var> varClass, Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.obtain((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.obtain((T4) args[0], (T5) args[1], (T6) args[2]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz, TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T4, T5, T6> secondBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3, T4, T5, T6>
    void matches(V value,
               Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
               Class<C2> secondClazz, TriPurchaser<T4, T5, T6> secondBranch,
               Class<Null> nullClass, Runnable nullBranch,
               Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, T1, C2, T2, T3, T4, T5, T6, R>
    R matches(V value,
              Class<C1> firstClazz,  TriFunction<T1, T2, T3, R> firstBranch,
              Class<C2> secondClazz, TriFunction<T4, T5, T6, R> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            return firstBranch.apply((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            return secondBranch.apply((T4) args[0], (T5) args[1], (T6) args[2]);
        }

        return null;
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz,  BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T3, T4, T5> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T3) args[0], (T4) args[1], (T5) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, T1, T2, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T3, T4, T5> secondBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T3) args[0], (T4) args[1], (T5) args[2]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T3, T4, T5> secondBranch,
                 Class<Var> varClass, Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.obtain((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.obtain((T3) args[0], (T4) args[1], (T5) args[2]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T3, T4, T5> secondBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T3, T4, T5> secondBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, T1, C2, T2, T3, T4, T5, R>
    R matches(V value,
              Class<C1> firstClazz,  BiFunction<T1, T2, R> firstBranch,
              Class<C2> secondClazz, TriFunction<T3, T4, T5, R> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            return firstBranch.apply((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            return secondBranch.apply((T3) args[0], (T4) args[1], (T5) args[2]);
        }

        return null;
    }

    public static <V, C1, C2, T1, T2, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T4, T5> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T4) args[0], (T5) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, T1, T2, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz, TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T4, T5> secondBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T4) args[0], (T5) args[1]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, BiPurchaser<T4, T5> secondBranch,
                 Class<Var> varClass, Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.obtain((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.obtain((T4) args[0], (T5) args[1]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz, TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T4, T5> secondBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, BiPurchaser<T4, T5> secondBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, T1, C2, T2, T3, T4, T5, R>
    R matches(V value,
              Class<C1> firstClazz,  TriFunction<T1, T2, T3, R> firstBranch,
              Class<C2> secondClazz, BiFunction<T4, T5, R> secondBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            return firstBranch.apply((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            return secondBranch.apply((T4) args[0], (T5) args[1]);
        }

        return null;
    }

    public static <V, C1, C2, C3, T1, T2, T3> void matches(V value,
                                                           Class<C1> firstClazz,  Consumer<T1> firstBranch,
                                                           Class<C2> secondClazz, Consumer<T2> secondBranch,
                                                           Class<C3> thirdClazz,  Consumer<T3> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T2) args[0]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.accept((T3) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3> void matches(V value,
                                                           Class<C1> firstClazz, Consumer<T1> firstBranch,
                                                           Class<C2> secondClazz, Consumer<T2> secondBranch,
                                                           Class<C3> thirdClazz, Consumer<T3> thirdBranch,
                                                           Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T2) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.accept((T3) args[0]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3> void matches(V value,
                                                           Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                                                           Class<C2> secondClazz, Purchaser<T2> secondBranch,
                                                           Class<C3> thirdClazz,  Purchaser<T3> thirdBranch,
                                                           Class<Var> varClass,   Purchaser<V>  varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.obtain((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.obtain((T2) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.obtain((T3) args[0]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3> void matches(V value,
                                                           Class<C1> firstClazz,  Consumer<T1> firstBranch,
                                                           Class<C2> secondClazz, Consumer<T2> secondBranch,
                                                           Class<C3> thirdClazz,  Consumer<T3> thirdBranch,
                                                           Class<Null> nullClass, Runnable nullBranch,
                                                           Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3> void matches(V value,
                                                           Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                                                           Class<C2> secondClazz, Purchaser<T2> secondBranch,
                                                           Class<C3> thirdClazz,  Purchaser<T3> thirdBranch,
                                                           Class<Null> nullClass, Runnable nullBranch,
                                                           Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4> void matches(V value,
                                                           Class<C1> firstClazz,  Consumer<T1> firstBranch,
                                                           Class<C2> secondClazz, Consumer<T2> secondBranch,
                                                           Class<C3> thirdClazz,  BiConsumer<T3, T4> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T2) args[0]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.accept((T3) args[0], (T4) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4> void matches(V value,
                                                               Class<C1> firstClazz, Consumer<T1> firstBranch,
                                                               Class<C2> secondClazz, Consumer<T2> secondBranch,
                                                               Class<C3> thirdClazz, BiConsumer<T3, T4> thirdBranch,
                                                               Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T2) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.accept((T3) args[0], (T4) args[1]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4> void matches(V value,
                                                           Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                                                           Class<C2> secondClazz, Purchaser<T2> secondBranch,
                                                           Class<C3> thirdClazz,  BiPurchaser<T3, T4> thirdBranch,
                                                           Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.obtain((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.obtain((T2) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.obtain((T3) args[0], (T4) args[1]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4> void matches(V value,
                                                               Class<C1> firstClazz,  Consumer<T1> firstBranch,
                                                               Class<C2> secondClazz, Consumer<T2> secondBranch,
                                                               Class<C3> thirdClazz,  BiConsumer<T3, T4> thirdBranch,
                                                               Class<Null> nullClass, Runnable nullBranch,
                                                               Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4> void matches(V value,
                                                           Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                                                           Class<C2> secondClazz, Purchaser<T2> secondBranch,
                                                           Class<C3> thirdClazz,  BiPurchaser<T3, T4> thirdBranch,
                                                           Class<Null> nullClass, Runnable nullBranch,
                                                           Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5> void matches(V value,
                                                           Class<C1> firstClazz,  Consumer<T1> firstBranch,
                                                           Class<C2> secondClazz, BiConsumer<T2, T3> secondBranch,
                                                           Class<C3> thirdClazz,  BiConsumer<T4, T5> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T2) args[0], (T3) args[1]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.accept((T4) args[0], (T5) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5> void matches(V value,
                                                                   Class<C1> firstClazz, Consumer<T1> firstBranch,
                                                                   Class<C2> secondClazz, BiConsumer<T2, T3> secondBranch,
                                                                   Class<C3> thirdClazz, BiConsumer<T4, T5> thirdBranch,
                                                                   Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T2) args[0], (T3) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.accept((T4) args[0], (T5) args[1]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5> void matches(V value,
                                                           Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                                                           Class<C2> secondClazz, BiPurchaser<T2, T3> secondBranch,
                                                           Class<C3> thirdClazz,  BiPurchaser<T4, T5> thirdBranch,
                                                           Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.obtain((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.obtain((T2) args[0], (T3) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.obtain((T4) args[0], (T5) args[1]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5> void matches(V value,
                                                                   Class<C1> firstClazz, Consumer<T1> firstBranch,
                                                                   Class<C2> secondClazz, BiConsumer<T2, T3> secondBranch,
                                                                   Class<C3> thirdClazz, BiConsumer<T4, T5> thirdBranch,
                                                                   Class<Null> nullClass, Runnable nullBranch,
                                                                   Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5> void matches(V value,
                                                               Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                                                               Class<C2> secondClazz, BiPurchaser<T2, T3> secondBranch,
                                                               Class<C3> thirdClazz,  BiPurchaser<T4, T5> thirdBranch,
                                                               Class<Null> nullClass, Runnable nullBranch,
                                                               Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6> void matches(V value,
                                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstBranch,
                                                           Class<C2> secondClazz, BiConsumer<T3, T4> secondBranch,
                                                           Class<C3> thirdClazz,  BiConsumer<T5, T6> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T3) args[0], (T4) args[1]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.accept((T5) args[0], (T6) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6> void matches(V value,
                                                                       Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                                                                       Class<C2> secondClazz, BiConsumer<T3, T4> secondBranch,
                                                                       Class<C3> thirdClazz, BiConsumer<T5, T6> thirdBranch,
                                                                       Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T3) args[0], (T4) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.accept((T5) args[0], (T6) args[1]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6> void matches(V value,
                                                           Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                                                           Class<C2> secondClazz, BiPurchaser<T3, T4> secondBranch,
                                                           Class<C3> thirdClazz,  BiPurchaser<T5, T6> thirdBranch,
                                                           Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.obtain((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.obtain((T3) args[0], (T4) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.obtain((T5) args[0], (T6) args[1]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6> void matches(V value,
                                                                       Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                                                                       Class<C2> secondClazz, BiConsumer<T3, T4> secondBranch,
                                                                       Class<C3> thirdClazz, BiConsumer<T5, T6> thirdBranch,
                                                                       Class<Null> nullClass, Runnable nullBranch,
                                                                       Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6> void matches(V value,
                                                                       Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                                                                       Class<C2> secondClazz, BiPurchaser<T3, T4> secondBranch,
                                                                       Class<C3> thirdClazz,  BiPurchaser<T5, T6> thirdBranch,
                                                                       Class<Null> nullClass, Runnable nullBranch,
                                                                       Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4> void matches(V value,
                                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstBranch,
                                                           Class<C2> secondClazz, Consumer<T3> secondBranch,
                                                           Class<C3> thirdClazz,  Consumer<T4> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T3) args[0]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.accept((T4) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4> void matches(V value,
                                                               Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                                                               Class<C2> secondClazz, Consumer<T3> secondBranch,
                                                               Class<C3> thirdClazz, Consumer<T4> thirdBranch,
                                                               Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T3) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.accept((T4) args[0]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4> void matches(V value,
                                                           Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                                                           Class<C2> secondClazz, Purchaser<T3> secondBranch,
                                                           Class<C3> thirdClazz,  Purchaser<T4> thirdBranch,
                                                           Class<Var> varClass,   Purchaser<V>  varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.obtain((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.obtain((T3) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.obtain((T4) args[0]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4> void matches(V value,
                                                               Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                                                               Class<C2> secondClazz, Consumer<T3> secondBranch,
                                                               Class<C3> thirdClazz, Consumer<T4> thirdBranch,
                                                               Class<Null> nullClass, Runnable nullBranch,
                                                               Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4> void matches(V value,
                                                               Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                                                               Class<C2> secondClazz, Purchaser<T3> secondBranch,
                                                               Class<C3> thirdClazz,  Purchaser<T4> thirdBranch,
                                                               Class<Null> nullClass, Runnable nullBranch,
                                                               Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5> void matches(V value,
                                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstBranch,
                                                           Class<C2> secondClazz, BiConsumer<T3, T4> secondBranch,
                                                           Class<C3> thirdClazz,  Consumer<T5> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T3) args[0], (T4) args[1]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.accept((T5) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5> void matches(V value,
                                                                   Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                                                                   Class<C2> secondClazz, BiConsumer<T3, T4> secondBranch,
                                                                   Class<C3> thirdClazz, Consumer<T5> thirdBranch,
                                                                   Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T3) args[0], (T4) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.accept((T5) args[0]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5> void matches(V value,
                                                           Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                                                           Class<C2> secondClazz, BiPurchaser<T3, T4> secondBranch,
                                                           Class<C3> thirdClazz,  Purchaser<T5> thirdBranch,
                                                           Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.obtain((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.obtain((T3) args[0], (T4) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.obtain((T5) args[0]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5> void matches(V value,
                                                                   Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                                                                   Class<C2> secondClazz, BiConsumer<T3, T4> secondBranch,
                                                                   Class<C3> thirdClazz, Consumer<T5> thirdBranch,
                                                                   Class<Null> nullClass, Runnable nullBranch,
                                                                   Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5> void matches(V value,
                                                               Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                                                               Class<C2> secondClazz, BiPurchaser<T3, T4> secondBranch,
                                                               Class<C3> thirdClazz,  Purchaser<T5> thirdBranch,
                                                               Class<Null> nullClass, Runnable nullBranch,
                                                               Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5> void matches(V value,
                                                           Class<C1> firstClazz,  BiConsumer<T1, T2> firstBranch,
                                                           Class<C2> secondClazz, Consumer<T3> secondBranch,
                                                           Class<C3> thirdClazz,  BiConsumer<T4, T5> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T3) args[0]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.accept((T4) args[0], (T5) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5> void matches(V value,
                                                                   Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                                                                   Class<C2> secondClazz, Consumer<T3> secondBranch,
                                                                   Class<C3> thirdClazz, BiConsumer<T4, T5> thirdBranch,
                                                                   Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T3) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.accept((T4) args[0], (T5) args[1]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5> void matches(V value,
                                                           Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                                                           Class<C2> secondClazz, Purchaser<T3> secondBranch,
                                                           Class<C3> thirdClazz,  BiPurchaser<T4, T5> thirdBranch,
                                                           Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.obtain((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.obtain((T3) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.obtain((T4) args[0], (T5) args[1]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5> void matches(V value,
                                                                   Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                                                                   Class<C2> secondClazz, Consumer<T3> secondBranch,
                                                                   Class<C3> thirdClazz, BiConsumer<T4, T5> thirdBranch,
                                                                   Class<Null> nullClass, Runnable nullBranch,
                                                                   Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5> void matches(V value,
                                                                   Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                                                                   Class<C2> secondClazz, Purchaser<T3> secondBranch,
                                                                   Class<C3> thirdClazz,  BiPurchaser<T4, T5> thirdBranch,
                                                                   Class<Null> nullClass, Runnable nullBranch,
                                                                   Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4> void matches(V value,
                                                           Class<C1> firstClazz,  Consumer<T1> firstBranch,
                                                           Class<C2> secondClazz, BiConsumer<T2, T3> secondBranch,
                                                           Class<C3> thirdClazz,  Consumer<T4> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T2) args[0], (T3) args[1]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.accept((T4) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4> void matches(V value,
                                                               Class<C1> firstClazz, Consumer<T1> firstBranch,
                                                               Class<C2> secondClazz, BiConsumer<T2, T3> secondBranch,
                                                               Class<C3> thirdClazz, Consumer<T4> thirdBranch,
                                                               Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T2) args[0], (T3) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.accept((T4) args[0]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4> void matches(V value,
                                                           Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                                                           Class<C2> secondClazz, BiPurchaser<T2, T3> secondBranch,
                                                           Class<C3> thirdClazz,  Purchaser<T4> thirdBranch,
                                                           Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.obtain((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.obtain((T2) args[0], (T3) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.obtain((T4) args[0]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4> void matches(V value,
                                                               Class<C1> firstClazz, Consumer<T1> firstBranch,
                                                               Class<C2> secondClazz, BiConsumer<T2, T3> secondBranch,
                                                               Class<C3> thirdClazz, Consumer<T4> thirdBranch,
                                                               Class<Null> nullClass, Runnable nullBranch,
                                                               Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4> void matches(V value,
                                                               Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                                                               Class<C2> secondClazz, BiPurchaser<T2, T3> secondBranch,
                                                               Class<C3> thirdClazz,  Purchaser<T4> thirdBranch,
                                                               Class<Null> nullClass, Runnable nullBranch,
                                                               Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz,  Consumer<T1> firstBranch,
                 Class<C2> secondClazz, Consumer<T2> secondBranch,
                 Class<C3> thirdClazz,  TriConsumer<T3, T4, T5> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T2) args[0]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.accept((T3) args[0], (T4) args[1], (T5) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz, Consumer<T1> firstBranch,
                 Class<C2> secondClazz, Consumer<T2> secondBranch,
                 Class<C3> thirdClazz, TriConsumer<T3, T4, T5> thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T2) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.accept((T3) args[0], (T4) args[1], (T5) args[2]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                 Class<C2> secondClazz, Purchaser<T2> secondBranch,
                 Class<C3> thirdClazz,  TriPurchaser<T3, T4, T5> thirdBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.obtain((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.obtain((T2) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.obtain((T3) args[0], (T4) args[1], (T5) args[2]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz, Consumer<T1> firstBranch,
                 Class<C2> secondClazz, Consumer<T2> secondBranch,
                 Class<C3> thirdClazz, TriConsumer<T3, T4, T5> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                 Class<C2> secondClazz, Purchaser<T2> secondBranch,
                 Class<C3> thirdClazz,  TriPurchaser<T3, T4, T5> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  Consumer<T1> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T2, T3, T4> secondBranch,
                 Class<C3> thirdClazz,  TriConsumer<T5, T6, T7> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T2) args[0], (T3) args[1], (T4) args[2]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.accept((T5) args[0], (T6) args[1], (T7) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz, Consumer<T1> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T2, T3, T4> secondBranch,
                 Class<C3> thirdClazz, TriConsumer<T5, T6, T7> thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T2) args[0], (T3) args[1], (T4) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.accept((T5) args[0], (T6) args[1], (T7) args[2]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T2, T3, T4> secondBranch,
                 Class<C3> thirdClazz,  TriPurchaser<T5, T6, T7> thirdBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.obtain((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.obtain((T2) args[0], (T3) args[1], (T4) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.obtain((T5) args[0], (T6) args[1], (T7) args[2]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz, Consumer<T1> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T2, T3, T4> secondBranch,
                 Class<C3> thirdClazz, TriConsumer<T5, T6, T7> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T2, T3, T4> secondBranch,
                 Class<C3> thirdClazz,  TriPurchaser<T5, T6, T7> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7, T8, T9>
    void matches(V value,
                 Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T4, T5, T6> secondBranch,
                 Class<C3> thirdClazz,  TriConsumer<T7, T8, T9> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T4) args[0], (T5) args[1], (T6) args[2]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.accept((T7) args[0], (T8) args[1], (T9) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7, T8, T9>
    void matches(V value,
                 Class<C1> firstClazz, TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T4, T5, T6> secondBranch,
                 Class<C3> thirdClazz, TriConsumer<T7, T8, T9> thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T4) args[0], (T5) args[1], (T6) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.accept((T7) args[0], (T8) args[1], (T9) args[2]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7, T8, T9>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T4, T5, T6> secondBranch,
                 Class<C3> thirdClazz,  TriPurchaser<T7, T8, T9> thirdBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.obtain((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.obtain((T4) args[0], (T5) args[1], (T6) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.obtain((T7) args[0], (T8) args[1], (T9) args[2]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7, T8, T9>
    void matches(V value,
                 Class<C1> firstClazz, TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T4, T5, T6> secondBranch,
                 Class<C3> thirdClazz, TriConsumer<T7, T8, T9> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7, T8, T9>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T4, T5, T6> secondBranch,
                 Class<C3> thirdClazz,  TriPurchaser<T7, T8, T9> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, Consumer<T4> secondBranch,
                 Class<C3> thirdClazz,  Consumer<T5> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T4) args[0]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.accept((T5) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz, TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, Consumer<T4> secondBranch,
                 Class<C3> thirdClazz, Consumer<T5> thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T4) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.accept((T5) args[0]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, Purchaser<T4> secondBranch,
                 Class<C3> thirdClazz,  Purchaser<T5> thirdBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.obtain((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.obtain((T4) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.obtain((T5) args[0]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz, TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, Consumer<T4> secondBranch,
                 Class<C3> thirdClazz, Consumer<T5> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, Purchaser<T4> secondBranch,
                 Class<C3> thirdClazz,  Purchaser<T5> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T4, T5, T6> secondBranch,
                 Class<C3> thirdClazz,  Consumer<T7> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T4) args[0], (T5) args[1], (T6) args[2]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.accept((T7) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz, TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T4, T5, T6> secondBranch,
                 Class<C3> thirdClazz, Consumer<T7> thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T4) args[0], (T5) args[1], (T6) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.accept((T7) args[0]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T4, T5, T6> secondBranch,
                 Class<C3> thirdClazz,  Purchaser<T7> thirdBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.obtain((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.obtain((T4) args[0], (T5) args[1], (T6) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.obtain((T7) args[0]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T4, T5, T6> secondBranch,
                 Class<C3> thirdClazz,  Consumer<T7> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T4, T5, T6> secondBranch,
                 Class<C3> thirdClazz,  Purchaser<T7> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, Consumer<T4> secondBranch,
                 Class<C3> thirdClazz,  TriConsumer<T5, T6, T7> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T4) args[0]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.accept((T5) args[0], (T6) args[1], (T7) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz, TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, Consumer<T4> secondBranch,
                 Class<C3> thirdClazz, TriConsumer<T5, T6, T7> thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T4) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.accept((T5) args[0], (T6) args[1], (T7) args[2]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, Purchaser<T4> secondBranch,
                 Class<C3> thirdClazz,  TriPurchaser<T5, T6, T7> thirdBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.obtain((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.obtain((T4) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.obtain((T5) args[0], (T6) args[1], (T7) args[2]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz, TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, Consumer<T4> secondBranch,
                 Class<C3> thirdClazz, TriConsumer<T5, T6, T7> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                    secondClazz, secondBranch,
                    thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, Purchaser<T4> secondBranch,
                 Class<C3> thirdClazz,  TriPurchaser<T5, T6, T7> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz,  Consumer<T1> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T2, T3 ,T4> secondBranch,
                 Class<C3> thirdClazz,  Consumer<T5> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T2) args[0], (T3) args[1], (T4) args[2]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.accept((T5) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz, Consumer<T1> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T2, T3 ,T4> secondBranch,
                 Class<C3> thirdClazz, Consumer<T5> thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T2) args[0], (T3) args[1], (T4) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.accept((T5) args[0]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5>
    void matches(V value,
                 Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T2, T3, T4> secondBranch,
                 Class<C3> thirdClazz,  Purchaser<T5> thirdBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.obtain((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.obtain((T2) args[0], (T3) args[1], (T4) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.obtain((T5) args[0]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz, Consumer<T1> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T2, T3, T4> secondBranch,
                 Class<C3> thirdClazz, Consumer<T7> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T2, T3, T4> secondBranch,
                 Class<C3> thirdClazz,  Purchaser<T5> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T3, T4> secondBranch,
                 Class<C3> thirdClazz,  TriConsumer<T5, T6, T7> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T3) args[0], (T4) args[1]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.accept((T5) args[0], (T6) args[1], (T7) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T3, T4> secondBranch,
                 Class<C3> thirdClazz, TriConsumer<T5, T6, T7> thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T3) args[0], (T4) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.accept((T5) args[0], (T6) args[1], (T7) args[2]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                 Class<C2> secondClazz, BiPurchaser<T3, T4> secondBranch,
                 Class<C3> thirdClazz,  TriPurchaser<T5, T6, T7> thirdBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.obtain((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.obtain((T3) args[0], (T4) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.obtain((T5) args[0], (T6) args[1], (T7) args[2]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T3, T4> secondBranch,
                 Class<C3> thirdClazz, TriConsumer<T5, T6, T7> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                 Class<C2> secondClazz, BiPurchaser<T3, T4> secondBranch,
                 Class<C3> thirdClazz,  TriPurchaser<T5, T6, T7> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7, T8>
    void matches(V value,
                 Class<C1> firstClazz,  BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T3, T4, T5> secondBranch,
                 Class<C3> thirdClazz,  TriConsumer<T6, T7, T8> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T3) args[0], (T4) args[1], (T5) args[2]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.accept((T6) args[0], (T7) args[1], (T8) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7, T8>
    void matches(V value,
                 Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T3, T4, T5> secondBranch,
                 Class<C3> thirdClazz, TriConsumer<T6, T7, T8> thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T3) args[0], (T4) args[1], (T5) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.accept((T6) args[0], (T7) args[1], (T8) args[2]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7, T8>
    void matches(V value,
                 Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T3, T4, T5> secondBranch,
                 Class<C3> thirdClazz,  TriPurchaser<T6, T7, T8> thirdBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.obtain((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.obtain((T3) args[0], (T4) args[1], (T5) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.obtain((T6) args[0], (T7) args[1], (T8) args[2]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7, T8>
    void matches(V value,
                 Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T3, T4, T5> secondBranch,
                 Class<C3> thirdClazz, TriConsumer<T6, T7, T8> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7, T8>
    void matches(V value,
                 Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T3, T4, T5> secondBranch,
                 Class<C3> thirdClazz,  TriPurchaser<T6, T7, T8> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                        secondClazz, secondBranch,
                        thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T4, T5> secondBranch,
                 Class<C3> thirdClazz,  BiConsumer<T6, T7> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T4) args[0], (T5) args[1]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.accept((T6) args[0], (T7) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz, TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T4, T5> secondBranch,
                 Class<C3> thirdClazz, BiConsumer<T6, T7> thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T4) args[0], (T5) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.accept((T6) args[0], (T7) args[1]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, BiPurchaser<T4, T5> secondBranch,
                 Class<C3> thirdClazz,  BiPurchaser<T6, T7> thirdBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.obtain((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.obtain((T4) args[0], (T5) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.obtain((T6) args[0], (T7) args[1]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T4, T5> secondBranch,
                 Class<C3> thirdClazz,  BiConsumer<T6, T7> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, BiPurchaser<T4, T5> secondBranch,
                 Class<C3> thirdClazz,  BiPurchaser<T6, T7> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                    secondClazz, secondBranch,
                    thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7, T8>
    void matches(V value,
                 Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T4, T5, T6> secondBranch,
                 Class<C3> thirdClazz,  BiConsumer<T7, T8> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T4) args[0], (T5) args[1], (T6) args[2]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.accept((T7) args[0], (T8) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7, T8>
    void matches(V value,
                 Class<C1> firstClazz, TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T4, T5, T6> secondBranch,
                 Class<C3> thirdClazz, BiConsumer<T7, T8> thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T4) args[0], (T5) args[1], (T6) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.accept((T7) args[0], (T8) args[1]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7, T8>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T4, T5, T6> secondBranch,
                 Class<C3> thirdClazz,  BiPurchaser<T7, T8> thirdBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.obtain((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.obtain((T4) args[0], (T5) args[1], (T6) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.obtain((T7) args[0], (T8) args[1]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7, T8>
    void matches(V value,
                 Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T4, T5, T6> secondBranch,
                 Class<C3> thirdClazz,  BiConsumer<T7, T8> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7, T8>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T4, T5, T6> secondBranch,
                 Class<C3> thirdClazz,  BiPurchaser<T7, T8> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                    secondClazz, secondBranch,
                    thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7, T8>
    void matches(V value,
                 Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T4, T5> secondBranch,
                 Class<C3> thirdClazz,  TriConsumer<T6, T7, T8> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T4) args[0], (T5) args[1]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.accept((T6) args[0], (T7) args[1], (T8) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7, T8>
    void matches(V value,
                 Class<C1> firstClazz, TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T4, T5> secondBranch,
                 Class<C3> thirdClazz, TriConsumer<T6, T7, T8> thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T4) args[0], (T5) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.accept((T6) args[0], (T7) args[1], (T8) args[2]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7, T8>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, BiPurchaser<T4, T5> secondBranch,
                 Class<C3> thirdClazz,  TriPurchaser<T6, T7, T8> thirdBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.obtain((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.obtain((T4) args[0], (T5) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.obtain((T6) args[0], (T7) args[1], (T8) args[2]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7, T8>
    void matches(V value,
                 Class<C1> firstClazz, TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T4, T5> secondBranch,
                 Class<C3> thirdClazz, TriConsumer<T6, T7, T8> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7, T8>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, BiPurchaser<T4, T5> secondBranch,
                 Class<C3> thirdClazz,  TriPurchaser<T6, T7, T8> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T3, T4, T5> secondBranch,
                 Class<C3> thirdClazz,  BiConsumer<T6, T7> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T3) args[0], (T4) args[1], (T5) args[2]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.accept((T6) args[0], (T7) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T3, T4, T5> secondBranch,
                 Class<C3> thirdClazz, BiConsumer<T6, T7> thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T3) args[0], (T4) args[1], (T5) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.accept((T6) args[0], (T7) args[1]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T3, T4, T5> secondBranch,
                 Class<C3> thirdClazz,  BiPurchaser<T6, T7> thirdBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.obtain((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.obtain((T3) args[0], (T4) args[1], (T5) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.obtain((T6) args[0], (T7) args[1]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T3, T4, T5> secondBranch,
                 Class<C3> thirdClazz, BiConsumer<T6, T7> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6, T7>
    void matches(V value,
                 Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T3, T4, T5> secondBranch,
                 Class<C3> thirdClazz,  BiPurchaser<T6, T7> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  Consumer<T1> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T2, T3> secondBranch,
                 Class<C3> thirdClazz,  TriConsumer<T4, T5, T6> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T2) args[0], (T3) args[1]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.accept((T4) args[0], (T5) args[1], (T6) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz, Consumer<T1> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T2, T3> secondBranch,
                 Class<C3> thirdClazz, TriConsumer<T4, T5, T6> thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T2) args[0], (T3) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.accept((T4) args[0], (T5) args[1], (T6) args[2]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                 Class<C2> secondClazz, BiPurchaser<T2, T3> secondBranch,
                 Class<C3> thirdClazz,  TriPurchaser<T4, T5, T6> thirdBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.obtain((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.obtain((T2) args[0], (T3) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.obtain((T4) args[0], (T5) args[1], (T6) args[2]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  Consumer<T1> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T2, T3> secondBranch,
                 Class<C3> thirdClazz,  TriConsumer<T4, T5, T6> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                 Class<C2> secondClazz, BiPurchaser<T2, T3> secondBranch,
                 Class<C3> thirdClazz,  TriPurchaser<T4, T5, T6> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T4, T5> secondBranch,
                 Class<C3> thirdClazz,  Consumer<T6> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T4) args[0], (T5) args[1]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.accept((T6) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz, TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T4, T5> secondBranch,
                 Class<C3> thirdClazz, Consumer<T6> thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.accept((T4) args[0], (T5) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.accept((T6) args[0]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, BiPurchaser<T4, T5> secondBranch,
                 Class<C3> thirdClazz,  Purchaser<T6> thirdBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.obtain((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            secondBranch.obtain((T4) args[0], (T5) args[1]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.obtain((T6) args[0]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, BiConsumer<T4, T5> secondBranch,
                 Class<C3> thirdClazz,  Consumer<T6> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, BiPurchaser<T4, T5> secondBranch,
                 Class<C3> thirdClazz,  Purchaser<T6> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, Consumer<T3> secondBranch,
                 Class<C3> thirdClazz,  TriConsumer<T4, T5, T6> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T3) args[0]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.accept((T4) args[0], (T5) args[1], (T6) args[2]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, Consumer<T3> secondBranch,
                 Class<C3> thirdClazz, TriConsumer<T4, T5, T6> thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T3) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.accept((T4) args[0], (T5) args[1], (T6) args[2]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                 Class<C2> secondClazz, Purchaser<T3> secondBranch,
                 Class<C3> thirdClazz,  TriPurchaser<T4, T5, T6> thirdBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.obtain((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.obtain((T3) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            thirdBranch.obtain((T4) args[0], (T5) args[1], (T6) args[2]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, Consumer<T3> secondBranch,
                 Class<C3> thirdClazz, TriConsumer<T4, T5, T6> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                 Class<C2> secondClazz, Purchaser<T3> secondBranch,
                 Class<C3> thirdClazz,  TriPurchaser<T4, T5, T6> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                    secondClazz, secondBranch,
                    thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, Consumer<T4> secondBranch,
                 Class<C3> thirdClazz,  BiConsumer<T5, T6> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T4) args[0]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.accept((T5) args[0], (T6) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz, TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, Consumer<T4> secondBranch,
                 Class<C3> thirdClazz, BiConsumer<T5, T6> thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.accept((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.accept((T4) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.accept((T5) args[0], (T6) args[1]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, Purchaser<T4> secondBranch,
                 Class<C3> thirdClazz,  BiPurchaser<T5, T6> thirdBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            firstBranch.obtain((T1) args[0], (T2) args[1], (T3) args[2]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            secondBranch.obtain((T4) args[0]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.obtain((T5) args[0], (T6) args[1]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  TriConsumer<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, Consumer<T4> secondBranch,
                 Class<C3> thirdClazz,  BiConsumer<T5, T6> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  TriPurchaser<T1, T2, T3> firstBranch,
                 Class<C2> secondClazz, Purchaser<T4> secondBranch,
                 Class<C3> thirdClazz,  BiPurchaser<T5, T6> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  Consumer<T1> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T2, T3, T4> secondBranch,
                 Class<C3> thirdClazz,  BiConsumer<T5, T6> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T2) args[0], (T3) args[1], (T4) args[2]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.accept((T5) args[0], (T6) args[1]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz, Consumer<T1> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T2, T3, T4> secondBranch,
                 Class<C3> thirdClazz, BiConsumer<T5, T6> thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.accept((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T2) args[0], (T3) args[1], (T4) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.accept((T5) args[0], (T6) args[1]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T2, T3, T4> secondBranch,
                 Class<C3> thirdClazz,  BiPurchaser<T5, T6> thirdBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            firstBranch.obtain((T1) args[0]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.obtain((T2) args[0], (T3) args[1], (T4) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            thirdBranch.obtain((T5) args[0], (T6) args[1]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz, Consumer<T1> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T2, T3, T4> secondBranch,
                 Class<C3> thirdClazz, BiConsumer<T5, T6> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  Purchaser<T1> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T2, T3, T4> secondBranch,
                 Class<C3> thirdClazz,  BiPurchaser<T5, T6> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T3, T4, T5> secondBranch,
                 Class<C3> thirdClazz,  Consumer<T6> thirdBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T3) args[0], (T4) args[1], (T5) args[2]);
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.accept((T6) args[0]);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T3, T4, T5> secondBranch,
                 Class<C3> thirdClazz, Consumer<T6> thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.accept((T1) args[0], (T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.accept((T3) args[0], (T4) args[1], (T5) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.accept((T6) args[0]);
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("unused")
    public static <V, C1, C2, C3, T1, T2, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T3, T4, T5> secondBranch,
                 Class<C3> thirdClazz,  Purchaser<T6> thirdBranch,
                 Class<Var> varClass,   Purchaser<V> varBranch)  {
        if (firstClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 2);

            firstBranch.obtain((T1) args[0],(T2) args[1]);
            return;
        } else if (secondClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 3);

            secondBranch.obtain((T3) args[0], (T4) args[1], (T5) args[2]);
            return;
        } else if (thirdClazz == value.getClass()) {
            Object[] args = Reflection.invokeExtractor(value, 1);

            thirdBranch.obtain((T6) args[0]);
            return;
        }

        varBranch.obtain(value);
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz, BiConsumer<T1, T2> firstBranch,
                 Class<C2> secondClazz, TriConsumer<T3, T4, T5> secondBranch,
                 Class<C3> thirdClazz, Consumer<T6> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    elseClass, elseBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V, C1, T1, C2, T2, C3, T3, T4, T5, T6>
    void matches(V value,
                 Class<C1> firstClazz,  BiPurchaser<T1, T2> firstBranch,
                 Class<C2> secondClazz, TriPurchaser<T3, T4, T5> secondBranch,
                 Class<C3> thirdClazz,  Purchaser<T6> thirdBranch,
                 Class<Null> nullClass, Runnable nullBranch,
                 Class<Var>  varClass,  Purchaser<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstClazz,  firstBranch,
                           secondClazz, secondBranch,
                           thirdClazz,  thirdBranch,
                    varClass, varBranch);
        }
    }
}
