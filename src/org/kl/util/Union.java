package org.kl.util;

import org.kl.error.PatternException;

public abstract class Union {
    private Union() {}

    public abstract <T> void set(T value);
    public abstract <T> T get(Class<T> clazz);

    public abstract <T> boolean isActive(Class<T> clazz);
    public abstract <T> Class<T> getActive();

    public static <T1, T2>
    Union2<T1, T2> of(Class<T1> firstClass, Class<T2> secondClass) {
        return new Union2<>(firstClass, secondClass);
    }

    public static <T1, T2, T3>
    Union3<T1, T2, T3> of(Class<T1> firstClass, Class<T2> secondClass, Class<T3> thirdClass) {
        return new Union3<>(firstClass, secondClass, thirdClass);
    }

    public static <T1, T2, T3, T4>
    Union4<T1, T2, T3, T4> of(Class<T1> firstClass, Class<T2> secondClass,
                              Class<T3> thirdClass, Class<T4> fourthClass) {
        return new Union4<>(firstClass, secondClass, thirdClass, fourthClass);
    }

    public static <T1, T2, T3, T4, T5>
    Union5<T1, T2, T3, T4, T5> of(Class<T1> firstClass, Class<T2> secondClass, Class<T3> thirdClass,
                                  Class<T4> fourthClass, Class<T5> fifthClass) {
        return new Union5<>(firstClass, secondClass, thirdClass, fourthClass, fifthClass);
    }

    public static <T1, T2, T3, T4, T5, T6>
    Union6<T1, T2, T3, T4, T5, T6> of(Class<T1> firstClass, Class<T2> secondClass, Class<T3> thirdClass,
                                      Class<T4> fourthClass, Class<T5> fifthClass, Class<T6> sixthClass) {
        return new Union6<>(firstClass, secondClass, thirdClass, fourthClass, fifthClass, sixthClass);
    }

    public static final class Union2<T1, T2> extends Union {
        private final Class<T1> firstClass;
        private final Class<T2> secondClass;
        private Object value;

        public Union2(Class<T1> firstClass, Class<T2> secondClass) {
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
        @SuppressWarnings("unchecked")
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
        @SuppressWarnings("unchecked")
        public <T> Class<T> getActive() {
            return (Class<T>) value.getClass();
        }
    }

    public static final class Union3<T1, T2, T3> extends Union {
        private final Class<T1> firstClass;
        private final Class<T2> secondClass;
        private final Class<T3> thirdClass;
        private Object value;

        public Union3(Class<T1> firstClass, Class<T2> secondClass, Class<T3> thirdClass) {
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
        @SuppressWarnings("unchecked")
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
        @SuppressWarnings("unchecked")
        public <T> Class<T> getActive() {
            return (Class<T>) value.getClass();
        }
    }

    public static final class Union4<T1, T2, T3, T4> extends Union {
        private final Class<T1> firstClass;
        private final Class<T2> secondClass;
        private final Class<T3> thirdClass;
        private final Class<T4> fourthClass;
        private Object value;

        public Union4(Class<T1> firstClass, Class<T2> secondClass, Class<T3> thirdClass, Class<T4> fourthClass) {
            this.firstClass = firstClass;
            this.secondClass = secondClass;
            this.thirdClass = thirdClass;
            this.fourthClass = fourthClass;
        }

