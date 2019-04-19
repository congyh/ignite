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

package org.apache.ignite.spi.discovery.zk.internal;

import java.io.Serializable;
import java.util.Map;
import org.apache.ignite.internal.util.tostring.GridToStringInclude;
import org.apache.ignite.internal.util.typedef.internal.S;

/**
 *
 */
class ZkJoiningNodeData implements Serializable {
    /** */
    private static final long serialVersionUID = 0L;

    /** */
    private int partCnt;

    /** */
    @GridToStringInclude
    private ZookeeperClusterNode node;

    /** */
    @GridToStringInclude
    private Map<Integer, Serializable> discoData;

    /**
     * @param partCnt Number of parts in multi-parts message.
     */
    ZkJoiningNodeData(int partCnt) {
        this.partCnt = partCnt;
    }

    /**
     * @param node Node.
     * @param discoData Discovery data.
     */
    ZkJoiningNodeData(ZookeeperClusterNode node, Map<Integer, Serializable> discoData) {
        assert node != null && node.id() != null : node;
        assert discoData != null;

        this.node = node;
        this.discoData = discoData;
    }

    /**
     * @return Number of parts in multi-parts message.
     */
    int partCount() {
        return partCnt;
    }

    /**
     * @return Node.
     */
    ZookeeperClusterNode node() {
        return node;
    }

    /**
     * @return Discovery data.
     */
    Map<Integer, Serializable> discoveryData() {
        return discoData;
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return S.toString(ZkJoiningNodeData.class, this);
    }
}
