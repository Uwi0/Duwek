package com.kakapo.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kakapo.profile.ProfileRoute

const val PROFILE_NAVIGATION_ROUTE = "profile_route"

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    this.navigate(PROFILE_NAVIGATION_ROUTE, navOptions)
}

fun NavGraphBuilder.profileScreen(){
    composable(PROFILE_NAVIGATION_ROUTE){
        ProfileRoute()
    }
}