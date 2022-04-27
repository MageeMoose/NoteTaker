package com.example.notetaker.screen

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notetaker.data.NoteDataSource
import com.example.notetaker.model.Note
import com.example.notetaker.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel @Inject constructor(private val repository: NoteRepository): ViewModel(){
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()
    //private var noteList = mutableStateListOf<Note>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect {listOfNotes ->
                    if (listOfNotes.isNullOrEmpty()){
                        Log.d("Empty", ":Empty List ")
                    }else{
                        _noteList.value = listOfNotes
                    }
                }
        }
        //noteList.addAll(NoteDataSource().loadNotes())
    }

    fun addNote(note: Note) = viewModelScope.launch{repository.addNote(note)}
    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
    fun  removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }


}