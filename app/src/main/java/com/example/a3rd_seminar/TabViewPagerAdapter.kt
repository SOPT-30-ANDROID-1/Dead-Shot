package com.example.a3rd_seminar

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    val fragments = mutableListOf<Fragment>()

    override fun getItemCount(): Int = fragments.size

    //무조건 새로운 프라그먼트의 객체를 즉각 생성하여 반환해야함 수정요망
    override fun createFragment(position: Int): Fragment = fragments[position]
}

