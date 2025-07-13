package com.example.anfield.repository

import com.example.anfield.data.Note
import com.example.anfield.data.NoteDao

// 🔹 Why this class exists:
// This class abstracts data access logic from the ViewModel.
// ViewModel will call repository methods instead of DAO directly.

class NoteRepository(private val noteDao: NoteDao) {

    // 🔹 These are just DAO method calls now.
    // In future, you can easily swap Room with Firebase, etc.
    suspend fun insert(note: Note) = noteDao.insert(note)

    suspend fun delete(note: Note) = noteDao.delete(note)

    suspend fun getAllNotes(): List<Note> = noteDao.getAllNotes()
}