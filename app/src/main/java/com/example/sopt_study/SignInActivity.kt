package com.example.sopt_study

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sopt_study.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logInButton.setOnClickListener{

            val id = binding.editTextId.text.toString()
            val password = binding.editTextPassword.text.toString()

            //항목을 다 채우지 않았을 경우 토스트 메시지 띄우기
            if(id.isEmpty() || password.isEmpty() ){
                Toast.makeText(this, "아이디/비밀번호를 확인해 주세요", Toast.LENGTH_SHORT).show()
            }
            //다 채워진 경우 HomeActivity 로 이동
            else{
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
        binding.signUpButton.setOnClickListener {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        }
    }
}