package com.kakapo.data.repository.base

import com.kakapo.model.transaction.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    suspend fun addTransaction(transaction: Transaction): Flow<Unit>

    suspend fun getListTransaction(): Flow<List<Transaction>>

}