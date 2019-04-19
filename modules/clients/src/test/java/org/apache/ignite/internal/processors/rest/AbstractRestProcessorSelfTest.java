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

package org.apache.ignite.internal.processors.rest;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.ConnectorConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.testframework.junits.common.GridCommonAbstractTest;

/**
 * Abstract class for REST protocols tests.
 */
public abstract class AbstractRestProcessorSelfTest extends GridCommonAbstractTest {
    /** Local host. */
    protected static final String LOC_HOST = "127.0.0.1";

    /**
     * @return Grid count.
     */
    protected abstract int gridCount();

    /** {@inheritDoc} */
    @Override protected void beforeTestsStarted() throws Exception {
        cleanPersistenceDir();

        startGrids(gridCount());
    }

    /** {@inheritDoc} */
    @Override protected void beforeTest() throws Exception {
        assert grid(0).cluster().nodes().size() == gridCount();
    }

    /** {@inheritDoc} */
    @Override protected void afterTest() throws Exception {
        jcache().clear();

        assertEquals(0, jcache().localSize());
    }

    /** {@inheritDoc} */
    @Override protected void afterTestsStopped() throws Exception {
        stopAllGrids();

        cleanPersistenceDir();
    }

    /** {@inheritDoc} */
    @Override protected IgniteConfiguration getConfiguration(String igniteInstanceName) throws Exception {
        IgniteConfiguration cfg = super.getConfiguration(igniteInstanceName);

        cfg.setLocalHost(LOC_HOST);

        assert cfg.getConnectorConfiguration() == null;

        ConnectorConfiguration clientCfg = new ConnectorConfiguration();

        clientCfg.setJettyPath("modules/clients/src/test/resources/jetty/rest-jetty.xml");

        clientCfg.setIdleQueryCursorTimeout(5000);
        clientCfg.setIdleQueryCursorCheckFrequency(5000);

        cfg.setConnectorConfiguration(clientCfg);

        CacheConfiguration ccfg = defaultCacheConfiguration();

        ccfg.setStatisticsEnabled(true);
        ccfg.setIndexedTypes(String.class, String.class);

        cfg.setCacheConfiguration(ccfg);

        return cfg;
    }

    /**
     * @return Cache.
     */
    @Override protected <K, V> IgniteCache<K, V> jcache() {
        return grid(0).cache(DEFAULT_CACHE_NAME);
    }
}