        @Override
        public <T> void set(T value) {
            if (value == null) {
                throw new PatternException("Union value must not to be null");
            }

            if (value.getClass() == firstClass || value.getClass() == secondClass ||
                value.getClass() == thirdClass || value.getClass() == fourthClass) {
                this.value = value;
            } else {
                throw new PatternException("Incorrect type: " + value.getClass().getName() + "\n" +
                                           "Union fourth types: [" + firstClass.getName()  + ", "
                                                                   + secondClass.getName() + ", "
                                                                   + thirdClass.getName()  + ", "
                                                                   + fourthClass.getName() + "]");
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T get(Class<T> clazz) {
            if (value.getClass() == firstClass || value.getClass() == secondClass ||
                value.getClass() == thirdClass || value.getClass() == fourthClass) {
                return (T) value;
            } else {
                throw new PatternException("Incorrect type: " + value.getClass().getName() + "\n" +
                                           "Union fourth types: [" + firstClass.getName()  + ", "
                                                                   + secondClass.getName() + ", "
                                                                   + thirdClass.getName()  + ", "
                                                                   + fourthClass.getName() + "]");
            }
        }

        @Override
        public <T> boolean isActive(Class<T> clazz) {
            return value.getClass() == clazz;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> Class<T> getActive() {
            return (Class<T>) value.getClass();
        }
    }

    public static final class Union5<T1, T2, T3, T4, T5> extends Union {
        private final Class<T1> firstClass;
        private final Class<T2> secondClass;
        private final Class<T3> thirdClass;
        private final Class<T4> fourthClass;
        private final Class<T5> fifthClass;
        private Object value;

        public Union5(Class<T1> firstClass, Class<T2> secondClass, Class<T3> thirdClass,
                      Class<T4> fourthClass, Class<T5> fifthClass) {
            this.firstClass = firstClass;
            this.secondClass = secondClass;
            this.thirdClass = thirdClass;
            this.fourthClass = fourthClass;
            this.fifthClass = fifthClass;
        }

        @Override
        public <T> void set(T value) {
            if (value == null) {
                throw new PatternException("Union value must not to be null");
            }

            if (value.getClass() == firstClass || value.getClass() == secondClass ||
                value.getClass() == thirdClass || value.getClass() == fourthClass ||
                value.getClass() == fifthClass) {
                this.value = value;
            } else {
                throw new PatternException("Incorrect type: " + value.getClass().getName() + "\n" +
                                           "Union fifth types: [" + firstClass.getName()  + ", "
                                                                  + secondClass.getName() + ", "
                                                                  + thirdClass.getName()  + ", "
                                                                  + fourthClass.getName() + ", "
                                                                  + fifthClass.getName() + "]");
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T get(Class<T> clazz) {
            if (value.getClass() == firstClass || value.getClass() == secondClass ||
                value.getClass() == thirdClass || value.getClass() == fourthClass ||
                value.getClass() == fifthClass) {
                return (T) value;
            } else {
                throw new PatternException("Incorrect type: " + value.getClass().getName() + "\n" +
                                           "Union fifth types: [" + firstClass.getName()  + ", "
                                                                  + secondClass.getName() + ", "
                                                                  + thirdClass.getName()  + ", "
                                                                  + fourthClass.getName() + ", "
                                                                  + fifthClass.getName() + "]");
            }
        }

        @Override
        public <T> boolean isActive(Class<T> clazz) {
            return value.getClass() == clazz;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> Class<T> getActive() {
            return (Class<T>) value.getClass();
        }
    }

    public static final class Union6<T1, T2, T3, T4, T5, T6> extends Union {
        private final Class<T1> firstClass;
        private final Class<T2> secondClass;
        private final Class<T3> thirdClass;
        private final Class<T4> fourthClass;
        private final Class<T5> fifthClass;
        private final Class<T6> sixthClass;
        private Object value;

        public Union6(Class<T1> firstClass, Class<T2> secondClass, Class<T3> thirdClass,
                      Class<T4> fourthClass, Class<T5> fifthClass, Class<T6> sixthClass) {
            this.firstClass = firstClass;
            this.secondClass = secondClass;
            this.thirdClass = thirdClass;
            this.fourthClass = fourthClass;
            this.fifthClass = fifthClass;
            this.sixthClass = sixthClass;
        }

        @Override
        public <T> void set(T value) {
            if (value == null) {
                throw new PatternException("Union value must not to be null");
            }

            if (value.getClass() == firstClass || value.getClass() == secondClass ||
                value.getClass() == thirdClass || value.getClass() == fourthClass ||
                value.getClass() == fifthClass || value.getClass() == sixthClass) {
                this.value = value;
            } else {
                throw new PatternException("Incorrect type: " + value.getClass().getName() + "\n" +
                                           "Union sixth types: [" + firstClass.getName()  + ", "
                                                                  + secondClass.getName() + ", "
                                                                  + thirdClass.getName()  + ", "
                                                                  + fourthClass.getName() + ", "
                                                                  + fifthClass.getName()  + ", "
                                                                  + sixthClass.getName()  + "]");
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T get(Class<T> clazz) {
            if (value.getClass() == firstClass || value.getClass() == secondClass ||
                value.getClass() == thirdClass || value.getClass() == fourthClass ||
                value.getClass() == fifthClass || value.getClass() == sixthClass) {
                return (T) value;
            } else {
                throw new PatternException("Incorrect type: " + value.getClass().getName() + "\n" +
                                           "Union sixth types: [" + firstClass.getName()  + ", "
                                                                  + secondClass.getName() + ", "
                                                                  + thirdClass.getName()  + ", "
                                                                  + fourthClass.getName() + ", "
                                                                  + fifthClass.getName()  + ", "
                                                                  + sixthClass.getName()  + "]");
            }
        }

        @Override
        public <T> boolean isActive(Class<T> clazz) {
            return value.getClass() == clazz;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> Class<T> getActive() {
            return (Class<T>) value.getClass();
        }
    }
}
