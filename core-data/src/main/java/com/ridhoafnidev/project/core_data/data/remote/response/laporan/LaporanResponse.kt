package com.ridhoafnidev.project.core_data.data.remote.response.laporan

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LaporanResponse(

	@Json(name="result")
	val result: ListLaporanItem = null,

	@Json(name="code")
	val code: Int? = null,

	@Json(name="message")
	val message: String? = null,

	@Json(name="status")
	val status: String? = null
)