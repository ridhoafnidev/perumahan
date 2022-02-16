package com.ridhoafnidev.project.core_domain.model.laporan

data class Laporan(
    val nama: String,
    val tipeRumah: String,
    val statusPengajuan: String,
    val id: Int,
    val perumahan: String,
    val tanggalPengajuan: String,
    val alamat: String
)

typealias ListLaporan = List<Laporan>