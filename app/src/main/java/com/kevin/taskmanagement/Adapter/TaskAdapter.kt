package com.kevin.taskmanagement.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kevin.taskmanagement.Enitiy.TaskEnitiy
import com.kevin.taskmanagement.databinding.TodolistviewBinding

class TaskAdapter(getTask: List<TaskEnitiy>) : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

    var Tasklist = ArrayList<TaskEnitiy>()
    lateinit var context : Context

    class TaskHolder(itemView: TodolistviewBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        context = parent.context
        var binding = TodolistviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskHolder(binding)
    }

    override fun getItemCount(): Int {
        return Tasklist.size
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.binding.apply {
            Tasklist.get(position).apply {
                txttitle.text = title
                txtdiscription.text = discription
                txtdate.text = date
                txtmonth.text = month
                txtyear.text = year
                txttime.text = hour
                txttime.text = minute

            }
        }
    }

    fun settask(Tasklist: ArrayList<TaskEnitiy>) {
        this.Tasklist = Tasklist
    }
}