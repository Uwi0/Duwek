package com.kakapo.calculator

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kakapo.calculator.model.CalculatorInput
import com.kakapo.calculator.model.DeleteButtonInput
import com.kakapo.calculator.model.NumberButtonInput
import com.kakapo.calculator.model.Operator
import com.kakapo.calculator.model.OperatorButtonInput
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor() : ViewModel() {
    private val _displayText = mutableStateOf("")
    val displayText: State<String> = _displayText

    fun handleInput(input: CalculatorInput) {
        when (input) {
            is NumberButtonInput -> appendToDisplay(input.displayText)
            is OperatorButtonInput -> applyOperator(input.operator)
            is DeleteButtonInput -> deleteLastInput()
        }
    }

    private fun appendToDisplay(text: String) {
        _displayText.value += text
    }

    private fun applyOperator(operator: Operator) {
        // Handle the operator logic
    }

    private fun deleteLastInput() {
        _displayText.value = _displayText.value.dropLast(1)
    }
}