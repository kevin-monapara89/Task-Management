package com.kevin.taskmanagement.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevin.taskmanagement.Adapter.TaskAdapter
import com.kevin.taskmanagement.R
import com.kevin.taskmanagement.databinding.ActivityIntroductionPageBinding
import com.kevin.taskmanagement.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        adapter = TaskAdapter()
        binding.rcvtasklist.layoutManager = LinearLayoutManager(context)
        binding.rcvtasklist.adapter = adapter
        return binding.root
    }

}