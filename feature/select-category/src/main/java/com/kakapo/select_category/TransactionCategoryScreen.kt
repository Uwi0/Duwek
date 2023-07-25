package com.kakapo.select_category

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kakapo.designsystem.component.CustomTab
import com.kakapo.designsystem.component.CustomTabRow
import com.kakapo.designsystem.component.MenuScreenTopAppBar
import com.kakapo.model.transaction.TransactionCategory
import com.kakapo.ui.item.ItemCategory

@Composable
internal fun TransactionCategoryRoute(
    viewModel: TransactionCategoryViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit,
    onNavigateUpWithValue: (TransactionCategory) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TransactionCategoryScreen(
        uiState = uiState,
        navigateUp = onNavigateUp,
        onIconSearchCategoryClicked = {},
        onItemClicked = onNavigateUpWithValue
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TransactionCategoryScreen(
    uiState: CategoryTransactionUiState,
    navigateUp: () -> Unit,
    onIconSearchCategoryClicked: () -> Unit,
    onItemClicked: (TransactionCategory) -> Unit
) {
    Scaffold(
        topBar = {
            MenuScreenTopAppBar(
                title = R.string.title_screen_select_category,
                navigateUp = { navigateUp.invoke() },
                action = { onIconSearchCategoryClicked.invoke() }
            )
        },
        content = { paddingValues ->
            TransactionCategoryContent(paddingValues, uiState, onItemClicked)
        }
    )
}

@Composable
private fun TransactionCategoryContent(
    paddingValues: PaddingValues,
    uiState: CategoryTransactionUiState,
    onItemClicked: (TransactionCategory) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        var currentTab by remember { mutableStateOf(Category.Expense) }
        CustomTabRow(selectedTabIndex = currentTab.ordinal) {
            Category.values().forEach { tab ->
                val selected = tab == currentTab
                CustomTab(
                    selected = selected,
                    onClick = {
                        currentTab = tab
                    }
                ) {
                    Text(
                        text = stringResource(id = tab.title),
                    )
                }
            }
        }
        AnimatedContent(
            targetState = currentTab,
            label = "AnimateSelectCategoryScreen"
        ) { tab ->
            when (tab) {
                Category.Expense -> {
                    ListViewItemCategory(
                        transactionCategories = uiState.expenseCategories,
                        onItemClicked
                    )
                }

                Category.Income -> {
                    ListViewItemCategory(
                        transactionCategories = uiState.incomeCategories,
                        onItemClicked
                    )
                }
            }
        }
    }
}

@Composable
private fun ListViewItemCategory(
    transactionCategories: List<TransactionCategory>,
    onItemClicked: (TransactionCategory) -> Unit
) {
    LazyColumn(
        content = {
            items(transactionCategories) { category ->
                ItemCategory(category, onItemClicked)
            }
        }
    )
}


enum class Category(val title: Int) {
    Expense(R.string.title_expense),
    Income(R.string.title_income)
}