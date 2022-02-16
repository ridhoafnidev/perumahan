package com.ridhoafnidev.project.feature.home.menu

import com.ridhoafnidev.home.R
import com.ridhoafnidev.project.core_domain.model.Menu

object KonsumenMenu {
    val menus = listOf(
        Menu(
            2,
            "Perumahan",
            R.drawable.ic_houses
        ),
        Menu(
            3,
            "Histori Checkout",
            R.drawable.ic_seller
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
            9,
            "Logout",
            R.drawable.ic_logout
        )
    )
}