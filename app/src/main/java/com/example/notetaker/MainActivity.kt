package com.example.notetaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notetaker.data.NoteDataSource
import com.example.notetaker.model.Note
import com.example.notetaker.screen.NoteScreen
import com.example.notetaker.screen.NoteViewModel
import com.example.notetaker.ui.theme.NoteTakerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteTakerTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background){
                    val noteViewModdel: NoteViewModel by viewModels()
                    NotesApp(noteViewModel = noteViewModdel)


                }
            }
        }
    }
}

@Composable
fun NotesApp(noteViewModel: NoteViewModel = viewModel()){
    val notesList = noteViewModel.getAllNotes()
    NoteScreen(notes = notesList,
        onAddNote = {noteViewModel.addNote(it)},
        onRemoveNote = {noteViewModel.removeNote(it)})
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NoteTakerTheme {

    }
}