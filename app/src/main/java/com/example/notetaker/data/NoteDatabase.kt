package com.example.notetaker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notetaker.model.Note
import com.example.notetaker.util.DateConverter
import com.example.notetaker.util.UUIDConverter

@Database(entities = [Note::class], version= 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}