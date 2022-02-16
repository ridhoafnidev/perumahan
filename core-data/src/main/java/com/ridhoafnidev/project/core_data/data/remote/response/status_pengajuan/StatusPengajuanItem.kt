package com.ridhoafnidev.project.core_data.data.remote.response.status_pengajuan

import com.ridhoafnidev.project.core_domain.model.status_pengajuan.ListStatusPengajuan
import com.ridhoafnidev.project.core_domain.model.status_pengajuan.StatusPengajuan
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatusPengajuanItem(

    @Json(name="id")
    val id: Int? = null,

    @Json(name="nama")
    val nama: String? = null
)

typealias ListStatusPengajuanItem = List<StatusPengajuanItem>?

fun ListStatusPengajuanItem.toDomain(): ListStatusPengajuan =
    this?.map {
        it.toDomain()
    } ?:
    emptyList()

fun StatusPengajuanItem.toDomain(): StatusPengajuan =
    StatusPengajuan(
        id = id ?: 0,
        nama = nama ?: ""
    )