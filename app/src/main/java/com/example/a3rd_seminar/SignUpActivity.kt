package com.example.a3rd_seminar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.a3rd_seminar.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btSignUp.setOnClickListener {
            val name = binding.etName.text.toString()
            val id = binding.etGithubId.text.toString()
            val password = binding.etPassword.text.toSet()

            //항목을 다 채우지 않았을 경우 토스트 메시지 띄우기
            if (name.isEmpty() || id.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            }
            //다 채워진 경우 회원 가입 페이지 종료
            else {
                finish()
            }

        }
    }
}