package com.kakapo.calculator.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.ui.graphics.vector.ImageVector

sealed interface CalculatorInput

sealed interface OperatorInput : CalculatorInput {
    val operator: Operator
}

sealed class ButtonDisplay : CalculatorInput {
    abstract val displayText: String
}

enum class Operator(val symbols: String) {
    CLEAR("C"), PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/")
}

data class NumberButtonInput(override val displayText: String) : ButtonDisplay()

data class OperatorButtonInput(
    override val operator: Operator,
    override val displayText: String = operator.symbols
) : ButtonDisplay(), OperatorInput

data class DeleteButtonInput(val icon: ImageVector, override val displayText: String) : ButtonDisplay()

data class SubmitButtonInput(val icon: ImageVector,override val displayText: String): ButtonDisplay()

fun listButton() = listOf(
    listOf(
        OperatorButtonInput(Operator.CLEAR),
        OperatorButtonInput(Operator.DIVIDE),
        OperatorButtonInput(Operator.MULTIPLY),
        DeleteButtonInput(icon = Icons.Default.Backspace, displayText = "Delete")
    ),
    listOf(
        NumberButtonInput("7"),
        NumberButtonInput("8"),
        NumberButtonInput("9"),
        OperatorButtonInput(Operator.MINUS)
    ),
    listOf(
        NumberButtonInput("4"),
        NumberButtonInput("5"),
        NumberButtonInput("6"),
        OperatorButtonInput(Operator.PLUS)
    )
)

fun listAnotherButton() = listOf(
    Pair(NumberButtonInput("1"), NumberButtonInput("0")),
    Pair(NumberButtonInput("2"), NumberButtonInput("000")),
    Pair(NumberButtonInput("3"), NumberButtonInput(".")),
    Pair(NumberButtonInput(""), NumberButtonInput(""))
)