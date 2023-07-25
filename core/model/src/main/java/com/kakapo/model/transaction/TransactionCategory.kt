package com.kakapo.model.transaction

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class TransactionCategory(
    val id: Int = 0,
    val icon: Int = 0,
    val type: TransactionCategoryType = TransactionCategoryType.Income,
    val name: Int = 0
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

fun TransactionCategory.asJson(): String{
    return Gson().toJson(this)
}

fun String.fromJson(): TransactionCategory {
    return if (this == ""){
        TransactionCategory()
    }else{
        Gson().fromJson(this, object : TypeToken<TransactionCategory>() {}.type)
    }
}