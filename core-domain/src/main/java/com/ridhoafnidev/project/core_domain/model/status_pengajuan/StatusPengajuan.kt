package com.ridhoafnidev.project.core_domain.model.status_pengajuan

data class StatusPengajuan(
    val id: Int,
    val nama: String
)

typealias ListStatusPengajuan = List<StatusPengajuan>
