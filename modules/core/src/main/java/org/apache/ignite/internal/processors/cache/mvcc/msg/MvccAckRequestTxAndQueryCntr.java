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

package org.apache.ignite.internal.processors.cache.mvcc.msg;

import java.nio.ByteBuffer;
import org.apache.ignite.internal.managers.communication.GridIoMessageFactory;
import org.apache.ignite.internal.util.typedef.internal.S;
import org.apache.ignite.plugin.extensions.communication.MessageReader;
import org.apache.ignite.plugin.extensions.communication.MessageWriter;

/**
 *
 */
public class MvccAckRequestTxAndQueryCntr extends MvccAckRequestTx {
    /** */
    private static final long serialVersionUID = 0L;

    /** */
    private long qryCntr;

    /**
     * Required by {@link GridIoMessageFactory}.
     */
    public MvccAckRequestTxAndQueryCntr() {
        // No-op.
    }

    /**
     * @param futId Future ID.
     * @param txCntr Counter assigned to transaction update.
     * @param qryCntr Counter assigned for transaction reads.
     */
    public MvccAckRequestTxAndQueryCntr(long futId, long txCntr, long qryCntr) {
        super(futId, txCntr);

        this.qryCntr = qryCntr;
    }

    /** {@inheritDoc} */
    @Override public long queryCounter() {
        return qryCntr;
    }

    /** {@inheritDoc} */
    @Override public boolean writeTo(ByteBuffer buf, MessageWriter writer) {
        writer.setBuffer(buf);

        if (!super.writeTo(buf, writer))
            return false;

        if (!writer.isHeaderWritten()) {
            if (!writer.writeHeader(directType(), fieldsCount()))
                return false;

            writer.onHeaderWritten();
        }

        switch (writer.state()) {
            case 3:
                if (!writer.writeLong("qryCntr", qryCntr))
                    return false;

                writer.incrementState();

        }

        return true;
    }

    /** {@inheritDoc} */
    @Override public boolean readFrom(ByteBuffer buf, MessageReader reader) {
        reader.setBuffer(buf);

        if (!reader.beforeMessageRead())
            return false;

        if (!super.readFrom(buf, reader))
            return false;

        switch (reader.state()) {
            case 3:
                qryCntr = reader.readLong("qryCntr");

                if (!reader.isLastRead())
                    return false;

                reader.incrementState();

        }

        return reader.afterMessageRead(MvccAckRequestTxAndQueryCntr.class);
    }

    /** {@inheritDoc} */
    @Override public short directType() {
        return 146;
    }

    /** {@inheritDoc} */
    @Override public byte fieldsCount() {
        return 4;
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return S.toString(MvccAckRequestTxAndQueryCntr.class, this);
    }
}
