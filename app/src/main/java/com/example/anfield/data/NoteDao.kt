package com.example.anfield.data
import androidx.room.*
@Dao // Data Access Object - methods to interact with the DB
interface NoteDao {
    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<Note>
}
