package com.example.anfield.ui.editor

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.anfield.R

class NoteEditorFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_editor)

        val titleInput = findViewById<EditText>(R.id.titleEditor)
        val descInput = findViewById<EditText>(R.id.descEditor)

        // You can set existing note data here if editing
    }
}
