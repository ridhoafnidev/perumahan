package com.ridhoafnidev.project.core_data.data.remote.service

import com.ridhoafnidev.project.core_data.data.remote.response.detail_tipe_rumah.DetailTipeRumahResponse
import com.ridhoafnidev.project.core_data.data.remote.response.tipe_rumah.TipeRumahResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PerumahanService {

    @GET(GetTipePerumahanAll)
    suspend fun getTipeRumahAll(): TipeRumahResponse

    @GET(GetDetailTipePerumahan)
    suspend fun getDetailPerumahan(
        @Path("id")
        id: Int
    ): DetailTipeRumahResponse

    companion object {
        const val GetTipePerumahanAll = "tipe-rumah-all"
        const val GetDetailTipePerumahan = "tipe-rumah/{id}"
    }
}