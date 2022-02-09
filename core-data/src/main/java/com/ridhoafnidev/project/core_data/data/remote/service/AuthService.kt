package com.ridhoafnidev.project.core_data.data.remote.service

import com.ridhoafnidev.project.core_data.data.remote.request.LoginRequest
import com.ridhoafnidev.project.core_data.data.remote.request.RegisterUserRequest
import com.ridhoafnidev.project.core_data.data.remote.response.CommonResponse
import com.ridhoafnidev.project.core_data.data.remote.response.auth.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST(Login)
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @POST(Register)
    suspend fun register(
        @Body registerUserRequest: RegisterUserRequest
    ): CommonResponse

    companion object {
        const val Login = "login"
        const val Register = "register"
    }
}