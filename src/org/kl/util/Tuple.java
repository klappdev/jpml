package org.kl.util;

import org.kl.error.PatternException;

public abstract class Tuple {
    private Tuple() {}

    public abstract <T> void set(int index, T value);
    public abstract <T> T get(int index);

    public static <T1, T2>
    Tuple2<T1, T2> of(T1 firstValue, T2 secondValue) {
        return new Tuple2<>(firstValue, secondValue);
    }

    public static <T1, T2, T3>
    Tuple3<T1, T2, T3> of(T1 firstValue, T2 secondValue, T3 thirdValue) {
        return new Tuple3<>(firstValue, secondValue, thirdValue);
    }

    public static <T1, T2, T3, T4>
    Tuple4<T1, T2, T3, T4> of(T1 firstValue, T2 secondValue, T3 thirdValue, T4 fourthValue) {
        return new Tuple4<>(firstValue, secondValue, thirdValue, fourthValue);
    }

    public static <T1, T2, T3, T4, T5>
    Tuple5<T1, T2, T3, T4, T5> of(T1 firstValue, T2 secondValue, T3 thirdValue,
                                  T4 fourthValue, T5 fifthValue) {
        return new Tuple5<>(firstValue, secondValue, thirdValue, fourthValue, fifthValue);
    }

    public static <T1, T2, T3, T4, T5, T6>
    Tuple6<T1, T2, T3, T4, T5, T6> of(T1 firstValue, T2 secondValue, T3 thirdValue,
                                      T4 fourthValue, T5 fifthValue, T6 sixthValue) {
        return new Tuple6<>(firstValue, secondValue, thirdValue, fourthValue, fifthValue, sixthValue);
    }

    public static final class Tuple2<T1, T2> extends Tuple {
        private T1 first;
        private T2 second;

