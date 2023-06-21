package com.kevin.taskmanagement.Fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevin.taskmanagement.Adapter.TaskAdapter
import com.kevin.taskmanagement.Database.RoomDB
import com.kevin.taskmanagement.Enitiy.TaskEnitiy
import com.kevin.taskmanagement.databinding.FragmentHomeBinding
import com.kevin.taskmanagement.databinding.UpdatedialogBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: TaskAdapter
    var Tasklist = ArrayList<TaskEnitiy>()
    lateinit var db: RoomDB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        db = RoomDB.init(context)



        initview()

        return binding.root
    }


    private fun initview() {
        var list = db.task().GetTask()
        adapter = TaskAdapter(
            list as ArrayList<TaskEnitiy>,
         {

         Update(it)
        }
         ,{

             Delete(it)
        })
        binding.rcvtasklist.layoutManager = LinearLayoutManager(context)
        binding.rcvtasklist.adapter = adapter
    }

    private fun Delete(it: Int) {
//        db.task().DeleteTask(it)

    }

    private fun Update(it: TaskEnitiy) {

        var dialog = Dialog(requireContext())
        var b = UpdatedialogBinding.inflate(layoutInflater)
        dialog.setContentView(b.root)

        b.edtdate.setOnClickListener {

            var date = Date()

            var format1 = SimpleDateFormat("dd-MM-YYYY")
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

            var format2 = SimpleDateFormat("hh:mm a")
            var currentTime = format2.format(date)

            b.edttime.text = currentTime
            var seleTime = currentTime
            var dialog1 = TimePickerDialog(context, object : TimePickerDialog.OnTimeSetListener {
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

            var text = b.edtdescription.text.toString()
            var title = b.edtTask.text.toString()
            var Date = b.edtdate.text.toString()
            var Month = b.edtdate.text.toString()
            var Year = b.edtdate.text.toString()
            var hour = b.edttime.text.toString()
            var minute = b.edttime.text.toString()
            var format = SimpleDateFormat("dd-MM-YYYY hh:mm a")
            var current = format.format(Date())
//
//
//            var data = TaskEnitiy(title ,text, Date, Month, Year, hour, minute)
//            val insertedId = db.task().AddTask(data)
//            data.id = insertedId

            var  task = db.task().GetTask()
            for (t in task)
            {

//                var text = b.edtdescription.text.toString()
//                var title = b.edtTask.text.toString()
//                var Date = b.edtdate.text.toString()
//                var Month = b.edtdate.text.toString()
//                var Year = b.edtdate.text.toString()
//                var hour = b.edttime.text.toString()
//                var minute = b.edttime.text.toString()
//                var format = SimpleDateFormat("dd-MM-YYYY hh:mm a")
//                var current = format.format(Date())


                t.title =title
                t.discription = text
                t.hour = hour
                t.date = Date
                t.month = Month
                t.year = Year
                t.minute = minute
                t.id



                var data = TaskEnitiy( title,text,Date,Month,Year,hour,minute)

                db.task().UpdateTask(t)



            }

            adapter.update(db.task().GetTask())



//            db.task().UpdateTask(data)
//            adapter.update(db.task().GetTask())

            dialog.dismiss()

//            if (title.isEmpty() || text.isEmpty()) {
//                Toast.makeText(context, "Please enter data", Toast.LENGTH_SHORT).show()
//            } else {
//                binding.edtTask.setText("")
//                binding.edtdescription.setText("")
//            }
        }

        dialog.show()

    }

    }

