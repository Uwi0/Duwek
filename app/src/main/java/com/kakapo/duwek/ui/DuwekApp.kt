package com.kakapo.duwek.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.kakapo.designsystem.component.DuwekNavigationBar
import com.kakapo.designsystem.component.DuwekNavigationBarItem
import com.kakapo.duwek.navigation.DuwekNavHost
import com.kakapo.duwek.navigation.TopLevelDestination
import com.kakapo.transactions.TRANSACTION_NAVIGATION_ROUTE

@Composable
fun DuwekApp(
    appState: DuwekAppState = rememberDuwekAppState()
) {
    Scaffold(
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                DuwekNavHost(appState = appState)
            }
        },
        bottomBar = {
            DuwekBottomBar(
                destinations = appState.topLevelDestinations,
                onNavigateToDestination = appState::navigateToTopLevelDestination,
                currentDestination = appState.currentDestination
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            if (appState.currentDestination?.route == TRANSACTION_NAVIGATION_ROUTE){
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Transaction")
                }
            }
        }
    )
}

@Composable
private fun DuwekBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    DuwekNavigationBar(modifier = modifier) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            val icon = if (selected) destination.selectedIcon else destination.unSelectedIcon
            DuwekNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination.invoke(destination) },
                icon = { Icon(imageVector = icon, contentDescription = null) },
                label = { Text(text = stringResource(id = destination.textId)) },
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
