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

            findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)

        }



        return binding.root
    }

    override fun onNoteEdit(note: Note) {

        val bundle = Bundle()
        bundle.putInt(AddNoteFragment.Note_ID,note.noteId)

        findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment,bundle)
    }

    override fun onNoteDelete(note: Note) {
        TODO("Not yet implemented")
    }

}