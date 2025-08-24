package com.example.anfield.data.repository

import androidx.lifecycle.LiveData
import com.example.anfield.data.local.NoteDao
import com.example.anfield.data.model.Note

// 🔹 Why this class exists:
// This class abstracts data access logic from the ViewModel.
// ViewModel will call repository methods instead of DAO directly.

class NoteRepository(private val noteDao: NoteDao) {

    // 🔹 These are just DAO method calls now.
    // In future, you can easily swap Room with Firebase, etc.
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun delete(note: Note) = noteDao.delete(note)


}