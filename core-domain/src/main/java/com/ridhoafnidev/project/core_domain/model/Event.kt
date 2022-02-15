package com.ridhoafnidev.project.core_domain.model

data class Event(
    val id: Int? = null, val name: String = "", val location: String = "", val startDate: String = "",
    val startTime: String = "", val endDate: String = "", val endTime: String = "", val image: String = "",
    val description: String = "", val poweredBy: String = "")

typealias ListEvents = List<Event>

@Suppress("MagicNumber")
enum class MenuStatus(val value: Int){
    Pengguna(1), Perumahan(2), CalonPembeli(3), TipeRumah(4),
    Persyaratan(5), SimulasiKPR(6), InfoPerumahan(7), Laporan(8), Logout(9);

    operator fun invoke() = value
}