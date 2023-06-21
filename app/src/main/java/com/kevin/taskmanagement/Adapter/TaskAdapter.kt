package com.kevin.taskmanagement.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kevin.taskmanagement.Enitiy.TaskEnitiy
import com.kevin.taskmanagement.databinding.TodolistviewBinding
import java.util.Locale
import java.util.logging.Filter

class TaskAdapter(getTask: ArrayList<TaskEnitiy>) : RecyclerView.Adapter<TaskAdapter.TaskHolder>(){

    var getTask = getTask
    private var filteredList: MutableList<TaskEnitiy> = getTask.toMutableList()

    class TaskHolder(itemView: TodolistviewBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        var binding = TodolistviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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

    fun setFilteredList(mList: ArrayList<TaskEnitiy>) {
        this.getTask = getTask
        notifyDataSetChanged()
    }

    fun update(notes: List<TaskEnitiy>) {
        this.getTask = notes as ArrayList<TaskEnitiy>
        notifyDataSetChanged()
    }

}