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

package org.apache.ignite.platform.plugin.cache;

import org.apache.ignite.plugin.CachePluginConfiguration;
import org.apache.ignite.plugin.CachePluginContext;
import org.apache.ignite.plugin.CachePluginProvider;

/**
 * Test plugin configuration.
 */
public class PlatformTestCachePluginConfiguration implements CachePluginConfiguration {
    /** */
    private String pluginProperty;

    /**
     * Initializes a new instance of PlatformTestPluginConfiguration.
     */
    PlatformTestCachePluginConfiguration() {
        // No-op.
    }

    /**
     * Gets the plugin property.
     *
     * @return Plugin property.
     */
    public String pluginProperty() {
        return pluginProperty;
    }

    /**
     * Sets the plugin property.
     *
     * @param pluginProperty Value.
     */
    void setPluginProperty(String pluginProperty) {
        this.pluginProperty = pluginProperty;
    }
}
