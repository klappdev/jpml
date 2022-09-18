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
package org.kl.jpml.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;
import static org.kl.jpml.pattern.SequencePattern.*;

public class SequencePatternTest {
    private static List<Integer> list;
    private static Map<String, Integer> map;

    @BeforeAll
    public static void init() {
        list = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
        }};

        map = new HashMap<String, Integer>() {{
            put("I", 1);
            put("II", 2);
            put("III", 3);
            put("IV", 4);
            put("V", 5);
        }};
    }

    @AfterAll
    public static void destroy() {
        list.clear();
        map.clear();
    }

    @Test
    public void matchStatementTest() {
        /* 1 */
        match(list,
                empty(), () -> out.println("empty list")
        );

        match(map,
                emptyMap(), () -> out.println("empty map")
        );

        System.out.print("\n");

        /* 2 */
        match(list,
                head(), h -> out.println("head list: " + h)
        );

        match(map,
                headMap(), h -> out.println("head map: " + h.getKey() + " - " + h.getValue())
        );

        match(map,
                headMap(), (k, v) -> out.println("head map: " + k + " - " + v)
        );

        System.out.print("\n");

        /* 3 */
        match(list,
                middle(), m -> out.println("middle list: " + m)
        );

        match(map,
                middleMap(), h -> out.println("middle map: " + h.getKey() + " - " + h.getValue())
        );

        match(map,
                middleMap(), (k, v) -> out.println("middle map: " + k + " - " + v)
        );

        System.out.print("\n");

        /* 4 */
        match(list,
                tail(), t -> out.println("tail list: " + t)
        );

        match(map,
                tailMap(), h -> out.println("tail map: " + h.getKey() + " - " + h.getValue())
        );

        match(map,
                tailMap(), (k, v) -> out.println("tail map: " + k + " - " + v)
        );

        System.out.print("\n");

        /* 5 */
        match(list,
                at(1), i -> out.println("at list: " + i)
        );

        match(map,
                atMap(2), h -> out.println("at map: " + h.getKey() + " - " + h.getValue())
        );

        match(map,
                atMap(2), (k, v) -> out.println("at map: " + k + " - " + v)
        );

        System.out.print("\n");

        /* 6 */
        match(list,
                edges(), (first, last) -> out.println("edges list: " + first + " - " + last)
        );

        match(map,
                edgesMap(), (first, last) -> out.println("edges map [" + first.getKey() + ":" + first.getValue() + "]" + "\n" +
                        "          [" + last.getKey() + ":" + last.getValue() + "]")
        );

        System.out.print("\n");

        /* 7 */
        match(list,
                sub(0, 2), xs -> xs.forEach(x -> System.out.print("sub item list: " + x + "\n"))
        );

        match(map,
                subMap(0, 2), xs -> xs.forEach(x -> System.out.print("sub item map: " + x + "\n"))
        );

        System.out.print("\n");

        /* 8 */
        match(list,
                rest(1), xs -> xs.forEach(x -> System.out.print("rest item list: " + x + "\n"))
        );

        match(map,
                restMap(1), xs -> xs.forEach(x -> System.out.print("rest item map: " + x + "\n"))
        );

        System.out.print("\n");

        /* 9 */
        match(list,
                some(0, 2, 4), xs -> xs.forEach(x -> System.out.print("some item list: " + x + "\n"))
        );

        match(map,
                someMap(0, 2, 4), xs -> xs.forEach(x -> System.out.print("some item map: " + x + "\n"))
        );

        /* 1-2 */
        match(list,
                empty(), () -> out.println("empty list"),
                head(), h -> out.println("head list: " + h)
        );

        match(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(), h -> out.println("head map: " + h.getKey() + " - " + h.getValue())
        );

        match(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(), (k, v) -> out.println("head map: " + k + " - " + v)
        );

        System.out.print("\n");

        /* 1-2-3 */
        match(list,
                empty(), () -> out.println("empty list"),
                head(), h -> out.println("head list: " + h),
                middle(), m -> out.println("middle list: " + m)
        );

        match(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(), h -> out.println("head map: " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map: " + m.getKey() + " - " + m.getValue())
        );

        match(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(), (k, v) -> out.println("head map: " + k + " - " + v),
                middleMap(), (k, v) -> out.println("middle map: " + k + " - " + v)
        );

        System.out.println();

        match(list,
                head(), h -> out.println("head list: " + h),
                middle(), m -> out.println("middle list: " + m)
        );

        match(map,
                headMap(), h -> out.println("head map: " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map: " + m.getKey() + " - " + m.getValue())
        );

        match(map,
                headMap(), (k, v) -> out.println("head map: " + k + " - " + v),
                middleMap(), (k, v) -> out.println("middle map: " + k + " - " + v)
        );

        System.out.println("\n");

        /* 1-2-3-4 */
        match(list,
                empty(), () -> out.println("empty list"),
                head(), h -> out.println("head list: " + h),
                middle(), m -> out.println("middle list:" + m),
                tail(), t -> out.println("tail list: " + t)
        );

        match(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(), h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(), t -> out.println("tail map:  " + t.getKey() + " - " + t.getValue())
        );

        match(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(), (k, v) -> out.println("head map:  " + k + " - " + v),
                middleMap(), (k, v) -> out.println("middle map:" + k + " - " + v),
                tailMap(), (k, v) -> out.println("tail map:  " + k + " - " + v)
        );

        System.out.println();

        match(list,
                head(), h -> out.println("head list: " + h),
                middle(), m -> out.println("middle list:" + m),
                tail(), t -> out.println("tail list: " + t)
        );

        match(map,
                headMap(), h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(), t -> out.println("tail map:  " + t.getKey() + " - " + t.getValue())
        );

        match(map,
                headMap(), (k, v) -> out.println("head map:  " + k + " - " + v),
                middleMap(), (k, v) -> out.println("middle map:" + k + " - " + v),
                tailMap(), (k, v) -> out.println("tail map:  " + k + " - " + v)
        );

        System.out.println("\n");

        /* 1-2-3-4-5 */
        match(list,
                empty(), () -> out.println("empty list"),
                head(), h -> out.println("head list:  " + h),
                middle(), m -> out.println("middle list:" + m),
                tail(), t -> out.println("tail list:  " + t),
                at(1), i -> out.println("at list:    " + i)
        );

        match(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(), h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(), t -> out.println("tail map:  " + t.getKey() + " - " + t.getValue()),
                atMap(2), h -> out.println("at map:    " + h.getKey() + " - " + h.getValue())
        );

        match(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(), (k, v) -> out.println("head map:  " + k + " - " + v),
                middleMap(), (k, v) -> out.println("middle map:" + k + " - " + v),
                tailMap(), (k, v) -> out.println("tail map:  " + k + " - " + v),
                atMap(2), (k, v) -> out.println("at map:    " + k + " - " + v)
        );

        System.out.println();

        match(list,
                head(), h -> out.println("head list:  " + h),
                middle(), m -> out.println("middle list:" + m),
                tail(), t -> out.println("tail list:  " + t),
                at(1), i -> out.println("at list:    " + i)
        );

        match(map,
                headMap(), h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(), t -> out.println("tail map:  " + t.getKey() + " - " + t.getValue()),
                atMap(2), h -> out.println("at map:    " + h.getKey() + " - " + h.getValue())
        );

        match(map,
                headMap(), (k, v) -> out.println("head map:  " + k + " - " + v),
                middleMap(), (k, v) -> out.println("middle map:" + k + " - " + v),
                tailMap(), (k, v) -> out.println("tail map:  " + k + " - " + v),
                atMap(2), (k, v) -> out.println("at map:    " + k + " - " + v)
        );

        System.out.println("\n");

        /* 1-2-3-4-5 */
        match(list,
                empty(), () -> out.println("empty list"),
                head(), h -> out.println("head list:  " + h),
                middle(), m -> out.println("middle list:" + m),
                tail(), t -> out.println("tail list:  " + t),
                at(1), i -> out.println("at list:    " + i),
                edges(), (first, last) -> out.println("edges list: " + first + " - " + last)
        );

        match(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(), h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(), t -> out.println("tail map:  " + t.getKey() + " - " + t.getValue()),
                atMap(2), h -> out.println("at map:    " + h.getKey() + " - " + h.getValue()),
                edgesMap(), (first, last) -> out.println("edges map [" + first.getKey() + ":" + first.getValue() + "] "
                        + "[" + last.getKey() + ":" + last.getValue() + "]")
        );

        match(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(), (k, v) -> out.println("head map:  " + k + " - " + v),
                middleMap(), (k, v) -> out.println("middle map:" + k + " - " + v),
                tailMap(), (k, v) -> out.println("tail map:  " + k + " - " + v),
                atMap(2), (k, v) -> out.println("at map:    " + k + " - " + v),
                edgesMap(), (first, last) -> out.println("edges map [" + first.getKey() + ":" + first.getValue() + "] "
                        + "[" + last.getKey() + ":" + last.getValue() + "]")
        );

        System.out.println();

        match(list,
                head(), h -> out.println("head list:  " + h),
                middle(), m -> out.println("middle list:" + m),
                tail(), t -> out.println("tail list:  " + t),
                at(1), i -> out.println("at list:    " + i),
                edges(), (first, last) -> out.println("edges list: " + first + " - " + last)
        );

        match(map,
                headMap(), h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(), t -> out.println("tail map:  " + t.getKey() + " - " + t.getValue()),
                atMap(2), h -> out.println("at map:    " + h.getKey() + " - " + h.getValue()),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        match(map,
                headMap(), (k, v) -> out.println("head map:  " + k + " - " + v),
                middleMap(), (k, v) -> out.println("middle map:" + k + " - " + v),
                tailMap(), (k, v) -> out.println("tail map:  " + k + " - " + v),
                atMap(2), (k, v) -> out.println("at map:    " + k + " - " + v),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.println();

        match(list,
                empty(), () -> out.println("empty list"),
                edges(), (first, last) -> out.println("edges list: " + first + " - " + last)
        );

        match(map,
                emptyMap(), () -> out.println("empty map"),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.println();

        match(list,
                empty(), () -> out.println("empty list"),
                head(), h -> out.println("head list:  " + h),
                edges(), (first, last) -> out.println("edges list: " + first + " - " + last)
        );

        match(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(), h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        match(map,
                headMap(), (k, v) -> out.println("head map:  " + k + " - " + v),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.println();

        match(list,
                head(), h -> out.println("head list:  " + h),
                edges(), (first, last) -> out.println("edges list: " + first + " - " + last)
        );

        match(map,
                headMap(), h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        match(map,
                headMap(), (k, v) -> out.println("head map:  " + k + " - " + v),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.println();

        match(list,
                empty(), () -> out.println("empty list"),
                head(), h -> out.println("head list:  " + h),
                middle(), m -> out.println("middle list:" + m),
                edges(), (first, last) -> out.println("edges list: " + first + " - " + last)
        );

        match(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(), h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map:" + m.getKey() + " - " + m.getValue()),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        match(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(), (k, v) -> out.println("head map:  " + k + " - " + v),
                middleMap(), (k, v) -> out.println("middle map:" + k + " - " + v),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.println();

        match(list,
                head(), h -> out.println("head list:  " + h),
                middle(), m -> out.println("middle list:" + m),
                edges(), (first, last) -> out.println("edges list: " + first + " - " + last)
        );

        match(map,
                headMap(), h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map:" + m.getKey() + " - " + m.getValue()),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        match(map,
                headMap(), (k, v) -> out.println("head map:  " + k + " - " + v),
                middleMap(), (k, v) -> out.println("middle map:" + k + " - " + v),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.println();

        match(list,
                empty(), () -> out.println("empty list"),
                head(), h -> out.println("head list:  " + h),
                middle(), m -> out.println("middle list:" + m),
                tail(), t -> out.println("tail list:  " + t),
                edges(), (first, last) -> out.println("edges list: " + first + " - " + last)
        );

        match(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(), h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(), t -> out.println("tail map:  " + t.getKey() + " - " + t.getValue()),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        match(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(), (k, v) -> out.println("head map:  " + k + " - " + v),
                middleMap(), (k, v) -> out.println("middle map:" + k + " - " + v),
                tailMap(), (k, v) -> out.println("tail map:  " + k + " - " + v),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.println();

        match(list,
                head(), h -> out.println("head list:  " + h),
                middle(), m -> out.println("middle list:" + m),
                tail(), t -> out.println("tail list:  " + t),
                edges(), (first, last) -> out.println("edges list: " + first + " - " + last)
        );

        match(map,
                headMap(), h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(), t -> out.println("tail map:  " + t.getKey() + " - " + t.getValue()),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        match(map,
                headMap(), (k, v) -> out.println("head map:  " + k + " - " + v),
                middleMap(), (k, v) -> out.println("middle map:" + k + " - " + v),
                tailMap(), (k, v) -> out.println("tail map:  " + k + " - " + v),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.println();
    }

    @Test
    public void matchAsStatementTest() {
        /* 1 */
        match(list).as(
                empty(), () -> out.println("as empty list")
        );

        match(map).as(
                emptyMap(), () -> out.println("as empty map")
        );

        System.out.print("\n");

        /* 2 */
        match(list).as(
                head(), h -> out.println("as head list: " + h)
        );

        match(map).as(
                headMap(), h -> out.println("as head map: " + h.getKey() + " - " + h.getValue())
        );

        match(map).as(
                headMap(), (k, v) -> out.println("as head map: " + k + " - " + v)
        );

        System.out.print("\n");

        /* 3 */
        match(list).as(
                middle(), m -> out.println("as middle list: " + m)
        );

        match(map).as(
                middleMap(), h -> out.println("as middle map: " + h.getKey() + " - " + h.getValue())
        );

        match(map).as(
                middleMap(), (k, v) -> out.println("as middle map: " + k + " - " + v)
        );

        System.out.print("\n");

        /* 4 */
        match(list).as(
                tail(), t -> out.println("as tail list: " + t)
        );

        match(map).as(
                tailMap(), h -> out.println("as tail map: " + h.getKey() + " - " + h.getValue())
        );

        match(map).as(
                tailMap(), (k, v) -> out.println("as tail map: " + k + " - " + v)
        );

        System.out.print("\n");

        /* 5 */
        match(list).as(
                at(1), i -> out.println("as at list: " + i)
        );

        match(map).as(
                atMap(2), h -> out.println("as at map: " + h.getKey() + " - " + h.getValue())
        );

        match(map).as(
                atMap(2), (k, v) -> out.println("as at map: " + k + " - " + v)
        );

        System.out.print("\n");

        /* 6 */
        match(list).as(
                edges(), (first, last) -> out.println("as edges list: " + first + " - " + last)
        );

        match(map).as(
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "]" + "\n" +
                        "             [" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.print("\n");

        /* 7 */
        match(list).as(
                sub(0, 2), xs -> xs.forEach(x -> System.out.print("as sub item list: " + x + "\n"))
        );

        match(map).as(
                subMap(0, 2), xs -> xs.forEach(x -> System.out.print("as sub item map: " + x + "\n"))
        );

        System.out.print("\n");

        /* 8 */
        match(list).as(
                rest(1), xs -> xs.forEach(x -> System.out.print("as rest item list: " + x + "\n"))
        );

        match(map).as(
                restMap(1), xs -> xs.forEach(x -> System.out.print("as rest item map: " + x + "\n"))
        );

        System.out.print("\n");

        /* 9 */
        match(list).as(
                some(0, 2, 4), xs -> xs.forEach(x -> System.out.print("as some item list: " + x + "\n"))
        );

        match(map).as(
                someMap(0, 2, 4), xs -> xs.forEach(x -> System.out.print("as some item map: " + x + "\n"))
        );

        /* 1-2 */
        match(list).as(
                empty(), () -> out.println("as empty list"),
                head(), h -> out.println("as head list: " + h)
        );

        match(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(), h -> out.println("as head map: " + h.getKey() + " - " + h.getValue())
        );

        match(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(), (k, v) -> out.println("as head map: " + k + " - " + v)
        );

        System.out.print("\n");

        /* 1-2-3 */
        match(list).as(
                empty(), () -> out.println("as empty list"),
                head(), h -> out.println("as head list: " + h),
                middle(), m -> out.println("as middle list: " + m)
        );

        match(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(), h -> out.println("as head map: " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map: " + m.getKey() + " - " + m.getValue())
        );

        match(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(), (k, v) -> out.println("as head map: " + k + " - " + v),
                middleMap(), (k, v) -> out.println("as middle map: " + k + " - " + v)
        );

        System.out.println();

        match(list).as(
                head(), h -> out.println("as head list: " + h),
                middle(), m -> out.println("as middle list: " + m)
        );

        match(map).as(
                headMap(), h -> out.println("as head map: " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map: " + m.getKey() + " - " + m.getValue())
        );

        match(map).as(
                headMap(), (k, v) -> out.println("as head map: " + k + " - " + v),
                middleMap(), (k, v) -> out.println("as middle map: " + k + " - " + v)
        );

        System.out.println("\n");

        /* 1-2-3-4 */
        match(list).as(
                empty(), () -> out.println("as empty list"),
                head(), h -> out.println("as head list: " + h),
                middle(), m -> out.println("as middle list:" + m),
                tail(), t -> out.println("as tail list: " + t)
        );

        match(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(), h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(), t -> out.println("as tail map:  " + t.getKey() + " - " + t.getValue())
        );

        match(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(), (k, v) -> out.println("as head map:  " + k + " - " + v),
                middleMap(), (k, v) -> out.println("as middle map:" + k + " - " + v),
                tailMap(), (k, v) -> out.println("as tail map:  " + k + " - " + v)
        );

        System.out.println();

        match(list).as(
                head(), h -> out.println("as head list: " + h),
                middle(), m -> out.println("as middle list:" + m),
                tail(), t -> out.println("as tail list: " + t)
        );

        match(map).as(
                headMap(), h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(), t -> out.println("as tail map:  " + t.getKey() + " - " + t.getValue())
        );

        match(map).as(
                headMap(), (k, v) -> out.println("as head map:  " + k + " - " + v),
                middleMap(), (k, v) -> out.println("as middle map:" + k + " - " + v),
                tailMap(), (k, v) -> out.println("as tail map:  " + k + " - " + v)
        );

        System.out.println("\n");

        /* 1-2-3-4-5 */
        match(list).as(
                empty(), () -> out.println("as empty list"),
                head(), h -> out.println("as head list:  " + h),
                middle(), m -> out.println("as middle list:" + m),
                tail(), t -> out.println("as tail list:  " + t),
                at(1), i -> out.println("as at list:    " + i)
        );

        match(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(), h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(), t -> out.println("as tail map:  " + t.getKey() + " - " + t.getValue()),
                atMap(2), h -> out.println("as at map:    " + h.getKey() + " - " + h.getValue())
        );

        match(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(), (k, v) -> out.println("as head map:  " + k + " - " + v),
                middleMap(), (k, v) -> out.println("as middle map:" + k + " - " + v),
                tailMap(), (k, v) -> out.println("as tail map:  " + k + " - " + v),
                atMap(2), (k, v) -> out.println("as at map:    " + k + " - " + v)
        );

        System.out.println();

        match(list).as(
                head(), h -> out.println("as head list:  " + h),
                middle(), m -> out.println("as middle list:" + m),
                tail(), t -> out.println("as tail list:  " + t),
                at(1), i -> out.println("as at list:    " + i)
        );

        match(map).as(
                headMap(), h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(), t -> out.println("as tail map:  " + t.getKey() + " - " + t.getValue()),
                atMap(2), h -> out.println("as at map:    " + h.getKey() + " - " + h.getValue())
        );

        match(map).as(
                headMap(), (k, v) -> out.println("as head map:  " + k + " - " + v),
                middleMap(), (k, v) -> out.println("as middle map:" + k + " - " + v),
                tailMap(), (k, v) -> out.println("as tail map:  " + k + " - " + v),
                atMap(2), (k, v) -> out.println("as at map:    " + k + " - " + v)
        );

        System.out.println("\n");

        /* 1-2-3-4-5 */
        match(list).as(
                empty(), () -> out.println("as empty list"),
                head(), h -> out.println("as head list:  " + h),
                middle(), m -> out.println("as middle list:" + m),
                tail(), t -> out.println("as tail list:  " + t),
                at(1), i -> out.println("as at list:    " + i),
                edges(), (first, last) -> out.println("as edges list: " + first + " - " + last)
        );

        match(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(), h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(), t -> out.println("as tail map:  " + t.getKey() + " - " + t.getValue()),
                atMap(2), h -> out.println("as at map:    " + h.getKey() + " - " + h.getValue()),
                edgesMap(), (first, last) -> out.println("edges map [" + first.getKey() + ":" + first.getValue() + "] "
                        + "[" + last.getKey() + ":" + last.getValue() + "]")
        );

        match(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(), (k, v) -> out.println("as head map:  " + k + " - " + v),
                middleMap(), (k, v) -> out.println("as middle map:" + k + " - " + v),
                tailMap(), (k, v) -> out.println("as tail map:  " + k + " - " + v),
                atMap(2), (k, v) -> out.println("as at map:    " + k + " - " + v),
                edgesMap(), (first, last) -> out.println("edges map [" + first.getKey() + ":" + first.getValue() + "] "
                        + "[" + last.getKey() + ":" + last.getValue() + "]")
        );

        System.out.println();

        match(list).as(
                head(), h -> out.println("as head list:  " + h),
                middle(), m -> out.println("as middle list:" + m),
                tail(), t -> out.println("as tail list:  " + t),
                at(1), i -> out.println("as at list:    " + i),
                edges(), (first, last) -> out.println("as edges list: " + first + " - " + last)
        );

        match(map).as(
                headMap(), h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(), t -> out.println("as tail map:  " + t.getKey() + " - " + t.getValue()),
                atMap(2), h -> out.println("as at map:    " + h.getKey() + " - " + h.getValue()),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        match(map).as(
                headMap(), (k, v) -> out.println("as head map:  " + k + " - " + v),
                middleMap(), (k, v) -> out.println("as middle map:" + k + " - " + v),
                tailMap(), (k, v) -> out.println("as tail map:  " + k + " - " + v),
                atMap(2), (k, v) -> out.println("as at map:    " + k + " - " + v),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.println();

        match(list).as(
                empty(), () -> out.println("as empty list"),
                edges(), (first, last) -> out.println("as edges list: " + first + " - " + last)
        );

        match(map).as(
                emptyMap(), () -> out.println("as empty map"),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.println();

        match(list).as(
                empty(), () -> out.println("as empty list"),
                head(), h -> out.println("as head list:  " + h),
                edges(), (first, last) -> out.println("as edges list: " + first + " - " + last)
        );

        match(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(), h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        match(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(), (k, v) -> out.println("as head map:  " + k + " - " + v),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.println();

        match(list).as(
                head(), h -> out.println("as head list:  " + h),
                edges(), (first, last) -> out.println("as edges list: " + first + " - " + last)
        );

        match(map).as(
                headMap(), h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        match(map).as(
                headMap(), (k, v) -> out.println("as head map:  " + k + " - " + v),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.println();

        match(list).as(
                empty(), () -> out.println("as empty list"),
                head(), h -> out.println("as head list:  " + h),
                middle(), m -> out.println("as middle list:" + m),
                edges(), (first, last) -> out.println("as edges list: " + first + " - " + last)
        );

        match(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(), h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map:" + m.getKey() + " - " + m.getValue()),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        match(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(), (k, v) -> out.println("as head map:  " + k + " - " + v),
                middleMap(), (k, v) -> out.println("as middle map:" + k + " - " + v),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.println();

        match(list).as(
                head(), h -> out.println("as head list:  " + h),
                middle(), m -> out.println("as middle list:" + m),
                edges(), (first, last) -> out.println("as edges list: " + first + " - " + last)
        );

        match(map).as(
                headMap(), h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map:" + m.getKey() + " - " + m.getValue()),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        match(map).as(
                headMap(), (k, v) -> out.println("as head map:  " + k + " - " + v),
                middleMap(), (k, v) -> out.println("as middle map:" + k + " - " + v),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.println();

        match(list).as(
                empty(), () -> out.println("as empty list"),
                head(), h -> out.println("as head list:  " + h),
                middle(), m -> out.println("as middle list:" + m),
                tail(), t -> out.println("as tail list:  " + t),
                edges(), (first, last) -> out.println("as edges list: " + first + " - " + last)
        );

        match(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(), h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(), t -> out.println("as tail map:  " + t.getKey() + " - " + t.getValue()),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        match(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(), (k, v) -> out.println("as head map:  " + k + " - " + v),
                middleMap(), (k, v) -> out.println("as middle map:" + k + " - " + v),
                tailMap(), (k, v) -> out.println("as tail map:  " + k + " - " + v),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.println();

        match(list).as(
                head(), h -> out.println("as head list:  " + h),
                middle(), m -> out.println("as middle list:" + m),
                tail(), t -> out.println("as tail list:  " + t),
                edges(), (first, last) -> out.println("as edges list: " + first + " - " + last)
        );

        match(map).as(
                headMap(), h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(), t -> out.println("as tail map:  " + t.getKey() + " - " + t.getValue()),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        match(map).as(
                headMap(), (k, v) -> out.println("as head map:  " + k + " - " + v),
                middleMap(), (k, v) -> out.println("as middle map:" + k + " - " + v),
                tailMap(), (k, v) -> out.println("as tail map:  " + k + " - " + v),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.println();
    }
}
