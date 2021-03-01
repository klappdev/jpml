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
            add(1); add(2); add(3); add(4); add(5);
        }};

        map = new HashMap<String, Integer>() {{
            put("I", 1); put("II", 2); put("III", 3); put("IV", 4); put("V", 5);
        }};
    }

    @AfterAll
    public static void destroy() {
        list.clear();
        map.clear();
    }

    @Test
    public void matchesStatementTest() {
        /* 1 */
        matches(list,
                empty(), () -> out.println("empty list")
        );

        matches(map,
                emptyMap(), () -> out.println("empty map")
        );

        System.out.print("\n");

        /* 2 */
        matches(list,
                head(), h -> out.println("head list: " + h)
        );

        matches(map,
                headMap(), h -> out.println("head map: " + h.getKey() + " - " + h.getValue())
        );

        matches(map,
                headMap(), (k, v) -> out.println("head map: " + k + " - " + v)
        );

        System.out.print("\n");

        /* 3 */
        matches(list,
                middle(), m -> out.println("middle list: " + m)
        );

        matches(map,
                middleMap(), h -> out.println("middle map: " + h.getKey() + " - " + h.getValue())
        );

        matches(map,
                middleMap(), (k, v) -> out.println("middle map: " + k + " - " + v)
        );

        System.out.print("\n");

        /* 4 */
        matches(list,
                tail(), t -> out.println("tail list: " + t)
        );

        matches(map,
                tailMap(), h -> out.println("tail map: " + h.getKey() + " - " + h.getValue())
        );

        matches(map,
                tailMap(), (k, v) -> out.println("tail map: " + k + " - " + v)
        );

        System.out.print("\n");

        /* 5 */
        matches(list,
                at(1), i -> out.println("at list: " + i)
        );

        matches(map,
                atMap(2), h -> out.println("at map: " + h.getKey() + " - " + h.getValue())
        );

        matches(map,
                atMap(2), (k, v) -> out.println("at map: " + k + " - " + v)
        );

        System.out.print("\n");

        /* 6 */
        matches(list,
                edges(), (first, last) -> out.println("edges list: " + first + " - " + last)
        );

        matches(map,
                edgesMap(), (first, last) -> out.println("edges map [" + first.getKey() + ":" + first.getValue() + "]" + "\n" +
                                                                "          [" + last.getKey()  + ":" + last.getValue()  + "]")
        );

        System.out.print("\n");

        /* 7 */
        matches(list,
                sub(0, 2), xs -> xs.forEach(x -> System.out.print("sub item list: " + x + "\n"))
        );

        matches(map,
                subMap(0, 2), xs -> xs.forEach(x -> System.out.print("sub item map: " + x + "\n"))
        );

        System.out.print("\n");

        /* 8 */
        matches(list,
                rest(1), xs -> xs.forEach(x -> System.out.print("rest item list: " + x + "\n"))
        );

        matches(map,
                restMap(1), xs -> xs.forEach(x -> System.out.print("rest item map: " + x + "\n"))
        );

        System.out.print("\n");

        /* 9 */
        matches(list,
                some(0, 2, 4), xs -> xs.forEach(x -> System.out.print("some item list: " + x + "\n"))
        );

        matches(map,
                someMap(0, 2, 4), xs -> xs.forEach(x -> System.out.print("some item map: " + x + "\n"))
        );

        /* 1-2 */
        matches(list,
                empty(), () -> out.println("empty list"),
                head(),   h -> out.println("head list: " + h)
        );

        matches(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(),   h -> out.println("head map: " + h.getKey() + " - " + h.getValue())
        );

        matches(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(),  (k, v) -> out.println("head map: " + k + " - " + v)
        );

        System.out.print("\n");

        /* 1-2-3 */
        matches(list,
                empty(), () -> out.println("empty list"),
                head(),   h -> out.println("head list: " + h),
                middle(), m -> out.println("middle list: " + m)
        );

        matches(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(),   h -> out.println("head map: " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map: " + m.getKey() + " - " + m.getValue())
        );

        matches(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(),  (k, v) -> out.println("head map: " + k + " - " + v),
                middleMap(),(k, v) -> out.println("middle map: " + k + " - " + v)
        );

        System.out.println();

        matches(list,
                head(),   h -> out.println("head list: " + h),
                middle(), m -> out.println("middle list: " + m)
        );

        matches(map,
                headMap(),   h -> out.println("head map: " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map: " + m.getKey() + " - " + m.getValue())
        );

        matches(map,
                headMap(),  (k, v) -> out.println("head map: " + k + " - " + v),
                middleMap(),(k, v) -> out.println("middle map: " + k + " - " + v)
        );

        System.out.println("\n");

        /* 1-2-3-4 */
        matches(list,
                empty(), () -> out.println("empty list"),
                head(),   h -> out.println("head list: "  + h),
                middle(), m -> out.println("middle list:" + m),
                tail(),   t -> out.println("tail list: "  + t)
        );

        matches(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(),   h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(),   t -> out.println("tail map:  " + t.getKey() + " - " + t.getValue())
        );

        matches(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(),  (k, v) -> out.println("head map:  " + k + " - " + v),
                middleMap(),(k, v) -> out.println("middle map:" + k + " - " + v),
                tailMap(),  (k, v) -> out.println("tail map:  " + k + " - " + v)
        );

        System.out.println();

        matches(list,
                head(),   h -> out.println("head list: "  + h),
                middle(), m -> out.println("middle list:" + m),
                tail(),   t -> out.println("tail list: "  + t)
        );

        matches(map,
                headMap(),   h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(),   t -> out.println("tail map:  " + t.getKey() + " - " + t.getValue())
        );

        matches(map,
                headMap(),  (k, v) -> out.println("head map:  " + k + " - " + v),
                middleMap(),(k, v) -> out.println("middle map:" + k + " - " + v),
                tailMap(),  (k, v) -> out.println("tail map:  " + k + " - " + v)
        );

        System.out.println("\n");

        /* 1-2-3-4-5 */
        matches(list,
                empty(), () -> out.println("empty list"),
                head(),   h -> out.println("head list:  " + h),
                middle(), m -> out.println("middle list:" + m),
                tail(),   t -> out.println("tail list:  " + t),
                at(1),    i -> out.println("at list:    " + i)
        );

        matches(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(),   h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(),   t -> out.println("tail map:  " + t.getKey() + " - " + t.getValue()),
                atMap(2),    h -> out.println("at map:    " + h.getKey() + " - " + h.getValue())
        );

        matches(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(),  (k, v) -> out.println("head map:  " + k + " - " + v),
                middleMap(),(k, v) -> out.println("middle map:" + k + " - " + v),
                tailMap(),  (k, v) -> out.println("tail map:  " + k + " - " + v),
                atMap(2),   (k, v) -> out.println("at map:    " + k + " - " + v)
        );

        System.out.println();

        matches(list,
                head(),   h -> out.println("head list:  " + h),
                middle(), m -> out.println("middle list:" + m),
                tail(),   t -> out.println("tail list:  " + t),
                at(1),    i -> out.println("at list:    " + i)
        );

        matches(map,
                headMap(),   h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(),   t -> out.println("tail map:  " + t.getKey() + " - " + t.getValue()),
                atMap(2),    h -> out.println("at map:    " + h.getKey() + " - " + h.getValue())
        );

        matches(map,
                headMap(),  (k, v) -> out.println("head map:  " + k + " - " + v),
                middleMap(),(k, v) -> out.println("middle map:" + k + " - " + v),
                tailMap(),  (k, v) -> out.println("tail map:  " + k + " - " + v),
                atMap(2),   (k, v) -> out.println("at map:    " + k + " - " + v)
        );

        System.out.println("\n");

        /* 1-2-3-4-5 */
        matches(list,
                empty(), () -> out.println("empty list"),
                head(),   h -> out.println("head list:  " + h),
                middle(), m -> out.println("middle list:" + m),
                tail(),   t -> out.println("tail list:  " + t),
                at(1),    i -> out.println("at list:    " + i),
                edges(), (first, last) -> out.println("edges list: " + first + " - " + last)
        );

        matches(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(),   h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(),   t -> out.println("tail map:  " + t.getKey() + " - " + t.getValue()),
                atMap(2),    h -> out.println("at map:    " + h.getKey() + " - " + h.getValue()),
                edgesMap(), (first, last) -> out.println("edges map [" + first.getKey() + ":" + first.getValue() + "] "
                                                                        + "[" + last.getKey()  + ":" + last.getValue()  + "]")
        );

        matches(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(),  (k, v) -> out.println("head map:  " + k + " - " + v),
                middleMap(),(k, v) -> out.println("middle map:" + k + " - " + v),
                tailMap(),  (k, v) -> out.println("tail map:  " + k + " - " + v),
                atMap(2),   (k, v) -> out.println("at map:    " + k + " - " + v),
                edgesMap(), (first, last) -> out.println("edges map [" + first.getKey() + ":" + first.getValue() + "] "
                                                                        + "[" + last.getKey()  + ":" + last.getValue()  + "]")
        );

        System.out.println();

        matches(list,
                head(),   h -> out.println("head list:  " + h),
                middle(), m -> out.println("middle list:" + m),
                tail(),   t -> out.println("tail list:  " + t),
                at(1),    i -> out.println("at list:    " + i),
                edges(), (first, last) -> out.println("edges list: " + first + " - " + last)
        );

        matches(map,
                headMap(),   h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(),   t -> out.println("tail map:  " + t.getKey() + " - " + t.getValue()),
                atMap(2),    h -> out.println("at map:    " + h.getKey() + " - " + h.getValue()),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                                 + "[" + l.getKey()  + ":" + l.getValue()  + "]")
        );

        matches(map,
                headMap(),  (k, v) -> out.println("head map:  " + k + " - " + v),
                middleMap(),(k, v) -> out.println("middle map:" + k + " - " + v),
                tailMap(),  (k, v) -> out.println("tail map:  " + k + " - " + v),
                atMap(2),   (k, v) -> out.println("at map:    " + k + " - " + v),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                                 + "[" + l.getKey()  + ":" + l.getValue()  + "]")
        );

        System.out.println();

        matches(list,
                empty(), () -> out.println("empty list"),
                edges(), (first, last) -> out.println("edges list: " + first + " - " + last)
        );

        matches(map,
                emptyMap(), () -> out.println("empty map"),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                                 + "[" + l.getKey() + ":" + l.getValue()  + "]")
        );

        System.out.println();

        matches(list,
                empty(), () -> out.println("empty list"),
                head(),   h -> out.println("head list:  " + h),
                edges(), (first, last) -> out.println("edges list: " + first + " - " + last)
        );

        matches(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(),   h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                                 + "[" + l.getKey() + ":" + l.getValue()  + "]")
        );

        matches(map,
                headMap(),  (k, v) -> out.println("head map:  " + k + " - " + v),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                        + "[" + l.getKey() + ":" + l.getValue()  + "]")
        );

        System.out.println();

        matches(list,
                head(),   h -> out.println("head list:  " + h),
                edges(), (first, last) -> out.println("edges list: " + first + " - " + last)
        );

        matches(map,
                headMap(),   h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                                 + "[" + l.getKey() + ":" + l.getValue()  + "]")
        );

        matches(map,
                headMap(),  (k, v) -> out.println("head map:  " + k + " - " + v),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                                 + "[" + l.getKey() + ":" + l.getValue()  + "]")
        );

        System.out.println();

        matches(list,
                empty(), () -> out.println("empty list"),
                head(),   h -> out.println("head list:  " + h),
                middle(), m -> out.println("middle list:" + m),
                edges(), (first, last) -> out.println("edges list: " + first + " - " + last)
        );

        matches(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(),   h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map:" + m.getKey() + " - " + m.getValue()),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                                 + "[" + l.getKey() + ":" + l.getValue()  + "]")
        );

        matches(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(),  (k, v) -> out.println("head map:  " + k + " - " + v),
                middleMap(),(k, v) -> out.println("middle map:" + k + " - " + v),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                                 + "[" + l.getKey() + ":" + l.getValue()  + "]")
        );

        System.out.println();

        matches(list,
                head(),   h -> out.println("head list:  " + h),
                middle(), m -> out.println("middle list:" + m),
                edges(), (first, last) -> out.println("edges list: " + first + " - " + last)
        );

        matches(map,
                headMap(),   h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map:" + m.getKey() + " - " + m.getValue()),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                                 + "[" + l.getKey() + ":" + l.getValue()  + "]")
        );

        matches(map,
                headMap(),  (k, v) -> out.println("head map:  " + k + " - " + v),
                middleMap(),(k, v) -> out.println("middle map:" + k + " - " + v),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                                 + "[" + l.getKey() + ":" + l.getValue()  + "]")
        );

        System.out.println();

        matches(list,
                empty(), () -> out.println("empty list"),
                head(),   h -> out.println("head list:  " + h),
                middle(), m -> out.println("middle list:" + m),
                tail(),   t -> out.println("tail list:  " + t),
                edges(), (first, last) -> out.println("edges list: " + first + " - " + last)
        );

        matches(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(),   h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(),   t -> out.println("tail map:  " + t.getKey() + " - " + t.getValue()),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                                 + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        matches(map,
                emptyMap(), () -> out.println("empty map"),
                headMap(),  (k, v) -> out.println("head map:  " + k + " - " + v),
                middleMap(),(k, v) -> out.println("middle map:" + k + " - " + v),
                tailMap(),  (k, v) -> out.println("tail map:  " + k + " - " + v),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                                 + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.println();

        matches(list,
                head(),   h -> out.println("head list:  " + h),
                middle(), m -> out.println("middle list:" + m),
                tail(),   t -> out.println("tail list:  " + t),
                edges(), (first, last) -> out.println("edges list: " + first + " - " + last)
        );

        matches(map,
                headMap(),   h -> out.println("head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(),   t -> out.println("tail map:  " + t.getKey() + " - " + t.getValue()),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                                 + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        matches(map,
                headMap(),  (k, v) -> out.println("head map:  " + k + " - " + v),
                middleMap(),(k, v) -> out.println("middle map:" + k + " - " + v),
                tailMap(),  (k, v) -> out.println("tail map:  " + k + " - " + v),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                                 + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.println();
    }

    @Test
    public void matchesAsStatementTest() {
        /* 1 */
        matches(list).as(
                empty(), () -> out.println("as empty list")
        );

        matches(map).as(
                emptyMap(), () -> out.println("as empty map")
        );

        System.out.print("\n");

        /* 2 */
        matches(list).as(
                head(), h -> out.println("as head list: " + h)
        );

        matches(map).as(
                headMap(), h -> out.println("as head map: " + h.getKey() + " - " + h.getValue())
        );

        matches(map).as(
                headMap(), (k, v) -> out.println("as head map: " + k + " - " + v)
        );

        System.out.print("\n");

        /* 3 */
        matches(list).as(
                middle(), m -> out.println("as middle list: " + m)
        );

        matches(map).as(
                middleMap(), h -> out.println("as middle map: " + h.getKey() + " - " + h.getValue())
        );

        matches(map).as(
                middleMap(), (k, v) -> out.println("as middle map: " + k + " - " + v)
        );

        System.out.print("\n");

        /* 4 */
        matches(list).as(
                tail(), t -> out.println("as tail list: " + t)
        );

        matches(map).as(
                tailMap(), h -> out.println("as tail map: " + h.getKey() + " - " + h.getValue())
        );

        matches(map).as(
                tailMap(), (k, v) -> out.println("as tail map: " + k + " - " + v)
        );

        System.out.print("\n");

        /* 5 */
        matches(list).as(
                at(1), i -> out.println("as at list: " + i)
        );

        matches(map).as(
                atMap(2), h -> out.println("as at map: " + h.getKey() + " - " + h.getValue())
        );

        matches(map).as(
                atMap(2), (k, v) -> out.println("as at map: " + k + " - " + v)
        );

        System.out.print("\n");

        /* 6 */
        matches(list).as(
                edges(), (first, last) -> out.println("as edges list: " + first + " - " + last)
        );

        matches(map).as(
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "]" + "\n" +
                                                  "             [" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.print("\n");

        /* 7 */
        matches(list).as(
                sub(0, 2), xs -> xs.forEach(x -> System.out.print("as sub item list: " + x + "\n"))
        );

        matches(map).as(
                subMap(0, 2), xs -> xs.forEach(x -> System.out.print("as sub item map: " + x + "\n"))
        );

        System.out.print("\n");

        /* 8 */
        matches(list).as(
                rest(1), xs -> xs.forEach(x -> System.out.print("as rest item list: " + x + "\n"))
        );

        matches(map).as(
                restMap(1), xs -> xs.forEach(x -> System.out.print("as rest item map: " + x + "\n"))
        );

        System.out.print("\n");

        /* 9 */
        matches(list).as(
                some(0, 2, 4), xs -> xs.forEach(x -> System.out.print("as some item list: " + x + "\n"))
        );

        matches(map).as(
                someMap(0, 2, 4), xs -> xs.forEach(x -> System.out.print("as some item map: " + x + "\n"))
        );

        /* 1-2 */
        matches(list).as(
                empty(), () -> out.println("as empty list"),
                head(),   h -> out.println("as head list: " + h)
        );

        matches(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(),   h -> out.println("as head map: " + h.getKey() + " - " + h.getValue())
        );

        matches(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(),  (k, v) -> out.println("as head map: " + k + " - " + v)
        );

        System.out.print("\n");

        /* 1-2-3 */
        matches(list).as(
                empty(), () -> out.println("as empty list"),
                head(),   h -> out.println("as head list: " + h),
                middle(), m -> out.println("as middle list: " + m)
        );

        matches(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(),   h -> out.println("as head map: " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map: " + m.getKey() + " - " + m.getValue())
        );

        matches(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(),  (k, v) -> out.println("as head map: " + k + " - " + v),
                middleMap(),(k, v) -> out.println("as middle map: " + k + " - " + v)
        );

        System.out.println();

        matches(list).as(
                head(),   h -> out.println("as head list: " + h),
                middle(), m -> out.println("as middle list: " + m)
        );

        matches(map).as(
                headMap(),   h -> out.println("as head map: " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map: " + m.getKey() + " - " + m.getValue())
        );

        matches(map).as(
                headMap(),  (k, v) -> out.println("as head map: " + k + " - " + v),
                middleMap(),(k, v) -> out.println("as middle map: " + k + " - " + v)
        );

        System.out.println("\n");

        /* 1-2-3-4 */
        matches(list).as(
                empty(), () -> out.println("as empty list"),
                head(),   h -> out.println("as head list: "  + h),
                middle(), m -> out.println("as middle list:" + m),
                tail(),   t -> out.println("as tail list: "  + t)
        );

        matches(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(),   h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(),   t -> out.println("as tail map:  " + t.getKey() + " - " + t.getValue())
        );

        matches(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(),  (k, v) -> out.println("as head map:  " + k + " - " + v),
                middleMap(),(k, v) -> out.println("as middle map:" + k + " - " + v),
                tailMap(),  (k, v) -> out.println("as tail map:  " + k + " - " + v)
        );

        System.out.println();

        matches(list).as(
                head(),   h -> out.println("as head list: "  + h),
                middle(), m -> out.println("as middle list:" + m),
                tail(),   t -> out.println("as tail list: "  + t)
        );

        matches(map).as(
                headMap(),   h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(),   t -> out.println("as tail map:  " + t.getKey() + " - " + t.getValue())
        );

        matches(map).as(
                headMap(),  (k, v) -> out.println("as head map:  " + k + " - " + v),
                middleMap(),(k, v) -> out.println("as middle map:" + k + " - " + v),
                tailMap(),  (k, v) -> out.println("as tail map:  " + k + " - " + v)
        );

        System.out.println("\n");

        /* 1-2-3-4-5 */
        matches(list).as(
                empty(), () -> out.println("as empty list"),
                head(),   h -> out.println("as head list:  " + h),
                middle(), m -> out.println("as middle list:" + m),
                tail(),   t -> out.println("as tail list:  " + t),
                at(1),    i -> out.println("as at list:    " + i)
        );

        matches(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(),   h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(),   t -> out.println("as tail map:  " + t.getKey() + " - " + t.getValue()),
                atMap(2),    h -> out.println("as at map:    " + h.getKey() + " - " + h.getValue())
        );

        matches(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(),  (k, v) -> out.println("as head map:  " + k + " - " + v),
                middleMap(),(k, v) -> out.println("as middle map:" + k + " - " + v),
                tailMap(),  (k, v) -> out.println("as tail map:  " + k + " - " + v),
                atMap(2),   (k, v) -> out.println("as at map:    " + k + " - " + v)
        );

        System.out.println();

        matches(list).as(
                head(),   h -> out.println("as head list:  " + h),
                middle(), m -> out.println("as middle list:" + m),
                tail(),   t -> out.println("as tail list:  " + t),
                at(1),    i -> out.println("as at list:    " + i)
        );

        matches(map).as(
                headMap(),   h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(),   t -> out.println("as tail map:  " + t.getKey() + " - " + t.getValue()),
                atMap(2),    h -> out.println("as at map:    " + h.getKey() + " - " + h.getValue())
        );

        matches(map).as(
                headMap(),  (k, v) -> out.println("as head map:  " + k + " - " + v),
                middleMap(),(k, v) -> out.println("as middle map:" + k + " - " + v),
                tailMap(),  (k, v) -> out.println("as tail map:  " + k + " - " + v),
                atMap(2),   (k, v) -> out.println("as at map:    " + k + " - " + v)
        );

        System.out.println("\n");

        /* 1-2-3-4-5 */
        matches(list).as(
                empty(), () -> out.println("as empty list"),
                head(),   h -> out.println("as head list:  " + h),
                middle(), m -> out.println("as middle list:" + m),
                tail(),   t -> out.println("as tail list:  " + t),
                at(1),    i -> out.println("as at list:    " + i),
                edges(), (first, last) -> out.println("as edges list: " + first + " - " + last)
        );

        matches(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(),   h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(),   t -> out.println("as tail map:  " + t.getKey() + " - " + t.getValue()),
                atMap(2),    h -> out.println("as at map:    " + h.getKey() + " - " + h.getValue()),
                edgesMap(), (first, last) -> out.println("edges map [" + first.getKey() + ":" + first.getValue() + "] "
                                                                 + "[" + last.getKey()  + ":" + last.getValue()  + "]")
        );

        matches(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(),  (k, v) -> out.println("as head map:  " + k + " - " + v),
                middleMap(),(k, v) -> out.println("as middle map:" + k + " - " + v),
                tailMap(),  (k, v) -> out.println("as tail map:  " + k + " - " + v),
                atMap(2),   (k, v) -> out.println("as at map:    " + k + " - " + v),
                edgesMap(), (first, last) -> out.println("edges map [" + first.getKey() + ":" + first.getValue() + "] "
                                                                 + "[" + last.getKey()  + ":" + last.getValue()  + "]")
        );

        System.out.println();

        matches(list).as(
                head(),   h -> out.println("as head list:  " + h),
                middle(), m -> out.println("as middle list:" + m),
                tail(),   t -> out.println("as tail list:  " + t),
                at(1),    i -> out.println("as at list:    " + i),
                edges(), (first, last) -> out.println("as edges list: " + first + " - " + last)
        );

        matches(map).as(
                headMap(),   h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(),   t -> out.println("as tail map:  " + t.getKey() + " - " + t.getValue()),
                atMap(2),    h -> out.println("as at map:    " + h.getKey() + " - " + h.getValue()),
                edgesMap(), (f, l) -> out.println("edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                          + "[" + l.getKey()  + ":" + l.getValue()  + "]")
        );

        matches(map).as(
                headMap(),  (k, v) -> out.println("as head map:  " + k + " - " + v),
                middleMap(),(k, v) -> out.println("as middle map:" + k + " - " + v),
                tailMap(),  (k, v) -> out.println("as tail map:  " + k + " - " + v),
                atMap(2),   (k, v) -> out.println("as at map:    " + k + " - " + v),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                             + "[" + l.getKey()  + ":" + l.getValue()  + "]")
        );

        System.out.println();

        matches(list).as(
                empty(), () -> out.println("as empty list"),
                edges(), (first, last) -> out.println("as edges list: " + first + " - " + last)
        );

        matches(map).as(
                emptyMap(), () -> out.println("as empty map"),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                          + "[" + l.getKey() + ":" + l.getValue()  + "]")
        );

        System.out.println();

        matches(list).as(
                empty(), () -> out.println("as empty list"),
                head(),   h -> out.println("as head list:  " + h),
                edges(), (first, last) -> out.println("as edges list: " + first + " - " + last)
        );

        matches(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(),   h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                          + "[" + l.getKey() + ":" + l.getValue()  + "]")
        );

        matches(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(),  (k, v) -> out.println("as head map:  " + k + " - " + v),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                             + "[" + l.getKey() + ":" + l.getValue()  + "]")
        );

        System.out.println();

        matches(list).as(
                head(),   h -> out.println("as head list:  " + h),
                edges(), (first, last) -> out.println("as edges list: " + first + " - " + last)
        );

        matches(map).as(
                headMap(),   h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                             + "[" + l.getKey() + ":" + l.getValue()  + "]")
        );

        matches(map).as(
                headMap(),  (k, v) -> out.println("as head map:  " + k + " - " + v),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                             + "[" + l.getKey() + ":" + l.getValue()  + "]")
        );

        System.out.println();

        matches(list).as(
                empty(), () -> out.println("as empty list"),
                head(),   h -> out.println("as head list:  " + h),
                middle(), m -> out.println("as middle list:" + m),
                edges(), (first, last) -> out.println("as edges list: " + first + " - " + last)
        );

        matches(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(),   h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map:" + m.getKey() + " - " + m.getValue()),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                             + "[" + l.getKey() + ":" + l.getValue()  + "]")
        );

        matches(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(),  (k, v) -> out.println("as head map:  " + k + " - " + v),
                middleMap(),(k, v) -> out.println("as middle map:" + k + " - " + v),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                             + "[" + l.getKey() + ":" + l.getValue()  + "]")
        );

        System.out.println();

        matches(list).as(
                head(),   h -> out.println("as head list:  " + h),
                middle(), m -> out.println("as middle list:" + m),
                edges(), (first, last) -> out.println("as edges list: " + first + " - " + last)
        );

        matches(map).as(
                headMap(),   h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map:" + m.getKey() + " - " + m.getValue()),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                             + "[" + l.getKey() + ":" + l.getValue()  + "]")
        );

        matches(map).as(
                headMap(),  (k, v) -> out.println("as head map:  " + k + " - " + v),
                middleMap(),(k, v) -> out.println("as middle map:" + k + " - " + v),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                             + "[" + l.getKey() + ":" + l.getValue()  + "]")
        );

        System.out.println();

        matches(list).as(
                empty(), () -> out.println("as empty list"),
                head(),   h -> out.println("as head list:  " + h),
                middle(), m -> out.println("as middle list:" + m),
                tail(),   t -> out.println("as tail list:  " + t),
                edges(), (first, last) -> out.println("as edges list: " + first + " - " + last)
        );

        matches(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(),   h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(),   t -> out.println("as tail map:  " + t.getKey() + " - " + t.getValue()),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                             + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        matches(map).as(
                emptyMap(), () -> out.println("as empty map"),
                headMap(),  (k, v) -> out.println("as head map:  " + k + " - " + v),
                middleMap(),(k, v) -> out.println("as middle map:" + k + " - " + v),
                tailMap(),  (k, v) -> out.println("as tail map:  " + k + " - " + v),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                             + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.println();

        matches(list).as(
                head(),   h -> out.println("as head list:  " + h),
                middle(), m -> out.println("as middle list:" + m),
                tail(),   t -> out.println("as tail list:  " + t),
                edges(), (first, last) -> out.println("as edges list: " + first + " - " + last)
        );

        matches(map).as(
                headMap(),   h -> out.println("as head map:  " + h.getKey() + " - " + h.getValue()),
                middleMap(), m -> out.println("as middle map:" + m.getKey() + " - " + m.getValue()),
                tailMap(),   t -> out.println("as tail map:  " + t.getKey() + " - " + t.getValue()),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                             + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        matches(map).as(
                headMap(),  (k, v) -> out.println("as head map:  " + k + " - " + v),
                middleMap(),(k, v) -> out.println("as middle map:" + k + " - " + v),
                tailMap(),  (k, v) -> out.println("as tail map:  " + k + " - " + v),
                edgesMap(), (f, l) -> out.println("as edges map [" + f.getKey() + ":" + f.getValue() + "] "
                                                             + "[" + l.getKey() + ":" + l.getValue() + "]")
        );

        System.out.println();
    }
}
