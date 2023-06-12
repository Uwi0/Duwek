package com.kakapo.calculator.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import com.kakapo.calculator.CalculatorRoute

const val CALCULATOR_NAVIGATION_ROUTE = "calculator_route"

fun NavController.navigateToCalculator(navOptions: NavOptions? = null) {
    this.navigate(CALCULATOR_NAVIGATION_ROUTE, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.calculatorScree(onNavigateUp: (String) -> Unit) {
    composable(
        CALCULATOR_NAVIGATION_ROUTE
    ) {
        CalculatorRoute(navigateToAddTransaction = onNavigateUp)
    }
}