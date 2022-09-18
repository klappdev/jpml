/*
 * Licensed under the MIT License <http://opensource.org/licenses/MIT>.
 * SPDX-License-Identifier: MIT
 * Copyright (c) 2019 - 2022 https://github.com/klappdev
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
import java.util.function.Supplier;

public abstract class Result {
    protected Result() {}

    public static final class Value<T> extends Result {
        private final T value;

        public Value(T value) {
            this.value = value;
        }

        public T value() {
            return value;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            if (!super.equals(other)) return false;

            final Value tmp = (Value) other;
            return Objects.equals(value, tmp.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), value);
        }

        @Override
        public String toString() {
            return "Result.Value [" + value + "]";
        }
    }

    public static final class Error<E extends Throwable> extends Result {
        private final E error;

        public Error(E error) {
            this.error = error;
        }

        public E error() {
            return error;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            if (!super.equals(other)) return false;

            final Error tmp = (Error) other;
            return Objects.equals(error, tmp.error);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), error);
        }

        @Override
        public String toString() {
            return "Result.Error [" + error + "]";
        }
    }

    public static <T, E extends Throwable> Result of(T value) {
        return new Result.Value<>(value);
    }

    public static <T, E extends Throwable> Result of(E error) {
        return new Result.Error<>(error);
    }

    public static <T, E extends Throwable> Result of(Supplier<T> supplier) {
        try {
            return new Result.Value<>(supplier.get());
        } catch (Throwable e) {
            return new Result.Error<>((E) e);
        }
    }
}
