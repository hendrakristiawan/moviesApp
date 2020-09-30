package com.hendra.movieapp.utils

open class Resource<out T> constructor(
    val status: ResourceState,
    val data: T?,
    val errorResource: ErrorResource?
) {

    companion object {
        fun <T> success(data: T): Resource<T> = Resource(ResourceState.SUCCESS, data, null)

        fun <T> error(errorResource: ErrorResource): Resource<T> =
            Resource(ResourceState.ERROR, null, errorResource)

        fun <T> loading(): Resource<T> = Resource(ResourceState.LOADING, null, null)

    }
}