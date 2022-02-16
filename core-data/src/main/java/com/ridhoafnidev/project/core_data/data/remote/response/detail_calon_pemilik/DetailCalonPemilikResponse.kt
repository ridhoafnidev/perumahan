package com.ridhoafnidev.project.core_data.data.remote.response.detail_calon_pemilik

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailCalonPemilikResponse(

	@Json(name="result")
	val result: DetailCalonPemilikItem,

	@Json(name="code")
	val code: Int? = null,

	@Json(name="message")
	val message: String? = null,

	@Json(name="status")
	val status: String? = null
)