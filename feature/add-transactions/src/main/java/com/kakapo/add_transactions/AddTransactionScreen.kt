package com.kakapo.add_transactions

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.rounded.Help
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kakapo.designsystem.component.ConfirmDateDatePickerDialog
import com.kakapo.model.transaction.TransactionCategory
import com.kakapo.ui.item.ItemCategory
import java.util.Calendar
import java.util.Date

@Composable
fun AddTransactionRoute(
    expense: String,
    category: TransactionCategory,
    note: String,
    onClosedScreen: () -> Unit,
    navigateToCalculator: () -> Unit,
    navigateToSelectCategory: () -> Unit,
    navigateToCreateNote: () -> Unit,
    viewModel: AddTransactionViewModel = hiltViewModel()
) {
    val formState by viewModel.formTransactionState.collectAsStateWithLifecycle()
    viewModel.saveExpense(expense)
    viewModel.saveTransactionCategory(category)
    viewModel.saveNote(note)
    AddTransactionScreen(
        uiState = formState,
        onClosedScreen = onClosedScreen,
        navigateToCalculator = { navigateToCalculator.invoke() },
        navigateToSelectCategory = navigateToSelectCategory,
        navigateToCreateNote = navigateToCreateNote,
        onSelectedDate = viewModel::saveDate
    )
}

@Composable
internal fun AddTransactionScreen(
    uiState: FormUiState,
    onClosedScreen: () -> Unit,
    navigateToCalculator: () -> Unit,
    navigateToSelectCategory: () -> Unit,
    navigateToCreateNote: () -> Unit,
    onSelectedDate: (Pair<String, Long>) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            AddTransactionTopAppbar(onClosedScreen)
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                Card(
                    modifier = Modifier.padding(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp)
                                .clickable { navigateToCalculator.invoke() },
                            text = "Rp ${uiState.expense}",
                            style = MaterialTheme.typography.displayMedium,
                            textAlign = TextAlign.Start
                        )
                        TransactionCategoryMenu(
                            uiState.transactionCategory,
                            navigateToSelectCategory
                        )
                        TransactionNoteMenu(
                            note = uiState.note,
                            navigateToCreateNote = navigateToCreateNote
                        )
                        TransactionSelectDateMenu(
                            onSelectedDate = onSelectedDate,
                            date = uiState.date
                        )
                        TransactionMenu(
                            icon = Icons.Filled.AccountBalanceWallet,
                            text = "Dompet",
                            onclick = {}
                        )
                    }
                }
            }
        }
    )
}

@Composable
private fun TransactionCategoryMenu(
    category: TransactionCategory,
    navigateToSelectCategory: () -> Unit
) {
    if (category.id == 0) {
        TransactionMenu(
            icon = Icons.Rounded.Help,
            iconSize = 36.dp,
            text = stringResource(id = R.string.title_select_category),
            onclick = navigateToSelectCategory
        )
    } else {
        ItemCategory(
            category = category,
            onItemClicked = { navigateToSelectCategory.invoke() }
        )
    }
}

@Composable
private fun TransactionNoteMenu(
    note: String,
    navigateToCreateNote: () -> Unit
) {
    val text = if (note == "") stringResource(id = R.string.title_write_note) else note
    TransactionMenu(
        icon = Icons.Default.Sort,
        text = text,
        onclick = { navigateToCreateNote.invoke() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TransactionSelectDateMenu(
    date: String,
    onSelectedDate: (Pair<String, Long>) -> Unit
) {
    var isVisible by rememberSaveable { mutableStateOf(false) }
    val dateDialogState = rememberDatePickerState()
    TransactionMenu(
        icon = Icons.Default.Event,
        text = date,
        onclick = {
            isVisible = !isVisible
        }
    )
    if (isVisible) {
        ConfirmDateDatePickerDialog(
            state = dateDialogState,
            onDismiss = { isVisible = !isVisible },
            onConfirm = { selectedDate ->
                onSelectedDate.invoke(selectedDate)
                isVisible = !isVisible
            }
        )
    }

}

@Composable
private fun TransactionDateMenu(
    onSelectedDate: (String) -> Unit
) {
    val date = remember { mutableStateOf("") }
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
    calendar.time = Date()
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, dateYear: Int, dateMonth: Int, dateDayOfMonth: Int ->
            date.value = "$dateYear-$dateMonth-$dateDayOfMonth"
            onSelectedDate.invoke(date.value)
        },
        year,
        month,
        dayOfMonth
    )
    val text = if (date.value == "") stringResource(id = R.string.default_day) else date.value
    TransactionMenu(
        icon = Icons.Default.Event,
        text = text,
        onclick = {
            datePickerDialog.show()
        }
    )
}

@Composable
private fun TransactionMenu(
    icon: ImageVector,
    iconSize: Dp = 24.dp,
    text: String,
    onclick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 36.dp)
            .padding(horizontal = 16.dp)
            .clickable { onclick.invoke() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.defaultMinSize(36.dp)) {
            Icon(
                imageVector = icon, contentDescription = "", modifier = Modifier
                    .size(iconSize)
                    .align(Alignment.Center)
            )
        }
        Text(modifier = Modifier.padding(start = 16.dp), text = text)
    }
}

@Composable
private fun AddTransactionTopAppbar(onClosedScreen: () -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onClosedScreen.invoke() }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close add Transaction"
                )
            }
            Text(
                text = stringResource(id = R.string.title_add_transaction),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            )
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = stringResource(id = R.string.btn_title_save))
            }
        }
        Divider()
    }
}
