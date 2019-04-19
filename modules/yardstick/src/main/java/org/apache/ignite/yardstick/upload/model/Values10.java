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

package org.apache.ignite.yardstick.upload.model;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Describes data model.
 * Matches data model, defined in {@link QueryFactory#createTable()}
 */
public class Values10 {
    /** */
    final String val1;

    /** */
    final long val2;

    /** */
    final String val3;

    /** */
    final long val4;

    /** */
    final String val5;

    /** */
    final long val6;

    /** */
    final String val7;

    /** */
    final long val8;

    /** */
    final String val9;

    /** */
    final long val10;

    /** Creates new object with randomly initialized fields */
    public Values10(){
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        val1 = String.valueOf(rnd.nextLong());
        val2 = rnd.nextLong();

        val3 = String.valueOf(rnd.nextLong());
        val4 = rnd.nextLong();

        val5 = String.valueOf(rnd.nextLong());
        val6 = rnd.nextLong();

        val7 = String.valueOf(rnd.nextLong());
        val8 = rnd.nextLong();

        val9 = String.valueOf(rnd.nextLong());
        val10 = rnd.nextLong();
    }
}
