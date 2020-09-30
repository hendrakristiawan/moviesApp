package com.hendra.movieapp

import android.app.Activity
import android.app.Application
import android.content.Context
import com.hendra.movieapp.cache.SharedPref
import com.hendra.movieapp.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MoviesApp : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        SharedPref.setSharedPreference(getSharedPreferences("movies-app", Context.MODE_PRIVATE))
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}