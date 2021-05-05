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
package org.kl.jpml.pattern;

import org.kl.jpml.error.PatternException;
import org.kl.jpml.reflect.Reflection;
import org.kl.jpml.state.Else;
import org.kl.jpml.state.Null;
import org.kl.jpml.state.Var;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/*
 * Constant pattern allow test for equality with constants.
 * Maximum number of branches for match six.
 */
public final class ConstantPattern {
    private static ConstantPattern instance;

    private static Object value;

    private ConstantPattern() {}

    public static <V> ConstantPattern match(V other) {
        value = other;

        if (instance == null) {
            instance = new ConstantPattern();
        }

        return instance;
    }

    public static <V> void match(V value, V data, Runnable firstBranch)  {
        if (!Reflection.checkTypes(data.getClass(), value.getClass())) {
            throw new PatternException("Type in brunches must to be equals");
        }

        if (value.equals(data)) {
            firstBranch.run();
        }
    }

    public <V> void as(V data, Runnable firstBranch)  {
        match(value, data, firstBranch);
    }

    @SuppressWarnings("unused")
    public static <V> void match(V value,
                                 V data, Runnable firstBranch,
                                 Class<Else> defaultClass, Runnable defaultBranch)  {
        if (!Reflection.checkTypes(value.getClass(), data.getClass())) {
            throw new PatternException("Type in brunches must to be equals");
        }

        if (value.equals(data)) {
            firstBranch.run();
        } else {
            defaultBranch.run();
        }
    }

