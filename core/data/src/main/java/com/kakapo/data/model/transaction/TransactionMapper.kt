package com.kakapo.data.model.transaction

import com.kakapo.database.model.DatabaseTransaction
import com.kakapo.model.transaction.Transaction

fun mapToDatabase(transaction: Transaction): DatabaseTransaction {
    return DatabaseTransaction(
        category = transaction.category,
        date = transaction.date,
        description = transaction.description,
        transactionType = transaction.type,
        amount = transaction.amount
    )
}

fun toTransaction(entity: DatabaseTransaction): Transaction {
    return Transaction(
        id = entity.id,
        amount = entity.amount,
        category = entity.category,
        date = entity.date,
        description = entity.description,
        type = entity.transactionType
    )
}