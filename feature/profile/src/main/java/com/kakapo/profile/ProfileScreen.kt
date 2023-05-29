package com.kakapo.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
internal fun ProfileRoute(){
    ProfileScreen()
}

@Composable
internal fun ProfileScreen(){
    Text(text = "Profile screen")
}