package com.kakapo.designsystem.component

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kakapo.common.utils.toCurrentDate
import com.kakapo.designsystem.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmDateDatePickerDialog(
    state: DatePickerState,
    onDismiss: () -> Unit,
    onConfirm: (Pair<String, Long>) -> Unit,
){
    DatePickerDialog(
        onDismissRequest = { onDismiss.invoke() },
        confirmButton = {
            TextButton(
                onClick = {
                    val selectedDate = state.selectedDateMillis ?: 0L
                    val convertedSelectedDate = selectedDate.toCurrentDate()
                    onConfirm.invoke(convertedSelectedDate to selectedDate)
                }
            ) {
                Text(text = stringResource(id = R.string.title_confirm))
            }
        }
    ) {
        DatePicker(state = state)
    }
}