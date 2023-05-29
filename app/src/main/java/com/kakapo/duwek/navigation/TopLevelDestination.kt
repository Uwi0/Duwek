package com.kakapo.duwek.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Savings
import androidx.compose.ui.graphics.vector.ImageVector
import com.kakapo.home.R as homeR
import com.kakapo.transactions.R as transactionR
import com.kakapo.budget.R as budgetR
import com.kakapo.profile.R as profileR

enum class TopLevelDestination(
    val textId: Int,
    val unSelectedIcon: ImageVector,
    val selectedIcon: ImageVector
) {
    Home(homeR.string.home, Icons.Outlined.Home, Icons.Filled.Home),
    Transaction(transactionR.string.transaction, Icons.Outlined.AccountBalanceWallet, Icons.Filled.AccountBalanceWallet),
    Budget(budgetR.string.budget, Icons.Outlined.Savings, Icons.Filled.Savings),
    Profile(profileR.string.profile, Icons.Outlined.AccountCircle, Icons.Filled.AccountCircle)
}