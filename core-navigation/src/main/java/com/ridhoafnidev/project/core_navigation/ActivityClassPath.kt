package com.ridhoafnidev.project.core_navigation

import android.content.Context
import android.content.Intent

enum class ActivityClassPath(private val className: String) {
    Auth("$BASE_PATH.auth.AuthActivity"),
    Home("$BASE_PATH.home.HomeActivity"),
    Perumahan("$BASE_PATH.perumahan.PerumahanActivity"),
    Pemilik("$BASE_PATH.calonpemilik.CalonPemilikActivity"),
    DetailPerumahan("$BASE_PATH.detail_perumahan.DetailPerumahanActivity"),
    TipeRumah("$BASE_PATH.tipe_rumah.TipeRumahActivity");

    fun getIntent(context: Context) = Intent(context, Class.forName(className))
}

private const val BASE_PATH = "com.ridhoafnidev.project.feature"
private const val SUB_PATH = "com.ridhoafnidev.project.subfeature"