package com.kakapo.budget.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kakapo.budget.BudgetRoute

const val BUDGET_NAVIGATION_ROUTE = "budget_route"

fun NavController.navigateToBudget(navOptions: NavOptions? = null){
    this.navigate(BUDGET_NAVIGATION_ROUTE, navOptions)
}


fun NavGraphBuilder.budgetScreen(){
    composable(BUDGET_NAVIGATION_ROUTE){
        BudgetRoute()
    }
}