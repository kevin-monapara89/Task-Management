package com.kevin.taskmanagement.Fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Delete
import androidx.room.Update
import com.kevin.taskmanagement.Adapter.TaskAdapter
import com.kevin.taskmanagement.Database.RoomDB
import com.kevin.taskmanagement.Enitiy.TaskEnitiy
import com.kevin.taskmanagement.databinding.FragmentHomeBinding
import com.kevin.taskmanagement.databinding.TodolistviewBinding
import java.util.Locale
import com.kevin.taskmanagement.databinding.UpdatedialogBinding
import java.text.SimpleDateFormat
import java.util.Date

//class HomeFragment : Fragment() {
//
//    lateinit var binding: FragmentHomeBinding
//    var Tasklist = ArrayList<TaskEnitiy>()
//    lateinit var db: RoomDB
//    lateinit var adapter: TaskAdapter
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View {
//        binding = FragmentHomeBinding.inflate(layoutInflater)
//        db = RoomDB.init(context)
//
////        adapter = TaskAdapter(Tasklist as ArrayList<TaskEnitiy>)
////         {.
////         Update(it)
////        }
////        ) {
////            Delete(it)
////        }
//
//        initView()
//
//        return binding.root
//    }
//
//    private fun initView() {
//        adapter.setTask(db.task().GetTask())
//        binding.rcvtasklist.layoutManager = LinearLayoutManager(context)
//        binding.rcvtasklist.adapter = adapter
//}
//

//
//
//
//    }

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: TaskAdapter
    var Tasklist = ArrayList<TaskEnitiy>()
    lateinit var db: RoomDB
    lateinit var todolistviewBinding: TodolistviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        db = RoomDB.init(context)

        initview()

        return binding.root
    }


    @SuppressLint("NewApi")
    private fun initview() {
        var list = db.task().GetTask()
        adapter = TaskAdapter(
            list as ArrayList<TaskEnitiy>, {
                Update(it)
            }, {
                Delete(it)
            })

        binding.rcvtasklist.layoutManager = LinearLayoutManager(context)
        binding.rcvtasklist.adapter = adapter


    }
    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun Update(it: TaskEnitiy) {

        var dialog = Dialog(requireContext())
        var b = UpdatedialogBinding.inflate(layoutInflater)
        dialog.setContentView(b.root)

        b.edtdate.setOnClickListener {

            var date = Date()

            var format1 = SimpleDateFormat("dd-MM-YY")
            var currentDate = format1.format(date)

            var dates = currentDate.split("-")
            b.edtdate.text = currentDate

            var dialog =
                DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener {
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

            var format2 = SimpleDateFormat("hh:mm")
            var currentTime = format2.format(date)

            b.edttime.text = currentTime
            var seleTime = currentTime
            var dialog1 = TimePickerDialog(context, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {

                    var hour = p1
                    var minute = p2
                    var sdf = SimpleDateFormat("hh:mm", Locale.US)
                    var tme = "$hour:$minute "
                    b.edttime.setText(tme)
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
            var format = SimpleDateFormat("dd-MM-YY hh:mm")
            var current = format.format(Date())
            var data = TaskEnitiy(title, text, Date, Month, Year, hour, minute)
            var tasks = db.task().GetTask()
            db.task().UpdateTask(data)

            for (task in tasks) {
                task.title = title
                task.discription = text
                task.hour = hour
                task.date = Date
                task.month = Month
                task.year = Year
                task.minute = minute


                var data = TaskEnitiy(title, text, Date, Month, Year, hour, minute)
                db.task().UpdateTask(task)
            }

            adapter.update(db.task().GetTask())
            dialog.dismiss()
        }

        dialog.show()


    }



    private fun Delete(it: Int) {
        db.task().DeleteTask(it)
        adapter.update(db.task().GetTask())

    }
}