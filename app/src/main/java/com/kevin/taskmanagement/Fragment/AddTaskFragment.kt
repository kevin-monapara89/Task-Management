package com.kevin.taskmanagement.Fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kevin.taskmanagement.Database.RoomDB
import com.kevin.taskmanagement.Enitiy.TaskEnitiy
import com.kevin.taskmanagement.databinding.FragmentAddTaskBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class AddTaskFragment : Fragment() {

    lateinit var binding: FragmentAddTaskBinding
    lateinit var db: RoomDB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAddTaskBinding.inflate(layoutInflater)
        db = RoomDB.init(context)
        AddData()

        return binding.root
    }

    private fun AddData() {

        binding.edtdate.setOnClickListener {

            var date = Date()

            var format1 = SimpleDateFormat("dd-MM-YY")
            var currentDate = format1.format(date)

            var dates = currentDate.split("-")
            binding.edtdate.text = currentDate

            var dialog =
                DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {

                        var Year = p1
                        var Month = p2 + 1
                        var Date = p3

                        var selectedDate = "$p3-${(p2 + 1)}-$p1"
                        binding.edtdate.text = selectedDate
                    }

                }, dates[2].toInt(), dates[1].toInt() - 1, dates[0].toInt())
            dialog.show()
        }

        binding.edttime.setOnClickListener {

            var date = Date()

            var format2 = SimpleDateFormat("hh:mm")
            var currentTime = format2.format(date)

            binding.edttime.text = currentTime
            var seleTime = currentTime
            var dialog1 = TimePickerDialog(context, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {

                    var hour = p1
                    var minute = p2
                    var sdf = SimpleDateFormat("hh:mm", Locale.US)
                    var tme = "$hour:$minute "
                    binding.edttime.setText(tme)

                }

            }, 10, 0, true)
            dialog1.show()
        }

        binding.btnsubmit.setOnClickListener {

            var title = binding.edtTask.text.toString()
            var discription = binding.edtdescription.text.toString()
            var Date = binding.edtdate.text.toString()
            var Month = binding.edtdate.text.toString()
            var Year = binding.edtdate.text.toString()
            var hour = binding.edttime.text.toString()
            var minute = binding.edttime.text.toString()
            var format = SimpleDateFormat("dd-MM-YY hh:mm")
            var current = format.format(Date())
            var data = TaskEnitiy(title, discription, Date, Month, Year, hour, minute)
            db.task().AddTask(data)

            if (title.isEmpty() || discription.isEmpty() || data.toString()
                    .isEmpty() || hour.toString().isEmpty()
            ) {
                Toast.makeText(context, "Please enter data", Toast.LENGTH_SHORT).show()
            } else {
                binding.edtTask.setText("")
                binding.edtdescription.setText("")
                binding.edtdate.setText("")
                binding.edttime.setText("")
            }
        }

    }
}