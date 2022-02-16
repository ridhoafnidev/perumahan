package com.ridhoafnidev.project.core_data.data.remote.response.calon_pemilik

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CalonPemilikResponse(

	@Json(name="result")
	val result: ListCalonPemilikItem = null,

	@Json(name="code")
	val code: Int? = null,

	@Json(name="message")
	val message: String? = null,

	@Json(name="status")
	val status: String? = null
)