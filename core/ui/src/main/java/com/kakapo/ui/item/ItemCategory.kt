package com.kakapo.ui.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kakapo.model.transaction.TransactionCategory

@Composable
fun ItemCategory(category: TransactionCategory, onItemClicked: (TransactionCategory) -> Unit) {
    ListItem(
        modifier = Modifier.clickable { onItemClicked.invoke(category) },
        headlineContent = {
            Text(text = stringResource(id = category.name))
        },
        leadingContent = {
            Image(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape),
                painter = painterResource(id = category.icon),
                contentDescription = stringResource(id = category.name)
            )
        },
        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface)
    )
}