/*
 * Copyright 2019 GridGain Systems, Inc. and Contributors.
 * 
 * Licensed under the GridGain Community Edition License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     https://www.gridgain.com/products/software/community-edition/gridgain-community-edition-license
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.ml.tree.impurity.util;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests for {@link StepFunction}.
 */
public class StepFunctionTest {
    /** */
    @Test
    public void testAddIncreasingFunctions() {
        StepFunction<TestImpurityMeasure> a = new StepFunction<>(
            new double[]{1, 3, 5},
            TestImpurityMeasure.asTestImpurityMeasures(1, 2, 3)
        );

        StepFunction<TestImpurityMeasure> b = new StepFunction<>(
            new double[]{0, 2, 4},
            TestImpurityMeasure.asTestImpurityMeasures(1, 2, 3)
        );

        StepFunction<TestImpurityMeasure> c = a.add(b);

        assertArrayEquals(new double[]{0, 1, 2, 3, 4, 5}, c.getX(), 1e-10);
        assertArrayEquals(
            TestImpurityMeasure.asTestImpurityMeasures(1, 2, 3, 4, 5, 6),
            c.getY()
        );
    }

    /** */
    @Test
    public void testAddDecreasingFunctions() {
        StepFunction<TestImpurityMeasure> a = new StepFunction<>(
            new double[]{1, 3, 5},
            TestImpurityMeasure.asTestImpurityMeasures(3, 2, 1)
        );

        StepFunction<TestImpurityMeasure> b = new StepFunction<>(
            new double[]{0, 2, 4},
            TestImpurityMeasure.asTestImpurityMeasures(3, 2, 1)
        );

        StepFunction<TestImpurityMeasure> c = a.add(b);

        assertArrayEquals(new double[]{0, 1, 2, 3, 4, 5}, c.getX(), 1e-10);
        assertArrayEquals(
            TestImpurityMeasure.asTestImpurityMeasures(3, 6, 5, 4, 3, 2),
            c.getY()
        );
    }
}
