package com.ridhoafnidev.project

import android.app.Application
import com.ridhoafnidev.project.core_data.di.dataModule
import com.ridhoafnidev.project.core_domain.di.domainModule
import com.ridhoafnidev.project.feature.auth.authModule
import com.ridhoafnidev.project.feature.calonpemilik.calonPemilikModule
import com.ridhoafnidev.project.feature.detail_perumahan.detailPerumahanModule
import com.ridhoafnidev.project.feature.home.homeModule
import com.ridhoafnidev.project.feature.perumahan.createEventModule
import io.armcha.debugBanner.Banner
import io.armcha.debugBanner.DebugBanner
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        when (BuildConfig.BUILD_TYPE) {
            "debug" -> "DEV"
            else -> ""
        }.let {
            val banner = Banner(bannerText = it, bannerColorRes = R.color.colorEnvBanner)
            DebugBanner.init(this, banner)
        }


        when(BuildConfig.BUILD_TYPE) {
            "debug" -> Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    dataModule,
                    authModule,
                    domainModule,
                    createEventModule,
                    homeModule,
                    detailPerumahanModule,
                    calonPemilikModule
                )
            )
        }
    }
}