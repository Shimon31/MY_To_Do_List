package com.example.noteapppracticeusingroomdatabase

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.noteapppracticeusingroomdatabase.databinding.FragmentAddNoteBinding
import java.util.Calendar


class AddNoteFragment : Fragment(), AdapterView.OnItemSelectedListener {

    lateinit var binding: FragmentAddNoteBinding

    var priorityList = listOf("select Priority", "High", "Medium", "low")

    private var timePicker: String? = null
    private var datePicker: String? = null
    private var priority: String? = null

    lateinit var note: Note

    private var noteId = 0


    companion object {

        var Note_ID = "note_Id"

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)

        noteId = requireArguments().getInt(Note_ID)

        if (noteId != 0) {
            note =
                NoteDatabase.getDB(requireContext()).getNoteDao()
                    .getNoteById(listOf<Int>(noteId))[0]

            binding.apply {

                notesTitle.setText(note.title)
                timePickerBtn.text = note.time
                datePickerBtn.text = note.date
            }


        }


        var spinnerAdapter = ArrayAdapter(
            requireContext(),
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            priorityList
        )

        binding.priorityMenu.adapter = spinnerAdapter

        binding.priorityMenu.onItemSelectedListener = this@AddNoteFragment

        binding.datePickerBtn.setOnClickListener {

            pickADate()

        }

        binding.timePickerBtn.setOnClickListener {

            pickATime()

        }
        binding.submitBtn.setOnClickListener {
            var titleStr = binding.notesTitle.text.toString()
            var timeStr = timePicker ?: "00:00"
            var dateStr = datePicker ?: "0/0/00"
            var priorityStr = priority ?: "Low"


            note =
                Note(title = titleStr, time = timeStr, date = dateStr, priority = priorityStr)

            if (noteId == 0) {

                NoteDatabase.getDB(requireContext()).getNoteDao().insertNote(note)
            }else{
                note.noteId = noteId
                NoteDatabase.getDB(requireContext()).getNoteDao().update(note)
            }


        }



        return binding.root
    }

    private fun pickATime() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val Minute = calendar.get(Calendar.MINUTE)

        val timePicker = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->


                timePicker = "$hourOfDay : $minute"
                binding.timePickerBtn.text = timePicker

            }, hour, Minute, false
        )
        timePicker.show()

    }

    private fun pickADate() {

        val calender = Calendar.getInstance()

        val date = calender.get(Calendar.DAY_OF_MONTH)
        val month = calender.get(Calendar.MONTH)
        val year = calender.get(Calendar.YEAR)


        val datePickerDialog = DatePickerDialog(
            requireActivity(),
            { _, year, month, dayOfMonth ->

                datePicker = "$dayOfMonth/$month/$year"

                binding.datePickerBtn.text = datePicker
            },

            year, month, date
        )

        datePickerDialog.show()

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        priority = priorityList[position]

        Toast.makeText(requireContext(), "$priority", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}