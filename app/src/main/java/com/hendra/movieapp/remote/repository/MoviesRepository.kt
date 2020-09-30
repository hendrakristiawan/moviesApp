package com.hendra.movieapp.remote.repository

import com.hendra.movieapp.remote.services.MoviesService

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(private val service: MoviesService)