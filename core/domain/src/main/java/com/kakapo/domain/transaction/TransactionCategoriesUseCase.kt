package com.kakapo.domain.transaction

import com.kakapo.data.model.transactionCategory.DataTransactionCategory
import com.kakapo.data.repository.base.TransactionRepository
import com.kakapo.data.result.proceed
import com.kakapo.domain.R
import com.kakapo.model.transaction.TransactionCategory
import com.kakapo.model.transaction.TransactionCategoryType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

typealias Categories = Pair<List<TransactionCategory>, List<TransactionCategory>>

class TransactionCategoriesUseCase @Inject constructor(private val transactionRepository: TransactionRepository) {

    operator fun invoke(): Flow<Categories> = flow {
        emit(
            proceed {
                val result = transactionRepository.getListTransactionType().first()
                val mappedResult = result.map(::toTransactionCategory)
                val expenseCategory = mappedResult.filter { it.type == TransactionCategoryType.Expense }
                val incomeCategory = mappedResult.filter { it.type == TransactionCategoryType.Income }
                expenseCategory to incomeCategory
            }
        )
    }
}

fun toTransactionCategory(entity: DataTransactionCategory): TransactionCategory {
    val (title, icon) = convertAsStringAndIcon(entity.id)
    return TransactionCategory(
        id = entity.id,
        icon = icon,
        type = entity.transactionCategoryType,
        name = title
    )
}

fun convertAsStringAndIcon(id: Int): Pair<Int, Int> {
    return when (id) {
        1 -> R.string.title_food_and_drink to R.drawable.ic_food_and_drink
        2 -> R.string.title_bills_and_utilities to R.drawable.ic_bills_and_utilities
        3 -> R.string.title_entertainment to R.drawable.ic_entertainment
        4 -> R.string.title_transportation to R.drawable.ic_transporation
        5 -> R.string.title_shopping to R.drawable.ic_shopping
        100 -> R.string.title_salary to R.drawable.ic_salary
        101 -> R.string.title_present to R.drawable.ic_present
        else -> 0 to 0
    }
}