        public Tuple2(T1 first, T2 last) {
            this.first = first;
            this.second  = last;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> void set(int index, T value) {
            switch (index) {
                case 0: first  = (T1) value; break;
                case 1: second = (T2) value; break;
                default: throw new PatternException("Incorrect type: " + value.getClass().getName() + "\n" +
                                                    "Tuple two types: [" + first.getClass().getName()  + ", "
                                                                         + second.getClass().getName() + "]");
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T get(int index) {
            switch (index) {
                case 0: return (T) first;
                case 1: return (T) second;
                default: throw new PatternException("Tuple two types: [" + first.getClass().getName()  + ", "
                                                                         + second.getClass().getName() + "]");
            }
        }
    }

    public static final class Tuple3<T1, T2, T3> extends Tuple{
        private T1 first;
        private T2 second;
        private T3 third;

        public Tuple3(T1 first, T2 second, T3 third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> void set(int index, T value) {
            switch (index) {
                case 0: first  = (T1) value; break;
                case 1: second = (T2) value; break;
                case 2: third  = (T3) value; break;
                default: throw new PatternException("Incorrect type: " + value.getClass().getName() + "\n" +
                                                    "Tuple three types: [" + first.getClass().getName()  + ", "
                                                                           + second.getClass().getName() + ", "
                                                                           + third.getClass().getName()  + "]");
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T get(int index) {
            switch (index) {
                case 0: return (T) first;
                case 1: return (T) second;
                case 2: return (T) third;
                default: throw new PatternException("Tuple three types: [" + first.getClass().getName()  + ", "
                                                                           + second.getClass().getName() + ", "
                                                                           + third.getClass().getName()  + "]");
            }
        }
    }

    public static final class Tuple4<T1, T2, T3, T4> extends Tuple {
        private T1 first;
        private T2 second;
        private T3 third;
        private T4 fourth;

        public Tuple4(T1 first, T2 second, T3 third, T4 fourth) {
            this.first = first;
            this.second = second;
            this.third = third;
            this.fourth = fourth;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> void set(int index, T value) {
            switch (index) {
                case 0: first  = (T1) value; break;
                case 1: second = (T2) value; break;
                case 2: third  = (T3) value; break;
                case 3: fourth = (T4) value; break;
                default: throw new PatternException("Incorrect type: " + value.getClass().getName() + "\n" +
                                                    "Tuple fourth types: [" + first.getClass().getName()  + ", "
                                                                            + second.getClass().getName() + ", "
                                                                            + third.getClass().getName()  + ", "
                                                                            + fourth.getClass().getName() + "]");
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T get(int index) {
            switch (index) {
                case 0: return (T) first;
                case 1: return (T) second;
                case 2: return (T) third;
                case 3: return (T) fourth;
                default: throw new PatternException("Tuple fourth types: [" + first.getClass().getName()  + ", "
                                                                            + second.getClass().getName() + ", "
                                                                            + third.getClass().getName()  + ", "
                                                                            + fourth.getClass().getName() + "]");
            }
        }
    }

    public static final class Tuple5<T1, T2, T3, T4, T5> extends Tuple {
        private T1 first;
        private T2 second;
        private T3 third;
        private T4 fourth;
        private T5 fifth;

        public Tuple5(T1 first, T2 second, T3 third, T4 fourth, T5 fifth) {
            this.first = first;
            this.second = second;
            this.third = third;
            this.fourth = fourth;
            this.fifth = fifth;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> void set(int index, T value) {
            switch (index) {
                case 0: first  = (T1) value; break;
                case 1: second = (T2) value; break;
                case 2: third  = (T3) value; break;
                case 3: fourth = (T4) value; break;
                case 4: fifth  = (T5) value; break;
                default: throw new PatternException("Incorrect type: " + value.getClass().getName() + "\n" +
                                                    "Tuple fifth types: [" + first.getClass().getName()  + ", "
                                                                           + second.getClass().getName() + ", "
                                                                           + third.getClass().getName()  + ", "
                                                                           + fourth.getClass().getName() + ", "
                                                                           + fifth.getClass().getName()  + "]");
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T get(int index) {
            switch (index) {
                case 0: return (T) first;
                case 1: return (T) second;
                case 2: return (T) third;
                case 3: return (T) fourth;
                case 4: return (T) fifth;
                default: throw new PatternException("Tuple fifth types: [" + first.getClass().getName()  + ", "
                                                                           + second.getClass().getName() + ", "
                                                                           + third.getClass().getName()  + ", "
                                                                           + fourth.getClass().getName() + ", "
                                                                           + fifth.getClass().getName()  + "]");
            }
        }
    }

    public static final class Tuple6<T1, T2, T3, T4, T5, T6> extends Tuple {
        private T1 first;
        private T2 second;
        private T3 third;
        private T4 fourth;
        private T5 fifth;
        private T6 sixth;

        public Tuple6(T1 first, T2 second, T3 third, T4 fourth, T5 fifth, T6 sixth) {
            this.first = first;
            this.second = second;
            this.third = third;
            this.fourth = fourth;
            this.fifth = fifth;
            this.sixth = sixth;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> void set(int index, T value) {
            switch (index) {
                case 0: first  = (T1) value; break;
                case 1: second = (T2) value; break;
                case 2: third  = (T3) value; break;
                case 3: fourth = (T4) value; break;
                case 4: fifth  = (T5) value; break;
                case 5: sixth  = (T6) value; break;
                default: throw new PatternException("Incorrect type: " + value.getClass().getName() + "\n" +
                                                    "Tuple sixth types: [" + first.getClass().getName()  + ", "
                                                                           + second.getClass().getName() + ", "
                                                                           + third.getClass().getName()  + ", "
                                                                           + fourth.getClass().getName() + ", "
                                                                           + fifth.getClass().getName()  + ", "
                                                                           + sixth.getClass().getName()  + "]");
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T get(int index) {
            switch (index) {
                case 0: return (T) first;
                case 1: return (T) second;
                case 2: return (T) third;
                case 3: return (T) fourth;
                case 4: return (T) fifth;
                case 5: return (T) sixth;
                default: throw new PatternException("Tuple sixth types: [" + first.getClass().getName()  + ", "
                                                                           + second.getClass().getName() + ", "
                                                                           + third.getClass().getName()  + ", "
                                                                           + fourth.getClass().getName() + ", "
                                                                           + fifth.getClass().getName()  + ", "
                                                                           + sixth.getClass().getName()  + "]");
            }
        }
    }
}
