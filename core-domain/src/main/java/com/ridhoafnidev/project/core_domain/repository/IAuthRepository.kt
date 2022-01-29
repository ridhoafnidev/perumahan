package com.ridhoafnidev.project.core_domain.repository

import com.ridhoafnidev.project.core_domain.model.Auth
import com.ridhoafnidev.project.core_util.Resource
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    fun login(auth: Auth) : Flow<Resource<Auth>>
}