package com.kevin.taskmanagement.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.RecyclerView
import com.kevin.taskmanagement.Adapter.TaskAdapter
import com.kevin.taskmanagement.Database.RoomDB
import com.kevin.taskmanagement.Enitiy.TaskEnitiy
import com.kevin.taskmanagement.R
import com.kevin.taskmanagement.databinding.FragmentHomeBinding
import java.util.Locale

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    var Tasklist = ArrayList<TaskEnitiy>()
    lateinit var db: RoomDB
    lateinit var adapter: TaskAdapter
    var recyclerView: RecyclerView = binding.rcvtasklist

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        db = RoomDB.init(context)
        recyclerView = binding.rcvtasklist
        recyclerView.adapter = adapter

        var list = db.task().GetTask()
        adapter = TaskAdapter(list as ArrayList<TaskEnitiy>)

        binding.rcvtasklist.layoutManager = LinearLayoutManager(context)
        binding.rcvtasklist.adapter = adapter

        searchData()

        adapter = TaskAdapter(Tasklist)
        recyclerView.adapter = adapter

        return binding.root
    }

    private fun searchData() {

//        var getTask : ArrayList<TaskEnitiy>
//        var gettask =getTask
//            binding.tasksearch.setOnQueryTextListener(object : OnQueryTextListener{
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    return false
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    filteredList(newText)
//                    return true
//                }
//
//                private fun filteredList(query: String?) {
//                    if(query != null) {
//                        var filteredList = ArrayList<TaskEnitiy>()
//                        for (i in getTask) {
//                            if (i.title.lowercase(Locale.ROOT).contains(query)) {
//                                filteredList.add(i)
//                            }
//                        }
//
//                        if (filteredList.isEmpty()) {
//                            Toast.makeText(context, "Enter valid data...", Toast.LENGTH_SHORT).show()
//                        } else {
//                            adapter.setFilteredList(filteredList)
//                        }
//                    }
//                }
//
//            })
    }
}