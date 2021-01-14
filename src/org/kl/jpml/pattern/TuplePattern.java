/*
 * JPML - Java pattern matching library.
 *
 * Tuple pattern allow test for equality multiple pieces
 * with constants. Maximum number of branches for match six
 * with fourth pieces.
 */
package org.kl.jpml.pattern;

import org.kl.jpml.error.PatternException;
import org.kl.jpml.lambda.QuarConsumer;
import org.kl.jpml.lambda.TriConsumer;
import org.kl.jpml.reflect.Reflection;
import org.kl.jpml.state.Else;
import org.kl.jpml.state.Var;
import org.kl.jpml.util.Tuple;

import java.util.Collection;
import java.util.function.BiConsumer;

public final class TuplePattern {
    private static Tuple.Tuple2 biData;
    private static Tuple.Tuple3 triData;
    private static Tuple.Tuple4 quarData;
    private static TuplePattern instance;

    private TuplePattern() {}

    public static <V1, V2>
    void foreach(Collection<Tuple.Tuple2<V1, V2>> data, BiConsumer<V1, V2> branch)  {
        for (Tuple.Tuple2<V1, V2> value : data) {
            V1 leftValue = value.first();
            V2 rightValue = value.second();

            branch.accept(leftValue, rightValue);
        }
    }

    public static <V1, V2>
    void let(Tuple.Tuple2<V1, V2> item, BiConsumer<V1, V2> branch)  {
        V1 leftValue = item.first();
        V2 rightValue = item.second();

        branch.accept(leftValue, rightValue);
    }

    public static <V1, V2> TuplePattern matches(V1 leftValue, V2 rightValue) {
        biData = Tuple.of(leftValue, rightValue);

        if (instance == null) {
            instance = new TuplePattern();
        }

        return instance;
    }

    public static <V1, V2, V3>
    void foreach(Collection<Tuple.Tuple3<V1, V2, V3>> data, TriConsumer<V1, V2, V3> branch) {
        for (Tuple.Tuple3<V1, V2, V3> value : data) {
            V1 leftValue = value.first();
            V2 middleValue = value.second();
            V3 rightValue = value.third();

            branch.accept(leftValue, middleValue, rightValue);
        }
    }

    public static <V1, V2, V3>
    void let(Tuple.Tuple3<V1, V2, V3> item, TriConsumer<V1, V2, V3> branch) {
        V1 leftValue = item.first();
        V2 middleValue = item.second();
        V3 rightValue = item.third();

        branch.accept(leftValue, middleValue, rightValue);
    }

    public static <V1, V2, V3> TuplePattern matches(V1 leftValue, V2 middleValue, V3 rightValue) {
        triData = Tuple.of(leftValue, middleValue, rightValue);

        if (instance == null) {
            instance = new TuplePattern();
        }

        return instance;
    }

    public static <V1, V2, V3, V4>
    void foreach(Collection<Tuple.Tuple4<V1, V2, V3, V4>> data, QuarConsumer<V1, V2, V3, V4> branch) {
        for (Tuple.Tuple4<V1, V2, V3, V4> value : data) {
            V1 leftValue = value.first();
            V2 topValue = value.second();
            V3 rightValue = value.third();
            V4 bottomValue = value.fourth();

            branch.accept(leftValue, topValue, rightValue, bottomValue);
        }
    }

    public static <V1, V2, V3, V4>
    void let(Tuple.Tuple4<V1, V2, V3, V4> item, QuarConsumer<V1, V2, V3, V4> branch) {
        V1 leftValue = item.first();
        V2 topValue = item.second();
        V3 rightValue = item.third();
        V4 bottomValue = item.fourth();

        branch.accept(leftValue, topValue, rightValue, bottomValue);
    }

