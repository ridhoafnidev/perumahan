package com.ridhoafnidev.project.core_domain.model.tipe_rumah

data class PerumahanGetAll(
    val id: Int,
    val namaPerumahan: String,
    val keterangan: String,
    val luasTanah: Int,
    val alamat: String
)

typealias ListPerumahanGetAll = List<PerumahanGetAll>
