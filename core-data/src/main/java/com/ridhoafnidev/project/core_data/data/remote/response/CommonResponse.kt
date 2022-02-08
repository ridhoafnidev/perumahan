package com.ridhoafnidev.project.core_data.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommonResponse(
    @Json(name = "code")
    val code: Int,
    @Json(name = "status")
    val status: String = "",
    @Json(name = "message")
    val message: String = "",
    @Json(name = "result")
    val result: String = "",
)
