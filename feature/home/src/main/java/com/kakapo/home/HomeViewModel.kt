package com.kakapo.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakapo.model.home.Wallet
import com.kakapo.model.home.fakeDataWallet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    val uiState get() = _uiState.asStateFlow()
    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)

    init {
        initHomeView()
    }

    private fun initHomeView(){
        viewModelScope.launch {
            _uiState.update {
                HomeUiState.Loading
            }
            delay(3_000)
            _uiState.update {
                HomeUiState.HomeData().copy(savings = "1000", wallet = fakeDataWallet()[1])
            }
        }
    }
}

sealed interface HomeUiState {
    object Loading: HomeUiState
    data class HomeData(
        val savings: String = "",
        val wallet: Wallet = Wallet()
    ): HomeUiState

}