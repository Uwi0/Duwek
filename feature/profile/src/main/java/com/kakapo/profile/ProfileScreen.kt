package com.kakapo.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
internal fun ProfileRoute(){
    ProfileScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProfileScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text(text = stringResource(id = R.string.profile)) })
        AccountSection()
    }
}

@Composable
private fun AccountSection() {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = stringResource(id = R.string.account))
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .size(62.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.nanashi_mumei_asset),
                contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "Person")
                Text(text = "person@gmail.com")
            }
            Icon(imageVector = Icons.Default.Edit, contentDescription = "")
        }
    }
}