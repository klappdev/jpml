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

import org.kl.jpml.meta.Exclude;
import org.kl.jpml.meta.Extract;
import org.kl.jpml.type.ShortRef;

public final class Parallelepiped extends Figure {
    private short width;
    private short longitude;
    private short height;

    @Exclude private int temp;
    @Exclude private static short staticWidth = 5;
    @Exclude private static short staticLongitude = 5;
    @Exclude private static short staticHeight = 10;

    public Parallelepiped() {
        this.width = 5;
        this.longitude = 5;
        this.height = 10;
    }

    public Parallelepiped(short width, short longitude, short height) {
        this.width = width;
        this.longitude = longitude;
        this.height = height;
    }

    @Override
    public int square() {
        return width * height * longitude;
    }

    @Extract
    public void deconstruct(ShortRef width, ShortRef longitude, ShortRef height) {
        width.set(this.width);
        longitude.set(this.longitude);
        height.set(this.height);
    }

    @Extract
    public static void unapply(ShortRef width, ShortRef longitude, ShortRef height) {
        width.set(staticWidth);
        longitude.set(staticLongitude);
        height.set(staticHeight);
    }

    public short width() {
        return width;
    }

    public short longitude() {
        return longitude;
    }

    public short height() {
        return height;
    }
}
