package com.example.a3rd_seminar.ui.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a3rd_seminar.databinding.ActivitySignUpBinding
import com.example.a3rd_seminar.sever_tools.RequestSignUp
import com.example.a3rd_seminar.sever_tools.ResponseSignUp
import com.example.a3rd_seminar.sever_tools.ServiceCreator
import com.example.a3rd_seminar.ui.signin.SignInActivity
import com.example.a3rd_seminar.util.enqueueUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent()
    }

/*
** 서버 통신 전에 공란 확인용 토스트 메시지 실행 코드
**
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

 */

    private fun initEvent() {
        binding.btSignUp.setOnClickListener {
            signUpNetwork()
        }
    }

    private fun signUpNetwork() {
        val requestSignUp = RequestSignUp(
            binding.etName.text.toString(),
            binding.etGithubId.text.toString(),
            binding.etPassword.text.toString()
        )

        val call: Call<ResponseSignUp> = ServiceCreator.soptService.signUp(requestSignUp)


        call.enqueueUtil(
            onSuccess = {

                Toast.makeText(
                    this@SignUpActivity,
                    it.message,
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                intent.apply {
                    putExtra("id", requestSignUp.email)
                    putExtra("password", requestSignUp.password)
                }
                setResult(RESULT_OK, intent)
                finish()
            },
            onError = {
                Toast.makeText(this@SignUpActivity, "회원가입 실패하셨습니다.", Toast.LENGTH_SHORT)
                    .show()
            }
        )


    }
}
