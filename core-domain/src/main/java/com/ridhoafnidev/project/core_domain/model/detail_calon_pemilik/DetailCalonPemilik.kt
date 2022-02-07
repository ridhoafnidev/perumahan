package com.ridhoafnidev.project.core_domain.model.detail_calon_pemilik

data class DetailCalonPemilik(
    val id: Int,
    val jumlahDp: Int,
    val tanggalUpdateBuktiTransfer: String,
    val konsumenAlamat: String,
    val perumahan: String,
    val konsumenEmail: String,
    val konsumenNama: String,
    val statusPengajuan: String,
    val tipeRumah: String,
    val buktiTransfer: String,
    val konsumenNoHp: String,
    val tanggalPengajuan: String
)