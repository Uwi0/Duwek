package com.kakapo.database.datasource.impl

import com.kakapo.database.DuwekLocalDB
import com.kakapo.database.datasource.base.DatabaseTransactionDatasource
import com.kakapo.database.model.DatabaseTransaction
import javax.inject.Inject

class DatabaseTransactionDatasourcceImpl @Inject constructor(
    private val database: DuwekLocalDB
) : DatabaseTransactionDatasource {
    override suspend fun addTransaction(transaction: DatabaseTransaction) {
        database.transactionDao().insertDataTransaction(transaction)
    }

    override suspend fun listTransaction(): List<DatabaseTransaction> {
        return database.transactionDao().getAllListTransaction()
    }

}