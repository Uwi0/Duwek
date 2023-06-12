package com.kakapo.add_transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakapo.data.repository.base.TransactionRepository
import com.kakapo.model.transaction.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
): ViewModel() {

    val addTransactionState get() = _addTransactionState.asStateFlow()
    private val _addTransactionState: MutableStateFlow<AddTransactionState> =
        MutableStateFlow(AddTransactionState.Loading)

    val formTransactionState get() = _formTransactionState.asStateFlow()
    private val _formTransactionState = MutableStateFlow(FormUiState())

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            transactionRepository.addTransaction(transaction)
        }
    }

    fun saveExpense(expense: String) {
        _formTransactionState.update { it.copy(expense = expense) }
    }
}

data class FormUiState(
    val expense: String = "0"
)

sealed interface AddTransactionState {
    object Success : AddTransactionState
    object Loading : AddTransactionState
}