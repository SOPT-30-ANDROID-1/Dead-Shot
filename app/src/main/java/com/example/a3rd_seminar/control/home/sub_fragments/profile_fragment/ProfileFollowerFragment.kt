package com.example.a3rd_seminar.control.home.sub_fragments.profile_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a3rd_seminar.FollowerAdapter
import com.example.a3rd_seminar.UserData
import com.example.a3rd_seminar.databinding.FragmentProfileFollowerBinding
import com.example.a3rd_seminar.server_github.GithubServiceCreator
import com.example.a3rd_seminar.server_github.ResponseFollowerList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFollowerFragment : Fragment() {
    private lateinit var followerAdapter: FollowerAdapter

    private var _binding: FragmentProfileFollowerBinding? = null
    private val binding get() = _binding ?: error("바인딩이 초기화 되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileFollowerBinding.inflate(layoutInflater, container, false)
        getFollowerList()
        //initAdapter()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getFollowerList() {
        followerAdapter = FollowerAdapter()
        binding.rvFollower.adapter = followerAdapter
        val username: String = "dn7638"


        val call: Call<ResponseFollowerList> =
            GithubServiceCreator.githubFollowerListService.getFollowerList(username = username)

        call.enqueue(object : Callback<ResponseFollowerList> {
            override fun onResponse(
                call: Call<ResponseFollowerList>,
                response: Response<ResponseFollowerList>
            ) {
                if (response.isSuccessful) {
                    val data: ResponseFollowerList? = response.body()

                    if (data != null) {
                        for (i in data) {

                            followerAdapter.userList.add(
                                UserData(
                                    i.login,
                                    i.htmlUrl
                                )
                            )

                        }
                        followerAdapter.notifyDataSetChanged()
                    }
                }

            }

            override fun onFailure(call: Call<ResponseFollowerList>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }
        })
    }

/*
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

 */
}