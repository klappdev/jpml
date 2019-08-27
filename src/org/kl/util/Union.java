package org.kl.util;

import org.kl.error.PatternException;

public abstract class Union {
    private Union() {}

    public abstract <T> void set(T value);
    public abstract <T> T get(Class<T> clazz);

    public abstract <T> boolean isActive(Class<T> clazz);
    public abstract <T> Class<T> getActive();

    public static <T1, T2> Union2<T1, T2> of(Class<T1> firstClass, Class<T2> secondClass) {
        return new Union2<>(firstClass, secondClass);
    }

    public static <T1, T2, T3> Union3<T1, T2, T3> of(Class<T1> firstClass, Class<T2> secondClass, Class<T3> thirdClass) {
        return new Union3<>(firstClass, secondClass, thirdClass);
    }

    private static class Union2<T1, T2> extends Union {
        private final Class<T1> firstClass;
        private final Class<T2> secondClass;
        private Object value;

        private Union2(Class<T1> firstClass, Class<T2> secondClass) {
            this.firstClass = firstClass;
            this.secondClass = secondClass;
        }

        @Override
        public <T> void set(T value) {
            if (value == null) {
                throw new PatternException("Union value must not to be null");
            }

            if (value.getClass() == firstClass || value.getClass() == secondClass) {
                this.value = value;
            } else {
                throw new PatternException("Incorrect type: " + value.getClass().getName() + "\n" +
                                           "Union two types: [" + firstClass.getName()  + ", "
                                                                + secondClass.getName() + "]");
            }
        }

        @Override
        public <T> T get(Class<T> clazz) {
            if (clazz == firstClass || clazz == secondClass) {
                return (T) value;
            } else {
                throw new PatternException("Incorrect type: " + value.getClass().getName() + "\n" +
                                           "Union two types: [" + firstClass.getName()  + ", "
                                                                + secondClass.getName() + "]");
            }
        }

        @Override
        public <T> boolean isActive(Class<T> clazz) {
            return value.getClass() == clazz;
        }

        @Override
        public <T> Class<T> getActive() {
            return (Class<T>) value.getClass();
        }
    }

    private static class Union3<T1, T2, T3> extends Union {
        private final Class<T1> firstClass;
        private final Class<T2> secondClass;
        private final Class<T3> thirdClass;
        private Object value;

        private Union3(Class<T1> firstClass, Class<T2> secondClass, Class<T3> thirdClass) {
            this.firstClass = firstClass;
            this.secondClass = secondClass;
            this.thirdClass = thirdClass;
        }

        @Override
        public <T> void set(T value) {
            if (value == null) {
                throw new PatternException("Union value must not to be null");
            }

            if (value.getClass() == firstClass || value.getClass() == secondClass || value.getClass() == thirdClass) {
                this.value = value;
            } else {
                throw new PatternException("Incorrect type: " + value.getClass().getName() + "\n" +
                                           "Union three types: [" + firstClass.getName()  + ", "
                                                                  + secondClass.getName() + ", "
                                                                  + thirdClass.getName()  + "]");
            }
        }

        @Override
        public <T> T get(Class<T> clazz) {
            if (clazz == firstClass || clazz == secondClass || value.getClass() == thirdClass) {
                return (T) value;
            } else {
                throw new PatternException("Incorrect type: " + value.getClass().getName() + "\n" +
                                           "Union three types: [" + firstClass.getName()  + ", "
                                                                  + secondClass.getName() + ", "
                                                                  + thirdClass.getName()  + "]");
            }
        }

        @Override
        public <T> boolean isActive(Class<T> clazz) {
            return value.getClass() == clazz;
        }

        @Override
        public <T> Class<T> getActive() {
            return (Class<T>) value.getClass();
        }
    }
}
