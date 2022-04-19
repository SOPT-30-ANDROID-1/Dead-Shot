package com.example.sopt_study

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.sopt_study.databinding.FragmentFollowerPageBinding


class FollowerPageFragment : Fragment() {
    private lateinit var followerAdapter: FollowerAdapter
    private var _binding: FragmentFollowerPageBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerPageBinding.inflate(layoutInflater, container, false)



        initAdapter()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter(){
        val myDecoration = MyDecoration(10f, 20f, Color.BLUE)

        followerAdapter = FollowerAdapter()
        binding.rvFollower.adapter = followerAdapter




        followerAdapter.userList.addAll(
            listOf(
                UserData("이강민", "1가나다라마바사아자차카타파하2가나다라마바사아자차카타파하3가나다라마바사아자차카타파하4가나다라마바사아자차카타파하5가나다라마바사아자차카타파하6"),
                UserData("이강민", "안드로이드 파트장"),
                UserData("이강민", "안드로이드 파트장"),
                UserData("이강민", "안드로이드 파트장"),
                UserData("이강민", "안드로이드 파트장"),
                UserData("이강민", "안드로이드 파트장")
            )
        )
        followerAdapter.notifyDataSetChanged()
    }
}