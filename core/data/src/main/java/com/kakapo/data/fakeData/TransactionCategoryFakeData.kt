package com.kakapo.data.fakeData

import com.kakapo.data.model.transactionCategory.DataTransactionCategory
import com.kakapo.model.transaction.TransactionCategoryType

fun listFakeTransactionCategory(): List<DataTransactionCategory> {
    return listOf(
        DataTransactionCategory(id = 1, transactionCategoryType = TransactionCategoryType.Expense),
        DataTransactionCategory(id = 2, transactionCategoryType = TransactionCategoryType.Expense),
        DataTransactionCategory(id = 3, transactionCategoryType = TransactionCategoryType.Expense),
        DataTransactionCategory(id = 4, transactionCategoryType = TransactionCategoryType.Expense),
        DataTransactionCategory(id = 5, transactionCategoryType = TransactionCategoryType.Expense),
        DataTransactionCategory(id = 100, transactionCategoryType = TransactionCategoryType.Income),
        DataTransactionCategory(id = 101, transactionCategoryType = TransactionCategoryType.Income)
    )
}