package com.hendra.movieapp.di.module

import com.google.gson.GsonBuilder
import com.hendra.movieapp.remote.services.MoviesService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class RemoteModule {

    companion object {
        private const val BASE_URL = "http://omdbapi.com/"
        private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    }

    private val gson = GsonBuilder()
        .setLenient()
        .setDateFormat(DATE_FORMAT)
        .create()

    private val okHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    //
    @Singleton
    @Provides
    fun provideMoviesService(retrofit: Retrofit) = retrofit.create(MoviesService::class.java)

}