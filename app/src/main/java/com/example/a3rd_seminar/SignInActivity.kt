package com.example.a3rd_seminar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a3rd_seminar.databinding.ActivitySignInBinding
import com.example.a3rd_seminar.sever_tools.RequestSignIn
import com.example.a3rd_seminar.sever_tools.ResponseSignIn
import com.example.a3rd_seminar.sever_tools.ServiceCreator
import com.example.a3rd_seminar.ui.home.HomeActivity
import com.example.a3rd_seminar.util.enqueueUtil
import retrofit2.Call

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent()
        initButtonClickListener()
        binding.rbAutoSignIn.isSelected = true

        binding.btSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initEvent() {
        binding.btLogIn.setOnClickListener {
            loginNetwork()
        }
    }

    private fun loginNetwork() {
        val requestSignIn = RequestSignIn(
            id = binding.etGithubId.text.toString(),
            password = binding.etPassword.text.toString()
        )
        val call: Call<ResponseSignIn> = ServiceCreator.soptService.signIn((requestSignIn))
        call.enqueueUtil(
            onSuccess = {
                Toast.makeText(
                    this@SignInActivity,
                    "${it.data.email}님 반갑습니다!",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
            },
            onError = {
                Toast.makeText(this@SignInActivity, "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun initButtonClickListener() {
        binding.rbAutoSignIn.setOnClickListener {
            binding.rbAutoSignIn.isSelected = !binding.rbAutoSignIn.isSelected
        }
    }
}