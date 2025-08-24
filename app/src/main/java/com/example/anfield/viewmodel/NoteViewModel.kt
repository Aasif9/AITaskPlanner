package com.example.anfield.viewmodel



import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.anfield.data.local.NoteDatabase
import com.example.anfield.data.model.Note
import com.example.anfield.data.repository.NoteRepository

// 🔹 ViewModel exists to hold data for the UI. It survives configuration changes like screen rotation.
// It also performs background work using coroutine scope.

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository
    val allNotes: LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }
}

