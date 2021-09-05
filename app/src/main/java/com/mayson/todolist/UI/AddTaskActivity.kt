package com.mayson.todolist.UI

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
//import com.google.android.material.datepicker.MaterialDatePicker
import com.mayson.todolist.databinding.ActivityAddTaskBinding

class AddTaskActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertListeners()

    }

    private fun insertListeners() {
        binding.inputData.editText?.setOnClickListener{
            Log.e("TAG","insertListeners")
            //val datePicker = MaterialDatePicker.Builder.datePicker().build()
            //datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }
    }
}