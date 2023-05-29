package com.kakapo

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
internal fun TransactionRoute(){
    TransactionScreen()
}

@Composable
internal fun TransactionScreen(){
    Text(text = "Transaction Screen")
}