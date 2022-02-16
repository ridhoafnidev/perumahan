package com.ridhoafnidev.project.core_data.data.remote

internal sealed class ApiResult<out T> {
    data class OnSuccess<out T>(val response: T) : ApiResult<T>()
    data class OnFailed(val exception: ApiException) : ApiResult<Nothing>()
}