package com.ridhoafnidev.project.feature.home.menu

import com.ridhoafnidev.home.R
import com.ridhoafnidev.project.core_domain.model.Menu

object AdminMenu {
    val menus = listOf(
        Menu(
            1,
            "Pengguna",
            R.drawable.ic_user
        ),
        Menu(
            2,
            "Perumahan",
            R.drawable.ic_houses
        ),
        Menu(
            3,
            "Calon Pembeli",
            R.drawable.ic_seller
        ),
        Menu(
            4,
            "Tipe Rumah",
            R.drawable.ic_house_type
        ),
        Menu(
            5,
            "Persyaratan",
            R.drawable.ic_requirement
        ),
        Menu(
            6,
            "Simulasi KPR",
            R.drawable.ic_simultion
        ),
        Menu(
            8,
            "Laporan",
            R.drawable.ic_reservation
        ),
        Menu(
            9,
            "Logout",
            R.drawable.ic_logout
        )
    )
}