package com.example.sopt_study

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sopt_study.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTransactionEvent()


    }


    private fun initTransactionEvent() {
        val followerPage = FollowerPageFragment()
        val repositoryPage = RepositoryPageFragment()

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, followerPage)
            .commit()


        binding.btnFollower.setOnClickListener {

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, followerPage).commit()


        }

        binding.btnRepository.setOnClickListener {

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, repositoryPage).commit()

        }
    }
}


