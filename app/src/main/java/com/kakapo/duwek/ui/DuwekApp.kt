package com.kakapo.duwek.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.kakapo.designsystem.component.DuwekNavigationBar
import com.kakapo.designsystem.component.DuwekNavigationBarItem
import com.kakapo.designsystem.component.DuwekNavigationRail
import com.kakapo.designsystem.component.DuwekNavigationRailItem
import com.kakapo.duwek.navigation.DuwekNavHost
import com.kakapo.duwek.navigation.TopLevelDestination

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DuwekApp(
    windowSizeClass: WindowSizeClass,
    appState: DuwekAppState = rememberDuwekAppState(windowSizeClass)
) {
    Scaffold(
        content = { padding ->
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal),),
            ) {
                if (appState.shouldShowNavRail) {
                    DuwekNavRail(
                        destinations = appState.topLevelDestinations,
                        onNavigateToDestination = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination,
                        modifier = Modifier.safeDrawingPadding()
                    )
                }
                DuwekNavHost(appState = appState)
            }
        },
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                DuwekBottomBar(
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentDestination
                )
            }
        },
        floatingActionButton = {
            if (appState.shouldShowFabButton) {
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Transaction")
                }
            }
        }
    )
}

@Composable
private fun DuwekNavRail(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    DuwekNavigationRail(modifier = modifier) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            val icon = if (selected) destination.selectedIcon else destination.unSelectedIcon
            DuwekNavigationRailItem(
                selected = selected,
                onClick = { onNavigateToDestination.invoke(destination) },
                icon = { Icon(imageVector = icon, contentDescription = null) },
                label = { Text(text = stringResource(id = destination.textId)) }
            )
        }
    }
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
