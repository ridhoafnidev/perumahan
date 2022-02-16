package com.ridhoafnidev.project.core_data.data.remote.response.calon_pemilik

import com.ridhoafnidev.project.core_domain.model.calon_pemilik.CalonPemilik
import com.ridhoafnidev.project.core_domain.model.calon_pemilik.ListCalonPemilik
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CalonPemilikItem(

	@Json(name="nama")
	val nama: String? = null,

	@Json(name="tipe_rumah")
	val tipeRumah: String? = null,

	@Json(name="status_pengajuan")
	val statusPengajuan: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="alamat")
	val alamat: String? = null
)

typealias ListCalonPemilikItem = List<CalonPemilikItem>?

fun ListCalonPemilikItem.toDomain(): ListCalonPemilik =
	this?.map {
		it.toDomain()
	} ?:
	emptyList()

fun CalonPemilikItem.toDomain(): CalonPemilik =
	CalonPemilik(
		id = id ?: 0,
		nama = nama ?: "",
		alamat = alamat ?: "",
		tipeRumah = tipeRumah ?: "",
		statusPengajuan = statusPengajuan ?: ""
	)