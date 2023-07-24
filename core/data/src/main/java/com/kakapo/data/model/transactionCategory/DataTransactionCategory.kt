package com.kakapo.data.model.transactionCategory

import com.kakapo.model.transaction.TransactionCategoryType

data class DataTransactionCategory(
    val id: Int,
    val transactionCategoryType: TransactionCategoryType
)