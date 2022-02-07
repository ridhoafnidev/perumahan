package com.ridhoafnidev.project.core_data.data.remote.response.detail_calon_pemilik

import com.ridhoafnidev.project.core_domain.model.detail_calon_pemilik.DetailCalonPemilik
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailCalonPemilikItem(

	@Json(name="id")
	val id: Int? = null,

	@Json(name="jumlah_dp")
	val jumlahDp: Int? = null,

	@Json(name="tanggal_update_bukti_transfer")
	val tanggalUpdateBuktiTransfer: String? = null,

	@Json(name="konsumen_alamat")
	val konsumenAlamat: String? = null,

	@Json(name="perumahan")
	val perumahan: String? = null,

	@Json(name="konsumen_email")
	val konsumenEmail: String? = null,

	@Json(name="konsumen_nama")
	val konsumenNama: String? = null,

	@Json(name="status_pengajuan")
	val statusPengajuan: String? = null,

	@Json(name="tipe_rumah")
	val tipeRumah: String? = null,

	@Json(name="bukti_transfer")
	val buktiTransfer: String? = null,

	@Json(name="konsumen_no_hp")
	val konsumenNoHp: String? = null,

	@Json(name="tanggal_pengajuan")
	val tanggalPengajuan: String? = null
)

fun DetailCalonPemilikItem.toDomain(): DetailCalonPemilik =
	DetailCalonPemilik(
		id = id ?: 0,
		konsumenNama = konsumenNama ?: "",
		konsumenAlamat = konsumenAlamat ?: "",
		konsumenNoHp = konsumenNoHp ?: "",
		konsumenEmail = konsumenEmail ?: "",
		perumahan = perumahan ?: "",
		statusPengajuan = statusPengajuan ?: "",
		tipeRumah = tipeRumah ?: "",
		jumlahDp = jumlahDp ?: 0,
		buktiTransfer = buktiTransfer ?: "",
		tanggalPengajuan = tanggalPengajuan ?: "",
		tanggalUpdateBuktiTransfer = tanggalUpdateBuktiTransfer ?: ""
	)