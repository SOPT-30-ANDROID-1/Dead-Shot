package com.example.a3rd_seminar.ui.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a3rd_seminar.ui.signup.SignUpActivity
import com.example.a3rd_seminar.data.SOPTSharedPreferences
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

        SOPTSharedPreferences.init(this)
        isAutoLogin()
        initEvent()
        initClickEvent()

        binding.btSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
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

        //처음 로그인 시 requestSignIn에 정보를 저장함과 동시에 sharedPreferences 에도 정보 저장
        SOPTSharedPreferences.setUserData(
            id = binding.etGithubId.text.toString(),
            passWord = binding.etPassword.text.toString()
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

    //체크박스를 통해 자동 로그인 여부를 결정
    private fun initClickEvent() {
        binding.rbAutoSignIn.setOnClickListener {
            binding.rbAutoSignIn.isSelected = !binding.rbAutoSignIn.isSelected

            SOPTSharedPreferences.setAutoLogin(binding.rbAutoSignIn.isSelected)
        }
    }

    //자동 로그인 설정이 되어있다면, 로그인 버튼을 클릭하기 전에 sharedPreferences 에 미리 저장되어있던 id,
    //password 를 활용하여 로그인 시도 loginNetwork() 함수와 중복되는 부분이 많아 개선할 부분이 많아보임.
    private fun isAutoLogin() {
        if (SOPTSharedPreferences.getAutoLogin()) {

            val requestSignIn = RequestSignIn(
                id = SOPTSharedPreferences.getUserID(),
                password = SOPTSharedPreferences.getUserPassWord()
            )

            val call: Call<ResponseSignIn> = ServiceCreator.soptService.signIn((requestSignIn))
            call.enqueueUtil(
                onSuccess = {
                    Toast.makeText(
                        this@SignInActivity,
                        "${it.data.email}님 반갑습니다! 자동 로그인 되었습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
                },
                onError = {
                    Toast.makeText(this@SignInActivity, "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
                }
            )
            finish()
        }
    }
}
