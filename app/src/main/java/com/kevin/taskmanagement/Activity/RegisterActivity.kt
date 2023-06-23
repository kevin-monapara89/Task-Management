package com.kevin.taskmanagement.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kevin.taskmanagement.R
import com.kevin.taskmanagement.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {


    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initView()
    }

    private fun initView() {
        binding.txtcontinue.setOnClickListener {

            var firstname = binding.edtName.text.toString()
            var lastname = binding.edtLastname.text.toString()
            var birthdate = binding.edtBirth.text.toString()
            var email = binding.edtEmail.text.toString()
            var password = binding.edtpassword.text.toString()

            if (firstname.isEmpty() || lastname.isEmpty() || birthdate.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter data", Toast.LENGTH_SHORT).show()
            } else {
                binding.edtName.setText("")
                binding.edtLastname.setText("")
                binding.edtBirth.setText("")
                binding.edtEmail.setText("")
                binding.edtpassword.setText("")

                binding.txtcontinue.setOnClickListener {
                    val mainIntent = Intent(this, MainActivity::class.java)
                    startActivity(mainIntent)
                    finish()
                }
            }
        }
    }
}