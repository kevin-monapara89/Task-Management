package com.kevin.taskmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.kevin.taskmanagement.databinding.ActivityIntroductionPageBinding

class IntroductionPage : AppCompatActivity() {

    lateinit var binding: ActivityIntroductionPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroductionPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnstart.setOnClickListener {
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }
    }
}