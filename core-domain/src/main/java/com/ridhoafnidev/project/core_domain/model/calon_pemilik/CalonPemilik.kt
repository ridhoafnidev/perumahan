package com.ridhoafnidev.project.core_domain.model.calon_pemilik

data class CalonPemilik(
    val nama: String,
    val tipeRumah: String,
    val statusPengajuan: String,
    val id: Int,
    val alamat: String
)

typealias ListCalonPemilik = List<CalonPemilik>
