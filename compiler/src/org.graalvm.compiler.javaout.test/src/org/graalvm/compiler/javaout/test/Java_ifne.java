/*
 * Copyright (c) 2013, 2015, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.graalvm.compiler.javaout.test;

import org.junit.Test;

public class Java_ifne extends JavaOutputTest {
    public static int test(int a) {
        int n = 0;
        if (a != 0) {
            n += 1;
        } else {
            n -= 1;
        }
        if (a == 0) {
            n -= 1;
        } else {
            n += 1;
        }
        return n;
    }

    public static int fac(int a) {
        int n = 1;
        for (int i = 1; i < a; i++) {
            n *= i;
        }
        return n;
    }

    public static int rec(int a) {
        if (a <= 1) {
            return 1;
        } else {
            return a * rec(a - 1);
        }
    }

    @Test
    public void run0() throws Throwable {
        runTest("test", 0);
        assertCode("Currently three checks for zero", "== 0", 1, 3);
    }

    @Test
    public void run1() throws Throwable {
        runTest("test", 1);
    }

    @Test
    public void fac3() throws Throwable {
        runTest("fac", 3);
    }

    @Test
    public void fac6() throws Throwable {
        runTest("rec", 6);
    }
}
