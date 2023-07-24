package com.kakapo.select_category.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.kakapo.select_category.TransactionCategoryRoute

const val SELECT_CATEGORY_NAVIGATION_ROUTE = "select_category_route"

fun NavController.navigateToTransactionCategory(navOptions: NavOptions? = null) {
    this.navigate(SELECT_CATEGORY_NAVIGATION_ROUTE, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.transactionCategoryScreen(){
    composable(SELECT_CATEGORY_NAVIGATION_ROUTE){
        TransactionCategoryRoute()
    }
}