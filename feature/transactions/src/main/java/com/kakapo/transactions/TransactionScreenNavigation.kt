package com.kakapo.transactions

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.kakapo.TransactionRoute

const val TRANSACTION_NAVIGATION_ROUTE = "transaction_route"

fun NavController.navigateToTransaction(navOptions: NavOptions? = null){
    this.navigate(TRANSACTION_NAVIGATION_ROUTE, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.transactionScreen(navigateToAddTransaction: () -> Unit){
    composable(TRANSACTION_NAVIGATION_ROUTE){
        TransactionRoute(navigateToAddTransaction)
    }
}