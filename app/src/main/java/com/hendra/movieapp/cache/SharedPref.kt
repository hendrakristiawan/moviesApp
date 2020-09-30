package com.hendra.movieapp.cache

import android.content.SharedPreferences

object SharedPref {
    private const val USER_LOGGED_IN = "user-logged-in"

    private var sharedPref: SharedPreferences? = null

    fun setSharedPreference(sharedPref: SharedPreferences) {
        this.sharedPref = sharedPref
    }

    fun saveSuccessSignIn() {
        sharedPref?.edit()?.putBoolean(USER_LOGGED_IN, true)?.apply()
    }

    fun getSessionSignIn(): Boolean = sharedPref?.getBoolean(USER_LOGGED_IN, false) ?: false

    fun clearData() {
        sharedPref?.edit()?.clear()?.apply()
    }
}