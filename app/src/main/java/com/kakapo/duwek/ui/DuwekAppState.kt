package com.kakapo.duwek.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.tracing.trace
import com.kakapo.budget.navigation.BUDGET_NAVIGATION_ROUTE
import com.kakapo.budget.navigation.navigateToBudget
import com.kakapo.duwek.navigation.TopLevelDestination
import com.kakapo.home.navigation.HOME_NAVIGATION_ROUTE
import com.kakapo.home.navigation.navigateToHome
import com.kakapo.profile.navigation.PROFILE_NAVIGATION_ROUTE
import com.kakapo.profile.navigation.navigateToProfile
import com.kakapo.transactions.TRANSACTION_NAVIGATION_ROUTE
import com.kakapo.transactions.navigateToTransaction
import com.kakapo.ui.TrackDisposableJank

@Composable
fun rememberDuwekAppState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController()
): DuwekAppState{
    NavigationTrackingSideEffect(navController)
    return remember(
        navController
    ){
        DuwekAppState(windowSizeClass, navController)
    }

}
class DuwekAppState(
    val windowSizeClass: WindowSizeClass,
    val navController: NavHostController
) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            HOME_NAVIGATION_ROUTE -> TopLevelDestination.Home
            TRANSACTION_NAVIGATION_ROUTE -> TopLevelDestination.Transaction
            BUDGET_NAVIGATION_ROUTE -> TopLevelDestination.Budget
            PROFILE_NAVIGATION_ROUTE -> TopLevelDestination.Profile
            else -> null
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    val shouldShowBottomBar: Boolean get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    val shouldShowNavRail: Boolean get() = !shouldShowBottomBar

    val shouldShowFabButton: Boolean @Composable get() = currentDestination?.route == TRANSACTION_NAVIGATION_ROUTE && shouldShowBottomBar

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation :${topLevelDestination.name}") {
            val topLevelDestinations = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

            when(topLevelDestination){
                TopLevelDestination.Home -> navController.navigateToHome(topLevelDestinations)
                TopLevelDestination.Transaction -> navController.navigateToTransaction(topLevelDestinations)
                TopLevelDestination.Budget -> navController.navigateToBudget(topLevelDestinations)
                TopLevelDestination.Profile -> navController.navigateToProfile(topLevelDestinations)
            }
        }
    }
}

@Composable
private fun NavigationTrackingSideEffect(navController: NavHostController) {
    TrackDisposableJank(navController) { metricsHolder ->
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            metricsHolder.state?.putState("Navigation", destination.route.toString())
        }

        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
}