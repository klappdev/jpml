/*
 * JPML - Java pattern matching library.
 *
 * Tuple pattern allow test for equality multiple pieces
 * with constants. Maximum number of branches for match six
 * with fourth pieces.
 */
package org.kl.pattern;

import org.kl.error.PatternException;
import org.kl.reflect.Reflection;
import org.kl.state.Else;

public class TuplePattern {

    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData, V2 firstRightData, Runnable firstBranch) throws PatternException {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                     firstLeftData.getClass(), firstRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        }
    }

    @SuppressWarnings("unused")
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData, V2 firstRightData, Runnable firstBranch,
                 Class<Else> elseClass, Runnable elseBranch) throws PatternException {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                     firstLeftData.getClass(), firstRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
            return;
        }

        elseBranch.run();
    }

    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch) throws PatternException {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(), firstMiddleData.getClass(), firstRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        }
    }

    @SuppressWarnings("unused")
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData, V2 firstMiddleData, V3 firstRightData, Runnable firstBranch,
                 Class<Else> elseClass, Runnable elseBranch) throws PatternException {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(), firstMiddleData.getClass(), firstRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
            return;
        }

        elseBranch.run();
    }

    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,  V2 firstBottomData,
                 V3 firstRightData, V4 firstTopData, Runnable firstBranch) throws PatternException {
        if (!Reflection.checkQuarTypes(leftValue.getClass(),  bottomValue.getClass(),
                                       rightValue.getClass(), topValue.getClass(),
                                       firstLeftData.getClass(),  firstBottomData.getClass(),
                                       firstRightData.getClass(), firstTopData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && bottomValue.equals(firstBottomData) &&
            rightValue.equals(firstRightData) && topValue.equals(firstTopData)) {
            firstBranch.run();
        }
    }

    @SuppressWarnings("unused")
    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,  V2 firstBottomData,
                 V3 firstRightData, V4 firstTopData, Runnable firstBranch,
                 Class<Else> elseClass, Runnable elseBranch) throws PatternException {
        if (!Reflection.checkQuarTypes(leftValue.getClass(),  bottomValue.getClass(),
                                       rightValue.getClass(), topValue.getClass(),
                                       firstLeftData.getClass(),  firstBottomData.getClass(),
                                       firstRightData.getClass(), firstTopData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && bottomValue.equals(firstBottomData) &&
            rightValue.equals(firstRightData) && topValue.equals(firstTopData)) {
            firstBranch.run();
            return;
        }

        elseBranch.run();
    }

    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondRightData, Runnable secondBranch) throws PatternException {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                     firstLeftData.getClass(),  firstRightData.getClass(),
                                     secondLeftData.getClass(), secondRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        }
    }

    @SuppressWarnings("unused")
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                 Class<Else> elseClass, Runnable elseBranch) throws PatternException {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                     firstLeftData.getClass(),  firstRightData.getClass(),
                                     secondLeftData.getClass(), secondRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
            return;
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
            return;
        }

        elseBranch.run();
    }

    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch) throws PatternException {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(),  firstMiddleData.getClass(),  firstRightData.getClass(),
                                       secondLeftData.getClass(), secondMiddleData.getClass(), secondRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
        }
    }

    @SuppressWarnings("unused")
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 Class<Else> elseClass, Runnable elseBranch) throws PatternException {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(),  firstMiddleData.getClass(),  firstRightData.getClass(),
                                       secondLeftData.getClass(), secondMiddleData.getClass(), secondRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
            return;
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
            return;
        }

        elseBranch.run();
    }

    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,   V2 firstBottomData,
                 V3 firstRightData,  V4 firstTopData,  Runnable firstBranch,
                 V1 secondLeftData,  V2 secondBottomData,
                 V3 secondRightData, V4 secondTopData, Runnable secondBranch) throws PatternException {
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
        }
    }

    @SuppressWarnings("unused")
    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,   V2 firstBottomData,
                 V3 firstRightData,  V4 firstTopData,  Runnable firstBranch,
                 V1 secondLeftData,  V2 secondBottomData,
                 V3 secondRightData, V4 secondTopData, Runnable secondBranch,
                 Class<Else> elseClass, Runnable elseBranch) throws PatternException {
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
            return;
        } else if (leftValue.equals(secondLeftData) && bottomValue.equals(secondBottomData) &&
            rightValue.equals(secondRightData) && topValue.equals(secondTopData)) {
            secondBranch.run();
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2> void matches(V1 leftValue, V2 rightValue,
                                V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                                V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                                V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch) throws PatternException {
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
        }
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch) throws PatternException {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                    firstLeftData.getClass(),  firstRightData.getClass(),
                                    secondLeftData.getClass(), secondRightData.getClass(),
                                    thirdLeftData.getClass(),  thirdRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
            return;
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
            return;
        } else if (leftValue.equals(thirdLeftData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch) throws PatternException {
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
        }
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
                 Class<Else> elseClass, Runnable elseBranch) throws PatternException {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                       firstLeftData.getClass(),  firstMiddleData.getClass(),  firstRightData.getClass(),
                                       secondLeftData.getClass(), secondMiddleData.getClass(), secondRightData.getClass(),
                                       thirdLeftData.getClass(),  thirdMiddleData.getClass(),  thirdRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
            return;
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
            return;
        } else if (leftValue.equals(thirdLeftData) && middleValue.equals(thirdMiddleData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2, V3, V4>
    void matches(V1 leftValue, V2 bottomValue, V3 rightValue, V4 topValue,
                 V1 firstLeftData,   V2 firstBottomData,
                 V3 firstRightData,  V4 firstTopData,  Runnable firstBranch,
                 V1 secondLeftData,  V2 secondBottomData,
                 V3 secondRightData, V4 secondTopData, Runnable secondBranch,
                 V1 thirdLeftData,   V2 thirdBottomData,
                 V3 thirdRightData,  V4 thirdTopData,  Runnable thirdBranch) throws PatternException {
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
        }
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
                 Class<Else> elseClass, Runnable elseBranch) throws PatternException {
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
            return;
        } else if (leftValue.equals(secondLeftData) && bottomValue.equals(secondBottomData) &&
            rightValue.equals(secondRightData) && topValue.equals(secondTopData)) {
            secondBranch.run();
            return;
        } else if (leftValue.equals(thirdLeftData) && bottomValue.equals(thirdBottomData) &&
            rightValue.equals(thirdRightData) && topValue.equals(thirdTopData)) {
            thirdBranch.run();
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2> void matches(V1 leftValue, V2 rightValue,
                                V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                                V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                                V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
                                V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch) throws PatternException {
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
        }
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2> void matches(V1 leftValue, V2 rightValue,
                                        V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                                        V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                                        V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
                                        V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch,
                                        Class<Else> elseClass, Runnable elseBranch) throws PatternException {
        if (!Reflection.checkBiTypes(leftValue.getClass(), rightValue.getClass(),
                                    firstLeftData.getClass(),  firstRightData.getClass(),
                                    secondLeftData.getClass(), secondRightData.getClass(),
                                    thirdLeftData.getClass(),  thirdRightData.getClass(),
                                    fourthLeftData.getClass(), fourthRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
            return;
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
            return;
        } else if (leftValue.equals(thirdLeftData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
            return;
        } else if (leftValue.equals(fourthLeftData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch) throws PatternException {
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
        }
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch,
                 Class<Else> elseClass, Runnable elseBranch) throws PatternException {
        if (!Reflection.checkTrioTypes(leftValue.getClass(), middleValue.getClass(), rightValue.getClass(),
                                        firstLeftData.getClass(),  firstMiddleData.getClass(),  firstRightData.getClass(),
                                        secondLeftData.getClass(), secondMiddleData.getClass(), secondRightData.getClass(),
                                        thirdLeftData.getClass(),  thirdMiddleData.getClass(),  thirdRightData.getClass(),
                                        fourthLeftData.getClass(), fourthMiddleData.getClass(), fourthRightData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (leftValue.equals(firstLeftData) && middleValue.equals(firstMiddleData) && rightValue.equals(firstRightData)) {
            firstBranch.run();
            return;
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
            return;
        } else if (leftValue.equals(thirdLeftData) && middleValue.equals(thirdMiddleData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
            return;
        }  else if (leftValue.equals(fourthLeftData) && middleValue.equals(fourthMiddleData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
            return;
        }

        elseBranch.run();
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
                 V3 fourthRightData, V4 fourthTopData, Runnable fourthBranch) throws PatternException {
        if (!Reflection.checkQuarTypes(leftValue.getClass(), bottomValue.getClass(), rightValue.getClass(), topValue.getClass(),
                firstLeftData.getClass(),  firstBottomData.getClass(),  firstRightData.getClass(),  firstTopData.getClass(),
                secondLeftData.getClass(), secondBottomData.getClass(), secondRightData.getClass(), secondTopData.getClass(),
                thirdLeftData.getClass(),  thirdBottomData.getClass(),  thirdRightData.getClass(),  thirdTopData.getClass(),
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
        }
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
                 Class<Else> elseClass, Runnable elseBranch) throws PatternException {
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
            return;
        } else if (leftValue.equals(secondLeftData) && bottomValue.equals(secondBottomData) &&
            rightValue.equals(secondRightData) && topValue.equals(secondTopData)) {
            secondBranch.run();
            return;
        } else if (leftValue.equals(thirdLeftData) && bottomValue.equals(thirdBottomData) &&
            rightValue.equals(thirdRightData) && topValue.equals(thirdTopData)) {
            thirdBranch.run();
            return;
        } else if (leftValue.equals(fourthLeftData) && bottomValue.equals(fourthBottomData) &&
            rightValue.equals(fourthRightData) && topValue.equals(fourthTopData)) {
            fourthBranch.run();
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
                V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch,
                V1 fifthLeftData,  V2 fifthRightData,  Runnable fifthBranch) throws PatternException {
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
        }
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2>
    void matches(V1 leftValue, V2 rightValue,
                 V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch,
                 V1 fifthLeftData,  V2 fifthRightData,  Runnable fifthBranch,
                 Class<Else> elseClass, Runnable elseBranch) throws PatternException {
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
            return;
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
            return;
        } else if (leftValue.equals(thirdLeftData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
            return;
        } else if (leftValue.equals(fourthLeftData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
            return;
        } else if (leftValue.equals(fifthLeftData) && rightValue.equals(fifthRightData)) {
            fifthBranch.run();
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch,
                 V1 fifthLeftData,  V2 fifthMiddleData,  V3 fifthRightData,  Runnable fifthBranch) throws PatternException {
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
        }
    }

    @SuppressWarnings({"Duplicates", "unused"})
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch,
                 V1 fifthLeftData,  V2 fifthMiddleData,  V3 fifthRightData,  Runnable fifthBranch,
                 Class<Else> elseClass, Runnable elseBranch) throws PatternException {
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
            return;
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
            return;
        } else if (leftValue.equals(thirdLeftData) && middleValue.equals(thirdMiddleData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
            return;
        } else if (leftValue.equals(fourthLeftData) && middleValue.equals(fourthMiddleData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
            return;
        } else if (leftValue.equals(fifthLeftData) && middleValue.equals(fifthMiddleData) && rightValue.equals(fifthRightData)) {
            fifthBranch.run();
            return;
        }

        elseBranch.run();
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
                 V3 fifthRightData,  V4 fifthTopData, Runnable fifthBranch) throws PatternException {
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
        }
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
                 Class<Else> elseClass, Runnable elseBranch) throws PatternException {
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
            return;
        } else if (leftValue.equals(secondLeftData) && bottomValue.equals(secondBottomData) &&
            rightValue.equals(secondRightData) && topValue.equals(secondTopData)) {
            secondBranch.run();
            return;
        } else if (leftValue.equals(thirdLeftData) && bottomValue.equals(thirdBottomData) &&
            rightValue.equals(thirdRightData) && topValue.equals(thirdTopData)) {
            thirdBranch.run();
            return;
        } else if (leftValue.equals(fourthLeftData) && bottomValue.equals(fourthBottomData) &&
            rightValue.equals(fourthRightData) && topValue.equals(fourthTopData)) {
            fourthBranch.run();
            return;
        } else if (leftValue.equals(fifthLeftData) && bottomValue.equals(fifthBottomData) &&
            rightValue.equals(fifthRightData) && topValue.equals(fifthTopData)) {
            fifthBranch.run();
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2> void matches(V1 leftValue, V2 rightValue,
                                V1 firstLeftData,  V2 firstRightData,  Runnable firstBranch,
                                V1 secondLeftData, V2 secondRightData, Runnable secondBranch,
                                V1 thirdLeftData,  V2 thirdRightData,  Runnable thirdBranch,
                                V1 fourthLeftData, V2 fourthRightData, Runnable fourthBranch,
                                V1 fifthLeftData,  V2 fifthRightData,  Runnable fifthBranch,
                                V1 sixthLeftData,  V2 sixthRightData,  Runnable sixthBranch) throws PatternException {
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
        }
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
                 Class<Else> elseClass, Runnable elseBranch) throws PatternException {
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
            return;
        } else if (leftValue.equals(secondLeftData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
            return;
        } else if (leftValue.equals(thirdLeftData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
            return;
        } else if (leftValue.equals(fourthLeftData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
            return;
        } else if (leftValue.equals(fifthLeftData) && rightValue.equals(fifthRightData)) {
            fifthBranch.run();
            return;
        } else if (leftValue.equals(sixthLeftData) && rightValue.equals(sixthRightData)) {
            sixthBranch.run();
            return;
        }

        elseBranch.run();
    }

    @SuppressWarnings("Duplicates")
    public static <V1, V2, V3>
    void matches(V1 leftValue, V2 middleValue, V3 rightValue,
                 V1 firstLeftData,  V2 firstMiddleData,  V3 firstRightData,  Runnable firstBranch,
                 V1 secondLeftData, V2 secondMiddleData, V3 secondRightData, Runnable secondBranch,
                 V1 thirdLeftData,  V2 thirdMiddleData,  V3 thirdRightData,  Runnable thirdBranch,
                 V1 fourthLeftData, V2 fourthMiddleData, V3 fourthRightData, Runnable fourthBranch,
                 V1 fifthLeftData,  V2 fifthMiddleData,  V3 fifthRightData,  Runnable fifthBranch,
                 V1 sixthLeftData,  V2 sixthMiddleData,  V3 sixthRightData,  Runnable sixthBranch) throws PatternException {
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
        }
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
                 Class<Else> elseClass, Runnable elseBranch) throws PatternException {
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
            return;
        } else if (leftValue.equals(secondLeftData) && middleValue.equals(secondMiddleData) && rightValue.equals(secondRightData)) {
            secondBranch.run();
            return;
        } else if (leftValue.equals(thirdLeftData) && middleValue.equals(thirdMiddleData) && rightValue.equals(thirdRightData)) {
            thirdBranch.run();
            return;
        } else if (leftValue.equals(fourthLeftData) && middleValue.equals(fourthMiddleData) && rightValue.equals(fourthRightData)) {
            fourthBranch.run();
            return;
        } else if (leftValue.equals(fifthLeftData) && middleValue.equals(fifthMiddleData) && rightValue.equals(fifthRightData)) {
            fifthBranch.run();
            return;
        }  else if (leftValue.equals(sixthLeftData) && middleValue.equals(sixthMiddleData) && rightValue.equals(sixthRightData)) {
            sixthBranch.run();
            return;
        }

        elseBranch.run();
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
                 V3 sixthRightData,  V4 sixthTopData, Runnable sixthBranch) throws PatternException {
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
        }
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
                 Class<Else> elseClass, Runnable elseBranch) throws PatternException {
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
            return;
        } else if (leftValue.equals(secondLeftData) && bottomValue.equals(secondBottomData) &&
            rightValue.equals(secondRightData) && topValue.equals(secondTopData)) {
            secondBranch.run();
            return;
        } else if (leftValue.equals(thirdLeftData) && bottomValue.equals(thirdBottomData) &&
            rightValue.equals(thirdRightData) && topValue.equals(thirdTopData)) {
            thirdBranch.run();
            return;
        } else if (leftValue.equals(fourthLeftData) && bottomValue.equals(fourthBottomData) &&
            rightValue.equals(fourthRightData) && topValue.equals(fourthTopData)) {
            fourthBranch.run();
            return;
        } else if (leftValue.equals(fifthLeftData) && bottomValue.equals(fifthBottomData) &&
            rightValue.equals(fifthRightData) && topValue.equals(fifthTopData)) {
            fifthBranch.run();
            return;
        } else if (leftValue.equals(sixthLeftData) && bottomValue.equals(sixthBottomData) &&
            rightValue.equals(sixthRightData) && topValue.equals(sixthTopData)) {
            sixthBranch.run();
            return;
        }

        elseBranch.run();
    }
}
