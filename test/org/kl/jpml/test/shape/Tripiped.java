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
import org.kl.jpml.type.FloatRef;

public final class Tripiped extends Figure {
    private float width;
    private float longitude;
    private float height;

    private static short staticWidth = 5;
    private static short staticLongitude = 5;
    private static short staticHeight = 10;

    public Tripiped() {
        this.width = 5;
        this.longitude = 10;
        this.height = 15;
    }

    public Tripiped(float width, float longitude, float height) {
        this.width = width;
        this.longitude = longitude;
        this.height = height;
    }

    @Override
    public int square() {
        return (int) (width * height * longitude);
    }

    @Extract
    public void deconstruct(FloatRef width, FloatRef longitude) {
        width.set(this.width);
        longitude.set(this.longitude);
    }

    @Extract
    public void deconstruct(FloatRef width, FloatRef longitude, FloatRef height) {
        width.set(this.width);
        longitude.set(this.longitude);
        height.set(this.height);
    }

    @Extract
    public static void unapply(FloatRef width, FloatRef longitude, FloatRef height) {
        width.set(staticWidth);
        longitude.set(staticLongitude);
        height.set(staticHeight);
    }

    public float width() {
        return width;
    }

    public float longitude() {
        return longitude;
    }

    public float height() {
        return height;
    }
}
