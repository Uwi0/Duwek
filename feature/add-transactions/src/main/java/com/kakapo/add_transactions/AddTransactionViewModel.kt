package com.kakapo.add_transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakapo.data.repository.base.TransactionRepository
import com.kakapo.model.transaction.Transaction
import com.kakapo.model.transaction.TransactionCategory
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

    fun saveTransactionCategory(category: TransactionCategory){
        _formTransactionState.update { it.copy(transactionCategory = category) }
    }

    fun saveNote(note: String){
        _formTransactionState.update { it.copy(note = note) }
    }

    fun saveDate(date: Pair<String, Long>){
        val (convertedDate, dateTime) = date
        _formTransactionState.update { it.copy(date = convertedDate) }
    }
}

data class FormUiState(
    val expense: String = "0",
    val transactionCategory: TransactionCategory = TransactionCategory(),
    val note: String = "",
    val date: String = "Today"
)

sealed interface AddTransactionState {
    object Success : AddTransactionState
    object Loading : AddTransactionState
}