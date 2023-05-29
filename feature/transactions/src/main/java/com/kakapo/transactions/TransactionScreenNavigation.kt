package com.kakapo.transactions

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kakapo.TransactionRoute

const val TRANSACTION_NAVIGATION_ROUTE = "transaction_route"

fun NavController.navigateToTransaction(navOptions: NavOptions? = null){
    this.navigate(TRANSACTION_NAVIGATION_ROUTE, navOptions)
}

fun NavGraphBuilder.transactionScreen(){
    composable(TRANSACTION_NAVIGATION_ROUTE){
        TransactionRoute()
    }
}