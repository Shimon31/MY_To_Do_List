package com.example.noteapppracticeusingroomdatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapppracticeusingroomdatabase.databinding.ItemDesignBinding

class NoteAdapter(
    var noteEditListener: NoteEditListener,
    var noteDeleteListener: NoteDeleteListener
) : ListAdapter<Note, NoteAdapter.NoteViewHolder>(comparator) {


    interface NoteEditListener {
        fun onNoteEdit(note: Note)
    }

    interface NoteDeleteListener {
        fun onNoteDelete(note: Note)
    }


    class NoteViewHolder(var binding: ItemDesignBinding) : RecyclerView.ViewHolder(binding.root)


    companion object {

        var comparator = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.noteId == newItem.noteId
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemDesignBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        getItem(position).let {

            holder.binding.apply {

                notesTitle.text = it.title
                notesTime.text = it.time
                notesDate.text = it.date

            }

            holder.itemView.setOnClickListener { _ ->

                noteEditListener.onNoteEdit(it)

            }

            holder.binding.deleteBtn.setOnClickListener { _ ->

                noteDeleteListener.onNoteDelete(it)

            }

        }


    }


}