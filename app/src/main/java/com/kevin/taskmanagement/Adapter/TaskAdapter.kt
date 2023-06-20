package com.kevin.taskmanagement.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kevin.taskmanagement.Enitiy.TaskEnitiy
import com.kevin.taskmanagement.databinding.TodolistviewBinding
import java.util.Date

class TaskAdapter(getTask: List<TaskEnitiy>) : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

    var getTask = getTask

    class TaskHolder(itemView: TodolistviewBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        var binding =
            TodolistviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskHolder(binding)
    }

    override fun getItemCount(): Int {
        return getTask.size
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.binding.apply {
            getTask.get(position).apply {
                txttitle.text = title
                txtdiscription.text = discription
                txtdate.text = date
                txtdate.text = month
                txtdate.text = year
                txttime.text = hour
                txttime.text = minute

            }
        }
    }

    fun update(notes: List<TaskEnitiy>) {
        this.getTask = notes as ArrayList<TaskEnitiy>
        notifyDataSetChanged()
    }

}