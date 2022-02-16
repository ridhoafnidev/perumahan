package com.ridhoafnidev.project.core_data.data.remote.response.laporan

import com.ridhoafnidev.project.core_domain.model.laporan.Laporan
import com.ridhoafnidev.project.core_domain.model.laporan.ListLaporan
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LaporanItem(

	@Json(name="nama")
	val nama: String? = null,

	@Json(name="tipe_rumah")
	val tipeRumah: String? = null,

	@Json(name="status_pengajuan")
	val statusPengajuan: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="perumahan")
	val perumahan: String? = null,

	@Json(name="tanggal_pengajuan")
	val tanggalPengajuan: String? = null,

	@Json(name="alamat")
	val alamat: String? = null
)

typealias ListLaporanItem = List<LaporanItem>?

fun ListLaporanItem.toDomain(): ListLaporan =
	this?.map {
		it.toDomain()
	} ?:
	emptyList()

fun LaporanItem.toDomain(): Laporan =
	Laporan(
		id = id ?: 0,
		nama = nama ?: "",
		tipeRumah = tipeRumah ?: "",
		statusPengajuan = statusPengajuan ?: "",
		perumahan = perumahan ?: "",
		tanggalPengajuan = tanggalPengajuan ?: "",
		alamat = alamat ?: ""
	)