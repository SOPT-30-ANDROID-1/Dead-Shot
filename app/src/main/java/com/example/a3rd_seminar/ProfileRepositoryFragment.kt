package com.example.a3rd_seminar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a3rd_seminar.databinding.FragmentProfileFollowerBinding
import com.example.a3rd_seminar.databinding.FragmentProfileRepositoryBinding

class ProfileRepositoryFragment : Fragment() {
    private lateinit var repositoryAdapter : RepositoryAdapter

    private var _binding : FragmentProfileRepositoryBinding? = null
    private val binding get() = _binding ?: error("바인딩이 초기화 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileRepositoryBinding.inflate(layoutInflater, container, false)
        initAdapter()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter(){

        repositoryAdapter = RepositoryAdapter()
        binding.rvRepository.adapter = repositoryAdapter
        repositoryAdapter.userList.addAll(
            listOf(
                UserData("안드로이드 과제 레포지토리1", "안드로이드 파트 과제"),
                UserData("안드로이드 과제 레포지토리2", "안드로이드 파트 과제"),
                UserData("안드로이드 과제 레포지토리13", "안드로이드 파트 과제"),
                UserData("안드로이드 과제 레포지토리14", "안드로이드 파트 과제"),
                UserData("안드로이드 과제 레포지토리15", "안드로이드 파트 과제"),
                UserData("안드로이드 과제 레포지토리16", "안드로이드 파트 과제")
            )
        )
        repositoryAdapter.notifyDataSetChanged()
    }
}