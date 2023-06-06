package com.kakapo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kakapo.transactions.R

@Composable
internal fun TransactionRoute(navigateToAddTransaction: () -> Unit){
    TransactionScreen(addTransaction = navigateToAddTransaction)
}

@Composable
internal fun TransactionScreen(addTransaction: () -> Unit)   {
    Scaffold(
        topBar = {
            TransactionTopAppbar()
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {

            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { addTransaction.invoke() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    )
}


@Composable
private fun TransactionTopAppbar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = stringResource(id = R.string.title_balance))
            Text(text = "Rp 27,000")
            Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.la_plus_image_asset),
                        contentDescription = ""
                    )
                    Text(text = "My Wallet", modifier = Modifier.padding(horizontal = 8.dp))
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
                }
            }
        }
        Row(
            modifier = Modifier.align(Alignment.TopEnd),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(imageVector = Icons.Outlined.Search, contentDescription = "")
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
        }
    }
}
