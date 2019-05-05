package org.kl.handle;

import org.kl.error.PatternException;
import org.kl.reflect.Reflection;

public class TuplePattern {

    public static <V1, V2> void matches(V1 firstValue, V2 secondValue,
                                        V1 firstData,  V2 secondData, Runnable firstBranch) throws PatternException {
        if (!Reflection.checkTypes(firstValue.getClass(),  firstData.getClass()) ||
            !Reflection.checkTypes(secondValue.getClass(), secondData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (firstValue.equals(firstData) && secondValue.equals(secondData)) {
            firstBranch.run();
        }
    }

    public static <V1, V2> void matches(V1 firstValue, V2 secondValue,
                                        V1 firstData,  V2 secondData, Runnable firstBranch,
                                        V1 thirdData,  V2 fourthData, Runnable secondBranch) throws PatternException {
        if (!Reflection.checkTypes(firstValue.getClass(),  firstData.getClass())  ||
            !Reflection.checkTypes(secondValue.getClass(), secondData.getClass()) ||
            !Reflection.checkTypes(firstValue.getClass(),  thirdData.getClass())  ||
            !Reflection.checkTypes(secondValue.getClass(), fourthData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (firstValue.equals(firstData) && secondValue.equals(secondData)) {
            firstBranch.run();
        } else if (firstValue.equals(thirdData) && secondValue.equals(fourthData)) {
            secondBranch.run();
        }
    }

    public static <V1, V2> void matches(V1 firstValue, V2 secondValue,
                                        V1 firstData,  V2 secondData, Runnable firstBranch,
                                        V1 thirdData,  V2 fourthData, Runnable secondBranch,
                                        V1 fifthData,  V2 sixthData,  Runnable thirdBranch) throws PatternException {
        if (!Reflection.checkTypes(firstValue.getClass(),  firstData.getClass())  ||
            !Reflection.checkTypes(secondValue.getClass(), secondData.getClass()) ||
            !Reflection.checkTypes(firstValue.getClass(),  thirdData.getClass())  ||
            !Reflection.checkTypes(secondValue.getClass(), fourthData.getClass()) ||
            !Reflection.checkTypes(firstValue.getClass(),  fifthData.getClass())  ||
            !Reflection.checkTypes(secondValue.getClass(), sixthData.getClass())) {
            throw new PatternException("Types in brunches must to be equals");
        }

        if (firstValue.equals(firstData) && secondValue.equals(secondData)) {
            firstBranch.run();
        } else if (firstValue.equals(thirdData) && secondValue.equals(fourthData)) {
            secondBranch.run();
        } else if (firstValue.equals(fifthData) && secondValue.equals(sixthData)) {
            thirdBranch.run();
        }
    }
}
