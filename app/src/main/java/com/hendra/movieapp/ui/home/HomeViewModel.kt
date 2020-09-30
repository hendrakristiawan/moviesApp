package com.hendra.movieapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hendra.movieapp.ui.detail.DetailActivity
import com.hendra.movieapp.utils.Resource

class HomeViewModel : ViewModel() {

    private val mutableListPoster: MutableLiveData<Resource<List<Pair<Int, String>>>> =
        MutableLiveData()
    val listPosterLiveData: LiveData<Resource<List<Pair<Int, String>>>> = mutableListPoster

    init {
        mutableListPoster.postValue(
            Resource.success(
                listOf(
                    Pair(DetailActivity.TYPE_BATMAN, "batman_poster.jpg"),
                    Pair(DetailActivity.TYPE_FAST, "fast_furious_poster.jpg"),
                    Pair(DetailActivity.TYPE_HARRY, "harry_potter_poster.jpg"),
                    Pair(DetailActivity.TYPE_SPIDER, "spider_man_poster.jpg"),
                    Pair(DetailActivity.TYPE_STAR, "star_wars_poster.jpg")
                )
            )
        )
    }
}