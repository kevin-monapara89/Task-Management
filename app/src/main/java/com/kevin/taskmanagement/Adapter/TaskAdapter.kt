package com.kevin.taskmanagement.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kevin.taskmanagement.Database.RoomDB
import com.kevin.taskmanagement.Enitiy.TaskEnitiy
import com.kevin.taskmanagement.R
import com.kevin.taskmanagement.databinding.TodolistviewBinding

class TaskAdapter(getTask: ArrayList<TaskEnitiy>, Update: (TaskEnitiy) -> Unit, delete: (Int) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.TaskHolder>() {
    var getTask = getTask
    var update = Update
    var delete = delete
    lateinit var context: Context

    class TaskHolder(itemView: TodolistviewBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        context = parent.context
        var binding = TodolistviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskHolder(binding)
    }

    override fun getItemCount(): Int {
        return getTask.size
    }

    override fun onBindViewHolder(holder: TaskHolder, @SuppressLint("RecyclerView") position: Int) {
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

        holder.binding.menuitem.setOnClickListener(fun (v: View?) {
            var popupMenu = PopupMenu(context, holder.itemView)
            popupMenu.menuInflater.inflate(R.menu.delete_ubdate, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(p0: MenuItem?): Boolean {

                    if (p0?.itemId == R.id.update) {
                        update.invoke(getTask.get(position))
                    }

                    if (p0?.itemId == R.id.delete) {
                        delete.invoke(getTask.get(position).id)
                    }
                    return true
                }
            })
            popupMenu.show()
        })

    }

    fun update(l: List<TaskEnitiy>) {
        this.getTask = l as ArrayList<TaskEnitiy>
        notifyDataSetChanged()
    }

}