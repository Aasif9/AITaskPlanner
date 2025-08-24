package com.example.anfield.ui.notes

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.anfield.R
import com.example.anfield.data.model.Note
import com.example.anfield.databinding.ActivityAddNoteBinding
import com.example.anfield.viewmodel.NoteViewModel

class AddNoteActivity : AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTextContent: EditText
    private lateinit var buttonSave: Button
    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        // init views
        editTextTitle = findViewById(R.id.editTextTitle)
        editTextContent = findViewById(R.id.editTextContent)
        buttonSave = findViewById(R.id.buttonSave)

        // setup toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbarAddNote)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // back button

        // ViewModel
        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        buttonSave.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
        val title = editTextTitle.text.toString().trim()
        val content = editTextContent.text.toString().trim()

        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "Please enter title and content", Toast.LENGTH_SHORT).show()
            return
        }

        val note = Note(
            title = title,
            content = content,
            timestamp = System.currentTimeMillis()
        )

        viewModel.insert(note) // inserts via Repository + Room
        Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
        finish() // go back to NotesFragment
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
