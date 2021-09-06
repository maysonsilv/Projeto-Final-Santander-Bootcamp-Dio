package com.mayson.todolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mayson.todolist.R
import com.mayson.todolist.databinding.ItemTaskBinding
import com.mayson.todolist.model.Task

class TaskListerAdapter : ListAdapter<Task,TaskListerAdapter.TaskViewHolder>(DiffCalLBack()) {

    var listenerEdit : (Task) -> Unit = {}
    var listenerDelet : (Task) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }



    inner class TaskViewHolder(
          private val binding: ItemTaskBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Task){
            binding.tvTitle.text = item.title
            binding.tvDate.text = "${item.date} às ${item.hour}"
            binding.ivMore.setOnClickListener{
                showPopUp(item)
            }
        }

        private fun showPopUp(item: Task) {
            val ivmore = binding.ivMore
            val popupMenu = PopupMenu(ivmore.context, ivmore)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_edit -> listenerEdit(item)
                    R.id.action_delet -> listenerDelet(item)
                }

                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
        }

    }
}

class DiffCalLBack : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id

}