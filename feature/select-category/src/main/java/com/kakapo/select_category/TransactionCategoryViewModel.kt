package com.kakapo.select_category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakapo.common.result.asResult
import com.kakapo.common.result.subscribe
import com.kakapo.domain.transaction.TransactionCategoriesUseCase
import com.kakapo.model.transaction.TransactionCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionCategoryViewModel @Inject constructor(
    private val transactionCategoriesUseCase: TransactionCategoriesUseCase
) : ViewModel() {

    val uiState get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow(CategoryTransactionUiState())

    init {
        loadListTransactionCategory()
    }

    private fun loadListTransactionCategory() {
        viewModelScope.launch {
            transactionCategoriesUseCase.invoke().asResult().subscribe(
                onLoading = {
                    _uiState.update { it.copy(loading = true) }
                },
                onSuccess = { categories ->
                    val (expenseCategories, incomeCategories) = categories
                    _uiState.update {
                        it.copy(
                            loading = false,
                            expenseCategories = expenseCategories,
                            incomeCategories = incomeCategories
                        )
                    }
                },
                onError = {
                    _uiState.update { it.copy(loading = false) }
                }
            )
        }
    }
}

data class CategoryTransactionUiState(
    val loading: Boolean = false,
    val expenseCategories: List<TransactionCategory> = emptyList(),
    val incomeCategories: List<TransactionCategory> = emptyList()
)