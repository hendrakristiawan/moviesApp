package com.hendra.movieapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hendra.movieapp.utils.ErrorResource
import com.hendra.movieapp.utils.Resource

class LoginViewModel : ViewModel() {

    private val mutableSignIn: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val signInLiveData: MutableLiveData<Resource<Unit>> = mutableSignIn

    fun signIn(email: String, password: String) {
        signInLiveData.postValue(Resource.loading())
        when {
            !isValidEmail(email) -> signInLiveData.postValue(Resource.error(ValidationType.INVALID_EMAIL))
            !isValidPassword(password) -> signInLiveData.postValue(Resource.error(ValidationType.INVALID_PASSWORD))
            else -> signInLiveData.postValue(Resource.success(Unit))
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return when {
            email.isEmpty() -> false
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> false
            else -> true
        }
    }

    private fun isValidPassword(password: String): Boolean {
        return when {
            password.isEmpty() -> false
            password.length < 8 -> false
            else -> true
        }
    }
}

enum class ValidationType : ErrorResource {
    INVALID_EMAIL, INVALID_PASSWORD,
}