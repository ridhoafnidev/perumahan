package com.ridhoafnidev.project.core_domain.model

data class TipeRumah(
    val id: String,
    val namaTipe: String,
    val ukuran: Int,
    val pondasi: String,
    val dinding: String,
    val lantai: String,
    val plafon: String,
    val pintuDepan: String,
    val dindingKamarMandi: String,
    val kusen: String,
    val rangkapAtap: String,
    val atap: String,
    val sanitasi: String,
    val listrik: String,
    val air: String,
    val harga: Int,
    val jumlahUnit: Int,
    val photo: String,
)