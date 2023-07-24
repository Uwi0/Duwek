package com.kakapo.duwek.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.navOptions
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.kakapo.add_transactions.navigation.addTransactionScreen
import com.kakapo.add_transactions.navigation.navigateToAddTransaction
import com.kakapo.budget.navigation.budgetScreen
import com.kakapo.calculator.navigation.calculatorScree
import com.kakapo.calculator.navigation.navigateToCalculator
import com.kakapo.designsystem.navigation_constants.TransactionArgument
import com.kakapo.duwek.ui.DuwekAppState
import com.kakapo.home.navigation.homeScreen
import com.kakapo.profile.navigation.profileScreen
import com.kakapo.select_category.navigation.SELECT_CATEGORY_NAVIGATION_ROUTE
import com.kakapo.select_category.navigation.navigateToTransactionCategory
import com.kakapo.select_category.navigation.transactionCategoryScreen
import com.kakapo.transactions.transactionScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun DuwekNavHost(
    appState: DuwekAppState,
    modifier: Modifier = Modifier,
    starDestination: String = SELECT_CATEGORY_NAVIGATION_ROUTE
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
        val transactionNavOptions = navOptions {
            launchSingleTop = true
            restoreState = true
        }
        addTransactionScreen(
            onNavigateUp = { navController.popBackStack() },
            navigateToCalculatorScreen = {
                navController.navigateToCalculator(transactionNavOptions)
            },
            navigateToSelectCategoryScreen = {
                navController.navigateToTransactionCategory(transactionNavOptions)
            }
        )
        calculatorScree(
            onNavigateUp = { amount ->
                navController.previousBackStackEntry?.savedStateHandle?.set(
                    TransactionArgument.EXPENSE,
                    amount
                )
                navController.navigateUp()
            }
        )
        transactionCategoryScreen()
    }
}