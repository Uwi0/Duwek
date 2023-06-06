package com.kakapo.home.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.kakapo.home.HomeRoute

const val HOME_NAVIGATION_ROUTE = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null){
    this.navigate(HOME_NAVIGATION_ROUTE, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeScreen(){
    composable(HOME_NAVIGATION_ROUTE){
        HomeRoute()
    }
}