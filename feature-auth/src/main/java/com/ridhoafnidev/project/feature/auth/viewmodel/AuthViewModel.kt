package com.ridhoafnidev.project.feature.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ridhoafnidev.project.core_data.data.AuthRepository
import com.ridhoafnidev.project.core_data.data.local.entity.toDomain
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_data.data.remote.request.LoginRequest
import com.ridhoafnidev.project.core_data.data.remote.request.RegisterUserRequest
import com.ridhoafnidev.project.core_data.data.remote.response.CommonResponse
import com.ridhoafnidev.project.core_domain.model.auth.Auth
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    var username = ""
    var password = ""

    private val _currentUser = MutableLiveData<Auth>()
    val currentUser: LiveData<Auth>
        get() = _currentUser

    private val _login = MutableLiveData<ApiEvent<Auth>>()
    val login: LiveData<ApiEvent<Auth>>
        get() = _login

    private val _register = MutableLiveData<ApiEvent<CommonResponse>>()
    val register: LiveData<ApiEvent<CommonResponse>>
        get() = _register

    fun getCurrentUser() {
        viewModelScope.launch {
            authRepository.getCurrentUser()
                .collect { _currentUser.value = it.getData()?.toDomain() }
        }
    }

    fun login() {
        val auth = LoginRequest(
            username = username,
            password = password
        )
        viewModelScope.launch {
            authRepository.login(auth)
                .onStart { emit(ApiEvent.OnProgress()) }
                .collect {
                    _login.value = it
                }
        }
    }

    fun register(
        username: String,
        password: String,
        namaLengkap: String,
        alamat: String,
        noHp: String,
        email: String
    ) {
        val registerUserRequest = RegisterUserRequest(
            username = username,
            password = password,
            namaLengkap = namaLengkap,
            alamat = alamat,
            noHp = noHp,
            email = email,
            role = "konsumen"
        )
        viewModelScope.launch {
            authRepository
                .register(registerUserRequest)
                .onStart { emit(ApiEvent.OnProgress()) }
                .collect { _register.value = it }
        }
    }
}