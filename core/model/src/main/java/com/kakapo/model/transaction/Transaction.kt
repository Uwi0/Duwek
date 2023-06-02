package com.kakapo.model.transaction

data class Transaction(
    val id: Long = 0,
    val amount: Double = 0.0,
    val category: String = "",
    val date: String = "",
    val description: String = "",
    val type: TransactionType = TransactionType.EXPENSE
)

enum class TransactionType{
    EXPENSE,
    INCOME
}
