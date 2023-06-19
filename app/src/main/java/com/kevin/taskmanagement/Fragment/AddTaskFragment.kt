package com.kevin.taskmanagement.Fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.kevin.taskmanagement.Database.RoomDB
import com.kevin.taskmanagement.Enitiy.TaskEnitiy
import com.kevin.taskmanagement.databinding.FragmentAddTaskBinding
import java.text.SimpleDateFormat
import java.util.Date

class AddTaskFragment : Fragment() {

    lateinit var binding: FragmentAddTaskBinding
    lateinit var db: RoomDB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentAddTaskBinding.inflate(layoutInflater)
        db = RoomDB.init(context)
        AddData()

        return binding.root
    }

    private fun AddData() {

        binding.txtdate.setOnClickListener {

            var date = Date()

            var format1 = SimpleDateFormat("dd-MM-YYYY")
            var currentDate = format1.format(date)

            var dates = currentDate.split("-")
            binding.txtdate.text = currentDate

            var dialog =
                DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {

                        var Year = p1
                        var Month = p2 + 1
                        var Date = p3

                        var selectedDate = "$p3-${(p2 + 1)}-$p1"
                        binding.txtdate.text = selectedDate
                    }

                }, dates[2].toInt(), dates[1].toInt() - 1, dates[0].toInt())
            dialog.show()
        }

        binding.txtTime.setOnClickListener {

            var date = Date()

            var format2 = SimpleDateFormat("hh:mm a")
            var currentTime = format2.format(date)

            binding.txtTime.text = currentTime
            var seleTime = currentTime
            var dialog1 = TimePickerDialog(context, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {

                    var hour = p1
                    var minute = p2
                    var selectedTime = "$p1:$p2"
                    binding.txtTime.text = selectedTime
                }

            }, 10, 0, true)
            dialog1.show()
        }

        binding.btnsubmit.setOnClickListener {

            var title = binding.edtTask.text.toString()
            var text = binding.edtdescription.text.toString()
            var date = binding.txtdate.text.toString()
            var month = binding.txtdate.text.toString()
            var year = binding.txtdate.text.toString()
            var hour = binding.txtTime.text.toString()
            var minute = binding.txtTime.text.toString()
            var format = SimpleDateFormat("DD-MM-YYYY hh:mm")
            var current = format.format(Date())
            var data = TaskEnitiy(title ,text, date, month, year, hour, minute)
            db.task().AddTask(data)

//            if (title.isEmpty() || text.isEmpty()) {
//                Toast.makeText(context, "Please enter data", Toast.LENGTH_SHORT).show()
//            } else {
//                binding.edtTask.setText("")
//                binding.edtdescription.setText("")
//            }
        }

    }

}