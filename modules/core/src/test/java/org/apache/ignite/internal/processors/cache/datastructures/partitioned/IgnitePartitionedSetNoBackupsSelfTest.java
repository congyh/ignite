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

package org.apache.ignite.internal.processors.cache.datastructures.partitioned;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import org.apache.ignite.configuration.CollectionConfiguration;
import org.apache.ignite.internal.IgniteKernal;
import org.apache.ignite.internal.processors.cache.GridCacheAdapter;
import org.apache.ignite.internal.processors.cache.GridCacheContext;
import org.apache.ignite.internal.processors.cache.GridCacheMapEntry;
import org.apache.ignite.testframework.GridTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *
 */
@RunWith(JUnit4.class)
public class IgnitePartitionedSetNoBackupsSelfTest extends GridCachePartitionedSetSelfTest {
    /** {@inheritDoc} */
    @Override protected CollectionConfiguration collectionConfiguration() {
        CollectionConfiguration colCfg = super.collectionConfiguration();

        colCfg.setBackups(0);

        return colCfg;
    }

    /**
     * @throws Exception If failed.
     */
    @Test
    public void testCollocation() throws Exception {
        Set<Integer> set0 = grid(0).set(SET_NAME, config(true));

        for (int i = 0; i < 1000; i++)
            assertTrue(set0.add(i));

        assertEquals(1000, set0.size());

        GridCacheContext cctx = GridTestUtils.getFieldValue(set0, "cctx");

        UUID setNodeId = null;

        for (int i = 0; i < gridCount(); i++) {
            IgniteKernal grid = (IgniteKernal)grid(i);

            GridCacheAdapter cache  = grid.context().cache().internalCache(cctx.name());

            Iterator<GridCacheMapEntry> entries = cache.map().entries(cache.context().cacheId()).iterator();

            if (entries.hasNext()) {
                if (setNodeId == null)
                    setNodeId = grid.localNode().id();
                else
                    fail("For collocated set all items should be stored on single node.");
            }
        }
    }
}
