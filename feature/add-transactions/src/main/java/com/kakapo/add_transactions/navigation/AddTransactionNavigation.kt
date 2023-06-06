package com.kakapo.add_transactions.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.kakapo.add_transactions.AddTransactionRoute

const val ADD_TRANSACTION_NAVIGATION_ROUTE = "add_transaction_route"

val slideIn = slideInVertically(
    initialOffsetY = { fullHeight -> fullHeight },
    animationSpec = tween(durationMillis = 500)
)

fun NavController.navigateToAddTransaction(navOptions: NavOptions? = null) {
    this.navigate(ADD_TRANSACTION_NAVIGATION_ROUTE, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addTransactionScreen(onNavigateUp: () -> Unit, navigateToCalculatorScreen: () -> Unit) {
    composable(
        ADD_TRANSACTION_NAVIGATION_ROUTE,
        enterTransition = {
            slideIn
        }
    ) {
        AddTransactionRoute(onNavigateUp, navigateToCalculatorScreen)
    }
}