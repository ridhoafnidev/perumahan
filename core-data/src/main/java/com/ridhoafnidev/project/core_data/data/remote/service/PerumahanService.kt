package com.ridhoafnidev.project.core_data.data.remote.service

import com.ridhoafnidev.project.core_data.data.remote.response.tipe_rumah.TipeRumahResponse
import retrofit2.Call
import retrofit2.http.GET

interface PerumahanService {
    @GET(GetTipePerumahanAll)
    suspend fun getTipeRumahAll(): TipeRumahResponse

    companion object {
        const val GetTipePerumahanAll = "tipe-rumah-all"
    }
}