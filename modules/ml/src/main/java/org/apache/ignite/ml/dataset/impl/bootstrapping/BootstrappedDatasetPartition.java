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

package org.apache.ignite.ml.dataset.impl.bootstrapping;

import java.util.Arrays;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;

/**
 * Partition of bootstrapped vectors.
 */
public class BootstrappedDatasetPartition implements AutoCloseable, Iterable<BootstrappedVector> {
    /** Vectors. */
    private final BootstrappedVector[] vectors;

    /**
     * Creates an instance of BootstrappedDatasetPartition.
     *
     * @param vectors Vectors.
     */
    public BootstrappedDatasetPartition(BootstrappedVector[] vectors) {
        this.vectors = vectors;
    }

    /**
     * Returns vector from dataset in according to row id.
     *
     * @param rowId Row id.
     * @return Vector.
     */
    public BootstrappedVector getRow(int rowId) {
        return vectors[rowId];
    }

    /**
     * Returns rows count.
     *
     * @return rows count.
     */
    public int getRowsCount() {
        return vectors.length;
    }

    /** {@inheritDoc} */
    @NotNull @Override public Iterator<BootstrappedVector> iterator() {
        return Arrays.stream(vectors).iterator();
    }

    /** {@inheritDoc} */
    @Override public void close() throws Exception {
        //NOP
    }
}
