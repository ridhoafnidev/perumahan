package com.ridhoafnidev.project.core_data.data.remote.response.tipe_rumah

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TipeRumahResponse(

	@Json(name="result")
	val result: ListTipeRumahItem = null,

	@Json(name="code")
	val code: Int? = null,

	@Json(name="message")
	val message: String? = null,

	@Json(name="status")
	val status: String? = null
)
