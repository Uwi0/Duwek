package com.kakapo.calculator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kakapo.calculator.model.ButtonDisplay
import com.kakapo.calculator.model.DeleteButtonInput
import com.kakapo.calculator.model.NumberButtonInput
import com.kakapo.calculator.model.OperatorButtonInput
import com.kakapo.calculator.model.SubmitButtonInput
import com.kakapo.calculator.model.listAnotherButton
import com.kakapo.calculator.model.listButton

@Composable
internal fun CalculatorRoute(
    navigateToAddTransaction: (String) -> Unit,
    viewModel: CalculatorViewModel = hiltViewModel()
) {
    val input by viewModel.displayText.collectAsStateWithLifecycle()
    val submitState by viewModel.submitAction.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = submitState){
        if (submitState is SubmitState.SubmitValue){
            navigateToAddTransaction.invoke(input)
        }
    }
    CalculatorScreen(
        input = input,
        submitState = submitState,
        onNavigateBackClick = { navigateToAddTransaction.invoke(input) },
        onUpdateText = viewModel::updateDisplayText,
        onButtonInputClick = viewModel::handleInput
    )
}

@Composable
internal fun CalculatorScreen(
    input: String,
    submitState: SubmitState,
    onNavigateBackClick: () -> Unit,
    onUpdateText: (String) -> Unit,
    onButtonInputClick: (ButtonDisplay) -> Unit
) {
    Scaffold(
        topBar = {
            CalculatorTopAppBar(onNavigateBackClick = onNavigateBackClick)
        },
        content = { padding ->
            CalculatorContent(input, submitState, onUpdateText, onButtonInputClick, padding)
        },
    )
}

@Composable
private fun CalculatorContent(
    input: String,
    submitState: SubmitState,
    onUpdateText: (String) -> Unit,
    onButtonClick: (ButtonDisplay) -> Unit,
    padding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        InputTextField(input = input, onUpdateText = onUpdateText)
        ButtonRow(onButtonClick, submitState)
    }
}

@Composable
private fun InputTextField(input: String, onUpdateText: (String) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "RP")
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
        }
        TextField(
            value = input,
            onValueChange = onUpdateText,
            modifier = Modifier.fillMaxWidth(),
            enabled = false,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun CalculatorTopAppBar(onNavigateBackClick: () -> Unit) {
    TopAppBar(
        modifier = Modifier.padding(horizontal = 16.dp),
        title = {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.title_calculator_screen)
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigateBackClick) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = ""
                )
            }
        }
    )
}

@Composable
fun ButtonRow(onButtonClick: (ButtonDisplay) -> Unit, buttonState: SubmitState) {
    Column {
        listButton().forEach { buttonColumn ->
            Row(Modifier.fillMaxWidth()) {
                buttonColumn.forEach {
                    ButtonCalculator(
                        buttonType = it,
                        modifier = Modifier.weight(1f),
                        onButtonClick = onButtonClick
                    ) {
                        if (it is OperatorButtonInput || it is NumberButtonInput) {
                            Text(text = it.displayText)
                        } else if (it is DeleteButtonInput) {
                            Icon(imageVector = it.icon, contentDescription = it.displayText)
                        }
                    }
                }
            }
        }
        Row {
            listAnotherButton().forEachIndexed { index, pair ->
                if (index != 3) {
                    Column(Modifier.weight(1f)) {
                        val modifier = Modifier.fillMaxWidth()
                        pair.toList().forEach {
                            ButtonCalculator(
                                modifier = modifier,
                                onButtonClick = onButtonClick,
                                content = {
                                    Text(text = it.displayText)
                                },
                                buttonType = it
                            )
                        }
                    }
                } else {
                    val buttonType = SubmitButtonInput(Icons.Default.ArrowForwardIos, "=")
                    ButtonCalculator(
                        buttonType = buttonType,
                        modifier = Modifier.weight(1f),
                        height = 96.dp,
                        onButtonClick = onButtonClick,
                        content = {
                            if (buttonState is SubmitState.Calculate) {
                                Text(text = buttonType.displayText)
                            } else {
                                Icon(
                                    imageVector = buttonType.icon,
                                    contentDescription = buttonType.displayText
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ButtonCalculator(
    buttonType: ButtonDisplay,
    modifier: Modifier = Modifier,
    height: Dp = 48.dp,
    onButtonClick: (ButtonDisplay) -> Unit,
    content: @Composable () -> Unit
) {
    Button(
        onClick = { onButtonClick.invoke(buttonType) },
        modifier = modifier
            .height(height),
        shape = RectangleShape,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        content.invoke()
    }
}