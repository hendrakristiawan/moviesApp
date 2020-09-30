package com.hendra.movieapp.remote.model

import com.google.gson.annotations.SerializedName

data class ModelResponse(
    @SerializedName("Search")
    val items: List<PosterModel>
)
