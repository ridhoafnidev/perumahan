package com.ridhoafnidev.project.core_domain.model

data class FotoGetAll(
    val id: Int,
    val tipeRumahId: Int,
    val foto: String
)

typealias ListFotoGetAll = List<FotoGetAll>
