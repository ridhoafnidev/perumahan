package com.ridhoafnidev.project.core_data.data.remote.response

import com.ridhoafnidev.project.core_domain.model.FotoGetAll
import com.ridhoafnidev.project.core_domain.model.ListFotoGetAll
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FotoItem(

    @Json(name="id")
    val id: Int? = null,

    @Json(name="tipe_rumah_id")
    val tipeRumahId: Int? = null,

    @Json(name="foto")
    val foto: String? = null
)

typealias ListFotoItem = List<FotoItem>?

fun ListFotoItem.toDomain(): ListFotoGetAll =
    this?.map {
        it.toDomain()
    } ?:
    emptyList()

fun FotoItem.toDomain(): FotoGetAll =
    FotoGetAll(
        id = id ?: 0,
        tipeRumahId = tipeRumahId ?: 0,
        foto = foto ?: ""
    )
