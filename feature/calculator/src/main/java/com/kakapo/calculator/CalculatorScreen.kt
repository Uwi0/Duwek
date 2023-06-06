package com.kakapo.calculator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kakapo.calculator.model.ButtonDisplay
import com.kakapo.calculator.model.DeleteButtonInput
import com.kakapo.calculator.model.NumberButtonInput
import com.kakapo.calculator.model.OperatorButtonInput
import com.kakapo.calculator.model.SubmitButtonInput
import com.kakapo.calculator.model.listAnotherButton
import com.kakapo.calculator.model.listButton

@Composable
internal fun CalculatorRoute() {
    CalculatorScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CalculatorScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = stringResource(id = R.string.title_calculator_screen)
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = ""
                    )
                }
            )
        },
        content = { padding ->
            var input by remember { mutableStateOf("") }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "RP")
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
                    }
                    TextField(
                        value = input,
                        onValueChange = { input = it },
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
                ButtonRow(onButtonClick = {})
            }
        },
    )
}

@Composable
fun ButtonRow(onButtonClick: () -> Unit) {
    Column {
        listButton().forEach { buttonColumn ->
            Row(Modifier.fillMaxWidth()) {
                buttonColumn.forEach {
                    ButtonCalculator(buttonType = it,modifier = Modifier.weight(1f), onButtonClick = { }) {
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
                        ButtonCalculator(modifier = modifier, onButtonClick = { /*TODO*/ }, content = {
                            Text(text = pair.first.displayText)
                        }, buttonType = pair.first)
                        ButtonCalculator(modifier = modifier, onButtonClick = { /*TODO*/ }, content = {
                            Text(text = pair.second.displayText)
                        }, buttonType = pair.second)
                    }
                } else {
                    ButtonCalculator(
                        buttonType = SubmitButtonInput(Icons.Default.ArrowForwardIos, "="),
                        modifier = Modifier.weight(1f),
                        height = 96.dp,
                        onButtonClick = { /*TODO*/ },
                        {
                            Text(text = "Button")
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
    onButtonClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(
        onClick = { onButtonClick.invoke() },
        modifier = modifier
            .height(height),
        shape = RectangleShape,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        content.invoke()
    }
}


