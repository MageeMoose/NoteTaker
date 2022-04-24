package com.example.notetaker.data

import com.example.notetaker.model.Note

class NoteDataSource{
    fun loadNotes(): List<Note>{
        return listOf(
            Note(title = "Test", description = "I am testing"),
            Note(title = "This is..", description = "Yaaaaay" )
        )
    }
}