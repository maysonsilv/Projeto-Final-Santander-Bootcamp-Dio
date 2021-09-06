package com.mayson.todolist.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mayson.todolist.databinding.ActivityMainBinding
import com.mayson.todolist.datasource.TaskDataSource

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy {TaskListerAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTasks.adapter = adapter
        updateList()


        insertListeners()


    }

    private fun insertListeners() {
        binding.fbt.setOnClickListener{

            startActivityForResult(Intent(this, AddTaskActivity::class.java), CREATE_NEW_TASK)

            }

        adapter.listenerEdit = {
            Log.e("TAG", "listenerEdit: $it")
        }

        adapter.listenerDelet = {

            Log.e("TAG", "listenerDelet: $it")
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NEW_TASK && resultCode == Activity.RESULT_OK) updateList()
    }

    private fun updateList(){
        adapter.submitList(TaskDataSource.getList())
    }

    companion object{
        private const val CREATE_NEW_TASK = 1000
    }




}