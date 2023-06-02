package com.kakapo.data.repository.impl

import com.kakapo.data.model.transaction.mapToDatabase
import com.kakapo.data.model.transaction.toTransaction
import com.kakapo.data.repository.base.TransactionRepository
import com.kakapo.data.result.proceed
import com.kakapo.database.datasource.base.DatabaseTransactionDatasource
import com.kakapo.model.transaction.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val databaseTransactionDatasource: DatabaseTransactionDatasource
) : TransactionRepository {

    override suspend fun addTransaction(transaction: Transaction): Flow<Unit> = flow {
        emit(
            proceed {
                val param = mapToDatabase(transaction)
                databaseTransactionDatasource.addTransaction(param)
            }
        )
    }

    override suspend fun getListTransaction(): Flow<List<Transaction>> = flow {
        emit(
            proceed {
                databaseTransactionDatasource.listTransaction().map(::toTransaction)
            }
        )
    }
}