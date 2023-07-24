package com.kakapo.data.repository.base

import com.kakapo.data.model.transactionCategory.DataTransactionCategory
import com.kakapo.model.transaction.Transaction
import com.kakapo.model.transaction.TransactionCategory
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    suspend fun addTransaction(transaction: Transaction): Flow<Unit>

    suspend fun getListTransaction(): Flow<List<Transaction>>

    suspend fun getListTransactionType(): Flow<List<DataTransactionCategory>>

}