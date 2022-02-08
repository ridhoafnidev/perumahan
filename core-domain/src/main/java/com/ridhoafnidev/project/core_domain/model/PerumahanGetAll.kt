package com.ridhoafnidev.project.core_domain.model

data class PerumahanGetAll(
    val id: Int,
    val namaPerumahan: String,
    val keterangan: String,
    val luasTanah: Int,
    val alamat: String
)

typealias ListPerumahanGetAll = List<PerumahanGetAll>
