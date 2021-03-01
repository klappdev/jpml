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
import java.util.function.Supplier;

public final class Result<T, E extends Throwable> {
    private final T value;
    private final E error;

    private Result() {
        this.value = null;
        this.error = null;
    }

    private Result(T value) {
        this.value = Objects.requireNonNull(value);
        this.error = null;
    }

    private Result(E error) {
        this.error = Objects.requireNonNull(error);
        this.value = null;
    }

    public static <T, E extends Throwable> Result<T, E> of(T value) {
        return new Result<>(value);
    }

    public static <T, E extends Throwable> Result<T, E> of(E error) {
        return new Result<>(error);
    }

    public static <T, E extends Throwable> Result<T, E> of(Supplier<T> supplier) {
        try {
            return new Result<>(supplier.get());
        } catch (Throwable e) {
            return new Result<>((E) e);
        }
    }

    public boolean isValue() {
        return value != null;
    }

    public boolean isError() {
        return error != null;
    }

    public T value() {
        return value;
    }

    public E error() {
        return error;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Result<?, ?> result = (Result<?, ?>) other;

        return Objects.equals(value, result.value) &&
               Objects.equals(error, result.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, error);
    }

    @Override
    public String toString() {
        return value != null
                ? "Value: " + value
                : "Error: " + error;
    }
}
