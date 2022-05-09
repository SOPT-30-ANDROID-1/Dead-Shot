package com.example.a3rd_seminar.sever_tools

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("auth/signin")
    fun signIn(
        @Body body: RequestSignIn
    ): Call<ResponseSignIn>

    @POST("auth/signup")
    fun signUp(
        @Body body: RequestSignUp
    ): Call<ResponseSignUp>

}