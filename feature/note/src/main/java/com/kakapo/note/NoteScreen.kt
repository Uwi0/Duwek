package com.kakapo.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kakapo.designsystem.component.MenuScreenTopAppBar

@Composable
internal fun NoteRoute(onNavigateUp: () -> Unit, onNavigateUpWithValue: (String) -> Unit) {
    NoteScreen(onNavigateUp, onNavigateUpWithValue)
}

@Composable
internal fun NoteScreen(onNavigateUp: () -> Unit, onNavigateUpWithValue: (String) -> Unit) {
    var note by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            MenuScreenTopAppBar(
                title = R.string.title_note_screen,
                actionIcon = Icons.Default.Check,
                navigateUp = { onNavigateUp.invoke() },
                action = { onNavigateUpWithValue.invoke(note) }
            )
        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                TextField(
                    modifier = Modifier.fillMaxSize(),
                    value = note,
                    onValueChange = { text -> note = text },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                    )
                )
            }
        }
    )
}