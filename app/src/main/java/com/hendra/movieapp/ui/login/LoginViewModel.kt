package com.hendra.movieapp.ui.login

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hendra.movieapp.cache.SharedPref
import com.hendra.movieapp.utils.ErrorResource
import com.hendra.movieapp.utils.Resource

class LoginViewModel : ViewModel() {

    private val mutableSignIn: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val signInLiveData: LiveData<Resource<Unit>> = mutableSignIn

    init {
        checkSession()
    }

    private fun checkSession() {
        if (SharedPref.getSessionSignIn()) {
            mutableSignIn.postValue(Resource.success(Unit))
        }
    }

    fun signIn(email: String, password: String) {
        mutableSignIn.postValue(Resource.loading())

        when {
            !isValidEmail(email) -> mutableSignIn.postValue(Resource.error(ValidationType.INVALID_EMAIL))
            !isValidPassword(password) -> mutableSignIn.postValue(Resource.error(ValidationType.INVALID_PASSWORD))
            else -> {
                Handler(Looper.getMainLooper()).postDelayed({
                    SharedPref.saveSuccessSignIn()
                    mutableSignIn.postValue(Resource.success(Unit))
                }, 3000)
            }
        }
    }

    private fun isValidEmail(email: String): Boolean =
        email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPassword(password: String): Boolean =
        password.isNotEmpty() && password.length >= 8
}

enum class ValidationType : ErrorResource {
    INVALID_EMAIL, INVALID_PASSWORD,
}