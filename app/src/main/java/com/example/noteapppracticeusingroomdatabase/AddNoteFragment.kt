package com.example.noteapppracticeusingroomdatabase

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import com.example.noteapppracticeusingroomdatabase.databinding.FragmentAddNoteBinding
import java.util.Calendar
import kotlin.math.min


class AddNoteFragment : Fragment() {

    lateinit var binding: FragmentAddNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)



        binding.datePickerBtn.setOnClickListener {

            pickADate()

        }

        binding.timePickerBtn.setOnClickListener {

            pickATime()

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


             var  time = "$hourOfDay : $minute"
                binding.timePickerBtn.text = time

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

                var selectedDate = "$dayOfMonth/$month/$year"

                binding.datePickerBtn.text = selectedDate
            },

           year,month,date
        )

        datePickerDialog.show()

    }

}