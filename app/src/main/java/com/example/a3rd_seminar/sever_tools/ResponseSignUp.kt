package com.example.a3rd_seminar.sever_tools

data class ResponseSignUp(
    val status: Int,
    val message : String,
    val data : Data
) {
    data class Data(
        val id : Int
    )
}

