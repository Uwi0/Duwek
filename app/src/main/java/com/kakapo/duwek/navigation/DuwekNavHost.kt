package com.kakapo.duwek.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.kakapo.budget.navigation.budgetScreen
import com.kakapo.duwek.ui.DuwekAppState
import com.kakapo.home.navigation.HOME_NAVIGATION_ROUTE
import com.kakapo.home.navigation.homeScreen
import com.kakapo.profile.navigation.profileScreen
import com.kakapo.transactions.transactionScreen

@Composable
internal fun DuwekNavHost(
    appState: DuwekAppState,
    modifier: Modifier = Modifier,
    starDestination: String = HOME_NAVIGATION_ROUTE
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = starDestination,
        modifier = modifier
    ) {
        homeScreen()
        transactionScreen()
        budgetScreen()
        profileScreen()
    }
}