package com.hendra.movieapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hendra.movieapp.remote.model.PosterModel
import com.hendra.movieapp.remote.repository.MoviesRepository
import com.hendra.movieapp.utils.ErrorResource
import com.hendra.movieapp.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ContainerViewModel @Inject constructor(private val repository: MoviesRepository) :
    ViewModel() {

    private val disposable = CompositeDisposable()
    private val mutableListPoster: MutableLiveData<Resource<List<String>>> = MutableLiveData()
    val listPosterLiveData: LiveData<Resource<List<String>>> = mutableListPoster

    fun doSearch(type: Int) {
        mutableListPoster.postValue(Resource.loading())
        disposable.add(
            repository.search(type).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<PosterModel>>() {
                    override fun onSuccess(t: List<PosterModel>) {
                        mutableListPoster.postValue(Resource.success(t.map { it.poster }.toList()))
                    }

                    override fun onError(e: Throwable) {
                        mutableListPoster.postValue(Resource.error(ErrorSearch.SearchFailed))
                    }
                })
        )
    }
}

enum class ErrorSearch : ErrorResource {
    SearchFailed
}