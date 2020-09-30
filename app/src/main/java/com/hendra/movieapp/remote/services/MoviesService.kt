package com.hendra.movieapp.remote.services

import com.hendra.movieapp.remote.model.ModelResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesService {
    @GET("")
    fun getUsers(
        @Query("api_key") apiKey: String,
        @Query("s") query: String,
        @Query("page") page: Int
    ): Single<ModelResponse>
}