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
package org.kl.jpml.reflect;

import org.kl.jpml.error.PatternException;
import org.kl.jpml.meta.Sealed;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

final class SealedAttribute {
    private Class<?>[] subClasses;

    /*package-private*/ SealedAttribute(Class<?> clazz) {
        Class<?> sealedClass = clazz.getSuperclass();

        if (!sealedClass.isAnnotationPresent(Sealed.class)) {
            throw new PatternException("Checked class must to be mark as sealed");
        }

        if (!Modifier.isAbstract(sealedClass.getModifiers())) {
            throw new PatternException("Checked class must to be abstract");
        }

        try {
            final Constructor<?> constructor = sealedClass.getDeclaredConstructor();

            if (!Modifier.isPrivate(constructor.getModifiers())) {
                throw new PatternException("Default constructor must to be private");
            }

            this.subClasses = sealedClass.getClasses();

            if (subClasses.length == 0) {
                throw new PatternException("Checked class must to has one or more visible subClasses");
            }

            for (Class<?> subclass : subClasses) {
                if (!Modifier.isStatic(subclass.getModifiers())) {
                    throw new PatternException("Subclass must to be static");
                }

                if (subclass.getSuperclass() != sealedClass) {
                    throw new PatternException("Subclass must to inherit from checked class");
                }
            }
        } catch (NoSuchMethodException e) {
            throw new PatternException("Checked class must to has default constructor " + e.getMessage());
        }
    }

    /*package-private*/ Class<?>[] getSubClasses() {
        return subClasses;
    }
}
