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

package org.apache.ignite.ml.tree.impurity.util;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.ignite.ml.tree.impurity.ImpurityMeasure;

/**
 * Base interface for step function compressors which reduces step function size.
 *
 * @param <T> Type of step function value.
 */
public interface StepFunctionCompressor<T extends ImpurityMeasure<T>> extends Serializable {
    /**
     * Compresses the given step function.
     *
     * @param function Step function.
     * @return Compressed step function.
     */
    public StepFunction<T> compress(StepFunction<T> function);

    /**
     * Compresses every step function in the given array.
     *
     * @param functions Array of step functions.
     * @return Arrays of compressed step function.
     */
    default public StepFunction<T>[] compress(StepFunction<T>[] functions) {
        if (functions == null)
            return null;

        StepFunction<T>[] res = Arrays.copyOf(functions, functions.length);

        for (int i = 0; i < res.length; i++)
            res[i] = compress(res[i]);

        return res;
    }
}
