package com.kakapo.calculator

import androidx.lifecycle.ViewModel
import com.kakapo.calculator.model.CalculatorInput
import com.kakapo.calculator.model.DeleteButtonInput
import com.kakapo.calculator.model.NumberButtonInput
import com.kakapo.calculator.model.Operator
import com.kakapo.calculator.model.OperatorButtonInput
import com.kakapo.calculator.model.SubmitButtonInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor() : ViewModel() {
    val displayText get() = _displayText.asStateFlow()
    private val _displayText = MutableStateFlow("0")
    val submitAction get() = _submitAction.asStateFlow()
    private val _submitAction = MutableStateFlow<SubmitState>(SubmitState.Initialize)

    fun updateDisplayText(text: String) {
        _displayText.update { text }
    }

    fun handleInput(input: CalculatorInput) {
        when (input) {
            is NumberButtonInput -> appendToDisplay(input.displayText)
            is OperatorButtonInput -> applyOperator(input.operator)
            is DeleteButtonInput -> deleteLastInput()
            is SubmitButtonInput -> onSubmitClick()
        }
    }

    private fun appendToDisplay(text: String) {
        _displayText.update { value ->
            if (value == "0") {
                text
            } else {
                value + text
            }
        }
    }

    private fun applyOperator(operator: Operator) {
        _submitAction.update { SubmitState.Calculate }
        when (operator) {
            Operator.CLEAR -> {
                _displayText.update { "0" }
            }

            Operator.PLUS -> {
                _displayText.update { it + operator.symbols }
            }

            Operator.MINUS -> {
                _displayText.update { it + operator.symbols }
            }

            Operator.MULTIPLY -> {
                _displayText.update { it + operator.symbols }
            }

            Operator.DIVIDE -> {
                _displayText.update { it + operator.symbols }
            }
        }
    }

    private fun deleteLastInput() {
        if (displayText.value.length == 1)
            _displayText.update { "0" }
        else
            _displayText.update { it.dropLast(1) }
    }

    private fun onSubmitClick() {
        val character = displayText.value
        if (character.any { isEqualsWithOperator(it) }){
            calculateValue()
        }else{
            _submitAction.update { SubmitState.SubmitValue }
        }
    }

    private fun calculateValue() {
        var currentOperator = '+'
        var operand = 0.0
        var result = 0.0

        for (i in displayText.value.indices) {
            val c = displayText.value[i]

            if (c in '0'..'9' || c == '.') {
                operand = (operand * 10) + (c - '0')
            } else if (isEqualsWithOperator(c)) {
                result = performOperation(result, operand, currentOperator)
                currentOperator = c
                operand = 0.0
            }
        }
        result = performOperation(result, operand, currentOperator)
        _displayText.update { result.toInt().toString() }
        _submitAction.update { SubmitState.Initialize }
    }

    private fun isEqualsWithOperator(value: Char) =  value == '+' || value == '-' || value == '*' || value == '/'

    private fun performOperation(num1: Double, num2: Double, operator: Char): Double {
        return when (operator) {
            '+' -> num1 + num2
            '-' -> num1 - num2
            '*' -> num1 * num2
            '/' -> num1 / num2
            else -> throw IllegalArgumentException("Invalid operator")
        }
    }
}

sealed interface SubmitState {
    object Calculate : SubmitState
    object SubmitValue : SubmitState
    object Initialize: SubmitState
}