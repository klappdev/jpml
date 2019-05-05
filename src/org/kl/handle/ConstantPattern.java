package org.kl.handle;

import org.kl.error.PatternException;
import org.kl.reflect.Reflection;

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

    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(), firstData.getClass()) ||
            !Reflection.checkTypes(value.getClass(), secondData.getClass())) {
            throw new PatternException("Type in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            firstBranch.run();
        } else if (value.equals(secondData)) {
            secondBranch.run();
        }
    }

    public static <V> void matches(V value,
                                   V firstData,  Runnable firstBranch,
                                   V secondData, Runnable secondBranch,
                                   V thirdData,  Runnable thirdBranch) throws PatternException {
        if (!Reflection.checkTypes(value.getClass(), firstData.getClass())  ||
            !Reflection.checkTypes(value.getClass(), secondData.getClass()) ||
            !Reflection.checkTypes(value.getClass(), thirdData.getClass())) {
            throw new PatternException("Type in brunches must to be equals");
        }

        if (value.equals(firstData)) {
            firstBranch.run();
        } else if (value.equals(secondData)) {
            secondBranch.run();
        } else if (value.equals(thirdData)) {
            thirdBranch.run();
        }
    }
}
