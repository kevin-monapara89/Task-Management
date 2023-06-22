package com.kevin.taskmanagement.Adapter

import android.content.Context
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

class TaskAdapter(getTask: List<TaskEnitiy>, update: (TaskEnitiy) -> Unit, delete: (Int) -> Unit) : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

        lateinit var context: Context
        var update = update
        var delete = delete
        lateinit var taskEnitiy: TaskEnitiy
        lateinit var db: RoomDB

        var getTask = getTask
        private var filteredList: MutableList<TaskEnitiy> = getTask.toMutableList()

        class TaskHolder(itemView: TodolistviewBinding) : ViewHolder(itemView.root) {
            var binding = itemView
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
            var binding =
                TodolistviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

            context = parent.context

            return TaskHolder(binding)
        }

        override fun getItemCount(): Int {
            return getTask.size
        }

        override fun onBindViewHolder(holder: TaskHolder, position: Int) {
            db = RoomDB.init(context)
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

        holder.binding.menuitem.setOnClickListener(
        object : View.OnClickListener {


            override fun onClick(v: View?) {
                var popupMenu = PopupMenu(context, holder.itemView)
                popupMenu.menuInflater.inflate(R.menu.delete_ubdate, popupMenu.menu)

                popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                    override fun onMenuItemClick(p0: MenuItem?): Boolean {

                        if (p0?.itemId == R.id.update) {
                            update.invoke(list.get(position))
                        }

                        if (p0?.itemId == R.id.delete) {
                            delete.invoke(list.get(position).id)
                        }
                        return true
                    }
                })
                popupMenu.show()

                fun update(notes: List<TaskEnitiy>) {
                    this.getTask = notes as ArrayList<TaskEnitiy>
                }
            })
        }

        fun update(l: List<TaskEnitiy>) {
            list = l as ArrayList<TaskEnitiy>
            notifyDataSetChanged()
        }
    }
}