package com.example.anfield.data.local
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.anfield.data.model.Note

@Dao // Data Access Object - methods to interact with the DB
interface NoteDao {
    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM notes ORDER BY timestamp DESC")
    fun getAllNotes(): LiveData<List<Note>>
}