    public static <V1, V2, V3, V4> TuplePattern matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue) {
        quarData = Tuple.of(leftValue, bottomValue, rightValue, topValue);

        if (instance == null) {
            instance = new TuplePattern();
        }

        return instance;
    }

    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData, V2 firstRightData, Runnable firstBranch) {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                     firstLeftData.getClass(), firstRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else {
            throw new PatternException("Statement must to have only one branch");
        }
    }

    public <V1, V2>
    void as(V1 firstLeftData, V2 firstRightData, Runnable firstBranch) {
        matches(biData.first(), biData.second(),
                firstLeftData, firstRightData, firstBranch);
    }

    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 Tuple.Tuple2<V1, V2> item, Runnable firstBranch) {
        matches(leftValue, rightValue,
                (V1) item.first(), (V2) item.second(), firstBranch);
    }

    @SuppressWarnings("unused")
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData, V2 firstRightData, Runnable firstBranch,
                 Class<Else> elseClass, Runnable elseBranch) {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                     firstLeftData.getClass(), firstRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else {
            elseBranch.run();
        }
    }

    public <V1, V2>
    void as(V1 firstLeftData, V2 firstRightData, Runnable firstBranch,
            Class<Else> elseClass, Runnable elseBranch) {
        matches(biData.first(), biData.second(),
                firstLeftData, firstRightData, firstBranch,
                elseClass, elseBranch);
    }

    @SuppressWarnings("unused")
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData, V2 firstRightData, Runnable firstBranch,
                 Class<Var> varClass, BiConsumer<V1, V2> varBranch) {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                     firstLeftData.getClass(), firstRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else {
            varBranch.accept(leftValue, rightValue);
        }
    }

    public <V1, V2>
    void as(V1 firstLeftData, V2 firstRightData, Runnable firstBranch,
            Class<Var> varClass, BiConsumer<V1, V2> varBranch) {
        matches((V1) biData.first(), (V2) biData.second(),
                firstLeftData, firstRightData, firstBranch,
                varClass, varBranch);
    }

    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch) {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(), firstMiddleData.getClass(), firstRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else {
            throw new PatternException("Statement must to have only one branch");
        }
    }

    public <V1, V2, V3>
    void as(V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch) {
        matches(triData.first(), triData.second(), triData.third(),
                firstLeftData, firstMiddleData, firstRightData, firstBranch);
    }

    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 Tuple.Tuple3<V1, V2, V3> item, Runnable firstBranch) {
        matches(leftValue, middleValue, rightValue,
                (V1) item.first(), (V2) item.second(), item.third(), firstBranch
        );
    }

    @SuppressWarnings("unused")
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(), firstMiddleData.getClass(), firstRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else {
            elseBranch.run();
        }
    }

    public <V1, V2, V3>
    void as(V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch,
            Class<Else> elseClass, Runnable elseBranch) {
        matches(triData.first(), triData.second(), triData.third(),
                firstLeftData, firstMiddleData, firstRightData, firstBranch,
                elseClass, elseBranch);
    }

    @SuppressWarnings("unused")
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch,
                 Class<Var> varClass, TriConsumer<V1, V2, V3> varBranch) {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(), firstMiddleData.getClass(), firstRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else {
            varBranch.accept(leftValue, middleValue, firstRightData);
        }
    }

    public <V1, V2, V3>
    void as(V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch,
            Class<Var> varClass, TriConsumer<V1, V2, V3> varBranch) {
        matches((V1) triData.first(), (V2) triData.second(), (V3) triData.third(),
                firstLeftData, firstMiddleData, firstRightData, firstBranch,
                varClass, varBranch);
    }

    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,  V2 firstBottomData,
                 V3 firstRightData, V4 firstTopData, Runnable firstBranch) {
        if (!Reflection.checkQuarTypes(leftValue.getClass(),  bottomValue.getClass(),
                                       rightValue.getClass(), topValue.getClass(),
                                       firstLeftData.getClass(),  firstBottomData.getClass(),
                                       firstRightData.getClass(), firstTopData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && bottomValue.equals(firstBottomData) &&
            rightValue.equals(firstRightData) && topValue.equals(firstTopData)) {
            firstBranch.run();
        } else {
            throw new PatternException("Statement must to have only one branch");
        }
    }

    public <V1, V2, V3, V4>
    void as(V1 firstLeftData,  V2 firstBottomData,
            V3 firstRightData, V4 firstTopData, Runnable firstBranch) {
        matches(quarData.first(), quarData.second(), quarData.third(), quarData.fourth(),
                firstLeftData, firstBottomData, firstRightData, firstTopData, firstBranch);
    }

    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 Tuple.Tuple4<V1, V2, V3, V4> item, Runnable firstBranch) {
        matches(leftValue, bottomValue, rightValue, topValue,
                (V1) item.first(), (V2) item.second(), (V3) item.third(), item.fourth(), firstBranch
        );
    }

    @SuppressWarnings("unused")
    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,  V2 firstBottomData,
                 V3 firstRightData, V4 firstTopData, Runnable firstBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (!Reflection.checkQuarTypes(leftValue.getClass(),  bottomValue.getClass(),
                                       rightValue.getClass(), topValue.getClass(),
                                       firstLeftData.getClass(),  firstBottomData.getClass(),
                                       firstRightData.getClass(), firstTopData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && bottomValue.equals(firstBottomData) &&
            rightValue.equals(firstRightData) && topValue.equals(firstTopData)) {
            firstBranch.run();
        } else {
            elseBranch.run();
        }
    }

    public <V1, V2, V3, V4>
    void as(V1 firstLeftData,  V2 firstBottomData,
            V3 firstRightData, V4 firstTopData, Runnable firstBranch,
            Class<Else> elseClass, Runnable elseBranch) {
        matches(quarData.first(), quarData.second(), quarData.third(), quarData.fourth(),
                firstLeftData, firstBottomData, firstRightData, firstTopData, firstBranch,
                elseClass, elseBranch);
    }

    @SuppressWarnings("unused")
    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,  V2 firstBottomData,
                 V3 firstRightData, V4 firstTopData, Runnable firstBranch,
                 Class<Var> varClass, QuarConsumer<V1, V2, V3, V4> varBranch) {
        if (!Reflection.checkQuarTypes(leftValue.getClass(),  bottomValue.getClass(),
                                       rightValue.getClass(), topValue.getClass(),
                                       firstLeftData.getClass(),  firstBottomData.getClass(),
                                       firstRightData.getClass(), firstTopData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && bottomValue.equals(firstBottomData) &&
                rightValue.equals(firstRightData) && topValue.equals(firstTopData)) {
            firstBranch.run();
        } else {
            varBranch.accept(leftValue, bottomValue, rightValue, topValue);
        }
    }

    public <V1, V2, V3, V4>
    void as(V1 firstLeftData,  V2 firstBottomData,
            V3 firstRightData, V4 firstTopData, Runnable firstBranch,
            Class<Var> varClass, QuarConsumer<V1, V2, V3, V4> varBranch) {
        matches((V1) quarData.first(), (V2) quarData.second(), (V3) quarData.third(), (V4) quarData.fourth(),
                firstLeftData, firstBottomData, firstRightData, firstTopData, firstBranch,
                varClass, varBranch);
    }

    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondRightData, Runnable secondBranch) {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                     firstLeftData.getClass(),  firstRightData.getClass(),
                                     secondLeftData.getClass(), secondRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else {
            throw new PatternException("Statement must to have only two branches");
        }
    }

    public <V1, V2>
    void as(V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
            V1 secondLeftData, V2 secondRightData, Runnable secondBranch) {
        matches(biData.first(), biData.second(),
                firstLeftData, firstRightData, firstBranch,
                secondLeftData, secondRightData, secondBranch);
    }

    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 Tuple.Tuple2<V1, V2> firstItem, Runnable firstBranch,
                 Tuple.Tuple2<V1, V2>  secondItem, Runnable secondBranch) {
        matches(leftValue, rightValue,
                firstItem.first(), firstItem.second(), firstBranch,
                secondItem.first(), secondItem.second(), secondBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                 Class<Else> elseClass, Runnable elseBranch) {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                     firstLeftData.getClass(),  firstRightData.getClass(),
                                     secondLeftData.getClass(), secondRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else {
            elseBranch.run();
        }
    }

    public <V1, V2>
    void as(V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
            V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
            Class<Else> elseClass, Runnable elseBranch) {
        matches(biData.first(), biData.second(),
                firstLeftData, firstRightData, firstBranch,
                secondLeftData, secondRightData, secondBranch,
                elseClass, elseBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                 Class<Var> varClass, BiConsumer<V1, V2> varBranch) {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                    firstLeftData.getClass(),  firstRightData.getClass(),
                                    secondLeftData.getClass(), secondRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else {
            varBranch.accept(leftValue, rightValue);
        }
    }

    public <V1, V2>
    void as(V1 firstLeftData, V2 firstRightData, Runnable firstBranch,
            V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
            Class<Var> varClass, BiConsumer<V1, V2> varBranch) {
        matches((V1) biData.first(), (V2) biData.second(),
                firstLeftData, firstRightData, firstBranch,
                secondLeftData, secondRightData, secondBranch,
                varClass, varBranch);
    }

    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch) {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(),  firstMiddleData.getClass(),  firstRightData.getClass(),
                                       secondLeftData.getClass(), secondMiddleData.getClass(), secondRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else {
            throw new PatternException("Statement must to have only two branches");
        }
    }

    public <V1, V2, V3>
    void as(V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch,
            V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch) {
        matches(triData.first(), triData.second(), triData.third(),
                firstLeftData, firstMiddleData, firstRightData, firstBranch,
                secondLeftData, secondMiddleData, secondRightData, secondBranch);
    }

    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 Tuple.Tuple3<V1, V2, V3> firstItem, Runnable firstBranch,
                 Tuple.Tuple3<V1, V2, V3> secondItem, Runnable secondBranch) {
        matches(leftValue, middleValue, rightValue,
                (V1) firstItem.first(), firstItem.second(), firstItem.third(), firstBranch,
                (V1) secondItem.first(), (V2) secondItem.second(), secondItem.third(), secondBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 Class<Else> elseClass, Runnable elseBranch) {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(),  firstMiddleData.getClass(),  firstRightData.getClass(),
                                       secondLeftData.getClass(), secondMiddleData.getClass(), secondRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else {
            elseBranch.run();
        }
    }

    public <V1, V2, V3>
    void as(V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch,
            V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
            Class<Else> elseClass, Runnable elseBranch) {
        matches(triData.first(), triData.second(), triData.third(),
                firstLeftData, firstMiddleData, firstRightData, firstBranch,
                secondLeftData, secondMiddleData, secondRightData, secondBranch,
                elseClass, elseBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 Class<Var> varClass, TriConsumer<V1, V2, V3> varBranch) {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(),  firstMiddleData.getClass(),  firstRightData.getClass(),
                                       secondLeftData.getClass(), secondMiddleData.getClass(), secondRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else {
            varBranch.accept(leftValue, middleValue, rightValue);
        }
    }

    public <V1, V2, V3>
    void as(V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch,
            V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
            Class<Var> varClass, TriConsumer<V1, V2, V3> varBranch) {
        matches((V1) triData.first(), (V2) triData.second(), (V3) triData.third(),
                firstLeftData, firstMiddleData, firstRightData, firstBranch,
                secondLeftData, secondMiddleData, secondRightData, secondBranch,
                varClass, varBranch);
    }

    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,   V2 firstBottomData,
                 V3 firstRightData,  V4 firstTopData,  Runnable firstBranch,
                 V1 secondLeftData,  V2 secondBottomData,
                 V3 secondRightData, V4 secondTopData, Runnable secondBranch)  {
        if (!Reflection.checkQuarTypes(leftValue.getClass(),  bottomValue.getClass(),
                                       rightValue.getClass(), topValue.getClass(),
                                       firstLeftData.getClass(),  firstBottomData.getClass(),
                                       firstRightData.getClass(), firstTopData.getClass(),
                                       secondLeftData.getClass(), secondBottomData.getClass(),
                                       secondRightData.getClass(),secondTopData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && bottomValue.equals(firstBottomData) &&
            rightValue.equals(firstRightData) && topValue.equals(firstTopData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && bottomValue.equals(secondBottomData) &&
            rightValue.equals(secondRightData) && topValue.equals(secondTopData)) {
            secondBranch.run();
        } else {
            throw new PatternException("Statement must to have only two branches");
        }
    }

    public <V1, V2, V3, V4>
    void as(V1 firstLeftData,  V2 firstBottomData,
            V3 firstRightData, V4 firstTopData, Runnable firstBranch,
            V1 secondLeftData,  V2 secondBottomData,
            V3 secondRightData, V4 secondTopData, Runnable secondBranch) {
        matches(quarData.first(), quarData.second(), quarData.third(), quarData.fourth(),
                firstLeftData, firstBottomData, firstRightData, firstTopData, firstBranch,
                secondLeftData, secondBottomData, secondRightData, secondTopData, secondBranch);
    }

    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 Tuple.Tuple4<V1, V2, V3, V4> firstItem, Runnable firstBranch,
                 Tuple.Tuple4<V1, V2, V3, V4> secondItem, Runnable secondBranch) {
        matches(leftValue, bottomValue, rightValue, topValue,
                (V1) firstItem.first(), (V2) firstItem.second(), firstItem.third(), firstItem.fourth(), firstBranch,
                (V1) secondItem.first(), (V2) secondItem.second(), secondItem.third(), firstItem.fourth(), secondBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,   V2 firstBottomData,
                 V3 firstRightData,  V4 firstTopData,  Runnable firstBranch,
                 V1 secondLeftData,  V2 secondBottomData,
                 V3 secondRightData, V4 secondTopData, Runnable secondBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (!Reflection.checkQuarTypes(leftValue.getClass(),  bottomValue.getClass(),
                                       rightValue.getClass(), topValue.getClass(),
                                       firstLeftData.getClass(),  firstBottomData.getClass(),
                                       firstRightData.getClass(), firstTopData.getClass(),
                                       secondLeftData.getClass(), secondBottomData.getClass(),
                                       secondRightData.getClass(),secondTopData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && bottomValue.equals(firstBottomData) &&
            rightValue.equals(firstRightData) && topValue.equals(firstTopData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && bottomValue.equals(secondBottomData) &&
            rightValue.equals(secondRightData) && topValue.equals(secondTopData)) {
            secondBranch.run();
        } else {
            elseBranch.run();
        }
    }

    public <V1, V2, V3, V4>
    void as(V1 firstLeftData,  V2 firstBottomData,
            V3 firstRightData, V4 firstTopData, Runnable firstBranch,
            V1 secondLeftData,  V2 secondBottomData,
            V3 secondRightData, V4 secondTopData, Runnable secondBranch,
            Class<Else> elseClass, Runnable elseBranch) {
        matches(quarData.first(), quarData.second(), quarData.third(), quarData.fourth(),
                firstLeftData, firstBottomData, firstRightData, firstTopData, firstBranch,
                secondLeftData, secondBottomData, secondRightData, secondTopData, secondBranch,
                elseClass, elseBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,   V2 firstBottomData,
                 V3 firstRightData,  V4 firstTopData,  Runnable firstBranch,
                 V1 secondLeftData,  V2 secondBottomData,
                 V3 secondRightData, V4 secondTopData, Runnable secondBranch,
                 Class<Var> elseClass, QuarConsumer<V1, V2, V3, V4> varBranch) {
        if (!Reflection.checkQuarTypes(leftValue.getClass(),  bottomValue.getClass(),
                                       rightValue.getClass(), topValue.getClass(),
                                       firstLeftData.getClass(),  firstBottomData.getClass(),
                                       firstRightData.getClass(), firstTopData.getClass(),
                                       secondLeftData.getClass(), secondBottomData.getClass(),
                                       secondRightData.getClass(),secondTopData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && bottomValue.equals(firstBottomData) &&
            rightValue.equals(firstRightData) && topValue.equals(firstTopData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && bottomValue.equals(secondBottomData) &&
                   rightValue.equals(secondRightData) && topValue.equals(secondTopData)) {
            secondBranch.run();
        } else {
            varBranch.accept(leftValue, bottomValue, rightValue, topValue);
        }
    }

    public <V1, V2, V3, V4>
    void as(V1 firstLeftData,  V2 firstBottomData,
            V3 firstRightData, V4 firstTopData, Runnable firstBranch,
            V1 secondLeftData,  V2 secondBottomData,
            V3 secondRightData, V4 secondTopData, Runnable secondBranch,
            Class<Var> varClass, QuarConsumer<V1, V2, V3, V4> varBranch) {
        matches((V1) quarData.first(), (V2) quarData.second(), (V3) quarData.third(), (V4) quarData.fourth(),
                firstLeftData, firstBottomData, firstRightData, firstTopData, firstBranch,
                secondLeftData, secondBottomData, secondRightData, secondTopData, secondBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch) {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                     firstLeftData.getClass(),  firstRightData.getClass(),
                                     secondLeftData.getClass(), secondRightData.getClass(),
                                     thirdLeftData.getClass(),  thirdRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else {
            throw new PatternException("Statement must to have only three branches");
        }
    }

    public <V1, V2>
    void as(V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
            V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch) {
        matches(biData.first(), biData.second(),
                firstLeftData, firstRightData, firstBranch,
                secondLeftData, secondRightData, secondBranch,
                thirdLeftData, thirdRightData, thirdBranch);
    }

    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 Tuple.Tuple2<V1, V2> firstItem,  Runnable firstBranch,
                 Tuple.Tuple2<V1, V2> secondItem, Runnable secondBranch,
                 Tuple.Tuple2<V1, V2> thirdItem,  Runnable thirdBranch) {
        matches(leftValue, rightValue,
                (V1) firstItem.first(), (V2) firstItem.second(), firstBranch,
                (V1) secondItem.first(), (V2) secondItem.second(), secondBranch,
                (V1) thirdItem.first(), (V2) thirdItem.second(), thirdBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                    firstLeftData.getClass(),  firstRightData.getClass(),
                                    secondLeftData.getClass(), secondRightData.getClass(),
                                    thirdLeftData.getClass(),  thirdRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else {
            elseBranch.run();
        }
    }

    public <V1, V2>
    void as(V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
            V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
            Class<Else> elseClass, Runnable elseBranch) {
        matches(biData.first(), biData.second(),
                firstLeftData, firstRightData, firstBranch,
                secondLeftData, secondRightData, secondBranch,
                thirdLeftData, thirdRightData, thirdBranch,
                elseClass, elseBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
                 Class<Var> elseClass, BiConsumer<V1, V2> varBranch) {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                    firstLeftData.getClass(),  firstRightData.getClass(),
                                    secondLeftData.getClass(), secondRightData.getClass(),
                                    thirdLeftData.getClass(),  thirdRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else {
            varBranch.accept(leftValue, rightValue);
        }
    }

    public <V1, V2>
    void as(V1 firstLeftData, V2 firstRightData, Runnable firstBranch,
            V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
            Class<Var> varClass, BiConsumer<V1, V2> varBranch) {
        matches((V1) biData.first(), (V2) biData.second(),
                firstLeftData, firstRightData, firstBranch,
                secondLeftData, secondRightData, secondBranch,
                thirdLeftData, thirdRightData, thirdBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch) {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(),  firstMiddleData.getClass(),  firstRightData.getClass(),
                                       secondLeftData.getClass(), secondMiddleData.getClass(), secondRightData.getClass(),
                                       thirdLeftData.getClass(),  thirdMiddleData.getClass(),  thirdRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && middleValue.equals(thirdMiddleData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else {
            throw new PatternException("Statement must to have only three branches");
        }
    }

    public <V1, V2, V3>
    void as(V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch,
            V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch) {
        matches(triData.first(), triData.second(), triData.third(),
                firstLeftData, firstMiddleData, firstRightData, firstBranch,
                secondLeftData, secondMiddleData, secondRightData, secondBranch,
                thirdLeftData, thirdMiddleData, thirdRightData, thirdBranch);
    }

    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 Tuple.Tuple3<V1, V2, V3> firstItem,  Runnable firstBranch,
                 Tuple.Tuple3<V1, V2, V3> secondItem, Runnable secondBranch,
                 Tuple.Tuple3<V1, V2, V3> thirdItem,  Runnable thirdBranch) {
        matches(leftValue, middleValue, rightValue,
                (V1) firstItem.first(), (V2) firstItem.second(), firstItem.third(), firstBranch,
                (V1) secondItem.first(), (V2) secondItem.second(), secondItem.third(), secondBranch,
                (V1) thirdItem.first(), (V2) thirdItem.second(), thirdItem.third(), thirdBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(),  firstMiddleData.getClass(),  firstRightData.getClass(),
                                       secondLeftData.getClass(), secondMiddleData.getClass(), secondRightData.getClass(),
                                       thirdLeftData.getClass(),  thirdMiddleData.getClass(),  thirdRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && middleValue.equals(thirdMiddleData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else {
            elseBranch.run();
        }
    }

    public <V1, V2, V3>
    void as(V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch,
            V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
            Class<Else> elseClass, Runnable elseBranch) {
        matches(triData.first(), triData.second(), triData.third(),
                firstLeftData, firstMiddleData, firstRightData, firstBranch,
                secondLeftData, secondMiddleData, secondRightData, secondBranch,
                thirdLeftData, thirdMiddleData, thirdRightData, thirdBranch,
                elseClass, elseBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
                 Class<Var> varClass, TriConsumer<V1, V2, V3> varBranch) {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(),  firstMiddleData.getClass(),  firstRightData.getClass(),
                                       secondLeftData.getClass(), secondMiddleData.getClass(), secondRightData.getClass(),
                                       thirdLeftData.getClass(),  thirdMiddleData.getClass(),  thirdRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && middleValue.equals(thirdMiddleData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else {
            varBranch.accept(leftValue, middleValue, rightValue);
        }
    }

    public <V1, V2, V3>
    void as(V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch,
            V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
            Class<Var> varClass, TriConsumer<V1, V2, V3> varBranch) {
        matches((V1) triData.first(), (V2) triData.second(), (V3) triData.third(),
                firstLeftData, firstMiddleData, firstRightData, firstBranch,
                secondLeftData, secondMiddleData, secondRightData, secondBranch,
                thirdLeftData, thirdMiddleData, thirdRightData, thirdBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,   V2 firstBottomData,
                 V3 firstRightData,  V4 firstTopData,  Runnable firstBranch,
                 V1 secondLeftData,  V2 secondBottomData,
                 V3 secondRightData, V4 secondTopData, Runnable secondBranch,
                 V1 thirdLeftData,   V2 thirdBottomData,
                 V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch)  {
        if (!Reflection.checkQuarTypes(leftValue.getClass(), bottomValue.getClass(),
                                        rightValue.getClass(), topValue.getClass(),
                                        firstLeftData.getClass(),   firstBottomData.getClass(),
                                        firstRightData.getClass(),  firstTopData.getClass(),
                                        secondLeftData.getClass(),  secondBottomData.getClass(),
                                        secondRightData.getClass(), secondTopData.getClass(),
                                        thirdLeftData.getClass(),   thirdBottomData.getClass(),
                                        thirdRightData.getClass(),  thirdTopData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && bottomValue.equals(firstBottomData) &&
            rightValue.equals(firstRightData) && topValue.equals(firstTopData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && bottomValue.equals(secondBottomData) &&
            rightValue.equals(secondRightData) && topValue.equals(secondTopData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && bottomValue.equals(thirdBottomData) &&
            rightValue.equals(thirdRightData) && topValue.equals(thirdTopData)) {
            thirdBranch.run();
        } else {
            throw new PatternException("Statement must to have only three branches");
        }
    }

    public <V1, V2, V3, V4>
    void as(V1 firstLeftData,  V2 firstBottomData,
            V3 firstRightData, V4 firstTopData, Runnable firstBranch,
            V1 secondLeftData,  V2 secondBottomData,
            V3 secondRightData, V4 secondTopData, Runnable secondBranch,
            V1 thirdLeftData,   V2 thirdBottomData,
            V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch) {
        matches(quarData.first(), quarData.second(), quarData.third(), quarData.fourth(),
                firstLeftData, firstBottomData, firstRightData, firstTopData, firstBranch,
                secondLeftData, secondBottomData, secondRightData, secondTopData, secondBranch,
                thirdLeftData, thirdBottomData, thirdRightData, thirdTopData, thirdBranch);
    }

    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 Tuple.Tuple4<V1, V2, V3, V4> firstItem,  Runnable firstBranch,
                 Tuple.Tuple4<V1, V2, V3, V4> secondItem, Runnable secondBranch,
                 Tuple.Tuple4<V1, V2, V3, V4> thirdItem,  Runnable thirdBranch) {
        matches(leftValue, bottomValue, rightValue, topValue,
                (V1) firstItem.first(), (V2) firstItem.second(), firstItem.third(), firstItem.fourth(), firstBranch,
                (V1) secondItem.first(), (V2) secondItem.second(), secondItem.third(), secondItem.fourth(), secondBranch,
                (V1) thirdItem.first(), (V2) thirdItem.second(), thirdItem.third(), thirdItem.fourth(), thirdBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,   V2 firstBottomData,
                 V3 firstRightData,  V4 firstTopData,  Runnable firstBranch,
                 V1 secondLeftData,  V2 secondBottomData,
                 V3 secondRightData, V4 secondTopData, Runnable secondBranch,
                 V1 thirdLeftData,   V2 thirdBottomData,
                 V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (!Reflection.checkQuarTypes(leftValue.getClass(), bottomValue.getClass(),
                                        rightValue.getClass(), topValue.getClass(),
                                        firstLeftData.getClass(),   firstBottomData.getClass(),
                                        firstRightData.getClass(),  firstTopData.getClass(),
                                        secondLeftData.getClass(),  secondBottomData.getClass(),
                                        secondRightData.getClass(), secondTopData.getClass(),
                                        thirdLeftData.getClass(),   thirdBottomData.getClass(),
                                        thirdRightData.getClass(),  thirdTopData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && bottomValue.equals(firstBottomData) &&
            rightValue.equals(firstRightData) && topValue.equals(firstTopData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && bottomValue.equals(secondBottomData) &&
            rightValue.equals(secondRightData) && topValue.equals(secondTopData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && bottomValue.equals(thirdBottomData) &&
            rightValue.equals(thirdRightData) && topValue.equals(thirdTopData)) {
            thirdBranch.run();
        } else {
            elseBranch.run();
        }
    }

    public <V1, V2, V3, V4>
    void as(V1 firstLeftData,  V2 firstBottomData,
            V3 firstRightData, V4 firstTopData, Runnable firstBranch,
            V1 secondLeftData,  V2 secondBottomData,
            V3 secondRightData, V4 secondTopData, Runnable secondBranch,
            V1 thirdLeftData,   V2 thirdBottomData,
            V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
            Class<Else> elseClass, Runnable elseBranch) {
        matches(quarData.first(), quarData.second(), quarData.third(), quarData.fourth(),
                firstLeftData, firstBottomData, firstRightData, firstTopData, firstBranch,
                secondLeftData, secondBottomData, secondRightData, secondTopData, secondBranch,
                thirdLeftData, thirdBottomData, thirdRightData, thirdTopData, thirdBranch,
                elseClass, elseBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,   V2 firstBottomData,
                 V3 firstRightData,  V4 firstTopData,  Runnable firstBranch,
                 V1 secondLeftData,  V2 secondBottomData,
                 V3 secondRightData, V4 secondTopData, Runnable secondBranch,
                 V1 thirdLeftData,   V2 thirdBottomData,
                 V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
                 Class<Var> varClass, QuarConsumer<V1, V2, V3, V4> varBranch) {
        if (!Reflection.checkQuarTypes(leftValue.getClass(), bottomValue.getClass(),
                                       rightValue.getClass(), topValue.getClass(),
                                       firstLeftData.getClass(),   firstBottomData.getClass(),
                                       firstRightData.getClass(),  firstTopData.getClass(),
                                       secondLeftData.getClass(),  secondBottomData.getClass(),
                                       secondRightData.getClass(), secondTopData.getClass(),
                                       thirdLeftData.getClass(),   thirdBottomData.getClass(),
                                       thirdRightData.getClass(),  thirdTopData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && bottomValue.equals(firstBottomData) &&
            rightValue.equals(firstRightData) && topValue.equals(firstTopData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && bottomValue.equals(secondBottomData) &&
                   rightValue.equals(secondRightData) && topValue.equals(secondTopData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && bottomValue.equals(thirdBottomData) &&
                   rightValue.equals(thirdRightData) && topValue.equals(thirdTopData)) {
            thirdBranch.run();
        } else {
            varBranch.accept(leftValue, bottomValue, rightValue, topValue);
        }
    }

    public <V1, V2, V3, V4>
    void as(V1 firstLeftData,  V2 firstBottomData,
            V3 firstRightData, V4 firstTopData, Runnable firstBranch,
            V1 secondLeftData,  V2 secondBottomData,
            V3 secondRightData, V4 secondTopData, Runnable secondBranch,
            V1 thirdLeftData,   V2 thirdBottomData,
            V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
            Class<Var> varClass, QuarConsumer<V1, V2, V3, V4> varBranch) {
        matches((V1) quarData.first(), (V2) quarData.second(), (V3) quarData.third(), (V4) quarData.fourth(),
                firstLeftData, firstBottomData, firstRightData, firstTopData, firstBranch,
                secondLeftData, secondBottomData, secondRightData, secondTopData, secondBranch,
                thirdLeftData, thirdBottomData, thirdRightData, thirdTopData, thirdBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch) {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                     firstLeftData.getClass(),  firstRightData.getClass(),
                                     secondLeftData.getClass(), secondRightData.getClass(),
                                     thirdLeftData.getClass(),  thirdRightData.getClass(),
                                     fourthLeftData.getClass(), fourthRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
        } else {
            throw new PatternException("Statement must to have only fourth branches");
        }
    }

    public <V1, V2>
    void as(V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
            V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
            V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch) {
        matches(biData.first(), biData.second(),
                firstLeftData, firstRightData, firstBranch,
                secondLeftData, secondRightData, secondBranch,
                thirdLeftData, thirdRightData, thirdBranch,
                fourthLeftData, fourthRightData, fourthBranch);
    }

    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 Tuple.Tuple2<V1, V2> firstItem,  Runnable firstBranch,
                 Tuple.Tuple2<V1, V2> secondItem, Runnable secondBranch,
                 Tuple.Tuple2<V1, V2> thirdItem,  Runnable thirdBranch,
                 Tuple.Tuple2<V1, V2> fourthItem,  Runnable fourthBranch) {
        matches(leftValue, rightValue,
                (V1) firstItem.first(), (V2) firstItem.second(), firstBranch,
                (V1) secondItem.first(), (V2) secondItem.second(), secondBranch,
                (V1) thirdItem.first(), (V2) thirdItem.second(), thirdBranch,
                (V1) fourthItem.first(), (V2) fourthItem.second(), fourthBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                    firstLeftData.getClass(),  firstRightData.getClass(),
                                    secondLeftData.getClass(), secondRightData.getClass(),
                                    thirdLeftData.getClass(),  thirdRightData.getClass(),
                                    fourthLeftData.getClass(), fourthRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
        } else {
            elseBranch.run();
        }
    }

    public <V1, V2>
    void as(V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
            V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
            V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch,
            Class<Else> elseClass, Runnable elseBranch) {
        matches(biData.first(), biData.second(),
                firstLeftData, firstRightData, firstBranch,
                secondLeftData, secondRightData, secondBranch,
                thirdLeftData, thirdRightData, thirdBranch,
                fourthLeftData, fourthRightData, fourthBranch,
                elseClass, elseBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch,
                 Class<Var> varClass, BiConsumer<V1, V2> varBranch) {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                    firstLeftData.getClass(),  firstRightData.getClass(),
                                    secondLeftData.getClass(), secondRightData.getClass(),
                                    thirdLeftData.getClass(),  thirdRightData.getClass(),
                                    fourthLeftData.getClass(), fourthRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
        } else {
            varBranch.accept(leftValue, rightValue);
        }
    }

    public <V1, V2>
    void as(V1 firstLeftData, V2 firstRightData, Runnable firstBranch,
            V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
            V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch,
            Class<Var> varClass, BiConsumer<V1, V2> varBranch) {
        matches((V1) biData.first(), (V2) biData.second(),
                firstLeftData, firstRightData, firstBranch,
                secondLeftData, secondRightData, secondBranch,
                thirdLeftData, thirdRightData, thirdBranch,
                fourthLeftData, fourthRightData, fourthBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch) {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(),  firstMiddleData.getClass(),  firstRightData.getClass(),
                                       secondLeftData.getClass(), secondMiddleData.getClass(), secondRightData.getClass(),
                                       thirdLeftData.getClass(),  thirdMiddleData.getClass(),  thirdRightData.getClass(),
                                       fourthLeftData.getClass(), fourthMiddleData.getClass(), fourthRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && middleValue.equals(thirdMiddleData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        }  else if (leftValue.equals(fourthLeftData) && middleValue.equals(fourthMiddleData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
        } else {
            throw new PatternException("Statement must to have only fourth branches");
        }
    }

    public <V1, V2, V3>
    void as(V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch,
            V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
            V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch) {
        matches(triData.first(), triData.second(), triData.third(),
                firstLeftData, firstMiddleData, firstRightData, firstBranch,
                secondLeftData, secondMiddleData, secondRightData, secondBranch,
                thirdLeftData, thirdMiddleData, thirdRightData, thirdBranch,
                fourthLeftData, fourthMiddleData, fourthRightData, fourthBranch);
    }

    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 Tuple.Tuple3<V1, V2, V3> firstItem,  Runnable firstBranch,
                 Tuple.Tuple3<V1, V2, V3> secondItem, Runnable secondBranch,
                 Tuple.Tuple3<V1, V2, V3> thirdItem,  Runnable thirdBranch,
                 Tuple.Tuple3<V1, V2, V3> fourthItem, Runnable fourthBranch) {
        matches(leftValue, middleValue, rightValue,
                (V1) firstItem.first(), (V2) firstItem.second(), firstItem.third(), firstBranch,
                (V1) secondItem.first(), (V2) secondItem.second(), secondItem.third(), secondBranch,
                (V1) thirdItem.first(), (V2) thirdItem.second(), thirdItem.third(), thirdBranch,
                (V1) fourthItem.first(), (V2) fourthItem.second(), fourthItem.third(), fourthBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                        firstLeftData.getClass(),  firstMiddleData.getClass(),  firstRightData.getClass(),
                                        secondLeftData.getClass(), secondMiddleData.getClass(), secondRightData.getClass(),
                                        thirdLeftData.getClass(),  thirdMiddleData.getClass(),  thirdRightData.getClass(),
                                        fourthLeftData.getClass(), fourthMiddleData.getClass(), fourthRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && middleValue.equals(thirdMiddleData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        }  else if (leftValue.equals(fourthLeftData) && middleValue.equals(fourthMiddleData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
        } else {
            elseBranch.run();
        }
    }

    public <V1, V2, V3>
    void as(V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch,
            V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
            V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch,
            Class<Else> elseClass, Runnable elseBranch) {
        matches(triData.first(), triData.second(), triData.third(),
                firstLeftData, firstMiddleData, firstRightData, firstBranch,
                secondLeftData, secondMiddleData, secondRightData, secondBranch,
                thirdLeftData, thirdMiddleData, thirdRightData, thirdBranch,
                fourthLeftData, fourthMiddleData, fourthRightData, fourthBranch,
                elseClass, elseBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch,
                 Class<Var> varClass, TriConsumer<V1, V2, V3> varBranch) {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(),  firstMiddleData.getClass(),  firstRightData.getClass(),
                                       secondLeftData.getClass(), secondMiddleData.getClass(), secondRightData.getClass(),
                                       thirdLeftData.getClass(),  thirdMiddleData.getClass(),  thirdRightData.getClass(),
                                       fourthLeftData.getClass(), fourthMiddleData.getClass(), fourthRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && middleValue.equals(thirdMiddleData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        }  else if (leftValue.equals(fourthLeftData) && middleValue.equals(fourthMiddleData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
        } else {
            varBranch.accept(leftValue, middleValue, rightValue);
        }
    }

    public <V1, V2, V3>
    void as(V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch,
            V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
            V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch,
            Class<Var> varClass, TriConsumer<V1, V2, V3> varBranch) {
        matches((V1) triData.first(), (V2) triData.second(), (V3) triData.third(),
                firstLeftData, firstMiddleData, firstRightData, firstBranch,
                secondLeftData, secondMiddleData, secondRightData, secondBranch,
                thirdLeftData, thirdMiddleData, thirdRightData, thirdBranch,
                fourthLeftData, fourthMiddleData, fourthRightData, fourthBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,   V2 firstBottomData,
                 V3 firstRightData,  V4 firstTopData,  Runnable firstBranch,
                 V1 secondLeftData,  V2 secondBottomData,
                 V3 secondRightData, V4 secondTopData, Runnable secondBranch,
                 V1 thirdLeftData,   V2 thirdBottomData,
                 V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
                 V1 fourthLeftData,  V2 fourthBottomData,
                 V3 fourthRightData, V4 fourthTopData, Runnable fourthBranch) {
        if (!Reflection.checkQuarTypes(leftValue.getClass(), bottomValue.getClass(), rightValue.getClass(), topValue.getClass(),
                firstLeftData.getClass(), firstBottomData.getClass(), firstRightData.getClass(), firstTopData.getClass(),
                secondLeftData.getClass(), secondBottomData.getClass(), secondRightData.getClass(), secondTopData.getClass(),
                thirdLeftData.getClass(), thirdBottomData.getClass(), thirdRightData.getClass(), thirdTopData.getClass(),
                fourthLeftData.getClass(), fourthBottomData.getClass(), fourthRightData.getClass(), fourthTopData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && bottomValue.equals(firstBottomData) &&
                rightValue.equals(firstRightData) && topValue.equals(firstTopData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && bottomValue.equals(secondBottomData) &&
                rightValue.equals(secondRightData) && topValue.equals(secondTopData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && bottomValue.equals(thirdBottomData) &&
                rightValue.equals(thirdRightData) && topValue.equals(thirdTopData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && bottomValue.equals(fourthBottomData) &&
                rightValue.equals(fourthRightData) && topValue.equals(fourthTopData)) {
            fourthBranch.run();
        } else {
            throw new PatternException("Statement must to have only fourth branches");
        }
    }

    public <V1, V2, V3, V4>
    void as(V1 firstLeftData,  V2 firstBottomData,
            V3 firstRightData, V4 firstTopData, Runnable firstBranch,
            V1 secondLeftData,  V2 secondBottomData,
            V3 secondRightData, V4 secondTopData, Runnable secondBranch,
            V1 thirdLeftData,   V2 thirdBottomData,
            V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
            V1 fourthLeftData,  V2 fourthBottomData,
            V3 fourthRightData, V4 fourthTopData, Runnable fourthBranch) {
        matches(quarData.first(), quarData.second(), quarData.third(), quarData.fourth(),
                firstLeftData, firstBottomData, firstRightData, firstTopData, firstBranch,
                secondLeftData, secondBottomData, secondRightData, secondTopData, secondBranch,
                thirdLeftData, thirdBottomData, thirdRightData, thirdTopData, thirdBranch,
                fourthLeftData, fourthBottomData, fourthRightData, fourthTopData, fourthBranch);
    }

    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 Tuple.Tuple4<V1, V2, V3, V4> firstItem,  Runnable firstBranch,
                 Tuple.Tuple4<V1, V2, V3, V4> secondItem, Runnable secondBranch,
                 Tuple.Tuple4<V1, V2, V3, V4> thirdItem,  Runnable thirdBranch,
                 Tuple.Tuple4<V1, V2, V3, V4> fourthItem, Runnable fourthBranch) {
        matches(leftValue, bottomValue, rightValue, topValue,
                (V1) firstItem.first(), (V2) firstItem.second(), firstItem.third(), firstItem.fourth(), firstBranch,
                (V1) secondItem.first(), (V2) secondItem.second(), secondItem.third(), secondItem.fourth(), secondBranch,
                (V1) thirdItem.first(), (V2) thirdItem.second(), thirdItem.third(), thirdItem.fourth(), thirdBranch,
                (V1) fourthItem.first(), (V2) fourthItem.second(), fourthItem.third(), fourthItem.fourth(), fourthBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,   V2 firstBottomData,
                 V3 firstRightData,  V4 firstTopData,  Runnable firstBranch,
                 V1 secondLeftData,  V2 secondBottomData,
                 V3 secondRightData, V4 secondTopData, Runnable secondBranch,
                 V1 thirdLeftData,   V2 thirdBottomData,
                 V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
                 V1 fourthLeftData,  V2 fourthBottomData,
                 V3 fourthRightData, V4 fourthTopData, Runnable fourthBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (!Reflection.checkQuarTypes(leftValue.getClass(), bottomValue.getClass(),
                                        rightValue.getClass(), topValue.getClass(),
                                        firstLeftData.getClass(),  firstBottomData.getClass(),
                                        firstRightData.getClass(),  firstTopData.getClass(),
                                        secondLeftData.getClass(), secondBottomData.getClass(),
                                        secondRightData.getClass(), secondTopData.getClass(),
                                        thirdLeftData.getClass(),  thirdBottomData.getClass(),
                                        thirdRightData.getClass(),  thirdTopData.getClass(),
                                        fourthLeftData.getClass(), fourthBottomData.getClass(),
                                        fourthRightData.getClass(), fourthTopData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && bottomValue.equals(firstBottomData) &&
            rightValue.equals(firstRightData) && topValue.equals(firstTopData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && bottomValue.equals(secondBottomData) &&
            rightValue.equals(secondRightData) && topValue.equals(secondTopData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && bottomValue.equals(thirdBottomData) &&
            rightValue.equals(thirdRightData) && topValue.equals(thirdTopData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && bottomValue.equals(fourthBottomData) &&
            rightValue.equals(fourthRightData) && topValue.equals(fourthTopData)) {
            fourthBranch.run();
        } else {
            elseBranch.run();
        }
    }

    public <V1, V2, V3, V4>
    void as(V1 firstLeftData,  V2 firstBottomData,
            V3 firstRightData, V4 firstTopData, Runnable firstBranch,
            V1 secondLeftData,  V2 secondBottomData,
            V3 secondRightData, V4 secondTopData, Runnable secondBranch,
            V1 thirdLeftData,   V2 thirdBottomData,
            V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
            V1 fourthLeftData,  V2 fourthBottomData,
            V3 fourthRightData, V4 fourthTopData, Runnable fourthBranch,
            Class<Else> elseClass, Runnable elseBranch) {
        matches(quarData.first(), quarData.second(), quarData.third(), quarData.fourth(),
                firstLeftData, firstBottomData, firstRightData, firstTopData, firstBranch,
                secondLeftData, secondBottomData, secondRightData, secondTopData, secondBranch,
                thirdLeftData, thirdBottomData, thirdRightData, thirdTopData, thirdBranch,
                fourthLeftData, fourthBottomData, fourthRightData, fourthTopData, fourthBranch,
                elseClass, elseBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,   V2 firstBottomData,
                 V3 firstRightData,  V4 firstTopData,  Runnable firstBranch,
                 V1 secondLeftData,  V2 secondBottomData,
                 V3 secondRightData, V4 secondTopData, Runnable secondBranch,
                 V1 thirdLeftData,   V2 thirdBottomData,
                 V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
                 V1 fourthLeftData,  V2 fourthBottomData,
                 V3 fourthRightData, V4 fourthTopData, Runnable fourthBranch,
                 Class<Var> varClass, QuarConsumer<V1, V2, V3, V4> varBranch) {
        if (!Reflection.checkQuarTypes(leftValue.getClass(), bottomValue.getClass(),
                                       rightValue.getClass(), topValue.getClass(),
                                       firstLeftData.getClass(),  firstBottomData.getClass(),
                                       firstRightData.getClass(),  firstTopData.getClass(),
                                       secondLeftData.getClass(), secondBottomData.getClass(),
                                       secondRightData.getClass(), secondTopData.getClass(),
                                       thirdLeftData.getClass(),  thirdBottomData.getClass(),
                                       thirdRightData.getClass(),  thirdTopData.getClass(),
                                       fourthLeftData.getClass(), fourthBottomData.getClass(),
                                       fourthRightData.getClass(), fourthTopData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && bottomValue.equals(firstBottomData) &&
            rightValue.equals(firstRightData) && topValue.equals(firstTopData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && bottomValue.equals(secondBottomData) &&
                   rightValue.equals(secondRightData) && topValue.equals(secondTopData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && bottomValue.equals(thirdBottomData) &&
                   rightValue.equals(thirdRightData) && topValue.equals(thirdTopData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && bottomValue.equals(fourthBottomData) &&
                   rightValue.equals(fourthRightData) && topValue.equals(fourthTopData)) {
            fourthBranch.run();
        } else {
            varBranch.accept(leftValue, bottomValue, rightValue, topValue);
        }
    }

    public <V1, V2, V3, V4>
    void as(V1 firstLeftData,  V2 firstBottomData,
            V3 firstRightData, V4 firstTopData, Runnable firstBranch,
            V1 secondLeftData,  V2 secondBottomData,
            V3 secondRightData, V4 secondTopData, Runnable secondBranch,
            V1 thirdLeftData,   V2 thirdBottomData,
            V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
            V1 fourthLeftData,  V2 fourthBottomData,
            V3 fourthRightData, V4 fourthTopData, Runnable fourthBranch,
            Class<Var> varClass, QuarConsumer<V1, V2, V3, V4> varBranch) {
        matches((V1) quarData.first(), (V2) quarData.second(), (V3) quarData.third(), (V4) quarData.fourth(),
                firstLeftData, firstBottomData, firstRightData, firstTopData, firstBranch,
                secondLeftData, secondBottomData, secondRightData, secondTopData, secondBranch,
                thirdLeftData, thirdBottomData, thirdRightData, thirdTopData, thirdBranch,
                fourthLeftData, fourthBottomData, fourthRightData, fourthTopData, fourthBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
                V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch,
                V1 fifthLeftData,  V2 fifthRightData,  Runnable fifthBranch)  {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                    firstLeftData.getClass(),  firstRightData.getClass(),
                                    secondLeftData.getClass(), secondRightData.getClass(),
                                    thirdLeftData.getClass(),  thirdRightData.getClass(),
                                    fourthLeftData.getClass(), fourthRightData.getClass(),
                                    fifthLeftData.getClass(),  fifthRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
        } else if (leftValue.equals(fifthLeftData) && rightValue.equals(fifthRightData)) {
            fifthBranch.run();
        } else {
            throw new PatternException("Statement must to have only fifth branches");
        }
    }

    public <V1, V2>
    void as(V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
            V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
            V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch,
            V1 fifthLeftData,  V2 fifthRightData,  Runnable fifthBranch) {
        matches(biData.first(), biData.second(),
                firstLeftData, firstRightData, firstBranch,
                secondLeftData, secondRightData, secondBranch,
                thirdLeftData, thirdRightData, thirdBranch,
                fourthLeftData, fourthRightData, fourthBranch,
                fifthLeftData, fifthRightData, fifthBranch);
    }

    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 Tuple.Tuple2<V1, V2> firstItem,  Runnable firstBranch,
                 Tuple.Tuple2<V1, V2> secondItem, Runnable secondBranch,
                 Tuple.Tuple2<V1, V2> thirdItem,  Runnable thirdBranch,
                 Tuple.Tuple2<V1, V2> fourthItem, Runnable fourthBranch,
                 Tuple.Tuple2<V1, V2> fifthItem,  Runnable fifthBranch) {
        matches(leftValue, rightValue,
                (V1) firstItem.first(), (V2) firstItem.second(), firstBranch,
                (V1) secondItem.first(), (V2) secondItem.second(), secondBranch,
                (V1) thirdItem.first(), (V2) thirdItem.second(), thirdBranch,
                (V1) fourthItem.first(), (V2) fourthItem.second(), fourthBranch,
                (V1) fifthItem.first(), (V2) fifthItem.second(), fifthBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch,
                 V1 fifthLeftData,  V2 fifthRightData,  Runnable fifthBranch,
                 Class<Else> elseClass, Runnable elseBranch)  {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                    firstLeftData.getClass(),  firstRightData.getClass(),
                                    secondLeftData.getClass(), secondRightData.getClass(),
                                    thirdLeftData.getClass(),  thirdRightData.getClass(),
                                    fourthLeftData.getClass(), fourthRightData.getClass(),
                                    fifthLeftData.getClass(),  fifthRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
        } else if (leftValue.equals(fifthLeftData) && rightValue.equals(fifthRightData)) {
            fifthBranch.run();
        } else {
            elseBranch.run();
        }
    }

    public <V1, V2>
    void as(V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
            V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
            V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch,
            V1 fifthLeftData,  V2 fifthRightData,  Runnable fifthBranch,
            Class<Else> elseClass, Runnable elseBranch) {
        matches(biData.first(), biData.second(),
                firstLeftData, firstRightData, firstBranch,
                secondLeftData, secondRightData, secondBranch,
                thirdLeftData, thirdRightData, thirdBranch,
                fourthLeftData, fourthRightData, fourthBranch,
                fifthLeftData, fifthRightData, fifthBranch,
                elseClass, elseBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch,
                 V1 fifthLeftData,  V2 fifthRightData,  Runnable fifthBranch,
                 Class<Var> varClass, BiConsumer<V1, V2> varBranch) {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                    firstLeftData.getClass(),  firstRightData.getClass(),
                                    secondLeftData.getClass(), secondRightData.getClass(),
                                    thirdLeftData.getClass(),  thirdRightData.getClass(),
                                    fourthLeftData.getClass(), fourthRightData.getClass(),
                                    fifthLeftData.getClass(),  fifthRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
        } else if (leftValue.equals(fifthLeftData) && rightValue.equals(fifthRightData)) {
            fifthBranch.run();
        } else {
            varBranch.accept(leftValue, rightValue);
        }
    }

    public <V1, V2>
    void as(V1 firstLeftData, V2 firstRightData, Runnable firstBranch,
            V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
            V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch,
            V1 fifthLeftData,  V2 fifthRightData,  Runnable fifthBranch,
            Class<Var> varClass, BiConsumer<V1, V2> varBranch) {
        matches((V1) biData.first(), (V2) biData.second(),
                firstLeftData, firstRightData, firstBranch,
                secondLeftData, secondRightData, secondBranch,
                thirdLeftData, thirdRightData, thirdBranch,
                fourthLeftData, fourthRightData, fourthBranch,
                fifthLeftData, fifthRightData, fifthBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch,
                 V1 fifthLeftData,  V2 fifthMiddleData,  V3 fifthRightData,  Runnable fifthBranch)  {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(),  firstMiddleData.getClass(),  firstRightData.getClass(),
                                       secondLeftData.getClass(), secondMiddleData.getClass(), secondRightData.getClass(),
                                       thirdLeftData.getClass(),  thirdMiddleData.getClass(),  thirdRightData.getClass(),
                                       fourthLeftData.getClass(), fourthMiddleData.getClass(), fourthRightData.getClass(),
                                       fifthLeftData.getClass(),  fifthMiddleData.getClass(),  fifthRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && middleValue.equals(thirdMiddleData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && middleValue.equals(fourthMiddleData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
        } else if (leftValue.equals(fifthLeftData) && middleValue.equals(fifthMiddleData) && rightValue.equals(fifthRightData)) {
            fifthBranch.run();
        } else {
            throw new PatternException("Statement must to have only fifth branches");
        }
    }

    public <V1, V2, V3>
    void as(V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch,
            V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
            V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch,
            V1 fifthLeftData,  V2 fifthMiddleData,  V3 fifthRightData,  Runnable fifthBranch) {
        matches(triData.first(), triData.second(), triData.third(),
                firstLeftData, firstMiddleData, firstRightData, firstBranch,
                secondLeftData, secondMiddleData, secondRightData, secondBranch,
                thirdLeftData, thirdMiddleData, thirdRightData, thirdBranch,
                fourthLeftData, fourthMiddleData, fourthRightData, fourthBranch,
                fifthLeftData, fifthMiddleData, fifthRightData, fifthBranch);
    }

    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 Tuple.Tuple3<V1, V2, V3> firstItem,  Runnable firstBranch,
                 Tuple.Tuple3<V1, V2, V3> secondItem, Runnable secondBranch,
                 Tuple.Tuple3<V1, V2, V3> thirdItem,  Runnable thirdBranch,
                 Tuple.Tuple3<V1, V2, V3> fourthItem, Runnable fourthBranch,
                 Tuple.Tuple3<V1, V2, V3> fifthItem, Runnable fifthBranch) {
        matches(leftValue, middleValue, rightValue,
                (V1) firstItem.first(), (V2) firstItem.second(), firstItem.third(), firstBranch,
                (V1) secondItem.first(), (V2) secondItem.second(), secondItem.third(), secondBranch,
                (V1) thirdItem.first(), (V2) thirdItem.second(), thirdItem.third(), thirdBranch,
                (V1) fourthItem.first(), (V2) fourthItem.second(), thirdItem.third(), fourthBranch,
                (V1) fifthItem.first(), (V2) fifthItem.second(), fifthItem.third(), fifthBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch,
                 V1 fifthLeftData,  V2 fifthMiddleData,  V3 fifthRightData,  Runnable fifthBranch,
                 Class<Else> elseClass, Runnable elseBranch) {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(),  firstMiddleData.getClass(),  firstRightData.getClass(),
                                       secondLeftData.getClass(), secondMiddleData.getClass(), secondRightData.getClass(),
                                       thirdLeftData.getClass(),  thirdMiddleData.getClass(),  thirdRightData.getClass(),
                                       fourthLeftData.getClass(), fourthMiddleData.getClass(), fourthRightData.getClass(),
                                       fifthLeftData.getClass(),  fifthMiddleData.getClass(),  fifthRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && middleValue.equals(thirdMiddleData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && middleValue.equals(fourthMiddleData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
        } else if (leftValue.equals(fifthLeftData) && middleValue.equals(fifthMiddleData) && rightValue.equals(fifthRightData)) {
            fifthBranch.run();
        } else {
            elseBranch.run();
        }
    }

    public <V1, V2, V3>
    void as(V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch,
            V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
            V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch,
            V1 fifthLeftData,  V2 fifthMiddleData,  V3 fifthRightData,  Runnable fifthBranch,
            Class<Else> elseClass, Runnable elseBranch) {
        matches(triData.first(), triData.second(), triData.third(),
                firstLeftData, firstMiddleData, firstRightData, firstBranch,
                secondLeftData, secondMiddleData, secondRightData, secondBranch,
                thirdLeftData, thirdMiddleData, thirdRightData, thirdBranch,
                fourthLeftData, fourthMiddleData, fourthRightData, fourthBranch,
                fifthLeftData, fifthMiddleData, fifthRightData, fifthBranch,
                elseClass, elseBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch,
                 V1 fifthLeftData,  V2 fifthMiddleData,  V3 fifthRightData,  Runnable fifthBranch,
                 Class<Var> varClass, TriConsumer<V1, V2, V3> varBranch) {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(),  firstMiddleData.getClass(),  firstRightData.getClass(),
                                       secondLeftData.getClass(), secondMiddleData.getClass(), secondRightData.getClass(),
                                       thirdLeftData.getClass(),  thirdMiddleData.getClass(),  thirdRightData.getClass(),
                                       fourthLeftData.getClass(), fourthMiddleData.getClass(), fourthRightData.getClass(),
                                       fifthLeftData.getClass(),  fifthMiddleData.getClass(),  fifthRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && middleValue.equals(thirdMiddleData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && middleValue.equals(fourthMiddleData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
        } else if (leftValue.equals(fifthLeftData) && middleValue.equals(fifthMiddleData) && rightValue.equals(fifthRightData)) {
            fifthBranch.run();
        } else {
            varBranch.accept(leftValue, middleValue, rightValue);
        }
    }

    public <V1, V2, V3>
    void as(V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch,
            V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
            V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch,
            V1 fifthLeftData,  V2 fifthMiddleData,  V3 fifthRightData,  Runnable fifthBranch,
            Class<Var> varClass, TriConsumer<V1, V2, V3> varBranch) {
        matches((V1) triData.first(), (V2) triData.second(), (V3) triData.third(),
                firstLeftData, firstMiddleData, firstRightData, firstBranch,
                secondLeftData, secondMiddleData, secondRightData, secondBranch,
                thirdLeftData, thirdMiddleData, thirdRightData, thirdBranch,
                fourthLeftData, fourthMiddleData, fourthRightData, fourthBranch,
                fifthLeftData, fifthMiddleData, fifthRightData, fifthBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,   V2 firstBottomData,
                 V3 firstRightData,  V4 firstTopData,  Runnable firstBranch,
                 V1 secondLeftData,  V2 secondBottomData,
                 V3 secondRightData, V4 secondTopData, Runnable secondBranch,
                 V1 thirdLeftData,   V2 thirdBottomData,
                 V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
                 V1 fourthLeftData,  V2 fourthBottomData,
                 V3 fourthRightData, V4 fourthTopData, Runnable fourthBranch,
                 V1 fifthLeftData,   V2 fifthBottomData,
                 V3 fifthRightData,  V4 fifthTopData, Runnable fifthBranch)  {
        if (!Reflection.checkQuarTypes(leftValue.getClass(), bottomValue.getClass(),
                                        rightValue.getClass(), topValue.getClass(),
                                        firstLeftData.getClass(),   firstBottomData.getClass(),
                                        firstRightData.getClass(),  firstTopData.getClass(),
                                        secondLeftData.getClass(),  secondBottomData.getClass(),
                                        secondRightData.getClass(), secondTopData.getClass(),
                                        thirdLeftData.getClass(),   thirdBottomData.getClass(),
                                        thirdRightData.getClass(),  thirdTopData.getClass(),
                                        fourthLeftData.getClass(),  fourthBottomData.getClass(),
                                        fourthRightData.getClass(), fourthTopData.getClass(),
                                        fifthLeftData.getClass(),   fifthBottomData.getClass(),
                                        fifthRightData.getClass(),  fifthTopData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && bottomValue.equals(firstBottomData) &&
            rightValue.equals(firstRightData) && topValue.equals(firstTopData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && bottomValue.equals(secondBottomData) &&
            rightValue.equals(secondRightData) && topValue.equals(secondTopData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && bottomValue.equals(thirdBottomData) &&
            rightValue.equals(thirdRightData) && topValue.equals(thirdTopData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && bottomValue.equals(fourthBottomData) &&
            rightValue.equals(fourthRightData) && topValue.equals(fourthTopData)) {
            fourthBranch.run();
        } else if (leftValue.equals(fifthLeftData) && bottomValue.equals(fifthBottomData) &&
            rightValue.equals(fifthRightData) && topValue.equals(fifthTopData)) {
            fifthBranch.run();
        } else {
            throw new PatternException("Statement must to have only fifth branches");
        }
    }

    public <V1, V2, V3, V4>
    void as(V1 firstLeftData,  V2 firstBottomData,
            V3 firstRightData, V4 firstTopData, Runnable firstBranch,
            V1 secondLeftData,  V2 secondBottomData,
            V3 secondRightData, V4 secondTopData, Runnable secondBranch,
            V1 thirdLeftData,   V2 thirdBottomData,
            V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
            V1 fourthLeftData,  V2 fourthBottomData,
            V3 fourthRightData, V4 fourthTopData, Runnable fourthBranch,
            V1 fifthLeftData,   V2 fifthBottomData,
            V3 fifthRightData,  V4 fifthTopData, Runnable fifthBranch) {
        matches(quarData.first(), quarData.second(), quarData.third(), quarData.fourth(),
                firstLeftData, firstBottomData, firstRightData, firstTopData, firstBranch,
                secondLeftData, secondBottomData, secondRightData, secondTopData, secondBranch,
                thirdLeftData, thirdBottomData, thirdRightData, thirdTopData, thirdBranch,
                fourthLeftData, fourthBottomData, fourthRightData, fourthTopData, fourthBranch,
                fifthLeftData, fifthBottomData, fifthRightData, fifthTopData, fifthBranch);
    }

    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 Tuple.Tuple4<V1, V2, V3, V4> firstItem,  Runnable firstBranch,
                 Tuple.Tuple4<V1, V2, V3, V4> secondItem, Runnable secondBranch,
                 Tuple.Tuple4<V1, V2, V3, V4> thirdItem,  Runnable thirdBranch,
                 Tuple.Tuple4<V1, V2, V3, V4> fourthItem, Runnable fourthBranch,
                 Tuple.Tuple4<V1, V2, V3, V4> fifthItem,  Runnable fifthBranch) {
        matches(leftValue, bottomValue, rightValue, topValue,
                (V1) firstItem.first(), (V2) firstItem.second(), firstItem.third(), firstItem.fourth(), firstBranch,
                (V1) secondItem.first(), (V2) secondItem.second(), secondItem.third(), secondItem.fourth(), secondBranch,
                (V1) thirdItem.first(), (V2) thirdItem.second(), thirdItem.third(), thirdItem.fourth(), thirdBranch,
                (V1) fourthItem.first(), (V2) fourthItem.second(), fourthItem.third(), fourthItem.fourth(), fourthBranch,
                (V1) fifthItem.first(), (V2) fifthItem.second(), fifthItem.third(), fifthItem.fourth(), fifthBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,   V2 firstBottomData,
                 V3 firstRightData,  V4 firstTopData,  Runnable firstBranch,
                 V1 secondLeftData,  V2 secondBottomData,
                 V3 secondRightData, V4 secondTopData, Runnable secondBranch,
                 V1 thirdLeftData,   V2 thirdBottomData,
                 V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
                 V1 fourthLeftData,  V2 fourthBottomData,
                 V3 fourthRightData, V4 fourthTopData, Runnable fourthBranch,
                 V1 fifthLeftData,   V2 fifthBottomData,
                 V3 fifthRightData,  V4 fifthTopData, Runnable fifthBranch,
                 Class<Else> elseClass, Runnable elseBranch) {
        if (!Reflection.checkQuarTypes(leftValue.getClass(), bottomValue.getClass(),
                                        rightValue.getClass(), topValue.getClass(),
                                        firstLeftData.getClass(),   firstBottomData.getClass(),
                                        firstRightData.getClass(),  firstTopData.getClass(),
                                        secondLeftData.getClass(),  secondBottomData.getClass(),
                                        secondRightData.getClass(), secondTopData.getClass(),
                                        thirdLeftData.getClass(),   thirdBottomData.getClass(),
                                        thirdRightData.getClass(),  thirdTopData.getClass(),
                                        fourthLeftData.getClass(),  fourthBottomData.getClass(),
                                        fourthRightData.getClass(), fourthTopData.getClass(),
                                        fifthLeftData.getClass(),   fifthBottomData.getClass(),
                                        fifthRightData.getClass(),  fifthTopData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && bottomValue.equals(firstBottomData) &&
            rightValue.equals(firstRightData) && topValue.equals(firstTopData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && bottomValue.equals(secondBottomData) &&
            rightValue.equals(secondRightData) && topValue.equals(secondTopData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && bottomValue.equals(thirdBottomData) &&
            rightValue.equals(thirdRightData) && topValue.equals(thirdTopData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && bottomValue.equals(fourthBottomData) &&
            rightValue.equals(fourthRightData) && topValue.equals(fourthTopData)) {
            fourthBranch.run();
        } else if (leftValue.equals(fifthLeftData) && bottomValue.equals(fifthBottomData) &&
            rightValue.equals(fifthRightData) && topValue.equals(fifthTopData)) {
            fifthBranch.run();
        } else {
            elseBranch.run();
        }
    }

    public <V1, V2, V3, V4>
    void as(V1 firstLeftData,  V2 firstBottomData,
            V3 firstRightData, V4 firstTopData, Runnable firstBranch,
            V1 secondLeftData,  V2 secondBottomData,
            V3 secondRightData, V4 secondTopData, Runnable secondBranch,
            V1 thirdLeftData,   V2 thirdBottomData,
            V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
            V1 fourthLeftData,  V2 fourthBottomData,
            V3 fourthRightData, V4 fourthTopData, Runnable fourthBranch,
            V1 fifthLeftData,   V2 fifthBottomData,
            V3 fifthRightData,  V4 fifthTopData, Runnable fifthBranch,
            Class<Else> elseClass, Runnable elseBranch) {
        matches(quarData.first(), quarData.second(), quarData.third(), quarData.fourth(),
                firstLeftData, firstBottomData, firstRightData, firstTopData, firstBranch,
                secondLeftData, secondBottomData, secondRightData, secondTopData, secondBranch,
                thirdLeftData, thirdBottomData, thirdRightData, thirdTopData, thirdBranch,
                fourthLeftData, fourthBottomData, fourthRightData, fourthTopData, fourthBranch,
                fifthLeftData, fifthBottomData, fifthRightData, fifthTopData, fifthBranch,
                elseClass, elseBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,   V2 firstBottomData,
                 V3 firstRightData,  V4 firstTopData,  Runnable firstBranch,
                 V1 secondLeftData,  V2 secondBottomData,
                 V3 secondRightData, V4 secondTopData, Runnable secondBranch,
                 V1 thirdLeftData,   V2 thirdBottomData,
                 V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
                 V1 fourthLeftData,  V2 fourthBottomData,
                 V3 fourthRightData, V4 fourthTopData, Runnable fourthBranch,
                 V1 fifthLeftData,   V2 fifthBottomData,
                 V3 fifthRightData,  V4 fifthTopData, Runnable fifthBranch,
                 Class<Var> varClass, QuarConsumer<V1, V2, V3, V4> varBranch) {
        if (!Reflection.checkQuarTypes(leftValue.getClass(), bottomValue.getClass(),
                                       rightValue.getClass(), topValue.getClass(),
                                       firstLeftData.getClass(),   firstBottomData.getClass(),
                                       firstRightData.getClass(),  firstTopData.getClass(),
                                       secondLeftData.getClass(),  secondBottomData.getClass(),
                                       secondRightData.getClass(), secondTopData.getClass(),
                                       thirdLeftData.getClass(),   thirdBottomData.getClass(),
                                       thirdRightData.getClass(),  thirdTopData.getClass(),
                                       fourthLeftData.getClass(),  fourthBottomData.getClass(),
                                       fourthRightData.getClass(), fourthTopData.getClass(),
                                       fifthLeftData.getClass(),   fifthBottomData.getClass(),
                                       fifthRightData.getClass(),  fifthTopData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && bottomValue.equals(firstBottomData) &&
            rightValue.equals(firstRightData) && topValue.equals(firstTopData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && bottomValue.equals(secondBottomData) &&
                   rightValue.equals(secondRightData) && topValue.equals(secondTopData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && bottomValue.equals(thirdBottomData) &&
                   rightValue.equals(thirdRightData) && topValue.equals(thirdTopData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && bottomValue.equals(fourthBottomData) &&
                   rightValue.equals(fourthRightData) && topValue.equals(fourthTopData)) {
            fourthBranch.run();
        } else if (leftValue.equals(fifthLeftData) && bottomValue.equals(fifthBottomData) &&
                   rightValue.equals(fifthRightData) && topValue.equals(fifthTopData)) {
            fifthBranch.run();
        } else {
            varBranch.accept(leftValue, bottomValue, rightValue, topValue);
        }
    }

    public <V1, V2, V3, V4>
    void as(V1 firstLeftData,  V2 firstBottomData,
            V3 firstRightData, V4 firstTopData, Runnable firstBranch,
            V1 secondLeftData,  V2 secondBottomData,
            V3 secondRightData, V4 secondTopData, Runnable secondBranch,
            V1 thirdLeftData,   V2 thirdBottomData,
            V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
            V1 fourthLeftData,  V2 fourthBottomData,
            V3 fourthRightData, V4 fourthTopData, Runnable fourthBranch,
            V1 fifthLeftData,   V2 fifthBottomData,
            V3 fifthRightData,  V4 fifthTopData, Runnable fifthBranch,
            Class<Var> varClass, QuarConsumer<V1, V2, V3, V4> varBranch) {
        matches((V1) quarData.first(), (V2) quarData.second(), (V3) quarData.third(), (V4) quarData.fourth(),
                firstLeftData, firstBottomData, firstRightData, firstTopData, firstBranch,
                secondLeftData, secondBottomData, secondRightData, secondTopData, secondBranch,
                thirdLeftData, thirdBottomData, thirdRightData, thirdTopData, thirdBranch,
                fourthLeftData, fourthBottomData, fourthRightData, fourthTopData, fourthBranch,
                fifthLeftData, fifthBottomData, fifthRightData, fifthTopData, fifthBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch,
                 V1 fifthLeftData,  V2 fifthRightData,  Runnable fifthBranch,
                 V1 sixthLeftData,  V2 sixthRightData,  Runnable sixthBranch)  {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                     firstLeftData.getClass(),  firstRightData.getClass(),
                                     secondLeftData.getClass(), secondRightData.getClass(),
                                     thirdLeftData.getClass(),  thirdRightData.getClass(),
                                     fourthLeftData.getClass(), fourthRightData.getClass(),
                                     fifthLeftData.getClass(),  fifthRightData.getClass(),
                                     sixthLeftData.getClass(),  sixthRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
        } else if (leftValue.equals(fifthLeftData) && rightValue.equals(fifthRightData)) {
            fifthBranch.run();
        } else if (leftValue.equals(sixthLeftData) && rightValue.equals(sixthRightData)) {
            sixthBranch.run();
        } else {
            throw new PatternException("Statement must to have only sixth branches");
        }
    }

    public <V1, V2>
    void as(V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
            V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
            V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch,
            V1 fifthLeftData,  V2 fifthRightData,  Runnable fifthBranch,
            V1 sixthLeftData,  V2 sixthRightData,  Runnable sixthBranch) {
        matches(biData.first(), biData.second(),
                firstLeftData, firstRightData, firstBranch,
                secondLeftData, secondRightData, secondBranch,
                thirdLeftData, thirdRightData, thirdBranch,
                fourthLeftData, fourthRightData, fourthBranch,
                fifthLeftData, fifthRightData, fifthBranch,
                sixthLeftData, sixthRightData, sixthBranch);
    }

    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 Tuple.Tuple2<V1, V2> firstItem,  Runnable firstBranch,
                 Tuple.Tuple2<V1, V2> secondItem, Runnable secondBranch,
                 Tuple.Tuple2<V1, V2> thirdItem,  Runnable thirdBranch,
                 Tuple.Tuple2<V1, V2> fourthItem, Runnable fourthBranch,
                 Tuple.Tuple2<V1, V2> fifthItem,  Runnable fifthBranch,
                 Tuple.Tuple2<V1, V2> sixthItem,  Runnable sixthBranch) {
        matches(leftValue, rightValue,
                (V1) firstItem.first(), (V2) firstItem.second(), firstBranch,
                (V1) secondItem.first(), (V2) secondItem.second(), secondBranch,
                (V1) thirdItem.first(), (V2) thirdItem.second(), thirdBranch,
                (V1) fourthItem.first(), (V2) fourthItem.second(), fourthBranch,
                (V1) fifthItem.first(), (V2) fifthItem.second(), fifthBranch,
                (V1) sixthItem.first(), (V2) sixthItem.second(), sixthBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch,
                 V1 fifthLeftData,  V2 fifthRightData,  Runnable fifthBranch,
                 V1 sixthLeftData,  V2 sixthRightData,  Runnable sixthBranch,
                 Class<Else> elseClass, Runnable elseBranch) {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                    firstLeftData.getClass(),  firstRightData.getClass(),
                                    secondLeftData.getClass(), secondRightData.getClass(),
                                    thirdLeftData.getClass(),  thirdRightData.getClass(),
                                    fourthLeftData.getClass(), fourthRightData.getClass(),
                                    fifthLeftData.getClass(),  fifthRightData.getClass(),
                                    sixthLeftData.getClass(),  sixthRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
        } else if (leftValue.equals(fifthLeftData) && rightValue.equals(fifthRightData)) {
            fifthBranch.run();
        } else if (leftValue.equals(sixthLeftData) && rightValue.equals(sixthRightData)) {
            sixthBranch.run();
        } else {
            elseBranch.run();
        }
    }

    public <V1, V2>
    void as(V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
            V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
            V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch,
            V1 fifthLeftData,  V2 fifthRightData,  Runnable fifthBranch,
            V1 sixthLeftData,  V2 sixthRightData,  Runnable sixthBranch,
            Class<Else> elseClass, Runnable elseBranch) {
        matches(biData.first(), biData.second(),
                firstLeftData, firstRightData, firstBranch,
                secondLeftData, secondRightData, secondBranch,
                thirdLeftData, thirdRightData, thirdBranch,
                fourthLeftData, fourthRightData, fourthBranch,
                fifthLeftData, fifthRightData, fifthBranch,
                sixthLeftData, sixthRightData, sixthBranch,
                elseClass, elseBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch,
                 V1 fifthLeftData,  V2 fifthRightData,  Runnable fifthBranch,
                 V1 sixthLeftData,  V2 sixthRightData,  Runnable sixthBranch,
                 Class<Var> varClass, BiConsumer<V1, V2> varBranch) {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                    firstLeftData.getClass(),  firstRightData.getClass(),
                                    secondLeftData.getClass(), secondRightData.getClass(),
                                    thirdLeftData.getClass(),  thirdRightData.getClass(),
                                    fourthLeftData.getClass(), fourthRightData.getClass(),
                                    fifthLeftData.getClass(),  fifthRightData.getClass(),
                                    sixthLeftData.getClass(),  sixthRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
        } else if (leftValue.equals(fifthLeftData) && rightValue.equals(fifthRightData)) {
            fifthBranch.run();
        } else if (leftValue.equals(sixthLeftData) && rightValue.equals(sixthRightData)) {
            sixthBranch.run();
        } else {
            varBranch.accept(leftValue, rightValue);
        }
    }

    public <V1, V2>
    void as(V1 firstLeftData, V2 firstRightData, Runnable firstBranch,
            V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
            V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch,
            V1 fifthLeftData,  V2 fifthRightData,  Runnable fifthBranch,
            V1 sixthLeftData,  V2 sixthRightData,  Runnable sixthBranch,
            Class<Var> varClass, BiConsumer<V1, V2> varBranch) {
        matches((V1) biData.first(), (V2) biData.second(),
                firstLeftData, firstRightData, firstBranch,
                secondLeftData, secondRightData, secondBranch,
                thirdLeftData, thirdRightData, thirdBranch,
                fourthLeftData, fourthRightData, fourthBranch,
                fifthLeftData, fifthRightData, fifthBranch,
                sixthLeftData, sixthRightData, sixthBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch,
                 V1 fifthLeftData,  V2 fifthMiddleData,  V3 fifthRightData,  Runnable fifthBranch,
                 V1 sixthLeftData,  V2 sixthMiddleData,  V3 sixthRightData,  Runnable sixthBranch)  {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(),  firstMiddleData.getClass(),  firstRightData.getClass(),
                                       secondLeftData.getClass(), secondMiddleData.getClass(), secondRightData.getClass(),
                                       thirdLeftData.getClass(),  thirdMiddleData.getClass(),  thirdRightData.getClass(),
                                       fourthLeftData.getClass(), fourthMiddleData.getClass(), fourthRightData.getClass(),
                                       fifthLeftData.getClass(),  fifthMiddleData.getClass(),  fifthRightData.getClass(),
                                       sixthLeftData.getClass(),  sixthMiddleData.getClass(),  sixthRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && middleValue.equals(thirdMiddleData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && middleValue.equals(fourthMiddleData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
        } else if (leftValue.equals(fifthLeftData) && middleValue.equals(fifthMiddleData) && rightValue.equals(fifthRightData)) {
            fifthBranch.run();
        }  else if (leftValue.equals(sixthLeftData) && middleValue.equals(sixthMiddleData) && rightValue.equals(sixthRightData)) {
            sixthBranch.run();
        } else {
            throw new PatternException("Statement must to have only sixth branches");
        }
    }

    public <V1, V2, V3>
    void as(V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch,
            V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
            V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch,
            V1 fifthLeftData,  V2 fifthMiddleData,  V3 fifthRightData,  Runnable fifthBranch,
            V1 sixthLeftData,  V2 sixthMiddleData,  V3 sixthRightData,  Runnable sixthBranch) {
        matches(triData.first(), triData.second(), triData.third(),
                firstLeftData, firstMiddleData, firstRightData, firstBranch,
                secondLeftData, secondMiddleData, secondRightData, secondBranch,
                thirdLeftData, thirdMiddleData, thirdRightData, thirdBranch,
                fourthLeftData, fourthMiddleData, fourthRightData, fourthBranch,
                fifthLeftData, fifthMiddleData, fifthRightData, fifthBranch,
                sixthLeftData, sixthMiddleData, sixthRightData, sixthBranch);
    }

    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 Tuple.Tuple3<V1, V2, V3> firstItem,  Runnable firstBranch,
                 Tuple.Tuple3<V1, V2, V3> secondItem, Runnable secondBranch,
                 Tuple.Tuple3<V1, V2, V3> thirdItem,  Runnable thirdBranch,
                 Tuple.Tuple3<V1, V2, V3> fourthItem, Runnable fourthBranch,
                 Tuple.Tuple3<V1, V2, V3> fifthItem,  Runnable fifthBranch,
                 Tuple.Tuple3<V1, V2, V3> sixthItem,  Runnable sixthBranch) {
        matches(leftValue, middleValue, rightValue,
                (V1) firstItem.first(), (V2) firstItem.second(), firstItem.third(), firstBranch,
                (V1) secondItem.first(), (V2) secondItem.second(), secondItem.third(), secondBranch,
                (V1) thirdItem.first(), (V2) thirdItem.second(), thirdItem.third(), thirdBranch,
                (V1) fourthItem.first(), (V2) fourthItem.second(), thirdItem.third(), fourthBranch,
                (V1) fifthItem.first(), (V2) fifthItem.second(), fifthItem.third(), fifthBranch,
                (V1) sixthItem.first(), (V2) sixthItem.second(), sixthItem.third(), sixthBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch,
                 V1 fifthLeftData,  V2 fifthMiddleData,  V3 fifthRightData,  Runnable fifthBranch,
                 V1 sixthLeftData,  V2 sixthMiddleData,  V3 sixthRightData,  Runnable sixthBranch,
                 Class<Else> elseClass, Runnable elseBranch) {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(),  firstMiddleData.getClass(),  firstRightData.getClass(),
                                       secondLeftData.getClass(), secondMiddleData.getClass(), secondRightData.getClass(),
                                       thirdLeftData.getClass(),  thirdMiddleData.getClass(),  thirdRightData.getClass(),
                                       fourthLeftData.getClass(), fourthMiddleData.getClass(), fourthRightData.getClass(),
                                       fifthLeftData.getClass(),  fifthMiddleData.getClass(),  fifthRightData.getClass(),
                                       sixthLeftData.getClass(),  sixthMiddleData.getClass(),  sixthRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && middleValue.equals(thirdMiddleData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && middleValue.equals(fourthMiddleData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
        } else if (leftValue.equals(fifthLeftData) && middleValue.equals(fifthMiddleData) && rightValue.equals(fifthRightData)) {
            fifthBranch.run();
        }  else if (leftValue.equals(sixthLeftData) && middleValue.equals(sixthMiddleData) && rightValue.equals(sixthRightData)) {
            sixthBranch.run();
        } else {
            elseBranch.run();
        }
    }

    public <V1, V2, V3>
    void as(V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch,
            V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
            V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch,
            V1 fifthLeftData,  V2 fifthMiddleData,  V3 fifthRightData,  Runnable fifthBranch,
            V1 sixthLeftData,  V2 sixthMiddleData,  V3 sixthRightData,  Runnable sixthBranch,
            Class<Else> elseClass, Runnable elseBranch) {
        matches(triData.first(), triData.second(), triData.third(),
                firstLeftData, firstMiddleData, firstRightData, firstBranch,
                secondLeftData, secondMiddleData, secondRightData, secondBranch,
                thirdLeftData, thirdMiddleData, thirdRightData, thirdBranch,
                fourthLeftData, fourthMiddleData, fourthRightData, fourthBranch,
                fifthLeftData, fifthMiddleData, fifthRightData, fifthBranch,
                sixthLeftData, sixthMiddleData, sixthRightData, sixthBranch,
                elseClass, elseBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch,
                 V1 fifthLeftData,  V2 fifthMiddleData,  V3 fifthRightData,  Runnable fifthBranch,
                 V1 sixthLeftData,  V2 sixthMiddleData,  V3 sixthRightData,  Runnable sixthBranch,
                 Class<Var> varClass, TriConsumer<V1, V2, V3> varBranch) {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(),  firstMiddleData.getClass(),  firstRightData.getClass(),
                                       secondLeftData.getClass(), secondMiddleData.getClass(), secondRightData.getClass(),
                                       thirdLeftData.getClass(),  thirdMiddleData.getClass(),  thirdRightData.getClass(),
                                       fourthLeftData.getClass(), fourthMiddleData.getClass(), fourthRightData.getClass(),
                                       fifthLeftData.getClass(),  fifthMiddleData.getClass(),  fifthRightData.getClass(),
                                       sixthLeftData.getClass(),  sixthMiddleData.getClass(),  sixthRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && middleValue.equals(thirdMiddleData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && middleValue.equals(fourthMiddleData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
        } else if (leftValue.equals(fifthLeftData) && middleValue.equals(fifthMiddleData) && rightValue.equals(fifthRightData)) {
            fifthBranch.run();
        }  else if (leftValue.equals(sixthLeftData) && middleValue.equals(sixthMiddleData) && rightValue.equals(sixthRightData)) {
            sixthBranch.run();
        } else {
            varBranch.accept(leftValue, middleValue, rightValue);
        }
    }

    public <V1, V2, V3>
    void as(V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch,
            V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
            V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
            V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch,
            V1 fifthLeftData,  V2 fifthMiddleData,  V3 fifthRightData,  Runnable fifthBranch,
            V1 sixthLeftData,  V2 sixthMiddleData,  V3 sixthRightData,  Runnable sixthBranch,
            Class<Var> varClass, TriConsumer<V1, V2, V3> varBranch) {
        matches((V1) triData.first(), (V2) triData.second(), (V3) triData.third(),
                firstLeftData, firstMiddleData, firstRightData, firstBranch,
                secondLeftData, secondMiddleData, secondRightData, secondBranch,
                thirdLeftData, thirdMiddleData, thirdRightData, thirdBranch,
                fourthLeftData, fourthMiddleData, fourthRightData, fourthBranch,
                fifthLeftData, fifthMiddleData, fifthRightData, fifthBranch,
                sixthLeftData, sixthMiddleData, sixthRightData, sixthBranch,
                varClass, varBranch);
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,   V2 firstBottomData,
                 V3 firstRightData,  V4 firstTopData,  Runnable firstBranch,
                 V1 secondLeftData,  V2 secondBottomData,
                 V3 secondRightData, V4 secondTopData, Runnable secondBranch,
                 V1 thirdLeftData,   V2 thirdBottomData,
                 V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
                 V1 fourthLeftData,  V2 fourthBottomData,
                 V3 fourthRightData, V4 fourthTopData, Runnable fourthBranch,
                 V1 fifthLeftData,   V2 fifthBottomData,
                 V3 fifthRightData,  V4 fifthTopData, Runnable fifthBranch,
                 V1 sixthLeftData,   V2 sixthBottomData,
                 V3 sixthRightData,  V4 sixthTopData, Runnable sixthBranch)  {
        if (!Reflection.checkQuarTypes(leftValue.getClass(), bottomValue.getClass(), rightValue.getClass(), topValue.getClass(),
                firstLeftData.getClass(),  firstBottomData.getClass(),  firstRightData.getClass(),  firstTopData.getClass(),
                secondLeftData.getClass(), secondBottomData.getClass(), secondRightData.getClass(), secondTopData.getClass(),
                thirdLeftData.getClass(),  thirdBottomData.getClass(),  thirdRightData.getClass(),  thirdTopData.getClass(),
                fourthLeftData.getClass(), fourthBottomData.getClass(), fourthRightData.getClass(), fourthTopData.getClass(),
                fifthLeftData.getClass(),  fifthBottomData.getClass(),  fifthRightData.getClass(),  fifthTopData.getClass(),
                sixthLeftData.getClass(),  sixthBottomData.getClass(),  sixthRightData.getClass(),  sixthTopData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && bottomValue.equals(firstBottomData) &&
            rightValue.equals(firstRightData) && topValue.equals(firstTopData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && bottomValue.equals(secondBottomData) &&
            rightValue.equals(secondRightData) && topValue.equals(secondTopData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && bottomValue.equals(thirdBottomData) &&
            rightValue.equals(thirdRightData) && topValue.equals(thirdTopData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && bottomValue.equals(fourthBottomData) &&
            rightValue.equals(fourthRightData) && topValue.equals(fourthTopData)) {
            fourthBranch.run();
        } else if (leftValue.equals(fifthLeftData) && bottomValue.equals(fifthBottomData) &&
            rightValue.equals(fifthRightData) && topValue.equals(fifthTopData)) {
            fifthBranch.run();
        } else if (leftValue.equals(sixthLeftData) && bottomValue.equals(sixthBottomData) &&
            rightValue.equals(sixthRightData) && topValue.equals(sixthTopData)) {
            sixthBranch.run();
        } else {
            throw new PatternException("Statement must to have only sixth branches");
        }
    }

    public <V1, V2, V3, V4>
    void as(V1 firstLeftData,  V2 firstBottomData,
            V3 firstRightData, V4 firstTopData, Runnable firstBranch,
            V1 secondLeftData,  V2 secondBottomData,
            V3 secondRightData, V4 secondTopData, Runnable secondBranch,
            V1 thirdLeftData,   V2 thirdBottomData,
            V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
            V1 fourthLeftData,  V2 fourthBottomData,
            V3 fourthRightData, V4 fourthTopData, Runnable fourthBranch,
            V1 fifthLeftData,   V2 fifthBottomData,
            V3 fifthRightData,  V4 fifthTopData, Runnable fifthBranch,
            V1 sixthLeftData,   V2 sixthBottomData,
            V3 sixthRightData,  V4 sixthTopData, Runnable sixthBranch) {
        matches(quarData.first(), quarData.second(), quarData.third(), quarData.fourth(),
                firstLeftData, firstBottomData, firstRightData, firstTopData, firstBranch,
                secondLeftData, secondBottomData, secondRightData, secondTopData, secondBranch,
                thirdLeftData, thirdBottomData, thirdRightData, thirdTopData, thirdBranch,
                fourthLeftData, fourthBottomData, fourthRightData, fourthTopData, fourthBranch,
                fifthLeftData, fifthBottomData, fifthRightData, fifthTopData, fifthBranch,
                sixthLeftData, sixthBottomData, sixthRightData, sixthTopData, sixthBranch);
    }

    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 Tuple.Tuple4<V1, V2, V3, V4> firstItem,  Runnable firstBranch,
                 Tuple.Tuple4<V1, V2, V3, V4> secondItem, Runnable secondBranch,
                 Tuple.Tuple4<V1, V2, V3, V4> thirdItem,  Runnable thirdBranch,
                 Tuple.Tuple4<V1, V2, V3, V4> fourthItem, Runnable fourthBranch,
                 Tuple.Tuple4<V1, V2, V3, V4> fifthItem,  Runnable fifthBranch,
                 Tuple.Tuple4<V1, V2, V3, V4> sixthItem,  Runnable sixthBranch) {
        matches(leftValue, bottomValue, rightValue, topValue,
                (V1) firstItem.first(), (V2) firstItem.second(), firstItem.third(), firstItem.fourth(), firstBranch,
                (V1) secondItem.first(), (V2) secondItem.second(), secondItem.third(), secondItem.fourth(), secondBranch,
                (V1) thirdItem.first(), (V2) thirdItem.second(), thirdItem.third(), thirdItem.fourth(), thirdBranch,
                (V1) fourthItem.first(), (V2) fourthItem.second(), fourthItem.third(), fourthItem.fourth(), fourthBranch,
                (V1) fifthItem.first(), (V2) fifthItem.second(), fifthItem.third(), fifthItem.fourth(), fifthBranch,
                (V1) sixthItem.first(), (V2) sixthItem.second(), sixthItem.third(), sixthItem.fourth(), sixthBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,   V2 firstBottomData,
                 V3 firstRightData,  V4 firstTopData,  Runnable firstBranch,
                 V1 secondLeftData,  V2 secondBottomData,
                 V3 secondRightData, V4 secondTopData, Runnable secondBranch,
                 V1 thirdLeftData,   V2 thirdBottomData,
                 V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
                 V1 fourthLeftData,  V2 fourthBottomData,
                 V3 fourthRightData, V4 fourthTopData, Runnable fourthBranch,
                 V1 fifthLeftData,   V2 fifthBottomData,
                 V3 fifthRightData,  V4 fifthTopData, Runnable fifthBranch,
                 V1 sixthLeftData,   V2 sixthBottomData,
                 V3 sixthRightData,  V4 sixthTopData, Runnable sixthBranch,
                 Class<Else> elseClass, Runnable elseBranch) {
        if (!Reflection.checkQuarTypes(leftValue.getClass(), bottomValue.getClass(),
                                       rightValue.getClass(), topValue.getClass(),
                                       firstLeftData.getClass(),   firstBottomData.getClass(),
                                       firstRightData.getClass(),  firstTopData.getClass(),
                                       secondLeftData.getClass(),  secondBottomData.getClass(),
                                       secondRightData.getClass(), secondTopData.getClass(),
                                       thirdLeftData.getClass(),   thirdBottomData.getClass(),
                                       thirdRightData.getClass(),  thirdTopData.getClass(),
                                       fourthLeftData.getClass(),  fourthBottomData.getClass(),
                                       fourthRightData.getClass(), fourthTopData.getClass(),
                                       fifthLeftData.getClass(),   fifthBottomData.getClass(),
                                       fifthRightData.getClass(),  fifthTopData.getClass(),
                                       sixthLeftData.getClass(),   sixthBottomData.getClass(),
                                       sixthRightData.getClass(),  sixthTopData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && bottomValue.equals(firstBottomData) &&
            rightValue.equals(firstRightData) && topValue.equals(firstTopData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && bottomValue.equals(secondBottomData) &&
            rightValue.equals(secondRightData) && topValue.equals(secondTopData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && bottomValue.equals(thirdBottomData) &&
            rightValue.equals(thirdRightData) && topValue.equals(thirdTopData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && bottomValue.equals(fourthBottomData) &&
            rightValue.equals(fourthRightData) && topValue.equals(fourthTopData)) {
            fourthBranch.run();
        } else if (leftValue.equals(fifthLeftData) && bottomValue.equals(fifthBottomData) &&
            rightValue.equals(fifthRightData) && topValue.equals(fifthTopData)) {
            fifthBranch.run();
        } else if (leftValue.equals(sixthLeftData) && bottomValue.equals(sixthBottomData) &&
            rightValue.equals(sixthRightData) && topValue.equals(sixthTopData)) {
            sixthBranch.run();
        } else {
            elseBranch.run();
        }
    }

    public <V1, V2, V3, V4>
    void as(V1 firstLeftData,  V2 firstBottomData,
            V3 firstRightData, V4 firstTopData, Runnable firstBranch,
            V1 secondLeftData,  V2 secondBottomData,
            V3 secondRightData, V4 secondTopData, Runnable secondBranch,
            V1 thirdLeftData,   V2 thirdBottomData,
            V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
            V1 fourthLeftData,  V2 fourthBottomData,
            V3 fourthRightData, V4 fourthTopData, Runnable fourthBranch,
            V1 fifthLeftData,   V2 fifthBottomData,
            V3 fifthRightData,  V4 fifthTopData, Runnable fifthBranch,
            V1 sixthLeftData,   V2 sixthBottomData,
            V3 sixthRightData,  V4 sixthTopData, Runnable sixthBranch,
            Class<Else> elseClass, Runnable elseBranch) {
        matches(quarData.first(), quarData.second(), quarData.third(), quarData.fourth(),
                firstLeftData, firstBottomData, firstRightData, firstTopData, firstBranch,
                secondLeftData, secondBottomData, secondRightData, secondTopData, secondBranch,
                thirdLeftData, thirdBottomData, thirdRightData, thirdTopData, thirdBranch,
                fourthLeftData, fourthBottomData, fourthRightData, fourthTopData, fourthBranch,
                fifthLeftData, fifthBottomData, fifthRightData, fifthTopData, fifthBranch,
                sixthLeftData, sixthBottomData, sixthRightData, sixthTopData, sixthBranch,
                elseClass, elseBranch);
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,   V2 firstBottomData,
                 V3 firstRightData,  V4 firstTopData,  Runnable firstBranch,
                 V1 secondLeftData,  V2 secondBottomData,
                 V3 secondRightData, V4 secondTopData, Runnable secondBranch,
                 V1 thirdLeftData,   V2 thirdBottomData,
                 V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
                 V1 fourthLeftData,  V2 fourthBottomData,
                 V3 fourthRightData, V4 fourthTopData, Runnable fourthBranch,
                 V1 fifthLeftData,   V2 fifthBottomData,
                 V3 fifthRightData,  V4 fifthTopData, Runnable fifthBranch,
                 V1 sixthLeftData,   V2 sixthBottomData,
                 V3 sixthRightData,  V4 sixthTopData, Runnable sixthBranch,
                 Class<Var> varClass, QuarConsumer<V1, V2, V3, V4> varBranch) {
        if (!Reflection.checkQuarTypes(leftValue.getClass(), bottomValue.getClass(),
                                       rightValue.getClass(), topValue.getClass(),
                                       firstLeftData.getClass(),   firstBottomData.getClass(),
                                       firstRightData.getClass(),  firstTopData.getClass(),
                                       secondLeftData.getClass(),  secondBottomData.getClass(),
                                       secondRightData.getClass(), secondTopData.getClass(),
                                       thirdLeftData.getClass(),   thirdBottomData.getClass(),
                                       thirdRightData.getClass(),  thirdTopData.getClass(),
                                       fourthLeftData.getClass(),  fourthBottomData.getClass(),
                                       fourthRightData.getClass(), fourthTopData.getClass(),
                                       fifthLeftData.getClass(),   fifthBottomData.getClass(),
                                       fifthRightData.getClass(),  fifthTopData.getClass(),
                                       sixthLeftData.getClass(),   sixthBottomData.getClass(),
                                       sixthRightData.getClass(),  sixthTopData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && bottomValue.equals(firstBottomData) &&
            rightValue.equals(firstRightData) && topValue.equals(firstTopData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && bottomValue.equals(secondBottomData) &&
                   rightValue.equals(secondRightData) && topValue.equals(secondTopData)) {
            secondBranch.run();
        } else if (leftValue.equals(thirdLeftData) && bottomValue.equals(thirdBottomData) &&
                   rightValue.equals(thirdRightData) && topValue.equals(thirdTopData)) {
            thirdBranch.run();
        } else if (leftValue.equals(fourthLeftData) && bottomValue.equals(fourthBottomData) &&
                   rightValue.equals(fourthRightData) && topValue.equals(fourthTopData)) {
            fourthBranch.run();
        } else if (leftValue.equals(fifthLeftData) && bottomValue.equals(fifthBottomData) &&
                   rightValue.equals(fifthRightData) && topValue.equals(fifthTopData)) {
            fifthBranch.run();
        } else if (leftValue.equals(sixthLeftData) && bottomValue.equals(sixthBottomData) &&
                   rightValue.equals(sixthRightData) && topValue.equals(sixthTopData)) {
            sixthBranch.run();
        } else {
            varBranch.accept(leftValue, bottomValue, rightValue, topValue);
        }
    }

    public <V1, V2, V3, V4>
    void as(V1 firstLeftData,  V2 firstBottomData,
            V3 firstRightData, V4 firstTopData, Runnable firstBranch,
            V1 secondLeftData,  V2 secondBottomData,
            V3 secondRightData, V4 secondTopData, Runnable secondBranch,
            V1 thirdLeftData,   V2 thirdBottomData,
            V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch,
            V1 fourthLeftData,  V2 fourthBottomData,
            V3 fourthRightData, V4 fourthTopData, Runnable fourthBranch,
            V1 fifthLeftData,   V2 fifthBottomData,
            V3 fifthRightData,  V4 fifthTopData, Runnable fifthBranch,
            V1 sixthLeftData,   V2 sixthBottomData,
            V3 sixthRightData,  V4 sixthTopData, Runnable sixthBranch,
            Class<Var> varClass, QuarConsumer<V1, V2, V3, V4> varBranch) {
        matches((V1) quarData.first(), (V2) quarData.second(), (V3) quarData.third(), (V4) quarData.fourth(),
                firstLeftData, firstBottomData, firstRightData, firstTopData, firstBranch,
                secondLeftData, secondBottomData, secondRightData, secondTopData, secondBranch,
                thirdLeftData, thirdBottomData, thirdRightData, thirdTopData, thirdBranch,
                fourthLeftData, fourthBottomData, fourthRightData, fourthTopData, fourthBranch,
                fifthLeftData, fifthBottomData, fifthRightData, fifthTopData, fifthBranch,
                sixthLeftData, sixthBottomData, sixthRightData, sixthTopData, sixthBranch,
                varClass, varBranch);
    }
}
