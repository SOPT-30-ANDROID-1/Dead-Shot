package com.example.a3rd_seminar.ui.on_boarding.on_boarding_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.a3rd_seminar.R
import com.example.a3rd_seminar.databinding.FragmentOnBoarding2Binding

class OnBoardingFragment2 : Fragment() {
    private var _binding: FragmentOnBoarding2Binding? = null
    private val binding get() = _binding ?: error("binding 이 초기화 되지 않았습니다.")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoarding2Binding.inflate(layoutInflater, container, false)

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment2_to_onBoardingFragment3)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}