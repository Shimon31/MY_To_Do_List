package com.example.noteapppracticeusingroomdatabase

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.blue
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.noteapppracticeusingroomdatabase.databinding.FragmentHomeBinding
import kotlin.math.log


class HomeFragment : Fragment(),NoteAdapter.NoteEditListener,NoteAdapter.NoteDeleteListener {

    lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)



        NoteDatabase.getDB(requireContext()).getNoteDao().getAllNote()?.let {

            var adapter = NoteAdapter(this,this)
            adapter.submitList(it)

            binding.recyclerView.adapter = adapter

        }

        binding.addNote.setOnClickListener {

            val bundle = Bundle()
            bundle.putInt(AddNoteFragment.Note_ID,0)

            findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment,bundle)

        }



        return binding.root
    }

    override fun onNoteEdit(note: Note) {

        val bundle = Bundle()
        bundle.putInt(AddNoteFragment.Note_ID,note.noteId)

        findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment,bundle
        )
    }

    override fun onNoteDelete(note: Note) {

        val noteDao = NoteDatabase.getDB(requireContext()).getNoteDao()

        // Delete the note in a background thread
        Thread {
            noteDao.delete(note)
            // Refresh the notes list on the main thread
            requireActivity().runOnUiThread {
                val updatedNotes = noteDao.getAllNote()
                (binding.recyclerView.adapter as NoteAdapter).submitList(updatedNotes)
                Toast.makeText(requireContext(), "Note deleted", Toast.LENGTH_SHORT).show()
            }
        }.start()

    }

}