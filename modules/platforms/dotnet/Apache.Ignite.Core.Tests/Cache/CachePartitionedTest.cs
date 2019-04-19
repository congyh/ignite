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

namespace Apache.Ignite.Core.Tests.Cache
{
    using Apache.Ignite.Core.Cache;
    using Apache.Ignite.Core.Cache.Configuration;
    using Apache.Ignite.Core.Transactions;
    using NUnit.Framework;

    [Category(TestUtils.CategoryIntensive)]
    public class CachePartitionedTest : CacheAbstractTransactionalTest
    {
        protected override int GridCount()
        {
            return 3;
        }

        protected override string CacheName()
        {
            return "partitioned";
        }

        protected override bool NearEnabled()
        {
            return false;
        }

        protected override int Backups()
        {
            return 1;
        }

        /// <summary>
        /// Test MVCC transaction.
        /// </summary>
        [Test]
        public void TestMvccTransaction()
        {
            IIgnite ignite = GetIgnite(0);

            ICache<int, int> cache = ignite.GetOrCreateCache<int, int>(new CacheConfiguration
            {
                Name = "mvcc",
                AtomicityMode = CacheAtomicityMode.TransactionalSnapshot
            });

            ITransaction tx = ignite.GetTransactions().TxStart();

            cache.Put(1, 1);
            cache.Put(2, 2);

            tx.Commit();

            Assert.AreEqual(1, cache.Get(1));
            Assert.AreEqual(2, cache.Get(2));

            tx = ignite.GetTransactions().TxStart();

            Assert.AreEqual(1, cache.Get(1));
            Assert.AreEqual(2, cache.Get(2));

            tx.Commit();

            ignite.DestroyCache("mvcc");
        }
    }
}
