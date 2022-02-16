package com.ridhoafnidev.project.core_data.data.remote.response.perumahan

import com.ridhoafnidev.project.core_data.data.remote.response.ListPerumahanItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PerumahanResponse(
    @Json(name = "code")
    val code: Int,
    @Json(name = "status")
    val status: String = "",
    @Json(name = "message")
    val message: String = "",
    @Json(name = "result")
    val result: ListPerumahanItem = null,
)