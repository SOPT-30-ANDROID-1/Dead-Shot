package com.example.a3rd_seminar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a3rd_seminar.databinding.FragmentHomeBinding
import com.example.a3rd_seminar.databinding.FragmentProfileFollowerBinding


class ProfileFollowerFragment : Fragment() {
    private lateinit var followerAdapter : FollowerAdapter

    private var _binding : FragmentProfileFollowerBinding? = null
    private val binding get() = _binding ?: error("바인딩이 초기화 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileFollowerBinding.inflate(layoutInflater, container, false)
        initAdapter()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter(){

        followerAdapter = FollowerAdapter()
        binding.rvFollower.adapter = followerAdapter
        followerAdapter.userList.addAll(
            listOf(
                UserData("최우형", "안드로이드 YB"),
                UserData("최우형2", "안드로이드 YB"),
                UserData("최우형3", "안드로이드 YB"),
                UserData("최우형4", "안드로이드 YB"),
                UserData("최우형5", "안드로이드 YB"),
                UserData("최우형6", "안드로이드 YB")
            )
        )
        followerAdapter.notifyDataSetChanged()
    }
}