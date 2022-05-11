package com.example.a3rd_seminar.server_github

import com.example.a3rd_seminar.sever_tools.RequestSignIn
import com.example.a3rd_seminar.sever_tools.RequestSignUp
import com.example.a3rd_seminar.sever_tools.ResponseSignIn
import com.example.a3rd_seminar.sever_tools.ResponseSignUp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubFollowerListService {
    @GET("users/{username}/following")
    fun getFollowerList (
        @Path("username") username:String
    ): Call<ResponseFollowerList>

}