package com.kakapo.budget.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.kakapo.budget.BudgetRoute

const val BUDGET_NAVIGATION_ROUTE = "budget_route"

fun NavController.navigateToBudget(navOptions: NavOptions? = null){
    this.navigate(BUDGET_NAVIGATION_ROUTE, navOptions)
}


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.budgetScreen(){
    composable(BUDGET_NAVIGATION_ROUTE){
        BudgetRoute()
    }
}