    public <V> void as(V data, Runnable firstBranch,
                       Class<Else> defaultClass, Runnable defaultBranch)  {
        match(value,
                data, firstBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings("unused")
    public static <V> void match(V value,
                                 V data, Runnable firstBranch,
                                 Class<Var> varClass, Consumer<V> varBranch)  {
        if (!Reflection.checkTypes(value.getClass(), data.getClass())) {
            throw new PatternException("Type in brunches must to be equals");
        }

        if (value.equals(data)) {
            firstBranch.run();
        } else {
            varBranch.accept(value);
        }
    }

    public <V> void as(V data, Runnable firstBranch,
                       Class<Var> varClass, Consumer<V> varBranch)  {
        match((V) value,
                data, firstBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("unused")
    public static <V> void match(V value,
                                 V data, Runnable firstBranch,
                                 Class<Null> nullClass, Runnable nullBranch,
                                 Class<Else> defaultClass, Runnable defaultBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            match(value, data, firstBranch, defaultClass, defaultBranch);
        }
    }

    public <V> void as(V data, Runnable firstBranch,
                       Class<Null> nullClass, Runnable nullBranch,
                       Class<Else> defaultClass, Runnable defaultBranch)  {
        match((V) value,
                data, firstBranch,
                nullClass, nullBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings("unused")
    public static <V> void match(V value,
                                 V data, Runnable firstBranch,
                                 Class<Null> nullClass, Runnable nullBranch,
                                 Class<Var>  varClass, Consumer<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            match(value, data, firstBranch, varClass, varBranch);
        }
    }

    public <V> void as(V data, Runnable firstBranch,
                       Class<Null> nullClass, Runnable nullBranch,
                       Class<Var>  varClass,  Consumer<V> varBranch)  {
        match((V) value,
                data, firstBranch,
                nullClass, nullBranch,
                varClass, varBranch);
    }

    public static <V> V match(V value,
                              V data, Supplier<V> firstBranch)  {
        if (!Reflection.checkTypes(value.getClass(), data.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(data)) {
            return firstBranch.get();
        }

        return null;
    }

    public <V> V as(V data, Supplier<V> firstBranch)  {
        return match((V) value,
                data, firstBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V data, Supplier<V> firstBranch,
                              Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        V result = match(value, data, firstBranch);

        if (result != null) {
            return result;
        } else {
            return defaultBranch.get();
        }
    }

    public <V> V as(V data, Supplier<V> firstBranch,
                    Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        return match((V) value,
                data, firstBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V data, Supplier<V> firstBranch,
                              Class<Var> varClass, UnaryOperator<V> varBranch)  {
        V result = match(value, data, firstBranch);

        if (result != null) {
            return result;
        } else {
            return varBranch.apply(value);
        }
    }

    public <V> V as(V data, Supplier<V> firstBranch,
                    Class<Var> varClass, UnaryOperator<V> varBranch)  {
        return match((V) value,
                data, firstBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V data, Supplier<V> firstBranch,
                              Class<Null> nullClass, Supplier<V> nullBranch,
                              Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        if (value == null) {
            return nullBranch.get();
        } else {
            return match(value, data, firstBranch, defaultClass, defaultBranch);
        }
    }

    public <V> V as(V data, Supplier<V> firstBranch,
                    Class<Null> nullClass, Supplier<V> nullBranch,
                    Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        return match((V) value,
                data, firstBranch,
                nullClass, nullBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V data, Supplier<V> firstBranch,
                              Class<Null> nullClass, Supplier<V> nullBranch,
                              Class<Var> varClass, UnaryOperator<V> varBranch)  {
        if (value == null) {
            return nullBranch.get();
        } else {
            return match(value, data, firstBranch, varClass, varBranch);
        }
    }

    public <V> V as(V data, Supplier<V> firstBranch,
                    Class<Null> nullClass, Supplier<V> nullBranch,
                    Class<Var> varClass, UnaryOperator<V> varBranch)  {
        return match((V) value,
                data, firstBranch,
                nullClass, nullBranch,
                varClass, varBranch);
    }

    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch)  {
        if (!Reflection.checkTypes(value.getClass(), firstData.getClass(), secondData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            firstBranch.run();
        } else if (value.equals(secondData)) {
            secondBranch.run();
        }
    }

    public <V> void as(V firstData, Runnable firstBranch,
                       V secondData, Runnable secondBranch)  {
        match(value,
                firstData, firstBranch,
                secondData, secondBranch);
    }

    @SuppressWarnings({"unused", "Duplicates"})
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 Class<Else> defaultClass, Runnable defaultBranch)  {
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

    public <V> void as(V firstData, Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       Class<Else> defaultClass, Runnable defaultBranch)  {
        match(value,
                firstData, firstBranch,
                secondData, secondBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings({"unused", "Duplicates"})
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 Class<Var> varClass, Consumer<V> varBranch)  {
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

    public <V> void as(V firstData,  Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       Class<Var> varClass, Consumer<V> varBranch)  {
        match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("unused")
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 Class<Null> nullClass, Runnable nullBranch,
                                 Class<Else> defaultClass, Runnable defaultBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            match(value, firstData,  firstBranch,
                           secondData, secondBranch,
                    defaultClass, defaultBranch);
        }
    }

    public <V> void as(V firstData, Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       Class<Null> nullClass, Runnable nullBranch,
                       Class<Else> defaultClass, Runnable defaultBranch)  {
        match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                nullClass, nullBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings("unused")
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 Class<Null> nullClass, Runnable nullBranch,
                                 Class<Var> varClass, Consumer<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            match(value, firstData,  firstBranch,
                           secondData, secondBranch,
                    varClass, varBranch);
        }
    }

    public <V> void as(V firstData, Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       Class<Null> nullClass, Runnable nullBranch,
                       Class<Var>  varClass,  Consumer<V> varBranch)  {
        match((V) value,
                firstData,  firstBranch,
                secondData, secondBranch,
                nullClass, nullBranch,
                varClass, varBranch);
    }

    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch)  {
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

    public <V> V as(V firstData,  Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch)  {
        return match((V) value,
                        firstData, firstBranch,
                        secondData, secondBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        V result = match(value,
                           firstData,  firstBranch,
                           secondData, secondBranch);

        if (result != null) {
            return result;
        } else {
            return defaultBranch.get();
        }
    }

    public <V> V as(V firstData, Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        return match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              Class<Var> varClass, UnaryOperator<V> varBranch)  {
        V result = match(value,
                           firstData,  firstBranch,
                           secondData, secondBranch);

        if (result != null) {
            return result;
        } else {
            return varBranch.apply(value);
        }
    }

    public <V> V as(V firstData,  Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    Class<Var> varClass, UnaryOperator<V> varBranch)  {
        return match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              Class<Null> nullClass, Supplier<V> nullBranch,
                              Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        if (value == null) {
            return nullBranch.get();
        } else {
            return match(value, firstData,  firstBranch,
                                  secondData, secondBranch,
                           defaultClass, defaultBranch);
        }
    }

    public <V> V as(V firstData, Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    Class<Null> nullClass, Supplier<V> nullBranch,
                    Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        return match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                nullClass, nullBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              Class<Null> nullClass, Supplier<V> nullBranch,
                              Class<Var> varClass, UnaryOperator<V> varBranch)  {
        if (value == null) {
            return nullBranch.get();
        } else {
            return match(value, firstData,  firstBranch,
                                  secondData, secondBranch,
                           varClass, varBranch);
        }
    }

    public <V> V as(V firstData,  Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    Class<Null> nullClass, Supplier<V> nullBranch,
                    Class<Var> varClass, UnaryOperator<V> varBranch)  {
        return match((V) value,
                firstData,  firstBranch,
                secondData, secondBranch,
                nullClass, nullBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 V thirdData, Runnable thirdBranch)  {
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

    public <V> void as(V firstData, Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       V thirdData, Runnable thirdBranch)  {
        match(value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch);
    }

    @SuppressWarnings({"unused", "Duplicates"})
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 V thirdData, Runnable thirdBranch,
                                 Class<Else> defaultClass, Runnable defaultBranch)  {
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

    public <V> void as(V firstData, Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       V thirdData, Runnable thirdBranch,
                       Class<Else> defaultClass, Runnable defaultBranch)  {
        match(value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings({"unused", "Duplicates"})
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 V thirdData, Runnable thirdBranch,
                                 Class<Var> varClass, Consumer<V> varBranch)  {
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

    public <V> void as(V firstData,  Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       V thirdData,  Runnable thirdBranch,
                       Class<Var> varClass, Consumer<V> varBranch)  {
        match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("unused")
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 V thirdData, Runnable thirdBranch,
                                 Class<Null> nullClass, Runnable nullBranch,
                                 Class<Else> defaultClass, Runnable defaultBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            match(value, firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                    defaultClass, defaultBranch);
        }
    }

    public <V> void as(V firstData, Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       V thirdData, Runnable thirdBranch,
                       Class<Null> nullClass, Runnable nullBranch,
                       Class<Else> defaultClass, Runnable defaultBranch)  {
        match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                nullClass, nullBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings("unused")
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 V thirdData, Runnable thirdBranch,
                                 Class<Null> nullClass, Runnable nullBranch,
                                 Class<Var> varClass, Consumer<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            match(value, firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                    varClass, varBranch);
        }
    }

    public <V> void as(V firstData, Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       V thirdData,  Runnable thirdBranch,
                       Class<Null> nullClass, Runnable nullBranch,
                       Class<Var>  varClass,  Consumer<V> varBranch)  {
        match((V) value,
                firstData,  firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                nullClass, nullBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              V thirdData, Supplier<V> thirdBranch)  {
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

    public <V> V as(V firstData,  Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    V thirdData, Supplier<V> thirdBranch)  {
        return match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              V thirdData, Supplier<V> thirdBranch,
                              Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        V result = match(value,
                           firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch);

        if (result != null) {
            return result;
        } else {
            return defaultBranch.get();
        }
    }

    public <V> V as(V firstData, Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    V thirdData, Supplier<V> thirdBranch,
                    Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        return match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              V thirdData, Supplier<V> thirdBranch,
                              Class<Var> varClass, UnaryOperator<V> varBranch)  {
        V result = match(value,
                           firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch);

        if (result != null) {
            return result;
        } else {
            return varBranch.apply(value);
        }
    }

    public <V> V as(V firstData,  Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    V thirdData,  Supplier<V> thirdBranch,
                    Class<Var> varClass, UnaryOperator<V> varBranch)  {
        return match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              V thirdData, Supplier<V> thirdBranch,
                              Class<Null> nullClass, Supplier<V> nullBranch,
                              Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        if (value == null) {
            return nullBranch.get();
        } else {
            return match(value, firstData,  firstBranch,
                                  secondData, secondBranch,
                                  thirdData,  thirdBranch,
                           defaultClass, defaultBranch);
        }
    }

    public <V> V as(V firstData, Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    V thirdData, Supplier<V> thirdBranch,
                    Class<Null> nullClass, Supplier<V> nullBranch,
                    Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        return match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                nullClass, nullBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              V thirdData, Supplier<V> thirdBranch,
                              Class<Null> nullClass, Supplier<V> nullBranch,
                              Class<Var> varClass, UnaryOperator<V> varBranch)  {
        if (value == null) {
            return nullBranch.get();
        } else {
            return match(value, firstData,  firstBranch,
                                  secondData, secondBranch,
                                  thirdData,  thirdBranch,
                           varClass, varBranch);
        }
    }

    public <V> V as(V firstData,  Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    V thirdData,  Supplier<V> thirdBranch,
                    Class<Null> nullClass, Supplier<V> nullBranch,
                    Class<Var> varClass, UnaryOperator<V> varBranch)  {
        return match((V) value,
                firstData,  firstBranch,
                secondData, secondBranch,
                thirdData,  thirdBranch,
                nullClass, nullBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 V thirdData, Runnable thirdBranch,
                                 V fourthData, Runnable fourthBranch)  {
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

    public <V> void as(V firstData, Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       V thirdData, Runnable thirdBranch,
                       V fourthData, Runnable fourthBranch)  {
        match(value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch);
    }

    @SuppressWarnings({"unused", "Duplicates"})
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 V thirdData, Runnable thirdBranch,
                                 V fourthData, Runnable fourthBranch,
                                 Class<Else> defaultClass, Runnable defaultBranch)  {
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

    public <V> void as(V firstData, Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       V thirdData, Runnable thirdBranch,
                       V fourthData, Runnable fourthBranch,
                       Class<Else> defaultClass, Runnable defaultBranch)  {
        match(value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings({"unused", "Duplicates"})
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 V thirdData, Runnable thirdBranch,
                                 V fourthData, Runnable fourthBranch,
                                 Class<Var> varClass, Consumer<V> varBranch)  {
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

    public <V> void as(V firstData,  Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       V thirdData,  Runnable thirdBranch,
                       V fourthData, Runnable fourthBranch,
                       Class<Var> varClass, Consumer<V> varBranch)  {
        match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("unused")
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 V thirdData, Runnable thirdBranch,
                                 V fourthData, Runnable fourthBranch,
                                 Class<Null> nullClass, Runnable nullBranch,
                                 Class<Else> defaultClass, Runnable defaultBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            match(value, firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                           fourthData, fourthBranch,
                    defaultClass, defaultBranch);
        }
    }

    public <V> void as(V firstData, Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       V thirdData, Runnable thirdBranch,
                       V fourthData, Runnable fourthBranch,
                       Class<Null> nullClass, Runnable nullBranch,
                       Class<Else> defaultClass, Runnable defaultBranch)  {
        match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                nullClass, nullBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings("unused")
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 V thirdData, Runnable thirdBranch,
                                 V fourthData, Runnable fourthBranch,
                                 Class<Null> nullClass, Runnable nullBranch,
                                 Class<Var> varClass, Consumer<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            match(value, firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                           fourthData, fourthBranch,
                    varClass, varBranch);
        }
    }

    public <V> void as(V firstData, Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       V thirdData,  Runnable thirdBranch,
                       V fourthData, Runnable fourthBranch,
                       Class<Null> nullClass, Runnable nullBranch,
                       Class<Var>  varClass,  Consumer<V> varBranch)  {
        match((V) value,
                firstData,  firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                nullClass, nullBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              V thirdData, Supplier<V> thirdBranch,
                              V fourthData, Supplier<V> fourthBranch)  {
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

    public <V> V as(V firstData,  Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    V thirdData, Supplier<V> thirdBranch,
                    V fourthData, Supplier<V> fourthBranch)  {
        return match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              V thirdData, Supplier<V> thirdBranch,
                              V fourthData, Supplier<V> fourthBranch,
                              Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        V result = match(value,
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

    public <V> V as(V firstData, Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    V thirdData, Supplier<V> thirdBranch,
                    V fourthData, Supplier<V> fourthBranch,
                    Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        return match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              V thirdData, Supplier<V> thirdBranch,
                              V fourthData, Supplier<V> fourthBranch,
                              Class<Var> varClass, UnaryOperator<V> varBranch)  {
        V result = match(value,
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

    public <V> V as(V firstData,  Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    V thirdData,  Supplier<V> thirdBranch,
                    V fourthData, Supplier<V> fourthBranch,
                    Class<Var> varClass, UnaryOperator<V> varBranch)  {
        return match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              V thirdData, Supplier<V> thirdBranch,
                              V fourthData, Supplier<V> fourthBranch,
                              Class<Null> nullClass, Supplier<V> nullBranch,
                              Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        if (value == null) {
            return nullBranch.get();
        } else {
            return match(value, firstData,  firstBranch,
                                  secondData, secondBranch,
                                  thirdData,  thirdBranch,
                                  fourthData, fourthBranch,
                           defaultClass, defaultBranch);
        }
    }

    public <V> V as(V firstData, Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    V thirdData, Supplier<V> thirdBranch,
                    V fourthData, Supplier<V> fourthBranch,
                    Class<Null> nullClass, Supplier<V> nullBranch,
                    Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        return match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                nullClass, nullBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              V thirdData, Supplier<V> thirdBranch,
                              V fourthData, Supplier<V> fourthBranch,
                              Class<Null> nullClass, Supplier<V> nullBranch,
                              Class<Var> varClass, UnaryOperator<V> varBranch)  {
        if (value == null) {
            return nullBranch.get();
        } else {
            return match(value, firstData,  firstBranch,
                                  secondData, secondBranch,
                                  thirdData,  thirdBranch,
                                  fourthData, fourthBranch,
                           varClass, varBranch);
        }
    }

    public <V> V as(V firstData,  Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    V thirdData,  Supplier<V> thirdBranch,
                    V fourthData, Supplier<V> fourthBranch,
                    Class<Null> nullClass, Supplier<V> nullBranch,
                    Class<Var> varClass, UnaryOperator<V> varBranch)  {
        return match((V) value,
                firstData,  firstBranch,
                secondData, secondBranch,
                thirdData,  thirdBranch,
                fourthData, fourthBranch,
                nullClass, nullBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 V thirdData, Runnable thirdBranch,
                                 V fourthData, Runnable fourthBranch,
                                 V fifthData, Runnable fifthBranch)  {
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

    public <V> void as(V firstData, Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       V thirdData, Runnable thirdBranch,
                       V fourthData, Runnable fourthBranch,
                       V fifthData, Runnable fifthBranch)  {
        match(value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                fifthData, fifthBranch);
    }

    @SuppressWarnings({"unused", "Duplicates"})
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 V thirdData, Runnable thirdBranch,
                                 V fourthData, Runnable fourthBranch,
                                 V fifthData, Runnable fifthBranch,
                                 Class<Else> defaultClass, Runnable defaultBranch)  {
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

    public <V> void as(V firstData, Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       V thirdData, Runnable thirdBranch,
                       V fourthData, Runnable fourthBranch,
                       V fifthData, Runnable fifthBranch,
                       Class<Else> defaultClass, Runnable defaultBranch)  {
        match(value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                fifthData, fifthBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings({"unused", "Duplicates"})
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 V thirdData, Runnable thirdBranch,
                                 V fourthData, Runnable fourthBranch,
                                 V fifthData, Runnable fifthBranch,
                                 Class<Var> varClass, Consumer<V> varBranch)  {
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

    public <V> void as(V firstData,  Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       V thirdData,  Runnable thirdBranch,
                       V fourthData, Runnable fourthBranch,
                       V fifthData,  Runnable fifthBranch,
                       Class<Var> varClass, Consumer<V> varBranch)  {
        match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                fifthData, fifthBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("unused")
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 V thirdData, Runnable thirdBranch,
                                 V fourthData, Runnable fourthBranch,
                                 V fifthData, Runnable fifthBranch,
                                 Class<Null> nullClass, Runnable nullBranch,
                                 Class<Else> defaultClass, Runnable defaultBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            match(value, firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                           fourthData, fourthBranch,
                           fifthData, fifthBranch,
                    defaultClass, defaultBranch);
        }
    }

    @SuppressWarnings("unused")
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 V thirdData, Runnable thirdBranch,
                                 V fourthData, Runnable fourthBranch,
                                 V fifthData, Runnable fifthBranch,
                                 Class<Null> nullClass, Runnable nullBranch,
                                 Class<Var> varClass, Consumer<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            match(value, firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                           fourthData, fourthBranch,
                           fifthData, fifthBranch,
                    varClass, varBranch);
        }
    }

    public <V> void as(V firstData, Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       V thirdData,  Runnable thirdBranch,
                       V fourthData, Runnable fourthBranch,
                       V fifthData,  Runnable fifthBranch,
                       Class<Null> nullClass, Runnable nullBranch,
                       Class<Var>  varClass,  Consumer<V> varBranch)  {
        match((V) value,
                firstData,  firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                fifthData, fifthBranch,
                nullClass, nullBranch,
                varClass, varBranch);
    }

    public <V> void as(V firstData, Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       V thirdData, Runnable thirdBranch,
                       V fourthData, Runnable fourthBranch,
                       V fifthData,  Runnable fifthBranch,
                       Class<Null> nullClass, Runnable nullBranch,
                       Class<Else> defaultClass, Runnable defaultBranch)  {
        match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                fifthData, fifthBranch,
                nullClass, nullBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              V thirdData, Supplier<V> thirdBranch,
                              V fourthData, Supplier<V> fourthBranch,
                              V fifthData, Supplier<V> fifthBranch)  {
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

    public <V> V as(V firstData,  Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    V thirdData, Supplier<V> thirdBranch,
                    V fourthData, Supplier<V> fourthBranch,
                    V fifthData,  Supplier<V> fifthBranch)  {
        return match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                fifthData, fifthBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              V thirdData, Supplier<V> thirdBranch,
                              V fourthData, Supplier<V> fourthBranch,
                              V fifthData, Supplier<V> fifthBranch,
                              Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        V result = match(value,
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

    public <V> V as(V firstData, Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    V thirdData, Supplier<V> thirdBranch,
                    V fourthData, Supplier<V> fourthBranch,
                    V fifthData, Supplier<V> fifthBranch,
                    Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        return match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                fifthData, fifthBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              V thirdData, Supplier<V> thirdBranch,
                              V fourthData, Supplier<V> fourthBranch,
                              V fifthData, Supplier<V> fifthBranch,
                              Class<Var> varClass, UnaryOperator<V> varBranch)  {
        V result = match(value,
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

    public <V> V as(V firstData,  Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    V thirdData,  Supplier<V> thirdBranch,
                    V fourthData, Supplier<V> fourthBranch,
                    V fifthData,  Supplier<V> fifthBranch,
                    Class<Var> varClass, UnaryOperator<V> varBranch)  {
        return match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                fifthData, fifthBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              V thirdData, Supplier<V> thirdBranch,
                              V fourthData, Supplier<V> fourthBranch,
                              V fifthData, Supplier<V> fifthBranch,
                              Class<Null> nullClass, Supplier<V> nullBranch,
                              Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        if (value == null) {
            return nullBranch.get();
        } else {
            return match(value, firstData,  firstBranch,
                                  secondData, secondBranch,
                                  thirdData,  thirdBranch,
                                  fourthData, fourthBranch,
                                  fifthData,  fifthBranch,
                           defaultClass, defaultBranch);
        }
    }

    public <V> V as(V firstData, Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    V thirdData, Supplier<V> thirdBranch,
                    V fourthData, Supplier<V> fourthBranch,
                    V fifthData, Supplier<V> fifthBranch,
                    Class<Null> nullClass, Supplier<V> nullBranch,
                    Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        return match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                fifthData, fifthBranch,
                nullClass, nullBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              V thirdData, Supplier<V> thirdBranch,
                              V fourthData, Supplier<V> fourthBranch,
                              V fifthData, Supplier<V> fifthBranch,
                              Class<Null> nullClass, Supplier<V> nullBranch,
                              Class<Var> varClass, UnaryOperator<V> varBranch)  {
        if (value == null) {
            return nullBranch.get();
        } else {
            return match(value, firstData,  firstBranch,
                                  secondData, secondBranch,
                                  thirdData,  thirdBranch,
                                  fourthData, fourthBranch,
                                  fifthData,  fifthBranch,
                           varClass, varBranch);
        }
    }

    public <V> V as(V firstData,  Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    V thirdData,  Supplier<V> thirdBranch,
                    V fourthData, Supplier<V> fourthBranch,
                    V fifthData,  Supplier<V> fifthBranch,
                    Class<Null> nullClass, Supplier<V> nullBranch,
                    Class<Var> varClass, UnaryOperator<V> varBranch)  {
        return match((V) value,
                firstData,  firstBranch,
                secondData, secondBranch,
                thirdData,  thirdBranch,
                fourthData, fourthBranch,
                fifthData,  fifthBranch,
                nullClass, nullBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 V thirdData, Runnable thirdBranch,
                                 V fourthData, Runnable fourthBranch,
                                 V fifthData, Runnable fifthBranch,
                                 V sixthData, Runnable sixthBranch)  {
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

    public <V> void as(V firstData, Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       V thirdData, Runnable thirdBranch,
                       V fourthData, Runnable fourthBranch,
                       V fifthData, Runnable fifthBranch,
                       V sixthData, Runnable sixthBranch)  {
        match(value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                fifthData, fifthBranch,
                sixthData, sixthBranch);
    }

    @SuppressWarnings({"unused", "Duplicates"})
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 V thirdData, Runnable thirdBranch,
                                 V fourthData, Runnable fourthBranch,
                                 V fifthData, Runnable fifthBranch,
                                 V sixthData, Runnable sixthBranch,
                                 Class<Else> defaultClass, Runnable defaultBranch)  {
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

    public <V> void as(V firstData, Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       V thirdData, Runnable thirdBranch,
                       V fourthData, Runnable fourthBranch,
                       V fifthData, Runnable fifthBranch,
                       V sixthData, Runnable sixthBranch,
                       Class<Else> defaultClass, Runnable defaultBranch)  {
        match(value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                fifthData, fifthBranch,
                sixthData, sixthBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings({"unused", "Duplicates"})
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 V thirdData, Runnable thirdBranch,
                                 V fourthData, Runnable fourthBranch,
                                 V fifthData, Runnable fifthBranch,
                                 V sixthData, Runnable sixthBranch,
                                 Class<Var> varClass, Consumer<V> varBranch)  {
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

    public <V> void as(V firstData,  Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       V thirdData,  Runnable thirdBranch,
                       V fourthData, Runnable fourthBranch,
                       V fifthData,  Runnable fifthBranch,
                       V sixthData,  Runnable sixthBranch,
                       Class<Var> varClass, Consumer<V> varBranch)  {
        match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                fifthData, fifthBranch,
                sixthData, sixthBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("unused")
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 V thirdData, Runnable thirdBranch,
                                 V fourthData, Runnable fourthBranch,
                                 V fifthData, Runnable fifthBranch,
                                 V sixthData, Runnable sixthBranch,
                                 Class<Null> nullClass, Runnable nullBranch,
                                 Class<Else> defaultClass, Runnable defaultBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            match(value, firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                           fourthData, fourthBranch,
                           fifthData, fifthBranch,
                           sixthData, sixthBranch,
                    defaultClass, defaultBranch);
        }
    }

    public <V> void as(V firstData, Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       V thirdData, Runnable thirdBranch,
                       V fourthData, Runnable fourthBranch,
                       V fifthData,  Runnable fifthBranch,
                       V sixthData, Runnable sixthBranch,
                       Class<Null> nullClass, Runnable nullBranch,
                       Class<Else> defaultClass, Runnable defaultBranch)  {
        match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                fifthData, fifthBranch,
                sixthData, sixthBranch,
                nullClass, nullBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings("unused")
    public static <V> void match(V value,
                                 V firstData, Runnable firstBranch,
                                 V secondData, Runnable secondBranch,
                                 V thirdData, Runnable thirdBranch,
                                 V fourthData, Runnable fourthBranch,
                                 V fifthData, Runnable fifthBranch,
                                 V sixthData, Runnable sixthBranch,
                                 Class<Null> nullClass, Runnable nullBranch,
                                 Class<Var> varClass, Consumer<V> varBranch)  {
        if (value == null) {
            nullBranch.run();
        } else {
            match(value, firstData,  firstBranch,
                           secondData, secondBranch,
                           thirdData,  thirdBranch,
                           fourthData, fourthBranch,
                           fifthData, fifthBranch,
                           sixthData, sixthBranch,
                    varClass, varBranch);
        }
    }

    public <V> void as(V firstData, Runnable firstBranch,
                       V secondData, Runnable secondBranch,
                       V thirdData,  Runnable thirdBranch,
                       V fourthData, Runnable fourthBranch,
                       V fifthData,  Runnable fifthBranch,
                       V sixthData,  Runnable sixthBranch,
                       Class<Null> nullClass, Runnable nullBranch,
                       Class<Var>  varClass,  Consumer<V> varBranch)  {
        match((V) value,
                firstData,  firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                fifthData, fifthBranch,
                sixthData, sixthBranch,
                nullClass, nullBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              V thirdData, Supplier<V> thirdBranch,
                              V fourthData, Supplier<V> fourthBranch,
                              V fifthData, Supplier<V> fifthBranch,
                              V sixthData, Supplier<V> sixthBranch)  {
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

    public <V> V as(V firstData,  Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    V thirdData, Supplier<V> thirdBranch,
                    V fourthData, Supplier<V> fourthBranch,
                    V fifthData,  Supplier<V> fifthBranch,
                    V sixthData,  Supplier<V> sixthBranch)  {
        return match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                fifthData, fifthBranch,
                sixthData, sixthBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              V thirdData, Supplier<V> thirdBranch,
                              V fourthData, Supplier<V> fourthBranch,
                              V fifthData, Supplier<V> fifthBranch,
                              V sixthData, Supplier<V> sixthBranch,
                              Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        V result = match(value,
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

    public <V> V as(V firstData, Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    V thirdData, Supplier<V> thirdBranch,
                    V fourthData, Supplier<V> fourthBranch,
                    V fifthData, Supplier<V> fifthBranch,
                    V sixthData, Supplier<V> sixthBranch,
                    Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        return match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                fifthData, fifthBranch,
                sixthData, sixthBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              V thirdData, Supplier<V> thirdBranch,
                              V fourthData, Supplier<V> fourthBranch,
                              V fifthData, Supplier<V> fifthBranch,
                              V sixthData, Supplier<V> sixthBranch,
                              Class<Var> varClass, UnaryOperator<V> varBranch)  {
        V result = match(value,
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

    public <V> V as(V firstData,  Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    V thirdData,  Supplier<V> thirdBranch,
                    V fourthData, Supplier<V> fourthBranch,
                    V fifthData,  Supplier<V> fifthBranch,
                    V sixthData,  Supplier<V> sixthBranch,
                    Class<Var> varClass, UnaryOperator<V> varBranch)  {
        return match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                fifthData, fifthBranch,
                sixthData, sixthBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              V thirdData, Supplier<V> thirdBranch,
                              V fourthData, Supplier<V> fourthBranch,
                              V fifthData, Supplier<V> fifthBranch,
                              V sixthData, Supplier<V> sixthBranch,
                              Class<Null> nullClass, Supplier<V> nullBranch,
                              Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        if (value == null) {
            return nullBranch.get();
        } else {
            return match(value, firstData,  firstBranch,
                                  secondData, secondBranch,
                                  thirdData,  thirdBranch,
                                  fourthData, fourthBranch,
                                  fifthData,  fifthBranch,
                                  sixthData,  sixthBranch,
                           defaultClass, defaultBranch);
        }
    }

    public <V> V as(V firstData, Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    V thirdData, Supplier<V> thirdBranch,
                    V fourthData, Supplier<V> fourthBranch,
                    V fifthData, Supplier<V> fifthBranch,
                    V sixthData, Supplier<V> sixthBranch,
                    Class<Null> nullClass, Supplier<V> nullBranch,
                    Class<Else> defaultClass, Supplier<V> defaultBranch)  {
        return match((V) value,
                firstData, firstBranch,
                secondData, secondBranch,
                thirdData, thirdBranch,
                fourthData, fourthBranch,
                fifthData, fifthBranch,
                sixthData, sixthBranch,
                nullClass, nullBranch,
                defaultClass, defaultBranch);
    }

    @SuppressWarnings("unused")
    public static <V> V match(V value,
                              V firstData, Supplier<V> firstBranch,
                              V secondData, Supplier<V> secondBranch,
                              V thirdData, Supplier<V> thirdBranch,
                              V fourthData, Supplier<V> fourthBranch,
                              V fifthData, Supplier<V> fifthBranch,
                              V sixthData, Supplier<V> sixthBranch,
                              Class<Null> nullClass, Supplier<V> nullBranch,
                              Class<Var> varClass, UnaryOperator<V> varBranch)  {
        if (value == null) {
            return nullBranch.get();
        } else {
            return match(value, firstData,  firstBranch,
                                  secondData, secondBranch,
                                  thirdData,  thirdBranch,
                                  fourthData, fourthBranch,
                                  fifthData,  fifthBranch,
                                  sixthData,  sixthBranch,
                            varClass, varBranch);
        }
    }

    public <V> V as(V firstData,  Supplier<V> firstBranch,
                    V secondData, Supplier<V> secondBranch,
                    V thirdData,  Supplier<V> thirdBranch,
                    V fourthData, Supplier<V> fourthBranch,
                    V fifthData,  Supplier<V> fifthBranch,
                    V sixthData,  Supplier<V> sixthBranch,
                    Class<Null> nullClass, Supplier<V> nullBranch,
                    Class<Var> varClass, UnaryOperator<V> varBranch)  {
        return match((V) value,
                firstData,  firstBranch,
                secondData, secondBranch,
                thirdData,  thirdBranch,
                fourthData, fourthBranch,
                fifthData,  fifthBranch,
                sixthData,  sixthBranch,
                nullClass, nullBranch,
                varClass, varBranch);
    }
}
