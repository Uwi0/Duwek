package com.kakapo.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
internal fun HomeRoute(){
    HomeScreen()
}

@Composable
private fun HomeScreen(){
    Text(text = "home screen")
}