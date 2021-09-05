package com.mayson.todolist.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.mayson.todolist.databinding.ActivityAddTaskBinding
import com.mayson.todolist.datasource.TaskDataSource
import com.mayson.todolist.extensions.format
import com.mayson.todolist.extensions.text
import com.mayson.todolist.model.Task
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

        binding.inputHora.editText?.setOnClickListener {
            val timePiker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            timePiker.addOnPositiveButtonClickListener{
               val minute =  if (timePiker.minute in 0..9) "0${timePiker.minute}" else timePiker.minute
               val hour =  if (timePiker.hour in 0..9) "0${timePiker.hour}" else timePiker.hour
                binding.inputHora.text = ("$hour:$minute")
            }
            timePiker.show(supportFragmentManager, null)
        }

        binding.btCancelar.setOnClickListener{
            finish()
        }

        binding.btCt.setOnClickListener {
            val task = Task(
                title = binding.inputText.text,
                description = binding.inputDesc.text,
                hour = binding.inputHora.text,
                date = binding.inputData.text

            )
            TaskDataSource.insertTask(task)
            Log.e("TAG", "InsertTask: " + TaskDataSource.getList())
        }
    }


}