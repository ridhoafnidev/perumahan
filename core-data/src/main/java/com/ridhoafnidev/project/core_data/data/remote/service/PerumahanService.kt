package com.ridhoafnidev.project.core_data.data.remote.service

import com.ridhoafnidev.project.core_data.data.remote.request.CalonPemilikRequest
import com.ridhoafnidev.project.core_data.data.remote.response.CommonResponse
import com.ridhoafnidev.project.core_data.data.remote.response.calon_pemilik.CalonPemilikResponse
import com.ridhoafnidev.project.core_data.data.remote.response.detail_calon_pemilik.DetailCalonPemilikResponse
import com.ridhoafnidev.project.core_data.data.remote.response.detail_tipe_rumah.DetailTipeRumahResponse
import com.ridhoafnidev.project.core_data.data.remote.response.perumahan.PerumahanResponse
import com.ridhoafnidev.project.core_data.data.remote.response.status_pengajuan.StatusPengajuanResponse
import com.ridhoafnidev.project.core_data.data.remote.response.tipe_rumah.TipeRumahResponse
import com.ridhoafnidev.project.core_data.data.remote.service.PerumahanService.Companion.UpdateStatusPengajuan
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface PerumahanService {

    @GET(GetTipePerumahanAll)
    suspend fun getTipeRumahAll(): TipeRumahResponse

    @GET(GetDetailTipePerumahan)
    suspend fun getDetailPerumahan(
        @Path("id")
        id: Int
    ): DetailTipeRumahResponse

    @GET(GetCalonPemilikAll)
    suspend fun getCalonPemilikAll(): CalonPemilikResponse

    @GET(GetCalonPemilikAllByKonsumen)
    suspend fun getCalonPemilikAllByKonsumen(
        @Path("id") id: Int
    ): CalonPemilikResponse

    @GET(GetDetailCalonPemilik)
    suspend fun getDetailCalonPemilik(
        @Path("id")
        id: Int
    ): DetailCalonPemilikResponse

    @GET(GetPerumahan)
    suspend fun getPerumahan(
        @Path("id") id: Int
    ): PerumahanResponse

    @GET(GetStatusPengajuanAll)
    suspend fun getStatusPengajuanAll(): StatusPengajuanResponse

    @FormUrlEncoded
    @PATCH(UpdateStatusPengajuan)
    suspend fun updateStatusPengajuan(
        @Path("id") id: Int,
        @Field("status_pengajuan_id") statusPengajuanId: Int
    ): CommonResponse

    @Multipart
    @POST(InsertCalonPemilik)
    suspend fun insertCalonPemilik(
        @Part("konsumen_id") konsumenId: RequestBody,
        @Part("tipe_perumahan_id") tipePerumahanId: RequestBody,
        @Part("rumah_id") rumahId: RequestBody,
        @Part("jumlah_dp") jumlahDp: RequestBody,
        @Part buktiTransfer: MultipartBody.Part
    ): CommonResponse

    companion object {
        const val GetTipePerumahanAll = "tipe-rumah-all"
        const val GetDetailTipePerumahan = "tipe-rumah/{id}"
        const val InsertCalonPemilik = "calon-pemilik"
        const val GetCalonPemilikAll = "calon-pemilik-all"
        const val GetCalonPemilikAllByKonsumen = "calon-pemilik-all/{id}"
        const val GetDetailCalonPemilik = "calon-pemilik/{id}"
        const val GetPerumahan = "perumahan/{id}"
        const val GetStatusPengajuanAll = "status-pengajuan-all"
        const val UpdateStatusPengajuan = "update-status-calon-pemilik/{id}"
    }
}