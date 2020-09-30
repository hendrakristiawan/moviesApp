package com.hendra.movieapp.cache

import android.content.SharedPreferences

object SharedPref {
    private const val USER_LOGGED_IN = "user-logged-in"

    private var sharedPref: SharedPreferences? = null

    fun setSharedPreference(sharedPref: SharedPreferences) {
        this.sharedPref = sharedPref
    }

    fun saveSuccessSignIn(email: String) {
        sharedPref?.edit()?.putString(USER_LOGGED_IN, email)?.apply()
    }

    fun getSessionSignIn(): String = sharedPref?.getString(USER_LOGGED_IN, "") ?: ""

    fun clearData() {
        sharedPref?.edit()?.clear()?.apply()
    }
}