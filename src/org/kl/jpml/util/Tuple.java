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
package org.kl.jpml.util;

import java.util.Objects;

public abstract class Tuple {
    private Tuple() {}

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
        private final T1 first;
        private final T2 second;

        public Tuple2(T1 first, T2 last) {
            this.first = first;
            this.second  = last;
        }

        public T1 first() {
            return first;
        }

        public T2 second() {
            return second;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }

            if (other == null || getClass() != other.getClass()) {
                return false;
            }

            final Tuple2<?, ?> tmp = (Tuple2<?, ?>) other;

            return Objects.equals(first, tmp.first) &&
                   Objects.equals(second, tmp.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }

        @Override
        public String toString() {
            return "Tuple2 [" + first + ", " + second + "]";
        }
    }

    public static final class Tuple3<T1, T2, T3> extends Tuple{
        private final T1 first;
        private final T2 second;
        private final T3 third;

        public Tuple3(T1 first, T2 second, T3 third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        public T1 first() {
            return first;
        }

        public T2 second() {
            return second;
        }

        public T3 third() {
            return third;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }

            if (other == null || getClass() != other.getClass()) {
                return false;
            }

            final Tuple3<?, ?, ?> tmp = (Tuple3<?, ?, ?>) other;

            return Objects.equals(first, tmp.first) &&
                   Objects.equals(second, tmp.second) &&
                   Objects.equals(third, tmp.third);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second, third);
        }

        @Override
        public String toString() {
            return "Tuple3 [" + first + ", " + second + ", " + third + "]";
        }
    }

    public static final class Tuple4<T1, T2, T3, T4> extends Tuple {
        private final T1 first;
        private final T2 second;
        private final T3 third;
        private final T4 fourth;

        public Tuple4(T1 first, T2 second, T3 third, T4 fourth) {
            this.first = first;
            this.second = second;
            this.third = third;
            this.fourth = fourth;
        }

        public T1 first() {
            return first;
        }

        public T2 second() {
            return second;
        }

        public T3 third() {
            return third;
        }

        public T4 fourth() {
            return fourth;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }

            if (other == null || getClass() != other.getClass()) {
                return false;
            }

            final Tuple4<?, ?, ?, ?> tmp = (Tuple4<?, ?, ?, ?>) other;

            return Objects.equals(first, tmp.first) &&
                   Objects.equals(second, tmp.second) &&
                   Objects.equals(third, tmp.third) &&
                   Objects.equals(fourth, tmp.fourth);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second, third, fourth);
        }

        public String toString() {
            return "Tuple4 [" + first + ", " + second + ", "
                              + third + ", " + fourth + "]";
        }
    }

    public static final class Tuple5<T1, T2, T3, T4, T5> extends Tuple {
        private final T1 first;
        private final T2 second;
        private final T3 third;
        private final T4 fourth;
        private final T5 fifth;

        public Tuple5(T1 first, T2 second, T3 third, T4 fourth, T5 fifth) {
            this.first = first;
            this.second = second;
            this.third = third;
            this.fourth = fourth;
            this.fifth = fifth;
        }

        public T1 first() {
            return first;
        }

        public T2 second() {
            return second;
        }

        public T3 third() {
            return third;
        }

        public T4 fourth() {
            return fourth;
        }

        public T5 fifth() {
            return fifth;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }

            if (other == null || getClass() != other.getClass()) {
                return false;
            }

            final Tuple5<?, ?, ?, ?, ?> tmp = (Tuple5<?, ?, ?, ?, ?>) other;
            return Objects.equals(first, tmp.first) &&
                   Objects.equals(second, tmp.second) &&
                   Objects.equals(third, tmp.third) &&
                   Objects.equals(fourth, tmp.fourth) &&
                   Objects.equals(fifth, tmp.fifth);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second, third, fourth, fifth);
        }

        public String toString() {
            return "Tuple5 [" + first + ", " + second + ", " + third + ", "
                              + fourth + ", " + fifth + "]";
        }
    }

    public static final class Tuple6<T1, T2, T3, T4, T5, T6> extends Tuple {
        private final T1 first;
        private final T2 second;
        private final T3 third;
        private final T4 fourth;
        private final T5 fifth;
        private final T6 sixth;

        public Tuple6(T1 first, T2 second, T3 third, T4 fourth, T5 fifth, T6 sixth) {
            this.first = first;
            this.second = second;
            this.third = third;
            this.fourth = fourth;
            this.fifth = fifth;
            this.sixth = sixth;
        }

        public T1 first() {
            return first;
        }

        public T2 second() {
            return second;
        }

        public T3 third() {
            return third;
        }

        public T4 fourth() {
            return fourth;
        }

        public T5 fifth() {
            return fifth;
        }

        public T6 sixth() {
            return sixth;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }

            if (other == null || getClass() != other.getClass()) {
                return false;
            }

            final Tuple6<?, ?, ?, ?, ?, ?> tmp = (Tuple6<?, ?, ?, ?, ?, ?>) other;

            return Objects.equals(first, tmp.first) &&
                   Objects.equals(second, tmp.second) &&
                   Objects.equals(third, tmp.third) &&
                   Objects.equals(fourth, tmp.fourth) &&
                   Objects.equals(fifth, tmp.fifth) &&
                   Objects.equals(sixth, tmp.sixth);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second, third, fourth, fifth, sixth);
        }

        @Override
        public String toString() {
            return "Tuple6 [" + first + ", " + second + ", " + third + ", "
                              + fourth + ", " + fifth + ", " + sixth + "]";
        }
    }
}
