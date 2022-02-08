package com.ridhoafnidev.project.core_data.data.remote.response

import com.ridhoafnidev.project.core_domain.model.ListPerumahanGetAll
import com.ridhoafnidev.project.core_domain.model.PerumahanGetAll
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PerumahanItem(

    @Json(name="id")
    val id: Int? = null,

    @Json(name="nama_perumahan")
    val namaPerumahan: String? = null,

    @Json(name="keterangan")
    val keterangan: String? = null,

    @Json(name="luas_tanah")
    val luasTanah: Int? = null,

    @Json(name="alamat")
    val alamat: String? = null
)

typealias ListPerumahanItem = List<PerumahanItem>?

fun ListPerumahanItem.toDomain(): ListPerumahanGetAll =
    this?.map {
        it.toDomain()
    } ?:
    emptyList()

fun PerumahanItem.toDomain(): PerumahanGetAll =
    PerumahanGetAll(
        id = id ?: 0,
        namaPerumahan = namaPerumahan ?: "",
        keterangan = keterangan ?: "",
        luasTanah = luasTanah ?: 0,
        alamat = alamat ?: ""
    )
