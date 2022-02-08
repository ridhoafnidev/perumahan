package com.ridhoafnidev.project.core_data.data

import com.ridhoafnidev.project.core_data.data.remote.*
import com.ridhoafnidev.project.core_data.data.remote.ApiExecutor
import com.ridhoafnidev.project.core_data.data.remote.ApiResult
import com.ridhoafnidev.project.core_data.data.remote.response.CommonResponse
import com.ridhoafnidev.project.core_data.data.remote.response.calon_pemilik.toDomain
import com.ridhoafnidev.project.core_data.data.remote.response.detail_calon_pemilik.toDomain
import com.ridhoafnidev.project.core_data.data.remote.response.detail_tipe_rumah.toDomain
import com.ridhoafnidev.project.core_data.data.remote.response.status_pengajuan.toDomain
import com.ridhoafnidev.project.core_data.data.remote.response.tipe_rumah.toDomain
import com.ridhoafnidev.project.core_data.data.remote.service.PerumahanService
import com.ridhoafnidev.project.core_data.data.remote.toFailedEvent
import com.ridhoafnidev.project.core_domain.model.calon_pemilik.ListCalonPemilik
import com.ridhoafnidev.project.core_domain.model.detail_calon_pemilik.DetailCalonPemilik
import com.ridhoafnidev.project.core_domain.model.detail_tipe_rumah.DetailTipeRumah
import com.ridhoafnidev.project.core_domain.model.status_pengajuan.ListStatusPengajuan
import com.ridhoafnidev.project.core_domain.model.status_pengajuan.StatusPengajuan
import com.ridhoafnidev.project.core_domain.model.tipe_rumah.ListTipePerumahanGetAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PerumahanRepository internal constructor(
    private val apiExecutor: ApiExecutor,
    private val perumahanService: PerumahanService
) {

    fun tipeRumahGetAll(): Flow<ApiEvent<ListTipePerumahanGetAll>> = flow {
        runCatching {
            val apiId = PerumahanService.GetTipePerumahanAll
            val apiResult = apiExecutor.callApi(apiId) {
                perumahanService.getTipeRumahAll()
            }

            val apiEvent: ApiEvent<ListTipePerumahanGetAll> = when (apiResult) {
                is ApiResult.OnFailed -> apiResult.exception.toFailedEvent()
                is ApiResult.OnSuccess -> with(apiResult.response.result) {
                    toDomain().run {
                        if (isEmpty()) {
                            ApiEvent.OnSuccess.fromServer(emptyList())
                        } else {
                            ApiEvent.OnSuccess.fromServer(this)
                        }
                    }
                }
            }

            emit(apiEvent)
        }.onFailure {
            emit(it.toFailedEvent())
        }
    }

    fun getDetailTipeRumah(id: Int): Flow<ApiEvent<DetailTipeRumah>> = flow {
        runCatching {
            val apiId = PerumahanService.GetDetailTipePerumahan
            val apiResult = apiExecutor.callApi(apiId) {
                perumahanService.getDetailPerumahan(id)
            }

            val apiEvent: ApiEvent<DetailTipeRumah> = when (apiResult) {
                is ApiResult.OnFailed -> apiResult
                    .exception
                    .toFailedEvent()
                is ApiResult.OnSuccess -> ApiEvent.OnSuccess
                    .fromServer(apiResult.response.result.toDomain())
            }

            emit(apiEvent)
        }.onFailure {
            emit(it.toFailedEvent())
        }
    }

    fun calonPemilikGetAll(): Flow<ApiEvent<ListCalonPemilik>> = flow {
        runCatching {
            val apiId = PerumahanService.GetCalonPemilikAll
            val apiResult = apiExecutor.callApi(apiId) {
                perumahanService.getCalonPemilikAll()
            }

            val apiEvent: ApiEvent<ListCalonPemilik> = when (apiResult) {
                is ApiResult.OnFailed -> apiResult.exception.toFailedEvent()
                is ApiResult.OnSuccess -> with(apiResult.response.result) {
                    toDomain().run {
                        if (isEmpty()) {
                            ApiEvent.OnSuccess.fromServer(emptyList())
                        } else {
                            ApiEvent.OnSuccess.fromServer(this)
                        }
                    }
                }
            }

            emit(apiEvent)
        }.onFailure {
            emit(it.toFailedEvent())
        }
    }

    fun getDetailCalonPemilik(id: Int): Flow<ApiEvent<DetailCalonPemilik>> = flow {
        runCatching {
            val apiId = PerumahanService.GetDetailCalonPemilik
            val apiResult = apiExecutor.callApi(apiId) {
                perumahanService.getDetailCalonPemilik(id)
            }

            val apiEvent: ApiEvent<DetailCalonPemilik> = when (apiResult) {
                is ApiResult.OnFailed -> apiResult
                    .exception
                    .toFailedEvent()
                is ApiResult.OnSuccess -> ApiEvent.OnSuccess
                    .fromServer(apiResult.response.result.toDomain())
            }

            emit(apiEvent)
        }.onFailure {
            emit(it.toFailedEvent())
        }
    }

    fun statusPengajuanGetAll(): Flow<ApiEvent<ListStatusPengajuan>> = flow {
        runCatching {
            val apiId = PerumahanService.GetStatusPengajuanAll
            val apiResult = apiExecutor.callApi(apiId) {
                perumahanService.getStatusPengajuanAll()
            }

            val apiEvent: ApiEvent<ListStatusPengajuan> = when (apiResult) {
                is ApiResult.OnFailed -> apiResult.exception.toFailedEvent()
                is ApiResult.OnSuccess -> with(apiResult.response.result) {
                    toDomain().run {
                        if (isEmpty()) {
                            ApiEvent.OnSuccess.fromServer(emptyList())
                        } else {
                            ApiEvent.OnSuccess.fromServer(this)
                        }
                    }
                }
            }

            emit(apiEvent)
        }.onFailure {
            emit(it.toFailedEvent())
        }
    }

    fun updateStatusPengajuan(
        id: Int,
        statusPengajuan: StatusPengajuan
    ): Flow<ApiEvent<CommonResponse>> = flow {
        runCatching {
            val apiId = PerumahanService.UpdateStatusPengajuan
            val apiResult = apiExecutor.callApi(apiId) {
                perumahanService.updateStatusPengajuan(id, statusPengajuan.id)
            }

            val apiEvent: ApiEvent<CommonResponse> = when (apiResult) {
                is ApiResult.OnFailed -> apiResult.exception.toFailedEvent()
                is ApiResult.OnSuccess -> with(apiResult.response) {
                    when {
                        this.message.equals(ApiException.FailedResponse.MESSAGE_FAILED, true) -> {
                            ApiException.FailedResponse(message).toFailedEvent()
                        }
                        else -> ApiEvent.OnSuccess.fromServer(this)
                    }
                }
            }

            emit(apiEvent)
        }.onFailure {
            emit(it.toFailedEvent())
        }
    }
}