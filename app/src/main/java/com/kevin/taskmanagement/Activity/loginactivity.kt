package com.kevin.taskmanagement.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kevin.taskmanagement.Database.RoomDB
import com.kevin.taskmanagement.Enitiy.LoginEntity
import com.kevin.taskmanagement.Enitiy.TaskEnitiy
import com.kevin.taskmanagement.R
import com.kevin.taskmanagement.databinding.ActivityLoginactivityBinding
import com.kevin.taskmanagement.databinding.ActivityIntroductionPageBinding
import com.kevin.taskmanagement.databinding.ActivityLoginactivityBinding

class loginactivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginactivityBinding


    lateinit var binding: ActivityLoginactivityBinding
    lateinit var db: RoomDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.login.setOnClickListener {
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }

        binding.register.setOnClickListener {
            val mainIntent = Intent(this, RegisterActivity::class.java)
            startActivity(mainIntent)
            finish()
        }


        binding = ActivityLoginactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }
        initView()
    }

    private fun initView() {
        binding.login.setOnClickListener {

            var username = binding.username.text.toString()
            var lpassword = binding.password.text.toString()

            if (username.isEmpty() || lpassword.isEmpty()) {
                Toast.makeText(this, "Please enter data", Toast.LENGTH_SHORT).show()
            } else {
                binding.username.setText("")
                binding.password.setText("")
                var userdata = LoginEntity(username, lpassword)
                db.task().LoginEntity(userdata)
            }
        }
    }
}