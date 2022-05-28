package com.example.a3rd_seminar.ui.home.sub_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a3rd_seminar.ui.home.sub_fragments.home_fragment.HomeTabFragment1
import com.example.a3rd_seminar.ui.home.sub_fragments.home_fragment.HomeTabFragment2
import com.example.a3rd_seminar.TabViewPagerAdapter
import com.example.a3rd_seminar.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding ?: error("바인딩이 초기화 되지 않았습니다.")
    private lateinit var tabViewPagerAdapter : TabViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        initAdapter()
        initTabLayout()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter(){
        val fragmentList = listOf(HomeTabFragment1(), HomeTabFragment2())

        tabViewPagerAdapter = TabViewPagerAdapter(this)
        tabViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpHome.adapter = tabViewPagerAdapter
    }

    private fun initTabLayout(){
        val tabLabel = listOf("팔로잉", "팔로워")

        TabLayoutMediator(binding.tabLayout, binding.vpHome){ tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }

}