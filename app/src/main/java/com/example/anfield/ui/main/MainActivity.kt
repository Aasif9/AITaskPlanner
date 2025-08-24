
package com.example.anfield.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anfield.R

import com.example.anfield.viewmodel.NoteViewModel
import com.example.anfield.viewmodel.NoteViewModelFactory
import android.content.Intent
import android.speech.RecognizerIntent
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.anfield.ui.notes.NotesFragment
import com.example.anfield.ui.tasks.TasksFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        // Default fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, NotesFragment())
            .commit()

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_notes -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, NotesFragment())
                        .commit()
                    true
                }
                R.id.nav_tasks -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, TasksFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }

}
//
//    private lateinit var noteAdapter: NoteAdapter
//    private lateinit var viewModel: NoteViewModel
//    private val SPEECH_REQUEST_CODE = 100
//    private var isFabOpen = false
//
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//
//
//
//        // Step 1: Setup RecyclerView and Adapter
//        noteAdapter = NoteAdapter(mutableListOf()) { noteToDelete ->
//            showDeleteConfirmation(noteToDelete)
//        }
//
//        val micButton = findViewById<ImageButton>(R.id.micButton)
//        micButton.setOnClickListener {
//            startVoiceRecognition()
//        }
//
//
//        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = noteAdapter
//
//        // Step 2: Initialize ViewModel with Repository and Factory
//        val noteDao = NoteDatabase.getInstance(applicationContext).noteDao()
//        val repository = NoteRepository(noteDao)
//        val factory = NoteViewModelFactory(repository)
//        viewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]
//
//        // Step 3: Observe LiveData from ViewModel
//        viewModel.notes.observe(this) { notes ->
//            noteAdapter.updateNotes(notes)
//        }
//
//        val fab = findViewById<FloatingActionButton>(R.id.voiceFab)
//        fab.setOnClickListener {
//            val intent = Intent(this, NoteEditorActivity::class.java)
//            startActivity(intent)
//        }
//
//
//        // Step 4: Load Notes Initially
//        viewModel.loadNotes()
//
//        // Step 5: Add Note on Button Click
//        val titleInput = findViewById<EditText>(R.id.titleInput)
//        val descInput = findViewById<EditText>(R.id.descInput)
//        val addBtn = findViewById<Button>(R.id.addBtn)
//
//        addBtn.setOnClickListener {
//            val title = titleInput.text.toString().trim()
//            val desc = descInput.text.toString().trim()
//
//            if (title.isNotEmpty() && desc.isNotEmpty()) {
//                val newNote = Note(title = title, description = desc)
//                viewModel.addNote(newNote)  // Call ViewModel instead of DAO
//                titleInput.text.clear()
//                descInput.text.clear()
//            }
//        }
//    }
//
//    // Utility: Show Delete Confirmation
//    private fun showDeleteConfirmation(note: Note) {
//        AlertDialog.Builder(this)
//            .setTitle("Delete Note")
//            .setMessage("Do you want to delete this note?")
//            .setPositiveButton("Delete") { _, _ ->
//                viewModel.deleteNote(note)  // Delete via ViewModel
//            }
//            .setNegativeButton("Cancel", null)
//            .create()
//            .show()
//    }
//
//    private fun startVoiceRecognition() {
//        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
//            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
//            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
//            putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak your note")
//        }
//
//        try {
//            startActivityForResult(intent, SPEECH_REQUEST_CODE)
//        } catch (e: Exception) {
//            Toast.makeText(this, "Your device doesn't support speech input", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
//            val results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
//            val spokenText = results?.get(0) ?: ""
//
//            val titleInput = findViewById<EditText>(R.id.titleInput)
//            val descInput = findViewById<EditText>(R.id.descInput)
//
//            if (titleInput.text.isEmpty()) {
//                titleInput.setText(spokenText)
//            } else {
//                descInput.setText(spokenText)
//            }
//
//            // ✅ Auto-save if both fields are filled
//            if (titleInput.text.isNotEmpty() && descInput.text.isNotEmpty()) {
//                val note = Note(
//                    title = titleInput.text.toString(),
//                    description = descInput.text.toString()
//                )
//                viewModel.addNote(note)
//                titleInput.text.clear()
//                descInput.text.clear()
//
//                // ✅ Show confirmation
//                Snackbar.make(findViewById(android.R.id.content), "Voice note added", Snackbar.LENGTH_SHORT).show()
//            }
//        }
//    }





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
