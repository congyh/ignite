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

package org.apache.ignite.cache.store.jdbc;

import org.apache.ignite.marshaller.Marshaller;
import org.apache.ignite.internal.binary.BinaryMarshaller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test for {@link CacheJdbcPojoStore} with binary marshaller.
 */
@RunWith(JUnit4.class)
public class CacheJdbcPojoStoreBinaryMarshallerSelfTest extends CacheJdbcPojoStoreAbstractSelfTest {
    /** {@inheritDoc} */
    @Override protected Marshaller marshaller(){
        return new BinaryMarshaller();
    }

    /**
     * @throws Exception If failed.
     */
    @Test
    public void testLoadCacheNoKeyClasses() throws Exception {
        startTestGrid(false, true, false, false, 512);

        checkCacheLoad();
    }

    /**
     * @throws Exception If failed.
     */
    @Test
    public void testLoadCacheNoKeyClassesTx() throws Exception {
        startTestGrid(false, true, false, true, 512);

        checkCacheLoad();
    }

    /**
     * @throws Exception If failed.
     */
    @Test
    public void testLoadCacheNoValueClasses() throws Exception {
        startTestGrid(false, false, true, false, 512);

        checkCacheLoad();
    }

    /**
     * @throws Exception If failed.
     */
    @Test
    public void testLoadCacheNoValueClassesTx() throws Exception {
        startTestGrid(false, false, true, true, 512);

        checkCacheLoad();
    }

    /**
     * @throws Exception If failed.
     */
    @Test
    public void testLoadCacheNoKeyAndValueClasses() throws Exception {
        startTestGrid(false, true, true, false, 512);

        checkCacheLoad();
    }

    /**
     * @throws Exception If failed.
     */
    @Test
    public void testLoadCacheNoKeyAndValueClassesTx() throws Exception {
        startTestGrid(false, true, true, true, 512);

        checkCacheLoad();
    }
}
