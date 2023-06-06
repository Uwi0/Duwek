package com.kakapo.profile.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.kakapo.profile.ProfileRoute

const val PROFILE_NAVIGATION_ROUTE = "profile_route"

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    this.navigate(PROFILE_NAVIGATION_ROUTE, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileScreen(){
    composable(PROFILE_NAVIGATION_ROUTE){
        ProfileRoute()
    }
}