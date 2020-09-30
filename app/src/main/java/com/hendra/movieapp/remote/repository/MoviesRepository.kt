package com.hendra.movieapp.remote.repository

import com.hendra.movieapp.remote.model.PosterModel
import com.hendra.movieapp.remote.services.MoviesService
import com.hendra.movieapp.ui.detail.DetailActivity
import io.reactivex.Single

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(private val service: MoviesService) {

    fun search(type: Int): Single<List<PosterModel>> {
        val query: String = when (type) {
            DetailActivity.TYPE_BATMAN -> "batman"
            DetailActivity.TYPE_FAST -> "fast furious"
            DetailActivity.TYPE_HARRY -> "Harry Potter"
            DetailActivity.TYPE_SPIDER -> "Spider Man"
            DetailActivity.TYPE_STAR -> "Star Wars"
            else -> ""
        }
        return service.getUsers("215327e1", query, 1).map { it.items }
    }
}