package com.ridhoafnidev.project.feature.detail_perumahan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ridhoafnidev.project.core_data.data.AuthRepository
import com.ridhoafnidev.project.core_data.data.local.entity.toDomain
import com.ridhoafnidev.project.core_domain.model.auth.Auth
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private var _currentUser = MutableLiveData<Auth>()
    val currentUser: LiveData<Auth>
        get() = _currentUser

    fun getCurrentUser() {
        viewModelScope.launch {
            authRepository.getCurrentUser()
                .collect { _currentUser.value = it.getData()?.toDomain() }
        }
    }
}