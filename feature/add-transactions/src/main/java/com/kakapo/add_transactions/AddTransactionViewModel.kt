package com.kakapo.add_transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakapo.data.repository.base.TransactionRepository
import com.kakapo.model.transaction.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
): ViewModel() {

    val uiState get() = _uiState.asStateFlow()
    private val _uiState: MutableStateFlow<AddTransactionState> = MutableStateFlow(AddTransactionState.Loading)


    fun addTransaction(transaction: Transaction){
        viewModelScope.launch {
            transactionRepository.addTransaction(transaction)
        }
    }
}

sealed interface AddTransactionState{
    object Success: AddTransactionState
    object Loading: AddTransactionState
}