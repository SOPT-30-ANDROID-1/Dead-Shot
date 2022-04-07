package com.example.sopt_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sopt_study.databinding.ActivityHomeBinding
import com.example.sopt_study.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}