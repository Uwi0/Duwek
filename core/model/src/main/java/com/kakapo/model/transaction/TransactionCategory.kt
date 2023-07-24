package com.kakapo.model.transaction

data class TransactionCategory(
    val id: Int,
    val icon: Int,
    val type: TransactionCategoryType,
    val name: Int
)

data class TransactionSubCategory(
    val id: Int,
)

enum class TransactionCategoryType{
    Expense,
    Income
}

enum class TransactionExpenseSubCategoryType {

}