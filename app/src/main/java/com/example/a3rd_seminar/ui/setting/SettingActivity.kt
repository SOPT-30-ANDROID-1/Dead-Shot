package com.example.a3rd_seminar.ui.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a3rd_seminar.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}