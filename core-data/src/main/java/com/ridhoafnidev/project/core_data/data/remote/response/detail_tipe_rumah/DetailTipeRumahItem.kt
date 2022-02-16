package com.ridhoafnidev.project.core_data.data.remote.response.detail_tipe_rumah

import com.ridhoafnidev.project.core_data.data.remote.response.ListFotoItem
import com.ridhoafnidev.project.core_data.data.remote.response.ListPerumahanItem
import com.ridhoafnidev.project.core_data.data.remote.response.toDomain
import com.ridhoafnidev.project.core_domain.model.detail_tipe_rumah.DetailTipeRumah
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailTipeRumahItem(

    @Json(name="id")
    val id: Int? = null,

    @Json(name="perumahan_id")
    val perumahanId: Int? = null,

    @Json(name="perumahan")
    val perumahan: ListPerumahanItem = null,

    @Json(name="foto")
    val foto: ListFotoItem = null,

    @Json(name="ukuran")
    val ukuran: String? = null,

    @Json(name="pondasi")
    val pondasi: String? = null,

    @Json(name="dinding_km")
    val dindingKm: String? = null,

    @Json(name="kusen")
    val kusen: String? = null,

    @Json(name="air")
    val air: String? = null,

    @Json(name="listrik")
    val listrik: String? = null,

    @Json(name="pintu_depan")
    val pintuDepan: String? = null,

    @Json(name="harga")
    val harga: String? = null,

    @Json(name="lantai")
    val lantai: String? = null,

    @Json(name="p_atap")
    val pAtap: String? = null,

    @Json(name="jumlah_unit")
    val jumlahUnit: String? = null,

    @Json(name="nama_tipe")
    val namaTipe: String? = null,

    @Json(name="sanitasi")
    val sanitasi: String? = null,

    @Json(name="dinding")
    val dinding: String? = null,

    @Json(name="plafon")
    val plafon: String? = null,

    @Json(name="r_atap")
    val rAtap: String? = null
)

fun DetailTipeRumahItem.toDomain(): DetailTipeRumah =
    DetailTipeRumah(
        id = id ?: 0,
        perumahanId = perumahanId ?: 0,
        perumahan = perumahan.toDomain(),
        foto = foto.toDomain(),
        ukuran = ukuran ?: "",
        pondasi = pondasi ?: "",
        dindingKm = dindingKm ?: "",
        kusen = kusen ?: "",
        air = air ?: "",
        listrik = listrik ?: "",
        pintuDepan = pintuDepan ?: "",
        harga = harga ?: "",
        lantai = lantai ?: "",
        pAtap = pAtap ?: "",
        jumlahUnit = jumlahUnit ?: "",
        namaTipe = namaTipe ?: "",
        sanitasi = sanitasi ?: "",
        dinding = dinding ?: "",
        plafon = plafon ?: "",
        rAtap = rAtap ?: ""
    )
