package com.ridhoafnidev.project.core_domain.usecase

import com.ridhoafnidev.project.core_domain.model.Auth
import com.ridhoafnidev.project.core_domain.repository.IAuthRepository
import com.ridhoafnidev.project.core_util.Resource
import kotlinx.coroutines.flow.Flow

class AuthInteractor(private val authRepository: IAuthRepository) : AuthUseCase {
    override fun login(auth: Auth): Flow<Resource<Auth>> = authRepository.login(auth)
}