package com.example.anfield.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes") // Marks this class as a table for Room DB
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Unique ID for each note
    val title: String,
    val description :String
)