package com.kakapo.designsystem.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.ui.graphics.Color

@Composable
fun DuwekNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
){
    NavigationBar(
        modifier = modifier,
        contentColor = DuwekNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
        content = content
    )
}

@Composable
fun RowScope.DuwekNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = DuwekNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = DuwekNavigationDefaults.navigationContentColor(),
            selectedTextColor = DuwekNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = DuwekNavigationDefaults.navigationContentColor(),
            indicatorColor = DuwekNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

@Composable
fun DuwekNavigationRail(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
){
    NavigationRail(
        modifier = modifier,
        header = header,
        content = content,
        containerColor = Color.Transparent,
        contentColor = DuwekNavigationDefaults.navigationContentColor()
    )
}

@Composable
fun DuwekNavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
) {
    NavigationRailItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationRailItemDefaults.colors(
            selectedIconColor = DuwekNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = DuwekNavigationDefaults.navigationContentColor(),
            selectedTextColor = DuwekNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = DuwekNavigationDefaults.navigationContentColor(),
            indicatorColor = DuwekNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

object DuwekNavigationDefaults {

    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}