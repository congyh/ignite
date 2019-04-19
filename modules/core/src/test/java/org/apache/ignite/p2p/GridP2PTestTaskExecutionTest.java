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

package org.apache.ignite.p2p;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCheckedException;
import org.apache.ignite.compute.ComputeTaskFuture;
import org.apache.ignite.internal.util.typedef.G;
import org.apache.ignite.internal.util.typedef.X;
import org.apache.ignite.testframework.junits.common.GridCommonAbstractTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test executes GridP2PTestTask on the remote node.
 * Before running of test you MUST start at least one remote node.
 */
@RunWith(JUnit4.class)
public final class GridP2PTestTaskExecutionTest extends GridCommonAbstractTest {
    /**
     * Method executes GridP2PTestTask.
     * @throws IgniteCheckedException If failed.
     */
    @Test
    public void testGridP2PTestTask() throws IgniteCheckedException {
        try (Ignite g  = G.start()) {
            assert g != null;

            assert !g.cluster().forRemotes().nodes().isEmpty() : "Test requires at least 1 remote node.";

            /* Execute GridP2PTestTask. */
            ComputeTaskFuture<Integer> fut = executeAsync(g.compute(), GridP2PTestTask.class, 1);

            /* Wait for task completion. */
            Integer res = fut.get();

            X.println("Result of execution is: " + res);

            assert res > 0 : "Result of execution is: " + res + " for more information see GridP2PTestJob";
        }
    }
}
