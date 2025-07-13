
package com.example.anfield.ui

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anfield.R
import com.example.anfield.adapter.NoteAdapter
import com.example.anfield.data.Note
import com.example.anfield.data.NoteDatabase
import com.example.anfield.repository.NoteRepository
import com.example.anfield.viewmodel.NoteViewModel
import com.example.anfield.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var noteAdapter: NoteAdapter
    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Step 1: Setup RecyclerView and Adapter
        noteAdapter = NoteAdapter(mutableListOf()) { noteToDelete ->
            showDeleteConfirmation(noteToDelete)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = noteAdapter

        // Step 2: Initialize ViewModel with Repository and Factory
        val noteDao = NoteDatabase.getInstance(applicationContext).noteDao()
        val repository = NoteRepository(noteDao)
        val factory = NoteViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]

        // Step 3: Observe LiveData from ViewModel
        viewModel.notes.observe(this) { notes ->
            noteAdapter.updateNotes(notes)
        }

        // Step 4: Load Notes Initially
        viewModel.loadNotes()

        // Step 5: Add Note on Button Click
        val titleInput = findViewById<EditText>(R.id.titleInput)
        val descInput = findViewById<EditText>(R.id.descInput)
        val addBtn = findViewById<Button>(R.id.addBtn)

        addBtn.setOnClickListener {
            val title = titleInput.text.toString().trim()
            val desc = descInput.text.toString().trim()

            if (title.isNotEmpty() && desc.isNotEmpty()) {
                val newNote = Note(title = title, description = desc)
                viewModel.addNote(newNote)  // Call ViewModel instead of DAO
                titleInput.text.clear()
                descInput.text.clear()
            }
        }
    }

    // Utility: Show Delete Confirmation
    private fun showDeleteConfirmation(note: Note) {
        AlertDialog.Builder(this)
            .setTitle("Delete Note")
            .setMessage("Do you want to delete this note?")
            .setPositiveButton("Delete") { _, _ ->
                viewModel.deleteNote(note)  // Delete via ViewModel
            }
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }
}


//package com.example.anfield.ui
//
//import android.app.AlertDialog
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.lifecycleScope
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.anfield.adapter.NoteAdapter
//import com.example.anfield.R
//import com.example.anfield.data.Note
//import com.example.anfield.data.NoteDatabase
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var noteAdapter: NoteAdapter
//    private lateinit var noteDatabase: NoteDatabase
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        noteDatabase = NoteDatabase.getInstance(this)
//        val noteDao = noteDatabase.noteDao();
//
//
////        noteAdapter = NoteAdapter(noteList){ noteToDelete ->
////            noteAdapter.deleteNote(noteToDelete)
////            Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show()
////        }
//
//        noteAdapter = NoteAdapter(mutableListOf()) { noteToDelete ->
//            //this was a long pressed delete
////            lifecycleScope.launch {
////                noteDao.delete(noteToDelete)
////                val updated = noteDao.getAllNotes()
////                withContext(Dispatchers.Main) {
////                    noteAdapter.updateNotes(updated)
////                }
////            }
//            val alertDialog = AlertDialog.Builder(this)
//                .setTitle("Delete Note")
//                .setMessage("Do you want to delete this note?")
//                .setPositiveButton("Delete") { _, _ ->
//                    lifecycleScope.launch {
//                        noteDao.delete(noteToDelete)
//                        val updated = noteDao.getAllNotes()
//                        withContext(Dispatchers.Main) {
//                            noteAdapter.updateNotes(updated)
//                        }
//                    }
//                }
//                .setNegativeButton("Cancel", null)
//                .create()
//
//            alertDialog.show()
//
//
//        }
//
//        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = noteAdapter
//
//        val titleInput = findViewById<EditText>(R.id.titleInput)
//        val descInput = findViewById<EditText>(R.id.descInput)
//        val addBtn = findViewById<Button>(R.id.addBtn)
//
//        addBtn.setOnClickListener {
//            val title = titleInput.text.toString()
//            val desc = descInput.text.toString()
////            if (title.isNotEmpty() && desc.isNotEmpty()) {
////                noteAdapter.addNote(Note(title, desc))
////                titleInput.text.clear()
////                descInput.text.clear()
////            }
//
//            if (title.isNotEmpty() && desc.isNotEmpty()) {
//                val newNote = Note(title = title, description = desc)
//                lifecycleScope.launch {
//                    noteDao.insert(newNote)
//                    val updated = noteDao.getAllNotes()
//                    withContext(Dispatchers.Main) {
//                        noteAdapter.updateNotes(updated)
//                    }
//                }
//                titleInput.text.clear()
//                descInput.text.clear()
//            }
//        }
//
////        noteAdapter = NoteAdapter(noteList) { noteToDelete ->
////            noteAdapter.deleteNote(noteToDelete)
////            Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show()
////        }
//
//        lifecycleScope.launch {
//            val notes = noteDao.getAllNotes()
//            withContext(Dispatchers.Main) {
//                noteAdapter.updateNotes(notes)
//            }
//        }
//
//    }
//}
