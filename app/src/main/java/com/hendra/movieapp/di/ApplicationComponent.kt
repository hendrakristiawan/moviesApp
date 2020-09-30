package com.hendra.movieapp.di

import com.hendra.movieapp.MoviesApp
import com.hendra.movieapp.di.module.RemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        RemoteModule::class,
        MainActivityModule::class
    ]
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MoviesApp): Builder

        fun build(): ApplicationComponent
    }

    fun inject(listAccountApp: MoviesApp)
}