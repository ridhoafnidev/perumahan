package com.ridhoafnidev.project.core_data.data.remote.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import okhttp3.MultipartBody

@JsonClass(generateAdapter = true)
data class CalonPemilikRequest(
    @Json(name = "konsumen_id")
    val konsumenId: Int,
    @Json(name = "tipe_rumah_id")
    val tipeRumahId: Int,
    @Json(name = "rumah_id")
    val rumahId: Int,
    @Json(name = "jumlah_dp")
    val jumlahDp: Int,
    @Json(name = "bukti_transfer")
    val buktiTransfer: MultipartBody.Part
)
