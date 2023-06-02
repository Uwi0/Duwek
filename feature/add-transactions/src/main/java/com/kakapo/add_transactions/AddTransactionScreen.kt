package com.kakapo.add_transactions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.rounded.Help
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AddTransactionScreen(viewModel: AddTransactionViewModel = hiltViewModel()) {
    AddTransactionContent()
}

@Composable
internal fun AddTransactionContent() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AddTransactionTopAppbar()
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
                .clickable { },
            text = "Rp 0",
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Start
        )
        TransactionMenu(
            icon = Icons.Rounded.Help,
            iconSize = 36.dp,
            text = stringResource(id = R.string.title_select_category),
            onclick = {}
        )
        TransactionMenu(
            icon = Icons.Default.Sort,
            text = stringResource(id = R.string.title_write_note),
            onclick = {}
        )
        TransactionMenu(
            icon = Icons.Default.Event,
            text = stringResource(id = R.string.default_day),
            onclick = {}
        )
        TransactionMenu(
            icon = Icons.Filled.AccountBalanceWallet,
            text = "Dompet",
            onclick = {}
        )
    }
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
private fun AddTransactionTopAppbar() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close add Transaction"
            )
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
