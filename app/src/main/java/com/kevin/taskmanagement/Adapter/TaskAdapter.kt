package com.kevin.taskmanagement.Adapter

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kevin.taskmanagement.Database.RoomDB
import com.kevin.taskmanagement.Enitiy.TaskEnitiy
import com.kevin.taskmanagement.databinding.TodolistviewBinding
import com.kevin.taskmanagement.databinding.UpdatedialogBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TaskAdapter(
    list: ArrayList<TaskEnitiy>
) : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {
    lateinit var context: Context
    var list = list
    lateinit var taskEnitiy :TaskEnitiy
    lateinit var db: RoomDB


    class TaskHolder(itemView: TodolistviewBinding) : ViewHolder(itemView.root) {

        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        var binding =
            TodolistviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        var binding =
            TodolistviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context


        return TaskHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskHolder, @SuppressLint("RecyclerView") position: Int) {
        db = RoomDB.init(context).task()
        holder.binding.apply {
            list.get(position).apply {
                txttitle.text = title
                txtdiscription.text = discription
                txtdate.text = date
                txtdate.text = month
                txtdate.text = year
                txttime.text = hour
                txttime.text = minute

            }
        }
        holder.binding.imgubdata.setOnClickListener {

            Toast.makeText(context, "Hiii....", Toast.LENGTH_SHORT).show()

            var dialogupdate = Dialog(context)
            var b = UpdatedialogBinding.inflate(LayoutInflater.from(context))
            dialogupdate.setContentView(b.root)


//                holder.binding.apply {
//                    list.get(position).apply {
//                        b.edtdate.setText(txtdate.text)
//                        b.edttime.setText(txttime.text)
//                        b.edtTask.setText(txttitle.text)
//                        b.edtdescription.setText(txtdiscription.text)
//
//
//                    }
//                }

            b.edtdate.setOnClickListener {

                var date = Date()

                var format1 = SimpleDateFormat("dd-MM-YYYY")
                var currentDate = format1.format(date)

                var dates = currentDate.split("-")
                b.edtdate.text = currentDate

                var dialog =
                    DatePickerDialog(context, object : DatePickerDialog.OnDateSetListener {
                        override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {

                            var Year = p1
                            var Month = p2 + 1
                            var Date = p3

                            var selectedDate = "$p3-${(p2 + 1)}-$p1"
                            b.edtdate.text = selectedDate
                        }

                    }, dates[2].toInt(), dates[1].toInt() - 1, dates[0].toInt())
                dialog.show()
            }

            b.edttime.setOnClickListener {

                var date = Date()

                var format2 = SimpleDateFormat("hh:mm a")
                var currentTime = format2.format(date)

                b.edttime.text = currentTime
                var seleTime = currentTime
                var dialog1 =
                    TimePickerDialog(context, object : TimePickerDialog.OnTimeSetListener {
                        override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {

                            var hour = p1
                            var minute = p2
                            var am_pm = if (p1 < 12) "AM" else "PM"
                            var sdf = SimpleDateFormat("hh:mm a", Locale.US)
                            var tme = "$hour:$minute $am_pm"
                            b.edttime.setText(tme)
//                    var selectedTime = "$p1:$p2"
//                    binding.edttime.text = selectedTime
                        }

                    }, 10, 0, true)
                dialog1.show()
            }

            b.btnsubmit.setOnClickListener {

                var title = b.edtTask.text.toString()
                var text = b.edtdescription.text.toString()
                var Date = b.edtdate.text.toString()
                var Month = b.edtdate.text.toString()
                var Year = b.edtdate.text.toString()
                var hour = b.edttime.text.toString()
                var minute = b.edttime.text.toString()
                var format = SimpleDateFormat("dd-MM-YYYY hh:mm a")
                var current = format.format(Date())

                var data = TaskEnitiy(title,text,Date,Month,Year,hour,minute,list.get(position).id)

                db.task().UpdateTask(data)

                dialogupdate.dismiss()

                update(db.task().GetTask())
            }

            dialogupdate.show()

            holder.binding.imgdelete.setOnClickListener {

                Toast.makeText(context, "Hiii123456789", Toast.LENGTH_SHORT).show()

                db.task().DeleteTask(list.get(position))

                update(db.task().GetTask())

            }
        }
    }
    fun update(l: List<TaskEnitiy>) {
        list = l as ArrayList<TaskEnitiy>
        notifyDataSetChanged()
    }
}






