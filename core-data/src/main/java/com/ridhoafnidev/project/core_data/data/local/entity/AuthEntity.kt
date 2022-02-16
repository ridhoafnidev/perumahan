package com.ridhoafnidev.project.core_data.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ridhoafnidev.project.core_domain.model.auth.Auth
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
data class AuthEntity(
	@PrimaryKey
	@ColumnInfo(name="id")
	val id: Int = 0,

	@ColumnInfo(name="konsumen_id")
	val konsumenId: Int = 0,

	@ColumnInfo(name="role")
	val role: String = "",

	@ColumnInfo(name="no_hp")
	val noHp: String = "",

	@ColumnInfo(name="user_id")
	val userId: Int = 0,

	@ColumnInfo(name="last_login")
	val lastLogin: String = "",

	@ColumnInfo(name="nama_lengkap")
	val namaLengkap: String = "",

	@ColumnInfo(name="email")
	val email: String = "",

	@ColumnInfo(name="username")
	val username: String = "",

	@ColumnInfo(name="alamat")
	val alamat: String = "",

	@ColumnInfo(name="is_login")
	val isLogin: Boolean = false
)

fun AuthEntity.toDomain(): Auth =
	Auth(
		id = id,
		userId = userId,
		konsumenId = konsumenId,
		role = role,
		noHp = noHp,
		lastLogin = lastLogin,
		namaLengkap = namaLengkap,
		email = email,
		username = username,
		alamat = alamat,
		isLogin = isLogin
	)