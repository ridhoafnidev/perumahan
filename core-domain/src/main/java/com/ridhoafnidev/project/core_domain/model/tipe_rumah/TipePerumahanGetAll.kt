package com.ridhoafnidev.project.core_domain.model.tipe_rumah

data class TipePerumahanGetAll(
    val id: Int,
    val perumahanId: Int,
    val perumahan: ListPerumahanGetAll,
    val foto: ListFotoGetAll,
    val ukuran: String,
    val pondasi: String,
    val dindingKm: String,
    val kusen: String,
    val air: String,
    val listrik: String,
    val pintuDepan: String,
    val harga: String,
    val lantai: String,
    val pAtap: String,
    val jumlahUnit: String,
    val namaTipe: String,
    val sanitasi: String,
    val dinding: String,
    val plafon: String,
    val rAtap: String
)

typealias ListTipePerumahanGetAll = List<TipePerumahanGetAll>
