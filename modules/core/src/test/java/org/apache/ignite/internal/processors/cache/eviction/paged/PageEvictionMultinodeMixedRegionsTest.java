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

package org.apache.ignite.internal.processors.cache.eviction.paged;

import org.apache.ignite.configuration.DataPageEvictionMode;
import org.apache.ignite.configuration.DataRegionConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.testframework.GridTestUtils;

/**
 * Enables but not touches persistent region, checks page eviction and PDS+no PDS mode.
 */
public class PageEvictionMultinodeMixedRegionsTest extends PageEvictionMultinodeAbstractTest {
    /** {@inheritDoc} */
    @Override protected IgniteConfiguration getConfiguration(String gridName) throws Exception {
        IgniteConfiguration cfg = super.getConfiguration(gridName);

        setEvictionMode(DataPageEvictionMode.RANDOM_2_LRU, cfg);

        DataRegionConfiguration persReg = new DataRegionConfiguration()
            .setName("persisted")
            .setPersistenceEnabled(true)
            .setMaxSize(128L * 1024 * 1024); // limit memory to save space on agents

        cfg.getDataStorageConfiguration().setDataRegionConfigurations(persReg);

        return cfg;
    }

    /** {@inheritDoc} */
    @Override protected void beforeTestsStarted() throws Exception {
        cleanPersistenceDir();

        super.beforeTestsStarted();

        clientGrid().cluster().active(true);
    }

    /** {@inheritDoc} */
    @Override protected void afterTestsStopped() throws Exception {
        stopAllGrids();

        cleanPersistenceDir();
    }
}
