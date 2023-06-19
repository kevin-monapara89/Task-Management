package com.kevin.taskmanagement.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kevin.taskmanagement.Fragment.AddTaskFragment
import com.kevin.taskmanagement.Fragment.HomeFragment
import com.kevin.taskmanagement.Fragment.ListFragment
import com.kevin.taskmanagement.R
import com.kevin.taskmanagement.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())
        binding.bottom.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.add -> replaceFragment(AddTaskFragment())
                R.id.list -> replaceFragment(ListFragment())

                else -> {

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragpageview,fragment).commit()
    }
}