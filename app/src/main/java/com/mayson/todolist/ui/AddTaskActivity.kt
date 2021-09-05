package com.mayson.todolist.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.mayson.todolist.databinding.ActivityAddTaskBinding
import com.mayson.todolist.extensions.format
import com.mayson.todolist.extensions.text
import java.time.DateTimeException
import java.util.*

class AddTaskActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertListeners()


    }

    private fun insertListeners() {
        binding.inputData.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener{
                val timeZone = TimeZone.getDefault()
                val offSet = timeZone.getOffset(Date().time) * -1
                binding.inputData.text = Date(it + offSet).format()
            }
            datePicker.show(supportFragmentManager,"DATA_PICKER_TAG")

        }
    }


}