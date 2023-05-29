package com.kakapo.budget

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
internal fun BudgetRoute(){
    BudgetScreen()
}

@Composable
internal fun BudgetScreen(){
    Text(text = "Budget Screen")
}