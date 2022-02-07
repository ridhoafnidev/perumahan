package com.ridhoafnidev.project.core_data.data

import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_data.data.remote.ApiExecutor
import com.ridhoafnidev.project.core_data.data.remote.ApiResult
import com.ridhoafnidev.project.core_data.data.remote.response.calon_pemilik.toDomain
import com.ridhoafnidev.project.core_data.data.remote.response.detail_tipe_rumah.toDomain
import com.ridhoafnidev.project.core_data.data.remote.response.tipe_rumah.toDomain
import com.ridhoafnidev.project.core_data.data.remote.service.PerumahanService
import com.ridhoafnidev.project.core_data.data.remote.toFailedEvent
import com.ridhoafnidev.project.core_domain.model.calon_pemilik.ListCalonPemilik
import com.ridhoafnidev.project.core_domain.model.detail_tipe_rumah.DetailTipeRumah
import com.ridhoafnidev.project.core_domain.model.tipe_rumah.ListPerumahanGetAll
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

}