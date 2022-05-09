package com.example.a3rd_seminar.sever_tools

data class ResponseSignIn(
    val status: Int,
    val message : String,
    val data : Data
) {
    data class Data(
        val name: String,
        val email: String
    )
}
