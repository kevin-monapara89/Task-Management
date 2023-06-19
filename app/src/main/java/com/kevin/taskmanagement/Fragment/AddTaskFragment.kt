package com.kevin.taskmanagement.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        return binding.root

        db = RoomDB.init(context)

        AddData()

    }

    private fun AddData() {

        binding.txtdate.setOnClickListener {

            var date = Date()

            var format1 = SimpleDateFormat("dd-MM-YYYY")
            var currentDate = format1.format(date)

            var dates = currentDate.split("-")
            binding.txtdate.text = currentDate


        }

        binding.txtTime.setOnClickListener {

            var date = Date()

            var format2 = SimpleDateFormat("hh:mm a")
            var currentTime = format2.format(date)

            binding.txtTime.text = currentTime
            var seleTime = currentTime

        }

        binding.btnsubmit.setOnClickListener {


            var title = binding.edtTask.text.toString()
            var text = binding.edtText.text.toString()
            var date = binding.txtdate.text.toString()
            var month = binding.txtdate.text.toString()
            var year = binding.txtdate.text.toString()
            var hour = binding.txtTime.text.toString()
            var minute = binding.txtTime.text.toString()
            var format = SimpleDateFormat("DD-MM-YYYY hh:mm")
            var current = format.format(Date())
            var data = TaskEnitiy(title ,text, date, month, year, hour, minute)
            db.task().AddTask(data)

        }

    }


}