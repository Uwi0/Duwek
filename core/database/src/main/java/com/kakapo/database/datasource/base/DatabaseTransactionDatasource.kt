package com.kakapo.database.datasource.base

import com.kakapo.database.model.DatabaseTransaction

interface DatabaseTransactionDatasource {

    suspend fun addTransaction(transaction: DatabaseTransaction)

    suspend fun listTransaction(): List<DatabaseTransaction>
}