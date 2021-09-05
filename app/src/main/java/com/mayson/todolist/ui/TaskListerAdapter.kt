package com.mayson.todolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mayson.todolist.databinding.ItemTaskBinding
import com.mayson.todolist.model.Task

class TaskListerAdapter : ListAdapter<Task,TaskListerAdapter.TaskViewHolder>(DiffCalLBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }



    class TaskViewHolder(
          private val binding: ItemTaskBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Task){
            binding.tvTitle.text = item.title
            binding.tvDate.text = "${item.date} ${item.hour}"
        }

    }
}

class DiffCalLBack : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id

}