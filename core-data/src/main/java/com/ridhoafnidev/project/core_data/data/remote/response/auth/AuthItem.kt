package com.ridhoafnidev.project.core_data.data.remote.response.auth

import com.ridhoafnidev.project.core_data.data.local.entity.AuthEntity
import com.ridhoafnidev.project.core_domain.model.auth.Auth
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthItem(

	@Json(name="id")
	val id: Int? = null,

	@Json(name="konsumen_id")
	val konsumenId: Int? = null,

	@Json(name="role")
	val role: String? = null,

	@Json(name="no_hp")
	val noHp: String? = null,

	@Json(name="user_id")
	val userId: Int? = null,

	@Json(name="last_login")
	val lastLogin: String? = null,

	@Json(name="nama_lengkap")
	val namaLengkap: String? = null,

	@Json(name="email")
	val email: String? = null,

	@Json(name="username")
	val username: String? = null,

	@Json(name="alamat")
	val alamat: String? = null
)

fun AuthItem.toDomain(): Auth =
	Auth(
		id = id ?: 0,
		userId = userId ?: 0,
		konsumenId = konsumenId ?: 0,
		role = role ?: "",
		noHp = noHp ?: "",
		lastLogin = lastLogin ?: "",
		namaLengkap = namaLengkap ?: "",
		email = email ?: "",
		username = username ?: "",
		alamat = alamat ?: "",
		isLogin = true
	)

fun AuthItem.toEntity(): AuthEntity =
	AuthEntity(
		id = id ?: 0,
		userId = userId ?: 0,
		konsumenId = konsumenId ?: 0,
		role = role ?: "",
		noHp = noHp ?: "",
		lastLogin = lastLogin ?: "",
		namaLengkap = namaLengkap ?: "",
		email = email ?: "",
		username = username ?: "",
		alamat = alamat ?: "",
		isLogin = true
	)