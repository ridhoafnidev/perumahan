package com.ridhoafnidev.project.feature.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ridhoafnidev.project.core_data.data.AuthRepository
import com.ridhoafnidev.project.core_data.data.local.entity.toDomain
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_data.data.remote.request.LoginRequest
import com.ridhoafnidev.project.core_domain.model.auth.Auth
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    var username = ""
    var password = ""

    private var _currentUser = MutableLiveData<Auth>()
    val currentUser: LiveData<Auth>
        get() = _currentUser

    private var _login = MutableLiveData<ApiEvent<Auth>>()
    var login: LiveData<ApiEvent<Auth>> = _login

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
            authRepository.login(auth).collect {
                _login.value = it
            }
        }
    }

}