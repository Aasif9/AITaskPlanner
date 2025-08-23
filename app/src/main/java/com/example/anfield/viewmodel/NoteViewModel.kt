package com.example.anfield.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.anfield.data.Note
import com.example.anfield.repository.NoteRepository

// 🔹 ViewModel exists to hold data for the UI. It survives configuration changes like screen rotation.
// It also performs background work using coroutine scope.

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    // 🔹 LiveData - UI observes this to get automatic updates
    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> get() = _notes

    // 🔹 Load notes from DB and expose via LiveData
    fun loadNotes() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getAllNotes()
            }
            _notes.value = result
        }
    }

    // 🔹 Add a new note and refresh notes list
    fun addNote(note: Note) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.insert(note)
                _notes.postValue(repository.getAllNotes())
            }
        }
    }

    // 🔹 Delete a note and refresh notes list
    fun deleteNote(note: Note) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.delete(note)
                _notes.postValue(repository.getAllNotes())
            }
        }
    }
}
