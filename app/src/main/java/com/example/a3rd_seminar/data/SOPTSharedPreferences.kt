package com.example.a3rd_seminar.data

import android.content.Context
import android.content.SharedPreferences

object SOPTSharedPreferences {
    private const val STORAGE_KEY = "USER_AUTH"
    private const val AUTO_LOGIN = "AUTO_LOGIN"
    private const val ID = "ID"
    private const val PASS_WORD = "PASS_WORD"

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
    }

    fun getAutoLogin(): Boolean {
        return preferences.getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(value: Boolean) {
        preferences.edit()
            .putBoolean(AUTO_LOGIN, value)
            .apply()
    }

    //ID, PASS_WORD 를 키 값으로 하는 id, password데이터 저장하는 함수
    fun setUserData(id: String, passWord: String) {
        preferences.edit().putString(ID, id).apply()
        preferences.edit().putString(PASS_WORD, passWord).apply()
    }

    //ID 를 가져오는 함수
    fun getUserID(): String {
        return preferences.getString(ID, "").toString()
    }

    //위와 동일
    fun getUserPassWord(): String {
        return preferences.getString(PASS_WORD, "").toString()
    }

    fun setLogout(context: Context) {
        preferences = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
        preferences.edit()
            .remove(AUTO_LOGIN)
            .clear()
            .apply()
    }
}