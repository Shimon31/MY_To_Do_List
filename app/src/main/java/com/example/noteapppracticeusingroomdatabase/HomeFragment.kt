package com.example.noteapppracticeusingroomdatabase

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.noteapppracticeusingroomdatabase.databinding.FragmentHomeBinding
import kotlin.math.log


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    lateinit var database: NoteDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        database = Room.databaseBuilder(requireActivity(),
            NoteDatabase::class.java, "Note-DB")
            .allowMainThreadQueries().build()


        var notes:List<Note> = database.getNoteDao().getAllNote()

        binding.addNote.setOnClickListener {

            findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)

        }



        return binding.root
    }

}