package com.example.a3rd_seminar.control.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.a3rd_seminar.*
import com.example.a3rd_seminar.control.home.sub_fragments.CameraFragment
import com.example.a3rd_seminar.control.home.sub_fragments.HomeFragment
import com.example.a3rd_seminar.control.home.sub_fragments.ProfileFragment
import com.example.a3rd_seminar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var soptViewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavi()
        initAdapter()
    }

    private fun initAdapter() {
        val fragmentList = listOf(ProfileFragment(), HomeFragment(), CameraFragment())
        soptViewPagerAdapter = ViewPagerAdapter(this)
        soptViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpMain.adapter = soptViewPagerAdapter
    }

    private fun initBottomNavi() {
        binding.vpMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.bnvMain.menu.getItem(position).isChecked = true
            }
        })

       binding.bnvMain.setOnItemSelectedListener {
           when(it.itemId) {
               R.id.menu_profile -> {
                   binding.vpMain.currentItem = FIRST_FRAGMENT
                   true
               }
               R.id.menu_home -> {
                   binding.vpMain.currentItem = SECOND_FRAGMENT
                   true
               }
               else -> {
                   binding.vpMain.currentItem = THIRD_FRAGMENT
                   //true 만 써도 될 것 같습니다. 모든 경우에서 return을 따로 안처리해주고 적용해보기
                   return@setOnItemSelectedListener true
               }
           }

       }


    }

    companion object {
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
        const val THIRD_FRAGMENT = 2
    }
}