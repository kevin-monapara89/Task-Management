package com.kevin.taskmanagement.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevin.taskmanagement.Adapter.TaskAdapter
import com.kevin.taskmanagement.Database.RoomDB
import com.kevin.taskmanagement.Enitiy.TaskEnitiy
import com.kevin.taskmanagement.databinding.FragmentHomeBinding

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
        adapter = TaskAdapter(db.task().GetTask())
        binding.rcvtasklist.layoutManager = LinearLayoutManager(context)
        binding.rcvtasklist.adapter = adapter
    }

}