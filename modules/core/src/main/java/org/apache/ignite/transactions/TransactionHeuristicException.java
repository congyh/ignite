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

package org.apache.ignite.transactions;

/**
 * Exception thrown whenever grid transaction enters an unknown state.
 * This exception is usually thrown whenever commit partially succeeds.
 * Cache will still resolve this situation automatically to ensure data
 * integrity, by invalidating all values participating in this transaction
 * on remote nodes.
 */
public class TransactionHeuristicException extends TransactionException {
    /** */
    private static final long serialVersionUID = 0L;

    /**
     * Creates new heuristic exception with given error message.
     *
     * @param msg Error message.
     */
    public TransactionHeuristicException(String msg) {
        super(msg);
    }

    /**
     * Creates new heuristic exception with given error message and optional nested exception.
     *
     * @param msg Error message.
     * @param cause Optional nested exception (can be <tt>null</tt>).
     */
    public TransactionHeuristicException(String msg, Throwable cause) {
        super(msg, cause);
    }
}