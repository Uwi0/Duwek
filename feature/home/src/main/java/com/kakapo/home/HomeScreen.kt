package com.kakapo.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.Help
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun HomeRoute(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(uiState = uiState)
}

@Composable
private fun HomeScreen(uiState: HomeUiState) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            HomeTopAppBar(modifier = Modifier.fillMaxWidth(), uiState = uiState)
        },
        content = { paddingValue ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValue)
            ) {
                when (uiState) {
                    is HomeUiState.HomeData -> {
                        HomeContent(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            data = uiState
                        )
                    }

                    HomeUiState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(modifier: Modifier = Modifier, uiState: HomeUiState) {
    val topAppBarText = when (uiState) {
        is HomeUiState.HomeData -> "Rp ${uiState.savings}"
        HomeUiState.Loading -> "Rp - "
    }
    TopAppBar(
        modifier = modifier,
        title = {
            TopAppBarTitle(topAppBarText)
        },
        actions = {
            Icon(imageVector = Icons.Default.Notifications, contentDescription = "")
        }
    )
}

@Composable
private fun TopAppBarTitle(topAppBarText: String) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = topAppBarText, style = MaterialTheme.typography.titleLarge)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.subtitle_balance),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline
            )
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Rounded.Help,
                contentDescription = stringResource(id = R.string.subtitle_balance),
                tint = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Composable
private fun HomeContent(modifier: Modifier = Modifier, data: HomeUiState.HomeData) {
    Column(modifier = modifier) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                ContentCardRow {
                    Text(
                        text = stringResource(id = R.string.title_list_wallet),
                        style = MaterialTheme.typography.titleMedium
                    )
                    TextButton(
                        onClick = { /*TODO*/ }) {
                        Text(
                            text = stringResource(id = R.string.btn_see_all),
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                Divider()
                ListItem(headlineContent = { /*TODO*/ })
            }
        }
    }
}

@Composable
private fun ContentCardRow(content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content.invoke(this)
    }
}