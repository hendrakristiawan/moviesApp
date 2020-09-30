package com.hendra.movieapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hendra.movieapp.utils.Resource

class HomeViewModel : ViewModel() {

    private val mutableListPoster: MutableLiveData<Resource<List<String>>> = MutableLiveData()
    val listPosterLiveData: LiveData<Resource<List<String>>> = mutableListPoster

    init {
        mutableListPoster.postValue(
            Resource.success(
                listOf(
                    "batman_poster.jpg",
                    "fast_furious_poster.jpg",
                    "harry_potter_poster.jpg",
                    "spider_man_poster.jpg",
                    "star_wars_poster.jpg"
                )
            )
        )
    }
}