package com.hendra.movieapp.remote.model

import com.google.gson.annotations.SerializedName

data class PosterModel(
    @SerializedName("Poster")
    val poster: String
)