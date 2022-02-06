package com.ridhoafnidev.project.core_data.di

import android.app.Application
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.ridhoafnidev.project.core_data.BuildConfig.BASE_URL
import com.ridhoafnidev.project.core_data.BuildConfig.DB_NAME
import com.ridhoafnidev.project.core_data.data.AuthRepository
import com.ridhoafnidev.project.core_data.data.EventRepository
import com.ridhoafnidev.project.core_data.data.PerumahanRepository
import com.ridhoafnidev.project.core_domain.repository.IAuthRepository
import com.ridhoafnidev.project.core_domain.repository.IEventDbRepository
import com.ridhoafnidev.project.core_data.data.local.LocalDataSource
import com.ridhoafnidev.project.core_data.data.local.room.CoreDatabase
import com.ridhoafnidev.project.core_data.data.remote.ApiExecutor
import com.ridhoafnidev.project.core_data.data.remote.apiClient
import com.ridhoafnidev.project.core_data.data.remote.httpClient
import com.ridhoafnidev.project.core_data.data.remote.service.PerumahanService
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.gotev.cookiestore.SharedPreferencesCookieStore
import net.gotev.cookiestore.okhttp.JavaNetCookieJar
import okhttp3.CookieJar
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.Koin
import org.koin.dsl.module
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy
import java.net.CookieStore

@Suppress("MaxLineLength")
val Application.dataModule
    get() = module {
        single<CookieStore> { SharedPreferencesCookieStore(applicationContext, "edtc_coding_testing") }
        single<CookieHandler> { CookieManager(get(), CookiePolicy.ACCEPT_ALL) }
        single<CookieJar> { JavaNetCookieJar(get()) }

        single {
            httpClient(
                TIMEOUT,
                HttpLoggingInterceptor.Level.BASIC,
                get(),
                ChuckerInterceptor.Builder(get()).build()
            )
        }

        single {
            Room.databaseBuilder(get(), CoreDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }

        single { Moshi.Builder().build() }
        single { ApiExecutor(get()) }

        single { apiClient<PerumahanService>(BASE_URL, get()) }

        single { get<CoreDatabase>().eventDao() }
        single { LocalDataSource(get()) }
        single<IEventDbRepository> { EventRepository(get()) }
        single<IAuthRepository> { AuthRepository() }
        single { PerumahanRepository(get(), get()) }
    }

private const val TIMEOUT = 30L

/**
 * Clear local database
 */
suspend fun Koin.clearAppData() {
    withContext(Dispatchers.IO) {
        getOrNull<CoreDatabase>()?.clearAllTables()
    }
}

fun CookieHandler.removeAll() {
    (this as CookieManager).cookieStore.removeAll()
}