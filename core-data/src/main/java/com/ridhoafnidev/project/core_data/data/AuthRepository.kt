package com.ridhoafnidev.project.core_data.data

import com.ridhoafnidev.project.core_data.data.local.room.AuthDao
import com.ridhoafnidev.project.core_data.data.remote.*
import com.ridhoafnidev.project.core_data.data.remote.ApiExecutor
import com.ridhoafnidev.project.core_data.data.remote.ApiResult
import com.ridhoafnidev.project.core_data.data.remote.request.LoginRequest
import com.ridhoafnidev.project.core_data.data.remote.request.RegisterUserRequest
import com.ridhoafnidev.project.core_data.data.remote.response.CommonResponse
import com.ridhoafnidev.project.core_data.data.remote.response.auth.toDomain
import com.ridhoafnidev.project.core_data.data.remote.response.auth.toEntity
import com.ridhoafnidev.project.core_data.data.remote.service.AuthService
import com.ridhoafnidev.project.core_data.data.remote.toFailedEvent
import com.ridhoafnidev.project.core_domain.model.auth.Auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class AuthRepository internal constructor(
    private val apiExecutor: ApiExecutor,
    private val authService: AuthService,
    private val authDao: AuthDao
) {

    fun getCurrentUser() = authDao.selectAuthAsFlow().map {
        ApiEvent.OnSuccess.fromCache(it)
    }

    suspend fun logout() = authDao.truncate()

    fun login(loginRequest: LoginRequest): Flow<ApiEvent<Auth>> = flow {
        runCatching {
            val apiId = AuthService.Login
            val apiResult = apiExecutor.callApi(apiId) {
                authService.login(loginRequest)
            }

            val apiEvent: ApiEvent<Auth> = when (apiResult) {
                is ApiResult.OnFailed -> apiResult.exception.toFailedEvent()
                is ApiResult.OnSuccess -> with(apiResult.response.result) {
                    authDao.replace(this.toEntity())
                    ApiEvent.OnSuccess.fromServer(this.toDomain())
                }
            }

            emit(apiEvent)
        }.onFailure {
            emit(it.toFailedEvent())
        }
    }

    fun register(registerUserRequest: RegisterUserRequest): Flow<ApiEvent<CommonResponse>> = flow {
        runCatching {
            val apiId = AuthService.Register
            val apiResult = apiExecutor.callApi(apiId) {
                authService.register(registerUserRequest)
            }

            val apiEvent: ApiEvent<CommonResponse> = when (apiResult) {
                is ApiResult.OnFailed -> apiResult.exception.toFailedEvent()
                is ApiResult.OnSuccess -> with (apiResult.response) {
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