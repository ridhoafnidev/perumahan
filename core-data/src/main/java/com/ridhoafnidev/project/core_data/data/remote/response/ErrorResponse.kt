package com.ridhoafnidev.project.core_data.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    val code: Int = 0,
    val result: String = "",
    val message: String = ""
)
