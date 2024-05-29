package com.example.noteapppracticeusingroomdatabase

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.health.connect.datatypes.units.Length
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TimePicker
import android.widget.Toast
import com.example.noteapppracticeusingroomdatabase.databinding.FragmentAddNoteBinding
import java.util.Calendar
import kotlin.math.min


class AddNoteFragment : Fragment(), AdapterView.OnItemSelectedListener {

    lateinit var binding: FragmentAddNoteBinding

    var priorityList = listOf("select Priority", "High", "Medium", "low")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)


        var spinnerAdapter = ArrayAdapter(
            requireContext(),
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            priorityList
        )

        binding.priorityMenu.adapter = spinnerAdapter

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


                var time = "$hourOfDay : $minute"
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

            year, month, date
        )

        datePickerDialog.show()

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(requireContext(), "$position[$priorityList]", Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}