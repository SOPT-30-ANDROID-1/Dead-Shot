package com.example.a3rd_seminar.ui.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.a3rd_seminar.data.SOPTSharedPreferences
import com.example.a3rd_seminar.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        initListener()

        setContentView(binding.root)
    }

    private fun initListener(){
        binding.btAutoLoginRelease.setOnClickListener(){
            SOPTSharedPreferences.setLogout(this)
            Toast.makeText(this, "자동완성이 취소되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}

