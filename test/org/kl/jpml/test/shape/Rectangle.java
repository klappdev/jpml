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
package org.kl.jpml.test.shape;

import org.kl.jpml.meta.Extract;
import org.kl.jpml.type.IntRef;

public final class Rectangle extends Figure {
    private int width;
    private int height;

    private static int staticWidth  = 5;
    private static int staticHeight = 10;

    public Rectangle() {
        this.width  = 5;
        this.height = 10;
    }

    public Rectangle(int width, int height) {
        this.width  = width;
        this.height = height;
    }

    @Override
    public int square() {
        return width * height;
    }

    @Extract
    public void deconstruct(IntRef width, IntRef height) {
        width.set(this.width);
        height.set(this.height);
    }

    @Extract
    public static void unapply(IntRef width, IntRef height) {
        width.set(staticWidth);
        height.set(staticHeight);
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
}

