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

package org.apache.ignite.mxbean;

import java.util.List;
import java.util.UUID;

/**
 * MXBean interface that provides access to ODBC\JDBC\Thin client connections.
 */
@MXBeanDescription("MBean that provides information about client connections.")
public interface ClientProcessorMXBean {
    /**
     * Returns list of active connections.
     *
     * @return Sessions.
     */
    @MXBeanDescription("List of client connections.")
    List<String> getConnections();

    /**
     * Drop all active connections.
     */
    @MXBeanDescription("Drop all client connections.")
    void dropAllConnections();

    /**
     * Drops client connection by {@code id}, if exists.
     *
     * @param id connection id.
     * @return {@code True} if connection has been dropped successfully, {@code false} otherwise.
     */
    @MXBeanDescription("Drop client connection by ID.")
    @MXBeanParametersNames(
        "id"
    )
    @MXBeanParametersDescriptions(
        "Client connection ID."
    )
    public boolean dropConnection(long id);
}
