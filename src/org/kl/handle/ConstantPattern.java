/*
 * JPML - Java pattern matching library.
 *
 * Constant pattern allow test for equality with constants.
 * Maximum number of branches for match six.
 */
package org.kl.handle;

import org.kl.error.PatternException;
import org.kl.reflect.Reflection;
import org.kl.state.Default;
import org.kl.state.Null;
import org.kl.state.Var;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ConstantPattern {

    public static <V> void matches(V value,
                                   V data, Runnable firstBranch) throws PatternException {
        if (!Reflection.checkTypes(data.getClass(), value.getClass())) {
            throw new PatternException("Type in brunches must to be equals");
        }

        if (value.equals(data)) {
            firstBranch.run();
        }
    }

    @SuppressWarnings("unused")
    public static <V> void matches(V value,
                                   V data, Runnable firstBranch,
                                   Class<Default> defaultClass, Runnable defaultBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(), data.getClass())) {
            throw new PatternException("Type in brunches must to be equals");
        }

        if (value.equals(data)) {
            firstBranch.run();
        } else {
            defaultBranch.run();
        }
    }

    @SuppressWarnings("unused")
    public static <V> void matches(V value,
                                   V data, Runnable firstBranch,
                                   Class<Var> varClass, Consumer<V> varBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(), data.getClass())) {
            throw new PatternException("Type in brunches must to be equals");
        }

        if (value.equals(data)) {
            firstBranch.run();
        } else {
            varBranch.accept(value);
        }
    }

    @SuppressWarnings("unused")
    public static <V> void matches(V value,
                                   V data, Runnable firstBranch,
                                   Class<Null> nullClass, Runnable nullBranch,
                                   Class<Default> defaultClass, Runnable defaultBranch) throws PatternException {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, data, firstBranch, defaultClass, defaultBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V> void matches(V value,
                                   V data, Runnable firstBranch,
                                   Class<Null> nullClass, Runnable nullBranch,
                                   Class<Var>  varClass,  Consumer<V> varBranch) throws PatternException {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, data, firstBranch, varClass, varBranch);
        }
    }

    public static <V> V matches(V value,
                                V data, Supplier<V> firstBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(), data.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(data)) {
            return firstBranch.get();
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V data, Supplier<V> firstBranch,
                                Class<Default> defaultClass, Supplier<V> defaultBranch) throws PatternException {
        V result = matches(value, data, firstBranch);

        if (result != null) {
            return result;
        } else {
            return defaultBranch.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V data, Supplier<V> firstBranch,
                                Class<Var> varClass, UnaryOperator<V> varBranch) throws PatternException {
        V result = matches(value, data, firstBranch);

        if (result != null) {
            return result;
        } else {
            return varBranch.apply(value);
        }
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V data, Supplier<V> firstBranch,
                                Class<Null> nullClass, Supplier<V> nullBranch,
                                Class<Default> defaultClass, Supplier<V> defaultBranch) throws PatternException {
        if (value == null) {
            return nullBranch.get();
        } else {
            return matches(value, data, firstBranch, defaultClass, defaultBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V data, Supplier<V> firstBranch,
                                Class<Null> nullClass, Supplier<V> nullBranch,
                                Class<Var> varClass, UnaryOperator<V> varBranch) throws PatternException {
        if (value == null) {
            return nullBranch.get();
        } else {
            return matches(value, data, firstBranch, varClass, varBranch);
        }
    }

    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(), firstData.getClass(), secondData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            firstBranch.run();
        } else if (value.equals(secondData)) {
            secondBranch.run();
        }
    }

    @SuppressWarnings({"unused", "Duplicates"})
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   Class<Default> defaultClass, Runnable defaultBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(), firstData.getClass(), secondData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            firstBranch.run();
        } else if (value.equals(secondData)) {
            secondBranch.run();
        } else {
            defaultBranch.run();
        }
    }

    @SuppressWarnings({"unused", "Duplicates"})
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   Class<Var> varClass, Consumer<V> varBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(), firstData.getClass(), secondData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            firstBranch.run();
        } else if (value.equals(secondData)) {
            secondBranch.run();
        } else {
            varBranch.accept(value);
        }
    }

    @SuppressWarnings("unused")
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   Class<Null> nullClass, Runnable nullBranch,
                                   Class<Default> defaultClass, Runnable defaultBranch) throws PatternException {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstData,  firstBranch,
                           secondData, secondBranch,
                    defaultClass, defaultBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   Class<Null> nullClass, Runnable nullBranch,
                                   Class<Var> varClass, Consumer<V> varBranch) throws PatternException {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstData,  firstBranch,
                           secondData, secondBranch,
                    varClass, varBranch);
        }
    }

    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(), firstData.getClass(), secondData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            return firstBranch.get();
        } else if (value.equals(secondData)) {
            return secondBranch.get();
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                Class<Default> defaultClass, Supplier<V> defaultBranch) throws PatternException {
        V result = matches(value,
                           firstData,  firstBranch,
                           secondData, secondBranch);

        if (result != null) {
            return result;
        } else {
            return defaultBranch.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                Class<Var> varClass, UnaryOperator<V> varBranch) throws PatternException {
        V result = matches(value,
                           firstData,  firstBranch,
                           secondData, secondBranch);

        if (result != null) {
            return result;
        } else {
            return varBranch.apply(value);
        }
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                Class<Null> nullClass, Supplier<V> nullBranch,
                                Class<Default> defaultClass, Supplier<V> defaultBranch) throws PatternException {
        if (value == null) {
            return nullBranch.get();
        } else {
            return matches(value, firstData,  firstBranch,
                                  secondData, secondBranch,
                           defaultClass, defaultBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                Class<Null> nullClass, Supplier<V> nullBranch,
                                Class<Var> varClass, UnaryOperator<V> varBranch) throws PatternException {
        if (value == null) {
            return nullBranch.get();
        } else {
            return matches(value, firstData,  firstBranch,
                                  secondData, secondBranch,
                           varClass, varBranch);
        }
    }

    @SuppressWarnings("Duplicates")
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   V thirdData,  Runnable thirdBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(), firstData.getClass(),
                                   secondData.getClass(), thirdData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            firstBranch.run();
        } else if (value.equals(secondData)) {
            secondBranch.run();
        } else if (value.equals(thirdData)) {
            thirdBranch.run();
        }
    }

    @SuppressWarnings({"unused", "Duplicates"})
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   V thirdData,  Runnable thirdBranch,
                                   Class<Default> defaultClass, Runnable defaultBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(), firstData.getClass(),
                                   secondData.getClass(), thirdData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            firstBranch.run();
        } else if (value.equals(secondData)) {
            secondBranch.run();
        } else if (value.equals(thirdData)) {
            thirdBranch.run();
        } else {
            defaultBranch.run();
        }
    }

    @SuppressWarnings({"unused", "Duplicates"})
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   V thirdData,  Runnable thirdBranch,
                                   Class<Var> varClass, Consumer<V> varBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(), firstData.getClass(),
                                   secondData.getClass(), thirdData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            firstBranch.run();
        } else if (value.equals(secondData)) {
            secondBranch.run();
        } else if (value.equals(thirdData)) {
            thirdBranch.run();
        } else {
            varBranch.accept(value);
        }
    }

    @SuppressWarnings("unused")
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   V thirdData,  Runnable thirdBranch,
                                   Class<Null> nullClass, Runnable nullBranch,
                                   Class<Default> defaultClass, Runnable defaultBranch) throws PatternException {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                    defaultClass, defaultBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   V thirdData,  Runnable thirdBranch,
                                   Class<Null> nullClass, Runnable nullBranch,
                                   Class<Var> varClass, Consumer<V> varBranch) throws PatternException {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                    varClass, varBranch);
        }
    }

    @SuppressWarnings("Duplicates")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                V thirdData, Supplier<V> thirdBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(), firstData.getClass(),
                                   secondData.getClass(), thirdData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            return firstBranch.get();
        } else if (value.equals(secondData)) {
            return secondBranch.get();
        } else if (value.equals(thirdData)) {
            return thirdBranch.get();
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                V thirdData,  Supplier<V> thirdBranch,
                                Class<Default> defaultClass, Supplier<V> defaultBranch) throws PatternException {
        V result = matches(value,
                           firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch);

        if (result != null) {
            return result;
        } else {
            return defaultBranch.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                V thirdData,  Supplier<V> thirdBranch,
                                Class<Var> varClass, UnaryOperator<V> varBranch) throws PatternException {
        V result = matches(value,
                           firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch);

        if (result != null) {
            return result;
        } else {
            return varBranch.apply(value);
        }
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                V thirdData,  Supplier<V> thirdBranch,
                                Class<Null> nullClass, Supplier<V> nullBranch,
                                Class<Default> defaultClass, Supplier<V> defaultBranch) throws PatternException {
        if (value == null) {
            return nullBranch.get();
        } else {
            return matches(value, firstData,  firstBranch,
                                  secondData, secondBranch,
                                  thirdData,  thirdBranch,
                           defaultClass, defaultBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                V thirdData,  Supplier<V> thirdBranch,
                                Class<Null> nullClass, Supplier<V> nullBranch,
                                Class<Var> varClass, UnaryOperator<V> varBranch) throws PatternException {
        if (value == null) {
            return nullBranch.get();
        } else {
            return matches(value, firstData,  firstBranch,
                                  secondData, secondBranch,
                                  thirdData,  thirdBranch,
                           varClass, varBranch);
        }
    }

    @SuppressWarnings("Duplicates")
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   V thirdData,  Runnable thirdBranch,
                                   V fourthData, Runnable fourthBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(),
                                   firstData.getClass(), secondData.getClass(),
                                   thirdData.getClass(), fourthData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            firstBranch.run();
        } else if (value.equals(secondData)) {
            secondBranch.run();
        } else if (value.equals(thirdData)) {
            thirdBranch.run();
        } else if (value.equals(fourthData)) {
            fourthBranch.run();
        }
    }

    @SuppressWarnings({"unused", "Duplicates"})
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   V thirdData,  Runnable thirdBranch,
                                   V fourthData, Runnable fourthBranch,
                                   Class<Default> defaultClass, Runnable defaultBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(),
                                   firstData.getClass(), secondData.getClass(),
                                   thirdData.getClass(), fourthData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            firstBranch.run();
        } else if (value.equals(secondData)) {
            secondBranch.run();
        } else if (value.equals(thirdData)) {
            thirdBranch.run();
        } else if (value.equals(fourthData)) {
            fourthBranch.run();
        } else {
            defaultBranch.run();
        }
    }

    @SuppressWarnings({"unused", "Duplicates"})
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   V thirdData,  Runnable thirdBranch,
                                   V fourthData, Runnable fourthBranch,
                                   Class<Var> varClass, Consumer<V> varBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(),
                                   firstData.getClass(), secondData.getClass(),
                                   thirdData.getClass(), fourthData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            firstBranch.run();
        } else if (value.equals(secondData)) {
            secondBranch.run();
        } else if (value.equals(thirdData)) {
            thirdBranch.run();
        } else if (value.equals(fourthData)) {
            fourthBranch.run();
        } else {
            varBranch.accept(value);
        }
    }

    @SuppressWarnings("unused")
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   V thirdData,  Runnable thirdBranch,
                                   V fourthData, Runnable fourthBranch,
                                   Class<Null> nullClass, Runnable nullBranch,
                                   Class<Default> defaultClass, Runnable defaultBranch) throws PatternException {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                           fourthData, fourthBranch,
                    defaultClass, defaultBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   V thirdData,  Runnable thirdBranch,
                                   V fourthData, Runnable fourthBranch,
                                   Class<Null> nullClass, Runnable nullBranch,
                                   Class<Var> varClass, Consumer<V> varBranch) throws PatternException {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                           fourthData, fourthBranch,
                    varClass, varBranch);
        }
    }

    @SuppressWarnings("Duplicates")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                V thirdData,  Supplier<V> thirdBranch,
                                V fourthData, Supplier<V> fourthBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(),
                                   firstData.getClass(), secondData.getClass(),
                                   thirdData.getClass(), fourthData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            return firstBranch.get();
        } else if (value.equals(secondData)) {
            return secondBranch.get();
        } else if (value.equals(thirdData)) {
            return thirdBranch.get();
        } else if (value.equals(fourthData)) {
            return  fourthBranch.get();
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                V thirdData,  Supplier<V> thirdBranch,
                                V fourthData, Supplier<V> fourthBranch,
                                Class<Default> defaultClass, Supplier<V> defaultBranch) throws PatternException {
        V result = matches(value,
                           firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                           fourthData, fourthBranch);
        if (result != null) {
            return result;
        } else {
            return defaultBranch.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                V thirdData,  Supplier<V> thirdBranch,
                                V fourthData, Supplier<V> fourthBranch,
                                Class<Var> varClass, UnaryOperator<V> varBranch) throws PatternException {
        V result = matches(value,
                           firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                           fourthData, fourthBranch);
        if (result != null) {
            return result;
        } else {
            return varBranch.apply(value);
        }
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                V thirdData,  Supplier<V> thirdBranch,
                                V fourthData, Supplier<V> fourthBranch,
                                Class<Null> nullClass, Supplier<V> nullBranch,
                                Class<Default> defaultClass, Supplier<V> defaultBranch) throws PatternException {
        if (value == null) {
            return nullBranch.get();
        } else {
            return matches(value, firstData,  firstBranch,
                                  secondData, secondBranch,
                                  thirdData,  thirdBranch,
                                  fourthData, fourthBranch,
                           defaultClass, defaultBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                V thirdData,  Supplier<V> thirdBranch,
                                V fourthData, Supplier<V> fourthBranch,
                                Class<Null> nullClass, Supplier<V> nullBranch,
                                Class<Var> varClass, UnaryOperator<V> varBranch) throws PatternException {
        if (value == null) {
            return nullBranch.get();
        } else {
            return matches(value, firstData,  firstBranch,
                                  secondData, secondBranch,
                                  thirdData,  thirdBranch,
                                  fourthData, fourthBranch,
                           varClass, varBranch);
        }
    }

    @SuppressWarnings("Duplicates")
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   V thirdData,  Runnable thirdBranch,
                                   V fourthData, Runnable fourthBranch,
                                   V fifthData,  Runnable fifthBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(), firstData.getClass(),
                                   secondData.getClass(), thirdData.getClass(),
                                   fourthData.getClass(), fifthData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            firstBranch.run();
        } else if (value.equals(secondData)) {
            secondBranch.run();
        } else if (value.equals(thirdData)) {
            thirdBranch.run();
        } else if (value.equals(fourthData)) {
            fourthBranch.run();
        } else if (value.equals(fifthData)) {
            fifthBranch.run();
        }
    }

    @SuppressWarnings({"unused", "Duplicates"})
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   V thirdData,  Runnable thirdBranch,
                                   V fourthData, Runnable fourthBranch,
                                   V fifthData,  Runnable fifthBranch,
                                   Class<Default> defaultClass, Runnable defaultBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(), firstData.getClass(),
                                   secondData.getClass(), thirdData.getClass(),
                                   fourthData.getClass(), fifthData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            firstBranch.run();
        } else if (value.equals(secondData)) {
            secondBranch.run();
        } else if (value.equals(thirdData)) {
            thirdBranch.run();
        } else if (value.equals(fourthData)) {
            fourthBranch.run();
        } else if (value.equals(fifthData)) {
            fifthBranch.run();
        } else {
            defaultBranch.run();
        }
    }

    @SuppressWarnings({"unused", "Duplicates"})
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   V thirdData,  Runnable thirdBranch,
                                   V fourthData, Runnable fourthBranch,
                                   V fifthData,  Runnable fifthBranch,
                                   Class<Var> varClass, Consumer<V> varBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(), firstData.getClass(),
                                   secondData.getClass(), thirdData.getClass(),
                                   fourthData.getClass(), fifthData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            firstBranch.run();
        } else if (value.equals(secondData)) {
            secondBranch.run();
        } else if (value.equals(thirdData)) {
            thirdBranch.run();
        } else if (value.equals(fourthData)) {
            fourthBranch.run();
        } else if (value.equals(fifthData)) {
            fifthBranch.run();
        } else {
            varBranch.accept(value);
        }
    }

    @SuppressWarnings("unused")
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   V thirdData,  Runnable thirdBranch,
                                   V fourthData, Runnable fourthBranch,
                                   V fifthData,  Runnable fifthBranch,
                                   Class<Null> nullClass, Runnable nullBranch,
                                   Class<Default> defaultClass, Runnable defaultBranch) throws PatternException {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                           fourthData, fourthBranch,
                           fifthData, fifthBranch,
                    defaultClass, defaultBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   V thirdData,  Runnable thirdBranch,
                                   V fourthData, Runnable fourthBranch,
                                   V fifthData,  Runnable fifthBranch,
                                   Class<Null> nullClass, Runnable nullBranch,
                                   Class<Var> varClass, Consumer<V> varBranch) throws PatternException {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                           fourthData, fourthBranch,
                           fifthData, fifthBranch,
                    varClass, varBranch);
        }
    }

    @SuppressWarnings("Duplicates")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                V thirdData,  Supplier<V> thirdBranch,
                                V fourthData, Supplier<V> fourthBranch,
                                V fifthData,  Supplier<V> fifthBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(),
                                   firstData.getClass(), secondData.getClass(),
                                   thirdData.getClass(), fourthData.getClass(), fifthData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            return firstBranch.get();
        } else if (value.equals(secondData)) {
            return secondBranch.get();
        } else if (value.equals(thirdData)) {
            return thirdBranch.get();
        } else if (value.equals(fourthData)) {
            return fourthBranch.get();
        } else if (value.equals(fifthData)) {
            return fifthBranch.get();
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                V thirdData,  Supplier<V> thirdBranch,
                                V fourthData, Supplier<V> fourthBranch,
                                V fifthData,  Supplier<V> fifthBranch,
                                Class<Default> defaultClass, Supplier<V> defaultBranch) throws PatternException {
        V result = matches(value,
                           firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                           fourthData, fourthBranch,
                           fifthData,  fifthBranch);
        if (result != null) {
            return result;
        } else {
            return defaultBranch.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                V thirdData,  Supplier<V> thirdBranch,
                                V fourthData, Supplier<V> fourthBranch,
                                V fifthData,  Supplier<V> fifthBranch,
                                Class<Var> varClass, UnaryOperator<V> varBranch) throws PatternException {
        V result = matches(value,
                           firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                           fourthData, fourthBranch,
                           fifthData,  fifthBranch);
        if (result != null) {
            return result;
        } else {
            return varBranch.apply(value);
        }
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                V thirdData,  Supplier<V> thirdBranch,
                                V fourthData, Supplier<V> fourthBranch,
                                V fifthData,  Supplier<V> fifthBranch,
                                Class<Null> nullClass, Supplier<V> nullBranch,
                                Class<Default> defaultClass, Supplier<V> defaultBranch) throws PatternException {
        if (value == null) {
            return nullBranch.get();
        } else {
            return matches(value, firstData,  firstBranch,
                                  secondData, secondBranch,
                                  thirdData,  thirdBranch,
                                  fourthData, fourthBranch,
                                  fifthData,  fifthBranch,
                           defaultClass, defaultBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                V thirdData,  Supplier<V> thirdBranch,
                                V fourthData, Supplier<V> fourthBranch,
                                V fifthData,  Supplier<V> fifthBranch,
                                Class<Null> nullClass, Supplier<V> nullBranch,
                                Class<Var> varClass, UnaryOperator<V> varBranch) throws PatternException {
        if (value == null) {
            return nullBranch.get();
        } else {
            return matches(value, firstData,  firstBranch,
                                  secondData, secondBranch,
                                  thirdData,  thirdBranch,
                                  fourthData, fourthBranch,
                                  fifthData,  fifthBranch,
                           varClass, varBranch);
        }
    }

    @SuppressWarnings("Duplicates")
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   V thirdData,  Runnable thirdBranch,
                                   V fourthData, Runnable fourthBranch,
                                   V fifthData,  Runnable fifthBranch,
                                   V sixthData,  Runnable sixthBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(),
                                   firstData.getClass(), secondData.getClass(),
                                   thirdData.getClass(), fourthData.getClass(),
                                   fifthData.getClass(), sixthData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            firstBranch.run();
        } else if (value.equals(secondData)) {
            secondBranch.run();
        } else if (value.equals(thirdData)) {
            thirdBranch.run();
        } else if (value.equals(fourthData)) {
            fourthBranch.run();
        } else if (value.equals(fifthData)) {
            fifthBranch.run();
        } else if (value.equals(sixthData)) {
            sixthBranch.run();
        }
    }

    @SuppressWarnings({"unused", "Duplicates"})
    public static <V> void matches(V value,
                                  V firstData,  Runnable firstBranch,
                                  V secondData, Runnable secondBranch,
                                  V thirdData,  Runnable thirdBranch,
                                  V fourthData, Runnable fourthBranch,
                                  V fifthData,  Runnable fifthBranch,
                                  V sixthData,  Runnable sixthBranch,
                                  Class<Default> defaultClass, Runnable defaultBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(),
                                   firstData.getClass(), secondData.getClass(),
                                   thirdData.getClass(), fourthData.getClass(),
                                   fifthData.getClass(), sixthData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            firstBranch.run();
        } else if (value.equals(secondData)) {
            secondBranch.run();
        } else if (value.equals(thirdData)) {
            thirdBranch.run();
        } else if (value.equals(fourthData)) {
            fourthBranch.run();
        } else if (value.equals(fifthData)) {
            fifthBranch.run();
        } else if (value.equals(sixthData)) {
            sixthBranch.run();
        } else {
            defaultBranch.run();
        }
    }

    @SuppressWarnings({"unused", "Duplicates"})
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   V thirdData,  Runnable thirdBranch,
                                   V fourthData, Runnable fourthBranch,
                                   V fifthData,  Runnable fifthBranch,
                                   V sixthData,  Runnable sixthBranch,
                                   Class<Var> varClass, Consumer<V> varBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(),
                                   firstData.getClass(), secondData.getClass(),
                                   thirdData.getClass(), fourthData.getClass(),
                                   fifthData.getClass(), sixthData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            firstBranch.run();
        } else if (value.equals(secondData)) {
            secondBranch.run();
        } else if (value.equals(thirdData)) {
            thirdBranch.run();
        } else if (value.equals(fourthData)) {
            fourthBranch.run();
        } else if (value.equals(fifthData)) {
            fifthBranch.run();
        } else if (value.equals(sixthData)) {
            sixthBranch.run();
        } else {
            varBranch.accept(value);
        }
    }

    @SuppressWarnings("unused")
    public static <V> void matches(V value,
                                  V firstData,  Runnable firstBranch,
                                  V secondData, Runnable secondBranch,
                                  V thirdData,  Runnable thirdBranch,
                                  V fourthData, Runnable fourthBranch,
                                  V fifthData,  Runnable fifthBranch,
                                  V sixthData,  Runnable sixthBranch,
                                  Class<Null> nullClass, Runnable nullBranch,
                                  Class<Default> defaultClass, Runnable defaultBranch) throws PatternException {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                           fourthData, fourthBranch,
                           fifthData, fifthBranch,
                           sixthData, sixthBranch,
                    defaultClass, defaultBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   V thirdData,  Runnable thirdBranch,
                                   V fourthData, Runnable fourthBranch,
                                   V fifthData,  Runnable fifthBranch,
                                   V sixthData,  Runnable sixthBranch,
                                   Class<Null> nullClass, Runnable nullBranch,
                                   Class<Var> varClass, Consumer<V> varBranch) throws PatternException {
        if (value == null) {
            nullBranch.run();
        } else {
            matches(value, firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                           fourthData, fourthBranch,
                           fifthData, fifthBranch,
                           sixthData, sixthBranch,
                    varClass, varBranch);
        }
    }

    @SuppressWarnings("Duplicates")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                V thirdData,  Supplier<V> thirdBranch,
                                V fourthData, Supplier<V> fourthBranch,
                                V fifthData,  Supplier<V> fifthBranch,
                                V sixthData,  Supplier<V> sixthBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(),
                                   firstData.getClass(), secondData.getClass(),
                                   thirdData.getClass(), fourthData.getClass(),
                                   fifthData.getClass(), sixthData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            return firstBranch.get();
        } else if (value.equals(secondData)) {
            return secondBranch.get();
        } else if (value.equals(thirdData)) {
            return thirdBranch.get();
        } else if (value.equals(fourthData)) {
            return fourthBranch.get();
        } else if (value.equals(fifthData)) {
            return fifthBranch.get();
        } else if (value.equals(sixthData)) {
            return sixthBranch.get();
        }

        return null;
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                V thirdData,  Supplier<V> thirdBranch,
                                V fourthData, Supplier<V> fourthBranch,
                                V fifthData,  Supplier<V> fifthBranch,
                                V sixthData,  Supplier<V> sixthBranch,
                                Class<Default> defaultClass, Supplier<V> defaultBranch) throws PatternException {
        V result = matches(value,
                           firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                           fourthData, fourthBranch,
                           fifthData,  fifthBranch,
                           sixthData,  sixthBranch);
        if (result != null) {
            return result;
        } else {
            return defaultBranch.get();
        }
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                V thirdData,  Supplier<V> thirdBranch,
                                V fourthData, Supplier<V> fourthBranch,
                                V fifthData,  Supplier<V> fifthBranch,
                                V sixthData,  Supplier<V> sixthBranch,
                                Class<Var> varClass, UnaryOperator<V> varBranch) throws PatternException {
        V result = matches(value,
                           firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                           fourthData, fourthBranch,
                           fifthData,  fifthBranch,
                           sixthData,  sixthBranch);
        if (result != null) {
            return result;
        } else {
            return varBranch.apply(value);
        }
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                V thirdData,  Supplier<V> thirdBranch,
                                V fourthData, Supplier<V> fourthBranch,
                                V fifthData,  Supplier<V> fifthBranch,
                                V sixthData,  Supplier<V> sixthBranch,
                                Class<Null> nullClass, Supplier<V> nullBranch,
                                Class<Default> defaultClass, Supplier<V> defaultBranch) throws PatternException {
        if (value == null) {
            return nullBranch.get();
        } else {
            return matches(value, firstData,  firstBranch,
                                  secondData, secondBranch,
                                  thirdData,  thirdBranch,
                                  fourthData, fourthBranch,
                                  fifthData,  fifthBranch,
                                  sixthData,  sixthBranch,
                           defaultClass, defaultBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V> V matches(V value,
                                V firstData,  Supplier<V> firstBranch,
                                V secondData, Supplier<V> secondBranch,
                                V thirdData,  Supplier<V> thirdBranch,
                                V fourthData, Supplier<V> fourthBranch,
                                V fifthData,  Supplier<V> fifthBranch,
                                V sixthData,  Supplier<V> sixthBranch,
                                Class<Null> nullClass, Supplier<V> nullBranch,
                                Class<Var> varClass, UnaryOperator<V> varBranch) throws PatternException {
        if (value == null) {
            return nullBranch.get();
        } else {
            return matches(value, firstData,  firstBranch,
                                  secondData, secondBranch,
                                  thirdData,  thirdBranch,
                                  fourthData, fourthBranch,
                                  fifthData,  fifthBranch,
                                  sixthData,  sixthBranch,
                            varClass, varBranch);
        }
    }
}
