package com.ridhoafnidev.project.core_domain.model.auth

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Auth(
	val id: Int,
	val konsumenId: Int,
	val role: String,
	val noHp: String,
	val userId: Int,
	val lastLogin: String,
	val namaLengkap: String,
	val email: String,
	val username: String,
	val alamat: String,
	val isLogin: Boolean
): Parcelable
