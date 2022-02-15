package com.ridhoafnidev.project.core_domain.model.pengguna

data class Pengguna(
    val id: Int,
    val username: String,
    val role: String
)

typealias ListPengguna = List<Pengguna>
