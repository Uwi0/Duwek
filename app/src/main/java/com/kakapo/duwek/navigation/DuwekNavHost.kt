package com.kakapo.duwek.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.navOptions
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.kakapo.add_transactions.navigation.addTransactionScreen
import com.kakapo.add_transactions.navigation.navigateToAddTransaction
import com.kakapo.budget.navigation.budgetScreen
import com.kakapo.calculator.navigation.calculatorScreen
import com.kakapo.calculator.navigation.navigateToCalculator
import com.kakapo.designsystem.navigation_constants.TransactionArgument
import com.kakapo.duwek.ui.DuwekAppState
import com.kakapo.home.navigation.HOME_NAVIGATION_ROUTE
import com.kakapo.home.navigation.homeScreen
import com.kakapo.model.transaction.asJson
import com.kakapo.note.navigation.navigateToNoteScreen
import com.kakapo.note.navigation.noteScreen
import com.kakapo.profile.navigation.profileScreen
import com.kakapo.select_category.navigation.navigateToTransactionCategory
import com.kakapo.select_category.navigation.transactionCategoryScreen
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
        val transactionNavOptions = navOptions {
            launchSingleTop = true
            restoreState = true
        }
        homeScreen()
        transactionScreen(navigateToAddTransaction = { navController.navigateToAddTransaction() })
        budgetScreen()
        profileScreen()
        addTransactionScreen(
            onNavigateUp = { navController.popBackStack() },
            navigateToCalculatorScreen = {
                navController.navigateToCalculator(transactionNavOptions)
            },
            navigateToSelectCategoryScreen = {
                navController.navigateToTransactionCategory(transactionNavOptions)
            },
            navigateToCreateNote = {
                navController.navigateToNoteScreen(transactionNavOptions)
            }
        )
        calculatorScreen(
            onNavigateUp = { amount ->
                navController.previousBackStackEntry?.savedStateHandle?.set(
                    TransactionArgument.EXPENSE,
                    amount
                )
                navController.popBackStack()
            }
        )
        transactionCategoryScreen(
            onNavigateUp = { navController.popBackStack() },
            onNavigateUpWithValue = { category ->
                navController.previousBackStackEntry?.savedStateHandle?.set(
                    TransactionArgument.TRANSACTION_CATEGORY,
                    category.asJson()
                )
                navController.popBackStack()
            }
        )
        noteScreen(
            onNavigateUp = {
                navController.popBackStack()
            }, onNavigateUpWithValue = { note ->
                navController.previousBackStackEntry?.savedStateHandle?.set(
                    TransactionArgument.NOTE,
                    note
                )
                navController.popBackStack()
            }
        )
    }
}