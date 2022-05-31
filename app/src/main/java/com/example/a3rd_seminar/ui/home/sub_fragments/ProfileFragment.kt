package com.example.a3rd_seminar.ui.home.sub_fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a3rd_seminar.ui.home.sub_fragments.profile.ProfileFollowerFragment
import com.example.a3rd_seminar.ui.home.sub_fragments.profile.ProfileRepositoryFragment
import com.example.a3rd_seminar.R
import com.example.a3rd_seminar.databinding.FragmentProfileBinding
import com.example.a3rd_seminar.ui.setting.SettingActivity


class ProfileFragment : Fragment() {
    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding ?: error("바인딩이 초기화 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        initSettingButtonListener()
        initTransactionEvent()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initTransactionEvent() {
        val followerPage = ProfileFollowerFragment()
        val repositoryPage = ProfileRepositoryFragment()

        childFragmentManager.beginTransaction().add(R.id.fragment_container_profile, followerPage)
            .commit()

        binding.btFollower.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_profile, followerPage).commit()
            binding.btFollower.isSelected = binding.btFollower.isSelected != true
            binding.btRepository.isSelected = false
        }

        binding.btRepository.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_profile, repositoryPage).commit()
            binding.btRepository.isSelected = binding.btRepository.isSelected != true
            binding.btFollower.isSelected = false

        }
    }

    private fun initSettingButtonListener(){
        binding.ivSetting.setOnClickListener(){
            val intent = Intent(activity, SettingActivity::class.java); //fragment라서 activity intent와는 다른 방식
            startActivity(intent);
        }
    }




}