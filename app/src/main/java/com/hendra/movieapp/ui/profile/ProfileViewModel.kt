package com.hendra.movieapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hendra.movieapp.cache.SharedPref
import com.hendra.movieapp.utils.Resource

class ProfileViewModel : ViewModel() {

    private val mutableEmail: MutableLiveData<Resource<String>> = MutableLiveData()
    val emailLiveData: LiveData<Resource<String>> = mutableEmail

    init {
        mutableEmail.postValue(Resource.success(SharedPref.getSessionSignIn()))
    }
}