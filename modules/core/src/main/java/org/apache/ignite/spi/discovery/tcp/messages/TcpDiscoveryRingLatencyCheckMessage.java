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

package org.apache.ignite.spi.discovery.tcp.messages;

import java.util.UUID;
import org.apache.ignite.internal.util.typedef.internal.S;
import org.apache.ignite.internal.util.typedef.internal.U;

/**
 *
 */
public class TcpDiscoveryRingLatencyCheckMessage extends TcpDiscoveryAbstractMessage {
    /** */
    private static final long serialVersionUID = 0L;

    /** */
    private int maxHops;

    /** */
    private int curHop;

    /**
     * @param creatorNodeId Creator node ID.
     * @param maxHops Max hops for this message.
     */
    public TcpDiscoveryRingLatencyCheckMessage(
        UUID creatorNodeId,
        int maxHops
    ) {
        super(creatorNodeId);

        assert maxHops > 0;

        this.maxHops = maxHops;
    }

    /**
     *
     */
    public void onRead() {
        curHop++;
    }

    /**
     * @return Max hops.
     */
    public int maxHops() {
        return maxHops;
    }

    /**
     * @return {@code True} if max hops has been reached.
     */
    public boolean maxHopsReached() {
        return curHop == maxHops;
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return S.toString(TcpDiscoveryRingLatencyCheckMessage.class, this, "super", super.toString());
    }
}
