package com.ridhoafnidev.project.core_data.data.remote.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterUserRequest(
    val username: String,
    val password: String,
    @Json(name = "nama_lengkap")
    val namaLengkap: String,
    val alamat: String,
    @Json(name = "no_hp")
    val noHp: String,
    val email: String,
    val role: String
)