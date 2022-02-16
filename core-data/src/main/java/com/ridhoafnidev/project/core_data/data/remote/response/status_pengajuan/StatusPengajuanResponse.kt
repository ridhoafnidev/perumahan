package com.ridhoafnidev.project.core_data.data.remote.response.status_pengajuan

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatusPengajuanResponse(

	@Json(name="result")
	val result: ListStatusPengajuanItem,

	@Json(name="code")
	val code: Int? = null,

	@Json(name="message")
	val message: String? = null,

	@Json(name="status")
	val status: String? = null
)