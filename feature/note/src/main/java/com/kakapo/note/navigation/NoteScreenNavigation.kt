package com.kakapo.note.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.kakapo.note.NoteRoute

const val NOTE_NAVIGATION_ROUTE = "note_navigation_route"

fun NavController.navigateToNoteScreen(navOptions: NavOptions? = null){
    this.navigate(NOTE_NAVIGATION_ROUTE, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.noteScreen(onNavigateUp: () -> Unit, onNavigateUpWithValue: (String) -> Unit){
    composable(NOTE_NAVIGATION_ROUTE){
        NoteRoute(onNavigateUp, onNavigateUpWithValue)
    }
}