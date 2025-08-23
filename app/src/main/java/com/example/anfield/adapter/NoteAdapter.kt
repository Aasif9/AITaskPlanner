package com.example.anfield.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.anfield.R
import com.example.anfield.data.Note

class NoteAdapter(
    private val notes: MutableList<Note>,
    private val onDelete: (Note) -> Unit
    ) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.noteTitle)
        val desc = itemView.findViewById<TextView>(R.id.noteDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.title.text = note.title
        holder.desc.text = note.description
        holder.itemView.setOnLongClickListener{
            onDelete(note)
            true
        }
    }

    override fun getItemCount(): Int = notes.size

//    fun addNote(note: Note) {
//        notes.add(note)
//        notifyItemInserted(notes.size - 1)
//    }
//
//    fun deleteNote(note: Note){
//        val index = notes.indexOf(note);
//        if(index!=-1){
//            notes.removeAt(index);
//            notifyItemRemoved(index);
//        }
//    }
    // Called after DB update to refresh UI
    fun updateNotes(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }
}
