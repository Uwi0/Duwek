package com.kakapo.duwek.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.navOptions
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.kakapo.add_transactions.navigation.addTransactionScreen
import com.kakapo.add_transactions.navigation.navigateToAddTransaction
import com.kakapo.budget.navigation.budgetScreen
import com.kakapo.calculator.navigation.calculatorScree
import com.kakapo.calculator.navigation.navigateToCalculator
import com.kakapo.duwek.ui.DuwekAppState
import com.kakapo.home.navigation.HOME_NAVIGATION_ROUTE
import com.kakapo.home.navigation.homeScreen
import com.kakapo.profile.navigation.profileScreen
import com.kakapo.transactions.transactionScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun DuwekNavHost(
    appState: DuwekAppState,
    modifier: Modifier = Modifier,
    starDestination: String = HOME_NAVIGATION_ROUTE
) {
    val navController = appState.navController
    AnimatedNavHost(
        navController = navController,
        startDestination = starDestination,
        modifier = modifier
    ) {
        homeScreen()
        transactionScreen(navigateToAddTransaction = { navController.navigateToAddTransaction() })
        budgetScreen()
        profileScreen()
        addTransactionScreen(
            onNavigateUp = { navController.popBackStack() },
            navigateToCalculatorScreen = {
                navController.navigateToCalculator()
            }
        )
        calculatorScree(
            onNavigateUp = { amount ->
                navController.previousBackStackEntry?.savedStateHandle?.set("expense", amount)
                navController.navigateUp()
            }
        )
    }
}